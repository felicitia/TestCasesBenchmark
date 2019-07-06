package okhttp3.internal.b;

import java.io.IOException;
import java.util.List;
import okhttp3.aa;
import okhttp3.e;
import okhttp3.i;
import okhttp3.internal.connection.c;
import okhttp3.internal.connection.f;
import okhttp3.p;
import okhttp3.t;
import okhttp3.t.a;
import okhttp3.y;

/* compiled from: RealInterceptorChain */
public final class g implements a {
    private final List<t> a;
    private final f b;
    private final c c;
    private final c d;
    private final int e;
    private final y f;
    private final e g;
    private final p h;
    private final int i;
    private final int j;
    private final int k;
    private int l;

    public g(List<t> list, f fVar, c cVar, c cVar2, int i2, y yVar, e eVar, p pVar, int i3, int i4, int i5) {
        this.a = list;
        this.d = cVar2;
        this.b = fVar;
        this.c = cVar;
        this.e = i2;
        this.f = yVar;
        this.g = eVar;
        this.h = pVar;
        this.i = i3;
        this.j = i4;
        this.k = i5;
    }

    public i b() {
        return this.d;
    }

    public int c() {
        return this.i;
    }

    public int d() {
        return this.j;
    }

    public int e() {
        return this.k;
    }

    public f f() {
        return this.b;
    }

    public c g() {
        return this.c;
    }

    public e h() {
        return this.g;
    }

    public p i() {
        return this.h;
    }

    public y a() {
        return this.f;
    }

    public aa a(y yVar) throws IOException {
        return a(yVar, this.b, this.c, this.d);
    }

    public aa a(y yVar, f fVar, c cVar, c cVar2) throws IOException {
        if (this.e >= this.a.size()) {
            throw new AssertionError();
        }
        this.l++;
        if (this.c != null && !this.d.a(yVar.a())) {
            StringBuilder sb = new StringBuilder();
            sb.append("network interceptor ");
            sb.append(this.a.get(this.e - 1));
            sb.append(" must retain the same host and port");
            throw new IllegalStateException(sb.toString());
        } else if (this.c == null || this.l <= 1) {
            g gVar = new g(this.a, fVar, cVar, cVar2, this.e + 1, yVar, this.g, this.h, this.i, this.j, this.k);
            t tVar = (t) this.a.get(this.e);
            aa a2 = tVar.a(gVar);
            if (cVar != null && this.e + 1 < this.a.size() && gVar.l != 1) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("network interceptor ");
                sb2.append(tVar);
                sb2.append(" must call proceed() exactly once");
                throw new IllegalStateException(sb2.toString());
            } else if (a2 == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("interceptor ");
                sb3.append(tVar);
                sb3.append(" returned null");
                throw new NullPointerException(sb3.toString());
            } else if (a2.g() != null) {
                return a2;
            } else {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("interceptor ");
                sb4.append(tVar);
                sb4.append(" returned a response with no body");
                throw new IllegalStateException(sb4.toString());
            }
        } else {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("network interceptor ");
            sb5.append(this.a.get(this.e - 1));
            sb5.append(" must call proceed() exactly once");
            throw new IllegalStateException(sb5.toString());
        }
    }
}
