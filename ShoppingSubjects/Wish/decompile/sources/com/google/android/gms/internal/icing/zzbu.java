package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzbu extends zzbh {
    private static final Logger logger = Logger.getLogger(zzbu.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzeg = zzfd.zzcs();
    zzbw zzeh = this;

    static class zza extends zzbu {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            }
            int i3 = i2 + 0;
            if ((i2 | 0 | (bArr.length - i3)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(0), Integer.valueOf(i2)}));
            }
            this.buffer = bArr;
            this.offset = 0;
            this.position = 0;
            this.limit = i3;
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        public final void zza(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zza(int i, long j) throws IOException {
            zzb(i, 0);
            zzb(j);
        }

        public final void zza(int i, zzbi zzbi) throws IOException {
            zzb(i, 2);
            zza(zzbi);
        }

        public final void zza(int i, zzdr zzdr) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zzb(3, 2);
            zzb(zzdr);
            zzb(1, 4);
        }

        /* access modifiers changed from: 0000 */
        public final void zza(int i, zzdr zzdr, zzef zzef) throws IOException {
            zzb(i, 2);
            zzbb zzbb = (zzbb) zzdr;
            int zzm = zzbb.zzm();
            if (zzm == -1) {
                zzm = zzef.zzj(zzbb);
                zzbb.zze(zzm);
            }
            zzm(zzm);
            zzef.zza(zzdr, this.zzeh);
        }

        public final void zza(int i, String str) throws IOException {
            zzb(i, 2);
            zzh(str);
        }

        public final void zza(int i, boolean z) throws IOException {
            zzb(i, 0);
            zza(z ? (byte) 1 : 0);
        }

        public final void zza(zzbi zzbi) throws IOException {
            zzm(zzbi.size());
            zzbi.zza((zzbh) this);
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final int zzab() {
            return this.limit - this.position;
        }

        public final void zzb(int i, int i2) throws IOException {
            zzm((i << 3) | i2);
        }

        public final void zzb(int i, zzbi zzbi) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzbi);
            zzb(1, 4);
        }

        public final void zzb(long j) throws IOException {
            if (!zzbu.zzeg || zzab() < 10) {
                while ((j & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr2[i2] = (byte) ((int) j);
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            } else {
                while ((j & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    zzfd.zza(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzfd.zza(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        public final void zzb(zzdr zzdr) throws IOException {
            zzm(zzdr.zzan());
            zzdr.zzb(this);
        }

        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            zzm(i2);
            write(bArr, 0, i2);
        }

        public final void zzc(int i, int i2) throws IOException {
            zzb(i, 0);
            zzl(i2);
        }

        public final void zzc(int i, long j) throws IOException {
            zzb(i, 1);
            zzd(j);
        }

        public final void zzd(int i, int i2) throws IOException {
            zzb(i, 0);
            zzm(i2);
        }

        public final void zzd(long j) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) j);
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr2[i2] = (byte) ((int) (j >> 8));
                byte[] bArr3 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr3[i3] = (byte) ((int) (j >> 16));
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr4[i4] = (byte) ((int) (j >> 24));
                byte[] bArr5 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr5[i5] = (byte) ((int) (j >> 32));
                byte[] bArr6 = this.buffer;
                int i6 = this.position;
                this.position = i6 + 1;
                bArr6[i6] = (byte) ((int) (j >> 40));
                byte[] bArr7 = this.buffer;
                int i7 = this.position;
                this.position = i7 + 1;
                bArr7[i7] = (byte) ((int) (j >> 48));
                byte[] bArr8 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                bArr8[i8] = (byte) ((int) (j >> 56));
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zzf(int i, int i2) throws IOException {
            zzb(i, 5);
            zzo(i2);
        }

        public final void zzh(String str) throws IOException {
            int i = this.position;
            try {
                int zzr = zzr(str.length() * 3);
                int zzr2 = zzr(str.length());
                if (zzr2 == zzr) {
                    this.position = i + zzr2;
                    int zza = zzff.zza(str, this.buffer, this.position, zzab());
                    this.position = i;
                    zzm((zza - i) - zzr2);
                    this.position = zza;
                    return;
                }
                zzm(zzff.zza(str));
                this.position = zzff.zza(str, this.buffer, this.position, zzab());
            } catch (zzfi e) {
                this.position = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(e2);
            }
        }

        public final void zzl(int i) throws IOException {
            if (i >= 0) {
                zzm(i);
            } else {
                zzb((long) i);
            }
        }

        public final void zzm(int i) throws IOException {
            if (!zzbu.zzeg || zzab() < 10) {
                while ((i & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    bArr2[i3] = (byte) i;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            } else {
                while ((i & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    zzfd.zza(bArr3, (long) i4, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                zzfd.zza(bArr4, (long) i5, (byte) i);
            }
        }

        /* JADX WARNING: type inference failed for: r0v4, types: [byte[]] */
        /* JADX WARNING: type inference failed for: r6v2, types: [byte, int] */
        /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r0v4, types: [byte[]] */
        /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=null, for r6v2, types: [byte, int] */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void zzo(int r6) throws java.io.IOException {
            /*
                r5 = this;
                byte[] r0 = r5.buffer     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r1 = r5.position     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r2 = r1 + 1
                r5.position = r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                byte r2 = (byte) r6     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                r0[r1] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                byte[] r0 = r5.buffer     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r1 = r5.position     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r2 = r1 + 1
                r5.position = r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r2 = r6 >> 8
                byte r2 = (byte) r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                r0[r1] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                byte[] r0 = r5.buffer     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r1 = r5.position     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r2 = r1 + 1
                r5.position = r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r2 = r6 >> 16
                byte r2 = (byte) r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                r0[r1] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                byte[] r0 = r5.buffer     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r1 = r5.position     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r2 = r1 + 1
                r5.position = r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r6 = r6 >> 24
                r0[r1] = r6     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                return
            L_0x0032:
                r6 = move-exception
                com.google.android.gms.internal.icing.zzbu$zzb r0 = new com.google.android.gms.internal.icing.zzbu$zzb
                java.lang.String r1 = "Pos: %d, limit: %d, len: %d"
                r2 = 3
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                int r4 = r5.position
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                r2[r3] = r4
                int r3 = r5.limit
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                r4 = 1
                r2[r4] = r3
                r3 = 2
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                r2[r3] = r4
                java.lang.String r1 = java.lang.String.format(r1, r2)
                r0.<init>(r1, r6)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzbu.zza.zzo(int):void");
        }
    }

    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzb(String str, Throwable th) {
            String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            String valueOf2 = String.valueOf(str);
            super(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), th);
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    private zzbu() {
    }

    public static int zza(int i, zzcz zzcz) {
        int zzp = zzp(i);
        int zzan = zzcz.zzan();
        return zzp + zzr(zzan) + zzan;
    }

    public static int zza(zzcz zzcz) {
        int zzan = zzcz.zzan();
        return zzr(zzan) + zzan;
    }

    static int zza(zzdr zzdr, zzef zzef) {
        zzbb zzbb = (zzbb) zzdr;
        int zzm = zzbb.zzm();
        if (zzm == -1) {
            zzm = zzef.zzj(zzbb);
            zzbb.zze(zzm);
        }
        return zzr(zzm) + zzm;
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzb(float f) {
        return 4;
    }

    public static int zzb(int i, double d) {
        return zzp(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzp(i) + 4;
    }

    public static int zzb(int i, zzcz zzcz) {
        return (zzp(1) << 1) + zzh(2, i) + zza(3, zzcz);
    }

    public static int zzb(int i, zzdr zzdr) {
        return (zzp(1) << 1) + zzh(2, i) + zzp(3) + zzc(zzdr);
    }

    static int zzb(int i, zzdr zzdr, zzef zzef) {
        return zzp(i) + zza(zzdr, zzef);
    }

    public static int zzb(int i, String str) {
        return zzp(i) + zzi(str);
    }

    public static int zzb(int i, boolean z) {
        return zzp(i) + 1;
    }

    public static int zzb(zzbi zzbi) {
        int size = zzbi.size();
        return zzr(size) + size;
    }

    public static zzbu zzb(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzc(int i, zzbi zzbi) {
        int zzp = zzp(i);
        int size = zzbi.size();
        return zzp + zzr(size) + size;
    }

    @Deprecated
    static int zzc(int i, zzdr zzdr, zzef zzef) {
        int zzp = zzp(i) << 1;
        zzbb zzbb = (zzbb) zzdr;
        int zzm = zzbb.zzm();
        if (zzm == -1) {
            zzm = zzef.zzj(zzbb);
            zzbb.zze(zzm);
        }
        return zzp + zzm;
    }

    public static int zzc(zzdr zzdr) {
        int zzan = zzdr.zzan();
        return zzr(zzan) + zzan;
    }

    public static int zzc(byte[] bArr) {
        int length = bArr.length;
        return zzr(length) + length;
    }

    public static int zzd(int i, long j) {
        return zzp(i) + zzf(j);
    }

    public static int zzd(int i, zzbi zzbi) {
        return (zzp(1) << 1) + zzh(2, i) + zzc(3, zzbi);
    }

    @Deprecated
    public static int zzd(zzdr zzdr) {
        return zzdr.zzan();
    }

    public static int zze(int i, long j) {
        return zzp(i) + zzf(j);
    }

    public static int zze(long j) {
        return zzf(j);
    }

    public static int zzf(int i, long j) {
        return zzp(i) + zzf(zzj(j));
    }

    public static int zzf(long j) {
        int i;
        if ((j & -128) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((j & -34359738368L) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if ((j & -2097152) != 0) {
            i += 2;
            j >>>= 14;
        }
        if ((j & -16384) != 0) {
            i++;
        }
        return i;
    }

    public static int zzf(boolean z) {
        return 1;
    }

    public static int zzg(int i, int i2) {
        return zzp(i) + zzq(i2);
    }

    public static int zzg(int i, long j) {
        return zzp(i) + 8;
    }

    public static int zzg(long j) {
        return zzf(zzj(j));
    }

    public static int zzh(int i, int i2) {
        return zzp(i) + zzr(i2);
    }

    public static int zzh(int i, long j) {
        return zzp(i) + 8;
    }

    public static int zzh(long j) {
        return 8;
    }

    public static int zzi(int i, int i2) {
        return zzp(i) + zzr(zzw(i2));
    }

    public static int zzi(long j) {
        return 8;
    }

    public static int zzi(String str) {
        int i;
        try {
            i = zzff.zza(str);
        } catch (zzfi unused) {
            i = str.getBytes(zzcm.UTF_8).length;
        }
        return zzr(i) + i;
    }

    public static int zzj(int i, int i2) {
        return zzp(i) + 4;
    }

    private static long zzj(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zzk(int i, int i2) {
        return zzp(i) + 4;
    }

    public static int zzl(int i, int i2) {
        return zzp(i) + zzq(i2);
    }

    public static int zzp(int i) {
        return zzr(i << 3);
    }

    public static int zzq(int i) {
        if (i >= 0) {
            return zzr(i);
        }
        return 10;
    }

    public static int zzr(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    public static int zzs(int i) {
        return zzr(zzw(i));
    }

    public static int zzt(int i) {
        return 4;
    }

    public static int zzu(int i) {
        return 4;
    }

    public static int zzv(int i) {
        return zzq(i);
    }

    private static int zzw(int i) {
        return (i >> 31) ^ (i << 1);
    }

    @Deprecated
    public static int zzx(int i) {
        return zzr(i);
    }

    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zza(byte b) throws IOException;

    public final void zza(double d) throws IOException {
        zzd(Double.doubleToRawLongBits(d));
    }

    public final void zza(float f) throws IOException {
        zzo(Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zza(int i, float f) throws IOException {
        zzf(i, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzbi zzbi) throws IOException;

    public abstract void zza(int i, zzdr zzdr) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zza(int i, zzdr zzdr, zzef zzef) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(int i, boolean z) throws IOException;

    public abstract void zza(zzbi zzbi) throws IOException;

    /* access modifiers changed from: 0000 */
    public final void zza(String str, zzfi zzfi) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzfi);
        byte[] bytes = str.getBytes(zzcm.UTF_8);
        try {
            zzm(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzb(e);
        } catch (zzb e2) {
            throw e2;
        }
    }

    public abstract int zzab();

    public abstract void zzb(int i, int i2) throws IOException;

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzj(j));
    }

    public abstract void zzb(int i, zzbi zzbi) throws IOException;

    public abstract void zzb(long j) throws IOException;

    public abstract void zzb(zzdr zzdr) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zzb(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzc(int i, int i2) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    public final void zzc(long j) throws IOException {
        zzb(zzj(j));
    }

    public abstract void zzd(int i, int i2) throws IOException;

    public abstract void zzd(long j) throws IOException;

    public final void zze(int i, int i2) throws IOException {
        zzd(i, zzw(i2));
    }

    public final void zze(boolean z) throws IOException {
        zza(z ? (byte) 1 : 0);
    }

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzh(String str) throws IOException;

    public abstract void zzl(int i) throws IOException;

    public abstract void zzm(int i) throws IOException;

    public final void zzn(int i) throws IOException {
        zzm(zzw(i));
    }

    public abstract void zzo(int i) throws IOException;
}
