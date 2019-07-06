package com.google.android.gms.internal.ads;

@bu
public final class ba implements aq<zzoo> {
    private final boolean a;
    private final boolean b;
    private final boolean c;

    public ba(boolean z, boolean z2, boolean z3) {
        this.a = z;
        this.b = z2;
        this.c = z3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00eb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ com.google.android.gms.internal.ads.aln a(com.google.android.gms.internal.ads.ai r22, org.json.JSONObject r23) throws org.json.JSONException, java.lang.InterruptedException, java.util.concurrent.ExecutionException {
        /*
            r21 = this;
            r0 = r21
            r7 = r22
            r8 = r23
            java.lang.String r3 = "images"
            boolean r5 = r0.a
            boolean r6 = r0.b
            r4 = 0
            r1 = r7
            r2 = r8
            java.util.List r1 = r1.a(r2, r3, r4, r5, r6)
            java.lang.String r2 = "app_icon"
            boolean r3 = r0.a
            r4 = 1
            com.google.android.gms.internal.ads.kt r2 = r7.a(r8, r2, r4, r3)
            java.lang.String r3 = "video"
            com.google.android.gms.internal.ads.kt r3 = r7.a(r8, r3)
            com.google.android.gms.internal.ads.kt r4 = r22.a(r23)
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x002f:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x0045
            java.lang.Object r5 = r1.next()
            com.google.android.gms.internal.ads.kt r5 = (com.google.android.gms.internal.ads.kt) r5
            java.lang.Object r5 = r5.get()
            com.google.android.gms.internal.ads.zzon r5 = (com.google.android.gms.internal.ads.zzon) r5
            r7.add(r5)
            goto L_0x002f
        L_0x0045:
            com.google.android.gms.internal.ads.nn r1 = com.google.android.gms.internal.ads.ai.a(r3)
            com.google.android.gms.internal.ads.zzoo r3 = new com.google.android.gms.internal.ads.zzoo
            java.lang.String r5 = "headline"
            java.lang.String r5 = r8.getString(r5)
            boolean r6 = r0.c
            if (r6 == 0) goto L_0x00a3
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r6 = com.google.android.gms.internal.ads.akl.dm
            com.google.android.gms.internal.ads.akj r9 = com.google.android.gms.internal.ads.ajh.f()
            java.lang.Object r6 = r9.a(r6)
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L_0x00a3
            com.google.android.gms.internal.ads.gf r6 = com.google.android.gms.ads.internal.ao.i()
            android.content.res.Resources r6 = r6.h()
            if (r6 == 0) goto L_0x0078
            int r9 = com.google.android.gms.ads.a.a.C0131a.s7
            java.lang.String r6 = r6.getString(r9)
            goto L_0x007a
        L_0x0078:
            java.lang.String r6 = "Test Ad"
        L_0x007a:
            if (r5 == 0) goto L_0x00a4
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
        L_0x00a3:
            r6 = r5
        L_0x00a4:
            java.lang.String r5 = "body"
            java.lang.String r9 = r8.getString(r5)
            java.lang.Object r2 = r2.get()
            com.google.android.gms.internal.ads.zzpw r2 = (com.google.android.gms.internal.ads.zzpw) r2
            java.lang.String r5 = "call_to_action"
            java.lang.String r10 = r8.getString(r5)
            java.lang.String r5 = "rating"
            r11 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            double r11 = r8.optDouble(r5, r11)
            java.lang.String r5 = "store"
            java.lang.String r13 = r8.optString(r5)
            java.lang.String r5 = "price"
            java.lang.String r14 = r8.optString(r5)
            java.lang.Object r4 = r4.get()
            r15 = r4
            com.google.android.gms.internal.ads.zzoj r15 = (com.google.android.gms.internal.ads.zzoj) r15
            android.os.Bundle r4 = new android.os.Bundle
            r4.<init>()
            r5 = 0
            if (r1 == 0) goto L_0x00e0
            com.google.android.gms.internal.ads.zzarl r8 = r1.zztm()
            r17 = r8
            goto L_0x00e2
        L_0x00e0:
            r17 = r5
        L_0x00e2:
            if (r1 == 0) goto L_0x00eb
            android.view.View r1 = r1.getView()
            r18 = r1
            goto L_0x00ed
        L_0x00eb:
            r18 = r5
        L_0x00ed:
            r19 = 0
            r20 = 0
            r5 = r3
            r8 = r9
            r9 = r2
            r16 = r4
            r5.<init>(r6, r7, r8, r9, r10, r11, r13, r14, r15, r16, r17, r18, r19, r20)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.ba.a(com.google.android.gms.internal.ads.ai, org.json.JSONObject):com.google.android.gms.internal.ads.aln");
    }
}
