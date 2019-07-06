package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.util.Map;

public final class zzih extends zzdz {
    protected zzig zzaqd;
    private volatile zzig zzaqe;
    private zzig zzaqf;
    private final Map<Activity, zzig> zzaqg = new ArrayMap();
    private zzig zzaqh;
    private String zzaqi;

    public zzih(zzgn zzgn) {
        super(zzgn);
    }

    private final void zza(Activity activity, zzig zzig, boolean z) {
        zzig zzig2 = this.zzaqe == null ? this.zzaqf : this.zzaqe;
        if (zzig.zzaqa == null) {
            zzig = new zzig(zzig.zzuk, zzcd(activity.getClass().getCanonicalName()), zzig.zzaqb);
        }
        this.zzaqf = this.zzaqe;
        this.zzaqe = zzig;
        zzgh().zzc((Runnable) new zzii(this, z, zzig2, zzig));
    }

    /* access modifiers changed from: private */
    public final void zza(zzig zzig) {
        zzfx().zzp(zzbt().elapsedRealtime());
        if (zzgd().zzl(zzig.zzaqc)) {
            zzig.zzaqc = false;
        }
    }

    public static void zza(zzig zzig, Bundle bundle, boolean z) {
        if (bundle == null || zzig == null || (bundle.containsKey("_sc") && !z)) {
            if (bundle != null && zzig == null && z) {
                bundle.remove("_sn");
                bundle.remove("_sc");
                bundle.remove("_si");
            }
            return;
        }
        if (zzig.zzuk != null) {
            bundle.putString("_sn", zzig.zzuk);
        } else {
            bundle.remove("_sn");
        }
        bundle.putString("_sc", zzig.zzaqa);
        bundle.putLong("_si", zzig.zzaqb);
    }

    private static String zzcd(String str) {
        String[] split = str.split("\\.");
        String str2 = split.length > 0 ? split[split.length - 1] : "";
        return str2.length() > 100 ? str2.substring(0, 100) : str2;
    }

    private final zzig zze(Activity activity) {
        Preconditions.checkNotNull(activity);
        zzig zzig = (zzig) this.zzaqg.get(activity);
        if (zzig != null) {
            return zzig;
        }
        zzig zzig2 = new zzig(null, zzcd(activity.getClass().getCanonicalName()), zzgg().zzln());
        this.zzaqg.put(activity, zzig2);
        return zzig2;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        if (bundle != null) {
            Bundle bundle2 = bundle.getBundle("com.google.firebase.analytics.screen_service");
            if (bundle2 != null) {
                this.zzaqg.put(activity, new zzig(bundle2.getString("name"), bundle2.getString("referrer_name"), bundle2.getLong("id")));
            }
        }
    }

    public final void onActivityDestroyed(Activity activity) {
        this.zzaqg.remove(activity);
    }

    public final void onActivityPaused(Activity activity) {
        zzig zze = zze(activity);
        this.zzaqf = this.zzaqe;
        this.zzaqe = null;
        zzgh().zzc((Runnable) new zzij(this, zze));
    }

    public final void onActivityResumed(Activity activity) {
        zza(activity, zze(activity), false);
        zzdu zzfx = zzfx();
        zzfx.zzgh().zzc((Runnable) new zzdx(zzfx, zzfx.zzbt().elapsedRealtime()));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        if (bundle != null) {
            zzig zzig = (zzig) this.zzaqg.get(activity);
            if (zzig != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putLong("id", zzig.zzaqb);
                bundle2.putString("name", zzig.zzuk);
                bundle2.putString("referrer_name", zzig.zzaqa);
                bundle.putBundle("com.google.firebase.analytics.screen_service", bundle2);
            }
        }
    }

    public final void setCurrentScreen(Activity activity, String str, String str2) {
        if (!zzee.isMainThread()) {
            zzgi().zziy().log("setCurrentScreen must be called from the main thread");
        } else if (this.zzaqe == null) {
            zzgi().zziy().log("setCurrentScreen cannot be called while no activity active");
        } else if (this.zzaqg.get(activity) == null) {
            zzgi().zziy().log("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = zzcd(activity.getClass().getCanonicalName());
            }
            boolean equals = this.zzaqe.zzaqa.equals(str2);
            boolean zzs = zzkd.zzs(this.zzaqe.zzuk, str);
            if (equals && zzs) {
                zzgi().zziz().log("setCurrentScreen cannot be called with the same class and name");
            } else if (str != null && (str.length() <= 0 || str.length() > 100)) {
                zzgi().zziy().zzg("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            } else if (str2 == null || (str2.length() > 0 && str2.length() <= 100)) {
                zzgi().zzjc().zze("Setting current screen to name, class", str == null ? "null" : str, str2);
                zzig zzig = new zzig(str, str2, zzgg().zzln());
                this.zzaqg.put(activity, zzig);
                zza(activity, zzig, true);
            } else {
                zzgi().zziy().zzg("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            }
        }
    }

    public final void zza(String str, zzig zzig) {
        zzab();
        synchronized (this) {
            if (this.zzaqi == null || this.zzaqi.equals(str) || zzig != null) {
                this.zzaqi = str;
                this.zzaqh = zzig;
            }
        }
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
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

    public final /* bridge */ /* synthetic */ zzdu zzfx() {
        return super.zzfx();
    }

    public final /* bridge */ /* synthetic */ zzhm zzfy() {
        return super.zzfy();
    }

    public final /* bridge */ /* synthetic */ zzfd zzfz() {
        return super.zzfz();
    }

    public final /* bridge */ /* synthetic */ zzik zzga() {
        return super.zzga();
    }

    public final /* bridge */ /* synthetic */ zzih zzgb() {
        return super.zzgb();
    }

    public final /* bridge */ /* synthetic */ zzfe zzgc() {
        return super.zzgc();
    }

    public final /* bridge */ /* synthetic */ zzjj zzgd() {
        return super.zzgd();
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

    /* access modifiers changed from: protected */
    public final boolean zzgn() {
        return false;
    }

    public final zzig zzkn() {
        zzch();
        zzab();
        return this.zzaqd;
    }

    public final zzig zzko() {
        zzfv();
        return this.zzaqe;
    }
}
