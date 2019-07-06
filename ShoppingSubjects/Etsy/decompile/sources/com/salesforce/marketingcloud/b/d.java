package com.salesforce.marketingcloud.b;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import com.salesforce.marketingcloud.InitializationStatus.a;
import com.salesforce.marketingcloud.i;
import com.salesforce.marketingcloud.j;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

@RestrictTo({Scope.LIBRARY})
public final class d extends i implements ActivityLifecycleCallbacks, ComponentCallbacks2 {
    @VisibleForTesting
    static d a;
    private static final String c = j.a(d.class);
    @VisibleForTesting
    AtomicBoolean b = new AtomicBoolean(false);
    private final Application d;
    private final AtomicBoolean e = new AtomicBoolean(false);

    private d(Application application) {
        this.d = application;
        application.registerActivityLifecycleCallbacks(this);
        application.registerComponentCallbacks(this);
    }

    public static synchronized d a(Application application) {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                a = new d(application);
            }
            dVar = a;
        }
        return dVar;
    }

    @Nullable
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("inForeground", this.b.get());
            return jSONObject;
        } catch (JSONException e2) {
            j.c(c, e2, "Failed to create component state", new Object[0]);
            return jSONObject;
        }
    }

    /* access modifiers changed from: protected */
    public void a(a aVar) {
        this.e.set(true);
        if (this.b.get()) {
            c.a((Context) this.d, a.BEHAVIOR_APP_FOREGROUNDED, (Bundle) null);
        }
    }

    public void a(boolean z) {
        this.e.set(false);
    }

    @NonNull
    public String b() {
        return "LifecycleManager";
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        if (!this.b.getAndSet(true) && this.e.get()) {
            j.b(c, "App came into the foreground.", new Object[0]);
            c.a((Context) this.d, a.BEHAVIOR_APP_FOREGROUNDED, (Bundle) null);
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int i) {
        if (i >= 20 && this.b.getAndSet(false)) {
            j.b(c, "App went into the background.", new Object[0]);
            c.a((Context) this.d, a.BEHAVIOR_APP_BACKGROUNDED, (Bundle) null);
        }
    }
}
