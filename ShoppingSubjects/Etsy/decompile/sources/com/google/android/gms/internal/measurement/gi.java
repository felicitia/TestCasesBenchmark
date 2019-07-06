package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build.VERSION;
import android.support.v4.content.PermissionChecker;
import android.util.Log;

public abstract class gi<T> {
    private static final Object b = new Object();
    @SuppressLint({"StaticFieldLeak"})
    private static Context c = null;
    private static boolean d = false;
    private static volatile Boolean e;
    final String a;
    private final gs f;
    private final String g;
    private final T h;
    private T i;
    private volatile gf j;
    private volatile SharedPreferences k;

    private gi(gs gsVar, String str, T t) {
        this.i = null;
        this.j = null;
        this.k = null;
        if (gsVar.b == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.f = gsVar;
        String valueOf = String.valueOf(gsVar.c);
        String valueOf2 = String.valueOf(str);
        this.g = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        String valueOf3 = String.valueOf(gsVar.d);
        String valueOf4 = String.valueOf(str);
        this.a = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
        this.h = t;
    }

    /* synthetic */ gi(gs gsVar, String str, Object obj, gm gmVar) {
        this(gsVar, str, obj);
    }

    private static <V> V a(gr<V> grVar) {
        long clearCallingIdentity;
        try {
            return grVar.a();
        } catch (SecurityException unused) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            V a2 = grVar.a();
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return a2;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }

    public static void a(Context context) {
        synchronized (b) {
            if (VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
            }
            if (c != context) {
                e = null;
            }
            c = context;
        }
        d = false;
    }

    static boolean a(String str, boolean z) {
        try {
            if (e()) {
                return ((Boolean) a((gr<V>) new gl<V>(str, false))).booleanValue();
            }
            return false;
        } catch (SecurityException e2) {
            Log.e("PhenotypeFlag", "Unable to read GServices, returning default value.", e2);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static gi<Double> b(gs gsVar, String str, double d2) {
        return new gp(gsVar, str, Double.valueOf(d2));
    }

    /* access modifiers changed from: private */
    public static gi<Integer> b(gs gsVar, String str, int i2) {
        return new gn(gsVar, str, Integer.valueOf(i2));
    }

    /* access modifiers changed from: private */
    public static gi<Long> b(gs gsVar, String str, long j2) {
        return new gm(gsVar, str, Long.valueOf(j2));
    }

    /* access modifiers changed from: private */
    public static gi<String> b(gs gsVar, String str, String str2) {
        return new gq(gsVar, str, str2);
    }

    /* access modifiers changed from: private */
    public static gi<Boolean> b(gs gsVar, String str, boolean z) {
        return new go(gsVar, str, Boolean.valueOf(z));
    }

    @TargetApi(24)
    private final T c() {
        if (a("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false)) {
            String str = "PhenotypeFlag";
            String str2 = "Bypass reading Phenotype values for flag: ";
            String valueOf = String.valueOf(this.a);
            Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else if (this.f.b != null) {
            if (this.j == null) {
                this.j = gf.a(c.getContentResolver(), this.f.b);
            }
            String str3 = (String) a((gr<V>) new gj<V>(this, this.j));
            if (str3 != null) {
                return a(str3);
            }
        } else {
            gs gsVar = this.f;
        }
        return null;
    }

    private final T d() {
        gs gsVar = this.f;
        if (e()) {
            try {
                String str = (String) a((gr<V>) new gk<V>(this));
                if (str != null) {
                    return a(str);
                }
            } catch (SecurityException e2) {
                String str2 = "PhenotypeFlag";
                String str3 = "Unable to read GServices for flag: ";
                String valueOf = String.valueOf(this.a);
                Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), e2);
            }
        }
        return null;
    }

    private static boolean e() {
        if (e == null) {
            boolean z = false;
            if (c == null) {
                return false;
            }
            if (PermissionChecker.checkSelfPermission(c, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
                z = true;
            }
            e = Boolean.valueOf(z);
        }
        return e.booleanValue();
    }

    public final T a() {
        if (c == null) {
            throw new IllegalStateException("Must call PhenotypeFlag.init() first");
        }
        gs gsVar = this.f;
        T c2 = c();
        if (c2 != null) {
            return c2;
        }
        T d2 = d();
        return d2 != null ? d2 : this.h;
    }

    /* access modifiers changed from: protected */
    public abstract T a(String str);

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ String b() {
        return gd.a(c.getContentResolver(), this.g, (String) null);
    }
}
