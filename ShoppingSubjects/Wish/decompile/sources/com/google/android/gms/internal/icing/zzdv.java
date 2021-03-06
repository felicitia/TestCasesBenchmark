package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

final class zzdv<T> implements zzef<T> {
    private static final int[] zzjw = new int[0];
    private static final Unsafe zzjx = zzfd.zzcu();
    private final int[] zzjy;
    private final Object[] zzjz;
    private final int zzka;
    private final int zzkb;
    private final zzdr zzkc;
    private final boolean zzkd;
    private final boolean zzke;
    private final boolean zzkf;
    private final boolean zzkg;
    private final int[] zzkh;
    private final int zzki;
    private final int zzkj;
    private final zzdy zzkk;
    private final zzdc zzkl;
    private final zzex<?, ?> zzkm;
    private final zzca<?> zzkn;
    private final zzdm zzko;

    private zzdv(int[] iArr, Object[] objArr, int i, int i2, zzdr zzdr, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzdy zzdy, zzdc zzdc, zzex<?, ?> zzex, zzca<?> zzca, zzdm zzdm) {
        this.zzjy = iArr;
        this.zzjz = objArr;
        this.zzka = i;
        this.zzkb = i2;
        this.zzke = zzdr instanceof zzck;
        this.zzkf = z;
        this.zzkd = zzca != null && zzca.zze(zzdr);
        this.zzkg = false;
        this.zzkh = iArr2;
        this.zzki = i3;
        this.zzkj = i4;
        this.zzkk = zzdy;
        this.zzkl = zzdc;
        this.zzkm = zzex;
        this.zzkn = zzca;
        this.zzkc = zzdr;
        this.zzko = zzdm;
    }

    private static <UT, UB> int zza(zzex<UT, UB> zzex, T t) {
        return zzex.zzj(zzex.zzm(t));
    }

    /* JADX WARNING: Removed duplicated region for block: B:167:0x03ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.gms.internal.icing.zzdv<T> zza(java.lang.Class<T> r40, com.google.android.gms.internal.icing.zzdp r41, com.google.android.gms.internal.icing.zzdy r42, com.google.android.gms.internal.icing.zzdc r43, com.google.android.gms.internal.icing.zzex<?, ?> r44, com.google.android.gms.internal.icing.zzca<?> r45, com.google.android.gms.internal.icing.zzdm r46) {
        /*
            r0 = r41
            boolean r1 = r0 instanceof com.google.android.gms.internal.icing.zzee
            if (r1 == 0) goto L_0x0484
            com.google.android.gms.internal.icing.zzee r0 = (com.google.android.gms.internal.icing.zzee) r0
            int r1 = r0.zzbs()
            int r2 = com.google.android.gms.internal.icing.zzck.zzd.zzic
            r3 = 0
            r4 = 1
            if (r1 != r2) goto L_0x0014
            r11 = 1
            goto L_0x0015
        L_0x0014:
            r11 = 0
        L_0x0015:
            java.lang.String r1 = r0.zzcb()
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
            int[] r9 = zzjw
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
            sun.misc.Unsafe r6 = zzjx
            java.lang.Object[] r17 = r0.zzcc()
            com.google.android.gms.internal.icing.zzdr r7 = r0.zzbu()
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
            com.google.android.gms.internal.icing.zzcg r14 = com.google.android.gms.internal.icing.zzcg.MAP
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
            com.google.android.gms.internal.icing.zzcg r14 = com.google.android.gms.internal.icing.zzcg.MESSAGE
            int r14 = r14.id()
            int r14 = r14 + 51
            if (r3 == r14) goto L_0x02b1
            com.google.android.gms.internal.icing.zzcg r14 = com.google.android.gms.internal.icing.zzcg.GROUP
            int r14 = r14.id()
            int r14 = r14 + 51
            if (r3 != r14) goto L_0x028d
            goto L_0x02b1
        L_0x028d:
            com.google.android.gms.internal.icing.zzcg r14 = com.google.android.gms.internal.icing.zzcg.ENUM
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
            com.google.android.gms.internal.icing.zzcg r14 = com.google.android.gms.internal.icing.zzcg.MESSAGE
            int r14 = r14.id()
            if (r3 == r14) goto L_0x03a1
            com.google.android.gms.internal.icing.zzcg r14 = com.google.android.gms.internal.icing.zzcg.GROUP
            int r14 = r14.id()
            if (r3 != r14) goto L_0x0321
            goto L_0x03a1
        L_0x0321:
            com.google.android.gms.internal.icing.zzcg r14 = com.google.android.gms.internal.icing.zzcg.MESSAGE_LIST
            int r14 = r14.id()
            if (r3 == r14) goto L_0x0391
            com.google.android.gms.internal.icing.zzcg r14 = com.google.android.gms.internal.icing.zzcg.GROUP_LIST
            int r14 = r14.id()
            if (r3 != r14) goto L_0x0332
            goto L_0x0391
        L_0x0332:
            com.google.android.gms.internal.icing.zzcg r14 = com.google.android.gms.internal.icing.zzcg.ENUM
            int r14 = r14.id()
            if (r3 == r14) goto L_0x037f
            com.google.android.gms.internal.icing.zzcg r14 = com.google.android.gms.internal.icing.zzcg.ENUM_LIST
            int r14 = r14.id()
            if (r3 == r14) goto L_0x037f
            com.google.android.gms.internal.icing.zzcg r14 = com.google.android.gms.internal.icing.zzcg.ENUM_LIST_PACKED
            int r14 = r14.id()
            if (r3 != r14) goto L_0x034b
            goto L_0x037f
        L_0x034b:
            com.google.android.gms.internal.icing.zzcg r14 = com.google.android.gms.internal.icing.zzcg.MAP
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
            com.google.android.gms.internal.icing.zzcg r10 = com.google.android.gms.internal.icing.zzcg.GROUP
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
            com.google.android.gms.internal.icing.zzdv r1 = new com.google.android.gms.internal.icing.zzdv
            com.google.android.gms.internal.icing.zzdr r10 = r0.zzbu()
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
            com.google.android.gms.internal.icing.zzes r0 = (com.google.android.gms.internal.icing.zzes) r0
            r0.zzbs()
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzdv.zza(java.lang.Class, com.google.android.gms.internal.icing.zzdp, com.google.android.gms.internal.icing.zzdy, com.google.android.gms.internal.icing.zzdc, com.google.android.gms.internal.icing.zzex, com.google.android.gms.internal.icing.zzca, com.google.android.gms.internal.icing.zzdm):com.google.android.gms.internal.icing.zzdv");
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

    private static void zza(int i, Object obj, zzfr zzfr) throws IOException {
        if (obj instanceof String) {
            zzfr.zza(i, (String) obj);
        } else {
            zzfr.zza(i, (zzbi) obj);
        }
    }

    private static <UT, UB> void zza(zzex<UT, UB> zzex, T t, zzfr zzfr) throws IOException {
        zzex.zza(zzex.zzm(t), zzfr);
    }

    private final <K, V> void zza(zzfr zzfr, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzfr.zza(i, this.zzko.zzi(zzab(i2)), this.zzko.zzg(obj));
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzac = (long) (zzac(i) & 1048575);
        if (zza(t2, i)) {
            Object zzo = zzfd.zzo(t, zzac);
            Object zzo2 = zzfd.zzo(t2, zzac);
            if (zzo == null || zzo2 == null) {
                if (zzo2 != null) {
                    zzfd.zza((Object) t, zzac, zzo2);
                    zzb(t, i);
                }
                return;
            }
            zzfd.zza((Object) t, zzac, zzcm.zza(zzo, zzo2));
            zzb(t, i);
        }
    }

    private final boolean zza(T t, int i) {
        if (this.zzkf) {
            int zzac = zzac(i);
            long j = (long) (zzac & 1048575);
            switch ((zzac & 267386880) >>> 20) {
                case 0:
                    return zzfd.zzn(t, j) != 0.0d;
                case 1:
                    return zzfd.zzm(t, j) != 0.0f;
                case 2:
                    return zzfd.zzk(t, j) != 0;
                case 3:
                    return zzfd.zzk(t, j) != 0;
                case 4:
                    return zzfd.zzj(t, j) != 0;
                case 5:
                    return zzfd.zzk(t, j) != 0;
                case 6:
                    return zzfd.zzj(t, j) != 0;
                case 7:
                    return zzfd.zzl(t, j);
                case 8:
                    Object zzo = zzfd.zzo(t, j);
                    if (zzo instanceof String) {
                        return !((String) zzo).isEmpty();
                    }
                    if (zzo instanceof zzbi) {
                        return !zzbi.zzdq.equals(zzo);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzfd.zzo(t, j) != null;
                case 10:
                    return !zzbi.zzdq.equals(zzfd.zzo(t, j));
                case 11:
                    return zzfd.zzj(t, j) != 0;
                case 12:
                    return zzfd.zzj(t, j) != 0;
                case 13:
                    return zzfd.zzj(t, j) != 0;
                case 14:
                    return zzfd.zzk(t, j) != 0;
                case 15:
                    return zzfd.zzj(t, j) != 0;
                case 16:
                    return zzfd.zzk(t, j) != 0;
                case 17:
                    return zzfd.zzo(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zzad = zzad(i);
            return (zzfd.zzj(t, (long) (zzad & 1048575)) & (1 << (zzad >>> 20))) != 0;
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzfd.zzj(t, (long) (zzad(i2) & 1048575)) == i;
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        return this.zzkf ? zza(t, i) : (i2 & i3) != 0;
    }

    private static boolean zza(Object obj, int i, zzef zzef) {
        return zzef.zzk(zzfd.zzo(obj, (long) (i & 1048575)));
    }

    private final zzef zzaa(int i) {
        int i2 = (i / 3) << 1;
        zzef zzef = (zzef) this.zzjz[i2];
        if (zzef != null) {
            return zzef;
        }
        zzef zze = zzec.zzbz().zze((Class) this.zzjz[i2 + 1]);
        this.zzjz[i2] = zze;
        return zze;
    }

    private final Object zzab(int i) {
        return this.zzjz[(i / 3) << 1];
    }

    private final int zzac(int i) {
        return this.zzjy[i + 1];
    }

    private final int zzad(int i) {
        return this.zzjy[i + 2];
    }

    private final void zzb(T t, int i) {
        if (!this.zzkf) {
            int zzad = zzad(i);
            long j = (long) (zzad & 1048575);
            zzfd.zza((Object) t, j, zzfd.zzj(t, j) | (1 << (zzad >>> 20)));
        }
    }

    private final void zzb(T t, int i, int i2) {
        zzfd.zza((Object) t, (long) (zzad(i2) & 1048575), i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x02bc, code lost:
        com.google.android.gms.internal.icing.zzeh.zze(r9, r4, r2, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x02c8, code lost:
        com.google.android.gms.internal.icing.zzeh.zzj(r9, (java.util.List) r8.getObject(r1, r4), r2, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x02da, code lost:
        com.google.android.gms.internal.icing.zzeh.zzg(r9, (java.util.List) r8.getObject(r1, r4), r2, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x02ec, code lost:
        com.google.android.gms.internal.icing.zzeh.zzl(r9, (java.util.List) r8.getObject(r1, r4), r2, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x02fe, code lost:
        com.google.android.gms.internal.icing.zzeh.zzm(r9, (java.util.List) r8.getObject(r1, r4), r2, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0310, code lost:
        com.google.android.gms.internal.icing.zzeh.zzi(r9, (java.util.List) r8.getObject(r1, r4), r2, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x04f1, code lost:
        r5 = r12 + 3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x04f7  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r21, com.google.android.gms.internal.icing.zzfr r22) throws java.io.IOException {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r22
            boolean r3 = r0.zzkd
            if (r3 == 0) goto L_0x0021
            com.google.android.gms.internal.icing.zzca<?> r3 = r0.zzkn
            com.google.android.gms.internal.icing.zzcd r3 = r3.zza(r1)
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
            int[] r7 = r0.zzjy
            int r7 = r7.length
            sun.misc.Unsafe r8 = zzjx
            r10 = r5
            r5 = 0
            r11 = 0
        L_0x002c:
            if (r5 >= r7) goto L_0x04f5
            int r12 = r0.zzac(r5)
            int[] r13 = r0.zzjy
            r13 = r13[r5]
            r14 = 267386880(0xff00000, float:2.3665827E-29)
            r14 = r14 & r12
            int r14 = r14 >>> 20
            boolean r15 = r0.zzkf
            r16 = 1048575(0xfffff, float:1.469367E-39)
            if (r15 != 0) goto L_0x0061
            r15 = 17
            if (r14 > r15) goto L_0x0061
            int[] r15 = r0.zzjy
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
            com.google.android.gms.internal.icing.zzca<?> r4 = r0.zzkn
            int r4 = r4.zza(r10)
            if (r4 > r13) goto L_0x0083
            com.google.android.gms.internal.icing.zzca<?> r4 = r0.zzkn
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
                case 0: goto L_0x04e4;
                case 1: goto L_0x04d6;
                case 2: goto L_0x04c8;
                case 3: goto L_0x04ba;
                case 4: goto L_0x04ac;
                case 5: goto L_0x049e;
                case 6: goto L_0x0490;
                case 7: goto L_0x0482;
                case 8: goto L_0x0473;
                case 9: goto L_0x0460;
                case 10: goto L_0x044f;
                case 11: goto L_0x0440;
                case 12: goto L_0x0431;
                case 13: goto L_0x0422;
                case 14: goto L_0x0413;
                case 15: goto L_0x0404;
                case 16: goto L_0x03f5;
                case 17: goto L_0x03e2;
                case 18: goto L_0x03d0;
                case 19: goto L_0x03be;
                case 20: goto L_0x03ac;
                case 21: goto L_0x039a;
                case 22: goto L_0x0388;
                case 23: goto L_0x0376;
                case 24: goto L_0x0364;
                case 25: goto L_0x0352;
                case 26: goto L_0x0341;
                case 27: goto L_0x032c;
                case 28: goto L_0x031b;
                case 29: goto L_0x0309;
                case 30: goto L_0x02f7;
                case 31: goto L_0x02e5;
                case 32: goto L_0x02d3;
                case 33: goto L_0x02c1;
                case 34: goto L_0x02af;
                case 35: goto L_0x029d;
                case 36: goto L_0x028b;
                case 37: goto L_0x0279;
                case 38: goto L_0x0267;
                case 39: goto L_0x0255;
                case 40: goto L_0x0243;
                case 41: goto L_0x0231;
                case 42: goto L_0x021f;
                case 43: goto L_0x0216;
                case 44: goto L_0x020d;
                case 45: goto L_0x0204;
                case 46: goto L_0x01fb;
                case 47: goto L_0x01f2;
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
            goto L_0x04f1
        L_0x008e:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            java.lang.Object r4 = r8.getObject(r1, r4)
            com.google.android.gms.internal.icing.zzef r5 = r0.zzaa(r12)
            r2.zzb(r13, r4, r5)
            goto L_0x008b
        L_0x00a2:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            long r4 = zzh(r1, r4)
            r2.zzb(r13, r4)
            goto L_0x008b
        L_0x00b2:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            int r4 = zzg(r1, r4)
            r2.zze(r13, r4)
            goto L_0x008b
        L_0x00c2:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            long r4 = zzh(r1, r4)
            r2.zzj(r13, r4)
            goto L_0x008b
        L_0x00d2:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            int r4 = zzg(r1, r4)
            r2.zzm(r13, r4)
            goto L_0x008b
        L_0x00e2:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            int r4 = zzg(r1, r4)
            r2.zzn(r13, r4)
            goto L_0x008b
        L_0x00f2:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            int r4 = zzg(r1, r4)
            r2.zzd(r13, r4)
            goto L_0x008b
        L_0x0102:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            java.lang.Object r4 = r8.getObject(r1, r4)
            com.google.android.gms.internal.icing.zzbi r4 = (com.google.android.gms.internal.icing.zzbi) r4
            r2.zza(r13, r4)
            goto L_0x008b
        L_0x0115:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            java.lang.Object r4 = r8.getObject(r1, r4)
            com.google.android.gms.internal.icing.zzef r5 = r0.zzaa(r12)
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
            boolean r4 = zzi(r1, r4)
            r2.zza(r13, r4)
            goto L_0x008b
        L_0x014c:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            int r4 = zzg(r1, r4)
            r2.zzf(r13, r4)
            goto L_0x008b
        L_0x015d:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            long r4 = zzh(r1, r4)
            r2.zzc(r13, r4)
            goto L_0x008b
        L_0x016e:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            int r4 = zzg(r1, r4)
            r2.zzc(r13, r4)
            goto L_0x008b
        L_0x017f:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            long r4 = zzh(r1, r4)
            r2.zza(r13, r4)
            goto L_0x008b
        L_0x0190:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            long r4 = zzh(r1, r4)
            r2.zzi(r13, r4)
            goto L_0x008b
        L_0x01a1:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            float r4 = zzf(r1, r4)
            r2.zza(r13, r4)
            goto L_0x008b
        L_0x01b2:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            double r4 = zze(r1, r4)
            r2.zza(r13, r4)
            goto L_0x008b
        L_0x01c3:
            r12 = r18
            java.lang.Object r4 = r8.getObject(r1, r4)
            r0.zza(r2, r13, r4, r12)
            goto L_0x008b
        L_0x01ce:
            r12 = r18
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzef r5 = r0.zzaa(r12)
            com.google.android.gms.internal.icing.zzeh.zzb(r9, r4, r2, r5)
            goto L_0x008b
        L_0x01e3:
            r12 = r18
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            r13 = 1
            goto L_0x02bc
        L_0x01f2:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            goto L_0x02c8
        L_0x01fb:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            goto L_0x02da
        L_0x0204:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            goto L_0x02ec
        L_0x020d:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            goto L_0x02fe
        L_0x0216:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            goto L_0x0310
        L_0x021f:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzn(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0231:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzk(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0243:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzf(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0255:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzh(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0267:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzd(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0279:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzc(r9, r4, r2, r13)
            goto L_0x008b
        L_0x028b:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzb(r9, r4, r2, r13)
            goto L_0x008b
        L_0x029d:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zza(r9, r4, r2, r13)
            goto L_0x008b
        L_0x02af:
            r12 = r18
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            r13 = 0
        L_0x02bc:
            com.google.android.gms.internal.icing.zzeh.zze(r9, r4, r2, r13)
            goto L_0x008b
        L_0x02c1:
            r12 = r18
            r13 = 0
            int[] r9 = r0.zzjy
            r9 = r9[r12]
        L_0x02c8:
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzj(r9, r4, r2, r13)
            goto L_0x008b
        L_0x02d3:
            r12 = r18
            r13 = 0
            int[] r9 = r0.zzjy
            r9 = r9[r12]
        L_0x02da:
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzg(r9, r4, r2, r13)
            goto L_0x008b
        L_0x02e5:
            r12 = r18
            r13 = 0
            int[] r9 = r0.zzjy
            r9 = r9[r12]
        L_0x02ec:
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzl(r9, r4, r2, r13)
            goto L_0x008b
        L_0x02f7:
            r12 = r18
            r13 = 0
            int[] r9 = r0.zzjy
            r9 = r9[r12]
        L_0x02fe:
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzm(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0309:
            r12 = r18
            r13 = 0
            int[] r9 = r0.zzjy
            r9 = r9[r12]
        L_0x0310:
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzi(r9, r4, r2, r13)
            goto L_0x008b
        L_0x031b:
            r12 = r18
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzb(r9, r4, r2)
            goto L_0x008b
        L_0x032c:
            r12 = r18
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzef r5 = r0.zzaa(r12)
            com.google.android.gms.internal.icing.zzeh.zza(r9, r4, r2, r5)
            goto L_0x008b
        L_0x0341:
            r12 = r18
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zza(r9, r4, r2)
            goto L_0x008b
        L_0x0352:
            r12 = r18
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            r14 = 0
            com.google.android.gms.internal.icing.zzeh.zzn(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x0364:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzk(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x0376:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzf(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x0388:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzh(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x039a:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzd(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x03ac:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzc(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x03be:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zzb(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x03d0:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzjy
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzeh.zza(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x03e2:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            java.lang.Object r4 = r8.getObject(r1, r4)
            com.google.android.gms.internal.icing.zzef r5 = r0.zzaa(r12)
            r2.zzb(r13, r4, r5)
            goto L_0x04f1
        L_0x03f5:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            long r4 = r8.getLong(r1, r4)
            r2.zzb(r13, r4)
            goto L_0x04f1
        L_0x0404:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            int r4 = r8.getInt(r1, r4)
            r2.zze(r13, r4)
            goto L_0x04f1
        L_0x0413:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            long r4 = r8.getLong(r1, r4)
            r2.zzj(r13, r4)
            goto L_0x04f1
        L_0x0422:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            int r4 = r8.getInt(r1, r4)
            r2.zzm(r13, r4)
            goto L_0x04f1
        L_0x0431:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            int r4 = r8.getInt(r1, r4)
            r2.zzn(r13, r4)
            goto L_0x04f1
        L_0x0440:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            int r4 = r8.getInt(r1, r4)
            r2.zzd(r13, r4)
            goto L_0x04f1
        L_0x044f:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            java.lang.Object r4 = r8.getObject(r1, r4)
            com.google.android.gms.internal.icing.zzbi r4 = (com.google.android.gms.internal.icing.zzbi) r4
            r2.zza(r13, r4)
            goto L_0x04f1
        L_0x0460:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            java.lang.Object r4 = r8.getObject(r1, r4)
            com.google.android.gms.internal.icing.zzef r5 = r0.zzaa(r12)
            r2.zza(r13, r4, r5)
            goto L_0x04f1
        L_0x0473:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            java.lang.Object r4 = r8.getObject(r1, r4)
            zza(r13, r4, r2)
            goto L_0x04f1
        L_0x0482:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            boolean r4 = com.google.android.gms.internal.icing.zzfd.zzl(r1, r4)
            r2.zza(r13, r4)
            goto L_0x04f1
        L_0x0490:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            int r4 = r8.getInt(r1, r4)
            r2.zzf(r13, r4)
            goto L_0x04f1
        L_0x049e:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            long r4 = r8.getLong(r1, r4)
            r2.zzc(r13, r4)
            goto L_0x04f1
        L_0x04ac:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            int r4 = r8.getInt(r1, r4)
            r2.zzc(r13, r4)
            goto L_0x04f1
        L_0x04ba:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            long r4 = r8.getLong(r1, r4)
            r2.zza(r13, r4)
            goto L_0x04f1
        L_0x04c8:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            long r4 = r8.getLong(r1, r4)
            r2.zzi(r13, r4)
            goto L_0x04f1
        L_0x04d6:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            float r4 = com.google.android.gms.internal.icing.zzfd.zzm(r1, r4)
            r2.zza(r13, r4)
            goto L_0x04f1
        L_0x04e4:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            double r4 = com.google.android.gms.internal.icing.zzfd.zzn(r1, r4)
            r2.zza(r13, r4)
        L_0x04f1:
            int r5 = r12 + 3
            goto L_0x002c
        L_0x04f5:
            if (r10 == 0) goto L_0x050c
            com.google.android.gms.internal.icing.zzca<?> r4 = r0.zzkn
            r4.zza(r2, r10)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x050a
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r10 = r4
            goto L_0x04f5
        L_0x050a:
            r10 = 0
            goto L_0x04f5
        L_0x050c:
            com.google.android.gms.internal.icing.zzex<?, ?> r3 = r0.zzkm
            zza(r3, (T) r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzdv.zzb(java.lang.Object, com.google.android.gms.internal.icing.zzfr):void");
    }

    private final void zzb(T t, T t2, int i) {
        int zzac = zzac(i);
        int i2 = this.zzjy[i];
        long j = (long) (zzac & 1048575);
        if (zza(t2, i2, i)) {
            Object zzo = zzfd.zzo(t, j);
            Object zzo2 = zzfd.zzo(t2, j);
            if (zzo == null || zzo2 == null) {
                if (zzo2 != null) {
                    zzfd.zza((Object) t, j, zzo2);
                    zzb(t, i2, i);
                }
                return;
            }
            zzfd.zza((Object) t, j, zzcm.zza(zzo, zzo2));
            zzb(t, i2, i);
        }
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private static <E> List<E> zzd(Object obj, long j) {
        return (List) zzfd.zzo(obj, j);
    }

    private static <T> double zze(T t, long j) {
        return ((Double) zzfd.zzo(t, j)).doubleValue();
    }

    private static <T> float zzf(T t, long j) {
        return ((Float) zzfd.zzo(t, j)).floatValue();
    }

    private static <T> int zzg(T t, long j) {
        return ((Integer) zzfd.zzo(t, j)).intValue();
    }

    private static <T> long zzh(T t, long j) {
        return ((Long) zzfd.zzo(t, j)).longValue();
    }

    private static <T> boolean zzi(T t, long j) {
        return ((Boolean) zzfd.zzo(t, j)).booleanValue();
    }

    /* JADX INFO: used method not loaded: com.google.android.gms.internal.icing.zzeh.zzd(java.lang.Object, java.lang.Object):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005c, code lost:
        if (com.google.android.gms.internal.icing.zzeh.zzd(com.google.android.gms.internal.icing.zzfd.zzo(r10, r6), com.google.android.gms.internal.icing.zzfd.zzo(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0070, code lost:
        if (com.google.android.gms.internal.icing.zzfd.zzk(r10, r6) == com.google.android.gms.internal.icing.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0082, code lost:
        if (com.google.android.gms.internal.icing.zzfd.zzj(r10, r6) == com.google.android.gms.internal.icing.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0096, code lost:
        if (com.google.android.gms.internal.icing.zzfd.zzk(r10, r6) == com.google.android.gms.internal.icing.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a8, code lost:
        if (com.google.android.gms.internal.icing.zzfd.zzj(r10, r6) == com.google.android.gms.internal.icing.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ba, code lost:
        if (com.google.android.gms.internal.icing.zzfd.zzj(r10, r6) == com.google.android.gms.internal.icing.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00cc, code lost:
        if (com.google.android.gms.internal.icing.zzfd.zzj(r10, r6) == com.google.android.gms.internal.icing.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e2, code lost:
        if (com.google.android.gms.internal.icing.zzeh.zzd(com.google.android.gms.internal.icing.zzfd.zzo(r10, r6), com.google.android.gms.internal.icing.zzfd.zzo(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00f8, code lost:
        if (com.google.android.gms.internal.icing.zzeh.zzd(com.google.android.gms.internal.icing.zzfd.zzo(r10, r6), com.google.android.gms.internal.icing.zzfd.zzo(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010e, code lost:
        if (com.google.android.gms.internal.icing.zzeh.zzd(com.google.android.gms.internal.icing.zzfd.zzo(r10, r6), com.google.android.gms.internal.icing.zzfd.zzo(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0120, code lost:
        if (com.google.android.gms.internal.icing.zzfd.zzl(r10, r6) == com.google.android.gms.internal.icing.zzfd.zzl(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0132, code lost:
        if (com.google.android.gms.internal.icing.zzfd.zzj(r10, r6) == com.google.android.gms.internal.icing.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0145, code lost:
        if (com.google.android.gms.internal.icing.zzfd.zzk(r10, r6) == com.google.android.gms.internal.icing.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0156, code lost:
        if (com.google.android.gms.internal.icing.zzfd.zzj(r10, r6) == com.google.android.gms.internal.icing.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0169, code lost:
        if (com.google.android.gms.internal.icing.zzfd.zzk(r10, r6) == com.google.android.gms.internal.icing.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x017c, code lost:
        if (com.google.android.gms.internal.icing.zzfd.zzk(r10, r6) == com.google.android.gms.internal.icing.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x018d, code lost:
        if (com.google.android.gms.internal.icing.zzfd.zzj(r10, r6) == com.google.android.gms.internal.icing.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01a0, code lost:
        if (com.google.android.gms.internal.icing.zzfd.zzk(r10, r6) == com.google.android.gms.internal.icing.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01a2, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
        if (com.google.android.gms.internal.icing.zzeh.zzd(com.google.android.gms.internal.icing.zzfd.zzo(r10, r6), com.google.android.gms.internal.icing.zzfd.zzo(r11, r6)) != false) goto L_0x01a3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(T r10, T r11) {
        /*
            r9 = this;
            int[] r0 = r9.zzjy
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            r3 = 1
            if (r2 >= r0) goto L_0x01aa
            int r4 = r9.zzac(r2)
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
            int r4 = r9.zzad(r2)
            r4 = r4 & r5
            long r4 = (long) r4
            int r8 = com.google.android.gms.internal.icing.zzfd.zzj(r10, r4)
            int r4 = com.google.android.gms.internal.icing.zzfd.zzj(r11, r4)
            if (r8 != r4) goto L_0x01a2
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzfd.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzfd.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.icing.zzeh.zzd(r4, r5)
            if (r4 != 0) goto L_0x01a3
            goto L_0x018f
        L_0x003c:
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzfd.zzo(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzfd.zzo(r11, r6)
            boolean r3 = com.google.android.gms.internal.icing.zzeh.zzd(r3, r4)
            goto L_0x01a3
        L_0x004a:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzfd.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzfd.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.icing.zzeh.zzd(r4, r5)
            if (r4 != 0) goto L_0x01a3
            goto L_0x01a2
        L_0x0060:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.icing.zzfd.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.icing.zzfd.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
            goto L_0x018f
        L_0x0074:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.icing.zzfd.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.icing.zzfd.zzj(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x01a2
        L_0x0086:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.icing.zzfd.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.icing.zzfd.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
            goto L_0x018f
        L_0x009a:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.icing.zzfd.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.icing.zzfd.zzj(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x01a2
        L_0x00ac:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.icing.zzfd.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.icing.zzfd.zzj(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x018f
        L_0x00be:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.icing.zzfd.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.icing.zzfd.zzj(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x01a2
        L_0x00d0:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzfd.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzfd.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.icing.zzeh.zzd(r4, r5)
            if (r4 != 0) goto L_0x01a3
            goto L_0x018f
        L_0x00e6:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzfd.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzfd.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.icing.zzeh.zzd(r4, r5)
            if (r4 != 0) goto L_0x01a3
            goto L_0x01a2
        L_0x00fc:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzfd.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzfd.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.icing.zzeh.zzd(r4, r5)
            if (r4 != 0) goto L_0x01a3
            goto L_0x018f
        L_0x0112:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            boolean r4 = com.google.android.gms.internal.icing.zzfd.zzl(r10, r6)
            boolean r5 = com.google.android.gms.internal.icing.zzfd.zzl(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x01a2
        L_0x0124:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.icing.zzfd.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.icing.zzfd.zzj(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x018f
        L_0x0135:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.icing.zzfd.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.icing.zzfd.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
            goto L_0x01a2
        L_0x0148:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.icing.zzfd.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.icing.zzfd.zzj(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x018f
        L_0x0159:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.icing.zzfd.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.icing.zzfd.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
            goto L_0x01a2
        L_0x016c:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.icing.zzfd.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.icing.zzfd.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
            goto L_0x018f
        L_0x017f:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.icing.zzfd.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.icing.zzfd.zzj(r11, r6)
            if (r4 == r5) goto L_0x01a3
        L_0x018f:
            goto L_0x01a2
        L_0x0190:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.icing.zzfd.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.icing.zzfd.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
        L_0x01a2:
            r3 = 0
        L_0x01a3:
            if (r3 != 0) goto L_0x01a6
            return r1
        L_0x01a6:
            int r2 = r2 + 3
            goto L_0x0005
        L_0x01aa:
            com.google.android.gms.internal.icing.zzex<?, ?> r0 = r9.zzkm
            java.lang.Object r0 = r0.zzm(r10)
            com.google.android.gms.internal.icing.zzex<?, ?> r2 = r9.zzkm
            java.lang.Object r2 = r2.zzm(r11)
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x01bd
            return r1
        L_0x01bd:
            boolean r0 = r9.zzkd
            if (r0 == 0) goto L_0x01d2
            com.google.android.gms.internal.icing.zzca<?> r0 = r9.zzkn
            com.google.android.gms.internal.icing.zzcd r10 = r0.zza(r10)
            com.google.android.gms.internal.icing.zzca<?> r0 = r9.zzkn
            com.google.android.gms.internal.icing.zzcd r11 = r0.zza(r11)
            boolean r10 = r10.equals(r11)
            return r10
        L_0x01d2:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzdv.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0061, code lost:
        r3 = com.google.android.gms.internal.icing.zzfd.zzo(r9, r5);
        r2 = r2 * 53;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0093, code lost:
        r2 = r2 * 53;
        r3 = zzg(r9, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a8, code lost:
        r2 = r2 * 53;
        r3 = zzh(r9, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ce, code lost:
        if (r3 != null) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d1, code lost:
        r2 = r2 * 53;
        r3 = com.google.android.gms.internal.icing.zzfd.zzo(r9, r5);
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
        r3 = ((java.lang.String) com.google.android.gms.internal.icing.zzfd.zzo(r9, r5)).hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00fd, code lost:
        r3 = com.google.android.gms.internal.icing.zzcm.zzg(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0116, code lost:
        r3 = java.lang.Float.floatToIntBits(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0121, code lost:
        r3 = java.lang.Double.doubleToLongBits(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0125, code lost:
        r3 = com.google.android.gms.internal.icing.zzcm.zzk(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0129, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x012a, code lost:
        r1 = r1 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int hashCode(T r9) {
        /*
            r8 = this;
            int[] r0 = r8.zzjy
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            if (r1 >= r0) goto L_0x012e
            int r3 = r8.zzac(r1)
            int[] r4 = r8.zzjy
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
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x0061
        L_0x0027:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00a8
        L_0x002f:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x004b
        L_0x0036:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00a8
        L_0x003e:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x004b
        L_0x0045:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
        L_0x004b:
            goto L_0x0093
        L_0x004c:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x0093
        L_0x0053:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00d1
        L_0x005b:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
        L_0x0061:
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzfd.zzo(r9, r5)
            int r2 = r2 * 53
            goto L_0x00d7
        L_0x0069:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00ea
        L_0x0071:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            int r2 = r2 * 53
            boolean r3 = zzi(r9, r5)
            goto L_0x00fd
        L_0x007f:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x0093
        L_0x0086:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00a8
        L_0x008d:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
        L_0x0093:
            int r2 = r2 * 53
            int r3 = zzg(r9, r5)
            goto L_0x0129
        L_0x009b:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00a8
        L_0x00a2:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
        L_0x00a8:
            int r2 = r2 * 53
            long r3 = zzh(r9, r5)
            goto L_0x0125
        L_0x00b0:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            int r2 = r2 * 53
            float r3 = zzf(r9, r5)
            goto L_0x0116
        L_0x00bd:
            boolean r3 = r8.zza((T) r9, r4, r1)
            if (r3 == 0) goto L_0x012a
            int r2 = r2 * 53
            double r3 = zze(r9, r5)
            goto L_0x0121
        L_0x00ca:
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzfd.zzo(r9, r5)
            if (r3 == 0) goto L_0x00e6
            goto L_0x00e2
        L_0x00d1:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzfd.zzo(r9, r5)
        L_0x00d7:
            int r3 = r3.hashCode()
            goto L_0x0129
        L_0x00dc:
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzfd.zzo(r9, r5)
            if (r3 == 0) goto L_0x00e6
        L_0x00e2:
            int r7 = r3.hashCode()
        L_0x00e6:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x012a
        L_0x00ea:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzfd.zzo(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0129
        L_0x00f7:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.icing.zzfd.zzl(r9, r5)
        L_0x00fd:
            int r3 = com.google.android.gms.internal.icing.zzcm.zzg(r3)
            goto L_0x0129
        L_0x0102:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.icing.zzfd.zzj(r9, r5)
            goto L_0x0129
        L_0x0109:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.icing.zzfd.zzk(r9, r5)
            goto L_0x0125
        L_0x0110:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.icing.zzfd.zzm(r9, r5)
        L_0x0116:
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0129
        L_0x011b:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.icing.zzfd.zzn(r9, r5)
        L_0x0121:
            long r3 = java.lang.Double.doubleToLongBits(r3)
        L_0x0125:
            int r3 = com.google.android.gms.internal.icing.zzcm.zzk(r3)
        L_0x0129:
            int r2 = r2 + r3
        L_0x012a:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x012e:
            int r2 = r2 * 53
            com.google.android.gms.internal.icing.zzex<?, ?> r0 = r8.zzkm
            java.lang.Object r0 = r0.zzm(r9)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r8.zzkd
            if (r0 == 0) goto L_0x014c
            int r2 = r2 * 53
            com.google.android.gms.internal.icing.zzca<?> r0 = r8.zzkn
            com.google.android.gms.internal.icing.zzcd r9 = r0.zza(r9)
            int r9 = r9.hashCode()
            int r2 = r2 + r9
        L_0x014c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzdv.hashCode(java.lang.Object):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0385, code lost:
        r15.zzb(r9, com.google.android.gms.internal.icing.zzfd.zzo(r14, (long) (r8 & 1048575)), zzaa(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x03a0, code lost:
        r15.zzb(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x03b1, code lost:
        r15.zze(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x03c2, code lost:
        r15.zzj(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x03d3, code lost:
        r15.zzm(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x03e4, code lost:
        r15.zzn(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x03f5, code lost:
        r15.zzd(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0400, code lost:
        r15.zza(r9, (com.google.android.gms.internal.icing.zzbi) com.google.android.gms.internal.icing.zzfd.zzo(r14, (long) (r8 & 1048575)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0413, code lost:
        r15.zza(r9, com.google.android.gms.internal.icing.zzfd.zzo(r14, (long) (r8 & 1048575)), zzaa(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0428, code lost:
        zza(r9, com.google.android.gms.internal.icing.zzfd.zzo(r14, (long) (r8 & 1048575)), r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x043f, code lost:
        r15.zza(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0450, code lost:
        r15.zzf(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0460, code lost:
        r15.zzc(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0470, code lost:
        r15.zzc(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0480, code lost:
        r15.zza(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0490, code lost:
        r15.zzi(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x04a0, code lost:
        r15.zza(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x04b0, code lost:
        r15.zza(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x0843, code lost:
        r15.zzb(r10, com.google.android.gms.internal.icing.zzfd.zzo(r14, (long) (r9 & 1048575)), zzaa(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x085e, code lost:
        r15.zzb(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x086f, code lost:
        r15.zze(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x0880, code lost:
        r15.zzj(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:0x0891, code lost:
        r15.zzm(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x08a2, code lost:
        r15.zzn(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x08b3, code lost:
        r15.zzd(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x08be, code lost:
        r15.zza(r10, (com.google.android.gms.internal.icing.zzbi) com.google.android.gms.internal.icing.zzfd.zzo(r14, (long) (r9 & 1048575)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x08d1, code lost:
        r15.zza(r10, com.google.android.gms.internal.icing.zzfd.zzo(r14, (long) (r9 & 1048575)), zzaa(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x08e6, code lost:
        zza(r10, com.google.android.gms.internal.icing.zzfd.zzo(r14, (long) (r9 & 1048575)), r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x08fd, code lost:
        r15.zza(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x090e, code lost:
        r15.zzf(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x091e, code lost:
        r15.zzc(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x092e, code lost:
        r15.zzc(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x093e, code lost:
        r15.zza(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:340:0x094e, code lost:
        r15.zzi(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x095e, code lost:
        r15.zza(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x096e, code lost:
        r15.zza(r10, r11);
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x04b9  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x04f7  */
    /* JADX WARNING: Removed duplicated region for block: B:351:0x0977  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r14, com.google.android.gms.internal.icing.zzfr r15) throws java.io.IOException {
        /*
            r13 = this;
            int r0 = r15.zzad()
            int r1 = com.google.android.gms.internal.icing.zzck.zzd.zzif
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x04cf
            com.google.android.gms.internal.icing.zzex<?, ?> r0 = r13.zzkm
            zza(r0, (T) r14, r15)
            boolean r0 = r13.zzkd
            if (r0 == 0) goto L_0x0030
            com.google.android.gms.internal.icing.zzca<?> r0 = r13.zzkn
            com.google.android.gms.internal.icing.zzcd r0 = r0.zza(r14)
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
            int[] r7 = r13.zzjy
            int r7 = r7.length
            int r7 = r7 + -3
        L_0x0037:
            if (r7 < 0) goto L_0x04b7
            int r8 = r13.zzac(r7)
            int[] r9 = r13.zzjy
            r9 = r9[r7]
        L_0x0041:
            if (r1 == 0) goto L_0x005f
            com.google.android.gms.internal.icing.zzca<?> r10 = r13.zzkn
            int r10 = r10.zza(r1)
            if (r10 <= r9) goto L_0x005f
            com.google.android.gms.internal.icing.zzca<?> r10 = r13.zzkn
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
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            goto L_0x0385
        L_0x0070:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            goto L_0x03a0
        L_0x007e:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            goto L_0x03b1
        L_0x008c:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            goto L_0x03c2
        L_0x009a:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            goto L_0x03d3
        L_0x00a8:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            goto L_0x03e4
        L_0x00b6:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            goto L_0x03f5
        L_0x00c4:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            goto L_0x0400
        L_0x00cc:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            goto L_0x0413
        L_0x00d4:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            goto L_0x0428
        L_0x00dc:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = zzi(r14, r10)
            goto L_0x043f
        L_0x00ea:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            goto L_0x0450
        L_0x00f8:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            goto L_0x0460
        L_0x0106:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            goto L_0x0470
        L_0x0114:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            goto L_0x0480
        L_0x0122:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            goto L_0x0490
        L_0x0130:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = zzf(r14, r10)
            goto L_0x04a0
        L_0x013e:
            boolean r10 = r13.zza((T) r14, r9, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = zze(r14, r10)
            goto L_0x04b0
        L_0x014c:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            r13.zza(r15, r9, r8, r7)
            goto L_0x04b3
        L_0x0157:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzef r10 = r13.zzaa(r7)
            com.google.android.gms.internal.icing.zzeh.zzb(r9, r8, r15, r10)
            goto L_0x04b3
        L_0x016c:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zze(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x017d:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzj(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x018e:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzg(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x019f:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzl(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01b0:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzm(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01c1:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzi(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01d2:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzn(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01e3:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzk(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01f4:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzf(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0205:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzh(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0216:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzd(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0227:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzc(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0238:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzb(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0249:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zza(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x025a:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zze(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x026b:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzj(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x027c:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzg(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x028d:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzl(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x029e:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzm(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x02af:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzi(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x02c0:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzb(r9, r8, r15)
            goto L_0x04b3
        L_0x02d1:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzef r10 = r13.zzaa(r7)
            com.google.android.gms.internal.icing.zzeh.zza(r9, r8, r15, r10)
            goto L_0x04b3
        L_0x02e6:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zza(r9, r8, r15)
            goto L_0x04b3
        L_0x02f7:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzn(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x0308:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzk(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x0319:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzf(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x032a:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzh(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x033b:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzd(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x034c:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzc(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x035d:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zzb(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x036e:
            int[] r9 = r13.zzjy
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzeh.zza(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x037f:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0385:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            com.google.android.gms.internal.icing.zzef r10 = r13.zzaa(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x04b3
        L_0x0394:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.icing.zzfd.zzk(r14, r10)
        L_0x03a0:
            r15.zzb(r9, r10)
            goto L_0x04b3
        L_0x03a5:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzfd.zzj(r14, r10)
        L_0x03b1:
            r15.zze(r9, r8)
            goto L_0x04b3
        L_0x03b6:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.icing.zzfd.zzk(r14, r10)
        L_0x03c2:
            r15.zzj(r9, r10)
            goto L_0x04b3
        L_0x03c7:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzfd.zzj(r14, r10)
        L_0x03d3:
            r15.zzm(r9, r8)
            goto L_0x04b3
        L_0x03d8:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzfd.zzj(r14, r10)
        L_0x03e4:
            r15.zzn(r9, r8)
            goto L_0x04b3
        L_0x03e9:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzfd.zzj(r14, r10)
        L_0x03f5:
            r15.zzd(r9, r8)
            goto L_0x04b3
        L_0x03fa:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0400:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            com.google.android.gms.internal.icing.zzbi r8 = (com.google.android.gms.internal.icing.zzbi) r8
            r15.zza(r9, r8)
            goto L_0x04b3
        L_0x040d:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0413:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            com.google.android.gms.internal.icing.zzef r10 = r13.zzaa(r7)
            r15.zza(r9, r8, r10)
            goto L_0x04b3
        L_0x0422:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0428:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r10)
            zza(r9, r8, r15)
            goto L_0x04b3
        L_0x0433:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.icing.zzfd.zzl(r14, r10)
        L_0x043f:
            r15.zza(r9, r8)
            goto L_0x04b3
        L_0x0444:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzfd.zzj(r14, r10)
        L_0x0450:
            r15.zzf(r9, r8)
            goto L_0x04b3
        L_0x0454:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.icing.zzfd.zzk(r14, r10)
        L_0x0460:
            r15.zzc(r9, r10)
            goto L_0x04b3
        L_0x0464:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzfd.zzj(r14, r10)
        L_0x0470:
            r15.zzc(r9, r8)
            goto L_0x04b3
        L_0x0474:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.icing.zzfd.zzk(r14, r10)
        L_0x0480:
            r15.zza(r9, r10)
            goto L_0x04b3
        L_0x0484:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.icing.zzfd.zzk(r14, r10)
        L_0x0490:
            r15.zzi(r9, r10)
            goto L_0x04b3
        L_0x0494:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.icing.zzfd.zzm(r14, r10)
        L_0x04a0:
            r15.zza(r9, r8)
            goto L_0x04b3
        L_0x04a4:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.icing.zzfd.zzn(r14, r10)
        L_0x04b0:
            r15.zza(r9, r10)
        L_0x04b3:
            int r7 = r7 + -3
            goto L_0x0037
        L_0x04b7:
            if (r1 == 0) goto L_0x04ce
            com.google.android.gms.internal.icing.zzca<?> r14 = r13.zzkn
            r14.zza(r15, r1)
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
            boolean r0 = r13.zzkf
            if (r0 == 0) goto L_0x0992
            boolean r0 = r13.zzkd
            if (r0 == 0) goto L_0x04ee
            com.google.android.gms.internal.icing.zzca<?> r0 = r13.zzkn
            com.google.android.gms.internal.icing.zzcd r0 = r0.zza(r14)
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x04ee
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x04f0
        L_0x04ee:
            r0 = r3
            r1 = r0
        L_0x04f0:
            int[] r7 = r13.zzjy
            int r7 = r7.length
            r8 = r1
            r1 = 0
        L_0x04f5:
            if (r1 >= r7) goto L_0x0975
            int r9 = r13.zzac(r1)
            int[] r10 = r13.zzjy
            r10 = r10[r1]
        L_0x04ff:
            if (r8 == 0) goto L_0x051d
            com.google.android.gms.internal.icing.zzca<?> r11 = r13.zzkn
            int r11 = r11.zza(r8)
            if (r11 > r10) goto L_0x051d
            com.google.android.gms.internal.icing.zzca<?> r11 = r13.zzkn
            r11.zza(r15, r8)
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
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            goto L_0x0843
        L_0x052e:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            goto L_0x085e
        L_0x053c:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            goto L_0x086f
        L_0x054a:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            goto L_0x0880
        L_0x0558:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            goto L_0x0891
        L_0x0566:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            goto L_0x08a2
        L_0x0574:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            goto L_0x08b3
        L_0x0582:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            goto L_0x08be
        L_0x058a:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            goto L_0x08d1
        L_0x0592:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            goto L_0x08e6
        L_0x059a:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = zzi(r14, r11)
            goto L_0x08fd
        L_0x05a8:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            goto L_0x090e
        L_0x05b6:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            goto L_0x091e
        L_0x05c4:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            goto L_0x092e
        L_0x05d2:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            goto L_0x093e
        L_0x05e0:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            goto L_0x094e
        L_0x05ee:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = zzf(r14, r11)
            goto L_0x095e
        L_0x05fc:
            boolean r11 = r13.zza((T) r14, r10, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = zze(r14, r11)
            goto L_0x096e
        L_0x060a:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            r13.zza(r15, r10, r9, r1)
            goto L_0x0971
        L_0x0615:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzef r11 = r13.zzaa(r1)
            com.google.android.gms.internal.icing.zzeh.zzb(r10, r9, r15, r11)
            goto L_0x0971
        L_0x062a:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zze(r10, r9, r15, r4)
            goto L_0x0971
        L_0x063b:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzj(r10, r9, r15, r4)
            goto L_0x0971
        L_0x064c:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzg(r10, r9, r15, r4)
            goto L_0x0971
        L_0x065d:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzl(r10, r9, r15, r4)
            goto L_0x0971
        L_0x066e:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzm(r10, r9, r15, r4)
            goto L_0x0971
        L_0x067f:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzi(r10, r9, r15, r4)
            goto L_0x0971
        L_0x0690:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzn(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06a1:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzk(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06b2:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzf(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06c3:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzh(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06d4:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzd(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06e5:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzc(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06f6:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzb(r10, r9, r15, r4)
            goto L_0x0971
        L_0x0707:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zza(r10, r9, r15, r4)
            goto L_0x0971
        L_0x0718:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zze(r10, r9, r15, r5)
            goto L_0x0971
        L_0x0729:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzj(r10, r9, r15, r5)
            goto L_0x0971
        L_0x073a:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzg(r10, r9, r15, r5)
            goto L_0x0971
        L_0x074b:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzl(r10, r9, r15, r5)
            goto L_0x0971
        L_0x075c:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzm(r10, r9, r15, r5)
            goto L_0x0971
        L_0x076d:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzi(r10, r9, r15, r5)
            goto L_0x0971
        L_0x077e:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzb(r10, r9, r15)
            goto L_0x0971
        L_0x078f:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzef r11 = r13.zzaa(r1)
            com.google.android.gms.internal.icing.zzeh.zza(r10, r9, r15, r11)
            goto L_0x0971
        L_0x07a4:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zza(r10, r9, r15)
            goto L_0x0971
        L_0x07b5:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzn(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07c6:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzk(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07d7:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzf(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07e8:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzh(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07f9:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzd(r10, r9, r15, r5)
            goto L_0x0971
        L_0x080a:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzc(r10, r9, r15, r5)
            goto L_0x0971
        L_0x081b:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zzb(r10, r9, r15, r5)
            goto L_0x0971
        L_0x082c:
            int[] r10 = r13.zzjy
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzeh.zza(r10, r9, r15, r5)
            goto L_0x0971
        L_0x083d:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
        L_0x0843:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            com.google.android.gms.internal.icing.zzef r11 = r13.zzaa(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0971
        L_0x0852:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.icing.zzfd.zzk(r14, r11)
        L_0x085e:
            r15.zzb(r10, r11)
            goto L_0x0971
        L_0x0863:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzfd.zzj(r14, r11)
        L_0x086f:
            r15.zze(r10, r9)
            goto L_0x0971
        L_0x0874:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.icing.zzfd.zzk(r14, r11)
        L_0x0880:
            r15.zzj(r10, r11)
            goto L_0x0971
        L_0x0885:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzfd.zzj(r14, r11)
        L_0x0891:
            r15.zzm(r10, r9)
            goto L_0x0971
        L_0x0896:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzfd.zzj(r14, r11)
        L_0x08a2:
            r15.zzn(r10, r9)
            goto L_0x0971
        L_0x08a7:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzfd.zzj(r14, r11)
        L_0x08b3:
            r15.zzd(r10, r9)
            goto L_0x0971
        L_0x08b8:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
        L_0x08be:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            com.google.android.gms.internal.icing.zzbi r9 = (com.google.android.gms.internal.icing.zzbi) r9
            r15.zza(r10, r9)
            goto L_0x0971
        L_0x08cb:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
        L_0x08d1:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            com.google.android.gms.internal.icing.zzef r11 = r13.zzaa(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0971
        L_0x08e0:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
        L_0x08e6:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0971
        L_0x08f1:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.icing.zzfd.zzl(r14, r11)
        L_0x08fd:
            r15.zza(r10, r9)
            goto L_0x0971
        L_0x0902:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzfd.zzj(r14, r11)
        L_0x090e:
            r15.zzf(r10, r9)
            goto L_0x0971
        L_0x0912:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.icing.zzfd.zzk(r14, r11)
        L_0x091e:
            r15.zzc(r10, r11)
            goto L_0x0971
        L_0x0922:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzfd.zzj(r14, r11)
        L_0x092e:
            r15.zzc(r10, r9)
            goto L_0x0971
        L_0x0932:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.icing.zzfd.zzk(r14, r11)
        L_0x093e:
            r15.zza(r10, r11)
            goto L_0x0971
        L_0x0942:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.icing.zzfd.zzk(r14, r11)
        L_0x094e:
            r15.zzi(r10, r11)
            goto L_0x0971
        L_0x0952:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.icing.zzfd.zzm(r14, r11)
        L_0x095e:
            r15.zza(r10, r9)
            goto L_0x0971
        L_0x0962:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.icing.zzfd.zzn(r14, r11)
        L_0x096e:
            r15.zza(r10, r11)
        L_0x0971:
            int r1 = r1 + 3
            goto L_0x04f5
        L_0x0975:
            if (r8 == 0) goto L_0x098c
            com.google.android.gms.internal.icing.zzca<?> r1 = r13.zzkn
            r1.zza(r15, r8)
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
            com.google.android.gms.internal.icing.zzex<?, ?> r0 = r13.zzkm
            zza(r0, (T) r14, r15)
            return
        L_0x0992:
            r13.zzb((T) r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzdv.zza(java.lang.Object, com.google.android.gms.internal.icing.zzfr):void");
    }

    public final void zzc(T t) {
        for (int i = this.zzki; i < this.zzkj; i++) {
            long zzac = (long) (zzac(this.zzkh[i]) & 1048575);
            Object zzo = zzfd.zzo(t, zzac);
            if (zzo != null) {
                zzfd.zza((Object) t, zzac, this.zzko.zzh(zzo));
            }
        }
        int length = this.zzkh.length;
        for (int i2 = this.zzkj; i2 < length; i2++) {
            this.zzkl.zza(t, (long) this.zzkh[i2]);
        }
        this.zzkm.zzc(t);
        if (this.zzkd) {
            this.zzkn.zzc(t);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0037, code lost:
        com.google.android.gms.internal.icing.zzfd.zza((java.lang.Object) r7, r2, com.google.android.gms.internal.icing.zzfd.zzo(r8, r2));
        zzb(r7, r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008f, code lost:
        com.google.android.gms.internal.icing.zzfd.zza((java.lang.Object) r7, r2, com.google.android.gms.internal.icing.zzfd.zzo(r8, r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b9, code lost:
        com.google.android.gms.internal.icing.zzfd.zza((java.lang.Object) r7, r2, com.google.android.gms.internal.icing.zzfd.zzj(r8, r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ce, code lost:
        com.google.android.gms.internal.icing.zzfd.zza((java.lang.Object) r7, r2, com.google.android.gms.internal.icing.zzfd.zzk(r8, r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f1, code lost:
        zzb(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f4, code lost:
        r0 = r0 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzc(T r7, T r8) {
        /*
            r6 = this;
            if (r8 != 0) goto L_0x0008
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            r7.<init>()
            throw r7
        L_0x0008:
            r0 = 0
        L_0x0009:
            int[] r1 = r6.zzjy
            int r1 = r1.length
            if (r0 >= r1) goto L_0x00f8
            int r1 = r6.zzac(r0)
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r1
            long r2 = (long) r2
            int[] r4 = r6.zzjy
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
            boolean r1 = r6.zza((T) r8, r4, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x0037
        L_0x002c:
            r6.zzb((T) r7, (T) r8, r0)
            goto L_0x00f4
        L_0x0031:
            boolean r1 = r6.zza((T) r8, r4, r0)
            if (r1 == 0) goto L_0x00f4
        L_0x0037:
            java.lang.Object r1 = com.google.android.gms.internal.icing.zzfd.zzo(r8, r2)
            com.google.android.gms.internal.icing.zzfd.zza(r7, r2, r1)
            r6.zzb((T) r7, r4, r0)
            goto L_0x00f4
        L_0x0043:
            com.google.android.gms.internal.icing.zzdm r1 = r6.zzko
            com.google.android.gms.internal.icing.zzeh.zza(r1, r7, r8, r2)
            goto L_0x00f4
        L_0x004a:
            com.google.android.gms.internal.icing.zzdc r1 = r6.zzkl
            r1.zza(r7, r8, r2)
            goto L_0x00f4
        L_0x0051:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x00ce
        L_0x0059:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x0075
        L_0x0060:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x00ce
        L_0x0068:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x0075
        L_0x006f:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
        L_0x0075:
            goto L_0x00b9
        L_0x0076:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x00b9
        L_0x007d:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x008f
        L_0x0084:
            r6.zza((T) r7, (T) r8, r0)
            goto L_0x00f4
        L_0x0089:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
        L_0x008f:
            java.lang.Object r1 = com.google.android.gms.internal.icing.zzfd.zzo(r8, r2)
            com.google.android.gms.internal.icing.zzfd.zza(r7, r2, r1)
            goto L_0x00f1
        L_0x0097:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            boolean r1 = com.google.android.gms.internal.icing.zzfd.zzl(r8, r2)
            com.google.android.gms.internal.icing.zzfd.zza(r7, r2, r1)
            goto L_0x00f1
        L_0x00a5:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x00b9
        L_0x00ac:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x00ce
        L_0x00b3:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
        L_0x00b9:
            int r1 = com.google.android.gms.internal.icing.zzfd.zzj(r8, r2)
            com.google.android.gms.internal.icing.zzfd.zza(r7, r2, r1)
            goto L_0x00f1
        L_0x00c1:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x00ce
        L_0x00c8:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
        L_0x00ce:
            long r4 = com.google.android.gms.internal.icing.zzfd.zzk(r8, r2)
            com.google.android.gms.internal.icing.zzfd.zza(r7, r2, r4)
            goto L_0x00f1
        L_0x00d6:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            float r1 = com.google.android.gms.internal.icing.zzfd.zzm(r8, r2)
            com.google.android.gms.internal.icing.zzfd.zza(r7, r2, r1)
            goto L_0x00f1
        L_0x00e4:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            double r4 = com.google.android.gms.internal.icing.zzfd.zzn(r8, r2)
            com.google.android.gms.internal.icing.zzfd.zza(r7, r2, r4)
        L_0x00f1:
            r6.zzb((T) r7, r0)
        L_0x00f4:
            int r0 = r0 + 3
            goto L_0x0009
        L_0x00f8:
            boolean r0 = r6.zzkf
            if (r0 != 0) goto L_0x010a
            com.google.android.gms.internal.icing.zzex<?, ?> r0 = r6.zzkm
            com.google.android.gms.internal.icing.zzeh.zza(r0, r7, r8)
            boolean r0 = r6.zzkd
            if (r0 == 0) goto L_0x010a
            com.google.android.gms.internal.icing.zzca<?> r0 = r6.zzkn
            com.google.android.gms.internal.icing.zzeh.zza(r0, r7, r8)
        L_0x010a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzdv.zzc(java.lang.Object, java.lang.Object):void");
    }

    /* JADX INFO: used method not loaded: com.google.android.gms.internal.icing.zzeh.zzc(int, java.lang.Object, com.google.android.gms.internal.icing.zzef):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01d8, code lost:
        if (r0.zzkg != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01e9, code lost:
        if (r0.zzkg != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01fa, code lost:
        if (r0.zzkg != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x020b, code lost:
        if (r0.zzkg != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x020d, code lost:
        r2.putInt(r1, (long) r14, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0211, code lost:
        r3 = (com.google.android.gms.internal.icing.zzbu.zzp(r3) + com.google.android.gms.internal.icing.zzbu.zzr(r4)) + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0296, code lost:
        r13 = r13 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x029f, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzc(r3, (com.google.android.gms.internal.icing.zzdr) com.google.android.gms.internal.icing.zzfd.zzo(r1, r4), zzaa(r12));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02b8, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzf(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x02c7, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzi(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x02d2, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzh(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x02dd, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzk(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x02ec, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzl(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x02fb, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzh(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0306, code lost:
        r4 = com.google.android.gms.internal.icing.zzfd.zzo(r1, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x030a, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzc(r3, (com.google.android.gms.internal.icing.zzbi) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0317, code lost:
        r3 = com.google.android.gms.internal.icing.zzeh.zzc(r3, com.google.android.gms.internal.icing.zzfd.zzo(r1, r4), zzaa(r12));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0331, code lost:
        if ((r4 instanceof com.google.android.gms.internal.icing.zzbi) != false) goto L_0x030a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0334, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzb(r3, (java.lang.String) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0342, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzb(r3, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x034e, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzj(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x035a, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzg(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x036a, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzg(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x037a, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zze(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x038a, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzd(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x0396, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzb(r3, 0.0f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x03a2, code lost:
        r3 = com.google.android.gms.internal.icing.zzbu.zzb(r3, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x03aa, code lost:
        r12 = r12 + 3;
        r3 = 267386880;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x0418, code lost:
        if (zza(r1, r14, r3) != false) goto L_0x06ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x0438, code lost:
        if (zza(r1, r14, r3) != false) goto L_0x06e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x0440, code lost:
        if (zza(r1, r14, r3) != false) goto L_0x06f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x0460, code lost:
        if (zza(r1, r14, r3) != false) goto L_0x0717;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x0468, code lost:
        if (zza(r1, r14, r3) != false) goto L_0x0726;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x0478, code lost:
        if ((r6 instanceof com.google.android.gms.internal.icing.zzbi) != false) goto L_0x071b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x0480, code lost:
        if (zza(r1, r14, r3) != false) goto L_0x074d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x0518, code lost:
        if (r0.zzkg != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:275:0x052a, code lost:
        if (r0.zzkg != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:279:0x053c, code lost:
        if (r0.zzkg != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x054e, code lost:
        if (r0.zzkg != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x0560, code lost:
        if (r0.zzkg != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x0572, code lost:
        if (r0.zzkg != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x0584, code lost:
        if (r0.zzkg != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:0x0596, code lost:
        if (r0.zzkg != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x05a7, code lost:
        if (r0.zzkg != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x05b8, code lost:
        if (r0.zzkg != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:311:0x05c9, code lost:
        if (r0.zzkg != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:315:0x05da, code lost:
        if (r0.zzkg != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:319:0x05eb, code lost:
        if (r0.zzkg != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:323:0x05fc, code lost:
        if (r0.zzkg != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x05fe, code lost:
        r2.putInt(r1, (long) r6, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:325:0x0602, code lost:
        r6 = (com.google.android.gms.internal.icing.zzbu.zzp(r14) + com.google.android.gms.internal.icing.zzbu.zzr(r9)) + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:339:0x06ad, code lost:
        r4 = r4 + r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:341:0x06af, code lost:
        r18 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:343:0x06b8, code lost:
        if ((r12 & r16) != 0) goto L_0x06ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x06ba, code lost:
        r6 = com.google.android.gms.internal.icing.zzbu.zzc(r14, (com.google.android.gms.internal.icing.zzdr) r2.getObject(r1, r9), zzaa(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x06d1, code lost:
        r6 = com.google.android.gms.internal.icing.zzbu.zzf(r14, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:352:0x06de, code lost:
        r6 = com.google.android.gms.internal.icing.zzbu.zzi(r14, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:0x06e5, code lost:
        if ((r12 & r16) != 0) goto L_0x06e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:355:0x06e7, code lost:
        r6 = com.google.android.gms.internal.icing.zzbu.zzh(r14, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:0x06f0, code lost:
        if ((r12 & r16) != 0) goto L_0x06f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:358:0x06f2, code lost:
        r9 = com.google.android.gms.internal.icing.zzbu.zzk(r14, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x06f7, code lost:
        r4 = r4 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:363:0x0701, code lost:
        r6 = com.google.android.gms.internal.icing.zzbu.zzl(r14, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:0x070e, code lost:
        r6 = com.google.android.gms.internal.icing.zzbu.zzh(r14, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:0x0715, code lost:
        if ((r12 & r16) != 0) goto L_0x0717;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:0x0717, code lost:
        r6 = r2.getObject(r1, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:0x071b, code lost:
        r6 = com.google.android.gms.internal.icing.zzbu.zzc(r14, (com.google.android.gms.internal.icing.zzbi) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:0x0724, code lost:
        if ((r12 & r16) != 0) goto L_0x0726;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:0x0726, code lost:
        r6 = com.google.android.gms.internal.icing.zzeh.zzc(r14, r2.getObject(r1, r9), zzaa(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:378:0x073e, code lost:
        if ((r6 instanceof com.google.android.gms.internal.icing.zzbi) != false) goto L_0x071b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x0741, code lost:
        r6 = com.google.android.gms.internal.icing.zzbu.zzb(r14, (java.lang.String) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ab, code lost:
        if ((r4 instanceof com.google.android.gms.internal.icing.zzbi) != false) goto L_0x030a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:381:0x074b, code lost:
        if ((r12 & r16) != 0) goto L_0x074d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:382:0x074d, code lost:
        r6 = com.google.android.gms.internal.icing.zzbu.zzb(r14, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:398:0x079d, code lost:
        r4 = r4 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:407:0x07bf, code lost:
        r3 = r3 + 3;
        r9 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0127, code lost:
        if (r0.zzkg != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0139, code lost:
        if (r0.zzkg != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x014b, code lost:
        if (r0.zzkg != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x015d, code lost:
        if (r0.zzkg != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x016f, code lost:
        if (r0.zzkg != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0181, code lost:
        if (r0.zzkg != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0193, code lost:
        if (r0.zzkg != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01a5, code lost:
        if (r0.zzkg != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01b6, code lost:
        if (r0.zzkg != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01c7, code lost:
        if (r0.zzkg != false) goto L_0x020d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzj(T r22) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            boolean r2 = r0.zzkf
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            r6 = 0
            r7 = 1
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r9 = 0
            r11 = 0
            if (r2 == 0) goto L_0x03b8
            sun.misc.Unsafe r2 = zzjx
            r12 = 0
            r13 = 0
        L_0x0016:
            int[] r14 = r0.zzjy
            int r14 = r14.length
            if (r12 >= r14) goto L_0x03b0
            int r14 = r0.zzac(r12)
            r15 = r14 & r3
            int r15 = r15 >>> 20
            int[] r3 = r0.zzjy
            r3 = r3[r12]
            r14 = r14 & r8
            long r4 = (long) r14
            com.google.android.gms.internal.icing.zzcg r14 = com.google.android.gms.internal.icing.zzcg.DOUBLE_LIST_PACKED
            int r14 = r14.id()
            if (r15 < r14) goto L_0x0041
            com.google.android.gms.internal.icing.zzcg r14 = com.google.android.gms.internal.icing.zzcg.SINT64_LIST_PACKED
            int r14 = r14.id()
            if (r15 > r14) goto L_0x0041
            int[] r14 = r0.zzjy
            int r17 = r12 + 2
            r14 = r14[r17]
            r14 = r14 & r8
            goto L_0x0042
        L_0x0041:
            r14 = 0
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
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            goto L_0x029f
        L_0x004f:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            long r4 = zzh(r1, r4)
            goto L_0x02b8
        L_0x005b:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = zzg(r1, r4)
            goto L_0x02c7
        L_0x0067:
            boolean r4 = r0.zza((T) r1, r3, r12)
            if (r4 == 0) goto L_0x03aa
            goto L_0x02d2
        L_0x006f:
            boolean r4 = r0.zza((T) r1, r3, r12)
            if (r4 == 0) goto L_0x03aa
            goto L_0x02dd
        L_0x0077:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = zzg(r1, r4)
            goto L_0x02ec
        L_0x0083:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = zzg(r1, r4)
            goto L_0x02fb
        L_0x008f:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            goto L_0x0306
        L_0x0097:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            goto L_0x0317
        L_0x009f:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzfd.zzo(r1, r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.icing.zzbi
            if (r5 == 0) goto L_0x0334
            goto L_0x0333
        L_0x00af:
            boolean r4 = r0.zza((T) r1, r3, r12)
            if (r4 == 0) goto L_0x03aa
            goto L_0x0342
        L_0x00b7:
            boolean r4 = r0.zza((T) r1, r3, r12)
            if (r4 == 0) goto L_0x03aa
            goto L_0x034e
        L_0x00bf:
            boolean r4 = r0.zza((T) r1, r3, r12)
            if (r4 == 0) goto L_0x03aa
            goto L_0x035a
        L_0x00c7:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = zzg(r1, r4)
            goto L_0x036a
        L_0x00d3:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            long r4 = zzh(r1, r4)
            goto L_0x037a
        L_0x00df:
            boolean r14 = r0.zza((T) r1, r3, r12)
            if (r14 == 0) goto L_0x03aa
            long r4 = zzh(r1, r4)
            goto L_0x038a
        L_0x00eb:
            boolean r4 = r0.zza((T) r1, r3, r12)
            if (r4 == 0) goto L_0x03aa
            goto L_0x0396
        L_0x00f3:
            boolean r4 = r0.zza((T) r1, r3, r12)
            if (r4 == 0) goto L_0x03aa
            goto L_0x03a2
        L_0x00fb:
            com.google.android.gms.internal.icing.zzdm r14 = r0.zzko
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzfd.zzo(r1, r4)
            java.lang.Object r5 = r0.zzab(r12)
            int r3 = r14.zzb(r3, r4, r5)
            goto L_0x0296
        L_0x010b:
            java.util.List r4 = zzd(r1, r4)
            com.google.android.gms.internal.icing.zzef r5 = r0.zzaa(r12)
            int r3 = com.google.android.gms.internal.icing.zzeh.zzd(r3, r4, r5)
            goto L_0x0296
        L_0x0119:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzeh.zzc(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzkg
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x012b:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzeh.zzg(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzkg
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x013d:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzeh.zzi(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzkg
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x014f:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzeh.zzh(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzkg
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0161:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzeh.zzd(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzkg
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0173:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzeh.zzf(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzkg
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0185:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzeh.zzj(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzkg
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0197:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzeh.zzh(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzkg
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01a8:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzeh.zzi(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzkg
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01b9:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzeh.zze(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzkg
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01ca:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzeh.zzb(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzkg
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01db:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzeh.zza(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzkg
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01ec:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzeh.zzh(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzkg
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01fd:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzeh.zzi(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzkg
            if (r5 == 0) goto L_0x0211
        L_0x020d:
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x0211:
            int r3 = com.google.android.gms.internal.icing.zzbu.zzp(r3)
            int r5 = com.google.android.gms.internal.icing.zzbu.zzr(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            goto L_0x0296
        L_0x021d:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.icing.zzeh.zzq(r3, r4, r11)
            goto L_0x0296
        L_0x0227:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.icing.zzeh.zzu(r3, r4, r11)
            goto L_0x0296
        L_0x0230:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.icing.zzeh.zzr(r3, r4, r11)
            goto L_0x0296
        L_0x0239:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.icing.zzeh.zzt(r3, r4, r11)
            goto L_0x0296
        L_0x0242:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.icing.zzeh.zzd(r3, r4)
            goto L_0x0296
        L_0x024b:
            java.util.List r4 = zzd(r1, r4)
            com.google.android.gms.internal.icing.zzef r5 = r0.zzaa(r12)
            int r3 = com.google.android.gms.internal.icing.zzeh.zzc(r3, r4, r5)
            goto L_0x0296
        L_0x0258:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.icing.zzeh.zzc(r3, r4)
            goto L_0x0296
        L_0x0261:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.icing.zzeh.zzx(r3, r4, r11)
            goto L_0x0296
        L_0x026a:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.icing.zzeh.zzs(r3, r4, r11)
            goto L_0x0296
        L_0x0273:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.icing.zzeh.zzp(r3, r4, r11)
            goto L_0x0296
        L_0x027c:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.icing.zzeh.zzo(r3, r4, r11)
            goto L_0x0296
        L_0x0285:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.icing.zzeh.zzv(r3, r4, r11)
            goto L_0x0296
        L_0x028e:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.icing.zzeh.zzw(r3, r4, r11)
        L_0x0296:
            int r13 = r13 + r3
            goto L_0x03aa
        L_0x0299:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
        L_0x029f:
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzfd.zzo(r1, r4)
            com.google.android.gms.internal.icing.zzdr r4 = (com.google.android.gms.internal.icing.zzdr) r4
            com.google.android.gms.internal.icing.zzef r5 = r0.zzaa(r12)
            int r3 = com.google.android.gms.internal.icing.zzbu.zzc(r3, r4, r5)
            goto L_0x0296
        L_0x02ae:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            long r4 = com.google.android.gms.internal.icing.zzfd.zzk(r1, r4)
        L_0x02b8:
            int r3 = com.google.android.gms.internal.icing.zzbu.zzf(r3, r4)
            goto L_0x0296
        L_0x02bd:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = com.google.android.gms.internal.icing.zzfd.zzj(r1, r4)
        L_0x02c7:
            int r3 = com.google.android.gms.internal.icing.zzbu.zzi(r3, r4)
            goto L_0x0296
        L_0x02cc:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x02d2:
            int r3 = com.google.android.gms.internal.icing.zzbu.zzh(r3, r9)
            goto L_0x0296
        L_0x02d7:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x02dd:
            int r3 = com.google.android.gms.internal.icing.zzbu.zzk(r3, r11)
            goto L_0x0296
        L_0x02e2:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = com.google.android.gms.internal.icing.zzfd.zzj(r1, r4)
        L_0x02ec:
            int r3 = com.google.android.gms.internal.icing.zzbu.zzl(r3, r4)
            goto L_0x0296
        L_0x02f1:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = com.google.android.gms.internal.icing.zzfd.zzj(r1, r4)
        L_0x02fb:
            int r3 = com.google.android.gms.internal.icing.zzbu.zzh(r3, r4)
            goto L_0x0296
        L_0x0300:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
        L_0x0306:
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzfd.zzo(r1, r4)
        L_0x030a:
            com.google.android.gms.internal.icing.zzbi r4 = (com.google.android.gms.internal.icing.zzbi) r4
            int r3 = com.google.android.gms.internal.icing.zzbu.zzc(r3, r4)
            goto L_0x0296
        L_0x0311:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
        L_0x0317:
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzfd.zzo(r1, r4)
            com.google.android.gms.internal.icing.zzef r5 = r0.zzaa(r12)
            int r3 = com.google.android.gms.internal.icing.zzeh.zzc(r3, r4, r5)
            goto L_0x0296
        L_0x0325:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzfd.zzo(r1, r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.icing.zzbi
            if (r5 == 0) goto L_0x0334
        L_0x0333:
            goto L_0x030a
        L_0x0334:
            java.lang.String r4 = (java.lang.String) r4
            int r3 = com.google.android.gms.internal.icing.zzbu.zzb(r3, r4)
            goto L_0x0296
        L_0x033c:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x0342:
            int r3 = com.google.android.gms.internal.icing.zzbu.zzb(r3, r7)
            goto L_0x0296
        L_0x0348:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x034e:
            int r3 = com.google.android.gms.internal.icing.zzbu.zzj(r3, r11)
            goto L_0x0296
        L_0x0354:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x035a:
            int r3 = com.google.android.gms.internal.icing.zzbu.zzg(r3, r9)
            goto L_0x0296
        L_0x0360:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = com.google.android.gms.internal.icing.zzfd.zzj(r1, r4)
        L_0x036a:
            int r3 = com.google.android.gms.internal.icing.zzbu.zzg(r3, r4)
            goto L_0x0296
        L_0x0370:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            long r4 = com.google.android.gms.internal.icing.zzfd.zzk(r1, r4)
        L_0x037a:
            int r3 = com.google.android.gms.internal.icing.zzbu.zze(r3, r4)
            goto L_0x0296
        L_0x0380:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            long r4 = com.google.android.gms.internal.icing.zzfd.zzk(r1, r4)
        L_0x038a:
            int r3 = com.google.android.gms.internal.icing.zzbu.zzd(r3, r4)
            goto L_0x0296
        L_0x0390:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x0396:
            int r3 = com.google.android.gms.internal.icing.zzbu.zzb(r3, r6)
            goto L_0x0296
        L_0x039c:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x03a2:
            r4 = 0
            int r3 = com.google.android.gms.internal.icing.zzbu.zzb(r3, r4)
            goto L_0x0296
        L_0x03aa:
            int r12 = r12 + 3
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            goto L_0x0016
        L_0x03b0:
            com.google.android.gms.internal.icing.zzex<?, ?> r2 = r0.zzkm
            int r1 = zza(r2, (T) r1)
            int r13 = r13 + r1
            return r13
        L_0x03b8:
            sun.misc.Unsafe r2 = zzjx
            r3 = -1
            r3 = 0
            r4 = 0
            r5 = -1
            r12 = 0
        L_0x03bf:
            int[] r13 = r0.zzjy
            int r13 = r13.length
            if (r3 >= r13) goto L_0x07c7
            int r13 = r0.zzac(r3)
            int[] r14 = r0.zzjy
            r14 = r14[r3]
            r15 = 267386880(0xff00000, float:2.3665827E-29)
            r16 = r13 & r15
            int r15 = r16 >>> 20
            r6 = 17
            if (r15 > r6) goto L_0x03eb
            int[] r6 = r0.zzjy
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
            boolean r6 = r0.zzkg
            if (r6 == 0) goto L_0x0409
            com.google.android.gms.internal.icing.zzcg r6 = com.google.android.gms.internal.icing.zzcg.DOUBLE_LIST_PACKED
            int r6 = r6.id()
            if (r15 < r6) goto L_0x0409
            com.google.android.gms.internal.icing.zzcg r6 = com.google.android.gms.internal.icing.zzcg.SINT64_LIST_PACKED
            int r6 = r6.id()
            if (r15 > r6) goto L_0x0409
            int[] r6 = r0.zzjy
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
                case 0: goto L_0x07b0;
                case 1: goto L_0x07a0;
                case 2: goto L_0x078e;
                case 3: goto L_0x077e;
                case 4: goto L_0x076e;
                case 5: goto L_0x075f;
                case 6: goto L_0x0753;
                case 7: goto L_0x0749;
                case 8: goto L_0x0734;
                case 9: goto L_0x0722;
                case 10: goto L_0x0713;
                case 11: goto L_0x0706;
                case 12: goto L_0x06f9;
                case 13: goto L_0x06ee;
                case 14: goto L_0x06e3;
                case 15: goto L_0x06d6;
                case 16: goto L_0x06c9;
                case 17: goto L_0x06b6;
                case 18: goto L_0x06a2;
                case 19: goto L_0x0696;
                case 20: goto L_0x068a;
                case 21: goto L_0x067e;
                case 22: goto L_0x0672;
                case 23: goto L_0x06a2;
                case 24: goto L_0x0696;
                case 25: goto L_0x0666;
                case 26: goto L_0x065b;
                case 27: goto L_0x064c;
                case 28: goto L_0x0641;
                case 29: goto L_0x0635;
                case 30: goto L_0x0628;
                case 31: goto L_0x0696;
                case 32: goto L_0x06a2;
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
            goto L_0x06ae
        L_0x0414:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            goto L_0x06ba
        L_0x041c:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            long r9 = zzh(r1, r9)
            goto L_0x06d1
        L_0x0428:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            int r6 = zzg(r1, r9)
            goto L_0x06de
        L_0x0434:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            goto L_0x06e7
        L_0x043c:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            goto L_0x06f2
        L_0x0444:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            int r6 = zzg(r1, r9)
            goto L_0x0701
        L_0x0450:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            int r6 = zzg(r1, r9)
            goto L_0x070e
        L_0x045c:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            goto L_0x0717
        L_0x0464:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            goto L_0x0726
        L_0x046c:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            java.lang.Object r6 = r2.getObject(r1, r9)
            boolean r9 = r6 instanceof com.google.android.gms.internal.icing.zzbi
            if (r9 == 0) goto L_0x0741
            goto L_0x0740
        L_0x047c:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            goto L_0x074d
        L_0x0484:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            r6 = 0
            int r9 = com.google.android.gms.internal.icing.zzbu.zzj(r14, r6)
            goto L_0x06f7
        L_0x0491:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            r9 = 0
            int r6 = com.google.android.gms.internal.icing.zzbu.zzg(r14, r9)
            goto L_0x06ad
        L_0x049f:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            int r6 = zzg(r1, r9)
            int r6 = com.google.android.gms.internal.icing.zzbu.zzg(r14, r6)
            goto L_0x06ad
        L_0x04af:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            long r9 = zzh(r1, r9)
            int r6 = com.google.android.gms.internal.icing.zzbu.zze(r14, r9)
            goto L_0x06ad
        L_0x04bf:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            long r9 = zzh(r1, r9)
            int r6 = com.google.android.gms.internal.icing.zzbu.zzd(r14, r9)
            goto L_0x06ad
        L_0x04cf:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            r6 = 0
            int r9 = com.google.android.gms.internal.icing.zzbu.zzb(r14, r6)
            goto L_0x06f7
        L_0x04dc:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            r9 = 0
            int r6 = com.google.android.gms.internal.icing.zzbu.zzb(r14, r9)
            goto L_0x06ad
        L_0x04ea:
            com.google.android.gms.internal.icing.zzdm r6 = r0.zzko
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.lang.Object r10 = r0.zzab(r3)
            int r6 = r6.zzb(r14, r9, r10)
            goto L_0x06ad
        L_0x04fa:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.icing.zzef r9 = r0.zzaa(r3)
            int r6 = com.google.android.gms.internal.icing.zzeh.zzd(r14, r6, r9)
            goto L_0x06ad
        L_0x050a:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.icing.zzeh.zzc(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzkg
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x051c:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.icing.zzeh.zzg(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzkg
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x052e:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.icing.zzeh.zzi(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzkg
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0540:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.icing.zzeh.zzh(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzkg
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0552:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.icing.zzeh.zzd(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzkg
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0564:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.icing.zzeh.zzf(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzkg
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0576:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.icing.zzeh.zzj(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzkg
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0588:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.icing.zzeh.zzh(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzkg
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0599:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.icing.zzeh.zzi(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzkg
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x05aa:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.icing.zzeh.zze(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzkg
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x05bb:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.icing.zzeh.zzb(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzkg
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x05cc:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.icing.zzeh.zza(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzkg
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x05dd:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.icing.zzeh.zzh(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzkg
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x05ee:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.icing.zzeh.zzi(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzkg
            if (r10 == 0) goto L_0x0602
        L_0x05fe:
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x0602:
            int r6 = com.google.android.gms.internal.icing.zzbu.zzp(r14)
            int r10 = com.google.android.gms.internal.icing.zzbu.zzr(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            goto L_0x06ad
        L_0x060e:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            r11 = 0
            int r6 = com.google.android.gms.internal.icing.zzeh.zzq(r14, r6, r11)
            goto L_0x06ad
        L_0x061b:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.icing.zzeh.zzu(r14, r6, r11)
            goto L_0x06ad
        L_0x0628:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.icing.zzeh.zzr(r14, r6, r11)
            goto L_0x06ad
        L_0x0635:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.icing.zzeh.zzt(r14, r6, r11)
            goto L_0x06ad
        L_0x0641:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.icing.zzeh.zzd(r14, r6)
            goto L_0x06ad
        L_0x064c:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.icing.zzef r9 = r0.zzaa(r3)
            int r6 = com.google.android.gms.internal.icing.zzeh.zzc(r14, r6, r9)
            goto L_0x06ad
        L_0x065b:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.icing.zzeh.zzc(r14, r6)
            goto L_0x06ad
        L_0x0666:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            r11 = 0
            int r6 = com.google.android.gms.internal.icing.zzeh.zzx(r14, r6, r11)
            goto L_0x06ad
        L_0x0672:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.icing.zzeh.zzs(r14, r6, r11)
            goto L_0x06ad
        L_0x067e:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.icing.zzeh.zzp(r14, r6, r11)
            goto L_0x06ad
        L_0x068a:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.icing.zzeh.zzo(r14, r6, r11)
            goto L_0x06ad
        L_0x0696:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.icing.zzeh.zzv(r14, r6, r11)
            goto L_0x06ad
        L_0x06a2:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.icing.zzeh.zzw(r14, r6, r11)
        L_0x06ad:
            int r4 = r4 + r6
        L_0x06ae:
            r6 = 0
        L_0x06af:
            r9 = 0
            r10 = 0
            r18 = 0
            goto L_0x07bf
        L_0x06b6:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
        L_0x06ba:
            java.lang.Object r6 = r2.getObject(r1, r9)
            com.google.android.gms.internal.icing.zzdr r6 = (com.google.android.gms.internal.icing.zzdr) r6
            com.google.android.gms.internal.icing.zzef r9 = r0.zzaa(r3)
            int r6 = com.google.android.gms.internal.icing.zzbu.zzc(r14, r6, r9)
            goto L_0x06ad
        L_0x06c9:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
            long r9 = r2.getLong(r1, r9)
        L_0x06d1:
            int r6 = com.google.android.gms.internal.icing.zzbu.zzf(r14, r9)
            goto L_0x06ad
        L_0x06d6:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
            int r6 = r2.getInt(r1, r9)
        L_0x06de:
            int r6 = com.google.android.gms.internal.icing.zzbu.zzi(r14, r6)
            goto L_0x06ad
        L_0x06e3:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
        L_0x06e7:
            r9 = 0
            int r6 = com.google.android.gms.internal.icing.zzbu.zzh(r14, r9)
            goto L_0x06ad
        L_0x06ee:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
        L_0x06f2:
            r6 = 0
            int r9 = com.google.android.gms.internal.icing.zzbu.zzk(r14, r6)
        L_0x06f7:
            int r4 = r4 + r9
            goto L_0x06ae
        L_0x06f9:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
            int r6 = r2.getInt(r1, r9)
        L_0x0701:
            int r6 = com.google.android.gms.internal.icing.zzbu.zzl(r14, r6)
            goto L_0x06ad
        L_0x0706:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
            int r6 = r2.getInt(r1, r9)
        L_0x070e:
            int r6 = com.google.android.gms.internal.icing.zzbu.zzh(r14, r6)
            goto L_0x06ad
        L_0x0713:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
        L_0x0717:
            java.lang.Object r6 = r2.getObject(r1, r9)
        L_0x071b:
            com.google.android.gms.internal.icing.zzbi r6 = (com.google.android.gms.internal.icing.zzbi) r6
            int r6 = com.google.android.gms.internal.icing.zzbu.zzc(r14, r6)
            goto L_0x06ad
        L_0x0722:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
        L_0x0726:
            java.lang.Object r6 = r2.getObject(r1, r9)
            com.google.android.gms.internal.icing.zzef r9 = r0.zzaa(r3)
            int r6 = com.google.android.gms.internal.icing.zzeh.zzc(r14, r6, r9)
            goto L_0x06ad
        L_0x0734:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
            java.lang.Object r6 = r2.getObject(r1, r9)
            boolean r9 = r6 instanceof com.google.android.gms.internal.icing.zzbi
            if (r9 == 0) goto L_0x0741
        L_0x0740:
            goto L_0x071b
        L_0x0741:
            java.lang.String r6 = (java.lang.String) r6
            int r6 = com.google.android.gms.internal.icing.zzbu.zzb(r14, r6)
            goto L_0x06ad
        L_0x0749:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
        L_0x074d:
            int r6 = com.google.android.gms.internal.icing.zzbu.zzb(r14, r7)
            goto L_0x06ad
        L_0x0753:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
            r6 = 0
            int r9 = com.google.android.gms.internal.icing.zzbu.zzj(r14, r6)
            int r4 = r4 + r9
            goto L_0x06af
        L_0x075f:
            r6 = 0
            r9 = r12 & r16
            if (r9 == 0) goto L_0x06af
            r9 = 0
            int r11 = com.google.android.gms.internal.icing.zzbu.zzg(r14, r9)
            int r4 = r4 + r11
            r18 = r9
            goto L_0x079e
        L_0x076e:
            r6 = 0
            r18 = 0
            r11 = r12 & r16
            if (r11 == 0) goto L_0x079e
            int r9 = r2.getInt(r1, r9)
            int r9 = com.google.android.gms.internal.icing.zzbu.zzg(r14, r9)
            goto L_0x079d
        L_0x077e:
            r6 = 0
            r18 = 0
            r11 = r12 & r16
            if (r11 == 0) goto L_0x079e
            long r9 = r2.getLong(r1, r9)
            int r9 = com.google.android.gms.internal.icing.zzbu.zze(r14, r9)
            goto L_0x079d
        L_0x078e:
            r6 = 0
            r18 = 0
            r11 = r12 & r16
            if (r11 == 0) goto L_0x079e
            long r9 = r2.getLong(r1, r9)
            int r9 = com.google.android.gms.internal.icing.zzbu.zzd(r14, r9)
        L_0x079d:
            int r4 = r4 + r9
        L_0x079e:
            r9 = 0
            goto L_0x07ad
        L_0x07a0:
            r6 = 0
            r18 = 0
            r9 = r12 & r16
            if (r9 == 0) goto L_0x079e
            r9 = 0
            int r10 = com.google.android.gms.internal.icing.zzbu.zzb(r14, r9)
            int r4 = r4 + r10
        L_0x07ad:
            r10 = 0
            goto L_0x07bf
        L_0x07b0:
            r6 = 0
            r9 = 0
            r18 = 0
            r10 = r12 & r16
            if (r10 == 0) goto L_0x07ad
            r10 = 0
            int r13 = com.google.android.gms.internal.icing.zzbu.zzb(r14, r10)
            int r4 = r4 + r13
        L_0x07bf:
            int r3 = r3 + 3
            r9 = r18
            r6 = 0
            r11 = 0
            goto L_0x03bf
        L_0x07c7:
            com.google.android.gms.internal.icing.zzex<?, ?> r2 = r0.zzkm
            int r2 = zza(r2, (T) r1)
            int r4 = r4 + r2
            boolean r2 = r0.zzkd
            if (r2 == 0) goto L_0x07dd
            com.google.android.gms.internal.icing.zzca<?> r2 = r0.zzkn
            com.google.android.gms.internal.icing.zzcd r1 = r2.zza(r1)
            int r1 = r1.zzan()
            int r4 = r4 + r1
        L_0x07dd:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzdv.zzj(java.lang.Object):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0104, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzk(T r14) {
        /*
            r13 = this;
            r0 = 0
            r1 = -1
            r1 = 0
            r2 = -1
            r3 = 0
        L_0x0005:
            int r4 = r13.zzki
            r5 = 1
            if (r1 >= r4) goto L_0x0108
            int[] r4 = r13.zzkh
            r4 = r4[r1]
            int[] r6 = r13.zzjy
            r6 = r6[r4]
            int r7 = r13.zzac(r4)
            boolean r8 = r13.zzkf
            r9 = 1048575(0xfffff, float:1.469367E-39)
            if (r8 != 0) goto L_0x0035
            int[] r8 = r13.zzjy
            int r10 = r4 + 2
            r8 = r8[r10]
            r10 = r8 & r9
            int r8 = r8 >>> 20
            int r8 = r5 << r8
            if (r10 == r2) goto L_0x0036
            sun.misc.Unsafe r2 = zzjx
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
            com.google.android.gms.internal.icing.zzdm r6 = r13.zzko
            r7 = r7 & r9
            long r7 = (long) r7
            java.lang.Object r7 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r7)
            java.util.Map r6 = r6.zzg(r7)
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L_0x00b3
            java.lang.Object r4 = r13.zzab(r4)
            com.google.android.gms.internal.icing.zzdm r7 = r13.zzko
            com.google.android.gms.internal.icing.zzdk r4 = r7.zzi(r4)
            com.google.android.gms.internal.icing.zzfl r4 = r4.zzjs
            com.google.android.gms.internal.icing.zzfq r4 = r4.zzcy()
            com.google.android.gms.internal.icing.zzfq r7 = com.google.android.gms.internal.icing.zzfq.MESSAGE
            if (r4 != r7) goto L_0x00b3
            r4 = 0
            java.util.Collection r6 = r6.values()
            java.util.Iterator r6 = r6.iterator()
        L_0x0094:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00b3
            java.lang.Object r7 = r6.next()
            if (r4 != 0) goto L_0x00ac
            com.google.android.gms.internal.icing.zzec r4 = com.google.android.gms.internal.icing.zzec.zzbz()
            java.lang.Class r8 = r7.getClass()
            com.google.android.gms.internal.icing.zzef r4 = r4.zze(r8)
        L_0x00ac:
            boolean r7 = r4.zzk(r7)
            if (r7 != 0) goto L_0x0094
            r5 = 0
        L_0x00b3:
            if (r5 != 0) goto L_0x0104
            return r0
        L_0x00b6:
            boolean r5 = r13.zza((T) r14, r6, r4)
            if (r5 == 0) goto L_0x0104
            com.google.android.gms.internal.icing.zzef r4 = r13.zzaa(r4)
            boolean r4 = zza(r14, r7, r4)
            if (r4 != 0) goto L_0x0104
            return r0
        L_0x00c7:
            r6 = r7 & r9
            long r6 = (long) r6
            java.lang.Object r6 = com.google.android.gms.internal.icing.zzfd.zzo(r14, r6)
            java.util.List r6 = (java.util.List) r6
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L_0x00f0
            com.google.android.gms.internal.icing.zzef r4 = r13.zzaa(r4)
            r7 = 0
        L_0x00db:
            int r8 = r6.size()
            if (r7 >= r8) goto L_0x00f0
            java.lang.Object r8 = r6.get(r7)
            boolean r8 = r4.zzk(r8)
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
            com.google.android.gms.internal.icing.zzef r4 = r13.zzaa(r4)
            boolean r4 = zza(r14, r7, r4)
            if (r4 != 0) goto L_0x0104
            return r0
        L_0x0104:
            int r1 = r1 + 1
            goto L_0x0005
        L_0x0108:
            boolean r1 = r13.zzkd
            if (r1 == 0) goto L_0x0119
            com.google.android.gms.internal.icing.zzca<?> r1 = r13.zzkn
            com.google.android.gms.internal.icing.zzcd r14 = r1.zza(r14)
            boolean r14 = r14.isInitialized()
            if (r14 != 0) goto L_0x0119
            return r0
        L_0x0119:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzdv.zzk(java.lang.Object):boolean");
    }
}
