package androidx.work.impl.utils;

import android.util.Log;
import androidx.work.State;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkSpecDao;

public class StopWorkRunnable implements Runnable {
    private WorkManagerImpl mWorkManagerImpl;
    private String mWorkSpecId;

    public StopWorkRunnable(WorkManagerImpl workManagerImpl, String str) {
        this.mWorkManagerImpl = workManagerImpl;
        this.mWorkSpecId = str;
    }

    public void run() {
        WorkDatabase workDatabase = this.mWorkManagerImpl.getWorkDatabase();
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        workDatabase.beginTransaction();
        try {
            if (workSpecDao.getState(this.mWorkSpecId) == State.RUNNING) {
                workSpecDao.setState(State.ENQUEUED, this.mWorkSpecId);
            }
            Log.d("StopWorkRunnable", String.format("StopWorkRunnable for %s; Processor.stopWork = %s", new Object[]{this.mWorkSpecId, Boolean.valueOf(this.mWorkManagerImpl.getProcessor().stopWork(this.mWorkSpecId))}));
            workDatabase.setTransactionSuccessful();
        } finally {
            workDatabase.endTransaction();
        }
    }
}
