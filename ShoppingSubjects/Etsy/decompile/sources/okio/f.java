package okio;

import java.io.IOException;
import java.util.zip.Deflater;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* compiled from: DeflaterSink */
public final class f implements s {
    private final d a;
    private final Deflater b;
    private boolean c;

    f(d dVar, Deflater deflater) {
        if (dVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (deflater == null) {
            throw new IllegalArgumentException("inflater == null");
        } else {
            this.a = dVar;
            this.b = deflater;
        }
    }

    public void a_(c cVar, long j) throws IOException {
        v.a(cVar.b, 0, j);
        while (j > 0) {
            q qVar = cVar.a;
            int min = (int) Math.min(j, (long) (qVar.c - qVar.b));
            this.b.setInput(qVar.a, qVar.b, min);
            a(false);
            long j2 = (long) min;
            cVar.b -= j2;
            qVar.b += min;
            if (qVar.b == qVar.c) {
                cVar.a = qVar.b();
                r.a(qVar);
            }
            j -= j2;
        }
    }

    @IgnoreJRERequirement
    private void a(boolean z) throws IOException {
        q f;
        int i;
        c c2 = this.a.c();
        while (true) {
            f = c2.f(1);
            if (z) {
                i = this.b.deflate(f.a, f.c, 8192 - f.c, 2);
            } else {
                i = this.b.deflate(f.a, f.c, 8192 - f.c);
            }
            if (i > 0) {
                f.c += i;
                c2.b += (long) i;
                this.a.w();
            } else if (this.b.needsInput()) {
                break;
            }
        }
        if (f.b == f.c) {
            c2.a = f.b();
            r.a(f);
        }
    }

    public void flush() throws IOException {
        a(true);
        this.a.flush();
    }

    /* access modifiers changed from: 0000 */
    public void b() throws IOException {
        this.b.finish();
        a(false);
    }

    public void close() throws IOException {
        if (!this.c) {
            Throwable th = null;
            try {
                b();
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
            this.c = true;
            if (th != null) {
                v.a(th);
            }
        }
    }

    public u a() {
        return this.a.a();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DeflaterSink(");
        sb.append(this.a);
        sb.append(")");
        return sb.toString();
    }
}
