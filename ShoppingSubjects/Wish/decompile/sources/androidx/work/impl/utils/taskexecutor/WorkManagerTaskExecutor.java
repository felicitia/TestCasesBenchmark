package androidx.work.impl.utils.taskexecutor;

public class WorkManagerTaskExecutor implements TaskExecutor {
    private static WorkManagerTaskExecutor sInstance;
    private final TaskExecutor mDefaultTaskExecutor = new DefaultTaskExecutor();
    private TaskExecutor mTaskExecutor = this.mDefaultTaskExecutor;

    public static synchronized WorkManagerTaskExecutor getInstance() {
        WorkManagerTaskExecutor workManagerTaskExecutor;
        synchronized (WorkManagerTaskExecutor.class) {
            if (sInstance == null) {
                sInstance = new WorkManagerTaskExecutor();
            }
            workManagerTaskExecutor = sInstance;
        }
        return workManagerTaskExecutor;
    }

    private WorkManagerTaskExecutor() {
    }

    public void postToMainThread(Runnable runnable) {
        this.mTaskExecutor.postToMainThread(runnable);
    }

    public void executeOnBackgroundThread(Runnable runnable) {
        this.mTaskExecutor.executeOnBackgroundThread(runnable);
    }
}
