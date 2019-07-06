package androidx.work.impl.utils;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import androidx.work.Data.a;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.b.j;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.d;
import androidx.work.impl.e;
import androidx.work.impl.f;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: EnqueueRunnable */
public class b implements Runnable {
    private final e a;

    public b(@NonNull e eVar) {
        this.a = eVar;
    }

    public void run() {
        if (this.a.j()) {
            throw new IllegalStateException(String.format("WorkContinuation has cycles (%s)", new Object[]{this.a}));
        } else if (a()) {
            d.a(this.a.a().c(), RescheduleReceiver.class, true);
            b();
        }
    }

    @VisibleForTesting
    public boolean a() {
        WorkDatabase d = this.a.a().d();
        d.beginTransaction();
        try {
            boolean a2 = a(this.a);
            d.setTransactionSuccessful();
            return a2;
        } finally {
            d.endTransaction();
        }
    }

    @VisibleForTesting
    public void b() {
        f a2 = this.a.a();
        d.a(a2.e(), a2.d(), a2.f());
    }

    private static boolean a(@NonNull e eVar) {
        List<e> h = eVar.h();
        boolean z = false;
        if (h != null) {
            boolean z2 = false;
            for (e eVar2 : h) {
                if (!eVar2.f()) {
                    z2 |= a(eVar2);
                } else {
                    androidx.work.e.d("EnqueueRunnable", String.format("Already enqueued work ids (%s).", new Object[]{TextUtils.join(", ", eVar2.e())}), new Throwable[0]);
                }
            }
            z = z2;
        }
        return b(eVar) | z;
    }

    private static boolean b(@NonNull e eVar) {
        boolean a2 = a(eVar.a(), eVar.d(), (String[]) e.a(eVar).toArray(new String[0]), eVar.b(), eVar.c());
        eVar.g();
        return a2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:75:0x011a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(androidx.work.impl.f r18, @android.support.annotation.NonNull java.util.List<? extends androidx.work.k> r19, java.lang.String[] r20, java.lang.String r21, androidx.work.ExistingWorkPolicy r22) {
        /*
            r0 = r20
            r1 = r21
            r2 = r22
            long r3 = java.lang.System.currentTimeMillis()
            androidx.work.impl.WorkDatabase r5 = r18.d()
            r6 = 0
            r7 = 1
            if (r0 == 0) goto L_0x0017
            int r8 = r0.length
            if (r8 <= 0) goto L_0x0017
            r8 = r7
            goto L_0x0018
        L_0x0017:
            r8 = r6
        L_0x0018:
            if (r8 == 0) goto L_0x0057
            int r9 = r0.length
            r10 = r6
            r12 = r10
            r13 = r12
            r11 = r7
        L_0x001f:
            if (r10 >= r9) goto L_0x005a
            r14 = r0[r10]
            androidx.work.impl.b.k r15 = r5.workSpecDao()
            androidx.work.impl.b.j r15 = r15.b(r14)
            if (r15 != 0) goto L_0x003f
            java.lang.String r0 = "EnqueueRunnable"
            java.lang.String r1 = "Prerequisite %s doesn't exist; not enqueuing"
            java.lang.Object[] r2 = new java.lang.Object[r7]
            r2[r6] = r14
            java.lang.String r1 = java.lang.String.format(r1, r2)
            java.lang.Throwable[] r2 = new java.lang.Throwable[r6]
            androidx.work.e.e(r0, r1, r2)
            return r6
        L_0x003f:
            androidx.work.State r14 = r15.b
            androidx.work.State r15 = androidx.work.State.SUCCEEDED
            if (r14 != r15) goto L_0x0047
            r15 = r7
            goto L_0x0048
        L_0x0047:
            r15 = r6
        L_0x0048:
            r11 = r11 & r15
            androidx.work.State r15 = androidx.work.State.FAILED
            if (r14 != r15) goto L_0x004f
            r12 = r7
            goto L_0x0054
        L_0x004f:
            androidx.work.State r15 = androidx.work.State.CANCELLED
            if (r14 != r15) goto L_0x0054
            r13 = r7
        L_0x0054:
            int r10 = r10 + 1
            goto L_0x001f
        L_0x0057:
            r12 = r6
            r13 = r12
            r11 = r7
        L_0x005a:
            boolean r9 = android.text.TextUtils.isEmpty(r21)
            r9 = r9 ^ r7
            if (r9 == 0) goto L_0x0065
            if (r8 != 0) goto L_0x0065
            r10 = r7
            goto L_0x0066
        L_0x0065:
            r10 = r6
        L_0x0066:
            if (r10 == 0) goto L_0x010f
            androidx.work.impl.b.k r10 = r5.workSpecDao()
            java.util.List r10 = r10.c(r1)
            boolean r14 = r10.isEmpty()
            if (r14 != 0) goto L_0x010f
            androidx.work.ExistingWorkPolicy r14 = androidx.work.ExistingWorkPolicy.APPEND
            if (r2 != r14) goto L_0x00c9
            androidx.work.impl.b.b r2 = r5.dependencyDao()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.Iterator r10 = r10.iterator()
        L_0x0087:
            boolean r14 = r10.hasNext()
            if (r14 == 0) goto L_0x00bc
            java.lang.Object r14 = r10.next()
            androidx.work.impl.b.j$a r14 = (androidx.work.impl.b.j.a) r14
            java.lang.String r15 = r14.a
            boolean r15 = r2.c(r15)
            if (r15 != 0) goto L_0x00ba
            androidx.work.State r15 = r14.b
            androidx.work.State r7 = androidx.work.State.SUCCEEDED
            if (r15 != r7) goto L_0x00a3
            r7 = 1
            goto L_0x00a4
        L_0x00a3:
            r7 = r6
        L_0x00a4:
            r7 = r7 & r11
            androidx.work.State r11 = r14.b
            androidx.work.State r15 = androidx.work.State.FAILED
            if (r11 != r15) goto L_0x00ad
            r12 = 1
            goto L_0x00b4
        L_0x00ad:
            androidx.work.State r11 = r14.b
            androidx.work.State r15 = androidx.work.State.CANCELLED
            if (r11 != r15) goto L_0x00b4
            r13 = 1
        L_0x00b4:
            java.lang.String r11 = r14.a
            r8.add(r11)
            r11 = r7
        L_0x00ba:
            r7 = 1
            goto L_0x0087
        L_0x00bc:
            java.lang.Object[] r0 = r8.toArray(r0)
            java.lang.String[] r0 = (java.lang.String[]) r0
            int r2 = r0.length
            if (r2 <= 0) goto L_0x00c7
            r8 = 1
            goto L_0x010f
        L_0x00c7:
            r8 = r6
            goto L_0x010f
        L_0x00c9:
            androidx.work.ExistingWorkPolicy r7 = androidx.work.ExistingWorkPolicy.KEEP
            if (r2 != r7) goto L_0x00ea
            java.util.Iterator r2 = r10.iterator()
        L_0x00d1:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x00ea
            java.lang.Object r7 = r2.next()
            androidx.work.impl.b.j$a r7 = (androidx.work.impl.b.j.a) r7
            androidx.work.State r14 = r7.b
            androidx.work.State r15 = androidx.work.State.ENQUEUED
            if (r14 == r15) goto L_0x00e9
            androidx.work.State r7 = r7.b
            androidx.work.State r14 = androidx.work.State.RUNNING
            if (r7 != r14) goto L_0x00d1
        L_0x00e9:
            return r6
        L_0x00ea:
            r2 = r18
            java.lang.Runnable r2 = androidx.work.impl.utils.a.a(r1, r2, r6)
            r2.run()
            androidx.work.impl.b.k r2 = r5.workSpecDao()
            java.util.Iterator r7 = r10.iterator()
        L_0x00fb:
            boolean r10 = r7.hasNext()
            if (r10 == 0) goto L_0x010d
            java.lang.Object r10 = r7.next()
            androidx.work.impl.b.j$a r10 = (androidx.work.impl.b.j.a) r10
            java.lang.String r10 = r10.a
            r2.a(r10)
            goto L_0x00fb
        L_0x010d:
            r2 = 1
            goto L_0x0110
        L_0x010f:
            r2 = r6
        L_0x0110:
            java.util.Iterator r7 = r19.iterator()
        L_0x0114:
            boolean r10 = r7.hasNext()
            if (r10 == 0) goto L_0x01bf
            java.lang.Object r10 = r7.next()
            androidx.work.k r10 = (androidx.work.k) r10
            androidx.work.impl.b.j r14 = r10.b()
            if (r8 == 0) goto L_0x013b
            if (r11 != 0) goto L_0x013b
            if (r12 == 0) goto L_0x012f
            androidx.work.State r15 = androidx.work.State.FAILED
            r14.b = r15
            goto L_0x013d
        L_0x012f:
            if (r13 == 0) goto L_0x0136
            androidx.work.State r15 = androidx.work.State.CANCELLED
            r14.b = r15
            goto L_0x013d
        L_0x0136:
            androidx.work.State r15 = androidx.work.State.BLOCKED
            r14.b = r15
            goto L_0x013d
        L_0x013b:
            r14.n = r3
        L_0x013d:
            int r15 = android.os.Build.VERSION.SDK_INT
            r6 = 23
            if (r15 < r6) goto L_0x014c
            int r6 = android.os.Build.VERSION.SDK_INT
            r15 = 25
            if (r6 > r15) goto L_0x014c
            a(r14)
        L_0x014c:
            androidx.work.State r6 = r14.b
            androidx.work.State r15 = androidx.work.State.ENQUEUED
            if (r6 != r15) goto L_0x0153
            r2 = 1
        L_0x0153:
            androidx.work.impl.b.k r6 = r5.workSpecDao()
            r6.a(r14)
            if (r8 == 0) goto L_0x017d
            int r6 = r0.length
            r14 = 0
        L_0x015e:
            if (r14 >= r6) goto L_0x017d
            r15 = r0[r14]
            r16 = r0
            androidx.work.impl.b.a r0 = new androidx.work.impl.b.a
            r17 = r2
            java.lang.String r2 = r10.a()
            r0.<init>(r2, r15)
            androidx.work.impl.b.b r2 = r5.dependencyDao()
            r2.a(r0)
            int r14 = r14 + 1
            r0 = r16
            r2 = r17
            goto L_0x015e
        L_0x017d:
            r16 = r0
            r17 = r2
            java.util.Set r0 = r10.c()
            java.util.Iterator r0 = r0.iterator()
        L_0x0189:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x01a6
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            androidx.work.impl.b.n r6 = r5.workTagDao()
            androidx.work.impl.b.m r14 = new androidx.work.impl.b.m
            java.lang.String r15 = r10.a()
            r14.<init>(r2, r15)
            r6.a(r14)
            goto L_0x0189
        L_0x01a6:
            if (r9 == 0) goto L_0x01b8
            androidx.work.impl.b.h r0 = r5.workNameDao()
            androidx.work.impl.b.g r2 = new androidx.work.impl.b.g
            java.lang.String r6 = r10.a()
            r2.<init>(r1, r6)
            r0.a(r2)
        L_0x01b8:
            r0 = r16
            r2 = r17
            r6 = 0
            goto L_0x0114
        L_0x01bf:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.utils.b.a(androidx.work.impl.f, java.util.List, java.lang.String[], java.lang.String, androidx.work.ExistingWorkPolicy):boolean");
    }

    private static void a(j jVar) {
        androidx.work.b bVar = jVar.j;
        if (bVar.d() || bVar.e()) {
            String str = jVar.c;
            a aVar = new a();
            aVar.a(jVar.e).a("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME", str);
            jVar.c = ConstraintTrackingWorker.class.getName();
            jVar.e = aVar.a();
        }
    }
}
