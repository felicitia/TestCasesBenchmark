package com.facebook.network.connectionclass;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class ConnectionClassManager {
    private AtomicReference<ConnectionQuality> mCurrentBandwidthConnectionQuality;
    private ExponentialGeometricAverage mDownloadBandwidth;
    private volatile boolean mInitiateStateChange;
    private ArrayList<ConnectionClassStateChangeListener> mListenerList;
    private AtomicReference<ConnectionQuality> mNextBandwidthConnectionQuality;
    private int mSampleCounter;

    private static class ConnectionClassManagerHolder {
        public static final ConnectionClassManager instance = new ConnectionClassManager();
    }

    public interface ConnectionClassStateChangeListener {
        void onBandwidthStateChange(ConnectionQuality connectionQuality);
    }

    public static ConnectionClassManager getInstance() {
        return ConnectionClassManagerHolder.instance;
    }

    private ConnectionClassManager() {
        this.mDownloadBandwidth = new ExponentialGeometricAverage(0.05d);
        this.mInitiateStateChange = false;
        this.mCurrentBandwidthConnectionQuality = new AtomicReference<>(ConnectionQuality.UNKNOWN);
        this.mListenerList = new ArrayList<>();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0055, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0070, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0075, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void addBandwidth(long r5, long r7) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            int r2 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r2 == 0) goto L_0x0074
            double r5 = (double) r5
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r5 = r5 * r0
            double r7 = (double) r7
            double r5 = r5 / r7
            r7 = 4620693217682128896(0x4020000000000000, double:8.0)
            double r5 = r5 * r7
            r7 = 4621819117588971520(0x4024000000000000, double:10.0)
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0019
            goto L_0x0074
        L_0x0019:
            com.facebook.network.connectionclass.ExponentialGeometricAverage r7 = r4.mDownloadBandwidth     // Catch:{ all -> 0x0071 }
            r7.addMeasurement(r5)     // Catch:{ all -> 0x0071 }
            boolean r5 = r4.mInitiateStateChange     // Catch:{ all -> 0x0071 }
            r6 = 1
            if (r5 == 0) goto L_0x0056
            int r5 = r4.mSampleCounter     // Catch:{ all -> 0x0071 }
            int r5 = r5 + r6
            r4.mSampleCounter = r5     // Catch:{ all -> 0x0071 }
            com.facebook.network.connectionclass.ConnectionQuality r5 = r4.getCurrentBandwidthQuality()     // Catch:{ all -> 0x0071 }
            java.util.concurrent.atomic.AtomicReference<com.facebook.network.connectionclass.ConnectionQuality> r7 = r4.mNextBandwidthConnectionQuality     // Catch:{ all -> 0x0071 }
            java.lang.Object r7 = r7.get()     // Catch:{ all -> 0x0071 }
            r8 = 0
            if (r5 == r7) goto L_0x0039
            r4.mInitiateStateChange = r8     // Catch:{ all -> 0x0071 }
            r4.mSampleCounter = r6     // Catch:{ all -> 0x0071 }
        L_0x0039:
            int r5 = r4.mSampleCounter     // Catch:{ all -> 0x0071 }
            double r0 = (double) r5     // Catch:{ all -> 0x0071 }
            r2 = 4617315517961601024(0x4014000000000000, double:5.0)
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 < 0) goto L_0x0054
            r4.mInitiateStateChange = r8     // Catch:{ all -> 0x0071 }
            r4.mSampleCounter = r6     // Catch:{ all -> 0x0071 }
            java.util.concurrent.atomic.AtomicReference<com.facebook.network.connectionclass.ConnectionQuality> r5 = r4.mCurrentBandwidthConnectionQuality     // Catch:{ all -> 0x0071 }
            java.util.concurrent.atomic.AtomicReference<com.facebook.network.connectionclass.ConnectionQuality> r6 = r4.mNextBandwidthConnectionQuality     // Catch:{ all -> 0x0071 }
            java.lang.Object r6 = r6.get()     // Catch:{ all -> 0x0071 }
            r5.set(r6)     // Catch:{ all -> 0x0071 }
            r4.notifyListeners()     // Catch:{ all -> 0x0071 }
        L_0x0054:
            monitor-exit(r4)
            return
        L_0x0056:
            java.util.concurrent.atomic.AtomicReference<com.facebook.network.connectionclass.ConnectionQuality> r5 = r4.mCurrentBandwidthConnectionQuality     // Catch:{ all -> 0x0071 }
            java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x0071 }
            com.facebook.network.connectionclass.ConnectionQuality r7 = r4.getCurrentBandwidthQuality()     // Catch:{ all -> 0x0071 }
            if (r5 == r7) goto L_0x006f
            r4.mInitiateStateChange = r6     // Catch:{ all -> 0x0071 }
            java.util.concurrent.atomic.AtomicReference r5 = new java.util.concurrent.atomic.AtomicReference     // Catch:{ all -> 0x0071 }
            com.facebook.network.connectionclass.ConnectionQuality r6 = r4.getCurrentBandwidthQuality()     // Catch:{ all -> 0x0071 }
            r5.<init>(r6)     // Catch:{ all -> 0x0071 }
            r4.mNextBandwidthConnectionQuality = r5     // Catch:{ all -> 0x0071 }
        L_0x006f:
            monitor-exit(r4)
            return
        L_0x0071:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L_0x0074:
            monitor-exit(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.network.connectionclass.ConnectionClassManager.addBandwidth(long, long):void");
    }

    public synchronized ConnectionQuality getCurrentBandwidthQuality() {
        if (this.mDownloadBandwidth == null) {
            return ConnectionQuality.UNKNOWN;
        }
        return mapBandwidthQuality(this.mDownloadBandwidth.getAverage());
    }

    private ConnectionQuality mapBandwidthQuality(double d) {
        if (d < 0.0d) {
            return ConnectionQuality.UNKNOWN;
        }
        if (d < 150.0d) {
            return ConnectionQuality.POOR;
        }
        if (d < 550.0d) {
            return ConnectionQuality.MODERATE;
        }
        if (d < 2000.0d) {
            return ConnectionQuality.GOOD;
        }
        return ConnectionQuality.EXCELLENT;
    }

    public synchronized double getDownloadKBitsPerSecond() {
        return this.mDownloadBandwidth == null ? -1.0d : this.mDownloadBandwidth.getAverage();
    }

    private void notifyListeners() {
        int size = this.mListenerList.size();
        for (int i = 0; i < size; i++) {
            ((ConnectionClassStateChangeListener) this.mListenerList.get(i)).onBandwidthStateChange((ConnectionQuality) this.mCurrentBandwidthConnectionQuality.get());
        }
    }
}
