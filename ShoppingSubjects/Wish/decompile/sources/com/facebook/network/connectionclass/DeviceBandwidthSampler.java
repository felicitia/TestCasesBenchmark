package com.facebook.network.connectionclass;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.atomic.AtomicInteger;

public class DeviceBandwidthSampler {
    /* access modifiers changed from: private */
    public final ConnectionClassManager mConnectionClassManager;
    private Handler mHandler;
    /* access modifiers changed from: private */
    public long mLastTimeReading;
    private AtomicInteger mSamplingCounter;
    private HandlerThread mThread;

    private static class DeviceBandwidthSamplerHolder {
        public static final DeviceBandwidthSampler instance = new DeviceBandwidthSampler(ConnectionClassManager.getInstance());
    }

    private class SamplingHandler extends Handler {
        public SamplingHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    addSample();
                    sendEmptyMessageDelayed(1, 1000);
                    return;
                case 2:
                    addSample();
                    removeMessages(1);
                    return;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unknown what=");
                    sb.append(message.what);
                    throw new IllegalArgumentException(sb.toString());
            }
        }

        private void addSample() {
            long parseDataUsageForUidAndTag = QTagParser.getInstance().parseDataUsageForUidAndTag(Process.myUid());
            synchronized (this) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (parseDataUsageForUidAndTag != -1) {
                    DeviceBandwidthSampler.this.mConnectionClassManager.addBandwidth(parseDataUsageForUidAndTag, elapsedRealtime - DeviceBandwidthSampler.this.mLastTimeReading);
                }
                DeviceBandwidthSampler.this.mLastTimeReading = elapsedRealtime;
            }
        }
    }

    public static DeviceBandwidthSampler getInstance() {
        return DeviceBandwidthSamplerHolder.instance;
    }

    private DeviceBandwidthSampler(ConnectionClassManager connectionClassManager) {
        this.mConnectionClassManager = connectionClassManager;
        this.mSamplingCounter = new AtomicInteger();
        this.mThread = new HandlerThread("ParseThread");
        this.mThread.start();
        this.mHandler = new SamplingHandler(this.mThread.getLooper());
    }

    public void startSampling() {
        if (this.mSamplingCounter.getAndIncrement() == 0) {
            this.mHandler.sendEmptyMessage(1);
            this.mLastTimeReading = SystemClock.elapsedRealtime();
        }
    }

    public void stopSampling() {
        if (this.mSamplingCounter.decrementAndGet() == 0) {
            this.mHandler.sendEmptyMessage(2);
        }
    }

    public boolean isSampling() {
        return this.mSamplingCounter.get() != 0;
    }
}
