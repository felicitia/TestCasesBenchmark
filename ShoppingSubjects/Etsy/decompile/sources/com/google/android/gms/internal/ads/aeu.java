package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class aeu implements ana {
    private final Map<String, List<amf<?>>> a = new HashMap();
    private final acz b;

    aeu(acz acz) {
        this.b = acz;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean b(com.google.android.gms.internal.ads.amf<?> r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = r6.e()     // Catch:{ all -> 0x0052 }
            java.util.Map<java.lang.String, java.util.List<com.google.android.gms.internal.ads.amf<?>>> r1 = r5.a     // Catch:{ all -> 0x0052 }
            boolean r1 = r1.containsKey(r0)     // Catch:{ all -> 0x0052 }
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x003a
            java.util.Map<java.lang.String, java.util.List<com.google.android.gms.internal.ads.amf<?>>> r1 = r5.a     // Catch:{ all -> 0x0052 }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x0052 }
            java.util.List r1 = (java.util.List) r1     // Catch:{ all -> 0x0052 }
            if (r1 != 0) goto L_0x001e
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0052 }
            r1.<init>()     // Catch:{ all -> 0x0052 }
        L_0x001e:
            java.lang.String r4 = "waiting-for-response"
            r6.b(r4)     // Catch:{ all -> 0x0052 }
            r1.add(r6)     // Catch:{ all -> 0x0052 }
            java.util.Map<java.lang.String, java.util.List<com.google.android.gms.internal.ads.amf<?>>> r6 = r5.a     // Catch:{ all -> 0x0052 }
            r6.put(r0, r1)     // Catch:{ all -> 0x0052 }
            boolean r6 = com.google.android.gms.internal.ads.ct.a     // Catch:{ all -> 0x0052 }
            if (r6 == 0) goto L_0x0038
            java.lang.String r6 = "Request for cacheKey=%s is in flight, putting on hold."
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch:{ all -> 0x0052 }
            r1[r2] = r0     // Catch:{ all -> 0x0052 }
            com.google.android.gms.internal.ads.ct.b(r6, r1)     // Catch:{ all -> 0x0052 }
        L_0x0038:
            monitor-exit(r5)
            return r3
        L_0x003a:
            java.util.Map<java.lang.String, java.util.List<com.google.android.gms.internal.ads.amf<?>>> r1 = r5.a     // Catch:{ all -> 0x0052 }
            r4 = 0
            r1.put(r0, r4)     // Catch:{ all -> 0x0052 }
            r6.a(r5)     // Catch:{ all -> 0x0052 }
            boolean r6 = com.google.android.gms.internal.ads.ct.a     // Catch:{ all -> 0x0052 }
            if (r6 == 0) goto L_0x0050
            java.lang.String r6 = "new request, sending to network %s"
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch:{ all -> 0x0052 }
            r1[r2] = r0     // Catch:{ all -> 0x0052 }
            com.google.android.gms.internal.ads.ct.b(r6, r1)     // Catch:{ all -> 0x0052 }
        L_0x0050:
            monitor-exit(r5)
            return r2
        L_0x0052:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.aeu.b(com.google.android.gms.internal.ads.amf):boolean");
    }

    public final synchronized void a(amf<?> amf) {
        String e = amf.e();
        List list = (List) this.a.remove(e);
        if (list != null && !list.isEmpty()) {
            if (ct.a) {
                ct.a("%d waiting requests for cacheKey=%s; resend to network", Integer.valueOf(list.size()), e);
            }
            amf amf2 = (amf) list.remove(0);
            this.a.put(e, list);
            amf2.a((ana) this);
            try {
                this.b.c.put(amf2);
            } catch (InterruptedException e2) {
                ct.c("Couldn't add request to queue. %s", e2.toString());
                Thread.currentThread().interrupt();
                this.b.a();
            }
        }
    }

    public final void a(amf<?> amf, arb<?> arb) {
        List<amf> list;
        if (arb.b == null || arb.b.a()) {
            a(amf);
            return;
        }
        String e = amf.e();
        synchronized (this) {
            list = (List) this.a.remove(e);
        }
        if (list != null) {
            if (ct.a) {
                ct.a("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(list.size()), e);
            }
            for (amf a2 : list) {
                this.b.e.a(a2, arb);
            }
        }
    }
}
