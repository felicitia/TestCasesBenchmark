package okhttp3.internal.a;

import com.android.volley.toolbox.BasicNetwork;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.internal.b.e;
import okhttp3.internal.b.f;
import okhttp3.internal.b.h;
import okhttp3.internal.c;
import okhttp3.t;
import okhttp3.y;
import okio.d;
import okio.m;
import okio.s;
import okio.u;
import org.apache.http.entity.mime.MIME;

/* compiled from: CacheInterceptor */
public final class a implements t {
    final e a;

    public a(e eVar) {
        this.a = eVar;
    }

    public aa a(okhttp3.t.a aVar) throws IOException {
        aa a2 = this.a != null ? this.a.a(aVar.a()) : null;
        c a3 = new okhttp3.internal.a.c.a(System.currentTimeMillis(), aVar.a(), a2).a();
        y yVar = a3.a;
        aa aaVar = a3.b;
        if (this.a != null) {
            this.a.a(a3);
        }
        if (a2 != null && aaVar == null) {
            c.a((Closeable) a2.g());
        }
        if (yVar == null && aaVar == null) {
            return new okhttp3.aa.a().a(aVar.a()).a(Protocol.HTTP_1_1).a(504).a("Unsatisfiable Request (only-if-cached)").a(c.c).a(-1).b(System.currentTimeMillis()).a();
        }
        if (yVar == null) {
            return aaVar.h().b(a(aaVar)).a();
        }
        try {
            aa a4 = aVar.a(yVar);
            if (a4 == null && a2 != null) {
            }
            if (aaVar != null) {
                if (a4.b() == 304) {
                    aa a5 = aaVar.h().a(a(aaVar.f(), a4.f())).a(a4.k()).b(a4.l()).b(a(aaVar)).a(a(a4)).a();
                    a4.g().close();
                    this.a.a();
                    this.a.a(aaVar, a5);
                    return a5;
                }
                c.a((Closeable) aaVar.g());
            }
            aa a6 = a4.h().b(a(aaVar)).a(a(a4)).a();
            if (this.a != null) {
                if (e.b(a6) && c.a(a6, yVar)) {
                    return a(this.a.a(a6), a6);
                }
                if (f.a(yVar.b())) {
                    try {
                        this.a.b(yVar);
                    } catch (IOException unused) {
                    }
                }
            }
            return a6;
        } finally {
            if (a2 != null) {
                c.a((Closeable) a2.g());
            }
        }
    }

    private static aa a(aa aaVar) {
        return (aaVar == null || aaVar.g() == null) ? aaVar : aaVar.h().a((ab) null).a();
    }

    private aa a(final b bVar, aa aaVar) throws IOException {
        if (bVar == null) {
            return aaVar;
        }
        s a2 = bVar.a();
        if (a2 == null) {
            return aaVar;
        }
        final okio.e c = aaVar.g().c();
        final d a3 = m.a(a2);
        AnonymousClass1 r2 = new okio.t() {
            boolean a;

            public long a(okio.c cVar, long j) throws IOException {
                try {
                    long a2 = c.a(cVar, j);
                    if (a2 == -1) {
                        if (!this.a) {
                            this.a = true;
                            a3.close();
                        }
                        return -1;
                    }
                    cVar.a(a3.c(), cVar.b() - a2, a2);
                    a3.w();
                    return a2;
                } catch (IOException e2) {
                    if (!this.a) {
                        this.a = true;
                        bVar.b();
                    }
                    throw e2;
                }
            }

            public u a() {
                return c.a();
            }

            public void close() throws IOException {
                if (!this.a && !c.a((okio.t) this, 100, TimeUnit.MILLISECONDS)) {
                    this.a = true;
                    bVar.b();
                }
                c.close();
            }
        };
        return aaVar.h().a((ab) new h(aaVar.a(MIME.CONTENT_TYPE), aaVar.g().b(), m.a((okio.t) r2))).a();
    }

    private static okhttp3.s a(okhttp3.s sVar, okhttp3.s sVar2) {
        okhttp3.s.a aVar = new okhttp3.s.a();
        int a2 = sVar.a();
        for (int i = 0; i < a2; i++) {
            String a3 = sVar.a(i);
            String b = sVar.b(i);
            if ((!"Warning".equalsIgnoreCase(a3) || !b.startsWith("1")) && (b(a3) || !a(a3) || sVar2.a(a3) == null)) {
                okhttp3.internal.a.a.a(aVar, a3, b);
            }
        }
        int a4 = sVar2.a();
        for (int i2 = 0; i2 < a4; i2++) {
            String a5 = sVar2.a(i2);
            if (!b(a5) && a(a5)) {
                okhttp3.internal.a.a.a(aVar, a5, sVar2.b(i2));
            }
        }
        return aVar.a();
    }

    static boolean a(String str) {
        return !"Connection".equalsIgnoreCase(str) && !"Keep-Alive".equalsIgnoreCase(str) && !"Proxy-Authenticate".equalsIgnoreCase(str) && !"Proxy-Authorization".equalsIgnoreCase(str) && !"TE".equalsIgnoreCase(str) && !"Trailers".equalsIgnoreCase(str) && !"Transfer-Encoding".equalsIgnoreCase(str) && !"Upgrade".equalsIgnoreCase(str);
    }

    static boolean b(String str) {
        return "Content-Length".equalsIgnoreCase(str) || BasicNetwork.HEADER_CONTENT_ENCODING.equalsIgnoreCase(str) || MIME.CONTENT_TYPE.equalsIgnoreCase(str);
    }
}
