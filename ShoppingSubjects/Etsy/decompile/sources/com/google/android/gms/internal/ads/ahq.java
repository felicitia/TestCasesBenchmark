package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class ahq extends aam<ahq> {
    public Integer a;
    public aht b;
    private Integer c;
    private ahs d;
    private ahr[] e;
    private ahu f;
    private aid g;
    private aic h;
    private ahz i;
    private aia j;
    private aij[] k;

    public ahq() {
        this.a = null;
        this.c = null;
        this.d = null;
        this.b = null;
        this.e = ahr.b();
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = aij.b();
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.ahq a(com.google.android.gms.internal.ads.aaj r7) throws java.io.IOException {
        /*
            r6 = this;
        L_0x0000:
            int r0 = r7.a()
            r1 = 0
            switch(r0) {
                case 0: goto L_0x0142;
                case 56: goto L_0x010b;
                case 64: goto L_0x00f7;
                case 74: goto L_0x00e5;
                case 82: goto L_0x00d7;
                case 90: goto L_0x0097;
                case 98: goto L_0x0089;
                case 106: goto L_0x007b;
                case 114: goto L_0x006c;
                case 122: goto L_0x005d;
                case 130: goto L_0x004e;
                case 138: goto L_0x000f;
                default: goto L_0x0008;
            }
        L_0x0008:
            boolean r0 = super.a(r7, r0)
            if (r0 != 0) goto L_0x0000
            return r6
        L_0x000f:
            r0 = 138(0x8a, float:1.93E-43)
            int r0 = com.google.android.gms.internal.ads.aau.a(r7, r0)
            com.google.android.gms.internal.ads.aij[] r2 = r6.k
            if (r2 != 0) goto L_0x001b
            r2 = r1
            goto L_0x001e
        L_0x001b:
            com.google.android.gms.internal.ads.aij[] r2 = r6.k
            int r2 = r2.length
        L_0x001e:
            int r0 = r0 + r2
            com.google.android.gms.internal.ads.aij[] r0 = new com.google.android.gms.internal.ads.aij[r0]
            if (r2 == 0) goto L_0x0028
            com.google.android.gms.internal.ads.aij[] r3 = r6.k
            java.lang.System.arraycopy(r3, r1, r0, r1, r2)
        L_0x0028:
            int r1 = r0.length
            int r1 = r1 + -1
            if (r2 >= r1) goto L_0x003f
            com.google.android.gms.internal.ads.aij r1 = new com.google.android.gms.internal.ads.aij
            r1.<init>()
            r0[r2] = r1
            r1 = r0[r2]
            r7.a(r1)
            r7.a()
            int r2 = r2 + 1
            goto L_0x0028
        L_0x003f:
            com.google.android.gms.internal.ads.aij r1 = new com.google.android.gms.internal.ads.aij
            r1.<init>()
            r0[r2] = r1
            r1 = r0[r2]
            r7.a(r1)
            r6.k = r0
            goto L_0x0000
        L_0x004e:
            com.google.android.gms.internal.ads.aia r0 = r6.j
            if (r0 != 0) goto L_0x0059
            com.google.android.gms.internal.ads.aia r0 = new com.google.android.gms.internal.ads.aia
            r0.<init>()
            r6.j = r0
        L_0x0059:
            com.google.android.gms.internal.ads.aia r0 = r6.j
            goto L_0x00f2
        L_0x005d:
            com.google.android.gms.internal.ads.ahz r0 = r6.i
            if (r0 != 0) goto L_0x0068
            com.google.android.gms.internal.ads.ahz r0 = new com.google.android.gms.internal.ads.ahz
            r0.<init>()
            r6.i = r0
        L_0x0068:
            com.google.android.gms.internal.ads.ahz r0 = r6.i
            goto L_0x00f2
        L_0x006c:
            com.google.android.gms.internal.ads.aic r0 = r6.h
            if (r0 != 0) goto L_0x0077
            com.google.android.gms.internal.ads.aic r0 = new com.google.android.gms.internal.ads.aic
            r0.<init>()
            r6.h = r0
        L_0x0077:
            com.google.android.gms.internal.ads.aic r0 = r6.h
            goto L_0x00f2
        L_0x007b:
            com.google.android.gms.internal.ads.aid r0 = r6.g
            if (r0 != 0) goto L_0x0086
            com.google.android.gms.internal.ads.aid r0 = new com.google.android.gms.internal.ads.aid
            r0.<init>()
            r6.g = r0
        L_0x0086:
            com.google.android.gms.internal.ads.aid r0 = r6.g
            goto L_0x00f2
        L_0x0089:
            com.google.android.gms.internal.ads.ahu r0 = r6.f
            if (r0 != 0) goto L_0x0094
            com.google.android.gms.internal.ads.ahu r0 = new com.google.android.gms.internal.ads.ahu
            r0.<init>()
            r6.f = r0
        L_0x0094:
            com.google.android.gms.internal.ads.ahu r0 = r6.f
            goto L_0x00f2
        L_0x0097:
            r0 = 90
            int r0 = com.google.android.gms.internal.ads.aau.a(r7, r0)
            com.google.android.gms.internal.ads.ahr[] r2 = r6.e
            if (r2 != 0) goto L_0x00a3
            r2 = r1
            goto L_0x00a6
        L_0x00a3:
            com.google.android.gms.internal.ads.ahr[] r2 = r6.e
            int r2 = r2.length
        L_0x00a6:
            int r0 = r0 + r2
            com.google.android.gms.internal.ads.ahr[] r0 = new com.google.android.gms.internal.ads.ahr[r0]
            if (r2 == 0) goto L_0x00b0
            com.google.android.gms.internal.ads.ahr[] r3 = r6.e
            java.lang.System.arraycopy(r3, r1, r0, r1, r2)
        L_0x00b0:
            int r1 = r0.length
            int r1 = r1 + -1
            if (r2 >= r1) goto L_0x00c7
            com.google.android.gms.internal.ads.ahr r1 = new com.google.android.gms.internal.ads.ahr
            r1.<init>()
            r0[r2] = r1
            r1 = r0[r2]
            r7.a(r1)
            r7.a()
            int r2 = r2 + 1
            goto L_0x00b0
        L_0x00c7:
            com.google.android.gms.internal.ads.ahr r1 = new com.google.android.gms.internal.ads.ahr
            r1.<init>()
            r0[r2] = r1
            r1 = r0[r2]
            r7.a(r1)
            r6.e = r0
            goto L_0x0000
        L_0x00d7:
            com.google.android.gms.internal.ads.aht r0 = r6.b
            if (r0 != 0) goto L_0x00e2
            com.google.android.gms.internal.ads.aht r0 = new com.google.android.gms.internal.ads.aht
            r0.<init>()
            r6.b = r0
        L_0x00e2:
            com.google.android.gms.internal.ads.aht r0 = r6.b
            goto L_0x00f2
        L_0x00e5:
            com.google.android.gms.internal.ads.ahs r0 = r6.d
            if (r0 != 0) goto L_0x00f0
            com.google.android.gms.internal.ads.ahs r0 = new com.google.android.gms.internal.ads.ahs
            r0.<init>()
            r6.d = r0
        L_0x00f0:
            com.google.android.gms.internal.ads.ahs r0 = r6.d
        L_0x00f2:
            r7.a(r0)
            goto L_0x0000
        L_0x00f7:
            int r1 = r7.j()
            int r2 = r7.g()     // Catch:{ IllegalArgumentException -> 0x013a }
            int r2 = com.google.android.gms.internal.ads.ahp.a(r2)     // Catch:{ IllegalArgumentException -> 0x013a }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x013a }
            r6.c = r2     // Catch:{ IllegalArgumentException -> 0x013a }
            goto L_0x0000
        L_0x010b:
            int r1 = r7.j()
            int r2 = r7.g()     // Catch:{ IllegalArgumentException -> 0x013a }
            if (r2 < 0) goto L_0x0121
            r3 = 9
            if (r2 > r3) goto L_0x0121
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x013a }
            r6.a = r2     // Catch:{ IllegalArgumentException -> 0x013a }
            goto L_0x0000
        L_0x0121:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x013a }
            r4 = 43
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x013a }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x013a }
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x013a }
            java.lang.String r2 = " is not a valid enum AdInitiater"
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x013a }
            java.lang.String r2 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x013a }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x013a }
            throw r3     // Catch:{ IllegalArgumentException -> 0x013a }
        L_0x013a:
            r7.e(r1)
            r6.a(r7, r0)
            goto L_0x0000
        L_0x0142:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.ahq.a(com.google.android.gms.internal.ads.aaj):com.google.android.gms.internal.ads.ahq");
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(7, this.a.intValue());
        }
        if (this.c != null) {
            a2 += aal.b(8, this.c.intValue());
        }
        if (this.d != null) {
            a2 += aal.b(9, (aar) this.d);
        }
        if (this.b != null) {
            a2 += aal.b(10, (aar) this.b);
        }
        if (this.e != null && this.e.length > 0) {
            int i2 = a2;
            for (ahr ahr : this.e) {
                if (ahr != null) {
                    i2 += aal.b(11, (aar) ahr);
                }
            }
            a2 = i2;
        }
        if (this.f != null) {
            a2 += aal.b(12, (aar) this.f);
        }
        if (this.g != null) {
            a2 += aal.b(13, (aar) this.g);
        }
        if (this.h != null) {
            a2 += aal.b(14, (aar) this.h);
        }
        if (this.i != null) {
            a2 += aal.b(15, (aar) this.i);
        }
        if (this.j != null) {
            a2 += aal.b(16, (aar) this.j);
        }
        if (this.k != null && this.k.length > 0) {
            for (aij aij : this.k) {
                if (aij != null) {
                    a2 += aal.b(17, (aar) aij);
                }
            }
        }
        return a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(7, this.a.intValue());
        }
        if (this.c != null) {
            aal.a(8, this.c.intValue());
        }
        if (this.d != null) {
            aal.a(9, (aar) this.d);
        }
        if (this.b != null) {
            aal.a(10, (aar) this.b);
        }
        if (this.e != null && this.e.length > 0) {
            for (ahr ahr : this.e) {
                if (ahr != null) {
                    aal.a(11, (aar) ahr);
                }
            }
        }
        if (this.f != null) {
            aal.a(12, (aar) this.f);
        }
        if (this.g != null) {
            aal.a(13, (aar) this.g);
        }
        if (this.h != null) {
            aal.a(14, (aar) this.h);
        }
        if (this.i != null) {
            aal.a(15, (aar) this.i);
        }
        if (this.j != null) {
            aal.a(16, (aar) this.j);
        }
        if (this.k != null && this.k.length > 0) {
            for (aij aij : this.k) {
                if (aij != null) {
                    aal.a(17, (aar) aij);
                }
            }
        }
        super.a(aal);
    }
}
