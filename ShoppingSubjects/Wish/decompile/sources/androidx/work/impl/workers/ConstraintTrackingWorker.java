package androidx.work.impl.workers;

import android.text.TextUtils;
import android.util.Log;
import androidx.work.Worker;
import androidx.work.Worker.WorkerResult;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.WorkerWrapper;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import java.util.Collections;
import java.util.List;

public class ConstraintTrackingWorker extends Worker implements WorkConstraintsCallback {
    private boolean mAreConstraintsUnmet = false;
    private Worker mDelegate;
    private final Object mLock = new Object();

    public void onAllConstraintsMet(List<String> list) {
    }

    public WorkerResult doWork() {
        String string = getInputData().getString("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME", null);
        if (TextUtils.isEmpty(string)) {
            Log.d("ConstraintTrkngWrkr", "No worker to delegate to.");
            return WorkerResult.FAILURE;
        }
        this.mDelegate = WorkerWrapper.workerFromClassName(getApplicationContext(), string, getId(), getExtras());
        if (this.mDelegate == null) {
            Log.d("ConstraintTrkngWrkr", "No worker to delegate to.");
            return WorkerResult.FAILURE;
        }
        WorkSpec workSpec = getWorkDatabase().workSpecDao().getWorkSpec(getId());
        if (workSpec == null) {
            return WorkerResult.FAILURE;
        }
        WorkConstraintsTracker workConstraintsTracker = new WorkConstraintsTracker(getApplicationContext(), this);
        workConstraintsTracker.replace(Collections.singletonList(workSpec));
        if (workConstraintsTracker.areAllConstraintsMet(getId())) {
            Log.d("ConstraintTrkngWrkr", String.format("Constraints met for delegate %s", new Object[]{string}));
            try {
                WorkerResult doWork = this.mDelegate.doWork();
                synchronized (this.mLock) {
                    if (this.mAreConstraintsUnmet) {
                        WorkerResult workerResult = WorkerResult.RETRY;
                        return workerResult;
                    }
                    setOutputData(this.mDelegate.getOutputData());
                    return doWork;
                }
            } catch (Throwable th) {
                Log.d("ConstraintTrkngWrkr", String.format("Delegated worker %s threw a runtime exception.", new Object[]{string}), th);
                synchronized (this.mLock) {
                    if (!this.mAreConstraintsUnmet) {
                        return WorkerResult.FAILURE;
                    }
                    Log.d("ConstraintTrkngWrkr", "Constraints were unmet, Retrying.");
                    return WorkerResult.RETRY;
                }
            }
        } else {
            Log.d("ConstraintTrkngWrkr", String.format("Constraints not met for delegate %s. Requesting retry.", new Object[]{string}));
            return WorkerResult.RETRY;
        }
    }

    public WorkDatabase getWorkDatabase() {
        return WorkManagerImpl.getInstance().getWorkDatabase();
    }

    public void onAllConstraintsNotMet(List<String> list) {
        Log.d("ConstraintTrkngWrkr", String.format("Constraints changed for %s", new Object[]{list}));
        synchronized (this.mLock) {
            this.mAreConstraintsUnmet = true;
        }
    }
}
