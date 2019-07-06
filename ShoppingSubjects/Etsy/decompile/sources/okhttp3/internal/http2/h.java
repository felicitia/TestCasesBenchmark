package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.c;
import okio.d;

/* compiled from: Http2Writer */
final class h implements Closeable {
    private static final Logger b = Logger.getLogger(c.class.getName());
    final C0196b a = new C0196b(this.e);
    private final d c;
    private final boolean d;
    private final c e = new c();
    private int f = 16384;
    private boolean g;

    h(d dVar, boolean z) {
        this.c = dVar;
        this.d = z;
    }

    public synchronized void a() throws IOException {
        if (this.g) {
            throw new IOException("closed");
        } else if (this.d) {
            if (b.isLoggable(Level.FINE)) {
                b.fine(okhttp3.internal.c.a(">> CONNECTION %s", c.a.hex()));
            }
            this.c.c(c.a.toByteArray());
            this.c.flush();
        }
    }

    public synchronized void a(k kVar) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.f = kVar.d(this.f);
        if (kVar.c() != -1) {
            this.a.a(kVar.c());
        }
        a(0, 0, 4, 1);
        this.c.flush();
    }

    public synchronized void a(int i, int i2, List<a> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.a.a(list);
        long b2 = this.e.b();
        int min = (int) Math.min((long) (this.f - 4), b2);
        long j = (long) min;
        a(i, min + 4, 5, b2 == j ? (byte) 4 : 0);
        this.c.i(i2 & Integer.MAX_VALUE);
        this.c.a_(this.e, j);
        if (b2 > j) {
            b(i, b2 - j);
        }
    }

    public synchronized void b() throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.c.flush();
    }

    public synchronized void a(boolean z, int i, int i2, List<a> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        a(z, i, list);
    }

    public synchronized void a(int i, ErrorCode errorCode) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        } else if (errorCode.httpCode == -1) {
            throw new IllegalArgumentException();
        } else {
            a(i, 4, 3, 0);
            this.c.i(errorCode.httpCode);
            this.c.flush();
        }
    }

    public int c() {
        return this.f;
    }

    public synchronized void a(boolean z, int i, c cVar, int i2) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        byte b2 = 0;
        if (z) {
            b2 = (byte) 1;
        }
        a(i, b2, cVar, i2);
    }

    /* access modifiers changed from: 0000 */
    public void a(int i, byte b2, c cVar, int i2) throws IOException {
        a(i, i2, 0, b2);
        if (i2 > 0) {
            this.c.a_(cVar, (long) i2);
        }
    }

    public synchronized void b(k kVar) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        int i = 0;
        a(0, kVar.b() * 6, 4, 0);
        while (i < 10) {
            if (kVar.a(i)) {
                int i2 = i == 4 ? 3 : i == 7 ? 4 : i;
                this.c.j(i2);
                this.c.i(kVar.b(i));
            }
            i++;
        }
        this.c.flush();
    }

    public synchronized void a(boolean z, int i, int i2) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        a(0, 8, 6, z ? (byte) 1 : 0);
        this.c.i(i);
        this.c.i(i2);
        this.c.flush();
    }

    public synchronized void a(int i, ErrorCode errorCode, byte[] bArr) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        } else if (errorCode.httpCode == -1) {
            throw c.a("errorCode.httpCode == -1", new Object[0]);
        } else {
            a(0, 8 + bArr.length, 7, 0);
            this.c.i(i);
            this.c.i(errorCode.httpCode);
            if (bArr.length > 0) {
                this.c.c(bArr);
            }
            this.c.flush();
        }
    }

    public synchronized void a(int i, long j) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        if (j != 0) {
            if (j <= 2147483647L) {
                a(i, 4, 8, 0);
                this.c.i((int) j);
                this.c.flush();
            }
        }
        throw c.a("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
    }

    public void a(int i, int i2, byte b2, byte b3) throws IOException {
        if (b.isLoggable(Level.FINE)) {
            b.fine(c.a(false, i, i2, b2, b3));
        }
        if (i2 > this.f) {
            throw c.a("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(this.f), Integer.valueOf(i2));
        } else if ((Integer.MIN_VALUE & i) != 0) {
            throw c.a("reserved bit set: %s", Integer.valueOf(i));
        } else {
            a(this.c, i2);
            this.c.k(b2 & 255);
            this.c.k(b3 & 255);
            this.c.i(i & Integer.MAX_VALUE);
        }
    }

    public synchronized void close() throws IOException {
        this.g = true;
        this.c.close();
    }

    private static void a(d dVar, int i) throws IOException {
        dVar.k((i >>> 16) & 255);
        dVar.k((i >>> 8) & 255);
        dVar.k(i & 255);
    }

    private void b(int i, long j) throws IOException {
        while (j > 0) {
            int min = (int) Math.min((long) this.f, j);
            long j2 = (long) min;
            long j3 = j - j2;
            a(i, min, 9, j3 == 0 ? (byte) 4 : 0);
            this.c.a_(this.e, j2);
            j = j3;
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z, int i, List<a> list) throws IOException {
        if (this.g) {
            throw new IOException("closed");
        }
        this.a.a(list);
        long b2 = this.e.b();
        int min = (int) Math.min((long) this.f, b2);
        long j = (long) min;
        byte b3 = b2 == j ? (byte) 4 : 0;
        if (z) {
            b3 = (byte) (b3 | 1);
        }
        a(i, min, 1, b3);
        this.c.a_(this.e, j);
        if (b2 > j) {
            b(i, b2 - j);
        }
    }
}
