package retrofit2;

import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.s;
import okhttp3.u;
import okhttp3.v;
import okhttp3.v.b;
import okhttp3.y;
import okhttp3.z;
import okio.c;
import okio.d;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.http.entity.mime.MIME;

/* compiled from: RequestBuilder */
final class k {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final String b;
    private final HttpUrl c;
    private String d;
    private Builder e;
    private final okhttp3.y.a f = new okhttp3.y.a();
    private u g;
    private final boolean h;
    private okhttp3.v.a i;
    private okhttp3.q.a j;
    private z k;

    /* compiled from: RequestBuilder */
    private static class a extends z {
        private final z a;
        private final u b;

        a(z zVar, u uVar) {
            this.a = zVar;
            this.b = uVar;
        }

        public u a() {
            return this.b;
        }

        public long b() throws IOException {
            return this.a.b();
        }

        public void a(d dVar) throws IOException {
            this.a.a(dVar);
        }
    }

    k(String str, HttpUrl httpUrl, String str2, s sVar, u uVar, boolean z, boolean z2, boolean z3) {
        this.b = str;
        this.c = httpUrl;
        this.d = str2;
        this.g = uVar;
        this.h = z;
        if (sVar != null) {
            this.f.a(sVar);
        }
        if (z2) {
            this.j = new okhttp3.q.a();
        } else if (z3) {
            this.i = new okhttp3.v.a();
            this.i.a(v.e);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Object obj) {
        this.d = obj.toString();
    }

    /* access modifiers changed from: 0000 */
    public void a(String str, String str2) {
        if (MIME.CONTENT_TYPE.equalsIgnoreCase(str)) {
            u a2 = u.a(str2);
            if (a2 == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Malformed content type: ");
                sb.append(str2);
                throw new IllegalArgumentException(sb.toString());
            }
            this.g = a2;
            return;
        }
        this.f.b(str, str2);
    }

    /* access modifiers changed from: 0000 */
    public void a(String str, String str2, boolean z) {
        if (this.d == null) {
            throw new AssertionError();
        }
        String str3 = this.d;
        StringBuilder sb = new StringBuilder();
        sb.append(VectorFormat.DEFAULT_PREFIX);
        sb.append(str);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        this.d = str3.replace(sb.toString(), a(str2, z));
    }

    private static String a(String str, boolean z) {
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            int codePointAt = str.codePointAt(i2);
            if (codePointAt < 32 || codePointAt >= 127 || " \"<>^`{}|\\?#".indexOf(codePointAt) != -1 || (!z && (codePointAt == 47 || codePointAt == 37))) {
                c cVar = new c();
                cVar.b(str, 0, i2);
                a(cVar, str, i2, length, z);
                return cVar.p();
            }
            i2 += Character.charCount(codePointAt);
        }
        return str;
    }

    private static void a(c cVar, String str, int i2, int i3, boolean z) {
        c cVar2 = null;
        while (i2 < i3) {
            int codePointAt = str.codePointAt(i2);
            if (!z || !(codePointAt == 9 || codePointAt == 10 || codePointAt == 12 || codePointAt == 13)) {
                if (codePointAt < 32 || codePointAt >= 127 || " \"<>^`{}|\\?#".indexOf(codePointAt) != -1 || (!z && (codePointAt == 47 || codePointAt == 37))) {
                    if (cVar2 == null) {
                        cVar2 = new c();
                    }
                    cVar2.a(codePointAt);
                    while (!cVar2.f()) {
                        byte i4 = cVar2.i() & 255;
                        cVar.k(37);
                        cVar.k((int) a[(i4 >> 4) & 15]);
                        cVar.k((int) a[i4 & 15]);
                    }
                } else {
                    cVar.a(codePointAt);
                }
            }
            i2 += Character.charCount(codePointAt);
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(String str, String str2, boolean z) {
        if (this.d != null) {
            this.e = this.c.d(this.d);
            if (this.e == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Malformed URL. Base: ");
                sb.append(this.c);
                sb.append(", Relative: ");
                sb.append(this.d);
                throw new IllegalArgumentException(sb.toString());
            }
            this.d = null;
        }
        if (z) {
            this.e.b(str, str2);
        } else {
            this.e.a(str, str2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void c(String str, String str2, boolean z) {
        if (z) {
            this.j.b(str, str2);
        } else {
            this.j.a(str, str2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(s sVar, z zVar) {
        this.i.a(sVar, zVar);
    }

    /* access modifiers changed from: 0000 */
    public void a(b bVar) {
        this.i.a(bVar);
    }

    /* access modifiers changed from: 0000 */
    public void a(z zVar) {
        this.k = zVar;
    }

    /* access modifiers changed from: 0000 */
    public y a() {
        HttpUrl httpUrl;
        Builder builder = this.e;
        if (builder != null) {
            httpUrl = builder.c();
        } else {
            httpUrl = this.c.c(this.d);
            if (httpUrl == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Malformed URL. Base: ");
                sb.append(this.c);
                sb.append(", Relative: ");
                sb.append(this.d);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        z zVar = this.k;
        if (zVar == null) {
            if (this.j != null) {
                zVar = this.j.a();
            } else if (this.i != null) {
                zVar = this.i.a();
            } else if (this.h) {
                zVar = z.a((u) null, new byte[0]);
            }
        }
        u uVar = this.g;
        if (uVar != null) {
            if (zVar != null) {
                zVar = new a(zVar, uVar);
            } else {
                this.f.b(MIME.CONTENT_TYPE, uVar.toString());
            }
        }
        return this.f.a(httpUrl).a(this.b, zVar).a();
    }
}
