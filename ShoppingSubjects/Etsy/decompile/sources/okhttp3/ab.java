package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import okhttp3.internal.c;
import okio.e;

/* compiled from: ResponseBody */
public abstract class ab implements Closeable {
    private Reader a;

    /* compiled from: ResponseBody */
    static final class a extends Reader {
        private final e a;
        private final Charset b;
        private boolean c;
        private Reader d;

        a(e eVar, Charset charset) {
            this.a = eVar;
            this.b = charset;
        }

        public int read(char[] cArr, int i, int i2) throws IOException {
            if (this.c) {
                throw new IOException("Stream closed");
            }
            Reader reader = this.d;
            if (reader == null) {
                Reader inputStreamReader = new InputStreamReader(this.a.g(), c.a(this.a, this.b));
                this.d = inputStreamReader;
                reader = inputStreamReader;
            }
            return reader.read(cArr, i, i2);
        }

        public void close() throws IOException {
            this.c = true;
            if (this.d != null) {
                this.d.close();
            } else {
                this.a.close();
            }
        }
    }

    public abstract u a();

    public abstract long b();

    public abstract e c();

    /* JADX INFO: finally extract failed */
    public final byte[] d() throws IOException {
        long b = b();
        if (b > 2147483647L) {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot buffer entire body for content length: ");
            sb.append(b);
            throw new IOException(sb.toString());
        }
        e c = c();
        try {
            byte[] s = c.s();
            c.a((Closeable) c);
            if (b == -1 || b == ((long) s.length)) {
                return s;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Content-Length (");
            sb2.append(b);
            sb2.append(") and stream length (");
            sb2.append(s.length);
            sb2.append(") disagree");
            throw new IOException(sb2.toString());
        } catch (Throwable th) {
            c.a((Closeable) c);
            throw th;
        }
    }

    public final Reader e() {
        Reader reader = this.a;
        if (reader != null) {
            return reader;
        }
        a aVar = new a(c(), f());
        this.a = aVar;
        return aVar;
    }

    private Charset f() {
        u a2 = a();
        return a2 != null ? a2.a(c.e) : c.e;
    }

    public void close() {
        c.a((Closeable) c());
    }

    public static ab a(u uVar, byte[] bArr) {
        return a(uVar, (long) bArr.length, new okio.c().c(bArr));
    }

    public static ab a(final u uVar, final long j, final e eVar) {
        if (eVar != null) {
            return new ab() {
                public u a() {
                    return u.this;
                }

                public long b() {
                    return j;
                }

                public e c() {
                    return eVar;
                }
            };
        }
        throw new NullPointerException("source == null");
    }
}
