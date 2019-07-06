package okhttp3;

import com.etsy.android.lib.convos.Draft;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.HttpUrl.Builder;
import okhttp3.internal.c;
import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: Address */
public final class a {
    final HttpUrl a;
    final o b;
    final SocketFactory c;
    final b d;
    final List<Protocol> e;
    final List<k> f;
    final ProxySelector g;
    final Proxy h;
    final SSLSocketFactory i;
    final HostnameVerifier j;
    final g k;

    public a(String str, int i2, o oVar, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, g gVar, b bVar, Proxy proxy, List<Protocol> list, List<k> list2, ProxySelector proxySelector) {
        this.a = new Builder().a(sSLSocketFactory != null ? "https" : "http").d(str).a(i2).c();
        if (oVar == null) {
            throw new NullPointerException("dns == null");
        }
        this.b = oVar;
        if (socketFactory == null) {
            throw new NullPointerException("socketFactory == null");
        }
        this.c = socketFactory;
        if (bVar == null) {
            throw new NullPointerException("proxyAuthenticator == null");
        }
        this.d = bVar;
        if (list == null) {
            throw new NullPointerException("protocols == null");
        }
        this.e = c.a(list);
        if (list2 == null) {
            throw new NullPointerException("connectionSpecs == null");
        }
        this.f = c.a(list2);
        if (proxySelector == null) {
            throw new NullPointerException("proxySelector == null");
        }
        this.g = proxySelector;
        this.h = proxy;
        this.i = sSLSocketFactory;
        this.j = hostnameVerifier;
        this.k = gVar;
    }

    public HttpUrl a() {
        return this.a;
    }

    public o b() {
        return this.b;
    }

    public SocketFactory c() {
        return this.c;
    }

    public b d() {
        return this.d;
    }

    public List<Protocol> e() {
        return this.e;
    }

    public List<k> f() {
        return this.f;
    }

    public ProxySelector g() {
        return this.g;
    }

    public Proxy h() {
        return this.h;
    }

    public SSLSocketFactory i() {
        return this.i;
    }

    public HostnameVerifier j() {
        return this.j;
    }

    public g k() {
        return this.k;
    }

    public boolean equals(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (this.a.equals(aVar.a) && a(aVar)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i2 = 0;
        int hashCode = 31 * (((((((((((((((((527 + this.a.hashCode()) * 31) + this.b.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + (this.h != null ? this.h.hashCode() : 0)) * 31) + (this.i != null ? this.i.hashCode() : 0)) * 31) + (this.j != null ? this.j.hashCode() : 0));
        if (this.k != null) {
            i2 = this.k.hashCode();
        }
        return hashCode + i2;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(a aVar) {
        return this.b.equals(aVar.b) && this.d.equals(aVar.d) && this.e.equals(aVar.e) && this.f.equals(aVar.f) && this.g.equals(aVar.g) && c.a((Object) this.h, (Object) aVar.h) && c.a((Object) this.i, (Object) aVar.i) && c.a((Object) this.j, (Object) aVar.j) && c.a((Object) this.k, (Object) aVar.k) && a().g() == aVar.a().g();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address{");
        sb.append(this.a.f());
        sb.append(Draft.IMAGE_DELIMITER);
        sb.append(this.a.g());
        if (this.h != null) {
            sb.append(", proxy=");
            sb.append(this.h);
        } else {
            sb.append(", proxySelector=");
            sb.append(this.g);
        }
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
