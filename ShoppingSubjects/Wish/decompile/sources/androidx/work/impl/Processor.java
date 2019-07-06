package androidx.work.impl;

import android.content.Context;
import android.util.Log;
import androidx.work.impl.Extras.RuntimeExtras;
import androidx.work.impl.WorkerWrapper.Builder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

public class Processor implements ExecutionListener {
    private Context mAppContext;
    private Set<String> mCancelledIds;
    private Map<String, WorkerWrapper> mEnqueuedWorkMap = new HashMap();
    private Executor mExecutor;
    private final List<ExecutionListener> mOuterListeners;
    private List<Scheduler> mSchedulers;
    private WorkDatabase mWorkDatabase;

    public Processor(Context context, WorkDatabase workDatabase, List<Scheduler> list, Executor executor) {
        this.mAppContext = context;
        this.mWorkDatabase = workDatabase;
        this.mSchedulers = list;
        this.mExecutor = executor;
        this.mCancelledIds = new HashSet();
        this.mOuterListeners = new ArrayList();
    }

    public synchronized boolean startWork(String str) {
        return startWork(str, null);
    }

    public synchronized boolean startWork(String str, RuntimeExtras runtimeExtras) {
        if (this.mEnqueuedWorkMap.containsKey(str)) {
            Log.d("Processor", String.format("Work %s is already enqueued for processing", new Object[]{str}));
            return false;
        }
        WorkerWrapper build = new Builder(this.mAppContext, this.mWorkDatabase, str).withListener(this).withSchedulers(this.mSchedulers).withRuntimeExtras(runtimeExtras).build();
        this.mEnqueuedWorkMap.put(str, build);
        this.mExecutor.execute(build);
        Log.d("Processor", String.format("%s: processing %s", new Object[]{getClass().getSimpleName(), str}));
        return true;
    }

    public synchronized boolean stopWork(String str) {
        Log.d("Processor", String.format("Processor cancelling %s", new Object[]{str}));
        WorkerWrapper workerWrapper = (WorkerWrapper) this.mEnqueuedWorkMap.remove(str);
        if (workerWrapper != null) {
            workerWrapper.interrupt();
            Log.d("Processor", String.format("WorkerWrapper interrupted for %s", new Object[]{str}));
            return true;
        }
        Log.d("Processor", String.format("WorkerWrapper could not be found for %s", new Object[]{str}));
        return false;
    }

    public synchronized void setCancelled(String str) {
        this.mCancelledIds.add(str);
    }

    public synchronized boolean isCancelled(String str) {
        return this.mCancelledIds.contains(str);
    }

    public synchronized boolean isEnqueued(String str) {
        return this.mEnqueuedWorkMap.containsKey(str);
    }

    public synchronized void addExecutionListener(ExecutionListener executionListener) {
        this.mOuterListeners.add(executionListener);
    }

    public synchronized void removeExecutionListener(ExecutionListener executionListener) {
        this.mOuterListeners.remove(executionListener);
    }

    public synchronized void onExecuted(String str, boolean z, boolean z2) {
        this.mEnqueuedWorkMap.remove(str);
        Log.d("Processor", String.format("%s %s executed; isSuccessful = %s, reschedule = %s", new Object[]{getClass().getSimpleName(), str, Boolean.valueOf(z), Boolean.valueOf(z2)}));
        for (ExecutionListener onExecuted : this.mOuterListeners) {
            onExecuted.onExecuted(str, z, z2);
        }
    }
}
