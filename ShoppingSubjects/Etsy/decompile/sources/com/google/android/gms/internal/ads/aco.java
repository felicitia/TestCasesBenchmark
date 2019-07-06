package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

final class aco implements acv {
    private final /* synthetic */ Activity a;
    private final /* synthetic */ Bundle b;

    aco(acn acn, Activity activity, Bundle bundle) {
        this.a = activity;
        this.b = bundle;
    }

    public final void a(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityCreated(this.a, this.b);
    }
}
