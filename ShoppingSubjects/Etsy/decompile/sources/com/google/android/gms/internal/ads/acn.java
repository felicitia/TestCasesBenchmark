package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import java.lang.ref.WeakReference;

final class acn implements ActivityLifecycleCallbacks {
    private final Application a;
    private final WeakReference<ActivityLifecycleCallbacks> b;
    private boolean c = false;

    public acn(Application application, ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.b = new WeakReference<>(activityLifecycleCallbacks);
        this.a = application;
    }

    private final void a(acv acv) {
        try {
            ActivityLifecycleCallbacks activityLifecycleCallbacks = (ActivityLifecycleCallbacks) this.b.get();
            if (activityLifecycleCallbacks != null) {
                acv.a(activityLifecycleCallbacks);
                return;
            }
            if (!this.c) {
                this.a.unregisterActivityLifecycleCallbacks(this);
                this.c = true;
            }
        } catch (Exception unused) {
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        a(new aco(this, activity, bundle));
    }

    public final void onActivityDestroyed(Activity activity) {
        a(new acu(this, activity));
    }

    public final void onActivityPaused(Activity activity) {
        a(new acr(this, activity));
    }

    public final void onActivityResumed(Activity activity) {
        a(new acq(this, activity));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        a(new act(this, activity, bundle));
    }

    public final void onActivityStarted(Activity activity) {
        a(new acp(this, activity));
    }

    public final void onActivityStopped(Activity activity) {
        a(new acs(this, activity));
    }
}
