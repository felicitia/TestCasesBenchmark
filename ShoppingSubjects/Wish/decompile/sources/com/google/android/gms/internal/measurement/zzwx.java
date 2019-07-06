package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build.VERSION;
import android.support.v4.content.PermissionChecker;
import android.util.Log;

public abstract class zzwx<T> {
    private static final Object zzbpg = new Object();
    private static boolean zzbph = false;
    private static volatile Boolean zzbpi;
    @SuppressLint({"StaticFieldLeak"})
    private static Context zzqx;
    private final zzxh zzbpj;
    final String zzbpk;
    private final String zzbpl;
    private final T zzbpm;
    private T zzbpn;
    private volatile zzwu zzbpo;
    private volatile SharedPreferences zzbpp;

    private zzwx(zzxh zzxh, String str, T t) {
        this.zzbpn = null;
        this.zzbpo = null;
        this.zzbpp = null;
        if (zzxh.zzbpv == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.zzbpj = zzxh;
        String valueOf = String.valueOf(zzxh.zzbpw);
        String valueOf2 = String.valueOf(str);
        this.zzbpl = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        String valueOf3 = String.valueOf(zzxh.zzbpx);
        String valueOf4 = String.valueOf(str);
        this.zzbpk = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
        this.zzbpm = t;
    }

    /* synthetic */ zzwx(zzxh zzxh, String str, Object obj, zzxb zzxb) {
        this(zzxh, str, obj);
    }

    public static void init(Context context) {
        synchronized (zzbpg) {
            if (VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
            }
            if (zzqx != context) {
                zzbpi = null;
            }
            zzqx = context;
        }
        zzbph = false;
    }

    /* access modifiers changed from: private */
    public static zzwx<Double> zza(zzxh zzxh, String str, double d) {
        return new zzxe(zzxh, str, Double.valueOf(d));
    }

    /* access modifiers changed from: private */
    public static zzwx<Integer> zza(zzxh zzxh, String str, int i) {
        return new zzxc(zzxh, str, Integer.valueOf(i));
    }

    /* access modifiers changed from: private */
    public static zzwx<Long> zza(zzxh zzxh, String str, long j) {
        return new zzxb(zzxh, str, Long.valueOf(j));
    }

    /* access modifiers changed from: private */
    public static zzwx<String> zza(zzxh zzxh, String str, String str2) {
        return new zzxf(zzxh, str, str2);
    }

    /* access modifiers changed from: private */
    public static zzwx<Boolean> zza(zzxh zzxh, String str, boolean z) {
        return new zzxd(zzxh, str, Boolean.valueOf(z));
    }

    private static <V> V zza(zzxg<V> zzxg) {
        long clearCallingIdentity;
        try {
            return zzxg.zzsq();
        } catch (SecurityException unused) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            V zzsq = zzxg.zzsq();
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return zzsq;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }

    static boolean zzd(String str, boolean z) {
        try {
            if (zzso()) {
                return ((Boolean) zza(new zzxa(str, false))).booleanValue();
            }
            return false;
        } catch (SecurityException e) {
            Log.e("PhenotypeFlag", "Unable to read GServices, returning default value.", e);
            return false;
        }
    }

    @TargetApi(24)
    private final T zzsm() {
        if (zzd("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false)) {
            String str = "PhenotypeFlag";
            String str2 = "Bypass reading Phenotype values for flag: ";
            String valueOf = String.valueOf(this.zzbpk);
            Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else if (this.zzbpj.zzbpv != null) {
            if (this.zzbpo == null) {
                this.zzbpo = zzwu.zza(zzqx.getContentResolver(), this.zzbpj.zzbpv);
            }
            String str3 = (String) zza(new zzwy(this, this.zzbpo));
            if (str3 != null) {
                return zzfa(str3);
            }
        } else {
            zzxh zzxh = this.zzbpj;
        }
        return null;
    }

    private final T zzsn() {
        zzxh zzxh = this.zzbpj;
        if (zzso()) {
            try {
                String str = (String) zza(new zzwz(this));
                if (str != null) {
                    return zzfa(str);
                }
            } catch (SecurityException e) {
                String str2 = "PhenotypeFlag";
                String str3 = "Unable to read GServices for flag: ";
                String valueOf = String.valueOf(this.zzbpk);
                Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), e);
            }
        }
        return null;
    }

    private static boolean zzso() {
        if (zzbpi == null) {
            boolean z = false;
            if (zzqx == null) {
                return false;
            }
            if (PermissionChecker.checkSelfPermission(zzqx, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
                z = true;
            }
            zzbpi = Boolean.valueOf(z);
        }
        return zzbpi.booleanValue();
    }

    public final T get() {
        if (zzqx == null) {
            throw new IllegalStateException("Must call PhenotypeFlag.init() first");
        }
        zzxh zzxh = this.zzbpj;
        T zzsm = zzsm();
        if (zzsm != null) {
            return zzsm;
        }
        T zzsn = zzsn();
        return zzsn != null ? zzsn : this.zzbpm;
    }

    /* access modifiers changed from: protected */
    public abstract T zzfa(String str);

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ String zzsp() {
        return zzws.zza(zzqx.getContentResolver(), this.zzbpl, (String) null);
    }
}
