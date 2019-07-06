package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class vy extends aam<vy> {
    public Long A;
    public Long B;
    public String C;
    public String D;
    public Integer E;
    public Integer F;
    public Long G;
    public Long H;
    public Long I;
    public Integer J;
    public wu K;
    public wu[] L;
    public xq M;
    public Long N;
    public Long O;
    public Long P;
    public Long Q;
    public Long R;
    public String S;
    public String T;
    public Integer U;
    public Boolean V;
    public Long W;
    public aak X;
    public String a;
    private Long aa;
    private Long ab;
    private Long ac;
    private Long ad;
    private Long ae;
    private Long af;
    private String ag;
    private Long ah;
    private Long ai;
    private yq aj;
    private Long ak;
    private Long al;
    private Long am;
    private Long an;
    private Integer ao;
    private Integer ap;
    private Integer aq;
    private Long ar;
    private String as;
    public String b;
    public Long c;
    public Long d;
    public Long e;
    public Long f;
    public Long g;
    public Long h;
    public Long i;
    public Long j;
    public Long k;
    public Long l;
    public Long m;
    public String n;
    public String o;
    public Long p;
    public Long q;
    public Long r;
    public String s;
    public Long t;
    public Long u;
    public Long v;
    public Long w;
    public Long x;
    public Long y;
    public Long z;

    public vy() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.aa = null;
        this.d = null;
        this.e = null;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.f = null;
        this.ag = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.ah = null;
        this.ai = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.aj = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.ak = null;
        this.al = null;
        this.K = null;
        this.L = wu.b();
        this.M = null;
        this.am = null;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.an = null;
        this.ar = null;
        this.T = null;
        this.V = null;
        this.as = null;
        this.W = null;
        this.X = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
        throw new java.lang.IllegalArgumentException(r5.toString());
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.vy a(com.google.android.gms.internal.ads.aaj r7) throws java.io.IOException {
        /*
            r6 = this;
        L_0x0000:
            int r0 = r7.a()
            switch(r0) {
                case 0: goto L_0x03d2;
                case 10: goto L_0x03ca;
                case 18: goto L_0x03c2;
                case 24: goto L_0x03b6;
                case 32: goto L_0x03aa;
                case 40: goto L_0x039e;
                case 48: goto L_0x0392;
                case 56: goto L_0x0386;
                case 64: goto L_0x037a;
                case 72: goto L_0x036e;
                case 80: goto L_0x0362;
                case 88: goto L_0x0356;
                case 96: goto L_0x034a;
                case 106: goto L_0x0342;
                case 112: goto L_0x0336;
                case 120: goto L_0x032a;
                case 128: goto L_0x031e;
                case 136: goto L_0x0312;
                case 144: goto L_0x0306;
                case 152: goto L_0x02fa;
                case 160: goto L_0x02ee;
                case 168: goto L_0x02e2;
                case 176: goto L_0x02d6;
                case 184: goto L_0x02ca;
                case 194: goto L_0x02c2;
                case 200: goto L_0x02b6;
                case 208: goto L_0x0280;
                case 218: goto L_0x0278;
                case 224: goto L_0x026c;
                case 234: goto L_0x0264;
                case 242: goto L_0x025c;
                case 248: goto L_0x0250;
                case 256: goto L_0x0244;
                case 264: goto L_0x0238;
                case 274: goto L_0x0230;
                case 280: goto L_0x0224;
                case 288: goto L_0x0218;
                case 296: goto L_0x020c;
                case 306: goto L_0x01fa;
                case 312: goto L_0x01ee;
                case 320: goto L_0x01e2;
                case 328: goto L_0x01d6;
                case 336: goto L_0x01ca;
                case 346: goto L_0x0189;
                case 352: goto L_0x017d;
                case 360: goto L_0x0171;
                case 370: goto L_0x0169;
                case 378: goto L_0x0161;
                case 384: goto L_0x014d;
                case 392: goto L_0x0139;
                case 402: goto L_0x012a;
                case 408: goto L_0x011e;
                case 416: goto L_0x0112;
                case 424: goto L_0x0106;
                case 432: goto L_0x00fa;
                case 440: goto L_0x00ee;
                case 448: goto L_0x00da;
                case 458: goto L_0x00cb;
                case 464: goto L_0x00bf;
                case 472: goto L_0x00b3;
                case 480: goto L_0x00a7;
                case 488: goto L_0x009b;
                case 496: goto L_0x008f;
                case 504: goto L_0x0083;
                case 512: goto L_0x0077;
                case 520: goto L_0x0064;
                case 528: goto L_0x0051;
                case 538: goto L_0x004a;
                case 544: goto L_0x001d;
                case 1610: goto L_0x000e;
                default: goto L_0x0007;
            }
        L_0x0007:
            boolean r0 = super.a(r7, r0)
            if (r0 != 0) goto L_0x0000
            return r6
        L_0x000e:
            com.google.android.gms.internal.ads.aak r0 = r6.X
            if (r0 != 0) goto L_0x0019
            com.google.android.gms.internal.ads.aak r0 = new com.google.android.gms.internal.ads.aak
            r0.<init>()
            r6.X = r0
        L_0x0019:
            com.google.android.gms.internal.ads.aak r0 = r6.X
            goto L_0x0207
        L_0x001d:
            int r1 = r7.j()
            int r2 = r7.g()     // Catch:{ IllegalArgumentException -> 0x02ae }
            if (r2 < 0) goto L_0x0031
            r3 = 3
            if (r2 > r3) goto L_0x0031
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            r6.aq = r2     // Catch:{ IllegalArgumentException -> 0x02ae }
            goto L_0x0000
        L_0x0031:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x02ae }
            r4 = 45
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x02ae }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x02ae }
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            java.lang.String r2 = " is not a valid enum DebuggerState"
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            java.lang.String r2 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x02ae }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            throw r3     // Catch:{ IllegalArgumentException -> 0x02ae }
        L_0x004a:
            java.lang.String r0 = r7.e()
            r6.S = r0
            goto L_0x0000
        L_0x0051:
            int r1 = r7.j()
            int r2 = r7.g()     // Catch:{ IllegalArgumentException -> 0x02ae }
            int r2 = com.google.android.gms.internal.ads.uw.b(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            r6.ap = r2     // Catch:{ IllegalArgumentException -> 0x02ae }
            goto L_0x0000
        L_0x0064:
            int r1 = r7.j()
            int r2 = r7.g()     // Catch:{ IllegalArgumentException -> 0x02ae }
            int r2 = com.google.android.gms.internal.ads.uw.c(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            r6.ao = r2     // Catch:{ IllegalArgumentException -> 0x02ae }
            goto L_0x0000
        L_0x0077:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.an = r0
            goto L_0x0000
        L_0x0083:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.R = r0
            goto L_0x0000
        L_0x008f:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.Q = r0
            goto L_0x0000
        L_0x009b:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.P = r0
            goto L_0x0000
        L_0x00a7:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.O = r0
            goto L_0x0000
        L_0x00b3:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.N = r0
            goto L_0x0000
        L_0x00bf:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.am = r0
            goto L_0x0000
        L_0x00cb:
            com.google.android.gms.internal.ads.xq r0 = r6.M
            if (r0 != 0) goto L_0x00d6
            com.google.android.gms.internal.ads.xq r0 = new com.google.android.gms.internal.ads.xq
            r0.<init>()
            r6.M = r0
        L_0x00d6:
            com.google.android.gms.internal.ads.xq r0 = r6.M
            goto L_0x0207
        L_0x00da:
            int r1 = r7.j()
            int r2 = r7.g()     // Catch:{ IllegalArgumentException -> 0x02ae }
            int r2 = com.google.android.gms.internal.ads.uw.a(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            r6.J = r2     // Catch:{ IllegalArgumentException -> 0x02ae }
            goto L_0x0000
        L_0x00ee:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.al = r0
            goto L_0x0000
        L_0x00fa:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.ak = r0
            goto L_0x0000
        L_0x0106:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.I = r0
            goto L_0x0000
        L_0x0112:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.H = r0
            goto L_0x0000
        L_0x011e:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.G = r0
            goto L_0x0000
        L_0x012a:
            com.google.android.gms.internal.ads.wu r0 = r6.K
            if (r0 != 0) goto L_0x0135
            com.google.android.gms.internal.ads.wu r0 = new com.google.android.gms.internal.ads.wu
            r0.<init>()
            r6.K = r0
        L_0x0135:
            com.google.android.gms.internal.ads.wu r0 = r6.K
            goto L_0x0207
        L_0x0139:
            int r1 = r7.j()
            int r2 = r7.g()     // Catch:{ IllegalArgumentException -> 0x02ae }
            int r2 = com.google.android.gms.internal.ads.uw.a(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            r6.F = r2     // Catch:{ IllegalArgumentException -> 0x02ae }
            goto L_0x0000
        L_0x014d:
            int r1 = r7.j()
            int r2 = r7.g()     // Catch:{ IllegalArgumentException -> 0x02ae }
            int r2 = com.google.android.gms.internal.ads.uw.a(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            r6.E = r2     // Catch:{ IllegalArgumentException -> 0x02ae }
            goto L_0x0000
        L_0x0161:
            java.lang.String r0 = r7.e()
            r6.D = r0
            goto L_0x0000
        L_0x0169:
            java.lang.String r0 = r7.e()
            r6.C = r0
            goto L_0x0000
        L_0x0171:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.B = r0
            goto L_0x0000
        L_0x017d:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.A = r0
            goto L_0x0000
        L_0x0189:
            r0 = 346(0x15a, float:4.85E-43)
            int r0 = com.google.android.gms.internal.ads.aau.a(r7, r0)
            com.google.android.gms.internal.ads.wu[] r1 = r6.L
            r2 = 0
            if (r1 != 0) goto L_0x0196
            r1 = r2
            goto L_0x0199
        L_0x0196:
            com.google.android.gms.internal.ads.wu[] r1 = r6.L
            int r1 = r1.length
        L_0x0199:
            int r0 = r0 + r1
            com.google.android.gms.internal.ads.wu[] r0 = new com.google.android.gms.internal.ads.wu[r0]
            if (r1 == 0) goto L_0x01a3
            com.google.android.gms.internal.ads.wu[] r3 = r6.L
            java.lang.System.arraycopy(r3, r2, r0, r2, r1)
        L_0x01a3:
            int r2 = r0.length
            int r2 = r2 + -1
            if (r1 >= r2) goto L_0x01ba
            com.google.android.gms.internal.ads.wu r2 = new com.google.android.gms.internal.ads.wu
            r2.<init>()
            r0[r1] = r2
            r2 = r0[r1]
            r7.a(r2)
            r7.a()
            int r1 = r1 + 1
            goto L_0x01a3
        L_0x01ba:
            com.google.android.gms.internal.ads.wu r2 = new com.google.android.gms.internal.ads.wu
            r2.<init>()
            r0[r1] = r2
            r1 = r0[r1]
            r7.a(r1)
            r6.L = r0
            goto L_0x0000
        L_0x01ca:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.z = r0
            goto L_0x0000
        L_0x01d6:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.y = r0
            goto L_0x0000
        L_0x01e2:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.x = r0
            goto L_0x0000
        L_0x01ee:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.w = r0
            goto L_0x0000
        L_0x01fa:
            com.google.android.gms.internal.ads.yq r0 = r6.aj
            if (r0 != 0) goto L_0x0205
            com.google.android.gms.internal.ads.yq r0 = new com.google.android.gms.internal.ads.yq
            r0.<init>()
            r6.aj = r0
        L_0x0205:
            com.google.android.gms.internal.ads.yq r0 = r6.aj
        L_0x0207:
            r7.a(r0)
            goto L_0x0000
        L_0x020c:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.v = r0
            goto L_0x0000
        L_0x0218:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.u = r0
            goto L_0x0000
        L_0x0224:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.t = r0
            goto L_0x0000
        L_0x0230:
            java.lang.String r0 = r7.e()
            r6.s = r0
            goto L_0x0000
        L_0x0238:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.r = r0
            goto L_0x0000
        L_0x0244:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.q = r0
            goto L_0x0000
        L_0x0250:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.p = r0
            goto L_0x0000
        L_0x025c:
            java.lang.String r0 = r7.e()
            r6.as = r0
            goto L_0x0000
        L_0x0264:
            java.lang.String r0 = r7.e()
            r6.o = r0
            goto L_0x0000
        L_0x026c:
            boolean r0 = r7.d()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.V = r0
            goto L_0x0000
        L_0x0278:
            java.lang.String r0 = r7.e()
            r6.n = r0
            goto L_0x0000
        L_0x0280:
            int r1 = r7.j()
            int r2 = r7.g()     // Catch:{ IllegalArgumentException -> 0x02ae }
            if (r2 < 0) goto L_0x0295
            r3 = 6
            if (r2 > r3) goto L_0x0295
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            r6.U = r2     // Catch:{ IllegalArgumentException -> 0x02ae }
            goto L_0x0000
        L_0x0295:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x02ae }
            r4 = 44
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x02ae }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x02ae }
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            java.lang.String r2 = " is not a valid enum DeviceIdType"
            r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            java.lang.String r2 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x02ae }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x02ae }
            throw r3     // Catch:{ IllegalArgumentException -> 0x02ae }
        L_0x02ae:
            r7.e(r1)
            r6.a(r7, r0)
            goto L_0x0000
        L_0x02b6:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.W = r0
            goto L_0x0000
        L_0x02c2:
            java.lang.String r0 = r7.e()
            r6.T = r0
            goto L_0x0000
        L_0x02ca:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.m = r0
            goto L_0x0000
        L_0x02d6:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.l = r0
            goto L_0x0000
        L_0x02e2:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.ar = r0
            goto L_0x0000
        L_0x02ee:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.k = r0
            goto L_0x0000
        L_0x02fa:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.ai = r0
            goto L_0x0000
        L_0x0306:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.ah = r0
            goto L_0x0000
        L_0x0312:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.j = r0
            goto L_0x0000
        L_0x031e:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.i = r0
            goto L_0x0000
        L_0x032a:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.h = r0
            goto L_0x0000
        L_0x0336:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.g = r0
            goto L_0x0000
        L_0x0342:
            java.lang.String r0 = r7.e()
            r6.ag = r0
            goto L_0x0000
        L_0x034a:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.f = r0
            goto L_0x0000
        L_0x0356:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.af = r0
            goto L_0x0000
        L_0x0362:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.ae = r0
            goto L_0x0000
        L_0x036e:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.ad = r0
            goto L_0x0000
        L_0x037a:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.ac = r0
            goto L_0x0000
        L_0x0386:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.ab = r0
            goto L_0x0000
        L_0x0392:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.e = r0
            goto L_0x0000
        L_0x039e:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.d = r0
            goto L_0x0000
        L_0x03aa:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.aa = r0
            goto L_0x0000
        L_0x03b6:
            long r0 = r7.h()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r6.c = r0
            goto L_0x0000
        L_0x03c2:
            java.lang.String r0 = r7.e()
            r6.b = r0
            goto L_0x0000
        L_0x03ca:
            java.lang.String r0 = r7.e()
            r6.a = r0
            goto L_0x0000
        L_0x03d2:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.vy.a(com.google.android.gms.internal.ads.aaj):com.google.android.gms.internal.ads.vy");
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(1, this.a);
        }
        if (this.b != null) {
            a2 += aal.b(2, this.b);
        }
        if (this.c != null) {
            a2 += aal.d(3, this.c.longValue());
        }
        if (this.aa != null) {
            a2 += aal.d(4, this.aa.longValue());
        }
        if (this.d != null) {
            a2 += aal.d(5, this.d.longValue());
        }
        if (this.e != null) {
            a2 += aal.d(6, this.e.longValue());
        }
        if (this.ab != null) {
            a2 += aal.d(7, this.ab.longValue());
        }
        if (this.ac != null) {
            a2 += aal.d(8, this.ac.longValue());
        }
        if (this.ad != null) {
            a2 += aal.d(9, this.ad.longValue());
        }
        if (this.ae != null) {
            a2 += aal.d(10, this.ae.longValue());
        }
        if (this.af != null) {
            a2 += aal.d(11, this.af.longValue());
        }
        if (this.f != null) {
            a2 += aal.d(12, this.f.longValue());
        }
        if (this.ag != null) {
            a2 += aal.b(13, this.ag);
        }
        if (this.g != null) {
            a2 += aal.d(14, this.g.longValue());
        }
        if (this.h != null) {
            a2 += aal.d(15, this.h.longValue());
        }
        if (this.i != null) {
            a2 += aal.d(16, this.i.longValue());
        }
        if (this.j != null) {
            a2 += aal.d(17, this.j.longValue());
        }
        if (this.ah != null) {
            a2 += aal.d(18, this.ah.longValue());
        }
        if (this.ai != null) {
            a2 += aal.d(19, this.ai.longValue());
        }
        if (this.k != null) {
            a2 += aal.d(20, this.k.longValue());
        }
        if (this.ar != null) {
            a2 += aal.d(21, this.ar.longValue());
        }
        if (this.l != null) {
            a2 += aal.d(22, this.l.longValue());
        }
        if (this.m != null) {
            a2 += aal.d(23, this.m.longValue());
        }
        if (this.T != null) {
            a2 += aal.b(24, this.T);
        }
        if (this.W != null) {
            a2 += aal.d(25, this.W.longValue());
        }
        if (this.U != null) {
            a2 += aal.b(26, this.U.intValue());
        }
        if (this.n != null) {
            a2 += aal.b(27, this.n);
        }
        if (this.V != null) {
            this.V.booleanValue();
            a2 += aal.b(28) + 1;
        }
        if (this.o != null) {
            a2 += aal.b(29, this.o);
        }
        if (this.as != null) {
            a2 += aal.b(30, this.as);
        }
        if (this.p != null) {
            a2 += aal.d(31, this.p.longValue());
        }
        if (this.q != null) {
            a2 += aal.d(32, this.q.longValue());
        }
        if (this.r != null) {
            a2 += aal.d(33, this.r.longValue());
        }
        if (this.s != null) {
            a2 += aal.b(34, this.s);
        }
        if (this.t != null) {
            a2 += aal.d(35, this.t.longValue());
        }
        if (this.u != null) {
            a2 += aal.d(36, this.u.longValue());
        }
        if (this.v != null) {
            a2 += aal.d(37, this.v.longValue());
        }
        if (this.aj != null) {
            a2 += aal.b(38, (aar) this.aj);
        }
        if (this.w != null) {
            a2 += aal.d(39, this.w.longValue());
        }
        if (this.x != null) {
            a2 += aal.d(40, this.x.longValue());
        }
        if (this.y != null) {
            a2 += aal.d(41, this.y.longValue());
        }
        if (this.z != null) {
            a2 += aal.d(42, this.z.longValue());
        }
        if (this.L != null && this.L.length > 0) {
            for (wu wuVar : this.L) {
                if (wuVar != null) {
                    a2 += aal.b(43, (aar) wuVar);
                }
            }
        }
        if (this.A != null) {
            a2 += aal.d(44, this.A.longValue());
        }
        if (this.B != null) {
            a2 += aal.d(45, this.B.longValue());
        }
        if (this.C != null) {
            a2 += aal.b(46, this.C);
        }
        if (this.D != null) {
            a2 += aal.b(47, this.D);
        }
        if (this.E != null) {
            a2 += aal.b(48, this.E.intValue());
        }
        if (this.F != null) {
            a2 += aal.b(49, this.F.intValue());
        }
        if (this.K != null) {
            a2 += aal.b(50, (aar) this.K);
        }
        if (this.G != null) {
            a2 += aal.d(51, this.G.longValue());
        }
        if (this.H != null) {
            a2 += aal.d(52, this.H.longValue());
        }
        if (this.I != null) {
            a2 += aal.d(53, this.I.longValue());
        }
        if (this.ak != null) {
            a2 += aal.d(54, this.ak.longValue());
        }
        if (this.al != null) {
            a2 += aal.d(55, this.al.longValue());
        }
        if (this.J != null) {
            a2 += aal.b(56, this.J.intValue());
        }
        if (this.M != null) {
            a2 += aal.b(57, (aar) this.M);
        }
        if (this.am != null) {
            a2 += aal.d(58, this.am.longValue());
        }
        if (this.N != null) {
            a2 += aal.d(59, this.N.longValue());
        }
        if (this.O != null) {
            a2 += aal.d(60, this.O.longValue());
        }
        if (this.P != null) {
            a2 += aal.d(61, this.P.longValue());
        }
        if (this.Q != null) {
            a2 += aal.d(62, this.Q.longValue());
        }
        if (this.R != null) {
            a2 += aal.d(63, this.R.longValue());
        }
        if (this.an != null) {
            a2 += aal.d(64, this.an.longValue());
        }
        if (this.ao != null) {
            a2 += aal.b(65, this.ao.intValue());
        }
        if (this.ap != null) {
            a2 += aal.b(66, this.ap.intValue());
        }
        if (this.S != null) {
            a2 += aal.b(67, this.S);
        }
        if (this.aq != null) {
            a2 += aal.b(68, this.aq.intValue());
        }
        return this.X != null ? a2 + aal.b(201, (aar) this.X) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, this.a);
        }
        if (this.b != null) {
            aal.a(2, this.b);
        }
        if (this.c != null) {
            aal.b(3, this.c.longValue());
        }
        if (this.aa != null) {
            aal.b(4, this.aa.longValue());
        }
        if (this.d != null) {
            aal.b(5, this.d.longValue());
        }
        if (this.e != null) {
            aal.b(6, this.e.longValue());
        }
        if (this.ab != null) {
            aal.b(7, this.ab.longValue());
        }
        if (this.ac != null) {
            aal.b(8, this.ac.longValue());
        }
        if (this.ad != null) {
            aal.b(9, this.ad.longValue());
        }
        if (this.ae != null) {
            aal.b(10, this.ae.longValue());
        }
        if (this.af != null) {
            aal.b(11, this.af.longValue());
        }
        if (this.f != null) {
            aal.b(12, this.f.longValue());
        }
        if (this.ag != null) {
            aal.a(13, this.ag);
        }
        if (this.g != null) {
            aal.b(14, this.g.longValue());
        }
        if (this.h != null) {
            aal.b(15, this.h.longValue());
        }
        if (this.i != null) {
            aal.b(16, this.i.longValue());
        }
        if (this.j != null) {
            aal.b(17, this.j.longValue());
        }
        if (this.ah != null) {
            aal.b(18, this.ah.longValue());
        }
        if (this.ai != null) {
            aal.b(19, this.ai.longValue());
        }
        if (this.k != null) {
            aal.b(20, this.k.longValue());
        }
        if (this.ar != null) {
            aal.b(21, this.ar.longValue());
        }
        if (this.l != null) {
            aal.b(22, this.l.longValue());
        }
        if (this.m != null) {
            aal.b(23, this.m.longValue());
        }
        if (this.T != null) {
            aal.a(24, this.T);
        }
        if (this.W != null) {
            aal.b(25, this.W.longValue());
        }
        if (this.U != null) {
            aal.a(26, this.U.intValue());
        }
        if (this.n != null) {
            aal.a(27, this.n);
        }
        if (this.V != null) {
            aal.a(28, this.V.booleanValue());
        }
        if (this.o != null) {
            aal.a(29, this.o);
        }
        if (this.as != null) {
            aal.a(30, this.as);
        }
        if (this.p != null) {
            aal.b(31, this.p.longValue());
        }
        if (this.q != null) {
            aal.b(32, this.q.longValue());
        }
        if (this.r != null) {
            aal.b(33, this.r.longValue());
        }
        if (this.s != null) {
            aal.a(34, this.s);
        }
        if (this.t != null) {
            aal.b(35, this.t.longValue());
        }
        if (this.u != null) {
            aal.b(36, this.u.longValue());
        }
        if (this.v != null) {
            aal.b(37, this.v.longValue());
        }
        if (this.aj != null) {
            aal.a(38, (aar) this.aj);
        }
        if (this.w != null) {
            aal.b(39, this.w.longValue());
        }
        if (this.x != null) {
            aal.b(40, this.x.longValue());
        }
        if (this.y != null) {
            aal.b(41, this.y.longValue());
        }
        if (this.z != null) {
            aal.b(42, this.z.longValue());
        }
        if (this.L != null && this.L.length > 0) {
            for (wu wuVar : this.L) {
                if (wuVar != null) {
                    aal.a(43, (aar) wuVar);
                }
            }
        }
        if (this.A != null) {
            aal.b(44, this.A.longValue());
        }
        if (this.B != null) {
            aal.b(45, this.B.longValue());
        }
        if (this.C != null) {
            aal.a(46, this.C);
        }
        if (this.D != null) {
            aal.a(47, this.D);
        }
        if (this.E != null) {
            aal.a(48, this.E.intValue());
        }
        if (this.F != null) {
            aal.a(49, this.F.intValue());
        }
        if (this.K != null) {
            aal.a(50, (aar) this.K);
        }
        if (this.G != null) {
            aal.b(51, this.G.longValue());
        }
        if (this.H != null) {
            aal.b(52, this.H.longValue());
        }
        if (this.I != null) {
            aal.b(53, this.I.longValue());
        }
        if (this.ak != null) {
            aal.b(54, this.ak.longValue());
        }
        if (this.al != null) {
            aal.b(55, this.al.longValue());
        }
        if (this.J != null) {
            aal.a(56, this.J.intValue());
        }
        if (this.M != null) {
            aal.a(57, (aar) this.M);
        }
        if (this.am != null) {
            aal.b(58, this.am.longValue());
        }
        if (this.N != null) {
            aal.b(59, this.N.longValue());
        }
        if (this.O != null) {
            aal.b(60, this.O.longValue());
        }
        if (this.P != null) {
            aal.b(61, this.P.longValue());
        }
        if (this.Q != null) {
            aal.b(62, this.Q.longValue());
        }
        if (this.R != null) {
            aal.b(63, this.R.longValue());
        }
        if (this.an != null) {
            aal.b(64, this.an.longValue());
        }
        if (this.ao != null) {
            aal.a(65, this.ao.intValue());
        }
        if (this.ap != null) {
            aal.a(66, this.ap.intValue());
        }
        if (this.S != null) {
            aal.a(67, this.S);
        }
        if (this.aq != null) {
            aal.a(68, this.aq.intValue());
        }
        if (this.X != null) {
            aal.a(201, (aar) this.X);
        }
        super.a(aal);
    }
}
