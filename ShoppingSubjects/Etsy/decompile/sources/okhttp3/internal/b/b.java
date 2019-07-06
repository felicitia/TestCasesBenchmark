package okhttp3.internal.b;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.aa;
import okhttp3.internal.connection.f;
import okhttp3.t;
import okhttp3.y;
import okio.c;
import okio.d;
import okio.g;
import okio.m;
import okio.s;

/* compiled from: CallServerInterceptor */
public final class b implements t {
    private final boolean a;

    /* compiled from: CallServerInterceptor */
    static final class a extends g {
        long a;

        a(s sVar) {
            super(sVar);
        }

        public void a_(c cVar, long j) throws IOException {
            super.a_(cVar, j);
            this.a += j;
        }
    }

    public b(boolean z) {
        this.a = z;
    }

    public aa a(okhttp3.t.a aVar) throws IOException {
        aa aaVar;
        g gVar = (g) aVar;
        c g = gVar.g();
        f f = gVar.f();
        okhttp3.internal.connection.c cVar = (okhttp3.internal.connection.c) gVar.b();
        y a2 = gVar.a();
        long currentTimeMillis = System.currentTimeMillis();
        gVar.i().c(gVar.h());
        g.a(a2);
        gVar.i().a(gVar.h(), a2);
        okhttp3.aa.a aVar2 = null;
        if (f.c(a2.b()) && a2.d() != null) {
            if ("100-continue".equalsIgnoreCase(a2.a("Expect"))) {
                g.a();
                gVar.i().e(gVar.h());
                aVar2 = g.a(true);
            }
            if (aVar2 == null) {
                gVar.i().d(gVar.h());
                a aVar3 = new a(g.a(a2, a2.d().b()));
                d a3 = m.a((s) aVar3);
                a2.d().a(a3);
                a3.close();
                gVar.i().a(gVar.h(), aVar3.a);
            } else if (!cVar.f()) {
                f.e();
            }
        }
        g.b();
        if (aVar2 == null) {
            gVar.i().e(gVar.h());
            aVar2 = g.a(false);
        }
        aa a4 = aVar2.a(a2).a(f.c().e()).a(currentTimeMillis).b(System.currentTimeMillis()).a();
        int b = a4.b();
        if (b == 100) {
            a4 = g.a(false).a(a2).a(f.c().e()).a(currentTimeMillis).b(System.currentTimeMillis()).a();
            b = a4.b();
        }
        gVar.i().a(gVar.h(), a4);
        if (!this.a || b != 101) {
            aaVar = a4.h().a(g.a(a4)).a();
        } else {
            aaVar = a4.h().a(okhttp3.internal.c.c).a();
        }
        if ("close".equalsIgnoreCase(aaVar.a().a("Connection")) || "close".equalsIgnoreCase(aaVar.a("Connection"))) {
            f.e();
        }
        if ((b != 204 && b != 205) || aaVar.g().b() <= 0) {
            return aaVar;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP ");
        sb.append(b);
        sb.append(" had non-zero Content-Length: ");
        sb.append(aaVar.g().b());
        throw new ProtocolException(sb.toString());
    }
}
