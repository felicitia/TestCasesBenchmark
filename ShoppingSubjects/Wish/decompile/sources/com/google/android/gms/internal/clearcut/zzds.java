package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sun.misc.Unsafe;

final class zzds<T> implements zzef<T> {
    private static final Unsafe zzmh = zzfd.zzef();
    private final int[] zzmi;
    private final Object[] zzmj;
    private final int zzmk;
    private final int zzml;
    private final int zzmm;
    private final zzdo zzmn;
    private final boolean zzmo;
    private final boolean zzmp;
    private final boolean zzmq;
    private final boolean zzmr;
    private final int[] zzms;
    private final int[] zzmt;
    private final int[] zzmu;
    private final zzdw zzmv;
    private final zzcy zzmw;
    private final zzex<?, ?> zzmx;
    private final zzbu<?> zzmy;
    private final zzdj zzmz;

    private zzds(int[] iArr, Object[] objArr, int i, int i2, int i3, zzdo zzdo, boolean z, boolean z2, int[] iArr2, int[] iArr3, int[] iArr4, zzdw zzdw, zzcy zzcy, zzex<?, ?> zzex, zzbu<?> zzbu, zzdj zzdj) {
        zzdo zzdo2 = zzdo;
        zzbu<?> zzbu2 = zzbu;
        this.zzmi = iArr;
        this.zzmj = objArr;
        this.zzmk = i;
        this.zzml = i2;
        this.zzmm = i3;
        this.zzmp = zzdo2 instanceof zzcg;
        this.zzmq = z;
        this.zzmo = zzbu2 != null && zzbu2.zze(zzdo2);
        this.zzmr = false;
        this.zzms = iArr2;
        this.zzmt = iArr3;
        this.zzmu = iArr4;
        this.zzmv = zzdw;
        this.zzmw = zzcy;
        this.zzmx = zzex;
        this.zzmy = zzbu2;
        this.zzmn = zzdo2;
        this.zzmz = zzdj;
    }

    private static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzay zzay) throws IOException {
        return zzax.zza(i, bArr, i2, i3, zzn(obj), zzay);
    }

    private static int zza(zzef<?> zzef, int i, byte[] bArr, int i2, int i3, zzcn<?> zzcn, zzay zzay) throws IOException {
        int zza = zza((zzef) zzef, bArr, i2, i3, zzay);
        while (true) {
            zzcn.add(zzay.zzff);
            if (zza >= i3) {
                break;
            }
            int zza2 = zzax.zza(bArr, zza, zzay);
            if (i != zzay.zzfd) {
                break;
            }
            zza = zza((zzef) zzef, bArr, zza2, i3, zzay);
        }
        return zza;
    }

    private static int zza(zzef zzef, byte[] bArr, int i, int i2, int i3, zzay zzay) throws IOException {
        zzds zzds = (zzds) zzef;
        Object newInstance = zzds.newInstance();
        int zza = zzds.zza((T) newInstance, bArr, i, i2, i3, zzay);
        zzds.zzc(newInstance);
        zzay.zzff = newInstance;
        return zza;
    }

    /* JADX WARNING: type inference failed for: r8v2, types: [int] */
    /* JADX WARNING: type inference failed for: r8v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zza(com.google.android.gms.internal.clearcut.zzef r6, byte[] r7, int r8, int r9, com.google.android.gms.internal.clearcut.zzay r10) throws java.io.IOException {
        /*
            int r0 = r8 + 1
            byte r8 = r7[r8]
            if (r8 >= 0) goto L_0x000c
            int r0 = com.google.android.gms.internal.clearcut.zzax.zza(r8, r7, r0, r10)
            int r8 = r10.zzfd
        L_0x000c:
            r3 = r0
            if (r8 < 0) goto L_0x0026
            int r9 = r9 - r3
            if (r8 <= r9) goto L_0x0013
            goto L_0x0026
        L_0x0013:
            java.lang.Object r9 = r6.newInstance()
            int r8 = r8 + r3
            r0 = r6
            r1 = r9
            r2 = r7
            r4 = r8
            r5 = r10
            r0.zza(r1, r2, r3, r4, r5)
            r6.zzc(r9)
            r10.zzff = r9
            return r8
        L_0x0026:
            com.google.android.gms.internal.clearcut.zzco r6 = com.google.android.gms.internal.clearcut.zzco.zzbl()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(com.google.android.gms.internal.clearcut.zzef, byte[], int, int, com.google.android.gms.internal.clearcut.zzay):int");
    }

    private static <UT, UB> int zza(zzex<UT, UB> zzex, T t) {
        return zzex.zzm(zzex.zzq(t));
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
    private final int zza(T r18, byte[] r19, int r20, int r21, int r22, int r23, int r24, int r25, int r26, long r27, int r29, com.google.android.gms.internal.clearcut.zzay r30) throws java.io.IOException {
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
            sun.misc.Unsafe r12 = zzmh
            int[] r7 = r0.zzmi
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
            com.google.android.gms.internal.clearcut.zzef r2 = r0.zzad(r6)
            r5 = r21
            r6 = r7
            r7 = r11
            int r2 = zza(r2, r3, r4, r5, r6, r7)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x0046
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x0047
        L_0x0046:
            r15 = 0
        L_0x0047:
            if (r15 != 0) goto L_0x004d
            java.lang.Object r3 = r11.zzff
            goto L_0x014d
        L_0x004d:
            java.lang.Object r3 = r11.zzff
            java.lang.Object r3 = com.google.android.gms.internal.clearcut.zzci.zza(r15, r3)
            goto L_0x014d
        L_0x0055:
            if (r5 != 0) goto L_0x0175
            int r2 = com.google.android.gms.internal.clearcut.zzax.zzb(r3, r4, r11)
            long r3 = r11.zzfe
            long r3 = com.google.android.gms.internal.clearcut.zzbk.zza(r3)
            goto L_0x0149
        L_0x0063:
            if (r5 != 0) goto L_0x0175
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r3, r4, r11)
            int r3 = r11.zzfd
            int r3 = com.google.android.gms.internal.clearcut.zzbk.zzm(r3)
            goto L_0x013c
        L_0x0071:
            if (r5 != 0) goto L_0x0175
            int r3 = com.google.android.gms.internal.clearcut.zzax.zza(r3, r4, r11)
            int r4 = r11.zzfd
            com.google.android.gms.internal.clearcut.zzck r5 = r0.zzaf(r6)
            if (r5 == 0) goto L_0x0094
            com.google.android.gms.internal.clearcut.zzcj r5 = r5.zzb(r4)
            if (r5 == 0) goto L_0x0086
            goto L_0x0094
        L_0x0086:
            com.google.android.gms.internal.clearcut.zzey r1 = zzn(r18)
            long r4 = (long) r4
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r1.zzb(r2, r4)
            r2 = r3
            return r2
        L_0x0094:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r12.putObject(r1, r9, r2)
            r2 = r3
            goto L_0x0171
        L_0x009e:
            if (r5 != r15) goto L_0x0175
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r3, r4, r11)
            int r4 = r11.zzfd
            if (r4 != 0) goto L_0x00ac
            com.google.android.gms.internal.clearcut.zzbb r3 = com.google.android.gms.internal.clearcut.zzbb.zzfi
            goto L_0x014d
        L_0x00ac:
            com.google.android.gms.internal.clearcut.zzbb r3 = com.google.android.gms.internal.clearcut.zzbb.zzb(r3, r2, r4)
            r12.putObject(r1, r9, r3)
        L_0x00b3:
            int r2 = r2 + r4
            goto L_0x0171
        L_0x00b6:
            if (r5 != r15) goto L_0x0175
            com.google.android.gms.internal.clearcut.zzef r2 = r0.zzad(r6)
            r5 = r21
            int r2 = zza(r2, r3, r4, r5, r11)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x00cd
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x00ce
        L_0x00cd:
            r15 = 0
        L_0x00ce:
            if (r15 != 0) goto L_0x00d4
            java.lang.Object r3 = r11.zzff
            goto L_0x014d
        L_0x00d4:
            java.lang.Object r3 = r11.zzff
            java.lang.Object r3 = com.google.android.gms.internal.clearcut.zzci.zza(r15, r3)
            goto L_0x014d
        L_0x00dc:
            if (r5 != r15) goto L_0x0175
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r3, r4, r11)
            int r4 = r11.zzfd
            if (r4 != 0) goto L_0x00e9
            java.lang.String r3 = ""
            goto L_0x014d
        L_0x00e9:
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r25 & r5
            if (r5 == 0) goto L_0x00fc
            int r5 = r2 + r4
            boolean r5 = com.google.android.gms.internal.clearcut.zzff.zze(r3, r2, r5)
            if (r5 != 0) goto L_0x00fc
            com.google.android.gms.internal.clearcut.zzco r1 = com.google.android.gms.internal.clearcut.zzco.zzbp()
            throw r1
        L_0x00fc:
            java.lang.String r5 = new java.lang.String
            java.nio.charset.Charset r6 = com.google.android.gms.internal.clearcut.zzci.UTF_8
            r5.<init>(r3, r2, r4, r6)
            r12.putObject(r1, r9, r5)
            goto L_0x00b3
        L_0x0107:
            if (r5 != 0) goto L_0x0175
            int r2 = com.google.android.gms.internal.clearcut.zzax.zzb(r3, r4, r11)
            long r3 = r11.zzfe
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
            int r2 = com.google.android.gms.internal.clearcut.zzax.zzc(r19, r20)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x015b
        L_0x0128:
            r2 = 1
            if (r5 != r2) goto L_0x0175
            long r2 = com.google.android.gms.internal.clearcut.zzax.zzd(r19, r20)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            goto L_0x016c
        L_0x0134:
            if (r5 != 0) goto L_0x0175
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r3, r4, r11)
            int r3 = r11.zzfd
        L_0x013c:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L_0x014d
        L_0x0141:
            if (r5 != 0) goto L_0x0175
            int r2 = com.google.android.gms.internal.clearcut.zzax.zzb(r3, r4, r11)
            long r3 = r11.zzfe
        L_0x0149:
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
        L_0x014d:
            r12.putObject(r1, r9, r3)
            goto L_0x0171
        L_0x0151:
            if (r5 != r7) goto L_0x0175
            float r2 = com.google.android.gms.internal.clearcut.zzax.zzf(r19, r20)
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
        L_0x015b:
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 4
            goto L_0x0171
        L_0x0161:
            r2 = 1
            if (r5 != r2) goto L_0x0175
            double r2 = com.google.android.gms.internal.clearcut.zzax.zze(r19, r20)
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, int, int, int, int, int, long, int, com.google.android.gms.internal.clearcut.zzay):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0216, code lost:
        if (r11.zzfe != 0) goto L_0x0218;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0218, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x021a, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x021b, code lost:
        r12.addBoolean(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x021e, code lost:
        if (r2 >= r8) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0220, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r2, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0226, code lost:
        if (r9 != r11.zzfd) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0228, code lost:
        r2 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r3, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0230, code lost:
        if (r11.zzfe == 0) goto L_0x021a;
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
        r12.add(com.google.android.gms.internal.clearcut.zzbb.zzfi);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x012c, code lost:
        r12.add(com.google.android.gms.internal.clearcut.zzbb.zzb(r7, r1, r2));
        r1 = r1 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0134, code lost:
        if (r1 >= r8) goto L_0x037e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0136, code lost:
        r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x013c, code lost:
        if (r9 != r11.zzfd) goto L_0x037e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x013e, code lost:
        r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r2, r11);
        r2 = r11.zzfd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0144, code lost:
        if (r2 != 0) goto L_0x012c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(T r18, byte[] r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, long r29, com.google.android.gms.internal.clearcut.zzay r31) throws java.io.IOException {
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
            sun.misc.Unsafe r3 = zzmh
            java.lang.Object r3 = r3.getObject(r1, r5)
            com.google.android.gms.internal.clearcut.zzcn r3 = (com.google.android.gms.internal.clearcut.zzcn) r3
            boolean r12 = r3.zzu()
            r13 = 1
            if (r12 != 0) goto L_0x0036
            int r12 = r3.size()
            if (r12 != 0) goto L_0x002c
            r12 = 10
            goto L_0x002d
        L_0x002c:
            int r12 = r12 << r13
        L_0x002d:
            com.google.android.gms.internal.clearcut.zzcn r3 = r3.zzi(r12)
            sun.misc.Unsafe r12 = zzmh
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
            com.google.android.gms.internal.clearcut.zzef r10 = r0.zzad(r10)
            r1 = r9 & -8
            r13 = r1 | 4
            r1 = r10
            r2 = r7
            r3 = r4
        L_0x004e:
            r4 = r8
            r5 = r13
            r6 = r11
            int r1 = zza(r1, r2, r3, r4, r5, r6)
            java.lang.Object r2 = r11.zzff
            r12.add(r2)
            if (r1 >= r8) goto L_0x037e
            int r3 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11)
            int r2 = r11.zzfd
            if (r9 != r2) goto L_0x037e
            r1 = r10
            r2 = r7
            goto L_0x004e
        L_0x0067:
            if (r2 != r14) goto L_0x0089
            com.google.android.gms.internal.clearcut.zzdc r12 = (com.google.android.gms.internal.clearcut.zzdc) r12
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11)
            int r2 = r11.zzfd
            int r2 = r2 + r1
        L_0x0072:
            if (r1 >= r2) goto L_0x0082
            int r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r1, r11)
            long r3 = r11.zzfe
            long r3 = com.google.android.gms.internal.clearcut.zzbk.zza(r3)
            r12.zzm(r3)
            goto L_0x0072
        L_0x0082:
            if (r1 == r2) goto L_0x037e
            com.google.android.gms.internal.clearcut.zzco r1 = com.google.android.gms.internal.clearcut.zzco.zzbl()
            throw r1
        L_0x0089:
            if (r2 != 0) goto L_0x037d
            com.google.android.gms.internal.clearcut.zzdc r12 = (com.google.android.gms.internal.clearcut.zzdc) r12
            int r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r4, r11)
        L_0x0091:
            long r2 = r11.zzfe
            long r2 = com.google.android.gms.internal.clearcut.zzbk.zza(r2)
            r12.zzm(r2)
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11)
            int r3 = r11.zzfd
            if (r9 != r3) goto L_0x037e
            int r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r2, r11)
            goto L_0x0091
        L_0x00a9:
            if (r2 != r14) goto L_0x00cb
            com.google.android.gms.internal.clearcut.zzch r12 = (com.google.android.gms.internal.clearcut.zzch) r12
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11)
            int r2 = r11.zzfd
            int r2 = r2 + r1
        L_0x00b4:
            if (r1 >= r2) goto L_0x00c4
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11)
            int r3 = r11.zzfd
            int r3 = com.google.android.gms.internal.clearcut.zzbk.zzm(r3)
            r12.zzac(r3)
            goto L_0x00b4
        L_0x00c4:
            if (r1 == r2) goto L_0x037e
            com.google.android.gms.internal.clearcut.zzco r1 = com.google.android.gms.internal.clearcut.zzco.zzbl()
            throw r1
        L_0x00cb:
            if (r2 != 0) goto L_0x037d
            com.google.android.gms.internal.clearcut.zzch r12 = (com.google.android.gms.internal.clearcut.zzch) r12
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11)
        L_0x00d3:
            int r2 = r11.zzfd
            int r2 = com.google.android.gms.internal.clearcut.zzbk.zzm(r2)
            r12.zzac(r2)
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11)
            int r3 = r11.zzfd
            if (r9 != r3) goto L_0x037e
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r2, r11)
            goto L_0x00d3
        L_0x00eb:
            if (r2 != r14) goto L_0x00f2
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r12, r11)
            goto L_0x00fd
        L_0x00f2:
            if (r2 != 0) goto L_0x037d
            r2 = r9
            r3 = r7
            r5 = r8
            r6 = r12
            r7 = r11
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r2, r3, r4, r5, r6, r7)
        L_0x00fd:
            com.google.android.gms.internal.clearcut.zzcg r1 = (com.google.android.gms.internal.clearcut.zzcg) r1
            com.google.android.gms.internal.clearcut.zzey r3 = r1.zzjp
            com.google.android.gms.internal.clearcut.zzey r4 = com.google.android.gms.internal.clearcut.zzey.zzea()
            if (r3 != r4) goto L_0x0108
            r3 = 0
        L_0x0108:
            com.google.android.gms.internal.clearcut.zzck r4 = r0.zzaf(r10)
            com.google.android.gms.internal.clearcut.zzex<?, ?> r5 = r0.zzmx
            r6 = r23
            java.lang.Object r3 = com.google.android.gms.internal.clearcut.zzeh.zza(r6, r12, r4, r3, r5)
            com.google.android.gms.internal.clearcut.zzey r3 = (com.google.android.gms.internal.clearcut.zzey) r3
            if (r3 == 0) goto L_0x011a
            r1.zzjp = r3
        L_0x011a:
            r1 = r2
            return r1
        L_0x011c:
            if (r2 != r14) goto L_0x037d
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11)
            int r2 = r11.zzfd
            if (r2 != 0) goto L_0x012c
        L_0x0126:
            com.google.android.gms.internal.clearcut.zzbb r2 = com.google.android.gms.internal.clearcut.zzbb.zzfi
            r12.add(r2)
            goto L_0x0134
        L_0x012c:
            com.google.android.gms.internal.clearcut.zzbb r3 = com.google.android.gms.internal.clearcut.zzbb.zzb(r7, r1, r2)
            r12.add(r3)
            int r1 = r1 + r2
        L_0x0134:
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11)
            int r3 = r11.zzfd
            if (r9 != r3) goto L_0x037e
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r2, r11)
            int r2 = r11.zzfd
            if (r2 != 0) goto L_0x012c
            goto L_0x0126
        L_0x0147:
            if (r2 != r14) goto L_0x037d
            com.google.android.gms.internal.clearcut.zzef r1 = r0.zzad(r10)
            r2 = r9
            r3 = r7
            r5 = r8
            r6 = r12
            r7 = r11
            int r1 = zza(r1, r2, r3, r4, r5, r6, r7)
            return r1
        L_0x0157:
            if (r2 != r14) goto L_0x037d
            r1 = 536870912(0x20000000, double:2.652494739E-315)
            long r15 = r26 & r1
            int r1 = (r15 > r5 ? 1 : (r15 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x0196
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11)
            int r2 = r11.zzfd
            if (r2 != 0) goto L_0x0170
        L_0x016a:
            java.lang.String r2 = ""
            r12.add(r2)
            goto L_0x017b
        L_0x0170:
            java.lang.String r3 = new java.lang.String
            java.nio.charset.Charset r4 = com.google.android.gms.internal.clearcut.zzci.UTF_8
            r3.<init>(r7, r1, r2, r4)
        L_0x0177:
            r12.add(r3)
            int r1 = r1 + r2
        L_0x017b:
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11)
            int r3 = r11.zzfd
            if (r9 != r3) goto L_0x037e
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r2, r11)
            int r2 = r11.zzfd
            if (r2 != 0) goto L_0x018e
            goto L_0x016a
        L_0x018e:
            java.lang.String r3 = new java.lang.String
            java.nio.charset.Charset r4 = com.google.android.gms.internal.clearcut.zzci.UTF_8
            r3.<init>(r7, r1, r2, r4)
            goto L_0x0177
        L_0x0196:
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11)
            int r2 = r11.zzfd
            if (r2 != 0) goto L_0x01a4
        L_0x019e:
            java.lang.String r2 = ""
            r12.add(r2)
            goto L_0x01bc
        L_0x01a4:
            int r3 = r1 + r2
            boolean r4 = com.google.android.gms.internal.clearcut.zzff.zze(r7, r1, r3)
            if (r4 != 0) goto L_0x01b1
            com.google.android.gms.internal.clearcut.zzco r1 = com.google.android.gms.internal.clearcut.zzco.zzbp()
            throw r1
        L_0x01b1:
            java.lang.String r4 = new java.lang.String
            java.nio.charset.Charset r5 = com.google.android.gms.internal.clearcut.zzci.UTF_8
            r4.<init>(r7, r1, r2, r5)
        L_0x01b8:
            r12.add(r4)
            r1 = r3
        L_0x01bc:
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11)
            int r3 = r11.zzfd
            if (r9 != r3) goto L_0x037e
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r2, r11)
            int r2 = r11.zzfd
            if (r2 != 0) goto L_0x01cf
            goto L_0x019e
        L_0x01cf:
            int r3 = r1 + r2
            boolean r4 = com.google.android.gms.internal.clearcut.zzff.zze(r7, r1, r3)
            if (r4 != 0) goto L_0x01dc
            com.google.android.gms.internal.clearcut.zzco r1 = com.google.android.gms.internal.clearcut.zzco.zzbp()
            throw r1
        L_0x01dc:
            java.lang.String r4 = new java.lang.String
            java.nio.charset.Charset r5 = com.google.android.gms.internal.clearcut.zzci.UTF_8
            r4.<init>(r7, r1, r2, r5)
            goto L_0x01b8
        L_0x01e4:
            r1 = 0
            if (r2 != r14) goto L_0x020a
            com.google.android.gms.internal.clearcut.zzaz r12 = (com.google.android.gms.internal.clearcut.zzaz) r12
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11)
            int r3 = r11.zzfd
            int r3 = r3 + r2
        L_0x01f0:
            if (r2 >= r3) goto L_0x0203
            int r2 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r2, r11)
            long r8 = r11.zzfe
            int r4 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r4 == 0) goto L_0x01fe
            r4 = 1
            goto L_0x01ff
        L_0x01fe:
            r4 = 0
        L_0x01ff:
            r12.addBoolean(r4)
            goto L_0x01f0
        L_0x0203:
            if (r2 == r3) goto L_0x011a
            com.google.android.gms.internal.clearcut.zzco r1 = com.google.android.gms.internal.clearcut.zzco.zzbl()
            throw r1
        L_0x020a:
            if (r2 != 0) goto L_0x037d
            com.google.android.gms.internal.clearcut.zzaz r12 = (com.google.android.gms.internal.clearcut.zzaz) r12
            int r2 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r4, r11)
            long r3 = r11.zzfe
            int r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r10 == 0) goto L_0x021a
        L_0x0218:
            r3 = 1
            goto L_0x021b
        L_0x021a:
            r3 = 0
        L_0x021b:
            r12.addBoolean(r3)
            if (r2 >= r8) goto L_0x011a
            int r3 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r2, r11)
            int r4 = r11.zzfd
            if (r9 != r4) goto L_0x011a
            int r2 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r3, r11)
            long r3 = r11.zzfe
            int r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r10 == 0) goto L_0x021a
            goto L_0x0218
        L_0x0233:
            if (r2 != r14) goto L_0x0251
            com.google.android.gms.internal.clearcut.zzch r12 = (com.google.android.gms.internal.clearcut.zzch) r12
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11)
            int r2 = r11.zzfd
            int r2 = r2 + r1
        L_0x023e:
            if (r1 >= r2) goto L_0x024a
            int r3 = com.google.android.gms.internal.clearcut.zzax.zzc(r7, r1)
            r12.zzac(r3)
            int r1 = r1 + 4
            goto L_0x023e
        L_0x024a:
            if (r1 == r2) goto L_0x037e
            com.google.android.gms.internal.clearcut.zzco r1 = com.google.android.gms.internal.clearcut.zzco.zzbl()
            throw r1
        L_0x0251:
            if (r2 != r3) goto L_0x037d
            com.google.android.gms.internal.clearcut.zzch r12 = (com.google.android.gms.internal.clearcut.zzch) r12
            int r1 = com.google.android.gms.internal.clearcut.zzax.zzc(r19, r20)
            r12.zzac(r1)
            int r1 = r4 + 4
        L_0x025e:
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11)
            int r3 = r11.zzfd
            if (r9 != r3) goto L_0x037e
            int r1 = com.google.android.gms.internal.clearcut.zzax.zzc(r7, r2)
            r12.zzac(r1)
            int r1 = r2 + 4
            goto L_0x025e
        L_0x0272:
            if (r2 != r14) goto L_0x0290
            com.google.android.gms.internal.clearcut.zzdc r12 = (com.google.android.gms.internal.clearcut.zzdc) r12
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11)
            int r2 = r11.zzfd
            int r2 = r2 + r1
        L_0x027d:
            if (r1 >= r2) goto L_0x0289
            long r3 = com.google.android.gms.internal.clearcut.zzax.zzd(r7, r1)
            r12.zzm(r3)
            int r1 = r1 + 8
            goto L_0x027d
        L_0x0289:
            if (r1 == r2) goto L_0x037e
            com.google.android.gms.internal.clearcut.zzco r1 = com.google.android.gms.internal.clearcut.zzco.zzbl()
            throw r1
        L_0x0290:
            if (r2 != r13) goto L_0x037d
            com.google.android.gms.internal.clearcut.zzdc r12 = (com.google.android.gms.internal.clearcut.zzdc) r12
            long r1 = com.google.android.gms.internal.clearcut.zzax.zzd(r19, r20)
            r12.zzm(r1)
            int r1 = r4 + 8
        L_0x029d:
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11)
            int r3 = r11.zzfd
            if (r9 != r3) goto L_0x037e
            long r3 = com.google.android.gms.internal.clearcut.zzax.zzd(r7, r2)
            r12.zzm(r3)
            int r1 = r2 + 8
            goto L_0x029d
        L_0x02b1:
            if (r2 != r14) goto L_0x02b8
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r12, r11)
            return r1
        L_0x02b8:
            if (r2 != 0) goto L_0x037d
            r1 = r9
            r2 = r7
            r3 = r4
            r4 = r8
            r5 = r12
            r6 = r11
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r1, r2, r3, r4, r5, r6)
            return r1
        L_0x02c5:
            if (r2 != r14) goto L_0x02e3
            com.google.android.gms.internal.clearcut.zzdc r12 = (com.google.android.gms.internal.clearcut.zzdc) r12
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11)
            int r2 = r11.zzfd
            int r2 = r2 + r1
        L_0x02d0:
            if (r1 >= r2) goto L_0x02dc
            int r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r1, r11)
            long r3 = r11.zzfe
            r12.zzm(r3)
            goto L_0x02d0
        L_0x02dc:
            if (r1 == r2) goto L_0x037e
            com.google.android.gms.internal.clearcut.zzco r1 = com.google.android.gms.internal.clearcut.zzco.zzbl()
            throw r1
        L_0x02e3:
            if (r2 != 0) goto L_0x037d
            com.google.android.gms.internal.clearcut.zzdc r12 = (com.google.android.gms.internal.clearcut.zzdc) r12
            int r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r4, r11)
        L_0x02eb:
            long r2 = r11.zzfe
            r12.zzm(r2)
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11)
            int r3 = r11.zzfd
            if (r9 != r3) goto L_0x037e
            int r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r7, r2, r11)
            goto L_0x02eb
        L_0x02ff:
            if (r2 != r14) goto L_0x031d
            com.google.android.gms.internal.clearcut.zzce r12 = (com.google.android.gms.internal.clearcut.zzce) r12
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11)
            int r2 = r11.zzfd
            int r2 = r2 + r1
        L_0x030a:
            if (r1 >= r2) goto L_0x0316
            float r3 = com.google.android.gms.internal.clearcut.zzax.zzf(r7, r1)
            r12.zzc(r3)
            int r1 = r1 + 4
            goto L_0x030a
        L_0x0316:
            if (r1 == r2) goto L_0x037e
            com.google.android.gms.internal.clearcut.zzco r1 = com.google.android.gms.internal.clearcut.zzco.zzbl()
            throw r1
        L_0x031d:
            if (r2 != r3) goto L_0x037d
            com.google.android.gms.internal.clearcut.zzce r12 = (com.google.android.gms.internal.clearcut.zzce) r12
            float r1 = com.google.android.gms.internal.clearcut.zzax.zzf(r19, r20)
            r12.zzc(r1)
            int r1 = r4 + 4
        L_0x032a:
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11)
            int r3 = r11.zzfd
            if (r9 != r3) goto L_0x037e
            float r1 = com.google.android.gms.internal.clearcut.zzax.zzf(r7, r2)
            r12.zzc(r1)
            int r1 = r2 + 4
            goto L_0x032a
        L_0x033e:
            if (r2 != r14) goto L_0x035c
            com.google.android.gms.internal.clearcut.zzbq r12 = (com.google.android.gms.internal.clearcut.zzbq) r12
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r4, r11)
            int r2 = r11.zzfd
            int r2 = r2 + r1
        L_0x0349:
            if (r1 >= r2) goto L_0x0355
            double r3 = com.google.android.gms.internal.clearcut.zzax.zze(r7, r1)
            r12.zzc(r3)
            int r1 = r1 + 8
            goto L_0x0349
        L_0x0355:
            if (r1 == r2) goto L_0x037e
            com.google.android.gms.internal.clearcut.zzco r1 = com.google.android.gms.internal.clearcut.zzco.zzbl()
            throw r1
        L_0x035c:
            if (r2 != r13) goto L_0x037d
            com.google.android.gms.internal.clearcut.zzbq r12 = (com.google.android.gms.internal.clearcut.zzbq) r12
            double r1 = com.google.android.gms.internal.clearcut.zzax.zze(r19, r20)
            r12.zzc(r1)
            int r1 = r4 + 8
        L_0x0369:
            if (r1 >= r8) goto L_0x037e
            int r2 = com.google.android.gms.internal.clearcut.zzax.zza(r7, r1, r11)
            int r3 = r11.zzfd
            if (r9 != r3) goto L_0x037e
            double r3 = com.google.android.gms.internal.clearcut.zzax.zze(r7, r2)
            r12.zzc(r3)
            int r1 = r2 + 8
            goto L_0x0369
        L_0x037d:
            r1 = r4
        L_0x037e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.clearcut.zzay):int");
    }

    /* JADX WARNING: type inference failed for: r9v4, types: [int] */
    /* JADX WARNING: type inference failed for: r9v11 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <K, V> int zza(T r7, byte[] r8, int r9, int r10, int r11, int r12, long r13, com.google.android.gms.internal.clearcut.zzay r15) throws java.io.IOException {
        /*
            r6 = this;
            sun.misc.Unsafe r12 = zzmh
            java.lang.Object r11 = r6.zzae(r11)
            java.lang.Object r0 = r12.getObject(r7, r13)
            com.google.android.gms.internal.clearcut.zzdj r1 = r6.zzmz
            boolean r1 = r1.zzi(r0)
            if (r1 == 0) goto L_0x0021
            com.google.android.gms.internal.clearcut.zzdj r1 = r6.zzmz
            java.lang.Object r1 = r1.zzk(r11)
            com.google.android.gms.internal.clearcut.zzdj r2 = r6.zzmz
            r2.zzb(r1, r0)
            r12.putObject(r7, r13, r1)
            r0 = r1
        L_0x0021:
            com.google.android.gms.internal.clearcut.zzdj r7 = r6.zzmz
            com.google.android.gms.internal.clearcut.zzdh r7 = r7.zzl(r11)
            com.google.android.gms.internal.clearcut.zzdj r11 = r6.zzmz
            java.util.Map r11 = r11.zzg(r0)
            int r9 = com.google.android.gms.internal.clearcut.zzax.zza(r8, r9, r15)
            int r12 = r15.zzfd
            if (r12 < 0) goto L_0x0095
            int r13 = r10 - r9
            if (r12 <= r13) goto L_0x003a
            goto L_0x0095
        L_0x003a:
            int r12 = r12 + r9
            K r13 = r7.zzmc
            V r14 = r7.zzdu
        L_0x003f:
            if (r9 >= r12) goto L_0x008a
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x004d
            int r0 = com.google.android.gms.internal.clearcut.zzax.zza(r9, r8, r0, r15)
            int r9 = r15.zzfd
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
            com.google.android.gms.internal.clearcut.zzfl r0 = r7.zzmd
            int r0 = r0.zzel()
            if (r2 != r0) goto L_0x0085
            com.google.android.gms.internal.clearcut.zzfl r3 = r7.zzmd
            V r9 = r7.zzdu
            java.lang.Class r4 = r9.getClass()
            r0 = r8
            r2 = r10
            r5 = r15
            int r9 = zza(r0, r1, r2, r3, r4, r5)
            java.lang.Object r14 = r15.zzff
            goto L_0x003f
        L_0x0070:
            com.google.android.gms.internal.clearcut.zzfl r0 = r7.zzmb
            int r0 = r0.zzel()
            if (r2 != r0) goto L_0x0085
            com.google.android.gms.internal.clearcut.zzfl r3 = r7.zzmb
            r4 = 0
            r0 = r8
            r2 = r10
            r5 = r15
            int r9 = zza(r0, r1, r2, r3, r4, r5)
            java.lang.Object r13 = r15.zzff
            goto L_0x003f
        L_0x0085:
            int r9 = com.google.android.gms.internal.clearcut.zzax.zza(r9, r8, r1, r10, r15)
            goto L_0x003f
        L_0x008a:
            if (r9 == r12) goto L_0x0091
            com.google.android.gms.internal.clearcut.zzco r7 = com.google.android.gms.internal.clearcut.zzco.zzbo()
            throw r7
        L_0x0091:
            r11.put(r13, r14)
            return r12
        L_0x0095:
            com.google.android.gms.internal.clearcut.zzco r7 = com.google.android.gms.internal.clearcut.zzco.zzbl()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, int, int, long, com.google.android.gms.internal.clearcut.zzay):int");
    }

    /* JADX WARNING: type inference failed for: r33v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v0 */
    /* JADX WARNING: type inference failed for: r12v1, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r0v16, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r5v5, types: [int] */
    /* JADX WARNING: type inference failed for: r12v3 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r12v4 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r6v5 */
    /* JADX WARNING: type inference failed for: r0v19, types: [int] */
    /* JADX WARNING: type inference failed for: r1v8, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v5 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r9v7 */
    /* JADX WARNING: type inference failed for: r6v7 */
    /* JADX WARNING: type inference failed for: r30v0 */
    /* JADX WARNING: type inference failed for: r6v8 */
    /* JADX WARNING: type inference failed for: r30v1 */
    /* JADX WARNING: type inference failed for: r30v2 */
    /* JADX WARNING: type inference failed for: r30v3 */
    /* JADX WARNING: type inference failed for: r20v0 */
    /* JADX WARNING: type inference failed for: r11v6 */
    /* JADX WARNING: type inference failed for: r30v4 */
    /* JADX WARNING: type inference failed for: r12v6 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r2v9, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v9, types: [int] */
    /* JADX WARNING: type inference failed for: r2v10, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r2v11, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v11, types: [int] */
    /* JADX WARNING: type inference failed for: r30v5 */
    /* JADX WARNING: type inference failed for: r12v9 */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r6v15 */
    /* JADX WARNING: type inference failed for: r30v6 */
    /* JADX WARNING: type inference failed for: r1v17, types: [int] */
    /* JADX WARNING: type inference failed for: r2v14, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v10 */
    /* JADX WARNING: type inference failed for: r1v18 */
    /* JADX WARNING: type inference failed for: r11v11 */
    /* JADX WARNING: type inference failed for: r6v20 */
    /* JADX WARNING: type inference failed for: r12v11 */
    /* JADX WARNING: type inference failed for: r11v12 */
    /* JADX WARNING: type inference failed for: r1v19 */
    /* JADX WARNING: type inference failed for: r12v12 */
    /* JADX WARNING: type inference failed for: r11v13 */
    /* JADX WARNING: type inference failed for: r11v14 */
    /* JADX WARNING: type inference failed for: r12v13, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v15 */
    /* JADX WARNING: type inference failed for: r12v14, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v15 */
    /* JADX WARNING: type inference failed for: r11v16 */
    /* JADX WARNING: type inference failed for: r1v23 */
    /* JADX WARNING: type inference failed for: r11v17 */
    /* JADX WARNING: type inference failed for: r12v16, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v17 */
    /* JADX WARNING: type inference failed for: r11v18 */
    /* JADX WARNING: type inference failed for: r11v19 */
    /* JADX WARNING: type inference failed for: r12v18, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v20 */
    /* JADX WARNING: type inference failed for: r11v21 */
    /* JADX WARNING: type inference failed for: r12v19, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v22 */
    /* JADX WARNING: type inference failed for: r12v20, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v23 */
    /* JADX WARNING: type inference failed for: r12v21, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v24 */
    /* JADX WARNING: type inference failed for: r12v22, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v25 */
    /* JADX WARNING: type inference failed for: r12v23, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r12v24 */
    /* JADX WARNING: type inference failed for: r11v26 */
    /* JADX WARNING: type inference failed for: r12v25 */
    /* JADX WARNING: type inference failed for: r11v27 */
    /* JADX WARNING: type inference failed for: r11v28 */
    /* JADX WARNING: type inference failed for: r12v26, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v29, types: [int] */
    /* JADX WARNING: type inference failed for: r12v27, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v30 */
    /* JADX WARNING: type inference failed for: r12v28, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v31 */
    /* JADX WARNING: type inference failed for: r11v32 */
    /* JADX WARNING: type inference failed for: r12v29, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v33 */
    /* JADX WARNING: type inference failed for: r12v30 */
    /* JADX WARNING: type inference failed for: r12v31 */
    /* JADX WARNING: type inference failed for: r1v41, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r11v34 */
    /* JADX WARNING: type inference failed for: r11v35 */
    /* JADX WARNING: type inference failed for: r12v32 */
    /* JADX WARNING: type inference failed for: r5v17 */
    /* JADX WARNING: type inference failed for: r1v46, types: [int] */
    /* JADX WARNING: type inference failed for: r5v18 */
    /* JADX WARNING: type inference failed for: r12v33 */
    /* JADX WARNING: type inference failed for: r12v34 */
    /* JADX WARNING: type inference failed for: r1v47 */
    /* JADX WARNING: type inference failed for: r12v35 */
    /* JADX WARNING: type inference failed for: r1v48 */
    /* JADX WARNING: type inference failed for: r6v26 */
    /* JADX WARNING: type inference failed for: r30v7 */
    /* JADX WARNING: type inference failed for: r30v8 */
    /* JADX WARNING: type inference failed for: r30v9 */
    /* JADX WARNING: type inference failed for: r30v10 */
    /* JADX WARNING: type inference failed for: r1v49 */
    /* JADX WARNING: type inference failed for: r12v36 */
    /* JADX WARNING: type inference failed for: r1v50 */
    /* JADX WARNING: type inference failed for: r12v37 */
    /* JADX WARNING: type inference failed for: r12v38 */
    /* JADX WARNING: type inference failed for: r11v38 */
    /* JADX WARNING: type inference failed for: r12v39 */
    /* JADX WARNING: type inference failed for: r12v40 */
    /* JADX WARNING: type inference failed for: r12v41 */
    /* JADX WARNING: type inference failed for: r12v42 */
    /* JADX WARNING: type inference failed for: r12v43 */
    /* JADX WARNING: type inference failed for: r11v39 */
    /* JADX WARNING: type inference failed for: r12v44 */
    /* JADX WARNING: type inference failed for: r11v40 */
    /* JADX WARNING: type inference failed for: r12v45 */
    /* JADX WARNING: type inference failed for: r12v46 */
    /* JADX WARNING: type inference failed for: r12v47 */
    /* JADX WARNING: type inference failed for: r12v48 */
    /* JADX WARNING: type inference failed for: r12v49 */
    /* JADX WARNING: type inference failed for: r12v50 */
    /* JADX WARNING: type inference failed for: r12v51 */
    /* JADX WARNING: type inference failed for: r12v52 */
    /* JADX WARNING: type inference failed for: r11v41 */
    /* JADX WARNING: type inference failed for: r12v53 */
    /* JADX WARNING: type inference failed for: r11v42 */
    /* JADX WARNING: type inference failed for: r12v54 */
    /* JADX WARNING: type inference failed for: r12v55 */
    /* JADX WARNING: type inference failed for: r12v56 */
    /* JADX WARNING: type inference failed for: r12v57 */
    /* JADX WARNING: type inference failed for: r11v43 */
    /* JADX WARNING: type inference failed for: r12v58 */
    /* JADX WARNING: type inference failed for: r12v59 */
    /* JADX WARNING: type inference failed for: r12v60 */
    /* JADX WARNING: type inference failed for: r11v44 */
    /* JADX WARNING: type inference failed for: r11v45 */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0308, code lost:
        if (r0 == r15) goto L_0x032b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0329, code lost:
        if (r0 == r15) goto L_0x032b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x032d, code lost:
        r12 = r33;
        r13 = r35;
        r9 = r37;
        r15 = r14;
        r6 = r18;
        r7 = r24;
        r10 = r29;
        r1 = r30;
        r8 = -1;
        r11 = r36;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00c8, code lost:
        r0 = r21;
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0127, code lost:
        r1 = r9.zzff;
        r12 = r12;
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0129, code lost:
        r10.putObject(r14, r7, r1);
        r12 = r12;
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01c5, code lost:
        r0 = r3;
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01da, code lost:
        r10.putInt(r14, r7, r1);
        r12 = r12;
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01f0, code lost:
        r10.putLong(r14, r7, r4);
        r6 = r6 | r18;
        r1 = r11;
        r0 = r17;
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0227, code lost:
        r6 = r6 | r18;
        r12 = r12;
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0229, code lost:
        r1 = r11;
        r12 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0231, code lost:
        r2 = r0;
        r18 = r6;
        r29 = r10;
        r6 = r11;
        r14 = r15;
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r0v16, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r33v0, types: [byte[]] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r12v3
      assigns: []
      uses: []
      mth insns count: 488
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/871566395.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$34/1534130292.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$69/1017384824.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
     */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0351 A[ADDED_TO_REGION] */
    /* JADX WARNING: Unknown variable types count: 56 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(T r32, byte[] r33, int r34, int r35, int r36, com.google.android.gms.internal.clearcut.zzay r37) throws java.io.IOException {
        /*
            r31 = this;
            r15 = r31
            r14 = r32
            r12 = r33
            r13 = r35
            r11 = r36
            r9 = r37
            sun.misc.Unsafe r10 = zzmh
            r16 = 0
            r8 = -1
            r0 = r34
            r1 = 0
            r6 = 0
            r7 = -1
        L_0x0016:
            r17 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 >= r13) goto L_0x0378
            int r1 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002a
            int r0 = com.google.android.gms.internal.clearcut.zzax.zza(r0, r12, r1, r9)
            int r1 = r9.zzfd
            r4 = r0
            r5 = r1
            goto L_0x002c
        L_0x002a:
            r5 = r0
            r4 = r1
        L_0x002c:
            int r3 = r5 >>> 3
            r2 = r5 & 7
            int r1 = r15.zzai(r3)
            if (r1 == r8) goto L_0x0340
            int[] r0 = r15.zzmi
            int r18 = r1 + 1
            r0 = r0[r18]
            r18 = 267386880(0xff00000, float:2.3665827E-29)
            r18 = r0 & r18
            int r8 = r18 >>> 20
            r20 = r5
            r5 = r0 & r17
            r21 = r4
            long r4 = (long) r5
            r22 = r4
            r4 = 17
            if (r8 > r4) goto L_0x023a
            int[] r4 = r15.zzmi
            int r18 = r1 + 2
            r4 = r4[r18]
            int r18 = r4 >>> 20
            r5 = 1
            int r18 = r5 << r18
            r4 = r4 & r17
            if (r4 == r7) goto L_0x006b
            r11 = -1
            if (r7 == r11) goto L_0x0065
            long r11 = (long) r7
            r10.putInt(r14, r11, r6)
        L_0x0065:
            long r6 = (long) r4
            int r6 = r10.getInt(r14, r6)
            r7 = r4
        L_0x006b:
            r4 = 5
            switch(r8) {
                case 0: goto L_0x0212;
                case 1: goto L_0x01fc;
                case 2: goto L_0x01de;
                case 3: goto L_0x01de;
                case 4: goto L_0x01c8;
                case 5: goto L_0x01a7;
                case 6: goto L_0x0190;
                case 7: goto L_0x0171;
                case 8: goto L_0x0155;
                case 9: goto L_0x012e;
                case 10: goto L_0x0116;
                case 11: goto L_0x01c8;
                case 12: goto L_0x00e4;
                case 13: goto L_0x0190;
                case 14: goto L_0x01a7;
                case 15: goto L_0x00cc;
                case 16: goto L_0x00b0;
                case 17: goto L_0x0079;
                default: goto L_0x006f;
            }
        L_0x006f:
            r24 = r7
            r11 = r20
            r0 = r21
            r12 = r33
            goto L_0x0231
        L_0x0079:
            r0 = 3
            if (r2 != r0) goto L_0x00a9
            int r0 = r3 << 3
            r4 = r0 | 4
            com.google.android.gms.internal.clearcut.zzef r0 = r15.zzad(r1)
            r12 = r33
            r1 = r12
            r2 = r21
            r3 = r13
            r24 = r7
            r7 = r22
            r11 = r20
            r5 = r9
            int r0 = zza(r0, r1, r2, r3, r4, r5)
            r1 = r6 & r18
            if (r1 != 0) goto L_0x009d
            java.lang.Object r1 = r9.zzff
            goto L_0x0129
        L_0x009d:
            java.lang.Object r1 = r10.getObject(r14, r7)
            java.lang.Object r2 = r9.zzff
            java.lang.Object r1 = com.google.android.gms.internal.clearcut.zzci.zza(r1, r2)
            goto L_0x0129
        L_0x00a9:
            r24 = r7
            r11 = r20
            r12 = r33
            goto L_0x00c8
        L_0x00b0:
            r24 = r7
            r11 = r20
            r7 = r22
            r12 = r33
            if (r2 != 0) goto L_0x00c8
            r3 = r21
            int r17 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r3, r9)
            long r0 = r9.zzfe
            long r4 = com.google.android.gms.internal.clearcut.zzbk.zza(r0)
            goto L_0x01f0
        L_0x00c8:
            r0 = r21
            goto L_0x0231
        L_0x00cc:
            r24 = r7
            r11 = r20
            r3 = r21
            r7 = r22
            r12 = r33
            if (r2 != 0) goto L_0x01c5
            int r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r3, r9)
            int r1 = r9.zzfd
            int r1 = com.google.android.gms.internal.clearcut.zzbk.zzm(r1)
            goto L_0x01da
        L_0x00e4:
            r24 = r7
            r11 = r20
            r3 = r21
            r7 = r22
            r12 = r33
            if (r2 != 0) goto L_0x01c5
            int r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r3, r9)
            int r2 = r9.zzfd
            com.google.android.gms.internal.clearcut.zzck r1 = r15.zzaf(r1)
            if (r1 == 0) goto L_0x0111
            com.google.android.gms.internal.clearcut.zzcj r1 = r1.zzb(r2)
            if (r1 == 0) goto L_0x0103
            goto L_0x0111
        L_0x0103:
            com.google.android.gms.internal.clearcut.zzey r1 = zzn(r32)
            long r2 = (long) r2
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r1.zzb(r11, r2)
            goto L_0x0229
        L_0x0111:
            r10.putInt(r14, r7, r2)
            goto L_0x0227
        L_0x0116:
            r24 = r7
            r11 = r20
            r3 = r21
            r7 = r22
            r0 = 2
            r12 = r33
            if (r2 != r0) goto L_0x01c5
            int r0 = com.google.android.gms.internal.clearcut.zzax.zze(r12, r3, r9)
        L_0x0127:
            java.lang.Object r1 = r9.zzff
        L_0x0129:
            r10.putObject(r14, r7, r1)
            goto L_0x0227
        L_0x012e:
            r24 = r7
            r11 = r20
            r3 = r21
            r7 = r22
            r0 = 2
            r12 = r33
            if (r2 != r0) goto L_0x01c5
            com.google.android.gms.internal.clearcut.zzef r0 = r15.zzad(r1)
            int r0 = zza(r0, r12, r3, r13, r9)
            r1 = r6 & r18
            if (r1 != 0) goto L_0x014a
            java.lang.Object r1 = r9.zzff
            goto L_0x0129
        L_0x014a:
            java.lang.Object r1 = r10.getObject(r14, r7)
            java.lang.Object r2 = r9.zzff
            java.lang.Object r1 = com.google.android.gms.internal.clearcut.zzci.zza(r1, r2)
            goto L_0x0129
        L_0x0155:
            r24 = r7
            r11 = r20
            r3 = r21
            r7 = r22
            r1 = 2
            r12 = r33
            if (r2 != r1) goto L_0x01c5
            r1 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r1
            if (r0 != 0) goto L_0x016c
            int r0 = com.google.android.gms.internal.clearcut.zzax.zzc(r12, r3, r9)
            goto L_0x0127
        L_0x016c:
            int r0 = com.google.android.gms.internal.clearcut.zzax.zzd(r12, r3, r9)
            goto L_0x0127
        L_0x0171:
            r24 = r7
            r11 = r20
            r3 = r21
            r7 = r22
            r12 = r33
            if (r2 != 0) goto L_0x01c5
            int r0 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r3, r9)
            long r1 = r9.zzfe
            r3 = 0
            int r17 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r17 == 0) goto L_0x018a
            goto L_0x018b
        L_0x018a:
            r5 = 0
        L_0x018b:
            com.google.android.gms.internal.clearcut.zzfd.zza(r14, r7, r5)
            goto L_0x0227
        L_0x0190:
            r24 = r7
            r11 = r20
            r3 = r21
            r7 = r22
            r12 = r33
            if (r2 != r4) goto L_0x01c5
            int r0 = com.google.android.gms.internal.clearcut.zzax.zzc(r12, r3)
            r10.putInt(r14, r7, r0)
            int r0 = r3 + 4
            goto L_0x0227
        L_0x01a7:
            r24 = r7
            r11 = r20
            r3 = r21
            r7 = r22
            r12 = r33
            if (r2 != r5) goto L_0x01c5
            long r4 = com.google.android.gms.internal.clearcut.zzax.zzd(r12, r3)
            r0 = r10
            r1 = r14
            r17 = r3
            r2 = r7
            r7 = r17
            r0.putLong(r1, r2, r4)
            int r0 = r7 + 8
            goto L_0x0227
        L_0x01c5:
            r0 = r3
            goto L_0x0231
        L_0x01c8:
            r24 = r7
            r11 = r20
            r0 = r21
            r7 = r22
            r12 = r33
            if (r2 != 0) goto L_0x0231
            int r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r0, r9)
            int r1 = r9.zzfd
        L_0x01da:
            r10.putInt(r14, r7, r1)
            goto L_0x0227
        L_0x01de:
            r24 = r7
            r11 = r20
            r0 = r21
            r7 = r22
            r12 = r33
            if (r2 != 0) goto L_0x0231
            int r17 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r0, r9)
            long r4 = r9.zzfe
        L_0x01f0:
            r0 = r10
            r1 = r14
            r2 = r7
            r0.putLong(r1, r2, r4)
            r6 = r6 | r18
            r1 = r11
            r0 = r17
            goto L_0x022a
        L_0x01fc:
            r24 = r7
            r11 = r20
            r0 = r21
            r7 = r22
            r12 = r33
            if (r2 != r4) goto L_0x0231
            float r1 = com.google.android.gms.internal.clearcut.zzax.zzf(r12, r0)
            com.google.android.gms.internal.clearcut.zzfd.zza(r14, r7, r1)
            int r0 = r0 + 4
            goto L_0x0227
        L_0x0212:
            r24 = r7
            r11 = r20
            r0 = r21
            r7 = r22
            r12 = r33
            if (r2 != r5) goto L_0x0231
            double r1 = com.google.android.gms.internal.clearcut.zzax.zze(r12, r0)
            com.google.android.gms.internal.clearcut.zzfd.zza(r14, r7, r1)
            int r0 = r0 + 8
        L_0x0227:
            r6 = r6 | r18
        L_0x0229:
            r1 = r11
        L_0x022a:
            r7 = r24
            r8 = -1
            r11 = r36
            goto L_0x0016
        L_0x0231:
            r2 = r0
            r18 = r6
            r29 = r10
            r6 = r11
            r14 = r15
            goto L_0x034d
        L_0x023a:
            r25 = r3
            r24 = r7
            r5 = r8
            r11 = r20
            r4 = r21
            r7 = r22
            r3 = 27
            if (r5 != r3) goto L_0x0285
            r3 = 2
            if (r2 != r3) goto L_0x027d
            java.lang.Object r0 = r10.getObject(r14, r7)
            com.google.android.gms.internal.clearcut.zzcn r0 = (com.google.android.gms.internal.clearcut.zzcn) r0
            boolean r2 = r0.zzu()
            if (r2 != 0) goto L_0x026a
            int r2 = r0.size()
            if (r2 != 0) goto L_0x0261
            r2 = 10
            goto L_0x0263
        L_0x0261:
            int r2 = r2 << 1
        L_0x0263:
            com.google.android.gms.internal.clearcut.zzcn r0 = r0.zzi(r2)
            r10.putObject(r14, r7, r0)
        L_0x026a:
            r5 = r0
            com.google.android.gms.internal.clearcut.zzef r0 = r15.zzad(r1)
            r1 = r11
            r2 = r12
            r3 = r4
            r4 = r13
            r18 = r6
            r6 = r9
            int r0 = zza(r0, r1, r2, r3, r4, r5, r6)
            r6 = r18
            goto L_0x022a
        L_0x027d:
            r18 = r6
            r29 = r10
            r30 = r11
            goto L_0x0348
        L_0x0285:
            r18 = r6
            r3 = 49
            if (r5 > r3) goto L_0x02d8
            r26 = r10
            long r9 = (long) r0
            r0 = r15
            r19 = r1
            r1 = r14
            r6 = r2
            r2 = r12
            r20 = r25
            r3 = r4
            r15 = r4
            r4 = r13
            r21 = r5
            r5 = r11
            r27 = r6
            r6 = r20
            r22 = r7
            r7 = r27
            r28 = r21
            r8 = r19
            r29 = r26
            r30 = r11
            r11 = r28
            r12 = r22
            r14 = r37
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 != r15) goto L_0x02c1
            r2 = r0
            r6 = r30
            r7 = r36
            r14 = r31
            goto L_0x034f
        L_0x02c1:
            r14 = r32
            r12 = r33
            r13 = r35
            r9 = r37
            r6 = r18
            r7 = r24
            r10 = r29
            r1 = r30
            r8 = -1
            r11 = r36
            r15 = r31
            goto L_0x0016
        L_0x02d8:
            r19 = r1
            r27 = r2
            r15 = r4
            r28 = r5
            r22 = r7
            r29 = r10
            r30 = r11
            r20 = r25
            r1 = 50
            r9 = r28
            if (r9 != r1) goto L_0x030e
            r7 = r27
            r1 = 2
            if (r7 != r1) goto L_0x030b
            r14 = r31
            r0 = r14
            r1 = r32
            r2 = r33
            r3 = r15
            r4 = r35
            r5 = r19
            r6 = r20
            r7 = r22
            r9 = r37
            int r0 = r0.zza(r1, r2, r3, r4, r5, r6, r7, r9)
            if (r0 != r15) goto L_0x032d
            goto L_0x032b
        L_0x030b:
            r14 = r31
            goto L_0x034a
        L_0x030e:
            r8 = r0
            r7 = r27
            r14 = r31
            r0 = r14
            r1 = r32
            r2 = r33
            r3 = r15
            r4 = r35
            r5 = r30
            r6 = r20
            r10 = r22
            r12 = r19
            r13 = r37
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 != r15) goto L_0x032d
        L_0x032b:
            r2 = r0
            goto L_0x034b
        L_0x032d:
            r12 = r33
            r13 = r35
            r9 = r37
            r15 = r14
            r6 = r18
            r7 = r24
            r10 = r29
            r1 = r30
            r8 = -1
            r11 = r36
            goto L_0x0374
        L_0x0340:
            r30 = r5
            r18 = r6
            r24 = r7
            r29 = r10
        L_0x0348:
            r14 = r15
            r15 = r4
        L_0x034a:
            r2 = r15
        L_0x034b:
            r6 = r30
        L_0x034d:
            r7 = r36
        L_0x034f:
            if (r6 != r7) goto L_0x0357
            if (r7 != 0) goto L_0x0354
            goto L_0x0357
        L_0x0354:
            r8 = r2
            r9 = r6
            goto L_0x0382
        L_0x0357:
            r0 = r6
            r1 = r33
            r3 = r35
            r4 = r32
            r5 = r37
            int r0 = zza(r0, r1, r2, r3, r4, r5)
            r12 = r33
            r13 = r35
            r9 = r37
            r1 = r6
            r11 = r7
            r15 = r14
            r6 = r18
            r7 = r24
            r10 = r29
            r8 = -1
        L_0x0374:
            r14 = r32
            goto L_0x0016
        L_0x0378:
            r18 = r6
            r24 = r7
            r29 = r10
            r7 = r11
            r14 = r15
            r8 = r0
            r9 = r1
        L_0x0382:
            r1 = r18
            r0 = r24
            r2 = -1
            if (r0 == r2) goto L_0x0392
            long r2 = (long) r0
            r10 = r32
            r0 = r29
            r0.putInt(r10, r2, r1)
            goto L_0x0394
        L_0x0392:
            r10 = r32
        L_0x0394:
            int[] r0 = r14.zzmt
            if (r0 == 0) goto L_0x03d4
            r0 = 0
            int[] r11 = r14.zzmt
            int r12 = r11.length
            r5 = r0
            r13 = 0
        L_0x039e:
            if (r13 >= r12) goto L_0x03cd
            r1 = r11[r13]
            com.google.android.gms.internal.clearcut.zzex<?, ?> r6 = r14.zzmx
            int[] r0 = r14.zzmi
            r2 = r0[r1]
            int r0 = r14.zzag(r1)
            r0 = r0 & r17
            long r3 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r3)
            if (r0 != 0) goto L_0x03b6
            goto L_0x03c8
        L_0x03b6:
            com.google.android.gms.internal.clearcut.zzck r4 = r14.zzaf(r1)
            if (r4 != 0) goto L_0x03bd
            goto L_0x03c8
        L_0x03bd:
            com.google.android.gms.internal.clearcut.zzdj r3 = r14.zzmz
            java.util.Map r3 = r3.zzg(r0)
            r0 = r14
            java.lang.Object r5 = r0.zza(r1, r2, r3, r4, (UB) r5, r6)
        L_0x03c8:
            com.google.android.gms.internal.clearcut.zzey r5 = (com.google.android.gms.internal.clearcut.zzey) r5
            int r13 = r13 + 1
            goto L_0x039e
        L_0x03cd:
            if (r5 == 0) goto L_0x03d4
            com.google.android.gms.internal.clearcut.zzex<?, ?> r0 = r14.zzmx
            r0.zzf(r10, r5)
        L_0x03d4:
            if (r7 != 0) goto L_0x03df
            r0 = r35
            if (r8 == r0) goto L_0x03e6
            com.google.android.gms.internal.clearcut.zzco r0 = com.google.android.gms.internal.clearcut.zzco.zzbo()
            throw r0
        L_0x03df:
            r0 = r35
            if (r8 > r0) goto L_0x03e7
            if (r9 == r7) goto L_0x03e6
            goto L_0x03e7
        L_0x03e6:
            return r8
        L_0x03e7:
            com.google.android.gms.internal.clearcut.zzco r0 = com.google.android.gms.internal.clearcut.zzco.zzbo()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.clearcut.zzay):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0041, code lost:
        r2 = java.lang.Long.valueOf(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004c, code lost:
        r2 = java.lang.Integer.valueOf(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0050, code lost:
        r6.zzff = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0052, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006d, code lost:
        r6.zzff = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0071, code lost:
        return r2 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007a, code lost:
        r6.zzff = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007e, code lost:
        return r2 + 8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zza(byte[] r1, int r2, int r3, com.google.android.gms.internal.clearcut.zzfl r4, java.lang.Class<?> r5, com.google.android.gms.internal.clearcut.zzay r6) throws java.io.IOException {
        /*
            int[] r0 = com.google.android.gms.internal.clearcut.zzdt.zzgq
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
            int r1 = com.google.android.gms.internal.clearcut.zzax.zzd(r1, r2, r6)
            return r1
        L_0x0018:
            int r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r1, r2, r6)
            long r2 = r6.zzfe
            long r2 = com.google.android.gms.internal.clearcut.zzbk.zza(r2)
            goto L_0x0041
        L_0x0023:
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r1, r2, r6)
            int r2 = r6.zzfd
            int r2 = com.google.android.gms.internal.clearcut.zzbk.zzm(r2)
            goto L_0x004c
        L_0x002e:
            com.google.android.gms.internal.clearcut.zzea r4 = com.google.android.gms.internal.clearcut.zzea.zzcm()
            com.google.android.gms.internal.clearcut.zzef r4 = r4.zze(r5)
            int r1 = zza(r4, r1, r2, r3, r6)
            return r1
        L_0x003b:
            int r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r1, r2, r6)
            long r2 = r6.zzfe
        L_0x0041:
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            goto L_0x0050
        L_0x0046:
            int r1 = com.google.android.gms.internal.clearcut.zzax.zza(r1, r2, r6)
            int r2 = r6.zzfd
        L_0x004c:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
        L_0x0050:
            r6.zzff = r2
            return r1
        L_0x0053:
            float r1 = com.google.android.gms.internal.clearcut.zzax.zzf(r1, r2)
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            goto L_0x006d
        L_0x005c:
            long r3 = com.google.android.gms.internal.clearcut.zzax.zzd(r1, r2)
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            goto L_0x007a
        L_0x0065:
            int r1 = com.google.android.gms.internal.clearcut.zzax.zzc(r1, r2)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
        L_0x006d:
            r6.zzff = r1
            int r1 = r2 + 4
            return r1
        L_0x0072:
            double r3 = com.google.android.gms.internal.clearcut.zzax.zze(r1, r2)
            java.lang.Double r1 = java.lang.Double.valueOf(r3)
        L_0x007a:
            r6.zzff = r1
            int r1 = r2 + 8
            return r1
        L_0x007f:
            int r1 = com.google.android.gms.internal.clearcut.zzax.zze(r1, r2, r6)
            return r1
        L_0x0084:
            int r1 = com.google.android.gms.internal.clearcut.zzax.zzb(r1, r2, r6)
            long r2 = r6.zzfe
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(byte[], int, int, com.google.android.gms.internal.clearcut.zzfl, java.lang.Class, com.google.android.gms.internal.clearcut.zzay):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x016d A[EDGE_INSN: B:75:0x016d->B:71:0x016d ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.gms.internal.clearcut.zzds<T> zza(java.lang.Class<T> r22, com.google.android.gms.internal.clearcut.zzdm r23, com.google.android.gms.internal.clearcut.zzdw r24, com.google.android.gms.internal.clearcut.zzcy r25, com.google.android.gms.internal.clearcut.zzex<?, ?> r26, com.google.android.gms.internal.clearcut.zzbu<?> r27, com.google.android.gms.internal.clearcut.zzdj r28) {
        /*
            r0 = r23
            boolean r1 = r0 instanceof com.google.android.gms.internal.clearcut.zzec
            if (r1 == 0) goto L_0x018b
            com.google.android.gms.internal.clearcut.zzec r0 = (com.google.android.gms.internal.clearcut.zzec) r0
            int r1 = r0.zzcf()
            int r2 = com.google.android.gms.internal.clearcut.zzcg.zzg.zzkm
            r3 = 0
            r4 = 1
            if (r1 != r2) goto L_0x0014
            r12 = 1
            goto L_0x0015
        L_0x0014:
            r12 = 0
        L_0x0015:
            int r1 = r0.getFieldCount()
            if (r1 != 0) goto L_0x001f
            r5 = 0
            r8 = 0
            r9 = 0
            goto L_0x002d
        L_0x001f:
            int r1 = r0.zzcp()
            int r2 = r0.zzcq()
            int r5 = r0.zzcu()
            r8 = r1
            r9 = r2
        L_0x002d:
            int r1 = r5 << 2
            int[] r6 = new int[r1]
            int r1 = r5 << 1
            java.lang.Object[] r7 = new java.lang.Object[r1]
            int r1 = r0.zzcr()
            r2 = 0
            if (r1 <= 0) goto L_0x0044
            int r1 = r0.zzcr()
            int[] r1 = new int[r1]
            r15 = r1
            goto L_0x0045
        L_0x0044:
            r15 = r2
        L_0x0045:
            int r1 = r0.zzcs()
            if (r1 <= 0) goto L_0x0054
            int r1 = r0.zzcs()
            int[] r1 = new int[r1]
            r16 = r1
            goto L_0x0056
        L_0x0054:
            r16 = r2
        L_0x0056:
            com.google.android.gms.internal.clearcut.zzed r1 = r0.zzco()
            boolean r2 = r1.next()
            if (r2 == 0) goto L_0x016d
            int r2 = r1.zzcx()
            r5 = 0
            r10 = 0
            r11 = 0
        L_0x0067:
            int r13 = r0.zzcv()
            if (r2 >= r13) goto L_0x0080
            int r13 = r2 - r8
            int r13 = r13 << 2
            if (r5 >= r13) goto L_0x0080
            r13 = 0
        L_0x0074:
            r14 = 4
            if (r13 >= r14) goto L_0x0169
            int r14 = r5 + r13
            r17 = -1
            r6[r14] = r17
            int r13 = r13 + 1
            goto L_0x0074
        L_0x0080:
            boolean r2 = r1.zzda()
            if (r2 == 0) goto L_0x009a
            java.lang.reflect.Field r2 = r1.zzdb()
            long r13 = com.google.android.gms.internal.clearcut.zzfd.zza(r2)
            int r2 = (int) r13
            java.lang.reflect.Field r13 = r1.zzdc()
            long r13 = com.google.android.gms.internal.clearcut.zzfd.zza(r13)
            int r13 = (int) r13
        L_0x0098:
            r14 = 0
            goto L_0x00b9
        L_0x009a:
            java.lang.reflect.Field r2 = r1.zzdd()
            long r13 = com.google.android.gms.internal.clearcut.zzfd.zza(r2)
            int r2 = (int) r13
            boolean r13 = r1.zzde()
            if (r13 == 0) goto L_0x00b7
            java.lang.reflect.Field r13 = r1.zzdf()
            long r13 = com.google.android.gms.internal.clearcut.zzfd.zza(r13)
            int r13 = (int) r13
            int r14 = r1.zzdg()
            goto L_0x00b9
        L_0x00b7:
            r13 = 0
            goto L_0x0098
        L_0x00b9:
            int r17 = r1.zzcx()
            r6[r5] = r17
            int r17 = r5 + 1
            boolean r18 = r1.zzdi()
            if (r18 == 0) goto L_0x00ca
            r18 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x00cc
        L_0x00ca:
            r18 = 0
        L_0x00cc:
            boolean r19 = r1.zzdh()
            if (r19 == 0) goto L_0x00d5
            r19 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x00d7
        L_0x00d5:
            r19 = 0
        L_0x00d7:
            r18 = r18 | r19
            int r19 = r1.zzcy()
            int r19 = r19 << 20
            r18 = r18 | r19
            r2 = r18 | r2
            r6[r17] = r2
            int r2 = r5 + 2
            int r14 = r14 << 20
            r13 = r13 | r14
            r6[r2] = r13
            java.lang.Object r2 = r1.zzdl()
            if (r2 == 0) goto L_0x0119
            int r2 = r5 / 4
            int r2 = r2 << r4
            java.lang.Object r13 = r1.zzdl()
            r7[r2] = r13
            java.lang.Object r13 = r1.zzdj()
            if (r13 == 0) goto L_0x010a
            int r2 = r2 + 1
            java.lang.Object r13 = r1.zzdj()
            r7[r2] = r13
            goto L_0x013a
        L_0x010a:
            java.lang.Object r13 = r1.zzdk()
            if (r13 == 0) goto L_0x013a
            int r2 = r2 + 1
            java.lang.Object r13 = r1.zzdk()
            r7[r2] = r13
            goto L_0x013a
        L_0x0119:
            java.lang.Object r2 = r1.zzdj()
            if (r2 == 0) goto L_0x012a
            int r2 = r5 / 4
            int r2 = r2 << r4
            int r2 = r2 + r4
            java.lang.Object r13 = r1.zzdj()
            r7[r2] = r13
            goto L_0x013a
        L_0x012a:
            java.lang.Object r2 = r1.zzdk()
            if (r2 == 0) goto L_0x013a
            int r2 = r5 / 4
            int r2 = r2 << r4
            int r2 = r2 + r4
            java.lang.Object r13 = r1.zzdk()
            r7[r2] = r13
        L_0x013a:
            int r2 = r1.zzcy()
            com.google.android.gms.internal.clearcut.zzcb r13 = com.google.android.gms.internal.clearcut.zzcb.MAP
            int r13 = r13.ordinal()
            if (r2 != r13) goto L_0x014c
            int r2 = r10 + 1
            r15[r10] = r5
            r10 = r2
            goto L_0x015f
        L_0x014c:
            r13 = 18
            if (r2 < r13) goto L_0x015f
            r13 = 49
            if (r2 > r13) goto L_0x015f
            int r2 = r11 + 1
            r13 = r6[r17]
            r14 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r13 & r14
            r16[r11] = r13
            r11 = r2
        L_0x015f:
            boolean r2 = r1.next()
            if (r2 == 0) goto L_0x016d
            int r2 = r1.zzcx()
        L_0x0169:
            int r5 = r5 + 4
            goto L_0x0067
        L_0x016d:
            com.google.android.gms.internal.clearcut.zzds r1 = new com.google.android.gms.internal.clearcut.zzds
            int r10 = r0.zzcv()
            com.google.android.gms.internal.clearcut.zzdo r11 = r0.zzch()
            r13 = 0
            int[] r14 = r0.zzct()
            r5 = r1
            r17 = r24
            r18 = r25
            r19 = r26
            r20 = r27
            r21 = r28
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            return r1
        L_0x018b:
            com.google.android.gms.internal.clearcut.zzes r0 = (com.google.android.gms.internal.clearcut.zzes) r0
            r0.zzcf()
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Class, com.google.android.gms.internal.clearcut.zzdm, com.google.android.gms.internal.clearcut.zzdw, com.google.android.gms.internal.clearcut.zzcy, com.google.android.gms.internal.clearcut.zzex, com.google.android.gms.internal.clearcut.zzbu, com.google.android.gms.internal.clearcut.zzdj):com.google.android.gms.internal.clearcut.zzds");
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzck<?> zzck, UB ub, zzex<UT, UB> zzex) {
        zzdh zzl = this.zzmz.zzl(zzae(i));
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (zzck.zzb(((Integer) entry.getValue()).intValue()) == null) {
                if (ub == null) {
                    ub = zzex.zzdz();
                }
                zzbg zzk = zzbb.zzk(zzdg.zza(zzl, entry.getKey(), entry.getValue()));
                try {
                    zzdg.zza(zzk.zzae(), zzl, entry.getKey(), entry.getValue());
                    zzex.zza(ub, i2, zzk.zzad());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    private static void zza(int i, Object obj, zzfr zzfr) throws IOException {
        if (obj instanceof String) {
            zzfr.zza(i, (String) obj);
        } else {
            zzfr.zza(i, (zzbb) obj);
        }
    }

    private static <UT, UB> void zza(zzex<UT, UB> zzex, T t, zzfr zzfr) throws IOException {
        zzex.zza(zzex.zzq(t), zzfr);
    }

    private final <K, V> void zza(zzfr zzfr, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzfr.zza(i, this.zzmz.zzl(zzae(i2)), this.zzmz.zzh(obj));
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzag = (long) (zzag(i) & 1048575);
        if (zza(t2, i)) {
            Object zzo = zzfd.zzo(t, zzag);
            Object zzo2 = zzfd.zzo(t2, zzag);
            if (zzo == null || zzo2 == null) {
                if (zzo2 != null) {
                    zzfd.zza((Object) t, zzag, zzo2);
                    zzb(t, i);
                }
                return;
            }
            zzfd.zza((Object) t, zzag, zzci.zza(zzo, zzo2));
            zzb(t, i);
        }
    }

    private final boolean zza(T t, int i) {
        if (this.zzmq) {
            int zzag = zzag(i);
            long j = (long) (zzag & 1048575);
            switch ((zzag & 267386880) >>> 20) {
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
                    if (zzo instanceof zzbb) {
                        return !zzbb.zzfi.equals(zzo);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzfd.zzo(t, j) != null;
                case 10:
                    return !zzbb.zzfi.equals(zzfd.zzo(t, j));
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
            int zzah = zzah(i);
            return (zzfd.zzj(t, (long) (zzah & 1048575)) & (1 << (zzah >>> 20))) != 0;
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzfd.zzj(t, (long) (zzah(i2) & 1048575)) == i;
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        return this.zzmq ? zza(t, i) : (i2 & i3) != 0;
    }

    private static boolean zza(Object obj, int i, zzef zzef) {
        return zzef.zzo(zzfd.zzo(obj, (long) (i & 1048575)));
    }

    private final zzef zzad(int i) {
        int i2 = (i / 4) << 1;
        zzef zzef = (zzef) this.zzmj[i2];
        if (zzef != null) {
            return zzef;
        }
        zzef zze = zzea.zzcm().zze((Class) this.zzmj[i2 + 1]);
        this.zzmj[i2] = zze;
        return zze;
    }

    private final Object zzae(int i) {
        return this.zzmj[(i / 4) << 1];
    }

    private final zzck<?> zzaf(int i) {
        return (zzck) this.zzmj[((i / 4) << 1) + 1];
    }

    private final int zzag(int i) {
        return this.zzmi[i + 1];
    }

    private final int zzah(int i) {
        return this.zzmi[i + 2];
    }

    private final int zzai(int i) {
        if (i >= this.zzmk) {
            if (i < this.zzmm) {
                int i2 = (i - this.zzmk) << 2;
                if (this.zzmi[i2] == i) {
                    return i2;
                }
                return -1;
            } else if (i <= this.zzml) {
                int i3 = this.zzmm - this.zzmk;
                int length = (this.zzmi.length / 4) - 1;
                while (i3 <= length) {
                    int i4 = (length + i3) >>> 1;
                    int i5 = i4 << 2;
                    int i6 = this.zzmi[i5];
                    if (i == i6) {
                        return i5;
                    }
                    if (i < i6) {
                        length = i4 - 1;
                    } else {
                        i3 = i4 + 1;
                    }
                }
            }
        }
        return -1;
    }

    private final void zzb(T t, int i) {
        if (!this.zzmq) {
            int zzah = zzah(i);
            long j = (long) (zzah & 1048575);
            zzfd.zza((Object) t, j, zzfd.zzj(t, j) | (1 << (zzah >>> 20)));
        }
    }

    private final void zzb(T t, int i, int i2) {
        zzfd.zza((Object) t, (long) (zzah(i2) & 1048575), i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x02bc, code lost:
        com.google.android.gms.internal.clearcut.zzeh.zze(r9, r4, r2, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x02c8, code lost:
        com.google.android.gms.internal.clearcut.zzeh.zzj(r9, (java.util.List) r8.getObject(r1, r4), r2, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x02da, code lost:
        com.google.android.gms.internal.clearcut.zzeh.zzg(r9, (java.util.List) r8.getObject(r1, r4), r2, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x02ec, code lost:
        com.google.android.gms.internal.clearcut.zzeh.zzl(r9, (java.util.List) r8.getObject(r1, r4), r2, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x02fe, code lost:
        com.google.android.gms.internal.clearcut.zzeh.zzm(r9, (java.util.List) r8.getObject(r1, r4), r2, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0310, code lost:
        com.google.android.gms.internal.clearcut.zzeh.zzi(r9, (java.util.List) r8.getObject(r1, r4), r2, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x04f1, code lost:
        r5 = r12 + 4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x04f7  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r21, com.google.android.gms.internal.clearcut.zzfr r22) throws java.io.IOException {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r22
            boolean r3 = r0.zzmo
            if (r3 == 0) goto L_0x0021
            com.google.android.gms.internal.clearcut.zzbu<?> r3 = r0.zzmy
            com.google.android.gms.internal.clearcut.zzby r3 = r3.zza(r1)
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
            int[] r7 = r0.zzmi
            int r7 = r7.length
            sun.misc.Unsafe r8 = zzmh
            r10 = r5
            r5 = 0
            r11 = 0
        L_0x002c:
            if (r5 >= r7) goto L_0x04f5
            int r12 = r0.zzag(r5)
            int[] r13 = r0.zzmi
            r13 = r13[r5]
            r14 = 267386880(0xff00000, float:2.3665827E-29)
            r14 = r14 & r12
            int r14 = r14 >>> 20
            boolean r15 = r0.zzmq
            r16 = 1048575(0xfffff, float:1.469367E-39)
            if (r15 != 0) goto L_0x0061
            r15 = 17
            if (r14 > r15) goto L_0x0061
            int[] r15 = r0.zzmi
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
            com.google.android.gms.internal.clearcut.zzbu<?> r4 = r0.zzmy
            int r4 = r4.zza(r10)
            if (r4 > r13) goto L_0x0083
            com.google.android.gms.internal.clearcut.zzbu<?> r4 = r0.zzmy
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
            com.google.android.gms.internal.clearcut.zzef r5 = r0.zzad(r12)
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
            com.google.android.gms.internal.clearcut.zzbb r4 = (com.google.android.gms.internal.clearcut.zzbb) r4
            r2.zza(r13, r4)
            goto L_0x008b
        L_0x0115:
            r12 = r18
            boolean r9 = r0.zza((T) r1, r13, r12)
            if (r9 == 0) goto L_0x008b
            java.lang.Object r4 = r8.getObject(r1, r4)
            com.google.android.gms.internal.clearcut.zzef r5 = r0.zzad(r12)
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
            r2.zzb(r13, r4)
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
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzef r5 = r0.zzad(r12)
            com.google.android.gms.internal.clearcut.zzeh.zzb(r9, r4, r2, r5)
            goto L_0x008b
        L_0x01e3:
            r12 = r18
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            r13 = 1
            goto L_0x02bc
        L_0x01f2:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            goto L_0x02c8
        L_0x01fb:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            goto L_0x02da
        L_0x0204:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            goto L_0x02ec
        L_0x020d:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            goto L_0x02fe
        L_0x0216:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            goto L_0x0310
        L_0x021f:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzn(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0231:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzk(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0243:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzf(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0255:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzh(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0267:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzd(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0279:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzc(r9, r4, r2, r13)
            goto L_0x008b
        L_0x028b:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzb(r9, r4, r2, r13)
            goto L_0x008b
        L_0x029d:
            r12 = r18
            r13 = 1
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zza(r9, r4, r2, r13)
            goto L_0x008b
        L_0x02af:
            r12 = r18
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            r13 = 0
        L_0x02bc:
            com.google.android.gms.internal.clearcut.zzeh.zze(r9, r4, r2, r13)
            goto L_0x008b
        L_0x02c1:
            r12 = r18
            r13 = 0
            int[] r9 = r0.zzmi
            r9 = r9[r12]
        L_0x02c8:
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzj(r9, r4, r2, r13)
            goto L_0x008b
        L_0x02d3:
            r12 = r18
            r13 = 0
            int[] r9 = r0.zzmi
            r9 = r9[r12]
        L_0x02da:
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzg(r9, r4, r2, r13)
            goto L_0x008b
        L_0x02e5:
            r12 = r18
            r13 = 0
            int[] r9 = r0.zzmi
            r9 = r9[r12]
        L_0x02ec:
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzl(r9, r4, r2, r13)
            goto L_0x008b
        L_0x02f7:
            r12 = r18
            r13 = 0
            int[] r9 = r0.zzmi
            r9 = r9[r12]
        L_0x02fe:
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzm(r9, r4, r2, r13)
            goto L_0x008b
        L_0x0309:
            r12 = r18
            r13 = 0
            int[] r9 = r0.zzmi
            r9 = r9[r12]
        L_0x0310:
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzi(r9, r4, r2, r13)
            goto L_0x008b
        L_0x031b:
            r12 = r18
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzb(r9, r4, r2)
            goto L_0x008b
        L_0x032c:
            r12 = r18
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzef r5 = r0.zzad(r12)
            com.google.android.gms.internal.clearcut.zzeh.zza(r9, r4, r2, r5)
            goto L_0x008b
        L_0x0341:
            r12 = r18
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zza(r9, r4, r2)
            goto L_0x008b
        L_0x0352:
            r12 = r18
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            r14 = 0
            com.google.android.gms.internal.clearcut.zzeh.zzn(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x0364:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzk(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x0376:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzf(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x0388:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzh(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x039a:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzd(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x03ac:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzc(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x03be:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zzb(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x03d0:
            r12 = r18
            r14 = 0
            int[] r9 = r0.zzmi
            r9 = r9[r12]
            java.lang.Object r4 = r8.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.clearcut.zzeh.zza(r9, r4, r2, r14)
            goto L_0x04f1
        L_0x03e2:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            java.lang.Object r4 = r8.getObject(r1, r4)
            com.google.android.gms.internal.clearcut.zzef r5 = r0.zzad(r12)
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
            com.google.android.gms.internal.clearcut.zzbb r4 = (com.google.android.gms.internal.clearcut.zzbb) r4
            r2.zza(r13, r4)
            goto L_0x04f1
        L_0x0460:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            java.lang.Object r4 = r8.getObject(r1, r4)
            com.google.android.gms.internal.clearcut.zzef r5 = r0.zzad(r12)
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
            boolean r4 = com.google.android.gms.internal.clearcut.zzfd.zzl(r1, r4)
            r2.zzb(r13, r4)
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
            float r4 = com.google.android.gms.internal.clearcut.zzfd.zzm(r1, r4)
            r2.zza(r13, r4)
            goto L_0x04f1
        L_0x04e4:
            r12 = r18
            r14 = 0
            r9 = r9 & r11
            if (r9 == 0) goto L_0x04f1
            double r4 = com.google.android.gms.internal.clearcut.zzfd.zzn(r1, r4)
            r2.zza(r13, r4)
        L_0x04f1:
            int r5 = r12 + 4
            goto L_0x002c
        L_0x04f5:
            if (r10 == 0) goto L_0x050c
            com.google.android.gms.internal.clearcut.zzbu<?> r4 = r0.zzmy
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
            com.google.android.gms.internal.clearcut.zzex<?, ?> r3 = r0.zzmx
            zza(r3, (T) r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zzb(java.lang.Object, com.google.android.gms.internal.clearcut.zzfr):void");
    }

    private final void zzb(T t, T t2, int i) {
        int zzag = zzag(i);
        int i2 = this.zzmi[i];
        long j = (long) (zzag & 1048575);
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
            zzfd.zza((Object) t, j, zzci.zza(zzo, zzo2));
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

    private static zzey zzn(Object obj) {
        zzcg zzcg = (zzcg) obj;
        zzey zzey = zzcg.zzjp;
        if (zzey != zzey.zzea()) {
            return zzey;
        }
        zzey zzeb = zzey.zzeb();
        zzcg.zzjp = zzeb;
        return zzeb;
    }

    /* JADX INFO: used method not loaded: com.google.android.gms.internal.clearcut.zzeh.zzd(java.lang.Object, java.lang.Object):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005c, code lost:
        if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0070, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0082, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0096, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a8, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ba, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00cc, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e2, code lost:
        if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00f8, code lost:
        if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010e, code lost:
        if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0120, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzl(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzl(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0132, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0145, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0156, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0169, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x017c, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x018d, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01a0, code lost:
        if (com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6) == com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)) goto L_0x01a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01a2, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
        if (com.google.android.gms.internal.clearcut.zzeh.zzd(com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6), com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)) != false) goto L_0x01a3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(T r10, T r11) {
        /*
            r9 = this;
            int[] r0 = r9.zzmi
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            r3 = 1
            if (r2 >= r0) goto L_0x01aa
            int r4 = r9.zzag(r2)
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
            int r4 = r9.zzah(r2)
            r4 = r4 & r5
            long r4 = (long) r4
            int r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r4)
            int r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r4)
            if (r8 != r4) goto L_0x01a2
            java.lang.Object r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.clearcut.zzeh.zzd(r4, r5)
            if (r4 != 0) goto L_0x01a3
            goto L_0x018f
        L_0x003c:
            java.lang.Object r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            boolean r3 = com.google.android.gms.internal.clearcut.zzeh.zzd(r3, r4)
            goto L_0x01a3
        L_0x004a:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            java.lang.Object r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.clearcut.zzeh.zzd(r4, r5)
            if (r4 != 0) goto L_0x01a3
            goto L_0x01a2
        L_0x0060:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
            goto L_0x018f
        L_0x0074:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x01a2
        L_0x0086:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
            goto L_0x018f
        L_0x009a:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x01a2
        L_0x00ac:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x018f
        L_0x00be:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x01a2
        L_0x00d0:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            java.lang.Object r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.clearcut.zzeh.zzd(r4, r5)
            if (r4 != 0) goto L_0x01a3
            goto L_0x018f
        L_0x00e6:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            java.lang.Object r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.clearcut.zzeh.zzd(r4, r5)
            if (r4 != 0) goto L_0x01a3
            goto L_0x01a2
        L_0x00fc:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            java.lang.Object r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.clearcut.zzfd.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.clearcut.zzeh.zzd(r4, r5)
            if (r4 != 0) goto L_0x01a3
            goto L_0x018f
        L_0x0112:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            boolean r4 = com.google.android.gms.internal.clearcut.zzfd.zzl(r10, r6)
            boolean r5 = com.google.android.gms.internal.clearcut.zzfd.zzl(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x01a2
        L_0x0124:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x018f
        L_0x0135:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
            goto L_0x01a2
        L_0x0148:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)
            if (r4 == r5) goto L_0x01a3
            goto L_0x018f
        L_0x0159:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
            goto L_0x01a2
        L_0x016c:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
            goto L_0x018f
        L_0x017f:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            int r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.clearcut.zzfd.zzj(r11, r6)
            if (r4 == r5) goto L_0x01a3
        L_0x018f:
            goto L_0x01a2
        L_0x0190:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01a2
            long r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.clearcut.zzfd.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01a3
        L_0x01a2:
            r3 = 0
        L_0x01a3:
            if (r3 != 0) goto L_0x01a6
            return r1
        L_0x01a6:
            int r2 = r2 + 4
            goto L_0x0005
        L_0x01aa:
            com.google.android.gms.internal.clearcut.zzex<?, ?> r0 = r9.zzmx
            java.lang.Object r0 = r0.zzq(r10)
            com.google.android.gms.internal.clearcut.zzex<?, ?> r2 = r9.zzmx
            java.lang.Object r2 = r2.zzq(r11)
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x01bd
            return r1
        L_0x01bd:
            boolean r0 = r9.zzmo
            if (r0 == 0) goto L_0x01d2
            com.google.android.gms.internal.clearcut.zzbu<?> r0 = r9.zzmy
            com.google.android.gms.internal.clearcut.zzby r10 = r0.zza(r10)
            com.google.android.gms.internal.clearcut.zzbu<?> r0 = r9.zzmy
            com.google.android.gms.internal.clearcut.zzby r11 = r0.zza(r11)
            boolean r10 = r10.equals(r11)
            return r10
        L_0x01d2:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0061, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5);
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
        r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5);
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
        r3 = ((java.lang.String) com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5)).hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00fd, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzci.zzc(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0116, code lost:
        r3 = java.lang.Float.floatToIntBits(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0121, code lost:
        r3 = java.lang.Double.doubleToLongBits(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0125, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzci.zzl(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0129, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x012a, code lost:
        r1 = r1 + 4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int hashCode(T r9) {
        /*
            r8 = this;
            int[] r0 = r8.zzmi
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            if (r1 >= r0) goto L_0x012e
            int r3 = r8.zzag(r1)
            int[] r4 = r8.zzmi
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
            java.lang.Object r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5)
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
            java.lang.Object r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5)
            if (r3 == 0) goto L_0x00e6
            goto L_0x00e2
        L_0x00d1:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5)
        L_0x00d7:
            int r3 = r3.hashCode()
            goto L_0x0129
        L_0x00dc:
            java.lang.Object r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5)
            if (r3 == 0) goto L_0x00e6
        L_0x00e2:
            int r7 = r3.hashCode()
        L_0x00e6:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x012a
        L_0x00ea:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0129
        L_0x00f7:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.clearcut.zzfd.zzl(r9, r5)
        L_0x00fd:
            int r3 = com.google.android.gms.internal.clearcut.zzci.zzc(r3)
            goto L_0x0129
        L_0x0102:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.clearcut.zzfd.zzj(r9, r5)
            goto L_0x0129
        L_0x0109:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.clearcut.zzfd.zzk(r9, r5)
            goto L_0x0125
        L_0x0110:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.clearcut.zzfd.zzm(r9, r5)
        L_0x0116:
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0129
        L_0x011b:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.clearcut.zzfd.zzn(r9, r5)
        L_0x0121:
            long r3 = java.lang.Double.doubleToLongBits(r3)
        L_0x0125:
            int r3 = com.google.android.gms.internal.clearcut.zzci.zzl(r3)
        L_0x0129:
            int r2 = r2 + r3
        L_0x012a:
            int r1 = r1 + 4
            goto L_0x0005
        L_0x012e:
            int r2 = r2 * 53
            com.google.android.gms.internal.clearcut.zzex<?, ?> r0 = r8.zzmx
            java.lang.Object r0 = r0.zzq(r9)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r8.zzmo
            if (r0 == 0) goto L_0x014c
            int r2 = r2 * 53
            com.google.android.gms.internal.clearcut.zzbu<?> r0 = r8.zzmy
            com.google.android.gms.internal.clearcut.zzby r9 = r0.zza(r9)
            int r9 = r9.hashCode()
            int r2 = r2 + r9
        L_0x014c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.hashCode(java.lang.Object):int");
    }

    public final T newInstance() {
        return this.zzmv.newInstance(this.zzmn);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0385, code lost:
        r15.zzb(r9, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r8 & 1048575)), zzad(r7));
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
        r15.zza(r9, (com.google.android.gms.internal.clearcut.zzbb) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r8 & 1048575)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0413, code lost:
        r15.zza(r9, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r8 & 1048575)), zzad(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0428, code lost:
        zza(r9, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r8 & 1048575)), r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x043f, code lost:
        r15.zzb(r9, r8);
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
        r15.zzb(r10, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r9 & 1048575)), zzad(r1));
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
        r15.zza(r10, (com.google.android.gms.internal.clearcut.zzbb) com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r9 & 1048575)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x08d1, code lost:
        r15.zza(r10, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r9 & 1048575)), zzad(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x08e6, code lost:
        zza(r10, com.google.android.gms.internal.clearcut.zzfd.zzo(r14, (long) (r9 & 1048575)), r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x08fd, code lost:
        r15.zzb(r10, r9);
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
    public final void zza(T r14, com.google.android.gms.internal.clearcut.zzfr r15) throws java.io.IOException {
        /*
            r13 = this;
            int r0 = r15.zzaj()
            int r1 = com.google.android.gms.internal.clearcut.zzcg.zzg.zzkp
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x04cf
            com.google.android.gms.internal.clearcut.zzex<?, ?> r0 = r13.zzmx
            zza(r0, (T) r14, r15)
            boolean r0 = r13.zzmo
            if (r0 == 0) goto L_0x0030
            com.google.android.gms.internal.clearcut.zzbu<?> r0 = r13.zzmy
            com.google.android.gms.internal.clearcut.zzby r0 = r0.zza(r14)
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
            int[] r7 = r13.zzmi
            int r7 = r7.length
            int r7 = r7 + -4
        L_0x0037:
            if (r7 < 0) goto L_0x04b7
            int r8 = r13.zzag(r7)
            int[] r9 = r13.zzmi
            r9 = r9[r7]
        L_0x0041:
            if (r1 == 0) goto L_0x005f
            com.google.android.gms.internal.clearcut.zzbu<?> r10 = r13.zzmy
            int r10 = r10.zza(r1)
            if (r10 <= r9) goto L_0x005f
            com.google.android.gms.internal.clearcut.zzbu<?> r10 = r13.zzmy
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
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            r13.zza(r15, r9, r8, r7)
            goto L_0x04b3
        L_0x0157:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzef r10 = r13.zzad(r7)
            com.google.android.gms.internal.clearcut.zzeh.zzb(r9, r8, r15, r10)
            goto L_0x04b3
        L_0x016c:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zze(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x017d:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzj(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x018e:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzg(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x019f:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzl(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01b0:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzm(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01c1:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzi(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01d2:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzn(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01e3:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzk(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01f4:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzf(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0205:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzh(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0216:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzd(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0227:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzc(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0238:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzb(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0249:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zza(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x025a:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zze(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x026b:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzj(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x027c:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzg(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x028d:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzl(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x029e:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzm(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x02af:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzi(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x02c0:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzb(r9, r8, r15)
            goto L_0x04b3
        L_0x02d1:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzef r10 = r13.zzad(r7)
            com.google.android.gms.internal.clearcut.zzeh.zza(r9, r8, r15, r10)
            goto L_0x04b3
        L_0x02e6:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zza(r9, r8, r15)
            goto L_0x04b3
        L_0x02f7:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzn(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x0308:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzk(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x0319:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzf(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x032a:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzh(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x033b:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzd(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x034c:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzc(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x035d:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zzb(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x036e:
            int[] r9 = r13.zzmi
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.clearcut.zzeh.zza(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x037f:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0385:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            com.google.android.gms.internal.clearcut.zzef r10 = r13.zzad(r7)
            r15.zzb(r9, r8, r10)
            goto L_0x04b3
        L_0x0394:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r10)
        L_0x03a0:
            r15.zzb(r9, r10)
            goto L_0x04b3
        L_0x03a5:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r10)
        L_0x03b1:
            r15.zze(r9, r8)
            goto L_0x04b3
        L_0x03b6:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r10)
        L_0x03c2:
            r15.zzj(r9, r10)
            goto L_0x04b3
        L_0x03c7:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r10)
        L_0x03d3:
            r15.zzm(r9, r8)
            goto L_0x04b3
        L_0x03d8:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r10)
        L_0x03e4:
            r15.zzn(r9, r8)
            goto L_0x04b3
        L_0x03e9:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r10)
        L_0x03f5:
            r15.zzd(r9, r8)
            goto L_0x04b3
        L_0x03fa:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0400:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            com.google.android.gms.internal.clearcut.zzbb r8 = (com.google.android.gms.internal.clearcut.zzbb) r8
            r15.zza(r9, r8)
            goto L_0x04b3
        L_0x040d:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0413:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            com.google.android.gms.internal.clearcut.zzef r10 = r13.zzad(r7)
            r15.zza(r9, r8, r10)
            goto L_0x04b3
        L_0x0422:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0428:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r10)
            zza(r9, r8, r15)
            goto L_0x04b3
        L_0x0433:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.clearcut.zzfd.zzl(r14, r10)
        L_0x043f:
            r15.zzb(r9, r8)
            goto L_0x04b3
        L_0x0444:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r10)
        L_0x0450:
            r15.zzf(r9, r8)
            goto L_0x04b3
        L_0x0454:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r10)
        L_0x0460:
            r15.zzc(r9, r10)
            goto L_0x04b3
        L_0x0464:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r10)
        L_0x0470:
            r15.zzc(r9, r8)
            goto L_0x04b3
        L_0x0474:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r10)
        L_0x0480:
            r15.zza(r9, r10)
            goto L_0x04b3
        L_0x0484:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r10)
        L_0x0490:
            r15.zzi(r9, r10)
            goto L_0x04b3
        L_0x0494:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.clearcut.zzfd.zzm(r14, r10)
        L_0x04a0:
            r15.zza(r9, r8)
            goto L_0x04b3
        L_0x04a4:
            boolean r10 = r13.zza((T) r14, r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.clearcut.zzfd.zzn(r14, r10)
        L_0x04b0:
            r15.zza(r9, r10)
        L_0x04b3:
            int r7 = r7 + -4
            goto L_0x0037
        L_0x04b7:
            if (r1 == 0) goto L_0x04ce
            com.google.android.gms.internal.clearcut.zzbu<?> r14 = r13.zzmy
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
            boolean r0 = r13.zzmq
            if (r0 == 0) goto L_0x0992
            boolean r0 = r13.zzmo
            if (r0 == 0) goto L_0x04ee
            com.google.android.gms.internal.clearcut.zzbu<?> r0 = r13.zzmy
            com.google.android.gms.internal.clearcut.zzby r0 = r0.zza(r14)
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
            int[] r7 = r13.zzmi
            int r7 = r7.length
            r8 = r1
            r1 = 0
        L_0x04f5:
            if (r1 >= r7) goto L_0x0975
            int r9 = r13.zzag(r1)
            int[] r10 = r13.zzmi
            r10 = r10[r1]
        L_0x04ff:
            if (r8 == 0) goto L_0x051d
            com.google.android.gms.internal.clearcut.zzbu<?> r11 = r13.zzmy
            int r11 = r11.zza(r8)
            if (r11 > r10) goto L_0x051d
            com.google.android.gms.internal.clearcut.zzbu<?> r11 = r13.zzmy
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
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            r13.zza(r15, r10, r9, r1)
            goto L_0x0971
        L_0x0615:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzef r11 = r13.zzad(r1)
            com.google.android.gms.internal.clearcut.zzeh.zzb(r10, r9, r15, r11)
            goto L_0x0971
        L_0x062a:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zze(r10, r9, r15, r4)
            goto L_0x0971
        L_0x063b:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzj(r10, r9, r15, r4)
            goto L_0x0971
        L_0x064c:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzg(r10, r9, r15, r4)
            goto L_0x0971
        L_0x065d:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzl(r10, r9, r15, r4)
            goto L_0x0971
        L_0x066e:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzm(r10, r9, r15, r4)
            goto L_0x0971
        L_0x067f:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzi(r10, r9, r15, r4)
            goto L_0x0971
        L_0x0690:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzn(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06a1:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzk(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06b2:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzf(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06c3:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzh(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06d4:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzd(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06e5:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzc(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06f6:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzb(r10, r9, r15, r4)
            goto L_0x0971
        L_0x0707:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zza(r10, r9, r15, r4)
            goto L_0x0971
        L_0x0718:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zze(r10, r9, r15, r5)
            goto L_0x0971
        L_0x0729:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzj(r10, r9, r15, r5)
            goto L_0x0971
        L_0x073a:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzg(r10, r9, r15, r5)
            goto L_0x0971
        L_0x074b:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzl(r10, r9, r15, r5)
            goto L_0x0971
        L_0x075c:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzm(r10, r9, r15, r5)
            goto L_0x0971
        L_0x076d:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzi(r10, r9, r15, r5)
            goto L_0x0971
        L_0x077e:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzb(r10, r9, r15)
            goto L_0x0971
        L_0x078f:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzef r11 = r13.zzad(r1)
            com.google.android.gms.internal.clearcut.zzeh.zza(r10, r9, r15, r11)
            goto L_0x0971
        L_0x07a4:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zza(r10, r9, r15)
            goto L_0x0971
        L_0x07b5:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzn(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07c6:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzk(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07d7:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzf(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07e8:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzh(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07f9:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzd(r10, r9, r15, r5)
            goto L_0x0971
        L_0x080a:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzc(r10, r9, r15, r5)
            goto L_0x0971
        L_0x081b:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zzb(r10, r9, r15, r5)
            goto L_0x0971
        L_0x082c:
            int[] r10 = r13.zzmi
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.clearcut.zzeh.zza(r10, r9, r15, r5)
            goto L_0x0971
        L_0x083d:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
        L_0x0843:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            com.google.android.gms.internal.clearcut.zzef r11 = r13.zzad(r1)
            r15.zzb(r10, r9, r11)
            goto L_0x0971
        L_0x0852:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r11)
        L_0x085e:
            r15.zzb(r10, r11)
            goto L_0x0971
        L_0x0863:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r11)
        L_0x086f:
            r15.zze(r10, r9)
            goto L_0x0971
        L_0x0874:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r11)
        L_0x0880:
            r15.zzj(r10, r11)
            goto L_0x0971
        L_0x0885:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r11)
        L_0x0891:
            r15.zzm(r10, r9)
            goto L_0x0971
        L_0x0896:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r11)
        L_0x08a2:
            r15.zzn(r10, r9)
            goto L_0x0971
        L_0x08a7:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r11)
        L_0x08b3:
            r15.zzd(r10, r9)
            goto L_0x0971
        L_0x08b8:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
        L_0x08be:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            com.google.android.gms.internal.clearcut.zzbb r9 = (com.google.android.gms.internal.clearcut.zzbb) r9
            r15.zza(r10, r9)
            goto L_0x0971
        L_0x08cb:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
        L_0x08d1:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            com.google.android.gms.internal.clearcut.zzef r11 = r13.zzad(r1)
            r15.zza(r10, r9, r11)
            goto L_0x0971
        L_0x08e0:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
        L_0x08e6:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.clearcut.zzfd.zzo(r14, r11)
            zza(r10, r9, r15)
            goto L_0x0971
        L_0x08f1:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.clearcut.zzfd.zzl(r14, r11)
        L_0x08fd:
            r15.zzb(r10, r9)
            goto L_0x0971
        L_0x0902:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r11)
        L_0x090e:
            r15.zzf(r10, r9)
            goto L_0x0971
        L_0x0912:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r11)
        L_0x091e:
            r15.zzc(r10, r11)
            goto L_0x0971
        L_0x0922:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.clearcut.zzfd.zzj(r14, r11)
        L_0x092e:
            r15.zzc(r10, r9)
            goto L_0x0971
        L_0x0932:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r11)
        L_0x093e:
            r15.zza(r10, r11)
            goto L_0x0971
        L_0x0942:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.clearcut.zzfd.zzk(r14, r11)
        L_0x094e:
            r15.zzi(r10, r11)
            goto L_0x0971
        L_0x0952:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.clearcut.zzfd.zzm(r14, r11)
        L_0x095e:
            r15.zza(r10, r9)
            goto L_0x0971
        L_0x0962:
            boolean r11 = r13.zza((T) r14, r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.clearcut.zzfd.zzn(r14, r11)
        L_0x096e:
            r15.zza(r10, r11)
        L_0x0971:
            int r1 = r1 + 4
            goto L_0x04f5
        L_0x0975:
            if (r8 == 0) goto L_0x098c
            com.google.android.gms.internal.clearcut.zzbu<?> r1 = r13.zzmy
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
            com.google.android.gms.internal.clearcut.zzex<?, ?> r0 = r13.zzmx
            zza(r0, (T) r14, r15)
            return
        L_0x0992:
            r13.zzb((T) r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, com.google.android.gms.internal.clearcut.zzfr):void");
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
        r1 = r11.zzff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0075, code lost:
        r9.putObject(r14, r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00cd, code lost:
        if (r7 == 0) goto L_0x00cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00cf, code lost:
        r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r10, r11);
        r1 = r11.zzfd;
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
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/871566395.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$34/1534130292.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$69/1017384824.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 16 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r25, byte[] r26, int r27, int r28, com.google.android.gms.internal.clearcut.zzay r29) throws java.io.IOException {
        /*
            r24 = this;
            r15 = r24
            r14 = r25
            r12 = r26
            r13 = r28
            r11 = r29
            boolean r0 = r15.zzmq
            if (r0 == 0) goto L_0x01d8
            sun.misc.Unsafe r9 = zzmh
            r0 = r27
        L_0x0012:
            if (r0 >= r13) goto L_0x01cf
            int r1 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0024
            int r0 = com.google.android.gms.internal.clearcut.zzax.zza(r0, r12, r1, r11)
            int r1 = r11.zzfd
            r10 = r0
            r16 = r1
            goto L_0x0027
        L_0x0024:
            r16 = r0
            r10 = r1
        L_0x0027:
            int r6 = r16 >>> 3
            r7 = r16 & 7
            int r8 = r15.zzai(r6)
            if (r8 < 0) goto L_0x01af
            int[] r0 = r15.zzmi
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
            int r6 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r10, r11)
            long r0 = r11.zzfe
            long r4 = com.google.android.gms.internal.clearcut.zzbk.zza(r0)
            goto L_0x00e2
        L_0x005b:
            if (r7 != 0) goto L_0x01af
            int r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r10, r11)
            int r1 = r11.zzfd
            int r1 = com.google.android.gms.internal.clearcut.zzbk.zzm(r1)
            goto L_0x00d5
        L_0x0069:
            if (r7 != 0) goto L_0x01af
            goto L_0x00cf
        L_0x006d:
            if (r7 != r1) goto L_0x01af
            int r0 = com.google.android.gms.internal.clearcut.zzax.zze(r12, r10, r11)
        L_0x0073:
            java.lang.Object r1 = r11.zzff
        L_0x0075:
            r9.putObject(r14, r2, r1)
            goto L_0x0012
        L_0x0079:
            if (r7 != r1) goto L_0x01af
            com.google.android.gms.internal.clearcut.zzef r0 = r15.zzad(r8)
            int r0 = zza(r0, r12, r10, r13, r11)
            java.lang.Object r1 = r9.getObject(r14, r2)
            if (r1 != 0) goto L_0x008a
            goto L_0x0073
        L_0x008a:
            java.lang.Object r4 = r11.zzff
            java.lang.Object r1 = com.google.android.gms.internal.clearcut.zzci.zza(r1, r4)
            goto L_0x0075
        L_0x0091:
            if (r7 != r1) goto L_0x01af
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r5
            if (r0 != 0) goto L_0x009d
            int r0 = com.google.android.gms.internal.clearcut.zzax.zzc(r12, r10, r11)
            goto L_0x0073
        L_0x009d:
            int r0 = com.google.android.gms.internal.clearcut.zzax.zzd(r12, r10, r11)
            goto L_0x0073
        L_0x00a2:
            if (r7 != 0) goto L_0x01af
            int r0 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r10, r11)
            long r4 = r11.zzfe
            r7 = 0
            int r1 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r1 == 0) goto L_0x00b1
            goto L_0x00b2
        L_0x00b1:
            r6 = 0
        L_0x00b2:
            com.google.android.gms.internal.clearcut.zzfd.zza(r14, r2, r6)
            goto L_0x0012
        L_0x00b7:
            if (r7 != r0) goto L_0x01af
            int r0 = com.google.android.gms.internal.clearcut.zzax.zzc(r12, r10)
            r9.putInt(r14, r2, r0)
            goto L_0x00f3
        L_0x00c1:
            if (r7 != r6) goto L_0x01af
            long r4 = com.google.android.gms.internal.clearcut.zzax.zzd(r12, r10)
            r0 = r9
            r1 = r14
            r0.putLong(r1, r2, r4)
            goto L_0x0100
        L_0x00cd:
            if (r7 != 0) goto L_0x01af
        L_0x00cf:
            int r0 = com.google.android.gms.internal.clearcut.zzax.zza(r12, r10, r11)
            int r1 = r11.zzfd
        L_0x00d5:
            r9.putInt(r14, r2, r1)
            goto L_0x0012
        L_0x00da:
            if (r7 != 0) goto L_0x01af
            int r6 = com.google.android.gms.internal.clearcut.zzax.zzb(r12, r10, r11)
            long r4 = r11.zzfe
        L_0x00e2:
            r0 = r9
            r1 = r14
            r0.putLong(r1, r2, r4)
            r0 = r6
            goto L_0x0012
        L_0x00ea:
            if (r7 != r0) goto L_0x01af
            float r0 = com.google.android.gms.internal.clearcut.zzax.zzf(r12, r10)
            com.google.android.gms.internal.clearcut.zzfd.zza(r14, r2, r0)
        L_0x00f3:
            int r0 = r10 + 4
            goto L_0x0012
        L_0x00f7:
            if (r7 != r6) goto L_0x01af
            double r0 = com.google.android.gms.internal.clearcut.zzax.zze(r12, r10)
            com.google.android.gms.internal.clearcut.zzfd.zza(r14, r2, r0)
        L_0x0100:
            int r0 = r10 + 8
            goto L_0x0012
        L_0x0104:
            r0 = 27
            if (r4 != r0) goto L_0x0139
            if (r7 != r1) goto L_0x01af
            java.lang.Object r0 = r9.getObject(r14, r2)
            com.google.android.gms.internal.clearcut.zzcn r0 = (com.google.android.gms.internal.clearcut.zzcn) r0
            boolean r1 = r0.zzu()
            if (r1 != 0) goto L_0x0128
            int r1 = r0.size()
            if (r1 != 0) goto L_0x011f
            r1 = 10
            goto L_0x0121
        L_0x011f:
            int r1 = r1 << 1
        L_0x0121:
            com.google.android.gms.internal.clearcut.zzcn r0 = r0.zzi(r1)
            r9.putObject(r14, r2, r0)
        L_0x0128:
            r5 = r0
            com.google.android.gms.internal.clearcut.zzef r0 = r15.zzad(r8)
            r1 = r16
            r2 = r12
            r3 = r10
            r4 = r13
            r6 = r11
            int r0 = zza(r0, r1, r2, r3, r4, r5, r6)
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
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
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
            int r0 = r0.zza(r1, r2, r3, r4, r5, r6, r7, r9)
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
            int r0 = r0.zza((T) r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
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
            int r0 = zza(r0, r1, r2, r3, r4, r5)
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
            com.google.android.gms.internal.clearcut.zzco r0 = com.google.android.gms.internal.clearcut.zzco.zzbo()
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
            r0.zza((T) r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.clearcut.zzay):void");
    }

    public final void zzc(T t) {
        if (this.zzmt != null) {
            for (int zzag : this.zzmt) {
                long zzag2 = (long) (zzag(zzag) & 1048575);
                Object zzo = zzfd.zzo(t, zzag2);
                if (zzo != null) {
                    zzfd.zza((Object) t, zzag2, this.zzmz.zzj(zzo));
                }
            }
        }
        if (this.zzmu != null) {
            for (int i : this.zzmu) {
                this.zzmw.zza(t, (long) i);
            }
        }
        this.zzmx.zzc(t);
        if (this.zzmo) {
            this.zzmy.zzc(t);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0037, code lost:
        com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r7, r2, com.google.android.gms.internal.clearcut.zzfd.zzo(r8, r2));
        zzb(r7, r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008f, code lost:
        com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r7, r2, com.google.android.gms.internal.clearcut.zzfd.zzo(r8, r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b9, code lost:
        com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r7, r2, com.google.android.gms.internal.clearcut.zzfd.zzj(r8, r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ce, code lost:
        com.google.android.gms.internal.clearcut.zzfd.zza((java.lang.Object) r7, r2, com.google.android.gms.internal.clearcut.zzfd.zzk(r8, r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f1, code lost:
        zzb(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f4, code lost:
        r0 = r0 + 4;
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
            int[] r1 = r6.zzmi
            int r1 = r1.length
            if (r0 >= r1) goto L_0x00f8
            int r1 = r6.zzag(r0)
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r1
            long r2 = (long) r2
            int[] r4 = r6.zzmi
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
            java.lang.Object r1 = com.google.android.gms.internal.clearcut.zzfd.zzo(r8, r2)
            com.google.android.gms.internal.clearcut.zzfd.zza(r7, r2, r1)
            r6.zzb((T) r7, r4, r0)
            goto L_0x00f4
        L_0x0043:
            com.google.android.gms.internal.clearcut.zzdj r1 = r6.zzmz
            com.google.android.gms.internal.clearcut.zzeh.zza(r1, r7, r8, r2)
            goto L_0x00f4
        L_0x004a:
            com.google.android.gms.internal.clearcut.zzcy r1 = r6.zzmw
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
            java.lang.Object r1 = com.google.android.gms.internal.clearcut.zzfd.zzo(r8, r2)
            com.google.android.gms.internal.clearcut.zzfd.zza(r7, r2, r1)
            goto L_0x00f1
        L_0x0097:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            boolean r1 = com.google.android.gms.internal.clearcut.zzfd.zzl(r8, r2)
            com.google.android.gms.internal.clearcut.zzfd.zza(r7, r2, r1)
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
            int r1 = com.google.android.gms.internal.clearcut.zzfd.zzj(r8, r2)
            com.google.android.gms.internal.clearcut.zzfd.zza(r7, r2, r1)
            goto L_0x00f1
        L_0x00c1:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            goto L_0x00ce
        L_0x00c8:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
        L_0x00ce:
            long r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r8, r2)
            com.google.android.gms.internal.clearcut.zzfd.zza(r7, r2, r4)
            goto L_0x00f1
        L_0x00d6:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            float r1 = com.google.android.gms.internal.clearcut.zzfd.zzm(r8, r2)
            com.google.android.gms.internal.clearcut.zzfd.zza(r7, r2, r1)
            goto L_0x00f1
        L_0x00e4:
            boolean r1 = r6.zza((T) r8, r0)
            if (r1 == 0) goto L_0x00f4
            double r4 = com.google.android.gms.internal.clearcut.zzfd.zzn(r8, r2)
            com.google.android.gms.internal.clearcut.zzfd.zza(r7, r2, r4)
        L_0x00f1:
            r6.zzb((T) r7, r0)
        L_0x00f4:
            int r0 = r0 + 4
            goto L_0x0009
        L_0x00f8:
            boolean r0 = r6.zzmq
            if (r0 != 0) goto L_0x010a
            com.google.android.gms.internal.clearcut.zzex<?, ?> r0 = r6.zzmx
            com.google.android.gms.internal.clearcut.zzeh.zza(r0, r7, r8)
            boolean r0 = r6.zzmo
            if (r0 == 0) goto L_0x010a
            com.google.android.gms.internal.clearcut.zzbu<?> r0 = r6.zzmy
            com.google.android.gms.internal.clearcut.zzeh.zza(r0, r7, r8)
        L_0x010a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zzc(java.lang.Object, java.lang.Object):void");
    }

    /* JADX INFO: used method not loaded: com.google.android.gms.internal.clearcut.zzeh.zzc(int, java.lang.Object, com.google.android.gms.internal.clearcut.zzef):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01d8, code lost:
        if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01e9, code lost:
        if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01fa, code lost:
        if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x020b, code lost:
        if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x020d, code lost:
        r2.putInt(r1, (long) r14, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0211, code lost:
        r3 = (com.google.android.gms.internal.clearcut.zzbn.zzr(r3) + com.google.android.gms.internal.clearcut.zzbn.zzt(r4)) + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0296, code lost:
        r13 = r13 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x029f, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, (com.google.android.gms.internal.clearcut.zzdo) com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4), zzad(r12));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02b8, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzf(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x02c7, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzi(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x02d2, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzh(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x02dd, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzk(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x02ec, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzl(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x02fb, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzh(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0306, code lost:
        r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x030a, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, (com.google.android.gms.internal.clearcut.zzbb) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0317, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzeh.zzc(r3, com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4), zzad(r12));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0331, code lost:
        if ((r4 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L_0x030a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0334, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, (java.lang.String) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0342, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x034e, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzj(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x035a, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzg(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x036a, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzg(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x037a, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zze(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x038a, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzd(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x0396, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, 0.0f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x03a2, code lost:
        r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x03aa, code lost:
        r12 = r12 + 4;
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
        if ((r6 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L_0x071b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x0480, code lost:
        if (zza(r1, r14, r3) != false) goto L_0x074d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x0518, code lost:
        if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:275:0x052a, code lost:
        if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:279:0x053c, code lost:
        if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x054e, code lost:
        if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x0560, code lost:
        if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x0572, code lost:
        if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x0584, code lost:
        if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:0x0596, code lost:
        if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x05a7, code lost:
        if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x05b8, code lost:
        if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:311:0x05c9, code lost:
        if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:315:0x05da, code lost:
        if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:319:0x05eb, code lost:
        if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:323:0x05fc, code lost:
        if (r0.zzmr != false) goto L_0x05fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x05fe, code lost:
        r2.putInt(r1, (long) r6, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:325:0x0602, code lost:
        r6 = (com.google.android.gms.internal.clearcut.zzbn.zzr(r14) + com.google.android.gms.internal.clearcut.zzbn.zzt(r9)) + r9;
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
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzc(r14, (com.google.android.gms.internal.clearcut.zzdo) r2.getObject(r1, r9), zzad(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x06d1, code lost:
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzf(r14, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:352:0x06de, code lost:
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzi(r14, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:0x06e5, code lost:
        if ((r12 & r16) != 0) goto L_0x06e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:355:0x06e7, code lost:
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzh(r14, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:0x06f0, code lost:
        if ((r12 & r16) != 0) goto L_0x06f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:358:0x06f2, code lost:
        r9 = com.google.android.gms.internal.clearcut.zzbn.zzk(r14, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x06f7, code lost:
        r4 = r4 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:363:0x0701, code lost:
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzl(r14, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:0x070e, code lost:
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzh(r14, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:0x0715, code lost:
        if ((r12 & r16) != 0) goto L_0x0717;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:0x0717, code lost:
        r6 = r2.getObject(r1, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:0x071b, code lost:
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzc(r14, (com.google.android.gms.internal.clearcut.zzbb) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:0x0724, code lost:
        if ((r12 & r16) != 0) goto L_0x0726;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:0x0726, code lost:
        r6 = com.google.android.gms.internal.clearcut.zzeh.zzc(r14, r2.getObject(r1, r9), zzad(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:378:0x073e, code lost:
        if ((r6 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L_0x071b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x0741, code lost:
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzb(r14, (java.lang.String) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ab, code lost:
        if ((r4 instanceof com.google.android.gms.internal.clearcut.zzbb) != false) goto L_0x030a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:381:0x074b, code lost:
        if ((r12 & r16) != 0) goto L_0x074d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:382:0x074d, code lost:
        r6 = com.google.android.gms.internal.clearcut.zzbn.zzc(r14, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:398:0x079d, code lost:
        r4 = r4 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:407:0x07bf, code lost:
        r3 = r3 + 4;
        r9 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0127, code lost:
        if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0139, code lost:
        if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x014b, code lost:
        if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x015d, code lost:
        if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x016f, code lost:
        if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0181, code lost:
        if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0193, code lost:
        if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01a5, code lost:
        if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01b6, code lost:
        if (r0.zzmr != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01c7, code lost:
        if (r0.zzmr != false) goto L_0x020d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzm(T r22) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            boolean r2 = r0.zzmq
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            r6 = 0
            r7 = 1
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r9 = 0
            r11 = 0
            if (r2 == 0) goto L_0x03b8
            sun.misc.Unsafe r2 = zzmh
            r12 = 0
            r13 = 0
        L_0x0016:
            int[] r14 = r0.zzmi
            int r14 = r14.length
            if (r12 >= r14) goto L_0x03b0
            int r14 = r0.zzag(r12)
            r15 = r14 & r3
            int r15 = r15 >>> 20
            int[] r3 = r0.zzmi
            r3 = r3[r12]
            r14 = r14 & r8
            long r4 = (long) r14
            com.google.android.gms.internal.clearcut.zzcb r14 = com.google.android.gms.internal.clearcut.zzcb.DOUBLE_LIST_PACKED
            int r14 = r14.id()
            if (r15 < r14) goto L_0x0041
            com.google.android.gms.internal.clearcut.zzcb r14 = com.google.android.gms.internal.clearcut.zzcb.SINT64_LIST_PACKED
            int r14 = r14.id()
            if (r15 > r14) goto L_0x0041
            int[] r14 = r0.zzmi
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
            java.lang.Object r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.clearcut.zzbb
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
            com.google.android.gms.internal.clearcut.zzdj r14 = r0.zzmz
            java.lang.Object r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4)
            java.lang.Object r5 = r0.zzae(r12)
            int r3 = r14.zzb(r3, r4, r5)
            goto L_0x0296
        L_0x010b:
            java.util.List r4 = zzd(r1, r4)
            com.google.android.gms.internal.clearcut.zzef r5 = r0.zzad(r12)
            int r3 = com.google.android.gms.internal.clearcut.zzeh.zzd(r3, r4, r5)
            goto L_0x0296
        L_0x0119:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.clearcut.zzeh.zzc(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzmr
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x012b:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.clearcut.zzeh.zzg(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzmr
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x013d:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.clearcut.zzeh.zzi(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzmr
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x014f:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.clearcut.zzeh.zzh(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzmr
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0161:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.clearcut.zzeh.zzd(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzmr
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0173:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.clearcut.zzeh.zzf(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzmr
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0185:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.clearcut.zzeh.zzj(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzmr
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0197:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.clearcut.zzeh.zzh(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzmr
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01a8:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.clearcut.zzeh.zzi(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzmr
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01b9:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.clearcut.zzeh.zze(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzmr
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01ca:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.clearcut.zzeh.zzb(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzmr
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01db:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.clearcut.zzeh.zza(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzmr
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01ec:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.clearcut.zzeh.zzh(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzmr
            if (r5 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01fd:
            java.lang.Object r4 = r2.getObject(r1, r4)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.clearcut.zzeh.zzi(r4)
            if (r4 <= 0) goto L_0x03aa
            boolean r5 = r0.zzmr
            if (r5 == 0) goto L_0x0211
        L_0x020d:
            long r14 = (long) r14
            r2.putInt(r1, r14, r4)
        L_0x0211:
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzr(r3)
            int r5 = com.google.android.gms.internal.clearcut.zzbn.zzt(r4)
            int r3 = r3 + r5
            int r3 = r3 + r4
            goto L_0x0296
        L_0x021d:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.clearcut.zzeh.zzq(r3, r4, r11)
            goto L_0x0296
        L_0x0227:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.clearcut.zzeh.zzu(r3, r4, r11)
            goto L_0x0296
        L_0x0230:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.clearcut.zzeh.zzr(r3, r4, r11)
            goto L_0x0296
        L_0x0239:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.clearcut.zzeh.zzt(r3, r4, r11)
            goto L_0x0296
        L_0x0242:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.clearcut.zzeh.zzd(r3, r4)
            goto L_0x0296
        L_0x024b:
            java.util.List r4 = zzd(r1, r4)
            com.google.android.gms.internal.clearcut.zzef r5 = r0.zzad(r12)
            int r3 = com.google.android.gms.internal.clearcut.zzeh.zzc(r3, r4, r5)
            goto L_0x0296
        L_0x0258:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.clearcut.zzeh.zzc(r3, r4)
            goto L_0x0296
        L_0x0261:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.clearcut.zzeh.zzx(r3, r4, r11)
            goto L_0x0296
        L_0x026a:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.clearcut.zzeh.zzs(r3, r4, r11)
            goto L_0x0296
        L_0x0273:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.clearcut.zzeh.zzp(r3, r4, r11)
            goto L_0x0296
        L_0x027c:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.clearcut.zzeh.zzo(r3, r4, r11)
            goto L_0x0296
        L_0x0285:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.clearcut.zzeh.zzv(r3, r4, r11)
            goto L_0x0296
        L_0x028e:
            java.util.List r4 = zzd(r1, r4)
            int r3 = com.google.android.gms.internal.clearcut.zzeh.zzw(r3, r4, r11)
        L_0x0296:
            int r13 = r13 + r3
            goto L_0x03aa
        L_0x0299:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
        L_0x029f:
            java.lang.Object r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4)
            com.google.android.gms.internal.clearcut.zzdo r4 = (com.google.android.gms.internal.clearcut.zzdo) r4
            com.google.android.gms.internal.clearcut.zzef r5 = r0.zzad(r12)
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, r4, r5)
            goto L_0x0296
        L_0x02ae:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            long r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r1, r4)
        L_0x02b8:
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzf(r3, r4)
            goto L_0x0296
        L_0x02bd:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r1, r4)
        L_0x02c7:
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzi(r3, r4)
            goto L_0x0296
        L_0x02cc:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x02d2:
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzh(r3, r9)
            goto L_0x0296
        L_0x02d7:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x02dd:
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzk(r3, r11)
            goto L_0x0296
        L_0x02e2:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r1, r4)
        L_0x02ec:
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzl(r3, r4)
            goto L_0x0296
        L_0x02f1:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r1, r4)
        L_0x02fb:
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzh(r3, r4)
            goto L_0x0296
        L_0x0300:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
        L_0x0306:
            java.lang.Object r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4)
        L_0x030a:
            com.google.android.gms.internal.clearcut.zzbb r4 = (com.google.android.gms.internal.clearcut.zzbb) r4
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, r4)
            goto L_0x0296
        L_0x0311:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
        L_0x0317:
            java.lang.Object r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4)
            com.google.android.gms.internal.clearcut.zzef r5 = r0.zzad(r12)
            int r3 = com.google.android.gms.internal.clearcut.zzeh.zzc(r3, r4, r5)
            goto L_0x0296
        L_0x0325:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            java.lang.Object r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r4)
            boolean r5 = r4 instanceof com.google.android.gms.internal.clearcut.zzbb
            if (r5 == 0) goto L_0x0334
        L_0x0333:
            goto L_0x030a
        L_0x0334:
            java.lang.String r4 = (java.lang.String) r4
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, r4)
            goto L_0x0296
        L_0x033c:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x0342:
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzc(r3, r7)
            goto L_0x0296
        L_0x0348:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x034e:
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzj(r3, r11)
            goto L_0x0296
        L_0x0354:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x035a:
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzg(r3, r9)
            goto L_0x0296
        L_0x0360:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            int r4 = com.google.android.gms.internal.clearcut.zzfd.zzj(r1, r4)
        L_0x036a:
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzg(r3, r4)
            goto L_0x0296
        L_0x0370:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            long r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r1, r4)
        L_0x037a:
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zze(r3, r4)
            goto L_0x0296
        L_0x0380:
            boolean r14 = r0.zza((T) r1, r12)
            if (r14 == 0) goto L_0x03aa
            long r4 = com.google.android.gms.internal.clearcut.zzfd.zzk(r1, r4)
        L_0x038a:
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzd(r3, r4)
            goto L_0x0296
        L_0x0390:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x0396:
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, r6)
            goto L_0x0296
        L_0x039c:
            boolean r4 = r0.zza((T) r1, r12)
            if (r4 == 0) goto L_0x03aa
        L_0x03a2:
            r4 = 0
            int r3 = com.google.android.gms.internal.clearcut.zzbn.zzb(r3, r4)
            goto L_0x0296
        L_0x03aa:
            int r12 = r12 + 4
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            goto L_0x0016
        L_0x03b0:
            com.google.android.gms.internal.clearcut.zzex<?, ?> r2 = r0.zzmx
            int r1 = zza(r2, (T) r1)
            int r13 = r13 + r1
            return r13
        L_0x03b8:
            sun.misc.Unsafe r2 = zzmh
            r3 = -1
            r3 = 0
            r4 = 0
            r5 = -1
            r12 = 0
        L_0x03bf:
            int[] r13 = r0.zzmi
            int r13 = r13.length
            if (r3 >= r13) goto L_0x07c7
            int r13 = r0.zzag(r3)
            int[] r14 = r0.zzmi
            r14 = r14[r3]
            r15 = 267386880(0xff00000, float:2.3665827E-29)
            r16 = r13 & r15
            int r15 = r16 >>> 20
            r6 = 17
            if (r15 > r6) goto L_0x03eb
            int[] r6 = r0.zzmi
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
            boolean r6 = r0.zzmr
            if (r6 == 0) goto L_0x0409
            com.google.android.gms.internal.clearcut.zzcb r6 = com.google.android.gms.internal.clearcut.zzcb.DOUBLE_LIST_PACKED
            int r6 = r6.id()
            if (r15 < r6) goto L_0x0409
            com.google.android.gms.internal.clearcut.zzcb r6 = com.google.android.gms.internal.clearcut.zzcb.SINT64_LIST_PACKED
            int r6 = r6.id()
            if (r15 > r6) goto L_0x0409
            int[] r6 = r0.zzmi
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
            boolean r9 = r6 instanceof com.google.android.gms.internal.clearcut.zzbb
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
            int r9 = com.google.android.gms.internal.clearcut.zzbn.zzj(r14, r6)
            goto L_0x06f7
        L_0x0491:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            r9 = 0
            int r6 = com.google.android.gms.internal.clearcut.zzbn.zzg(r14, r9)
            goto L_0x06ad
        L_0x049f:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            int r6 = zzg(r1, r9)
            int r6 = com.google.android.gms.internal.clearcut.zzbn.zzg(r14, r6)
            goto L_0x06ad
        L_0x04af:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            long r9 = zzh(r1, r9)
            int r6 = com.google.android.gms.internal.clearcut.zzbn.zze(r14, r9)
            goto L_0x06ad
        L_0x04bf:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            long r9 = zzh(r1, r9)
            int r6 = com.google.android.gms.internal.clearcut.zzbn.zzd(r14, r9)
            goto L_0x06ad
        L_0x04cf:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            r6 = 0
            int r9 = com.google.android.gms.internal.clearcut.zzbn.zzb(r14, r6)
            goto L_0x06f7
        L_0x04dc:
            boolean r6 = r0.zza((T) r1, r14, r3)
            if (r6 == 0) goto L_0x06ae
            r9 = 0
            int r6 = com.google.android.gms.internal.clearcut.zzbn.zzb(r14, r9)
            goto L_0x06ad
        L_0x04ea:
            com.google.android.gms.internal.clearcut.zzdj r6 = r0.zzmz
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.lang.Object r10 = r0.zzae(r3)
            int r6 = r6.zzb(r14, r9, r10)
            goto L_0x06ad
        L_0x04fa:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.clearcut.zzef r9 = r0.zzad(r3)
            int r6 = com.google.android.gms.internal.clearcut.zzeh.zzd(r14, r6, r9)
            goto L_0x06ad
        L_0x050a:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.clearcut.zzeh.zzc(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzmr
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x051c:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.clearcut.zzeh.zzg(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzmr
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x052e:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.clearcut.zzeh.zzi(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzmr
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0540:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.clearcut.zzeh.zzh(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzmr
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0552:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.clearcut.zzeh.zzd(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzmr
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0564:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.clearcut.zzeh.zzf(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzmr
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0576:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.clearcut.zzeh.zzj(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzmr
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0588:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.clearcut.zzeh.zzh(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzmr
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x0599:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.clearcut.zzeh.zzi(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzmr
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x05aa:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.clearcut.zzeh.zze(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzmr
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x05bb:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.clearcut.zzeh.zzb(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzmr
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x05cc:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.clearcut.zzeh.zza(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzmr
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x05dd:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.clearcut.zzeh.zzh(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzmr
            if (r10 == 0) goto L_0x0602
            goto L_0x05fe
        L_0x05ee:
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            int r9 = com.google.android.gms.internal.clearcut.zzeh.zzi(r9)
            if (r9 <= 0) goto L_0x06ae
            boolean r10 = r0.zzmr
            if (r10 == 0) goto L_0x0602
        L_0x05fe:
            long r10 = (long) r6
            r2.putInt(r1, r10, r9)
        L_0x0602:
            int r6 = com.google.android.gms.internal.clearcut.zzbn.zzr(r14)
            int r10 = com.google.android.gms.internal.clearcut.zzbn.zzt(r9)
            int r6 = r6 + r10
            int r6 = r6 + r9
            goto L_0x06ad
        L_0x060e:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            r11 = 0
            int r6 = com.google.android.gms.internal.clearcut.zzeh.zzq(r14, r6, r11)
            goto L_0x06ad
        L_0x061b:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.clearcut.zzeh.zzu(r14, r6, r11)
            goto L_0x06ad
        L_0x0628:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.clearcut.zzeh.zzr(r14, r6, r11)
            goto L_0x06ad
        L_0x0635:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.clearcut.zzeh.zzt(r14, r6, r11)
            goto L_0x06ad
        L_0x0641:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.clearcut.zzeh.zzd(r14, r6)
            goto L_0x06ad
        L_0x064c:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.clearcut.zzef r9 = r0.zzad(r3)
            int r6 = com.google.android.gms.internal.clearcut.zzeh.zzc(r14, r6, r9)
            goto L_0x06ad
        L_0x065b:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.clearcut.zzeh.zzc(r14, r6)
            goto L_0x06ad
        L_0x0666:
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            r11 = 0
            int r6 = com.google.android.gms.internal.clearcut.zzeh.zzx(r14, r6, r11)
            goto L_0x06ad
        L_0x0672:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.clearcut.zzeh.zzs(r14, r6, r11)
            goto L_0x06ad
        L_0x067e:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.clearcut.zzeh.zzp(r14, r6, r11)
            goto L_0x06ad
        L_0x068a:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.clearcut.zzeh.zzo(r14, r6, r11)
            goto L_0x06ad
        L_0x0696:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.clearcut.zzeh.zzv(r14, r6, r11)
            goto L_0x06ad
        L_0x06a2:
            r11 = 0
            java.lang.Object r6 = r2.getObject(r1, r9)
            java.util.List r6 = (java.util.List) r6
            int r6 = com.google.android.gms.internal.clearcut.zzeh.zzw(r14, r6, r11)
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
            com.google.android.gms.internal.clearcut.zzdo r6 = (com.google.android.gms.internal.clearcut.zzdo) r6
            com.google.android.gms.internal.clearcut.zzef r9 = r0.zzad(r3)
            int r6 = com.google.android.gms.internal.clearcut.zzbn.zzc(r14, r6, r9)
            goto L_0x06ad
        L_0x06c9:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
            long r9 = r2.getLong(r1, r9)
        L_0x06d1:
            int r6 = com.google.android.gms.internal.clearcut.zzbn.zzf(r14, r9)
            goto L_0x06ad
        L_0x06d6:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
            int r6 = r2.getInt(r1, r9)
        L_0x06de:
            int r6 = com.google.android.gms.internal.clearcut.zzbn.zzi(r14, r6)
            goto L_0x06ad
        L_0x06e3:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
        L_0x06e7:
            r9 = 0
            int r6 = com.google.android.gms.internal.clearcut.zzbn.zzh(r14, r9)
            goto L_0x06ad
        L_0x06ee:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
        L_0x06f2:
            r6 = 0
            int r9 = com.google.android.gms.internal.clearcut.zzbn.zzk(r14, r6)
        L_0x06f7:
            int r4 = r4 + r9
            goto L_0x06ae
        L_0x06f9:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
            int r6 = r2.getInt(r1, r9)
        L_0x0701:
            int r6 = com.google.android.gms.internal.clearcut.zzbn.zzl(r14, r6)
            goto L_0x06ad
        L_0x0706:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
            int r6 = r2.getInt(r1, r9)
        L_0x070e:
            int r6 = com.google.android.gms.internal.clearcut.zzbn.zzh(r14, r6)
            goto L_0x06ad
        L_0x0713:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
        L_0x0717:
            java.lang.Object r6 = r2.getObject(r1, r9)
        L_0x071b:
            com.google.android.gms.internal.clearcut.zzbb r6 = (com.google.android.gms.internal.clearcut.zzbb) r6
            int r6 = com.google.android.gms.internal.clearcut.zzbn.zzc(r14, r6)
            goto L_0x06ad
        L_0x0722:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
        L_0x0726:
            java.lang.Object r6 = r2.getObject(r1, r9)
            com.google.android.gms.internal.clearcut.zzef r9 = r0.zzad(r3)
            int r6 = com.google.android.gms.internal.clearcut.zzeh.zzc(r14, r6, r9)
            goto L_0x06ad
        L_0x0734:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
            java.lang.Object r6 = r2.getObject(r1, r9)
            boolean r9 = r6 instanceof com.google.android.gms.internal.clearcut.zzbb
            if (r9 == 0) goto L_0x0741
        L_0x0740:
            goto L_0x071b
        L_0x0741:
            java.lang.String r6 = (java.lang.String) r6
            int r6 = com.google.android.gms.internal.clearcut.zzbn.zzb(r14, r6)
            goto L_0x06ad
        L_0x0749:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
        L_0x074d:
            int r6 = com.google.android.gms.internal.clearcut.zzbn.zzc(r14, r7)
            goto L_0x06ad
        L_0x0753:
            r6 = r12 & r16
            if (r6 == 0) goto L_0x06ae
            r6 = 0
            int r9 = com.google.android.gms.internal.clearcut.zzbn.zzj(r14, r6)
            int r4 = r4 + r9
            goto L_0x06af
        L_0x075f:
            r6 = 0
            r9 = r12 & r16
            if (r9 == 0) goto L_0x06af
            r9 = 0
            int r11 = com.google.android.gms.internal.clearcut.zzbn.zzg(r14, r9)
            int r4 = r4 + r11
            r18 = r9
            goto L_0x079e
        L_0x076e:
            r6 = 0
            r18 = 0
            r11 = r12 & r16
            if (r11 == 0) goto L_0x079e
            int r9 = r2.getInt(r1, r9)
            int r9 = com.google.android.gms.internal.clearcut.zzbn.zzg(r14, r9)
            goto L_0x079d
        L_0x077e:
            r6 = 0
            r18 = 0
            r11 = r12 & r16
            if (r11 == 0) goto L_0x079e
            long r9 = r2.getLong(r1, r9)
            int r9 = com.google.android.gms.internal.clearcut.zzbn.zze(r14, r9)
            goto L_0x079d
        L_0x078e:
            r6 = 0
            r18 = 0
            r11 = r12 & r16
            if (r11 == 0) goto L_0x079e
            long r9 = r2.getLong(r1, r9)
            int r9 = com.google.android.gms.internal.clearcut.zzbn.zzd(r14, r9)
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
            int r10 = com.google.android.gms.internal.clearcut.zzbn.zzb(r14, r9)
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
            int r13 = com.google.android.gms.internal.clearcut.zzbn.zzb(r14, r10)
            int r4 = r4 + r13
        L_0x07bf:
            int r3 = r3 + 4
            r9 = r18
            r6 = 0
            r11 = 0
            goto L_0x03bf
        L_0x07c7:
            com.google.android.gms.internal.clearcut.zzex<?, ?> r2 = r0.zzmx
            int r2 = zza(r2, (T) r1)
            int r4 = r4 + r2
            boolean r2 = r0.zzmo
            if (r2 == 0) goto L_0x07dd
            com.google.android.gms.internal.clearcut.zzbu<?> r2 = r0.zzmy
            com.google.android.gms.internal.clearcut.zzby r1 = r2.zza(r1)
            int r1 = r1.zzas()
            int r4 = r4 + r1
        L_0x07dd:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zzm(java.lang.Object):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:92:0x011a, code lost:
        continue;
     */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0108 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x011a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzo(T r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            int[] r2 = r0.zzms
            r3 = 1
            if (r2 == 0) goto L_0x0133
            int[] r2 = r0.zzms
            int r2 = r2.length
            if (r2 != 0) goto L_0x0010
            goto L_0x0133
        L_0x0010:
            r2 = -1
            int[] r4 = r0.zzms
            int r5 = r4.length
            r6 = 0
            r2 = 0
            r7 = -1
            r8 = 0
        L_0x0018:
            if (r2 >= r5) goto L_0x0120
            r9 = r4[r2]
            int r10 = r0.zzai(r9)
            int r11 = r0.zzag(r10)
            boolean r12 = r0.zzmq
            r13 = 1048575(0xfffff, float:1.469367E-39)
            if (r12 != 0) goto L_0x0046
            int[] r12 = r0.zzmi
            int r14 = r10 + 2
            r12 = r12[r14]
            r14 = r12 & r13
            int r12 = r12 >>> 20
            int r12 = r3 << r12
            if (r14 == r7) goto L_0x0044
            sun.misc.Unsafe r7 = zzmh
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
            r12 = 0
        L_0x0048:
            r3 = 268435456(0x10000000, float:2.5243549E-29)
            r3 = r3 & r11
            if (r3 == 0) goto L_0x004f
            r3 = 1
            goto L_0x0050
        L_0x004f:
            r3 = 0
        L_0x0050:
            if (r3 == 0) goto L_0x0059
            boolean r3 = r0.zza((T) r1, r10, r8, r12)
            if (r3 != 0) goto L_0x0059
            return r6
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
            com.google.android.gms.internal.clearcut.zzdj r3 = r0.zzmz
            r4 = r11 & r13
            long r11 = (long) r4
            java.lang.Object r4 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r11)
            java.util.Map r3 = r3.zzh(r4)
            boolean r4 = r3.isEmpty()
            if (r4 != 0) goto L_0x00c7
            java.lang.Object r4 = r0.zzae(r10)
            com.google.android.gms.internal.clearcut.zzdj r9 = r0.zzmz
            com.google.android.gms.internal.clearcut.zzdh r4 = r9.zzl(r4)
            com.google.android.gms.internal.clearcut.zzfl r4 = r4.zzmd
            com.google.android.gms.internal.clearcut.zzfq r4 = r4.zzek()
            com.google.android.gms.internal.clearcut.zzfq r9 = com.google.android.gms.internal.clearcut.zzfq.MESSAGE
            if (r4 != r9) goto L_0x00c7
            r4 = 0
            java.util.Collection r3 = r3.values()
            java.util.Iterator r3 = r3.iterator()
        L_0x00a7:
            boolean r9 = r3.hasNext()
            if (r9 == 0) goto L_0x00c7
            java.lang.Object r9 = r3.next()
            if (r4 != 0) goto L_0x00bf
            com.google.android.gms.internal.clearcut.zzea r4 = com.google.android.gms.internal.clearcut.zzea.zzcm()
            java.lang.Class r10 = r9.getClass()
            com.google.android.gms.internal.clearcut.zzef r4 = r4.zze(r10)
        L_0x00bf:
            boolean r9 = r4.zzo(r9)
            if (r9 != 0) goto L_0x00a7
            r3 = 0
            goto L_0x00c8
        L_0x00c7:
            r3 = 1
        L_0x00c8:
            if (r3 != 0) goto L_0x011a
            return r6
        L_0x00cb:
            boolean r3 = r0.zza((T) r1, r9, r10)
            if (r3 == 0) goto L_0x011a
            com.google.android.gms.internal.clearcut.zzef r3 = r0.zzad(r10)
            boolean r3 = zza(r1, r11, r3)
            if (r3 != 0) goto L_0x011a
            return r6
        L_0x00dc:
            r3 = r11 & r13
            long r3 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.clearcut.zzfd.zzo(r1, r3)
            java.util.List r3 = (java.util.List) r3
            boolean r4 = r3.isEmpty()
            if (r4 != 0) goto L_0x0105
            com.google.android.gms.internal.clearcut.zzef r4 = r0.zzad(r10)
            r9 = 0
        L_0x00f0:
            int r10 = r3.size()
            if (r9 >= r10) goto L_0x0105
            java.lang.Object r10 = r3.get(r9)
            boolean r10 = r4.zzo(r10)
            if (r10 != 0) goto L_0x0102
            r3 = 0
            goto L_0x0106
        L_0x0102:
            int r9 = r9 + 1
            goto L_0x00f0
        L_0x0105:
            r3 = 1
        L_0x0106:
            if (r3 != 0) goto L_0x011a
            return r6
        L_0x0109:
            boolean r3 = r0.zza((T) r1, r10, r8, r12)
            if (r3 == 0) goto L_0x011a
            com.google.android.gms.internal.clearcut.zzef r3 = r0.zzad(r10)
            boolean r3 = zza(r1, r11, r3)
            if (r3 != 0) goto L_0x011a
            return r6
        L_0x011a:
            int r2 = r2 + 1
            r4 = r15
            r3 = 1
            goto L_0x0018
        L_0x0120:
            boolean r2 = r0.zzmo
            if (r2 == 0) goto L_0x0131
            com.google.android.gms.internal.clearcut.zzbu<?> r2 = r0.zzmy
            com.google.android.gms.internal.clearcut.zzby r1 = r2.zza(r1)
            boolean r1 = r1.isInitialized()
            if (r1 != 0) goto L_0x0131
            return r6
        L_0x0131:
            r1 = 1
            return r1
        L_0x0133:
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzds.zzo(java.lang.Object):boolean");
    }
}
