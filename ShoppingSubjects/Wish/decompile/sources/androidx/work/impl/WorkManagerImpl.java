package androidx.work.impl;

import android.content.Context;
import androidx.work.Configuration;
import androidx.work.R;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.impl.Extras.RuntimeExtras;
import androidx.work.impl.background.greedy.GreedyScheduler;
import androidx.work.impl.utils.CancelWorkRunnable;
import androidx.work.impl.utils.ForceStopRunnable;
import androidx.work.impl.utils.StartWorkRunnable;
import androidx.work.impl.utils.StopWorkRunnable;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import java.util.Arrays;
import java.util.List;

public class WorkManagerImpl extends WorkManager {
    private static WorkManagerImpl sDefaultInstance;
    private static WorkManagerImpl sDelegatedInstance;
    private static final Object sLock = new Object();
    private Context mContext;
    private Processor mProcessor;
    private List<Scheduler> mSchedulers;
    private TaskExecutor mTaskExecutor;
    private WorkDatabase mWorkDatabase;

    public static WorkManagerImpl getInstance() {
        synchronized (sLock) {
            if (sDelegatedInstance != null) {
                WorkManagerImpl workManagerImpl = sDelegatedInstance;
                return workManagerImpl;
            }
            WorkManagerImpl workManagerImpl2 = sDefaultInstance;
            return workManagerImpl2;
        }
    }

    public static void initialize(Context context, Configuration configuration) {
        synchronized (sLock) {
            if (sDelegatedInstance == null) {
                Context applicationContext = context.getApplicationContext();
                if (sDefaultInstance == null) {
                    sDefaultInstance = new WorkManagerImpl(applicationContext, configuration);
                }
                sDelegatedInstance = sDefaultInstance;
            }
        }
    }

    public WorkManagerImpl(Context context, Configuration configuration) {
        this(context, configuration, context.getResources().getBoolean(R.bool.workmanager_test_configuration));
    }

    public WorkManagerImpl(Context context, Configuration configuration, boolean z) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mWorkDatabase = WorkDatabase.create(applicationContext, z);
        this.mTaskExecutor = WorkManagerTaskExecutor.getInstance();
        this.mProcessor = new Processor(applicationContext, this.mWorkDatabase, getSchedulers(), configuration.getExecutor());
        this.mTaskExecutor.executeOnBackgroundThread(new ForceStopRunnable(applicationContext, this));
    }

    public WorkDatabase getWorkDatabase() {
        return this.mWorkDatabase;
    }

    public List<Scheduler> getSchedulers() {
        if (this.mSchedulers == null) {
            this.mSchedulers = Arrays.asList(new Scheduler[]{Schedulers.createBestAvailableBackgroundScheduler(this.mContext), new GreedyScheduler(this.mContext, this)});
        }
        return this.mSchedulers;
    }

    public Processor getProcessor() {
        return this.mProcessor;
    }

    public TaskExecutor getTaskExecutor() {
        return this.mTaskExecutor;
    }

    public void enqueue(List<? extends WorkRequest> list) {
        new WorkContinuationImpl(this, list).enqueue();
    }

    public void cancelAllWorkByTag(String str) {
        this.mTaskExecutor.executeOnBackgroundThread(CancelWorkRunnable.forTag(str, this));
    }

    public void startWork(String str) {
        startWork(str, null);
    }

    public void startWork(String str, RuntimeExtras runtimeExtras) {
        this.mTaskExecutor.executeOnBackgroundThread(new StartWorkRunnable(this, str, runtimeExtras));
    }

    public void stopWork(String str) {
        this.mTaskExecutor.executeOnBackgroundThread(new StopWorkRunnable(this, str));
    }

    public void rescheduleEligibleWork() {
        getWorkDatabase().workSpecDao().resetScheduledState();
        Schedulers.schedule(getWorkDatabase(), getSchedulers());
    }
}
