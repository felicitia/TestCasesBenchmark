package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzbt extends zzbc {
    private static final Logger logger = Logger.getLogger(zzbt.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzik = zzfh.zzfv();
    zzbv zzil = this;

    static class zza extends zzbt {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            }
            int i3 = i + i2;
            if ((i | i2 | (bArr.length - i3)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
            }
            this.buffer = bArr;
            this.offset = i;
            this.position = i;
            this.limit = i3;
        }

        public void flush() {
        }

        public final void zzb(int i, int i2) throws IOException {
            zzw((i << 3) | i2);
        }

        public final void zzc(int i, int i2) throws IOException {
            zzb(i, 0);
            zzv(i2);
        }

        public final void zzd(int i, int i2) throws IOException {
            zzb(i, 0);
            zzw(i2);
        }

        public final void zzf(int i, int i2) throws IOException {
            zzb(i, 5);
            zzy(i2);
        }

        public final void zza(int i, long j) throws IOException {
            zzb(i, 0);
            zzk(j);
        }

        public final void zzc(int i, long j) throws IOException {
            zzb(i, 1);
            zzm(j);
        }

        public final void zzb(int i, boolean z) throws IOException {
            zzb(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zza(int i, String str) throws IOException {
            zzb(i, 2);
            zzm(str);
        }

        public final void zza(int i, zzbd zzbd) throws IOException {
            zzb(i, 2);
            zza(zzbd);
        }

        public final void zza(zzbd zzbd) throws IOException {
            zzw(zzbd.size());
            zzbd.zza((zzbc) this);
        }

        public final void zze(byte[] bArr, int i, int i2) throws IOException {
            zzw(i2);
            write(bArr, 0, i2);
        }

        public final void zza(int i, zzdt zzdt) throws IOException {
            zzb(i, 2);
            zzb(zzdt);
        }

        /* access modifiers changed from: 0000 */
        public final void zza(int i, zzdt zzdt, zzej zzej) throws IOException {
            zzb(i, 2);
            zzaw zzaw = (zzaw) zzdt;
            int zzbf = zzaw.zzbf();
            if (zzbf == -1) {
                zzbf = zzej.zzm(zzaw);
                zzaw.zzf(zzbf);
            }
            zzw(zzbf);
            zzej.zza(zzdt, this.zzil);
        }

        public final void zzb(int i, zzdt zzdt) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzdt);
            zzb(1, 4);
        }

        public final void zzb(int i, zzbd zzbd) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzbd);
            zzb(1, 4);
        }

        public final void zzb(zzdt zzdt) throws IOException {
            zzw(zzdt.zzdg());
            zzdt.zzb(this);
        }

        /* access modifiers changed from: 0000 */
        public final void zza(zzdt zzdt, zzej zzej) throws IOException {
            zzaw zzaw = (zzaw) zzdt;
            int zzbf = zzaw.zzbf();
            if (zzbf == -1) {
                zzbf = zzej.zzm(zzaw);
                zzaw.zzf(zzbf);
            }
            zzw(zzbf);
            zzej.zza(zzdt, this.zzil);
        }

        public final void zzc(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void zzv(int i) throws IOException {
            if (i >= 0) {
                zzw(i);
            } else {
                zzk((long) i);
            }
        }

        public final void zzw(int i) throws IOException {
            if (!zzbt.zzik || zzcs() < 10) {
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
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            } else {
                while ((i & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    zzfh.zza(bArr3, (long) i4, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                zzfh.zza(bArr4, (long) i5, (byte) i);
            }
        }

        /* JADX WARNING: type inference failed for: r0v4, types: [byte[]] */
        /* JADX WARNING: type inference failed for: r6v2, types: [byte, int] */
        /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r0v4, types: [byte[]] */
        /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=null, for r6v2, types: [byte, int] */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void zzy(int r6) throws java.io.IOException {
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
                com.google.android.gms.internal.firebase-perf.zzbt$zzc r0 = new com.google.android.gms.internal.firebase-perf.zzbt$zzc
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzbt.zza.zzy(int):void");
        }

        public final void zzk(long j) throws IOException {
            if (!zzbt.zzik || zzcs() < 10) {
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
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            } else {
                while ((j & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    zzfh.zza(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzfh.zza(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        public final void zzm(long j) throws IOException {
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
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
            }
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzm(String str) throws IOException {
            int i = this.position;
            try {
                int zzab = zzab(str.length() * 3);
                int zzab2 = zzab(str.length());
                if (zzab2 == zzab) {
                    this.position = i + zzab2;
                    int zza = zzfj.zza(str, this.buffer, this.position, zzcs());
                    this.position = i;
                    zzw((zza - i) - zzab2);
                    this.position = zza;
                    return;
                }
                zzw(zzfj.zza(str));
                this.position = zzfj.zza(str, this.buffer, this.position, zzcs());
            } catch (zzfn e) {
                this.position = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzc((Throwable) e2);
            }
        }

        public final int zzcs() {
            return this.limit - this.position;
        }

        public final int zzcu() {
            return this.position - this.offset;
        }
    }

    static final class zzb extends zza {
        private final ByteBuffer zzim;
        private int zzin;

        zzb(ByteBuffer byteBuffer) {
            super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            this.zzim = byteBuffer;
            this.zzin = byteBuffer.position();
        }

        public final void flush() {
            this.zzim.position(this.zzin + zzcu());
        }
    }

    public static class zzc extends IOException {
        zzc() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzc(String str) {
            String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            String valueOf2 = String.valueOf(str);
            super(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        }

        zzc(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        zzc(String str, Throwable th) {
            String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            String valueOf2 = String.valueOf(str);
            super(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), th);
        }
    }

    static final class zzd extends zzbt {
        private final int zzin;
        private final ByteBuffer zzio;
        private final ByteBuffer zzip;

        zzd(ByteBuffer byteBuffer) {
            super();
            this.zzio = byteBuffer;
            this.zzip = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzin = byteBuffer.position();
        }

        public final void zzb(int i, int i2) throws IOException {
            zzw((i << 3) | i2);
        }

        public final void zzc(int i, int i2) throws IOException {
            zzb(i, 0);
            zzv(i2);
        }

        public final void zzd(int i, int i2) throws IOException {
            zzb(i, 0);
            zzw(i2);
        }

        public final void zzf(int i, int i2) throws IOException {
            zzb(i, 5);
            zzy(i2);
        }

        public final void zza(int i, long j) throws IOException {
            zzb(i, 0);
            zzk(j);
        }

        public final void zzc(int i, long j) throws IOException {
            zzb(i, 1);
            zzm(j);
        }

        public final void zzb(int i, boolean z) throws IOException {
            zzb(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zza(int i, String str) throws IOException {
            zzb(i, 2);
            zzm(str);
        }

        public final void zza(int i, zzbd zzbd) throws IOException {
            zzb(i, 2);
            zza(zzbd);
        }

        public final void zza(int i, zzdt zzdt) throws IOException {
            zzb(i, 2);
            zzb(zzdt);
        }

        /* access modifiers changed from: 0000 */
        public final void zza(int i, zzdt zzdt, zzej zzej) throws IOException {
            zzb(i, 2);
            zza(zzdt, zzej);
        }

        public final void zzb(int i, zzdt zzdt) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzdt);
            zzb(1, 4);
        }

        public final void zzb(int i, zzbd zzbd) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzbd);
            zzb(1, 4);
        }

        public final void zzb(zzdt zzdt) throws IOException {
            zzw(zzdt.zzdg());
            zzdt.zzb(this);
        }

        /* access modifiers changed from: 0000 */
        public final void zza(zzdt zzdt, zzej zzej) throws IOException {
            zzaw zzaw = (zzaw) zzdt;
            int zzbf = zzaw.zzbf();
            if (zzbf == -1) {
                zzbf = zzej.zzm(zzaw);
                zzaw.zzf(zzbf);
            }
            zzw(zzbf);
            zzej.zza(zzdt, this.zzil);
        }

        public final void zzc(byte b) throws IOException {
            try {
                this.zzip.put(b);
            } catch (BufferOverflowException e) {
                throw new zzc((Throwable) e);
            }
        }

        public final void zza(zzbd zzbd) throws IOException {
            zzw(zzbd.size());
            zzbd.zza((zzbc) this);
        }

        public final void zze(byte[] bArr, int i, int i2) throws IOException {
            zzw(i2);
            write(bArr, 0, i2);
        }

        public final void zzv(int i) throws IOException {
            if (i >= 0) {
                zzw(i);
            } else {
                zzk((long) i);
            }
        }

        public final void zzw(int i) throws IOException {
            while ((i & -128) != 0) {
                this.zzip.put((byte) ((i & 127) | 128));
                i >>>= 7;
            }
            try {
                this.zzip.put((byte) i);
            } catch (BufferOverflowException e) {
                throw new zzc((Throwable) e);
            }
        }

        public final void zzy(int i) throws IOException {
            try {
                this.zzip.putInt(i);
            } catch (BufferOverflowException e) {
                throw new zzc((Throwable) e);
            }
        }

        public final void zzk(long j) throws IOException {
            while ((j & -128) != 0) {
                this.zzip.put((byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            try {
                this.zzip.put((byte) ((int) j));
            } catch (BufferOverflowException e) {
                throw new zzc((Throwable) e);
            }
        }

        public final void zzm(long j) throws IOException {
            try {
                this.zzip.putLong(j);
            } catch (BufferOverflowException e) {
                throw new zzc((Throwable) e);
            }
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                this.zzip.put(bArr, i, i2);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc((Throwable) e);
            } catch (BufferOverflowException e2) {
                throw new zzc((Throwable) e2);
            }
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzm(String str) throws IOException {
            int position = this.zzip.position();
            try {
                int zzab = zzab(str.length() * 3);
                int zzab2 = zzab(str.length());
                if (zzab2 == zzab) {
                    int position2 = this.zzip.position() + zzab2;
                    this.zzip.position(position2);
                    zzo(str);
                    int position3 = this.zzip.position();
                    this.zzip.position(position);
                    zzw(position3 - position2);
                    this.zzip.position(position3);
                    return;
                }
                zzw(zzfj.zza(str));
                zzo(str);
            } catch (zzfn e) {
                this.zzip.position(position);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc((Throwable) e2);
            }
        }

        public final void flush() {
            this.zzio.position(this.zzip.position());
        }

        public final int zzcs() {
            return this.zzip.remaining();
        }

        private final void zzo(String str) throws IOException {
            try {
                zzfj.zza(str, this.zzip);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc((Throwable) e);
            }
        }
    }

    static final class zze extends zzbt {
        private final ByteBuffer zzio;
        private final ByteBuffer zzip;
        private final long zziq;
        private final long zzir;
        private final long zzis;
        private final long zzit = (this.zzis - 10);
        private long zziu = this.zzir;

        zze(ByteBuffer byteBuffer) {
            super();
            this.zzio = byteBuffer;
            this.zzip = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zziq = zzfh.zzb(byteBuffer);
            this.zzir = this.zziq + ((long) byteBuffer.position());
            this.zzis = this.zziq + ((long) byteBuffer.limit());
        }

        public final void zzb(int i, int i2) throws IOException {
            zzw((i << 3) | i2);
        }

        public final void zzc(int i, int i2) throws IOException {
            zzb(i, 0);
            zzv(i2);
        }

        public final void zzd(int i, int i2) throws IOException {
            zzb(i, 0);
            zzw(i2);
        }

        public final void zzf(int i, int i2) throws IOException {
            zzb(i, 5);
            zzy(i2);
        }

        public final void zza(int i, long j) throws IOException {
            zzb(i, 0);
            zzk(j);
        }

        public final void zzc(int i, long j) throws IOException {
            zzb(i, 1);
            zzm(j);
        }

        public final void zzb(int i, boolean z) throws IOException {
            zzb(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zza(int i, String str) throws IOException {
            zzb(i, 2);
            zzm(str);
        }

        public final void zza(int i, zzbd zzbd) throws IOException {
            zzb(i, 2);
            zza(zzbd);
        }

        public final void zza(int i, zzdt zzdt) throws IOException {
            zzb(i, 2);
            zzb(zzdt);
        }

        /* access modifiers changed from: 0000 */
        public final void zza(int i, zzdt zzdt, zzej zzej) throws IOException {
            zzb(i, 2);
            zza(zzdt, zzej);
        }

        public final void zzb(int i, zzdt zzdt) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzdt);
            zzb(1, 4);
        }

        public final void zzb(int i, zzbd zzbd) throws IOException {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzbd);
            zzb(1, 4);
        }

        public final void zzb(zzdt zzdt) throws IOException {
            zzw(zzdt.zzdg());
            zzdt.zzb(this);
        }

        /* access modifiers changed from: 0000 */
        public final void zza(zzdt zzdt, zzej zzej) throws IOException {
            zzaw zzaw = (zzaw) zzdt;
            int zzbf = zzaw.zzbf();
            if (zzbf == -1) {
                zzbf = zzej.zzm(zzaw);
                zzaw.zzf(zzbf);
            }
            zzw(zzbf);
            zzej.zza(zzdt, this.zzil);
        }

        public final void zzc(byte b) throws IOException {
            if (this.zziu >= this.zzis) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zziu), Long.valueOf(this.zzis), Integer.valueOf(1)}));
            }
            long j = this.zziu;
            this.zziu = j + 1;
            zzfh.zza(j, b);
        }

        public final void zza(zzbd zzbd) throws IOException {
            zzw(zzbd.size());
            zzbd.zza((zzbc) this);
        }

        public final void zze(byte[] bArr, int i, int i2) throws IOException {
            zzw(i2);
            write(bArr, 0, i2);
        }

        public final void zzv(int i) throws IOException {
            if (i >= 0) {
                zzw(i);
            } else {
                zzk((long) i);
            }
        }

        public final void zzw(int i) throws IOException {
            if (this.zziu <= this.zzit) {
                while ((i & -128) != 0) {
                    long j = this.zziu;
                    this.zziu = j + 1;
                    zzfh.zza(j, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                long j2 = this.zziu;
                this.zziu = j2 + 1;
                zzfh.zza(j2, (byte) i);
                return;
            }
            while (this.zziu < this.zzis) {
                if ((i & -128) == 0) {
                    long j3 = this.zziu;
                    this.zziu = j3 + 1;
                    zzfh.zza(j3, (byte) i);
                    return;
                }
                long j4 = this.zziu;
                this.zziu = j4 + 1;
                zzfh.zza(j4, (byte) ((i & 127) | 128));
                i >>>= 7;
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zziu), Long.valueOf(this.zzis), Integer.valueOf(1)}));
        }

        public final void zzy(int i) throws IOException {
            this.zzip.putInt((int) (this.zziu - this.zziq), i);
            this.zziu += 4;
        }

        public final void zzk(long j) throws IOException {
            if (this.zziu <= this.zzit) {
                while ((j & -128) != 0) {
                    long j2 = this.zziu;
                    this.zziu = j2 + 1;
                    zzfh.zza(j2, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                long j3 = this.zziu;
                this.zziu = j3 + 1;
                zzfh.zza(j3, (byte) ((int) j));
                return;
            }
            while (this.zziu < this.zzis) {
                if ((j & -128) == 0) {
                    long j4 = this.zziu;
                    this.zziu = j4 + 1;
                    zzfh.zza(j4, (byte) ((int) j));
                    return;
                }
                long j5 = this.zziu;
                this.zziu = j5 + 1;
                zzfh.zza(j5, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zziu), Long.valueOf(this.zzis), Integer.valueOf(1)}));
        }

        public final void zzm(long j) throws IOException {
            this.zzip.putLong((int) (this.zziu - this.zziq), j);
            this.zziu += 8;
        }

        public final void write(byte[] bArr, int i, int i2) throws IOException {
            if (bArr != null && i >= 0 && i2 >= 0 && bArr.length - i2 >= i) {
                long j = (long) i2;
                if (this.zzis - j >= this.zziu) {
                    zzfh.zza(bArr, (long) i, this.zziu, j);
                    this.zziu += j;
                    return;
                }
            }
            if (bArr == null) {
                throw new NullPointerException("value");
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zziu), Long.valueOf(this.zzis), Integer.valueOf(i2)}));
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        public final void zzm(String str) throws IOException {
            long j = this.zziu;
            try {
                int zzab = zzab(str.length() * 3);
                int zzab2 = zzab(str.length());
                if (zzab2 == zzab) {
                    int i = ((int) (this.zziu - this.zziq)) + zzab2;
                    this.zzip.position(i);
                    zzfj.zza(str, this.zzip);
                    int position = this.zzip.position() - i;
                    zzw(position);
                    this.zziu += (long) position;
                    return;
                }
                int zza = zzfj.zza(str);
                zzw(zza);
                zzt(this.zziu);
                zzfj.zza(str, this.zzip);
                this.zziu += (long) zza;
            } catch (zzfn e) {
                this.zziu = j;
                zzt(this.zziu);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc((Throwable) e2);
            } catch (IndexOutOfBoundsException e3) {
                throw new zzc((Throwable) e3);
            }
        }

        public final void flush() {
            this.zzio.position((int) (this.zziu - this.zziq));
        }

        public final int zzcs() {
            return (int) (this.zzis - this.zziu);
        }

        private final void zzt(long j) {
            this.zzip.position((int) (j - this.zziq));
        }
    }

    public static int zzab(int i) {
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

    public static int zzad(int i) {
        return 4;
    }

    public static int zzae(int i) {
        return 4;
    }

    private static int zzag(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzb(float f) {
        return 4;
    }

    public static zzbt zzc(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zze(boolean z) {
        return 1;
    }

    public static int zzo(long j) {
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

    public static int zzq(long j) {
        return 8;
    }

    public static int zzr(long j) {
        return 8;
    }

    private static long zzs(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public abstract void flush() throws IOException;

    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzbd zzbd) throws IOException;

    public abstract void zza(int i, zzdt zzdt) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zza(int i, zzdt zzdt, zzej zzej) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(zzbd zzbd) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zza(zzdt zzdt, zzej zzej) throws IOException;

    public abstract void zzb(int i, int i2) throws IOException;

    public abstract void zzb(int i, zzbd zzbd) throws IOException;

    public abstract void zzb(int i, zzdt zzdt) throws IOException;

    public abstract void zzb(int i, boolean z) throws IOException;

    public abstract void zzb(zzdt zzdt) throws IOException;

    public abstract void zzc(byte b) throws IOException;

    public abstract void zzc(int i, int i2) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    public abstract int zzcs();

    public abstract void zzd(int i, int i2) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zze(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzk(long j) throws IOException;

    public abstract void zzm(long j) throws IOException;

    public abstract void zzm(String str) throws IOException;

    public abstract void zzv(int i) throws IOException;

    public abstract void zzw(int i) throws IOException;

    public abstract void zzy(int i) throws IOException;

    public static zzbt zza(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return new zzb(byteBuffer);
        }
        if (!byteBuffer.isDirect() || byteBuffer.isReadOnly()) {
            throw new IllegalArgumentException("ByteBuffer is read-only");
        } else if (zzfh.zzfw()) {
            return new zze(byteBuffer);
        } else {
            return new zzd(byteBuffer);
        }
    }

    private zzbt() {
    }

    public final void zze(int i, int i2) throws IOException {
        zzd(i, zzag(i2));
    }

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzs(j));
    }

    public final void zza(int i, float f) throws IOException {
        zzf(i, Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zzx(int i) throws IOException {
        zzw(zzag(i));
    }

    public final void zzl(long j) throws IOException {
        zzk(zzs(j));
    }

    public final void zza(float f) throws IOException {
        zzy(Float.floatToRawIntBits(f));
    }

    public final void zza(double d) throws IOException {
        zzm(Double.doubleToRawLongBits(d));
    }

    public final void zzd(boolean z) throws IOException {
        zzc(z ? (byte) 1 : 0);
    }

    public static int zzg(int i, int i2) {
        return zzz(i) + zzaa(i2);
    }

    public static int zzh(int i, int i2) {
        return zzz(i) + zzab(i2);
    }

    public static int zzi(int i, int i2) {
        return zzz(i) + zzab(zzag(i2));
    }

    public static int zzj(int i, int i2) {
        return zzz(i) + 4;
    }

    public static int zzk(int i, int i2) {
        return zzz(i) + 4;
    }

    public static int zzd(int i, long j) {
        return zzz(i) + zzo(j);
    }

    public static int zze(int i, long j) {
        return zzz(i) + zzo(j);
    }

    public static int zzf(int i, long j) {
        return zzz(i) + zzo(zzs(j));
    }

    public static int zzg(int i, long j) {
        return zzz(i) + 8;
    }

    public static int zzh(int i, long j) {
        return zzz(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzz(i) + 4;
    }

    public static int zzb(int i, double d) {
        return zzz(i) + 8;
    }

    public static int zzc(int i, boolean z) {
        return zzz(i) + 1;
    }

    public static int zzl(int i, int i2) {
        return zzz(i) + zzaa(i2);
    }

    public static int zzb(int i, String str) {
        return zzz(i) + zzn(str);
    }

    public static int zzc(int i, zzbd zzbd) {
        int zzz = zzz(i);
        int size = zzbd.size();
        return zzz + zzab(size) + size;
    }

    public static int zza(int i, zzda zzda) {
        int zzz = zzz(i);
        int zzdg = zzda.zzdg();
        return zzz + zzab(zzdg) + zzdg;
    }

    public static int zzc(int i, zzdt zzdt) {
        return zzz(i) + zzc(zzdt);
    }

    static int zzb(int i, zzdt zzdt, zzej zzej) {
        return zzz(i) + zzb(zzdt, zzej);
    }

    public static int zzd(int i, zzdt zzdt) {
        return (zzz(1) << 1) + zzh(2, i) + zzc(3, zzdt);
    }

    public static int zzd(int i, zzbd zzbd) {
        return (zzz(1) << 1) + zzh(2, i) + zzc(3, zzbd);
    }

    public static int zzb(int i, zzda zzda) {
        return (zzz(1) << 1) + zzh(2, i) + zza(3, zzda);
    }

    public static int zzz(int i) {
        return zzab(i << 3);
    }

    public static int zzaa(int i) {
        if (i >= 0) {
            return zzab(i);
        }
        return 10;
    }

    public static int zzac(int i) {
        return zzab(zzag(i));
    }

    public static int zzn(long j) {
        return zzo(j);
    }

    public static int zzp(long j) {
        return zzo(zzs(j));
    }

    public static int zzaf(int i) {
        return zzaa(i);
    }

    public static int zzn(String str) {
        int i;
        try {
            i = zzfj.zza(str);
        } catch (zzfn unused) {
            i = str.getBytes(zzco.UTF_8).length;
        }
        return zzab(i) + i;
    }

    public static int zza(zzda zzda) {
        int zzdg = zzda.zzdg();
        return zzab(zzdg) + zzdg;
    }

    public static int zzb(zzbd zzbd) {
        int size = zzbd.size();
        return zzab(size) + size;
    }

    public static int zzd(byte[] bArr) {
        int length = bArr.length;
        return zzab(length) + length;
    }

    public static int zzc(zzdt zzdt) {
        int zzdg = zzdt.zzdg();
        return zzab(zzdg) + zzdg;
    }

    static int zzb(zzdt zzdt, zzej zzej) {
        zzaw zzaw = (zzaw) zzdt;
        int zzbf = zzaw.zzbf();
        if (zzbf == -1) {
            zzbf = zzej.zzm(zzaw);
            zzaw.zzf(zzbf);
        }
        return zzab(zzbf) + zzbf;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(String str, zzfn zzfn) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzfn);
        byte[] bytes = str.getBytes(zzco.UTF_8);
        try {
            zzw(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzc((Throwable) e);
        } catch (zzc e2) {
            throw e2;
        }
    }

    @Deprecated
    static int zzc(int i, zzdt zzdt, zzej zzej) {
        int zzz = zzz(i) << 1;
        zzaw zzaw = (zzaw) zzdt;
        int zzbf = zzaw.zzbf();
        if (zzbf == -1) {
            zzbf = zzej.zzm(zzaw);
            zzaw.zzf(zzbf);
        }
        return zzz + zzbf;
    }

    @Deprecated
    public static int zzd(zzdt zzdt) {
        return zzdt.zzdg();
    }

    @Deprecated
    public static int zzah(int i) {
        return zzab(i);
    }
}
