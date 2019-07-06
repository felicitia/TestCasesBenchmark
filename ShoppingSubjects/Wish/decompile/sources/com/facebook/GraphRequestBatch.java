package com.facebook;

import android.os.Handler;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GraphRequestBatch extends AbstractList<GraphRequest> {
    private static AtomicInteger idGenerator = new AtomicInteger();
    private String batchApplicationId;
    private Handler callbackHandler;
    private List<Callback> callbacks;
    private final String id;
    private List<GraphRequest> requests;
    private int timeoutInMilliseconds;

    public interface Callback {
        void onBatchCompleted(GraphRequestBatch graphRequestBatch);
    }

    public interface OnProgressCallback extends Callback {
        void onBatchProgress(GraphRequestBatch graphRequestBatch, long j, long j2);
    }

    public GraphRequestBatch() {
        this.requests = new ArrayList();
        this.timeoutInMilliseconds = 0;
        this.id = Integer.valueOf(idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList();
        this.requests = new ArrayList();
    }

    public GraphRequestBatch(Collection<GraphRequest> collection) {
        this.requests = new ArrayList();
        this.timeoutInMilliseconds = 0;
        this.id = Integer.valueOf(idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList();
        this.requests = new ArrayList(collection);
    }

    public GraphRequestBatch(GraphRequest... graphRequestArr) {
        this.requests = new ArrayList();
        this.timeoutInMilliseconds = 0;
        this.id = Integer.valueOf(idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList();
        this.requests = Arrays.asList(graphRequestArr);
    }

    public int getTimeout() {
        return this.timeoutInMilliseconds;
    }

    public void addCallback(Callback callback) {
        if (!this.callbacks.contains(callback)) {
            this.callbacks.add(callback);
        }
    }

    public final boolean add(GraphRequest graphRequest) {
        return this.requests.add(graphRequest);
    }

    public final void add(int i, GraphRequest graphRequest) {
        this.requests.add(i, graphRequest);
    }

    public final void clear() {
        this.requests.clear();
    }

    public final GraphRequest get(int i) {
        return (GraphRequest) this.requests.get(i);
    }

    public final GraphRequest remove(int i) {
        return (GraphRequest) this.requests.remove(i);
    }

    public final GraphRequest set(int i, GraphRequest graphRequest) {
        return (GraphRequest) this.requests.set(i, graphRequest);
    }

    public final int size() {
        return this.requests.size();
    }

    /* access modifiers changed from: 0000 */
    public final String getId() {
        return this.id;
    }

    /* access modifiers changed from: 0000 */
    public final Handler getCallbackHandler() {
        return this.callbackHandler;
    }

    /* access modifiers changed from: 0000 */
    public final void setCallbackHandler(Handler handler) {
        this.callbackHandler = handler;
    }

    /* access modifiers changed from: 0000 */
    public final List<GraphRequest> getRequests() {
        return this.requests;
    }

    /* access modifiers changed from: 0000 */
    public final List<Callback> getCallbacks() {
        return this.callbacks;
    }

    public final String getBatchApplicationId() {
        return this.batchApplicationId;
    }

    public final List<GraphResponse> executeAndWait() {
        return executeAndWaitImpl();
    }

    public final GraphRequestAsyncTask executeAsync() {
        return executeAsyncImpl();
    }

    /* access modifiers changed from: 0000 */
    public List<GraphResponse> executeAndWaitImpl() {
        return GraphRequest.executeBatchAndWait(this);
    }

    /* access modifiers changed from: 0000 */
    public GraphRequestAsyncTask executeAsyncImpl() {
        return GraphRequest.executeBatchAsync(this);
    }
}
