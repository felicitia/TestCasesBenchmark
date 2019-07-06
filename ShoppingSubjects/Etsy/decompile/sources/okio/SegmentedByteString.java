package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

final class SegmentedByteString extends ByteString {
    final transient int[] directory;
    final transient byte[][] segments;

    SegmentedByteString(c cVar, int i) {
        super(null);
        v.a(cVar.b, 0, (long) i);
        int i2 = 0;
        q qVar = cVar.a;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (qVar.c == qVar.b) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += qVar.c - qVar.b;
            i4++;
            qVar = qVar.f;
        }
        this.segments = new byte[i4][];
        this.directory = new int[(i4 * 2)];
        q qVar2 = cVar.a;
        int i5 = 0;
        while (i2 < i) {
            this.segments[i5] = qVar2.a;
            i2 += qVar2.c - qVar2.b;
            if (i2 > i) {
                i2 = i;
            }
            this.directory[i5] = i2;
            this.directory[this.segments.length + i5] = qVar2.b;
            qVar2.d = true;
            i5++;
            qVar2 = qVar2.f;
        }
    }

    public String utf8() {
        return a().utf8();
    }

    public String string(Charset charset) {
        return a().string(charset);
    }

    public String base64() {
        return a().base64();
    }

    public String hex() {
        return a().hex();
    }

    public ByteString toAsciiLowercase() {
        return a().toAsciiLowercase();
    }

    public ByteString toAsciiUppercase() {
        return a().toAsciiUppercase();
    }

    public ByteString md5() {
        return a().md5();
    }

    public ByteString sha1() {
        return a().sha1();
    }

    public ByteString sha256() {
        return a().sha256();
    }

    public ByteString hmacSha1(ByteString byteString) {
        return a().hmacSha1(byteString);
    }

    public ByteString hmacSha256(ByteString byteString) {
        return a().hmacSha256(byteString);
    }

    public String base64Url() {
        return a().base64Url();
    }

    public ByteString substring(int i) {
        return a().substring(i);
    }

    public ByteString substring(int i, int i2) {
        return a().substring(i, i2);
    }

    public byte getByte(int i) {
        int i2;
        v.a((long) this.directory[this.segments.length - 1], (long) i, 1);
        int a = a(i);
        if (a == 0) {
            i2 = 0;
        } else {
            i2 = this.directory[a - 1];
        }
        return this.segments[a][(i - i2) + this.directory[this.segments.length + a]];
    }

    private int a(int i) {
        int binarySearch = Arrays.binarySearch(this.directory, 0, this.segments.length, i + 1);
        return binarySearch >= 0 ? binarySearch : binarySearch ^ -1;
    }

    public int size() {
        return this.directory[this.segments.length - 1];
    }

    public byte[] toByteArray() {
        int i = 0;
        byte[] bArr = new byte[this.directory[this.segments.length - 1]];
        int length = this.segments.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.directory[length + i];
            int i4 = this.directory[i];
            System.arraycopy(this.segments[i], i3, bArr, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr;
    }

    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    public void write(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        int i = 0;
        int length = this.segments.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.directory[length + i];
            int i4 = this.directory[i];
            outputStream.write(this.segments[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
    }

    /* access modifiers changed from: 0000 */
    public void write(c cVar) {
        int i = 0;
        int length = this.segments.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.directory[length + i];
            int i4 = this.directory[i];
            q qVar = new q(this.segments[i], i3, (i3 + i4) - i2, true, false);
            if (cVar.a == null) {
                qVar.g = qVar;
                qVar.f = qVar;
                cVar.a = qVar;
            } else {
                cVar.a.g.a(qVar);
            }
            i++;
            i2 = i4;
        }
        cVar.b += (long) i2;
    }

    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        int i4;
        if (i < 0 || i > size() - i3) {
            return false;
        }
        int a = a(i);
        while (i3 > 0) {
            if (a == 0) {
                i4 = 0;
            } else {
                i4 = this.directory[a - 1];
            }
            int min = Math.min(i3, ((this.directory[a] - i4) + i4) - i);
            if (!byteString.rangeEquals(i2, this.segments[a], (i - i4) + this.directory[this.segments.length + a], min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            a++;
        }
        return true;
    }

    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        int i4;
        if (i < 0 || i > size() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int a = a(i);
        while (i3 > 0) {
            if (a == 0) {
                i4 = 0;
            } else {
                i4 = this.directory[a - 1];
            }
            int min = Math.min(i3, ((this.directory[a] - i4) + i4) - i);
            if (!v.a(this.segments[a], (i - i4) + this.directory[this.segments.length + a], bArr, i2, min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            a++;
        }
        return true;
    }

    public int indexOf(byte[] bArr, int i) {
        return a().indexOf(bArr, i);
    }

    public int lastIndexOf(byte[] bArr, int i) {
        return a().lastIndexOf(bArr, i);
    }

    private ByteString a() {
        return new ByteString(toByteArray());
    }

    /* access modifiers changed from: 0000 */
    public byte[] internalArray() {
        return toByteArray();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        if (rangeEquals(0, r5, 0, size()) != false) goto L_0x0021;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof okio.ByteString
            r2 = 0
            if (r1 == 0) goto L_0x0020
            okio.ByteString r5 = (okio.ByteString) r5
            int r1 = r5.size()
            int r3 = r4.size()
            if (r1 != r3) goto L_0x0020
            int r1 = r4.size()
            boolean r5 = r4.rangeEquals(r2, r5, r2, r1)
            if (r5 == 0) goto L_0x0020
            goto L_0x0021
        L_0x0020:
            r0 = r2
        L_0x0021:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.SegmentedByteString.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int i2 = 0;
        int length = this.segments.length;
        int i3 = 1;
        int i4 = 0;
        while (i2 < length) {
            byte[] bArr = this.segments[i2];
            int i5 = this.directory[length + i2];
            int i6 = this.directory[i2];
            int i7 = (i6 - i4) + i5;
            while (i5 < i7) {
                i3 = bArr[i5] + (31 * i3);
                i5++;
            }
            i2++;
            i4 = i6;
        }
        this.hashCode = i3;
        return i3;
    }

    public String toString() {
        return a().toString();
    }

    private Object writeReplace() {
        return a();
    }
}
