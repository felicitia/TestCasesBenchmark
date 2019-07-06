package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzbav extends wf {
    private static final Logger b = Logger.getLogger(zzbav.class.getName());
    /* access modifiers changed from: private */
    public static final boolean c = aab.a();
    wr a = this;

    static class a extends zzbav {
        private final byte[] b;
        private final int c;
        private final int d;
        private int e;

        a(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            }
            int i3 = i2 + 0;
            if ((0 | i2 | (bArr.length - i3)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(0), Integer.valueOf(i2)}));
            }
            this.b = bArr;
            this.c = 0;
            this.e = 0;
            this.d = i3;
        }

        public final int a() {
            return this.d - this.e;
        }

        public final void a(byte b2) throws IOException {
            try {
                byte[] bArr = this.b;
                int i = this.e;
                this.e = i + 1;
                bArr[i] = b2;
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.d), Integer.valueOf(1)}), e2);
            }
        }

        public final void a(int i) throws IOException {
            if (i >= 0) {
                b(i);
            } else {
                a((long) i);
            }
        }

        public final void a(int i, int i2) throws IOException {
            b((i << 3) | i2);
        }

        public final void a(int i, long j) throws IOException {
            a(i, 0);
            a(j);
        }

        public final void a(int i, yk ykVar) throws IOException {
            a(1, 3);
            c(2, i);
            a(3, 2);
            a(ykVar);
            a(1, 4);
        }

        /* access modifiers changed from: 0000 */
        public final void a(int i, yk ykVar, zd zdVar) throws IOException {
            a(i, 2);
            vv vvVar = (vv) ykVar;
            int j = vvVar.j();
            if (j == -1) {
                j = zdVar.b(vvVar);
                vvVar.a(j);
            }
            b(j);
            zdVar.a(ykVar, (aai) this.a);
        }

        public final void a(int i, zzbah zzbah) throws IOException {
            a(i, 2);
            a(zzbah);
        }

        public final void a(int i, String str) throws IOException {
            a(i, 2);
            a(str);
        }

        public final void a(int i, boolean z) throws IOException {
            a(i, 0);
            a(z ? (byte) 1 : 0);
        }

        public final void a(long j) throws IOException {
            if (!zzbav.c || a() < 10) {
                while ((j & -128) != 0) {
                    byte[] bArr = this.b;
                    int i = this.e;
                    this.e = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                }
                try {
                    byte[] bArr2 = this.b;
                    int i2 = this.e;
                    this.e = i2 + 1;
                    bArr2[i2] = (byte) ((int) j);
                } catch (IndexOutOfBoundsException e2) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.d), Integer.valueOf(1)}), e2);
                }
            } else {
                while ((j & -128) != 0) {
                    byte[] bArr3 = this.b;
                    int i3 = this.e;
                    this.e = i3 + 1;
                    aab.a(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.b;
                int i4 = this.e;
                this.e = i4 + 1;
                aab.a(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        public final void a(yk ykVar) throws IOException {
            b(ykVar.l());
            ykVar.a(this);
        }

        public final void a(zzbah zzbah) throws IOException {
            b(zzbah.size());
            zzbah.zza((wf) this);
        }

        public final void a(String str) throws IOException {
            int i = this.e;
            try {
                int g = g(str.length() * 3);
                int g2 = g(str.length());
                if (g2 == g) {
                    this.e = i + g2;
                    int a = aad.a(str, this.b, this.e, a());
                    this.e = i;
                    b((a - i) - g2);
                    this.e = a;
                    return;
                }
                b(aad.a((CharSequence) str));
                this.e = aad.a(str, this.b, this.e, a());
            } catch (zzbep e2) {
                this.e = i;
                a(str, e2);
            } catch (IndexOutOfBoundsException e3) {
                throw new zzb(e3);
            }
        }

        public final void a(byte[] bArr, int i, int i2) throws IOException {
            b(bArr, i, i2);
        }

        public final void b(int i) throws IOException {
            if (!zzbav.c || a() < 10) {
                while ((i & -128) != 0) {
                    byte[] bArr = this.b;
                    int i2 = this.e;
                    this.e = i2 + 1;
                    bArr[i2] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                }
                try {
                    byte[] bArr2 = this.b;
                    int i3 = this.e;
                    this.e = i3 + 1;
                    bArr2[i3] = (byte) i;
                } catch (IndexOutOfBoundsException e2) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.d), Integer.valueOf(1)}), e2);
                }
            } else {
                while ((i & -128) != 0) {
                    byte[] bArr3 = this.b;
                    int i4 = this.e;
                    this.e = i4 + 1;
                    aab.a(bArr3, (long) i4, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                byte[] bArr4 = this.b;
                int i5 = this.e;
                this.e = i5 + 1;
                aab.a(bArr4, (long) i5, (byte) i);
            }
        }

        public final void b(int i, int i2) throws IOException {
            a(i, 0);
            a(i2);
        }

        public final void b(int i, zzbah zzbah) throws IOException {
            a(1, 3);
            c(2, i);
            a(3, zzbah);
            a(1, 4);
        }

        public final void b(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.b, this.e, i2);
                this.e += i2;
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.d), Integer.valueOf(i2)}), e2);
            }
        }

        public final void c(int i, int i2) throws IOException {
            a(i, 0);
            b(i2);
        }

        public final void c(int i, long j) throws IOException {
            a(i, 1);
            c(j);
        }

        public final void c(long j) throws IOException {
            try {
                byte[] bArr = this.b;
                int i = this.e;
                this.e = i + 1;
                bArr[i] = (byte) ((int) j);
                byte[] bArr2 = this.b;
                int i2 = this.e;
                this.e = i2 + 1;
                bArr2[i2] = (byte) ((int) (j >> 8));
                byte[] bArr3 = this.b;
                int i3 = this.e;
                this.e = i3 + 1;
                bArr3[i3] = (byte) ((int) (j >> 16));
                byte[] bArr4 = this.b;
                int i4 = this.e;
                this.e = i4 + 1;
                bArr4[i4] = (byte) ((int) (j >> 24));
                byte[] bArr5 = this.b;
                int i5 = this.e;
                this.e = i5 + 1;
                bArr5[i5] = (byte) ((int) (j >> 32));
                byte[] bArr6 = this.b;
                int i6 = this.e;
                this.e = i6 + 1;
                bArr6[i6] = (byte) ((int) (j >> 40));
                byte[] bArr7 = this.b;
                int i7 = this.e;
                this.e = i7 + 1;
                bArr7[i7] = (byte) ((int) (j >> 48));
                byte[] bArr8 = this.b;
                int i8 = this.e;
                this.e = i8 + 1;
                bArr8[i8] = (byte) ((int) (j >> 56));
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.d), Integer.valueOf(1)}), e2);
            }
        }

        public final void c(byte[] bArr, int i, int i2) throws IOException {
            b(i2);
            b(bArr, 0, i2);
        }

        /* JADX WARNING: type inference failed for: r0v4, types: [byte[]] */
        /* JADX WARNING: type inference failed for: r6v2, types: [int, byte] */
        /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r0v4, types: [byte[]] */
        /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=null, for r6v2, types: [int, byte] */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void d(int r6) throws java.io.IOException {
            /*
                r5 = this;
                byte[] r0 = r5.b     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r1 = r5.e     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r2 = r1 + 1
                r5.e = r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                byte r2 = (byte) r6     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                r0[r1] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                byte[] r0 = r5.b     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r1 = r5.e     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r2 = r1 + 1
                r5.e = r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r2 = r6 >> 8
                byte r2 = (byte) r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                r0[r1] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                byte[] r0 = r5.b     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r1 = r5.e     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r2 = r1 + 1
                r5.e = r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r2 = r6 >> 16
                byte r2 = (byte) r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                r0[r1] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                byte[] r0 = r5.b     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r1 = r5.e     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r2 = r1 + 1
                r5.e = r2     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                int r6 = r6 >> 24
                r0[r1] = r6     // Catch:{ IndexOutOfBoundsException -> 0x0032 }
                return
            L_0x0032:
                r6 = move-exception
                com.google.android.gms.internal.ads.zzbav$zzb r0 = new com.google.android.gms.internal.ads.zzbav$zzb
                java.lang.String r1 = "Pos: %d, limit: %d, len: %d"
                r2 = 3
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                int r4 = r5.e
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                r2[r3] = r4
                int r3 = r5.d
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbav.a.d(int):void");
        }

        public final void e(int i, int i2) throws IOException {
            a(i, 5);
            d(i2);
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

    private zzbav() {
    }

    public static int a(int i, xs xsVar) {
        int e = e(i);
        int b2 = xsVar.b();
        return e + g(b2) + b2;
    }

    public static int a(xs xsVar) {
        int b2 = xsVar.b();
        return g(b2) + b2;
    }

    static int a(yk ykVar, zd zdVar) {
        vv vvVar = (vv) ykVar;
        int j = vvVar.j();
        if (j == -1) {
            j = zdVar.b(vvVar);
            vvVar.a(j);
        }
        return g(j) + j;
    }

    public static zzbav a(byte[] bArr) {
        return new a(bArr, 0, bArr.length);
    }

    public static int b(double d) {
        return 8;
    }

    public static int b(float f) {
        return 4;
    }

    public static int b(int i, double d) {
        return e(i) + 8;
    }

    public static int b(int i, float f) {
        return e(i) + 4;
    }

    public static int b(int i, xs xsVar) {
        return (e(1) << 1) + g(2, i) + a(3, xsVar);
    }

    public static int b(int i, yk ykVar) {
        return (e(1) << 1) + g(2, i) + e(3) + b(ykVar);
    }

    static int b(int i, yk ykVar, zd zdVar) {
        return e(i) + a(ykVar, zdVar);
    }

    public static int b(int i, String str) {
        return e(i) + b(str);
    }

    public static int b(int i, boolean z) {
        return e(i) + 1;
    }

    public static int b(yk ykVar) {
        int l = ykVar.l();
        return g(l) + l;
    }

    public static int b(zzbah zzbah) {
        int size = zzbah.size();
        return g(size) + size;
    }

    public static int b(String str) {
        int i;
        try {
            i = aad.a((CharSequence) str);
        } catch (zzbep unused) {
            i = str.getBytes(xj.a).length;
        }
        return g(i) + i;
    }

    public static int b(boolean z) {
        return 1;
    }

    public static int b(byte[] bArr) {
        int length = bArr.length;
        return g(length) + length;
    }

    @Deprecated
    static int c(int i, yk ykVar, zd zdVar) {
        int e = e(i) << 1;
        vv vvVar = (vv) ykVar;
        int j = vvVar.j();
        if (j == -1) {
            j = zdVar.b(vvVar);
            vvVar.a(j);
        }
        return e + j;
    }

    public static int c(int i, zzbah zzbah) {
        int e = e(i);
        int size = zzbah.size();
        return e + g(size) + size;
    }

    @Deprecated
    public static int c(yk ykVar) {
        return ykVar.l();
    }

    public static int d(int i, long j) {
        return e(i) + e(j);
    }

    public static int d(int i, zzbah zzbah) {
        return (e(1) << 1) + g(2, i) + c(3, zzbah);
    }

    public static int d(long j) {
        return e(j);
    }

    public static int e(int i) {
        return g(i << 3);
    }

    public static int e(int i, long j) {
        return e(i) + e(j);
    }

    public static int e(long j) {
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

    public static int f(int i) {
        if (i >= 0) {
            return g(i);
        }
        return 10;
    }

    public static int f(int i, int i2) {
        return e(i) + f(i2);
    }

    public static int f(int i, long j) {
        return e(i) + e(i(j));
    }

    public static int f(long j) {
        return e(i(j));
    }

    public static int g(int i) {
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

    public static int g(int i, int i2) {
        return e(i) + g(i2);
    }

    public static int g(int i, long j) {
        return e(i) + 8;
    }

    public static int g(long j) {
        return 8;
    }

    public static int h(int i) {
        return g(m(i));
    }

    public static int h(int i, int i2) {
        return e(i) + g(m(i2));
    }

    public static int h(int i, long j) {
        return e(i) + 8;
    }

    public static int h(long j) {
        return 8;
    }

    public static int i(int i) {
        return 4;
    }

    public static int i(int i, int i2) {
        return e(i) + 4;
    }

    private static long i(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int j(int i) {
        return 4;
    }

    public static int j(int i, int i2) {
        return e(i) + 4;
    }

    public static int k(int i) {
        return f(i);
    }

    public static int k(int i, int i2) {
        return e(i) + f(i2);
    }

    @Deprecated
    public static int l(int i) {
        return g(i);
    }

    private static int m(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public abstract int a();

    public abstract void a(byte b2) throws IOException;

    public final void a(double d) throws IOException {
        c(Double.doubleToRawLongBits(d));
    }

    public final void a(float f) throws IOException {
        d(Float.floatToRawIntBits(f));
    }

    public abstract void a(int i) throws IOException;

    public final void a(int i, double d) throws IOException {
        c(i, Double.doubleToRawLongBits(d));
    }

    public final void a(int i, float f) throws IOException {
        e(i, Float.floatToRawIntBits(f));
    }

    public abstract void a(int i, int i2) throws IOException;

    public abstract void a(int i, long j) throws IOException;

    public abstract void a(int i, yk ykVar) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void a(int i, yk ykVar, zd zdVar) throws IOException;

    public abstract void a(int i, zzbah zzbah) throws IOException;

    public abstract void a(int i, String str) throws IOException;

    public abstract void a(int i, boolean z) throws IOException;

    public abstract void a(long j) throws IOException;

    public abstract void a(yk ykVar) throws IOException;

    public abstract void a(zzbah zzbah) throws IOException;

    public abstract void a(String str) throws IOException;

    /* access modifiers changed from: 0000 */
    public final void a(String str, zzbep zzbep) throws IOException {
        b.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzbep);
        byte[] bytes = str.getBytes(xj.a);
        try {
            b(bytes.length);
            a(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzb(e);
        } catch (zzb e2) {
            throw e2;
        }
    }

    public final void a(boolean z) throws IOException {
        a(z ? (byte) 1 : 0);
    }

    public final void b() {
        if (a() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract void b(int i) throws IOException;

    public abstract void b(int i, int i2) throws IOException;

    public final void b(int i, long j) throws IOException {
        a(i, i(j));
    }

    public abstract void b(int i, zzbah zzbah) throws IOException;

    public final void b(long j) throws IOException {
        a(i(j));
    }

    public abstract void b(byte[] bArr, int i, int i2) throws IOException;

    public final void c(int i) throws IOException {
        b(m(i));
    }

    public abstract void c(int i, int i2) throws IOException;

    public abstract void c(int i, long j) throws IOException;

    public abstract void c(long j) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void c(byte[] bArr, int i, int i2) throws IOException;

    public abstract void d(int i) throws IOException;

    public final void d(int i, int i2) throws IOException {
        c(i, m(i2));
    }

    public abstract void e(int i, int i2) throws IOException;
}
