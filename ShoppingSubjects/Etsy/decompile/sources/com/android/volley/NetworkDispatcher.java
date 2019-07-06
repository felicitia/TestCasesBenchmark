package com.android.volley;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.Process;
import java.util.concurrent.BlockingQueue;

public class NetworkDispatcher extends Thread {
    private final Cache mCache;
    private final ResponseDelivery mDelivery;
    private final Network mNetwork;
    private final BlockingQueue<Request<?>> mQueue;
    private volatile boolean mQuit = false;

    public NetworkDispatcher(BlockingQueue<Request<?>> blockingQueue, Network network, Cache cache, ResponseDelivery responseDelivery) {
        this.mQueue = blockingQueue;
        this.mNetwork = network;
        this.mCache = cache;
        this.mDelivery = responseDelivery;
    }

    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    @TargetApi(14)
    private void addTrafficStatsTag(Request<?> request) {
        if (VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
        }
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                Request request = (Request) this.mQueue.take();
                try {
                    request.addMarker("network-queue-take");
                    if (request.isCanceled()) {
                        request.finish("network-discard-cancelled");
                    } else {
                        addTrafficStatsTag(request);
                        NetworkResponse performRequest = this.mNetwork.performRequest(request);
                        request.addMarker("network-http-complete");
                        if (!performRequest.notModified || !request.hasHadResponseDelivered()) {
                            Response parseNetworkResponse = request.parseNetworkResponse(performRequest);
                            request.addMarker("network-parse-complete");
                            if (request.shouldCache() && parseNetworkResponse.cacheEntry != null) {
                                this.mCache.put(request.getCacheKey(), parseNetworkResponse.cacheEntry);
                                request.addMarker("network-cache-written");
                            }
                            request.markDelivered();
                            this.mDelivery.postResponse(request, parseNetworkResponse);
                        } else {
                            request.finish("not-modified");
                        }
                    }
                } catch (VolleyError e) {
                    parseAndDeliverNetworkError(request, e);
                } catch (Exception e2) {
                    VolleyLog.e(e2, "Unhandled exception %s", e2.toString());
                    this.mDelivery.postError(request, new VolleyError((Throwable) e2));
                }
            } catch (InterruptedException unused) {
                if (this.mQuit) {
                    return;
                }
            }
        }
    }

    private void parseAndDeliverNetworkError(Request<?> request, VolleyError volleyError) {
        this.mDelivery.postError(request, request.parseNetworkError(volleyError));
    }
}
