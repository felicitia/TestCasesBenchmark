package org.scribe.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.http.entity.mime.MIME;
import org.scribe.exceptions.OAuthConnectionException;
import org.scribe.exceptions.OAuthException;

/* compiled from: Request */
public class f {
    private static g a = new g() {
        public void a(f fVar) {
        }
    };
    private String b;
    private Verb c;
    private e d;
    private e e;
    private Map<String, String> f;
    private String g = null;
    private HttpURLConnection h;
    private String i;
    private byte[] j = null;
    private boolean k = false;
    private Long l = null;
    private Long m = null;

    public f(Verb verb, String str) {
        this.c = verb;
        this.b = str;
        this.d = new e();
        this.e = new e();
        this.f = new HashMap();
    }

    public h a(g gVar) {
        try {
            a();
            return b(gVar);
        } catch (Exception e2) {
            throw new OAuthConnectionException(e2);
        }
    }

    public h b() {
        return a(a);
    }

    private void a() throws IOException {
        String c2 = c();
        if (this.h == null) {
            System.setProperty("http.keepAlive", this.k ? "true" : "false");
            this.h = (HttpURLConnection) new URL(c2).openConnection();
        }
    }

    public String c() {
        return this.d.a(this.b);
    }

    /* access modifiers changed from: 0000 */
    public h b(g gVar) throws IOException {
        this.h.setRequestMethod(this.c.name());
        if (this.l != null) {
            this.h.setConnectTimeout(this.l.intValue());
        }
        if (this.m != null) {
            this.h.setReadTimeout(this.m.intValue());
        }
        a(this.h);
        if (this.c.equals(Verb.PUT) || this.c.equals(Verb.POST)) {
            a(this.h, h());
        }
        gVar.a(this);
        return new h(this.h);
    }

    /* access modifiers changed from: 0000 */
    public void a(HttpURLConnection httpURLConnection) {
        for (String str : this.f.keySet()) {
            httpURLConnection.setRequestProperty(str, (String) this.f.get(str));
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(HttpURLConnection httpURLConnection, byte[] bArr) throws IOException {
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(bArr.length));
        if (httpURLConnection.getRequestProperty(MIME.CONTENT_TYPE) == null) {
            httpURLConnection.setRequestProperty(MIME.CONTENT_TYPE, "application/x-www-form-urlencoded");
        }
        httpURLConnection.setDoOutput(true);
        httpURLConnection.getOutputStream().write(bArr);
    }

    public void b(String str, String str2) {
        this.f.put(str, str2);
    }

    public void c(String str, String str2) {
        this.e.a(str, str2);
    }

    public void d(String str, String str2) {
        this.d.a(str, str2);
    }

    public e d() {
        try {
            e eVar = new e();
            eVar.b(new URL(this.b).getQuery());
            eVar.a(this.d);
            return eVar;
        } catch (MalformedURLException e2) {
            throw new OAuthException("Malformed URL", e2);
        }
    }

    public e e() {
        return this.e;
    }

    public String f() {
        return this.b;
    }

    public String g() {
        return this.b.replaceAll("\\?.*", "").replace("\\:\\d{4}", "");
    }

    /* access modifiers changed from: 0000 */
    public byte[] h() {
        if (this.j != null) {
            return this.j;
        }
        try {
            return (this.g != null ? this.g : this.e.b()).getBytes(j());
        } catch (UnsupportedEncodingException e2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unsupported Charset: ");
            sb.append(j());
            throw new OAuthException(sb.toString(), e2);
        }
    }

    public Verb i() {
        return this.c;
    }

    public String j() {
        return this.i == null ? Charset.defaultCharset().name() : this.i;
    }

    public void a(int i2, TimeUnit timeUnit) {
        this.l = Long.valueOf(timeUnit.toMillis((long) i2));
    }

    public String toString() {
        return String.format("@Request(%s %s)", new Object[]{i(), f()});
    }
}
