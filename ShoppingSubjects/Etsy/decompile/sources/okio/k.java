package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* compiled from: GzipSource */
public final class k implements t {
    private int a = 0;
    private final e b;
    private final Inflater c;
    private final l d;
    private final CRC32 e = new CRC32();

    public k(t tVar) {
        if (tVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.c = new Inflater(true);
        this.b = m.a(tVar);
        this.d = new l(this.b, this.c);
    }

    public long a(c cVar, long j) throws IOException {
        if (j < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount < 0: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        } else if (j == 0) {
            return 0;
        } else {
            if (this.a == 0) {
                b();
                this.a = 1;
            }
            if (this.a == 1) {
                long j2 = cVar.b;
                long a2 = this.d.a(cVar, j);
                if (a2 != -1) {
                    a(cVar, j2, a2);
                    return a2;
                }
                this.a = 2;
            }
            if (this.a == 2) {
                c();
                this.a = 3;
                if (!this.b.f()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1;
        }
    }

    private void b() throws IOException {
        this.b.a(10);
        byte c2 = this.b.c().c(3);
        boolean z = ((c2 >> 1) & 1) == 1;
        if (z) {
            a(this.b.c(), 0, 10);
        }
        a("ID1ID2", 8075, (int) this.b.j());
        this.b.i(8);
        if (((c2 >> 2) & 1) == 1) {
            this.b.a(2);
            if (z) {
                a(this.b.c(), 0, 2);
            }
            long l = (long) this.b.c().l();
            this.b.a(l);
            if (z) {
                a(this.b.c(), 0, l);
            }
            this.b.i(l);
        }
        if (((c2 >> 3) & 1) == 1) {
            long a2 = this.b.a(0);
            if (a2 == -1) {
                throw new EOFException();
            }
            if (z) {
                a(this.b.c(), 0, a2 + 1);
            }
            this.b.i(a2 + 1);
        }
        if (((c2 >> 4) & 1) == 1) {
            long a3 = this.b.a(0);
            if (a3 == -1) {
                throw new EOFException();
            }
            if (z) {
                a(this.b.c(), 0, a3 + 1);
            }
            this.b.i(a3 + 1);
        }
        if (z) {
            a("FHCRC", (int) this.b.l(), (int) (short) ((int) this.e.getValue()));
            this.e.reset();
        }
    }

    private void c() throws IOException {
        a("CRC", this.b.m(), (int) this.e.getValue());
        a("ISIZE", this.b.m(), (int) this.c.getBytesWritten());
    }

    public u a() {
        return this.b.a();
    }

    public void close() throws IOException {
        this.d.close();
    }

    private void a(c cVar, long j, long j2) {
        q qVar = cVar.a;
        while (j >= ((long) (qVar.c - qVar.b))) {
            long j3 = j - ((long) (qVar.c - qVar.b));
            qVar = qVar.f;
            j = j3;
        }
        while (j2 > 0) {
            int i = (int) (((long) qVar.b) + j);
            int min = (int) Math.min((long) (qVar.c - i), j2);
            this.e.update(qVar.a, i, min);
            long j4 = j2 - ((long) min);
            qVar = qVar.f;
            j = 0;
            j2 = j4;
        }
    }

    private void a(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i)}));
        }
    }
}
