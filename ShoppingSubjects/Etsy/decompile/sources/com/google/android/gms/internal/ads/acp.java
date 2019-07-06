package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;

final class acp implements acv {
    private final /* synthetic */ Activity a;

    acp(acn acn, Activity activity) {
        this.a = activity;
    }

    public final void a(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityStarted(this.a);
    }
}
