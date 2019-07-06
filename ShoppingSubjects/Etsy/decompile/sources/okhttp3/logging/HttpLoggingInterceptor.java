package okhttp3.logging;

import com.android.volley.toolbox.BasicNetwork;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.i;
import okhttp3.internal.b.e;
import okhttp3.internal.e.f;
import okhttp3.s;
import okhttp3.t;
import okhttp3.u;
import okhttp3.y;
import okhttp3.z;
import okio.c;
import org.apache.http.entity.mime.MIME;

public final class HttpLoggingInterceptor implements t {
    private static final Charset a = Charset.forName("UTF-8");
    private final a b;
    private volatile Level c;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public interface a {
        public static final a a = new a() {
            public void a(String str) {
                f.c().a(4, str, (Throwable) null);
            }
        };

        void a(String str);
    }

    public HttpLoggingInterceptor() {
        this(a.a);
    }

    public HttpLoggingInterceptor(a aVar) {
        this.c = Level.NONE;
        this.b = aVar;
    }

    public HttpLoggingInterceptor a(Level level) {
        if (level == null) {
            throw new NullPointerException("level == null. Use Level.NONE instead.");
        }
        this.c = level;
        return this;
    }

    public aa a(okhttp3.t.a aVar) throws IOException {
        String str;
        String str2;
        char c2;
        String str3;
        String str4;
        okhttp3.t.a aVar2 = aVar;
        Level level = this.c;
        y a2 = aVar.a();
        if (level == Level.NONE) {
            return aVar2.a(a2);
        }
        boolean z = true;
        boolean z2 = level == Level.BODY;
        boolean z3 = z2 || level == Level.HEADERS;
        z d = a2.d();
        if (d == null) {
            z = false;
        }
        i b2 = aVar.b();
        StringBuilder sb = new StringBuilder();
        sb.append("--> ");
        sb.append(a2.b());
        sb.append(' ');
        sb.append(a2.a());
        if (b2 != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb2.append(b2.a());
            str = sb2.toString();
        } else {
            str = "";
        }
        sb.append(str);
        String sb3 = sb.toString();
        if (!z3 && z) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(sb3);
            sb4.append(" (");
            sb4.append(d.b());
            sb4.append("-byte body)");
            sb3 = sb4.toString();
        }
        this.b.a(sb3);
        if (z3) {
            if (z) {
                if (d.a() != null) {
                    a aVar3 = this.b;
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("Content-Type: ");
                    sb5.append(d.a());
                    aVar3.a(sb5.toString());
                }
                if (d.b() != -1) {
                    a aVar4 = this.b;
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("Content-Length: ");
                    sb6.append(d.b());
                    aVar4.a(sb6.toString());
                }
            }
            s c3 = a2.c();
            int a3 = c3.a();
            for (int i = 0; i < a3; i++) {
                String a4 = c3.a(i);
                if (!MIME.CONTENT_TYPE.equalsIgnoreCase(a4) && !"Content-Length".equalsIgnoreCase(a4)) {
                    a aVar5 = this.b;
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append(a4);
                    sb7.append(": ");
                    sb7.append(c3.b(i));
                    aVar5.a(sb7.toString());
                }
            }
            if (!z2 || !z) {
                a aVar6 = this.b;
                StringBuilder sb8 = new StringBuilder();
                sb8.append("--> END ");
                sb8.append(a2.b());
                aVar6.a(sb8.toString());
            } else if (a(a2.c())) {
                a aVar7 = this.b;
                StringBuilder sb9 = new StringBuilder();
                sb9.append("--> END ");
                sb9.append(a2.b());
                sb9.append(" (encoded body omitted)");
                aVar7.a(sb9.toString());
            } else {
                c cVar = new c();
                d.a(cVar);
                Charset charset = a;
                u a5 = d.a();
                if (a5 != null) {
                    charset = a5.a(a);
                }
                this.b.a("");
                if (a(cVar)) {
                    this.b.a(cVar.a(charset));
                    a aVar8 = this.b;
                    StringBuilder sb10 = new StringBuilder();
                    sb10.append("--> END ");
                    sb10.append(a2.b());
                    sb10.append(" (");
                    sb10.append(d.b());
                    sb10.append("-byte body)");
                    aVar8.a(sb10.toString());
                } else {
                    a aVar9 = this.b;
                    StringBuilder sb11 = new StringBuilder();
                    sb11.append("--> END ");
                    sb11.append(a2.b());
                    sb11.append(" (binary ");
                    sb11.append(d.b());
                    sb11.append("-byte body omitted)");
                    aVar9.a(sb11.toString());
                }
            }
        }
        long nanoTime = System.nanoTime();
        try {
            aa a6 = aVar2.a(a2);
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
            ab g = a6.g();
            long b3 = g.b();
            if (b3 != -1) {
                StringBuilder sb12 = new StringBuilder();
                sb12.append(b3);
                sb12.append("-byte");
                str2 = sb12.toString();
            } else {
                str2 = "unknown-length";
            }
            a aVar10 = this.b;
            StringBuilder sb13 = new StringBuilder();
            sb13.append("<-- ");
            sb13.append(a6.b());
            if (a6.d().isEmpty()) {
                str3 = "";
                c2 = ' ';
            } else {
                StringBuilder sb14 = new StringBuilder();
                c2 = ' ';
                sb14.append(' ');
                sb14.append(a6.d());
                str3 = sb14.toString();
            }
            sb13.append(str3);
            sb13.append(c2);
            sb13.append(a6.a().a());
            sb13.append(" (");
            sb13.append(millis);
            sb13.append("ms");
            if (!z3) {
                StringBuilder sb15 = new StringBuilder();
                sb15.append(", ");
                sb15.append(str2);
                sb15.append(" body");
                str4 = sb15.toString();
            } else {
                str4 = "";
            }
            sb13.append(str4);
            sb13.append(')');
            aVar10.a(sb13.toString());
            if (z3) {
                s f = a6.f();
                int a7 = f.a();
                for (int i2 = 0; i2 < a7; i2++) {
                    a aVar11 = this.b;
                    StringBuilder sb16 = new StringBuilder();
                    sb16.append(f.a(i2));
                    sb16.append(": ");
                    sb16.append(f.b(i2));
                    aVar11.a(sb16.toString());
                }
                if (!z2 || !e.b(a6)) {
                    this.b.a("<-- END HTTP");
                } else if (a(a6.f())) {
                    this.b.a("<-- END HTTP (encoded body omitted)");
                } else {
                    okio.e c4 = g.c();
                    c4.b(Long.MAX_VALUE);
                    c c5 = c4.c();
                    Charset charset2 = a;
                    u a8 = g.a();
                    if (a8 != null) {
                        charset2 = a8.a(a);
                    }
                    if (!a(c5)) {
                        this.b.a("");
                        a aVar12 = this.b;
                        StringBuilder sb17 = new StringBuilder();
                        sb17.append("<-- END HTTP (binary ");
                        sb17.append(c5.b());
                        sb17.append("-byte body omitted)");
                        aVar12.a(sb17.toString());
                        return a6;
                    }
                    if (b3 != 0) {
                        this.b.a("");
                        this.b.a(c5.clone().a(charset2));
                    }
                    a aVar13 = this.b;
                    StringBuilder sb18 = new StringBuilder();
                    sb18.append("<-- END HTTP (");
                    sb18.append(c5.b());
                    sb18.append("-byte body)");
                    aVar13.a(sb18.toString());
                }
            }
            return a6;
        } catch (Exception e) {
            a aVar14 = this.b;
            StringBuilder sb19 = new StringBuilder();
            sb19.append("<-- HTTP FAILED: ");
            sb19.append(e);
            aVar14.a(sb19.toString());
            throw e;
        }
    }

    static boolean a(c cVar) {
        try {
            c cVar2 = new c();
            cVar.a(cVar2, 0, cVar.b() < 64 ? cVar.b() : 64);
            int i = 0;
            while (true) {
                if (i >= 16) {
                    break;
                } else if (cVar2.f()) {
                    break;
                } else {
                    int r = cVar2.r();
                    if (Character.isISOControl(r) && !Character.isWhitespace(r)) {
                        return false;
                    }
                    i++;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    private boolean a(s sVar) {
        String a2 = sVar.a(BasicNetwork.HEADER_CONTENT_ENCODING);
        return a2 != null && !a2.equalsIgnoreCase("identity");
    }
}
