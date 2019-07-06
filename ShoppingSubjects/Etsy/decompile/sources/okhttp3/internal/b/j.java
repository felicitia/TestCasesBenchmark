package okhttp3.internal.b;

import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.uikit.adapter.EndlessRecyclerViewAdapter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.HttpUrl;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.ac;
import okhttp3.e;
import okhttp3.g;
import okhttp3.internal.c;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.connection.f;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.p;
import okhttp3.t;
import okhttp3.t.a;
import okhttp3.w;
import okhttp3.y;
import okhttp3.z;
import org.apache.http.entity.mime.MIME;

/* compiled from: RetryAndFollowUpInterceptor */
public final class j implements t {
    private final w a;
    private final boolean b;
    private volatile f c;
    private Object d;
    private volatile boolean e;

    public j(w wVar, boolean z) {
        this.a = wVar;
        this.b = z;
    }

    public void a() {
        this.e = true;
        f fVar = this.c;
        if (fVar != null) {
            fVar.f();
        }
    }

    public boolean b() {
        return this.e;
    }

    public void a(Object obj) {
        this.d = obj;
    }

    public aa a(a aVar) throws IOException {
        y a2 = aVar.a();
        g gVar = (g) aVar;
        e h = gVar.h();
        p i = gVar.i();
        f fVar = new f(this.a.p(), a(a2.a()), h, i, this.d);
        this.c = fVar;
        int i2 = 0;
        aa aaVar = null;
        while (!this.e) {
            try {
                aa a3 = gVar.a(a2, fVar, null, null);
                aa a4 = aaVar != null ? a3.h().c(aaVar.h().a((ab) null).a()).a() : a3;
                y a5 = a(a4, fVar.b());
                if (a5 == null) {
                    if (!this.b) {
                        fVar.d();
                    }
                    return a4;
                }
                c.a((Closeable) a4.g());
                int i3 = i2 + 1;
                if (i3 > 20) {
                    fVar.d();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Too many follow-up requests: ");
                    sb.append(i3);
                    throw new ProtocolException(sb.toString());
                } else if (a5.d() instanceof l) {
                    fVar.d();
                    throw new HttpRetryException("Cannot retry streamed HTTP body", a4.b());
                } else {
                    if (!a(a4, a5.a())) {
                        fVar.d();
                        fVar = new f(this.a.p(), a(a5.a()), h, i, this.d);
                        this.c = fVar;
                    } else if (fVar.a() != null) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Closing the body of ");
                        sb2.append(a4);
                        sb2.append(" didn't close its backing stream. Bad interceptor?");
                        throw new IllegalStateException(sb2.toString());
                    }
                    aaVar = a4;
                    a2 = a5;
                    i2 = i3;
                }
            } catch (RouteException e2) {
                if (!a(e2.getLastConnectException(), fVar, false, a2)) {
                    throw e2.getLastConnectException();
                }
            } catch (IOException e3) {
                if (!a(e3, fVar, !(e3 instanceof ConnectionShutdownException), a2)) {
                    throw e3;
                }
            } catch (Throwable th) {
                fVar.a((IOException) null);
                fVar.d();
                throw th;
            }
        }
        fVar.d();
        throw new IOException("Canceled");
    }

    private okhttp3.a a(HttpUrl httpUrl) {
        g gVar;
        HostnameVerifier hostnameVerifier;
        SSLSocketFactory sSLSocketFactory;
        if (httpUrl.c()) {
            SSLSocketFactory k = this.a.k();
            hostnameVerifier = this.a.l();
            sSLSocketFactory = k;
            gVar = this.a.m();
        } else {
            sSLSocketFactory = null;
            hostnameVerifier = null;
            gVar = null;
        }
        okhttp3.a aVar = new okhttp3.a(httpUrl.f(), httpUrl.g(), this.a.i(), this.a.j(), sSLSocketFactory, hostnameVerifier, gVar, this.a.o(), this.a.e(), this.a.u(), this.a.v(), this.a.f());
        return aVar;
    }

    private boolean a(IOException iOException, f fVar, boolean z, y yVar) {
        fVar.a(iOException);
        if (!this.a.s()) {
            return false;
        }
        if ((!z || !(yVar.d() instanceof l)) && a(iOException, z) && fVar.g()) {
            return true;
        }
        return false;
    }

    private boolean a(IOException iOException, boolean z) {
        boolean z2 = false;
        if (iOException instanceof ProtocolException) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            if ((iOException instanceof SocketTimeoutException) && !z) {
                z2 = true;
            }
            return z2;
        } else if ((!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) && !(iOException instanceof SSLPeerUnverifiedException)) {
            return true;
        } else {
            return false;
        }
    }

    private y a(aa aaVar, ac acVar) throws IOException {
        Proxy proxy;
        if (aaVar == null) {
            throw new IllegalStateException();
        }
        int b2 = aaVar.b();
        String b3 = aaVar.a().b();
        z zVar = null;
        switch (b2) {
            case 300:
            case 301:
            case 302:
            case 303:
                break;
            case 307:
            case 308:
                if (!b3.equals(BaseHttpRequest.GET) && !b3.equals(BaseHttpRequest.HEAD)) {
                    return null;
                }
            case 401:
                return this.a.n().a(acVar, aaVar);
            case 407:
                if (acVar != null) {
                    proxy = acVar.b();
                } else {
                    proxy = this.a.e();
                }
                if (proxy.type() == Type.HTTP) {
                    return this.a.o().a(acVar, aaVar);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            case 408:
                if (!this.a.s() || (aaVar.a().d() instanceof l)) {
                    return null;
                }
                if ((aaVar.i() == null || aaVar.i().b() != 408) && a(aaVar, 0) <= 0) {
                    return aaVar.a();
                }
                return null;
            case EndlessRecyclerViewAdapter.VIEW_TYPE_ENDLESS_LOADING /*503*/:
                if ((aaVar.i() == null || aaVar.i().b() != 503) && a(aaVar, Integer.MAX_VALUE) == 0) {
                    return aaVar.a();
                }
                return null;
            default:
                return null;
        }
        if (!this.a.r()) {
            return null;
        }
        String a2 = aaVar.a(Includes.LOCATION);
        if (a2 == null) {
            return null;
        }
        HttpUrl c2 = aaVar.a().a().c(a2);
        if (c2 == null) {
            return null;
        }
        if (!c2.b().equals(aaVar.a().a().b()) && !this.a.q()) {
            return null;
        }
        y.a e2 = aaVar.a().e();
        if (f.c(b3)) {
            boolean d2 = f.d(b3);
            if (f.e(b3)) {
                e2.a(BaseHttpRequest.GET, (z) null);
            } else {
                if (d2) {
                    zVar = aaVar.a().d();
                }
                e2.a(b3, zVar);
            }
            if (!d2) {
                e2.a("Transfer-Encoding");
                e2.a("Content-Length");
                e2.a(MIME.CONTENT_TYPE);
            }
        }
        if (!a(aaVar, c2)) {
            e2.a("Authorization");
        }
        return e2.a(c2).a();
    }

    private int a(aa aaVar, int i) {
        String a2 = aaVar.a("Retry-After");
        if (a2 == null) {
            return i;
        }
        if (a2.matches("\\d+")) {
            return Integer.valueOf(a2).intValue();
        }
        return Integer.MAX_VALUE;
    }

    private boolean a(aa aaVar, HttpUrl httpUrl) {
        HttpUrl a2 = aaVar.a().a();
        return a2.f().equals(httpUrl.f()) && a2.g() == httpUrl.g() && a2.b().equals(httpUrl.b());
    }
}
