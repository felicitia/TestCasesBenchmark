package com.google.android.gms.internal.firebase-perf;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.support.v4.app.FrameMetricsAggregator;
import com.google.firebase.perf.metrics.Trace;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class zze implements ActivityLifecycleCallbacks {
    private static volatile zze zzaj;
    private boolean mRegistered = false;
    private final zzx zzak;
    private boolean zzal = true;
    private final WeakHashMap<Activity, Boolean> zzam = new WeakHashMap<>();
    private zzaa zzan;
    private zzaa zzao;
    private final Map<String, Long> zzap = new HashMap();
    private AtomicInteger zzaq = new AtomicInteger(0);
    private int zzar = 2;
    private Set<WeakReference<zza>> zzas = new HashSet();
    private boolean zzat = false;
    private FrameMetricsAggregator zzau;
    private final WeakHashMap<Activity, Trace> zzav = new WeakHashMap<>();
    private zzh zzq = null;

    public interface zza {
        void zzd(int i);
    }

    public static zze zzg() {
        if (zzaj != null) {
            return zzaj;
        }
        return zza((zzh) null, new zzx());
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    private static zze zza(zzh zzh, zzx zzx) {
        if (zzaj == null) {
            synchronized (zze.class) {
                if (zzaj == null) {
                    zzaj = new zze(null, zzx);
                }
            }
        }
        return zzaj;
    }

    private zze(zzh zzh, zzx zzx) {
        this.zzak = zzx;
        this.zzat = zzk();
        if (this.zzat) {
            this.zzau = new FrameMetricsAggregator();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzc(android.content.Context r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.mRegistered     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ all -> 0x0019 }
            boolean r0 = r2 instanceof android.app.Application     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0017
            android.app.Application r2 = (android.app.Application) r2     // Catch:{ all -> 0x0019 }
            r2.registerActivityLifecycleCallbacks(r1)     // Catch:{ all -> 0x0019 }
            r2 = 1
            r1.mRegistered = r2     // Catch:{ all -> 0x0019 }
        L_0x0017:
            monitor-exit(r1)
            return
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zze.zzc(android.content.Context):void");
    }

    public final void zza(String str, long j) {
        synchronized (this.zzap) {
            Long l = (Long) this.zzap.get(str);
            if (l == null) {
                this.zzap.put(str, Long.valueOf(1));
            } else {
                this.zzap.put(str, Long.valueOf(l.longValue() + 1));
            }
        }
    }

    public final void zzb(int i) {
        this.zzaq.addAndGet(1);
    }

    public synchronized void onActivityStarted(Activity activity) {
        if (zzj()) {
            this.zzau.add(activity);
            zzi();
            Trace trace = new Trace(zza(activity), this.zzq, this.zzak, this);
            trace.start();
            this.zzav.put(activity, trace);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onActivityStopped(android.app.Activity r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            boolean r0 = r10.zzj()     // Catch:{ all -> 0x00ed }
            r1 = 0
            if (r0 == 0) goto L_0x00bb
            java.util.WeakHashMap<android.app.Activity, com.google.firebase.perf.metrics.Trace> r0 = r10.zzav     // Catch:{ all -> 0x00ed }
            boolean r0 = r0.containsKey(r11)     // Catch:{ all -> 0x00ed }
            if (r0 == 0) goto L_0x00bb
            java.util.WeakHashMap<android.app.Activity, com.google.firebase.perf.metrics.Trace> r0 = r10.zzav     // Catch:{ all -> 0x00ed }
            java.lang.Object r0 = r0.get(r11)     // Catch:{ all -> 0x00ed }
            com.google.firebase.perf.metrics.Trace r0 = (com.google.firebase.perf.metrics.Trace) r0     // Catch:{ all -> 0x00ed }
            if (r0 == 0) goto L_0x00bb
            java.util.WeakHashMap<android.app.Activity, com.google.firebase.perf.metrics.Trace> r2 = r10.zzav     // Catch:{ all -> 0x00ed }
            r2.remove(r11)     // Catch:{ all -> 0x00ed }
            android.support.v4.app.FrameMetricsAggregator r2 = r10.zzau     // Catch:{ all -> 0x00ed }
            android.util.SparseIntArray[] r2 = r2.remove(r11)     // Catch:{ all -> 0x00ed }
            if (r2 == 0) goto L_0x004b
            r2 = r2[r1]     // Catch:{ all -> 0x00ed }
            if (r2 == 0) goto L_0x004b
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
        L_0x002f:
            int r7 = r2.size()     // Catch:{ all -> 0x00ed }
            if (r3 >= r7) goto L_0x004e
            int r7 = r2.keyAt(r3)     // Catch:{ all -> 0x00ed }
            int r8 = r2.valueAt(r3)     // Catch:{ all -> 0x00ed }
            int r4 = r4 + r8
            r9 = 700(0x2bc, float:9.81E-43)
            if (r7 <= r9) goto L_0x0043
            int r6 = r6 + r8
        L_0x0043:
            r9 = 16
            if (r7 <= r9) goto L_0x0048
            int r5 = r5 + r8
        L_0x0048:
            int r3 = r3 + 1
            goto L_0x002f
        L_0x004b:
            r4 = 0
            r5 = 0
            r6 = 0
        L_0x004e:
            if (r4 <= 0) goto L_0x005a
            com.google.android.gms.internal.firebase-perf.zzy r2 = com.google.android.gms.internal.firebase-perf.zzy.FRAMES_TOTAL     // Catch:{ all -> 0x00ed }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00ed }
            long r7 = (long) r4     // Catch:{ all -> 0x00ed }
            r0.incrementCounter(r2, r7)     // Catch:{ all -> 0x00ed }
        L_0x005a:
            if (r5 <= 0) goto L_0x0066
            com.google.android.gms.internal.firebase-perf.zzy r2 = com.google.android.gms.internal.firebase-perf.zzy.FRAMES_SLOW     // Catch:{ all -> 0x00ed }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00ed }
            long r7 = (long) r5     // Catch:{ all -> 0x00ed }
            r0.incrementCounter(r2, r7)     // Catch:{ all -> 0x00ed }
        L_0x0066:
            if (r6 <= 0) goto L_0x0072
            com.google.android.gms.internal.firebase-perf.zzy r2 = com.google.android.gms.internal.firebase-perf.zzy.FRAMES_FROZEN     // Catch:{ all -> 0x00ed }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00ed }
            long r7 = (long) r6     // Catch:{ all -> 0x00ed }
            r0.incrementCounter(r2, r7)     // Catch:{ all -> 0x00ed }
        L_0x0072:
            android.content.Context r2 = r11.getApplicationContext()     // Catch:{ all -> 0x00ed }
            boolean r2 = com.google.android.gms.internal.firebase-perf.zzae.zzf(r2)     // Catch:{ all -> 0x00ed }
            if (r2 == 0) goto L_0x00b8
            java.lang.String r2 = "FirebasePerformance"
            java.lang.String r3 = zza(r11)     // Catch:{ all -> 0x00ed }
            java.lang.String r7 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00ed }
            int r7 = r7.length()     // Catch:{ all -> 0x00ed }
            int r7 = r7 + 81
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ed }
            r8.<init>(r7)     // Catch:{ all -> 0x00ed }
            java.lang.String r7 = "sendScreenTrace name:"
            r8.append(r7)     // Catch:{ all -> 0x00ed }
            r8.append(r3)     // Catch:{ all -> 0x00ed }
            java.lang.String r3 = " _fr_tot:"
            r8.append(r3)     // Catch:{ all -> 0x00ed }
            r8.append(r4)     // Catch:{ all -> 0x00ed }
            java.lang.String r3 = " _fr_slo:"
            r8.append(r3)     // Catch:{ all -> 0x00ed }
            r8.append(r5)     // Catch:{ all -> 0x00ed }
            java.lang.String r3 = " _fr_fzn:"
            r8.append(r3)     // Catch:{ all -> 0x00ed }
            r8.append(r6)     // Catch:{ all -> 0x00ed }
            java.lang.String r3 = r8.toString()     // Catch:{ all -> 0x00ed }
            android.util.Log.d(r2, r3)     // Catch:{ all -> 0x00ed }
        L_0x00b8:
            r0.stop()     // Catch:{ all -> 0x00ed }
        L_0x00bb:
            java.util.WeakHashMap<android.app.Activity, java.lang.Boolean> r0 = r10.zzam     // Catch:{ all -> 0x00ed }
            boolean r0 = r0.containsKey(r11)     // Catch:{ all -> 0x00ed }
            if (r0 == 0) goto L_0x00eb
            java.util.WeakHashMap<android.app.Activity, java.lang.Boolean> r0 = r10.zzam     // Catch:{ all -> 0x00ed }
            r0.remove(r11)     // Catch:{ all -> 0x00ed }
            java.util.WeakHashMap<android.app.Activity, java.lang.Boolean> r11 = r10.zzam     // Catch:{ all -> 0x00ed }
            boolean r11 = r11.isEmpty()     // Catch:{ all -> 0x00ed }
            if (r11 == 0) goto L_0x00eb
            com.google.android.gms.internal.firebase-perf.zzaa r11 = new com.google.android.gms.internal.firebase-perf.zzaa     // Catch:{ all -> 0x00ed }
            r11.<init>()     // Catch:{ all -> 0x00ed }
            r10.zzan = r11     // Catch:{ all -> 0x00ed }
            r11 = 2
            r10.zzc(r11)     // Catch:{ all -> 0x00ed }
            r10.zza(r1)     // Catch:{ all -> 0x00ed }
            com.google.android.gms.internal.firebase-perf.zzz r11 = com.google.android.gms.internal.firebase-perf.zzz.FOREGROUND_TRACE_NAME     // Catch:{ all -> 0x00ed }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00ed }
            com.google.android.gms.internal.firebase-perf.zzaa r0 = r10.zzao     // Catch:{ all -> 0x00ed }
            com.google.android.gms.internal.firebase-perf.zzaa r1 = r10.zzan     // Catch:{ all -> 0x00ed }
            r10.zza(r11, r0, r1)     // Catch:{ all -> 0x00ed }
        L_0x00eb:
            monitor-exit(r10)
            return
        L_0x00ed:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zze.onActivityStopped(android.app.Activity):void");
    }

    public synchronized void onActivityResumed(Activity activity) {
        if (this.zzam.isEmpty()) {
            this.zzao = new zzaa();
            this.zzam.put(activity, Boolean.valueOf(true));
            if (this.zzal) {
                this.zzal = false;
                zzc(1);
                zza(true);
                return;
            }
            zzc(1);
            zza(true);
            zza(zzz.BACKGROUND_TRACE_NAME.toString(), this.zzan, this.zzao);
            return;
        }
        this.zzam.put(activity, Boolean.valueOf(true));
    }

    public final int zzh() {
        return this.zzar;
    }

    public final void zza(WeakReference<zza> weakReference) {
        synchronized (this.zzas) {
            this.zzas.add(weakReference);
        }
    }

    public final void zzb(WeakReference<zza> weakReference) {
        synchronized (this.zzas) {
            this.zzas.remove(weakReference);
        }
    }

    private final void zzc(int i) {
        this.zzar = i;
        synchronized (this.zzas) {
            Iterator it = this.zzas.iterator();
            while (it.hasNext()) {
                zza zza2 = (zza) ((WeakReference) it.next()).get();
                if (zza2 != null) {
                    zza2.zzd(this.zzar);
                } else {
                    it.remove();
                }
            }
        }
    }

    private final void zza(String str, zzaa zzaa, zzaa zzaa2) {
        zzi();
        zzat zzat2 = new zzat();
        zzat2.name = str;
        zzat2.zzgk = Long.valueOf(zzaa.zzar());
        zzat2.zzgz = Long.valueOf(zzaa.zza(zzaa2));
        int i = 0;
        int andSet = this.zzaq.getAndSet(0);
        synchronized (this.zzap) {
            if (!this.zzap.isEmpty() || andSet != 0) {
                zzat2.zzha = new zzau[(andSet != 0 ? this.zzap.size() + 1 : this.zzap.size())];
                for (String str2 : this.zzap.keySet()) {
                    long longValue = ((Long) this.zzap.get(str2)).longValue();
                    zzau zzau2 = new zzau();
                    zzau2.key = str2;
                    zzau2.zzhe = Long.valueOf(longValue);
                    int i2 = i + 1;
                    zzat2.zzha[i] = zzau2;
                    i = i2;
                }
                if (andSet != 0) {
                    zzau zzau3 = new zzau();
                    zzau3.key = zzy.TRACE_STARTED_NOT_STOPPED.toString();
                    zzau3.zzhe = Long.valueOf((long) andSet);
                    zzat2.zzha[i] = zzau3;
                }
            }
            this.zzap.clear();
        }
        if (this.zzq != null) {
            this.zzq.zza(zzat2, 3);
        }
    }

    private final void zza(boolean z) {
        zzi();
        if (this.zzq != null) {
            this.zzq.zzb(z);
        }
    }

    private final void zzi() {
        if (this.zzq == null) {
            this.zzq = zzh.zzo();
        }
    }

    private final boolean zzj() {
        return this.zzat && zzt.zzv();
    }

    private static boolean zzk() {
        try {
            Class.forName("android.support.v4.app.FrameMetricsAggregator");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private static String zza(Activity activity) {
        String valueOf = String.valueOf("_st_");
        String valueOf2 = String.valueOf(activity.getClass().getSimpleName());
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }
}
