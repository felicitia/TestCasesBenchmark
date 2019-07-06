package androidx.work.impl.utils;

import androidx.work.State;
import androidx.work.impl.Processor;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkSpecDao;

public abstract class CancelWorkRunnable implements Runnable {
    /* access modifiers changed from: 0000 */
    public void cancel(WorkManagerImpl workManagerImpl, String str) {
        recursivelyCancelWorkAndDependents(workManagerImpl.getWorkDatabase(), str);
        Processor processor = workManagerImpl.getProcessor();
        processor.stopWork(str);
        processor.setCancelled(str);
        for (Scheduler cancel : workManagerImpl.getSchedulers()) {
            cancel.cancel(str);
        }
    }

    /* access modifiers changed from: 0000 */
    public void reschedulePendingWorkers(WorkManagerImpl workManagerImpl) {
        Schedulers.schedule(workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }

    private void recursivelyCancelWorkAndDependents(WorkDatabase workDatabase, String str) {
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        for (String recursivelyCancelWorkAndDependents : workDatabase.dependencyDao().getDependentWorkIds(str)) {
            recursivelyCancelWorkAndDependents(workDatabase, recursivelyCancelWorkAndDependents);
        }
        State state = workSpecDao.getState(str);
        if (state != State.SUCCEEDED && state != State.FAILED) {
            workSpecDao.setState(State.CANCELLED, str);
        }
    }

    public static Runnable forTag(final String str, final WorkManagerImpl workManagerImpl) {
        return new CancelWorkRunnable() {
            /* JADX INFO: finally extract failed */
            public void run() {
                WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
                workDatabase.beginTransaction();
                try {
                    for (String cancel : workDatabase.workSpecDao().getUnfinishedWorkWithTag(str)) {
                        cancel(workManagerImpl, cancel);
                    }
                    workDatabase.setTransactionSuccessful();
                    workDatabase.endTransaction();
                    reschedulePendingWorkers(workManagerImpl);
                } catch (Throwable th) {
                    workDatabase.endTransaction();
                    throw th;
                }
            }
        };
    }

    public static Runnable forName(final String str, final WorkManagerImpl workManagerImpl) {
        return new CancelWorkRunnable() {
            /* JADX INFO: finally extract failed */
            public void run() {
                WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
                workDatabase.beginTransaction();
                try {
                    for (String cancel : workDatabase.workSpecDao().getUnfinishedWorkWithName(str)) {
                        cancel(workManagerImpl, cancel);
                    }
                    workDatabase.setTransactionSuccessful();
                    workDatabase.endTransaction();
                    reschedulePendingWorkers(workManagerImpl);
                } catch (Throwable th) {
                    workDatabase.endTransaction();
                    throw th;
                }
            }
        };
    }
}
