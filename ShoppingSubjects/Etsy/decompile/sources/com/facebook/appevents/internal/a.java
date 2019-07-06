package com.facebook.appevents.internal;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.codeless.CodelessMatcher;
import com.facebook.f;
import com.facebook.internal.j;
import com.facebook.internal.k;
import com.facebook.internal.t;
import com.facebook.internal.z;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: ActivityLifecycleTracker */
public class a {
    /* access modifiers changed from: private */
    public static final String a = a.class.getCanonicalName();
    /* access modifiers changed from: private */
    public static final ScheduledExecutorService b = Executors.newSingleThreadScheduledExecutor();
    /* access modifiers changed from: private */
    public static volatile ScheduledFuture c;
    /* access modifiers changed from: private */
    public static final Object d = new Object();
    /* access modifiers changed from: private */
    public static AtomicInteger e = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public static volatile g f;
    private static AtomicBoolean g = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public static String h;
    /* access modifiers changed from: private */
    public static long i;
    private static final CodelessMatcher j = new CodelessMatcher();

    public static void a(Application application, String str) {
        if (g.compareAndSet(false, true)) {
            h = str;
            application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    t.a(LoggingBehavior.APP_EVENTS, a.a, "onActivityCreated");
                    b.b();
                    a.a(activity);
                }

                public void onActivityStarted(Activity activity) {
                    t.a(LoggingBehavior.APP_EVENTS, a.a, "onActivityStarted");
                }

                public void onActivityResumed(Activity activity) {
                    t.a(LoggingBehavior.APP_EVENTS, a.a, "onActivityResumed");
                    b.b();
                    a.b(activity);
                }

                public void onActivityPaused(Activity activity) {
                    t.a(LoggingBehavior.APP_EVENTS, a.a, "onActivityPaused");
                    b.b();
                    a.d(activity);
                }

                public void onActivityStopped(Activity activity) {
                    t.a(LoggingBehavior.APP_EVENTS, a.a, "onActivityStopped");
                    AppEventsLogger.c();
                }

                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    t.a(LoggingBehavior.APP_EVENTS, a.a, "onActivitySaveInstanceState");
                }

                public void onActivityDestroyed(Activity activity) {
                    t.a(LoggingBehavior.APP_EVENTS, a.a, "onActivityDestroyed");
                }
            });
        }
    }

    public static UUID a() {
        if (f != null) {
            return f.g();
        }
        return null;
    }

    public static void a(Activity activity) {
        final long currentTimeMillis = System.currentTimeMillis();
        final Context applicationContext = activity.getApplicationContext();
        final String c2 = z.c((Context) activity);
        final i a2 = com.facebook.appevents.internal.i.a.a(activity);
        AnonymousClass2 r0 = new Runnable() {
            public void run() {
                if (a.f == null) {
                    g a2 = g.a();
                    if (a2 != null) {
                        h.a(applicationContext, c2, a2, a.h);
                    }
                    a.f = new g(Long.valueOf(currentTimeMillis), null);
                    a.f.a(a2);
                    h.a(applicationContext, c2, a2, a.h);
                }
            }
        };
        b.execute(r0);
    }

    public static void b(Activity activity) {
        e.incrementAndGet();
        k();
        final long currentTimeMillis = System.currentTimeMillis();
        i = currentTimeMillis;
        final Context applicationContext = activity.getApplicationContext();
        final String c2 = z.c((Context) activity);
        j.a(activity);
        b.execute(new Runnable() {
            public void run() {
                if (a.f == null) {
                    a.f = new g(Long.valueOf(currentTimeMillis), null);
                    h.a(applicationContext, c2, (i) null, a.h);
                } else if (a.f.c() != null) {
                    long longValue = currentTimeMillis - a.f.c().longValue();
                    if (longValue > ((long) (a.j() * 1000))) {
                        h.a(applicationContext, c2, a.f, a.h);
                        h.a(applicationContext, c2, (i) null, a.h);
                        a.f = new g(Long.valueOf(currentTimeMillis), null);
                    } else if (longValue > 1000) {
                        a.f.e();
                    }
                }
                a.f.a(Long.valueOf(currentTimeMillis));
                a.f.j();
            }
        });
    }

    /* access modifiers changed from: private */
    public static void d(Activity activity) {
        if (e.decrementAndGet() < 0) {
            e.set(0);
            Log.w(a, "Unexpected activity pause without a matching activity resume. Logging data may be incorrect. Make sure you call activateApp from your Application's onCreate method");
        }
        k();
        final long currentTimeMillis = System.currentTimeMillis();
        final Context applicationContext = activity.getApplicationContext();
        final String c2 = z.c((Context) activity);
        j.b(activity);
        b.execute(new Runnable() {
            public void run() {
                if (a.f == null) {
                    a.f = new g(Long.valueOf(currentTimeMillis), null);
                }
                a.f.a(Long.valueOf(currentTimeMillis));
                if (a.e.get() <= 0) {
                    AnonymousClass1 r0 = new Runnable() {
                        public void run() {
                            if (a.e.get() <= 0) {
                                h.a(applicationContext, c2, a.f, a.h);
                                g.b();
                                a.f = null;
                            }
                            synchronized (a.d) {
                                a.c = null;
                            }
                        }
                    };
                    synchronized (a.d) {
                        a.c = a.b.schedule(r0, (long) a.j(), TimeUnit.SECONDS);
                    }
                }
                long i = a.i;
                long j = 0;
                if (i > 0) {
                    j = (currentTimeMillis - i) / 1000;
                }
                c.a(c2, j);
                a.f.j();
            }
        });
    }

    /* access modifiers changed from: private */
    public static int j() {
        j a2 = k.a(f.j());
        if (a2 == null) {
            return d.a();
        }
        return a2.e();
    }

    private static void k() {
        synchronized (d) {
            if (c != null) {
                c.cancel(false);
            }
            c = null;
        }
    }
}
