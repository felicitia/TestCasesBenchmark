package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sun.misc.Unsafe;

final class zzdx<T> implements zzej<T> {
    private static final int[] zzon = new int[0];
    private static final Unsafe zzoo = zzfh.zzfx();
    private final int[] zzop;
    private final Object[] zzoq;
    private final int zzor;
    private final int zzos;
    private final zzdt zzot;
    private final boolean zzou;
    private final boolean zzov;
    private final boolean zzow;
    private final boolean zzox;
    private final int[] zzoy;
    private final int zzoz;
    private final int zzpa;
    private final zzea zzpb;
    private final zzdd zzpc;
    private final zzfb<?, ?> zzpd;
    private final zzca<?> zzpe;
    private final zzdo zzpf;

    private zzdx(int[] iArr, Object[] objArr, int i, int i2, zzdt zzdt, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzea zzea, zzdd zzdd, zzfb<?, ?> zzfb, zzca<?> zzca, zzdo zzdo) {
        this.zzop = iArr;
        this.zzoq = objArr;
        this.zzor = i;
        this.zzos = i2;
        this.zzov = zzdt instanceof zzcm;
        this.zzow = z;
        this.zzou = zzca != null && zzca.zze(zzdt);
        this.zzox = false;
        this.zzoy = iArr2;
        this.zzoz = i3;
        this.zzpa = i4;
        this.zzpb = zzea;
        this.zzpc = zzdd;
        this.zzpd = zzfb;
        this.zzpe = zzca;
        this.zzot = zzdt;
        this.zzpf = zzdo;
    }

    private static boolean zzar(int i) {
        return (i & 536870912) != 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:167:0x03ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.gms.internal.firebase-perf.zzdx<T> zza(java.lang.Class<T> r40, com.google.android.gms.internal.firebase-perf.zzdr r41, com.google.android.gms.internal.firebase-perf.zzea r42, com.google.android.gms.internal.firebase-perf.zzdd r43, com.google.android.gms.internal.firebase-perf.zzfb<?, ?> r44, com.google.android.gms.internal.firebase-perf.zzca<?> r45, com.google.android.gms.internal.firebase-perf.zzdo r46) {
        /*
            r0 = r41
            boolean r1 = r0 instanceof com.google.android.gms.internal.firebase-perf.zzeh
            if (r1 == 0) goto L_0x0484
            com.google.android.gms.internal.firebase-perf.zzeh r0 = (com.google.android.gms.internal.firebase-perf.zzeh) r0
            int r1 = r0.zzet()
            int r2 = com.google.android.gms.internal.firebase-perf.zzcm.zze.zzms
            r3 = 0
            r4 = 1
            if (r1 != r2) goto L_0x0014
            r11 = 1
            goto L_0x0015
        L_0x0014:
            r11 = 0
        L_0x0015:
            java.lang.String r1 = r0.zzfc()
            int r2 = r1.length()
            char r5 = r1.charAt(r3)
            r7 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r7) goto L_0x003f
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            r8 = r5
            r5 = 1
            r9 = 13
        L_0x002c:
            int r10 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r7) goto L_0x003c
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r9
            r8 = r8 | r5
            int r9 = r9 + 13
            r5 = r10
            goto L_0x002c
        L_0x003c:
            int r5 = r5 << r9
            r5 = r5 | r8
            goto L_0x0040
        L_0x003f:
            r10 = 1
        L_0x0040:
            int r8 = r10 + 1
            char r9 = r1.charAt(r10)
            if (r9 < r7) goto L_0x005f
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x004c:
            int r12 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x005c
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r10
            r9 = r9 | r8
            int r10 = r10 + 13
            r8 = r12
            goto L_0x004c
        L_0x005c:
            int r8 = r8 << r10
            r9 = r9 | r8
            r8 = r12
        L_0x005f:
            if (r9 != 0) goto L_0x006d
            int[] r9 = zzon
            r16 = r9
            r9 = 0
            r10 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            goto L_0x019e
        L_0x006d:
            int r9 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x008c
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0079:
            int r12 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r7) goto L_0x0089
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r10
            r8 = r8 | r9
            int r10 = r10 + 13
            r9 = r12
            goto L_0x0079
        L_0x0089:
            int r9 = r9 << r10
            r8 = r8 | r9
            r9 = r12
        L_0x008c:
            int r10 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r7) goto L_0x00ab
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x0098:
            int r13 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r7) goto L_0x00a8
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r12
            r9 = r9 | r10
            int r12 = r12 + 13
            r10 = r13
            goto L_0x0098
        L_0x00a8:
            int r10 = r10 << r12
            r9 = r9 | r10
            r10 = r13
        L_0x00ab:
            int r12 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r7) goto L_0x00ca
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00b7:
            int r14 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r7) goto L_0x00c7
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r10 = r10 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00b7
        L_0x00c7:
            int r12 = r12 << r13
            r10 = r10 | r12
            r12 = r14
        L_0x00ca:
            int r13 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r7) goto L_0x00e9
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00d6:
            int r15 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r7) goto L_0x00e6
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x00d6
        L_0x00e6:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
        L_0x00e9:
            int r14 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r7) goto L_0x010a
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x00f5:
            int r16 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r7) goto L_0x0106
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r15
            r13 = r13 | r14
            int r15 = r15 + 13
            r14 = r16
            goto L_0x00f5
        L_0x0106:
            int r14 = r14 << r15
            r13 = r13 | r14
            r14 = r16
        L_0x010a:
            int r15 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r7) goto L_0x012d
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x0116:
            int r17 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x0128
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r14 = r14 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x0116
        L_0x0128:
            int r15 = r15 << r16
            r14 = r14 | r15
            r15 = r17
        L_0x012d:
            int r16 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x0159
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            r17 = 13
            r38 = r16
            r16 = r15
            r15 = r38
        L_0x013f:
            int r18 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x0152
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r17
            r16 = r16 | r15
            int r17 = r17 + 13
            r15 = r18
            goto L_0x013f
        L_0x0152:
            int r15 = r15 << r17
            r15 = r16 | r15
            r3 = r18
            goto L_0x015b
        L_0x0159:
            r3 = r16
        L_0x015b:
            int r16 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r7) goto L_0x0186
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r17 = 13
            r38 = r16
            r16 = r3
            r3 = r38
        L_0x016d:
            int r18 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r7) goto L_0x0180
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r17
            r16 = r16 | r3
            int r17 = r17 + 13
            r3 = r18
            goto L_0x016d
        L_0x0180:
            int r3 = r3 << r17
            r3 = r16 | r3
            r16 = r18
        L_0x0186:
            int r17 = r3 + r14
            int r15 = r17 + r15
            int[] r15 = new int[r15]
            int r17 = r8 << 1
            int r9 = r17 + r9
            r38 = r14
            r14 = r3
            r3 = r38
            r39 = r9
            r9 = r8
            r8 = r16
            r16 = r15
            r15 = r39
        L_0x019e:
            sun.misc.Unsafe r6 = zzoo
            java.lang.Object[] r17 = r0.zzfd()
            com.google.android.gms.internal.firebase-perf.zzdt r7 = r0.zzev()
            java.lang.Class r7 = r7.getClass()
            r22 = r8
            int r8 = r13 * 3
            int[] r8 = new int[r8]
            int r13 = r13 << r4
            java.lang.Object[] r13 = new java.lang.Object[r13]
            int r3 = r3 + r14
            r23 = r14
            r20 = r15
            r15 = r22
            r18 = 0
            r19 = 0
            r22 = r3
        L_0x01c2:
            if (r15 >= r2) goto L_0x045a
            int r24 = r15 + 1
            char r15 = r1.charAt(r15)
            r4 = 55296(0xd800, float:7.7486E-41)
            if (r15 < r4) goto L_0x01f6
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            r25 = 13
            r38 = r24
            r24 = r15
            r15 = r38
        L_0x01d9:
            int r26 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r4) goto L_0x01ef
            r4 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r25
            r24 = r24 | r4
            int r25 = r25 + 13
            r15 = r26
            r4 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01d9
        L_0x01ef:
            int r4 = r15 << r25
            r15 = r24 | r4
            r4 = r26
            goto L_0x01f8
        L_0x01f6:
            r4 = r24
        L_0x01f8:
            int r24 = r4 + 1
            char r4 = r1.charAt(r4)
            r27 = r2
            r2 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r2) goto L_0x022c
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r25 = 13
            r38 = r24
            r24 = r4
            r4 = r38
        L_0x020f:
            int r26 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r2) goto L_0x0225
            r2 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r25
            r24 = r24 | r2
            int r25 = r25 + 13
            r4 = r26
            r2 = 55296(0xd800, float:7.7486E-41)
            goto L_0x020f
        L_0x0225:
            int r2 = r4 << r25
            r4 = r24 | r2
            r2 = r26
            goto L_0x022e
        L_0x022c:
            r2 = r24
        L_0x022e:
            r28 = r3
            r3 = r4 & 255(0xff, float:3.57E-43)
            r29 = r14
            r14 = r4 & 1024(0x400, float:1.435E-42)
            if (r14 == 0) goto L_0x023e
            int r14 = r18 + 1
            r16[r18] = r19
            r18 = r14
        L_0x023e:
            com.google.android.gms.internal.firebase-perf.zzcg r14 = com.google.android.gms.internal.firebase-perf.zzcg.MAP
            int r14 = r14.id()
            if (r3 <= r14) goto L_0x0301
            int r14 = r2 + 1
            char r2 = r1.charAt(r2)
            r30 = r14
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r14) goto L_0x0276
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r24 = r2
            r2 = r30
            r25 = 13
        L_0x025b:
            int r26 = r2 + 1
            char r2 = r1.charAt(r2)
            if (r2 < r14) goto L_0x0271
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r25
            r24 = r24 | r2
            int r25 = r25 + 13
            r2 = r26
            r14 = 55296(0xd800, float:7.7486E-41)
            goto L_0x025b
        L_0x0271:
            int r2 = r2 << r25
            r2 = r24 | r2
            goto L_0x0278
        L_0x0276:
            r26 = r30
        L_0x0278:
            com.google.android.gms.internal.firebase-perf.zzcg r14 = com.google.android.gms.internal.firebase-perf.zzcg.MESSAGE
            int r14 = r14.id()
            int r14 = r14 + 51
            if (r3 == r14) goto L_0x02b1
            com.google.android.gms.internal.firebase-perf.zzcg r14 = com.google.android.gms.internal.firebase-perf.zzcg.GROUP
            int r14 = r14.id()
            int r14 = r14 + 51
            if (r3 != r14) goto L_0x028d
            goto L_0x02b1
        L_0x028d:
            com.google.android.gms.internal.firebase-perf.zzcg r14 = com.google.android.gms.internal.firebase-perf.zzcg.ENUM
            int r14 = r14.id()
            int r14 = r14 + 51
            if (r3 != r14) goto L_0x02ab
            r14 = r5 & 1
            r31 = r11
            r11 = 1
            if (r14 != r11) goto L_0x02ad
            int r14 = r19 / 3
            int r14 = r14 << r11
            int r14 = r14 + r11
            int r11 = r20 + 1
            r20 = r17[r20]
            r13[r14] = r20
            r24 = r11
            goto L_0x02af
        L_0x02ab:
            r31 = r11
        L_0x02ad:
            r24 = r20
        L_0x02af:
            r14 = 1
            goto L_0x02be
        L_0x02b1:
            r31 = r11
            int r11 = r19 / 3
            r14 = 1
            int r11 = r11 << r14
            int r11 = r11 + r14
            int r24 = r20 + 1
            r20 = r17[r20]
            r13[r11] = r20
        L_0x02be:
            int r2 = r2 << r14
            r11 = r17[r2]
            boolean r14 = r11 instanceof java.lang.reflect.Field
            if (r14 == 0) goto L_0x02ca
            java.lang.reflect.Field r11 = (java.lang.reflect.Field) r11
        L_0x02c7:
            r32 = r12
            goto L_0x02d3
        L_0x02ca:
            java.lang.String r11 = (java.lang.String) r11
            java.lang.reflect.Field r11 = zza(r7, r11)
            r17[r2] = r11
            goto L_0x02c7
        L_0x02d3:
            long r11 = r6.objectFieldOffset(r11)
            int r11 = (int) r11
            int r2 = r2 + 1
            r12 = r17[r2]
            boolean r14 = r12 instanceof java.lang.reflect.Field
            if (r14 == 0) goto L_0x02e5
            java.lang.reflect.Field r12 = (java.lang.reflect.Field) r12
        L_0x02e2:
            r33 = r11
            goto L_0x02ee
        L_0x02e5:
            java.lang.String r12 = (java.lang.String) r12
            java.lang.reflect.Field r12 = zza(r7, r12)
            r17[r2] = r12
            goto L_0x02e2
        L_0x02ee:
            long r11 = r6.objectFieldOffset(r12)
            int r2 = (int) r11
            r36 = r9
            r34 = r10
            r20 = r24
            r37 = r26
            r11 = r33
            r9 = r2
            r2 = 0
            goto L_0x0415
        L_0x0301:
            r31 = r11
            r32 = r12
            int r11 = r20 + 1
            r12 = r17[r20]
            java.lang.String r12 = (java.lang.String) r12
            java.lang.reflect.Field r12 = zza(r7, r12)
            com.google.android.gms.internal.firebase-perf.zzcg r14 = com.google.android.gms.internal.firebase-perf.zzcg.MESSAGE
            int r14 = r14.id()
            if (r3 == r14) goto L_0x03a1
            com.google.android.gms.internal.firebase-perf.zzcg r14 = com.google.android.gms.internal.firebase-perf.zzcg.GROUP
            int r14 = r14.id()
            if (r3 != r14) goto L_0x0321
            goto L_0x03a1
        L_0x0321:
            com.google.android.gms.internal.firebase-perf.zzcg r14 = com.google.android.gms.internal.firebase-perf.zzcg.MESSAGE_LIST
            int r14 = r14.id()
            if (r3 == r14) goto L_0x0391
            com.google.android.gms.internal.firebase-perf.zzcg r14 = com.google.android.gms.internal.firebase-perf.zzcg.GROUP_LIST
            int r14 = r14.id()
            if (r3 != r14) goto L_0x0332
            goto L_0x0391
        L_0x0332:
            com.google.android.gms.internal.firebase-perf.zzcg r14 = com.google.android.gms.internal.firebase-perf.zzcg.ENUM
            int r14 = r14.id()
            if (r3 == r14) goto L_0x037f
            com.google.android.gms.internal.firebase-perf.zzcg r14 = com.google.android.gms.internal.firebase-perf.zzcg.ENUM_LIST
            int r14 = r14.id()
            if (r3 == r14) goto L_0x037f
            com.google.android.gms.internal.firebase-perf.zzcg r14 = com.google.android.gms.internal.firebase-perf.zzcg.ENUM_LIST_PACKED
            int r14 = r14.id()
            if (r3 != r14) goto L_0x034b
            goto L_0x037f
        L_0x034b:
            com.google.android.gms.internal.firebase-perf.zzcg r14 = com.google.android.gms.internal.firebase-perf.zzcg.MAP
            int r14 = r14.id()
            if (r3 != r14) goto L_0x037b
            int r14 = r23 + 1
            r16[r23] = r19
            int r20 = r19 / 3
            r23 = 1
            int r20 = r20 << 1
            int r23 = r11 + 1
            r11 = r17[r11]
            r13[r20] = r11
            r11 = r4 & 2048(0x800, float:2.87E-42)
            if (r11 == 0) goto L_0x0374
            int r20 = r20 + 1
            int r11 = r23 + 1
            r23 = r17[r23]
            r13[r20] = r23
            r34 = r10
            r35 = r11
            goto L_0x0378
        L_0x0374:
            r34 = r10
            r35 = r23
        L_0x0378:
            r23 = r14
            goto L_0x03b0
        L_0x037b:
            r34 = r10
            r10 = 1
            goto L_0x03ae
        L_0x037f:
            r14 = r5 & 1
            r34 = r10
            r10 = 1
            if (r14 != r10) goto L_0x03ae
            int r14 = r19 / 3
            int r14 = r14 << r10
            int r14 = r14 + r10
            int r20 = r11 + 1
            r11 = r17[r11]
            r13[r14] = r11
            goto L_0x039e
        L_0x0391:
            r34 = r10
            r10 = 1
            int r14 = r19 / 3
            int r14 = r14 << r10
            int r14 = r14 + r10
            int r20 = r11 + 1
            r11 = r17[r11]
            r13[r14] = r11
        L_0x039e:
            r35 = r20
            goto L_0x03b0
        L_0x03a1:
            r34 = r10
            r10 = 1
            int r14 = r19 / 3
            int r14 = r14 << r10
            int r14 = r14 + r10
            java.lang.Class r20 = r12.getType()
            r13[r14] = r20
        L_0x03ae:
            r35 = r11
        L_0x03b0:
            long r10 = r6.objectFieldOffset(r12)
            int r11 = (int) r10
            r10 = r5 & 1
            r12 = 1
            if (r10 != r12) goto L_0x040d
            com.google.android.gms.internal.firebase-perf.zzcg r10 = com.google.android.gms.internal.firebase-perf.zzcg.GROUP
            int r10 = r10.id()
            if (r3 > r10) goto L_0x040d
            int r10 = r2 + 1
            char r2 = r1.charAt(r2)
            r12 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r12) goto L_0x03e6
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x03d1:
            int r20 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r12) goto L_0x03e2
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r14
            r2 = r2 | r10
            int r14 = r14 + 13
            r10 = r20
            goto L_0x03d1
        L_0x03e2:
            int r10 = r10 << r14
            r2 = r2 | r10
            r10 = r20
        L_0x03e6:
            r14 = 1
            int r20 = r9 << 1
            int r21 = r2 / 32
            int r20 = r20 + r21
            r12 = r17[r20]
            boolean r14 = r12 instanceof java.lang.reflect.Field
            if (r14 == 0) goto L_0x03fa
            java.lang.reflect.Field r12 = (java.lang.reflect.Field) r12
        L_0x03f5:
            r36 = r9
            r37 = r10
            goto L_0x0403
        L_0x03fa:
            java.lang.String r12 = (java.lang.String) r12
            java.lang.reflect.Field r12 = zza(r7, r12)
            r17[r20] = r12
            goto L_0x03f5
        L_0x0403:
            long r9 = r6.objectFieldOffset(r12)
            int r9 = (int) r9
            int r2 = r2 % 32
            r20 = r35
            goto L_0x0415
        L_0x040d:
            r36 = r9
            r37 = r2
            r20 = r35
            r2 = 0
            r9 = 0
        L_0x0415:
            r10 = 18
            if (r3 < r10) goto L_0x0423
            r10 = 49
            if (r3 > r10) goto L_0x0423
            int r10 = r22 + 1
            r16[r22] = r11
            r22 = r10
        L_0x0423:
            int r10 = r19 + 1
            r8[r19] = r15
            int r12 = r10 + 1
            r14 = r4 & 512(0x200, float:7.175E-43)
            if (r14 == 0) goto L_0x0430
            r14 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x0431
        L_0x0430:
            r14 = 0
        L_0x0431:
            r4 = r4 & 256(0x100, float:3.59E-43)
            if (r4 == 0) goto L_0x0438
            r4 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x0439
        L_0x0438:
            r4 = 0
        L_0x0439:
            r4 = r4 | r14
            int r3 = r3 << 20
            r3 = r3 | r4
            r3 = r3 | r11
            r8[r10] = r3
            int r19 = r12 + 1
            int r2 = r2 << 20
            r2 = r2 | r9
            r8[r12] = r2
            r2 = r27
            r3 = r28
            r14 = r29
            r11 = r31
            r12 = r32
            r10 = r34
            r9 = r36
            r15 = r37
            r4 = 1
            goto L_0x01c2
        L_0x045a:
            r28 = r3
            r34 = r10
            r31 = r11
            r32 = r12
            r29 = r14
            com.google.android.gms.internal.firebase-perf.zzdx r1 = new com.google.android.gms.internal.firebase-perf.zzdx
            com.google.android.gms.internal.firebase-perf.zzdt r10 = r0.zzev()
            r12 = 0
            r5 = r1
            r6 = r8
            r7 = r13
            r8 = r34
            r9 = r32
            r13 = r16
            r15 = r28
            r16 = r42
            r17 = r43
            r18 = r44
            r19 = r45
            r20 = r46
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r1
        L_0x0484:
            com.google.android.gms.internal.firebase-perf.zzew r0 = (com.google.android.gms.internal.firebase-perf.zzew) r0
            r0.zzet()
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzdx.zza(java.lang.Class, com.google.android.gms.internal.firebase-perf.zzdr, com.google.android.gms.internal.firebase-perf.zzea, com.google.android.gms.internal.firebase-perf.zzdd, com.google.android.gms.internal.firebase-perf.zzfb, com.google.android.gms.internal.firebase-perf.zzca, com.google.android.gms.internal.firebase-perf.zzdo):com.google.android.gms.internal.firebase-perf.zzdx");
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    public final T newInstance() {
        return this.zzpb.newInstance(this.zzot);
    }

    /* JADX INFO: used method not loaded: com.google.android.gms.internal.firebase-perf.zzel.zzd(java.lang.Object, java.lang.Object):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006a, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzel.zzd(com.google.android.gms.internal.firebase-perf.zzfh.zzp(r10, r6), com.google.android.gms.internal.firebase-perf.zzfh.zzp(r11, r6)) != false) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007e, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzfh.zzl(r10, r6) == com.google.android.gms.internal.firebase-perf.zzfh.zzl(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0090, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzfh.zzk(r10, r6) == com.google.android.gms.internal.firebase-perf.zzfh.zzk(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a4, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzfh.zzl(r10, r6) == com.google.android.gms.internal.firebase-perf.zzfh.zzl(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b6, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzfh.zzk(r10, r6) == com.google.android.gms.internal.firebase-perf.zzfh.zzk(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c8, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzfh.zzk(r10, r6) == com.google.android.gms.internal.firebase-perf.zzfh.zzk(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00da, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzfh.zzk(r10, r6) == com.google.android.gms.internal.firebase-perf.zzfh.zzk(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f0, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzel.zzd(com.google.android.gms.internal.firebase-perf.zzfh.zzp(r10, r6), com.google.android.gms.internal.firebase-perf.zzfh.zzp(r11, r6)) != false) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0106, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzel.zzd(com.google.android.gms.internal.firebase-perf.zzfh.zzp(r10, r6), com.google.android.gms.internal.firebase-perf.zzfh.zzp(r11, r6)) != false) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x011c, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzel.zzd(com.google.android.gms.internal.firebase-perf.zzfh.zzp(r10, r6), com.google.android.gms.internal.firebase-perf.zzfh.zzp(r11, r6)) != false) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x012e, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzfh.zzm(r10, r6) == com.google.android.gms.internal.firebase-perf.zzfh.zzm(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0140, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzfh.zzk(r10, r6) == com.google.android.gms.internal.firebase-perf.zzfh.zzk(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0154, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzfh.zzl(r10, r6) == com.google.android.gms.internal.firebase-perf.zzfh.zzl(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0165, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzfh.zzk(r10, r6) == com.google.android.gms.internal.firebase-perf.zzfh.zzk(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0178, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzfh.zzl(r10, r6) == com.google.android.gms.internal.firebase-perf.zzfh.zzl(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x018b, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzfh.zzl(r10, r6) == com.google.android.gms.internal.firebase-perf.zzfh.zzl(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x019c, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzfh.zzk(r10, r6) == com.google.android.gms.internal.firebase-perf.zzfh.zzk(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01af, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzfh.zzl(r10, r6) == com.google.android.gms.internal.firebase-perf.zzfh.zzl(r11, r6)) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01b1, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
        if (com.google.android.gms.internal.firebase-perf.zzel.zzd(com.google.android.gms.internal.firebase-perf.zzfh.zzp(r10, r6), com.google.android.gms.internal.firebase-perf.zzfh.zzp(r11, r6)) != false) goto L_0x01b2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(T r10, T r11) {
        /*
            r9 = this;
            int[] r0 = r9.zzop
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            r3 = 1
            if (r2 >= r0) goto L_0x01b9
            int r4 = r9.zzap(r2)
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r6 = r4 & r5
            long r6 = (long) r6
            r8 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = r4 & r8
            int r4 = r4 >>> 20
            switch(r4) {
                case 0: goto L_0x019f;
                case 1: goto L_0x018e;
                case 2: goto L_0x017b;
                case 3: goto L_0x0168;
                case 4: goto L_0x0157;
                case 5: goto L_0x0144;
                case 6: goto L_0x0132;
                case 7: goto L_0x0120;
                case 8: goto L_0x010a;
                case 9: goto L_0x00f4;
                case 10: goto L_0x00de;
                case 11: goto L_0x00cc;
                case 12: goto L_0x00ba;
                case 13: goto L_0x00a8;
                case 14: goto L_0x0094;
                case 15: goto L_0x0082;
                case 16: goto L_0x006e;
                case 17: goto L_0x0058;
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
            goto L_0x01b2
        L_0x001c:
            int r4 = r9.zzaq(r2)
            r4 = r4 & r5
            long r4 = (long) r4
            int r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r10, r4)
            int r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r11, r4)
            if (r8 != r4) goto L_0x01b1
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.firebase-perf.zzel.zzd(r4, r5)
            if (r4 != 0) goto L_0x01b2
            goto L_0x01b1
        L_0x003c:
            java.lang.Object r3 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r11, r6)
            boolean r3 = com.google.android.gms.internal.firebase-perf.zzel.zzd(r3, r4)
            goto L_0x01b2
        L_0x004a:
            java.lang.Object r3 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r11, r6)
            boolean r3 = com.google.android.gms.internal.firebase-perf.zzel.zzd(r3, r4)
            goto L_0x01b2
        L_0x0058:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.firebase-perf.zzel.zzd(r4, r5)
            if (r4 != 0) goto L_0x01b2
            goto L_0x01b1
        L_0x006e:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            long r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b2
            goto L_0x01b1
        L_0x0082:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            int r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r11, r6)
            if (r4 == r5) goto L_0x01b2
            goto L_0x01b1
        L_0x0094:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            long r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b2
            goto L_0x01b1
        L_0x00a8:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            int r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r11, r6)
            if (r4 == r5) goto L_0x01b2
            goto L_0x01b1
        L_0x00ba:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            int r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r11, r6)
            if (r4 == r5) goto L_0x01b2
            goto L_0x01b1
        L_0x00cc:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            int r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r11, r6)
            if (r4 == r5) goto L_0x01b2
            goto L_0x01b1
        L_0x00de:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.firebase-perf.zzel.zzd(r4, r5)
            if (r4 != 0) goto L_0x01b2
            goto L_0x01b1
        L_0x00f4:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.firebase-perf.zzel.zzd(r4, r5)
            if (r4 != 0) goto L_0x01b2
            goto L_0x01b1
        L_0x010a:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.firebase-perf.zzel.zzd(r4, r5)
            if (r4 != 0) goto L_0x01b2
            goto L_0x01b1
        L_0x0120:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            boolean r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzm(r10, r6)
            boolean r5 = com.google.android.gms.internal.firebase-perf.zzfh.zzm(r11, r6)
            if (r4 == r5) goto L_0x01b2
            goto L_0x01b1
        L_0x0132:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            int r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r11, r6)
            if (r4 == r5) goto L_0x01b2
            goto L_0x01b1
        L_0x0144:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            long r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b2
            goto L_0x01b1
        L_0x0157:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            int r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r11, r6)
            if (r4 == r5) goto L_0x01b2
            goto L_0x01b1
        L_0x0168:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            long r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b2
            goto L_0x01b1
        L_0x017b:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            long r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b2
            goto L_0x01b1
        L_0x018e:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            int r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r11, r6)
            if (r4 == r5) goto L_0x01b2
            goto L_0x01b1
        L_0x019f:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b1
            long r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b2
        L_0x01b1:
            r3 = 0
        L_0x01b2:
            if (r3 != 0) goto L_0x01b5
            return r1
        L_0x01b5:
            int r2 = r2 + 3
            goto L_0x0005
        L_0x01b9:
            com.google.android.gms.internal.firebase-perf.zzfb<?, ?> r0 = r9.zzpd
            java.lang.Object r0 = r0.zzp(r10)
            com.google.android.gms.internal.firebase-perf.zzfb<?, ?> r2 = r9.zzpd
            java.lang.Object r2 = r2.zzp(r11)
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x01cc
            return r1
        L_0x01cc:
            boolean r0 = r9.zzou
            if (r0 == 0) goto L_0x01e1
            com.google.android.gms.internal.firebase-perf.zzca<?> r0 = r9.zzpe
            com.google.android.gms.internal.firebase-perf.zzcd r10 = r0.zza(r10)
            com.google.android.gms.internal.firebase-perf.zzca<?> r0 = r9.zzpe
            com.google.android.gms.internal.firebase-perf.zzcd r11 = r0.zza(r11)
            boolean r10 = r10.equals(r11)
            return r10
        L_0x01e1:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzdx.equals(java.lang.Object, java.lang.Object):boolean");
    }

    public final int hashCode(T t) {
        int length = this.zzop.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int zzap = zzap(i2);
            int i3 = this.zzop[i2];
            long j = (long) (1048575 & zzap);
            int i4 = 37;
            switch ((zzap & 267386880) >>> 20) {
                case 0:
                    i = (i * 53) + zzco.zzu(Double.doubleToLongBits(zzfh.zzo(t, j)));
                    break;
                case 1:
                    i = (i * 53) + Float.floatToIntBits(zzfh.zzn(t, j));
                    break;
                case 2:
                    i = (i * 53) + zzco.zzu(zzfh.zzl(t, j));
                    break;
                case 3:
                    i = (i * 53) + zzco.zzu(zzfh.zzl(t, j));
                    break;
                case 4:
                    i = (i * 53) + zzfh.zzk(t, j);
                    break;
                case 5:
                    i = (i * 53) + zzco.zzu(zzfh.zzl(t, j));
                    break;
                case 6:
                    i = (i * 53) + zzfh.zzk(t, j);
                    break;
                case 7:
                    i = (i * 53) + zzco.zzf(zzfh.zzm(t, j));
                    break;
                case 8:
                    i = (i * 53) + ((String) zzfh.zzp(t, j)).hashCode();
                    break;
                case 9:
                    Object zzp = zzfh.zzp(t, j);
                    if (zzp != null) {
                        i4 = zzp.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 10:
                    i = (i * 53) + zzfh.zzp(t, j).hashCode();
                    break;
                case 11:
                    i = (i * 53) + zzfh.zzk(t, j);
                    break;
                case 12:
                    i = (i * 53) + zzfh.zzk(t, j);
                    break;
                case 13:
                    i = (i * 53) + zzfh.zzk(t, j);
                    break;
                case 14:
                    i = (i * 53) + zzco.zzu(zzfh.zzl(t, j));
                    break;
                case 15:
                    i = (i * 53) + zzfh.zzk(t, j);
                    break;
                case 16:
                    i = (i * 53) + zzco.zzu(zzfh.zzl(t, j));
                    break;
                case 17:
                    Object zzp2 = zzfh.zzp(t, j);
                    if (zzp2 != null) {
                        i4 = zzp2.hashCode();
                    }
                    i = (i * 53) + i4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = (i * 53) + zzfh.zzp(t, j).hashCode();
                    break;
                case 50:
                    i = (i * 53) + zzfh.zzp(t, j).hashCode();
                    break;
                case 51:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzco.zzu(Double.doubleToLongBits(zzf(t, j)));
                        break;
                    }
                case 52:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + Float.floatToIntBits(zzg(t, j));
                        break;
                    }
                case 53:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzco.zzu(zzi(t, j));
                        break;
                    }
                case 54:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzco.zzu(zzi(t, j));
                        break;
                    }
                case 55:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 56:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzco.zzu(zzi(t, j));
                        break;
                    }
                case 57:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 58:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzco.zzf(zzj(t, j));
                        break;
                    }
                case 59:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + ((String) zzfh.zzp(t, j)).hashCode();
                        break;
                    }
                case 60:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzfh.zzp(t, j).hashCode();
                        break;
                    }
                case 61:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzfh.zzp(t, j).hashCode();
                        break;
                    }
                case 62:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 63:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 64:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 65:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzco.zzu(zzi(t, j));
                        break;
                    }
                case 66:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzh(t, j);
                        break;
                    }
                case 67:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzco.zzu(zzi(t, j));
                        break;
                    }
                case 68:
                    if (!zza(t, i3, i2)) {
                        break;
                    } else {
                        i = (i * 53) + zzfh.zzp(t, j).hashCode();
                        break;
                    }
            }
        }
        int hashCode = (i * 53) + this.zzpd.zzp(t).hashCode();
        return this.zzou ? (hashCode * 53) + this.zzpe.zza((Object) t).hashCode() : hashCode;
    }

    public final void zzc(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzop.length; i += 3) {
            int zzap = zzap(i);
            long j = (long) (1048575 & zzap);
            int i2 = this.zzop[i];
            switch ((zzap & 267386880) >>> 20) {
                case 0:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zza((Object) t, j, zzfh.zzo(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 1:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zza((Object) t, j, zzfh.zzn(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 2:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zza((Object) t, j, zzfh.zzl(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 3:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zza((Object) t, j, zzfh.zzl(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 4:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zzb((Object) t, j, zzfh.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 5:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zza((Object) t, j, zzfh.zzl(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 6:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zzb((Object) t, j, zzfh.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 7:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zza((Object) t, j, zzfh.zzm(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 8:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zza((Object) t, j, zzfh.zzp(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 9:
                    zza(t, t2, i);
                    break;
                case 10:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zza((Object) t, j, zzfh.zzp(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 11:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zzb((Object) t, j, zzfh.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 12:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zzb((Object) t, j, zzfh.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 13:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zzb((Object) t, j, zzfh.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 14:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zza((Object) t, j, zzfh.zzl(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 15:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zzb((Object) t, j, zzfh.zzk(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 16:
                    if (!zza(t2, i)) {
                        break;
                    } else {
                        zzfh.zza((Object) t, j, zzfh.zzl(t2, j));
                        zzb(t, i);
                        break;
                    }
                case 17:
                    zza(t, t2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzpc.zza(t, t2, j);
                    break;
                case 50:
                    zzel.zza(this.zzpf, t, t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (!zza(t2, i2, i)) {
                        break;
                    } else {
                        zzfh.zza((Object) t, j, zzfh.zzp(t2, j));
                        zzb(t, i2, i);
                        break;
                    }
                case 60:
                    zzb(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!zza(t2, i2, i)) {
                        break;
                    } else {
                        zzfh.zza((Object) t, j, zzfh.zzp(t2, j));
                        zzb(t, i2, i);
                        break;
                    }
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        if (!this.zzow) {
            zzel.zza(this.zzpd, t, t2);
            if (this.zzou) {
                zzel.zza(this.zzpe, t, t2);
            }
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzap = (long) (zzap(i) & 1048575);
        if (zza(t2, i)) {
            Object zzp = zzfh.zzp(t, zzap);
            Object zzp2 = zzfh.zzp(t2, zzap);
            if (zzp == null || zzp2 == null) {
                if (zzp2 != null) {
                    zzfh.zza((Object) t, zzap, zzp2);
                    zzb(t, i);
                }
                return;
            }
            zzfh.zza((Object) t, zzap, zzco.zza(zzp, zzp2));
            zzb(t, i);
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzap = zzap(i);
        int i2 = this.zzop[i];
        long j = (long) (zzap & 1048575);
        if (zza(t2, i2, i)) {
            Object zzp = zzfh.zzp(t, j);
            Object zzp2 = zzfh.zzp(t2, j);
            if (zzp == null || zzp2 == null) {
                if (zzp2 != null) {
                    zzfh.zza((Object) t, j, zzp2);
                    zzb(t, i2, i);
                }
                return;
            }
            zzfh.zza((Object) t, j, zzco.zza(zzp, zzp2));
            zzb(t, i2, i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:412:0x09cb, code lost:
        r18 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:472:0x0aef, code lost:
        r3 = r3 + 3;
        r9 = r18;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzm(T r22) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            boolean r2 = r0.zzow
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            r6 = 0
            r7 = 1
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r9 = 0
            r11 = 0
            if (r2 == 0) goto L_0x055f
            sun.misc.Unsafe r2 = zzoo
            r12 = 0
            r13 = 0
        L_0x0016:
            int[] r14 = r0.zzop
            int r14 = r14.length
            if (r12 >= r14) goto L_0x0557
            int r14 = r0.zzap(r12)
            r15 = r14 & r3
            int r15 = r15 >>> 20
            int[] r3 = r0.zzop
            r3 = r3[r12]
            r14 = r14 & r8
            long r4 = (long) r14
            com.google.android.gms.internal.firebase-perf.zzcg r14 = com.google.android.gms.internal.firebase-perf.zzcg.DOUBLE_LIST_PACKED
            int r14 = r14.id()
            if (r15 < r14) goto L_0x0041
            com.google.android.gms.internal.firebase-perf.zzcg r14 = com.google.android.gms.internal.firebase-perf.zzcg.SINT64_LIST_PACKED
            int r14 = r14.id()
            if (r15 > r14) goto L_0x0041
            int[] r14 = r0.zzop
            int r17 = r12 + 2
            r14 = r14[r17]
            r14 = r14 & r8
            goto L_0x0042
        L_0x0041:
            r14 = 0
        L_0x0042:
            switch(r15) {
                case 0: goto L_0x0544;
                case 1: goto L_0x0538;
                case 2: goto L_0x0528;
                case 3: goto L_0x0518;
                case 4: goto L_0x0508;
                case 5: goto L_0x04fc;
                case 6: goto L_0x04f0;
                case 7: goto L_0x04e4;
                case 8: goto L_0x04c4;
                case 9: goto L_0x04af;
                case 10: goto L_0x049c;
                case 11: goto L_0x048b;
                case 12: goto L_0x047a;
                case 13: goto L_0x046d;
                case 14: goto L_0x0460;
                case 15: goto L_0x044f;
                case 16: goto L_0x043e;
                case 17: goto L_0x0427;
                case 18: goto L_0x041c;
                case 19: goto L_0x0411;
                case 20: goto L_0x0406;
                case 21: goto L_0x03fb;
                case 22: goto L_0x03f0;
                case 23: goto L_0x03e5;
                case 24: goto L_0x03da;
                case 25: goto L_0x03cf;
                case 26: goto L_0x03c4;
                case 27: goto L_0x03b5;
                case 28: goto L_0x03aa;
                case 29: goto L_0x039f;
                case 30: goto L_0x0394;
                case 31: goto L_0x0389;
                case 32: goto L_0x037e;
                case 33: goto L_0x0373;
                case 34: goto L_0x0368;
                case 35: goto L_0x0347;
                case 36: goto L_0x0326;
                case 37: goto L_0x0305;
                case 38: goto L_0x02e4;
                case 39: goto L_0x02c3;
                case 40: goto L_0x02a2;
                case 41: goto L_0x0281;
                case 42: goto L_0x0260;
                case 43: goto L_0x023f;
                case 44: goto L_0x021e;
                case 45: goto L_0x01fd;
                case 46: goto L_0x01dc;
                case 47: goto L_0x01bb;
                case 48: goto L_0x019a;
                case 49: goto L_0x018b;
                case 50: goto L_0x017a;
                case 51: goto L_0x016b;
                case 52: goto L_0x015e;
                case 53: goto L_0x014d;
                case 54: goto L_0x013c;
                case 55: goto L_0x012b;
                case 56: goto L_0x011e;
                case 57: goto L_0x0111;
                case 58: goto L_0x0104;
                case 59: goto L_0x00e4;
                case 60: goto L_0x00cf;
                case 61: goto L_0x00bc;
                case 62: goto L_0x00ab;
                case 63: goto L_0x009a;
                case 64: goto L_0x008d;
                case 65: goto L_0x0080;
                case 66: goto L_0x006f;
                case 67: goto L_0x005e;
                case 68: goto L_0x0047;
                default: goto L_0x0045;
            }
        L_0x0045:
            goto L_0x0551
        L_0x0047:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x0551
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r1, r4)
            com.google.android.gms.internal.firebase-perf.zzdt r4 = (com.google.android.gms.internal.firebase-perf.zzdt) r4
            com.google.android.gms.internal.firebase-perf.zzej r5 = r0.zzam(r12)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r3, r4, r5)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x005e:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x0551
            long r4 = zzi(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzf(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x006f:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x0551
            int r4 = zzh(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzi(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0080:
            boolean r4 = r0.zza((T) r1, r3, r12)
            if (r4 == 0) goto L_0x0551
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzh(r3, r9)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x008d:
            boolean r4 = r0.zza((T) r1, r3, r12)
            if (r4 == 0) goto L_0x0551
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzk(r3, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x009a:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x0551
            int r4 = zzh(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzl(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x00ab:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x0551
            int r4 = zzh(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzh(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x00bc:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x0551
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r1, r4)
            com.google.android.gms.internal.firebase-perf.zzbd r4 = (com.google.android.gms.internal.firebase-perf.zzbd) r4
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x00cf:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x0551
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r1, r4)
            com.google.android.gms.internal.firebase-perf.zzej r5 = r0.zzam(r12)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzc(r3, r4, r5)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x00e4:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x0551
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r1, r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.firebase-perf.zzbd
            if (r5 == 0) goto L_0x00fb
            com.google.android.gms.internal.firebase-perf.zzbd r4 = (com.google.android.gms.internal.firebase-perf.zzbd) r4
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x00fb:
            java.lang.String r4 = (java.lang.String) r4
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzb(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0104:
            boolean r4 = r0.zza((T) r1, r3, r12)
            if (r4 == 0) goto L_0x0551
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r3, r7)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0111:
            boolean r4 = r0.zza((T) r1, r3, r12)
            if (r4 == 0) goto L_0x0551
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzj(r3, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x011e:
            boolean r4 = r0.zza((T) r1, r3, r12)
            if (r4 == 0) goto L_0x0551
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzg(r3, r9)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x012b:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x0551
            int r4 = zzh(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzg(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x013c:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x0551
            long r4 = zzi(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zze(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x014d:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x0551
            long r4 = zzi(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzd(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x015e:
            boolean r4 = r0.zza((T) r1, r3, r12)
            if (r4 == 0) goto L_0x0551
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzb(r3, r6)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x016b:
            boolean r4 = r0.zza((T) r1, r3, r12)
            if (r4 == 0) goto L_0x0551
            r4 = 0
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzb(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x017a:
            com.google.android.gms.internal.firebase-perf.zzdo r14 = r0.zzpf
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r1, r4)
            java.lang.Object r5 = r0.zzan(r12)
            int r3 = r14.zzb(r3, r4, r5)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x018b:
            java.util.List r4 = zze(r1, r4)
            com.google.android.gms.internal.firebase-perf.zzej r5 = r0.zzam(r12)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzd(r3, r4, r5)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x019a:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase-perf.zzel.zzt(r4)
            if (r4 <= 0) goto L_0x0551
            boolean r5 = r0.zzox
            if (r5 == 0) goto L_0x01ae
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x01ae:
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r3)
            int r5 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            int r13 = r13 + r3
            goto L_0x0551
        L_0x01bb:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase-perf.zzel.zzx(r4)
            if (r4 <= 0) goto L_0x0551
            boolean r5 = r0.zzox
            if (r5 == 0) goto L_0x01cf
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x01cf:
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r3)
            int r5 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            int r13 = r13 + r3
            goto L_0x0551
        L_0x01dc:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase-perf.zzel.zzz(r4)
            if (r4 <= 0) goto L_0x0551
            boolean r5 = r0.zzox
            if (r5 == 0) goto L_0x01f0
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x01f0:
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r3)
            int r5 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            int r13 = r13 + r3
            goto L_0x0551
        L_0x01fd:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase-perf.zzel.zzy(r4)
            if (r4 <= 0) goto L_0x0551
            boolean r5 = r0.zzox
            if (r5 == 0) goto L_0x0211
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x0211:
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r3)
            int r5 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            int r13 = r13 + r3
            goto L_0x0551
        L_0x021e:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase-perf.zzel.zzu(r4)
            if (r4 <= 0) goto L_0x0551
            boolean r5 = r0.zzox
            if (r5 == 0) goto L_0x0232
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x0232:
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r3)
            int r5 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            int r13 = r13 + r3
            goto L_0x0551
        L_0x023f:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase-perf.zzel.zzw(r4)
            if (r4 <= 0) goto L_0x0551
            boolean r5 = r0.zzox
            if (r5 == 0) goto L_0x0253
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x0253:
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r3)
            int r5 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0260:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase-perf.zzel.zzaa(r4)
            if (r4 <= 0) goto L_0x0551
            boolean r5 = r0.zzox
            if (r5 == 0) goto L_0x0274
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x0274:
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r3)
            int r5 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0281:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase-perf.zzel.zzy(r4)
            if (r4 <= 0) goto L_0x0551
            boolean r5 = r0.zzox
            if (r5 == 0) goto L_0x0295
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x0295:
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r3)
            int r5 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            int r13 = r13 + r3
            goto L_0x0551
        L_0x02a2:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase-perf.zzel.zzz(r4)
            if (r4 <= 0) goto L_0x0551
            boolean r5 = r0.zzox
            if (r5 == 0) goto L_0x02b6
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x02b6:
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r3)
            int r5 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            int r13 = r13 + r3
            goto L_0x0551
        L_0x02c3:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase-perf.zzel.zzv(r4)
            if (r4 <= 0) goto L_0x0551
            boolean r5 = r0.zzox
            if (r5 == 0) goto L_0x02d7
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x02d7:
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r3)
            int r5 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            int r13 = r13 + r3
            goto L_0x0551
        L_0x02e4:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase-perf.zzel.zzs(r4)
            if (r4 <= 0) goto L_0x0551
            boolean r5 = r0.zzox
            if (r5 == 0) goto L_0x02f8
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x02f8:
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r3)
            int r5 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0305:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase-perf.zzel.zzr(r4)
            if (r4 <= 0) goto L_0x0551
            boolean r5 = r0.zzox
            if (r5 == 0) goto L_0x0319
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x0319:
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r3)
            int r5 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0326:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase-perf.zzel.zzy(r4)
            if (r4 <= 0) goto L_0x0551
            boolean r5 = r0.zzox
            if (r5 == 0) goto L_0x033a
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x033a:
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r3)
            int r5 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0347:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase-perf.zzel.zzz(r4)
            if (r4 <= 0) goto L_0x0551
            boolean r5 = r0.zzox
            if (r5 == 0) goto L_0x035b
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x035b:
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r3)
            int r5 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0368:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzq(r3, r4, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0373:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzu(r3, r4, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x037e:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzw(r3, r4, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0389:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzv(r3, r4, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0394:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzr(r3, r4, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x039f:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzt(r3, r4, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x03aa:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzd(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x03b5:
            java.util.List r4 = zze(r1, r4)
            com.google.android.gms.internal.firebase-perf.zzej r5 = r0.zzam(r12)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzc(r3, r4, r5)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x03c4:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzc(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x03cf:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzx(r3, r4, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x03da:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzv(r3, r4, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x03e5:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzw(r3, r4, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x03f0:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzs(r3, r4, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x03fb:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzp(r3, r4, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0406:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzo(r3, r4, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0411:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzv(r3, r4, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x041c:
            java.util.List r4 = zze(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzw(r3, r4, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0427:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x0551
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r1, r4)
            com.google.android.gms.internal.firebase-perf.zzdt r4 = (com.google.android.gms.internal.firebase-perf.zzdt) r4
            com.google.android.gms.internal.firebase-perf.zzej r5 = r0.zzam(r12)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r3, r4, r5)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x043e:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x0551
            long r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzf(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x044f:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x0551
            int r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzi(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0460:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x0551
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzh(r3, r9)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x046d:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x0551
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzk(r3, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x047a:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x0551
            int r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzl(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x048b:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x0551
            int r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzh(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x049c:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x0551
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r1, r4)
            com.google.android.gms.internal.firebase-perf.zzbd r4 = (com.google.android.gms.internal.firebase-perf.zzbd) r4
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x04af:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x0551
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r1, r4)
            com.google.android.gms.internal.firebase-perf.zzej r5 = r0.zzam(r12)
            int r3 = com.google.android.gms.internal.firebase-perf.zzel.zzc(r3, r4, r5)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x04c4:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x0551
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r1, r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.firebase-perf.zzbd
            if (r5 == 0) goto L_0x04db
            com.google.android.gms.internal.firebase-perf.zzbd r4 = (com.google.android.gms.internal.firebase-perf.zzbd) r4
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x04db:
            java.lang.String r4 = (java.lang.String) r4
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzb(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x04e4:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x0551
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r3, r7)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x04f0:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x0551
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzj(r3, r11)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x04fc:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x0551
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzg(r3, r9)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0508:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x0551
            int r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzg(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0518:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x0551
            long r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zze(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0528:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x0551
            long r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r1, r4)
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzd(r3, r4)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0538:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x0551
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzb(r3, r6)
            int r13 = r13 + r3
            goto L_0x0551
        L_0x0544:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x0551
            r4 = 0
            int r3 = com.google.android.gms.internal.firebase-perf.zzbt.zzb(r3, r4)
            int r13 = r13 + r3
        L_0x0551:
            int r12 = r12 + 3
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            goto L_0x0016
        L_0x0557:
            com.google.android.gms.internal.firebase-perf.zzfb<?, ?> r2 = r0.zzpd
            int r1 = zza(r2, (T) r1)
            int r13 = r13 + r1
            return r13
        L_0x055f:
            sun.misc.Unsafe r2 = zzoo
            r3 = -1
            r3 = 0
            r4 = 0
            r5 = -1
            r12 = 0
        L_0x0566:
            int[] r13 = r0.zzop
            int r13 = r13.length
            if (r3 >= r13) goto L_0x0af7
            int r13 = r0.zzap(r3)
            int[] r14 = r0.zzop
            r14 = r14[r3]
            r15 = 267386880(0xff00000, float:2.3665827E-29)
            r16 = r13 & r15
            int r15 = r16 >>> 20
            r6 = 17
            if (r15 > r6) goto L_0x0592
            int[] r6 = r0.zzop
            int r16 = r3 + 2
            r6 = r6[r16]
            r11 = r6 & r8
            int r16 = r6 >>> 20
            int r16 = r7 << r16
            if (r11 == r5) goto L_0x05b3
            long r9 = (long) r11
            int r12 = r2.getInt(r1, r9)
            r5 = r11
            goto L_0x05b3
        L_0x0592:
            boolean r6 = r0.zzox
            if (r6 == 0) goto L_0x05b0
            com.google.android.gms.internal.firebase-perf.zzcg r6 = com.google.android.gms.internal.firebase-perf.zzcg.DOUBLE_LIST_PACKED
            int r6 = r6.id()
            if (r15 < r6) goto L_0x05b0
            com.google.android.gms.internal.firebase-perf.zzcg r6 = com.google.android.gms.internal.firebase-perf.zzcg.SINT64_LIST_PACKED
            int r6 = r6.id()
            if (r15 > r6) goto L_0x05b0
            int[] r6 = r0.zzop
            int r9 = r3 + 2
            r6 = r6[r9]
            r11 = r6 & r8
            r6 = r11
            goto L_0x05b1
        L_0x05b0:
            r6 = 0
        L_0x05b1:
            r16 = 0
        L_0x05b3:
            r9 = r13 & r8
            long r9 = (long) r9
            switch(r15) {
                case 0: goto L_0x0ae0;
                case 1: goto L_0x0ad0;
                case 2: goto L_0x0abe;
                case 3: goto L_0x0aad;
                case 4: goto L_0x0a9c;
                case 5: goto L_0x0a8d;
                case 6: goto L_0x0a81;
                case 7: goto L_0x0a76;
                case 8: goto L_0x0a58;
                case 9: goto L_0x0a45;
                case 10: goto L_0x0a35;
                case 11: goto L_0x0a27;
                case 12: goto L_0x0a19;
                case 13: goto L_0x0a0e;
                case 14: goto L_0x0a02;
                case 15: goto L_0x09f4;
                case 16: goto L_0x09e6;
                case 17: goto L_0x09d2;
                case 18: goto L_0x09be;
                case 19: goto L_0x09b1;
                case 20: goto L_0x09a4;
                case 21: goto L_0x0997;
                case 22: goto L_0x098a;
                case 23: goto L_0x097d;
                case 24: goto L_0x0970;
                case 25: goto L_0x0963;
                case 26: goto L_0x0957;
                case 27: goto L_0x0946;
                case 28: goto L_0x0939;
                case 29: goto L_0x092b;
                case 30: goto L_0x091d;
                case 31: goto L_0x090f;
                case 32: goto L_0x0901;
                case 33: goto L_0x08f3;
                case 34: goto L_0x08e5;
                case 35: goto L_0x08c4;
                case 36: goto L_0x08a3;
                case 37: goto L_0x0882;
                case 38: goto L_0x0861;
                case 39: goto L_0x0840;
                case 40: goto L_0x081f;
                case 41: goto L_0x07fe;
                case 42: goto L_0x07dd;
                case 43: goto L_0x07bc;
                case 44: goto L_0x079b;
                case 45: goto L_0x077a;
                case 46: goto L_0x0759;
                case 47: goto L_0x0738;
                case 48: goto L_0x0717;
                case 49: goto L_0x0706;
                case 50: goto L_0x06f5;
                case 51: goto L_0x06e6;
                case 52: goto L_0x06d8;
                case 53: goto L_0x06c7;
                case 54: goto L_0x06b6;
                case 55: goto L_0x06a5;
                case 56: goto L_0x0696;
                case 57: goto L_0x0688;
                case 58: goto L_0x067b;
                case 59: goto L_0x065b;
                case 60: goto L_0x0646;
                case 61: goto L_0x0633;
                case 62: goto L_0x0622;
                case 63: goto L_0x0611;
                case 64: goto L_0x0603;
                case 65: goto L_0x05f4;
                case 66: goto L_0x05e3;
                case 67: goto L_0x05d2;
                case 68: goto L_0x05bb;
                default: goto L_0x05b9;
            }
        L_0x05b9:
            goto L_0x09ca
        L_0x05bb:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            java.lang.Object r6 = r2.getObject(r1, r9)
            com.google.android.gms.internal.firebase-perf.zzdt r6 = (com.google.android.gms.internal.firebase-perf.zzdt) r6
            com.google.android.gms.internal.firebase-perf.zzej r9 = r0.zzam(r3)
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r14, r6, r9)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x05d2:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            long r9 = zzi(r1, r9)
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzf(r14, r9)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x05e3:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            int r6 = zzh(r1, r9)
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzi(r14, r6)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x05f4:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            r9 = 0
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzh(r14, r9)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0603:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            r6 = 0
            int r9 = com.google.android.gms.internal.firebase-perf.zzbt.zzk(r14, r6)
            int r4 = r4 + r9
            goto L_0x09ca
        L_0x0611:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            int r6 = zzh(r1, r9)
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzl(r14, r6)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0622:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            int r6 = zzh(r1, r9)
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzh(r14, r6)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0633:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            java.lang.Object r6 = r2.getObject(r1, r9)
            com.google.android.gms.internal.firebase-perf.zzbd r6 = (com.google.android.gms.internal.firebase-perf.zzbd) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r14, r6)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0646:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            java.lang.Object r6 = r2.getObject(r1, r9)
            com.google.android.gms.internal.firebase-perf.zzej r9 = r0.zzam(r3)
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzc(r14, r6, r9)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x065b:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            java.lang.Object r6 = r2.getObject(r1, r9)
            boolean r9 = r6 instanceof com.google.android.gms.internal.firebase-perf.zzbd
            if (r9 == 0) goto L_0x0672
            com.google.android.gms.internal.firebase-perf.zzbd r6 = (com.google.android.gms.internal.firebase-perf.zzbd) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r14, r6)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0672:
            java.lang.String r6 = (java.lang.String) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzb(r14, r6)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x067b:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r14, r7)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0688:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            r6 = 0
            int r9 = com.google.android.gms.internal.firebase-perf.zzbt.zzj(r14, r6)
            int r4 = r4 + r9
            goto L_0x09ca
        L_0x0696:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            r9 = 0
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzg(r14, r9)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x06a5:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            int r6 = zzh(r1, r9)
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzg(r14, r6)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x06b6:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            long r9 = zzi(r1, r9)
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zze(r14, r9)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x06c7:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            long r9 = zzi(r1, r9)
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzd(r14, r9)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x06d8:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            r6 = 0
            int r9 = com.google.android.gms.internal.firebase-perf.zzbt.zzb(r14, r6)
            int r4 = r4 + r9
            goto L_0x09ca
        L_0x06e6:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x09ca
            r9 = 0
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzb(r14, r9)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x06f5:
            com.google.android.gms.internal.firebase-perf.zzdo r6 = r0.zzpf
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.lang.Object r10 = r0.zzan(r3)
            int r6 = r6.zzb(r14, r9, r10)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0706:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.firebase-perf.zzej r9 = r0.zzam(r3)
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzd(r14, r6, r9)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0717:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzel.zzt(r9)
            if (r9 <= 0) goto L_0x09ca
            boolean r10 = r0.zzox
            if (r10 == 0) goto L_0x072b
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x072b:
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r14)
            int r10 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0738:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzel.zzx(r9)
            if (r9 <= 0) goto L_0x09ca
            boolean r10 = r0.zzox
            if (r10 == 0) goto L_0x074c
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x074c:
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r14)
            int r10 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0759:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzel.zzz(r9)
            if (r9 <= 0) goto L_0x09ca
            boolean r10 = r0.zzox
            if (r10 == 0) goto L_0x076d
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x076d:
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r14)
            int r10 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x077a:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzel.zzy(r9)
            if (r9 <= 0) goto L_0x09ca
            boolean r10 = r0.zzox
            if (r10 == 0) goto L_0x078e
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x078e:
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r14)
            int r10 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x079b:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzel.zzu(r9)
            if (r9 <= 0) goto L_0x09ca
            boolean r10 = r0.zzox
            if (r10 == 0) goto L_0x07af
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x07af:
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r14)
            int r10 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x07bc:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzel.zzw(r9)
            if (r9 <= 0) goto L_0x09ca
            boolean r10 = r0.zzox
            if (r10 == 0) goto L_0x07d0
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x07d0:
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r14)
            int r10 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x07dd:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzel.zzaa(r9)
            if (r9 <= 0) goto L_0x09ca
            boolean r10 = r0.zzox
            if (r10 == 0) goto L_0x07f1
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x07f1:
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r14)
            int r10 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x07fe:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzel.zzy(r9)
            if (r9 <= 0) goto L_0x09ca
            boolean r10 = r0.zzox
            if (r10 == 0) goto L_0x0812
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x0812:
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r14)
            int r10 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x081f:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzel.zzz(r9)
            if (r9 <= 0) goto L_0x09ca
            boolean r10 = r0.zzox
            if (r10 == 0) goto L_0x0833
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x0833:
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r14)
            int r10 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0840:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzel.zzv(r9)
            if (r9 <= 0) goto L_0x09ca
            boolean r10 = r0.zzox
            if (r10 == 0) goto L_0x0854
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x0854:
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r14)
            int r10 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0861:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzel.zzs(r9)
            if (r9 <= 0) goto L_0x09ca
            boolean r10 = r0.zzox
            if (r10 == 0) goto L_0x0875
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x0875:
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r14)
            int r10 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0882:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzel.zzr(r9)
            if (r9 <= 0) goto L_0x09ca
            boolean r10 = r0.zzox
            if (r10 == 0) goto L_0x0896
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x0896:
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r14)
            int r10 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x08a3:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzel.zzy(r9)
            if (r9 <= 0) goto L_0x09ca
            boolean r10 = r0.zzox
            if (r10 == 0) goto L_0x08b7
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x08b7:
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r14)
            int r10 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x08c4:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzel.zzz(r9)
            if (r9 <= 0) goto L_0x09ca
            boolean r10 = r0.zzox
            if (r10 == 0) goto L_0x08d8
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x08d8:
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzz(r14)
            int r10 = com.google.android.gms.internal.firebase-perf.zzbt.zzab(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x08e5:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            r11 = 0
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzq(r14, r6, r11)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x08f3:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzu(r14, r6, r11)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0901:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzw(r14, r6, r11)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x090f:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzv(r14, r6, r11)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x091d:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzr(r14, r6, r11)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x092b:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzt(r14, r6, r11)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0939:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzd(r14, r6)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0946:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.firebase-perf.zzej r9 = r0.zzam(r3)
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzc(r14, r6, r9)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0957:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzc(r14, r6)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0963:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            r11 = 0
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzx(r14, r6, r11)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0970:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzv(r14, r6, r11)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x097d:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzw(r14, r6, r11)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x098a:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzs(r14, r6, r11)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0997:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzp(r14, r6, r11)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x09a4:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzo(r14, r6, r11)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x09b1:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzv(r14, r6, r11)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x09be:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzw(r14, r6, r11)
            int r4 = r4 + r6
        L_0x09ca:
            r6 = 0
        L_0x09cb:
            r9 = 0
            r10 = 0
            r18 = 0
            goto L_0x0aef
        L_0x09d2:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x09ca
            java.lang.Object r6 = r2.getObject(r1, r9)
            com.google.android.gms.internal.firebase-perf.zzdt r6 = (com.google.android.gms.internal.firebase-perf.zzdt) r6
            com.google.android.gms.internal.firebase-perf.zzej r9 = r0.zzam(r3)
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r14, r6, r9)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x09e6:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x09ca
            long r9 = r2.getLong(r1, r9)
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzf(r14, r9)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x09f4:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x09ca
            int r6 = r2.getInt(r1, r9)
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzi(r14, r6)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0a02:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x09ca
            r9 = 0
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzh(r14, r9)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0a0e:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x09ca
            r6 = 0
            int r9 = com.google.android.gms.internal.firebase-perf.zzbt.zzk(r14, r6)
            int r4 = r4 + r9
            goto L_0x09ca
        L_0x0a19:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x09ca
            int r6 = r2.getInt(r1, r9)
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzl(r14, r6)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0a27:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x09ca
            int r6 = r2.getInt(r1, r9)
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzh(r14, r6)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0a35:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x09ca
            java.lang.Object r6 = r2.getObject(r1, r9)
            com.google.android.gms.internal.firebase-perf.zzbd r6 = (com.google.android.gms.internal.firebase-perf.zzbd) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r14, r6)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0a45:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x09ca
            java.lang.Object r6 = r2.getObject(r1, r9)
            com.google.android.gms.internal.firebase-perf.zzej r9 = r0.zzam(r3)
            int r6 = com.google.android.gms.internal.firebase-perf.zzel.zzc(r14, r6, r9)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0a58:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x09ca
            java.lang.Object r6 = r2.getObject(r1, r9)
            boolean r9 = r6 instanceof com.google.android.gms.internal.firebase-perf.zzbd
            if (r9 == 0) goto L_0x0a6d
            com.google.android.gms.internal.firebase-perf.zzbd r6 = (com.google.android.gms.internal.firebase-perf.zzbd) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r14, r6)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0a6d:
            java.lang.String r6 = (java.lang.String) r6
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzb(r14, r6)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0a76:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x09ca
            int r6 = com.google.android.gms.internal.firebase-perf.zzbt.zzc(r14, r7)
            int r4 = r4 + r6
            goto L_0x09ca
        L_0x0a81:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x09ca
            r6 = 0
            int r9 = com.google.android.gms.internal.firebase-perf.zzbt.zzj(r14, r6)
            int r4 = r4 + r9
            goto L_0x09cb
        L_0x0a8d:
            r6 = 0
            r9 = r12 & r16
            if (r9 == 0) goto L_0x09cb
            r9 = 0
            int r11 = com.google.android.gms.internal.firebase-perf.zzbt.zzg(r14, r9)
            int r4 = r4 + r11
            r18 = r9
            goto L_0x0ace
        L_0x0a9c:
            r6 = 0
            r18 = 0
            r11 = r12 & r16
            if (r11 == 0) goto L_0x0ace
            int r9 = r2.getInt(r1, r9)
            int r9 = com.google.android.gms.internal.firebase-perf.zzbt.zzg(r14, r9)
            int r4 = r4 + r9
            goto L_0x0ace
        L_0x0aad:
            r6 = 0
            r18 = 0
            r11 = r12 & r16
            if (r11 == 0) goto L_0x0ace
            long r9 = r2.getLong(r1, r9)
            int r9 = com.google.android.gms.internal.firebase-perf.zzbt.zze(r14, r9)
            int r4 = r4 + r9
            goto L_0x0ace
        L_0x0abe:
            r6 = 0
            r18 = 0
            r11 = r12 & r16
            if (r11 == 0) goto L_0x0ace
            long r9 = r2.getLong(r1, r9)
            int r9 = com.google.android.gms.internal.firebase-perf.zzbt.zzd(r14, r9)
            int r4 = r4 + r9
        L_0x0ace:
            r9 = 0
            goto L_0x0add
        L_0x0ad0:
            r6 = 0
            r18 = 0
            r9 = r12 & r16
            if (r9 == 0) goto L_0x0ace
            r9 = 0
            int r10 = com.google.android.gms.internal.firebase-perf.zzbt.zzb(r14, r9)
            int r4 = r4 + r10
        L_0x0add:
            r10 = 0
            goto L_0x0aef
        L_0x0ae0:
            r6 = 0
            r9 = 0
            r18 = 0
            r10 = r12 & r16
            if (r10 == 0) goto L_0x0add
            r10 = 0
            int r13 = com.google.android.gms.internal.firebase-perf.zzbt.zzb(r14, r10)
            int r4 = r4 + r13
        L_0x0aef:
            int r3 = r3 + 3
            r9 = r18
            r6 = 0
            r11 = 0
            goto L_0x0566
        L_0x0af7:
            com.google.android.gms.internal.firebase-perf.zzfb<?, ?> r2 = r0.zzpd
            int r2 = zza(r2, (T) r1)
            int r4 = r4 + r2
            boolean r2 = r0.zzou
            if (r2 == 0) goto L_0x0b0d
            com.google.android.gms.internal.firebase-perf.zzca<?> r2 = r0.zzpe
            com.google.android.gms.internal.firebase-perf.zzcd r1 = r2.zza(r1)
            int r1 = r1.zzdg()
            int r4 = r4 + r1
        L_0x0b0d:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzdx.zzm(java.lang.Object):int");
    }

    private static <UT, UB> int zza(zzfb<UT, UB> zzfb, T t) {
        return zzfb.zzm(zzfb.zzp(t));
    }

    private static <E> List<E> zze(Object obj, long j) {
        return (List) zzfh.zzp(obj, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0511  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x054f  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0a27  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r14, com.google.android.gms.internal.firebase-perf.zzfw r15) throws java.io.IOException {
        /*
            r13 = this;
            int r0 = r15.zzcv()
            int r1 = com.google.android.gms.internal.firebase-perf.zzcm.zze.zzmv
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x0527
            com.google.android.gms.internal.firebase-perf.zzfb<?, ?> r0 = r13.zzpd
            zza(r0, (T) r14, r15)
            boolean r0 = r13.zzou
            if (r0 == 0) goto L_0x0030
            com.google.android.gms.internal.firebase-perf.zzca<?> r0 = r13.zzpe
            com.google.android.gms.internal.firebase-perf.zzcd r0 = r0.zza(r14)
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0030
            java.util.Iterator r0 = r0.descendingIterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0032
        L_0x0030:
            r0 = r3
            r1 = r0
        L_0x0032:
            int[] r7 = r13.zzop
            int r7 = r7.length
            int r7 = r7 + -3
        L_0x0037:
            if (r7 < 0) goto L_0x050f
            int r8 = r13.zzap(r7)
            int[] r9 = r13.zzop
            r9 = r9[r7]
        L_0x0041:
            if (r1 == 0) goto L_0x005f
            com.google.android.gms.internal.firebase-perf.zzca<?> r10 = r13.zzpe
            int r10 = r10.zza(r1)
            if (r10 <= r9) goto L_0x005f
            com.google.android.gms.internal.firebase-perf.zzca<?> r10 = r13.zzpe
            r10.zza(r15, r1)
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
                case 0: goto L_0x04fc;
                case 1: goto L_0x04ec;
                case 2: goto L_0x04dc;
                case 3: goto L_0x04cc;
                case 4: goto L_0x04bc;
                case 5: goto L_0x04ac;
                case 6: goto L_0x049c;
                case 7: goto L_0x048b;
                case 8: goto L_0x047a;
                case 9: goto L_0x0465;
                case 10: goto L_0x0452;
                case 11: goto L_0x0441;
                case 12: goto L_0x0430;
                case 13: goto L_0x041f;
                case 14: goto L_0x040e;
                case 15: goto L_0x03fd;
                case 16: goto L_0x03ec;
                case 17: goto L_0x03d7;
                case 18: goto L_0x03c6;
                case 19: goto L_0x03b5;
                case 20: goto L_0x03a4;
                case 21: goto L_0x0393;
                case 22: goto L_0x0382;
                case 23: goto L_0x0371;
                case 24: goto L_0x0360;
                case 25: goto L_0x034f;
                case 26: goto L_0x033e;
                case 27: goto L_0x0329;
                case 28: goto L_0x0318;
                case 29: goto L_0x0307;
                case 30: goto L_0x02f6;
                case 31: goto L_0x02e5;
                case 32: goto L_0x02d4;
                case 33: goto L_0x02c3;
                case 34: goto L_0x02b2;
                case 35: goto L_0x02a1;
                case 36: goto L_0x0290;
                case 37: goto L_0x027f;
                case 38: goto L_0x026e;
                case 39: goto L_0x025d;
                case 40: goto L_0x024c;
                case 41: goto L_0x023b;
                case 42: goto L_0x022a;
                case 43: goto L_0x0219;
                case 44: goto L_0x0208;
                case 45: goto L_0x01f7;
                case 46: goto L_0x01e6;
                case 47: goto L_0x01d5;
                case 48: goto L_0x01c4;
                case 49: goto L_0x01af;
                case 50: goto L_0x01a4;
                case 51: goto L_0x0193;
                case 52: goto L_0x0182;
                case 53: goto L_0x0171;
                case 54: goto L_0x0160;
                case 55: goto L_0x014f;
                case 56: goto L_0x013e;
                case 57: goto L_0x012d;
                case 58: goto L_0x011c;
                case 59: goto L_0x010b;
                case 60: goto L_0x00f6;
                case 61: goto L_0x00e3;
                case 62: goto L_0x00d2;
                case 63: goto L_0x00c1;
                case 64: goto L_0x00b0;
                case 65: goto L_0x009f;
                case 66: goto L_0x008e;
                case 67: goto L_0x007d;
                case 68: goto L_0x0068;
                default: goto L_0x0066;
            }
        L_0x0066:
            goto L_0x050b
        L_0x0068:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            com.google.android.gms.internal.firebase-perf.zzej r10 = r13.zzam(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x050b
        L_0x007d:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzb(r9, r10)
            goto L_0x050b
        L_0x008e:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zze(r9, r8)
            goto L_0x050b
        L_0x009f:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x050b
        L_0x00b0:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzm(r9, r8)
            goto L_0x050b
        L_0x00c1:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzn(r9, r8)
            goto L_0x050b
        L_0x00d2:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzd(r9, r8)
            goto L_0x050b
        L_0x00e3:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            com.google.android.gms.internal.firebase-perf.zzbd r8 = (com.google.android.gms.internal.firebase-perf.zzbd) r8
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x00f6:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            com.google.android.gms.internal.firebase-perf.zzej r10 = r13.zzam(r7)
            r15.zza(r9, r8, r10)
            goto L_0x050b
        L_0x010b:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            zza(r9, r8, r15)
            goto L_0x050b
        L_0x011c:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = zzj(r14, r10)
            r15.zzb(r9, r8)
            goto L_0x050b
        L_0x012d:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x050b
        L_0x013e:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x050b
        L_0x014f:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzc(r9, r8)
            goto L_0x050b
        L_0x0160:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zza(r9, r10)
            goto L_0x050b
        L_0x0171:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x050b
        L_0x0182:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = zzg(r14, r10)
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x0193:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = zzf(r14, r10)
            r15.zza(r9, r10)
            goto L_0x050b
        L_0x01a4:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            r13.zza(r15, r9, r8, r7)
            goto L_0x050b
        L_0x01af:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzej r10 = r13.zzam(r7)
            com.google.android.gms.internal.firebase-perf.zzel.zzb(r9, r8, r15, r10)
            goto L_0x050b
        L_0x01c4:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zze(r9, r8, r15, r4)
            goto L_0x050b
        L_0x01d5:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzj(r9, r8, r15, r4)
            goto L_0x050b
        L_0x01e6:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzg(r9, r8, r15, r4)
            goto L_0x050b
        L_0x01f7:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzl(r9, r8, r15, r4)
            goto L_0x050b
        L_0x0208:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzm(r9, r8, r15, r4)
            goto L_0x050b
        L_0x0219:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzi(r9, r8, r15, r4)
            goto L_0x050b
        L_0x022a:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzn(r9, r8, r15, r4)
            goto L_0x050b
        L_0x023b:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzk(r9, r8, r15, r4)
            goto L_0x050b
        L_0x024c:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzf(r9, r8, r15, r4)
            goto L_0x050b
        L_0x025d:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzh(r9, r8, r15, r4)
            goto L_0x050b
        L_0x026e:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzd(r9, r8, r15, r4)
            goto L_0x050b
        L_0x027f:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzc(r9, r8, r15, r4)
            goto L_0x050b
        L_0x0290:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzb(r9, r8, r15, r4)
            goto L_0x050b
        L_0x02a1:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zza(r9, r8, r15, r4)
            goto L_0x050b
        L_0x02b2:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zze(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02c3:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzj(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02d4:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzg(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02e5:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzl(r9, r8, r15, r5)
            goto L_0x050b
        L_0x02f6:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzm(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0307:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzi(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0318:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzb(r9, r8, r15)
            goto L_0x050b
        L_0x0329:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzej r10 = r13.zzam(r7)
            com.google.android.gms.internal.firebase-perf.zzel.zza(r9, r8, r15, r10)
            goto L_0x050b
        L_0x033e:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zza(r9, r8, r15)
            goto L_0x050b
        L_0x034f:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzn(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0360:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzk(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0371:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzf(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0382:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzh(r9, r8, r15, r5)
            goto L_0x050b
        L_0x0393:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzd(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03a4:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzc(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03b5:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zzb(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03c6:
            int[] r9 = r13.zzop
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase-perf.zzel.zza(r9, r8, r15, r5)
            goto L_0x050b
        L_0x03d7:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            com.google.android.gms.internal.firebase-perf.zzej r10 = r13.zzam(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x050b
        L_0x03ec:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r14, r10)
            r15.zzb(r9, r10)
            goto L_0x050b
        L_0x03fd:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r14, r10)
            r15.zze(r9, r8)
            goto L_0x050b
        L_0x040e:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x050b
        L_0x041f:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r14, r10)
            r15.zzm(r9, r8)
            goto L_0x050b
        L_0x0430:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r14, r10)
            r15.zzn(r9, r8)
            goto L_0x050b
        L_0x0441:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r14, r10)
            r15.zzd(r9, r8)
            goto L_0x050b
        L_0x0452:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            com.google.android.gms.internal.firebase-perf.zzbd r8 = (com.google.android.gms.internal.firebase-perf.zzbd) r8
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x0465:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            com.google.android.gms.internal.firebase-perf.zzej r10 = r13.zzam(r7)
            r15.zza(r9, r8, r10)
            goto L_0x050b
        L_0x047a:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r10)
            zza(r9, r8, r15)
            goto L_0x050b
        L_0x048b:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzm(r14, r10)
            r15.zzb(r9, r8)
            goto L_0x050b
        L_0x049c:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x050b
        L_0x04ac:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x050b
        L_0x04bc:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r14, r10)
            r15.zzc(r9, r8)
            goto L_0x050b
        L_0x04cc:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r14, r10)
            r15.zza(r9, r10)
            goto L_0x050b
        L_0x04dc:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x050b
        L_0x04ec:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.firebase-perf.zzfh.zzn(r14, r10)
            r15.zza(r9, r8)
            goto L_0x050b
        L_0x04fc:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x050b
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.firebase-perf.zzfh.zzo(r14, r10)
            r15.zza(r9, r10)
        L_0x050b:
            int r7 = r7 + -3
            goto L_0x0037
        L_0x050f:
            if (r1 == 0) goto L_0x0526
            com.google.android.gms.internal.firebase-perf.zzca<?> r14 = r13.zzpe
            r14.zza(r15, r1)
            boolean r14 = r0.hasNext()
            if (r14 == 0) goto L_0x0524
            java.lang.Object r14 = r0.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            r1 = r14
            goto L_0x050f
        L_0x0524:
            r1 = r3
            goto L_0x050f
        L_0x0526:
            return
        L_0x0527:
            boolean r0 = r13.zzow
            if (r0 == 0) goto L_0x0a42
            boolean r0 = r13.zzou
            if (r0 == 0) goto L_0x0546
            com.google.android.gms.internal.firebase-perf.zzca<?> r0 = r13.zzpe
            com.google.android.gms.internal.firebase-perf.zzcd r0 = r0.zza(r14)
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0546
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0548
        L_0x0546:
            r0 = r3
            r1 = r0
        L_0x0548:
            int[] r7 = r13.zzop
            int r7 = r7.length
            r8 = r1
            r1 = 0
        L_0x054d:
            if (r1 >= r7) goto L_0x0a25
            int r9 = r13.zzap(r1)
            int[] r10 = r13.zzop
            r10 = r10[r1]
        L_0x0557:
            if (r8 == 0) goto L_0x0575
            com.google.android.gms.internal.firebase-perf.zzca<?> r11 = r13.zzpe
            int r11 = r11.zza(r8)
            if (r11 > r10) goto L_0x0575
            com.google.android.gms.internal.firebase-perf.zzca<?> r11 = r13.zzpe
            r11.zza(r15, r8)
            boolean r8 = r0.hasNext()
            if (r8 == 0) goto L_0x0573
            java.lang.Object r8 = r0.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            goto L_0x0557
        L_0x0573:
            r8 = r3
            goto L_0x0557
        L_0x0575:
            r11 = r9 & r2
            int r11 = r11 >>> 20
            switch(r11) {
                case 0: goto L_0x0a12;
                case 1: goto L_0x0a02;
                case 2: goto L_0x09f2;
                case 3: goto L_0x09e2;
                case 4: goto L_0x09d2;
                case 5: goto L_0x09c2;
                case 6: goto L_0x09b2;
                case 7: goto L_0x09a1;
                case 8: goto L_0x0990;
                case 9: goto L_0x097b;
                case 10: goto L_0x0968;
                case 11: goto L_0x0957;
                case 12: goto L_0x0946;
                case 13: goto L_0x0935;
                case 14: goto L_0x0924;
                case 15: goto L_0x0913;
                case 16: goto L_0x0902;
                case 17: goto L_0x08ed;
                case 18: goto L_0x08dc;
                case 19: goto L_0x08cb;
                case 20: goto L_0x08ba;
                case 21: goto L_0x08a9;
                case 22: goto L_0x0898;
                case 23: goto L_0x0887;
                case 24: goto L_0x0876;
                case 25: goto L_0x0865;
                case 26: goto L_0x0854;
                case 27: goto L_0x083f;
                case 28: goto L_0x082e;
                case 29: goto L_0x081d;
                case 30: goto L_0x080c;
                case 31: goto L_0x07fb;
                case 32: goto L_0x07ea;
                case 33: goto L_0x07d9;
                case 34: goto L_0x07c8;
                case 35: goto L_0x07b7;
                case 36: goto L_0x07a6;
                case 37: goto L_0x0795;
                case 38: goto L_0x0784;
                case 39: goto L_0x0773;
                case 40: goto L_0x0762;
                case 41: goto L_0x0751;
                case 42: goto L_0x0740;
                case 43: goto L_0x072f;
                case 44: goto L_0x071e;
                case 45: goto L_0x070d;
                case 46: goto L_0x06fc;
                case 47: goto L_0x06eb;
                case 48: goto L_0x06da;
                case 49: goto L_0x06c5;
                case 50: goto L_0x06ba;
                case 51: goto L_0x06a9;
                case 52: goto L_0x0698;
                case 53: goto L_0x0687;
                case 54: goto L_0x0676;
                case 55: goto L_0x0665;
                case 56: goto L_0x0654;
                case 57: goto L_0x0643;
                case 58: goto L_0x0632;
                case 59: goto L_0x0621;
                case 60: goto L_0x060c;
                case 61: goto L_0x05f9;
                case 62: goto L_0x05e8;
                case 63: goto L_0x05d7;
                case 64: goto L_0x05c6;
                case 65: goto L_0x05b5;
                case 66: goto L_0x05a4;
                case 67: goto L_0x0593;
                case 68: goto L_0x057e;
                default: goto L_0x057c;
            }
        L_0x057c:
            goto L_0x0a21
        L_0x057e:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            com.google.android.gms.internal.firebase-perf.zzej r11 = r13.zzam(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0a21
        L_0x0593:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzb(r10, r11)
            goto L_0x0a21
        L_0x05a4:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0a21
        L_0x05b5:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0a21
        L_0x05c6:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzm(r10, r9)
            goto L_0x0a21
        L_0x05d7:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzn(r10, r9)
            goto L_0x0a21
        L_0x05e8:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzd(r10, r9)
            goto L_0x0a21
        L_0x05f9:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            com.google.android.gms.internal.firebase-perf.zzbd r9 = (com.google.android.gms.internal.firebase-perf.zzbd) r9
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x060c:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            com.google.android.gms.internal.firebase-perf.zzej r11 = r13.zzam(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0a21
        L_0x0621:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0a21
        L_0x0632:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = zzj(r14, r11)
            r15.zzb(r10, r9)
            goto L_0x0a21
        L_0x0643:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0a21
        L_0x0654:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0a21
        L_0x0665:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzc(r10, r9)
            goto L_0x0a21
        L_0x0676:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0a21
        L_0x0687:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0a21
        L_0x0698:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = zzg(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x06a9:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = zzf(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0a21
        L_0x06ba:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            r13.zza(r15, r10, r9, r1)
            goto L_0x0a21
        L_0x06c5:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzej r11 = r13.zzam(r1)
            com.google.android.gms.internal.firebase-perf.zzel.zzb(r10, r9, r15, r11)
            goto L_0x0a21
        L_0x06da:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zze(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x06eb:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzj(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x06fc:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzg(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x070d:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzl(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x071e:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzm(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x072f:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzi(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0740:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzn(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0751:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzk(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0762:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzf(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0773:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzh(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0784:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzd(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x0795:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzc(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x07a6:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzb(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x07b7:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zza(r10, r9, r15, r4)
            goto L_0x0a21
        L_0x07c8:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zze(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x07d9:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzj(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x07ea:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzg(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x07fb:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzl(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x080c:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzm(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x081d:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzi(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x082e:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzb(r10, r9, r15)
            goto L_0x0a21
        L_0x083f:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzej r11 = r13.zzam(r1)
            com.google.android.gms.internal.firebase-perf.zzel.zza(r10, r9, r15, r11)
            goto L_0x0a21
        L_0x0854:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zza(r10, r9, r15)
            goto L_0x0a21
        L_0x0865:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzn(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x0876:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzk(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x0887:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzf(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x0898:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzh(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08a9:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzd(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08ba:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzc(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08cb:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zzb(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08dc:
            int[] r10 = r13.zzop
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase-perf.zzel.zza(r10, r9, r15, r5)
            goto L_0x0a21
        L_0x08ed:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            com.google.android.gms.internal.firebase-perf.zzej r11 = r13.zzam(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0a21
        L_0x0902:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r14, r11)
            r15.zzb(r10, r11)
            goto L_0x0a21
        L_0x0913:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0a21
        L_0x0924:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0a21
        L_0x0935:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r14, r11)
            r15.zzm(r10, r9)
            goto L_0x0a21
        L_0x0946:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r14, r11)
            r15.zzn(r10, r9)
            goto L_0x0a21
        L_0x0957:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r14, r11)
            r15.zzd(r10, r9)
            goto L_0x0a21
        L_0x0968:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            com.google.android.gms.internal.firebase-perf.zzbd r9 = (com.google.android.gms.internal.firebase-perf.zzbd) r9
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x097b:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            com.google.android.gms.internal.firebase-perf.zzej r11 = r13.zzam(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0a21
        L_0x0990:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0a21
        L_0x09a1:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzm(r14, r11)
            r15.zzb(r10, r9)
            goto L_0x0a21
        L_0x09b2:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0a21
        L_0x09c2:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0a21
        L_0x09d2:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzk(r14, r11)
            r15.zzc(r10, r9)
            goto L_0x0a21
        L_0x09e2:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r14, r11)
            r15.zza(r10, r11)
            goto L_0x0a21
        L_0x09f2:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.firebase-perf.zzfh.zzl(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0a21
        L_0x0a02:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.firebase-perf.zzfh.zzn(r14, r11)
            r15.zza(r10, r9)
            goto L_0x0a21
        L_0x0a12:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0a21
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.firebase-perf.zzfh.zzo(r14, r11)
            r15.zza(r10, r11)
        L_0x0a21:
            int r1 = r1 + 3
            goto L_0x054d
        L_0x0a25:
            if (r8 == 0) goto L_0x0a3c
            com.google.android.gms.internal.firebase-perf.zzca<?> r1 = r13.zzpe
            r1.zza(r15, r8)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0a3a
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r8 = r1
            goto L_0x0a25
        L_0x0a3a:
            r8 = r3
            goto L_0x0a25
        L_0x0a3c:
            com.google.android.gms.internal.firebase-perf.zzfb<?, ?> r0 = r13.zzpd
            zza(r0, (T) r14, r15)
            return
        L_0x0a42:
            r13.zzb((T) r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzdx.zza(java.lang.Object, com.google.android.gms.internal.firebase-perf.zzfw):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:172:0x0527  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r21, com.google.android.gms.internal.firebase-perf.zzfw r22) throws java.io.IOException {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r22
            boolean r3 = r0.zzou
            if (r3 == 0) goto L_0x0021
            com.google.android.gms.internal.firebase-perf.zzca<?> r3 = r0.zzpe
            com.google.android.gms.internal.firebase-perf.zzcd r3 = r3.zza(r1)
            boolean r5 = r3.isEmpty()
            if (r5 != 0) goto L_0x0021
            java.util.Iterator r3 = r3.iterator()
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0023
        L_0x0021:
            r3 = 0
            r5 = 0
        L_0x0023:
            r6 = -1
            int[] r7 = r0.zzop
            int r7 = r7.length
            sun.misc.Unsafe r8 = zzoo
            r10 = r5
            r5 = 0
            r11 = 0
        L_0x002c:
            if (r5 >= r7) goto L_0x0525
            int r12 = r0.zzap(r5)
            int[] r13 = r0.zzop
            r13 = r13[r5]
            r14 = 267386880(0xff00000, float:2.3665827E-29)
            r14 = r14 & r12
            int r14 = r14 >>> 20
            boolean r15 = r0.zzow
            r16 = 1048575(0xfffff, float:1.469367E-39)
            if (r15 != 0) goto L_0x0061
            r15 = 17
            if (r14 > r15) goto L_0x0061
            int[] r15 = r0.zzop
            int r17 = r5 + 2
            r15 = r15[r17]
            r9 = r15 & r16
            if (r9 == r6) goto L_0x0059
            r18 = r5
            long r4 = (long) r9
            int r11 = r8.getInt(r1, r4)
            r6 = r9
            goto L_0x005b
        L_0x0059:
            r18 = r5
        L_0x005b:
            int r4 = r15 >>> 20
            r5 = 1
            int r9 = r5 << r4
            goto L_0x0064
        L_0x0061:
            r18 = r5
            r9 = 0
        L_0x0064:
            if (r10 == 0) goto L_0x0083
            com.google.android.gms.internal.firebase-perf.zzca<?> r4 = r0.zzpe
            int r4 = r4.zza(r10)
            if (r4 > r13) goto L_0x0083
            com.google.android.gms.internal.firebase-perf.zzca<?> r4 = r0.zzpe
            r4.zza(r2, r10)
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
                case 0: goto L_0x0514;
                case 1: goto L_0x0506;
                case 2: goto L_0x04f8;
                case 3: goto L_0x04ea;
                case 4: goto L_0x04dc;
                case 5: goto L_0x04ce;
                case 6: goto L_0x04c0;
                case 7: goto L_0x04b2;
                case 8: goto L_0x04a3;
                case 9: goto L_0x0490;
                case 10: goto L_0x047f;
                case 11: goto L_0x0470;
                case 12: goto L_0x0461;
                case 13: goto L_0x0452;
                case 14: goto L_0x0443;
                case 15: goto L_0x0434;
                case 16: goto L_0x0425;
                case 17: goto L_0x0412;
                case 18: goto L_0x0400;
                case 19: goto L_0x03ee;
                case 20: goto L_0x03dc;
                case 21: goto L_0x03ca;
                case 22: goto L_0x03b8;
                case 23: goto L_0x03a6;
                case 24: goto L_0x0394;
                case 25: goto L_0x0382;
                case 26: goto L_0x0371;
                case 27: goto L_0x035c;
                case 28: goto L_0x034b;
                case 29: goto L_0x0339;
                case 30: goto L_0x0327;
                case 31: goto L_0x0315;
                case 32: goto L_0x0303;
                case 33: goto L_0x02f1;
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
            goto L_0x0521
        L_0x008e:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            java.lang.Object r4 = r8.getObject(r1, r4)
            com.google.android.gms.internal.firebase-perf.zzej r5 = r0.zzam(r12)
            r2.zzb(r13, r4, r5)
            goto L_0x008b
        L_0x00a2:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            long r4 = zzi(r1, r4)
            r2.zzb(r13, r4)
            goto L_0x008b
        L_0x00b2:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            int r4 = zzh(r1, r4)
            r2.zze(r13, r4)
            goto L_0x008b
        L_0x00c2:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            long r4 = zzi(r1, r4)
            r2.zzj(r13, r4)
            goto L_0x008b
        L_0x00d2:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            int r4 = zzh(r1, r4)
            r2.zzm(r13, r4)
            goto L_0x008b
        L_0x00e2:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            int r4 = zzh(r1, r4)
            r2.zzn(r13, r4)
            goto L_0x008b
        L_0x00f2:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            int r4 = zzh(r1, r4)
            r2.zzd(r13, r4)
            goto L_0x008b
        L_0x0102:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            java.lang.Object r4 = r8.getObject(r1, r4)
            com.google.android.gms.internal.firebase-perf.zzbd r4 = (com.google.android.gms.internal.firebase-perf.zzbd) r4
            r2.zza(r13, r4)
            goto L_0x008b
        L_0x0115:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            java.lang.Object r4 = r8.getObject(r1, r4)
            com.google.android.gms.internal.firebase-perf.zzej r5 = r0.zzam(r12)
            r2.zza(r13, r4, r5)
            goto L_0x008b
        L_0x012a:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            java.lang.Object r4 = r8.getObject(r1, r4)
            zza(r13, r4, r2)
            goto L_0x008b
        L_0x013b:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            boolean r4 = zzj(r1, r4)
            r2.zzb(r13, r4)
            goto L_0x008b
        L_0x014c:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            int r4 = zzh(r1, r4)
            r2.zzf(r13, r4)
            goto L_0x008b
        L_0x015d:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            long r4 = zzi(r1, r4)
            r2.zzc(r13, r4)
            goto L_0x008b
        L_0x016e:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            int r4 = zzh(r1, r4)
            r2.zzc(r13, r4)
            goto L_0x008b
        L_0x017f:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            long r4 = zzi(r1, r4)
            r2.zza(r13, r4)
            goto L_0x008b
        L_0x0190:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            long r4 = zzi(r1, r4)
            r2.zzi(r13, r4)
            goto L_0x008b
        L_0x01a1:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            float r4 = zzg(r1, r4)
            r2.zza(r13, r4)
            goto L_0x008b
        L_0x01b2:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            double r4 = zzf(r1, r4)
            r2.zza(r13, r4)
            goto L_0x008b
        L_0x01c3:
            r12 = r18
            java.lang.Object r4 = r8.getObject(r1, r4)
            r0.zza(r2, r13, r4, r12)
            goto L_0x008b
        L_0x01ce:
            r12 = r18
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzej r5 = r0.zzam(r12)
            com.google.android.gms.internal.firebase-perf.zzel.zzb(r9, r4, r2, r5)
            goto L_0x008b
        L_0x01e3:
            r12 = r18
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            r13 = 1
            com.google.android.gms.internal.firebase-perf.zzel.zze(r9, r4, r2, r13)
            goto L_0x008b
        L_0x01f5:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzj(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0207:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzg(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0219:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzl(r9, r4, r2, r13)
            goto L_0x008b
        L_0x022b:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzm(r9, r4, r2, r13)
            goto L_0x008b
        L_0x023d:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzi(r9, r4, r2, r13)
            goto L_0x008b
        L_0x024f:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzn(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0261:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzk(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0273:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzf(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0285:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzh(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0297:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzd(r9, r4, r2, r13)
            goto L_0x008b
        L_0x02a9:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzc(r9, r4, r2, r13)
            goto L_0x008b
        L_0x02bb:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzb(r9, r4, r2, r13)
            goto L_0x008b
        L_0x02cd:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zza(r9, r4, r2, r13)
            goto L_0x008b
        L_0x02df:
            r12 = r18
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            r13 = 0
            com.google.android.gms.internal.firebase-perf.zzel.zze(r9, r4, r2, r13)
            goto L_0x008b
        L_0x02f1:
            r12 = r18
            r13 = 0
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzj(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0303:
            r12 = r18
            r13 = 0
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzg(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0315:
            r12 = r18
            r13 = 0
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzl(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0327:
            r12 = r18
            r13 = 0
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzm(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0339:
            r12 = r18
            r13 = 0
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzi(r9, r4, r2, r13)
            goto L_0x008b
        L_0x034b:
            r12 = r18
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzb(r9, r4, r2)
            goto L_0x008b
        L_0x035c:
            r12 = r18
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzej r5 = r0.zzam(r12)
            com.google.android.gms.internal.firebase-perf.zzel.zza(r9, r4, r2, r5)
            goto L_0x008b
        L_0x0371:
            r12 = r18
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zza(r9, r4, r2)
            goto L_0x008b
        L_0x0382:
            r12 = r18
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            r14 = 0
            com.google.android.gms.internal.firebase-perf.zzel.zzn(r9, r4, r2, r14)
            goto L_0x0521
        L_0x0394:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzk(r9, r4, r2, r14)
            goto L_0x0521
        L_0x03a6:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzf(r9, r4, r2, r14)
            goto L_0x0521
        L_0x03b8:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzh(r9, r4, r2, r14)
            goto L_0x0521
        L_0x03ca:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzd(r9, r4, r2, r14)
            goto L_0x0521
        L_0x03dc:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzc(r9, r4, r2, r14)
            goto L_0x0521
        L_0x03ee:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zzb(r9, r4, r2, r14)
            goto L_0x0521
        L_0x0400:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzop
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase-perf.zzel.zza(r9, r4, r2, r14)
            goto L_0x0521
        L_0x0412:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            java.lang.Object r4 = r8.getObject(r1, r4)
            com.google.android.gms.internal.firebase-perf.zzej r5 = r0.zzam(r12)
            r2.zzb(r13, r4, r5)
            goto L_0x0521
        L_0x0425:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            long r4 = r8.getLong(r1, r4)
            r2.zzb(r13, r4)
            goto L_0x0521
        L_0x0434:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            int r4 = r8.getInt(r1, r4)
            r2.zze(r13, r4)
            goto L_0x0521
        L_0x0443:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            long r4 = r8.getLong(r1, r4)
            r2.zzj(r13, r4)
            goto L_0x0521
        L_0x0452:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            int r4 = r8.getInt(r1, r4)
            r2.zzm(r13, r4)
            goto L_0x0521
        L_0x0461:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            int r4 = r8.getInt(r1, r4)
            r2.zzn(r13, r4)
            goto L_0x0521
        L_0x0470:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            int r4 = r8.getInt(r1, r4)
            r2.zzd(r13, r4)
            goto L_0x0521
        L_0x047f:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            java.lang.Object r4 = r8.getObject(r1, r4)
            com.google.android.gms.internal.firebase-perf.zzbd r4 = (com.google.android.gms.internal.firebase-perf.zzbd) r4
            r2.zza(r13, r4)
            goto L_0x0521
        L_0x0490:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            java.lang.Object r4 = r8.getObject(r1, r4)
            com.google.android.gms.internal.firebase-perf.zzej r5 = r0.zzam(r12)
            r2.zza(r13, r4, r5)
            goto L_0x0521
        L_0x04a3:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            java.lang.Object r4 = r8.getObject(r1, r4)
            zza(r13, r4, r2)
            goto L_0x0521
        L_0x04b2:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            boolean r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzm(r1, r4)
            r2.zzb(r13, r4)
            goto L_0x0521
        L_0x04c0:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            int r4 = r8.getInt(r1, r4)
            r2.zzf(r13, r4)
            goto L_0x0521
        L_0x04ce:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            long r4 = r8.getLong(r1, r4)
            r2.zzc(r13, r4)
            goto L_0x0521
        L_0x04dc:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            int r4 = r8.getInt(r1, r4)
            r2.zzc(r13, r4)
            goto L_0x0521
        L_0x04ea:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            long r4 = r8.getLong(r1, r4)
            r2.zza(r13, r4)
            goto L_0x0521
        L_0x04f8:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            long r4 = r8.getLong(r1, r4)
            r2.zzi(r13, r4)
            goto L_0x0521
        L_0x0506:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            float r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzn(r1, r4)
            r2.zza(r13, r4)
            goto L_0x0521
        L_0x0514:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x0521
            double r4 = com.google.android.gms.internal.firebase-perf.zzfh.zzo(r1, r4)
            r2.zza(r13, r4)
        L_0x0521:
            int r5 = r12 + 3
            goto L_0x002c
        L_0x0525:
            if (r10 == 0) goto L_0x053c
            com.google.android.gms.internal.firebase-perf.zzca<?> r4 = r0.zzpe
            r4.zza(r2, r10)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x053a
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r10 = r4
            goto L_0x0525
        L_0x053a:
            r10 = 0
            goto L_0x0525
        L_0x053c:
            com.google.android.gms.internal.firebase-perf.zzfb<?, ?> r3 = r0.zzpd
            zza(r3, (T) r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzdx.zzb(java.lang.Object, com.google.android.gms.internal.firebase-perf.zzfw):void");
    }

    private final <K, V> void zza(zzfw zzfw, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzfw.zza(i, this.zzpf.zzl(zzan(i2)), this.zzpf.zzh(obj));
        }
    }

    private static <UT, UB> void zza(zzfb<UT, UB> zzfb, T t, zzfw zzfw) throws IOException {
        zzfb.zza(zzfb.zzp(t), zzfw);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:168:?, code lost:
        r12.zza(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x05bb, code lost:
        if (r15 == null) goto L_0x05bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x05bd, code lost:
        r15 = r12.zzq(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x05c6, code lost:
        if (r12.zza(r15, r10) == false) goto L_0x05c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x05c8, code lost:
        r3 = r1.zzoz;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x05cc, code lost:
        if (r3 < r1.zzpa) goto L_0x05ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x05ce, code lost:
        r15 = zza((java.lang.Object) r2, r1.zzoy[r3], (UB) r15, r12);
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x05d9, code lost:
        if (r15 != null) goto L_0x05db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x05db, code lost:
        r12.zzf(r2, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x05de, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:167:0x05b8 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r18, com.google.android.gms.internal.firebase-perf.zzei r19, com.google.android.gms.internal.firebase-perf.zzbz r20) throws java.io.IOException {
        /*
            r17 = this;
            r1 = r17
            r2 = r18
            r10 = r19
            r11 = r20
            if (r11 != 0) goto L_0x0010
            java.lang.NullPointerException r2 = new java.lang.NullPointerException
            r2.<init>()
            throw r2
        L_0x0010:
            com.google.android.gms.internal.firebase-perf.zzfb<?, ?> r12 = r1.zzpd
            com.google.android.gms.internal.firebase-perf.zzca<?> r13 = r1.zzpe
            r14 = 0
            r3 = r14
            r15 = r3
        L_0x0017:
            int r4 = r19.zzcq()     // Catch:{ all -> 0x05df }
            int r5 = r1.zzor     // Catch:{ all -> 0x05df }
            if (r4 < r5) goto L_0x0042
            int r5 = r1.zzos     // Catch:{ all -> 0x05df }
            if (r4 > r5) goto L_0x0042
            r5 = 0
            int[] r7 = r1.zzop     // Catch:{ all -> 0x05df }
            int r7 = r7.length     // Catch:{ all -> 0x05df }
            int r7 = r7 / 3
            int r7 = r7 + -1
        L_0x002b:
            if (r5 > r7) goto L_0x0042
            int r8 = r7 + r5
            int r8 = r8 >>> 1
            int r9 = r8 * 3
            int[] r6 = r1.zzop     // Catch:{ all -> 0x05df }
            r6 = r6[r9]     // Catch:{ all -> 0x05df }
            if (r4 != r6) goto L_0x003a
            goto L_0x0043
        L_0x003a:
            if (r4 >= r6) goto L_0x003f
            int r7 = r8 + -1
            goto L_0x002b
        L_0x003f:
            int r5 = r8 + 1
            goto L_0x002b
        L_0x0042:
            r9 = -1
        L_0x0043:
            if (r9 >= 0) goto L_0x00ae
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 != r5) goto L_0x0061
            int r3 = r1.zzoz
        L_0x004c:
            int r4 = r1.zzpa
            if (r3 >= r4) goto L_0x005b
            int[] r4 = r1.zzoy
            r4 = r4[r3]
            java.lang.Object r15 = r1.zza(r2, r4, (UB) r15, r12)
            int r3 = r3 + 1
            goto L_0x004c
        L_0x005b:
            if (r15 == 0) goto L_0x0060
            r12.zzf(r2, r15)
        L_0x0060:
            return
        L_0x0061:
            boolean r5 = r1.zzou     // Catch:{ all -> 0x05df }
            if (r5 != 0) goto L_0x0067
            r5 = r14
            goto L_0x006e
        L_0x0067:
            com.google.android.gms.internal.firebase-perf.zzdt r5 = r1.zzot     // Catch:{ all -> 0x05df }
            java.lang.Object r4 = r13.zza(r11, r5, r4)     // Catch:{ all -> 0x05df }
            r5 = r4
        L_0x006e:
            if (r5 == 0) goto L_0x0087
            if (r3 != 0) goto L_0x0076
            com.google.android.gms.internal.firebase-perf.zzcd r3 = r13.zzb(r2)     // Catch:{ all -> 0x05df }
        L_0x0076:
            r16 = r3
            r3 = r13
            r4 = r10
            r6 = r11
            r7 = r16
            r8 = r15
            r9 = r12
            java.lang.Object r3 = r3.zza(r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x05df }
            r15 = r3
            r3 = r16
            goto L_0x0017
        L_0x0087:
            r12.zza(r10)     // Catch:{ all -> 0x05df }
            if (r15 != 0) goto L_0x0091
            java.lang.Object r4 = r12.zzq(r2)     // Catch:{ all -> 0x05df }
            r15 = r4
        L_0x0091:
            boolean r4 = r12.zza(r15, r10)     // Catch:{ all -> 0x05df }
            if (r4 != 0) goto L_0x0017
            int r3 = r1.zzoz
        L_0x0099:
            int r4 = r1.zzpa
            if (r3 >= r4) goto L_0x00a8
            int[] r4 = r1.zzoy
            r4 = r4[r3]
            java.lang.Object r15 = r1.zza(r2, r4, (UB) r15, r12)
            int r3 = r3 + 1
            goto L_0x0099
        L_0x00a8:
            if (r15 == 0) goto L_0x00ad
            r12.zzf(r2, r15)
        L_0x00ad:
            return
        L_0x00ae:
            int r5 = r1.zzap(r9)     // Catch:{ all -> 0x05df }
            r6 = 267386880(0xff00000, float:2.3665827E-29)
            r6 = r6 & r5
            int r6 = r6 >>> 20
            r7 = 1048575(0xfffff, float:1.469367E-39)
            switch(r6) {
                case 0: goto L_0x058b;
                case 1: goto L_0x057c;
                case 2: goto L_0x056d;
                case 3: goto L_0x055e;
                case 4: goto L_0x054f;
                case 5: goto L_0x0540;
                case 6: goto L_0x0531;
                case 7: goto L_0x0522;
                case 8: goto L_0x051a;
                case 9: goto L_0x04e9;
                case 10: goto L_0x04da;
                case 11: goto L_0x04cb;
                case 12: goto L_0x04a9;
                case 13: goto L_0x049a;
                case 14: goto L_0x048b;
                case 15: goto L_0x047c;
                case 16: goto L_0x046d;
                case 17: goto L_0x043c;
                case 18: goto L_0x042f;
                case 19: goto L_0x0422;
                case 20: goto L_0x0415;
                case 21: goto L_0x0408;
                case 22: goto L_0x03fb;
                case 23: goto L_0x03ee;
                case 24: goto L_0x03e1;
                case 25: goto L_0x03d4;
                case 26: goto L_0x03b4;
                case 27: goto L_0x03a3;
                case 28: goto L_0x0396;
                case 29: goto L_0x0389;
                case 30: goto L_0x0373;
                case 31: goto L_0x0366;
                case 32: goto L_0x0359;
                case 33: goto L_0x034c;
                case 34: goto L_0x033f;
                case 35: goto L_0x0332;
                case 36: goto L_0x0325;
                case 37: goto L_0x0318;
                case 38: goto L_0x030b;
                case 39: goto L_0x02fe;
                case 40: goto L_0x02f1;
                case 41: goto L_0x02e4;
                case 42: goto L_0x02d7;
                case 43: goto L_0x02ca;
                case 44: goto L_0x02b5;
                case 45: goto L_0x02a8;
                case 46: goto L_0x029b;
                case 47: goto L_0x028e;
                case 48: goto L_0x0281;
                case 49: goto L_0x026f;
                case 50: goto L_0x022d;
                case 51: goto L_0x021b;
                case 52: goto L_0x0209;
                case 53: goto L_0x01f7;
                case 54: goto L_0x01e5;
                case 55: goto L_0x01d3;
                case 56: goto L_0x01c1;
                case 57: goto L_0x01af;
                case 58: goto L_0x019d;
                case 59: goto L_0x0195;
                case 60: goto L_0x0164;
                case 61: goto L_0x0156;
                case 62: goto L_0x0144;
                case 63: goto L_0x011f;
                case 64: goto L_0x010d;
                case 65: goto L_0x00fb;
                case 66: goto L_0x00e9;
                case 67: goto L_0x00d7;
                case 68: goto L_0x00c5;
                default: goto L_0x00bd;
            }
        L_0x00bd:
            if (r15 != 0) goto L_0x059b
            java.lang.Object r4 = r12.zzfr()     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x059a
        L_0x00c5:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzej r7 = r1.zzam(r9)     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r7 = r10.zzb(r7, r11)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x00d7:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            long r7 = r19.zzcg()     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x00e9:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            int r7 = r19.zzcf()     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x00fb:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            long r7 = r19.zzce()     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x010d:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            int r7 = r19.zzcd()     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x011f:
            int r6 = r19.zzcc()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzcr r8 = r1.zzao(r9)     // Catch:{ zzcu -> 0x05b8 }
            if (r8 == 0) goto L_0x0136
            boolean r8 = r8.zzal(r6)     // Catch:{ zzcu -> 0x05b8 }
            if (r8 == 0) goto L_0x0130
            goto L_0x0136
        L_0x0130:
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzel.zza(r4, r6, r15, r12)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0386
        L_0x0136:
            r5 = r5 & r7
            long r7 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r6)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r7, r5)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0144:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            int r7 = r19.zzcb()     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0156:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzbd r7 = r19.zzca()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0164:
            boolean r6 = r1.zza((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            if (r6 == 0) goto L_0x0180
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r7 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzej r8 = r1.zzam(r9)     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r8 = r10.zza(r8, r11)     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r7 = com.google.android.gms.internal.firebase-perf.zzco.zza(r7, r8)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0190
        L_0x0180:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzej r7 = r1.zzam(r9)     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r7 = r10.zza(r7, r11)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
        L_0x0190:
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0195:
            r1.zza(r2, r5, r10)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x019d:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            boolean r7 = r19.zzby()     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x01af:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            int r7 = r19.zzbx()     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x01c1:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            long r7 = r19.zzbw()     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x01d3:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            int r7 = r19.zzbv()     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x01e5:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            long r7 = r19.zzbt()     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x01f7:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            long r7 = r19.zzbu()     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0209:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            float r7 = r19.readFloat()     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Float r7 = java.lang.Float.valueOf(r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x021b:
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            double r7 = r19.readDouble()     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Double r7 = java.lang.Double.valueOf(r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r4, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x022d:
            java.lang.Object r4 = r1.zzan(r9)     // Catch:{ zzcu -> 0x05b8 }
            int r5 = r1.zzap(r9)     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r7 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            if (r7 != 0) goto L_0x0247
            com.google.android.gms.internal.firebase-perf.zzdo r7 = r1.zzpf     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r7 = r7.zzk(r4)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r7)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x025e
        L_0x0247:
            com.google.android.gms.internal.firebase-perf.zzdo r8 = r1.zzpf     // Catch:{ zzcu -> 0x05b8 }
            boolean r8 = r8.zzi(r7)     // Catch:{ zzcu -> 0x05b8 }
            if (r8 == 0) goto L_0x025e
            com.google.android.gms.internal.firebase-perf.zzdo r8 = r1.zzpf     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r8 = r8.zzk(r4)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzdo r9 = r1.zzpf     // Catch:{ zzcu -> 0x05b8 }
            r9.zzb(r8, r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r5, r8)     // Catch:{ zzcu -> 0x05b8 }
            r7 = r8
        L_0x025e:
            com.google.android.gms.internal.firebase-perf.zzdo r5 = r1.zzpf     // Catch:{ zzcu -> 0x05b8 }
            java.util.Map r5 = r5.zzg(r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzdo r6 = r1.zzpf     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzdm r4 = r6.zzl(r4)     // Catch:{ zzcu -> 0x05b8 }
            r10.zza(r5, r4, r11)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x026f:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzej r6 = r1.zzam(r9)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzdd r7 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r7.zza(r2, r4)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzb(r4, r6, r11)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0281:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzq(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x028e:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzp(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x029b:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzo(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x02a8:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzn(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x02b5:
            com.google.android.gms.internal.firebase-perf.zzdd r6 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r7 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r5 = r6.zza(r2, r7)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzm(r5)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzcr r6 = r1.zzao(r9)     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzel.zza(r4, r5, r6, r15, r12)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0386
        L_0x02ca:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzl(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x02d7:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzi(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x02e4:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzh(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x02f1:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzg(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x02fe:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzf(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x030b:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzd(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0318:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zze(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0325:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzc(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0332:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzb(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x033f:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzq(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x034c:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzp(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0359:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzo(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0366:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzn(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0373:
            com.google.android.gms.internal.firebase-perf.zzdd r6 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r7 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r5 = r6.zza(r2, r7)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzm(r5)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzcr r6 = r1.zzao(r9)     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzel.zza(r4, r5, r6, r15, r12)     // Catch:{ zzcu -> 0x05b8 }
        L_0x0386:
            r15 = r4
            goto L_0x0017
        L_0x0389:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzl(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0396:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzk(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x03a3:
            com.google.android.gms.internal.firebase-perf.zzej r4 = r1.zzam(r9)     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzdd r7 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r5 = r7.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zza(r5, r4, r11)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x03b4:
            boolean r4 = zzar(r5)     // Catch:{ zzcu -> 0x05b8 }
            if (r4 == 0) goto L_0x03c7
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzj(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x03c7:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.readStringList(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x03d4:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzi(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x03e1:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzh(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x03ee:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzg(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x03fb:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzf(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0408:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzd(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0415:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zze(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0422:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzc(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x042f:
            com.google.android.gms.internal.firebase-perf.zzdd r4 = r1.zzpc     // Catch:{ zzcu -> 0x05b8 }
            r5 = r5 & r7
            long r5 = (long) r5     // Catch:{ zzcu -> 0x05b8 }
            java.util.List r4 = r4.zza(r2, r5)     // Catch:{ zzcu -> 0x05b8 }
            r10.zzb(r4)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x043c:
            boolean r4 = r1.zza((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            if (r4 == 0) goto L_0x045a
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r6 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r2, r4)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzej r7 = r1.zzam(r9)     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r7 = r10.zzb(r7, r11)     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r6 = com.google.android.gms.internal.firebase-perf.zzco.zza(r6, r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x045a:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzej r6 = r1.zzam(r9)     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r6 = r10.zzb(r6, r11)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x046d:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            long r6 = r19.zzcg()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x047c:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            int r6 = r19.zzcf()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zzb(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x048b:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            long r6 = r19.zzce()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x049a:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            int r6 = r19.zzcd()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zzb(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x04a9:
            int r6 = r19.zzcc()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzcr r8 = r1.zzao(r9)     // Catch:{ zzcu -> 0x05b8 }
            if (r8 == 0) goto L_0x04c0
            boolean r8 = r8.zzal(r6)     // Catch:{ zzcu -> 0x05b8 }
            if (r8 == 0) goto L_0x04ba
            goto L_0x04c0
        L_0x04ba:
            java.lang.Object r4 = com.google.android.gms.internal.firebase-perf.zzel.zza(r4, r6, r15, r12)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0386
        L_0x04c0:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zzb(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x04cb:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            int r6 = r19.zzcb()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zzb(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x04da:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzbd r6 = r19.zzca()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x04e9:
            boolean r4 = r1.zza((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            if (r4 == 0) goto L_0x0507
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r6 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r2, r4)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzej r7 = r1.zzam(r9)     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r7 = r10.zza(r7, r11)     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r6 = com.google.android.gms.internal.firebase-perf.zzco.zza(r6, r7)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0507:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzej r6 = r1.zzam(r9)     // Catch:{ zzcu -> 0x05b8 }
            java.lang.Object r6 = r10.zza(r6, r11)     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x051a:
            r1.zza(r2, r5, r10)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0522:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            boolean r6 = r19.zzby()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0531:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            int r6 = r19.zzbx()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zzb(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x0540:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            long r6 = r19.zzbw()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x054f:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            int r6 = r19.zzbv()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zzb(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x055e:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            long r6 = r19.zzbt()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x056d:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            long r6 = r19.zzbu()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x057c:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            float r6 = r19.readFloat()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x058b:
            r4 = r5 & r7
            long r4 = (long) r4     // Catch:{ zzcu -> 0x05b8 }
            double r6 = r19.readDouble()     // Catch:{ zzcu -> 0x05b8 }
            com.google.android.gms.internal.firebase-perf.zzfh.zza(r2, r4, r6)     // Catch:{ zzcu -> 0x05b8 }
            r1.zzb((T) r2, r9)     // Catch:{ zzcu -> 0x05b8 }
            goto L_0x0017
        L_0x059a:
            r15 = r4
        L_0x059b:
            boolean r4 = r12.zza(r15, r10)     // Catch:{ zzcu -> 0x05b8 }
            if (r4 != 0) goto L_0x0017
            int r3 = r1.zzoz
        L_0x05a3:
            int r4 = r1.zzpa
            if (r3 >= r4) goto L_0x05b2
            int[] r4 = r1.zzoy
            r4 = r4[r3]
            java.lang.Object r15 = r1.zza(r2, r4, (UB) r15, r12)
            int r3 = r3 + 1
            goto L_0x05a3
        L_0x05b2:
            if (r15 == 0) goto L_0x05b7
            r12.zzf(r2, r15)
        L_0x05b7:
            return
        L_0x05b8:
            r12.zza(r10)     // Catch:{ all -> 0x05df }
            if (r15 != 0) goto L_0x05c2
            java.lang.Object r4 = r12.zzq(r2)     // Catch:{ all -> 0x05df }
            r15 = r4
        L_0x05c2:
            boolean r4 = r12.zza(r15, r10)     // Catch:{ all -> 0x05df }
            if (r4 != 0) goto L_0x0017
            int r3 = r1.zzoz
        L_0x05ca:
            int r4 = r1.zzpa
            if (r3 >= r4) goto L_0x05d9
            int[] r4 = r1.zzoy
            r4 = r4[r3]
            java.lang.Object r15 = r1.zza(r2, r4, (UB) r15, r12)
            int r3 = r3 + 1
            goto L_0x05ca
        L_0x05d9:
            if (r15 == 0) goto L_0x05de
            r12.zzf(r2, r15)
        L_0x05de:
            return
        L_0x05df:
            r0 = move-exception
            r3 = r0
            int r4 = r1.zzoz
        L_0x05e3:
            int r5 = r1.zzpa
            if (r4 >= r5) goto L_0x05f2
            int[] r5 = r1.zzoy
            r5 = r5[r4]
            java.lang.Object r15 = r1.zza(r2, r5, (UB) r15, r12)
            int r4 = r4 + 1
            goto L_0x05e3
        L_0x05f2:
            if (r15 == 0) goto L_0x05f7
            r12.zzf(r2, r15)
        L_0x05f7:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzdx.zza(java.lang.Object, com.google.android.gms.internal.firebase-perf.zzei, com.google.android.gms.internal.firebase-perf.zzbz):void");
    }

    private final zzej zzam(int i) {
        int i2 = (i / 3) << 1;
        zzej zzej = (zzej) this.zzoq[i2];
        if (zzej != null) {
            return zzej;
        }
        zzej zzf = zzef.zzfa().zzf((Class) this.zzoq[i2 + 1]);
        this.zzoq[i2] = zzf;
        return zzf;
    }

    private final Object zzan(int i) {
        return this.zzoq[(i / 3) << 1];
    }

    private final zzcr zzao(int i) {
        return (zzcr) this.zzoq[((i / 3) << 1) + 1];
    }

    public final void zzc(T t) {
        for (int i = this.zzoz; i < this.zzpa; i++) {
            long zzap = (long) (zzap(this.zzoy[i]) & 1048575);
            Object zzp = zzfh.zzp(t, zzap);
            if (zzp != null) {
                zzfh.zza((Object) t, zzap, this.zzpf.zzj(zzp));
            }
        }
        int length = this.zzoy.length;
        for (int i2 = this.zzpa; i2 < length; i2++) {
            this.zzpc.zzb(t, (long) this.zzoy[i2]);
        }
        this.zzpd.zzc(t);
        if (this.zzou) {
            this.zzpe.zzc(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzfb<UT, UB> zzfb) {
        int i2 = this.zzop[i];
        Object zzp = zzfh.zzp(obj, (long) (zzap(i) & 1048575));
        if (zzp == null) {
            return ub;
        }
        zzcr zzao = zzao(i);
        if (zzao == null) {
            return ub;
        }
        return zza(i, i2, this.zzpf.zzg(zzp), zzao, ub, zzfb);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzcr zzcr, UB ub, zzfb<UT, UB> zzfb) {
        zzdm zzl = this.zzpf.zzl(zzan(i));
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (!zzcr.zzal(((Integer) entry.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzfb.zzfr();
                }
                zzbk zzk = zzbd.zzk(zzdl.zza(zzl, entry.getKey(), entry.getValue()));
                try {
                    zzdl.zza(zzk.zzbr(), zzl, entry.getKey(), entry.getValue());
                    zzfb.zza(ub, i2, zzk.zzbq());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0104, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzn(T r14) {
        /*
            r13 = this;
            r0 = 0
            r1 = -1
            r1 = 0
            r2 = -1
            r3 = 0
        L_0x0005:
            int r4 = r13.zzoz
            r5 = 1
            if (r1 >= r4) goto L_0x0108
            int[] r4 = r13.zzoy
            r4 = r4[r1]
            int[] r6 = r13.zzop
            r6 = r6[r4]
            int r7 = r13.zzap(r4)
            boolean r8 = r13.zzow
            r9 = 1048575(0xfffff, float:1.469367E-39)
            if (r8 != 0) goto L_0x0035
            int[] r8 = r13.zzop
            int r10 = r4 + 2
            r8 = r8[r10]
            r10 = r8 & r9
            int r8 = r8 >>> 20
            int r8 = r5 << r8
            if (r10 == r2) goto L_0x0036
            sun.misc.Unsafe r2 = zzoo
            long r11 = (long) r10
            int r2 = r2.getInt(r14, r11)
            r3 = r2
            r2 = r10
            goto L_0x0036
        L_0x0035:
            r8 = 0
        L_0x0036:
            r10 = 268435456(0x10000000, float:2.5243549E-29)
            r10 = r10 & r7
            if (r10 == 0) goto L_0x003d
            r10 = 1
            goto L_0x003e
        L_0x003d:
            r10 = 0
        L_0x003e:
            if (r10 == 0) goto L_0x0047
            boolean r10 = r13.zza((T) r14, r4, r3, r8)
            if (r10 != 0) goto L_0x0047
            return r0
        L_0x0047:
            r10 = 267386880(0xff00000, float:2.3665827E-29)
            r10 = r10 & r7
            int r10 = r10 >>> 20
            r11 = 9
            if (r10 == r11) goto L_0x00f3
            r11 = 17
            if (r10 == r11) goto L_0x00f3
            r8 = 27
            if (r10 == r8) goto L_0x00c7
            r8 = 60
            if (r10 == r8) goto L_0x00b6
            r8 = 68
            if (r10 == r8) goto L_0x00b6
            switch(r10) {
                case 49: goto L_0x00c7;
                case 50: goto L_0x0065;
                default: goto L_0x0063;
            }
        L_0x0063:
            goto L_0x0104
        L_0x0065:
            com.google.android.gms.internal.firebase-perf.zzdo r6 = r13.zzpf
            r7 = r7 & r9
            long r7 = (long) r7
            java.lang.Object r7 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r7)
            java.util.Map r6 = r6.zzh(r7)
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L_0x00b3
            java.lang.Object r4 = r13.zzan(r4)
            com.google.android.gms.internal.firebase-perf.zzdo r7 = r13.zzpf
            com.google.android.gms.internal.firebase-perf.zzdm r4 = r7.zzl(r4)
            com.google.android.gms.internal.firebase-perf.zzfq r4 = r4.zzoi
            com.google.android.gms.internal.firebase-perf.zzfv r4 = r4.zzgc()
            com.google.android.gms.internal.firebase-perf.zzfv r7 = com.google.android.gms.internal.firebase-perf.zzfv.MESSAGE
            if (r4 != r7) goto L_0x00b3
            r4 = 0
            java.util.Collection r6 = r6.values()
            java.util.Iterator r6 = r6.iterator()
        L_0x0094:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00b3
            java.lang.Object r7 = r6.next()
            if (r4 != 0) goto L_0x00ac
            com.google.android.gms.internal.firebase-perf.zzef r4 = com.google.android.gms.internal.firebase-perf.zzef.zzfa()
            java.lang.Class r8 = r7.getClass()
            com.google.android.gms.internal.firebase-perf.zzej r4 = r4.zzf(r8)
        L_0x00ac:
            boolean r7 = r4.zzn(r7)
            if (r7 != 0) goto L_0x0094
            r5 = 0
        L_0x00b3:
            if (r5 != 0) goto L_0x0104
            return r0
        L_0x00b6:
            boolean r5 = r13.zza((T) r14, r6, r4)
            if (r5 == 0) goto L_0x0104
            com.google.android.gms.internal.firebase-perf.zzej r4 = r13.zzam(r4)
            boolean r4 = zza(r14, r7, r4)
            if (r4 != 0) goto L_0x0104
            return r0
        L_0x00c7:
            r6 = r7 & r9
            long r6 = (long) r6
            java.lang.Object r6 = com.google.android.gms.internal.firebase-perf.zzfh.zzp(r14, r6)
            java.util.List r6 = (java.util.List) r6
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L_0x00f0
            com.google.android.gms.internal.firebase-perf.zzej r4 = r13.zzam(r4)
            r7 = 0
        L_0x00db:
            int r8 = r6.size()
            if (r7 >= r8) goto L_0x00f0
            java.lang.Object r8 = r6.get(r7)
            boolean r8 = r4.zzn(r8)
            if (r8 != 0) goto L_0x00ed
            r5 = 0
            goto L_0x00f0
        L_0x00ed:
            int r7 = r7 + 1
            goto L_0x00db
        L_0x00f0:
            if (r5 != 0) goto L_0x0104
            return r0
        L_0x00f3:
            boolean r5 = r13.zza((T) r14, r4, r3, r8)
            if (r5 == 0) goto L_0x0104
            com.google.android.gms.internal.firebase-perf.zzej r4 = r13.zzam(r4)
            boolean r4 = zza(r14, r7, r4)
            if (r4 != 0) goto L_0x0104
            return r0
        L_0x0104:
            int r1 = r1 + 1
            goto L_0x0005
        L_0x0108:
            boolean r1 = r13.zzou
            if (r1 == 0) goto L_0x0119
            com.google.android.gms.internal.firebase-perf.zzca<?> r1 = r13.zzpe
            com.google.android.gms.internal.firebase-perf.zzcd r14 = r1.zza(r14)
            boolean r14 = r14.isInitialized()
            if (r14 != 0) goto L_0x0119
            return r0
        L_0x0119:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzdx.zzn(java.lang.Object):boolean");
    }

    private static boolean zza(Object obj, int i, zzej zzej) {
        return zzej.zzn(zzfh.zzp(obj, (long) (i & 1048575)));
    }

    private static void zza(int i, Object obj, zzfw zzfw) throws IOException {
        if (obj instanceof String) {
            zzfw.zza(i, (String) obj);
        } else {
            zzfw.zza(i, (zzbd) obj);
        }
    }

    private final void zza(Object obj, int i, zzei zzei) throws IOException {
        if (zzar(i)) {
            zzfh.zza(obj, (long) (i & 1048575), (Object) zzei.zzbz());
        } else if (this.zzov) {
            zzfh.zza(obj, (long) (i & 1048575), (Object) zzei.readString());
        } else {
            zzfh.zza(obj, (long) (i & 1048575), (Object) zzei.zzca());
        }
    }

    private final int zzap(int i) {
        return this.zzop[i + 1];
    }

    private final int zzaq(int i) {
        return this.zzop[i + 2];
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzfh.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzfh.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzfh.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzfh.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzfh.zzp(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzow) {
            return zza(t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zza(T t, int i) {
        if (this.zzow) {
            int zzap = zzap(i);
            long j = (long) (zzap & 1048575);
            switch ((zzap & 267386880) >>> 20) {
                case 0:
                    return zzfh.zzo(t, j) != 0.0d;
                case 1:
                    return zzfh.zzn(t, j) != 0.0f;
                case 2:
                    return zzfh.zzl(t, j) != 0;
                case 3:
                    return zzfh.zzl(t, j) != 0;
                case 4:
                    return zzfh.zzk(t, j) != 0;
                case 5:
                    return zzfh.zzl(t, j) != 0;
                case 6:
                    return zzfh.zzk(t, j) != 0;
                case 7:
                    return zzfh.zzm(t, j);
                case 8:
                    Object zzp = zzfh.zzp(t, j);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    }
                    if (zzp instanceof zzbd) {
                        return !zzbd.zzho.equals(zzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzfh.zzp(t, j) != null;
                case 10:
                    return !zzbd.zzho.equals(zzfh.zzp(t, j));
                case 11:
                    return zzfh.zzk(t, j) != 0;
                case 12:
                    return zzfh.zzk(t, j) != 0;
                case 13:
                    return zzfh.zzk(t, j) != 0;
                case 14:
                    return zzfh.zzl(t, j) != 0;
                case 15:
                    return zzfh.zzk(t, j) != 0;
                case 16:
                    return zzfh.zzl(t, j) != 0;
                case 17:
                    return zzfh.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zzaq = zzaq(i);
            return (zzfh.zzk(t, (long) (zzaq & 1048575)) & (1 << (zzaq >>> 20))) != 0;
        }
    }

    private final void zzb(T t, int i) {
        if (!this.zzow) {
            int zzaq = zzaq(i);
            long j = (long) (zzaq & 1048575);
            zzfh.zzb((Object) t, j, zzfh.zzk(t, j) | (1 << (zzaq >>> 20)));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzfh.zzk(t, (long) (zzaq(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzfh.zzb((Object) t, (long) (zzaq(i2) & 1048575), i);
    }
}
