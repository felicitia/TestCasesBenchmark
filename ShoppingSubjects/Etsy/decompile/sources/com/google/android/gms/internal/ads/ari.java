package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.ao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@bu
public final class ari implements aqx {
    private final zzaef a;
    private final zzxn b;
    private final Context c;
    private final aqz d;
    private final boolean e;
    /* access modifiers changed from: private */
    public final long f;
    /* access modifiers changed from: private */
    public final long g;
    private final int h;
    /* access modifiers changed from: private */
    public final Object i = new Object();
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public final Map<kt<arf>, arc> k = new HashMap();
    private final boolean l;
    private final String m;
    private List<arf> n = new ArrayList();
    private final boolean o;

    public ari(Context context, zzaef zzaef, zzxn zzxn, aqz aqz, boolean z, boolean z2, String str, long j2, long j3, int i2, boolean z3) {
        this.c = context;
        this.a = zzaef;
        this.b = zzxn;
        this.d = aqz;
        this.e = z;
        this.l = z2;
        this.m = str;
        this.f = j2;
        this.g = j3;
        this.h = 2;
        this.o = z3;
    }

    private final void a(kt<arf> ktVar) {
        hd.a.post(new ark(this, ktVar));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        if (r4.hasNext() == false) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r0 = (com.google.android.gms.internal.ads.kt) r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = (com.google.android.gms.internal.ads.arf) r0.get();
        r3.n.add(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r1 == null) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        if (r1.a != 0) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        a(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0035, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
        com.google.android.gms.internal.ads.gv.c("Exception while processing an adapter; continuing with other adapters", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003c, code lost:
        a(null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0046, code lost:
        return new com.google.android.gms.internal.ads.arf(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r4 = r4.iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.arf b(java.util.List<com.google.android.gms.internal.ads.kt<com.google.android.gms.internal.ads.arf>> r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.i
            monitor-enter(r0)
            boolean r1 = r3.j     // Catch:{ all -> 0x0047 }
            if (r1 == 0) goto L_0x000f
            com.google.android.gms.internal.ads.arf r4 = new com.google.android.gms.internal.ads.arf     // Catch:{ all -> 0x0047 }
            r1 = -1
            r4.<init>(r1)     // Catch:{ all -> 0x0047 }
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            return r4
        L_0x000f:
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            java.util.Iterator r4 = r4.iterator()
        L_0x0014:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x003c
            java.lang.Object r0 = r4.next()
            com.google.android.gms.internal.ads.kt r0 = (com.google.android.gms.internal.ads.kt) r0
            java.lang.Object r1 = r0.get()     // Catch:{ InterruptedException | ExecutionException -> 0x0035 }
            com.google.android.gms.internal.ads.arf r1 = (com.google.android.gms.internal.ads.arf) r1     // Catch:{ InterruptedException | ExecutionException -> 0x0035 }
            java.util.List<com.google.android.gms.internal.ads.arf> r2 = r3.n     // Catch:{ InterruptedException | ExecutionException -> 0x0035 }
            r2.add(r1)     // Catch:{ InterruptedException | ExecutionException -> 0x0035 }
            if (r1 == 0) goto L_0x0014
            int r2 = r1.a     // Catch:{ InterruptedException | ExecutionException -> 0x0035 }
            if (r2 != 0) goto L_0x0014
            r3.a(r0)     // Catch:{ InterruptedException | ExecutionException -> 0x0035 }
            return r1
        L_0x0035:
            r0 = move-exception
            java.lang.String r1 = "Exception while processing an adapter; continuing with other adapters"
            com.google.android.gms.internal.ads.gv.c(r1, r0)
            goto L_0x0014
        L_0x003c:
            r4 = 0
            r3.a(r4)
            com.google.android.gms.internal.ads.arf r4 = new com.google.android.gms.internal.ads.arf
            r0 = 1
            r4.<init>(r0)
            return r4
        L_0x0047:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.ari.b(java.util.List):com.google.android.gms.internal.ads.arf");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r14.d.n == -1) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        r0 = r14.d.n;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        r0 = 10000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        r15 = r15.iterator();
        r3 = null;
        r4 = -1;
        r1 = r0;
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        if (r15.hasNext() == false) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        r5 = (com.google.android.gms.internal.ads.kt) r15.next();
        r6 = com.google.android.gms.ads.internal.ao.l().currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0041, code lost:
        if (r1 != 0) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        if (r5.isDone() == false) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        r10 = r5.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004d, code lost:
        r10 = (com.google.android.gms.internal.ads.arf) r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0050, code lost:
        r15 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0052, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0054, code lost:
        r10 = r5.get(r1, java.util.concurrent.TimeUnit.MILLISECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005b, code lost:
        r14.n.add(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0060, code lost:
        if (r10 == null) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0064, code lost:
        if (r10.a != 0) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0066, code lost:
        r11 = r10.f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0068, code lost:
        if (r11 == null) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006e, code lost:
        if (r11.zzmm() <= r4) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0074, code lost:
        r3 = r5;
        r0 = r10;
        r4 = r11.zzmm();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        com.google.android.gms.internal.ads.gv.c("Exception while processing an adapter; continuing with other adapters", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x008e, code lost:
        java.lang.Math.max(r1 - (com.google.android.gms.ads.internal.ao.l().currentTimeMillis() - r6), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009d, code lost:
        throw r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x009e, code lost:
        a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a1, code lost:
        if (r0 != null) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a9, code lost:
        return new com.google.android.gms.internal.ads.arf(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00aa, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.arf c(java.util.List<com.google.android.gms.internal.ads.kt<com.google.android.gms.internal.ads.arf>> r15) {
        /*
            r14 = this;
            java.lang.Object r0 = r14.i
            monitor-enter(r0)
            boolean r1 = r14.j     // Catch:{ all -> 0x00ab }
            r2 = -1
            if (r1 == 0) goto L_0x000f
            com.google.android.gms.internal.ads.arf r15 = new com.google.android.gms.internal.ads.arf     // Catch:{ all -> 0x00ab }
            r15.<init>(r2)     // Catch:{ all -> 0x00ab }
            monitor-exit(r0)     // Catch:{ all -> 0x00ab }
            return r15
        L_0x000f:
            monitor-exit(r0)     // Catch:{ all -> 0x00ab }
            com.google.android.gms.internal.ads.aqz r0 = r14.d
            long r0 = r0.n
            r3 = -1
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x001f
            com.google.android.gms.internal.ads.aqz r0 = r14.d
            long r0 = r0.n
            goto L_0x0021
        L_0x001f:
            r0 = 10000(0x2710, double:4.9407E-320)
        L_0x0021:
            java.util.Iterator r15 = r15.iterator()
            r3 = 0
            r4 = r2
            r1 = r0
            r0 = r3
        L_0x0029:
            boolean r5 = r15.hasNext()
            if (r5 == 0) goto L_0x009e
            java.lang.Object r5 = r15.next()
            com.google.android.gms.internal.ads.kt r5 = (com.google.android.gms.internal.ads.kt) r5
            com.google.android.gms.common.util.Clock r6 = com.google.android.gms.ads.internal.ao.l()
            long r6 = r6.currentTimeMillis()
            r8 = 0
            int r10 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x0054
            boolean r10 = r5.isDone()     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            if (r10 == 0) goto L_0x0054
            java.lang.Object r10 = r5.get()     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
        L_0x004d:
            com.google.android.gms.internal.ads.arf r10 = (com.google.android.gms.internal.ads.arf) r10     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            goto L_0x005b
        L_0x0050:
            r15 = move-exception
            goto L_0x008e
        L_0x0052:
            r5 = move-exception
            goto L_0x0078
        L_0x0054:
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            java.lang.Object r10 = r5.get(r1, r10)     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            goto L_0x004d
        L_0x005b:
            java.util.List<com.google.android.gms.internal.ads.arf> r11 = r14.n     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            r11.add(r10)     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            if (r10 == 0) goto L_0x007d
            int r11 = r10.a     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            if (r11 != 0) goto L_0x007d
            com.google.android.gms.internal.ads.zzxw r11 = r10.f     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            if (r11 == 0) goto L_0x007d
            int r12 = r11.zzmm()     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            if (r12 <= r4) goto L_0x007d
            int r11 = r11.zzmm()     // Catch:{ RemoteException | InterruptedException | ExecutionException | TimeoutException -> 0x0052 }
            r3 = r5
            r0 = r10
            r4 = r11
            goto L_0x007d
        L_0x0078:
            java.lang.String r10 = "Exception while processing an adapter; continuing with other adapters"
            com.google.android.gms.internal.ads.gv.c(r10, r5)     // Catch:{ all -> 0x0050 }
        L_0x007d:
            com.google.android.gms.common.util.Clock r5 = com.google.android.gms.ads.internal.ao.l()
            long r10 = r5.currentTimeMillis()
            long r12 = r10 - r6
            long r5 = r1 - r12
            long r1 = java.lang.Math.max(r5, r8)
            goto L_0x0029
        L_0x008e:
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.ao.l()
            long r3 = r0.currentTimeMillis()
            long r10 = r3 - r6
            long r3 = r1 - r10
            java.lang.Math.max(r3, r8)
            throw r15
        L_0x009e:
            r14.a(r3)
            if (r0 != 0) goto L_0x00aa
            com.google.android.gms.internal.ads.arf r15 = new com.google.android.gms.internal.ads.arf
            r0 = 1
            r15.<init>(r0)
            return r15
        L_0x00aa:
            return r0
        L_0x00ab:
            r15 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ab }
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.ari.c(java.util.List):com.google.android.gms.internal.ads.arf");
    }

    public final arf a(List<aqy> list) {
        gv.b("Starting mediation.");
        ArrayList arrayList = new ArrayList();
        zzjn zzjn = this.a.zzacv;
        int[] iArr = new int[2];
        if (zzjn.zzard != null) {
            ao.x();
            if (arh.a(this.m, iArr)) {
                int i2 = 0;
                int i3 = iArr[0];
                int i4 = iArr[1];
                zzjn[] zzjnArr = zzjn.zzard;
                int length = zzjnArr.length;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    zzjn zzjn2 = zzjnArr[i2];
                    if (i3 == zzjn2.width && i4 == zzjn2.height) {
                        zzjn = zzjn2;
                        break;
                    }
                    i2++;
                }
            }
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            aqy aqy = (aqy) it.next();
            String str = "Trying mediation network: ";
            String valueOf = String.valueOf(aqy.b);
            gv.d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            Iterator it2 = aqy.c.iterator();
            while (it2.hasNext()) {
                String str2 = (String) it2.next();
                Context context = this.c;
                zzxn zzxn = this.b;
                aqz aqz = this.d;
                zzjj zzjj = this.a.zzccv;
                zzang zzang = this.a.zzacr;
                boolean z = this.e;
                Iterator it3 = it;
                Iterator it4 = it2;
                ArrayList arrayList2 = arrayList;
                boolean z2 = z;
                aqy aqy2 = aqy;
                zzjn zzjn3 = zzjn;
                zzang zzang2 = zzang;
                arc arc = new arc(context, str2, zzxn, aqz, aqy2, zzjj, zzjn3, zzang2, z2, this.l, this.a.zzadj, this.a.zzads, this.a.zzcdk, this.a.zzcef, this.o);
                kt a2 = hb.a((Callable<T>) new arj<T>(this, arc));
                this.k.put(a2, arc);
                ArrayList arrayList3 = arrayList2;
                arrayList3.add(a2);
                arrayList = arrayList3;
                it = it3;
                it2 = it4;
            }
        }
        ArrayList arrayList4 = arrayList;
        return this.h != 2 ? b((List<kt<arf>>) arrayList4) : c((List<kt<arf>>) arrayList4);
    }

    public final void a() {
        synchronized (this.i) {
            this.j = true;
            for (arc a2 : this.k.values()) {
                a2.a();
            }
        }
    }

    public final List<arf> b() {
        return this.n;
    }
}
