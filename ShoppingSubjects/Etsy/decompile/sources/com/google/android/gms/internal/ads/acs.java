package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;

final class acs implements acv {
    private final /* synthetic */ Activity a;

    acs(acn acn, Activity activity) {
        this.a = activity;
    }

    public final void a(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityStopped(this.a);
    }
}
