package okhttp3;

import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import okhttp3.internal.b.f;

/* compiled from: Request */
public final class y {
    final HttpUrl a;
    final String b;
    final s c;
    final z d;
    final Object e;
    private volatile d f;

    /* compiled from: Request */
    public static class a {
        HttpUrl a;
        String b;
        okhttp3.s.a c;
        z d;
        Object e;

        public a() {
            this.b = BaseHttpRequest.GET;
            this.c = new okhttp3.s.a();
        }

        a(y yVar) {
            this.a = yVar.a;
            this.b = yVar.b;
            this.d = yVar.d;
            this.e = yVar.e;
            this.c = yVar.c.c();
        }

        public a a(HttpUrl httpUrl) {
            if (httpUrl == null) {
                throw new NullPointerException("url == null");
            }
            this.a = httpUrl;
            return this;
        }

        public a a(String str, String str2) {
            this.c.c(str, str2);
            return this;
        }

        public a b(String str, String str2) {
            this.c.a(str, str2);
            return this;
        }

        public a a(String str) {
            this.c.b(str);
            return this;
        }

        public a a(s sVar) {
            this.c = sVar.c();
            return this;
        }

        public a a(String str, z zVar) {
            if (str == null) {
                throw new NullPointerException("method == null");
            } else if (str.length() == 0) {
                throw new IllegalArgumentException("method.length() == 0");
            } else if (zVar != null && !f.c(str)) {
                StringBuilder sb = new StringBuilder();
                sb.append("method ");
                sb.append(str);
                sb.append(" must not have a request body.");
                throw new IllegalArgumentException(sb.toString());
            } else if (zVar != null || !f.b(str)) {
                this.b = str;
                this.d = zVar;
                return this;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("method ");
                sb2.append(str);
                sb2.append(" must have a request body.");
                throw new IllegalArgumentException(sb2.toString());
            }
        }

        public y a() {
            if (this.a != null) {
                return new y(this);
            }
            throw new IllegalStateException("url == null");
        }
    }

    y(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c.a();
        this.d = aVar.d;
        this.e = aVar.e != null ? aVar.e : this;
    }

    public HttpUrl a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public s c() {
        return this.c;
    }

    public String a(String str) {
        return this.c.a(str);
    }

    public z d() {
        return this.d;
    }

    public a e() {
        return new a(this);
    }

    public d f() {
        d dVar = this.f;
        if (dVar != null) {
            return dVar;
        }
        d a2 = d.a(this.c);
        this.f = a2;
        return a2;
    }

    public boolean g() {
        return this.a.c();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request{method=");
        sb.append(this.b);
        sb.append(", url=");
        sb.append(this.a);
        sb.append(", tag=");
        sb.append(this.e != this ? this.e : null);
        sb.append('}');
        return sb.toString();
    }
}
