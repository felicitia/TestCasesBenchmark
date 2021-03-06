package com.google.android.gms.internal.measurement;

final class zzcf implements zzbt<zzcg> {
    private final zzcg zzaac = new zzcg();
    private final zzat zzvm;

    public zzcf(zzat zzat) {
        this.zzvm = zzat;
    }

    public final void zza(String str, boolean z) {
        if ("ga_dryRun".equals(str)) {
            this.zzaac.zzaah = z ? 1 : 0;
            return;
        }
        this.zzvm.zzbu().zzd("Bool xml configuration name not recognized", str);
    }

    public final void zzb(String str, int i) {
        if ("ga_dispatchPeriod".equals(str)) {
            this.zzaac.zzaag = i;
        } else {
            this.zzvm.zzbu().zzd("Int xml configuration name not recognized", str);
        }
    }

    public final void zzb(String str, String str2) {
    }

    public final void zzc(String str, String str2) {
        if ("ga_appName".equals(str)) {
            this.zzaac.zzaad = str2;
        } else if ("ga_appVersion".equals(str)) {
            this.zzaac.zzaae = str2;
        } else if ("ga_logLevel".equals(str)) {
            this.zzaac.zzaaf = str2;
        } else {
            this.zzvm.zzbu().zzd("String xml configuration name not recognized", str);
        }
    }

    public final /* synthetic */ zzbr zzdr() {
        return this.zzaac;
    }
}
