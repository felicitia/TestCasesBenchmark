package com.gu.toolargetool;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import java.util.HashMap;
import java.util.Map;

public class ActivitySavedStateLogger extends a {
    @NonNull
    b formatter;
    @Nullable
    private final FragmentSavedStateLogger fragmentLogger;
    @NonNull
    c logger;
    @NonNull
    private final Map<Activity, Bundle> savedStates;

    public /* bridge */ /* synthetic */ void onActivityPaused(Activity activity) {
        super.onActivityPaused(activity);
    }

    public /* bridge */ /* synthetic */ void onActivityResumed(Activity activity) {
        super.onActivityResumed(activity);
    }

    public /* bridge */ /* synthetic */ void onActivityStarted(Activity activity) {
        super.onActivityStarted(activity);
    }

    public ActivitySavedStateLogger(@NonNull b bVar, @NonNull c cVar, @Nullable FragmentSavedStateLogger fragmentSavedStateLogger) {
        this.savedStates = new HashMap();
        this.formatter = bVar;
        this.logger = cVar;
        this.fragmentLogger = fragmentSavedStateLogger;
    }

    public ActivitySavedStateLogger(@NonNull b bVar, @NonNull c cVar, boolean z) {
        this(bVar, cVar, z ? new FragmentSavedStateLogger(bVar, cVar) : null);
    }

    public ActivitySavedStateLogger(boolean z) {
        this(b.c, c.d, z);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        if ((activity instanceof FragmentActivity) && this.fragmentLogger != null) {
            ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(this.fragmentLogger, true);
        }
    }

    public void onActivityDestroyed(Activity activity) {
        if ((activity instanceof FragmentActivity) && this.fragmentLogger != null) {
            ((FragmentActivity) activity).getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(this.fragmentLogger);
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.savedStates.put(activity, bundle);
    }

    public void onActivityStopped(Activity activity) {
        Bundle bundle = (Bundle) this.savedStates.remove(activity);
        if (bundle != null) {
            try {
                this.logger.a(this.formatter.a(activity, bundle));
            } catch (RuntimeException e) {
                this.logger.a((Exception) e);
            }
        }
    }
}
