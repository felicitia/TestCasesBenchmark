package androidx.work;

import android.content.Context;
import androidx.work.impl.WorkManagerImpl;
import java.util.Arrays;
import java.util.List;

public abstract class WorkManager {
    public abstract void cancelAllWorkByTag(String str);

    public abstract void enqueue(List<? extends WorkRequest> list);

    public static WorkManager getInstance() {
        return WorkManagerImpl.getInstance();
    }

    public static void initialize(Context context, Configuration configuration) {
        WorkManagerImpl.initialize(context, configuration);
    }

    public final void enqueue(WorkRequest... workRequestArr) {
        enqueue(Arrays.asList(workRequestArr));
    }

    protected WorkManager() {
    }
}
