package org.apache.commons.lang3.concurrent;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimedSemaphore {
    public static final int NO_LIMIT = 0;
    private static final int THREAD_POOL_SIZE = 1;
    private int acquireCount;
    private final ScheduledExecutorService executorService;
    private int lastCallsPerPeriod;
    private int limit;
    private final boolean ownExecutor;
    private final long period;
    private long periodCount;
    private boolean shutdown;
    private ScheduledFuture<?> task;
    private long totalAcquireCount;
    private final TimeUnit unit;

    public TimedSemaphore(long j, TimeUnit timeUnit, int i) {
        this(null, j, timeUnit, i);
    }

    public TimedSemaphore(ScheduledExecutorService scheduledExecutorService, long j, TimeUnit timeUnit, int i) {
        if (j <= 0) {
            throw new IllegalArgumentException("Time period must be greater 0!");
        }
        this.period = j;
        this.unit = timeUnit;
        if (scheduledExecutorService != null) {
            this.executorService = scheduledExecutorService;
            this.ownExecutor = false;
        } else {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
            scheduledThreadPoolExecutor.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
            scheduledThreadPoolExecutor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
            this.executorService = scheduledThreadPoolExecutor;
            this.ownExecutor = true;
        }
        setLimit(i);
    }

    public final synchronized int getLimit() {
        return this.limit;
    }

    public final synchronized void setLimit(int i) {
        this.limit = i;
    }

    public synchronized void shutdown() {
        if (!this.shutdown) {
            if (this.ownExecutor) {
                getExecutorService().shutdownNow();
            }
            if (this.task != null) {
                this.task.cancel(false);
            }
            this.shutdown = true;
        }
    }

    public synchronized boolean isShutdown() {
        return this.shutdown;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void acquire() throws java.lang.InterruptedException {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.isShutdown()     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x000f
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x003b }
            java.lang.String r1 = "TimedSemaphore is shut down!"
            r0.<init>(r1)     // Catch:{ all -> 0x003b }
            throw r0     // Catch:{ all -> 0x003b }
        L_0x000f:
            java.util.concurrent.ScheduledFuture<?> r0 = r3.task     // Catch:{ all -> 0x003b }
            if (r0 != 0) goto L_0x0019
            java.util.concurrent.ScheduledFuture r0 = r3.startTimer()     // Catch:{ all -> 0x003b }
            r3.task = r0     // Catch:{ all -> 0x003b }
        L_0x0019:
            int r0 = r3.getLimit()     // Catch:{ all -> 0x003b }
            r1 = 1
            if (r0 <= 0) goto L_0x002b
            int r0 = r3.acquireCount     // Catch:{ all -> 0x003b }
            int r2 = r3.getLimit()     // Catch:{ all -> 0x003b }
            if (r0 >= r2) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r0 = 0
            goto L_0x002c
        L_0x002b:
            r0 = r1
        L_0x002c:
            if (r0 != 0) goto L_0x0032
            r3.wait()     // Catch:{ all -> 0x003b }
            goto L_0x0037
        L_0x0032:
            int r2 = r3.acquireCount     // Catch:{ all -> 0x003b }
            int r2 = r2 + r1
            r3.acquireCount = r2     // Catch:{ all -> 0x003b }
        L_0x0037:
            if (r0 == 0) goto L_0x0019
            monitor-exit(r3)
            return
        L_0x003b:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.concurrent.TimedSemaphore.acquire():void");
    }

    public synchronized int getLastAcquiresPerPeriod() {
        return this.lastCallsPerPeriod;
    }

    public synchronized int getAcquireCount() {
        return this.acquireCount;
    }

    public synchronized int getAvailablePermits() {
        return getLimit() - getAcquireCount();
    }

    public synchronized double getAverageCallsPerPeriod() {
        return this.periodCount == 0 ? 0.0d : ((double) this.totalAcquireCount) / ((double) this.periodCount);
    }

    public long getPeriod() {
        return this.period;
    }

    public TimeUnit getUnit() {
        return this.unit;
    }

    /* access modifiers changed from: protected */
    public ScheduledExecutorService getExecutorService() {
        return this.executorService;
    }

    /* access modifiers changed from: protected */
    public ScheduledFuture<?> startTimer() {
        return getExecutorService().scheduleAtFixedRate(new Runnable() {
            public void run() {
                TimedSemaphore.this.endOfPeriod();
            }
        }, getPeriod(), getPeriod(), getUnit());
    }

    /* access modifiers changed from: 0000 */
    public synchronized void endOfPeriod() {
        this.lastCallsPerPeriod = this.acquireCount;
        this.totalAcquireCount += (long) this.acquireCount;
        this.periodCount++;
        this.acquireCount = 0;
        notifyAll();
    }
}
