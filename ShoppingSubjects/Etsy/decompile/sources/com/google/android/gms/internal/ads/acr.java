package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;

final class acr implements acv {
    private final /* synthetic */ Activity a;

    acr(acn acn, Activity activity) {
        this.a = activity;
    }

    public final void a(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityPaused(this.a);
    }
}
