package androidx.work.impl;

import android.content.Context;
import android.util.Log;
import androidx.work.Data;
import androidx.work.InputMerger;
import androidx.work.State;
import androidx.work.Worker;
import androidx.work.Worker.WorkerResult;
import androidx.work.impl.Extras.RuntimeExtras;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class WorkerWrapper implements Runnable {
    private Context mAppContext;
    private DependencyDao mDependencyDao;
    private volatile boolean mInterrupted;
    /* access modifiers changed from: private */
    public ExecutionListener mListener;
    private RuntimeExtras mRuntimeExtras;
    private List<Scheduler> mSchedulers;
    private WorkDatabase mWorkDatabase;
    private WorkSpec mWorkSpec;
    private WorkSpecDao mWorkSpecDao;
    /* access modifiers changed from: private */
    public String mWorkSpecId;
    private WorkTagDao mWorkTagDao;
    Worker mWorker;

    public static class Builder {
        /* access modifiers changed from: private */
        public Context mAppContext;
        /* access modifiers changed from: private */
        public ExecutionListener mListener;
        /* access modifiers changed from: private */
        public RuntimeExtras mRuntimeExtras;
        /* access modifiers changed from: private */
        public List<Scheduler> mSchedulers;
        /* access modifiers changed from: private */
        public WorkDatabase mWorkDatabase;
        /* access modifiers changed from: private */
        public String mWorkSpecId;
        /* access modifiers changed from: private */
        public Worker mWorker;

        public Builder(Context context, WorkDatabase workDatabase, String str) {
            this.mAppContext = context.getApplicationContext();
            this.mWorkDatabase = workDatabase;
            this.mWorkSpecId = str;
        }

        public Builder withListener(ExecutionListener executionListener) {
            this.mListener = executionListener;
            return this;
        }

        public Builder withSchedulers(List<Scheduler> list) {
            this.mSchedulers = list;
            return this;
        }

        public Builder withRuntimeExtras(RuntimeExtras runtimeExtras) {
            this.mRuntimeExtras = runtimeExtras;
            return this;
        }

        public WorkerWrapper build() {
            return new WorkerWrapper(this);
        }
    }

    private WorkerWrapper(Builder builder) {
        this.mAppContext = builder.mAppContext;
        this.mWorkSpecId = builder.mWorkSpecId;
        this.mListener = builder.mListener;
        this.mSchedulers = builder.mSchedulers;
        this.mRuntimeExtras = builder.mRuntimeExtras;
        this.mWorker = builder.mWorker;
        this.mWorkDatabase = builder.mWorkDatabase;
        this.mWorkSpecDao = this.mWorkDatabase.workSpecDao();
        this.mDependencyDao = this.mWorkDatabase.dependencyDao();
        this.mWorkTagDao = this.mWorkDatabase.workTagDao();
    }

    public void run() {
        Data data;
        WorkerResult workerResult;
        if (!tryCheckForInterruptionAndNotify()) {
            this.mWorkSpec = this.mWorkSpecDao.getWorkSpec(this.mWorkSpecId);
            if (this.mWorkSpec == null) {
                Log.e("WorkerWrapper", String.format("Didn't find WorkSpec for id %s", new Object[]{this.mWorkSpecId}));
                notifyListener(false, false);
            } else if (this.mWorkSpec.state != State.ENQUEUED) {
                notifyIncorrectStatus();
            } else {
                if (this.mWorkSpec.isPeriodic()) {
                    data = this.mWorkSpec.input;
                } else {
                    InputMerger fromClassName = InputMerger.fromClassName(this.mWorkSpec.inputMergerClassName);
                    if (fromClassName == null) {
                        Log.e("WorkerWrapper", String.format("Could not create Input Merger %s", new Object[]{this.mWorkSpec.inputMergerClassName}));
                        setFailedAndNotify();
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(this.mWorkSpec.input);
                    arrayList.addAll(this.mWorkSpecDao.getInputsFromPrerequisites(this.mWorkSpecId));
                    data = fromClassName.merge(arrayList);
                }
                Extras extras = new Extras(data, this.mWorkTagDao.getTagsForWorkSpecId(this.mWorkSpecId), this.mRuntimeExtras);
                if (this.mWorker == null) {
                    this.mWorker = workerFromWorkSpec(this.mAppContext, this.mWorkSpec, extras);
                }
                if (this.mWorker == null) {
                    Log.e("WorkerWrapper", String.format("Could for create Worker %s", new Object[]{this.mWorkSpec.workerClassName}));
                    setFailedAndNotify();
                    return;
                }
                if (!trySetRunning()) {
                    notifyIncorrectStatus();
                } else if (!tryCheckForInterruptionAndNotify()) {
                    try {
                        workerResult = this.mWorker.doWork();
                    } catch (Error | Exception unused) {
                        workerResult = WorkerResult.FAILURE;
                    }
                    try {
                        this.mWorkDatabase.beginTransaction();
                        if (!tryCheckForInterruptionAndNotify()) {
                            State state = this.mWorkSpecDao.getState(this.mWorkSpecId);
                            if (state == null) {
                                notifyListener(false, false);
                            } else if (state == State.RUNNING) {
                                handleResult(workerResult);
                            } else if (!state.isFinished()) {
                                rescheduleAndNotify();
                            }
                            this.mWorkDatabase.setTransactionSuccessful();
                        }
                    } finally {
                        this.mWorkDatabase.endTransaction();
                    }
                }
            }
        }
    }

    public void interrupt() {
        this.mInterrupted = true;
        if (this.mWorker != null) {
            this.mWorker.stop();
        }
    }

    private void notifyIncorrectStatus() {
        State state = this.mWorkSpecDao.getState(this.mWorkSpecId);
        if (state == State.RUNNING) {
            Log.d("WorkerWrapper", String.format("Status for %s is RUNNING;not doing any work and rescheduling for later execution", new Object[]{this.mWorkSpecId}));
            notifyListener(false, true);
            return;
        }
        Log.e("WorkerWrapper", String.format("Status for %s is %s; not doing any work", new Object[]{this.mWorkSpecId, state}));
        notifyListener(false, false);
    }

    private boolean tryCheckForInterruptionAndNotify() {
        boolean z = false;
        if (!this.mInterrupted) {
            return false;
        }
        Log.d("WorkerWrapper", String.format("Work interrupted for %s", new Object[]{this.mWorkSpecId}));
        State state = this.mWorkSpecDao.getState(this.mWorkSpecId);
        if (state == null) {
            notifyListener(false, false);
        } else {
            if (state == State.SUCCEEDED) {
                z = true;
            }
            notifyListener(z, !state.isFinished());
        }
        return true;
    }

    private void notifyListener(final boolean z, final boolean z2) {
        if (this.mListener != null) {
            WorkManagerTaskExecutor.getInstance().postToMainThread(new Runnable() {
                public void run() {
                    WorkerWrapper.this.mListener.onExecuted(WorkerWrapper.this.mWorkSpecId, z, z2);
                }
            });
        }
    }

    private void handleResult(WorkerResult workerResult) {
        switch (workerResult) {
            case SUCCESS:
                Log.d("WorkerWrapper", String.format("Worker result SUCCESS for %s", new Object[]{this.mWorkSpecId}));
                if (this.mWorkSpec.isPeriodic()) {
                    resetPeriodicAndNotify(true);
                    return;
                } else {
                    setSucceededAndNotify();
                    return;
                }
            case RETRY:
                Log.d("WorkerWrapper", String.format("Worker result RETRY for %s", new Object[]{this.mWorkSpecId}));
                rescheduleAndNotify();
                return;
            default:
                Log.d("WorkerWrapper", String.format("Worker result FAILURE for %s", new Object[]{this.mWorkSpecId}));
                if (this.mWorkSpec.isPeriodic()) {
                    resetPeriodicAndNotify(false);
                    return;
                } else {
                    setFailedAndNotify();
                    return;
                }
        }
    }

    private boolean trySetRunning() {
        this.mWorkDatabase.beginTransaction();
        try {
            boolean z = true;
            if (this.mWorkSpecDao.getState(this.mWorkSpecId) == State.ENQUEUED) {
                this.mWorkSpecDao.setState(State.RUNNING, this.mWorkSpecId);
                this.mWorkSpecDao.incrementWorkSpecRunAttemptCount(this.mWorkSpecId);
                this.mWorkDatabase.setTransactionSuccessful();
            } else {
                z = false;
            }
            return z;
        } finally {
            this.mWorkDatabase.endTransaction();
        }
    }

    /* JADX INFO: finally extract failed */
    private void setFailedAndNotify() {
        this.mWorkDatabase.beginTransaction();
        try {
            recursivelyFailWorkAndDependents(this.mWorkSpecId);
            if (this.mWorker != null) {
                this.mWorkSpecDao.setOutput(this.mWorkSpecId, this.mWorker.getOutputData());
            }
            this.mWorkDatabase.setTransactionSuccessful();
            this.mWorkDatabase.endTransaction();
            notifyListener(false, false);
            Schedulers.schedule(this.mWorkDatabase, this.mSchedulers);
        } catch (Throwable th) {
            this.mWorkDatabase.endTransaction();
            notifyListener(false, false);
            throw th;
        }
    }

    private void recursivelyFailWorkAndDependents(String str) {
        for (String recursivelyFailWorkAndDependents : this.mDependencyDao.getDependentWorkIds(str)) {
            recursivelyFailWorkAndDependents(recursivelyFailWorkAndDependents);
        }
        if (this.mWorkSpecDao.getState(str) != State.CANCELLED) {
            this.mWorkSpecDao.setState(State.FAILED, str);
        }
    }

    private void rescheduleAndNotify() {
        this.mWorkDatabase.beginTransaction();
        try {
            this.mWorkSpecDao.setState(State.ENQUEUED, this.mWorkSpecId);
            this.mWorkSpecDao.setPeriodStartTime(this.mWorkSpecId, System.currentTimeMillis());
            this.mWorkDatabase.setTransactionSuccessful();
        } finally {
            this.mWorkDatabase.endTransaction();
            notifyListener(false, true);
        }
    }

    private void resetPeriodicAndNotify(boolean z) {
        this.mWorkDatabase.beginTransaction();
        try {
            this.mWorkSpecDao.setPeriodStartTime(this.mWorkSpecId, this.mWorkSpec.periodStartTime + this.mWorkSpec.intervalDuration);
            this.mWorkSpecDao.setState(State.ENQUEUED, this.mWorkSpecId);
            this.mWorkSpecDao.resetWorkSpecRunAttemptCount(this.mWorkSpecId);
            this.mWorkDatabase.setTransactionSuccessful();
        } finally {
            this.mWorkDatabase.endTransaction();
            notifyListener(z, false);
        }
    }

    /* JADX INFO: finally extract failed */
    private void setSucceededAndNotify() {
        this.mWorkDatabase.beginTransaction();
        try {
            this.mWorkSpecDao.setState(State.SUCCEEDED, this.mWorkSpecId);
            this.mWorkSpecDao.setOutput(this.mWorkSpecId, this.mWorker.getOutputData());
            long currentTimeMillis = System.currentTimeMillis();
            for (String str : this.mDependencyDao.getDependentWorkIds(this.mWorkSpecId)) {
                if (this.mDependencyDao.hasCompletedAllPrerequisites(str)) {
                    Log.d("WorkerWrapper", String.format("Setting status to enqueued for %s", new Object[]{str}));
                    this.mWorkSpecDao.setState(State.ENQUEUED, str);
                    this.mWorkSpecDao.setPeriodStartTime(str, currentTimeMillis);
                }
            }
            this.mWorkDatabase.setTransactionSuccessful();
            this.mWorkDatabase.endTransaction();
            notifyListener(true, false);
            Schedulers.schedule(this.mWorkDatabase, this.mSchedulers);
        } catch (Throwable th) {
            this.mWorkDatabase.endTransaction();
            notifyListener(true, false);
            throw th;
        }
    }

    static Worker workerFromWorkSpec(Context context, WorkSpec workSpec, Extras extras) {
        return workerFromClassName(context, workSpec.workerClassName, workSpec.id, extras);
    }

    public static Worker workerFromClassName(Context context, String str, String str2, Extras extras) {
        Context applicationContext = context.getApplicationContext();
        try {
            Worker worker = (Worker) Class.forName(str).newInstance();
            Method declaredMethod = Worker.class.getDeclaredMethod("internalInit", new Class[]{Context.class, String.class, Extras.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(worker, new Object[]{applicationContext, str2, extras});
            return worker;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Trouble instantiating ");
            sb.append(str);
            Log.e("WorkerWrapper", sb.toString(), e);
            return null;
        }
    }
}
