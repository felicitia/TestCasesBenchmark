package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.ao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@TargetApi(14)
final class agb implements ActivityLifecycleCallbacks {
    @Nullable
    private Activity a;
    private Context b;
    /* access modifiers changed from: private */
    public final Object c = new Object();
    /* access modifiers changed from: private */
    public boolean d = true;
    /* access modifiers changed from: private */
    public boolean e = false;
    /* access modifiers changed from: private */
    public final List<agd> f = new ArrayList();
    private final List<agq> g = new ArrayList();
    private Runnable h;
    private boolean i = false;
    private long j;

    agb() {
    }

    private final void a(Activity activity) {
        synchronized (this.c) {
            if (!activity.getClass().getName().startsWith("com.google.android.gms.ads")) {
                this.a = activity;
            }
        }
    }

    @Nullable
    public final Activity a() {
        return this.a;
    }

    public final void a(Application application, Context context) {
        if (!this.i) {
            application.registerActivityLifecycleCallbacks(this);
            if (context instanceof Activity) {
                a((Activity) context);
            }
            this.b = application;
            this.j = ((Long) ajh.f().a(akl.aH)).longValue();
            this.i = true;
        }
    }

    public final void a(agd agd) {
        synchronized (this.c) {
            this.f.add(agd);
        }
    }

    @Nullable
    public final Context b() {
        return this.b;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
        synchronized (this.c) {
            if (this.a != null) {
                if (this.a.equals(activity)) {
                    this.a = null;
                }
                Iterator it = this.g.iterator();
                while (it.hasNext()) {
                    try {
                        if (((agq) it.next()).c(activity)) {
                            it.remove();
                        }
                    } catch (Exception e2) {
                        ao.i().a((Throwable) e2, "AppActivityTracker.ActivityListener.onActivityDestroyed");
                        ka.b("", e2);
                    }
                }
            }
        }
    }

    public final void onActivityPaused(Activity activity) {
        a(activity);
        synchronized (this.c) {
            for (agq b2 : this.g) {
                try {
                    b2.b(activity);
                } catch (Exception e2) {
                    ao.i().a((Throwable) e2, "AppActivityTracker.ActivityListener.onActivityPaused");
                    ka.b("", e2);
                }
            }
        }
        this.e = true;
        if (this.h != null) {
            hd.a.removeCallbacks(this.h);
        }
        Handler handler = hd.a;
        agc agc = new agc(this);
        this.h = agc;
        handler.postDelayed(agc, this.j);
    }

    public final void onActivityResumed(Activity activity) {
        a(activity);
        this.e = false;
        boolean z = !this.d;
        this.d = true;
        if (this.h != null) {
            hd.a.removeCallbacks(this.h);
        }
        synchronized (this.c) {
            for (agq a2 : this.g) {
                try {
                    a2.a(activity);
                } catch (Exception e2) {
                    ao.i().a((Throwable) e2, "AppActivityTracker.ActivityListener.onActivityResumed");
                    ka.b("", e2);
                }
            }
            if (z) {
                for (agd a3 : this.f) {
                    try {
                        a3.a(true);
                    } catch (Exception e3) {
                        ka.b("", e3);
                    }
                }
            } else {
                gv.b("App is still foreground.");
            }
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
        a(activity);
    }

    public final void onActivityStopped(Activity activity) {
    }
}
