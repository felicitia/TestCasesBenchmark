package androidx.work.impl.background.systemalarm;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class WorkTimer {
    private final ScheduledExecutorService mExecutorService = Executors.newSingleThreadScheduledExecutor();
    /* access modifiers changed from: private */
    public final Map<String, TimeLimitExceededListener> mListeners = new HashMap();
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    public final Map<String, WorkTimerRunnable> mTimerMap = new HashMap();

    interface TimeLimitExceededListener {
        void onTimeLimitExceeded(String str);
    }

    static class WorkTimerRunnable implements Runnable {
        private final String mWorkSpecId;
        private final WorkTimer mWorkTimer;

        WorkTimerRunnable(WorkTimer workTimer, String str) {
            this.mWorkTimer = workTimer;
            this.mWorkSpecId = str;
        }

        public void run() {
            synchronized (this.mWorkTimer.mLock) {
                if (this.mWorkTimer.mTimerMap.containsKey(this.mWorkSpecId)) {
                    this.mWorkTimer.mTimerMap.remove(this.mWorkSpecId);
                    TimeLimitExceededListener timeLimitExceededListener = (TimeLimitExceededListener) this.mWorkTimer.mListeners.remove(this.mWorkSpecId);
                    if (timeLimitExceededListener != null) {
                        timeLimitExceededListener.onTimeLimitExceeded(this.mWorkSpecId);
                    }
                } else {
                    Log.d("WrkTimerRunnable", String.format("Timer with %s is already marked as complete.", new Object[]{this.mWorkSpecId}));
                }
            }
        }
    }

    WorkTimer() {
    }

    /* access modifiers changed from: 0000 */
    public void startTimer(String str, long j, TimeLimitExceededListener timeLimitExceededListener) {
        synchronized (this.mLock) {
            Log.d("WorkTimer", String.format("Starting timer for %s", new Object[]{str}));
            stopTimer(str);
            WorkTimerRunnable workTimerRunnable = new WorkTimerRunnable(this, str);
            this.mTimerMap.put(str, workTimerRunnable);
            this.mListeners.put(str, timeLimitExceededListener);
            this.mExecutorService.schedule(workTimerRunnable, j, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: 0000 */
    public void stopTimer(String str) {
        synchronized (this.mLock) {
            if (this.mTimerMap.containsKey(str)) {
                Log.d("WorkTimer", String.format("Stopping timer for %s", new Object[]{str}));
                this.mTimerMap.remove(str);
                this.mListeners.remove(str);
            }
        }
    }
}
