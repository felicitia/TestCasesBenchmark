package okhttp3.internal.connection;

import com.etsy.android.lib.convos.Draft;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.HttpUrl;
import okhttp3.ac;
import okhttp3.internal.c;
import okhttp3.p;

/* compiled from: RouteSelector */
public final class e {
    private final okhttp3.a a;
    private final d b;
    private final okhttp3.e c;
    private final p d;
    private List<Proxy> e = Collections.emptyList();
    private int f;
    private List<InetSocketAddress> g = Collections.emptyList();
    private final List<ac> h = new ArrayList();

    /* compiled from: RouteSelector */
    public static final class a {
        private final List<ac> a;
        private int b = 0;

        a(List<ac> list) {
            this.a = list;
        }

        public boolean a() {
            return this.b < this.a.size();
        }

        public ac b() {
            if (!a()) {
                throw new NoSuchElementException();
            }
            List<ac> list = this.a;
            int i = this.b;
            this.b = i + 1;
            return (ac) list.get(i);
        }

        public List<ac> c() {
            return new ArrayList(this.a);
        }
    }

    public e(okhttp3.a aVar, d dVar, okhttp3.e eVar, p pVar) {
        this.a = aVar;
        this.b = dVar;
        this.c = eVar;
        this.d = pVar;
        a(aVar.a(), aVar.h());
    }

    public boolean a() {
        return c() || !this.h.isEmpty();
    }

    public a b() throws IOException {
        if (!a()) {
            throw new NoSuchElementException();
        }
        ArrayList arrayList = new ArrayList();
        while (c()) {
            Proxy d2 = d();
            int size = this.g.size();
            for (int i = 0; i < size; i++) {
                ac acVar = new ac(this.a, d2, (InetSocketAddress) this.g.get(i));
                if (this.b.c(acVar)) {
                    this.h.add(acVar);
                } else {
                    arrayList.add(acVar);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.addAll(this.h);
            this.h.clear();
        }
        return new a(arrayList);
    }

    public void a(ac acVar, IOException iOException) {
        if (!(acVar.b().type() == Type.DIRECT || this.a.g() == null)) {
            this.a.g().connectFailed(this.a.a().a(), acVar.b().address(), iOException);
        }
        this.b.a(acVar);
    }

    private void a(HttpUrl httpUrl, Proxy proxy) {
        List<Proxy> list;
        if (proxy != null) {
            this.e = Collections.singletonList(proxy);
        } else {
            List select = this.a.g().select(httpUrl.a());
            if (select == null || select.isEmpty()) {
                list = c.a((T[]) new Proxy[]{Proxy.NO_PROXY});
            } else {
                list = c.a(select);
            }
            this.e = list;
        }
        this.f = 0;
    }

    private boolean c() {
        return this.f < this.e.size();
    }

    private Proxy d() throws IOException {
        if (!c()) {
            StringBuilder sb = new StringBuilder();
            sb.append("No route to ");
            sb.append(this.a.a().f());
            sb.append("; exhausted proxy configurations: ");
            sb.append(this.e);
            throw new SocketException(sb.toString());
        }
        List<Proxy> list = this.e;
        int i = this.f;
        this.f = i + 1;
        Proxy proxy = (Proxy) list.get(i);
        a(proxy);
        return proxy;
    }

    private void a(Proxy proxy) throws IOException {
        String str;
        int i;
        this.g = new ArrayList();
        if (proxy.type() == Type.DIRECT || proxy.type() == Type.SOCKS) {
            str = this.a.a().f();
            i = this.a.a().g();
        } else {
            SocketAddress address = proxy.address();
            if (!(address instanceof InetSocketAddress)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Proxy.address() is not an InetSocketAddress: ");
                sb.append(address.getClass());
                throw new IllegalArgumentException(sb.toString());
            }
            InetSocketAddress inetSocketAddress = (InetSocketAddress) address;
            str = a(inetSocketAddress);
            i = inetSocketAddress.getPort();
        }
        if (i < 1 || i > 65535) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("No route to ");
            sb2.append(str);
            sb2.append(Draft.IMAGE_DELIMITER);
            sb2.append(i);
            sb2.append("; port is out of range");
            throw new SocketException(sb2.toString());
        } else if (proxy.type() == Type.SOCKS) {
            this.g.add(InetSocketAddress.createUnresolved(str, i));
        } else {
            this.d.a(this.c, str);
            List a2 = this.a.b().a(str);
            if (a2.isEmpty()) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(this.a.b());
                sb3.append(" returned no addresses for ");
                sb3.append(str);
                throw new UnknownHostException(sb3.toString());
            }
            this.d.a(this.c, str, a2);
            int size = a2.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.g.add(new InetSocketAddress((InetAddress) a2.get(i2), i));
            }
        }
    }

    static String a(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        if (address == null) {
            return inetSocketAddress.getHostName();
        }
        return address.getHostAddress();
    }
}
