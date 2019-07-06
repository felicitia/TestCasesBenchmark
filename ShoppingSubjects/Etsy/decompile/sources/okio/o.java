package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: RealBufferedSink */
final class o implements d {
    public final c a = new c();
    public final s b;
    boolean c;

    o(s sVar) {
        if (sVar == null) {
            throw new NullPointerException("sink == null");
        }
        this.b = sVar;
    }

    public c c() {
        return this.a;
    }

    public void a_(c cVar, long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a_(cVar, j);
        w();
    }

    public d c(ByteString byteString) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.c(byteString);
        return w();
    }

    public d b(String str) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(str);
        return w();
    }

    public d b(String str, int i, int i2) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(str, i, i2);
        return w();
    }

    public d c(byte[] bArr) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.c(bArr);
        return w();
    }

    public d c(byte[] bArr, int i, int i2) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.c(bArr, i, i2);
        return w();
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        int write = this.a.write(byteBuffer);
        w();
        return write;
    }

    public long a(t tVar) throws IOException {
        if (tVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long a2 = tVar.a(this.a, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (a2 == -1) {
                return j;
            }
            long j2 = j + a2;
            w();
            j = j2;
        }
    }

    public d k(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.k(i);
        return w();
    }

    public d j(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.j(i);
        return w();
    }

    public d i(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.i(i);
        return w();
    }

    public d h(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.h(i);
        return w();
    }

    public d m(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.m(j);
        return w();
    }

    public d l(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.l(j);
        return w();
    }

    public d w() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long h = this.a.h();
        if (h > 0) {
            this.b.a_(this.a, h);
        }
        return this;
    }

    public void flush() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (this.a.b > 0) {
            this.b.a_(this.a, this.a.b);
        }
        this.b.flush();
    }

    public boolean isOpen() {
        return !this.c;
    }

    public void close() throws IOException {
        if (!this.c) {
            Throwable th = null;
            try {
                if (this.a.b > 0) {
                    this.b.a_(this.a, this.a.b);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.b.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.c = true;
            if (th != null) {
                v.a(th);
            }
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
