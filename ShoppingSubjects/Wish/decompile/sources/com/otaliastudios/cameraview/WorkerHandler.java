package com.otaliastudios.cameraview;

import android.os.Handler;
import android.os.HandlerThread;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;

class WorkerHandler {
    private static final CameraLogger LOG = CameraLogger.create(WorkerHandler.class.getSimpleName());
    private static final ConcurrentHashMap<String, WeakReference<WorkerHandler>> sCache = new ConcurrentHashMap<>(4);
    private Handler mHandler = new Handler(this.mThread.getLooper());
    private HandlerThread mThread;

    public static WorkerHandler get(String str) {
        if (sCache.containsKey(str)) {
            WorkerHandler workerHandler = (WorkerHandler) ((WeakReference) sCache.get(str)).get();
            if (workerHandler != null) {
                HandlerThread handlerThread = workerHandler.mThread;
                if (handlerThread.isAlive() && !handlerThread.isInterrupted()) {
                    LOG.w("get:", "Reusing cached worker handler.", str);
                    return workerHandler;
                }
            }
            LOG.w("get:", "Thread reference died, removing.", str);
            sCache.remove(str);
        }
        LOG.i("get:", "Creating new handler.", str);
        WorkerHandler workerHandler2 = new WorkerHandler(str);
        sCache.put(str, new WeakReference(workerHandler2));
        return workerHandler2;
    }

    public static void run(Runnable runnable) {
        get("FallbackCameraThread").post(runnable);
    }

    private WorkerHandler(String str) {
        this.mThread = new HandlerThread(str);
        this.mThread.setDaemon(true);
        this.mThread.start();
    }

    public Handler get() {
        return this.mHandler;
    }

    public void post(Runnable runnable) {
        this.mHandler.post(runnable);
    }

    public Thread getThread() {
        return this.mThread;
    }
}
