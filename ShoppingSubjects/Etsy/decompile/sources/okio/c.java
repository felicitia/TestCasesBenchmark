package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import com.etsy.android.lib.models.editable.EditableListing;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import org.apache.commons.math3.dfp.Dfp;

/* compiled from: Buffer */
public final class c implements Cloneable, ByteChannel, d, e {
    private static final byte[] c = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    q a;
    long b;

    public c c() {
        return this;
    }

    public void close() {
    }

    /* renamed from: e */
    public c w() {
        return this;
    }

    public void flush() {
    }

    public boolean isOpen() {
        return true;
    }

    public long b() {
        return this.b;
    }

    public OutputStream d() {
        return new OutputStream() {
            public void close() {
            }

            public void flush() {
            }

            public void write(int i) {
                c.this.k((int) (byte) i);
            }

            public void write(byte[] bArr, int i, int i2) {
                c.this.c(bArr, i, i2);
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(c.this);
                sb.append(".outputStream()");
                return sb.toString();
            }
        };
    }

    public boolean f() {
        return this.b == 0;
    }

    public void a(long j) throws EOFException {
        if (this.b < j) {
            throw new EOFException();
        }
    }

    public boolean b(long j) {
        return this.b >= j;
    }

    public InputStream g() {
        return new InputStream() {
            public void close() {
            }

            public int read() {
                if (c.this.b > 0) {
                    return c.this.i() & 255;
                }
                return -1;
            }

            public int read(byte[] bArr, int i, int i2) {
                return c.this.a(bArr, i, i2);
            }

            public int available() {
                return (int) Math.min(c.this.b, 2147483647L);
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(c.this);
                sb.append(".inputStream()");
                return sb.toString();
            }
        };
    }

    public c a(c cVar, long j, long j2) {
        if (cVar == null) {
            throw new IllegalArgumentException("out == null");
        }
        v.a(this.b, j, j2);
        if (j2 == 0) {
            return this;
        }
        cVar.b += j2;
        q qVar = this.a;
        while (j >= ((long) (qVar.c - qVar.b))) {
            long j3 = j - ((long) (qVar.c - qVar.b));
            qVar = qVar.f;
            j = j3;
        }
        while (j2 > 0) {
            q a2 = qVar.a();
            a2.b = (int) (((long) a2.b) + j);
            a2.c = Math.min(a2.b + ((int) j2), a2.c);
            if (cVar.a == null) {
                a2.g = a2;
                a2.f = a2;
                cVar.a = a2;
            } else {
                cVar.a.g.a(a2);
            }
            long j4 = j2 - ((long) (a2.c - a2.b));
            qVar = qVar.f;
            j = 0;
            j2 = j4;
        }
        return this;
    }

    public long h() {
        long j = this.b;
        if (j == 0) {
            return 0;
        }
        q qVar = this.a.g;
        if (qVar.c < 8192 && qVar.e) {
            j -= (long) (qVar.c - qVar.b);
        }
        return j;
    }

    public byte i() {
        if (this.b == 0) {
            throw new IllegalStateException("size == 0");
        }
        q qVar = this.a;
        int i = qVar.b;
        int i2 = qVar.c;
        int i3 = i + 1;
        byte b2 = qVar.a[i];
        this.b--;
        if (i3 == i2) {
            this.a = qVar.b();
            r.a(qVar);
        } else {
            qVar.b = i3;
        }
        return b2;
    }

    public byte c(long j) {
        v.a(this.b, j, 1);
        if (this.b - j > j) {
            q qVar = this.a;
            while (true) {
                long j2 = (long) (qVar.c - qVar.b);
                if (j < j2) {
                    return qVar.a[qVar.b + ((int) j)];
                }
                long j3 = j - j2;
                qVar = qVar.f;
                j = j3;
            }
        } else {
            long j4 = j - this.b;
            q qVar2 = this.a.g;
            while (true) {
                long j5 = j4 + ((long) (qVar2.c - qVar2.b));
                if (j5 >= 0) {
                    return qVar2.a[qVar2.b + ((int) j5)];
                }
                qVar2 = qVar2.g;
                j4 = j5;
            }
        }
    }

    public short j() {
        if (this.b < 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("size < 2: ");
            sb.append(this.b);
            throw new IllegalStateException(sb.toString());
        }
        q qVar = this.a;
        int i = qVar.b;
        int i2 = qVar.c;
        if (i2 - i < 2) {
            return (short) (((i() & 255) << 8) | (i() & 255));
        }
        byte[] bArr = qVar.a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        byte b2 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        this.b -= 2;
        if (i4 == i2) {
            this.a = qVar.b();
            r.a(qVar);
        } else {
            qVar.b = i4;
        }
        return (short) b2;
    }

    public int k() {
        if (this.b < 4) {
            StringBuilder sb = new StringBuilder();
            sb.append("size < 4: ");
            sb.append(this.b);
            throw new IllegalStateException(sb.toString());
        }
        q qVar = this.a;
        int i = qVar.b;
        int i2 = qVar.c;
        if (i2 - i < 4) {
            return ((i() & 255) << 24) | ((i() & 255) << 16) | ((i() & 255) << 8) | (i() & 255);
        }
        byte[] bArr = qVar.a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        byte b2 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        int i5 = i4 + 1;
        byte b3 = b2 | ((bArr[i4] & 255) << 8);
        int i6 = i5 + 1;
        byte b4 = b3 | (bArr[i5] & 255);
        this.b -= 4;
        if (i6 == i2) {
            this.a = qVar.b();
            r.a(qVar);
        } else {
            qVar.b = i6;
        }
        return b4;
    }

    public short l() {
        return v.a(j());
    }

    public int m() {
        return v.a(k());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009e, code lost:
        if (r9 != r10) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a0, code lost:
        r0.a = r7.b();
        okio.r.a(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00aa, code lost:
        r7.b = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ac, code lost:
        if (r2 != false) goto L_0x00b2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0082 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long n() {
        /*
            r17 = this;
            r0 = r17
            long r1 = r0.b
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x0012
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "size == 0"
            r1.<init>(r2)
            throw r1
        L_0x0012:
            r1 = 0
            r2 = r1
            r5 = r3
        L_0x0015:
            okio.q r7 = r0.a
            byte[] r8 = r7.a
            int r9 = r7.b
            int r10 = r7.c
        L_0x001d:
            if (r9 >= r10) goto L_0x009e
            byte r11 = r8[r9]
            r12 = 48
            if (r11 < r12) goto L_0x002c
            r12 = 57
            if (r11 > r12) goto L_0x002c
            int r12 = r11 + -48
            goto L_0x0045
        L_0x002c:
            r12 = 97
            if (r11 < r12) goto L_0x0039
            r12 = 102(0x66, float:1.43E-43)
            if (r11 > r12) goto L_0x0039
            int r12 = r11 + -97
            int r12 = r12 + 10
            goto L_0x0045
        L_0x0039:
            r12 = 65
            if (r11 < r12) goto L_0x0080
            r12 = 70
            if (r11 > r12) goto L_0x0080
            int r12 = r11 + -65
            int r12 = r12 + 10
        L_0x0045:
            r13 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r15 = r5 & r13
            int r13 = (r15 > r3 ? 1 : (r15 == r3 ? 0 : -1))
            if (r13 == 0) goto L_0x0075
            okio.c r1 = new okio.c
            r1.<init>()
            okio.c r1 = r1.l(r5)
            okio.c r1 = r1.k(r11)
            java.lang.NumberFormatException r2 = new java.lang.NumberFormatException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Number too large: "
            r3.append(r4)
            java.lang.String r1 = r1.p()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L_0x0075:
            r11 = 4
            long r5 = r5 << r11
            long r11 = (long) r12
            long r13 = r5 | r11
            int r9 = r9 + 1
            int r1 = r1 + 1
            r5 = r13
            goto L_0x001d
        L_0x0080:
            if (r1 != 0) goto L_0x009d
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Expected leading [0-9a-fA-F] character but was 0x"
            r2.append(r3)
            java.lang.String r3 = java.lang.Integer.toHexString(r11)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x009d:
            r2 = 1
        L_0x009e:
            if (r9 != r10) goto L_0x00aa
            okio.q r8 = r7.b()
            r0.a = r8
            okio.r.a(r7)
            goto L_0x00ac
        L_0x00aa:
            r7.b = r9
        L_0x00ac:
            if (r2 != 0) goto L_0x00b2
            okio.q r7 = r0.a
            if (r7 != 0) goto L_0x0015
        L_0x00b2:
            long r2 = r0.b
            long r7 = (long) r1
            long r9 = r2 - r7
            r0.b = r9
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.c.n():long");
    }

    public ByteString o() {
        return new ByteString(s());
    }

    public ByteString d(long j) throws EOFException {
        return new ByteString(h(j));
    }

    public int a(n nVar) {
        q qVar = this.a;
        if (qVar == null) {
            return nVar.indexOf(ByteString.EMPTY);
        }
        ByteString[] byteStringArr = nVar.a;
        int length = byteStringArr.length;
        for (int i = 0; i < length; i++) {
            ByteString byteString = byteStringArr[i];
            if (this.b >= ((long) byteString.size())) {
                if (a(qVar, qVar.b, byteString, 0, byteString.size())) {
                    try {
                        i((long) byteString.size());
                        return i;
                    } catch (EOFException e) {
                        throw new AssertionError(e);
                    }
                }
            }
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    public int b(n nVar) {
        q qVar = this.a;
        ByteString[] byteStringArr = nVar.a;
        int length = byteStringArr.length;
        int i = 0;
        while (i < length) {
            ByteString byteString = byteStringArr[i];
            int min = (int) Math.min(this.b, (long) byteString.size());
            if (min != 0) {
                if (!a(qVar, qVar.b, byteString, 0, min)) {
                    i++;
                }
            }
            return i;
        }
        return -1;
    }

    public long a(s sVar) throws IOException {
        long j = this.b;
        if (j > 0) {
            sVar.a_(this, j);
        }
        return j;
    }

    public String p() {
        try {
            return a(this.b, v.a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String e(long j) throws EOFException {
        return a(j, v.a);
    }

    public String a(Charset charset) {
        try {
            return a(this.b, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String a(long j, Charset charset) throws EOFException {
        v.a(this.b, 0, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > 2147483647L) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount > Integer.MAX_VALUE: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        } else if (j == 0) {
            return "";
        } else {
            q qVar = this.a;
            if (((long) qVar.b) + j > ((long) qVar.c)) {
                return new String(h(j), charset);
            }
            String str = new String(qVar.a, qVar.b, (int) j, charset);
            qVar.b = (int) (((long) qVar.b) + j);
            this.b -= j;
            if (qVar.b == qVar.c) {
                this.a = qVar.b();
                r.a(qVar);
            }
            return str;
        }
    }

    public String q() throws EOFException {
        return f(Long.MAX_VALUE);
    }

    public String f(long j) throws EOFException {
        if (j < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("limit < 0: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        }
        long j2 = Long.MAX_VALUE;
        if (j != Long.MAX_VALUE) {
            j2 = j + 1;
        }
        long a2 = a(10, 0, j2);
        if (a2 != -1) {
            return g(a2);
        }
        if (j2 < b() && c(j2 - 1) == 13 && c(j2) == 10) {
            return g(j2);
        }
        c cVar = new c();
        a(cVar, 0, Math.min(32, b()));
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\\n not found: limit=");
        sb2.append(Math.min(b(), j));
        sb2.append(" content=");
        sb2.append(cVar.o().hex());
        sb2.append(8230);
        throw new EOFException(sb2.toString());
    }

    /* access modifiers changed from: 0000 */
    public String g(long j) throws EOFException {
        if (j > 0) {
            long j2 = j - 1;
            if (c(j2) == 13) {
                String e = e(j2);
                i(2);
                return e;
            }
        }
        String e2 = e(j);
        i(1);
        return e2;
    }

    public int r() throws EOFException {
        byte b2;
        int i;
        byte b3;
        if (this.b == 0) {
            throw new EOFException();
        }
        byte c2 = c(0);
        int i2 = 1;
        if ((c2 & 128) == 0) {
            b3 = c2 & Byte.MAX_VALUE;
            b2 = 0;
            i = 1;
        } else if ((c2 & 224) == 192) {
            b3 = c2 & 31;
            i = 2;
            b2 = 128;
        } else if ((c2 & 240) == 224) {
            b3 = c2 & 15;
            i = 3;
            b2 = Dfp.FINITE;
        } else if ((c2 & 248) == 240) {
            b3 = c2 & 7;
            i = 4;
            b2 = Dfp.FINITE;
        } else {
            i(1);
            return 65533;
        }
        long j = (long) i;
        if (this.b < j) {
            StringBuilder sb = new StringBuilder();
            sb.append("size < ");
            sb.append(i);
            sb.append(": ");
            sb.append(this.b);
            sb.append(" (to read code point prefixed 0x");
            sb.append(Integer.toHexString(c2));
            sb.append(")");
            throw new EOFException(sb.toString());
        }
        while (i2 < i) {
            long j2 = (long) i2;
            byte c3 = c(j2);
            if ((c3 & 192) == 128) {
                b3 = (b3 << 6) | (c3 & 63);
                i2++;
            } else {
                i(j2);
                return 65533;
            }
        }
        i(j);
        if (b3 > 1114111) {
            return 65533;
        }
        if ((b3 < 55296 || b3 > 57343) && b3 >= b2) {
            return b3;
        }
        return 65533;
    }

    public byte[] s() {
        try {
            return h(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public byte[] h(long j) throws EOFException {
        v.a(this.b, 0, j);
        if (j > 2147483647L) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount > Integer.MAX_VALUE: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        }
        byte[] bArr = new byte[((int) j)];
        a(bArr);
        return bArr;
    }

    public void a(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int a2 = a(bArr, i, bArr.length - i);
            if (a2 == -1) {
                throw new EOFException();
            }
            i += a2;
        }
    }

    public int a(byte[] bArr, int i, int i2) {
        v.a((long) bArr.length, (long) i, (long) i2);
        q qVar = this.a;
        if (qVar == null) {
            return -1;
        }
        int min = Math.min(i2, qVar.c - qVar.b);
        System.arraycopy(qVar.a, qVar.b, bArr, i, min);
        qVar.b += min;
        this.b -= (long) min;
        if (qVar.b == qVar.c) {
            this.a = qVar.b();
            r.a(qVar);
        }
        return min;
    }

    public int read(ByteBuffer byteBuffer) throws IOException {
        q qVar = this.a;
        if (qVar == null) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), qVar.c - qVar.b);
        byteBuffer.put(qVar.a, qVar.b, min);
        qVar.b += min;
        this.b -= (long) min;
        if (qVar.b == qVar.c) {
            this.a = qVar.b();
            r.a(qVar);
        }
        return min;
    }

    public void t() {
        try {
            i(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public void i(long j) throws EOFException {
        while (j > 0) {
            if (this.a == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j, (long) (this.a.c - this.a.b));
            long j2 = (long) min;
            this.b -= j2;
            long j3 = j - j2;
            this.a.b += min;
            if (this.a.b == this.a.c) {
                q qVar = this.a;
                this.a = qVar.b();
                r.a(qVar);
            }
            j = j3;
        }
    }

    /* renamed from: a */
    public c c(ByteString byteString) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        byteString.write(this);
        return this;
    }

    /* renamed from: a */
    public c b(String str) {
        return b(str, 0, str.length());
    }

    /* renamed from: a */
    public c b(String str, int i, int i2) {
        char c2;
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("beginIndex < 0: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 < i) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("endIndex < beginIndex: ");
            sb2.append(i2);
            sb2.append(" < ");
            sb2.append(i);
            throw new IllegalArgumentException(sb2.toString());
        } else if (i2 > str.length()) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("endIndex > string.length: ");
            sb3.append(i2);
            sb3.append(EditableListing.CATEGORY_PATH_JOIN_STRING);
            sb3.append(str.length());
            throw new IllegalArgumentException(sb3.toString());
        } else {
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt < 128) {
                    q f = f(1);
                    byte[] bArr = f.a;
                    int i3 = f.c - i;
                    int min = Math.min(i2, 8192 - i3);
                    int i4 = i + 1;
                    bArr[i + i3] = (byte) charAt;
                    while (i4 < min) {
                        char charAt2 = str.charAt(i4);
                        if (charAt2 >= 128) {
                            break;
                        }
                        int i5 = i4 + 1;
                        bArr[i4 + i3] = (byte) charAt2;
                        i4 = i5;
                    }
                    int i6 = (i3 + i4) - f.c;
                    f.c += i6;
                    this.b += (long) i6;
                    i = i4;
                } else if (charAt < 2048) {
                    k((charAt >> 6) | 192);
                    k((int) (charAt & '?') | 128);
                    i++;
                } else if (charAt < 55296 || charAt > 57343) {
                    k((charAt >> 12) | 224);
                    k(((charAt >> 6) & 63) | 128);
                    k((int) (charAt & '?') | 128);
                    i++;
                } else {
                    int i7 = i + 1;
                    if (i7 < i2) {
                        c2 = str.charAt(i7);
                    } else {
                        c2 = 0;
                    }
                    if (charAt > 56319 || c2 < 56320 || c2 > 57343) {
                        k(63);
                        i = i7;
                    } else {
                        int i8 = 0 + (((charAt & 10239) << 10) | (9215 & c2));
                        k((i8 >> 18) | 240);
                        k(((i8 >> 12) & 63) | 128);
                        k(((i8 >> 6) & 63) | 128);
                        k((i8 & 63) | 128);
                        i += 2;
                    }
                }
            }
            return this;
        }
    }

    public c a(int i) {
        if (i < 128) {
            k(i);
        } else if (i < 2048) {
            k((i >> 6) | 192);
            k((i & 63) | 128);
        } else if (i < 65536) {
            if (i < 55296 || i > 57343) {
                k((i >> 12) | 224);
                k(((i >> 6) & 63) | 128);
                k((i & 63) | 128);
            } else {
                k(63);
            }
        } else if (i <= 1114111) {
            k((i >> 18) | 240);
            k(((i >> 12) & 63) | 128);
            k(((i >> 6) & 63) | 128);
            k((i & 63) | 128);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected code point: ");
            sb.append(Integer.toHexString(i));
            throw new IllegalArgumentException(sb.toString());
        }
        return this;
    }

    public c a(String str, int i, int i2, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("beginIndex < 0: ");
            sb.append(i);
            throw new IllegalAccessError(sb.toString());
        } else if (i2 < i) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("endIndex < beginIndex: ");
            sb2.append(i2);
            sb2.append(" < ");
            sb2.append(i);
            throw new IllegalArgumentException(sb2.toString());
        } else if (i2 > str.length()) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("endIndex > string.length: ");
            sb3.append(i2);
            sb3.append(EditableListing.CATEGORY_PATH_JOIN_STRING);
            sb3.append(str.length());
            throw new IllegalArgumentException(sb3.toString());
        } else if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (charset.equals(v.a)) {
            return b(str, i, i2);
        } else {
            byte[] bytes = str.substring(i, i2).getBytes(charset);
            return c(bytes, 0, bytes.length);
        }
    }

    /* renamed from: b */
    public c c(byte[] bArr) {
        if (bArr != null) {
            return c(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    /* renamed from: b */
    public c c(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = (long) i2;
        v.a((long) bArr.length, (long) i, j);
        int i3 = i2 + i;
        while (i < i3) {
            q f = f(1);
            int min = Math.min(i3 - i, 8192 - f.c);
            System.arraycopy(bArr, i, f.a, f.c, min);
            i += min;
            f.c += min;
        }
        this.b += j;
        return this;
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        int remaining = byteBuffer.remaining();
        int i = remaining;
        while (i > 0) {
            q f = f(1);
            int min = Math.min(i, 8192 - f.c);
            byteBuffer.get(f.a, f.c, min);
            i -= min;
            f.c += min;
        }
        this.b += (long) remaining;
        return remaining;
    }

    public long a(t tVar) throws IOException {
        if (tVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long a2 = tVar.a(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (a2 == -1) {
                return j;
            }
            j += a2;
        }
    }

    /* renamed from: b */
    public c k(int i) {
        q f = f(1);
        byte[] bArr = f.a;
        int i2 = f.c;
        f.c = i2 + 1;
        bArr[i2] = (byte) i;
        this.b++;
        return this;
    }

    /* renamed from: c */
    public c j(int i) {
        q f = f(2);
        byte[] bArr = f.a;
        int i2 = f.c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i & 255);
        f.c = i4;
        this.b += 2;
        return this;
    }

    /* renamed from: d */
    public c i(int i) {
        q f = f(4);
        byte[] bArr = f.a;
        int i2 = f.c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i & 255);
        f.c = i6;
        this.b += 4;
        return this;
    }

    /* renamed from: e */
    public c h(int i) {
        return i(v.a(i));
    }

    /* renamed from: j */
    public c m(long j) {
        if (j == 0) {
            return k(48);
        }
        boolean z = false;
        int i = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                return b("-9223372036854775808");
            }
            z = true;
        }
        if (j >= 100000000) {
            i = j < 1000000000000L ? j < 10000000000L ? j < 1000000000 ? 9 : 10 : j < 100000000000L ? 11 : 12 : j < 1000000000000000L ? j < 10000000000000L ? 13 : j < 100000000000000L ? 14 : 15 : j < 100000000000000000L ? j < 10000000000000000L ? 16 : 17 : j < 1000000000000000000L ? 18 : 19;
        } else if (j >= 10000) {
            i = j < 1000000 ? j < 100000 ? 5 : 6 : j < 10000000 ? 7 : 8;
        } else if (j >= 100) {
            i = j < 1000 ? 3 : 4;
        } else if (j >= 10) {
            i = 2;
        }
        if (z) {
            i++;
        }
        q f = f(i);
        byte[] bArr = f.a;
        int i2 = f.c + i;
        while (j != 0) {
            i2--;
            bArr[i2] = c[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i2 - 1] = 45;
        }
        f.c += i;
        this.b += (long) i;
        return this;
    }

    /* renamed from: k */
    public c l(long j) {
        if (j == 0) {
            return k(48);
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        q f = f(numberOfTrailingZeros);
        byte[] bArr = f.a;
        int i = f.c;
        for (int i2 = (f.c + numberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = c[(int) (j & 15)];
            j >>>= 4;
        }
        f.c += numberOfTrailingZeros;
        this.b += (long) numberOfTrailingZeros;
        return this;
    }

    /* access modifiers changed from: 0000 */
    public q f(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        } else if (this.a == null) {
            this.a = r.a();
            q qVar = this.a;
            q qVar2 = this.a;
            q qVar3 = this.a;
            qVar2.g = qVar3;
            qVar.f = qVar3;
            return qVar3;
        } else {
            q qVar4 = this.a.g;
            if (qVar4.c + i > 8192 || !qVar4.e) {
                qVar4 = qVar4.a(r.a());
            }
            return qVar4;
        }
    }

    public void a_(c cVar, long j) {
        int i;
        if (cVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (cVar == this) {
            throw new IllegalArgumentException("source == this");
        } else {
            v.a(cVar.b, 0, j);
            while (j > 0) {
                if (j < ((long) (cVar.a.c - cVar.a.b))) {
                    q qVar = this.a != null ? this.a.g : null;
                    if (qVar != null && qVar.e) {
                        long j2 = j + ((long) qVar.c);
                        if (qVar.d) {
                            i = 0;
                        } else {
                            i = qVar.b;
                        }
                        if (j2 - ((long) i) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                            cVar.a.a(qVar, (int) j);
                            cVar.b -= j;
                            this.b += j;
                            return;
                        }
                    }
                    cVar.a = cVar.a.a((int) j);
                }
                q qVar2 = cVar.a;
                long j3 = (long) (qVar2.c - qVar2.b);
                cVar.a = qVar2.b();
                if (this.a == null) {
                    this.a = qVar2;
                    q qVar3 = this.a;
                    q qVar4 = this.a;
                    q qVar5 = this.a;
                    qVar4.g = qVar5;
                    qVar3.f = qVar5;
                } else {
                    this.a.g.a(qVar2).c();
                }
                cVar.b -= j3;
                this.b += j3;
                j -= j3;
            }
        }
    }

    public long a(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount < 0: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        } else if (this.b == 0) {
            return -1;
        } else {
            if (j > this.b) {
                j = this.b;
            }
            cVar.a_(this, j);
            return j;
        }
    }

    public long a(byte b2) {
        return a(b2, 0, Long.MAX_VALUE);
    }

    public long a(byte b2, long j, long j2) {
        long j3;
        long j4 = 0;
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", new Object[]{Long.valueOf(this.b), Long.valueOf(j), Long.valueOf(j2)}));
        }
        long j5 = j2 > this.b ? this.b : j2;
        if (j == j5) {
            return -1;
        }
        q qVar = this.a;
        if (qVar == null) {
            return -1;
        }
        if (this.b - j >= j) {
            while (true) {
                long j6 = j3 + ((long) (qVar.c - qVar.b));
                if (j6 >= j) {
                    break;
                }
                qVar = qVar.f;
                j4 = j6;
            }
        } else {
            j3 = this.b;
            while (j3 > j) {
                qVar = qVar.g;
                j3 -= (long) (qVar.c - qVar.b);
            }
        }
        long j7 = j;
        while (j3 < j5) {
            byte[] bArr = qVar.a;
            int min = (int) Math.min((long) qVar.c, (((long) qVar.b) + j5) - j3);
            for (int i = (int) ((((long) qVar.b) + j7) - j3); i < min; i++) {
                if (bArr[i] == b2) {
                    return ((long) (i - qVar.b)) + j3;
                }
            }
            byte b3 = b2;
            long j8 = j3 + ((long) (qVar.c - qVar.b));
            qVar = qVar.f;
            j7 = j8;
            j3 = j7;
        }
        return -1;
    }

    public long b(ByteString byteString) {
        return a(byteString, 0);
    }

    public long a(ByteString byteString, long j) {
        long j2;
        long j3 = 0;
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        q qVar = this.a;
        if (qVar == null) {
            return -1;
        }
        if (this.b - j >= j) {
            while (true) {
                long j4 = j2 + ((long) (qVar.c - qVar.b));
                if (j4 >= j) {
                    break;
                }
                qVar = qVar.f;
                j3 = j4;
            }
        } else {
            j2 = this.b;
            while (j2 > j) {
                qVar = qVar.g;
                j2 -= (long) (qVar.c - qVar.b);
            }
        }
        if (byteString.size() == 2) {
            byte b2 = byteString.getByte(0);
            byte b3 = byteString.getByte(1);
            while (j2 < this.b) {
                byte[] bArr = qVar.a;
                int i = qVar.c;
                for (int i2 = (int) ((((long) qVar.b) + j) - j2); i2 < i; i2++) {
                    byte b4 = bArr[i2];
                    if (b4 == b2 || b4 == b3) {
                        return ((long) (i2 - qVar.b)) + j2;
                    }
                }
                long j5 = j2 + ((long) (qVar.c - qVar.b));
                qVar = qVar.f;
                j = j5;
                j2 = j;
            }
        } else {
            byte[] internalArray = byteString.internalArray();
            while (j2 < this.b) {
                byte[] bArr2 = qVar.a;
                int i3 = qVar.c;
                for (int i4 = (int) ((((long) qVar.b) + j) - j2); i4 < i3; i4++) {
                    byte b5 = bArr2[i4];
                    for (byte b6 : internalArray) {
                        if (b5 == b6) {
                            return ((long) (i4 - qVar.b)) + j2;
                        }
                    }
                }
                long j6 = j2 + ((long) (qVar.c - qVar.b));
                qVar = qVar.f;
                j = j6;
                j2 = j;
            }
        }
        return -1;
    }

    public boolean a(long j, ByteString byteString) {
        return a(j, byteString, 0, byteString.size());
    }

    public boolean a(long j, ByteString byteString, int i, int i2) {
        if (j < 0 || i < 0 || i2 < 0 || this.b - j < ((long) i2) || byteString.size() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (c(j + ((long) i3)) != byteString.getByte(i + i3)) {
                return false;
            }
        }
        return true;
    }

    private boolean a(q qVar, int i, ByteString byteString, int i2, int i3) {
        int i4 = qVar.c;
        byte[] bArr = qVar.a;
        while (i2 < i3) {
            if (i == i4) {
                qVar = qVar.f;
                byte[] bArr2 = qVar.a;
                int i5 = qVar.b;
                bArr = bArr2;
                i = i5;
                i4 = qVar.c;
            }
            if (bArr[i] != byteString.getByte(i2)) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    public u a() {
        return u.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (this.b != cVar.b) {
            return false;
        }
        long j = 0;
        if (this.b == 0) {
            return true;
        }
        q qVar = this.a;
        q qVar2 = cVar.a;
        int i = qVar.b;
        int i2 = qVar2.b;
        while (j < this.b) {
            long min = (long) Math.min(qVar.c - i, qVar2.c - i2);
            int i3 = i2;
            int i4 = i;
            int i5 = 0;
            while (((long) i5) < min) {
                int i6 = i4 + 1;
                int i7 = i3 + 1;
                if (qVar.a[i4] != qVar2.a[i3]) {
                    return false;
                }
                i5++;
                i4 = i6;
                i3 = i7;
            }
            if (i4 == qVar.c) {
                qVar = qVar.f;
                i = qVar.b;
            } else {
                i = i4;
            }
            if (i3 == qVar2.c) {
                qVar2 = qVar2.f;
                i2 = qVar2.b;
            } else {
                i2 = i3;
            }
            j += min;
        }
        return true;
    }

    public int hashCode() {
        q qVar = this.a;
        if (qVar == null) {
            return 0;
        }
        int i = 1;
        do {
            for (int i2 = qVar.b; i2 < qVar.c; i2++) {
                i = qVar.a[i2] + (31 * i);
            }
            qVar = qVar.f;
        } while (qVar != this.a);
        return i;
    }

    public String toString() {
        return v().toString();
    }

    /* renamed from: u */
    public c clone() {
        c cVar = new c();
        if (this.b == 0) {
            return cVar;
        }
        cVar.a = this.a.a();
        q qVar = cVar.a;
        q qVar2 = cVar.a;
        q qVar3 = cVar.a;
        qVar2.g = qVar3;
        qVar.f = qVar3;
        q qVar4 = this.a;
        while (true) {
            qVar4 = qVar4.f;
            if (qVar4 != this.a) {
                cVar.a.g.a(qVar4.a());
            } else {
                cVar.b = this.b;
                return cVar;
            }
        }
    }

    public ByteString v() {
        if (this.b <= 2147483647L) {
            return g((int) this.b);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("size > Integer.MAX_VALUE: ");
        sb.append(this.b);
        throw new IllegalArgumentException(sb.toString());
    }

    public ByteString g(int i) {
        if (i == 0) {
            return ByteString.EMPTY;
        }
        return new SegmentedByteString(this, i);
    }
}
