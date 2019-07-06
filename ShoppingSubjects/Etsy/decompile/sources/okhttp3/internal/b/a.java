package okhttp3.internal.b;

import com.android.volley.toolbox.BasicNetwork;
import java.io.IOException;
import java.util.List;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.internal.c;
import okhttp3.internal.d;
import okhttp3.l;
import okhttp3.m;
import okhttp3.t;
import okhttp3.u;
import okhttp3.y;
import okhttp3.z;
import okio.k;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.http.entity.mime.MIME;

/* compiled from: BridgeInterceptor */
public final class a implements t {
    private final m a;

    public a(m mVar) {
        this.a = mVar;
    }

    public aa a(okhttp3.t.a aVar) throws IOException {
        y a2 = aVar.a();
        okhttp3.y.a e = a2.e();
        z d = a2.d();
        if (d != null) {
            u a3 = d.a();
            if (a3 != null) {
                e.a(MIME.CONTENT_TYPE, a3.toString());
            }
            long b = d.b();
            if (b != -1) {
                e.a("Content-Length", Long.toString(b));
                e.a("Transfer-Encoding");
            } else {
                e.a("Transfer-Encoding", "chunked");
                e.a("Content-Length");
            }
        }
        boolean z = false;
        if (a2.a("Host") == null) {
            e.a("Host", c.a(a2.a(), false));
        }
        if (a2.a("Connection") == null) {
            e.a("Connection", "Keep-Alive");
        }
        if (a2.a("Accept-Encoding") == null && a2.a("Range") == null) {
            z = true;
            e.a("Accept-Encoding", BasicNetwork.ENCODING_GZIP);
        }
        List a4 = this.a.a(a2.a());
        if (!a4.isEmpty()) {
            e.a("Cookie", a(a4));
        }
        if (a2.a("User-Agent") == null) {
            e.a("User-Agent", d.a());
        }
        aa a5 = aVar.a(e.a());
        e.a(this.a, a2.a(), a5.f());
        okhttp3.aa.a a6 = a5.h().a(a2);
        if (z && BasicNetwork.ENCODING_GZIP.equalsIgnoreCase(a5.a(BasicNetwork.HEADER_CONTENT_ENCODING)) && e.b(a5)) {
            k kVar = new k(a5.g().c());
            a6.a(a5.f().c().b(BasicNetwork.HEADER_CONTENT_ENCODING).b("Content-Length").a());
            a6.a((ab) new h(a5.a(MIME.CONTENT_TYPE), -1, okio.m.a((okio.t) kVar)));
        }
        return a6.a();
    }

    private String a(List<l> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(VectorFormat.DEFAULT_SEPARATOR);
            }
            l lVar = (l) list.get(i);
            sb.append(lVar.a());
            sb.append('=');
            sb.append(lVar.b());
        }
        return sb.toString();
    }
}
