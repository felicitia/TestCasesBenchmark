package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.internal.a.e;
import okhttp3.internal.c;
import okhttp3.internal.e.f;
import okhttp3.internal.g.d;

/* compiled from: OkHttpClient */
public class w implements Cloneable, okhttp3.e.a {
    static final List<Protocol> a = c.a((T[]) new Protocol[]{Protocol.HTTP_2, Protocol.HTTP_1_1});
    static final List<k> b = c.a((T[]) new k[]{k.a, k.c});
    final int A;
    final int B;
    final int C;
    final n c;
    final Proxy d;
    final List<Protocol> e;
    final List<k> f;
    final List<t> g;
    final List<t> h;
    final okhttp3.p.a i;
    final ProxySelector j;
    final m k;
    final c l;
    final e m;
    final SocketFactory n;
    final SSLSocketFactory o;
    final okhttp3.internal.g.c p;
    final HostnameVerifier q;
    final g r;
    final b s;
    final b t;
    final j u;
    final o v;
    final boolean w;
    final boolean x;
    final boolean y;
    final int z;

    /* compiled from: OkHttpClient */
    public static final class a {
        int A = 0;
        n a = new n();
        Proxy b;
        List<Protocol> c = w.a;
        List<k> d = w.b;
        final List<t> e = new ArrayList();
        final List<t> f = new ArrayList();
        okhttp3.p.a g = p.a(p.a);
        ProxySelector h = ProxySelector.getDefault();
        m i = m.a;
        c j;
        e k;
        SocketFactory l = SocketFactory.getDefault();
        SSLSocketFactory m;
        okhttp3.internal.g.c n;
        HostnameVerifier o = d.a;
        g p = g.a;
        b q = b.a;
        b r = b.a;
        j s = new j();
        o t = o.a;
        boolean u = true;
        boolean v = true;
        boolean w = true;
        int x = 10000;
        int y = 10000;
        int z = 10000;

        public a a(SSLSocketFactory sSLSocketFactory) {
            if (sSLSocketFactory == null) {
                throw new NullPointerException("sslSocketFactory == null");
            }
            this.m = sSLSocketFactory;
            this.n = f.c().b(sSLSocketFactory);
            return this;
        }

        public a a(boolean z2) {
            this.w = z2;
            return this;
        }

        public a a(List<k> list) {
            this.d = c.a(list);
            return this;
        }

        public a a(t tVar) {
            if (tVar == null) {
                throw new IllegalArgumentException("interceptor == null");
            }
            this.e.add(tVar);
            return this;
        }

        public w a() {
            return new w(this);
        }
    }

    static {
        okhttp3.internal.a.a = new okhttp3.internal.a() {
            public void a(okhttp3.s.a aVar, String str) {
                aVar.a(str);
            }

            public void a(okhttp3.s.a aVar, String str, String str2) {
                aVar.b(str, str2);
            }

            public boolean a(j jVar, okhttp3.internal.connection.c cVar) {
                return jVar.b(cVar);
            }

            public okhttp3.internal.connection.c a(j jVar, a aVar, okhttp3.internal.connection.f fVar, ac acVar) {
                return jVar.a(aVar, fVar, acVar);
            }

            public boolean a(a aVar, a aVar2) {
                return aVar.a(aVar2);
            }

            public Socket a(j jVar, a aVar, okhttp3.internal.connection.f fVar) {
                return jVar.a(aVar, fVar);
            }

            public void b(j jVar, okhttp3.internal.connection.c cVar) {
                jVar.a(cVar);
            }

            public okhttp3.internal.connection.d a(j jVar) {
                return jVar.a;
            }

            public int a(okhttp3.aa.a aVar) {
                return aVar.c;
            }

            public void a(k kVar, SSLSocket sSLSocket, boolean z) {
                kVar.a(sSLSocket, z);
            }
        };
    }

    public w() {
        this(new a());
    }

    w(a aVar) {
        boolean z2;
        this.c = aVar.a;
        this.d = aVar.b;
        this.e = aVar.c;
        this.f = aVar.d;
        this.g = c.a(aVar.e);
        this.h = c.a(aVar.f);
        this.i = aVar.g;
        this.j = aVar.h;
        this.k = aVar.i;
        this.l = aVar.j;
        this.m = aVar.k;
        this.n = aVar.l;
        Iterator it = this.f.iterator();
        loop0:
        while (true) {
            z2 = false;
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                }
                k kVar = (k) it.next();
                if (z2 || kVar.a()) {
                    z2 = true;
                }
            }
        }
        if (aVar.m != null || !z2) {
            this.o = aVar.m;
            this.p = aVar.n;
        } else {
            X509TrustManager z3 = z();
            this.o = a(z3);
            this.p = okhttp3.internal.g.c.a(z3);
        }
        this.q = aVar.o;
        this.r = aVar.p.a(this.p);
        this.s = aVar.q;
        this.t = aVar.r;
        this.u = aVar.s;
        this.v = aVar.t;
        this.w = aVar.u;
        this.x = aVar.v;
        this.y = aVar.w;
        this.z = aVar.x;
        this.A = aVar.y;
        this.B = aVar.z;
        this.C = aVar.A;
        if (this.g.contains(null)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Null interceptor: ");
            sb.append(this.g);
            throw new IllegalStateException(sb.toString());
        } else if (this.h.contains(null)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Null network interceptor: ");
            sb2.append(this.h);
            throw new IllegalStateException(sb2.toString());
        }
    }

    private X509TrustManager z() {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init(null);
            TrustManager[] trustManagers = instance.getTrustManagers();
            if (trustManagers.length == 1) {
                if (trustManagers[0] instanceof X509TrustManager) {
                    return (X509TrustManager) trustManagers[0];
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected default trust managers:");
            sb.append(Arrays.toString(trustManagers));
            throw new IllegalStateException(sb.toString());
        } catch (GeneralSecurityException e2) {
            throw c.a("No System TLS", (Exception) e2);
        }
    }

    private SSLSocketFactory a(X509TrustManager x509TrustManager) {
        try {
            SSLContext m_ = f.c().m_();
            m_.init(null, new TrustManager[]{x509TrustManager}, null);
            return m_.getSocketFactory();
        } catch (GeneralSecurityException e2) {
            throw c.a("No System TLS", (Exception) e2);
        }
    }

    public int a() {
        return this.z;
    }

    public int b() {
        return this.A;
    }

    public int c() {
        return this.B;
    }

    public int d() {
        return this.C;
    }

    public Proxy e() {
        return this.d;
    }

    public ProxySelector f() {
        return this.j;
    }

    public m g() {
        return this.k;
    }

    /* access modifiers changed from: 0000 */
    public e h() {
        return this.l != null ? this.l.a : this.m;
    }

    public o i() {
        return this.v;
    }

    public SocketFactory j() {
        return this.n;
    }

    public SSLSocketFactory k() {
        return this.o;
    }

    public HostnameVerifier l() {
        return this.q;
    }

    public g m() {
        return this.r;
    }

    public b n() {
        return this.t;
    }

    public b o() {
        return this.s;
    }

    public j p() {
        return this.u;
    }

    public boolean q() {
        return this.w;
    }

    public boolean r() {
        return this.x;
    }

    public boolean s() {
        return this.y;
    }

    public n t() {
        return this.c;
    }

    public List<Protocol> u() {
        return this.e;
    }

    public List<k> v() {
        return this.f;
    }

    public List<t> w() {
        return this.g;
    }

    public List<t> x() {
        return this.h;
    }

    public okhttp3.p.a y() {
        return this.i;
    }

    public e a(y yVar) {
        return x.a(this, yVar, false);
    }
}
