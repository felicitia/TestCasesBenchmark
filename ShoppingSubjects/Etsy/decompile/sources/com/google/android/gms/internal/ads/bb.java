package com.google.android.gms.internal.ads;

@bu
public final class bb implements aq<zzoq> {
    private final boolean a;
    private final boolean b;
    private final boolean c;

    public bb(boolean z, boolean z2, boolean z3) {
        this.a = z;
        this.b = z2;
        this.c = z3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ com.google.android.gms.internal.ads.aln a(com.google.android.gms.internal.ads.ai r19, org.json.JSONObject r20) throws org.json.JSONException, java.lang.InterruptedException, java.util.concurrent.ExecutionException {
        /*
            r18 = this;
            r0 = r18
            r7 = r19
            r8 = r20
            java.lang.String r3 = "images"
            boolean r5 = r0.a
            boolean r6 = r0.b
            r4 = 0
            r1 = r7
            r2 = r8
            java.util.List r1 = r1.a(r2, r3, r4, r5, r6)
            java.lang.String r2 = "secondary_image"
            boolean r3 = r0.a
            com.google.android.gms.internal.ads.kt r2 = r7.a(r8, r2, r4, r3)
            com.google.android.gms.internal.ads.kt r3 = r19.a(r20)
            java.lang.String r4 = "video"
            com.google.android.gms.internal.ads.kt r4 = r7.a(r8, r4)
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x002e:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x0044
            java.lang.Object r5 = r1.next()
            com.google.android.gms.internal.ads.kt r5 = (com.google.android.gms.internal.ads.kt) r5
            java.lang.Object r5 = r5.get()
            com.google.android.gms.internal.ads.zzon r5 = (com.google.android.gms.internal.ads.zzon) r5
            r7.add(r5)
            goto L_0x002e
        L_0x0044:
            com.google.android.gms.internal.ads.nn r1 = com.google.android.gms.internal.ads.ai.a(r4)
            com.google.android.gms.internal.ads.zzoq r4 = new com.google.android.gms.internal.ads.zzoq
            java.lang.String r5 = "headline"
            java.lang.String r5 = r8.getString(r5)
            boolean r6 = r0.c
            if (r6 == 0) goto L_0x00a2
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r6 = com.google.android.gms.internal.ads.akl.dm
            com.google.android.gms.internal.ads.akj r9 = com.google.android.gms.internal.ads.ajh.f()
            java.lang.Object r6 = r9.a(r6)
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L_0x00a2
            com.google.android.gms.internal.ads.gf r6 = com.google.android.gms.ads.internal.ao.i()
            android.content.res.Resources r6 = r6.h()
            if (r6 == 0) goto L_0x0077
            int r9 = com.google.android.gms.ads.a.a.C0131a.s7
            java.lang.String r6 = r6.getString(r9)
            goto L_0x0079
        L_0x0077:
            java.lang.String r6 = "Test Ad"
        L_0x0079:
            if (r5 == 0) goto L_0x00a3
            r9 = 3
            java.lang.String r10 = java.lang.String.valueOf(r6)
            int r10 = r10.length()
            int r9 = r9 + r10
            java.lang.String r10 = java.lang.String.valueOf(r5)
            int r10 = r10.length()
            int r9 = r9 + r10
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r9)
            r10.append(r6)
            java.lang.String r6 = " : "
            r10.append(r6)
            r10.append(r5)
            java.lang.String r5 = r10.toString()
        L_0x00a2:
            r6 = r5
        L_0x00a3:
            java.lang.String r5 = "body"
            java.lang.String r9 = r8.getString(r5)
            java.lang.Object r2 = r2.get()
            com.google.android.gms.internal.ads.zzpw r2 = (com.google.android.gms.internal.ads.zzpw) r2
            java.lang.String r5 = "call_to_action"
            java.lang.String r10 = r8.getString(r5)
            java.lang.String r5 = "advertiser"
            java.lang.String r11 = r8.getString(r5)
            java.lang.Object r3 = r3.get()
            r12 = r3
            com.google.android.gms.internal.ads.zzoj r12 = (com.google.android.gms.internal.ads.zzoj) r12
            android.os.Bundle r13 = new android.os.Bundle
            r13.<init>()
            r3 = 0
            if (r1 == 0) goto L_0x00d0
            com.google.android.gms.internal.ads.zzarl r5 = r1.zztm()
            r14 = r5
            goto L_0x00d1
        L_0x00d0:
            r14 = r3
        L_0x00d1:
            if (r1 == 0) goto L_0x00d9
            android.view.View r1 = r1.getView()
            r15 = r1
            goto L_0x00da
        L_0x00d9:
            r15 = r3
        L_0x00da:
            r16 = 0
            r17 = 0
            r5 = r4
            r8 = r9
            r9 = r2
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.bb.a(com.google.android.gms.internal.ads.ai, org.json.JSONObject):com.google.android.gms.internal.ads.aln");
    }
}
