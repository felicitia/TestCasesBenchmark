package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import com.google.firebase.iid.FirebaseInstanceId;
import java.math.BigInteger;
import java.util.Locale;

public final class zzfd extends zzdz {
    private String zzafa;
    private String zzafh;
    private long zzafl;
    private int zzagb;
    private int zzakd;
    private long zzake;
    private String zztf;
    private String zztg;
    private String zzth;

    zzfd(zzgn zzgn) {
        super(zzgn);
    }

    private final String zzgr() {
        zzab();
        zzfv();
        if (zzgk().zzbb(this.zzth) && !this.zzacv.isEnabled()) {
            return null;
        }
        try {
            return FirebaseInstanceId.getInstance().getId();
        } catch (IllegalStateException unused) {
            zzgi().zziy().log("Failed to retrieve Firebase Instance Id");
            return null;
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: 0000 */
    public final String getGmpAppId() {
        zzch();
        return this.zzafa;
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: 0000 */
    public final String zzah() {
        zzch();
        return this.zzth;
    }

    /* access modifiers changed from: 0000 */
    public final zzeb zzbl(String str) {
        zzab();
        zzfv();
        String zzah = zzah();
        String gmpAppId = getGmpAppId();
        zzch();
        String str2 = this.zztg;
        long zzis = (long) zzis();
        zzch();
        String str3 = this.zzafh;
        long zzgw = zzgk().zzgw();
        zzch();
        zzab();
        if (this.zzake == 0) {
            this.zzake = this.zzacv.zzgg().zzd(getContext(), getContext().getPackageName());
        }
        long j = this.zzake;
        boolean isEnabled = this.zzacv.isEnabled();
        boolean z = !zzgj().zzamm;
        String zzgr = zzgr();
        zzch();
        boolean z2 = z;
        String str4 = zzgr;
        long j2 = this.zzafl;
        long zzke = this.zzacv.zzke();
        int zzit = zzit();
        zzeh zzgk = zzgk();
        zzgk.zzfv();
        Boolean zzat = zzgk.zzat("google_analytics_adid_collection_enabled");
        boolean booleanValue = Boolean.valueOf(zzat == null || zzat.booleanValue()).booleanValue();
        zzeh zzgk2 = zzgk();
        zzgk2.zzfv();
        Boolean zzat2 = zzgk2.zzat("google_analytics_ssaid_collection_enabled");
        zzeb zzeb = new zzeb(zzah, gmpAppId, str2, zzis, str3, zzgw, j, str, isEnabled, z2, str4, j2, zzke, zzit, booleanValue, Boolean.valueOf(zzat2 == null || zzat2.booleanValue()).booleanValue(), zzgj().zzjo());
        return zzeb;
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
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x014c A[SYNTHETIC, Splitter:B:55:0x014c] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0191  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzgo() {
        /*
            r10 = this;
            java.lang.String r0 = "unknown"
            java.lang.String r1 = "Unknown"
            java.lang.String r2 = "Unknown"
            android.content.Context r3 = r10.getContext()
            java.lang.String r3 = r3.getPackageName()
            android.content.Context r4 = r10.getContext()
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            r5 = 0
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r4 != 0) goto L_0x002d
            com.google.android.gms.internal.measurement.zzfi r4 = r10.zzgi()
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()
            java.lang.String r7 = "PackageManager is null, app identity information might be inaccurate. appId"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3)
            r4.zzg(r7, r8)
            goto L_0x008b
        L_0x002d:
            java.lang.String r7 = r4.getInstallerPackageName(r3)     // Catch:{ IllegalArgumentException -> 0x0033 }
            r0 = r7
            goto L_0x0044
        L_0x0033:
            com.google.android.gms.internal.measurement.zzfi r7 = r10.zzgi()
            com.google.android.gms.internal.measurement.zzfk r7 = r7.zziv()
            java.lang.String r8 = "Error retrieving app installer package name. appId"
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3)
            r7.zzg(r8, r9)
        L_0x0044:
            if (r0 != 0) goto L_0x0049
            java.lang.String r0 = "manual_install"
            goto L_0x0053
        L_0x0049:
            java.lang.String r7 = "com.android.vending"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0053
            java.lang.String r0 = ""
        L_0x0053:
            android.content.Context r7 = r10.getContext()     // Catch:{ NameNotFoundException -> 0x007a }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x007a }
            android.content.pm.PackageInfo r7 = r4.getPackageInfo(r7, r5)     // Catch:{ NameNotFoundException -> 0x007a }
            if (r7 == 0) goto L_0x008b
            android.content.pm.ApplicationInfo r8 = r7.applicationInfo     // Catch:{ NameNotFoundException -> 0x007a }
            java.lang.CharSequence r4 = r4.getApplicationLabel(r8)     // Catch:{ NameNotFoundException -> 0x007a }
            boolean r8 = android.text.TextUtils.isEmpty(r4)     // Catch:{ NameNotFoundException -> 0x007a }
            if (r8 != 0) goto L_0x0072
            java.lang.String r4 = r4.toString()     // Catch:{ NameNotFoundException -> 0x007a }
            r2 = r4
        L_0x0072:
            java.lang.String r4 = r7.versionName     // Catch:{ NameNotFoundException -> 0x007a }
            int r1 = r7.versionCode     // Catch:{ NameNotFoundException -> 0x0079 }
            r6 = r1
            r1 = r4
            goto L_0x008b
        L_0x0079:
            r1 = r4
        L_0x007a:
            com.google.android.gms.internal.measurement.zzfi r4 = r10.zzgi()
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziv()
            java.lang.String r7 = "Error retrieving package info. appId, appName"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3)
            r4.zze(r7, r8, r2)
        L_0x008b:
            r10.zzth = r3
            r10.zzafh = r0
            r10.zztg = r1
            r10.zzakd = r6
            r10.zztf = r2
            r0 = 0
            r10.zzake = r0
            r10.zzgl()
            android.content.Context r2 = r10.getContext()
            com.google.android.gms.common.api.Status r2 = com.google.android.gms.common.api.internal.GoogleServices.initialize(r2)
            r4 = 1
            if (r2 == 0) goto L_0x00af
            boolean r6 = r2.isSuccess()
            if (r6 == 0) goto L_0x00af
            r6 = 1
            goto L_0x00b0
        L_0x00af:
            r6 = 0
        L_0x00b0:
            if (r6 != 0) goto L_0x00db
            if (r2 != 0) goto L_0x00c2
            com.google.android.gms.internal.measurement.zzfi r2 = r10.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zziv()
            java.lang.String r7 = "GoogleService failed to initialize (no status)"
            r2.log(r7)
            goto L_0x00db
        L_0x00c2:
            com.google.android.gms.internal.measurement.zzfi r7 = r10.zzgi()
            com.google.android.gms.internal.measurement.zzfk r7 = r7.zziv()
            java.lang.String r8 = "GoogleService failed to initialize, status"
            int r9 = r2.getStatusCode()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r2 = r2.getStatusMessage()
            r7.zze(r8, r9, r2)
        L_0x00db:
            if (r6 == 0) goto L_0x0131
            com.google.android.gms.internal.measurement.zzeh r2 = r10.zzgk()
            java.lang.Boolean r2 = r2.zzhp()
            com.google.android.gms.internal.measurement.zzeh r6 = r10.zzgk()
            boolean r6 = r6.zzho()
            if (r6 == 0) goto L_0x00fd
            com.google.android.gms.internal.measurement.zzfi r2 = r10.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zzja()
            java.lang.String r4 = "Collection disabled with firebase_analytics_collection_deactivated=1"
        L_0x00f9:
            r2.log(r4)
            goto L_0x0131
        L_0x00fd:
            if (r2 == 0) goto L_0x0110
            boolean r6 = r2.booleanValue()
            if (r6 != 0) goto L_0x0110
            com.google.android.gms.internal.measurement.zzfi r2 = r10.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zzja()
            java.lang.String r4 = "Collection disabled with firebase_analytics_collection_enabled=0"
            goto L_0x00f9
        L_0x0110:
            if (r2 != 0) goto L_0x0123
            boolean r2 = com.google.android.gms.common.api.internal.GoogleServices.isMeasurementExplicitlyDisabled()
            if (r2 == 0) goto L_0x0123
            com.google.android.gms.internal.measurement.zzfi r2 = r10.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zzja()
            java.lang.String r4 = "Collection disabled with google_app_measurement_enable=0"
            goto L_0x00f9
        L_0x0123:
            com.google.android.gms.internal.measurement.zzfi r2 = r10.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zzjc()
            java.lang.String r6 = "Collection enabled"
            r2.log(r6)
            goto L_0x0132
        L_0x0131:
            r4 = 0
        L_0x0132:
            java.lang.String r2 = ""
            r10.zzafa = r2
            r10.zzafl = r0
            r10.zzgl()
            com.google.android.gms.internal.measurement.zzgn r0 = r10.zzacv
            java.lang.String r0 = r0.zzkd()
            if (r0 == 0) goto L_0x014c
            com.google.android.gms.internal.measurement.zzgn r0 = r10.zzacv
            java.lang.String r0 = r0.zzkd()
            r10.zzafa = r0
            goto L_0x0180
        L_0x014c:
            java.lang.String r0 = com.google.android.gms.common.api.internal.GoogleServices.getGoogleAppId()     // Catch:{ IllegalStateException -> 0x016e }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ IllegalStateException -> 0x016e }
            if (r1 == 0) goto L_0x0158
            java.lang.String r0 = ""
        L_0x0158:
            r10.zzafa = r0     // Catch:{ IllegalStateException -> 0x016e }
            if (r4 == 0) goto L_0x0180
            com.google.android.gms.internal.measurement.zzfi r0 = r10.zzgi()     // Catch:{ IllegalStateException -> 0x016e }
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zzjc()     // Catch:{ IllegalStateException -> 0x016e }
            java.lang.String r1 = "App package, google app id"
            java.lang.String r2 = r10.zzth     // Catch:{ IllegalStateException -> 0x016e }
            java.lang.String r4 = r10.zzafa     // Catch:{ IllegalStateException -> 0x016e }
            r0.zze(r1, r2, r4)     // Catch:{ IllegalStateException -> 0x016e }
            goto L_0x0180
        L_0x016e:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzfi r1 = r10.zzgi()
            com.google.android.gms.internal.measurement.zzfk r1 = r1.zziv()
            java.lang.String r2 = "getGoogleAppId or isMeasurementEnabled failed with exception. appId"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfi.zzbp(r3)
            r1.zze(r2, r3, r0)
        L_0x0180:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 16
            if (r0 < r1) goto L_0x0191
            android.content.Context r0 = r10.getContext()
            boolean r0 = com.google.android.gms.common.wrappers.InstantApps.isInstantApp(r0)
            r10.zzagb = r0
            return
        L_0x0191:
            r10.zzagb = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfd.zzgo():void");
    }

    /* access modifiers changed from: 0000 */
    public final String zzir() {
        byte[] bArr = new byte[16];
        zzgg().zzlo().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    /* access modifiers changed from: 0000 */
    public final int zzis() {
        zzch();
        return this.zzakd;
    }

    /* access modifiers changed from: 0000 */
    public final int zzit() {
        zzch();
        return this.zzagb;
    }
}
