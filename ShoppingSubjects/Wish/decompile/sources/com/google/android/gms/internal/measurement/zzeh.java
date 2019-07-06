package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzez.zza;
import java.lang.reflect.InvocationTargetException;

public final class zzeh extends zzhi {
    private Boolean zzagi;
    private zzej zzagj = zzei.zzagk;
    private Boolean zzxy;

    zzeh(zzgn zzgn) {
        super(zzgn);
    }

    static String zzhn() {
        return (String) zzez.zzaie.get();
    }

    public static long zzhq() {
        return ((Long) zzez.zzajh.get()).longValue();
    }

    public static long zzhr() {
        return ((Long) zzez.zzaih.get()).longValue();
    }

    public static boolean zzht() {
        return ((Boolean) zzez.zzaid.get()).booleanValue();
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final long zza(String str, zza<Long> zza) {
        if (str != null) {
            String zze = this.zzagj.zze(str, zza.getKey());
            if (!TextUtils.isEmpty(zze)) {
                try {
                    return ((Long) zza.get(Long.valueOf(Long.parseLong(zze)))).longValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return ((Long) zza.get()).longValue();
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzej zzej) {
        this.zzagj = zzej;
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    public final int zzas(String str) {
        return zzb(str, zzez.zzais);
    }

    /* access modifiers changed from: 0000 */
    public final Boolean zzat(String str) {
        Preconditions.checkNotEmpty(str);
        try {
            if (getContext().getPackageManager() == null) {
                zzgi().zziv().log("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(getContext()).getApplicationInfo(getContext().getPackageName(), 128);
            if (applicationInfo == null) {
                zzgi().zziv().log("Failed to load metadata: ApplicationInfo is null");
                return null;
            } else if (applicationInfo.metaData == null) {
                zzgi().zziv().log("Failed to load metadata: Metadata bundle is null");
                return null;
            } else if (!applicationInfo.metaData.containsKey(str)) {
                return null;
            } else {
                return Boolean.valueOf(applicationInfo.metaData.getBoolean(str));
            }
        } catch (NameNotFoundException e) {
            zzgi().zziv().zzg("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    public final boolean zzau(String str) {
        return "1".equals(this.zzagj.zze(str, "gaia_collection_enabled"));
    }

    public final boolean zzav(String str) {
        return "1".equals(this.zzagj.zze(str, "measurement.event_sampling_enabled"));
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzaw(String str) {
        return zzd(str, zzez.zzajq);
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzax(String str) {
        return zzd(str, zzez.zzajs);
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzay(String str) {
        return zzd(str, zzez.zzajt);
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzaz(String str) {
        return zzd(str, zzez.zzajl);
    }

    public final int zzb(String str, zza<Integer> zza) {
        if (str != null) {
            String zze = this.zzagj.zze(str, zza.getKey());
            if (!TextUtils.isEmpty(zze)) {
                try {
                    return ((Integer) zza.get(Integer.valueOf(Integer.parseInt(zze)))).intValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return ((Integer) zza.get()).intValue();
    }

    /* access modifiers changed from: 0000 */
    public final String zzba(String str) {
        zza<String> zza = zzez.zzajm;
        return (String) (str == null ? zza.get() : zza.get(this.zzagj.zze(str, zza.getKey())));
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzbb(String str) {
        return zzd(str, zzez.zzaju);
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzbc(String str) {
        return zzd(str, zzez.zzajv);
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzbd(String str) {
        return zzd(str, zzez.zzajy);
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final double zzc(String str, zza<Double> zza) {
        if (str != null) {
            String zze = this.zzagj.zze(str, zza.getKey());
            if (!TextUtils.isEmpty(zze)) {
                try {
                    return ((Double) zza.get(Double.valueOf(Double.parseDouble(zze)))).doubleValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return ((Double) zza.get()).doubleValue();
    }

    public final boolean zzd(String str, zza<Boolean> zza) {
        Object obj;
        if (str != null) {
            String zze = this.zzagj.zze(str, zza.getKey());
            if (!TextUtils.isEmpty(zze)) {
                obj = zza.get(Boolean.valueOf(Boolean.parseBoolean(zze)));
                return ((Boolean) obj).booleanValue();
            }
        }
        obj = zza.get();
        return ((Boolean) obj).booleanValue();
    }

    public final boolean zzds() {
        if (this.zzxy == null) {
            synchronized (this) {
                if (this.zzxy == null) {
                    ApplicationInfo applicationInfo = getContext().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzxy = Boolean.valueOf(str != null && str.equals(myProcessName));
                    }
                    if (this.zzxy == null) {
                        this.zzxy = Boolean.TRUE;
                        zzgi().zziv().log("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzxy.booleanValue();
    }

    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    public final /* bridge */ /* synthetic */ void zzfw() {
        super.zzfw();
    }

    public final /* bridge */ /* synthetic */ zzer zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzfg zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzkd zzgg() {
        return super.zzgg();
    }

    public final /* bridge */ /* synthetic */ zzgi zzgh() {
        return super.zzgh();
    }

    public final /* bridge */ /* synthetic */ zzfi zzgi() {
        return super.zzgi();
    }

    public final /* bridge */ /* synthetic */ zzft zzgj() {
        return super.zzgj();
    }

    public final /* bridge */ /* synthetic */ zzeh zzgk() {
        return super.zzgk();
    }

    public final /* bridge */ /* synthetic */ zzee zzgl() {
        return super.zzgl();
    }

    public final long zzgw() {
        zzgl();
        return 12780;
    }

    public final boolean zzho() {
        zzgl();
        Boolean zzat = zzat("firebase_analytics_collection_deactivated");
        return zzat != null && zzat.booleanValue();
    }

    public final Boolean zzhp() {
        zzgl();
        return zzat("firebase_analytics_collection_enabled");
    }

    public final String zzhs() {
        String str;
        zzfk zzfk;
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{"debug.firebase.analytics.app", ""});
        } catch (ClassNotFoundException e) {
            e = e;
            zzfk = zzgi().zziv();
            str = "Could not find SystemProperties class";
            zzfk.zzg(str, e);
            return "";
        } catch (NoSuchMethodException e2) {
            e = e2;
            zzfk = zzgi().zziv();
            str = "Could not find SystemProperties.get() method";
            zzfk.zzg(str, e);
            return "";
        } catch (IllegalAccessException e3) {
            e = e3;
            zzfk = zzgi().zziv();
            str = "Could not access SystemProperties.get()";
            zzfk.zzg(str, e);
            return "";
        } catch (InvocationTargetException e4) {
            e = e4;
            zzfk = zzgi().zziv();
            str = "SystemProperties.get() threw an exception";
            zzfk.zzg(str, e);
            return "";
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzhu() {
        if (this.zzagi == null) {
            this.zzagi = zzat("app_measurement_lite");
            if (this.zzagi == null) {
                this.zzagi = Boolean.valueOf(false);
            }
        }
        return this.zzagi.booleanValue();
    }
}
