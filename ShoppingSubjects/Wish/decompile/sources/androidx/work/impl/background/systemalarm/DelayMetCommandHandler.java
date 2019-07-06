package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.WakeLocks;
import java.util.Collections;
import java.util.List;

public class DelayMetCommandHandler implements ExecutionListener, TimeLimitExceededListener, WorkConstraintsCallback {
    private final Context mContext;
    private final SystemAlarmDispatcher mDispatcher;
    private boolean mHasConstraints = false;
    private boolean mHasPendingStopWorkCommand = false;
    private final Object mLock = new Object();
    private final int mStartId;
    private WakeLock mWakeLock;
    private final WorkConstraintsTracker mWorkConstraintsTracker = new WorkConstraintsTracker(this.mContext, this);
    private final String mWorkSpecId;

    DelayMetCommandHandler(Context context, int i, String str, SystemAlarmDispatcher systemAlarmDispatcher) {
        this.mContext = context;
        this.mStartId = i;
        this.mDispatcher = systemAlarmDispatcher;
        this.mWorkSpecId = str;
    }

    public void onAllConstraintsMet(List<String> list) {
        Log.d("DelayMetCommandHandler", String.format("onAllConstraintsMet for %s", new Object[]{this.mWorkSpecId}));
        if (this.mDispatcher.getProcessor().startWork(this.mWorkSpecId)) {
            this.mDispatcher.getWorkTimer().startTimer(this.mWorkSpecId, 600000, this);
        } else {
            cleanUp();
        }
    }

    public void onExecuted(String str, boolean z, boolean z2) {
        Log.d("DelayMetCommandHandler", String.format("onExecuted %s, %s, %s", new Object[]{str, Boolean.valueOf(z), Boolean.valueOf(z2)}));
        cleanUp();
        if (this.mHasConstraints) {
            this.mDispatcher.postOnMainThread(new AddRunnable(this.mDispatcher, CommandHandler.createConstraintsChangedIntent(this.mContext), this.mStartId));
        }
    }

    public void onTimeLimitExceeded(String str) {
        Log.d("DelayMetCommandHandler", String.format("Exceeded time limits on execution for %s", new Object[]{str}));
        stopWork();
    }

    public void onAllConstraintsNotMet(List<String> list) {
        stopWork();
    }

    /* access modifiers changed from: 0000 */
    public void handleProcessWork() {
        this.mWakeLock = WakeLocks.newWakeLock(this.mContext, String.format("%s (%s)", new Object[]{this.mWorkSpecId, Integer.valueOf(this.mStartId)}));
        Log.d("DelayMetCommandHandler", String.format("Acquiring wakelock %s for WorkSpec %s", new Object[]{this.mWakeLock, this.mWorkSpecId}));
        this.mWakeLock.acquire();
        WorkSpec workSpec = this.mDispatcher.getWorkManager().getWorkDatabase().workSpecDao().getWorkSpec(this.mWorkSpecId);
        this.mHasConstraints = workSpec.hasConstraints();
        if (!this.mHasConstraints) {
            Log.d("DelayMetCommandHandler", String.format("No constraints for %s", new Object[]{this.mWorkSpecId}));
            onAllConstraintsMet(Collections.singletonList(this.mWorkSpecId));
            return;
        }
        this.mWorkConstraintsTracker.replace(Collections.singletonList(workSpec));
    }

    private void stopWork() {
        synchronized (this.mLock) {
            if (!this.mHasPendingStopWorkCommand) {
                Log.d("DelayMetCommandHandler", String.format("Stopping work for workspec %s", new Object[]{this.mWorkSpecId}));
                this.mDispatcher.postOnMainThread(new AddRunnable(this.mDispatcher, CommandHandler.createStopWorkIntent(this.mContext, this.mWorkSpecId), this.mStartId));
                if (this.mDispatcher.getProcessor().isEnqueued(this.mWorkSpecId)) {
                    Log.d("DelayMetCommandHandler", String.format("WorkSpec %s needs to be rescheduled", new Object[]{this.mWorkSpecId}));
                    this.mDispatcher.postOnMainThread(new AddRunnable(this.mDispatcher, CommandHandler.createScheduleWorkIntent(this.mContext, this.mWorkSpecId), this.mStartId));
                } else {
                    Log.d("DelayMetCommandHandler", String.format("Processor does not have WorkSpec %s. No need to reschedule ", new Object[]{this.mWorkSpecId}));
                }
                this.mHasPendingStopWorkCommand = true;
            } else {
                Log.d("DelayMetCommandHandler", String.format("Already stopped work for %s", new Object[]{this.mWorkSpecId}));
            }
        }
    }

    private void cleanUp() {
        synchronized (this.mLock) {
            this.mDispatcher.getWorkTimer().stopTimer(this.mWorkSpecId);
            if (this.mWakeLock != null && this.mWakeLock.isHeld()) {
                Log.d("DelayMetCommandHandler", String.format("Releasing wakelock %s for WorkSpec %s", new Object[]{this.mWakeLock, this.mWorkSpecId}));
                this.mWakeLock.release();
            }
        }
    }
}
