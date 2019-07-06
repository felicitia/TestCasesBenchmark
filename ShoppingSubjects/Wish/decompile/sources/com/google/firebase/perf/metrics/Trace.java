package com.google.firebase.perf.metrics;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Keep;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.google.android.gms.internal.firebase-perf.zzaa;
import com.google.android.gms.internal.firebase-perf.zze;
import com.google.android.gms.internal.firebase-perf.zzf;
import com.google.android.gms.internal.firebase-perf.zzh;
import com.google.android.gms.internal.firebase-perf.zzo;
import com.google.android.gms.internal.firebase-perf.zzq;
import com.google.android.gms.internal.firebase-perf.zzr;
import com.google.android.gms.internal.firebase-perf.zzw;
import com.google.android.gms.internal.firebase-perf.zzx;
import com.google.android.gms.internal.firebase-perf.zzz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Trace extends zzf implements Parcelable {
    @Keep
    public static final Creator<Trace> CREATOR = new zzd();
    private static final Map<String, Trace> zzdr = new ConcurrentHashMap();
    private static final Creator<Trace> zzdx = new zze();
    private final String mName;
    /* access modifiers changed from: private */
    public final List<zzr> zzag;
    private BroadcastReceiver zzah;
    private final zzx zzak;
    private final Map<String, zza> zzap;
    private final Map<String, String> zzdp;
    private final Trace zzds;
    private final List<Trace> zzdt;
    private final zzh zzdu;
    private zzaa zzdv;
    private zzaa zzdw;

    @Keep
    public int describeContents() {
        return 0;
    }

    public Trace(String str, zzh zzh, zzx zzx, zze zze) {
        super(zze);
        this.zzah = new zzc(this);
        this.zzds = null;
        this.mName = str.trim();
        this.zzdt = new ArrayList();
        this.zzap = new ConcurrentHashMap();
        this.zzdp = new ConcurrentHashMap();
        this.zzak = zzx;
        this.zzdu = zzh;
        this.zzag = new ArrayList();
    }

    @Keep
    public void start() {
        String str;
        String str2 = this.mName;
        if (str2 == null) {
            str = "Trace name must not be null";
        } else if (str2.length() > 100) {
            str = String.format(Locale.US, "Trace name must not exceed %d characters", new Object[]{Integer.valueOf(100)});
        } else {
            if (str2.startsWith("_")) {
                zzz[] values = zzz.values();
                int length = values.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        if (values[i].toString().equals(str2)) {
                            break;
                        }
                        i++;
                    } else if (!str2.startsWith("_st_")) {
                        str = "Trace name must not start with '_'";
                    }
                }
            }
            str = null;
        }
        if (str != null) {
            Log.e("FirebasePerformance", String.format("Cannot start trace %s. Trace name is invalid.(%s)", new Object[]{this.mName, str}));
        } else if (this.zzdv != null) {
            Log.e("FirebasePerformance", String.format("Trace '%s' has already started, should not start again!", new Object[]{this.mName}));
        } else {
            zzl();
            zzw zzae = zzw.zzae();
            this.zzag.add(zzae.zzaf());
            this.zzdv = new zzaa();
            Log.i("FirebasePerformance", String.format("Session ID - %s", new Object[]{zzae.zzaf().zzs()}));
            zzw.zzae();
            LocalBroadcastManager.getInstance(zzw.zzag()).registerReceiver(this.zzah, new IntentFilter("SessionIdUpdate"));
        }
    }

    @Keep
    public void stop() {
        if (!hasStarted()) {
            Log.e("FirebasePerformance", String.format("Trace '%s' has not been started so unable to stop!", new Object[]{this.mName}));
        } else if (isStopped()) {
            Log.e("FirebasePerformance", String.format("Trace '%s' has already stopped, should not stop again!", new Object[]{this.mName}));
        } else {
            zzw.zzae();
            LocalBroadcastManager.getInstance(zzw.zzag()).unregisterReceiver(this.zzah);
            zzm();
            this.zzdw = new zzaa();
            if (this.zzds == null) {
                zzaa zzaa = this.zzdw;
                if (!this.zzdt.isEmpty()) {
                    Trace trace = (Trace) this.zzdt.get(this.zzdt.size() - 1);
                    if (trace.zzdw == null) {
                        trace.zzdw = zzaa;
                    }
                }
                if (this.mName.isEmpty()) {
                    Log.e("FirebasePerformance", "Trace name is empty, no log is sent to server");
                } else if (this.zzdu != null) {
                    this.zzdu.zza(new zzg(this).zzao(), zzh());
                }
            }
        }
    }

    private final zza zzi(String str) {
        zza zza = (zza) this.zzap.get(str);
        if (zza != null) {
            return zza;
        }
        zza zza2 = new zza(str);
        this.zzap.put(str, zza2);
        return zza2;
    }

    @Keep
    @Deprecated
    public void incrementCounter(String str, long j) {
        zza(str, j, zzq.zzbu);
    }

    private final void zza(String str, long j, int i) {
        String zza = zzo.zza(str, i);
        if (zza != null) {
            switch (zzf.zzbt[i - 1]) {
                case 1:
                    Log.e("FirebasePerformance", String.format("Cannot increment counter %s. Counter name is invalid.(%s)", new Object[]{str, zza}));
                    return;
                case 2:
                    Log.e("FirebasePerformance", String.format("Cannot increment metric %s. Metric name is invalid.(%s)", new Object[]{str, zza}));
                    break;
            }
        } else if (!hasStarted()) {
            switch (zzf.zzbt[i - 1]) {
                case 1:
                    Log.w("FirebasePerformance", String.format("Cannot increment counter '%s' for trace '%s' because it's not started", new Object[]{str, this.mName}));
                    return;
                case 2:
                    Log.w("FirebasePerformance", String.format("Cannot increment metric '%s' for trace '%s' because it's not started", new Object[]{str, this.mName}));
                    break;
            }
        } else if (isStopped()) {
            switch (zzf.zzbt[i - 1]) {
                case 1:
                    Log.w("FirebasePerformance", String.format("Cannot increment counter '%s' for trace '%s' because it's been stopped", new Object[]{str, this.mName}));
                    return;
                case 2:
                    Log.w("FirebasePerformance", String.format("Cannot increment metric '%s' for trace '%s' because it's been stopped", new Object[]{str, this.mName}));
                    break;
            }
        } else {
            zzi(str.trim()).zzi(j);
        }
    }

    @Keep
    @Deprecated
    public void incrementCounter(String str) {
        incrementCounter(str, 1);
    }

    @Keep
    public void incrementMetric(String str, long j) {
        zza(str, j, zzq.zzbv);
    }

    @Keep
    public long getLongMetric(String str) {
        zza zza = str != null ? (zza) this.zzap.get(str.trim()) : null;
        if (zza == null) {
            return 0;
        }
        return zza.getCount();
    }

    @Keep
    public void putMetric(String str, long j) {
        String zza = zzo.zza(str, zzq.zzbv);
        if (zza != null) {
            Log.e("FirebasePerformance", String.format("Cannot set value for metric %s. Metric name is invalid.(%s)", new Object[]{str, zza}));
        } else if (!hasStarted()) {
            Log.w("FirebasePerformance", String.format("Cannot set value for metric '%s' for trace '%s' because it's not started", new Object[]{str, this.mName}));
        } else if (isStopped()) {
            Log.w("FirebasePerformance", String.format("Cannot set value for metric '%s' for trace '%s' because it's been stopped", new Object[]{str, this.mName}));
        } else {
            zzi(str.trim()).zzj(j);
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (hasStarted() && !isStopped()) {
                Log.w("FirebasePerformance", String.format("Trace '%s' is started but not stopped when it is destructed!", new Object[]{this.mName}));
                zzb(1);
            }
        } finally {
            super.finalize();
        }
    }

    /* access modifiers changed from: 0000 */
    public final String getName() {
        return this.mName;
    }

    /* access modifiers changed from: 0000 */
    public final Map<String, zza> zzaj() {
        return this.zzap;
    }

    /* access modifiers changed from: 0000 */
    public final zzaa zzak() {
        return this.zzdv;
    }

    /* access modifiers changed from: 0000 */
    public final zzaa zzal() {
        return this.zzdw;
    }

    /* access modifiers changed from: 0000 */
    public final List<Trace> zzam() {
        return this.zzdt;
    }

    /* access modifiers changed from: 0000 */
    public final boolean isStopped() {
        return this.zzdw != null;
    }

    /* access modifiers changed from: 0000 */
    public final boolean hasStarted() {
        return this.zzdv != null;
    }

    @Keep
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.zzds, 0);
        parcel.writeString(this.mName);
        parcel.writeList(this.zzdt);
        parcel.writeMap(this.zzap);
        parcel.writeParcelable(this.zzdv, 0);
        parcel.writeParcelable(this.zzdw, 0);
        parcel.writeList(this.zzag);
    }

    private Trace(Parcel parcel, boolean z) {
        super(z ? null : zze.zzg());
        this.zzah = new zzc(this);
        this.zzds = (Trace) parcel.readParcelable(Trace.class.getClassLoader());
        this.mName = parcel.readString();
        this.zzdt = new ArrayList();
        parcel.readList(this.zzdt, Trace.class.getClassLoader());
        this.zzap = new ConcurrentHashMap();
        this.zzdp = new ConcurrentHashMap();
        parcel.readMap(this.zzap, zza.class.getClassLoader());
        this.zzdv = (zzaa) parcel.readParcelable(zzaa.class.getClassLoader());
        this.zzdw = (zzaa) parcel.readParcelable(zzaa.class.getClassLoader());
        this.zzag = new ArrayList();
        parcel.readList(this.zzag, zzr.class.getClassLoader());
        if (z) {
            this.zzdu = null;
            this.zzak = null;
            return;
        }
        this.zzdu = zzh.zzo();
        this.zzak = new zzx();
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    @android.support.annotation.Keep
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void putAttribute(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            r0 = 0
            r1 = 1
            java.lang.String r2 = r9.trim()     // Catch:{ Exception -> 0x0065 }
            java.lang.String r9 = r10.trim()     // Catch:{ Exception -> 0x0060 }
            boolean r10 = r8.isStopped()     // Catch:{ Exception -> 0x005e }
            if (r10 == 0) goto L_0x0024
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x005e }
            java.util.Locale r3 = java.util.Locale.US     // Catch:{ Exception -> 0x005e }
            java.lang.String r4 = "Trace %s has been stopped"
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x005e }
            java.lang.String r6 = r8.mName     // Catch:{ Exception -> 0x005e }
            r5[r0] = r6     // Catch:{ Exception -> 0x005e }
            java.lang.String r3 = java.lang.String.format(r3, r4, r5)     // Catch:{ Exception -> 0x005e }
            r10.<init>(r3)     // Catch:{ Exception -> 0x005e }
            throw r10     // Catch:{ Exception -> 0x005e }
        L_0x0024:
            java.util.Map<java.lang.String, java.lang.String> r10 = r8.zzdp     // Catch:{ Exception -> 0x005e }
            boolean r10 = r10.containsKey(r2)     // Catch:{ Exception -> 0x005e }
            if (r10 != 0) goto L_0x004b
            java.util.Map<java.lang.String, java.lang.String> r10 = r8.zzdp     // Catch:{ Exception -> 0x005e }
            int r10 = r10.size()     // Catch:{ Exception -> 0x005e }
            r3 = 5
            if (r10 < r3) goto L_0x004b
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x005e }
            java.util.Locale r4 = java.util.Locale.US     // Catch:{ Exception -> 0x005e }
            java.lang.String r5 = "Exceeds max limit of number of attributes - %d"
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x005e }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Exception -> 0x005e }
            r6[r0] = r3     // Catch:{ Exception -> 0x005e }
            java.lang.String r3 = java.lang.String.format(r4, r5, r6)     // Catch:{ Exception -> 0x005e }
            r10.<init>(r3)     // Catch:{ Exception -> 0x005e }
            throw r10     // Catch:{ Exception -> 0x005e }
        L_0x004b:
            java.util.AbstractMap$SimpleEntry r10 = new java.util.AbstractMap$SimpleEntry     // Catch:{ Exception -> 0x005e }
            r10.<init>(r2, r9)     // Catch:{ Exception -> 0x005e }
            java.lang.String r10 = com.google.android.gms.internal.firebase-perf.zzo.zza(r10)     // Catch:{ Exception -> 0x005e }
            if (r10 == 0) goto L_0x005c
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x005e }
            r3.<init>(r10)     // Catch:{ Exception -> 0x005e }
            throw r3     // Catch:{ Exception -> 0x005e }
        L_0x005c:
            r0 = 1
            goto L_0x0083
        L_0x005e:
            r10 = move-exception
            goto L_0x006a
        L_0x0060:
            r9 = move-exception
            r7 = r10
            r10 = r9
            r9 = r7
            goto L_0x006a
        L_0x0065:
            r2 = move-exception
            r7 = r2
            r2 = r9
            r9 = r10
            r10 = r7
        L_0x006a:
            java.lang.String r3 = "FirebasePerformance"
            java.lang.String r4 = "Can not set attribute %s with value %s (%s)"
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r0] = r2
            r5[r1] = r9
            r1 = 2
            java.lang.String r10 = r10.getMessage()
            r5[r1] = r10
            java.lang.String r10 = java.lang.String.format(r4, r5)
            android.util.Log.e(r3, r10)
        L_0x0083:
            if (r0 == 0) goto L_0x008a
            java.util.Map<java.lang.String, java.lang.String> r10 = r8.zzdp
            r10.put(r2, r9)
        L_0x008a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.metrics.Trace.putAttribute(java.lang.String, java.lang.String):void");
    }

    @Keep
    public void removeAttribute(String str) {
        if (isStopped()) {
            Log.e("FirebasePerformance", "Can't remove a attribute from a Trace that's stopped.");
        } else {
            this.zzdp.remove(str);
        }
    }

    @Keep
    public String getAttribute(String str) {
        return (String) this.zzdp.get(str);
    }

    @Keep
    public Map<String, String> getAttributes() {
        return new HashMap(this.zzdp);
    }

    public final List<zzr> zzan() {
        return this.zzag;
    }

    /* synthetic */ Trace(Parcel parcel, boolean z, zzc zzc) {
        this(parcel, z);
    }
}
