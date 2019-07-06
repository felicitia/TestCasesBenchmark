package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aav extends aam<aav> {
    public Integer a;
    public String b;
    public String c;
    public aaw d;
    public abd[] e;
    public String f;
    public abc g;
    public abe h;
    public String[] i;
    public String[] j;
    private Integer k;
    private String l;
    private Boolean m;
    private String[] n;
    private String o;
    private Boolean p;
    private Boolean q;
    private byte[] r;

    public aav() {
        this.a = null;
        this.k = null;
        this.b = null;
        this.c = null;
        this.l = null;
        this.d = null;
        this.e = abd.b();
        this.f = null;
        this.g = null;
        this.m = null;
        this.n = aau.c;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.h = null;
        this.i = aau.c;
        this.j = aau.c;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e0, code lost:
        throw new java.lang.IllegalArgumentException(r5.toString());
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.aav a(com.google.android.gms.internal.ads.aaj r7) throws java.io.IOException {
        /*
            r6 = this;
        L_0x0000:
            int r0 = r7.a()
            r1 = 0
            switch(r0) {
                case 0: goto L_0x01d0;
                case 10: goto L_0x01c8;
                case 18: goto L_0x01c0;
                case 26: goto L_0x01b8;
                case 34: goto L_0x0178;
                case 40: goto L_0x016c;
                case 50: goto L_0x0138;
                case 58: goto L_0x0130;
                case 64: goto L_0x0124;
                case 72: goto L_0x0118;
                case 80: goto L_0x00e1;
                case 88: goto L_0x00b3;
                case 98: goto L_0x00a1;
                case 106: goto L_0x0099;
                case 114: goto L_0x008b;
                case 122: goto L_0x0083;
                case 138: goto L_0x0075;
                case 162: goto L_0x0042;
                case 170: goto L_0x000f;
                default: goto L_0x0008;
            }
        L_0x0008:
            boolean r0 = super.a(r7, r0)
            if (r0 != 0) goto L_0x0000
            return r6
        L_0x000f:
            r0 = 170(0xaa, float:2.38E-43)
            int r0 = com.google.android.gms.internal.ads.aau.a(r7, r0)
            java.lang.String[] r2 = r6.j
            if (r2 != 0) goto L_0x001b
            r2 = r1
            goto L_0x001e
        L_0x001b:
            java.lang.String[] r2 = r6.j
            int r2 = r2.length
        L_0x001e:
            int r0 = r0 + r2
            java.lang.String[] r0 = new java.lang.String[r0]
            if (r2 == 0) goto L_0x0028
            java.lang.String[] r3 = r6.j
            java.lang.System.arraycopy(r3, r1, r0, r1, r2)
        L_0x0028:
            int r1 = r0.length
            int r1 = r1 + -1
            if (r2 >= r1) goto L_0x0039
            java.lang.String r1 = r7.e()
            r0[r2] = r1
            r7.a()
            int r2 = r2 + 1
            goto L_0x0028
        L_0x0039:
            java.lang.String r1 = r7.e()
            r0[r2] = r1
            r6.j = r0
            goto L_0x0000
        L_0x0042:
            r0 = 162(0xa2, float:2.27E-43)
            int r0 = com.google.android.gms.internal.ads.aau.a(r7, r0)
            java.lang.String[] r2 = r6.i
            if (r2 != 0) goto L_0x004e
            r2 = r1
            goto L_0x0051
        L_0x004e:
            java.lang.String[] r2 = r6.i
            int r2 = r2.length
        L_0x0051:
            int r0 = r0 + r2
            java.lang.String[] r0 = new java.lang.String[r0]
            if (r2 == 0) goto L_0x005b
            java.lang.String[] r3 = r6.i
            java.lang.System.arraycopy(r3, r1, r0, r1, r2)
        L_0x005b:
            int r1 = r0.length
            int r1 = r1 + -1
            if (r2 >= r1) goto L_0x006c
            java.lang.String r1 = r7.e()
            r0[r2] = r1
            r7.a()
            int r2 = r2 + 1
            goto L_0x005b
        L_0x006c:
            java.lang.String r1 = r7.e()
            r0[r2] = r1
            r6.i = r0
            goto L_0x0000
        L_0x0075:
            com.google.android.gms.internal.ads.abe r0 = r6.h
            if (r0 != 0) goto L_0x0080
            com.google.android.gms.internal.ads.abe r0 = new com.google.android.gms.internal.ads.abe
            r0.<init>()
            r6.h = r0
        L_0x0080:
            com.google.android.gms.internal.ads.abe r0 = r6.h
            goto L_0x00ae
        L_0x0083:
            byte[] r0 = r7.f()
            r6.r = r0
            goto L_0x0000
        L_0x008b:
            com.google.android.gms.internal.ads.abc r0 = r6.g
            if (r0 != 0) goto L_0x0096
            com.google.android.gms.internal.ads.abc r0 = new com.google.android.gms.internal.ads.abc
            r0.<init>()
            r6.g = r0
        L_0x0096:
            com.google.android.gms.internal.ads.abc r0 = r6.g
            goto L_0x00ae
        L_0x0099:
            java.lang.String r0 = r7.e()
            r6.f = r0
            goto L_0x0000
        L_0x00a1:
            com.google.android.gms.internal.ads.aaw r0 = r6.d
            if (r0 != 0) goto L_0x00ac
            com.google.android.gms.internal.ads.aaw r0 = new com.google.android.gms.internal.ads.aaw
            r0.<init>()
            r6.d = r0
        L_0x00ac:
            com.google.android.gms.internal.ads.aaw r0 = r6.d
        L_0x00ae:
            r7.a(r0)
            goto L_0x0000
        L_0x00b3:
            int r1 = r7.j()
            int r2 = r7.c()     // Catch:{ IllegalArgumentException -> 0x0110 }
            if (r2 < 0) goto L_0x00c8
            r3 = 4
            if (r2 > r3) goto L_0x00c8
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x0110 }
            r6.k = r2     // Catch:{ IllegalArgumentException -> 0x0110 }
            goto L_0x0000
        L_0x00c8:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x0110 }
            r4 = 39
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0110 }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x0110 }
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x0110 }
            java.lang.String r2 = " is not a valid enum Verdict"
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x0110 }
            java.lang.String r2 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x0110 }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x0110 }
            throw r3     // Catch:{ IllegalArgumentException -> 0x0110 }
        L_0x00e1:
            int r1 = r7.j()
            int r2 = r7.c()     // Catch:{ IllegalArgumentException -> 0x0110 }
            if (r2 < 0) goto L_0x00f7
            r3 = 9
            if (r2 > r3) goto L_0x00f7
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x0110 }
            r6.a = r2     // Catch:{ IllegalArgumentException -> 0x0110 }
            goto L_0x0000
        L_0x00f7:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x0110 }
            r4 = 42
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0110 }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x0110 }
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x0110 }
            java.lang.String r2 = " is not a valid enum ReportType"
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x0110 }
            java.lang.String r2 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x0110 }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x0110 }
            throw r3     // Catch:{ IllegalArgumentException -> 0x0110 }
        L_0x0110:
            r7.e(r1)
            r6.a(r7, r0)
            goto L_0x0000
        L_0x0118:
            boolean r0 = r7.d()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.q = r0
            goto L_0x0000
        L_0x0124:
            boolean r0 = r7.d()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.p = r0
            goto L_0x0000
        L_0x0130:
            java.lang.String r0 = r7.e()
            r6.o = r0
            goto L_0x0000
        L_0x0138:
            r0 = 50
            int r0 = com.google.android.gms.internal.ads.aau.a(r7, r0)
            java.lang.String[] r2 = r6.n
            if (r2 != 0) goto L_0x0144
            r2 = r1
            goto L_0x0147
        L_0x0144:
            java.lang.String[] r2 = r6.n
            int r2 = r2.length
        L_0x0147:
            int r0 = r0 + r2
            java.lang.String[] r0 = new java.lang.String[r0]
            if (r2 == 0) goto L_0x0151
            java.lang.String[] r3 = r6.n
            java.lang.System.arraycopy(r3, r1, r0, r1, r2)
        L_0x0151:
            int r1 = r0.length
            int r1 = r1 + -1
            if (r2 >= r1) goto L_0x0162
            java.lang.String r1 = r7.e()
            r0[r2] = r1
            r7.a()
            int r2 = r2 + 1
            goto L_0x0151
        L_0x0162:
            java.lang.String r1 = r7.e()
            r0[r2] = r1
            r6.n = r0
            goto L_0x0000
        L_0x016c:
            boolean r0 = r7.d()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.m = r0
            goto L_0x0000
        L_0x0178:
            r0 = 34
            int r0 = com.google.android.gms.internal.ads.aau.a(r7, r0)
            com.google.android.gms.internal.ads.abd[] r2 = r6.e
            if (r2 != 0) goto L_0x0184
            r2 = r1
            goto L_0x0187
        L_0x0184:
            com.google.android.gms.internal.ads.abd[] r2 = r6.e
            int r2 = r2.length
        L_0x0187:
            int r0 = r0 + r2
            com.google.android.gms.internal.ads.abd[] r0 = new com.google.android.gms.internal.ads.abd[r0]
            if (r2 == 0) goto L_0x0191
            com.google.android.gms.internal.ads.abd[] r3 = r6.e
            java.lang.System.arraycopy(r3, r1, r0, r1, r2)
        L_0x0191:
            int r1 = r0.length
            int r1 = r1 + -1
            if (r2 >= r1) goto L_0x01a8
            com.google.android.gms.internal.ads.abd r1 = new com.google.android.gms.internal.ads.abd
            r1.<init>()
            r0[r2] = r1
            r1 = r0[r2]
            r7.a(r1)
            r7.a()
            int r2 = r2 + 1
            goto L_0x0191
        L_0x01a8:
            com.google.android.gms.internal.ads.abd r1 = new com.google.android.gms.internal.ads.abd
            r1.<init>()
            r0[r2] = r1
            r1 = r0[r2]
            r7.a(r1)
            r6.e = r0
            goto L_0x0000
        L_0x01b8:
            java.lang.String r0 = r7.e()
            r6.l = r0
            goto L_0x0000
        L_0x01c0:
            java.lang.String r0 = r7.e()
            r6.c = r0
            goto L_0x0000
        L_0x01c8:
            java.lang.String r0 = r7.e()
            r6.b = r0
            goto L_0x0000
        L_0x01d0:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.aav.a(com.google.android.gms.internal.ads.aaj):com.google.android.gms.internal.ads.aav");
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.b != null) {
            a2 += aal.b(1, this.b);
        }
        if (this.c != null) {
            a2 += aal.b(2, this.c);
        }
        if (this.l != null) {
            a2 += aal.b(3, this.l);
        }
        if (this.e != null && this.e.length > 0) {
            int i2 = a2;
            for (abd abd : this.e) {
                if (abd != null) {
                    i2 += aal.b(4, (aar) abd);
                }
            }
            a2 = i2;
        }
        if (this.m != null) {
            this.m.booleanValue();
            a2 += aal.b(5) + 1;
        }
        if (this.n != null && this.n.length > 0) {
            int i3 = 0;
            int i4 = 0;
            for (String str : this.n) {
                if (str != null) {
                    i4++;
                    i3 += aal.a(str);
                }
            }
            a2 = a2 + i3 + (i4 * 1);
        }
        if (this.o != null) {
            a2 += aal.b(7, this.o);
        }
        if (this.p != null) {
            this.p.booleanValue();
            a2 += aal.b(8) + 1;
        }
        if (this.q != null) {
            this.q.booleanValue();
            a2 += aal.b(9) + 1;
        }
        if (this.a != null) {
            a2 += aal.b(10, this.a.intValue());
        }
        if (this.k != null) {
            a2 += aal.b(11, this.k.intValue());
        }
        if (this.d != null) {
            a2 += aal.b(12, (aar) this.d);
        }
        if (this.f != null) {
            a2 += aal.b(13, this.f);
        }
        if (this.g != null) {
            a2 += aal.b(14, (aar) this.g);
        }
        if (this.r != null) {
            a2 += aal.b(15, this.r);
        }
        if (this.h != null) {
            a2 += aal.b(17, (aar) this.h);
        }
        if (this.i != null && this.i.length > 0) {
            int i5 = 0;
            int i6 = 0;
            for (String str2 : this.i) {
                if (str2 != null) {
                    i6++;
                    i5 += aal.a(str2);
                }
            }
            a2 = a2 + i5 + (i6 * 2);
        }
        if (this.j == null || this.j.length <= 0) {
            return a2;
        }
        int i7 = 0;
        int i8 = 0;
        for (String str3 : this.j) {
            if (str3 != null) {
                i8++;
                i7 += aal.a(str3);
            }
        }
        return a2 + i7 + (2 * i8);
    }

    public final void a(aal aal) throws IOException {
        if (this.b != null) {
            aal.a(1, this.b);
        }
        if (this.c != null) {
            aal.a(2, this.c);
        }
        if (this.l != null) {
            aal.a(3, this.l);
        }
        if (this.e != null && this.e.length > 0) {
            for (abd abd : this.e) {
                if (abd != null) {
                    aal.a(4, (aar) abd);
                }
            }
        }
        if (this.m != null) {
            aal.a(5, this.m.booleanValue());
        }
        if (this.n != null && this.n.length > 0) {
            for (String str : this.n) {
                if (str != null) {
                    aal.a(6, str);
                }
            }
        }
        if (this.o != null) {
            aal.a(7, this.o);
        }
        if (this.p != null) {
            aal.a(8, this.p.booleanValue());
        }
        if (this.q != null) {
            aal.a(9, this.q.booleanValue());
        }
        if (this.a != null) {
            aal.a(10, this.a.intValue());
        }
        if (this.k != null) {
            aal.a(11, this.k.intValue());
        }
        if (this.d != null) {
            aal.a(12, (aar) this.d);
        }
        if (this.f != null) {
            aal.a(13, this.f);
        }
        if (this.g != null) {
            aal.a(14, (aar) this.g);
        }
        if (this.r != null) {
            aal.a(15, this.r);
        }
        if (this.h != null) {
            aal.a(17, (aar) this.h);
        }
        if (this.i != null && this.i.length > 0) {
            for (String str2 : this.i) {
                if (str2 != null) {
                    aal.a(20, str2);
                }
            }
        }
        if (this.j != null && this.j.length > 0) {
            for (String str3 : this.j) {
                if (str3 != null) {
                    aal.a(21, str3);
                }
            }
        }
        super.a(aal);
    }
}
