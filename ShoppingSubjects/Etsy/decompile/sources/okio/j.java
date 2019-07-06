package okio;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

/* compiled from: GzipSink */
public final class j implements s {
    private final d a;
    private final Deflater b;
    private final f c;
    private boolean d;
    private final CRC32 e = new CRC32();

    public j(s sVar) {
        if (sVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.b = new Deflater(-1, true);
        this.a = m.a(sVar);
        this.c = new f(this.a, this.b);
        b();
    }

    public void a_(c cVar, long j) throws IOException {
        if (j < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount < 0: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        } else if (j != 0) {
            b(cVar, j);
            this.c.a_(cVar, j);
        }
    }

    public void flush() throws IOException {
        this.c.flush();
    }

    public u a() {
        return this.a.a();
    }

    public void close() throws IOException {
        if (!this.d) {
            Throwable th = null;
            try {
                this.c.b();
                c();
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.b.end();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            try {
                this.a.close();
            } catch (Throwable th4) {
                if (th == null) {
                    th = th4;
                }
            }
            this.d = true;
            if (th != null) {
                v.a(th);
            }
        }
    }

    private void b() {
        c c2 = this.a.c();
        c2.j(8075);
        c2.k(8);
        c2.k(0);
        c2.i(0);
        c2.k(0);
        c2.k(0);
    }

    private void c() throws IOException {
        this.a.h((int) this.e.getValue());
        this.a.h((int) this.b.getBytesRead());
    }

    private void b(c cVar, long j) {
        q qVar = cVar.a;
        while (j > 0) {
            int min = (int) Math.min(j, (long) (qVar.c - qVar.b));
            this.e.update(qVar.a, qVar.b, min);
            long j2 = j - ((long) min);
            qVar = qVar.f;
            j = j2;
        }
    }
}
