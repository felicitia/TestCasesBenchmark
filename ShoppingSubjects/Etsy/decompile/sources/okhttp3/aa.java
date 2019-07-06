package okhttp3;

import java.io.Closeable;

/* compiled from: Response */
public final class aa implements Closeable {
    final y a;
    final Protocol b;
    final int c;
    final String d;
    final r e;
    final s f;
    final ab g;
    final aa h;
    final aa i;
    final aa j;
    final long k;
    final long l;
    private volatile d m;

    /* compiled from: Response */
    public static class a {
        y a;
        Protocol b;
        int c;
        String d;
        r e;
        okhttp3.s.a f;
        ab g;
        aa h;
        aa i;
        aa j;
        long k;
        long l;

        public a() {
            this.c = -1;
            this.f = new okhttp3.s.a();
        }

        a(aa aaVar) {
            this.c = -1;
            this.a = aaVar.a;
            this.b = aaVar.b;
            this.c = aaVar.c;
            this.d = aaVar.d;
            this.e = aaVar.e;
            this.f = aaVar.f.c();
            this.g = aaVar.g;
            this.h = aaVar.h;
            this.i = aaVar.i;
            this.j = aaVar.j;
            this.k = aaVar.k;
            this.l = aaVar.l;
        }

        public a a(y yVar) {
            this.a = yVar;
            return this;
        }

        public a a(Protocol protocol) {
            this.b = protocol;
            return this;
        }

        public a a(int i2) {
            this.c = i2;
            return this;
        }

        public a a(String str) {
            this.d = str;
            return this;
        }

        public a a(r rVar) {
            this.e = rVar;
            return this;
        }

        public a a(String str, String str2) {
            this.f.a(str, str2);
            return this;
        }

        public a a(s sVar) {
            this.f = sVar.c();
            return this;
        }

        public a a(ab abVar) {
            this.g = abVar;
            return this;
        }

        public a a(aa aaVar) {
            if (aaVar != null) {
                a("networkResponse", aaVar);
            }
            this.h = aaVar;
            return this;
        }

        public a b(aa aaVar) {
            if (aaVar != null) {
                a("cacheResponse", aaVar);
            }
            this.i = aaVar;
            return this;
        }

        private void a(String str, aa aaVar) {
            if (aaVar.g != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(".body != null");
                throw new IllegalArgumentException(sb.toString());
            } else if (aaVar.h != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(".networkResponse != null");
                throw new IllegalArgumentException(sb2.toString());
            } else if (aaVar.i != null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(".cacheResponse != null");
                throw new IllegalArgumentException(sb3.toString());
            } else if (aaVar.j != null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(".priorResponse != null");
                throw new IllegalArgumentException(sb4.toString());
            }
        }

        public a c(aa aaVar) {
            if (aaVar != null) {
                d(aaVar);
            }
            this.j = aaVar;
            return this;
        }

        private void d(aa aaVar) {
            if (aaVar.g != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        public a a(long j2) {
            this.k = j2;
            return this;
        }

        public a b(long j2) {
            this.l = j2;
            return this;
        }

        public aa a() {
            if (this.a == null) {
                throw new IllegalStateException("request == null");
            } else if (this.b == null) {
                throw new IllegalStateException("protocol == null");
            } else if (this.c < 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("code < 0: ");
                sb.append(this.c);
                throw new IllegalStateException(sb.toString());
            } else if (this.d != null) {
                return new aa(this);
            } else {
                throw new IllegalStateException("message == null");
            }
        }
    }

    aa(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f.a();
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        this.l = aVar.l;
    }

    public y a() {
        return this.a;
    }

    public int b() {
        return this.c;
    }

    public boolean c() {
        return this.c >= 200 && this.c < 300;
    }

    public String d() {
        return this.d;
    }

    public r e() {
        return this.e;
    }

    public String a(String str) {
        return a(str, null);
    }

    public String a(String str, String str2) {
        String a2 = this.f.a(str);
        return a2 != null ? a2 : str2;
    }

    public s f() {
        return this.f;
    }

    public ab g() {
        return this.g;
    }

    public a h() {
        return new a(this);
    }

    public aa i() {
        return this.j;
    }

    public d j() {
        d dVar = this.m;
        if (dVar != null) {
            return dVar;
        }
        d a2 = d.a(this.f);
        this.m = a2;
        return a2;
    }

    public long k() {
        return this.k;
    }

    public long l() {
        return this.l;
    }

    public void close() {
        if (this.g == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        this.g.close();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Response{protocol=");
        sb.append(this.b);
        sb.append(", code=");
        sb.append(this.c);
        sb.append(", message=");
        sb.append(this.d);
        sb.append(", url=");
        sb.append(this.a.a());
        sb.append('}');
        return sb.toString();
    }
}
