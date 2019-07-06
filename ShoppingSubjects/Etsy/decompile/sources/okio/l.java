package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* compiled from: InflaterSource */
public final class l implements t {
    private final e a;
    private final Inflater b;
    private int c;
    private boolean d;

    l(e eVar, Inflater inflater) {
        if (eVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        } else {
            this.a = eVar;
            this.b = inflater;
        }
    }

    public long a(c cVar, long j) throws IOException {
        boolean b2;
        if (j < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("byteCount < 0: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        } else if (this.d) {
            throw new IllegalStateException("closed");
        } else if (j == 0) {
            return 0;
        } else {
            do {
                b2 = b();
                try {
                    q f = cVar.f(1);
                    int inflate = this.b.inflate(f.a, f.c, (int) Math.min(j, (long) (8192 - f.c)));
                    if (inflate > 0) {
                        f.c += inflate;
                        long j2 = (long) inflate;
                        cVar.b += j2;
                        return j2;
                    }
                    if (!this.b.finished()) {
                        if (this.b.needsDictionary()) {
                        }
                    }
                    c();
                    if (f.b == f.c) {
                        cVar.a = f.b();
                        r.a(f);
                    }
                    return -1;
                } catch (DataFormatException e) {
                    throw new IOException(e);
                }
            } while (!b2);
            throw new EOFException("source exhausted prematurely");
        }
    }

    public boolean b() throws IOException {
        if (!this.b.needsInput()) {
            return false;
        }
        c();
        if (this.b.getRemaining() != 0) {
            throw new IllegalStateException("?");
        } else if (this.a.f()) {
            return true;
        } else {
            q qVar = this.a.c().a;
            this.c = qVar.c - qVar.b;
            this.b.setInput(qVar.a, qVar.b, this.c);
            return false;
        }
    }

    private void c() throws IOException {
        if (this.c != 0) {
            int remaining = this.c - this.b.getRemaining();
            this.c -= remaining;
            this.a.i((long) remaining);
        }
    }

    public u a() {
        return this.a.a();
    }

    public void close() throws IOException {
        if (!this.d) {
            this.b.end();
            this.d = true;
            this.a.close();
        }
    }
}
