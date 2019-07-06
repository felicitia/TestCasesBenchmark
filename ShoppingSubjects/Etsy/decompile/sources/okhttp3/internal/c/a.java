package okhttp3.internal.c;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.internal.b.h;
import okhttp3.internal.b.k;
import okhttp3.w;
import okhttp3.y;
import okio.i;
import okio.m;
import okio.s;
import okio.t;
import okio.u;
import org.apache.http.entity.mime.MIME;

/* compiled from: Http1Codec */
public final class a implements okhttp3.internal.b.c {
    final w a;
    final okhttp3.internal.connection.f b;
    final okio.e c;
    final okio.d d;
    int e = 0;
    private long f = PlaybackStateCompat.ACTION_SET_REPEAT_MODE;

    /* renamed from: okhttp3.internal.c.a$a reason: collision with other inner class name */
    /* compiled from: Http1Codec */
    private abstract class C0194a implements t {
        protected final i a;
        protected boolean b;
        protected long c;

        private C0194a() {
            this.a = new i(a.this.c.a());
            this.c = 0;
        }

        public u a() {
            return this.a;
        }

        public long a(okio.c cVar, long j) throws IOException {
            try {
                long a2 = a.this.c.a(cVar, j);
                if (a2 > 0) {
                    this.c += a2;
                }
                return a2;
            } catch (IOException e) {
                a(false, e);
                throw e;
            }
        }

        /* access modifiers changed from: protected */
        public final void a(boolean z, IOException iOException) throws IOException {
            if (a.this.e != 6) {
                if (a.this.e != 5) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("state: ");
                    sb.append(a.this.e);
                    throw new IllegalStateException(sb.toString());
                }
                a.this.a(this.a);
                a.this.e = 6;
                if (a.this.b != null) {
                    a.this.b.a(!z, a.this, this.c, iOException);
                }
            }
        }
    }

    /* compiled from: Http1Codec */
    private final class b implements s {
        private final i b = new i(a.this.d.a());
        private boolean c;

        b() {
        }

        public u a() {
            return this.b;
        }

        public void a_(okio.c cVar, long j) throws IOException {
            if (this.c) {
                throw new IllegalStateException("closed");
            } else if (j != 0) {
                a.this.d.l(j);
                a.this.d.b("\r\n");
                a.this.d.a_(cVar, j);
                a.this.d.b("\r\n");
            }
        }

        public synchronized void flush() throws IOException {
            if (!this.c) {
                a.this.d.flush();
            }
        }

        public synchronized void close() throws IOException {
            if (!this.c) {
                this.c = true;
                a.this.d.b("0\r\n\r\n");
                a.this.a(this.b);
                a.this.e = 3;
            }
        }
    }

    /* compiled from: Http1Codec */
    private class c extends C0194a {
        private final HttpUrl f;
        private long g = -1;
        private boolean h = true;

        c(HttpUrl httpUrl) {
            super();
            this.f = httpUrl;
        }

        public long a(okio.c cVar, long j) throws IOException {
            if (j < 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("byteCount < 0: ");
                sb.append(j);
                throw new IllegalArgumentException(sb.toString());
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (!this.h) {
                return -1;
            } else {
                if (this.g == 0 || this.g == -1) {
                    b();
                    if (!this.h) {
                        return -1;
                    }
                }
                long a = super.a(cVar, Math.min(j, this.g));
                if (a == -1) {
                    ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                    a(false, (IOException) protocolException);
                    throw protocolException;
                }
                this.g -= a;
                return a;
            }
        }

        private void b() throws IOException {
            if (this.g != -1) {
                a.this.c.q();
            }
            try {
                this.g = a.this.c.n();
                String trim = a.this.c.q().trim();
                if (this.g < 0 || (!trim.isEmpty() && !trim.startsWith(";"))) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("expected chunk size and optional extensions but was \"");
                    sb.append(this.g);
                    sb.append(trim);
                    sb.append("\"");
                    throw new ProtocolException(sb.toString());
                } else if (this.g == 0) {
                    this.h = false;
                    okhttp3.internal.b.e.a(a.this.a.g(), this.f, a.this.d());
                    a(true, (IOException) null);
                }
            } catch (NumberFormatException e2) {
                throw new ProtocolException(e2.getMessage());
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (this.h && !okhttp3.internal.c.a((t) this, 100, TimeUnit.MILLISECONDS)) {
                    a(false, (IOException) null);
                }
                this.b = true;
            }
        }
    }

    /* compiled from: Http1Codec */
    private final class d implements s {
        private final i b = new i(a.this.d.a());
        private boolean c;
        private long d;

        d(long j) {
            this.d = j;
        }

        public u a() {
            return this.b;
        }

        public void a_(okio.c cVar, long j) throws IOException {
            if (this.c) {
                throw new IllegalStateException("closed");
            }
            okhttp3.internal.c.a(cVar.b(), 0, j);
            if (j > this.d) {
                StringBuilder sb = new StringBuilder();
                sb.append("expected ");
                sb.append(this.d);
                sb.append(" bytes but received ");
                sb.append(j);
                throw new ProtocolException(sb.toString());
            }
            a.this.d.a_(cVar, j);
            this.d -= j;
        }

        public void flush() throws IOException {
            if (!this.c) {
                a.this.d.flush();
            }
        }

        public void close() throws IOException {
            if (!this.c) {
                this.c = true;
                if (this.d > 0) {
                    throw new ProtocolException("unexpected end of stream");
                }
                a.this.a(this.b);
                a.this.e = 3;
            }
        }
    }

    /* compiled from: Http1Codec */
    private class e extends C0194a {
        private long f;

        e(long j) throws IOException {
            super();
            this.f = j;
            if (this.f == 0) {
                a(true, (IOException) null);
            }
        }

        public long a(okio.c cVar, long j) throws IOException {
            if (j < 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("byteCount < 0: ");
                sb.append(j);
                throw new IllegalArgumentException(sb.toString());
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (this.f == 0) {
                return -1;
            } else {
                long a = super.a(cVar, Math.min(this.f, j));
                if (a == -1) {
                    ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                    a(false, (IOException) protocolException);
                    throw protocolException;
                }
                this.f -= a;
                if (this.f == 0) {
                    a(true, (IOException) null);
                }
                return a;
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (this.f != 0 && !okhttp3.internal.c.a((t) this, 100, TimeUnit.MILLISECONDS)) {
                    a(false, (IOException) null);
                }
                this.b = true;
            }
        }
    }

    /* compiled from: Http1Codec */
    private class f extends C0194a {
        private boolean f;

        f() {
            super();
        }

        public long a(okio.c cVar, long j) throws IOException {
            if (j < 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("byteCount < 0: ");
                sb.append(j);
                throw new IllegalArgumentException(sb.toString());
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (this.f) {
                return -1;
            } else {
                long a = super.a(cVar, j);
                if (a != -1) {
                    return a;
                }
                this.f = true;
                a(true, (IOException) null);
                return -1;
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (!this.f) {
                    a(false, (IOException) null);
                }
                this.b = true;
            }
        }
    }

    public a(w wVar, okhttp3.internal.connection.f fVar, okio.e eVar, okio.d dVar) {
        this.a = wVar;
        this.b = fVar;
        this.c = eVar;
        this.d = dVar;
    }

    public s a(y yVar, long j) {
        if ("chunked".equalsIgnoreCase(yVar.a("Transfer-Encoding"))) {
            return e();
        }
        if (j != -1) {
            return a(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    public void c() {
        okhttp3.internal.connection.c c2 = this.b.c();
        if (c2 != null) {
            c2.c();
        }
    }

    public void a(y yVar) throws IOException {
        a(yVar.c(), okhttp3.internal.b.i.a(yVar, this.b.c().b().b().type()));
    }

    public ab a(aa aaVar) throws IOException {
        this.b.c.f(this.b.b);
        String a2 = aaVar.a(MIME.CONTENT_TYPE);
        if (!okhttp3.internal.b.e.b(aaVar)) {
            return new h(a2, 0, m.a(b(0)));
        }
        if ("chunked".equalsIgnoreCase(aaVar.a("Transfer-Encoding"))) {
            return new h(a2, -1, m.a(a(aaVar.a().a())));
        }
        long a3 = okhttp3.internal.b.e.a(aaVar);
        if (a3 != -1) {
            return new h(a2, a3, m.a(b(a3)));
        }
        return new h(a2, -1, m.a(f()));
    }

    public void a() throws IOException {
        this.d.flush();
    }

    public void b() throws IOException {
        this.d.flush();
    }

    public void a(okhttp3.s sVar, String str) throws IOException {
        if (this.e != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("state: ");
            sb.append(this.e);
            throw new IllegalStateException(sb.toString());
        }
        this.d.b(str).b("\r\n");
        int a2 = sVar.a();
        for (int i = 0; i < a2; i++) {
            this.d.b(sVar.a(i)).b(": ").b(sVar.b(i)).b("\r\n");
        }
        this.d.b("\r\n");
        this.e = 1;
    }

    public okhttp3.aa.a a(boolean z) throws IOException {
        if (this.e == 1 || this.e == 3) {
            try {
                k a2 = k.a(g());
                okhttp3.aa.a a3 = new okhttp3.aa.a().a(a2.a).a(a2.b).a(a2.c).a(d());
                if (z && a2.b == 100) {
                    return null;
                }
                if (a2.b == 100) {
                    this.e = 3;
                    return a3;
                }
                this.e = 4;
                return a3;
            } catch (EOFException e2) {
                StringBuilder sb = new StringBuilder();
                sb.append("unexpected end of stream on ");
                sb.append(this.b);
                IOException iOException = new IOException(sb.toString());
                iOException.initCause(e2);
                throw iOException;
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("state: ");
            sb2.append(this.e);
            throw new IllegalStateException(sb2.toString());
        }
    }

    private String g() throws IOException {
        String f2 = this.c.f(this.f);
        this.f -= (long) f2.length();
        return f2;
    }

    public okhttp3.s d() throws IOException {
        okhttp3.s.a aVar = new okhttp3.s.a();
        while (true) {
            String g = g();
            if (g.length() == 0) {
                return aVar.a();
            }
            okhttp3.internal.a.a.a(aVar, g);
        }
    }

    public s e() {
        if (this.e != 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("state: ");
            sb.append(this.e);
            throw new IllegalStateException(sb.toString());
        }
        this.e = 2;
        return new b();
    }

    public s a(long j) {
        if (this.e != 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("state: ");
            sb.append(this.e);
            throw new IllegalStateException(sb.toString());
        }
        this.e = 2;
        return new d(j);
    }

    public t b(long j) throws IOException {
        if (this.e != 4) {
            StringBuilder sb = new StringBuilder();
            sb.append("state: ");
            sb.append(this.e);
            throw new IllegalStateException(sb.toString());
        }
        this.e = 5;
        return new e(j);
    }

    public t a(HttpUrl httpUrl) throws IOException {
        if (this.e != 4) {
            StringBuilder sb = new StringBuilder();
            sb.append("state: ");
            sb.append(this.e);
            throw new IllegalStateException(sb.toString());
        }
        this.e = 5;
        return new c(httpUrl);
    }

    public t f() throws IOException {
        if (this.e != 4) {
            StringBuilder sb = new StringBuilder();
            sb.append("state: ");
            sb.append(this.e);
            throw new IllegalStateException(sb.toString());
        } else if (this.b == null) {
            throw new IllegalStateException("streamAllocation == null");
        } else {
            this.e = 5;
            this.b.e();
            return new f();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(i iVar) {
        u a2 = iVar.a();
        iVar.a(u.c);
        a2.f();
        a2.l_();
    }
}
