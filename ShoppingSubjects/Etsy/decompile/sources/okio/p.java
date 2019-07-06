package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* compiled from: RealBufferedSource */
final class p implements e {
    public final c a = new c();
    public final t b;
    boolean c;

    p(t tVar) {
        if (tVar == null) {
            throw new NullPointerException("source == null");
        }
        this.b = tVar;
    }

    public c c() {
        return this.a;
    }

    public long a(c cVar, long j) throws IOException {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount < 0: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        } else if (this.c) {
            throw new IllegalStateException("closed");
        } else if (this.a.b == 0 && this.b.a(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        } else {
            return this.a.a(cVar, Math.min(j, this.a.b));
        }
    }

    public boolean f() throws IOException {
        if (!this.c) {
            return this.a.f() && this.b.a(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
        }
        throw new IllegalStateException("closed");
    }

    public void a(long j) throws IOException {
        if (!b(j)) {
            throw new EOFException();
        }
    }

    public boolean b(long j) throws IOException {
        if (j < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount < 0: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        } else if (this.c) {
            throw new IllegalStateException("closed");
        } else {
            while (this.a.b < j) {
                if (this.b.a(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return false;
                }
            }
            return true;
        }
    }

    public byte i() throws IOException {
        a(1);
        return this.a.i();
    }

    public ByteString d(long j) throws IOException {
        a(j);
        return this.a.d(j);
    }

    public int a(n nVar) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        do {
            int b2 = this.a.b(nVar);
            if (b2 == -1) {
                return -1;
            }
            long size = (long) nVar.a[b2].size();
            if (size <= this.a.b) {
                this.a.i(size);
                return b2;
            }
        } while (this.b.a(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1);
        return -1;
    }

    public byte[] s() throws IOException {
        this.a.a(this.b);
        return this.a.s();
    }

    public byte[] h(long j) throws IOException {
        a(j);
        return this.a.h(j);
    }

    public void a(byte[] bArr) throws IOException {
        try {
            a((long) bArr.length);
            this.a.a(bArr);
        } catch (EOFException e) {
            int i = 0;
            while (this.a.b > 0) {
                int a2 = this.a.a(bArr, i, (int) this.a.b);
                if (a2 == -1) {
                    throw new AssertionError();
                }
                i += a2;
            }
            throw e;
        }
    }

    public int read(ByteBuffer byteBuffer) throws IOException {
        if (this.a.b == 0 && this.b.a(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return this.a.read(byteBuffer);
    }

    public long a(s sVar) throws IOException {
        if (sVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        long j = 0;
        while (this.b.a(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
            long h = this.a.h();
            if (h > 0) {
                long j2 = j + h;
                sVar.a_(this.a, h);
                j = j2;
            }
        }
        if (this.a.b() <= 0) {
            return j;
        }
        long b2 = j + this.a.b();
        sVar.a_(this.a, this.a.b());
        return b2;
    }

    public String q() throws IOException {
        return f(Long.MAX_VALUE);
    }

    public String f(long j) throws IOException {
        if (j < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("limit < 0: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        }
        long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
        long a2 = a(10, 0, j2);
        if (a2 != -1) {
            return this.a.g(a2);
        }
        if (j2 < Long.MAX_VALUE && b(j2) && this.a.c(j2 - 1) == 13 && b(j2 + 1) && this.a.c(j2) == 10) {
            return this.a.g(j2);
        }
        c cVar = new c();
        this.a.a(cVar, 0, Math.min(32, this.a.b()));
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\\n not found: limit=");
        sb2.append(Math.min(this.a.b(), j));
        sb2.append(" content=");
        sb2.append(cVar.o().hex());
        sb2.append(8230);
        throw new EOFException(sb2.toString());
    }

    public short j() throws IOException {
        a(2);
        return this.a.j();
    }

    public short l() throws IOException {
        a(2);
        return this.a.l();
    }

    public int k() throws IOException {
        a(4);
        return this.a.k();
    }

    public int m() throws IOException {
        a(4);
        return this.a.m();
    }

    public long n() throws IOException {
        a(1);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!b((long) i2)) {
                break;
            }
            byte c2 = this.a.c((long) i);
            if ((c2 >= 48 && c2 <= 57) || ((c2 >= 97 && c2 <= 102) || (c2 >= 65 && c2 <= 70))) {
                i = i2;
            } else if (i == 0) {
                throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", new Object[]{Byte.valueOf(c2)}));
            }
        }
        return this.a.n();
    }

    public void i(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            if (this.a.b == 0 && this.b.a(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, this.a.b());
            this.a.i(min);
            j -= min;
        }
    }

    public long a(byte b2) throws IOException {
        return a(b2, 0, Long.MAX_VALUE);
    }

    public long a(byte b2, long j, long j2) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        } else if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", new Object[]{Long.valueOf(j), Long.valueOf(j2)}));
        } else {
            while (j < j2) {
                long a2 = this.a.a(b2, j, j2);
                if (a2 != -1) {
                    return a2;
                }
                long j3 = this.a.b;
                if (j3 >= j2 || this.b.a(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1;
                }
                j = Math.max(j, j3);
            }
            return -1;
        }
    }

    public long b(ByteString byteString) throws IOException {
        return a(byteString, 0);
    }

    public long a(ByteString byteString, long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long a2 = this.a.a(byteString, j);
            if (a2 != -1) {
                return a2;
            }
            long j2 = this.a.b;
            if (this.b.a(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1;
            }
            j = Math.max(j, j2);
        }
    }

    public boolean a(long j, ByteString byteString) throws IOException {
        return a(j, byteString, 0, byteString.size());
    }

    public boolean a(long j, ByteString byteString, int i, int i2) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        } else if (j < 0 || i < 0 || i2 < 0 || byteString.size() - i < i2) {
            return false;
        } else {
            for (int i3 = 0; i3 < i2; i3++) {
                long j2 = j + ((long) i3);
                if (!b(j2 + 1) || this.a.c(j2) != byteString.getByte(i + i3)) {
                    return false;
                }
            }
            return true;
        }
    }

    public InputStream g() {
        return new InputStream() {
            public int read() throws IOException {
                if (p.this.c) {
                    throw new IOException("closed");
                } else if (p.this.a.b == 0 && p.this.b.a(p.this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1;
                } else {
                    return p.this.a.i() & 255;
                }
            }

            public int read(byte[] bArr, int i, int i2) throws IOException {
                if (p.this.c) {
                    throw new IOException("closed");
                }
                v.a((long) bArr.length, (long) i, (long) i2);
                if (p.this.a.b == 0 && p.this.b.a(p.this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1;
                }
                return p.this.a.a(bArr, i, i2);
            }

            public int available() throws IOException {
                if (!p.this.c) {
                    return (int) Math.min(p.this.a.b, 2147483647L);
                }
                throw new IOException("closed");
            }

            public void close() throws IOException {
                p.this.close();
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(p.this);
                sb.append(".inputStream()");
                return sb.toString();
            }
        };
    }

    public boolean isOpen() {
        return !this.c;
    }

    public void close() throws IOException {
        if (!this.c) {
            this.c = true;
            this.b.close();
            this.a.t();
        }
    }

    public u a() {
        return this.b.a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("buffer(");
        sb.append(this.b);
        sb.append(")");
        return sb.toString();
    }
}
