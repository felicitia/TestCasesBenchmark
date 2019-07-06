package androidx.work.impl.utils;

import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import androidx.work.Constraints;
import androidx.work.Data.Builder;
import androidx.work.ExistingWorkPolicy;
import androidx.work.State;
import androidx.work.WorkRequest;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.Dependency;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpec.IdAndState;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTag;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnqueueRunnable implements Runnable {
    private final WorkContinuationImpl mWorkContinuation;

    public EnqueueRunnable(WorkContinuationImpl workContinuationImpl) {
        this.mWorkContinuation = workContinuationImpl;
    }

    public void run() {
        if (this.mWorkContinuation.hasCycles()) {
            throw new IllegalStateException(String.format("WorkContinuation has cycles (%s)", new Object[]{this.mWorkContinuation}));
        } else if (addToDatabase()) {
            scheduleWorkInBackground();
        }
    }

    public boolean addToDatabase() {
        WorkDatabase workDatabase = this.mWorkContinuation.getWorkManagerImpl().getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            boolean processContinuation = processContinuation(this.mWorkContinuation);
            workDatabase.setTransactionSuccessful();
            return processContinuation;
        } finally {
            workDatabase.endTransaction();
        }
    }

    public void scheduleWorkInBackground() {
        WorkManagerImpl workManagerImpl = this.mWorkContinuation.getWorkManagerImpl();
        Schedulers.schedule(workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }

    private static boolean processContinuation(WorkContinuationImpl workContinuationImpl) {
        List<WorkContinuationImpl> parents = workContinuationImpl.getParents();
        boolean z = false;
        if (parents != null) {
            boolean z2 = false;
            for (WorkContinuationImpl workContinuationImpl2 : parents) {
                if (!workContinuationImpl2.isEnqueued()) {
                    z2 |= processContinuation(workContinuationImpl2);
                } else {
                    Log.w("EnqueueRunnable", String.format("Already enqueued work ids (%s).", new Object[]{TextUtils.join(", ", workContinuationImpl2.getIds())}));
                }
            }
            z = z2;
        }
        return enqueueContinuation(workContinuationImpl) | z;
    }

    private static boolean enqueueContinuation(WorkContinuationImpl workContinuationImpl) {
        boolean enqueueWorkWithPrerequisites = enqueueWorkWithPrerequisites(workContinuationImpl.getWorkManagerImpl(), workContinuationImpl.getWork(), (String[]) WorkContinuationImpl.prerequisitesFor(workContinuationImpl).toArray(new String[0]), workContinuationImpl.getName(), workContinuationImpl.getExistingWorkPolicy());
        workContinuationImpl.markEnqueued();
        return enqueueWorkWithPrerequisites;
    }

    private static boolean enqueueWorkWithPrerequisites(WorkManagerImpl workManagerImpl, List<? extends WorkRequest> list, String[] strArr, String str, ExistingWorkPolicy existingWorkPolicy) {
        boolean z;
        boolean z2;
        boolean z3;
        String[] strArr2 = strArr;
        String str2 = str;
        ExistingWorkPolicy existingWorkPolicy2 = existingWorkPolicy;
        long currentTimeMillis = System.currentTimeMillis();
        WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
        boolean z4 = strArr2 != null && strArr2.length > 0;
        if (z4) {
            z3 = true;
            z2 = false;
            z = false;
            for (String str3 : strArr2) {
                WorkSpec workSpec = workDatabase.workSpecDao().getWorkSpec(str3);
                if (workSpec == null) {
                    Log.e("EnqueueRunnable", String.format("Prerequisite %s doesn't exist; not enqueuing", new Object[]{str3}));
                    return false;
                }
                State state = workSpec.state;
                z3 &= state == State.SUCCEEDED;
                if (state == State.FAILED) {
                    z2 = true;
                } else if (state == State.CANCELLED) {
                    z = true;
                }
            }
        } else {
            z3 = true;
            z2 = false;
            z = false;
        }
        boolean z5 = !TextUtils.isEmpty(str);
        if (z5 && !z4) {
            List<IdAndState> workSpecIdAndStatesForName = workDatabase.workSpecDao().getWorkSpecIdAndStatesForName(str2);
            if (!workSpecIdAndStatesForName.isEmpty()) {
                if (existingWorkPolicy2 == ExistingWorkPolicy.APPEND) {
                    DependencyDao dependencyDao = workDatabase.dependencyDao();
                    ArrayList arrayList = new ArrayList();
                    for (IdAndState idAndState : workSpecIdAndStatesForName) {
                        if (!dependencyDao.hasDependents(idAndState.id)) {
                            boolean z6 = (idAndState.state == State.SUCCEEDED) & z3;
                            if (idAndState.state == State.FAILED) {
                                z2 = true;
                            } else if (idAndState.state == State.CANCELLED) {
                                z = true;
                            }
                            arrayList.add(idAndState.id);
                            z3 = z6;
                        }
                    }
                    strArr2 = (String[]) arrayList.toArray(strArr2);
                    z4 = strArr2.length > 0;
                } else {
                    if (existingWorkPolicy2 == ExistingWorkPolicy.KEEP) {
                        for (IdAndState idAndState2 : workSpecIdAndStatesForName) {
                            if (idAndState2.state != State.ENQUEUED) {
                                if (idAndState2.state == State.RUNNING) {
                                }
                            }
                            return false;
                        }
                    }
                    CancelWorkRunnable.forName(str2, workManagerImpl).run();
                    WorkSpecDao workSpecDao = workDatabase.workSpecDao();
                    for (IdAndState idAndState3 : workSpecIdAndStatesForName) {
                        workSpecDao.delete(idAndState3.id);
                    }
                }
            }
        }
        Iterator it = list.iterator();
        boolean z7 = false;
        while (it.hasNext()) {
            WorkRequest workRequest = (WorkRequest) it.next();
            WorkSpec workSpec2 = workRequest.getWorkSpec();
            if (!z4 || z3) {
                workSpec2.periodStartTime = currentTimeMillis;
            } else if (z2) {
                workSpec2.state = State.FAILED;
            } else if (z) {
                workSpec2.state = State.CANCELLED;
            } else {
                workSpec2.state = State.BLOCKED;
            }
            if (VERSION.SDK_INT >= 23 && VERSION.SDK_INT <= 25) {
                tryDelegateConstrainedWorkSpec(workSpec2);
            }
            if (workSpec2.state == State.ENQUEUED) {
                z7 = true;
            }
            workDatabase.workSpecDao().insertWorkSpec(workSpec2);
            if (z4) {
                int length = strArr2.length;
                int i = 0;
                while (i < length) {
                    String[] strArr3 = strArr2;
                    Iterator it2 = it;
                    workDatabase.dependencyDao().insertDependency(new Dependency(workRequest.getStringId(), strArr2[i]));
                    i++;
                    strArr2 = strArr3;
                    it = it2;
                }
            }
            String[] strArr4 = strArr2;
            Iterator it3 = it;
            for (String workTag : workRequest.getTags()) {
                workDatabase.workTagDao().insert(new WorkTag(workTag, workRequest.getStringId()));
            }
            if (z5) {
                workDatabase.workNameDao().insert(new WorkName(str2, workRequest.getStringId()));
            }
            strArr2 = strArr4;
            it = it3;
        }
        return z7;
    }

    private static void tryDelegateConstrainedWorkSpec(WorkSpec workSpec) {
        Constraints constraints = workSpec.constraints;
        if (constraints.requiresBatteryNotLow() || constraints.requiresStorageNotLow()) {
            String str = workSpec.workerClassName;
            Builder builder = new Builder();
            builder.putAll(workSpec.input).putString("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME", str);
            workSpec.workerClassName = ConstraintTrackingWorker.class.getName();
            workSpec.input = builder.build();
        }
    }
}
