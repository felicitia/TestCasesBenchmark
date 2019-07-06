package com.threatmetrix.TrustDefender.internal;

import java.util.concurrent.atomic.AtomicLong;

public class K7 {
    /* access modifiers changed from: private */

    /* renamed from: long reason: not valid java name */
    public static final String f269long = TL.m331if(K7.class);

    /* renamed from: new reason: not valid java name */
    public static final String f270new = "5.2-34";
    /* access modifiers changed from: private */

    /* renamed from: break reason: not valid java name */
    public final AtomicLong f271break;

    /* renamed from: byte reason: not valid java name */
    final String f272byte;

    /* renamed from: case reason: not valid java name */
    final String f273case;

    /* renamed from: char reason: not valid java name */
    final String f274char;

    /* renamed from: do reason: not valid java name */
    final boolean f275do;

    /* renamed from: else reason: not valid java name */
    final String f276else;

    /* renamed from: for reason: not valid java name */
    final long f277for;
    /* access modifiers changed from: private */

    /* renamed from: goto reason: not valid java name */
    public final String f278goto;

    /* renamed from: if reason: not valid java name */
    final String f279if;

    /* renamed from: int reason: not valid java name */
    final com.threatmetrix.TrustDefender.internal.P.O f280int;

    /* renamed from: this reason: not valid java name */
    final boolean f281this;

    /* renamed from: try reason: not valid java name */
    final K f282try;

    /* renamed from: void reason: not valid java name */
    volatile E f283void = new E();

    class E {

        /* renamed from: boolean reason: not valid java name */
        String f284boolean;

        /* renamed from: break reason: not valid java name */
        final String f285break;

        /* renamed from: byte reason: not valid java name */
        final long f286byte;

        /* renamed from: case reason: not valid java name */
        final long f287case;

        /* renamed from: catch reason: not valid java name */
        final String f288catch;

        /* renamed from: char reason: not valid java name */
        final long f289char;

        /* renamed from: class reason: not valid java name */
        final String f290class;

        /* renamed from: const reason: not valid java name */
        final String f291const;

        /* renamed from: do reason: not valid java name */
        final int f292do;

        /* renamed from: double reason: not valid java name */
        final Z2 f293double;

        /* renamed from: else reason: not valid java name */
        final String f294else;

        /* renamed from: final reason: not valid java name */
        final String f296final;

        /* renamed from: float reason: not valid java name */
        final String f297float;

        /* renamed from: for reason: not valid java name */
        final int f298for;

        /* renamed from: goto reason: not valid java name */
        final String f299goto;

        /* renamed from: if reason: not valid java name */
        final int f300if;

        /* renamed from: import reason: not valid java name */
        final String f301import;

        /* renamed from: int reason: not valid java name */
        final int f302int;

        /* renamed from: long reason: not valid java name */
        final String f303long;

        /* renamed from: native reason: not valid java name */
        final I f304native;

        /* renamed from: new reason: not valid java name */
        final int f305new;

        /* renamed from: public reason: not valid java name */
        final W f306public;

        /* renamed from: return reason: not valid java name */
        final String f307return;

        /* renamed from: short reason: not valid java name */
        final String f308short;

        /* renamed from: static reason: not valid java name */
        final String f309static;

        /* renamed from: super reason: not valid java name */
        final String f310super;

        /* renamed from: this reason: not valid java name */
        final String f311this;

        /* renamed from: throw reason: not valid java name */
        final String f312throw;

        /* renamed from: try reason: not valid java name */
        final long f313try;

        /* renamed from: void reason: not valid java name */
        final String f314void;

        /* renamed from: while reason: not valid java name */
        final String f315while;

        public E() {
            this.f302int = 0;
            this.f298for = 0;
            this.f291const = null;
            this.f288catch = null;
            this.f286byte = 0;
            this.f289char = 0;
            this.f287case = 0;
            this.f293double = null;
            this.f290class = null;
            this.f310super = null;
            this.f305new = 0;
            this.f292do = 0;
            this.f315while = null;
            this.f312throw = null;
            this.f306public = W.NOT_CHECKED;
            this.f303long = null;
            this.f296final = null;
            this.f299goto = null;
            this.f314void = null;
            this.f311this = null;
            this.f285break = null;
            this.f308short = null;
            this.f297float = null;
            this.f294else = null;
            this.f300if = 0;
            this.f304native = null;
            this.f313try = 0;
            this.f309static = null;
            this.f301import = null;
            this.f307return = null;
            this.f284boolean = null;
        }

        E(K7 k7, E e, String str, Y y, String str2) throws InterruptedException {
            String str3;
            boolean z;
            String str4;
            int i;
            int i2;
            int i3;
            int i4;
            K7 k72 = k7;
            E e2 = e;
            K7.this = k72;
            long j = S.m172if();
            String str5 = e2.f303long;
            String str6 = e2.f285break;
            String str7 = e2.f311this;
            String str8 = e2.f297float;
            String str9 = e2.f314void;
            String str10 = e2.f299goto;
            String str11 = e2.f294else;
            String str12 = e2.f296final;
            String str13 = e2.f308short;
            String str14 = e2.f301import;
            I i5 = e2.f304native;
            int i6 = e2.f300if;
            CC cc = new CC();
            long j2 = j;
            this.f288catch = P.m267new();
            cc.m26do("get locale", "gl");
            boolean z2 = k72.f282try.m125for();
            I i7 = i5;
            cc.m26do("get browser info", "gbi");
            TL.m338new(K7.f269long, "Getting timezone info");
            if ((k7.f271break.get() & 8) != 0) {
                I i8 = new I();
                if (P.m262int(i8)) {
                    i3 = i8.f478new;
                    i4 = i8.f477for;
                    z = z2;
                } else {
                    z = z2;
                    i4 = 0;
                    i3 = 0;
                }
                str3 = str13;
                cc.m26do("Get time zone", "gtz");
                this.f302int = i4;
                this.f298for = i3;
            } else {
                z = z2;
                str3 = str13;
                this.f302int = 0;
                this.f298for = 0;
            }
            this.f309static = P.m254goto(k72.f280int);
            cc.m26do("cookie", "c");
            if (str5 == null) {
                str5 = JG.m108if(k72.f280int);
                cc.m26do("hardwareID", "hi");
            }
            if (str14 == null) {
                str14 = JG.m112new(k72.f280int);
                cc.m26do("serial", "s");
            }
            if (str6 == null || str7 == null) {
                String str15 = JG.m103do(k72.f280int);
                K7.m135int(y);
                cc.m26do("LSC", "l");
                if (str6 == null) {
                    str6 = JG.m113new(str15, k72.f281this);
                }
                K7.m135int(y);
                if (str7 == null) {
                    str7 = JG.m106do(k72.f279if, str15, str5, k72.f281this, k72.f280int);
                }
                cc.m26do("Flash", "f");
            }
            K7.m135int(y);
            if ((k7.f271break.get() & 16) != 0) {
                Z z3 = new Z(k72.f280int);
                this.f305new = z3.m455new();
                this.f292do = z3.m454do();
            } else {
                this.f305new = 0;
                this.f292do = 0;
            }
            cc.m26do("Get screen dimensions", "gsd");
            K7.m135int(y);
            if (str12 == null) {
                str12 = P.m251for(k72.f280int);
            }
            cc.m26do("Get device fingerprint", "gdf");
            if ((k7.f271break.get() & 2048) != 0 && str8 == null) {
                str8 = T.m319int(k72.f280int);
                cc.m26do("Get self hash", "gsh");
            }
            if (str9 == null || str10 == null) {
                StringBuilder sb = new StringBuilder();
                str10 = P.m261int(sb);
                if (str10 != null) {
                    str9 = sb.toString();
                }
                K7.f269long;
            }
            cc.m26do("Get font list", "gfl");
            K7.m135int(y);
            this.f286byte = P.m260int();
            cc.m26do("Get agent boot time", "gabt");
            K7.m135int(y);
            if (str11 == null) {
                str11 = P.m257if(k72.f280int);
                cc.m26do("Get agent name, version", "ganv");
            }
            K7.m135int(y);
            this.f289char = P.m255if();
            cc.m26do("Get the freeSpace in bytes", "gtfib");
            K7.m135int(y);
            this.f287case = P.m237case();
            cc.m26do("Get the totalSpace in bytes", "gttib");
            K7.m135int(y);
            String str16 = str14;
            this.f291const = P.m256if(this.f289char, this.f286byte);
            cc.m26do("Get device state", "gdst");
            String str17 = null;
            if ((k7.f271break.get() & 32768) != 0) {
                K7.m135int(y);
                this.f293double = P.m266new(k72.f280int);
                cc.m26do("Get the SSID , BSSID, RSSI and connection type", "gsbrc");
            } else {
                this.f293double = null;
            }
            K7.m135int(y);
            this.f290class = P.m236byte(k72.f280int);
            cc.m26do("Get Cell information", "gci");
            K7.m135int(y);
            this.f310super = P.m238case(k72.f280int);
            cc.m26do("Get Cell ID", "gcid");
            K7.m135int(y);
            if (!NK.m215if(str)) {
                str4 = str;
            } else {
                str4 = str3;
                if (NK.m215if(str4) && (k7.f271break.get() & 131072) != 0) {
                    str4 = P.m241char(k72.f280int);
                }
            }
            cc.m26do("Get Google Advertising ID", "ggai");
            if ((k7.f271break.get() & 262144) != 0) {
                this.f306public = P.m264long();
            } else {
                this.f306public = W.NOT_CHECKED;
            }
            cc.m26do("Get Selinux Mode", "gsm");
            if ((k7.f271break.get() & 2097152) != 0) {
                if (k72.f275do) {
                    str17 = P.m253goto();
                }
                this.f312throw = P.m265long(k72.f280int);
                this.f315while = str17;
            } else {
                this.f312throw = null;
                this.f315while = null;
            }
            if (z) {
                k72.f282try.m124do();
            }
            I i9 = (i7 != null || (k7.f271break.get() & 8388608) == 0) ? i7 : P.m270try();
            String str18 = PH.m275do().m280do(k72.f280int);
            K7.f269long;
            cc.m26do("wait for browser info", "wfbi");
            this.f285break = str6;
            this.f311this = str7;
            this.f303long = str5;
            this.f297float = str8;
            this.f314void = str9;
            this.f299goto = str10;
            this.f294else = str11;
            this.f308short = str4;
            this.f296final = str12;
            this.f304native = i9;
            this.f301import = str16;
            this.f313try = S.m172if() - j2;
            if (i6 != 0 || (k7.f271break.get() & 67108864) == 0) {
                i = i6;
            } else {
                com.threatmetrix.TrustDefender.internal.P.O o = k72.f280int;
                if (P.m240char() != null) {
                    K7.f269long;
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (!PH.m275do().f494char && PH.m275do().f495do) {
                    i2 |= 2;
                }
                com.threatmetrix.TrustDefender.internal.P.O o2 = o;
                String str19 = str2;
                V v = new V(o2, str19, "SHENASE_A", this.f308short, null, false);
                V v2 = new V(o2, str19, "SHENASE_S", this.f301import, JG.m112new(o), true);
                V v3 = new V(o2, str19, "SHENASE_H", this.f303long, JG.m108if(o), true);
                V v4 = new V(o2, str19, "SHENASE_D", K7.this.f279if, JG.m109int(o), true);
                if (v2.m346for(o) || v3.m346for(o) || v4.m346for(o) || v.m346for(o)) {
                    i2 |= 4;
                }
                cc.m26do("maliciousAppStatus", "mas");
                i = i2;
            }
            this.f300if = i;
            this.f307return = str18;
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Removed duplicated region for block: B:100:0x03fb  */
        /* JADX WARNING: Removed duplicated region for block: B:101:0x03fe  */
        /* JADX WARNING: Removed duplicated region for block: B:104:0x0418  */
        /* JADX WARNING: Removed duplicated region for block: B:105:0x041b  */
        /* JADX WARNING: Removed duplicated region for block: B:108:0x042c  */
        /* JADX WARNING: Removed duplicated region for block: B:109:0x042f  */
        /* JADX WARNING: Removed duplicated region for block: B:112:0x043e  */
        /* JADX WARNING: Removed duplicated region for block: B:113:0x0441  */
        /* JADX WARNING: Removed duplicated region for block: B:116:0x044a  */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x00a5  */
        /* JADX WARNING: Removed duplicated region for block: B:152:0x0559  */
        /* JADX WARNING: Removed duplicated region for block: B:153:0x055c  */
        /* JADX WARNING: Removed duplicated region for block: B:156:0x0569  */
        /* JADX WARNING: Removed duplicated region for block: B:157:0x0574  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x00ae  */
        /* JADX WARNING: Removed duplicated region for block: B:160:0x057f  */
        /* JADX WARNING: Removed duplicated region for block: B:161:0x058a  */
        /* JADX WARNING: Removed duplicated region for block: B:164:0x0595  */
        /* JADX WARNING: Removed duplicated region for block: B:165:0x05a0  */
        /* JADX WARNING: Removed duplicated region for block: B:168:0x05ab  */
        /* JADX WARNING: Removed duplicated region for block: B:169:0x05b6  */
        /* JADX WARNING: Removed duplicated region for block: B:172:0x05c1  */
        /* JADX WARNING: Removed duplicated region for block: B:173:0x05cc  */
        /* JADX WARNING: Removed duplicated region for block: B:176:0x0600  */
        /* JADX WARNING: Removed duplicated region for block: B:179:0x061d  */
        /* JADX WARNING: Removed duplicated region for block: B:180:0x062a  */
        /* JADX WARNING: Removed duplicated region for block: B:189:0x0671  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x00c1  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x00e0  */
        /* JADX WARNING: Removed duplicated region for block: B:205:0x06db  */
        /* JADX WARNING: Removed duplicated region for block: B:208:0x06f8  */
        /* JADX WARNING: Removed duplicated region for block: B:209:0x06fb  */
        /* JADX WARNING: Removed duplicated region for block: B:212:0x0714  */
        /* JADX WARNING: Removed duplicated region for block: B:213:0x0722  */
        /* JADX WARNING: Removed duplicated region for block: B:224:0x0753  */
        /* JADX WARNING: Removed duplicated region for block: B:227:0x0767  */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x013c  */
        /* JADX WARNING: Removed duplicated region for block: B:232:0x0793  */
        /* JADX WARNING: Removed duplicated region for block: B:237:0x07b5  */
        /* JADX WARNING: Removed duplicated region for block: B:238:0x07c4  */
        /* JADX WARNING: Removed duplicated region for block: B:256:0x083e  */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x017e  */
        /* JADX WARNING: Removed duplicated region for block: B:269:0x0894  */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x01b3  */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x01df  */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x0297  */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x029e  */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x02ba  */
        /* JADX WARNING: Removed duplicated region for block: B:41:0x02bd  */
        /* JADX WARNING: Removed duplicated region for block: B:44:0x02e9  */
        /* JADX WARNING: Removed duplicated region for block: B:77:0x0380  */
        /* JADX WARNING: Removed duplicated region for block: B:79:0x0386  */
        /* JADX WARNING: Removed duplicated region for block: B:82:0x0390  */
        /* JADX WARNING: Removed duplicated region for block: B:85:0x03aa  */
        /* JADX WARNING: Removed duplicated region for block: B:86:0x03ac  */
        /* JADX WARNING: Removed duplicated region for block: B:88:0x03af  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0066  */
        /* JADX WARNING: Removed duplicated region for block: B:96:0x03dc  */
        /* JADX WARNING: Removed duplicated region for block: B:97:0x03df  */
        /* renamed from: do reason: not valid java name */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.threatmetrix.TrustDefender.internal.X m137do(com.threatmetrix.TrustDefender.internal.CG r26, java.lang.String r27, java.lang.String r28, com.threatmetrix.TrustDefender.internal.K7.O r29, com.threatmetrix.TrustDefender.internal.K7.L r30, long r31, java.util.Map<java.lang.String, java.lang.String> r33) throws java.lang.InterruptedException {
            /*
                r25 = this;
                r1 = r25
                r2 = r26
                r3 = r27
                r4 = r28
                r5 = r29
                r6 = r30
                r7 = r33
                java.lang.String r8 = ""
                java.lang.String r9 = ""
                com.threatmetrix.TrustDefender.internal.CC r10 = new com.threatmetrix.TrustDefender.internal.CC
                r10.<init>()
                com.threatmetrix.TrustDefender.internal.K7 r11 = com.threatmetrix.TrustDefender.internal.K7.this
                java.util.concurrent.atomic.AtomicLong r11 = r11.f271break
                long r11 = r11.get()
                r13 = 16384(0x4000, double:8.0948E-320)
                long r15 = r11 & r13
                r11 = 0
                int r13 = (r15 > r11 ? 1 : (r15 == r11 ? 0 : -1))
                r15 = 1
                if (r13 == 0) goto L_0x004b
                com.threatmetrix.TrustDefender.internal.K7 r13 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r13 = r13.f280int
                java.util.ArrayList<java.lang.String> r14 = r2.f77new
                java.util.List r13 = com.threatmetrix.TrustDefender.internal.P.m258if(r13, r14)
                boolean r14 = r13.isEmpty()
                if (r14 != 0) goto L_0x004b
                int r8 = r13.size()
                java.lang.String r8 = java.lang.String.valueOf(r8)
                java.lang.String r14 = ";"
                java.lang.String r14 = com.threatmetrix.TrustDefender.internal.NK.m218int(r13, r14, r15)
                goto L_0x004c
            L_0x004b:
                r14 = 0
            L_0x004c:
                java.lang.String r13 = "Check URLs"
                java.lang.String r15 = "cu"
                r10.m26do(r13, r15)
                com.threatmetrix.TrustDefender.internal.K7 r13 = com.threatmetrix.TrustDefender.internal.K7.this
                java.util.concurrent.atomic.AtomicLong r13 = r13.f271break
                long r15 = r13.get()
                r17 = 4194304(0x400000, double:2.0722615E-317)
                long r19 = r15 & r17
                int r13 = (r19 > r11 ? 1 : (r19 == r11 ? 0 : -1))
                if (r13 == 0) goto L_0x0088
                com.threatmetrix.TrustDefender.internal.K7 r13 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r13 = r13.f280int
                java.util.ArrayList<java.lang.String> r15 = r2.f68break
                java.util.List r13 = com.threatmetrix.TrustDefender.internal.P.m258if(r13, r15)
                java.util.ArrayList<java.lang.String> r15 = r2.f68break
                int r15 = r15.size()
                if (r15 <= 0) goto L_0x0088
                int r9 = r13.size()
                java.lang.String r9 = java.lang.String.valueOf(r9)
                java.lang.String r15 = ";"
                r11 = 1
                java.lang.String r12 = com.threatmetrix.TrustDefender.internal.NK.m218int(r13, r15, r11)
                goto L_0x0089
            L_0x0088:
                r12 = 0
            L_0x0089:
                java.lang.String r11 = "Check EmPaths"
                java.lang.String r13 = "ce"
                r10.m26do(r11, r13)
                com.threatmetrix.TrustDefender.internal.K7 r11 = com.threatmetrix.TrustDefender.internal.K7.this
                java.util.concurrent.atomic.AtomicLong r11 = r11.f271break
                long r15 = r11.get()
                r17 = 33554432(0x2000000, double:1.6578092E-316)
                long r19 = r15 & r17
                r15 = 0
                int r11 = (r19 > r15 ? 1 : (r19 == r15 ? 0 : -1))
                if (r11 == 0) goto L_0x00ae
                com.threatmetrix.TrustDefender.internal.K7 r11 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r11 = r11.f280int
                java.lang.String r11 = com.threatmetrix.TrustDefender.internal.P.m273void(r11)
                goto L_0x00b0
            L_0x00ae:
                java.lang.String r11 = "Could not check"
            L_0x00b0:
                com.threatmetrix.TrustDefender.internal.X r13 = new com.threatmetrix.TrustDefender.internal.X
                r13.<init>()
                r15 = 255(0xff, float:3.57E-43)
                r13.f607if = r15
                int r15 = r1.f305new
                r21 = r10
                int r10 = r1.f292do
                if (r15 < r10) goto L_0x00e0
                java.lang.String r10 = "f"
                java.lang.StringBuilder r15 = new java.lang.StringBuilder
                r15.<init>()
                int r7 = r1.f305new
                r15.append(r7)
                java.lang.String r7 = "x"
                r15.append(r7)
                int r7 = r1.f292do
                r15.append(r7)
                java.lang.String r7 = r15.toString()
                r15 = 0
                r13.m374for(r10, r7, r15)
                goto L_0x00fe
            L_0x00e0:
                java.lang.String r7 = "f"
                java.lang.StringBuilder r10 = new java.lang.StringBuilder
                r10.<init>()
                int r15 = r1.f292do
                r10.append(r15)
                java.lang.String r15 = "x"
                r10.append(r15)
                int r15 = r1.f305new
                r10.append(r15)
                java.lang.String r10 = r10.toString()
                r15 = 0
                r13.m374for(r7, r10, r15)
            L_0x00fe:
                java.lang.String r7 = "w"
                java.lang.String r10 = r2.f75if
                r13.m374for(r7, r10, r15)
                java.lang.String r7 = "c"
                int r10 = r1.f302int
                java.lang.String r10 = java.lang.String.valueOf(r10)
                r13.m374for(r7, r10, r15)
                java.lang.String r7 = "z"
                int r10 = r1.f298for
                java.lang.String r10 = java.lang.String.valueOf(r10)
                r13.m374for(r7, r10, r15)
                java.lang.String r7 = "lh"
                com.threatmetrix.TrustDefender.internal.K7 r10 = com.threatmetrix.TrustDefender.internal.K7.this
                java.lang.String r10 = r10.f274char
                r15 = 1
                r13.m374for(r7, r10, r15)
                java.lang.String r7 = "dr"
                com.threatmetrix.TrustDefender.internal.K7 r10 = com.threatmetrix.TrustDefender.internal.K7.this
                java.lang.String r10 = r10.f272byte
                r13.m374for(r7, r10, r15)
                com.threatmetrix.TrustDefender.internal.K7 r7 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.K r7 = r7.f282try
                java.lang.String r7 = r7.f262new
                java.lang.String r10 = "0"
                boolean r7 = r7.equals(r10)
                if (r7 != 0) goto L_0x015d
                java.lang.String r7 = "p"
                com.threatmetrix.TrustDefender.internal.K7 r10 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.K r10 = r10.f282try
                java.lang.String r10 = r10.f261int
                r13.m374for(r7, r10, r15)
                java.lang.String r7 = "pl"
                com.threatmetrix.TrustDefender.internal.K7 r10 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.K r10 = r10.f282try
                java.lang.String r10 = r10.f262new
                r13.m374for(r7, r10, r15)
                java.lang.String r7 = "ph"
                com.threatmetrix.TrustDefender.internal.K7 r10 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.K r10 = r10.f282try
                java.lang.String r10 = r10.f259for
                r13.m374for(r7, r10, r15)
            L_0x015d:
                java.lang.String r7 = "hh"
                java.lang.StringBuilder r10 = new java.lang.StringBuilder
                r10.<init>()
                r10.append(r3)
                r10.append(r4)
                java.lang.String r10 = r10.toString()
                java.lang.String r10 = com.threatmetrix.TrustDefender.internal.NK.m208do(r10)
                r15 = 0
                r13.m374for(r7, r10, r15)
                com.threatmetrix.TrustDefender.internal.K7 r7 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.K r7 = r7.f282try
                int r7 = r7.f256char
                if (r7 <= 0) goto L_0x0198
                java.lang.String r7 = "mt"
                com.threatmetrix.TrustDefender.internal.K7 r10 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.K r10 = r10.f282try
                java.lang.String r10 = r10.f258else
                r13.m374for(r7, r10, r15)
                java.lang.String r7 = "mn"
                com.threatmetrix.TrustDefender.internal.K7 r10 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.K r10 = r10.f282try
                int r10 = r10.f256char
                java.lang.String r10 = java.lang.String.valueOf(r10)
                r13.m374for(r7, r10, r15)
            L_0x0198:
                java.lang.String r7 = "mdf"
                java.lang.String r10 = r1.f296final
                r15 = 1
                r13.m374for(r7, r10, r15)
                java.lang.String r7 = "mds"
                java.lang.String r10 = r1.f291const
                r13.m374for(r7, r10, r15)
                com.threatmetrix.TrustDefender.internal.P.m245do()
                java.lang.String r7 = "imei"
                java.lang.String r10 = r1.f303long
                r13.m374for(r7, r10, r15)
                if (r5 == 0) goto L_0x01df
                java.lang.String r7 = "tdlat"
                r22 = r11
                double r10 = r5.f324for
                java.lang.String r10 = java.lang.String.valueOf(r10)
                r11 = 0
                r13.m374for(r7, r10, r11)
                java.lang.String r7 = "tdlon"
                double r2 = r5.f327new
                java.lang.String r2 = java.lang.String.valueOf(r2)
                r13.m374for(r7, r2, r11)
                java.lang.String r2 = "tdlacc"
                java.lang.Float r3 = r5.f323do
                if (r3 == 0) goto L_0x01d9
                java.lang.Float r3 = r5.f323do
                java.lang.String r3 = java.lang.String.valueOf(r3)
                goto L_0x01db
            L_0x01d9:
                java.lang.String r3 = ""
            L_0x01db:
                r13.m374for(r2, r3, r11)
                goto L_0x01e2
            L_0x01df:
                r22 = r11
                r11 = 0
            L_0x01e2:
                java.lang.String r2 = "ah"
                java.lang.String r3 = r1.f297float
                r13.m374for(r2, r3, r11)
                java.lang.String r2 = "lq"
                com.threatmetrix.TrustDefender.internal.K7 r3 = com.threatmetrix.TrustDefender.internal.K7.this
                java.lang.String r3 = r3.f276else
                r13.m374for(r2, r3, r11)
                java.lang.String r2 = "ftsn"
                java.lang.String r3 = r1.f314void
                r13.m374for(r2, r3, r11)
                java.lang.String r2 = "fts"
                java.lang.String r3 = r1.f299goto
                r7 = 1
                r13.m374for(r2, r3, r7)
                java.lang.String r2 = "aov"
                java.lang.String r3 = com.threatmetrix.TrustDefender.internal.N.I.C0012I.f389int
                r13.m374for(r2, r3, r7)
                java.lang.String r2 = "al"
                java.lang.String r3 = com.threatmetrix.TrustDefender.internal.TB.f533if
                r13.m374for(r2, r3, r7)
                java.lang.String r2 = "alo"
                java.lang.String r3 = r1.f288catch
                r13.m374for(r2, r3, r7)
                java.lang.String r2 = "ab"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r10 = com.threatmetrix.TrustDefender.internal.N.I.f383new
                r3.append(r10)
                java.lang.String r10 = ", "
                r3.append(r10)
                java.lang.String r10 = com.threatmetrix.TrustDefender.internal.N.I.f370break
                r3.append(r10)
                java.lang.String r3 = r3.toString()
                r13.m374for(r2, r3, r7)
                java.lang.String r2 = "ad"
                java.lang.String r3 = com.threatmetrix.TrustDefender.internal.N.I.f377else
                r13.m374for(r2, r3, r7)
                java.lang.String r2 = "am"
                java.lang.String r3 = com.threatmetrix.TrustDefender.internal.N.I.f374char
                r13.m374for(r2, r3, r7)
                java.lang.String r2 = "aos"
                java.lang.String r3 = com.threatmetrix.TrustDefender.internal.P.m246else()
                r13.m374for(r2, r3, r7)
                java.lang.String r2 = "cos"
                java.lang.String r3 = com.threatmetrix.TrustDefender.internal.P.m235byte()
                r13.m374for(r2, r3, r7)
                java.lang.String r2 = "fg"
                java.lang.String r3 = r1.f311this
                r7 = 0
                r13.m374for(r2, r3, r7)
                java.lang.String r2 = "ls"
                java.lang.String r3 = r1.f285break
                r13.m374for(r2, r3, r7)
                java.lang.String r2 = "gr"
                r13.m374for(r2, r8, r7)
                java.lang.String r2 = "grr"
                r3 = 1024(0x400, float:1.435E-42)
                java.lang.Integer r8 = java.lang.Integer.valueOf(r3)
                java.util.HashMap<java.lang.String, java.lang.Integer> r10 = r13.f606do
                r10.put(r2, r8)
                r13.m374for(r2, r14, r7)
                java.lang.String r2 = "mr"
                r13.m374for(r2, r9, r7)
                java.lang.String r2 = "mrr"
                java.lang.Integer r8 = java.lang.Integer.valueOf(r3)
                java.util.HashMap<java.lang.String, java.lang.Integer> r9 = r13.f606do
                r9.put(r2, r8)
                r13.m374for(r2, r12, r7)
                java.lang.String r2 = "at"
                java.lang.String r8 = "agent_mobile"
                r13.m374for(r2, r8, r7)
                java.lang.String r2 = "se"
                com.threatmetrix.TrustDefender.internal.K7$W r8 = r1.f306public
                if (r8 == 0) goto L_0x029e
                com.threatmetrix.TrustDefender.internal.K7$W r8 = r1.f306public
                java.lang.String r14 = r8.identifier()
                goto L_0x029f
            L_0x029e:
                r14 = 0
            L_0x029f:
                r13.m374for(r2, r14, r7)
                java.lang.String r2 = "av"
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                java.lang.String r8 = com.threatmetrix.TrustDefender.internal.K7.f270new
                r7.append(r8)
                com.threatmetrix.TrustDefender.internal.K7 r8 = com.threatmetrix.TrustDefender.internal.K7.this
                java.lang.String r8 = r8.f278goto
                boolean r8 = r8.isEmpty()
                if (r8 == 0) goto L_0x02bd
                java.lang.String r8 = ""
                goto L_0x02d1
            L_0x02bd:
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                java.lang.String r9 = " : "
                r8.<init>(r9)
                com.threatmetrix.TrustDefender.internal.K7 r9 = com.threatmetrix.TrustDefender.internal.K7.this
                java.lang.String r9 = r9.f278goto
                r8.append(r9)
                java.lang.String r8 = r8.toString()
            L_0x02d1:
                r7.append(r8)
                java.lang.String r7 = r7.toString()
                r8 = 0
                r13.m374for(r2, r7, r8)
                java.lang.String r2 = "mex2"
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                java.lang.String r8 = "{"
                r7.<init>(r8)
                com.threatmetrix.TrustDefender.internal.K7$I r8 = r1.f304native
                if (r8 == 0) goto L_0x0374
                com.threatmetrix.TrustDefender.internal.K7$I r8 = r1.f304native
                int r8 = r8.f318if
                if (r8 < 0) goto L_0x02fd
                java.lang.String r8 = "\"mlc\":"
                r7.append(r8)
                com.threatmetrix.TrustDefender.internal.K7$I r8 = r1.f304native
                int r8 = r8.f318if
                r7.append(r8)
                r8 = 0
                goto L_0x02fe
            L_0x02fd:
                r8 = 1
            L_0x02fe:
                com.threatmetrix.TrustDefender.internal.K7$I r9 = r1.f304native
                int r9 = r9.f319int
                if (r9 < 0) goto L_0x0319
                if (r8 == 0) goto L_0x0308
                r8 = 0
                goto L_0x030d
            L_0x0308:
                java.lang.String r9 = ","
                r7.append(r9)
            L_0x030d:
                java.lang.String r9 = "\"mls\":"
                r7.append(r9)
                com.threatmetrix.TrustDefender.internal.K7$I r9 = r1.f304native
                int r9 = r9.f319int
                r7.append(r9)
            L_0x0319:
                com.threatmetrix.TrustDefender.internal.K7$I r9 = r1.f304native
                int r9 = r9.f317for
                if (r9 < 0) goto L_0x0334
                if (r8 == 0) goto L_0x0323
                r8 = 0
                goto L_0x0328
            L_0x0323:
                java.lang.String r9 = ","
                r7.append(r9)
            L_0x0328:
                java.lang.String r9 = "\"slc\":"
                r7.append(r9)
                com.threatmetrix.TrustDefender.internal.K7$I r9 = r1.f304native
                int r9 = r9.f317for
                r7.append(r9)
            L_0x0334:
                com.threatmetrix.TrustDefender.internal.K7$I r9 = r1.f304native
                int r9 = r9.f320new
                if (r9 < 0) goto L_0x034f
                if (r8 == 0) goto L_0x033e
                r8 = 0
                goto L_0x0343
            L_0x033e:
                java.lang.String r9 = ","
                r7.append(r9)
            L_0x0343:
                java.lang.String r9 = "\"sls\":"
                r7.append(r9)
                com.threatmetrix.TrustDefender.internal.K7$I r9 = r1.f304native
                int r9 = r9.f320new
                r7.append(r9)
            L_0x034f:
                com.threatmetrix.TrustDefender.internal.K7$I r9 = r1.f304native
                boolean r9 = r9.f316do
                if (r9 != 0) goto L_0x0361
                com.threatmetrix.TrustDefender.internal.K7$I r9 = r1.f304native
                int r9 = r9.f318if
                if (r9 >= 0) goto L_0x0361
                com.threatmetrix.TrustDefender.internal.K7$I r9 = r1.f304native
                int r9 = r9.f319int
                if (r9 < 0) goto L_0x0374
            L_0x0361:
                if (r8 != 0) goto L_0x0368
                java.lang.String r8 = ","
                r7.append(r8)
            L_0x0368:
                java.lang.String r8 = "\"tda\":"
                r7.append(r8)
                com.threatmetrix.TrustDefender.internal.K7$I r8 = r1.f304native
                boolean r8 = r8.f316do
                r7.append(r8)
            L_0x0374:
                java.lang.String r8 = "}"
                r7.append(r8)
                int r8 = r7.length()
                r9 = 2
                if (r8 <= r9) goto L_0x0386
                java.lang.String r7 = r7.toString()
            L_0x0384:
                r8 = 0
                goto L_0x0389
            L_0x0386:
                java.lang.String r7 = ""
                goto L_0x0384
            L_0x0389:
                r13.m374for(r2, r7, r8)
                java.lang.String r2 = r1.f307return
                if (r2 == 0) goto L_0x03a0
                java.lang.String r2 = "nci"
                java.lang.String r7 = r1.f307return
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                java.util.HashMap<java.lang.String, java.lang.Integer> r10 = r13.f606do
                r10.put(r2, r3)
                r13.m374for(r2, r7, r8)
            L_0x03a0:
                com.threatmetrix.TrustDefender.internal.K7 r2 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r2 = r2.f280int
                boolean r2 = com.threatmetrix.TrustDefender.internal.P.m263int(r2)
                if (r2 == 0) goto L_0x03ac
                r2 = 2
                goto L_0x03ad
            L_0x03ac:
                r2 = 0
            L_0x03ad:
                if (r5 == 0) goto L_0x03bb
                boolean r3 = r5.f326int
                if (r3 == 0) goto L_0x03b5
                r2 = r2 | 1
            L_0x03b5:
                boolean r3 = r5.f325if
                if (r3 == 0) goto L_0x03bb
                r2 = r2 | 4
            L_0x03bb:
                java.lang.String r3 = "mlst"
                java.lang.String r2 = java.lang.String.valueOf(r2)
                r5 = 0
                r13.m374for(r3, r2, r5)
                java.lang.String r2 = "mex6"
                com.threatmetrix.TrustDefender.internal.K7 r3 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r3 = r3.f280int
                int r3 = com.threatmetrix.TrustDefender.internal.P.m243do(r3)
                java.lang.String r3 = java.lang.String.valueOf(r3)
                r13.m374for(r2, r3, r5)
                java.lang.String r2 = "mex7"
                java.lang.String r3 = r1.f290class
                if (r3 != 0) goto L_0x03df
                java.lang.String r3 = ""
                goto L_0x03e1
            L_0x03df:
                java.lang.String r3 = r1.f290class
            L_0x03e1:
                r13.m374for(r2, r3, r5)
                java.lang.String r2 = "mex10"
                com.threatmetrix.TrustDefender.internal.K7 r3 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r3 = r3.f280int
                java.lang.String r3 = com.threatmetrix.TrustDefender.internal.P.m271try(r3)
                r13.m374for(r2, r3, r5)
                java.lang.String r2 = "abt"
                long r7 = r1.f286byte
                r10 = 0
                int r3 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
                if (r3 != 0) goto L_0x03fe
                java.lang.String r3 = ""
                goto L_0x0404
            L_0x03fe:
                long r7 = r1.f286byte
                java.lang.String r3 = java.lang.String.valueOf(r7)
            L_0x0404:
                r13.m374for(r2, r3, r5)
                java.lang.String r2 = "anv"
                java.lang.String r3 = r1.f294else
                r13.m374for(r2, r3, r5)
                java.lang.String r2 = "afs"
                long r7 = r1.f289char
                r10 = 0
                int r3 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
                if (r3 != 0) goto L_0x041b
                java.lang.String r3 = ""
                goto L_0x0421
            L_0x041b:
                long r7 = r1.f289char
                java.lang.String r3 = java.lang.String.valueOf(r7)
            L_0x0421:
                r13.m374for(r2, r3, r5)
                java.lang.String r2 = "ats"
                long r7 = r1.f287case
                int r3 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
                if (r3 != 0) goto L_0x042f
                java.lang.String r3 = ""
                goto L_0x0435
            L_0x042f:
                long r7 = r1.f287case
                java.lang.String r3 = java.lang.String.valueOf(r7)
            L_0x0435:
                r13.m374for(r2, r3, r5)
                java.lang.String r2 = "aci"
                java.lang.String r3 = r1.f310super
                if (r3 != 0) goto L_0x0441
                java.lang.String r3 = ""
                goto L_0x0443
            L_0x0441:
                java.lang.String r3 = r1.f310super
            L_0x0443:
                r13.m374for(r2, r3, r5)
                com.threatmetrix.TrustDefender.internal.Z2 r2 = r1.f293double
                if (r2 == 0) goto L_0x04dc
                java.lang.String r2 = "wbs"
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f729new
                if (r3 != 0) goto L_0x0456
                java.lang.String r3 = ""
            L_0x0454:
                r5 = 0
                goto L_0x045b
            L_0x0456:
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f729new
                goto L_0x0454
            L_0x045b:
                r13.m374for(r2, r3, r5)
                java.lang.String r2 = "wss"
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f724do
                if (r3 != 0) goto L_0x0469
                java.lang.String r3 = ""
                goto L_0x046d
            L_0x0469:
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f724do
            L_0x046d:
                r13.m374for(r2, r3, r5)
                java.lang.String r2 = "wrr"
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f728int
                if (r3 != 0) goto L_0x047b
                java.lang.String r3 = ""
                goto L_0x047f
            L_0x047b:
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f728int
            L_0x047f:
                r13.m374for(r2, r3, r5)
                java.lang.String r2 = "wc"
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f727if
                if (r3 != 0) goto L_0x048d
                java.lang.String r3 = ""
                goto L_0x0491
            L_0x048d:
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f727if
            L_0x0491:
                r13.m374for(r2, r3, r5)
                java.lang.String r2 = "ipv4"
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f726for
                if (r3 != 0) goto L_0x049f
                java.lang.String r3 = ""
                goto L_0x04a3
            L_0x049f:
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f726for
            L_0x04a3:
                r13.m374for(r2, r3, r5)
                java.lang.String r2 = "ipv6"
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f723char
                if (r3 != 0) goto L_0x04b1
                java.lang.String r3 = ""
                goto L_0x04b5
            L_0x04b1:
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f723char
            L_0x04b5:
                r13.m374for(r2, r3, r5)
                java.lang.String r2 = "mac"
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f730try
                if (r3 != 0) goto L_0x04c3
                java.lang.String r3 = ""
                goto L_0x04c7
            L_0x04c3:
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f730try
            L_0x04c7:
                r13.m374for(r2, r3, r5)
                java.lang.String r2 = "wfs"
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f725else
                if (r3 != 0) goto L_0x04d5
                java.lang.String r3 = ""
                goto L_0x04d9
            L_0x04d5:
                com.threatmetrix.TrustDefender.internal.Z2 r3 = r1.f293double
                java.lang.String r3 = r3.f725else
            L_0x04d9:
                r13.m374for(r2, r3, r5)
            L_0x04dc:
                long r2 = com.threatmetrix.TrustDefender.internal.N.S.m172if()
                java.lang.String r5 = "atr"
                com.threatmetrix.TrustDefender.internal.K7 r7 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r7 = r7.f280int
                java.lang.String r7 = com.threatmetrix.TrustDefender.internal.P.m242const(r7)
                com.threatmetrix.TrustDefender.internal.K7 r8 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r8 = r8.f280int
                java.lang.String r8 = com.threatmetrix.TrustDefender.internal.P.m249float(r8)
                com.threatmetrix.TrustDefender.internal.K7 r10 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r10 = r10.f280int
                java.lang.String r10 = com.threatmetrix.TrustDefender.internal.P.m248final(r10)
                java.lang.String r11 = com.threatmetrix.TrustDefender.internal.P.m272void()
                com.threatmetrix.TrustDefender.internal.PH r12 = com.threatmetrix.TrustDefender.internal.PH.m275do()
                java.lang.String r12 = r12.m304try()
                java.lang.StringBuilder r14 = new java.lang.StringBuilder
                java.lang.String r15 = "{\"opts\":"
                r14.<init>(r15)
                com.threatmetrix.TrustDefender.internal.K7 r15 = com.threatmetrix.TrustDefender.internal.K7.this
                r23 = r10
                long r9 = r15.f277for
                r14.append(r9)
                java.lang.String r9 = ",\"dyOpt\":"
                r14.append(r9)
                com.threatmetrix.TrustDefender.internal.K7 r9 = com.threatmetrix.TrustDefender.internal.K7.this
                java.util.concurrent.atomic.AtomicLong r9 = r9.f271break
                r14.append(r9)
                java.lang.String r9 = ",\"psi\":"
                r14.append(r9)
                int r9 = r6.f322new
                r14.append(r9)
                java.lang.String r9 = ",\"pr\":"
                r14.append(r9)
                long r9 = r1.f313try
                r14.append(r9)
                java.lang.String r9 = ",\"cpi\":"
                r14.append(r9)
                r24 = r5
                long r4 = r2 - r31
                r14.append(r4)
                java.lang.String r2 = ",\"lpi\":"
                r14.append(r2)
                long r2 = r6.f321int
                r14.append(r2)
                java.lang.String r2 = ",\"ori\":"
                r14.append(r2)
                int r2 = r1.f305new
                int r3 = r1.f292do
                if (r2 < r3) goto L_0x055c
                java.lang.String r2 = "landscape"
                goto L_0x055e
            L_0x055c:
                java.lang.String r2 = "portrait"
            L_0x055e:
                r14.append(r2)
                r2 = r23
                boolean r3 = com.threatmetrix.TrustDefender.internal.NK.m203byte(r2)
                if (r3 == 0) goto L_0x0574
                java.lang.String r3 = ",\"debug\":"
                java.lang.String r2 = java.lang.String.valueOf(r2)
                java.lang.String r2 = r3.concat(r2)
                goto L_0x0576
            L_0x0574:
                java.lang.String r2 = ""
            L_0x0576:
                r14.append(r2)
                boolean r2 = com.threatmetrix.TrustDefender.internal.NK.m203byte(r11)
                if (r2 == 0) goto L_0x058a
                java.lang.String r2 = ",\"dbgj\":"
                java.lang.String r3 = java.lang.String.valueOf(r11)
                java.lang.String r2 = r2.concat(r3)
                goto L_0x058c
            L_0x058a:
                java.lang.String r2 = ""
            L_0x058c:
                r14.append(r2)
                boolean r2 = com.threatmetrix.TrustDefender.internal.NK.m203byte(r12)
                if (r2 == 0) goto L_0x05a0
                java.lang.String r2 = ",\"dbgc\":"
                java.lang.String r3 = java.lang.String.valueOf(r12)
                java.lang.String r2 = r2.concat(r3)
                goto L_0x05a2
            L_0x05a0:
                java.lang.String r2 = ""
            L_0x05a2:
                r14.append(r2)
                boolean r2 = com.threatmetrix.TrustDefender.internal.NK.m203byte(r7)
                if (r2 == 0) goto L_0x05b6
                java.lang.String r2 = ",\"adb\":"
                java.lang.String r3 = java.lang.String.valueOf(r7)
                java.lang.String r2 = r2.concat(r3)
                goto L_0x05b8
            L_0x05b6:
                java.lang.String r2 = ""
            L_0x05b8:
                r14.append(r2)
                boolean r2 = com.threatmetrix.TrustDefender.internal.NK.m203byte(r8)
                if (r2 == 0) goto L_0x05cc
                java.lang.String r2 = ",\"dev\":"
                java.lang.String r3 = java.lang.String.valueOf(r8)
                java.lang.String r2 = r2.concat(r3)
                goto L_0x05ce
            L_0x05cc:
                java.lang.String r2 = ""
            L_0x05ce:
                r14.append(r2)
                java.lang.String r2 = "}"
                r14.append(r2)
                java.lang.String r2 = r14.toString()
                r3 = r24
                r6 = 0
                r13.m374for(r3, r2, r6)
                java.lang.String r2 = "apd"
                java.lang.String r3 = java.lang.String.valueOf(r4)
                r13.m374for(r2, r3, r6)
                com.threatmetrix.TrustDefender.internal.X r2 = new com.threatmetrix.TrustDefender.internal.X
                r2.<init>()
                java.lang.String r3 = "org_id"
                r4 = r27
                r2.m374for(r3, r4, r6)
                java.lang.String r3 = "session_id"
                r4 = r28
                r2.m374for(r3, r4, r6)
                int r3 = r1.f300if
                if (r3 == 0) goto L_0x060b
                java.lang.String r3 = "mlapp"
                int r5 = r1.f300if
                java.lang.String r5 = java.lang.String.valueOf(r5)
                r13.m374for(r3, r5, r6)
            L_0x060b:
                com.threatmetrix.TrustDefender.internal.K7 r3 = com.threatmetrix.TrustDefender.internal.K7.this
                java.util.concurrent.atomic.AtomicLong r3 = r3.f271break
                long r5 = r3.get()
                r7 = 12288(0x3000, double:6.071E-320)
                long r9 = r5 & r7
                int r3 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
                if (r3 != 0) goto L_0x062a
                com.threatmetrix.TrustDefender.internal.PH r3 = com.threatmetrix.TrustDefender.internal.PH.m275do()
                com.threatmetrix.TrustDefender.internal.K7 r5 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r5 = r5.f280int
                com.threatmetrix.TrustDefender.internal.NN[] r14 = r3.m287for(r5)
                goto L_0x066d
            L_0x062a:
                com.threatmetrix.TrustDefender.internal.K7 r3 = com.threatmetrix.TrustDefender.internal.K7.this
                java.util.concurrent.atomic.AtomicLong r3 = r3.f271break
                long r5 = r3.get()
                r7 = 8192(0x2000, double:4.0474E-320)
                long r9 = r5 & r7
                r5 = 0
                int r3 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
                if (r3 == 0) goto L_0x064b
                com.threatmetrix.TrustDefender.internal.PH r3 = com.threatmetrix.TrustDefender.internal.PH.m275do()
                com.threatmetrix.TrustDefender.internal.K7 r5 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r5 = r5.f280int
                com.threatmetrix.TrustDefender.internal.NN[] r14 = r3.m300int(r5)
                goto L_0x066d
            L_0x064b:
                com.threatmetrix.TrustDefender.internal.K7 r3 = com.threatmetrix.TrustDefender.internal.K7.this
                java.util.concurrent.atomic.AtomicLong r3 = r3.f271break
                long r5 = r3.get()
                r7 = 4096(0x1000, double:2.0237E-320)
                long r9 = r5 & r7
                r5 = 0
                int r3 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
                if (r3 == 0) goto L_0x066c
                com.threatmetrix.TrustDefender.internal.PH r3 = com.threatmetrix.TrustDefender.internal.PH.m275do()
                com.threatmetrix.TrustDefender.internal.K7 r5 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r5 = r5.f280int
                com.threatmetrix.TrustDefender.internal.NN[] r14 = r3.m303new(r5)
                goto L_0x066d
            L_0x066c:
                r14 = 0
            L_0x066d:
                java.lang.String r3 = ""
                if (r14 == 0) goto L_0x06db
                r5 = r26
                short[] r6 = r5.f71char
                r7 = 43
                if (r6 == 0) goto L_0x0687
                short[] r6 = r5.f71char
                int r6 = r6.length
                int r8 = com.threatmetrix.TrustDefender.internal.NN.m228if(r6)
                int r6 = r6 * r8
                int r6 = r6 / 10
                r8 = 1
                int r6 = r6 + r8
                int r7 = r7 + r6
            L_0x0687:
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                int r8 = r14.length
                int r8 = r8 * r7
                r6.<init>(r8)
                int r7 = r14.length
                r8 = r3
                r3 = 0
                r15 = 0
            L_0x0693:
                if (r3 >= r7) goto L_0x06cd
                r9 = r14[r3]
                int r10 = r6.length()
                if (r10 <= 0) goto L_0x06a2
                java.lang.String r10 = ","
                r6.append(r10)
            L_0x06a2:
                short[] r10 = r5.f71char
                r9.m229if(r6, r10)
                java.util.HashSet<java.lang.String> r10 = r5.f72do
                if (r10 == 0) goto L_0x06ca
                if (r15 != 0) goto L_0x06ca
                java.util.HashSet<java.lang.String> r10 = r5.f72do
                java.lang.String r11 = r9.f469for
                boolean r10 = r10.contains(r11)
                if (r10 == 0) goto L_0x06ca
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                java.lang.String r10 = "lwif="
                r8.<init>(r10)
                java.lang.String r9 = r9.toString()
                r8.append(r9)
                java.lang.String r8 = r8.toString()
                r15 = 1
            L_0x06ca:
                int r3 = r3 + 1
                goto L_0x0693
            L_0x06cd:
                com.threatmetrix.TrustDefender.internal.K7.f269long
                java.lang.String r3 = "ih"
                java.lang.String r6 = r6.toString()
                r7 = 0
                r2.m374for(r3, r6, r7)
                goto L_0x06df
            L_0x06db:
                r5 = r26
                r7 = 0
                r8 = r3
            L_0x06df:
                java.lang.String r3 = "pldec"
                r11 = r22
                r13.m374for(r3, r11, r7)
                java.lang.String r3 = "mex3"
                r13.m374for(r3, r8, r7)
                java.lang.String r3 = "adid"
                java.lang.String r6 = r1.f308short
                r13.m374for(r3, r6, r7)
                java.lang.String r3 = "name"
                java.lang.String r6 = r1.f309static
                if (r6 != 0) goto L_0x06fb
                java.lang.String r6 = ""
                goto L_0x06fd
            L_0x06fb:
                java.lang.String r6 = r1.f309static
            L_0x06fd:
                r13.m374for(r3, r6, r7)
                java.lang.String r3 = "h"
                java.lang.String r6 = "0"
                r2.m374for(r3, r6, r7)
                java.lang.String r3 = "m"
                java.lang.String r6 = "2"
                r2.m374for(r3, r6, r7)
                java.lang.String r3 = ""
                java.lang.String r6 = r1.f315while
                if (r6 == 0) goto L_0x0722
                java.lang.String r6 = "sa_pt"
                java.lang.String r8 = r1.f315while
                r13.m374for(r6, r8, r7)
                java.lang.String r6 = r1.f315while
                java.lang.String r3 = r3.concat(r6)
                goto L_0x074f
            L_0x0722:
                boolean r6 = com.threatmetrix.TrustDefender.internal.P.m233break()
                if (r6 == 0) goto L_0x074f
                com.threatmetrix.TrustDefender.internal.K7 r6 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r6 = r6.f280int
                boolean r6 = com.threatmetrix.TrustDefender.internal.P.m247else(r6)
                if (r6 == 0) goto L_0x074f
                com.threatmetrix.TrustDefender.internal.K7 r6 = com.threatmetrix.TrustDefender.internal.K7.this
                boolean r6 = r6.f275do
                if (r6 == 0) goto L_0x074f
                com.threatmetrix.TrustDefender.internal.K7 r6 = com.threatmetrix.TrustDefender.internal.K7.this
                java.util.concurrent.atomic.AtomicLong r6 = r6.f271break
                long r6 = r6.get()
                r8 = 2097152(0x200000, double:1.0361308E-317)
                long r10 = r6 & r8
                r6 = 0
                int r8 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
                if (r8 == 0) goto L_0x074f
                r1.f284boolean = r4
            L_0x074f:
                java.lang.String r6 = r1.f312throw
                if (r6 == 0) goto L_0x0761
                java.lang.String r6 = "caps"
                java.lang.String r7 = r1.f312throw
                r8 = 0
                r13.m374for(r6, r7, r8)
                java.lang.String r6 = r1.f312throw
                java.lang.String r3 = r3.concat(r6)
            L_0x0761:
                r6 = r3
                r3 = r33
                r7 = 0
                if (r3 == 0) goto L_0x077e
                java.lang.String r8 = "sa_st"
                java.lang.Object r8 = r3.get(r8)
                java.lang.String r8 = (java.lang.String) r8
                boolean r9 = com.threatmetrix.TrustDefender.internal.NK.m203byte(r8)
                if (r9 == 0) goto L_0x077e
                java.lang.String r9 = "sa_st"
                r13.m374for(r9, r8, r7)
                java.lang.String r6 = r6.concat(r8)
            L_0x077e:
                com.threatmetrix.TrustDefender.internal.K7 r8 = com.threatmetrix.TrustDefender.internal.K7.this
                java.util.concurrent.atomic.AtomicLong r8 = r8.f271break
                long r8 = r8.get()
                r10 = 134217728(0x8000000, double:6.63123685E-316)
                long r14 = r8 & r10
                r8 = 0
                int r10 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
                if (r10 == 0) goto L_0x07ab
                com.threatmetrix.TrustDefender.internal.PH r8 = com.threatmetrix.TrustDefender.internal.PH.m275do()
                boolean r8 = r8.f494char
                if (r8 == 0) goto L_0x07ab
                com.threatmetrix.TrustDefender.internal.PH r8 = com.threatmetrix.TrustDefender.internal.PH.m275do()
                java.lang.String r5 = r5.f73else
                com.threatmetrix.TrustDefender.internal.K7 r9 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r9 = r9.f280int
                com.threatmetrix.TrustDefender.internal.DN$L r5 = r8.m290if(r5, r6, r9)
                goto L_0x083c
            L_0x07ab:
                com.threatmetrix.TrustDefender.internal.K7 r8 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r8 = r8.f280int
                boolean r8 = com.threatmetrix.TrustDefender.internal.K5.m131int(r8)
                if (r8 == 0) goto L_0x07c4
                java.lang.String r5 = r5.f73else
                com.threatmetrix.TrustDefender.internal.K7 r8 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r8 = r8.f280int
                com.threatmetrix.TrustDefender.internal.DN$L r5 = com.threatmetrix.TrustDefender.internal.K5.m128if(r5, r8, r6)
                com.threatmetrix.TrustDefender.internal.K7.f269long
                goto L_0x083c
            L_0x07c4:
                com.threatmetrix.TrustDefender.internal.PH r8 = com.threatmetrix.TrustDefender.internal.PH.m275do()
                boolean r8 = r8.f494char
                if (r8 == 0) goto L_0x07de
                com.threatmetrix.TrustDefender.internal.PH r8 = com.threatmetrix.TrustDefender.internal.PH.m275do()
                java.lang.String r5 = r5.f73else
                com.threatmetrix.TrustDefender.internal.K7 r9 = com.threatmetrix.TrustDefender.internal.K7.this
                com.threatmetrix.TrustDefender.internal.P$O r9 = r9.f280int
                com.threatmetrix.TrustDefender.internal.DN$L r5 = r8.m290if(r5, r6, r9)
                com.threatmetrix.TrustDefender.internal.K7.f269long
                goto L_0x083c
            L_0x07de:
                java.lang.String r5 = r5.f73else     // Catch:{ Throwable -> 0x0825 }
                com.threatmetrix.TrustDefender.internal.K7 r8 = com.threatmetrix.TrustDefender.internal.K7.this     // Catch:{ Throwable -> 0x0825 }
                com.threatmetrix.TrustDefender.internal.P$O r8 = r8.f280int     // Catch:{ Throwable -> 0x0825 }
                r9 = 3
                java.lang.Object[] r10 = new java.lang.Object[r9]     // Catch:{ all -> 0x081b }
                r11 = 2
                r10[r11] = r6     // Catch:{ all -> 0x081b }
                r6 = 1
                r10[r6] = r8     // Catch:{ all -> 0x081b }
                r10[r7] = r5     // Catch:{ all -> 0x081b }
                r5 = 42
                r6 = 42552(0xa638, float:5.9628E-41)
                java.lang.Object r5 = com.threatmetrix.TrustDefender.internal.WM.m367for(r7, r5, r6)     // Catch:{ all -> 0x081b }
                java.lang.Class r5 = (java.lang.Class) r5     // Catch:{ all -> 0x081b }
                java.lang.String r6 = "if"
                java.lang.Class[] r8 = new java.lang.Class[r9]     // Catch:{ all -> 0x081b }
                java.lang.Class<java.lang.String> r9 = java.lang.String.class
                r8[r7] = r9     // Catch:{ all -> 0x081b }
                java.lang.Class<com.threatmetrix.TrustDefender.internal.P$O> r9 = com.threatmetrix.TrustDefender.internal.P.O.class
                r11 = 1
                r8[r11] = r9     // Catch:{ all -> 0x081b }
                java.lang.Class<java.lang.String> r9 = java.lang.String.class
                r11 = 2
                r8[r11] = r9     // Catch:{ all -> 0x081b }
                java.lang.reflect.Method r5 = r5.getDeclaredMethod(r6, r8)     // Catch:{ all -> 0x081b }
                r6 = 0
                java.lang.Object r5 = r5.invoke(r6, r10)     // Catch:{ all -> 0x081b }
                com.threatmetrix.TrustDefender.internal.DN$L r5 = (com.threatmetrix.TrustDefender.internal.DN$L) r5     // Catch:{ all -> 0x081b }
                com.threatmetrix.TrustDefender.internal.K7.f269long     // Catch:{ Throwable -> 0x0825 }
                goto L_0x083c
            L_0x081b:
                r0 = move-exception
                r5 = r0
                java.lang.Throwable r6 = r5.getCause()     // Catch:{ Throwable -> 0x0825 }
                if (r6 == 0) goto L_0x0824
                throw r6     // Catch:{ Throwable -> 0x0825 }
            L_0x0824:
                throw r5     // Catch:{ Throwable -> 0x0825 }
            L_0x0825:
                r0 = move-exception
                r5 = r0
                java.lang.String r6 = com.threatmetrix.TrustDefender.internal.K7.f269long
                java.lang.String r8 = "Grave error in Software Strong ID"
                com.threatmetrix.TrustDefender.internal.TL.m337int(r6, r8, r5)
                com.threatmetrix.TrustDefender.internal.DN$L r6 = new com.threatmetrix.TrustDefender.internal.DN$L
                r6.<init>()
                java.lang.String r5 = r5.toString()
                r6.f140int = r5
                r5 = r6
            L_0x083c:
                if (r5 == 0) goto L_0x0877
                com.threatmetrix.TrustDefender.internal.X r6 = new com.threatmetrix.TrustDefender.internal.X
                r6.<init>()
                java.lang.String r8 = r5.f140int
                boolean r8 = com.threatmetrix.TrustDefender.internal.NK.m215if(r8)
                if (r8 == 0) goto L_0x086f
                java.lang.String r8 = "sid_sig"
                java.lang.String r9 = r5.f139if
                r6.m374for(r8, r9, r7)
                java.lang.String r8 = "sid_rnd"
                java.lang.String r9 = r5.f141new
                r6.m374for(r8, r9, r7)
                java.lang.String r8 = "sid_type"
                java.lang.String r9 = r5.f138for
                r6.m374for(r8, r9, r7)
                java.lang.String r8 = "sid_date"
                java.lang.String r9 = r5.f137do
                r6.m374for(r8, r9, r7)
                java.lang.String r8 = "sid_key"
                java.lang.String r5 = r5.f136byte
            L_0x086b:
                r6.m374for(r8, r5, r7)
                goto L_0x0874
            L_0x086f:
                java.lang.String r8 = "sid_type"
                java.lang.String r5 = r5.f140int
                goto L_0x086b
            L_0x0874:
                r2.putAll(r6)
            L_0x0877:
                com.threatmetrix.TrustDefender.internal.K7.f269long
                if (r3 == 0) goto L_0x0885
                int r5 = r33.size()
                if (r5 <= 0) goto L_0x0885
                r13.m376new(r3)
            L_0x0885:
                java.lang.String r3 = "Params without xor"
                java.lang.String r5 = "pwx"
                r6 = r21
                r6.m26do(r3, r5)
                java.lang.String r3 = com.threatmetrix.TrustDefender.internal.CC.m23do()
                if (r3 == 0) goto L_0x0899
                java.lang.String r5 = "sp"
                r13.m374for(r5, r3, r7)
            L_0x0899:
                java.lang.String r3 = r13.m375if()
                java.lang.String r5 = "URL encoding"
                java.lang.String r8 = "ue"
                r6.m26do(r5, r8)
                com.threatmetrix.TrustDefender.internal.K7.f269long
                java.lang.String r5 = "ja"
                java.lang.String r3 = com.threatmetrix.TrustDefender.internal.NK.m212for(r3, r4)
                r2.m374for(r5, r3, r7)
                java.lang.String r3 = "Params xor'd"
                java.lang.String r4 = "pxd"
                r6.m26do(r3, r4)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.K7.E.m137do(com.threatmetrix.TrustDefender.internal.CG, java.lang.String, java.lang.String, com.threatmetrix.TrustDefender.internal.K7$O, com.threatmetrix.TrustDefender.internal.K7$L, long, java.util.Map):com.threatmetrix.TrustDefender.internal.X");
        }
    }

    static class I {

        /* renamed from: do reason: not valid java name */
        final boolean f316do;

        /* renamed from: for reason: not valid java name */
        final int f317for;

        /* renamed from: if reason: not valid java name */
        final int f318if;

        /* renamed from: int reason: not valid java name */
        final int f319int;

        /* renamed from: new reason: not valid java name */
        final int f320new;

        public I(int i, int i2, int i3, int i4, boolean z) {
            this.f317for = i;
            this.f320new = i2;
            this.f318if = i3;
            this.f319int = i4;
            this.f316do = z;
        }
    }

    static class L {

        /* renamed from: int reason: not valid java name */
        volatile long f321int = 0;

        /* renamed from: new reason: not valid java name */
        volatile int f322new = 0;

        L() {
        }
    }

    public static class O {

        /* renamed from: do reason: not valid java name */
        final Float f323do;

        /* renamed from: for reason: not valid java name */
        final double f324for;

        /* renamed from: if reason: not valid java name */
        final boolean f325if;

        /* renamed from: int reason: not valid java name */
        final boolean f326int;

        /* renamed from: new reason: not valid java name */
        final double f327new;

        public O(double d, double d2, Float f, boolean z, boolean z2) {
            this.f324for = d;
            this.f327new = d2;
            this.f323do = f;
            this.f326int = z;
            this.f325if = z2;
        }
    }

    public enum W {
        NOT_CHECKED(""),
        NONE("none"),
        UNKNOWN("unknown"),
        PERMISSIVE("permissive"),
        ENFORCING("enforcing");
        

        /* renamed from: if reason: not valid java name */
        private final String f329if;

        private W(String str) {
            this.f329if = str;
        }

        public final String identifier() {
            return this.f329if;
        }
    }

    K7(com.threatmetrix.TrustDefender.internal.P.O o, String str, long j, AtomicLong atomicLong, boolean z, K k) {
        this.f278goto = str;
        this.f277for = j;
        this.f271break = atomicLong;
        this.f281this = (this.f271break.get() & 512) != 0 && JG.m107for(o);
        this.f275do = z;
        this.f280int = o;
        this.f279if = JG.m109int(this.f280int);
        this.f273case = JG.m110int(this.f279if, this.f281this);
        String packageName = this.f280int.f487for.getPackageName();
        if (NK.m215if(packageName)) {
            packageName = "TrustDefenderSDK";
        }
        this.f272byte = "http://".concat(String.valueOf(packageName));
        StringBuilder sb = new StringBuilder("http://");
        sb.append(packageName);
        sb.append("/mobile");
        this.f274char = sb.toString();
        if (k != null) {
            this.f282try = k;
            this.f276else = NK.m211else(k.m126int());
            return;
        }
        this.f282try = new K();
        this.f276else = null;
    }

    /* renamed from: int reason: not valid java name */
    static void m135int(Y y) throws InterruptedException {
        if (y != null && y.m443for()) {
            throw new InterruptedException();
        }
    }
}
