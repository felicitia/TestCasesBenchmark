package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.text.TextUtils;

@TargetApi(14)
@MainThread
final class dl implements ActivityLifecycleCallbacks {
    private final /* synthetic */ cs a;

    private dl(cs csVar) {
        this.a = csVar;
    }

    /* synthetic */ dl(cs csVar, ct ctVar) {
        this(csVar);
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        try {
            this.a.r().w().a("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent != null) {
                Uri data = intent.getData();
                if (data != null && data.isHierarchical()) {
                    if (bundle == null) {
                        Bundle a2 = this.a.p().a(data);
                        this.a.p();
                        String str = fg.a(intent) ? "gs" : "auto";
                        if (a2 != null) {
                            this.a.a(str, "_cmp", a2);
                        }
                    }
                    String queryParameter = data.getQueryParameter("referrer");
                    if (!TextUtils.isEmpty(queryParameter)) {
                        if (!(queryParameter.contains("gclid") && (queryParameter.contains("utm_campaign") || queryParameter.contains("utm_source") || queryParameter.contains("utm_medium") || queryParameter.contains("utm_term") || queryParameter.contains("utm_content")))) {
                            this.a.r().v().a("Activity created with data 'referrer' param without gclid and at least one utm field");
                            return;
                        }
                        this.a.r().v().a("Activity created with referrer", queryParameter);
                        if (!TextUtils.isEmpty(queryParameter)) {
                            this.a.a("auto", "_ldl", (Object) queryParameter);
                        }
                    } else {
                        return;
                    }
                }
            }
        } catch (Exception e) {
            this.a.r().h_().a("Throwable caught in onActivityCreated", e);
        }
        this.a.i().a(activity, bundle);
    }

    public final void onActivityDestroyed(Activity activity) {
        this.a.i().c(activity);
    }

    @MainThread
    public final void onActivityPaused(Activity activity) {
        this.a.i().b(activity);
        eo k = this.a.k();
        k.q().a((Runnable) new es(k, k.m().elapsedRealtime()));
    }

    @MainThread
    public final void onActivityResumed(Activity activity) {
        this.a.i().a(activity);
        eo k = this.a.k();
        k.q().a((Runnable) new er(k, k.m().elapsedRealtime()));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.a.i().b(activity, bundle);
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }
}
