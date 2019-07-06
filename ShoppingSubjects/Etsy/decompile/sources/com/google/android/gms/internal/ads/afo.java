package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import java.lang.ref.WeakReference;

final class afo implements ActivityLifecycleCallbacks {
    private final Application a;
    private final WeakReference<ActivityLifecycleCallbacks> b;
    private boolean c = false;

    public afo(Application application, ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.b = new WeakReference<>(activityLifecycleCallbacks);
        this.a = application;
    }

    private final void a(afw afw) {
        try {
            ActivityLifecycleCallbacks activityLifecycleCallbacks = (ActivityLifecycleCallbacks) this.b.get();
            if (activityLifecycleCallbacks != null) {
                afw.a(activityLifecycleCallbacks);
                return;
            }
            if (!this.c) {
                this.a.unregisterActivityLifecycleCallbacks(this);
                this.c = true;
            }
        } catch (Exception e) {
            gv.b("Error while dispatching lifecycle callback.", e);
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        a(new afp(this, activity, bundle));
    }

    public final void onActivityDestroyed(Activity activity) {
        a(new afv(this, activity));
    }

    public final void onActivityPaused(Activity activity) {
        a(new afs(this, activity));
    }

    public final void onActivityResumed(Activity activity) {
        a(new afr(this, activity));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        a(new afu(this, activity, bundle));
    }

    public final void onActivityStarted(Activity activity) {
        a(new afq(this, activity));
    }

    public final void onActivityStopped(Activity activity) {
        a(new aft(this, activity));
    }
}
