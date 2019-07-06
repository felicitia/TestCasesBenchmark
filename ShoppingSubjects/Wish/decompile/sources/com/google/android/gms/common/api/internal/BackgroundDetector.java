package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public final class BackgroundDetector implements ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final BackgroundDetector zzem = new BackgroundDetector();
    private final AtomicBoolean zzen = new AtomicBoolean();
    private final AtomicBoolean zzeo = new AtomicBoolean();
    private final ArrayList<BackgroundStateChangeListener> zzep = new ArrayList<>();
    private boolean zzeq = false;

    public interface BackgroundStateChangeListener {
        void onBackgroundStateChanged(boolean z);
    }

    private BackgroundDetector() {
    }

    public static BackgroundDetector getInstance() {
        return zzem;
    }

    public static void initialize(Application application) {
        synchronized (zzem) {
            if (!zzem.zzeq) {
                application.registerActivityLifecycleCallbacks(zzem);
                application.registerComponentCallbacks(zzem);
                zzem.zzeq = true;
            }
        }
    }

    private final void onBackgroundStateChanged(boolean z) {
        synchronized (zzem) {
            ArrayList arrayList = this.zzep;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((BackgroundStateChangeListener) obj).onBackgroundStateChanged(z);
            }
        }
    }

    public final void addListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        synchronized (zzem) {
            this.zzep.add(backgroundStateChangeListener);
        }
    }

    public final boolean isInBackground() {
        return this.zzen.get();
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        boolean compareAndSet = this.zzen.compareAndSet(true, false);
        this.zzeo.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
        boolean compareAndSet = this.zzen.compareAndSet(true, false);
        this.zzeo.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    public final void onTrimMemory(int i) {
        if (i == 20 && this.zzen.compareAndSet(false, true)) {
            this.zzeo.set(true);
            onBackgroundStateChanged(true);
        }
    }

    @TargetApi(16)
    public final boolean readCurrentStateIfPossible(boolean z) {
        if (!this.zzeo.get()) {
            if (!PlatformVersion.isAtLeastJellyBean()) {
                return z;
            }
            RunningAppProcessInfo runningAppProcessInfo = new RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (!this.zzeo.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                this.zzen.set(true);
            }
        }
        return isInBackground();
    }
}