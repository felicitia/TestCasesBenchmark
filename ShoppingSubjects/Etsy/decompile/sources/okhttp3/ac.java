package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: Route */
public final class ac {
    final a a;
    final Proxy b;
    final InetSocketAddress c;

    public ac(a aVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (aVar == null) {
            throw new NullPointerException("address == null");
        } else if (proxy == null) {
            throw new NullPointerException("proxy == null");
        } else if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        } else {
            this.a = aVar;
            this.b = proxy;
            this.c = inetSocketAddress;
        }
    }

    public a a() {
        return this.a;
    }

    public Proxy b() {
        return this.b;
    }

    public InetSocketAddress c() {
        return this.c;
    }

    public boolean d() {
        return this.a.i != null && this.b.type() == Type.HTTP;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ac) {
            ac acVar = (ac) obj;
            if (acVar.a.equals(this.a) && acVar.b.equals(this.b) && acVar.c.equals(this.c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (31 * (((527 + this.a.hashCode()) * 31) + this.b.hashCode())) + this.c.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Route{");
        sb.append(this.c);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
