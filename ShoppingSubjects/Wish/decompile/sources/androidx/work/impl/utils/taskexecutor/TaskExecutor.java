package androidx.work.impl.utils.taskexecutor;

public interface TaskExecutor {
    void executeOnBackgroundThread(Runnable runnable);

    void postToMainThread(Runnable runnable);
}
