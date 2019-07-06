package com.wish.android.shaky;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

class LifecycleCallbacks implements ActivityLifecycleCallbacks {
    private final Shaky shaky;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    LifecycleCallbacks(Shaky shaky2) {
        this.shaky = shaky2;
    }

    public void onActivityResumed(Activity activity) {
        this.shaky.setActivity(activity);
    }

    public void onActivityPaused(Activity activity) {
        this.shaky.setActivity(null);
    }
}
