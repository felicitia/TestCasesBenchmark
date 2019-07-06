package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.internal.ads.xh.e;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sun.misc.Unsafe;

final class yo<T> implements zd<T> {
    private static final Unsafe a = aab.c();
    private final int[] b;
    private final Object[] c;
    private final int d;
    private final int e;
    private final int f;
    private final yk g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;
    private final int[] l;
    private final int[] m;
    private final int[] n;
    private final yt o;
    private final xv p;
    private final zv<?, ?> q;
    private final wy<?> r;
    private final yf s;

    private yo(int[] iArr, Object[] objArr, int i2, int i3, int i4, yk ykVar, boolean z, boolean z2, int[] iArr2, int[] iArr3, int[] iArr4, yt ytVar, xv xvVar, zv<?, ?> zvVar, wy<?> wyVar, yf yfVar) {
        yk ykVar2 = ykVar;
        wy<?> wyVar2 = wyVar;
        this.b = iArr;
        this.c = objArr;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.i = ykVar2 instanceof xh;
        this.j = z;
        this.h = wyVar2 != null && wyVar2.a(ykVar2);
        this.k = false;
        this.l = iArr2;
        this.m = iArr3;
        this.n = iArr4;
        this.o = ytVar;
        this.p = xvVar;
        this.q = zvVar;
        this.r = wyVar2;
        this.g = ykVar2;
        this.s = yfVar;
    }

    private static int a(int i2, byte[] bArr, int i3, int i4, Object obj, wd wdVar) throws IOException {
        return wc.a(i2, bArr, i3, i4, e(obj), wdVar);
    }

    private static int a(zd<?> zdVar, int i2, byte[] bArr, int i3, int i4, xm<?> xmVar, wd wdVar) throws IOException {
        int a2 = a((zd) zdVar, bArr, i3, i4, wdVar);
        while (true) {
            xmVar.add(wdVar.c);
            if (a2 >= i4) {
                break;
            }
            int a3 = wc.a(bArr, a2, wdVar);
            if (i2 != wdVar.a) {
                break;
            }
            a2 = a((zd) zdVar, bArr, a3, i4, wdVar);
        }
        return a2;
    }

    private static int a(zd zdVar, byte[] bArr, int i2, int i3, int i4, wd wdVar) throws IOException {
        yo yoVar = (yo) zdVar;
        Object a2 = yoVar.a();
        int a3 = yoVar.a((T) a2, bArr, i2, i3, i4, wdVar);
        yoVar.c((T) a2);
        wdVar.c = a2;
        return a3;
    }

    /* JADX WARNING: type inference failed for: r8v2, types: [int] */
    /* JADX WARNING: type inference failed for: r8v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(com.google.android.gms.internal.ads.zd r6, byte[] r7, int r8, int r9, com.google.android.gms.internal.ads.wd r10) throws java.io.IOException {
        /*
            int r0 = r8 + 1
            byte r8 = r7[r8]
            if (r8 >= 0) goto L_0x000c
            int r0 = com.google.android.gms.internal.ads.wc.a(r8, r7, r0, r10)
            int r8 = r10.a
        L_0x000c:
            r3 = r0
            if (r8 < 0) goto L_0x0026
            int r9 = r9 - r3
            if (r8 <= r9) goto L_0x0013
            goto L_0x0026
        L_0x0013:
            java.lang.Object r9 = r6.a()
            int r8 = r8 + r3
            r0 = r6
            r1 = r9
            r2 = r7
            r4 = r8
            r5 = r10
            r0.a(r1, r2, r3, r4, r5)
            r6.c(r9)
            r10.c = r9
            return r8
        L_0x0026:
            com.google.android.gms.internal.ads.zzbbu r6 = com.google.android.gms.internal.ads.zzbbu.zzadl()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yo.a(com.google.android.gms.internal.ads.zd, byte[], int, int, com.google.android.gms.internal.ads.wd):int");
    }

    private static <UT, UB> int a(zv<UT, UB> zvVar, T t) {
        return zvVar.f(zvVar.b(t));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b3, code lost:
        r2 = r2 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x013c, code lost:
        r3 = java.lang.Integer.valueOf(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0149, code lost:
        r3 = java.lang.Long.valueOf(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x014d, code lost:
        r12.putObject(r1, r9, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x015b, code lost:
        r12.putObject(r1, r9, r2);
        r2 = r4 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x016c, code lost:
        r12.putObject(r1, r9, r2);
        r2 = r4 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0171, code lost:
        r12.putInt(r1, r13, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0174, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0176, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int a(T r18, byte[] r19, int r20, int r21, int r22, int r23, int r24, int r25, int r26, long r27, int r29, com.google.android.gms.internal.ads.wd r30) throws java.io.IOException {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r3 = r19
            r4 = r20
            r2 = r22
            r8 = r23
            r5 = r24
            r9 = r27
            r6 = r29
            r11 = r30
            sun.misc.Unsafe r12 = a
            int[] r7 = r0.b
            int r13 = r6 + 2
            r7 = r7[r13]
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r7 = r7 & r13
            long r13 = (long) r7
            r7 = 5
            r15 = 2
            switch(r26) {
                case 51: goto L_0x0161;
                case 52: goto L_0x0151;
                case 53: goto L_0x0141;
                case 54: goto L_0x0141;
                case 55: goto L_0x0134;
                case 56: goto L_0x0128;
                case 57: goto L_0x011d;
                case 58: goto L_0x0107;
                case 59: goto L_0x00dc;
                case 60: goto L_0x00b6;
                case 61: goto L_0x009e;
                case 62: goto L_0x0134;
                case 63: goto L_0x0071;
                case 64: goto L_0x011d;
                case 65: goto L_0x0128;
                case 66: goto L_0x0063;
                case 67: goto L_0x0055;
                case 68: goto L_0x0028;
                default: goto L_0x0026;
            }
        L_0x0026:
            goto L_0x0175
        L_0x0028:
            r7 = 3
            if (r5 != r7) goto L_0x0175
            r2 = r2 & -8
            r7 = r2 | 4
            com.google.android.gms.internal.ads.zd r2 = r0.a(r6)
            r5 = r21
            r6 = r7
            r7 = r11
            int r2 = a(r2, r3, r4, r5, r6, r7)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x0046
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x0047
        L_0x0046:
            r15 = 0
        L_0x0047:
            if (r15 != 0) goto L_0x004d
            java.lang.Object r3 = r11.c
            goto L_0x014d
        L_0x004d:
            java.lang.Object r3 = r11.c
            java.lang.Object r3 = com.google.android.gms.internal.ads.xj.a(r15, r3)
            goto L_0x014d
        L_0x0055:
            if (r5 != 0) goto L_0x0175
            int r2 = com.google.android.gms.internal.ads.wc.b(r3, r4, r11)
            long r3 = r11.b
            long r3 = com.google.android.gms.internal.ads.wl.a(r3)
            goto L_0x0149
        L_0x0063:
            if (r5 != 0) goto L_0x0175
            int r2 = com.google.android.gms.internal.ads.wc.a(r3, r4, r11)
            int r3 = r11.a
            int r3 = com.google.android.gms.internal.ads.wl.f(r3)
            goto L_0x013c
        L_0x0071:
            if (r5 != 0) goto L_0x0175
            int r3 = com.google.android.gms.internal.ads.wc.a(r3, r4, r11)
            int r4 = r11.a
            com.google.android.gms.internal.ads.xl r5 = r0.c(r6)
            if (r5 == 0) goto L_0x0094
            com.google.android.gms.internal.ads.xk r5 = r5.a(r4)
            if (r5 == 0) goto L_0x0086
            goto L_0x0094
        L_0x0086:
            com.google.android.gms.internal.ads.zw r1 = e(r18)
            long r4 = (long) r4
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r1.a(r2, r4)
            r2 = r3
            return r2
        L_0x0094:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r12.putObject(r1, r9, r2)
            r2 = r3
            goto L_0x0171
        L_0x009e:
            if (r5 != r15) goto L_0x0175
            int r2 = com.google.android.gms.internal.ads.wc.a(r3, r4, r11)
            int r4 = r11.a
            if (r4 != 0) goto L_0x00ac
            com.google.android.gms.internal.ads.zzbah r3 = com.google.android.gms.internal.ads.zzbah.zzdpq
            goto L_0x014d
        L_0x00ac:
            com.google.android.gms.internal.ads.zzbah r3 = com.google.android.gms.internal.ads.zzbah.zzc(r3, r2, r4)
            r12.putObject(r1, r9, r3)
        L_0x00b3:
            int r2 = r2 + r4
            goto L_0x0171
        L_0x00b6:
            if (r5 != r15) goto L_0x0175
            com.google.android.gms.internal.ads.zd r2 = r0.a(r6)
            r5 = r21
            int r2 = a(r2, r3, r4, r5, r11)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x00cd
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x00ce
        L_0x00cd:
            r15 = 0
        L_0x00ce:
            if (r15 != 0) goto L_0x00d4
            java.lang.Object r3 = r11.c
            goto L_0x014d
        L_0x00d4:
            java.lang.Object r3 = r11.c
            java.lang.Object r3 = com.google.android.gms.internal.ads.xj.a(r15, r3)
            goto L_0x014d
        L_0x00dc:
            if (r5 != r15) goto L_0x0175
            int r2 = com.google.android.gms.internal.ads.wc.a(r3, r4, r11)
            int r4 = r11.a
            if (r4 != 0) goto L_0x00e9
            java.lang.String r3 = ""
            goto L_0x014d
        L_0x00e9:
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r25 & r5
            if (r5 == 0) goto L_0x00fc
            int r5 = r2 + r4
            boolean r5 = com.google.android.gms.internal.ads.aad.a(r3, r2, r5)
            if (r5 != 0) goto L_0x00fc
            com.google.android.gms.internal.ads.zzbbu r1 = com.google.android.gms.internal.ads.zzbbu.zzads()
            throw r1
        L_0x00fc:
            java.lang.String r5 = new java.lang.String
            java.nio.charset.Charset r6 = com.google.android.gms.internal.ads.xj.a
            r5.<init>(r3, r2, r4, r6)
            r12.putObject(r1, r9, r5)
            goto L_0x00b3
        L_0x0107:
            if (r5 != 0) goto L_0x0175
            int r2 = com.google.android.gms.internal.ads.wc.b(r3, r4, r11)
            long r3 = r11.b
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0117
            r15 = 1
            goto L_0x0118
        L_0x0117:
            r15 = 0
        L_0x0118:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r15)
            goto L_0x014d
        L_0x011d:
            if (r5 != r7) goto L_0x0175
            int r2 = com.google.android.gms.internal.ads.wc.a(r19, r20)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x015b
        L_0x0128:
            r2 = 1
            if (r5 != r2) goto L_0x0175
            long r2 = com.google.android.gms.internal.ads.wc.b(r19, r20)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            goto L_0x016c
        L_0x0134:
            if (r5 != 0) goto L_0x0175
            int r2 = com.google.android.gms.internal.ads.wc.a(r3, r4, r11)
            int r3 = r11.a
        L_0x013c:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L_0x014d
        L_0x0141:
            if (r5 != 0) goto L_0x0175
            int r2 = com.google.android.gms.internal.ads.wc.b(r3, r4, r11)
            long r3 = r11.b
        L_0x0149:
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
        L_0x014d:
            r12.putObject(r1, r9, r3)
            goto L_0x0171
        L_0x0151:
            if (r5 != r7) goto L_0x0175
            float r2 = com.google.android.gms.internal.ads.wc.d(r19, r20)
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
        L_0x015b:
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 4
            goto L_0x0171
        L_0x0161:
            r2 = 1
            if (r5 != r2) goto L_0x0175
            double r2 = com.google.android.gms.internal.ads.wc.c(r19, r20)
            java.lang.Double r2 = java.lang.Double.valueOf(r2)
        L_0x016c:
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 8
        L_0x0171:
            r12.putInt(r1, r13, r8)
            return r2
        L_0x0175:
            r2 = r4
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yo.a(java.lang.Object, byte[], int, int, int, int, int, int, int, long, int, com.google.android.gms.internal.ads.wd):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0216, code lost:
        if (r11.b != 0) goto L_0x0218;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0218, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x021a, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x021b, code lost:
        r12.a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x021e, code lost:
        if (r2 >= r8) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0220, code lost:
        r3 = com.google.android.gms.internal.ads.wc.a(r7, r2, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0226, code lost:
        if (r9 != r11.a) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0228, code lost:
        r2 = com.google.android.gms.internal.ads.wc.b(r7, r3, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0230, code lost:
        if (r11.b == 0) goto L_0x021a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x037e, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x011b, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0124, code lost:
        if (r2 == 0) goto L_0x0126;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0126, code lost:
        r12.add(com.google.android.gms.internal.ads.zzbah.zzdpq);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x012c, code lost:
        r12.add(com.google.android.gms.internal.ads.zzbah.zzc(r7, r1, r2));
        r1 = r1 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0134, code lost:
        if (r1 >= r8) goto L_0x037e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0136, code lost:
        r2 = com.google.android.gms.internal.ads.wc.a(r7, r1, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x013c, code lost:
        if (r9 != r11.a) goto L_0x037e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x013e, code lost:
        r1 = com.google.android.gms.internal.ads.wc.a(r7, r2, r11);
        r2 = r11.a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0144, code lost:
        if (r2 != 0) goto L_0x012c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int a(T r18, byte[] r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, long r29, com.google.android.gms.internal.ads.wd r31) throws java.io.IOException {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r7 = r19
            r4 = r20
            r8 = r21
            r9 = r22
            r2 = r24
            r10 = r25
            r5 = r29
            r11 = r31
            sun.misc.Unsafe r3 = a
            java.lang.Object r3 = r3.getObject(r1, r5)
            com.google.android.gms.internal.ads.xm r3 = (com.google.android.gms.internal.ads.xm) r3
            boolean r12 = r3.a()
            r13 = 1
            if (r12 != 0) goto L_0x0036
            int r12 = r3.size()
            if (r12 != 0) goto L_0x002c
            r12 = 10
            goto L_0x002d
        L_0x002c:
            int r12 = r12 << r13
        L_0x002d:
            com.google.android.gms.internal.ads.xm r3 = r3.a(r12)
            sun.misc.Unsafe r12 = a
            r12.putObject(r1, r5, r3)
        L_0x0036:
            r12 = r3
            r3 = 5
            r5 = 0
            r14 = 2
            switch(r28) {
                case 18: goto L_0x033e;
                case 19: goto L_0x02ff;
                case 20: goto L_0x02c5;
                case 21: goto L_0x02c5;
                case 22: goto L_0x02b1;
                case 23: goto L_0x0272;
                case 24: goto L_0x0233;
                case 25: goto L_0x01e4;
                case 26: goto L_0x0157;
                case 27: goto L_0x0147;
                case 28: goto L_0x011c;
                case 29: goto L_0x02b1;
                case 30: goto L_0x00eb;
                case 31: goto L_0x0233;
                case 32: goto L_0x0272;
                case 33: goto L_0x00a9;
                case 34: goto L_0x0067;
                case 35: goto L_0x033e;
                case 36: goto L_0x02ff;
                case 37: goto L_0x02c5;
                case 38: goto L_0x02c5;
                case 39: goto L_0x02b1;
                case 40: goto L_0x0272;
                case 41: goto L_0x0233;
                case 42: goto L_0x01e4;
                case 43: goto L_0x02b1;
                case 44: goto L_0x00eb;
                case 45: goto L_0x0233;
                case 46: goto L_0x0272;
                case 47: goto L_0x00a9;
                case 48: goto L_0x0067;
                case 49: goto L_0x0040;
                default: goto L_0x003e;
            }
        L_0x003e:
            goto L_0x037d
        L_0x0040:
            r1 = 3
            if (r2 != r1) goto L_0x037d
            com.google.android.gms.internal.ads.zd r10 = r0.a(r10)
            r1 = r9 & -8
            r13 = r1 | 4
            r1 = r10
            r2 = r7
            r3 = r4
        L_0x004e:
            r4 = r8
            r5 = r13
            r6 = r11
            int r1 = a(r1, r2, r3, r4, r5, r6)
            java.lang.Object r2 = r11.c
            r12.add(r2)
            if (r1 >= r8) goto L_0x037e
            int r3 = com.google.android.gms.internal.ads.wc.a(r7, r1, r11)
            int r2 = r11.a
            if (r9 != r2) goto L_0x037e
            r1 = r10
            r2 = r7
            goto L_0x004e
        L_0x0067:
            if (r2 != r14) goto L_0x0089
            com.google.android.gms.internal.ads.xz r12 = (com.google.android.gms.internal.ads.xz) r12
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r4, r11)
            int r2 = r11.a
            int r2 = r2 + r1
        L_0x0072:
            if (r1 >= r2) goto L_0x0082
            int r1 = com.google.android.gms.internal.ads.wc.b(r7, r1, r11)
            long r3 = r11.b
            long r3 = com.google.android.gms.internal.ads.wl.a(r3)
            r12.a(r3)
            goto L_0x0072
        L_0x0082:
            if (r1 == r2) goto L_0x037e
            com.google.android.gms.internal.ads.zzbbu r1 = com.google.android.gms.internal.ads.zzbbu.zzadl()
            throw r1
        L_0x0089:
            if (r2 != 0) goto L_0x037d
            com.google.android.gms.internal.ads.xz r12 = (com.google.android.gms.internal.ads.xz) r12
            int r1 = com.google.android.gms.internal.ads.wc.b(r7, r4, r11)
        L_0x0091:
            long r2 = r11.b
            long r2 = com.google.android.gms.internal.ads.wl.a(r2)
            r12.a(r2)
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.ads.wc.a(r7, r1, r11)
            int r3 = r11.a
            if (r9 != r3) goto L_0x037e
            int r1 = com.google.android.gms.internal.ads.wc.b(r7, r2, r11)
            goto L_0x0091
        L_0x00a9:
            if (r2 != r14) goto L_0x00cb
            com.google.android.gms.internal.ads.xi r12 = (com.google.android.gms.internal.ads.xi) r12
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r4, r11)
            int r2 = r11.a
            int r2 = r2 + r1
        L_0x00b4:
            if (r1 >= r2) goto L_0x00c4
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r1, r11)
            int r3 = r11.a
            int r3 = com.google.android.gms.internal.ads.wl.f(r3)
            r12.c(r3)
            goto L_0x00b4
        L_0x00c4:
            if (r1 == r2) goto L_0x037e
            com.google.android.gms.internal.ads.zzbbu r1 = com.google.android.gms.internal.ads.zzbbu.zzadl()
            throw r1
        L_0x00cb:
            if (r2 != 0) goto L_0x037d
            com.google.android.gms.internal.ads.xi r12 = (com.google.android.gms.internal.ads.xi) r12
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r4, r11)
        L_0x00d3:
            int r2 = r11.a
            int r2 = com.google.android.gms.internal.ads.wl.f(r2)
            r12.c(r2)
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.ads.wc.a(r7, r1, r11)
            int r3 = r11.a
            if (r9 != r3) goto L_0x037e
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r2, r11)
            goto L_0x00d3
        L_0x00eb:
            if (r2 != r14) goto L_0x00f2
            int r2 = com.google.android.gms.internal.ads.wc.a(r7, r4, r12, r11)
            goto L_0x00fd
        L_0x00f2:
            if (r2 != 0) goto L_0x037d
            r2 = r9
            r3 = r7
            r5 = r8
            r6 = r12
            r7 = r11
            int r2 = com.google.android.gms.internal.ads.wc.a(r2, r3, r4, r5, r6, r7)
        L_0x00fd:
            com.google.android.gms.internal.ads.xh r1 = (com.google.android.gms.internal.ads.xh) r1
            com.google.android.gms.internal.ads.zw r3 = r1.zzdtt
            com.google.android.gms.internal.ads.zw r4 = com.google.android.gms.internal.ads.zw.a()
            if (r3 != r4) goto L_0x0108
            r3 = 0
        L_0x0108:
            com.google.android.gms.internal.ads.xl r4 = r0.c(r10)
            com.google.android.gms.internal.ads.zv<?, ?> r5 = r0.q
            r6 = r23
            java.lang.Object r3 = com.google.android.gms.internal.ads.zf.a(r6, r12, r4, r3, r5)
            com.google.android.gms.internal.ads.zw r3 = (com.google.android.gms.internal.ads.zw) r3
            if (r3 == 0) goto L_0x011a
            r1.zzdtt = r3
        L_0x011a:
            r1 = r2
            return r1
        L_0x011c:
            if (r2 != r14) goto L_0x037d
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r4, r11)
            int r2 = r11.a
            if (r2 != 0) goto L_0x012c
        L_0x0126:
            com.google.android.gms.internal.ads.zzbah r2 = com.google.android.gms.internal.ads.zzbah.zzdpq
            r12.add(r2)
            goto L_0x0134
        L_0x012c:
            com.google.android.gms.internal.ads.zzbah r3 = com.google.android.gms.internal.ads.zzbah.zzc(r7, r1, r2)
            r12.add(r3)
            int r1 = r1 + r2
        L_0x0134:
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.ads.wc.a(r7, r1, r11)
            int r3 = r11.a
            if (r9 != r3) goto L_0x037e
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r2, r11)
            int r2 = r11.a
            if (r2 != 0) goto L_0x012c
            goto L_0x0126
        L_0x0147:
            if (r2 != r14) goto L_0x037d
            com.google.android.gms.internal.ads.zd r1 = r0.a(r10)
            r2 = r9
            r3 = r7
            r5 = r8
            r6 = r12
            r7 = r11
            int r1 = a(r1, r2, r3, r4, r5, r6, r7)
            return r1
        L_0x0157:
            if (r2 != r14) goto L_0x037d
            r1 = 536870912(0x20000000, double:2.652494739E-315)
            long r15 = r26 & r1
            int r1 = (r15 > r5 ? 1 : (r15 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x0196
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r4, r11)
            int r2 = r11.a
            if (r2 != 0) goto L_0x0170
        L_0x016a:
            java.lang.String r2 = ""
            r12.add(r2)
            goto L_0x017b
        L_0x0170:
            java.lang.String r3 = new java.lang.String
            java.nio.charset.Charset r4 = com.google.android.gms.internal.ads.xj.a
            r3.<init>(r7, r1, r2, r4)
        L_0x0177:
            r12.add(r3)
            int r1 = r1 + r2
        L_0x017b:
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.ads.wc.a(r7, r1, r11)
            int r3 = r11.a
            if (r9 != r3) goto L_0x037e
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r2, r11)
            int r2 = r11.a
            if (r2 != 0) goto L_0x018e
            goto L_0x016a
        L_0x018e:
            java.lang.String r3 = new java.lang.String
            java.nio.charset.Charset r4 = com.google.android.gms.internal.ads.xj.a
            r3.<init>(r7, r1, r2, r4)
            goto L_0x0177
        L_0x0196:
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r4, r11)
            int r2 = r11.a
            if (r2 != 0) goto L_0x01a4
        L_0x019e:
            java.lang.String r2 = ""
            r12.add(r2)
            goto L_0x01bc
        L_0x01a4:
            int r3 = r1 + r2
            boolean r4 = com.google.android.gms.internal.ads.aad.a(r7, r1, r3)
            if (r4 != 0) goto L_0x01b1
            com.google.android.gms.internal.ads.zzbbu r1 = com.google.android.gms.internal.ads.zzbbu.zzads()
            throw r1
        L_0x01b1:
            java.lang.String r4 = new java.lang.String
            java.nio.charset.Charset r5 = com.google.android.gms.internal.ads.xj.a
            r4.<init>(r7, r1, r2, r5)
        L_0x01b8:
            r12.add(r4)
            r1 = r3
        L_0x01bc:
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.ads.wc.a(r7, r1, r11)
            int r3 = r11.a
            if (r9 != r3) goto L_0x037e
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r2, r11)
            int r2 = r11.a
            if (r2 != 0) goto L_0x01cf
            goto L_0x019e
        L_0x01cf:
            int r3 = r1 + r2
            boolean r4 = com.google.android.gms.internal.ads.aad.a(r7, r1, r3)
            if (r4 != 0) goto L_0x01dc
            com.google.android.gms.internal.ads.zzbbu r1 = com.google.android.gms.internal.ads.zzbbu.zzads()
            throw r1
        L_0x01dc:
            java.lang.String r4 = new java.lang.String
            java.nio.charset.Charset r5 = com.google.android.gms.internal.ads.xj.a
            r4.<init>(r7, r1, r2, r5)
            goto L_0x01b8
        L_0x01e4:
            r1 = 0
            if (r2 != r14) goto L_0x020a
            com.google.android.gms.internal.ads.we r12 = (com.google.android.gms.internal.ads.we) r12
            int r2 = com.google.android.gms.internal.ads.wc.a(r7, r4, r11)
            int r3 = r11.a
            int r3 = r3 + r2
        L_0x01f0:
            if (r2 >= r3) goto L_0x0203
            int r2 = com.google.android.gms.internal.ads.wc.b(r7, r2, r11)
            long r8 = r11.b
            int r4 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r4 == 0) goto L_0x01fe
            r4 = r13
            goto L_0x01ff
        L_0x01fe:
            r4 = r1
        L_0x01ff:
            r12.a(r4)
            goto L_0x01f0
        L_0x0203:
            if (r2 == r3) goto L_0x011a
            com.google.android.gms.internal.ads.zzbbu r1 = com.google.android.gms.internal.ads.zzbbu.zzadl()
            throw r1
        L_0x020a:
            if (r2 != 0) goto L_0x037d
            com.google.android.gms.internal.ads.we r12 = (com.google.android.gms.internal.ads.we) r12
            int r2 = com.google.android.gms.internal.ads.wc.b(r7, r4, r11)
            long r3 = r11.b
            int r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r10 == 0) goto L_0x021a
        L_0x0218:
            r3 = r13
            goto L_0x021b
        L_0x021a:
            r3 = r1
        L_0x021b:
            r12.a(r3)
            if (r2 >= r8) goto L_0x011a
            int r3 = com.google.android.gms.internal.ads.wc.a(r7, r2, r11)
            int r4 = r11.a
            if (r9 != r4) goto L_0x011a
            int r2 = com.google.android.gms.internal.ads.wc.b(r7, r3, r11)
            long r3 = r11.b
            int r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r10 == 0) goto L_0x021a
            goto L_0x0218
        L_0x0233:
            if (r2 != r14) goto L_0x0251
            com.google.android.gms.internal.ads.xi r12 = (com.google.android.gms.internal.ads.xi) r12
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r4, r11)
            int r2 = r11.a
            int r2 = r2 + r1
        L_0x023e:
            if (r1 >= r2) goto L_0x024a
            int r3 = com.google.android.gms.internal.ads.wc.a(r7, r1)
            r12.c(r3)
            int r1 = r1 + 4
            goto L_0x023e
        L_0x024a:
            if (r1 == r2) goto L_0x037e
            com.google.android.gms.internal.ads.zzbbu r1 = com.google.android.gms.internal.ads.zzbbu.zzadl()
            throw r1
        L_0x0251:
            if (r2 != r3) goto L_0x037d
            com.google.android.gms.internal.ads.xi r12 = (com.google.android.gms.internal.ads.xi) r12
            int r1 = com.google.android.gms.internal.ads.wc.a(r19, r20)
            r12.c(r1)
            int r1 = r4 + 4
        L_0x025e:
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.ads.wc.a(r7, r1, r11)
            int r3 = r11.a
            if (r9 != r3) goto L_0x037e
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r2)
            r12.c(r1)
            int r1 = r2 + 4
            goto L_0x025e
        L_0x0272:
            if (r2 != r14) goto L_0x0290
            com.google.android.gms.internal.ads.xz r12 = (com.google.android.gms.internal.ads.xz) r12
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r4, r11)
            int r2 = r11.a
            int r2 = r2 + r1
        L_0x027d:
            if (r1 >= r2) goto L_0x0289
            long r3 = com.google.android.gms.internal.ads.wc.b(r7, r1)
            r12.a(r3)
            int r1 = r1 + 8
            goto L_0x027d
        L_0x0289:
            if (r1 == r2) goto L_0x037e
            com.google.android.gms.internal.ads.zzbbu r1 = com.google.android.gms.internal.ads.zzbbu.zzadl()
            throw r1
        L_0x0290:
            if (r2 != r13) goto L_0x037d
            com.google.android.gms.internal.ads.xz r12 = (com.google.android.gms.internal.ads.xz) r12
            long r1 = com.google.android.gms.internal.ads.wc.b(r19, r20)
            r12.a(r1)
            int r1 = r4 + 8
        L_0x029d:
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.ads.wc.a(r7, r1, r11)
            int r3 = r11.a
            if (r9 != r3) goto L_0x037e
            long r3 = com.google.android.gms.internal.ads.wc.b(r7, r2)
            r12.a(r3)
            int r1 = r2 + 8
            goto L_0x029d
        L_0x02b1:
            if (r2 != r14) goto L_0x02b8
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r4, r12, r11)
            return r1
        L_0x02b8:
            if (r2 != 0) goto L_0x037d
            r1 = r9
            r2 = r7
            r3 = r4
            r4 = r8
            r5 = r12
            r6 = r11
            int r1 = com.google.android.gms.internal.ads.wc.a(r1, r2, r3, r4, r5, r6)
            return r1
        L_0x02c5:
            if (r2 != r14) goto L_0x02e3
            com.google.android.gms.internal.ads.xz r12 = (com.google.android.gms.internal.ads.xz) r12
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r4, r11)
            int r2 = r11.a
            int r2 = r2 + r1
        L_0x02d0:
            if (r1 >= r2) goto L_0x02dc
            int r1 = com.google.android.gms.internal.ads.wc.b(r7, r1, r11)
            long r3 = r11.b
            r12.a(r3)
            goto L_0x02d0
        L_0x02dc:
            if (r1 == r2) goto L_0x037e
            com.google.android.gms.internal.ads.zzbbu r1 = com.google.android.gms.internal.ads.zzbbu.zzadl()
            throw r1
        L_0x02e3:
            if (r2 != 0) goto L_0x037d
            com.google.android.gms.internal.ads.xz r12 = (com.google.android.gms.internal.ads.xz) r12
            int r1 = com.google.android.gms.internal.ads.wc.b(r7, r4, r11)
        L_0x02eb:
            long r2 = r11.b
            r12.a(r2)
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.ads.wc.a(r7, r1, r11)
            int r3 = r11.a
            if (r9 != r3) goto L_0x037e
            int r1 = com.google.android.gms.internal.ads.wc.b(r7, r2, r11)
            goto L_0x02eb
        L_0x02ff:
            if (r2 != r14) goto L_0x031d
            com.google.android.gms.internal.ads.xf r12 = (com.google.android.gms.internal.ads.xf) r12
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r4, r11)
            int r2 = r11.a
            int r2 = r2 + r1
        L_0x030a:
            if (r1 >= r2) goto L_0x0316
            float r3 = com.google.android.gms.internal.ads.wc.d(r7, r1)
            r12.a(r3)
            int r1 = r1 + 4
            goto L_0x030a
        L_0x0316:
            if (r1 == r2) goto L_0x037e
            com.google.android.gms.internal.ads.zzbbu r1 = com.google.android.gms.internal.ads.zzbbu.zzadl()
            throw r1
        L_0x031d:
            if (r2 != r3) goto L_0x037d
            com.google.android.gms.internal.ads.xf r12 = (com.google.android.gms.internal.ads.xf) r12
            float r1 = com.google.android.gms.internal.ads.wc.d(r19, r20)
            r12.a(r1)
            int r1 = r4 + 4
        L_0x032a:
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.ads.wc.a(r7, r1, r11)
            int r3 = r11.a
            if (r9 != r3) goto L_0x037e
            float r1 = com.google.android.gms.internal.ads.wc.d(r7, r2)
            r12.a(r1)
            int r1 = r2 + 4
            goto L_0x032a
        L_0x033e:
            if (r2 != r14) goto L_0x035c
            com.google.android.gms.internal.ads.ws r12 = (com.google.android.gms.internal.ads.ws) r12
            int r1 = com.google.android.gms.internal.ads.wc.a(r7, r4, r11)
            int r2 = r11.a
            int r2 = r2 + r1
        L_0x0349:
            if (r1 >= r2) goto L_0x0355
            double r3 = com.google.android.gms.internal.ads.wc.c(r7, r1)
            r12.a(r3)
            int r1 = r1 + 8
            goto L_0x0349
        L_0x0355:
            if (r1 == r2) goto L_0x037e
            com.google.android.gms.internal.ads.zzbbu r1 = com.google.android.gms.internal.ads.zzbbu.zzadl()
            throw r1
        L_0x035c:
            if (r2 != r13) goto L_0x037d
            com.google.android.gms.internal.ads.ws r12 = (com.google.android.gms.internal.ads.ws) r12
            double r1 = com.google.android.gms.internal.ads.wc.c(r19, r20)
            r12.a(r1)
            int r1 = r4 + 8
        L_0x0369:
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.ads.wc.a(r7, r1, r11)
            int r3 = r11.a
            if (r9 != r3) goto L_0x037e
            double r3 = com.google.android.gms.internal.ads.wc.c(r7, r2)
            r12.a(r3)
            int r1 = r2 + 8
            goto L_0x0369
        L_0x037d:
            r1 = r4
        L_0x037e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yo.a(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.ads.wd):int");
    }

    /* JADX WARNING: type inference failed for: r9v4, types: [int] */
    /* JADX WARNING: type inference failed for: r9v11 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <K, V> int a(T r7, byte[] r8, int r9, int r10, int r11, int r12, long r13, com.google.android.gms.internal.ads.wd r15) throws java.io.IOException {
        /*
            r6 = this;
            sun.misc.Unsafe r12 = a
            java.lang.Object r11 = r6.b(r11)
            java.lang.Object r0 = r12.getObject(r7, r13)
            com.google.android.gms.internal.ads.yf r1 = r6.s
            boolean r1 = r1.c(r0)
            if (r1 == 0) goto L_0x0021
            com.google.android.gms.internal.ads.yf r1 = r6.s
            java.lang.Object r1 = r1.e(r11)
            com.google.android.gms.internal.ads.yf r2 = r6.s
            r2.a(r1, r0)
            r12.putObject(r7, r13, r1)
            r0 = r1
        L_0x0021:
            com.google.android.gms.internal.ads.yf r7 = r6.s
            com.google.android.gms.internal.ads.ye r7 = r7.f(r11)
            com.google.android.gms.internal.ads.yf r11 = r6.s
            java.util.Map r11 = r11.a(r0)
            int r9 = com.google.android.gms.internal.ads.wc.a(r8, r9, r15)
            int r12 = r15.a
            if (r12 < 0) goto L_0x0095
            int r13 = r10 - r9
            if (r12 <= r13) goto L_0x003a
            goto L_0x0095
        L_0x003a:
            int r12 = r12 + r9
            K r13 = r7.b
            V r14 = r7.d
        L_0x003f:
            if (r9 >= r12) goto L_0x008a
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x004d
            int r0 = com.google.android.gms.internal.ads.wc.a(r9, r8, r0, r15)
            int r9 = r15.a
        L_0x004d:
            r1 = r0
            int r0 = r9 >>> 3
            r2 = r9 & 7
            switch(r0) {
                case 1: goto L_0x0070;
                case 2: goto L_0x0056;
                default: goto L_0x0055;
            }
        L_0x0055:
            goto L_0x0085
        L_0x0056:
            com.google.android.gms.internal.ads.zzbes r0 = r7.c
            int r0 = r0.zzagm()
            if (r2 != r0) goto L_0x0085
            com.google.android.gms.internal.ads.zzbes r3 = r7.c
            V r9 = r7.d
            java.lang.Class r4 = r9.getClass()
            r0 = r8
            r2 = r10
            r5 = r15
            int r9 = a(r0, r1, r2, r3, r4, r5)
            java.lang.Object r14 = r15.c
            goto L_0x003f
        L_0x0070:
            com.google.android.gms.internal.ads.zzbes r0 = r7.a
            int r0 = r0.zzagm()
            if (r2 != r0) goto L_0x0085
            com.google.android.gms.internal.ads.zzbes r3 = r7.a
            r4 = 0
            r0 = r8
            r2 = r10
            r5 = r15
            int r9 = a(r0, r1, r2, r3, r4, r5)
            java.lang.Object r13 = r15.c
            goto L_0x003f
        L_0x0085:
            int r9 = com.google.android.gms.internal.ads.wc.a(r9, r8, r1, r10, r15)
            goto L_0x003f
        L_0x008a:
            if (r9 == r12) goto L_0x0091
            com.google.android.gms.internal.ads.zzbbu r7 = com.google.android.gms.internal.ads.zzbbu.zzadr()
            throw r7
        L_0x0091:
            r11.put(r13, r14)
            return r12
        L_0x0095:
            com.google.android.gms.internal.ads.zzbbu r7 = com.google.android.gms.internal.ads.zzbbu.zzadl()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yo.a(java.lang.Object, byte[], int, int, int, int, long, com.google.android.gms.internal.ads.wd):int");
    }

    /* JADX WARNING: type inference failed for: r33v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v0 */
    /* JADX WARNING: type inference failed for: r12v1, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v11, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r5v5, types: [int] */
    /* JADX WARNING: type inference failed for: r12v2 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r12v3 */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: type inference failed for: r7v6 */
    /* JADX WARNING: type inference failed for: r0v14, types: [int] */
    /* JADX WARNING: type inference failed for: r1v14, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v4 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r7v8 */
    /* JADX WARNING: type inference failed for: r30v0 */
    /* JADX WARNING: type inference failed for: r7v9 */
    /* JADX WARNING: type inference failed for: r30v1 */
    /* JADX WARNING: type inference failed for: r30v2 */
    /* JADX WARNING: type inference failed for: r30v3 */
    /* JADX WARNING: type inference failed for: r19v0 */
    /* JADX WARNING: type inference failed for: r11v5 */
    /* JADX WARNING: type inference failed for: r30v4 */
    /* JADX WARNING: type inference failed for: r12v5 */
    /* JADX WARNING: type inference failed for: r1v18 */
    /* JADX WARNING: type inference failed for: r2v8, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v9, types: [int] */
    /* JADX WARNING: type inference failed for: r2v9, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r2v10, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v11, types: [int] */
    /* JADX WARNING: type inference failed for: r30v5 */
    /* JADX WARNING: type inference failed for: r12v8 */
    /* JADX WARNING: type inference failed for: r1v23 */
    /* JADX WARNING: type inference failed for: r7v17 */
    /* JADX WARNING: type inference failed for: r30v6 */
    /* JADX WARNING: type inference failed for: r1v24, types: [int] */
    /* JADX WARNING: type inference failed for: r2v13, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v9 */
    /* JADX WARNING: type inference failed for: r1v25 */
    /* JADX WARNING: type inference failed for: r11v10 */
    /* JADX WARNING: type inference failed for: r7v20 */
    /* JADX WARNING: type inference failed for: r12v10 */
    /* JADX WARNING: type inference failed for: r11v11 */
    /* JADX WARNING: type inference failed for: r1v26 */
    /* JADX WARNING: type inference failed for: r12v11 */
    /* JADX WARNING: type inference failed for: r11v12 */
    /* JADX WARNING: type inference failed for: r11v13 */
    /* JADX WARNING: type inference failed for: r12v12, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v14 */
    /* JADX WARNING: type inference failed for: r12v13, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v14 */
    /* JADX WARNING: type inference failed for: r11v15 */
    /* JADX WARNING: type inference failed for: r1v30 */
    /* JADX WARNING: type inference failed for: r11v16 */
    /* JADX WARNING: type inference failed for: r12v15, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v16 */
    /* JADX WARNING: type inference failed for: r11v17 */
    /* JADX WARNING: type inference failed for: r11v18 */
    /* JADX WARNING: type inference failed for: r12v17, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v19 */
    /* JADX WARNING: type inference failed for: r11v20 */
    /* JADX WARNING: type inference failed for: r12v18, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v21 */
    /* JADX WARNING: type inference failed for: r12v19, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v22 */
    /* JADX WARNING: type inference failed for: r12v20, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v23 */
    /* JADX WARNING: type inference failed for: r12v21, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v24 */
    /* JADX WARNING: type inference failed for: r12v22, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v23 */
    /* JADX WARNING: type inference failed for: r11v25 */
    /* JADX WARNING: type inference failed for: r12v24 */
    /* JADX WARNING: type inference failed for: r11v26 */
    /* JADX WARNING: type inference failed for: r11v27 */
    /* JADX WARNING: type inference failed for: r12v25, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v28, types: [int] */
    /* JADX WARNING: type inference failed for: r12v26, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v29 */
    /* JADX WARNING: type inference failed for: r12v27, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v30 */
    /* JADX WARNING: type inference failed for: r11v31 */
    /* JADX WARNING: type inference failed for: r12v28, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v32 */
    /* JADX WARNING: type inference failed for: r12v29 */
    /* JADX WARNING: type inference failed for: r12v30 */
    /* JADX WARNING: type inference failed for: r1v48, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v33 */
    /* JADX WARNING: type inference failed for: r11v34 */
    /* JADX WARNING: type inference failed for: r12v31 */
    /* JADX WARNING: type inference failed for: r5v17 */
    /* JADX WARNING: type inference failed for: r1v53, types: [int] */
    /* JADX WARNING: type inference failed for: r5v18 */
    /* JADX WARNING: type inference failed for: r12v32 */
    /* JADX WARNING: type inference failed for: r12v33 */
    /* JADX WARNING: type inference failed for: r1v54 */
    /* JADX WARNING: type inference failed for: r12v34 */
    /* JADX WARNING: type inference failed for: r1v55 */
    /* JADX WARNING: type inference failed for: r7v41 */
    /* JADX WARNING: type inference failed for: r7v42 */
    /* JADX WARNING: type inference failed for: r30v7 */
    /* JADX WARNING: type inference failed for: r30v8 */
    /* JADX WARNING: type inference failed for: r30v9 */
    /* JADX WARNING: type inference failed for: r30v10 */
    /* JADX WARNING: type inference failed for: r1v56 */
    /* JADX WARNING: type inference failed for: r12v35 */
    /* JADX WARNING: type inference failed for: r1v57 */
    /* JADX WARNING: type inference failed for: r12v36 */
    /* JADX WARNING: type inference failed for: r12v37 */
    /* JADX WARNING: type inference failed for: r11v37 */
    /* JADX WARNING: type inference failed for: r12v38 */
    /* JADX WARNING: type inference failed for: r12v39 */
    /* JADX WARNING: type inference failed for: r12v40 */
    /* JADX WARNING: type inference failed for: r12v41 */
    /* JADX WARNING: type inference failed for: r12v42 */
    /* JADX WARNING: type inference failed for: r11v38 */
    /* JADX WARNING: type inference failed for: r12v43 */
    /* JADX WARNING: type inference failed for: r11v39 */
    /* JADX WARNING: type inference failed for: r12v44 */
    /* JADX WARNING: type inference failed for: r12v45 */
    /* JADX WARNING: type inference failed for: r12v46 */
    /* JADX WARNING: type inference failed for: r12v47 */
    /* JADX WARNING: type inference failed for: r12v48 */
    /* JADX WARNING: type inference failed for: r12v49 */
    /* JADX WARNING: type inference failed for: r12v50 */
    /* JADX WARNING: type inference failed for: r12v51 */
    /* JADX WARNING: type inference failed for: r11v40 */
    /* JADX WARNING: type inference failed for: r12v52 */
    /* JADX WARNING: type inference failed for: r11v41 */
    /* JADX WARNING: type inference failed for: r12v53 */
    /* JADX WARNING: type inference failed for: r12v54 */
    /* JADX WARNING: type inference failed for: r12v55 */
    /* JADX WARNING: type inference failed for: r12v56 */
    /* JADX WARNING: type inference failed for: r11v42 */
    /* JADX WARNING: type inference failed for: r12v57 */
    /* JADX WARNING: type inference failed for: r12v58 */
    /* JADX WARNING: type inference failed for: r12v59 */
    /* JADX WARNING: type inference failed for: r11v43 */
    /* JADX WARNING: type inference failed for: r11v44 */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x030a, code lost:
        if (r0 == r15) goto L_0x032d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x032b, code lost:
        if (r0 == r15) goto L_0x032d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x032f, code lost:
        r12 = r33;
        r13 = r35;
        r9 = r37;
        r15 = r14;
        r6 = r17;
        r7 = r24;
        r10 = r29;
        r1 = r30;
        r8 = -1;
        r11 = r36;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00c9, code lost:
        r0 = r20;
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0128, code lost:
        r1 = r9.c;
        r12 = r12;
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x012a, code lost:
        r10.putObject(r14, r7, r1);
        r12 = r12;
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01c7, code lost:
        r0 = r3;
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01dc, code lost:
        r10.putInt(r14, r7, r1);
        r12 = r12;
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01f2, code lost:
        r10.putLong(r14, r7, r4);
        r6 = r6 | r23;
        r1 = r11;
        r0 = r17;
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0229, code lost:
        r6 = r6 | r23;
        r12 = r12;
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x022b, code lost:
        r1 = r11;
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0233, code lost:
        r2 = r0;
        r17 = r6;
        r29 = r10;
        r7 = r11;
        r14 = r15;
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r0v11, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r33v0, types: [byte[]] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r12v2
      assigns: []
      uses: []
      mth insns count: 479
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$38/2083670723.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0353 A[ADDED_TO_REGION] */
    /* JADX WARNING: Unknown variable types count: 56 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int a(T r32, byte[] r33, int r34, int r35, int r36, com.google.android.gms.internal.ads.wd r37) throws java.io.IOException {
        /*
            r31 = this;
            r15 = r31
            r14 = r32
            r12 = r33
            r13 = r35
            r11 = r36
            r9 = r37
            sun.misc.Unsafe r10 = a
            r16 = 0
            r8 = -1
            r0 = r34
            r7 = r8
            r1 = r16
            r6 = r1
        L_0x0017:
            if (r0 >= r13) goto L_0x037d
            int r1 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0028
            int r0 = com.google.android.gms.internal.ads.wc.a(r0, r12, r1, r9)
            int r1 = r9.a
            r4 = r0
            r5 = r1
            goto L_0x002a
        L_0x0028:
            r5 = r0
            r4 = r1
        L_0x002a:
            int r3 = r5 >>> 3
            r2 = r5 & 7
            int r1 = r15.g(r3)
            if (r1 == r8) goto L_0x0342
            int[] r0 = r15.b
            int r17 = r1 + 1
            r0 = r0[r17]
            r17 = 267386880(0xff00000, float:2.3665827E-29)
            r17 = r0 & r17
            int r8 = r17 >>> 20
            r17 = 1048575(0xfffff, float:1.469367E-39)
            r19 = r5
            r5 = r0 & r17
            r20 = r4
            long r4 = (long) r5
            r21 = r4
            r4 = 17
            if (r8 > r4) goto L_0x023c
            int[] r4 = r15.b
            int r23 = r1 + 2
            r4 = r4[r23]
            int r23 = r4 >>> 20
            r5 = 1
            int r23 = r5 << r23
            r4 = r4 & r17
            if (r4 == r7) goto L_0x006c
            r11 = -1
            if (r7 == r11) goto L_0x0066
            long r11 = (long) r7
            r10.putInt(r14, r11, r6)
        L_0x0066:
            long r6 = (long) r4
            int r6 = r10.getInt(r14, r6)
            r7 = r4
        L_0x006c:
            r4 = 5
            switch(r8) {
                case 0: goto L_0x0214;
                case 1: goto L_0x01fe;
                case 2: goto L_0x01e0;
                case 3: goto L_0x01e0;
                case 4: goto L_0x01ca;
                case 5: goto L_0x01a9;
                case 6: goto L_0x0192;
                case 7: goto L_0x0172;
                case 8: goto L_0x0156;
                case 9: goto L_0x012f;
                case 10: goto L_0x0117;
                case 11: goto L_0x01ca;
                case 12: goto L_0x00e5;
                case 13: goto L_0x0192;
                case 14: goto L_0x01a9;
                case 15: goto L_0x00cd;
                case 16: goto L_0x00b1;
                case 17: goto L_0x007a;
                default: goto L_0x0070;
            }
        L_0x0070:
            r24 = r7
            r11 = r19
            r0 = r20
            r12 = r33
            goto L_0x0233
        L_0x007a:
            r0 = 3
            if (r2 != r0) goto L_0x00aa
            int r0 = r3 << 3
            r4 = r0 | 4
            com.google.android.gms.internal.ads.zd r0 = r15.a(r1)
            r12 = r33
            r1 = r12
            r2 = r20
            r3 = r13
            r24 = r7
            r7 = r21
            r11 = r19
            r5 = r9
            int r0 = a(r0, r1, r2, r3, r4, r5)
            r1 = r6 & r23
            if (r1 != 0) goto L_0x009e
            java.lang.Object r1 = r9.c
            goto L_0x012a
        L_0x009e:
            java.lang.Object r1 = r10.getObject(r14, r7)
            java.lang.Object r2 = r9.c
            java.lang.Object r1 = com.google.android.gms.internal.ads.xj.a(r1, r2)
            goto L_0x012a
        L_0x00aa:
            r24 = r7
            r11 = r19
            r12 = r33
            goto L_0x00c9
        L_0x00b1:
            r24 = r7
            r11 = r19
            r7 = r21
            r12 = r33
            if (r2 != 0) goto L_0x00c9
            r3 = r20
            int r17 = com.google.android.gms.internal.ads.wc.b(r12, r3, r9)
            long r0 = r9.b
            long r4 = com.google.android.gms.internal.ads.wl.a(r0)
            goto L_0x01f2
        L_0x00c9:
            r0 = r20
            goto L_0x0233
        L_0x00cd:
            r24 = r7
            r11 = r19
            r3 = r20
            r7 = r21
            r12 = r33
            if (r2 != 0) goto L_0x01c7
            int r0 = com.google.android.gms.internal.ads.wc.a(r12, r3, r9)
            int r1 = r9.a
            int r1 = com.google.android.gms.internal.ads.wl.f(r1)
            goto L_0x01dc
        L_0x00e5:
            r24 = r7
            r11 = r19
            r3 = r20
            r7 = r21
            r12 = r33
            if (r2 != 0) goto L_0x01c7
            int r0 = com.google.android.gms.internal.ads.wc.a(r12, r3, r9)
            int r2 = r9.a
            com.google.android.gms.internal.ads.xl r1 = r15.c(r1)
            if (r1 == 0) goto L_0x0112
            com.google.android.gms.internal.ads.xk r1 = r1.a(r2)
            if (r1 == 0) goto L_0x0104
            goto L_0x0112
        L_0x0104:
            com.google.android.gms.internal.ads.zw r1 = e(r32)
            long r2 = (long) r2
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r1.a(r11, r2)
            goto L_0x022b
        L_0x0112:
            r10.putInt(r14, r7, r2)
            goto L_0x0229
        L_0x0117:
            r24 = r7
            r11 = r19
            r3 = r20
            r7 = r21
            r0 = 2
            r12 = r33
            if (r2 != r0) goto L_0x01c7
            int r0 = com.google.android.gms.internal.ads.wc.e(r12, r3, r9)
        L_0x0128:
            java.lang.Object r1 = r9.c
        L_0x012a:
            r10.putObject(r14, r7, r1)
            goto L_0x0229
        L_0x012f:
            r24 = r7
            r11 = r19
            r3 = r20
            r7 = r21
            r0 = 2
            r12 = r33
            if (r2 != r0) goto L_0x01c7
            com.google.android.gms.internal.ads.zd r0 = r15.a(r1)
            int r0 = a(r0, r12, r3, r13, r9)
            r1 = r6 & r23
            if (r1 != 0) goto L_0x014b
            java.lang.Object r1 = r9.c
            goto L_0x012a
        L_0x014b:
            java.lang.Object r1 = r10.getObject(r14, r7)
            java.lang.Object r2 = r9.c
            java.lang.Object r1 = com.google.android.gms.internal.ads.xj.a(r1, r2)
            goto L_0x012a
        L_0x0156:
            r24 = r7
            r11 = r19
            r3 = r20
            r7 = r21
            r1 = 2
            r12 = r33
            if (r2 != r1) goto L_0x01c7
            r1 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r1
            if (r0 != 0) goto L_0x016d
            int r0 = com.google.android.gms.internal.ads.wc.c(r12, r3, r9)
            goto L_0x0128
        L_0x016d:
            int r0 = com.google.android.gms.internal.ads.wc.d(r12, r3, r9)
            goto L_0x0128
        L_0x0172:
            r24 = r7
            r11 = r19
            r3 = r20
            r7 = r21
            r12 = r33
            if (r2 != 0) goto L_0x01c7
            int r0 = com.google.android.gms.internal.ads.wc.b(r12, r3, r9)
            long r1 = r9.b
            r3 = 0
            int r17 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r17 == 0) goto L_0x018b
            goto L_0x018d
        L_0x018b:
            r5 = r16
        L_0x018d:
            com.google.android.gms.internal.ads.aab.a(r14, r7, r5)
            goto L_0x0229
        L_0x0192:
            r24 = r7
            r11 = r19
            r3 = r20
            r7 = r21
            r12 = r33
            if (r2 != r4) goto L_0x01c7
            int r0 = com.google.android.gms.internal.ads.wc.a(r12, r3)
            r10.putInt(r14, r7, r0)
            int r0 = r3 + 4
            goto L_0x0229
        L_0x01a9:
            r24 = r7
            r11 = r19
            r3 = r20
            r7 = r21
            r12 = r33
            if (r2 != r5) goto L_0x01c7
            long r4 = com.google.android.gms.internal.ads.wc.b(r12, r3)
            r0 = r10
            r1 = r14
            r17 = r3
            r2 = r7
            r7 = r17
            r0.putLong(r1, r2, r4)
            int r0 = r7 + 8
            goto L_0x0229
        L_0x01c7:
            r0 = r3
            goto L_0x0233
        L_0x01ca:
            r24 = r7
            r11 = r19
            r0 = r20
            r7 = r21
            r12 = r33
            if (r2 != 0) goto L_0x0233
            int r0 = com.google.android.gms.internal.ads.wc.a(r12, r0, r9)
            int r1 = r9.a
        L_0x01dc:
            r10.putInt(r14, r7, r1)
            goto L_0x0229
        L_0x01e0:
            r24 = r7
            r11 = r19
            r0 = r20
            r7 = r21
            r12 = r33
            if (r2 != 0) goto L_0x0233
            int r17 = com.google.android.gms.internal.ads.wc.b(r12, r0, r9)
            long r4 = r9.b
        L_0x01f2:
            r0 = r10
            r1 = r14
            r2 = r7
            r0.putLong(r1, r2, r4)
            r6 = r6 | r23
            r1 = r11
            r0 = r17
            goto L_0x022c
        L_0x01fe:
            r24 = r7
            r11 = r19
            r0 = r20
            r7 = r21
            r12 = r33
            if (r2 != r4) goto L_0x0233
            float r1 = com.google.android.gms.internal.ads.wc.d(r12, r0)
            com.google.android.gms.internal.ads.aab.a(r14, r7, r1)
            int r0 = r0 + 4
            goto L_0x0229
        L_0x0214:
            r24 = r7
            r11 = r19
            r0 = r20
            r7 = r21
            r12 = r33
            if (r2 != r5) goto L_0x0233
            double r1 = com.google.android.gms.internal.ads.wc.c(r12, r0)
            com.google.android.gms.internal.ads.aab.a(r14, r7, r1)
            int r0 = r0 + 8
        L_0x0229:
            r6 = r6 | r23
        L_0x022b:
            r1 = r11
        L_0x022c:
            r7 = r24
            r8 = -1
            r11 = r36
            goto L_0x0017
        L_0x0233:
            r2 = r0
            r17 = r6
            r29 = r10
            r7 = r11
            r14 = r15
            goto L_0x034f
        L_0x023c:
            r25 = r3
            r24 = r7
            r5 = r8
            r11 = r19
            r4 = r20
            r7 = r21
            r3 = 27
            if (r5 != r3) goto L_0x0287
            r3 = 2
            if (r2 != r3) goto L_0x027f
            java.lang.Object r0 = r10.getObject(r14, r7)
            com.google.android.gms.internal.ads.xm r0 = (com.google.android.gms.internal.ads.xm) r0
            boolean r2 = r0.a()
            if (r2 != 0) goto L_0x026c
            int r2 = r0.size()
            if (r2 != 0) goto L_0x0263
            r2 = 10
            goto L_0x0265
        L_0x0263:
            int r2 = r2 << 1
        L_0x0265:
            com.google.android.gms.internal.ads.xm r0 = r0.a(r2)
            r10.putObject(r14, r7, r0)
        L_0x026c:
            r5 = r0
            com.google.android.gms.internal.ads.zd r0 = r15.a(r1)
            r1 = r11
            r2 = r12
            r3 = r4
            r4 = r13
            r17 = r6
            r6 = r9
            int r0 = a(r0, r1, r2, r3, r4, r5, r6)
            r6 = r17
            goto L_0x022c
        L_0x027f:
            r17 = r6
            r29 = r10
            r30 = r11
            goto L_0x034a
        L_0x0287:
            r17 = r6
            r3 = 49
            if (r5 > r3) goto L_0x02da
            r26 = r10
            long r9 = (long) r0
            r0 = r15
            r18 = r1
            r1 = r14
            r6 = r2
            r2 = r12
            r19 = r25
            r3 = r4
            r15 = r4
            r4 = r13
            r20 = r5
            r5 = r11
            r27 = r6
            r6 = r19
            r21 = r7
            r7 = r27
            r28 = r20
            r8 = r18
            r29 = r26
            r30 = r11
            r11 = r28
            r12 = r21
            r14 = r37
            int r0 = r0.a((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 != r15) goto L_0x02c3
            r2 = r0
            r7 = r30
            r6 = r36
            r14 = r31
            goto L_0x0351
        L_0x02c3:
            r14 = r32
            r12 = r33
            r13 = r35
            r9 = r37
            r6 = r17
            r7 = r24
            r10 = r29
            r1 = r30
            r8 = -1
            r11 = r36
            r15 = r31
            goto L_0x0017
        L_0x02da:
            r18 = r1
            r27 = r2
            r15 = r4
            r28 = r5
            r21 = r7
            r29 = r10
            r30 = r11
            r19 = r25
            r1 = 50
            r9 = r28
            if (r9 != r1) goto L_0x0310
            r7 = r27
            r1 = 2
            if (r7 != r1) goto L_0x030d
            r14 = r31
            r0 = r14
            r1 = r32
            r2 = r33
            r3 = r15
            r4 = r35
            r5 = r18
            r6 = r19
            r7 = r21
            r9 = r37
            int r0 = r0.a(r1, r2, r3, r4, r5, r6, r7, r9)
            if (r0 != r15) goto L_0x032f
            goto L_0x032d
        L_0x030d:
            r14 = r31
            goto L_0x034c
        L_0x0310:
            r8 = r0
            r7 = r27
            r14 = r31
            r0 = r14
            r1 = r32
            r2 = r33
            r3 = r15
            r4 = r35
            r5 = r30
            r6 = r19
            r10 = r21
            r12 = r18
            r13 = r37
            int r0 = r0.a((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 != r15) goto L_0x032f
        L_0x032d:
            r2 = r0
            goto L_0x034d
        L_0x032f:
            r12 = r33
            r13 = r35
            r9 = r37
            r15 = r14
            r6 = r17
            r7 = r24
            r10 = r29
            r1 = r30
            r8 = -1
            r11 = r36
            goto L_0x0379
        L_0x0342:
            r30 = r5
            r17 = r6
            r24 = r7
            r29 = r10
        L_0x034a:
            r14 = r15
            r15 = r4
        L_0x034c:
            r2 = r15
        L_0x034d:
            r7 = r30
        L_0x034f:
            r6 = r36
        L_0x0351:
            if (r7 != r6) goto L_0x035c
            if (r6 != 0) goto L_0x0356
            goto L_0x035c
        L_0x0356:
            r1 = r17
            r0 = r24
            r3 = -1
            goto L_0x038c
        L_0x035c:
            r0 = r7
            r1 = r33
            r3 = r35
            r4 = r32
            r5 = r37
            int r0 = a(r0, r1, r2, r3, r4, r5)
            r12 = r33
            r13 = r35
            r9 = r37
            r11 = r6
            r1 = r7
            r15 = r14
            r6 = r17
            r7 = r24
            r10 = r29
            r8 = -1
        L_0x0379:
            r14 = r32
            goto L_0x0017
        L_0x037d:
            r17 = r6
            r24 = r7
            r29 = r10
            r6 = r11
            r14 = r15
            r2 = r0
            r7 = r1
            r3 = r8
            r1 = r17
            r0 = r24
        L_0x038c:
            if (r0 == r3) goto L_0x0397
            long r3 = (long) r0
            r0 = r32
            r5 = r29
            r5.putInt(r0, r3, r1)
            goto L_0x0399
        L_0x0397:
            r0 = r32
        L_0x0399:
            int[] r1 = r14.m
            if (r1 == 0) goto L_0x03ba
            r1 = 0
            int[] r3 = r14.m
            int r4 = r3.length
            r5 = r1
            r1 = r16
        L_0x03a4:
            if (r1 >= r4) goto L_0x03b3
            r8 = r3[r1]
            com.google.android.gms.internal.ads.zv<?, ?> r9 = r14.q
            java.lang.Object r5 = r14.a(r0, r8, (UB) r5, r9)
            com.google.android.gms.internal.ads.zw r5 = (com.google.android.gms.internal.ads.zw) r5
            int r1 = r1 + 1
            goto L_0x03a4
        L_0x03b3:
            if (r5 == 0) goto L_0x03ba
            com.google.android.gms.internal.ads.zv<?, ?> r1 = r14.q
            r1.b(r0, r5)
        L_0x03ba:
            if (r6 != 0) goto L_0x03c5
            r0 = r35
            if (r2 == r0) goto L_0x03cc
            com.google.android.gms.internal.ads.zzbbu r0 = com.google.android.gms.internal.ads.zzbbu.zzadr()
            throw r0
        L_0x03c5:
            r0 = r35
            if (r2 > r0) goto L_0x03cd
            if (r7 == r6) goto L_0x03cc
            goto L_0x03cd
        L_0x03cc:
            return r2
        L_0x03cd:
            com.google.android.gms.internal.ads.zzbbu r0 = com.google.android.gms.internal.ads.zzbbu.zzadr()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yo.a(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.ads.wd):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0041, code lost:
        r2 = java.lang.Long.valueOf(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004c, code lost:
        r2 = java.lang.Integer.valueOf(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0050, code lost:
        r6.c = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0052, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006d, code lost:
        r6.c = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0071, code lost:
        return r2 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007a, code lost:
        r6.c = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007e, code lost:
        return r2 + 8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(byte[] r1, int r2, int r3, com.google.android.gms.internal.ads.zzbes r4, java.lang.Class<?> r5, com.google.android.gms.internal.ads.wd r6) throws java.io.IOException {
        /*
            int[] r0 = com.google.android.gms.internal.ads.yp.a
            int r4 = r4.ordinal()
            r4 = r0[r4]
            switch(r4) {
                case 1: goto L_0x0084;
                case 2: goto L_0x007f;
                case 3: goto L_0x0072;
                case 4: goto L_0x0065;
                case 5: goto L_0x0065;
                case 6: goto L_0x005c;
                case 7: goto L_0x005c;
                case 8: goto L_0x0053;
                case 9: goto L_0x0046;
                case 10: goto L_0x0046;
                case 11: goto L_0x0046;
                case 12: goto L_0x003b;
                case 13: goto L_0x003b;
                case 14: goto L_0x002e;
                case 15: goto L_0x0023;
                case 16: goto L_0x0018;
                case 17: goto L_0x0013;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "unsupported field type."
            r1.<init>(r2)
            throw r1
        L_0x0013:
            int r1 = com.google.android.gms.internal.ads.wc.d(r1, r2, r6)
            return r1
        L_0x0018:
            int r1 = com.google.android.gms.internal.ads.wc.b(r1, r2, r6)
            long r2 = r6.b
            long r2 = com.google.android.gms.internal.ads.wl.a(r2)
            goto L_0x0041
        L_0x0023:
            int r1 = com.google.android.gms.internal.ads.wc.a(r1, r2, r6)
            int r2 = r6.a
            int r2 = com.google.android.gms.internal.ads.wl.f(r2)
            goto L_0x004c
        L_0x002e:
            com.google.android.gms.internal.ads.yx r4 = com.google.android.gms.internal.ads.yx.a()
            com.google.android.gms.internal.ads.zd r4 = r4.a(r5)
            int r1 = a(r4, r1, r2, r3, r6)
            return r1
        L_0x003b:
            int r1 = com.google.android.gms.internal.ads.wc.b(r1, r2, r6)
            long r2 = r6.b
        L_0x0041:
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            goto L_0x0050
        L_0x0046:
            int r1 = com.google.android.gms.internal.ads.wc.a(r1, r2, r6)
            int r2 = r6.a
        L_0x004c:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
        L_0x0050:
            r6.c = r2
            return r1
        L_0x0053:
            float r1 = com.google.android.gms.internal.ads.wc.d(r1, r2)
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            goto L_0x006d
        L_0x005c:
            long r3 = com.google.android.gms.internal.ads.wc.b(r1, r2)
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            goto L_0x007a
        L_0x0065:
            int r1 = com.google.android.gms.internal.ads.wc.a(r1, r2)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
        L_0x006d:
            r6.c = r1
            int r1 = r2 + 4
            return r1
        L_0x0072:
            double r3 = com.google.android.gms.internal.ads.wc.c(r1, r2)
            java.lang.Double r1 = java.lang.Double.valueOf(r3)
        L_0x007a:
            r6.c = r1
            int r1 = r2 + 8
            return r1
        L_0x007f:
            int r1 = com.google.android.gms.internal.ads.wc.e(r1, r2, r6)
            return r1
        L_0x0084:
            int r1 = com.google.android.gms.internal.ads.wc.b(r1, r2, r6)
            long r2 = r6.b
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0092
            r2 = 1
            goto L_0x0093
        L_0x0092:
            r2 = 0
        L_0x0093:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            goto L_0x0050
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yo.a(byte[], int, int, com.google.android.gms.internal.ads.zzbes, java.lang.Class, com.google.android.gms.internal.ads.wd):int");
    }

    static <T> yo<T> a(Class<T> cls, yi yiVar, yt ytVar, xv xvVar, zv<?, ?> zvVar, wy<?> wyVar, yf yfVar) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        yi yiVar2 = yiVar;
        if (yiVar2 instanceof yz) {
            yz yzVar = (yz) yiVar2;
            boolean z = yzVar.a() == e.i;
            if (yzVar.g() == 0) {
                i4 = 0;
                i3 = 0;
                i2 = 0;
            } else {
                int e2 = yzVar.e();
                int f2 = yzVar.f();
                i4 = yzVar.k();
                i3 = e2;
                i2 = f2;
            }
            int[] iArr = new int[(i4 << 2)];
            Object[] objArr = new Object[(i4 << 1)];
            int[] iArr2 = yzVar.h() > 0 ? new int[yzVar.h()] : null;
            int[] iArr3 = yzVar.i() > 0 ? new int[yzVar.i()] : null;
            za d2 = yzVar.d();
            if (d2.a()) {
                int b2 = d2.b();
                int i8 = 0;
                int i9 = 0;
                int i10 = 0;
                while (true) {
                    if (b2 >= yzVar.l() || i8 >= ((b2 - i3) << 2)) {
                        if (d2.d()) {
                            i7 = (int) aab.a(d2.e());
                            i6 = (int) aab.a(d2.f());
                            i5 = 0;
                        } else {
                            i7 = (int) aab.a(d2.g());
                            if (d2.h()) {
                                i6 = (int) aab.a(d2.i());
                                i5 = d2.j();
                            } else {
                                i6 = 0;
                                i5 = 0;
                            }
                        }
                        iArr[i8] = d2.b();
                        int i11 = i8 + 1;
                        iArr[i11] = (d2.l() ? ErrorDialogData.DYNAMITE_CRASH : 0) | (d2.k() ? ErrorDialogData.BINDER_CRASH : 0) | (d2.c() << 20) | i7;
                        iArr[i8 + 2] = i6 | (i5 << 20);
                        if (d2.o() != null) {
                            int i12 = (i8 / 4) << 1;
                            objArr[i12] = d2.o();
                            if (d2.m() != null) {
                                objArr[i12 + 1] = d2.m();
                            } else if (d2.n() != null) {
                                objArr[i12 + 1] = d2.n();
                            }
                        } else if (d2.m() != null) {
                            objArr[((i8 / 4) << 1) + 1] = d2.m();
                        } else if (d2.n() != null) {
                            objArr[((i8 / 4) << 1) + 1] = d2.n();
                        }
                        int c2 = d2.c();
                        if (c2 == zzbbj.MAP.ordinal()) {
                            int i13 = i9 + 1;
                            iArr2[i9] = i8;
                            i9 = i13;
                        } else if (c2 >= 18 && c2 <= 49) {
                            int i14 = i10 + 1;
                            iArr3[i10] = iArr[i11] & 1048575;
                            i10 = i14;
                        }
                        if (!d2.a()) {
                            break;
                        }
                        b2 = d2.b();
                    } else {
                        for (int i15 = 0; i15 < 4; i15++) {
                            iArr[i8 + i15] = -1;
                        }
                    }
                    i8 += 4;
                }
            }
            yo yoVar = new yo(iArr, objArr, i3, i2, yzVar.l(), yzVar.c(), z, false, yzVar.j(), iArr2, iArr3, ytVar, xvVar, zvVar, wyVar, yfVar);
            return yoVar;
        }
        ((zq) yiVar2).a();
        throw new NoSuchMethodError();
    }

    private final zd a(int i2) {
        int i3 = (i2 / 4) << 1;
        zd zdVar = (zd) this.c[i3];
        if (zdVar != null) {
            return zdVar;
        }
        zd a2 = yx.a().a((Class) this.c[i3 + 1]);
        this.c[i3] = a2;
        return a2;
    }

    private final <K, V, UT, UB> UB a(int i2, int i3, Map<K, V> map, xl<?> xlVar, UB ub, zv<UT, UB> zvVar) {
        ye f2 = this.s.f(b(i2));
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (xlVar.a(((Integer) entry.getValue()).intValue()) == null) {
                if (ub == null) {
                    ub = zvVar.a();
                }
                wj zzbo = zzbah.zzbo(yd.a(f2, entry.getKey(), entry.getValue()));
                try {
                    yd.a(zzbo.b(), f2, entry.getKey(), entry.getValue());
                    zvVar.a(ub, i3, zzbo.a());
                    it.remove();
                } catch (IOException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }
        return ub;
    }

    private final <UT, UB> UB a(Object obj, int i2, UB ub, zv<UT, UB> zvVar) {
        int i3 = this.b[i2];
        Object f2 = aab.f(obj, (long) (d(i2) & 1048575));
        if (f2 == null) {
            return ub;
        }
        xl c2 = c(i2);
        if (c2 == null) {
            return ub;
        }
        return a(i2, i3, this.s.a(f2), c2, ub, zvVar);
    }

    private static <E> List<E> a(Object obj, long j2) {
        return (List) aab.f(obj, j2);
    }

    private static void a(int i2, Object obj, aai aai) throws IOException {
        if (obj instanceof String) {
            aai.a(i2, (String) obj);
        } else {
            aai.a(i2, (zzbah) obj);
        }
    }

    private final <K, V> void a(aai aai, int i2, Object obj, int i3) throws IOException {
        if (obj != null) {
            aai.a(i2, this.s.f(b(i3)), this.s.b(obj));
        }
    }

    private static <UT, UB> void a(zv<UT, UB> zvVar, T t, aai aai) throws IOException {
        zvVar.a(zvVar.b(t), aai);
    }

    private final void a(Object obj, int i2, zc zcVar) throws IOException {
        long j2;
        Object n2;
        if (f(i2)) {
            j2 = (long) (i2 & 1048575);
            n2 = zcVar.m();
        } else if (this.i) {
            j2 = (long) (i2 & 1048575);
            n2 = zcVar.l();
        } else {
            j2 = (long) (i2 & 1048575);
            n2 = zcVar.n();
        }
        aab.a(obj, j2, n2);
    }

    private final void a(T t, T t2, int i2) {
        long d2 = (long) (d(i2) & 1048575);
        if (a(t2, i2)) {
            Object f2 = aab.f(t, d2);
            Object f3 = aab.f(t2, d2);
            if (f2 == null || f3 == null) {
                if (f3 != null) {
                    aab.a((Object) t, d2, f3);
                    b(t, i2);
                }
                return;
            }
            aab.a((Object) t, d2, xj.a(f2, f3));
            b(t, i2);
        }
    }

    private final boolean a(T t, int i2) {
        if (this.j) {
            int d2 = d(i2);
            long j2 = (long) (d2 & 1048575);
            switch ((d2 & 267386880) >>> 20) {
                case 0:
                    return aab.e(t, j2) != 0.0d;
                case 1:
                    return aab.d(t, j2) != 0.0f;
                case 2:
                    return aab.b(t, j2) != 0;
                case 3:
                    return aab.b(t, j2) != 0;
                case 4:
                    return aab.a((Object) t, j2) != 0;
                case 5:
                    return aab.b(t, j2) != 0;
                case 6:
                    return aab.a((Object) t, j2) != 0;
                case 7:
                    return aab.c(t, j2);
                case 8:
                    Object f2 = aab.f(t, j2);
                    if (f2 instanceof String) {
                        return !((String) f2).isEmpty();
                    }
                    if (f2 instanceof zzbah) {
                        return !zzbah.zzdpq.equals(f2);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return aab.f(t, j2) != null;
                case 10:
                    return !zzbah.zzdpq.equals(aab.f(t, j2));
                case 11:
                    return aab.a((Object) t, j2) != 0;
                case 12:
                    return aab.a((Object) t, j2) != 0;
                case 13:
                    return aab.a((Object) t, j2) != 0;
                case 14:
                    return aab.b(t, j2) != 0;
                case 15:
                    return aab.a((Object) t, j2) != 0;
                case 16:
                    return aab.b(t, j2) != 0;
                case 17:
                    return aab.f(t, j2) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int e2 = e(i2);
            return (aab.a((Object) t, (long) (e2 & 1048575)) & (1 << (e2 >>> 20))) != 0;
        }
    }

    private final boolean a(T t, int i2, int i3) {
        return aab.a((Object) t, (long) (e(i3) & 1048575)) == i2;
    }

    private final boolean a(T t, int i2, int i3, int i4) {
        return this.j ? a(t, i2) : (i3 & i4) != 0;
    }

    private static boolean a(Object obj, int i2, zd zdVar) {
        return zdVar.d(aab.f(obj, (long) (i2 & 1048575)));
    }

    private static <T> double b(T t, long j2) {
        return ((Double) aab.f(t, j2)).doubleValue();
    }

    private final Object b(int i2) {
        return this.c[(i2 / 4) << 1];
    }

    private final void b(T t, int i2) {
        if (!this.j) {
            int e2 = e(i2);
            long j2 = (long) (e2 & 1048575);
            aab.a((Object) t, j2, aab.a((Object) t, j2) | (1 << (e2 >>> 20)));
        }
    }

    private final void b(T t, int i2, int i3) {
        aab.a((Object) t, (long) (e(i3) & 1048575), i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0344, code lost:
        r14 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x051d, code lost:
        r5 = r12 + 4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0523  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void b(T r21, com.google.android.gms.internal.ads.aai r22) throws java.io.IOException {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r22
            boolean r3 = r0.h
            if (r3 == 0) goto L_0x0021
            com.google.android.gms.internal.ads.wy<?> r3 = r0.r
            com.google.android.gms.internal.ads.xb r3 = r3.a(r1)
            boolean r5 = r3.b()
            if (r5 != 0) goto L_0x0021
            java.util.Iterator r3 = r3.e()
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0023
        L_0x0021:
            r3 = 0
            r5 = 0
        L_0x0023:
            r6 = -1
            int[] r7 = r0.b
            int r7 = r7.length
            sun.misc.Unsafe r9 = a
            r10 = r5
            r5 = 0
            r11 = 0
        L_0x002c:
            if (r5 >= r7) goto L_0x0521
            int r12 = r0.d(r5)
            int[] r13 = r0.b
            r13 = r13[r5]
            r14 = 267386880(0xff00000, float:2.3665827E-29)
            r14 = r14 & r12
            int r14 = r14 >>> 20
            boolean r15 = r0.j
            r16 = 1048575(0xfffff, float:1.469367E-39)
            if (r15 != 0) goto L_0x0061
            r15 = 17
            if (r14 > r15) goto L_0x0061
            int[] r15 = r0.b
            int r17 = r5 + 2
            r15 = r15[r17]
            r8 = r15 & r16
            if (r8 == r6) goto L_0x0059
            r18 = r5
            long r4 = (long) r8
            int r11 = r9.getInt(r1, r4)
            r6 = r8
            goto L_0x005b
        L_0x0059:
            r18 = r5
        L_0x005b:
            int r4 = r15 >>> 20
            r5 = 1
            int r8 = r5 << r4
            goto L_0x0064
        L_0x0061:
            r18 = r5
            r8 = 0
        L_0x0064:
            if (r10 == 0) goto L_0x0083
            com.google.android.gms.internal.ads.wy<?> r4 = r0.r
            int r4 = r4.a(r10)
            if (r4 > r13) goto L_0x0083
            com.google.android.gms.internal.ads.wy<?> r4 = r0.r
            r4.a(r2, r10)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0081
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r10 = r4
            goto L_0x0064
        L_0x0081:
            r10 = 0
            goto L_0x0064
        L_0x0083:
            r4 = r12 & r16
            long r4 = (long) r4
            switch(r14) {
                case 0: goto L_0x0510;
                case 1: goto L_0x0502;
                case 2: goto L_0x04f4;
                case 3: goto L_0x04e6;
                case 4: goto L_0x04d8;
                case 5: goto L_0x04ca;
                case 6: goto L_0x04bc;
                case 7: goto L_0x04ae;
                case 8: goto L_0x049f;
                case 9: goto L_0x048c;
                case 10: goto L_0x047b;
                case 11: goto L_0x046c;
                case 12: goto L_0x045d;
                case 13: goto L_0x044e;
                case 14: goto L_0x043f;
                case 15: goto L_0x0430;
                case 16: goto L_0x0421;
                case 17: goto L_0x040e;
                case 18: goto L_0x03fc;
                case 19: goto L_0x03ea;
                case 20: goto L_0x03d8;
                case 21: goto L_0x03c6;
                case 22: goto L_0x03b4;
                case 23: goto L_0x03a2;
                case 24: goto L_0x0390;
                case 25: goto L_0x037e;
                case 26: goto L_0x036d;
                case 27: goto L_0x0358;
                case 28: goto L_0x0347;
                case 29: goto L_0x0334;
                case 30: goto L_0x0323;
                case 31: goto L_0x0312;
                case 32: goto L_0x0301;
                case 33: goto L_0x02f0;
                case 34: goto L_0x02df;
                case 35: goto L_0x02cd;
                case 36: goto L_0x02bb;
                case 37: goto L_0x02a9;
                case 38: goto L_0x0297;
                case 39: goto L_0x0285;
                case 40: goto L_0x0273;
                case 41: goto L_0x0261;
                case 42: goto L_0x024f;
                case 43: goto L_0x023d;
                case 44: goto L_0x022b;
                case 45: goto L_0x0219;
                case 46: goto L_0x0207;
                case 47: goto L_0x01f5;
                case 48: goto L_0x01e3;
                case 49: goto L_0x01ce;
                case 50: goto L_0x01c3;
                case 51: goto L_0x01b2;
                case 52: goto L_0x01a1;
                case 53: goto L_0x0190;
                case 54: goto L_0x017f;
                case 55: goto L_0x016e;
                case 56: goto L_0x015d;
                case 57: goto L_0x014c;
                case 58: goto L_0x013b;
                case 59: goto L_0x012a;
                case 60: goto L_0x0115;
                case 61: goto L_0x0102;
                case 62: goto L_0x00f2;
                case 63: goto L_0x00e2;
                case 64: goto L_0x00d2;
                case 65: goto L_0x00c2;
                case 66: goto L_0x00b2;
                case 67: goto L_0x00a2;
                case 68: goto L_0x008e;
                default: goto L_0x0089;
            }
        L_0x0089:
            r12 = r18
        L_0x008b:
            r14 = 0
            goto L_0x051d
        L_0x008e:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            java.lang.Object r4 = r9.getObject(r1, r4)
            com.google.android.gms.internal.ads.zd r5 = r0.a(r12)
            r2.b(r13, r4, r5)
            goto L_0x008b
        L_0x00a2:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            long r4 = e(r1, r4)
            r2.e(r13, r4)
            goto L_0x008b
        L_0x00b2:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            int r4 = d(r1, r4)
            r2.f(r13, r4)
            goto L_0x008b
        L_0x00c2:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            long r4 = e(r1, r4)
            r2.b(r13, r4)
            goto L_0x008b
        L_0x00d2:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            int r4 = d(r1, r4)
            r2.a(r13, r4)
            goto L_0x008b
        L_0x00e2:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            int r4 = d(r1, r4)
            r2.b(r13, r4)
            goto L_0x008b
        L_0x00f2:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            int r4 = d(r1, r4)
            r2.e(r13, r4)
            goto L_0x008b
        L_0x0102:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            java.lang.Object r4 = r9.getObject(r1, r4)
            com.google.android.gms.internal.ads.zzbah r4 = (com.google.android.gms.internal.ads.zzbah) r4
            r2.a(r13, r4)
            goto L_0x008b
        L_0x0115:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            java.lang.Object r4 = r9.getObject(r1, r4)
            com.google.android.gms.internal.ads.zd r5 = r0.a(r12)
            r2.a(r13, r4, r5)
            goto L_0x008b
        L_0x012a:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            java.lang.Object r4 = r9.getObject(r1, r4)
            a(r13, r4, r2)
            goto L_0x008b
        L_0x013b:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            boolean r4 = f(r1, r4)
            r2.a(r13, r4)
            goto L_0x008b
        L_0x014c:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            int r4 = d(r1, r4)
            r2.d(r13, r4)
            goto L_0x008b
        L_0x015d:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            long r4 = e(r1, r4)
            r2.d(r13, r4)
            goto L_0x008b
        L_0x016e:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            int r4 = d(r1, r4)
            r2.c(r13, r4)
            goto L_0x008b
        L_0x017f:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            long r4 = e(r1, r4)
            r2.c(r13, r4)
            goto L_0x008b
        L_0x0190:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            long r4 = e(r1, r4)
            r2.a(r13, r4)
            goto L_0x008b
        L_0x01a1:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            float r4 = c(r1, r4)
            r2.a(r13, r4)
            goto L_0x008b
        L_0x01b2:
            r12 = r18
            boolean r8 = r0.a((T) r1, r13, r12)
            if (r8 == 0) goto L_0x008b
            double r4 = b((T) r1, r4)
            r2.a(r13, r4)
            goto L_0x008b
        L_0x01c3:
            r12 = r18
            java.lang.Object r4 = r9.getObject(r1, r4)
            r0.a(r2, r13, r4, r12)
            goto L_0x008b
        L_0x01ce:
            r12 = r18
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zd r5 = r0.a(r12)
            com.google.android.gms.internal.ads.zf.b(r8, r4, r2, r5)
            goto L_0x008b
        L_0x01e3:
            r12 = r18
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            r13 = 1
            com.google.android.gms.internal.ads.zf.e(r8, r4, r2, r13)
            goto L_0x008b
        L_0x01f5:
            r12 = r18
            r13 = 1
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.j(r8, r4, r2, r13)
            goto L_0x008b
        L_0x0207:
            r12 = r18
            r13 = 1
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.g(r8, r4, r2, r13)
            goto L_0x008b
        L_0x0219:
            r12 = r18
            r13 = 1
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.l(r8, r4, r2, r13)
            goto L_0x008b
        L_0x022b:
            r12 = r18
            r13 = 1
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.m(r8, r4, r2, r13)
            goto L_0x008b
        L_0x023d:
            r12 = r18
            r13 = 1
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.i(r8, r4, r2, r13)
            goto L_0x008b
        L_0x024f:
            r12 = r18
            r13 = 1
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.n(r8, r4, r2, r13)
            goto L_0x008b
        L_0x0261:
            r12 = r18
            r13 = 1
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.k(r8, r4, r2, r13)
            goto L_0x008b
        L_0x0273:
            r12 = r18
            r13 = 1
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.f(r8, r4, r2, r13)
            goto L_0x008b
        L_0x0285:
            r12 = r18
            r13 = 1
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.h(r8, r4, r2, r13)
            goto L_0x008b
        L_0x0297:
            r12 = r18
            r13 = 1
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.d(r8, r4, r2, r13)
            goto L_0x008b
        L_0x02a9:
            r12 = r18
            r13 = 1
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.c(r8, r4, r2, r13)
            goto L_0x008b
        L_0x02bb:
            r12 = r18
            r13 = 1
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.b(r8, r4, r2, r13)
            goto L_0x008b
        L_0x02cd:
            r12 = r18
            r13 = 1
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.a(r8, r4, r2, r13)
            goto L_0x008b
        L_0x02df:
            r12 = r18
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            r13 = 0
            com.google.android.gms.internal.ads.zf.e(r8, r4, r2, r13)
            goto L_0x0344
        L_0x02f0:
            r12 = r18
            r13 = 0
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.j(r8, r4, r2, r13)
            goto L_0x0344
        L_0x0301:
            r12 = r18
            r13 = 0
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.g(r8, r4, r2, r13)
            goto L_0x0344
        L_0x0312:
            r12 = r18
            r13 = 0
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.l(r8, r4, r2, r13)
            goto L_0x0344
        L_0x0323:
            r12 = r18
            r13 = 0
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.m(r8, r4, r2, r13)
            goto L_0x0344
        L_0x0334:
            r12 = r18
            r13 = 0
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.i(r8, r4, r2, r13)
        L_0x0344:
            r14 = r13
            goto L_0x051d
        L_0x0347:
            r12 = r18
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.b(r8, r4, r2)
            goto L_0x008b
        L_0x0358:
            r12 = r18
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zd r5 = r0.a(r12)
            com.google.android.gms.internal.ads.zf.a(r8, r4, r2, r5)
            goto L_0x008b
        L_0x036d:
            r12 = r18
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.a(r8, r4, r2)
            goto L_0x008b
        L_0x037e:
            r12 = r18
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            r14 = 0
            com.google.android.gms.internal.ads.zf.n(r8, r4, r2, r14)
            goto L_0x051d
        L_0x0390:
            r12 = r18
            r14 = 0
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.k(r8, r4, r2, r14)
            goto L_0x051d
        L_0x03a2:
            r12 = r18
            r14 = 0
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.f(r8, r4, r2, r14)
            goto L_0x051d
        L_0x03b4:
            r12 = r18
            r14 = 0
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.h(r8, r4, r2, r14)
            goto L_0x051d
        L_0x03c6:
            r12 = r18
            r14 = 0
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.d(r8, r4, r2, r14)
            goto L_0x051d
        L_0x03d8:
            r12 = r18
            r14 = 0
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.c(r8, r4, r2, r14)
            goto L_0x051d
        L_0x03ea:
            r12 = r18
            r14 = 0
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.b(r8, r4, r2, r14)
            goto L_0x051d
        L_0x03fc:
            r12 = r18
            r14 = 0
            int[] r8 = r0.b
            r8 = r8[r12]
            java.lang.Object r4 = r9.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.ads.zf.a(r8, r4, r2, r14)
            goto L_0x051d
        L_0x040e:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            java.lang.Object r4 = r9.getObject(r1, r4)
            com.google.android.gms.internal.ads.zd r5 = r0.a(r12)
            r2.b(r13, r4, r5)
            goto L_0x051d
        L_0x0421:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            long r4 = r9.getLong(r1, r4)
            r2.e(r13, r4)
            goto L_0x051d
        L_0x0430:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            int r4 = r9.getInt(r1, r4)
            r2.f(r13, r4)
            goto L_0x051d
        L_0x043f:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            long r4 = r9.getLong(r1, r4)
            r2.b(r13, r4)
            goto L_0x051d
        L_0x044e:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            int r4 = r9.getInt(r1, r4)
            r2.a(r13, r4)
            goto L_0x051d
        L_0x045d:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            int r4 = r9.getInt(r1, r4)
            r2.b(r13, r4)
            goto L_0x051d
        L_0x046c:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            int r4 = r9.getInt(r1, r4)
            r2.e(r13, r4)
            goto L_0x051d
        L_0x047b:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            java.lang.Object r4 = r9.getObject(r1, r4)
            com.google.android.gms.internal.ads.zzbah r4 = (com.google.android.gms.internal.ads.zzbah) r4
            r2.a(r13, r4)
            goto L_0x051d
        L_0x048c:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            java.lang.Object r4 = r9.getObject(r1, r4)
            com.google.android.gms.internal.ads.zd r5 = r0.a(r12)
            r2.a(r13, r4, r5)
            goto L_0x051d
        L_0x049f:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            java.lang.Object r4 = r9.getObject(r1, r4)
            a(r13, r4, r2)
            goto L_0x051d
        L_0x04ae:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            boolean r4 = com.google.android.gms.internal.ads.aab.c(r1, r4)
            r2.a(r13, r4)
            goto L_0x051d
        L_0x04bc:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            int r4 = r9.getInt(r1, r4)
            r2.d(r13, r4)
            goto L_0x051d
        L_0x04ca:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            long r4 = r9.getLong(r1, r4)
            r2.d(r13, r4)
            goto L_0x051d
        L_0x04d8:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            int r4 = r9.getInt(r1, r4)
            r2.c(r13, r4)
            goto L_0x051d
        L_0x04e6:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            long r4 = r9.getLong(r1, r4)
            r2.c(r13, r4)
            goto L_0x051d
        L_0x04f4:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            long r4 = r9.getLong(r1, r4)
            r2.a(r13, r4)
            goto L_0x051d
        L_0x0502:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            float r4 = com.google.android.gms.internal.ads.aab.d(r1, r4)
            r2.a(r13, r4)
            goto L_0x051d
        L_0x0510:
            r12 = r18
            r14 = 0
            r8 = r8 & r11
            if (r8 == 0) goto L_0x051d
            double r4 = com.google.android.gms.internal.ads.aab.e(r1, r4)
            r2.a(r13, r4)
        L_0x051d:
            int r5 = r12 + 4
            goto L_0x002c
        L_0x0521:
            if (r10 == 0) goto L_0x0538
            com.google.android.gms.internal.ads.wy<?> r4 = r0.r
            r4.a(r2, r10)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0536
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r10 = r4
            goto L_0x0521
        L_0x0536:
            r10 = 0
            goto L_0x0521
        L_0x0538:
            com.google.android.gms.internal.ads.zv<?, ?> r3 = r0.q
            a(r3, (T) r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yo.b(java.lang.Object, com.google.android.gms.internal.ads.aai):void");
    }

    private final void b(T t, T t2, int i2) {
        int d2 = d(i2);
        int i3 = this.b[i2];
        long j2 = (long) (d2 & 1048575);
        if (a(t2, i3, i2)) {
            Object f2 = aab.f(t, j2);
            Object f3 = aab.f(t2, j2);
            if (f2 == null || f3 == null) {
                if (f3 != null) {
                    aab.a((Object) t, j2, f3);
                    b(t, i3, i2);
                }
                return;
            }
            aab.a((Object) t, j2, xj.a(f2, f3));
            b(t, i3, i2);
        }
    }

    private static <T> float c(T t, long j2) {
        return ((Float) aab.f(t, j2)).floatValue();
    }

    private final xl<?> c(int i2) {
        return (xl) this.c[((i2 / 4) << 1) + 1];
    }

    private final boolean c(T t, T t2, int i2) {
        return a(t, i2) == a(t2, i2);
    }

    private final int d(int i2) {
        return this.b[i2 + 1];
    }

    private static <T> int d(T t, long j2) {
        return ((Integer) aab.f(t, j2)).intValue();
    }

    private final int e(int i2) {
        return this.b[i2 + 2];
    }

    private static <T> long e(T t, long j2) {
        return ((Long) aab.f(t, j2)).longValue();
    }

    private static zw e(Object obj) {
        xh xhVar = (xh) obj;
        zw zwVar = xhVar.zzdtt;
        if (zwVar != zw.a()) {
            return zwVar;
        }
        zw b2 = zw.b();
        xhVar.zzdtt = b2;
        return b2;
    }

    private static boolean f(int i2) {
        return (i2 & ErrorDialogData.DYNAMITE_CRASH) != 0;
    }

    private static <T> boolean f(T t, long j2) {
        return ((Boolean) aab.f(t, j2)).booleanValue();
    }

    private final int g(int i2) {
        if (i2 >= this.d) {
            if (i2 < this.f) {
                int i3 = (i2 - this.d) << 2;
                if (this.b[i3] == i2) {
                    return i3;
                }
                return -1;
            } else if (i2 <= this.e) {
                int i4 = this.f - this.d;
                int length = (this.b.length / 4) - 1;
                while (i4 <= length) {
                    int i5 = (length + i4) >>> 1;
                    int i6 = i5 << 2;
                    int i7 = this.b[i6];
                    if (i2 == i7) {
                        return i6;
                    }
                    if (i2 < i7) {
                        length = i5 - 1;
                    } else {
                        i4 = i5 + 1;
                    }
                }
            }
        }
        return -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0061, code lost:
        r3 = com.google.android.gms.internal.ads.aab.f(r9, r5);
        r2 = r2 * 53;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0093, code lost:
        r2 = r2 * 53;
        r3 = d(r9, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a8, code lost:
        r2 = r2 * 53;
        r3 = e(r9, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ce, code lost:
        if (r3 != null) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d1, code lost:
        r2 = r2 * 53;
        r3 = com.google.android.gms.internal.ads.aab.f(r9, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d7, code lost:
        r3 = r3.hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e0, code lost:
        if (r3 != null) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e2, code lost:
        r7 = r3.hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e6, code lost:
        r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ea, code lost:
        r2 = r2 * 53;
        r3 = ((java.lang.String) com.google.android.gms.internal.ads.aab.f(r9, r5)).hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00fd, code lost:
        r3 = com.google.android.gms.internal.ads.xj.a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0116, code lost:
        r3 = java.lang.Float.floatToIntBits(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0121, code lost:
        r3 = java.lang.Double.doubleToLongBits(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0125, code lost:
        r3 = com.google.android.gms.internal.ads.xj.a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0129, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x012a, code lost:
        r1 = r1 + 4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(T r9) {
        /*
            r8 = this;
            int[] r0 = r8.b
            r1 = 0
            int r0 = r0.length
            r2 = r1
        L_0x0005:
            if (r1 >= r0) goto L_0x012e
            int r3 = r8.d(r1)
            int[] r4 = r8.b
            r4 = r4[r1]
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r3
            long r5 = (long) r5
            r7 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = r3 & r7
            int r3 = r3 >>> 20
            r7 = 37
            switch(r3) {
                case 0: goto L_0x011b;
                case 1: goto L_0x0110;
                case 2: goto L_0x0109;
                case 3: goto L_0x0109;
                case 4: goto L_0x0102;
                case 5: goto L_0x0109;
                case 6: goto L_0x0102;
                case 7: goto L_0x00f7;
                case 8: goto L_0x00ea;
                case 9: goto L_0x00dc;
                case 10: goto L_0x00d1;
                case 11: goto L_0x0102;
                case 12: goto L_0x0102;
                case 13: goto L_0x0102;
                case 14: goto L_0x0109;
                case 15: goto L_0x0102;
                case 16: goto L_0x0109;
                case 17: goto L_0x00ca;
                case 18: goto L_0x00d1;
                case 19: goto L_0x00d1;
                case 20: goto L_0x00d1;
                case 21: goto L_0x00d1;
                case 22: goto L_0x00d1;
                case 23: goto L_0x00d1;
                case 24: goto L_0x00d1;
                case 25: goto L_0x00d1;
                case 26: goto L_0x00d1;
                case 27: goto L_0x00d1;
                case 28: goto L_0x00d1;
                case 29: goto L_0x00d1;
                case 30: goto L_0x00d1;
                case 31: goto L_0x00d1;
                case 32: goto L_0x00d1;
                case 33: goto L_0x00d1;
                case 34: goto L_0x00d1;
                case 35: goto L_0x00d1;
                case 36: goto L_0x00d1;
                case 37: goto L_0x00d1;
                case 38: goto L_0x00d1;
                case 39: goto L_0x00d1;
                case 40: goto L_0x00d1;
                case 41: goto L_0x00d1;
                case 42: goto L_0x00d1;
                case 43: goto L_0x00d1;
                case 44: goto L_0x00d1;
                case 45: goto L_0x00d1;
                case 46: goto L_0x00d1;
                case 47: goto L_0x00d1;
                case 48: goto L_0x00d1;
                case 49: goto L_0x00d1;
                case 50: goto L_0x00d1;
                case 51: goto L_0x00bd;
                case 52: goto L_0x00b0;
                case 53: goto L_0x00a2;
                case 54: goto L_0x009b;
                case 55: goto L_0x008d;
                case 56: goto L_0x0086;
                case 57: goto L_0x007f;
                case 58: goto L_0x0071;
                case 59: goto L_0x0069;
                case 60: goto L_0x005b;
                case 61: goto L_0x0053;
                case 62: goto L_0x004c;
                case 63: goto L_0x0045;
                case 64: goto L_0x003e;
                case 65: goto L_0x0036;
                case 66: goto L_0x002f;
                case 67: goto L_0x0027;
                case 68: goto L_0x0020;
                default: goto L_0x001e;
            }
        L_0x001e:
            goto L_0x012a
        L_0x0020:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x0061
        L_0x0027:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00a8
        L_0x002f:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x004b
        L_0x0036:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00a8
        L_0x003e:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x004b
        L_0x0045:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
        L_0x004b:
            goto L_0x0093
        L_0x004c:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x0093
        L_0x0053:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00d1
        L_0x005b:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
        L_0x0061:
            java.lang.Object r3 = com.google.android.gms.internal.ads.aab.f(r9, r5)
            int r2 = r2 * 53
            goto L_0x00d7
        L_0x0069:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00ea
        L_0x0071:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            int r2 = r2 * 53
            boolean r3 = f(r9, r5)
            goto L_0x00fd
        L_0x007f:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x0093
        L_0x0086:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00a8
        L_0x008d:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
        L_0x0093:
            int r2 = r2 * 53
            int r3 = d(r9, r5)
            goto L_0x0129
        L_0x009b:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00a8
        L_0x00a2:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
        L_0x00a8:
            int r2 = r2 * 53
            long r3 = e(r9, r5)
            goto L_0x0125
        L_0x00b0:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            int r2 = r2 * 53
            float r3 = c(r9, r5)
            goto L_0x0116
        L_0x00bd:
            boolean r3 = r8.a((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            int r2 = r2 * 53
            double r3 = b((T) r9, r5)
            goto L_0x0121
        L_0x00ca:
            java.lang.Object r3 = com.google.android.gms.internal.ads.aab.f(r9, r5)
            if (r3 == 0) goto L_0x00e6
            goto L_0x00e2
        L_0x00d1:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.ads.aab.f(r9, r5)
        L_0x00d7:
            int r3 = r3.hashCode()
            goto L_0x0129
        L_0x00dc:
            java.lang.Object r3 = com.google.android.gms.internal.ads.aab.f(r9, r5)
            if (r3 == 0) goto L_0x00e6
        L_0x00e2:
            int r7 = r3.hashCode()
        L_0x00e6:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x012a
        L_0x00ea:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.ads.aab.f(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0129
        L_0x00f7:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.ads.aab.c(r9, r5)
        L_0x00fd:
            int r3 = com.google.android.gms.internal.ads.xj.a(r3)
            goto L_0x0129
        L_0x0102:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.ads.aab.a(r9, r5)
            goto L_0x0129
        L_0x0109:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.ads.aab.b(r9, r5)
            goto L_0x0125
        L_0x0110:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.ads.aab.d(r9, r5)
        L_0x0116:
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0129
        L_0x011b:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.ads.aab.e(r9, r5)
        L_0x0121:
            long r3 = java.lang.Double.doubleToLongBits(r3)
        L_0x0125:
            int r3 = com.google.android.gms.internal.ads.xj.a(r3)
        L_0x0129:
            int r2 = r2 + r3
        L_0x012a:
            int r1 = r1 + 4
            goto L_0x0005
        L_0x012e:
            int r2 = r2 * 53
            com.google.android.gms.internal.ads.zv<?, ?> r0 = r8.q
            java.lang.Object r0 = r0.b(r9)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r8.h
            if (r0 == 0) goto L_0x014c
            int r2 = r2 * 53
            com.google.android.gms.internal.ads.wy<?> r0 = r8.r
            com.google.android.gms.internal.ads.xb r9 = r0.a(r9)
            int r9 = r9.hashCode()
            int r2 = r2 + r9
        L_0x014c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yo.a(java.lang.Object):int");
    }

    public final T a() {
        return this.o.a(this.g);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0385, code lost:
        r15.b(r9, com.google.android.gms.internal.ads.aab.f(r14, (long) (r8 & 1048575)), a(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x03a0, code lost:
        r15.e(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x03b1, code lost:
        r15.f(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x03c2, code lost:
        r15.b(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x03d3, code lost:
        r15.a(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x03e4, code lost:
        r15.b(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x03f5, code lost:
        r15.e(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0400, code lost:
        r15.a(r9, (com.google.android.gms.internal.ads.zzbah) com.google.android.gms.internal.ads.aab.f(r14, (long) (r8 & 1048575)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0413, code lost:
        r15.a(r9, com.google.android.gms.internal.ads.aab.f(r14, (long) (r8 & 1048575)), a(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0428, code lost:
        a(r9, com.google.android.gms.internal.ads.aab.f(r14, (long) (r8 & 1048575)), r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x043f, code lost:
        r15.a(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0450, code lost:
        r15.d(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0460, code lost:
        r15.d(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0470, code lost:
        r15.c(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0480, code lost:
        r15.c(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0490, code lost:
        r15.a(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x04a0, code lost:
        r15.a(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x04b0, code lost:
        r15.a(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x0843, code lost:
        r15.b(r10, com.google.android.gms.internal.ads.aab.f(r14, (long) (r9 & 1048575)), a(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x085e, code lost:
        r15.e(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x086f, code lost:
        r15.f(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x0880, code lost:
        r15.b(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:0x0891, code lost:
        r15.a(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x08a2, code lost:
        r15.b(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x08b3, code lost:
        r15.e(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x08be, code lost:
        r15.a(r10, (com.google.android.gms.internal.ads.zzbah) com.google.android.gms.internal.ads.aab.f(r14, (long) (r9 & 1048575)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x08d1, code lost:
        r15.a(r10, com.google.android.gms.internal.ads.aab.f(r14, (long) (r9 & 1048575)), a(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x08e6, code lost:
        a(r10, com.google.android.gms.internal.ads.aab.f(r14, (long) (r9 & 1048575)), r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x08fd, code lost:
        r15.a(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x090e, code lost:
        r15.d(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x091e, code lost:
        r15.d(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x092e, code lost:
        r15.c(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x093e, code lost:
        r15.c(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:340:0x094e, code lost:
        r15.a(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x095e, code lost:
        r15.a(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x096e, code lost:
        r15.a(r10, r11);
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x04b9  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x04f7  */
    /* JADX WARNING: Removed duplicated region for block: B:351:0x0977  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(T r14, com.google.android.gms.internal.ads.aai r15) throws java.io.IOException {
        /*
            r13 = this;
            int r0 = r15.a()
            int r1 = com.google.android.gms.internal.ads.xh.e.k
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x04cf
            com.google.android.gms.internal.ads.zv<?, ?> r0 = r13.q
            a(r0, (T) r14, r15)
            boolean r0 = r13.h
            if (r0 == 0) goto L_0x0030
            com.google.android.gms.internal.ads.wy<?> r0 = r13.r
            com.google.android.gms.internal.ads.xb r0 = r0.a(r14)
            boolean r1 = r0.b()
            if (r1 != 0) goto L_0x0030
            java.util.Iterator r0 = r0.f()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0032
        L_0x0030:
            r0 = r3
            r1 = r0
        L_0x0032:
            int[] r7 = r13.b
            int r7 = r7.length
            int r7 = r7 + -4
        L_0x0037:
            if (r7 < 0) goto L_0x04b7
            int r8 = r13.d(r7)
            int[] r9 = r13.b
            r9 = r9[r7]
        L_0x0041:
            if (r1 == 0) goto L_0x005f
            com.google.android.gms.internal.ads.wy<?> r10 = r13.r
            int r10 = r10.a(r1)
            if (r10 <= r9) goto L_0x005f
            com.google.android.gms.internal.ads.wy<?> r10 = r13.r
            r10.a(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005d
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0041
        L_0x005d:
            r1 = r3
            goto L_0x0041
        L_0x005f:
            r10 = r8 & r2
            int r10 = r10 >>> 20
            switch(r10) {
                case 0: goto L_0x04a4;
                case 1: goto L_0x0494;
                case 2: goto L_0x0484;
                case 3: goto L_0x0474;
                case 4: goto L_0x0464;
                case 5: goto L_0x0454;
                case 6: goto L_0x0444;
                case 7: goto L_0x0433;
                case 8: goto L_0x0422;
                case 9: goto L_0x040d;
                case 10: goto L_0x03fa;
                case 11: goto L_0x03e9;
                case 12: goto L_0x03d8;
                case 13: goto L_0x03c7;
                case 14: goto L_0x03b6;
                case 15: goto L_0x03a5;
                case 16: goto L_0x0394;
                case 17: goto L_0x037f;
                case 18: goto L_0x036e;
                case 19: goto L_0x035d;
                case 20: goto L_0x034c;
                case 21: goto L_0x033b;
                case 22: goto L_0x032a;
                case 23: goto L_0x0319;
                case 24: goto L_0x0308;
                case 25: goto L_0x02f7;
                case 26: goto L_0x02e6;
                case 27: goto L_0x02d1;
                case 28: goto L_0x02c0;
                case 29: goto L_0x02af;
                case 30: goto L_0x029e;
                case 31: goto L_0x028d;
                case 32: goto L_0x027c;
                case 33: goto L_0x026b;
                case 34: goto L_0x025a;
                case 35: goto L_0x0249;
                case 36: goto L_0x0238;
                case 37: goto L_0x0227;
                case 38: goto L_0x0216;
                case 39: goto L_0x0205;
                case 40: goto L_0x01f4;
                case 41: goto L_0x01e3;
                case 42: goto L_0x01d2;
                case 43: goto L_0x01c1;
                case 44: goto L_0x01b0;
                case 45: goto L_0x019f;
                case 46: goto L_0x018e;
                case 47: goto L_0x017d;
                case 48: goto L_0x016c;
                case 49: goto L_0x0157;
                case 50: goto L_0x014c;
                case 51: goto L_0x013e;
                case 52: goto L_0x0130;
                case 53: goto L_0x0122;
                case 54: goto L_0x0114;
                case 55: goto L_0x0106;
                case 56: goto L_0x00f8;
                case 57: goto L_0x00ea;
                case 58: goto L_0x00dc;
                case 59: goto L_0x00d4;
                case 60: goto L_0x00cc;
                case 61: goto L_0x00c4;
                case 62: goto L_0x00b6;
                case 63: goto L_0x00a8;
                case 64: goto L_0x009a;
                case 65: goto L_0x008c;
                case 66: goto L_0x007e;
                case 67: goto L_0x0070;
                case 68: goto L_0x0068;
                default: goto L_0x0066;
            }
        L_0x0066:
            goto L_0x04b3
        L_0x0068:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            goto L_0x0385
        L_0x0070:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = e(r14, r10)
            goto L_0x03a0
        L_0x007e:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = d(r14, r10)
            goto L_0x03b1
        L_0x008c:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = e(r14, r10)
            goto L_0x03c2
        L_0x009a:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = d(r14, r10)
            goto L_0x03d3
        L_0x00a8:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = d(r14, r10)
            goto L_0x03e4
        L_0x00b6:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = d(r14, r10)
            goto L_0x03f5
        L_0x00c4:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            goto L_0x0400
        L_0x00cc:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            goto L_0x0413
        L_0x00d4:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            goto L_0x0428
        L_0x00dc:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = f(r14, r10)
            goto L_0x043f
        L_0x00ea:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = d(r14, r10)
            goto L_0x0450
        L_0x00f8:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = e(r14, r10)
            goto L_0x0460
        L_0x0106:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = d(r14, r10)
            goto L_0x0470
        L_0x0114:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = e(r14, r10)
            goto L_0x0480
        L_0x0122:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = e(r14, r10)
            goto L_0x0490
        L_0x0130:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = c(r14, r10)
            goto L_0x04a0
        L_0x013e:
            boolean r10 = r13.a((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = b((T) r14, r10)
            goto L_0x04b0
        L_0x014c:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            r13.a(r15, r9, r8, r7)
            goto L_0x04b3
        L_0x0157:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zd r10 = r13.a(r7)
            com.google.android.gms.internal.ads.zf.b(r9, r8, r15, r10)
            goto L_0x04b3
        L_0x016c:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.e(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x017d:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.j(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x018e:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.g(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x019f:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.l(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01b0:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.m(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01c1:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.i(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01d2:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.n(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01e3:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.k(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01f4:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.f(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0205:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.h(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0216:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.d(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0227:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.c(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0238:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.b(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0249:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.a(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x025a:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.e(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x026b:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.j(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x027c:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.g(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x028d:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.l(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x029e:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.m(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x02af:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.i(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x02c0:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.b(r9, r8, r15)
            goto L_0x04b3
        L_0x02d1:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zd r10 = r13.a(r7)
            com.google.android.gms.internal.ads.zf.a(r9, r8, r15, r10)
            goto L_0x04b3
        L_0x02e6:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.a(r9, r8, r15)
            goto L_0x04b3
        L_0x02f7:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.n(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x0308:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.k(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x0319:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.f(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x032a:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.h(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x033b:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.d(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x034c:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.c(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x035d:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.b(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x036e:
            int[] r9 = r13.b
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.ads.zf.a(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x037f:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0385:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            com.google.android.gms.internal.ads.zd r10 = r13.a(r7)
            r15.b(r9, r8, r10)
            goto L_0x04b3
        L_0x0394:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.ads.aab.b(r14, r10)
        L_0x03a0:
            r15.e(r9, r10)
            goto L_0x04b3
        L_0x03a5:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.ads.aab.a(r14, r10)
        L_0x03b1:
            r15.f(r9, r8)
            goto L_0x04b3
        L_0x03b6:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.ads.aab.b(r14, r10)
        L_0x03c2:
            r15.b(r9, r10)
            goto L_0x04b3
        L_0x03c7:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.ads.aab.a(r14, r10)
        L_0x03d3:
            r15.a(r9, r8)
            goto L_0x04b3
        L_0x03d8:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.ads.aab.a(r14, r10)
        L_0x03e4:
            r15.b(r9, r8)
            goto L_0x04b3
        L_0x03e9:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.ads.aab.a(r14, r10)
        L_0x03f5:
            r15.e(r9, r8)
            goto L_0x04b3
        L_0x03fa:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0400:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            com.google.android.gms.internal.ads.zzbah r8 = (com.google.android.gms.internal.ads.zzbah) r8
            r15.a(r9, r8)
            goto L_0x04b3
        L_0x040d:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0413:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            com.google.android.gms.internal.ads.zd r10 = r13.a(r7)
            r15.a(r9, r8, r10)
            goto L_0x04b3
        L_0x0422:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0428:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r14, r10)
            a(r9, r8, r15)
            goto L_0x04b3
        L_0x0433:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.ads.aab.c(r14, r10)
        L_0x043f:
            r15.a(r9, r8)
            goto L_0x04b3
        L_0x0444:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.ads.aab.a(r14, r10)
        L_0x0450:
            r15.d(r9, r8)
            goto L_0x04b3
        L_0x0454:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.ads.aab.b(r14, r10)
        L_0x0460:
            r15.d(r9, r10)
            goto L_0x04b3
        L_0x0464:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.ads.aab.a(r14, r10)
        L_0x0470:
            r15.c(r9, r8)
            goto L_0x04b3
        L_0x0474:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.ads.aab.b(r14, r10)
        L_0x0480:
            r15.c(r9, r10)
            goto L_0x04b3
        L_0x0484:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.ads.aab.b(r14, r10)
        L_0x0490:
            r15.a(r9, r10)
            goto L_0x04b3
        L_0x0494:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.ads.aab.d(r14, r10)
        L_0x04a0:
            r15.a(r9, r8)
            goto L_0x04b3
        L_0x04a4:
            boolean r10 = r13.a((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.ads.aab.e(r14, r10)
        L_0x04b0:
            r15.a(r9, r10)
        L_0x04b3:
            int r7 = r7 + -4
            goto L_0x0037
        L_0x04b7:
            if (r1 == 0) goto L_0x04ce
            com.google.android.gms.internal.ads.wy<?> r14 = r13.r
            r14.a(r15, r1)
            boolean r14 = r0.hasNext()
            if (r14 == 0) goto L_0x04cc
            java.lang.Object r14 = r0.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            r1 = r14
            goto L_0x04b7
        L_0x04cc:
            r1 = r3
            goto L_0x04b7
        L_0x04ce:
            return
        L_0x04cf:
            boolean r0 = r13.j
            if (r0 == 0) goto L_0x0992
            boolean r0 = r13.h
            if (r0 == 0) goto L_0x04ee
            com.google.android.gms.internal.ads.wy<?> r0 = r13.r
            com.google.android.gms.internal.ads.xb r0 = r0.a(r14)
            boolean r1 = r0.b()
            if (r1 != 0) goto L_0x04ee
            java.util.Iterator r0 = r0.e()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x04f0
        L_0x04ee:
            r0 = r3
            r1 = r0
        L_0x04f0:
            int[] r7 = r13.b
            int r7 = r7.length
            r8 = r1
            r1 = r5
        L_0x04f5:
            if (r1 >= r7) goto L_0x0975
            int r9 = r13.d(r1)
            int[] r10 = r13.b
            r10 = r10[r1]
        L_0x04ff:
            if (r8 == 0) goto L_0x051d
            com.google.android.gms.internal.ads.wy<?> r11 = r13.r
            int r11 = r11.a(r8)
            if (r11 > r10) goto L_0x051d
            com.google.android.gms.internal.ads.wy<?> r11 = r13.r
            r11.a(r15, r8)
            boolean r8 = r0.hasNext()
            if (r8 == 0) goto L_0x051b
            java.lang.Object r8 = r0.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            goto L_0x04ff
        L_0x051b:
            r8 = r3
            goto L_0x04ff
        L_0x051d:
            r11 = r9 & r2
            int r11 = r11 >>> 20
            switch(r11) {
                case 0: goto L_0x0962;
                case 1: goto L_0x0952;
                case 2: goto L_0x0942;
                case 3: goto L_0x0932;
                case 4: goto L_0x0922;
                case 5: goto L_0x0912;
                case 6: goto L_0x0902;
                case 7: goto L_0x08f1;
                case 8: goto L_0x08e0;
                case 9: goto L_0x08cb;
                case 10: goto L_0x08b8;
                case 11: goto L_0x08a7;
                case 12: goto L_0x0896;
                case 13: goto L_0x0885;
                case 14: goto L_0x0874;
                case 15: goto L_0x0863;
                case 16: goto L_0x0852;
                case 17: goto L_0x083d;
                case 18: goto L_0x082c;
                case 19: goto L_0x081b;
                case 20: goto L_0x080a;
                case 21: goto L_0x07f9;
                case 22: goto L_0x07e8;
                case 23: goto L_0x07d7;
                case 24: goto L_0x07c6;
                case 25: goto L_0x07b5;
                case 26: goto L_0x07a4;
                case 27: goto L_0x078f;
                case 28: goto L_0x077e;
                case 29: goto L_0x076d;
                case 30: goto L_0x075c;
                case 31: goto L_0x074b;
                case 32: goto L_0x073a;
                case 33: goto L_0x0729;
                case 34: goto L_0x0718;
                case 35: goto L_0x0707;
                case 36: goto L_0x06f6;
                case 37: goto L_0x06e5;
                case 38: goto L_0x06d4;
                case 39: goto L_0x06c3;
                case 40: goto L_0x06b2;
                case 41: goto L_0x06a1;
                case 42: goto L_0x0690;
                case 43: goto L_0x067f;
                case 44: goto L_0x066e;
                case 45: goto L_0x065d;
                case 46: goto L_0x064c;
                case 47: goto L_0x063b;
                case 48: goto L_0x062a;
                case 49: goto L_0x0615;
                case 50: goto L_0x060a;
                case 51: goto L_0x05fc;
                case 52: goto L_0x05ee;
                case 53: goto L_0x05e0;
                case 54: goto L_0x05d2;
                case 55: goto L_0x05c4;
                case 56: goto L_0x05b6;
                case 57: goto L_0x05a8;
                case 58: goto L_0x059a;
                case 59: goto L_0x0592;
                case 60: goto L_0x058a;
                case 61: goto L_0x0582;
                case 62: goto L_0x0574;
                case 63: goto L_0x0566;
                case 64: goto L_0x0558;
                case 65: goto L_0x054a;
                case 66: goto L_0x053c;
                case 67: goto L_0x052e;
                case 68: goto L_0x0526;
                default: goto L_0x0524;
            }
        L_0x0524:
            goto L_0x0971
        L_0x0526:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            goto L_0x0843
        L_0x052e:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = e(r14, r11)
            goto L_0x085e
        L_0x053c:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = d(r14, r11)
            goto L_0x086f
        L_0x054a:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = e(r14, r11)
            goto L_0x0880
        L_0x0558:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = d(r14, r11)
            goto L_0x0891
        L_0x0566:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = d(r14, r11)
            goto L_0x08a2
        L_0x0574:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = d(r14, r11)
            goto L_0x08b3
        L_0x0582:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            goto L_0x08be
        L_0x058a:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            goto L_0x08d1
        L_0x0592:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            goto L_0x08e6
        L_0x059a:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = f(r14, r11)
            goto L_0x08fd
        L_0x05a8:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = d(r14, r11)
            goto L_0x090e
        L_0x05b6:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = e(r14, r11)
            goto L_0x091e
        L_0x05c4:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = d(r14, r11)
            goto L_0x092e
        L_0x05d2:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = e(r14, r11)
            goto L_0x093e
        L_0x05e0:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = e(r14, r11)
            goto L_0x094e
        L_0x05ee:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = c(r14, r11)
            goto L_0x095e
        L_0x05fc:
            boolean r11 = r13.a((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = b((T) r14, r11)
            goto L_0x096e
        L_0x060a:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            r13.a(r15, r10, r9, r1)
            goto L_0x0971
        L_0x0615:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zd r11 = r13.a(r1)
            com.google.android.gms.internal.ads.zf.b(r10, r9, r15, r11)
            goto L_0x0971
        L_0x062a:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.e(r10, r9, r15, r4)
            goto L_0x0971
        L_0x063b:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.j(r10, r9, r15, r4)
            goto L_0x0971
        L_0x064c:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.g(r10, r9, r15, r4)
            goto L_0x0971
        L_0x065d:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.l(r10, r9, r15, r4)
            goto L_0x0971
        L_0x066e:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.m(r10, r9, r15, r4)
            goto L_0x0971
        L_0x067f:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.i(r10, r9, r15, r4)
            goto L_0x0971
        L_0x0690:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.n(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06a1:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.k(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06b2:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.f(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06c3:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.h(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06d4:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.d(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06e5:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.c(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06f6:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.b(r10, r9, r15, r4)
            goto L_0x0971
        L_0x0707:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.a(r10, r9, r15, r4)
            goto L_0x0971
        L_0x0718:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.e(r10, r9, r15, r5)
            goto L_0x0971
        L_0x0729:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.j(r10, r9, r15, r5)
            goto L_0x0971
        L_0x073a:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.g(r10, r9, r15, r5)
            goto L_0x0971
        L_0x074b:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.l(r10, r9, r15, r5)
            goto L_0x0971
        L_0x075c:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.m(r10, r9, r15, r5)
            goto L_0x0971
        L_0x076d:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.i(r10, r9, r15, r5)
            goto L_0x0971
        L_0x077e:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.b(r10, r9, r15)
            goto L_0x0971
        L_0x078f:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zd r11 = r13.a(r1)
            com.google.android.gms.internal.ads.zf.a(r10, r9, r15, r11)
            goto L_0x0971
        L_0x07a4:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.a(r10, r9, r15)
            goto L_0x0971
        L_0x07b5:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.n(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07c6:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.k(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07d7:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.f(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07e8:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.h(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07f9:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.d(r10, r9, r15, r5)
            goto L_0x0971
        L_0x080a:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.c(r10, r9, r15, r5)
            goto L_0x0971
        L_0x081b:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.b(r10, r9, r15, r5)
            goto L_0x0971
        L_0x082c:
            int[] r10 = r13.b
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.ads.zf.a(r10, r9, r15, r5)
            goto L_0x0971
        L_0x083d:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
        L_0x0843:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            com.google.android.gms.internal.ads.zd r11 = r13.a(r1)
            r15.b(r10, r9, r11)
            goto L_0x0971
        L_0x0852:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.ads.aab.b(r14, r11)
        L_0x085e:
            r15.e(r10, r11)
            goto L_0x0971
        L_0x0863:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.ads.aab.a(r14, r11)
        L_0x086f:
            r15.f(r10, r9)
            goto L_0x0971
        L_0x0874:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.ads.aab.b(r14, r11)
        L_0x0880:
            r15.b(r10, r11)
            goto L_0x0971
        L_0x0885:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.ads.aab.a(r14, r11)
        L_0x0891:
            r15.a(r10, r9)
            goto L_0x0971
        L_0x0896:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.ads.aab.a(r14, r11)
        L_0x08a2:
            r15.b(r10, r9)
            goto L_0x0971
        L_0x08a7:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.ads.aab.a(r14, r11)
        L_0x08b3:
            r15.e(r10, r9)
            goto L_0x0971
        L_0x08b8:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
        L_0x08be:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            com.google.android.gms.internal.ads.zzbah r9 = (com.google.android.gms.internal.ads.zzbah) r9
            r15.a(r10, r9)
            goto L_0x0971
        L_0x08cb:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
        L_0x08d1:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            com.google.android.gms.internal.ads.zd r11 = r13.a(r1)
            r15.a(r10, r9, r11)
            goto L_0x0971
        L_0x08e0:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
        L_0x08e6:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.ads.aab.f(r14, r11)
            a(r10, r9, r15)
            goto L_0x0971
        L_0x08f1:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.ads.aab.c(r14, r11)
        L_0x08fd:
            r15.a(r10, r9)
            goto L_0x0971
        L_0x0902:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.ads.aab.a(r14, r11)
        L_0x090e:
            r15.d(r10, r9)
            goto L_0x0971
        L_0x0912:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.ads.aab.b(r14, r11)
        L_0x091e:
            r15.d(r10, r11)
            goto L_0x0971
        L_0x0922:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.ads.aab.a(r14, r11)
        L_0x092e:
            r15.c(r10, r9)
            goto L_0x0971
        L_0x0932:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.ads.aab.b(r14, r11)
        L_0x093e:
            r15.c(r10, r11)
            goto L_0x0971
        L_0x0942:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.ads.aab.b(r14, r11)
        L_0x094e:
            r15.a(r10, r11)
            goto L_0x0971
        L_0x0952:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.ads.aab.d(r14, r11)
        L_0x095e:
            r15.a(r10, r9)
            goto L_0x0971
        L_0x0962:
            boolean r11 = r13.a((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.ads.aab.e(r14, r11)
        L_0x096e:
            r15.a(r10, r11)
        L_0x0971:
            int r1 = r1 + 4
            goto L_0x04f5
        L_0x0975:
            if (r8 == 0) goto L_0x098c
            com.google.android.gms.internal.ads.wy<?> r1 = r13.r
            r1.a(r15, r8)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x098a
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r8 = r1
            goto L_0x0975
        L_0x098a:
            r8 = r3
            goto L_0x0975
        L_0x098c:
            com.google.android.gms.internal.ads.zv<?, ?> r0 = r13.q
            a(r0, (T) r14, r15)
            return
        L_0x0992:
            r13.b((T) r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yo.a(java.lang.Object, com.google.android.gms.internal.ads.aai):void");
    }

    /* JADX INFO: used method not loaded: com.google.android.gms.internal.ads.zf.a(int, int, java.lang.Object, com.google.android.gms.internal.ads.zv):null, types can be incorrect */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:174|175|(1:177)|178|(5:198|180|(3:182|(1:184)|232)|(1:186)|187)(1:228)) */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x02a8, code lost:
        r10.f(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x02b6, code lost:
        r10.e(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x02c4, code lost:
        r10.c(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x02d2, code lost:
        r10.d(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x02e0, code lost:
        r10.b(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x02ee, code lost:
        r10.a(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x032e, code lost:
        r4 = com.google.android.gms.internal.ads.zf.a(r4, r6, r5, r15, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0332, code lost:
        r15 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x03f3, code lost:
        com.google.android.gms.internal.ads.aab.a((java.lang.Object) r2, r6, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0406, code lost:
        b(r2, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:?, code lost:
        r12.a(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0518, code lost:
        if (r15 == null) goto L_0x051a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x051a, code lost:
        r15 = r12.c(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0523, code lost:
        if (r12.a(r15, r10) == false) goto L_0x0525;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0527, code lost:
        if (r1.m != null) goto L_0x0529;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0529, code lost:
        r3 = r1.m;
        r4 = r3.length;
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x052e, code lost:
        if (r5 < r4) goto L_0x0530;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0530, code lost:
        r15 = a((java.lang.Object) r2, r3[r5], (UB) r15, r12);
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0539, code lost:
        if (r15 != null) goto L_0x053b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x053b, code lost:
        r12.b((java.lang.Object) r2, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x053e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b6, code lost:
        b(r2, r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0104, code lost:
        r4 = com.google.android.gms.internal.ads.zf.a(r4, r7, r15, (com.google.android.gms.internal.ads.zv) r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0235, code lost:
        r10.q(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0243, code lost:
        r10.p(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0251, code lost:
        r10.o(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x025f, code lost:
        r10.n(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x027e, code lost:
        r10.l(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x028c, code lost:
        r10.h(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x029a, code lost:
        r10.g(r4);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:174:0x0515 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(T r19, com.google.android.gms.internal.ads.zc r20, com.google.android.gms.internal.ads.ww r21) throws java.io.IOException {
        /*
            r18 = this;
            r1 = r18
            r2 = r19
            r10 = r20
            r11 = r21
            if (r11 != 0) goto L_0x0010
            java.lang.NullPointerException r2 = new java.lang.NullPointerException
            r2.<init>()
            throw r2
        L_0x0010:
            com.google.android.gms.internal.ads.zv<?, ?> r12 = r1.q
            com.google.android.gms.internal.ads.wy<?> r13 = r1.r
            r14 = 0
            r3 = r14
            r15 = r3
        L_0x0017:
            r16 = 0
            int r4 = r20.a()     // Catch:{ all -> 0x053f }
            int r5 = r1.g(r4)     // Catch:{ all -> 0x053f }
            if (r5 >= 0) goto L_0x0092
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 != r5) goto L_0x0042
            int[] r3 = r1.m
            if (r3 == 0) goto L_0x003c
            int[] r3 = r1.m
            int r4 = r3.length
            r5 = r16
        L_0x0031:
            if (r5 >= r4) goto L_0x003c
            r6 = r3[r5]
            java.lang.Object r15 = r1.a(r2, r6, (UB) r15, r12)
            int r5 = r5 + 1
            goto L_0x0031
        L_0x003c:
            if (r15 == 0) goto L_0x0041
            r12.b(r2, r15)
        L_0x0041:
            return
        L_0x0042:
            boolean r5 = r1.h     // Catch:{ all -> 0x053f }
            if (r5 != 0) goto L_0x0048
            r5 = r14
            goto L_0x004f
        L_0x0048:
            com.google.android.gms.internal.ads.yk r5 = r1.g     // Catch:{ all -> 0x053f }
            java.lang.Object r4 = r13.a(r11, r5, r4)     // Catch:{ all -> 0x053f }
            r5 = r4
        L_0x004f:
            if (r5 == 0) goto L_0x0068
            if (r3 != 0) goto L_0x0057
            com.google.android.gms.internal.ads.xb r3 = r13.b(r2)     // Catch:{ all -> 0x053f }
        L_0x0057:
            r17 = r3
            r3 = r13
            r4 = r10
            r6 = r11
            r7 = r17
            r8 = r15
            r9 = r12
            java.lang.Object r3 = r3.a(r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x053f }
            r15 = r3
            r3 = r17
            goto L_0x0017
        L_0x0068:
            r12.a(r10)     // Catch:{ all -> 0x053f }
            if (r15 != 0) goto L_0x0072
            java.lang.Object r4 = r12.c(r2)     // Catch:{ all -> 0x053f }
            r15 = r4
        L_0x0072:
            boolean r4 = r12.a(r15, r10)     // Catch:{ all -> 0x053f }
            if (r4 != 0) goto L_0x0017
            int[] r3 = r1.m
            if (r3 == 0) goto L_0x008c
            int[] r3 = r1.m
            int r4 = r3.length
            r5 = r16
        L_0x0081:
            if (r5 >= r4) goto L_0x008c
            r6 = r3[r5]
            java.lang.Object r15 = r1.a(r2, r6, (UB) r15, r12)
            int r5 = r5 + 1
            goto L_0x0081
        L_0x008c:
            if (r15 == 0) goto L_0x0091
            r12.b(r2, r15)
        L_0x0091:
            return
        L_0x0092:
            int r6 = r1.d(r5)     // Catch:{ all -> 0x053f }
            r7 = 267386880(0xff00000, float:2.3665827E-29)
            r7 = r7 & r6
            int r7 = r7 >>> 20
            r8 = 1048575(0xfffff, float:1.469367E-39)
            switch(r7) {
                case 0: goto L_0x04e8;
                case 1: goto L_0x04dc;
                case 2: goto L_0x04d0;
                case 3: goto L_0x04c4;
                case 4: goto L_0x04b8;
                case 5: goto L_0x04ac;
                case 6: goto L_0x04a0;
                case 7: goto L_0x0494;
                case 8: goto L_0x048f;
                case 9: goto L_0x0464;
                case 10: goto L_0x0459;
                case 11: goto L_0x044e;
                case 12: goto L_0x0437;
                case 13: goto L_0x042c;
                case 14: goto L_0x0421;
                case 15: goto L_0x0416;
                case 16: goto L_0x040b;
                case 17: goto L_0x03da;
                case 18: goto L_0x03cf;
                case 19: goto L_0x03c4;
                case 20: goto L_0x03b9;
                case 21: goto L_0x03ae;
                case 22: goto L_0x03a3;
                case 23: goto L_0x0398;
                case 24: goto L_0x038d;
                case 25: goto L_0x0382;
                case 26: goto L_0x0360;
                case 27: goto L_0x034e;
                case 28: goto L_0x0340;
                case 29: goto L_0x0335;
                case 30: goto L_0x031f;
                case 31: goto L_0x0314;
                case 32: goto L_0x0309;
                case 33: goto L_0x02fe;
                case 34: goto L_0x02f3;
                case 35: goto L_0x02e5;
                case 36: goto L_0x02d7;
                case 37: goto L_0x02c9;
                case 38: goto L_0x02bb;
                case 39: goto L_0x02ad;
                case 40: goto L_0x029f;
                case 41: goto L_0x0291;
                case 42: goto L_0x0283;
                case 43: goto L_0x0275;
                case 44: goto L_0x0264;
                case 45: goto L_0x0256;
                case 46: goto L_0x0248;
                case 47: goto L_0x023a;
                case 48: goto L_0x022c;
                case 49: goto L_0x021a;
                case 50: goto L_0x01d8;
                case 51: goto L_0x01c9;
                case 52: goto L_0x01ba;
                case 53: goto L_0x01ab;
                case 54: goto L_0x019c;
                case 55: goto L_0x018d;
                case 56: goto L_0x017e;
                case 57: goto L_0x016f;
                case 58: goto L_0x0160;
                case 59: goto L_0x015b;
                case 60: goto L_0x012c;
                case 61: goto L_0x0122;
                case 62: goto L_0x0114;
                case 63: goto L_0x00f3;
                case 64: goto L_0x00e5;
                case 65: goto L_0x00d7;
                case 66: goto L_0x00c9;
                case 67: goto L_0x00bb;
                case 68: goto L_0x00a9;
                default: goto L_0x00a1;
            }
        L_0x00a1:
            if (r15 != 0) goto L_0x04f5
            java.lang.Object r4 = r12.a()     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x04f4
        L_0x00a9:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.zd r8 = r1.a(r5)     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r8 = r10.b(r8, r11)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
        L_0x00b6:
            r1.b((T) r2, r4, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x00bb:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            long r8 = r20.t()     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x00c9:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            int r8 = r20.s()     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x00d7:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            long r8 = r20.r()     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x00e5:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            int r8 = r20.q()     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x00f3:
            int r7 = r20.p()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.xl r9 = r1.c(r5)     // Catch:{ zzbbv -> 0x0515 }
            if (r9 == 0) goto L_0x010a
            com.google.android.gms.internal.ads.xk r9 = r9.a(r7)     // Catch:{ zzbbv -> 0x0515 }
            if (r9 == 0) goto L_0x0104
            goto L_0x010a
        L_0x0104:
            java.lang.Object r4 = com.google.android.gms.internal.ads.zf.a(r4, r7, r15, r12)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0332
        L_0x010a:
            r6 = r6 & r8
            long r8 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r8, r6)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x0114:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            int r8 = r20.o()     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x0122:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.zzbah r8 = r20.n()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x012c:
            boolean r7 = r1.a((T) r2, r4, r5)     // Catch:{ zzbbv -> 0x0515 }
            if (r7 == 0) goto L_0x0149
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r8 = com.google.android.gms.internal.ads.aab.f(r2, r6)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.zd r9 = r1.a(r5)     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r9 = r10.a(r9, r11)     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r8 = com.google.android.gms.internal.ads.xj.a(r8, r9)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x0149:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.zd r8 = r1.a(r5)     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r8 = r10.a(r8, r11)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            r1.b((T) r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x015b:
            r1.a(r2, r6, r10)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x0160:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            boolean r8 = r20.k()     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x016f:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            int r8 = r20.j()     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x017e:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            long r8 = r20.i()     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x018d:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            int r8 = r20.h()     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x019c:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            long r8 = r20.f()     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x01ab:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            long r8 = r20.g()     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x01ba:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            float r8 = r20.e()     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Float r8 = java.lang.Float.valueOf(r8)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x01c9:
            r6 = r6 & r8
            long r6 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            double r8 = r20.d()     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Double r8 = java.lang.Double.valueOf(r8)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x00b6
        L_0x01d8:
            java.lang.Object r4 = r1.b(r5)     // Catch:{ zzbbv -> 0x0515 }
            int r5 = r1.d(r5)     // Catch:{ zzbbv -> 0x0515 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r7 = com.google.android.gms.internal.ads.aab.f(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            if (r7 != 0) goto L_0x01f2
            com.google.android.gms.internal.ads.yf r7 = r1.s     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r7 = r7.e(r4)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r5, r7)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0209
        L_0x01f2:
            com.google.android.gms.internal.ads.yf r8 = r1.s     // Catch:{ zzbbv -> 0x0515 }
            boolean r8 = r8.c(r7)     // Catch:{ zzbbv -> 0x0515 }
            if (r8 == 0) goto L_0x0209
            com.google.android.gms.internal.ads.yf r8 = r1.s     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r8 = r8.e(r4)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.yf r9 = r1.s     // Catch:{ zzbbv -> 0x0515 }
            r9.a(r8, r7)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r5, r8)     // Catch:{ zzbbv -> 0x0515 }
            r7 = r8
        L_0x0209:
            com.google.android.gms.internal.ads.yf r5 = r1.s     // Catch:{ zzbbv -> 0x0515 }
            java.util.Map r5 = r5.a(r7)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.yf r6 = r1.s     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.ye r4 = r6.f(r4)     // Catch:{ zzbbv -> 0x0515 }
            r10.a(r5, r4, r11)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x021a:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.zd r4 = r1.a(r5)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.xv r5 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r5 = r5.a(r2, r6)     // Catch:{ zzbbv -> 0x0515 }
            r10.b(r5, r4, r11)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x022c:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
        L_0x0235:
            r10.q(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x023a:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
        L_0x0243:
            r10.p(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x0248:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
        L_0x0251:
            r10.o(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x0256:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
        L_0x025f:
            r10.n(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x0264:
            com.google.android.gms.internal.ads.xv r7 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r6 = r6 & r8
            long r8 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r6 = r7.a(r2, r8)     // Catch:{ zzbbv -> 0x0515 }
            r10.m(r6)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.xl r5 = r1.c(r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x032e
        L_0x0275:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
        L_0x027e:
            r10.l(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x0283:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
        L_0x028c:
            r10.h(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x0291:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
        L_0x029a:
            r10.g(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x029f:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
        L_0x02a8:
            r10.f(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x02ad:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
        L_0x02b6:
            r10.e(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x02bb:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
        L_0x02c4:
            r10.c(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x02c9:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
        L_0x02d2:
            r10.d(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x02d7:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
        L_0x02e0:
            r10.b(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x02e5:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
        L_0x02ee:
            r10.a(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x02f3:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0235
        L_0x02fe:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0243
        L_0x0309:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0251
        L_0x0314:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x025f
        L_0x031f:
            com.google.android.gms.internal.ads.xv r7 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r6 = r6 & r8
            long r8 = (long) r6     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r6 = r7.a(r2, r8)     // Catch:{ zzbbv -> 0x0515 }
            r10.m(r6)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.xl r5 = r1.c(r5)     // Catch:{ zzbbv -> 0x0515 }
        L_0x032e:
            java.lang.Object r4 = com.google.android.gms.internal.ads.zf.a(r4, r6, r5, r15, r12)     // Catch:{ zzbbv -> 0x0515 }
        L_0x0332:
            r15 = r4
            goto L_0x0017
        L_0x0335:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x027e
        L_0x0340:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            r10.k(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x034e:
            com.google.android.gms.internal.ads.zd r4 = r1.a(r5)     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.xv r7 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r5 = r7.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            r10.a(r5, r4, r11)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x0360:
            boolean r4 = f(r6)     // Catch:{ zzbbv -> 0x0515 }
            if (r4 == 0) goto L_0x0374
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            r10.j(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x0374:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            r10.i(r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x0382:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x028c
        L_0x038d:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x029a
        L_0x0398:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x02a8
        L_0x03a3:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x02b6
        L_0x03ae:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x02c4
        L_0x03b9:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x02d2
        L_0x03c4:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x02e0
        L_0x03cf:
            com.google.android.gms.internal.ads.xv r4 = r1.p     // Catch:{ zzbbv -> 0x0515 }
            r5 = r6 & r8
            long r5 = (long) r5     // Catch:{ zzbbv -> 0x0515 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x02ee
        L_0x03da:
            boolean r4 = r1.a((T) r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            if (r4 == 0) goto L_0x03f8
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r4 = com.google.android.gms.internal.ads.aab.f(r2, r6)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.zd r5 = r1.a(r5)     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r5 = r10.b(r5, r11)     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r4 = com.google.android.gms.internal.ads.xj.a(r4, r5)     // Catch:{ zzbbv -> 0x0515 }
        L_0x03f3:
            com.google.android.gms.internal.ads.aab.a(r2, r6, r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x03f8:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.zd r4 = r1.a(r5)     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r4 = r10.b(r4, r11)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r4)     // Catch:{ zzbbv -> 0x0515 }
        L_0x0406:
            r1.b((T) r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0017
        L_0x040b:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            long r8 = r20.t()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x0416:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            int r4 = r20.s()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x0421:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            long r8 = r20.r()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x042c:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            int r4 = r20.q()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x0437:
            int r7 = r20.p()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.xl r9 = r1.c(r5)     // Catch:{ zzbbv -> 0x0515 }
            if (r9 == 0) goto L_0x0447
            com.google.android.gms.internal.ads.xk r9 = r9.a(r7)     // Catch:{ zzbbv -> 0x0515 }
            if (r9 == 0) goto L_0x0104
        L_0x0447:
            r4 = r6 & r8
            long r8 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r8, r7)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x044e:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            int r4 = r20.o()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x0459:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.zzbah r4 = r20.n()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x0464:
            boolean r4 = r1.a((T) r2, r5)     // Catch:{ zzbbv -> 0x0515 }
            if (r4 == 0) goto L_0x047f
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r4 = com.google.android.gms.internal.ads.aab.f(r2, r6)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.zd r5 = r1.a(r5)     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r5 = r10.a(r5, r11)     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r4 = com.google.android.gms.internal.ads.xj.a(r4, r5)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x03f3
        L_0x047f:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.zd r4 = r1.a(r5)     // Catch:{ zzbbv -> 0x0515 }
            java.lang.Object r4 = r10.a(r4, r11)     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x048f:
            r1.a(r2, r6, r10)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x0494:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            boolean r4 = r20.k()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x04a0:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            int r4 = r20.j()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x04ac:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            long r8 = r20.i()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x04b8:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            int r4 = r20.h()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x04c4:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            long r8 = r20.f()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x04d0:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            long r8 = r20.g()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x04dc:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            float r4 = r20.e()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r4)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x04e8:
            r4 = r6 & r8
            long r6 = (long) r4     // Catch:{ zzbbv -> 0x0515 }
            double r8 = r20.d()     // Catch:{ zzbbv -> 0x0515 }
            com.google.android.gms.internal.ads.aab.a(r2, r6, r8)     // Catch:{ zzbbv -> 0x0515 }
            goto L_0x0406
        L_0x04f4:
            r15 = r4
        L_0x04f5:
            boolean r4 = r12.a(r15, r10)     // Catch:{ zzbbv -> 0x0515 }
            if (r4 != 0) goto L_0x0017
            int[] r3 = r1.m
            if (r3 == 0) goto L_0x050f
            int[] r3 = r1.m
            int r4 = r3.length
            r5 = r16
        L_0x0504:
            if (r5 >= r4) goto L_0x050f
            r6 = r3[r5]
            java.lang.Object r15 = r1.a(r2, r6, (UB) r15, r12)
            int r5 = r5 + 1
            goto L_0x0504
        L_0x050f:
            if (r15 == 0) goto L_0x0514
            r12.b(r2, r15)
        L_0x0514:
            return
        L_0x0515:
            r12.a(r10)     // Catch:{ all -> 0x053f }
            if (r15 != 0) goto L_0x051f
            java.lang.Object r4 = r12.c(r2)     // Catch:{ all -> 0x053f }
            r15 = r4
        L_0x051f:
            boolean r4 = r12.a(r15, r10)     // Catch:{ all -> 0x053f }
            if (r4 != 0) goto L_0x0017
            int[] r3 = r1.m
            if (r3 == 0) goto L_0x0539
            int[] r3 = r1.m
            int r4 = r3.length
            r5 = r16
        L_0x052e:
            if (r5 >= r4) goto L_0x0539
            r6 = r3[r5]
            java.lang.Object r15 = r1.a(r2, r6, (UB) r15, r12)
            int r5 = r5 + 1
            goto L_0x052e
        L_0x0539:
            if (r15 == 0) goto L_0x053e
            r12.b(r2, r15)
        L_0x053e:
            return
        L_0x053f:
            r0 = move-exception
            r3 = r0
            int[] r4 = r1.m
            if (r4 == 0) goto L_0x0555
            int[] r4 = r1.m
            int r5 = r4.length
            r6 = r16
        L_0x054a:
            if (r6 >= r5) goto L_0x0555
            r7 = r4[r6]
            java.lang.Object r15 = r1.a(r2, r7, (UB) r15, r12)
            int r6 = r6 + 1
            goto L_0x054a
        L_0x0555:
            if (r15 == 0) goto L_0x055a
            r12.b(r2, r15)
        L_0x055a:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yo.a(java.lang.Object, com.google.android.gms.internal.ads.zc, com.google.android.gms.internal.ads.ww):void");
    }

    /* JADX WARNING: type inference failed for: r26v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v0 */
    /* JADX WARNING: type inference failed for: r2v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v1, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v5, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r16v0, types: [int] */
    /* JADX WARNING: type inference failed for: r12v2 */
    /* JADX WARNING: type inference failed for: r12v3 */
    /* JADX WARNING: type inference failed for: r0v8, types: [int] */
    /* JADX WARNING: type inference failed for: r1v2, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r2v4, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v3, types: [int] */
    /* JADX WARNING: type inference failed for: r2v5, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r2v7, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v6, types: [int] */
    /* JADX WARNING: type inference failed for: r12v6 */
    /* JADX WARNING: type inference failed for: r1v9, types: [int] */
    /* JADX WARNING: type inference failed for: r2v8, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r16v1 */
    /* JADX WARNING: type inference failed for: r1v25, types: [int] */
    /* JADX WARNING: type inference failed for: r16v2 */
    /* JADX WARNING: type inference failed for: r12v7 */
    /* JADX WARNING: type inference failed for: r12v8 */
    /* JADX WARNING: type inference failed for: r12v9 */
    /* JADX WARNING: type inference failed for: r12v10 */
    /* JADX WARNING: type inference failed for: r12v11 */
    /* JADX WARNING: type inference failed for: r12v12 */
    /* JADX WARNING: type inference failed for: r12v13 */
    /* JADX WARNING: type inference failed for: r12v14 */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0069, code lost:
        if (r7 == 0) goto L_0x00cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0073, code lost:
        r1 = r11.c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0075, code lost:
        r9.putObject(r14, r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00cd, code lost:
        if (r7 == 0) goto L_0x00cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00cf, code lost:
        r0 = com.google.android.gms.internal.ads.wc.a(r12, r10, r11);
        r1 = r11.a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d5, code lost:
        r9.putInt(r14, r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e2, code lost:
        r9.putLong(r14, r2, r4);
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f3, code lost:
        r0 = r10 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0100, code lost:
        r0 = r10 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0190, code lost:
        if (r0 == r14) goto L_0x015d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01ac, code lost:
        if (r0 == r14) goto L_0x015d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0012, code lost:
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0012, code lost:
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0012, code lost:
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0012, code lost:
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0012, code lost:
        r12 = r12;
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r0v5, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r26v0, types: [byte[]] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r12v2
      assigns: []
      uses: []
      mth insns count: 208
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$38/2083670723.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 16 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(T r25, byte[] r26, int r27, int r28, com.google.android.gms.internal.ads.wd r29) throws java.io.IOException {
        /*
            r24 = this;
            r15 = r24
            r14 = r25
            r12 = r26
            r13 = r28
            r11 = r29
            boolean r0 = r15.j
            if (r0 == 0) goto L_0x01d8
            sun.misc.Unsafe r9 = a
            r0 = r27
        L_0x0012:
            if (r0 >= r13) goto L_0x01cf
            int r1 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0024
            int r0 = com.google.android.gms.internal.ads.wc.a(r0, r12, r1, r11)
            int r1 = r11.a
            r10 = r0
            r16 = r1
            goto L_0x0027
        L_0x0024:
            r16 = r0
            r10 = r1
        L_0x0027:
            int r6 = r16 >>> 3
            r7 = r16 & 7
            int r8 = r15.g(r6)
            if (r8 < 0) goto L_0x01af
            int[] r0 = r15.b
            int r1 = r8 + 1
            r5 = r0[r1]
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r5
            int r4 = r0 >>> 20
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r2 = (long) r0
            r0 = 17
            r1 = 2
            if (r4 > r0) goto L_0x0104
            r0 = 5
            r6 = 1
            switch(r4) {
                case 0: goto L_0x00f7;
                case 1: goto L_0x00ea;
                case 2: goto L_0x00da;
                case 3: goto L_0x00da;
                case 4: goto L_0x00cd;
                case 5: goto L_0x00c1;
                case 6: goto L_0x00b7;
                case 7: goto L_0x00a2;
                case 8: goto L_0x0091;
                case 9: goto L_0x0079;
                case 10: goto L_0x006d;
                case 11: goto L_0x00cd;
                case 12: goto L_0x0069;
                case 13: goto L_0x00b7;
                case 14: goto L_0x00c1;
                case 15: goto L_0x005b;
                case 16: goto L_0x004d;
                default: goto L_0x004b;
            }
        L_0x004b:
            goto L_0x01af
        L_0x004d:
            if (r7 != 0) goto L_0x01af
            int r6 = com.google.android.gms.internal.ads.wc.b(r12, r10, r11)
            long r0 = r11.b
            long r4 = com.google.android.gms.internal.ads.wl.a(r0)
            goto L_0x00e2
        L_0x005b:
            if (r7 != 0) goto L_0x01af
            int r0 = com.google.android.gms.internal.ads.wc.a(r12, r10, r11)
            int r1 = r11.a
            int r1 = com.google.android.gms.internal.ads.wl.f(r1)
            goto L_0x00d5
        L_0x0069:
            if (r7 != 0) goto L_0x01af
            goto L_0x00cf
        L_0x006d:
            if (r7 != r1) goto L_0x01af
            int r0 = com.google.android.gms.internal.ads.wc.e(r12, r10, r11)
        L_0x0073:
            java.lang.Object r1 = r11.c
        L_0x0075:
            r9.putObject(r14, r2, r1)
            goto L_0x0012
        L_0x0079:
            if (r7 != r1) goto L_0x01af
            com.google.android.gms.internal.ads.zd r0 = r15.a(r8)
            int r0 = a(r0, r12, r10, r13, r11)
            java.lang.Object r1 = r9.getObject(r14, r2)
            if (r1 != 0) goto L_0x008a
            goto L_0x0073
        L_0x008a:
            java.lang.Object r4 = r11.c
            java.lang.Object r1 = com.google.android.gms.internal.ads.xj.a(r1, r4)
            goto L_0x0075
        L_0x0091:
            if (r7 != r1) goto L_0x01af
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r5
            if (r0 != 0) goto L_0x009d
            int r0 = com.google.android.gms.internal.ads.wc.c(r12, r10, r11)
            goto L_0x0073
        L_0x009d:
            int r0 = com.google.android.gms.internal.ads.wc.d(r12, r10, r11)
            goto L_0x0073
        L_0x00a2:
            if (r7 != 0) goto L_0x01af
            int r0 = com.google.android.gms.internal.ads.wc.b(r12, r10, r11)
            long r4 = r11.b
            r7 = 0
            int r1 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r1 == 0) goto L_0x00b1
            goto L_0x00b2
        L_0x00b1:
            r6 = 0
        L_0x00b2:
            com.google.android.gms.internal.ads.aab.a(r14, r2, r6)
            goto L_0x0012
        L_0x00b7:
            if (r7 != r0) goto L_0x01af
            int r0 = com.google.android.gms.internal.ads.wc.a(r12, r10)
            r9.putInt(r14, r2, r0)
            goto L_0x00f3
        L_0x00c1:
            if (r7 != r6) goto L_0x01af
            long r4 = com.google.android.gms.internal.ads.wc.b(r12, r10)
            r0 = r9
            r1 = r14
            r0.putLong(r1, r2, r4)
            goto L_0x0100
        L_0x00cd:
            if (r7 != 0) goto L_0x01af
        L_0x00cf:
            int r0 = com.google.android.gms.internal.ads.wc.a(r12, r10, r11)
            int r1 = r11.a
        L_0x00d5:
            r9.putInt(r14, r2, r1)
            goto L_0x0012
        L_0x00da:
            if (r7 != 0) goto L_0x01af
            int r6 = com.google.android.gms.internal.ads.wc.b(r12, r10, r11)
            long r4 = r11.b
        L_0x00e2:
            r0 = r9
            r1 = r14
            r0.putLong(r1, r2, r4)
            r0 = r6
            goto L_0x0012
        L_0x00ea:
            if (r7 != r0) goto L_0x01af
            float r0 = com.google.android.gms.internal.ads.wc.d(r12, r10)
            com.google.android.gms.internal.ads.aab.a(r14, r2, r0)
        L_0x00f3:
            int r0 = r10 + 4
            goto L_0x0012
        L_0x00f7:
            if (r7 != r6) goto L_0x01af
            double r0 = com.google.android.gms.internal.ads.wc.c(r12, r10)
            com.google.android.gms.internal.ads.aab.a(r14, r2, r0)
        L_0x0100:
            int r0 = r10 + 8
            goto L_0x0012
        L_0x0104:
            r0 = 27
            if (r4 != r0) goto L_0x0139
            if (r7 != r1) goto L_0x01af
            java.lang.Object r0 = r9.getObject(r14, r2)
            com.google.android.gms.internal.ads.xm r0 = (com.google.android.gms.internal.ads.xm) r0
            boolean r1 = r0.a()
            if (r1 != 0) goto L_0x0128
            int r1 = r0.size()
            if (r1 != 0) goto L_0x011f
            r1 = 10
            goto L_0x0121
        L_0x011f:
            int r1 = r1 << 1
        L_0x0121:
            com.google.android.gms.internal.ads.xm r0 = r0.a(r1)
            r9.putObject(r14, r2, r0)
        L_0x0128:
            r5 = r0
            com.google.android.gms.internal.ads.zd r0 = r15.a(r8)
            r1 = r16
            r2 = r12
            r3 = r10
            r4 = r13
            r6 = r11
            int r0 = a(r0, r1, r2, r3, r4, r5, r6)
            goto L_0x0012
        L_0x0139:
            r0 = 49
            if (r4 > r0) goto L_0x016e
            long r0 = (long) r5
            r17 = r0
            r0 = r15
            r1 = r14
            r19 = r2
            r2 = r12
            r3 = r10
            r5 = r4
            r4 = r13
            r21 = r5
            r5 = r16
            r22 = r9
            r15 = r10
            r9 = r17
            r11 = r21
            r12 = r19
            r14 = r29
            int r0 = r0.a((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 != r15) goto L_0x0160
        L_0x015d:
            r2 = r0
            goto L_0x01b3
        L_0x0160:
            r14 = r25
            r12 = r26
            r13 = r28
            r11 = r29
            r9 = r22
            r15 = r24
            goto L_0x0012
        L_0x016e:
            r19 = r2
            r21 = r4
            r22 = r9
            r15 = r10
            r0 = 50
            r9 = r21
            if (r9 != r0) goto L_0x0195
            if (r7 != r1) goto L_0x0193
            r14 = r15
            r0 = r24
            r1 = r25
            r2 = r26
            r3 = r14
            r4 = r28
            r5 = r8
            r7 = r19
            r9 = r29
            int r0 = r0.a(r1, r2, r3, r4, r5, r6, r7, r9)
            if (r0 != r14) goto L_0x01c1
            goto L_0x015d
        L_0x0193:
            r14 = r15
            goto L_0x01b2
        L_0x0195:
            r14 = r15
            r0 = r24
            r1 = r25
            r2 = r26
            r3 = r14
            r4 = r28
            r10 = r5
            r5 = r16
            r12 = r8
            r8 = r10
            r10 = r19
            r13 = r29
            int r0 = r0.a((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 != r14) goto L_0x01c1
            goto L_0x015d
        L_0x01af:
            r22 = r9
            r14 = r10
        L_0x01b2:
            r2 = r14
        L_0x01b3:
            r0 = r16
            r1 = r26
            r3 = r28
            r4 = r25
            r5 = r29
            int r0 = a(r0, r1, r2, r3, r4, r5)
        L_0x01c1:
            r15 = r24
            r14 = r25
            r12 = r26
            r13 = r28
            r11 = r29
            r9 = r22
            goto L_0x0012
        L_0x01cf:
            r4 = r13
            if (r0 == r4) goto L_0x01d7
            com.google.android.gms.internal.ads.zzbbu r0 = com.google.android.gms.internal.ads.zzbbu.zzadr()
            throw r0
        L_0x01d7:
            return
        L_0x01d8:
            r4 = r13
            r5 = 0
            r0 = r24
            r1 = r25
            r2 = r26
            r3 = r27
            r6 = r29
            r0.a((T) r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yo.a(java.lang.Object, byte[], int, int, com.google.android.gms.internal.ads.wd):void");
    }

    /* JADX INFO: used method not loaded: com.google.android.gms.internal.ads.zf.a(java.lang.Object, java.lang.Object):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005c, code lost:
        if (com.google.android.gms.internal.ads.zf.a(com.google.android.gms.internal.ads.aab.f(r10, r6), com.google.android.gms.internal.ads.aab.f(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0070, code lost:
        if (com.google.android.gms.internal.ads.aab.b(r10, r6) == com.google.android.gms.internal.ads.aab.b(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0082, code lost:
        if (com.google.android.gms.internal.ads.aab.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.ads.aab.a((java.lang.Object) r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0096, code lost:
        if (com.google.android.gms.internal.ads.aab.b(r10, r6) == com.google.android.gms.internal.ads.aab.b(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a8, code lost:
        if (com.google.android.gms.internal.ads.aab.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.ads.aab.a((java.lang.Object) r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ba, code lost:
        if (com.google.android.gms.internal.ads.aab.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.ads.aab.a((java.lang.Object) r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00cc, code lost:
        if (com.google.android.gms.internal.ads.aab.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.ads.aab.a((java.lang.Object) r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e2, code lost:
        if (com.google.android.gms.internal.ads.zf.a(com.google.android.gms.internal.ads.aab.f(r10, r6), com.google.android.gms.internal.ads.aab.f(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00f8, code lost:
        if (com.google.android.gms.internal.ads.zf.a(com.google.android.gms.internal.ads.aab.f(r10, r6), com.google.android.gms.internal.ads.aab.f(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010e, code lost:
        if (com.google.android.gms.internal.ads.zf.a(com.google.android.gms.internal.ads.aab.f(r10, r6), com.google.android.gms.internal.ads.aab.f(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0120, code lost:
        if (com.google.android.gms.internal.ads.aab.c(r10, r6) == com.google.android.gms.internal.ads.aab.c(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0132, code lost:
        if (com.google.android.gms.internal.ads.aab.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.ads.aab.a((java.lang.Object) r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0145, code lost:
        if (com.google.android.gms.internal.ads.aab.b(r10, r6) == com.google.android.gms.internal.ads.aab.b(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0156, code lost:
        if (com.google.android.gms.internal.ads.aab.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.ads.aab.a((java.lang.Object) r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0169, code lost:
        if (com.google.android.gms.internal.ads.aab.b(r10, r6) == com.google.android.gms.internal.ads.aab.b(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x017c, code lost:
        if (com.google.android.gms.internal.ads.aab.b(r10, r6) == com.google.android.gms.internal.ads.aab.b(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x018d, code lost:
        if (com.google.android.gms.internal.ads.aab.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.ads.aab.a((java.lang.Object) r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01a0, code lost:
        if (com.google.android.gms.internal.ads.aab.b(r10, r6) == com.google.android.gms.internal.ads.aab.b(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01a2, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
        if (com.google.android.gms.internal.ads.zf.a(com.google.android.gms.internal.ads.aab.f(r10, r6), com.google.android.gms.internal.ads.aab.f(r11, r6)) != false) goto L_0x01a3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(T r10, T r11) {
        /*
            r9 = this;
            int[] r0 = r9.b
            r1 = 0
            int r0 = r0.length
            r2 = r1
        L_0x0005:
            r3 = 1
            if (r2 >= r0) goto L_0x01aa
            int r4 = r9.d(r2)
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r6 = r4 & r5
            long r6 = (long) r6
            r8 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = r4 & r8
            int r4 = r4 >>> 20
            switch(r4) {
                case 0: goto L_0x0190;
                case 1: goto L_0x017f;
                case 2: goto L_0x016c;
                case 3: goto L_0x0159;
                case 4: goto L_0x0148;
                case 5: goto L_0x0135;
                case 6: goto L_0x0124;
                case 7: goto L_0x0112;
                case 8: goto L_0x00fc;
                case 9: goto L_0x00e6;
                case 10: goto L_0x00d0;
                case 11: goto L_0x00be;
                case 12: goto L_0x00ac;
                case 13: goto L_0x009a;
                case 14: goto L_0x0086;
                case 15: goto L_0x0074;
                case 16: goto L_0x0060;
                case 17: goto L_0x004a;
                case 18: goto L_0x003c;
                case 19: goto L_0x003c;
                case 20: goto L_0x003c;
                case 21: goto L_0x003c;
                case 22: goto L_0x003c;
                case 23: goto L_0x003c;
                case 24: goto L_0x003c;
                case 25: goto L_0x003c;
                case 26: goto L_0x003c;
                case 27: goto L_0x003c;
                case 28: goto L_0x003c;
                case 29: goto L_0x003c;
                case 30: goto L_0x003c;
                case 31: goto L_0x003c;
                case 32: goto L_0x003c;
                case 33: goto L_0x003c;
                case 34: goto L_0x003c;
                case 35: goto L_0x003c;
                case 36: goto L_0x003c;
                case 37: goto L_0x003c;
                case 38: goto L_0x003c;
                case 39: goto L_0x003c;
                case 40: goto L_0x003c;
                case 41: goto L_0x003c;
                case 42: goto L_0x003c;
                case 43: goto L_0x003c;
                case 44: goto L_0x003c;
                case 45: goto L_0x003c;
                case 46: goto L_0x003c;
                case 47: goto L_0x003c;
                case 48: goto L_0x003c;
                case 49: goto L_0x003c;
                case 50: goto L_0x003c;
                case 51: goto L_0x001c;
                case 52: goto L_0x001c;
                case 53: goto L_0x001c;
                case 54: goto L_0x001c;
                case 55: goto L_0x001c;
                case 56: goto L_0x001c;
                case 57: goto L_0x001c;
                case 58: goto L_0x001c;
                case 59: goto L_0x001c;
                case 60: goto L_0x001c;
                case 61: goto L_0x001c;
                case 62: goto L_0x001c;
                case 63: goto L_0x001c;
                case 64: goto L_0x001c;
                case 65: goto L_0x001c;
                case 66: goto L_0x001c;
                case 67: goto L_0x001c;
                case 68: goto L_0x001c;
                default: goto L_0x001a;
            }
        L_0x001a:
            goto L_0x01a3
        L_0x001c:
            int r4 = r9.e(r2)
            r4 = r4 & r5
            long r4 = (long) r4
            int r8 = com.google.android.gms.internal.ads.aab.a(r10, r4)
            int r4 = com.google.android.gms.internal.ads.aab.a(r11, r4)
            if (r8 != r4) goto L_0x01a2
            java.lang.Object r4 = com.google.android.gms.internal.ads.aab.f(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.ads.aab.f(r11, r6)
            boolean r4 = com.google.android.gms.internal.ads.zf.a(r4, r5)
            if (r4 != 0) goto L_0x01a3
            goto L_0x018f
        L_0x003c:
            java.lang.Object r3 = com.google.android.gms.internal.ads.aab.f(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.ads.aab.f(r11, r6)
            boolean r3 = com.google.android.gms.internal.ads.zf.a(r3, r4)
            goto L_0x01a3
        L_0x004a:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            java.lang.Object r4 = com.google.android.gms.internal.ads.aab.f(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.ads.aab.f(r11, r6)
            boolean r4 = com.google.android.gms.internal.ads.zf.a(r4, r5)
            if (r4 != 0) goto L_0x01a3
            goto L_0x01a2
        L_0x0060:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.ads.aab.b(r10, r6)
            long r6 = com.google.android.gms.internal.ads.aab.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
            goto L_0x018f
        L_0x0074:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.ads.aab.a(r10, r6)
            int r5 = com.google.android.gms.internal.ads.aab.a(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x01a2
        L_0x0086:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.ads.aab.b(r10, r6)
            long r6 = com.google.android.gms.internal.ads.aab.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
            goto L_0x018f
        L_0x009a:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.ads.aab.a(r10, r6)
            int r5 = com.google.android.gms.internal.ads.aab.a(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x01a2
        L_0x00ac:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.ads.aab.a(r10, r6)
            int r5 = com.google.android.gms.internal.ads.aab.a(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x018f
        L_0x00be:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.ads.aab.a(r10, r6)
            int r5 = com.google.android.gms.internal.ads.aab.a(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x01a2
        L_0x00d0:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            java.lang.Object r4 = com.google.android.gms.internal.ads.aab.f(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.ads.aab.f(r11, r6)
            boolean r4 = com.google.android.gms.internal.ads.zf.a(r4, r5)
            if (r4 != 0) goto L_0x01a3
            goto L_0x018f
        L_0x00e6:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            java.lang.Object r4 = com.google.android.gms.internal.ads.aab.f(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.ads.aab.f(r11, r6)
            boolean r4 = com.google.android.gms.internal.ads.zf.a(r4, r5)
            if (r4 != 0) goto L_0x01a3
            goto L_0x01a2
        L_0x00fc:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            java.lang.Object r4 = com.google.android.gms.internal.ads.aab.f(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.ads.aab.f(r11, r6)
            boolean r4 = com.google.android.gms.internal.ads.zf.a(r4, r5)
            if (r4 != 0) goto L_0x01a3
            goto L_0x018f
        L_0x0112:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            boolean r4 = com.google.android.gms.internal.ads.aab.c(r10, r6)
            boolean r5 = com.google.android.gms.internal.ads.aab.c(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x01a2
        L_0x0124:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.ads.aab.a(r10, r6)
            int r5 = com.google.android.gms.internal.ads.aab.a(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x018f
        L_0x0135:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.ads.aab.b(r10, r6)
            long r6 = com.google.android.gms.internal.ads.aab.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
            goto L_0x01a2
        L_0x0148:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.ads.aab.a(r10, r6)
            int r5 = com.google.android.gms.internal.ads.aab.a(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x018f
        L_0x0159:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.ads.aab.b(r10, r6)
            long r6 = com.google.android.gms.internal.ads.aab.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
            goto L_0x01a2
        L_0x016c:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.ads.aab.b(r10, r6)
            long r6 = com.google.android.gms.internal.ads.aab.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
            goto L_0x018f
        L_0x017f:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.ads.aab.a(r10, r6)
            int r5 = com.google.android.gms.internal.ads.aab.a(r11, r6)
            if (r4 == r5) goto L_0x01a3
        L_0x018f:
            goto L_0x01a2
        L_0x0190:
            boolean r4 = r9.c(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.ads.aab.b(r10, r6)
            long r6 = com.google.android.gms.internal.ads.aab.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
        L_0x01a2:
            r3 = r1
        L_0x01a3:
            if (r3 != 0) goto L_0x01a6
            return r1
        L_0x01a6:
            int r2 = r2 + 4
            goto L_0x0005
        L_0x01aa:
            com.google.android.gms.internal.ads.zv<?, ?> r0 = r9.q
            java.lang.Object r0 = r0.b(r10)
            com.google.android.gms.internal.ads.zv<?, ?> r2 = r9.q
            java.lang.Object r2 = r2.b(r11)
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x01bd
            return r1
        L_0x01bd:
            boolean r0 = r9.h
            if (r0 == 0) goto L_0x01d2
            com.google.android.gms.internal.ads.wy<?> r0 = r9.r
            com.google.android.gms.internal.ads.xb r10 = r0.a(r10)
            com.google.android.gms.internal.ads.wy<?> r0 = r9.r
            com.google.android.gms.internal.ads.xb r11 = r0.a(r11)
            boolean r10 = r10.equals(r11)
            return r10
        L_0x01d2:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yo.a(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX INFO: used method not loaded: com.google.android.gms.internal.ads.zf.a(int, java.lang.Object, com.google.android.gms.internal.ads.zd):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01d8, code lost:
        if (r0.k != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01e9, code lost:
        if (r0.k != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01fa, code lost:
        if (r0.k != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x020b, code lost:
        if (r0.k != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x020d, code lost:
        r2.putInt(r1, (long) r14, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0211, code lost:
        r3 = (com.google.android.gms.internal.ads.zzbav.e(r3) + com.google.android.gms.internal.ads.zzbav.g(r4)) + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0296, code lost:
        r13 = r13 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x029f, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.c(r3, (com.google.android.gms.internal.ads.yk) com.google.android.gms.internal.ads.aab.f(r1, r4), a(r12));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02b8, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.f(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x02c7, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.h(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x02d2, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.h(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x02dd, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.j(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x02ec, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.k(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x02fb, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.g(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0306, code lost:
        r4 = com.google.android.gms.internal.ads.aab.f(r1, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x030a, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.c(r3, (com.google.android.gms.internal.ads.zzbah) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0317, code lost:
        r3 = com.google.android.gms.internal.ads.zf.a(r3, com.google.android.gms.internal.ads.aab.f(r1, r4), a(r12));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0331, code lost:
        if ((r4 instanceof com.google.android.gms.internal.ads.zzbah) != false) goto L_0x030a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0334, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.b(r3, (java.lang.String) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0342, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.b(r3, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x034e, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.i(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x035a, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.g(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x036a, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.f(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x037a, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.e(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x038a, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.d(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x0396, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.b(r3, 0.0f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x03a2, code lost:
        r3 = com.google.android.gms.internal.ads.zzbav.b(r3, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x03aa, code lost:
        r12 = r12 + 4;
        r3 = 267386880;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x0418, code lost:
        if (a(r1, r14, r3) != false) goto L_0x06ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x0438, code lost:
        if (a(r1, r14, r3) != false) goto L_0x06f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x0440, code lost:
        if (a(r1, r14, r3) != false) goto L_0x0702;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x0460, code lost:
        if (a(r1, r14, r3) != false) goto L_0x0727;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x0468, code lost:
        if (a(r1, r14, r3) != false) goto L_0x0736;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x0478, code lost:
        if ((r6 instanceof com.google.android.gms.internal.ads.zzbah) != false) goto L_0x072b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x0480, code lost:
        if (a(r1, r14, r3) != false) goto L_0x075d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x0518, code lost:
        if (r0.k != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:275:0x052a, code lost:
        if (r0.k != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:279:0x053c, code lost:
        if (r0.k != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x054e, code lost:
        if (r0.k != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x0560, code lost:
        if (r0.k != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x0572, code lost:
        if (r0.k != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x0584, code lost:
        if (r0.k != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:0x0596, code lost:
        if (r0.k != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x05a7, code lost:
        if (r0.k != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x05b8, code lost:
        if (r0.k != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:311:0x05c9, code lost:
        if (r0.k != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:315:0x05da, code lost:
        if (r0.k != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:319:0x05eb, code lost:
        if (r0.k != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:323:0x05fc, code lost:
        if (r0.k != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x05fe, code lost:
        r2.putInt(r1, (long) r6, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:325:0x0602, code lost:
        r6 = (com.google.android.gms.internal.ads.zzbav.e(r14) + com.google.android.gms.internal.ads.zzbav.g(r9)) + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:339:0x06af, code lost:
        r4 = r4 + r6;
        r6 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:341:0x06bd, code lost:
        r4 = r4 + r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:343:0x06bf, code lost:
        r9 = false;
        r18 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:345:0x06c8, code lost:
        if ((r12 & r16) != 0) goto L_0x06ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:346:0x06ca, code lost:
        r6 = com.google.android.gms.internal.ads.zzbav.c(r14, (com.google.android.gms.internal.ads.yk) r2.getObject(r1, r9), a(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:350:0x06e1, code lost:
        r6 = com.google.android.gms.internal.ads.zzbav.f(r14, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:0x06ee, code lost:
        r6 = com.google.android.gms.internal.ads.zzbav.h(r14, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:356:0x06f5, code lost:
        if ((r12 & r16) != 0) goto L_0x06f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:0x06f7, code lost:
        r6 = com.google.android.gms.internal.ads.zzbav.h(r14, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x0700, code lost:
        if ((r12 & r16) != 0) goto L_0x0702;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:360:0x0702, code lost:
        r9 = com.google.android.gms.internal.ads.zzbav.j(r14, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:361:0x0707, code lost:
        r4 = r4 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:0x0711, code lost:
        r6 = com.google.android.gms.internal.ads.zzbav.k(r14, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:0x071e, code lost:
        r6 = com.google.android.gms.internal.ads.zzbav.g(r14, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:0x0725, code lost:
        if ((r12 & r16) != 0) goto L_0x0727;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:0x0727, code lost:
        r6 = r2.getObject(r1, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:0x072b, code lost:
        r6 = com.google.android.gms.internal.ads.zzbav.c(r14, (com.google.android.gms.internal.ads.zzbah) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:375:0x0734, code lost:
        if ((r12 & r16) != 0) goto L_0x0736;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:376:0x0736, code lost:
        r6 = com.google.android.gms.internal.ads.zf.a(r14, r2.getObject(r1, r9), a(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ab, code lost:
        if ((r4 instanceof com.google.android.gms.internal.ads.zzbah) != false) goto L_0x030a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x074e, code lost:
        if ((r6 instanceof com.google.android.gms.internal.ads.zzbah) != false) goto L_0x072b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:381:0x0751, code lost:
        r6 = com.google.android.gms.internal.ads.zzbav.b(r14, (java.lang.String) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:383:0x075b, code lost:
        if ((r12 & r16) != 0) goto L_0x075d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:384:0x075d, code lost:
        r6 = com.google.android.gms.internal.ads.zzbav.b(r14, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:400:0x07ad, code lost:
        r4 = r4 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:409:0x07cf, code lost:
        r3 = r3 + 4;
        r11 = r6;
        r6 = r9;
        r9 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0127, code lost:
        if (r0.k != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0139, code lost:
        if (r0.k != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x014b, code lost:
        if (r0.k != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x015d, code lost:
        if (r0.k != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x016f, code lost:
        if (r0.k != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0181, code lost:
        if (r0.k != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0193, code lost:
        if (r0.k != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01a5, code lost:
        if (r0.k != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01b6, code lost:
        if (r0.k != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01c7, code lost:
        if (r0.k != false) goto L_0x020d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int b(T r22) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            boolean r2 = r0.j
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            r6 = 0
            r7 = 1
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r9 = 0
            r11 = 0
            if (r2 == 0) goto L_0x03b8
            sun.misc.Unsafe r2 = a
            r12 = r11
            r13 = r12
        L_0x0016:
            int[] r14 = r0.b
            int r14 = r14.length
            if (r12 >= r14) goto L_0x03b0
            int r14 = r0.d(r12)
            r15 = r14 & r3
            int r15 = r15 >>> 20
            int[] r3 = r0.b
            r3 = r3[r12]
            r14 = r14 & r8
            long r4 = (long) r14
            com.google.android.gms.internal.ads.zzbbj r14 = com.google.android.gms.internal.ads.zzbbj.DOUBLE_LIST_PACKED
            int r14 = r14.id()
            if (r15 < r14) goto L_0x0041
            com.google.android.gms.internal.ads.zzbbj r14 = com.google.android.gms.internal.ads.zzbbj.SINT64_LIST_PACKED
            int r14 = r14.id()
            if (r15 > r14) goto L_0x0041
            int[] r14 = r0.b
            int r17 = r12 + 2
            r14 = r14[r17]
            r14 = r14 & r8
            goto L_0x0042
        L_0x0041:
            r14 = r11
        L_0x0042:
            switch(r15) {
                case 0: goto L_0x039c;
                case 1: goto L_0x0390;
                case 2: goto L_0x0380;
                case 3: goto L_0x0370;
                case 4: goto L_0x0360;
                case 5: goto L_0x0354;
                case 6: goto L_0x0348;
                case 7: goto L_0x033c;
                case 8: goto L_0x0325;
                case 9: goto L_0x0311;
                case 10: goto L_0x0300;
                case 11: goto L_0x02f1;
                case 12: goto L_0x02e2;
                case 13: goto L_0x02d7;
                case 14: goto L_0x02cc;
                case 15: goto L_0x02bd;
                case 16: goto L_0x02ae;
                case 17: goto L_0x0299;
                case 18: goto L_0x028e;
                case 19: goto L_0x0285;
                case 20: goto L_0x027c;
                case 21: goto L_0x0273;
                case 22: goto L_0x026a;
                case 23: goto L_0x028e;
                case 24: goto L_0x0285;
                case 25: goto L_0x0261;
                case 26: goto L_0x0258;
                case 27: goto L_0x024b;
                case 28: goto L_0x0242;
                case 29: goto L_0x0239;
                case 30: goto L_0x0230;
                case 31: goto L_0x0285;
                case 32: goto L_0x028e;
                case 33: goto L_0x0227;
                case 34: goto L_0x021d;
                case 35: goto L_0x01fd;
                case 36: goto L_0x01ec;
                case 37: goto L_0x01db;
                case 38: goto L_0x01ca;
                case 39: goto L_0x01b9;
                case 40: goto L_0x01a8;
                case 41: goto L_0x0197;
                case 42: goto L_0x0185;
                case 43: goto L_0x0173;
                case 44: goto L_0x0161;
                case 45: goto L_0x014f;
                case 46: goto L_0x013d;
                case 47: goto L_0x012b;
                case 48: goto L_0x0119;
                case 49: goto L_0x010b;
                case 50: goto L_0x00fb;
                case 51: goto L_0x00f3;
                case 52: goto L_0x00eb;
                case 53: goto L_0x00df;
                case 54: goto L_0x00d3;
                case 55: goto L_0x00c7;
                case 56: goto L_0x00bf;
                case 57: goto L_0x00b7;
                case 58: goto L_0x00af;
                case 59: goto L_0x009f;
                case 60: goto L_0x0097;
                case 61: goto L_0x008f;
                case 62: goto L_0x0083;
                case 63: goto L_0x0077;
                case 64: goto L_0x006f;
                case 65: goto L_0x0067;
                case 66: goto L_0x005b;
                case 67: goto L_0x004f;
                case 68: goto L_0x0047;
                default: goto L_0x0045;
            }
        L_0x0045:
            goto L_0x03aa
        L_0x0047:
            boolean r14 = r0.a((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            goto L_0x029f
        L_0x004f:
            boolean r14 = r0.a((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            long r4 = e(r1, r4)
            goto L_0x02b8
        L_0x005b:
            boolean r14 = r0.a((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = d(r1, r4)
            goto L_0x02c7
        L_0x0067:
            boolean r4 = r0.a((T) r1, r3, r12)
            if (r4 == 0) goto L_0x03aa
            goto L_0x02d2
        L_0x006f:
            boolean r4 = r0.a((T) r1, r3, r12)
            if (r4 == 0) goto L_0x03aa
            goto L_0x02dd
        L_0x0077:
            boolean r14 = r0.a((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = d(r1, r4)
            goto L_0x02ec
        L_0x0083:
            boolean r14 = r0.a((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = d(r1, r4)
            goto L_0x02fb
        L_0x008f:
            boolean r14 = r0.a((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            goto L_0x0306
        L_0x0097:
            boolean r14 = r0.a((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            goto L_0x0317
        L_0x009f:
            boolean r14 = r0.a((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            java.lang.Object r4 = com.google.android.gms.internal.ads.aab.f(r1, r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.ads.zzbah
            if (r5 == 0) goto L_0x0334
            goto L_0x0333
        L_0x00af:
            boolean r4 = r0.a((T) r1, r3, r12)
            if (r4 == 0) goto L_0x03aa
            goto L_0x0342
        L_0x00b7:
            boolean r4 = r0.a((T) r1, r3, r12)
            if (r4 == 0) goto L_0x03aa
            goto L_0x034e
        L_0x00bf:
            boolean r4 = r0.a((T) r1, r3, r12)
            if (r4 == 0) goto L_0x03aa
            goto L_0x035a
        L_0x00c7:
            boolean r14 = r0.a((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = d(r1, r4)
            goto L_0x036a
        L_0x00d3:
            boolean r14 = r0.a((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            long r4 = e(r1, r4)
            goto L_0x037a
        L_0x00df:
            boolean r14 = r0.a((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            long r4 = e(r1, r4)
            goto L_0x038a
        L_0x00eb:
            boolean r4 = r0.a((T) r1, r3, r12)
            if (r4 == 0) goto L_0x03aa
            goto L_0x0396
        L_0x00f3:
            boolean r4 = r0.a((T) r1, r3, r12)
            if (r4 == 0) goto L_0x03aa
            goto L_0x03a2
        L_0x00fb:
            com.google.android.gms.internal.ads.yf r14 = r0.s
            java.lang.Object r4 = com.google.android.gms.internal.ads.aab.f(r1, r4)
            java.lang.Object r5 = r0.b(r12)
            int r3 = r14.a(r3, r4, r5)
            goto L_0x0296
        L_0x010b:
            java.util.List r4 = a(r1, r4)
            com.google.android.gms.internal.ads.zd r5 = r0.a(r12)
            int r3 = com.google.android.gms.internal.ads.zf.b(r3, r4, r5)
            goto L_0x0296
        L_0x0119:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.ads.zf.c(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.k
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x012b:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.ads.zf.g(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.k
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x013d:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.ads.zf.i(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.k
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x014f:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.ads.zf.h(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.k
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0161:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.ads.zf.d(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.k
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0173:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.ads.zf.f(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.k
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0185:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.ads.zf.j(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.k
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0197:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.ads.zf.h(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.k
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01a8:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.ads.zf.i(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.k
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01b9:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.ads.zf.e(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.k
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01ca:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.ads.zf.b(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.k
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01db:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.ads.zf.a(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.k
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01ec:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.ads.zf.h(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.k
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01fd:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.ads.zf.i(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.k
            if (r5 == 0) goto L_0x0211
        L_0x020d:
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x0211:
            int r3 = com.google.android.gms.internal.ads.zzbav.e(r3)
            int r5 = com.google.android.gms.internal.ads.zzbav.g(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            goto L_0x0296
        L_0x021d:
            java.util.List r4 = a(r1, r4)
            int r3 = com.google.android.gms.internal.ads.zf.c(r3, r4, r11)
            goto L_0x0296
        L_0x0227:
            java.util.List r4 = a(r1, r4)
            int r3 = com.google.android.gms.internal.ads.zf.g(r3, r4, r11)
            goto L_0x0296
        L_0x0230:
            java.util.List r4 = a(r1, r4)
            int r3 = com.google.android.gms.internal.ads.zf.d(r3, r4, r11)
            goto L_0x0296
        L_0x0239:
            java.util.List r4 = a(r1, r4)
            int r3 = com.google.android.gms.internal.ads.zf.f(r3, r4, r11)
            goto L_0x0296
        L_0x0242:
            java.util.List r4 = a(r1, r4)
            int r3 = com.google.android.gms.internal.ads.zf.b(r3, r4)
            goto L_0x0296
        L_0x024b:
            java.util.List r4 = a(r1, r4)
            com.google.android.gms.internal.ads.zd r5 = r0.a(r12)
            int r3 = com.google.android.gms.internal.ads.zf.a(r3, r4, r5)
            goto L_0x0296
        L_0x0258:
            java.util.List r4 = a(r1, r4)
            int r3 = com.google.android.gms.internal.ads.zf.a(r3, r4)
            goto L_0x0296
        L_0x0261:
            java.util.List r4 = a(r1, r4)
            int r3 = com.google.android.gms.internal.ads.zf.j(r3, r4, r11)
            goto L_0x0296
        L_0x026a:
            java.util.List r4 = a(r1, r4)
            int r3 = com.google.android.gms.internal.ads.zf.e(r3, r4, r11)
            goto L_0x0296
        L_0x0273:
            java.util.List r4 = a(r1, r4)
            int r3 = com.google.android.gms.internal.ads.zf.b(r3, r4, r11)
            goto L_0x0296
        L_0x027c:
            java.util.List r4 = a(r1, r4)
            int r3 = com.google.android.gms.internal.ads.zf.a(r3, r4, r11)
            goto L_0x0296
        L_0x0285:
            java.util.List r4 = a(r1, r4)
            int r3 = com.google.android.gms.internal.ads.zf.h(r3, r4, r11)
            goto L_0x0296
        L_0x028e:
            java.util.List r4 = a(r1, r4)
            int r3 = com.google.android.gms.internal.ads.zf.i(r3, r4, r11)
        L_0x0296:
            int r13 = r13 + r3
            goto L_0x03aa
        L_0x0299:
            boolean r14 = r0.a((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
        L_0x029f:
            java.lang.Object r4 = com.google.android.gms.internal.ads.aab.f(r1, r4)
            com.google.android.gms.internal.ads.yk r4 = (com.google.android.gms.internal.ads.yk) r4
            com.google.android.gms.internal.ads.zd r5 = r0.a(r12)
            int r3 = com.google.android.gms.internal.ads.zzbav.c(r3, r4, r5)
            goto L_0x0296
        L_0x02ae:
            boolean r14 = r0.a((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            long r4 = com.google.android.gms.internal.ads.aab.b(r1, r4)
        L_0x02b8:
            int r3 = com.google.android.gms.internal.ads.zzbav.f(r3, r4)
            goto L_0x0296
        L_0x02bd:
            boolean r14 = r0.a((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = com.google.android.gms.internal.ads.aab.a(r1, r4)
        L_0x02c7:
            int r3 = com.google.android.gms.internal.ads.zzbav.h(r3, r4)
            goto L_0x0296
        L_0x02cc:
            boolean r4 = r0.a((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x02d2:
            int r3 = com.google.android.gms.internal.ads.zzbav.h(r3, r9)
            goto L_0x0296
        L_0x02d7:
            boolean r4 = r0.a((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x02dd:
            int r3 = com.google.android.gms.internal.ads.zzbav.j(r3, r11)
            goto L_0x0296
        L_0x02e2:
            boolean r14 = r0.a((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = com.google.android.gms.internal.ads.aab.a(r1, r4)
        L_0x02ec:
            int r3 = com.google.android.gms.internal.ads.zzbav.k(r3, r4)
            goto L_0x0296
        L_0x02f1:
            boolean r14 = r0.a((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = com.google.android.gms.internal.ads.aab.a(r1, r4)
        L_0x02fb:
            int r3 = com.google.android.gms.internal.ads.zzbav.g(r3, r4)
            goto L_0x0296
        L_0x0300:
            boolean r14 = r0.a((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
        L_0x0306:
            java.lang.Object r4 = com.google.android.gms.internal.ads.aab.f(r1, r4)
        L_0x030a:
            com.google.android.gms.internal.ads.zzbah r4 = (com.google.android.gms.internal.ads.zzbah) r4
            int r3 = com.google.android.gms.internal.ads.zzbav.c(r3, r4)
            goto L_0x0296
        L_0x0311:
            boolean r14 = r0.a((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
        L_0x0317:
            java.lang.Object r4 = com.google.android.gms.internal.ads.aab.f(r1, r4)
            com.google.android.gms.internal.ads.zd r5 = r0.a(r12)
            int r3 = com.google.android.gms.internal.ads.zf.a(r3, r4, r5)
            goto L_0x0296
        L_0x0325:
            boolean r14 = r0.a((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            java.lang.Object r4 = com.google.android.gms.internal.ads.aab.f(r1, r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.ads.zzbah
            if (r5 == 0) goto L_0x0334
        L_0x0333:
            goto L_0x030a
        L_0x0334:
            java.lang.String r4 = (java.lang.String) r4
            int r3 = com.google.android.gms.internal.ads.zzbav.b(r3, r4)
            goto L_0x0296
        L_0x033c:
            boolean r4 = r0.a((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x0342:
            int r3 = com.google.android.gms.internal.ads.zzbav.b(r3, r7)
            goto L_0x0296
        L_0x0348:
            boolean r4 = r0.a((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x034e:
            int r3 = com.google.android.gms.internal.ads.zzbav.i(r3, r11)
            goto L_0x0296
        L_0x0354:
            boolean r4 = r0.a((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x035a:
            int r3 = com.google.android.gms.internal.ads.zzbav.g(r3, r9)
            goto L_0x0296
        L_0x0360:
            boolean r14 = r0.a((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = com.google.android.gms.internal.ads.aab.a(r1, r4)
        L_0x036a:
            int r3 = com.google.android.gms.internal.ads.zzbav.f(r3, r4)
            goto L_0x0296
        L_0x0370:
            boolean r14 = r0.a((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            long r4 = com.google.android.gms.internal.ads.aab.b(r1, r4)
        L_0x037a:
            int r3 = com.google.android.gms.internal.ads.zzbav.e(r3, r4)
            goto L_0x0296
        L_0x0380:
            boolean r14 = r0.a((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            long r4 = com.google.android.gms.internal.ads.aab.b(r1, r4)
        L_0x038a:
            int r3 = com.google.android.gms.internal.ads.zzbav.d(r3, r4)
            goto L_0x0296
        L_0x0390:
            boolean r4 = r0.a((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x0396:
            int r3 = com.google.android.gms.internal.ads.zzbav.b(r3, r6)
            goto L_0x0296
        L_0x039c:
            boolean r4 = r0.a((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x03a2:
            r4 = 0
            int r3 = com.google.android.gms.internal.ads.zzbav.b(r3, r4)
            goto L_0x0296
        L_0x03aa:
            int r12 = r12 + 4
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            goto L_0x0016
        L_0x03b0:
            com.google.android.gms.internal.ads.zv<?, ?> r2 = r0.q
            int r1 = a(r2, (T) r1)
            int r13 = r13 + r1
            return r13
        L_0x03b8:
            sun.misc.Unsafe r2 = a
            r3 = -1
            r5 = r3
            r3 = r11
            r4 = r3
            r12 = r4
        L_0x03bf:
            int[] r13 = r0.b
            int r13 = r13.length
            if (r3 >= r13) goto L_0x07d7
            int r13 = r0.d(r3)
            int[] r14 = r0.b
            r14 = r14[r3]
            r15 = 267386880(0xff00000, float:2.3665827E-29)
            r16 = r13 & r15
            int r15 = r16 >>> 20
            r6 = 17
            if (r15 > r6) goto L_0x03eb
            int[] r6 = r0.b
            int r16 = r3 + 2
            r6 = r6[r16]
            r11 = r6 & r8
            int r16 = r6 >>> 20
            int r16 = r7 << r16
            if (r11 == r5) goto L_0x040c
            long r9 = (long) r11
            int r12 = r2.getInt(r1, r9)
            r5 = r11
            goto L_0x040c
        L_0x03eb:
            boolean r6 = r0.k
            if (r6 == 0) goto L_0x0409
            com.google.android.gms.internal.ads.zzbbj r6 = com.google.android.gms.internal.ads.zzbbj.DOUBLE_LIST_PACKED
            int r6 = r6.id()
            if (r15 < r6) goto L_0x0409
            com.google.android.gms.internal.ads.zzbbj r6 = com.google.android.gms.internal.ads.zzbbj.SINT64_LIST_PACKED
            int r6 = r6.id()
            if (r15 > r6) goto L_0x0409
            int[] r6 = r0.b
            int r9 = r3 + 2
            r6 = r6[r9]
            r11 = r6 & r8
            r6 = r11
            goto L_0x040a
        L_0x0409:
            r6 = 0
        L_0x040a:
            r16 = 0
        L_0x040c:
            r9 = r13 & r8
            long r9 = (long) r9
            switch(r15) {
                case 0: goto L_0x07c0;
                case 1: goto L_0x07b0;
                case 2: goto L_0x079e;
                case 3: goto L_0x078e;
                case 4: goto L_0x077e;
                case 5: goto L_0x076f;
                case 6: goto L_0x0763;
                case 7: goto L_0x0759;
                case 8: goto L_0x0744;
                case 9: goto L_0x0732;
                case 10: goto L_0x0723;
                case 11: goto L_0x0716;
                case 12: goto L_0x0709;
                case 13: goto L_0x06fe;
                case 14: goto L_0x06f3;
                case 15: goto L_0x06e6;
                case 16: goto L_0x06d9;
                case 17: goto L_0x06c6;
                case 18: goto L_0x06b2;
                case 19: goto L_0x06a4;
                case 20: goto L_0x0698;
                case 21: goto L_0x068c;
                case 22: goto L_0x0680;
                case 23: goto L_0x0674;
                case 24: goto L_0x06a4;
                case 25: goto L_0x0668;
                case 26: goto L_0x065d;
                case 27: goto L_0x064e;
                case 28: goto L_0x0642;
                case 29: goto L_0x0635;
                case 30: goto L_0x0628;
                case 31: goto L_0x06a4;
                case 32: goto L_0x0674;
                case 33: goto L_0x061b;
                case 34: goto L_0x060e;
                case 35: goto L_0x05ee;
                case 36: goto L_0x05dd;
                case 37: goto L_0x05cc;
                case 38: goto L_0x05bb;
                case 39: goto L_0x05aa;
                case 40: goto L_0x0599;
                case 41: goto L_0x0588;
                case 42: goto L_0x0576;
                case 43: goto L_0x0564;
                case 44: goto L_0x0552;
                case 45: goto L_0x0540;
                case 46: goto L_0x052e;
                case 47: goto L_0x051c;
                case 48: goto L_0x050a;
                case 49: goto L_0x04fa;
                case 50: goto L_0x04ea;
                case 51: goto L_0x04dc;
                case 52: goto L_0x04cf;
                case 53: goto L_0x04bf;
                case 54: goto L_0x04af;
                case 55: goto L_0x049f;
                case 56: goto L_0x0491;
                case 57: goto L_0x0484;
                case 58: goto L_0x047c;
                case 59: goto L_0x046c;
                case 60: goto L_0x0464;
                case 61: goto L_0x045c;
                case 62: goto L_0x0450;
                case 63: goto L_0x0444;
                case 64: goto L_0x043c;
                case 65: goto L_0x0434;
                case 66: goto L_0x0428;
                case 67: goto L_0x041c;
                case 68: goto L_0x0414;
                default: goto L_0x0412;
            }
        L_0x0412:
            goto L_0x06be
        L_0x0414:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            goto L_0x06ca
        L_0x041c:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            long r9 = e(r1, r9)
            goto L_0x06e1
        L_0x0428:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            int r6 = d(r1, r9)
            goto L_0x06ee
        L_0x0434:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            goto L_0x06f7
        L_0x043c:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            goto L_0x0702
        L_0x0444:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            int r6 = d(r1, r9)
            goto L_0x0711
        L_0x0450:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            int r6 = d(r1, r9)
            goto L_0x071e
        L_0x045c:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            goto L_0x0727
        L_0x0464:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            goto L_0x0736
        L_0x046c:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            java.lang.Object r6 = r2.getObject(r1, r9)
            boolean r9 = r6 instanceof com.google.android.gms.internal.ads.zzbah
            if (r9 == 0) goto L_0x0751
            goto L_0x0750
        L_0x047c:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            goto L_0x075d
        L_0x0484:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            r6 = 0
            int r9 = com.google.android.gms.internal.ads.zzbav.i(r14, r6)
            goto L_0x0707
        L_0x0491:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            r9 = 0
            int r6 = com.google.android.gms.internal.ads.zzbav.g(r14, r9)
            goto L_0x06bd
        L_0x049f:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            int r6 = d(r1, r9)
            int r6 = com.google.android.gms.internal.ads.zzbav.f(r14, r6)
            goto L_0x06bd
        L_0x04af:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            long r9 = e(r1, r9)
            int r6 = com.google.android.gms.internal.ads.zzbav.e(r14, r9)
            goto L_0x06bd
        L_0x04bf:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            long r9 = e(r1, r9)
            int r6 = com.google.android.gms.internal.ads.zzbav.d(r14, r9)
            goto L_0x06bd
        L_0x04cf:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            r6 = 0
            int r9 = com.google.android.gms.internal.ads.zzbav.b(r14, r6)
            goto L_0x0707
        L_0x04dc:
            boolean r6 = r0.a((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06be
            r9 = 0
            int r6 = com.google.android.gms.internal.ads.zzbav.b(r14, r9)
            goto L_0x06bd
        L_0x04ea:
            com.google.android.gms.internal.ads.yf r6 = r0.s
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.lang.Object r10 = r0.b(r3)
            int r6 = r6.a(r14, r9, r10)
            goto L_0x06bd
        L_0x04fa:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.ads.zd r9 = r0.a(r3)
            int r6 = com.google.android.gms.internal.ads.zf.b(r14, r6, r9)
            goto L_0x06bd
        L_0x050a:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.ads.zf.c(r9)
            if (r9 <= 0) goto L_0x06be
            boolean r10 = r0.k
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x051c:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.ads.zf.g(r9)
            if (r9 <= 0) goto L_0x06be
            boolean r10 = r0.k
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x052e:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.ads.zf.i(r9)
            if (r9 <= 0) goto L_0x06be
            boolean r10 = r0.k
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0540:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.ads.zf.h(r9)
            if (r9 <= 0) goto L_0x06be
            boolean r10 = r0.k
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0552:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.ads.zf.d(r9)
            if (r9 <= 0) goto L_0x06be
            boolean r10 = r0.k
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0564:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.ads.zf.f(r9)
            if (r9 <= 0) goto L_0x06be
            boolean r10 = r0.k
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0576:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.ads.zf.j(r9)
            if (r9 <= 0) goto L_0x06be
            boolean r10 = r0.k
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0588:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.ads.zf.h(r9)
            if (r9 <= 0) goto L_0x06be
            boolean r10 = r0.k
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0599:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.ads.zf.i(r9)
            if (r9 <= 0) goto L_0x06be
            boolean r10 = r0.k
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x05aa:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.ads.zf.e(r9)
            if (r9 <= 0) goto L_0x06be
            boolean r10 = r0.k
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x05bb:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.ads.zf.b(r9)
            if (r9 <= 0) goto L_0x06be
            boolean r10 = r0.k
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x05cc:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.ads.zf.a(r9)
            if (r9 <= 0) goto L_0x06be
            boolean r10 = r0.k
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x05dd:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.ads.zf.h(r9)
            if (r9 <= 0) goto L_0x06be
            boolean r10 = r0.k
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x05ee:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.ads.zf.i(r9)
            if (r9 <= 0) goto L_0x06be
            boolean r10 = r0.k
            if (r10 == 0) goto L_0x0602
        L_0x05fe:
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x0602:
            int r6 = com.google.android.gms.internal.ads.zzbav.e(r14)
            int r10 = com.google.android.gms.internal.ads.zzbav.g(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            goto L_0x06bd
        L_0x060e:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            r11 = 0
            int r6 = com.google.android.gms.internal.ads.zf.c(r14, r6, r11)
            goto L_0x06af
        L_0x061b:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.ads.zf.g(r14, r6, r11)
            goto L_0x06af
        L_0x0628:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.ads.zf.d(r14, r6, r11)
            goto L_0x06af
        L_0x0635:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.ads.zf.f(r14, r6, r11)
            goto L_0x06bd
        L_0x0642:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.ads.zf.b(r14, r6)
            goto L_0x06bd
        L_0x064e:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.ads.zd r9 = r0.a(r3)
            int r6 = com.google.android.gms.internal.ads.zf.a(r14, r6, r9)
            goto L_0x06bd
        L_0x065d:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.ads.zf.a(r14, r6)
            goto L_0x06bd
        L_0x0668:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            r11 = 0
            int r6 = com.google.android.gms.internal.ads.zf.j(r14, r6, r11)
            goto L_0x06af
        L_0x0674:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.ads.zf.i(r14, r6, r11)
            goto L_0x06af
        L_0x0680:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.ads.zf.e(r14, r6, r11)
            goto L_0x06af
        L_0x068c:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.ads.zf.b(r14, r6, r11)
            goto L_0x06af
        L_0x0698:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.ads.zf.a(r14, r6, r11)
            goto L_0x06af
        L_0x06a4:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.ads.zf.h(r14, r6, r11)
        L_0x06af:
            int r4 = r4 + r6
            r6 = r11
            goto L_0x06bf
        L_0x06b2:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.ads.zf.i(r14, r6, r11)
        L_0x06bd:
            int r4 = r4 + r6
        L_0x06be:
            r6 = 0
        L_0x06bf:
            r9 = 0
            r10 = 0
            r18 = 0
            goto L_0x07cf
        L_0x06c6:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06be
        L_0x06ca:
            java.lang.Object r6 = r2.getObject(r1, r9)
            com.google.android.gms.internal.ads.yk r6 = (com.google.android.gms.internal.ads.yk) r6
            com.google.android.gms.internal.ads.zd r9 = r0.a(r3)
            int r6 = com.google.android.gms.internal.ads.zzbav.c(r14, r6, r9)
            goto L_0x06bd
        L_0x06d9:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06be
            long r9 = r2.getLong(r1, r9)
        L_0x06e1:
            int r6 = com.google.android.gms.internal.ads.zzbav.f(r14, r9)
            goto L_0x06bd
        L_0x06e6:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06be
            int r6 = r2.getInt(r1, r9)
        L_0x06ee:
            int r6 = com.google.android.gms.internal.ads.zzbav.h(r14, r6)
            goto L_0x06bd
        L_0x06f3:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06be
        L_0x06f7:
            r9 = 0
            int r6 = com.google.android.gms.internal.ads.zzbav.h(r14, r9)
            goto L_0x06bd
        L_0x06fe:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06be
        L_0x0702:
            r6 = 0
            int r9 = com.google.android.gms.internal.ads.zzbav.j(r14, r6)
        L_0x0707:
            int r4 = r4 + r9
            goto L_0x06be
        L_0x0709:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06be
            int r6 = r2.getInt(r1, r9)
        L_0x0711:
            int r6 = com.google.android.gms.internal.ads.zzbav.k(r14, r6)
            goto L_0x06bd
        L_0x0716:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06be
            int r6 = r2.getInt(r1, r9)
        L_0x071e:
            int r6 = com.google.android.gms.internal.ads.zzbav.g(r14, r6)
            goto L_0x06bd
        L_0x0723:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06be
        L_0x0727:
            java.lang.Object r6 = r2.getObject(r1, r9)
        L_0x072b:
            com.google.android.gms.internal.ads.zzbah r6 = (com.google.android.gms.internal.ads.zzbah) r6
            int r6 = com.google.android.gms.internal.ads.zzbav.c(r14, r6)
            goto L_0x06bd
        L_0x0732:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06be
        L_0x0736:
            java.lang.Object r6 = r2.getObject(r1, r9)
            com.google.android.gms.internal.ads.zd r9 = r0.a(r3)
            int r6 = com.google.android.gms.internal.ads.zf.a(r14, r6, r9)
            goto L_0x06bd
        L_0x0744:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06be
            java.lang.Object r6 = r2.getObject(r1, r9)
            boolean r9 = r6 instanceof com.google.android.gms.internal.ads.zzbah
            if (r9 == 0) goto L_0x0751
        L_0x0750:
            goto L_0x072b
        L_0x0751:
            java.lang.String r6 = (java.lang.String) r6
            int r6 = com.google.android.gms.internal.ads.zzbav.b(r14, r6)
            goto L_0x06bd
        L_0x0759:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06be
        L_0x075d:
            int r6 = com.google.android.gms.internal.ads.zzbav.b(r14, r7)
            goto L_0x06bd
        L_0x0763:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06be
            r6 = 0
            int r9 = com.google.android.gms.internal.ads.zzbav.i(r14, r6)
            int r4 = r4 + r9
            goto L_0x06bf
        L_0x076f:
            r6 = 0
            r9 = r12 & r16
            if (r9 == 0) goto L_0x06bf
            r9 = 0
            int r11 = com.google.android.gms.internal.ads.zzbav.g(r14, r9)
            int r4 = r4 + r11
            r18 = r9
            goto L_0x07ae
        L_0x077e:
            r6 = 0
            r18 = 0
            r11 = r12 & r16
            if (r11 == 0) goto L_0x07ae
            int r9 = r2.getInt(r1, r9)
            int r9 = com.google.android.gms.internal.ads.zzbav.f(r14, r9)
            goto L_0x07ad
        L_0x078e:
            r6 = 0
            r18 = 0
            r11 = r12 & r16
            if (r11 == 0) goto L_0x07ae
            long r9 = r2.getLong(r1, r9)
            int r9 = com.google.android.gms.internal.ads.zzbav.e(r14, r9)
            goto L_0x07ad
        L_0x079e:
            r6 = 0
            r18 = 0
            r11 = r12 & r16
            if (r11 == 0) goto L_0x07ae
            long r9 = r2.getLong(r1, r9)
            int r9 = com.google.android.gms.internal.ads.zzbav.d(r14, r9)
        L_0x07ad:
            int r4 = r4 + r9
        L_0x07ae:
            r9 = 0
            goto L_0x07bd
        L_0x07b0:
            r6 = 0
            r18 = 0
            r9 = r12 & r16
            if (r9 == 0) goto L_0x07ae
            r9 = 0
            int r10 = com.google.android.gms.internal.ads.zzbav.b(r14, r9)
            int r4 = r4 + r10
        L_0x07bd:
            r10 = 0
            goto L_0x07cf
        L_0x07c0:
            r6 = 0
            r9 = 0
            r18 = 0
            r10 = r12 & r16
            if (r10 == 0) goto L_0x07bd
            r10 = 0
            int r13 = com.google.android.gms.internal.ads.zzbav.b(r14, r10)
            int r4 = r4 + r13
        L_0x07cf:
            int r3 = r3 + 4
            r11 = r6
            r6 = r9
            r9 = r18
            goto L_0x03bf
        L_0x07d7:
            com.google.android.gms.internal.ads.zv<?, ?> r2 = r0.q
            int r2 = a(r2, (T) r1)
            int r4 = r4 + r2
            boolean r2 = r0.h
            if (r2 == 0) goto L_0x07ed
            com.google.android.gms.internal.ads.wy<?> r2 = r0.r
            com.google.android.gms.internal.ads.xb r1 = r2.a(r1)
            int r1 = r1.h()
            int r4 = r4 + r1
        L_0x07ed:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yo.b(java.lang.Object):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0037, code lost:
        com.google.android.gms.internal.ads.aab.a((java.lang.Object) r7, r2, com.google.android.gms.internal.ads.aab.f(r8, r2));
        b(r7, r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008f, code lost:
        com.google.android.gms.internal.ads.aab.a((java.lang.Object) r7, r2, com.google.android.gms.internal.ads.aab.f(r8, r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b9, code lost:
        com.google.android.gms.internal.ads.aab.a((java.lang.Object) r7, r2, com.google.android.gms.internal.ads.aab.a((java.lang.Object) r8, r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ce, code lost:
        com.google.android.gms.internal.ads.aab.a((java.lang.Object) r7, r2, com.google.android.gms.internal.ads.aab.b(r8, r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f1, code lost:
        b(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f4, code lost:
        r0 = r0 + 4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(T r7, T r8) {
        /*
            r6 = this;
            if (r8 != 0) goto L_0x0008
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            r7.<init>()
            throw r7
        L_0x0008:
            r0 = 0
        L_0x0009:
            int[] r1 = r6.b
            int r1 = r1.length
            if (r0 >= r1) goto L_0x00f8
            int r1 = r6.d(r0)
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r1
            long r2 = (long) r2
            int[] r4 = r6.b
            r4 = r4[r0]
            r5 = 267386880(0xff00000, float:2.3665827E-29)
            r1 = r1 & r5
            int r1 = r1 >>> 20
            switch(r1) {
                case 0: goto L_0x00e4;
                case 1: goto L_0x00d6;
                case 2: goto L_0x00c8;
                case 3: goto L_0x00c1;
                case 4: goto L_0x00b3;
                case 5: goto L_0x00ac;
                case 6: goto L_0x00a5;
                case 7: goto L_0x0097;
                case 8: goto L_0x0089;
                case 9: goto L_0x0084;
                case 10: goto L_0x007d;
                case 11: goto L_0x0076;
                case 12: goto L_0x006f;
                case 13: goto L_0x0068;
                case 14: goto L_0x0060;
                case 15: goto L_0x0059;
                case 16: goto L_0x0051;
                case 17: goto L_0x0084;
                case 18: goto L_0x004a;
                case 19: goto L_0x004a;
                case 20: goto L_0x004a;
                case 21: goto L_0x004a;
                case 22: goto L_0x004a;
                case 23: goto L_0x004a;
                case 24: goto L_0x004a;
                case 25: goto L_0x004a;
                case 26: goto L_0x004a;
                case 27: goto L_0x004a;
                case 28: goto L_0x004a;
                case 29: goto L_0x004a;
                case 30: goto L_0x004a;
                case 31: goto L_0x004a;
                case 32: goto L_0x004a;
                case 33: goto L_0x004a;
                case 34: goto L_0x004a;
                case 35: goto L_0x004a;
                case 36: goto L_0x004a;
                case 37: goto L_0x004a;
                case 38: goto L_0x004a;
                case 39: goto L_0x004a;
                case 40: goto L_0x004a;
                case 41: goto L_0x004a;
                case 42: goto L_0x004a;
                case 43: goto L_0x004a;
                case 44: goto L_0x004a;
                case 45: goto L_0x004a;
                case 46: goto L_0x004a;
                case 47: goto L_0x004a;
                case 48: goto L_0x004a;
                case 49: goto L_0x004a;
                case 50: goto L_0x0043;
                case 51: goto L_0x0031;
                case 52: goto L_0x0031;
                case 53: goto L_0x0031;
                case 54: goto L_0x0031;
                case 55: goto L_0x0031;
                case 56: goto L_0x0031;
                case 57: goto L_0x0031;
                case 58: goto L_0x0031;
                case 59: goto L_0x0031;
                case 60: goto L_0x002c;
                case 61: goto L_0x0025;
                case 62: goto L_0x0025;
                case 63: goto L_0x0025;
                case 64: goto L_0x0025;
                case 65: goto L_0x0025;
                case 66: goto L_0x0025;
                case 67: goto L_0x0025;
                case 68: goto L_0x002c;
                default: goto L_0x0023;
            }
        L_0x0023:
            goto L_0x00f4
        L_0x0025:
            boolean r1 = r6.a((T) r8, r4, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x0037
        L_0x002c:
            r6.b((T) r7, (T) r8, r0)
            goto L_0x00f4
        L_0x0031:
            boolean r1 = r6.a((T) r8, r4, r0)
            if (r1 == 0) goto L_0x00f4
        L_0x0037:
            java.lang.Object r1 = com.google.android.gms.internal.ads.aab.f(r8, r2)
            com.google.android.gms.internal.ads.aab.a(r7, r2, r1)
            r6.b((T) r7, r4, r0)
            goto L_0x00f4
        L_0x0043:
            com.google.android.gms.internal.ads.yf r1 = r6.s
            com.google.android.gms.internal.ads.zf.a(r1, r7, r8, r2)
            goto L_0x00f4
        L_0x004a:
            com.google.android.gms.internal.ads.xv r1 = r6.p
            r1.a(r7, r8, r2)
            goto L_0x00f4
        L_0x0051:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x00ce
        L_0x0059:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x0075
        L_0x0060:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x00ce
        L_0x0068:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x0075
        L_0x006f:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
        L_0x0075:
            goto L_0x00b9
        L_0x0076:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x00b9
        L_0x007d:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x008f
        L_0x0084:
            r6.a((T) r7, (T) r8, r0)
            goto L_0x00f4
        L_0x0089:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
        L_0x008f:
            java.lang.Object r1 = com.google.android.gms.internal.ads.aab.f(r8, r2)
            com.google.android.gms.internal.ads.aab.a(r7, r2, r1)
            goto L_0x00f1
        L_0x0097:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            boolean r1 = com.google.android.gms.internal.ads.aab.c(r8, r2)
            com.google.android.gms.internal.ads.aab.a(r7, r2, r1)
            goto L_0x00f1
        L_0x00a5:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x00b9
        L_0x00ac:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x00ce
        L_0x00b3:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
        L_0x00b9:
            int r1 = com.google.android.gms.internal.ads.aab.a(r8, r2)
            com.google.android.gms.internal.ads.aab.a(r7, r2, r1)
            goto L_0x00f1
        L_0x00c1:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x00ce
        L_0x00c8:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
        L_0x00ce:
            long r4 = com.google.android.gms.internal.ads.aab.b(r8, r2)
            com.google.android.gms.internal.ads.aab.a(r7, r2, r4)
            goto L_0x00f1
        L_0x00d6:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            float r1 = com.google.android.gms.internal.ads.aab.d(r8, r2)
            com.google.android.gms.internal.ads.aab.a(r7, r2, r1)
            goto L_0x00f1
        L_0x00e4:
            boolean r1 = r6.a((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            double r4 = com.google.android.gms.internal.ads.aab.e(r8, r2)
            com.google.android.gms.internal.ads.aab.a(r7, r2, r4)
        L_0x00f1:
            r6.b((T) r7, r0)
        L_0x00f4:
            int r0 = r0 + 4
            goto L_0x0009
        L_0x00f8:
            boolean r0 = r6.j
            if (r0 != 0) goto L_0x010a
            com.google.android.gms.internal.ads.zv<?, ?> r0 = r6.q
            com.google.android.gms.internal.ads.zf.a(r0, r7, r8)
            boolean r0 = r6.h
            if (r0 == 0) goto L_0x010a
            com.google.android.gms.internal.ads.wy<?> r0 = r6.r
            com.google.android.gms.internal.ads.zf.a(r0, r7, r8)
        L_0x010a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yo.b(java.lang.Object, java.lang.Object):void");
    }

    public final void c(T t) {
        if (this.m != null) {
            for (int d2 : this.m) {
                long d3 = (long) (d(d2) & 1048575);
                Object f2 = aab.f(t, d3);
                if (f2 != null) {
                    aab.a((Object) t, d3, this.s.d(f2));
                }
            }
        }
        if (this.n != null) {
            for (int i2 : this.n) {
                this.p.b(t, (long) i2);
            }
        }
        this.q.d(t);
        if (this.h) {
            this.r.c(t);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:92:0x011a, code lost:
        continue;
     */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0108 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x011a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean d(T r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            int[] r2 = r0.l
            r3 = 1
            if (r2 == 0) goto L_0x0133
            int[] r2 = r0.l
            int r2 = r2.length
            if (r2 != 0) goto L_0x0010
            goto L_0x0133
        L_0x0010:
            r2 = -1
            int[] r4 = r0.l
            r5 = 0
            int r6 = r4.length
            r7 = r2
            r2 = r5
            r8 = r2
        L_0x0018:
            if (r2 >= r6) goto L_0x0120
            r9 = r4[r2]
            int r10 = r0.g(r9)
            int r11 = r0.d(r10)
            boolean r12 = r0.j
            r13 = 1048575(0xfffff, float:1.469367E-39)
            if (r12 != 0) goto L_0x0046
            int[] r12 = r0.b
            int r14 = r10 + 2
            r12 = r12[r14]
            r14 = r12 & r13
            int r12 = r12 >>> 20
            int r12 = r3 << r12
            if (r14 == r7) goto L_0x0044
            sun.misc.Unsafe r7 = a
            r15 = r4
            long r3 = (long) r14
            int r3 = r7.getInt(r1, r3)
            r8 = r3
            r7 = r14
            goto L_0x0048
        L_0x0044:
            r15 = r4
            goto L_0x0048
        L_0x0046:
            r15 = r4
            r12 = r5
        L_0x0048:
            r3 = 268435456(0x10000000, float:2.5243549E-29)
            r3 = r3 & r11
            if (r3 == 0) goto L_0x004f
            r3 = 1
            goto L_0x0050
        L_0x004f:
            r3 = r5
        L_0x0050:
            if (r3 == 0) goto L_0x0059
            boolean r3 = r0.a((T) r1, r10, r8, r12)
            if (r3 != 0) goto L_0x0059
            return r5
        L_0x0059:
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = r3 & r11
            int r3 = r3 >>> 20
            r4 = 9
            if (r3 == r4) goto L_0x0109
            r4 = 17
            if (r3 == r4) goto L_0x0109
            r4 = 27
            if (r3 == r4) goto L_0x00dc
            r4 = 60
            if (r3 == r4) goto L_0x00cb
            r4 = 68
            if (r3 == r4) goto L_0x00cb
            switch(r3) {
                case 49: goto L_0x00dc;
                case 50: goto L_0x0077;
                default: goto L_0x0075;
            }
        L_0x0075:
            goto L_0x011a
        L_0x0077:
            com.google.android.gms.internal.ads.yf r3 = r0.s
            r4 = r11 & r13
            long r11 = (long) r4
            java.lang.Object r4 = com.google.android.gms.internal.ads.aab.f(r1, r11)
            java.util.Map r3 = r3.b(r4)
            boolean r4 = r3.isEmpty()
            if (r4 != 0) goto L_0x00c7
            java.lang.Object r4 = r0.b(r10)
            com.google.android.gms.internal.ads.yf r9 = r0.s
            com.google.android.gms.internal.ads.ye r4 = r9.f(r4)
            com.google.android.gms.internal.ads.zzbes r4 = r4.c
            com.google.android.gms.internal.ads.zzbex r4 = r4.zzagl()
            com.google.android.gms.internal.ads.zzbex r9 = com.google.android.gms.internal.ads.zzbex.MESSAGE
            if (r4 != r9) goto L_0x00c7
            r4 = 0
            java.util.Collection r3 = r3.values()
            java.util.Iterator r3 = r3.iterator()
        L_0x00a7:
            boolean r9 = r3.hasNext()
            if (r9 == 0) goto L_0x00c7
            java.lang.Object r9 = r3.next()
            if (r4 != 0) goto L_0x00bf
            com.google.android.gms.internal.ads.yx r4 = com.google.android.gms.internal.ads.yx.a()
            java.lang.Class r10 = r9.getClass()
            com.google.android.gms.internal.ads.zd r4 = r4.a(r10)
        L_0x00bf:
            boolean r9 = r4.d(r9)
            if (r9 != 0) goto L_0x00a7
            r3 = r5
            goto L_0x00c8
        L_0x00c7:
            r3 = 1
        L_0x00c8:
            if (r3 != 0) goto L_0x011a
            return r5
        L_0x00cb:
            boolean r3 = r0.a((T) r1, r9, r10)
            if (r3 == 0) goto L_0x011a
            com.google.android.gms.internal.ads.zd r3 = r0.a(r10)
            boolean r3 = a(r1, r11, r3)
            if (r3 != 0) goto L_0x011a
            return r5
        L_0x00dc:
            r3 = r11 & r13
            long r3 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.ads.aab.f(r1, r3)
            java.util.List r3 = (java.util.List) r3
            boolean r4 = r3.isEmpty()
            if (r4 != 0) goto L_0x0105
            com.google.android.gms.internal.ads.zd r4 = r0.a(r10)
            r9 = r5
        L_0x00f0:
            int r10 = r3.size()
            if (r9 >= r10) goto L_0x0105
            java.lang.Object r10 = r3.get(r9)
            boolean r10 = r4.d(r10)
            if (r10 != 0) goto L_0x0102
            r3 = r5
            goto L_0x0106
        L_0x0102:
            int r9 = r9 + 1
            goto L_0x00f0
        L_0x0105:
            r3 = 1
        L_0x0106:
            if (r3 != 0) goto L_0x011a
            return r5
        L_0x0109:
            boolean r3 = r0.a((T) r1, r10, r8, r12)
            if (r3 == 0) goto L_0x011a
            com.google.android.gms.internal.ads.zd r3 = r0.a(r10)
            boolean r3 = a(r1, r11, r3)
            if (r3 != 0) goto L_0x011a
            return r5
        L_0x011a:
            int r2 = r2 + 1
            r4 = r15
            r3 = 1
            goto L_0x0018
        L_0x0120:
            boolean r2 = r0.h
            if (r2 == 0) goto L_0x0131
            com.google.android.gms.internal.ads.wy<?> r2 = r0.r
            com.google.android.gms.internal.ads.xb r1 = r2.a(r1)
            boolean r1 = r1.g()
            if (r1 != 0) goto L_0x0131
            return r5
        L_0x0131:
            r1 = 1
            return r1
        L_0x0133:
            r1 = r3
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yo.d(java.lang.Object):boolean");
    }
}
