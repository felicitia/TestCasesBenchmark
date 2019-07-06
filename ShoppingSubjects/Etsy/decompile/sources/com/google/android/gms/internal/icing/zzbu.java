package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzbu extends k {
    private static final Logger b = Logger.getLogger(zzbu.class.getName());
    /* access modifiers changed from: private */
    public static final boolean c = cy.a();
    u a = this;

    static class a extends zzbu {
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

        public final void a(int i, bl blVar) throws IOException {
            a(1, 3);
            c(2, i);
            a(3, 2);
            a(blVar);
            a(1, 4);
        }

        /* access modifiers changed from: 0000 */
        public final void a(int i, bl blVar, ca caVar) throws IOException {
            a(i, 2);
            e eVar = (e) blVar;
            int b2 = eVar.b();
            if (b2 == -1) {
                b2 = caVar.b(eVar);
                eVar.a(b2);
            }
            b(b2);
            caVar.a(blVar, (df) this.a);
        }

        public final void a(int i, zzbi zzbi) throws IOException {
            a(i, 2);
            a(zzbi);
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
            if (!zzbu.c || a() < 10) {
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
                    cy.a(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.b;
                int i4 = this.e;
                this.e = i4 + 1;
                cy.a(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        public final void a(bl blVar) throws IOException {
            b(blVar.d());
            blVar.a(this);
        }

        public final void a(zzbi zzbi) throws IOException {
            b(zzbi.size());
            zzbi.zza((k) this);
        }

        public final void a(String str) throws IOException {
            int i = this.e;
            try {
                int g = g(str.length() * 3);
                int g2 = g(str.length());
                if (g2 == g) {
                    this.e = i + g2;
                    int a = da.a(str, this.b, this.e, a());
                    this.e = i;
                    b((a - i) - g2);
                    this.e = a;
                    return;
                }
                b(da.a((CharSequence) str));
                this.e = da.a(str, this.b, this.e, a());
            } catch (zzfi e2) {
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
            if (!zzbu.c || a() < 10) {
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
                    cy.a(bArr3, (long) i4, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                byte[] bArr4 = this.b;
                int i5 = this.e;
                this.e = i5 + 1;
                cy.a(bArr4, (long) i5, (byte) i);
            }
        }

        public final void b(int i, int i2) throws IOException {
            a(i, 0);
            a(i2);
        }

        public final void b(int i, zzbi zzbi) throws IOException {
            a(1, 3);
            c(2, i);
            a(3, zzbi);
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
                com.google.android.gms.internal.icing.zzbu$zzb r0 = new com.google.android.gms.internal.icing.zzbu$zzb
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzbu.a.d(int):void");
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

    private zzbu() {
    }

    public static int a(int i, at atVar) {
        int e = e(i);
        int b2 = atVar.b();
        return e + g(b2) + b2;
    }

    public static int a(at atVar) {
        int b2 = atVar.b();
        return g(b2) + b2;
    }

    static int a(bl blVar, ca caVar) {
        e eVar = (e) blVar;
        int b2 = eVar.b();
        if (b2 == -1) {
            b2 = caVar.b(eVar);
            eVar.a(b2);
        }
        return g(b2) + b2;
    }

    public static zzbu a(byte[] bArr) {
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

    public static int b(int i, at atVar) {
        return (e(1) << 1) + g(2, i) + a(3, atVar);
    }

    public static int b(int i, bl blVar) {
        return (e(1) << 1) + g(2, i) + e(3) + b(blVar);
    }

    static int b(int i, bl blVar, ca caVar) {
        return e(i) + a(blVar, caVar);
    }

    public static int b(int i, String str) {
        return e(i) + b(str);
    }

    public static int b(int i, boolean z) {
        return e(i) + 1;
    }

    public static int b(bl blVar) {
        int d = blVar.d();
        return g(d) + d;
    }

    public static int b(zzbi zzbi) {
        int size = zzbi.size();
        return g(size) + size;
    }

    public static int b(String str) {
        int i;
        try {
            i = da.a((CharSequence) str);
        } catch (zzfi unused) {
            i = str.getBytes(aj.a).length;
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
    static int c(int i, bl blVar, ca caVar) {
        int e = e(i) << 1;
        e eVar = (e) blVar;
        int b2 = eVar.b();
        if (b2 == -1) {
            b2 = caVar.b(eVar);
            eVar.a(b2);
        }
        return e + b2;
    }

    public static int c(int i, zzbi zzbi) {
        int e = e(i);
        int size = zzbi.size();
        return e + g(size) + size;
    }

    @Deprecated
    public static int c(bl blVar) {
        return blVar.d();
    }

    public static int d(int i, long j) {
        return e(i) + e(j);
    }

    public static int d(int i, zzbi zzbi) {
        return (e(1) << 1) + g(2, i) + c(3, zzbi);
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

    public abstract void a(int i, bl blVar) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void a(int i, bl blVar, ca caVar) throws IOException;

    public abstract void a(int i, zzbi zzbi) throws IOException;

    public abstract void a(int i, String str) throws IOException;

    public abstract void a(int i, boolean z) throws IOException;

    public abstract void a(long j) throws IOException;

    public abstract void a(bl blVar) throws IOException;

    public abstract void a(zzbi zzbi) throws IOException;

    public abstract void a(String str) throws IOException;

    /* access modifiers changed from: 0000 */
    public final void a(String str, zzfi zzfi) throws IOException {
        b.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzfi);
        byte[] bytes = str.getBytes(aj.a);
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

    public abstract void b(int i) throws IOException;

    public abstract void b(int i, int i2) throws IOException;

    public final void b(int i, long j) throws IOException {
        a(i, i(j));
    }

    public abstract void b(int i, zzbi zzbi) throws IOException;

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
