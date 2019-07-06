package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement.ConditionalUserProperty;
import com.google.android.gms.measurement.AppMeasurement.b;
import com.google.android.gms.measurement.AppMeasurement.c;
import com.google.android.gms.tasks.f;
import com.google.android.gms.tasks.i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

public final class cs extends s {
    @VisibleForTesting
    protected dl a;
    @VisibleForTesting
    protected boolean b = true;
    private b c;
    private final Set<c> d = new CopyOnWriteArraySet();
    private boolean e;
    private final AtomicReference<String> f = new AtomicReference<>();

    protected cs(bu buVar) {
        super(buVar);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ac  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r32, java.lang.String r33, long r34, android.os.Bundle r36, boolean r37, boolean r38, boolean r39, java.lang.String r40) {
        /*
            r31 = this;
            r1 = r31
            r8 = r32
            r6 = r33
            r5 = r36
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r32)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r33)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r36)
            r31.d()
            r31.w()
            com.google.android.gms.internal.measurement.bu r4 = r1.q
            boolean r4 = r4.y()
            if (r4 != 0) goto L_0x002d
            com.google.android.gms.internal.measurement.aq r2 = r31.r()
            com.google.android.gms.internal.measurement.as r2 = r2.v()
            java.lang.String r3 = "Event not sent since app measurement is disabled"
            r2.a(r3)
            return
        L_0x002d:
            boolean r4 = r1.e
            r7 = 0
            r16 = 0
            r15 = 1
            if (r4 != 0) goto L_0x0072
            r1.e = r15
            java.lang.String r4 = "com.google.android.gms.tagmanager.TagManagerService"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x0065 }
            java.lang.String r9 = "initialize"
            java.lang.Class[] r10 = new java.lang.Class[r15]     // Catch:{ Exception -> 0x0055 }
            java.lang.Class<android.content.Context> r11 = android.content.Context.class
            r10[r16] = r11     // Catch:{ Exception -> 0x0055 }
            java.lang.reflect.Method r4 = r4.getDeclaredMethod(r9, r10)     // Catch:{ Exception -> 0x0055 }
            java.lang.Object[] r9 = new java.lang.Object[r15]     // Catch:{ Exception -> 0x0055 }
            android.content.Context r10 = r31.n()     // Catch:{ Exception -> 0x0055 }
            r9[r16] = r10     // Catch:{ Exception -> 0x0055 }
            r4.invoke(r7, r9)     // Catch:{ Exception -> 0x0055 }
            goto L_0x0072
        L_0x0055:
            r0 = move-exception
            r4 = r0
            com.google.android.gms.internal.measurement.aq r9 = r31.r()     // Catch:{ ClassNotFoundException -> 0x0065 }
            com.google.android.gms.internal.measurement.as r9 = r9.i()     // Catch:{ ClassNotFoundException -> 0x0065 }
            java.lang.String r10 = "Failed to invoke Tag Manager's initialize() method"
            r9.a(r10, r4)     // Catch:{ ClassNotFoundException -> 0x0065 }
            goto L_0x0072
        L_0x0065:
            com.google.android.gms.internal.measurement.aq r4 = r31.r()
            com.google.android.gms.internal.measurement.as r4 = r4.k()
            java.lang.String r9 = "Tag Manager is not found and thus will not be used"
            r4.a(r9)
        L_0x0072:
            r4 = 40
            r9 = 2
            if (r39 == 0) goto L_0x00de
            r31.u()
            java.lang.String r10 = "_iap"
            boolean r10 = r10.equals(r6)
            if (r10 != 0) goto L_0x00de
            com.google.android.gms.internal.measurement.bu r10 = r1.q
            com.google.android.gms.internal.measurement.fg r10 = r10.k()
            java.lang.String r11 = "event"
            boolean r11 = r10.a(r11, r6)
            if (r11 != 0) goto L_0x0092
        L_0x0090:
            r10 = r9
            goto L_0x00aa
        L_0x0092:
            java.lang.String r11 = "event"
            java.lang.String[] r12 = com.google.android.gms.measurement.AppMeasurement.a.a
            boolean r11 = r10.a(r11, r12, r6)
            if (r11 != 0) goto L_0x009f
            r10 = 13
            goto L_0x00aa
        L_0x009f:
            java.lang.String r11 = "event"
            boolean r10 = r10.a(r11, r4, r6)
            if (r10 != 0) goto L_0x00a8
            goto L_0x0090
        L_0x00a8:
            r10 = r16
        L_0x00aa:
            if (r10 == 0) goto L_0x00de
            com.google.android.gms.internal.measurement.aq r2 = r31.r()
            com.google.android.gms.internal.measurement.as r2 = r2.h()
            java.lang.String r3 = "Invalid public event name. Event will not be logged (FE)"
            com.google.android.gms.internal.measurement.ao r5 = r31.o()
            java.lang.String r5 = r5.a(r6)
            r2.a(r3, r5)
            com.google.android.gms.internal.measurement.bu r2 = r1.q
            r2.k()
            java.lang.String r2 = com.google.android.gms.internal.measurement.fg.a(r6, r4, r15)
            if (r6 == 0) goto L_0x00d0
            int r16 = r33.length()
        L_0x00d0:
            r3 = r16
            com.google.android.gms.internal.measurement.bu r4 = r1.q
            com.google.android.gms.internal.measurement.fg r4 = r4.k()
            java.lang.String r5 = "_ev"
            r4.a(r10, r5, r2, r3)
            return
        L_0x00de:
            r31.u()
            com.google.android.gms.internal.measurement.dn r10 = r31.i()
            com.google.android.gms.internal.measurement.dm r14 = r10.B()
            if (r14 == 0) goto L_0x00f5
            java.lang.String r10 = "_sc"
            boolean r10 = r5.containsKey(r10)
            if (r10 != 0) goto L_0x00f5
            r14.d = r15
        L_0x00f5:
            if (r37 == 0) goto L_0x00fb
            if (r39 == 0) goto L_0x00fb
            r10 = r15
            goto L_0x00fd
        L_0x00fb:
            r10 = r16
        L_0x00fd:
            com.google.android.gms.internal.measurement.dn.a(r14, r5, r10)
            java.lang.String r10 = "am"
            boolean r17 = r10.equals(r8)
            boolean r10 = com.google.android.gms.internal.measurement.fg.g(r33)
            if (r37 == 0) goto L_0x013b
            com.google.android.gms.measurement.AppMeasurement$b r2 = r1.c
            if (r2 == 0) goto L_0x013b
            if (r10 != 0) goto L_0x013b
            if (r17 != 0) goto L_0x013b
            com.google.android.gms.internal.measurement.aq r2 = r31.r()
            com.google.android.gms.internal.measurement.as r2 = r2.v()
            java.lang.String r3 = "Passing event to registered event handler (FE)"
            com.google.android.gms.internal.measurement.ao r4 = r31.o()
            java.lang.String r4 = r4.a(r6)
            com.google.android.gms.internal.measurement.ao r7 = r31.o()
            java.lang.String r7 = r7.a(r5)
            r2.a(r3, r4, r7)
            com.google.android.gms.measurement.AppMeasurement$b r2 = r1.c
            r3 = r8
            r4 = r6
            r6 = r34
            r2.a(r3, r4, r5, r6)
            return
        L_0x013b:
            com.google.android.gms.internal.measurement.bu r2 = r1.q
            boolean r2 = r2.D()
            if (r2 != 0) goto L_0x0144
            return
        L_0x0144:
            com.google.android.gms.internal.measurement.fg r2 = r31.p()
            int r20 = r2.b(r6)
            if (r20 == 0) goto L_0x0180
            com.google.android.gms.internal.measurement.aq r2 = r31.r()
            com.google.android.gms.internal.measurement.as r2 = r2.h()
            java.lang.String r3 = "Invalid event name. Event will not be logged (FE)"
            com.google.android.gms.internal.measurement.ao r5 = r31.o()
            java.lang.String r5 = r5.a(r6)
            r2.a(r3, r5)
            r31.p()
            java.lang.String r22 = com.google.android.gms.internal.measurement.fg.a(r6, r4, r15)
            if (r6 == 0) goto L_0x0170
            int r16 = r33.length()
        L_0x0170:
            r23 = r16
            com.google.android.gms.internal.measurement.bu r2 = r1.q
            com.google.android.gms.internal.measurement.fg r18 = r2.k()
            java.lang.String r21 = "_ev"
            r19 = r40
            r18.a(r19, r20, r21, r22, r23)
            return
        L_0x0180:
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
            com.google.android.gms.internal.measurement.fg r9 = r31.p()
            r4 = 1
            r10 = r40
            r11 = r6
            r12 = r5
            r13 = r2
            r18 = r14
            r14 = r39
            r7 = r15
            r15 = r4
            android.os.Bundle r4 = r9.a(r10, r11, r12, r13, r14, r15)
            if (r4 == 0) goto L_0x01e0
            java.lang.String r9 = "_sc"
            boolean r9 = r4.containsKey(r9)
            if (r9 == 0) goto L_0x01e0
            java.lang.String r9 = "_si"
            boolean r9 = r4.containsKey(r9)
            if (r9 != 0) goto L_0x01bf
            goto L_0x01e0
        L_0x01bf:
            java.lang.String r9 = "_sn"
            java.lang.String r9 = r4.getString(r9)
            java.lang.String r10 = "_sc"
            java.lang.String r10 = r4.getString(r10)
            java.lang.String r11 = "_si"
            long r11 = r4.getLong(r11)
            java.lang.Long r11 = java.lang.Long.valueOf(r11)
            com.google.android.gms.internal.measurement.dm r12 = new com.google.android.gms.internal.measurement.dm
            long r13 = r11.longValue()
            r12.<init>(r9, r10, r13)
            r14 = r12
            goto L_0x01e1
        L_0x01e0:
            r14 = 0
        L_0x01e1:
            if (r14 != 0) goto L_0x01e6
            r15 = r18
            goto L_0x01e7
        L_0x01e6:
            r15 = r14
        L_0x01e7:
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            r14.add(r4)
            com.google.android.gms.internal.measurement.fg r9 = r31.p()
            java.security.SecureRandom r9 = r9.h()
            long r12 = r9.nextLong()
            java.util.Set r9 = r4.keySet()
            int r5 = r36.size()
            java.lang.String[] r5 = new java.lang.String[r5]
            java.lang.Object[] r5 = r9.toArray(r5)
            java.lang.String[] r5 = (java.lang.String[]) r5
            java.util.Arrays.sort(r5)
            int r11 = r5.length
            r9 = r16
            r10 = r9
        L_0x0212:
            if (r10 >= r11) goto L_0x02c9
            r7 = r5[r10]
            r24 = r5
            java.lang.Object r5 = r4.get(r7)
            r31.p()
            android.os.Bundle[] r5 = com.google.android.gms.internal.measurement.fg.a(r5)
            if (r5 == 0) goto L_0x02a9
            r25 = r9
            int r9 = r5.length
            r4.putInt(r7, r9)
            r26 = r10
            r9 = r16
        L_0x022f:
            int r10 = r5.length
            if (r9 >= r10) goto L_0x0296
            r10 = r5[r9]
            r8 = 1
            com.google.android.gms.internal.measurement.dn.a(r15, r10, r8)
            com.google.android.gms.internal.measurement.fg r18 = r31.p()
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
            android.os.Bundle r9 = r9.a(r10, r11, r12, r13, r14, r15)
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
            goto L_0x022f
        L_0x0296:
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
            goto L_0x02b6
        L_0x02a9:
            r30 = r2
            r28 = r4
            r8 = r9
            r18 = r10
            r22 = r11
            r4 = r12
            r2 = r14
            r19 = r15
        L_0x02b6:
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
            goto L_0x0212
        L_0x02c9:
            r28 = r4
            r8 = r9
            r4 = r12
            r2 = r14
            if (r8 == 0) goto L_0x02dc
            java.lang.String r3 = "_eid"
            r7 = r28
            r7.putLong(r3, r4)
            java.lang.String r3 = "_epc"
            r7.putInt(r3, r8)
        L_0x02dc:
            r8 = r16
        L_0x02de:
            int r3 = r2.size()
            if (r8 >= r3) goto L_0x036b
            java.lang.Object r3 = r2.get(r8)
            android.os.Bundle r3 = (android.os.Bundle) r3
            if (r8 == 0) goto L_0x02ee
            r4 = 1
            goto L_0x02f0
        L_0x02ee:
            r4 = r16
        L_0x02f0:
            if (r4 == 0) goto L_0x02f5
            java.lang.String r4 = "_ep"
            goto L_0x02f6
        L_0x02f5:
            r4 = r6
        L_0x02f6:
            java.lang.String r5 = "_o"
            r7 = 1
            r9 = r32
            r3.putString(r5, r9)
            if (r38 == 0) goto L_0x0308
            com.google.android.gms.internal.measurement.fg r5 = r31.p()
            android.os.Bundle r3 = r5.a(r3)
        L_0x0308:
            r11 = r3
            com.google.android.gms.internal.measurement.aq r3 = r31.r()
            com.google.android.gms.internal.measurement.as r3 = r3.v()
            java.lang.String r5 = "Logging event (FE)"
            com.google.android.gms.internal.measurement.ao r12 = r31.o()
            java.lang.String r12 = r12.a(r6)
            com.google.android.gms.internal.measurement.ao r13 = r31.o()
            java.lang.String r13 = r13.a(r11)
            r3.a(r5, r12, r13)
            com.google.android.gms.internal.measurement.zzex r12 = new com.google.android.gms.internal.measurement.zzex
            com.google.android.gms.internal.measurement.zzeu r5 = new com.google.android.gms.internal.measurement.zzeu
            r5.<init>(r11)
            r13 = r2
            r2 = r12
            r3 = r4
            r4 = r5
            r5 = r9
            r14 = r6
            r15 = r7
            r6 = r34
            r2.<init>(r3, r4, r5, r6)
            com.google.android.gms.internal.measurement.dq r2 = r31.h()
            r6 = r40
            r2.a(r12, r6)
            if (r17 != 0) goto L_0x0365
            java.util.Set<com.google.android.gms.measurement.AppMeasurement$c> r2 = r1.d
            java.util.Iterator r12 = r2.iterator()
        L_0x034a:
            boolean r2 = r12.hasNext()
            if (r2 == 0) goto L_0x0365
            java.lang.Object r2 = r12.next()
            com.google.android.gms.measurement.AppMeasurement$c r2 = (com.google.android.gms.measurement.AppMeasurement.c) r2
            android.os.Bundle r5 = new android.os.Bundle
            r5.<init>(r11)
            r3 = r9
            r4 = r14
            r6 = r34
            r2.a(r3, r4, r5, r6)
            r6 = r40
            goto L_0x034a
        L_0x0365:
            int r8 = r8 + 1
            r2 = r13
            r6 = r14
            goto L_0x02de
        L_0x036b:
            r14 = r6
            r15 = 1
            r31.u()
            com.google.android.gms.internal.measurement.dn r2 = r31.i()
            com.google.android.gms.internal.measurement.dm r2 = r2.B()
            if (r2 == 0) goto L_0x0389
            java.lang.String r2 = "_ae"
            boolean r2 = r2.equals(r14)
            if (r2 == 0) goto L_0x0389
            com.google.android.gms.internal.measurement.eo r2 = r31.k()
            r2.a(r15)
        L_0x0389:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.cs.a(java.lang.String, java.lang.String, long, android.os.Bundle, boolean, boolean, boolean, java.lang.String):void");
    }

    private final void a(String str, String str2, long j, Object obj) {
        bq q = q();
        cu cuVar = new cu(this, str, str2, obj, j);
        q.a((Runnable) cuVar);
    }

    private final void a(String str, String str2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        b(str, str2, m().currentTimeMillis(), bundle, z, z2, z3, str3);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void a(String str, String str2, Object obj, long j) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        d();
        b();
        w();
        if (!this.q.y()) {
            r().v().a("User property not set since app measurement is disabled");
        } else if (this.q.D()) {
            r().v().a("Setting user property (FE)", o().a(str2), obj);
            zzka zzka = new zzka(str2, j, obj, str);
            h().a(zzka);
        }
    }

    @VisibleForTesting
    private final List<ConditionalUserProperty> b(String str, String str2, String str3) {
        as h_;
        String str4;
        if (q().g()) {
            h_ = r().h_();
            str4 = "Cannot get conditional user properties from analytics worker thread";
        } else if (v.a()) {
            h_ = r().h_();
            str4 = "Cannot get conditional user properties from main thread";
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                bq q = this.q.q();
                db dbVar = new db(this, atomicReference, str, str2, str3);
                q.a((Runnable) dbVar);
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e2) {
                    r().i().a("Interrupted waiting for get conditional user properties", str, e2);
                }
            }
            List<zzef> list = (List) atomicReference.get();
            if (list == null) {
                r().i().a("Timed out waiting for get conditional user properties", str);
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
        h_.a(str4);
        return Collections.emptyList();
    }

    @VisibleForTesting
    private final Map<String, Object> b(String str, String str2, String str3, boolean z) {
        as i;
        String str4;
        if (q().g()) {
            i = r().h_();
            str4 = "Cannot get user properties from analytics worker thread";
        } else if (v.a()) {
            i = r().h_();
            str4 = "Cannot get user properties from main thread";
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                bq q = this.q.q();
                dc dcVar = new dc(this, atomicReference, str, str2, str3, z);
                q.a((Runnable) dcVar);
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e2) {
                    r().i().a("Interrupted waiting for get user properties", e2);
                }
            }
            List<zzka> list = (List) atomicReference.get();
            if (list == null) {
                i = r().i();
                str4 = "Timed out waiting for get user properties";
            } else {
                ArrayMap arrayMap = new ArrayMap(list.size());
                for (zzka zzka : list) {
                    arrayMap.put(zzka.name, zzka.getValue());
                }
                return arrayMap;
            }
        }
        i.a(str4);
        return Collections.emptyMap();
    }

    private final void b(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
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
        bq q = q();
        dk dkVar = new dk(this, str, str2, j, bundle2, z, z2, z3, str3);
        q.a((Runnable) dkVar);
    }

    private final void b(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = m().currentTimeMillis();
        Preconditions.checkNotEmpty(str2);
        ConditionalUserProperty conditionalUserProperty = new ConditionalUserProperty();
        conditionalUserProperty.mAppId = str;
        conditionalUserProperty.mName = str2;
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        if (str3 != null) {
            conditionalUserProperty.mExpiredEventName = str3;
            conditionalUserProperty.mExpiredEventParams = bundle;
        }
        q().a((Runnable) new da(this, conditionalUserProperty));
    }

    private final void c(ConditionalUserProperty conditionalUserProperty) {
        long currentTimeMillis = m().currentTimeMillis();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mName);
        Preconditions.checkNotEmpty(conditionalUserProperty.mOrigin);
        Preconditions.checkNotNull(conditionalUserProperty.mValue);
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        String str = conditionalUserProperty.mName;
        Object obj = conditionalUserProperty.mValue;
        if (p().d(str) != 0) {
            r().h_().a("Invalid conditional user property name", o().c(str));
        } else if (p().b(str, obj) != 0) {
            r().h_().a("Invalid conditional user property value", o().c(str), obj);
        } else {
            Object c2 = p().c(str, obj);
            if (c2 == null) {
                r().h_().a("Unable to normalize conditional user property value", o().c(str), obj);
                return;
            }
            conditionalUserProperty.mValue = c2;
            long j = conditionalUserProperty.mTriggerTimeout;
            if (TextUtils.isEmpty(conditionalUserProperty.mTriggerEventName) || (j <= 15552000000L && j >= 1)) {
                long j2 = conditionalUserProperty.mTimeToLive;
                if (j2 > 15552000000L || j2 < 1) {
                    r().h_().a("Invalid conditional user property time to live", o().c(str), Long.valueOf(j2));
                } else {
                    q().a((Runnable) new cz(this, conditionalUserProperty));
                }
            } else {
                r().h_().a("Invalid conditional user property timeout", o().c(str), Long.valueOf(j));
            }
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void c(boolean z) {
        d();
        b();
        w();
        r().v().a("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        s().b(z);
        if (!t().k(g().C())) {
            h().C();
        } else if (!this.q.y() || !this.b) {
            h().C();
        } else {
            r().v().a("Recording app launch after enabling measurement for the first time (FE)");
            J();
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void d(ConditionalUserProperty conditionalUserProperty) {
        ConditionalUserProperty conditionalUserProperty2 = conditionalUserProperty;
        d();
        w();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty2.mName);
        Preconditions.checkNotEmpty(conditionalUserProperty2.mOrigin);
        Preconditions.checkNotNull(conditionalUserProperty2.mValue);
        if (!this.q.y()) {
            r().v().a("Conditional property not sent since Firebase Analytics is disabled");
            return;
        }
        zzka zzka = new zzka(conditionalUserProperty2.mName, conditionalUserProperty2.mTriggeredTimestamp, conditionalUserProperty2.mValue, conditionalUserProperty2.mOrigin);
        try {
            zzex a2 = p().a(conditionalUserProperty2.mAppId, conditionalUserProperty2.mTriggeredEventName, conditionalUserProperty2.mTriggeredEventParams, conditionalUserProperty2.mOrigin, 0, true, false);
            zzex a3 = p().a(conditionalUserProperty2.mAppId, conditionalUserProperty2.mTimedOutEventName, conditionalUserProperty2.mTimedOutEventParams, conditionalUserProperty2.mOrigin, 0, true, false);
            zzex a4 = p().a(conditionalUserProperty2.mAppId, conditionalUserProperty2.mExpiredEventName, conditionalUserProperty2.mExpiredEventParams, conditionalUserProperty2.mOrigin, 0, true, false);
            String str = conditionalUserProperty2.mAppId;
            String str2 = conditionalUserProperty2.mOrigin;
            long j = conditionalUserProperty2.mCreationTimestamp;
            String str3 = conditionalUserProperty2.mTriggerEventName;
            long j2 = conditionalUserProperty2.mTriggerTimeout;
            zzef zzef = r3;
            zzef zzef2 = new zzef(str, str2, zzka, j, false, str3, a3, j2, a2, conditionalUserProperty2.mTimeToLive, a4);
            h().a(zzef);
        } catch (IllegalArgumentException unused) {
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void e(ConditionalUserProperty conditionalUserProperty) {
        ConditionalUserProperty conditionalUserProperty2 = conditionalUserProperty;
        d();
        w();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty2.mName);
        if (!this.q.y()) {
            r().v().a("Conditional property not cleared since Firebase Analytics is disabled");
            return;
        }
        zzka zzka = new zzka(conditionalUserProperty2.mName, 0, null, null);
        try {
            zzex a2 = p().a(conditionalUserProperty2.mAppId, conditionalUserProperty2.mExpiredEventName, conditionalUserProperty2.mExpiredEventParams, conditionalUserProperty2.mOrigin, conditionalUserProperty2.mCreationTimestamp, true, false);
            String str = conditionalUserProperty2.mAppId;
            String str2 = conditionalUserProperty2.mOrigin;
            long j = conditionalUserProperty2.mCreationTimestamp;
            boolean z = conditionalUserProperty2.mActive;
            String str3 = conditionalUserProperty2.mTriggerEventName;
            long j2 = conditionalUserProperty2.mTriggerTimeout;
            zzef zzef = r3;
            zzef zzef2 = new zzef(str, str2, zzka, j, z, str3, null, j2, null, conditionalUserProperty2.mTimeToLive, a2);
            h().a(zzef);
        } catch (IllegalArgumentException unused) {
        }
    }

    public final Boolean B() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) q().a(atomicReference, 15000, "boolean test flag value", new ct(this, atomicReference));
    }

    public final String C() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) q().a(atomicReference, 15000, "String test flag value", new dd(this, atomicReference));
    }

    public final Long D() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) q().a(atomicReference, 15000, "long test flag value", new de(this, atomicReference));
    }

    public final Integer E() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) q().a(atomicReference, 15000, "int test flag value", new df(this, atomicReference));
    }

    public final Double F() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) q().a(atomicReference, 15000, "double test flag value", new dg(this, atomicReference));
    }

    public final f<String> G() {
        try {
            String h = s().h();
            return h != null ? i.a(h) : i.a((Executor) q().h(), (Callable<TResult>) new cw<TResult>(this));
        } catch (Exception e2) {
            r().i().a("Failed to schedule task for getAppInstanceId");
            return i.a(e2);
        }
    }

    @Nullable
    public final String H() {
        b();
        return (String) this.f.get();
    }

    public final void I() {
        q().a((Runnable) new cy(this, m().currentTimeMillis()));
    }

    @WorkerThread
    public final void J() {
        d();
        b();
        w();
        if (this.q.D()) {
            h().E();
            this.b = false;
            String k = s().k();
            if (!TextUtils.isEmpty(k)) {
                l().z();
                if (!k.equals(VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", k);
                    a("auto", "_ou", bundle);
                }
            }
        }
    }

    public final List<ConditionalUserProperty> a(String str, String str2) {
        b();
        return b((String) null, str, str2);
    }

    public final List<ConditionalUserProperty> a(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        a();
        return b(str, str2, str3);
    }

    public final Map<String, Object> a(String str, String str2, String str3, boolean z) {
        Preconditions.checkNotEmpty(str);
        a();
        return b(str, str2, str3, z);
    }

    public final Map<String, Object> a(String str, String str2, boolean z) {
        b();
        return b((String) null, str, str2, z);
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    public final void a(long j) {
        b();
        q().a((Runnable) new di(this, j));
    }

    public final void a(ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        b();
        ConditionalUserProperty conditionalUserProperty2 = new ConditionalUserProperty(conditionalUserProperty);
        if (!TextUtils.isEmpty(conditionalUserProperty2.mAppId)) {
            r().i().a("Package name should be null when calling setConditionalUserProperty");
        }
        conditionalUserProperty2.mAppId = null;
        c(conditionalUserProperty2);
    }

    @WorkerThread
    public final void a(b bVar) {
        d();
        b();
        w();
        if (!(bVar == null || bVar == this.c)) {
            Preconditions.checkState(this.c == null, "EventInterceptor already set.");
        }
        this.c = bVar;
    }

    public final void a(c cVar) {
        b();
        w();
        Preconditions.checkNotNull(cVar);
        if (!this.d.add(cVar)) {
            r().i().a("OnEventListener already registered");
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(@Nullable String str) {
        this.f.set(str);
    }

    public final void a(String str, String str2, Bundle bundle) {
        b();
        a(str, str2, bundle, true, this.c == null || fg.g(str2), false, null);
    }

    public final void a(String str, String str2, Bundle bundle, long j) {
        b();
        b(str, str2, j, bundle, false, true, true, null);
    }

    public final void a(String str, String str2, Bundle bundle, boolean z) {
        b();
        a(str, str2, bundle, true, this.c == null || fg.g(str2), true, null);
    }

    public final void a(String str, String str2, Object obj) {
        Preconditions.checkNotEmpty(str);
        long currentTimeMillis = m().currentTimeMillis();
        int d2 = p().d(str2);
        int i = 0;
        if (d2 != 0) {
            p();
            String a2 = fg.a(str2, 24, true);
            if (str2 != null) {
                i = str2.length();
            }
            this.q.k().a(d2, "_ev", a2, i);
        } else if (obj != null) {
            int b2 = p().b(str2, obj);
            if (b2 != 0) {
                p();
                String a3 = fg.a(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i = String.valueOf(obj).length();
                }
                this.q.k().a(b2, "_ev", a3, i);
                return;
            }
            Object c2 = p().c(str2, obj);
            if (c2 != null) {
                a(str, str2, currentTimeMillis, c2);
            }
        } else {
            a(str, str2, currentTimeMillis, (Object) null);
        }
    }

    public final void a(String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotEmpty(str);
        a();
        b(str, str2, str3, bundle);
    }

    public final void a(boolean z) {
        w();
        b();
        q().a((Runnable) new dh(this, z));
    }

    public final List<zzka> b(boolean z) {
        as i;
        String str;
        b();
        w();
        r().v().a("Fetching user attributes (FE)");
        if (q().g()) {
            i = r().h_();
            str = "Cannot get all user properties from analytics worker thread";
        } else if (v.a()) {
            i = r().h_();
            str = "Cannot get all user properties from main thread";
        } else {
            AtomicReference atomicReference = new AtomicReference();
            synchronized (atomicReference) {
                this.q.q().a((Runnable) new cv(this, atomicReference, z));
                try {
                    atomicReference.wait(5000);
                } catch (InterruptedException e2) {
                    r().i().a("Interrupted waiting for get user properties", e2);
                }
            }
            List<zzka> list = (List) atomicReference.get();
            if (list != null) {
                return list;
            }
            i = r().i();
            str = "Timed out waiting for get user properties";
        }
        i.a(str);
        return Collections.emptyList();
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final void b(long j) {
        b();
        q().a((Runnable) new dj(this, j));
    }

    public final void b(ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mAppId);
        a();
        c(new ConditionalUserProperty(conditionalUserProperty));
    }

    public final void b(c cVar) {
        b();
        w();
        Preconditions.checkNotNull(cVar);
        if (!this.d.remove(cVar)) {
            r().i().a("OnEventListener had not been registered");
        }
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public final void b(String str, String str2, Bundle bundle) {
        b();
        d();
        a(str, str2, m().currentTimeMillis(), bundle, true, this.c == null || fg.g(str2), false, null);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:10|11|12|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r().i().a("Interrupted waiting for app instance id");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001d */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String c(long r4) {
        /*
            r3 = this;
            java.util.concurrent.atomic.AtomicReference r0 = new java.util.concurrent.atomic.AtomicReference
            r0.<init>()
            monitor-enter(r0)
            com.google.android.gms.internal.measurement.bq r1 = r3.q()     // Catch:{ all -> 0x002d }
            com.google.android.gms.internal.measurement.cx r2 = new com.google.android.gms.internal.measurement.cx     // Catch:{ all -> 0x002d }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x002d }
            r1.a(r2)     // Catch:{ all -> 0x002d }
            r0.wait(r4)     // Catch:{ InterruptedException -> 0x001d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            java.lang.Object r4 = r0.get()
            java.lang.String r4 = (java.lang.String) r4
            return r4
        L_0x001d:
            com.google.android.gms.internal.measurement.aq r4 = r3.r()     // Catch:{ all -> 0x002d }
            com.google.android.gms.internal.measurement.as r4 = r4.i()     // Catch:{ all -> 0x002d }
            java.lang.String r5 = "Interrupted waiting for app instance id"
            r4.a(r5)     // Catch:{ all -> 0x002d }
            r4 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r4
        L_0x002d:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.cs.c(long):java.lang.String");
    }

    public final /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    public final void c(String str, String str2, Bundle bundle) {
        b();
        b((String) null, str, str2, bundle);
    }

    public final /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    public final /* bridge */ /* synthetic */ n e() {
        return super.e();
    }

    public final /* bridge */ /* synthetic */ cs f() {
        return super.f();
    }

    public final /* bridge */ /* synthetic */ al g() {
        return super.g();
    }

    public final /* bridge */ /* synthetic */ dq h() {
        return super.h();
    }

    public final /* bridge */ /* synthetic */ dn i() {
        return super.i();
    }

    public final /* bridge */ /* synthetic */ am j() {
        return super.j();
    }

    public final /* bridge */ /* synthetic */ eo k() {
        return super.k();
    }

    public final /* bridge */ /* synthetic */ ag l() {
        return super.l();
    }

    public final /* bridge */ /* synthetic */ Clock m() {
        return super.m();
    }

    public final /* bridge */ /* synthetic */ Context n() {
        return super.n();
    }

    public final /* bridge */ /* synthetic */ ao o() {
        return super.o();
    }

    public final /* bridge */ /* synthetic */ fg p() {
        return super.p();
    }

    public final /* bridge */ /* synthetic */ bq q() {
        return super.q();
    }

    public final /* bridge */ /* synthetic */ aq r() {
        return super.r();
    }

    public final /* bridge */ /* synthetic */ bb s() {
        return super.s();
    }

    public final /* bridge */ /* synthetic */ w t() {
        return super.t();
    }

    public final /* bridge */ /* synthetic */ v u() {
        return super.u();
    }

    /* access modifiers changed from: protected */
    public final boolean z() {
        return false;
    }
}
