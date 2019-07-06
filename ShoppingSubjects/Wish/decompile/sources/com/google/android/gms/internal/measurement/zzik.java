package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public final class zzik extends zzdz {
    /* access modifiers changed from: private */
    public final zziy zzaqo;
    /* access modifiers changed from: private */
    public zzfa zzaqp;
    private volatile Boolean zzaqq;
    private final zzep zzaqr;
    private final zzjo zzaqs;
    private final List<Runnable> zzaqt = new ArrayList();
    private final zzep zzaqu;

    protected zzik(zzgn zzgn) {
        super(zzgn);
        this.zzaqs = new zzjo(zzgn.zzbt());
        this.zzaqo = new zziy(this);
        this.zzaqr = new zzil(this, zzgn);
        this.zzaqu = new zziq(this, zzgn);
    }

    /* access modifiers changed from: private */
    public final void onServiceDisconnected(ComponentName componentName) {
        zzab();
        if (this.zzaqp != null) {
            this.zzaqp = null;
            zzgi().zzjc().zzg("Disconnected from device MeasurementService", componentName);
            zzab();
            zzdf();
        }
    }

    /* access modifiers changed from: private */
    public final void zzcu() {
        zzab();
        this.zzaqs.start();
        this.zzaqr.zzh(((Long) zzez.zzajk.get()).longValue());
    }

    /* access modifiers changed from: private */
    public final void zzcv() {
        zzab();
        if (isConnected()) {
            zzgi().zzjc().log("Inactivity, disconnecting from the service");
            disconnect();
        }
    }

    private final void zzf(Runnable runnable) throws IllegalStateException {
        zzab();
        if (isConnected()) {
            runnable.run();
        } else if (((long) this.zzaqt.size()) >= 1000) {
            zzgi().zziv().log("Discarding data. Max runnable queue size reached");
        } else {
            this.zzaqt.add(runnable);
            this.zzaqu.zzh(60000);
            zzdf();
        }
    }

    private final zzeb zzk(boolean z) {
        zzgl();
        return zzfz().zzbl(z ? zzgi().zzje() : null);
    }

    private final boolean zzkq() {
        zzgl();
        return true;
    }

    /* access modifiers changed from: private */
    public final void zzks() {
        zzab();
        zzgi().zzjc().zzg("Processing queued up service tasks", Integer.valueOf(this.zzaqt.size()));
        for (Runnable run : this.zzaqt) {
            try {
                run.run();
            } catch (Exception e) {
                zzgi().zziv().zzg("Task exception while flushing queue", e);
            }
        }
        this.zzaqt.clear();
        this.zzaqu.cancel();
    }

    public final void disconnect() {
        zzab();
        zzch();
        try {
            ConnectionTracker.getInstance().unbindService(getContext(), this.zzaqo);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzaqp = null;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final boolean isConnected() {
        zzab();
        zzch();
        return this.zzaqp != null;
    }

    /* access modifiers changed from: protected */
    public final void resetAnalyticsData() {
        zzab();
        zzfv();
        zzch();
        zzeb zzk = zzk(false);
        if (zzkq()) {
            zzgc().resetAnalyticsData();
        }
        zzf(new zzim(this, zzk));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzfa zzfa) {
        zzab();
        Preconditions.checkNotNull(zzfa);
        this.zzaqp = zzfa;
        zzcu();
        zzks();
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.measurement.zzfa r12, com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable r13, com.google.android.gms.internal.measurement.zzeb r14) {
        /*
            r11 = this;
            r11.zzab()
            r11.zzfv()
            r11.zzch()
            boolean r0 = r11.zzkq()
            r1 = 0
            r2 = 100
            r3 = 0
            r4 = 100
        L_0x0013:
            r5 = 1001(0x3e9, float:1.403E-42)
            if (r3 >= r5) goto L_0x00a3
            if (r4 != r2) goto L_0x00a3
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            if (r0 == 0) goto L_0x0032
            com.google.android.gms.internal.measurement.zzfe r5 = r11.zzgc()
            java.util.List r5 = r5.zzp(r2)
            if (r5 == 0) goto L_0x0032
            r4.addAll(r5)
            int r5 = r5.size()
            goto L_0x0033
        L_0x0032:
            r5 = 0
        L_0x0033:
            if (r13 == 0) goto L_0x003a
            if (r5 >= r2) goto L_0x003a
            r4.add(r13)
        L_0x003a:
            java.util.ArrayList r4 = (java.util.ArrayList) r4
            int r6 = r4.size()
            r7 = 0
        L_0x0041:
            if (r7 >= r6) goto L_0x009e
            java.lang.Object r8 = r4.get(r7)
            int r7 = r7 + 1
            com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable r8 = (com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable) r8
            boolean r9 = r8 instanceof com.google.android.gms.internal.measurement.zzex
            if (r9 == 0) goto L_0x0064
            com.google.android.gms.internal.measurement.zzex r8 = (com.google.android.gms.internal.measurement.zzex) r8     // Catch:{ RemoteException -> 0x0055 }
            r12.zza(r8, r14)     // Catch:{ RemoteException -> 0x0055 }
            goto L_0x0041
        L_0x0055:
            r8 = move-exception
            com.google.android.gms.internal.measurement.zzfi r9 = r11.zzgi()
            com.google.android.gms.internal.measurement.zzfk r9 = r9.zziv()
            java.lang.String r10 = "Failed to send event to the service"
        L_0x0060:
            r9.zzg(r10, r8)
            goto L_0x0041
        L_0x0064:
            boolean r9 = r8 instanceof com.google.android.gms.internal.measurement.zzka
            if (r9 == 0) goto L_0x007a
            com.google.android.gms.internal.measurement.zzka r8 = (com.google.android.gms.internal.measurement.zzka) r8     // Catch:{ RemoteException -> 0x006e }
            r12.zza(r8, r14)     // Catch:{ RemoteException -> 0x006e }
            goto L_0x0041
        L_0x006e:
            r8 = move-exception
            com.google.android.gms.internal.measurement.zzfi r9 = r11.zzgi()
            com.google.android.gms.internal.measurement.zzfk r9 = r9.zziv()
            java.lang.String r10 = "Failed to send attribute to the service"
            goto L_0x0060
        L_0x007a:
            boolean r9 = r8 instanceof com.google.android.gms.internal.measurement.zzef
            if (r9 == 0) goto L_0x0090
            com.google.android.gms.internal.measurement.zzef r8 = (com.google.android.gms.internal.measurement.zzef) r8     // Catch:{ RemoteException -> 0x0084 }
            r12.zza(r8, r14)     // Catch:{ RemoteException -> 0x0084 }
            goto L_0x0041
        L_0x0084:
            r8 = move-exception
            com.google.android.gms.internal.measurement.zzfi r9 = r11.zzgi()
            com.google.android.gms.internal.measurement.zzfk r9 = r9.zziv()
            java.lang.String r10 = "Failed to send conditional property to the service"
            goto L_0x0060
        L_0x0090:
            com.google.android.gms.internal.measurement.zzfi r8 = r11.zzgi()
            com.google.android.gms.internal.measurement.zzfk r8 = r8.zziv()
            java.lang.String r9 = "Discarding data. Unrecognized parcel type."
            r8.log(r9)
            goto L_0x0041
        L_0x009e:
            int r3 = r3 + 1
            r4 = r5
            goto L_0x0013
        L_0x00a3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzik.zza(com.google.android.gms.internal.measurement.zzfa, com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable, com.google.android.gms.internal.measurement.zzeb):void");
    }

    public final void zza(AtomicReference<String> atomicReference) {
        zzab();
        zzch();
        zzf(new zzin(this, atomicReference, zzk(false)));
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzef>> atomicReference, String str, String str2, String str3) {
        zzab();
        zzch();
        zziu zziu = new zziu(this, atomicReference, str, str2, str3, zzk(false));
        zzf(zziu);
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzka>> atomicReference, String str, String str2, String str3, boolean z) {
        zzab();
        zzch();
        zziv zziv = new zziv(this, atomicReference, str, str2, str3, z, zzk(false));
        zzf(zziv);
    }

    /* access modifiers changed from: protected */
    public final void zza(AtomicReference<List<zzka>> atomicReference, boolean z) {
        zzab();
        zzch();
        zzf(new zzix(this, atomicReference, zzk(false), z));
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzex zzex, String str) {
        Preconditions.checkNotNull(zzex);
        zzab();
        zzch();
        boolean zzkq = zzkq();
        zzis zzis = new zzis(this, zzkq, zzkq && zzgc().zza(zzex), zzex, zzk(true), str);
        zzf(zzis);
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzig zzig) {
        zzab();
        zzch();
        zzf(new zzip(this, zzig));
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzka zzka) {
        zzab();
        zzch();
        zzf(new zziw(this, zzkq() && zzgc().zza(zzka), zzka, zzk(true)));
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    /* access modifiers changed from: protected */
    public final void zzd(zzef zzef) {
        Preconditions.checkNotNull(zzef);
        zzab();
        zzch();
        zzgl();
        zzit zzit = new zzit(this, true, zzgc().zzc(zzef), new zzef(zzef), zzk(true), zzef);
        zzf(zzit);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00c4, code lost:
        r0 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0108  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzdf() {
        /*
            r6 = this;
            r6.zzab()
            r6.zzch()
            boolean r0 = r6.isConnected()
            if (r0 == 0) goto L_0x000d
            return
        L_0x000d:
            java.lang.Boolean r0 = r6.zzaqq
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0115
            r6.zzab()
            r6.zzch()
            com.google.android.gms.internal.measurement.zzft r0 = r6.zzgj()
            java.lang.Boolean r0 = r0.zzjl()
            if (r0 == 0) goto L_0x002c
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x002c
            r0 = 1
            goto L_0x010f
        L_0x002c:
            r6.zzgl()
            com.google.android.gms.internal.measurement.zzfd r0 = r6.zzfz()
            int r0 = r0.zzit()
            if (r0 != r2) goto L_0x003d
        L_0x0039:
            r0 = 1
        L_0x003a:
            r3 = 1
            goto L_0x00ec
        L_0x003d:
            com.google.android.gms.internal.measurement.zzfi r0 = r6.zzgi()
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zzjc()
            java.lang.String r3 = "Checking service availability"
            r0.log(r3)
            com.google.android.gms.internal.measurement.zzkd r0 = r6.zzgg()
            com.google.android.gms.common.GoogleApiAvailabilityLight r3 = com.google.android.gms.common.GoogleApiAvailabilityLight.getInstance()
            android.content.Context r0 = r0.getContext()
            r4 = 12451000(0xbdfcb8, float:1.7447567E-38)
            int r0 = r3.isGooglePlayServicesAvailable(r0, r4)
            r3 = 9
            if (r0 == r3) goto L_0x00e1
            r3 = 18
            if (r0 == r3) goto L_0x00d6
            switch(r0) {
                case 0: goto L_0x00c7;
                case 1: goto L_0x00b7;
                case 2: goto L_0x008b;
                case 3: goto L_0x007d;
                default: goto L_0x0068;
            }
        L_0x0068:
            com.google.android.gms.internal.measurement.zzfi r3 = r6.zzgi()
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zziy()
            java.lang.String r4 = "Unexpected service status"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r3.zzg(r4, r0)
        L_0x0079:
            r0 = 0
        L_0x007a:
            r3 = 0
            goto L_0x00ec
        L_0x007d:
            com.google.android.gms.internal.measurement.zzfi r0 = r6.zzgi()
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zziy()
            java.lang.String r3 = "Service disabled"
        L_0x0087:
            r0.log(r3)
            goto L_0x0079
        L_0x008b:
            com.google.android.gms.internal.measurement.zzfi r0 = r6.zzgi()
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zzjb()
            java.lang.String r3 = "Service container out of date"
            r0.log(r3)
            com.google.android.gms.internal.measurement.zzkd r0 = r6.zzgg()
            int r0 = r0.zzlp()
            r3 = 12600(0x3138, float:1.7656E-41)
            if (r0 >= r3) goto L_0x00a5
            goto L_0x00c4
        L_0x00a5:
            com.google.android.gms.internal.measurement.zzft r0 = r6.zzgj()
            java.lang.Boolean r0 = r0.zzjl()
            if (r0 == 0) goto L_0x00b5
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0079
        L_0x00b5:
            r0 = 1
            goto L_0x007a
        L_0x00b7:
            com.google.android.gms.internal.measurement.zzfi r0 = r6.zzgi()
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zzjc()
            java.lang.String r3 = "Service missing"
            r0.log(r3)
        L_0x00c4:
            r0 = 0
            goto L_0x003a
        L_0x00c7:
            com.google.android.gms.internal.measurement.zzfi r0 = r6.zzgi()
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zzjc()
            java.lang.String r3 = "Service available"
        L_0x00d1:
            r0.log(r3)
            goto L_0x0039
        L_0x00d6:
            com.google.android.gms.internal.measurement.zzfi r0 = r6.zzgi()
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zziy()
            java.lang.String r3 = "Service updating"
            goto L_0x00d1
        L_0x00e1:
            com.google.android.gms.internal.measurement.zzfi r0 = r6.zzgi()
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zziy()
            java.lang.String r3 = "Service invalid"
            goto L_0x0087
        L_0x00ec:
            if (r0 != 0) goto L_0x0106
            com.google.android.gms.internal.measurement.zzeh r4 = r6.zzgk()
            boolean r4 = r4.zzhu()
            if (r4 == 0) goto L_0x0106
            com.google.android.gms.internal.measurement.zzfi r3 = r6.zzgi()
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zziv()
            java.lang.String r4 = "No way to upload. Consider using the full version of Analytics"
            r3.log(r4)
            r3 = 0
        L_0x0106:
            if (r3 == 0) goto L_0x010f
            com.google.android.gms.internal.measurement.zzft r3 = r6.zzgj()
            r3.zzf(r0)
        L_0x010f:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.zzaqq = r0
        L_0x0115:
            java.lang.Boolean r0 = r6.zzaqq
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0123
            com.google.android.gms.internal.measurement.zziy r0 = r6.zzaqo
            r0.zzkt()
            return
        L_0x0123:
            com.google.android.gms.internal.measurement.zzeh r0 = r6.zzgk()
            boolean r0 = r0.zzhu()
            if (r0 != 0) goto L_0x0183
            r6.zzgl()
            android.content.Context r0 = r6.getContext()
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            android.content.Intent r3 = new android.content.Intent
            r3.<init>()
            android.content.Context r4 = r6.getContext()
            java.lang.String r5 = "com.google.android.gms.measurement.AppMeasurementService"
            android.content.Intent r3 = r3.setClassName(r4, r5)
            r4 = 65536(0x10000, float:9.18355E-41)
            java.util.List r0 = r0.queryIntentServices(r3, r4)
            if (r0 == 0) goto L_0x0156
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x0156
            r1 = 1
        L_0x0156:
            if (r1 == 0) goto L_0x0176
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.google.android.gms.measurement.START"
            r0.<init>(r1)
            android.content.ComponentName r1 = new android.content.ComponentName
            android.content.Context r2 = r6.getContext()
            r6.zzgl()
            java.lang.String r3 = "com.google.android.gms.measurement.AppMeasurementService"
            r1.<init>(r2, r3)
            r0.setComponent(r1)
            com.google.android.gms.internal.measurement.zziy r1 = r6.zzaqo
            r1.zzc(r0)
            return
        L_0x0176:
            com.google.android.gms.internal.measurement.zzfi r0 = r6.zzgi()
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zziv()
            java.lang.String r1 = "Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest"
            r0.log(r1)
        L_0x0183:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzik.zzdf():void");
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

    /* access modifiers changed from: protected */
    public final void zzkm() {
        zzab();
        zzch();
        zzf(new zzio(this, zzk(true)));
    }

    /* access modifiers changed from: protected */
    public final void zzkp() {
        zzab();
        zzch();
        zzf(new zzir(this, zzk(true)));
    }

    /* access modifiers changed from: 0000 */
    public final Boolean zzkr() {
        return this.zzaqq;
    }
}
