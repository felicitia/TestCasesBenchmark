package androidx.work.impl.background.greedy;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.work.State;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.List;

public class GreedyScheduler implements ExecutionListener, Scheduler, WorkConstraintsCallback {
    private List<WorkSpec> mConstrainedWorkSpecs = new ArrayList();
    private WorkConstraintsTracker mWorkConstraintsTracker;
    private WorkManagerImpl mWorkManagerImpl;

    public GreedyScheduler(Context context, WorkManagerImpl workManagerImpl) {
        this.mWorkManagerImpl = workManagerImpl;
        this.mWorkConstraintsTracker = new WorkConstraintsTracker(context, this);
    }

    public synchronized void schedule(WorkSpec... workSpecArr) {
        int size = this.mConstrainedWorkSpecs.size();
        for (WorkSpec workSpec : workSpecArr) {
            if (workSpec.state == State.ENQUEUED && !workSpec.isPeriodic() && workSpec.initialDelay == 0) {
                if (!workSpec.hasConstraints()) {
                    this.mWorkManagerImpl.startWork(workSpec.id);
                } else if (VERSION.SDK_INT < 24 || !workSpec.constraints.hasContentUriTriggers()) {
                    Log.d("GreedyScheduler", String.format("Starting tracking for %s", new Object[]{workSpec.id}));
                    this.mConstrainedWorkSpecs.add(workSpec);
                }
            }
        }
        if (size != this.mConstrainedWorkSpecs.size()) {
            this.mWorkConstraintsTracker.replace(this.mConstrainedWorkSpecs);
        }
    }

    public synchronized void cancel(String str) {
        Log.d("GreedyScheduler", String.format("Cancelling work ID %s", new Object[]{str}));
        this.mWorkManagerImpl.stopWork(str);
        removeConstraintTrackingFor(str);
    }

    public synchronized void onAllConstraintsMet(List<String> list) {
        for (String str : list) {
            Log.d("GreedyScheduler", String.format("Constraints met: Scheduling work ID %s", new Object[]{str}));
            this.mWorkManagerImpl.startWork(str);
        }
    }

    public synchronized void onAllConstraintsNotMet(List<String> list) {
        for (String str : list) {
            Log.d("GreedyScheduler", String.format("Constraints not met: Cancelling work ID %s", new Object[]{str}));
            this.mWorkManagerImpl.stopWork(str);
        }
    }

    public synchronized void onExecuted(String str, boolean z, boolean z2) {
        removeConstraintTrackingFor(str);
    }

    private synchronized void removeConstraintTrackingFor(String str) {
        int size = this.mConstrainedWorkSpecs.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            } else if (((WorkSpec) this.mConstrainedWorkSpecs.get(i)).id.equals(str)) {
                Log.d("GreedyScheduler", String.format("Stopping tracking for %s", new Object[]{str}));
                this.mConstrainedWorkSpecs.remove(i);
                this.mWorkConstraintsTracker.replace(this.mConstrainedWorkSpecs);
                break;
            } else {
                i++;
            }
        }
    }
}
