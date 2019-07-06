package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@bu
public final class fq {
    private final AtomicReference<ThreadPoolExecutor> a = new AtomicReference<>(null);
    private final Object b = new Object();
    @Nullable
    private String c = null;
    @VisibleForTesting
    private final AtomicBoolean d = new AtomicBoolean(false);
    @VisibleForTesting
    private final AtomicInteger e = new AtomicInteger(-1);
    private final AtomicReference<Object> f = new AtomicReference<>(null);
    private final AtomicReference<Object> g = new AtomicReference<>(null);
    private ConcurrentMap<String, Method> h = new ConcurrentHashMap(9);

    private static Bundle a(Context context, String str, boolean z) {
        Bundle bundle = new Bundle();
        try {
            bundle.putLong("_aeid", Long.parseLong(str));
        } catch (NullPointerException | NumberFormatException e2) {
            String str2 = "Invalid event ID: ";
            String valueOf = String.valueOf(str);
            gv.b(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e2);
        }
        if (z) {
            bundle.putInt("_r", 1);
        }
        return bundle;
    }

    private final Object a(String str, Context context) {
        if (!a(context, "com.google.android.gms.measurement.AppMeasurement", this.f, true)) {
            return null;
        }
        try {
            return h(context, str).invoke(this.f.get(), new Object[0]);
        } catch (Exception e2) {
            a(e2, str, true);
            return null;
        }
    }

    private final void a(Context context, String str, Bundle bundle) {
        if (a(context) && a(context, "com.google.android.gms.measurement.AppMeasurement", this.f, true)) {
            Method l = l(context);
            try {
                l.invoke(this.f.get(), new Object[]{"am", str, bundle});
            } catch (Exception e2) {
                a(e2, "logEventInternal", true);
            }
        }
    }

    private final void a(Exception exc, String str, boolean z) {
        if (!this.d.get()) {
            StringBuilder sb = new StringBuilder(30 + String.valueOf(str).length());
            sb.append("Invoke Firebase method ");
            sb.append(str);
            sb.append(" error.");
            gv.e(sb.toString());
            if (z) {
                gv.e("The Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires the latest Firebase SDK jar, but Firebase SDK is either missing or out of date");
                this.d.set(true);
            }
        }
    }

    private final boolean a(Context context, String str, AtomicReference<Object> atomicReference, boolean z) {
        if (atomicReference.get() != null) {
            return true;
        }
        try {
            atomicReference.compareAndSet(null, context.getClassLoader().loadClass(str).getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{context}));
            return true;
        } catch (Exception e2) {
            a(e2, "getInstance", z);
            return false;
        }
    }

    private final void b(Context context, String str, String str2) {
        if (a(context, "com.google.android.gms.measurement.AppMeasurement", this.f, true)) {
            Method g2 = g(context, str2);
            try {
                g2.invoke(this.f.get(), new Object[]{str});
                StringBuilder sb = new StringBuilder(37 + String.valueOf(str2).length() + String.valueOf(str).length());
                sb.append("Invoke Firebase method ");
                sb.append(str2);
                sb.append(", Ad Unit Id: ");
                sb.append(str);
                gv.a(sb.toString());
            } catch (Exception e2) {
                a(e2, str2, false);
            }
        }
    }

    private final Method g(Context context, String str) {
        Method method = (Method) this.h.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, new Class[]{String.class});
            this.h.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e2) {
            a(e2, str, false);
            return null;
        }
    }

    private final Method h(Context context, String str) {
        Method method = (Method) this.h.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod(str, new Class[0]);
            this.h.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e2) {
            a(e2, str, false);
            return null;
        }
    }

    private final Method i(Context context, String str) {
        Method method = (Method) this.h.get(str);
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics").getDeclaredMethod(str, new Class[]{Activity.class, String.class, String.class});
            this.h.put(str, declaredMethod);
            return declaredMethod;
        } catch (Exception e2) {
            a(e2, str, false);
            return null;
        }
    }

    private final Method l(Context context) {
        Method method = (Method) this.h.get("logEventInternal");
        if (method != null) {
            return method;
        }
        try {
            Method declaredMethod = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement").getDeclaredMethod("logEventInternal", new Class[]{String.class, String.class, Bundle.class});
            this.h.put("logEventInternal", declaredMethod);
            return declaredMethod;
        } catch (Exception e2) {
            a(e2, "logEventInternal", true);
            return null;
        }
    }

    public final void a(Context context, String str) {
        if (a(context)) {
            b(context, str, "beginAdUnitExposure");
        }
    }

    public final void a(Context context, String str, String str2) {
        if (a(context)) {
            a(context, str, a(context, str2, "_ac".equals(str)));
        }
    }

    public final void a(Context context, String str, String str2, String str3, int i) {
        if (a(context)) {
            Bundle a2 = a(context, str, false);
            a2.putString("_ai", str2);
            a2.putString("type", str3);
            a2.putInt(ResponseConstants.VALUE, i);
            a(context, "_ar", a2);
            StringBuilder sb = new StringBuilder(75 + String.valueOf(str3).length());
            sb.append("Log a Firebase reward video event, reward type: ");
            sb.append(str3);
            sb.append(", reward value: ");
            sb.append(i);
            gv.a(sb.toString());
        }
    }

    public final boolean a(Context context) {
        if (!((Boolean) ajh.f().a(akl.al)).booleanValue() || this.d.get()) {
            return false;
        }
        if (this.e.get() == -1) {
            ajh.a();
            if (!jp.c(context)) {
                ajh.a();
                if (jp.f(context)) {
                    gv.e("Google Play Service is out of date, the Google Mobile Ads SDK will not integrate with Firebase. Admob/Firebase integration requires updated Google Play Service.");
                    this.e.set(0);
                }
            }
            this.e.set(1);
        }
        return this.e.get() == 1;
    }

    public final void b(Context context, String str) {
        if (a(context)) {
            b(context, str, "endAdUnitExposure");
        }
    }

    public final boolean b(Context context) {
        return ((Boolean) ajh.f().a(akl.am)).booleanValue() && a(context);
    }

    public final void c(Context context, String str) {
        if (a(context) && (context instanceof Activity) && a(context, "com.google.firebase.analytics.FirebaseAnalytics", this.g, false)) {
            Method i = i(context, "setCurrentScreen");
            try {
                Activity activity = (Activity) context;
                i.invoke(this.g.get(), new Object[]{activity, str, context.getPackageName()});
            } catch (Exception e2) {
                a(e2, "setCurrentScreen", false);
            }
        }
    }

    public final boolean c(Context context) {
        return ((Boolean) ajh.f().a(akl.an)).booleanValue() && a(context);
    }

    public final void d(Context context, String str) {
        a(context, "_ac", str);
    }

    public final boolean d(Context context) {
        return ((Boolean) ajh.f().a(akl.ao)).booleanValue() && a(context);
    }

    public final void e(Context context, String str) {
        a(context, "_ai", str);
    }

    public final boolean e(Context context) {
        return ((Boolean) ajh.f().a(akl.ap)).booleanValue() && a(context);
    }

    public final void f(Context context, String str) {
        a(context, "_aq", str);
    }

    public final boolean f(Context context) {
        return ((Boolean) ajh.f().a(akl.as)).booleanValue() && a(context);
    }

    public final String g(Context context) {
        if (!a(context)) {
            return "";
        }
        if (!a(context, "com.google.android.gms.measurement.AppMeasurement", this.f, true)) {
            return "";
        }
        try {
            String str = (String) h(context, "getCurrentScreenName").invoke(this.f.get(), new Object[0]);
            if (str == null) {
                str = (String) h(context, "getCurrentScreenClass").invoke(this.f.get(), new Object[0]);
            }
            return str != null ? str : "";
        } catch (Exception e2) {
            a(e2, "getCurrentScreenName", false);
            return "";
        }
    }

    @Nullable
    public final String h(Context context) {
        if (!a(context)) {
            return null;
        }
        synchronized (this.b) {
            if (this.c != null) {
                String str = this.c;
                return str;
            }
            this.c = (String) a("getGmpAppId", context);
            String str2 = this.c;
            return str2;
        }
    }

    @Nullable
    public final String i(Context context) {
        if (!a(context)) {
            return null;
        }
        long longValue = ((Long) ajh.f().a(akl.av)).longValue();
        if (longValue < 0) {
            return (String) a("getAppInstanceId", context);
        }
        if (this.a.get() == null) {
            AtomicReference<ThreadPoolExecutor> atomicReference = this.a;
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(((Integer) ajh.f().a(akl.aw)).intValue(), ((Integer) ajh.f().a(akl.aw)).intValue(), 1, TimeUnit.MINUTES, new LinkedBlockingQueue(), new ft(this));
            atomicReference.compareAndSet(null, threadPoolExecutor);
        }
        Future submit = ((ThreadPoolExecutor) this.a.get()).submit(new fr(this, context));
        try {
            return (String) submit.get(longValue, TimeUnit.MILLISECONDS);
        } catch (Exception e2) {
            submit.cancel(true);
            if (e2 instanceof TimeoutException) {
                return "TIME_OUT";
            }
            return null;
        }
    }

    @Nullable
    public final String j(Context context) {
        if (!a(context)) {
            return null;
        }
        Object a2 = a("generateEventId", context);
        if (a2 != null) {
            return a2.toString();
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ String k(Context context) throws Exception {
        return (String) a("getAppInstanceId", context);
    }
}
