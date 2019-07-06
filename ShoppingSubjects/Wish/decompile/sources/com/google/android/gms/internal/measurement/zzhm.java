package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.measurement.AppMeasurement.ConditionalUserProperty;
import com.google.android.gms.measurement.AppMeasurement.EventInterceptor;
import com.google.android.gms.measurement.AppMeasurement.OnEventListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public final class zzhm extends zzdz {
    protected zzif zzapl;
    private EventInterceptor zzapm;
    private final Set<OnEventListener> zzapn = new CopyOnWriteArraySet();
    private boolean zzapo;
    private final AtomicReference<String> zzapp = new AtomicReference<>();
    protected boolean zzapq = true;

    protected zzhm(zzgn zzgn) {
        super(zzgn);
    }

    private final void zza(ConditionalUserProperty conditionalUserProperty) {
        long currentTimeMillis = zzbt().currentTimeMillis();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mName);
        Preconditions.checkNotEmpty(conditionalUserProperty.mOrigin);
        Preconditions.checkNotNull(conditionalUserProperty.mValue);
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        String str = conditionalUserProperty.mName;
        Object obj = conditionalUserProperty.mValue;
        if (zzgg().zzcj(str) != 0) {
            zzgi().zziv().zzg("Invalid conditional user property name", zzgf().zzbo(str));
        } else if (zzgg().zzi(str, obj) != 0) {
            zzgi().zziv().zze("Invalid conditional user property value", zzgf().zzbo(str), obj);
        } else {
            Object zzj = zzgg().zzj(str, obj);
            if (zzj == null) {
                zzgi().zziv().zze("Unable to normalize conditional user property value", zzgf().zzbo(str), obj);
                return;
            }
            conditionalUserProperty.mValue = zzj;
            long j = conditionalUserProperty.mTriggerTimeout;
            if (TextUtils.isEmpty(conditionalUserProperty.mTriggerEventName) || (j <= 15552000000L && j >= 1)) {
                long j2 = conditionalUserProperty.mTimeToLive;
                if (j2 > 15552000000L || j2 < 1) {
                    zzgi().zziv().zze("Invalid conditional user property time to live", zzgf().zzbo(str), Long.valueOf(j2));
                } else {
                    zzgh().zzc((Runnable) new zzht(this, conditionalUserProperty));
                }
            } else {
                zzgi().zziv().zze("Invalid conditional user property timeout", zzgf().zzbo(str), Long.valueOf(j));
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ab  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r32, java.lang.String r33, long r34, android.os.Bundle r36, boolean r37, boolean r38, boolean r39, java.lang.String r40) {
        /*
            r31 = this;
            r1 = r31
            r8 = r32
            r6 = r33
            r5 = r36
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r32)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r33)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r36)
            r31.zzab()
            r31.zzch()
            com.google.android.gms.internal.measurement.zzgn r4 = r1.zzacv
            boolean r4 = r4.isEnabled()
            if (r4 != 0) goto L_0x002d
            com.google.android.gms.internal.measurement.zzfi r2 = r31.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zzjb()
            java.lang.String r3 = "Event not sent since app measurement is disabled"
            r2.log(r3)
            return
        L_0x002d:
            boolean r4 = r1.zzapo
            r7 = 0
            r16 = 0
            r15 = 1
            if (r4 != 0) goto L_0x0072
            r1.zzapo = r15
            java.lang.String r4 = "com.google.android.gms.tagmanager.TagManagerService"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x0065 }
            java.lang.String r9 = "initialize"
            java.lang.Class[] r10 = new java.lang.Class[r15]     // Catch:{ Exception -> 0x0055 }
            java.lang.Class<android.content.Context> r11 = android.content.Context.class
            r10[r16] = r11     // Catch:{ Exception -> 0x0055 }
            java.lang.reflect.Method r4 = r4.getDeclaredMethod(r9, r10)     // Catch:{ Exception -> 0x0055 }
            java.lang.Object[] r9 = new java.lang.Object[r15]     // Catch:{ Exception -> 0x0055 }
            android.content.Context r10 = r31.getContext()     // Catch:{ Exception -> 0x0055 }
            r9[r16] = r10     // Catch:{ Exception -> 0x0055 }
            r4.invoke(r7, r9)     // Catch:{ Exception -> 0x0055 }
            goto L_0x0072
        L_0x0055:
            r0 = move-exception
            r4 = r0
            com.google.android.gms.internal.measurement.zzfi r9 = r31.zzgi()     // Catch:{ ClassNotFoundException -> 0x0065 }
            com.google.android.gms.internal.measurement.zzfk r9 = r9.zziy()     // Catch:{ ClassNotFoundException -> 0x0065 }
            java.lang.String r10 = "Failed to invoke Tag Manager's initialize() method"
            r9.zzg(r10, r4)     // Catch:{ ClassNotFoundException -> 0x0065 }
            goto L_0x0072
        L_0x0065:
            com.google.android.gms.internal.measurement.zzfi r4 = r31.zzgi()
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zzja()
            java.lang.String r9 = "Tag Manager is not found and thus will not be used"
            r4.log(r9)
        L_0x0072:
            r4 = 40
            r9 = 2
            if (r39 == 0) goto L_0x00df
            r31.zzgl()
            java.lang.String r10 = "_iap"
            boolean r10 = r10.equals(r6)
            if (r10 != 0) goto L_0x00df
            com.google.android.gms.internal.measurement.zzgn r10 = r1.zzacv
            com.google.android.gms.internal.measurement.zzkd r10 = r10.zzgg()
            java.lang.String r11 = "event"
            boolean r11 = r10.zzq(r11, r6)
            if (r11 != 0) goto L_0x0092
        L_0x0090:
            r10 = 2
            goto L_0x00a9
        L_0x0092:
            java.lang.String r11 = "event"
            java.lang.String[] r12 = com.google.android.gms.measurement.AppMeasurement.Event.zzacw
            boolean r11 = r10.zza(r11, r12, r6)
            if (r11 != 0) goto L_0x009f
            r10 = 13
            goto L_0x00a9
        L_0x009f:
            java.lang.String r11 = "event"
            boolean r10 = r10.zza(r11, r4, r6)
            if (r10 != 0) goto L_0x00a8
            goto L_0x0090
        L_0x00a8:
            r10 = 0
        L_0x00a9:
            if (r10 == 0) goto L_0x00df
            com.google.android.gms.internal.measurement.zzfi r2 = r31.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zzix()
            java.lang.String r3 = "Invalid public event name. Event will not be logged (FE)"
            com.google.android.gms.internal.measurement.zzfg r5 = r31.zzgf()
            java.lang.String r5 = r5.zzbm(r6)
            r2.zzg(r3, r5)
            com.google.android.gms.internal.measurement.zzgn r2 = r1.zzacv
            r2.zzgg()
            java.lang.String r2 = com.google.android.gms.internal.measurement.zzkd.zza(r6, r4, r15)
            if (r6 == 0) goto L_0x00d2
            int r16 = r33.length()
            r3 = r16
            goto L_0x00d3
        L_0x00d2:
            r3 = 0
        L_0x00d3:
            com.google.android.gms.internal.measurement.zzgn r4 = r1.zzacv
            com.google.android.gms.internal.measurement.zzkd r4 = r4.zzgg()
            java.lang.String r5 = "_ev"
            r4.zza(r10, r5, r2, r3)
            return
        L_0x00df:
            r31.zzgl()
            com.google.android.gms.internal.measurement.zzih r10 = r31.zzgb()
            com.google.android.gms.internal.measurement.zzig r14 = r10.zzkn()
            if (r14 == 0) goto L_0x00f6
            java.lang.String r10 = "_sc"
            boolean r10 = r5.containsKey(r10)
            if (r10 != 0) goto L_0x00f6
            r14.zzaqc = r15
        L_0x00f6:
            if (r37 == 0) goto L_0x00fc
            if (r39 == 0) goto L_0x00fc
            r10 = 1
            goto L_0x00fd
        L_0x00fc:
            r10 = 0
        L_0x00fd:
            com.google.android.gms.internal.measurement.zzih.zza(r14, r5, r10)
            java.lang.String r10 = "am"
            boolean r17 = r10.equals(r8)
            boolean r10 = com.google.android.gms.internal.measurement.zzkd.zzcm(r33)
            if (r37 == 0) goto L_0x013b
            com.google.android.gms.measurement.AppMeasurement$EventInterceptor r2 = r1.zzapm
            if (r2 == 0) goto L_0x013b
            if (r10 != 0) goto L_0x013b
            if (r17 != 0) goto L_0x013b
            com.google.android.gms.internal.measurement.zzfi r2 = r31.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zzjb()
            java.lang.String r3 = "Passing event to registered event handler (FE)"
            com.google.android.gms.internal.measurement.zzfg r4 = r31.zzgf()
            java.lang.String r4 = r4.zzbm(r6)
            com.google.android.gms.internal.measurement.zzfg r7 = r31.zzgf()
            java.lang.String r7 = r7.zzb(r5)
            r2.zze(r3, r4, r7)
            com.google.android.gms.measurement.AppMeasurement$EventInterceptor r2 = r1.zzapm
            r3 = r8
            r4 = r6
            r6 = r34
            r2.interceptEvent(r3, r4, r5, r6)
            return
        L_0x013b:
            com.google.android.gms.internal.measurement.zzgn r2 = r1.zzacv
            boolean r2 = r2.zzkg()
            if (r2 != 0) goto L_0x0144
            return
        L_0x0144:
            com.google.android.gms.internal.measurement.zzkd r2 = r31.zzgg()
            int r20 = r2.zzch(r6)
            if (r20 == 0) goto L_0x0183
            com.google.android.gms.internal.measurement.zzfi r2 = r31.zzgi()
            com.google.android.gms.internal.measurement.zzfk r2 = r2.zzix()
            java.lang.String r3 = "Invalid event name. Event will not be logged (FE)"
            com.google.android.gms.internal.measurement.zzfg r5 = r31.zzgf()
            java.lang.String r5 = r5.zzbm(r6)
            r2.zzg(r3, r5)
            r31.zzgg()
            java.lang.String r22 = com.google.android.gms.internal.measurement.zzkd.zza(r6, r4, r15)
            if (r6 == 0) goto L_0x0173
            int r16 = r33.length()
            r23 = r16
            goto L_0x0175
        L_0x0173:
            r23 = 0
        L_0x0175:
            com.google.android.gms.internal.measurement.zzgn r2 = r1.zzacv
            com.google.android.gms.internal.measurement.zzkd r18 = r2.zzgg()
            java.lang.String r21 = "_ev"
            r19 = r40
            r18.zza(r19, r20, r21, r22, r23)
            return
        L_0x0183:
            r2 = 4
            java.lang.String[] r2 = new java.lang.String[r2]
            java.lang.String r4 = "_o"
            r2[r16] = r4
            java.lang.String r4 = "_sn"
            r2[r15] = r4
            java.lang.String r4 = "_sc"
            r2[r9] = r4
            r4 = 3
            java.lang.String r9 = "_si"
            r2[r4] = r9
            java.util.List r2 = com.google.android.gms.common.util.CollectionUtils.listOf((T[]) r2)
            com.google.android.gms.internal.measurement.zzkd r9 = r31.zzgg()
            r4 = 1
            r10 = r40
            r11 = r6
            r12 = r5
            r13 = r2
            r18 = r14
            r14 = r39
            r7 = 1
            r15 = r4
            android.os.Bundle r4 = r9.zza(r10, r11, r12, r13, r14, r15)
            if (r4 == 0) goto L_0x01e3
            java.lang.String r9 = "_sc"
            boolean r9 = r4.containsKey(r9)
            if (r9 == 0) goto L_0x01e3
            java.lang.String r9 = "_si"
            boolean r9 = r4.containsKey(r9)
            if (r9 != 0) goto L_0x01c2
            goto L_0x01e3
        L_0x01c2:
            java.lang.String r9 = "_sn"
            java.lang.String r9 = r4.getString(r9)
            java.lang.String r10 = "_sc"
            java.lang.String r10 = r4.getString(r10)
            java.lang.String r11 = "_si"
            long r11 = r4.getLong(r11)
            java.lang.Long r11 = java.lang.Long.valueOf(r11)
            com.google.android.gms.internal.measurement.zzig r12 = new com.google.android.gms.internal.measurement.zzig
            long r13 = r11.longValue()
            r12.<init>(r9, r10, r13)
            r14 = r12
            goto L_0x01e4
        L_0x01e3:
            r14 = 0
        L_0x01e4:
            if (r14 != 0) goto L_0x01e9
            r15 = r18
            goto L_0x01ea
        L_0x01e9:
            r15 = r14
        L_0x01ea:
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            r14.add(r4)
            com.google.android.gms.internal.measurement.zzkd r9 = r31.zzgg()
            java.security.SecureRandom r9 = r9.zzlo()
            long r12 = r9.nextLong()
            java.util.Set r9 = r4.keySet()
            int r5 = r36.size()
            java.lang.String[] r5 = new java.lang.String[r5]
            java.lang.Object[] r5 = r9.toArray(r5)
            java.lang.String[] r5 = (java.lang.String[]) r5
            java.util.Arrays.sort(r5)
            int r11 = r5.length
            r9 = 0
            r10 = 0
        L_0x0214:
            if (r10 >= r11) goto L_0x02ca
            r7 = r5[r10]
            r24 = r5
            java.lang.Object r5 = r4.get(r7)
            r31.zzgg()
            android.os.Bundle[] r5 = com.google.android.gms.internal.measurement.zzkd.zze(r5)
            if (r5 == 0) goto L_0x02aa
            r25 = r9
            int r9 = r5.length
            r4.putInt(r7, r9)
            r26 = r10
            r9 = 0
        L_0x0230:
            int r10 = r5.length
            if (r9 >= r10) goto L_0x0297
            r10 = r5[r9]
            r8 = 1
            com.google.android.gms.internal.measurement.zzih.zza(r15, r10, r8)
            com.google.android.gms.internal.measurement.zzkd r18 = r31.zzgg()
            java.lang.String r19 = "_ep"
            r20 = 0
            r27 = r9
            r8 = r25
            r9 = r18
            r21 = r10
            r18 = r26
            r10 = r40
            r22 = r11
            r11 = r19
            r28 = r4
            r29 = r5
            r4 = r12
            r12 = r21
            r13 = r2
            r30 = r2
            r2 = r14
            r14 = r39
            r19 = r15
            r15 = r20
            android.os.Bundle r9 = r9.zza(r10, r11, r12, r13, r14, r15)
            java.lang.String r10 = "_en"
            r9.putString(r10, r6)
            java.lang.String r10 = "_eid"
            r9.putLong(r10, r4)
            java.lang.String r10 = "_gn"
            r9.putString(r10, r7)
            java.lang.String r10 = "_ll"
            r11 = r29
            int r12 = r11.length
            r9.putInt(r10, r12)
            java.lang.String r10 = "_i"
            r12 = r27
            r9.putInt(r10, r12)
            r2.add(r9)
            int r9 = r12 + 1
            r14 = r2
            r12 = r4
            r5 = r11
            r15 = r19
            r11 = r22
            r4 = r28
            r2 = r30
            r8 = r32
            goto L_0x0230
        L_0x0297:
            r30 = r2
            r28 = r4
            r22 = r11
            r2 = r14
            r19 = r15
            r8 = r25
            r18 = r26
            r11 = r5
            r4 = r12
            int r7 = r11.length
            int r9 = r8 + r7
            goto L_0x02b7
        L_0x02aa:
            r30 = r2
            r28 = r4
            r8 = r9
            r18 = r10
            r22 = r11
            r4 = r12
            r2 = r14
            r19 = r15
        L_0x02b7:
            int r10 = r18 + 1
            r14 = r2
            r12 = r4
            r15 = r19
            r11 = r22
            r5 = r24
            r4 = r28
            r2 = r30
            r7 = 1
            r8 = r32
            goto L_0x0214
        L_0x02ca:
            r28 = r4
            r8 = r9
            r4 = r12
            r2 = r14
            if (r8 == 0) goto L_0x02dd
            java.lang.String r3 = "_eid"
            r7 = r28
            r7.putLong(r3, r4)
            java.lang.String r3 = "_epc"
            r7.putInt(r3, r8)
        L_0x02dd:
            r8 = 0
        L_0x02de:
            int r3 = r2.size()
            if (r8 >= r3) goto L_0x036a
            java.lang.Object r3 = r2.get(r8)
            android.os.Bundle r3 = (android.os.Bundle) r3
            if (r8 == 0) goto L_0x02ee
            r4 = 1
            goto L_0x02ef
        L_0x02ee:
            r4 = 0
        L_0x02ef:
            if (r4 == 0) goto L_0x02f4
            java.lang.String r4 = "_ep"
            goto L_0x02f5
        L_0x02f4:
            r4 = r6
        L_0x02f5:
            java.lang.String r5 = "_o"
            r7 = 1
            r9 = r32
            r3.putString(r5, r9)
            if (r38 == 0) goto L_0x0307
            com.google.android.gms.internal.measurement.zzkd r5 = r31.zzgg()
            android.os.Bundle r3 = r5.zzd(r3)
        L_0x0307:
            r11 = r3
            com.google.android.gms.internal.measurement.zzfi r3 = r31.zzgi()
            com.google.android.gms.internal.measurement.zzfk r3 = r3.zzjb()
            java.lang.String r5 = "Logging event (FE)"
            com.google.android.gms.internal.measurement.zzfg r12 = r31.zzgf()
            java.lang.String r12 = r12.zzbm(r6)
            com.google.android.gms.internal.measurement.zzfg r13 = r31.zzgf()
            java.lang.String r13 = r13.zzb(r11)
            r3.zze(r5, r12, r13)
            com.google.android.gms.internal.measurement.zzex r12 = new com.google.android.gms.internal.measurement.zzex
            com.google.android.gms.internal.measurement.zzeu r5 = new com.google.android.gms.internal.measurement.zzeu
            r5.<init>(r11)
            r13 = r2
            r2 = r12
            r3 = r4
            r4 = r5
            r5 = r9
            r14 = r6
            r15 = 1
            r6 = r34
            r2.<init>(r3, r4, r5, r6)
            com.google.android.gms.internal.measurement.zzik r2 = r31.zzga()
            r6 = r40
            r2.zzb(r12, r6)
            if (r17 != 0) goto L_0x0364
            java.util.Set<com.google.android.gms.measurement.AppMeasurement$OnEventListener> r2 = r1.zzapn
            java.util.Iterator r12 = r2.iterator()
        L_0x0349:
            boolean r2 = r12.hasNext()
            if (r2 == 0) goto L_0x0364
            java.lang.Object r2 = r12.next()
            com.google.android.gms.measurement.AppMeasurement$OnEventListener r2 = (com.google.android.gms.measurement.AppMeasurement.OnEventListener) r2
            android.os.Bundle r5 = new android.os.Bundle
            r5.<init>(r11)
            r3 = r9
            r4 = r14
            r6 = r34
            r2.onEvent(r3, r4, r5, r6)
            r6 = r40
            goto L_0x0349
        L_0x0364:
            int r8 = r8 + 1
            r2 = r13
            r6 = r14
            goto L_0x02de
        L_0x036a:
            r14 = r6
            r15 = 1
            r31.zzgl()
            com.google.android.gms.internal.measurement.zzih r2 = r31.zzgb()
            com.google.android.gms.internal.measurement.zzig r2 = r2.zzkn()
            if (r2 == 0) goto L_0x0388
            java.lang.String r2 = "_ae"
            boolean r2 = r2.equals(r14)
            if (r2 == 0) goto L_0x0388
            com.google.android.gms.internal.measurement.zzjj r2 = r31.zzgd()
            r2.zzl(r15)
        L_0x0388:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhm.zza(java.lang.String, java.lang.String, long, android.os.Bundle, boolean, boolean, boolean, java.lang.String):void");
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzgi zzgh = zzgh();
        zzho zzho = new zzho(this, str, str2, obj, j);
        zzgh.zzc((Runnable) zzho);
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzb(str, str2, zzbt().currentTimeMillis(), bundle, z, z2, z3, str3);
    }

    /* access modifiers changed from: private */
    public final void zza(String str, String str2, Object obj, long j) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzfv();
        zzch();
        if (!this.zzacv.isEnabled()) {
            zzgi().zzjb().log("User property not set since app measurement is disabled");
        } else if (this.zzacv.zzkg()) {
            zzgi().zzjb().zze("Setting user property (FE)", zzgf().zzbm(str2), obj);
            zzka zzka = new zzka(str2, j, obj, str);
            zzga().zzb(zzka);
        }
    }

    private final void zza(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zzbt().currentTimeMillis();
        Preconditions.checkNotEmpty(str2);
        ConditionalUserProperty conditionalUserProperty = new ConditionalUserProperty();
        conditionalUserProperty.mAppId = str;
        conditionalUserProperty.mName = str2;
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        if (str3 != null) {
            conditionalUserProperty.mExpiredEventName = str3;
            conditionalUserProperty.mExpiredEventParams = bundle;
        }
        zzgh().zzc((Runnable) new zzhu(this, conditionalUserProperty));
    }

    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        zzfk zziy;
        String str4;
        if (zzgh().zzju()) {
            zziy = zzgi().zziv();
            str4 = "Cannot get user properties from analytics worker thread";
        } else if (zzee.isMainThread()) {
            zziy = zzgi().zziv();
            str4 = "Cannot get user properties from main thread";
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                zzgi zzgh = this.zzacv.zzgh();
                zzhw zzhw = new zzhw(this, atomicReference, str, str2, str3, z);
                zzgh.zzc((Runnable) zzhw);
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e) {
                    zzgi().zziy().zzg("Interrupted waiting for get user properties", e);
                }
            }
            List<zzka> list = (List) atomicReference.get();
            if (list == null) {
                zziy = zzgi().zziy();
                str4 = "Timed out waiting for get user properties";
            } else {
                ArrayMap arrayMap = new ArrayMap(list.size());
                for (zzka zzka : list) {
                    arrayMap.put(zzka.name, zzka.getValue());
                }
                return arrayMap;
            }
        }
        zziy.log(str4);
        return Collections.emptyMap();
    }

    /* access modifiers changed from: private */
    public final void zzb(ConditionalUserProperty conditionalUserProperty) {
        ConditionalUserProperty conditionalUserProperty2 = conditionalUserProperty;
        zzab();
        zzch();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty2.mName);
        Preconditions.checkNotEmpty(conditionalUserProperty2.mOrigin);
        Preconditions.checkNotNull(conditionalUserProperty2.mValue);
        if (!this.zzacv.isEnabled()) {
            zzgi().zzjb().log("Conditional property not sent since Firebase Analytics is disabled");
            return;
        }
        zzka zzka = new zzka(conditionalUserProperty2.mName, conditionalUserProperty2.mTriggeredTimestamp, conditionalUserProperty2.mValue, conditionalUserProperty2.mOrigin);
        try {
            zzex zza = zzgg().zza(conditionalUserProperty2.mAppId, conditionalUserProperty2.mTriggeredEventName, conditionalUserProperty2.mTriggeredEventParams, conditionalUserProperty2.mOrigin, 0, true, false);
            zzex zza2 = zzgg().zza(conditionalUserProperty2.mAppId, conditionalUserProperty2.mTimedOutEventName, conditionalUserProperty2.mTimedOutEventParams, conditionalUserProperty2.mOrigin, 0, true, false);
            zzex zza3 = zzgg().zza(conditionalUserProperty2.mAppId, conditionalUserProperty2.mExpiredEventName, conditionalUserProperty2.mExpiredEventParams, conditionalUserProperty2.mOrigin, 0, true, false);
            String str = conditionalUserProperty2.mAppId;
            String str2 = conditionalUserProperty2.mOrigin;
            long j = conditionalUserProperty2.mCreationTimestamp;
            String str3 = conditionalUserProperty2.mTriggerEventName;
            long j2 = conditionalUserProperty2.mTriggerTimeout;
            zzef zzef = r3;
            zzef zzef2 = new zzef(str, str2, zzka, j, false, str3, zza2, j2, zza, conditionalUserProperty2.mTimeToLive, zza3);
            zzga().zzd(zzef);
        } catch (IllegalArgumentException unused) {
        }
    }

    private final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        Bundle bundle2;
        Bundle bundle3 = bundle;
        if (bundle3 == null) {
            bundle2 = new Bundle();
        } else {
            Bundle bundle4 = new Bundle(bundle3);
            for (String str4 : bundle4.keySet()) {
                Object obj = bundle4.get(str4);
                if (obj instanceof Bundle) {
                    bundle4.putBundle(str4, new Bundle((Bundle) obj));
                } else {
                    int i = 0;
                    if (obj instanceof Parcelable[]) {
                        Parcelable[] parcelableArr = (Parcelable[]) obj;
                        while (i < parcelableArr.length) {
                            if (parcelableArr[i] instanceof Bundle) {
                                parcelableArr[i] = new Bundle((Bundle) parcelableArr[i]);
                            }
                            i++;
                        }
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList = (ArrayList) obj;
                        while (i < arrayList.size()) {
                            Object obj2 = arrayList.get(i);
                            if (obj2 instanceof Bundle) {
                                arrayList.set(i, new Bundle((Bundle) obj2));
                            }
                            i++;
                        }
                    }
                }
            }
            bundle2 = bundle4;
        }
        zzgi zzgh = zzgh();
        zzie zzie = new zzie(this, str, str2, j, bundle2, z, z2, z3, str3);
        zzgh.zzc((Runnable) zzie);
    }

    /* access modifiers changed from: private */
    public final void zzc(ConditionalUserProperty conditionalUserProperty) {
        ConditionalUserProperty conditionalUserProperty2 = conditionalUserProperty;
        zzab();
        zzch();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty2.mName);
        if (!this.zzacv.isEnabled()) {
            zzgi().zzjb().log("Conditional property not cleared since Firebase Analytics is disabled");
            return;
        }
        zzka zzka = new zzka(conditionalUserProperty2.mName, 0, null, null);
        try {
            zzex zza = zzgg().zza(conditionalUserProperty2.mAppId, conditionalUserProperty2.mExpiredEventName, conditionalUserProperty2.mExpiredEventParams, conditionalUserProperty2.mOrigin, conditionalUserProperty2.mCreationTimestamp, true, false);
            String str = conditionalUserProperty2.mAppId;
            String str2 = conditionalUserProperty2.mOrigin;
            long j = conditionalUserProperty2.mCreationTimestamp;
            boolean z = conditionalUserProperty2.mActive;
            String str3 = conditionalUserProperty2.mTriggerEventName;
            long j2 = conditionalUserProperty2.mTriggerTimeout;
            zzef zzef = r3;
            zzef zzef2 = new zzef(str, str2, zzka, j, z, str3, null, j2, null, conditionalUserProperty2.mTimeToLive, zza);
            zzga().zzd(zzef);
        } catch (IllegalArgumentException unused) {
        }
    }

    private final List<ConditionalUserProperty> zzf(String str, String str2, String str3) {
        zzfk zziv;
        String str4;
        if (zzgh().zzju()) {
            zziv = zzgi().zziv();
            str4 = "Cannot get conditional user properties from analytics worker thread";
        } else if (zzee.isMainThread()) {
            zziv = zzgi().zziv();
            str4 = "Cannot get conditional user properties from main thread";
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                zzgi zzgh = this.zzacv.zzgh();
                zzhv zzhv = new zzhv(this, atomicReference, str, str2, str3);
                zzgh.zzc((Runnable) zzhv);
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e) {
                    zzgi().zziy().zze("Interrupted waiting for get conditional user properties", str, e);
                }
            }
            List<zzef> list = (List) atomicReference.get();
            if (list == null) {
                zzgi().zziy().zzg("Timed out waiting for get conditional user properties", str);
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (zzef zzef : list) {
                ConditionalUserProperty conditionalUserProperty = new ConditionalUserProperty();
                conditionalUserProperty.mAppId = zzef.packageName;
                conditionalUserProperty.mOrigin = zzef.origin;
                conditionalUserProperty.mCreationTimestamp = zzef.creationTimestamp;
                conditionalUserProperty.mName = zzef.zzage.name;
                conditionalUserProperty.mValue = zzef.zzage.getValue();
                conditionalUserProperty.mActive = zzef.active;
                conditionalUserProperty.mTriggerEventName = zzef.triggerEventName;
                if (zzef.zzagf != null) {
                    conditionalUserProperty.mTimedOutEventName = zzef.zzagf.name;
                    if (zzef.zzagf.zzahg != null) {
                        conditionalUserProperty.mTimedOutEventParams = zzef.zzagf.zzahg.zzin();
                    }
                }
                conditionalUserProperty.mTriggerTimeout = zzef.triggerTimeout;
                if (zzef.zzagg != null) {
                    conditionalUserProperty.mTriggeredEventName = zzef.zzagg.name;
                    if (zzef.zzagg.zzahg != null) {
                        conditionalUserProperty.mTriggeredEventParams = zzef.zzagg.zzahg.zzin();
                    }
                }
                conditionalUserProperty.mTriggeredTimestamp = zzef.zzage.zzast;
                conditionalUserProperty.mTimeToLive = zzef.timeToLive;
                if (zzef.zzagh != null) {
                    conditionalUserProperty.mExpiredEventName = zzef.zzagh.name;
                    if (zzef.zzagh.zzahg != null) {
                        conditionalUserProperty.mExpiredEventParams = zzef.zzagh.zzahg.zzin();
                    }
                }
                arrayList.add(conditionalUserProperty);
            }
            return arrayList;
        }
        zziv.log(str4);
        return Collections.emptyList();
    }

    /* access modifiers changed from: private */
    public final void zzi(boolean z) {
        zzab();
        zzfv();
        zzch();
        zzgi().zzjb().zzg("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzgj().setMeasurementEnabled(z);
        if (!zzgk().zzbc(zzfz().zzah())) {
            zzga().zzkp();
        } else if (!this.zzacv.isEnabled() || !this.zzapq) {
            zzga().zzkp();
        } else {
            zzgi().zzjb().log("Recording app launch after enabling measurement for the first time (FE)");
            zzkm();
        }
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zzfv();
        zza((String) null, str, str2, bundle);
    }

    public final void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotEmpty(str);
        zzfu();
        zza(str, str2, str3, bundle);
    }

    public final Task<String> getAppInstanceId() {
        try {
            String zzjk = zzgj().zzjk();
            return zzjk != null ? Tasks.forResult(zzjk) : Tasks.call(zzgh().zzjv(), new zzhq(this));
        } catch (Exception e) {
            zzgi().zziy().log("Failed to schedule task for getAppInstanceId");
            return Tasks.forException(e);
        }
    }

    public final List<ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        zzfv();
        return zzf(null, str, str2);
    }

    public final List<ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzfu();
        return zzf(str, str2, str3);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        zzfv();
        return zzb(null, str, str2, z);
    }

    public final Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        Preconditions.checkNotEmpty(str);
        zzfu();
        return zzb(str, str2, str3, z);
    }

    public final void logEvent(String str, String str2, Bundle bundle) {
        zzfv();
        zza(str, str2, bundle, true, this.zzapm == null || zzkd.zzcm(str2), false, null);
    }

    public final void logEventNoInterceptor(String str, String str2, Bundle bundle, long j) {
        zzfv();
        zzb(str, str2, j, bundle, false, true, true, null);
    }

    public final void registerOnMeasurementEventListener(OnEventListener onEventListener) {
        zzfv();
        zzch();
        Preconditions.checkNotNull(onEventListener);
        if (!this.zzapn.add(onEventListener)) {
            zzgi().zziy().log("OnEventListener already registered");
        }
    }

    public final void resetAnalyticsData() {
        zzgh().zzc((Runnable) new zzhs(this, zzbt().currentTimeMillis()));
    }

    public final void setConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        zzfv();
        ConditionalUserProperty conditionalUserProperty2 = new ConditionalUserProperty(conditionalUserProperty);
        if (!TextUtils.isEmpty(conditionalUserProperty2.mAppId)) {
            zzgi().zziy().log("Package name should be null when calling setConditionalUserProperty");
        }
        conditionalUserProperty2.mAppId = null;
        zza(conditionalUserProperty2);
    }

    public final void setConditionalUserPropertyAs(ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mAppId);
        zzfu();
        zza(new ConditionalUserProperty(conditionalUserProperty));
    }

    public final void setEventInterceptor(EventInterceptor eventInterceptor) {
        zzab();
        zzfv();
        zzch();
        if (!(eventInterceptor == null || eventInterceptor == this.zzapm)) {
            Preconditions.checkState(this.zzapm == null, "EventInterceptor already set.");
        }
        this.zzapm = eventInterceptor;
    }

    public final void setMeasurementEnabled(boolean z) {
        zzch();
        zzfv();
        zzgh().zzc((Runnable) new zzib(this, z));
    }

    public final void setMinimumSessionDuration(long j) {
        zzfv();
        zzgh().zzc((Runnable) new zzic(this, j));
    }

    public final void setSessionTimeoutDuration(long j) {
        zzfv();
        zzgh().zzc((Runnable) new zzid(this, j));
    }

    public final void setUserProperty(String str, String str2, Object obj) {
        Preconditions.checkNotEmpty(str);
        long currentTimeMillis = zzbt().currentTimeMillis();
        int zzcj = zzgg().zzcj(str2);
        int i = 0;
        if (zzcj != 0) {
            zzgg();
            String zza = zzkd.zza(str2, 24, true);
            if (str2 != null) {
                i = str2.length();
            }
            this.zzacv.zzgg().zza(zzcj, "_ev", zza, i);
        } else if (obj != null) {
            int zzi = zzgg().zzi(str2, obj);
            if (zzi != 0) {
                zzgg();
                String zza2 = zzkd.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i = String.valueOf(obj).length();
                }
                this.zzacv.zzgg().zza(zzi, "_ev", zza2, i);
                return;
            }
            Object zzj = zzgg().zzj(str2, obj);
            if (zzj != null) {
                zza(str, str2, currentTimeMillis, zzj);
            }
        } else {
            zza(str, str2, currentTimeMillis, (Object) null);
        }
    }

    public final void unregisterOnMeasurementEventListener(OnEventListener onEventListener) {
        zzfv();
        zzch();
        Preconditions.checkNotNull(onEventListener);
        if (!this.zzapn.remove(onEventListener)) {
            zzgi().zziy().log("OnEventListener had not been registered");
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(String str, String str2, Bundle bundle) {
        zzfv();
        zzab();
        zza(str, str2, zzbt().currentTimeMillis(), bundle, true, this.zzapm == null || zzkd.zzcm(str2), false, null);
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z) {
        zzfv();
        zza(str, str2, bundle, true, this.zzapm == null || zzkd.zzcm(str2), true, null);
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:10|11|12|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        zzgi().zziy().log("Interrupted waiting for app instance id");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzaj(long r4) {
        /*
            r3 = this;
            java.util.concurrent.atomic.AtomicReference r0 = new java.util.concurrent.atomic.AtomicReference
            r0.<init>()
            monitor-enter(r0)
            com.google.android.gms.internal.measurement.zzgi r1 = r3.zzgh()     // Catch:{ all -> 0x002d }
            com.google.android.gms.internal.measurement.zzhr r2 = new com.google.android.gms.internal.measurement.zzhr     // Catch:{ all -> 0x002d }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x002d }
            r1.zzc(r2)     // Catch:{ all -> 0x002d }
            r0.wait(r4)     // Catch:{ InterruptedException -> 0x001d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            java.lang.Object r4 = r0.get()
            java.lang.String r4 = (java.lang.String) r4
            return r4
        L_0x001d:
            com.google.android.gms.internal.measurement.zzfi r4 = r3.zzgi()     // Catch:{ all -> 0x002d }
            com.google.android.gms.internal.measurement.zzfk r4 = r4.zziy()     // Catch:{ all -> 0x002d }
            java.lang.String r5 = "Interrupted waiting for app instance id"
            r4.log(r5)     // Catch:{ all -> 0x002d }
            r4 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r4
        L_0x002d:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhm.zzaj(long):java.lang.String");
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    /* access modifiers changed from: 0000 */
    public final void zzbu(String str) {
        this.zzapp.set(str);
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

    public final List<zzka> zzj(boolean z) {
        zzfk zziy;
        String str;
        zzfv();
        zzch();
        zzgi().zzjb().log("Fetching user attributes (FE)");
        if (zzgh().zzju()) {
            zziy = zzgi().zziv();
            str = "Cannot get all user properties from analytics worker thread";
        } else if (zzee.isMainThread()) {
            zziy = zzgi().zziv();
            str = "Cannot get all user properties from main thread";
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.zzacv.zzgh().zzc((Runnable) new zzhp(this, atomicReference, z));
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e) {
                    zzgi().zziy().zzg("Interrupted waiting for get user properties", e);
                }
            }
            List<zzka> list = (List) atomicReference.get();
            if (list != null) {
                return list;
            }
            zziy = zzgi().zziy();
            str = "Timed out waiting for get user properties";
        }
        zziy.log(str);
        return Collections.emptyList();
    }

    public final String zzjk() {
        zzfv();
        return (String) this.zzapp.get();
    }

    public final Boolean zzkh() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzgh().zza(atomicReference, 15000, "boolean test flag value", new zzhn(this, atomicReference));
    }

    public final String zzki() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzgh().zza(atomicReference, 15000, "String test flag value", new zzhx(this, atomicReference));
    }

    public final Long zzkj() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzgh().zza(atomicReference, 15000, "long test flag value", new zzhy(this, atomicReference));
    }

    public final Integer zzkk() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzgh().zza(atomicReference, 15000, "int test flag value", new zzhz(this, atomicReference));
    }

    public final Double zzkl() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzgh().zza(atomicReference, 15000, "double test flag value", new zzia(this, atomicReference));
    }

    public final void zzkm() {
        zzab();
        zzfv();
        zzch();
        if (this.zzacv.zzkg()) {
            zzga().zzkm();
            this.zzapq = false;
            String zzjn = zzgj().zzjn();
            if (!TextUtils.isEmpty(zzjn)) {
                zzge().zzch();
                if (!zzjn.equals(VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", zzjn);
                    logEvent("auto", "_ou", bundle);
                }
            }
        }
    }
}
