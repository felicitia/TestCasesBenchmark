package okhttp3;

import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp3.internal.c;

/* compiled from: ConnectionSpec */
public final class k {
    public static final k a = new a(true).a(h).a(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0).a(true).a();
    public static final k b = new a(a).a(TlsVersion.TLS_1_0).a(true).a();
    public static final k c = new a(false).a();
    private static final h[] h = {h.aX, h.bb, h.aY, h.bc, h.bi, h.bh, h.aI, h.aJ, h.ag, h.ah, h.E, h.I, h.i};
    final boolean d;
    final boolean e;
    final String[] f;
    final String[] g;

    /* compiled from: ConnectionSpec */
    public static final class a {
        boolean a;
        String[] b;
        String[] c;
        boolean d;

        a(boolean z) {
            this.a = z;
        }

        public a(k kVar) {
            this.a = kVar.d;
            this.b = kVar.f;
            this.c = kVar.g;
            this.d = kVar.e;
        }

        public a a(h... hVarArr) {
            if (!this.a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            String[] strArr = new String[hVarArr.length];
            for (int i = 0; i < hVarArr.length; i++) {
                strArr[i] = hVarArr[i].bj;
            }
            return a(strArr);
        }

        public a a(String... strArr) {
            if (!this.a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            } else if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one cipher suite is required");
            } else {
                this.b = (String[]) strArr.clone();
                return this;
            }
        }

        public a a(TlsVersion... tlsVersionArr) {
            if (!this.a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            String[] strArr = new String[tlsVersionArr.length];
            for (int i = 0; i < tlsVersionArr.length; i++) {
                strArr[i] = tlsVersionArr[i].javaName;
            }
            return b(strArr);
        }

        public a b(String... strArr) {
            if (!this.a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            } else if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one TLS version is required");
            } else {
                this.c = (String[]) strArr.clone();
                return this;
            }
        }

        public a a(boolean z) {
            if (!this.a) {
                throw new IllegalStateException("no TLS extensions for cleartext connections");
            }
            this.d = z;
            return this;
        }

        public k a() {
            return new k(this);
        }
    }

    k(a aVar) {
        this.d = aVar.a;
        this.f = aVar.b;
        this.g = aVar.c;
        this.e = aVar.d;
    }

    public boolean a() {
        return this.d;
    }

    public List<h> b() {
        if (this.f != null) {
            return h.a(this.f);
        }
        return null;
    }

    public List<TlsVersion> c() {
        if (this.g != null) {
            return TlsVersion.forJavaNames(this.g);
        }
        return null;
    }

    public boolean d() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public void a(SSLSocket sSLSocket, boolean z) {
        k b2 = b(sSLSocket, z);
        if (b2.g != null) {
            sSLSocket.setEnabledProtocols(b2.g);
        }
        if (b2.f != null) {
            sSLSocket.setEnabledCipherSuites(b2.f);
        }
    }

    private k b(SSLSocket sSLSocket, boolean z) {
        String[] strArr;
        String[] strArr2;
        if (this.f != null) {
            strArr = c.a(h.a, sSLSocket.getEnabledCipherSuites(), this.f);
        } else {
            strArr = sSLSocket.getEnabledCipherSuites();
        }
        if (this.g != null) {
            strArr2 = c.a(c.h, sSLSocket.getEnabledProtocols(), this.g);
        } else {
            strArr2 = sSLSocket.getEnabledProtocols();
        }
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        int a2 = c.a(h.a, supportedCipherSuites, "TLS_FALLBACK_SCSV");
        if (z && a2 != -1) {
            strArr = c.a(strArr, supportedCipherSuites[a2]);
        }
        return new a(this).a(strArr).b(strArr2).a();
    }

    public boolean a(SSLSocket sSLSocket) {
        if (!this.d) {
            return false;
        }
        if (this.g != null && !c.b(c.h, this.g, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        if (this.f == null || c.b(h.a, this.f, sSLSocket.getEnabledCipherSuites())) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        k kVar = (k) obj;
        if (this.d != kVar.d) {
            return false;
        }
        return !this.d || (Arrays.equals(this.f, kVar.f) && Arrays.equals(this.g, kVar.g) && this.e == kVar.e);
    }

    public int hashCode() {
        if (this.d) {
            return (31 * (((527 + Arrays.hashCode(this.f)) * 31) + Arrays.hashCode(this.g))) + (this.e ^ true ? 1 : 0);
        }
        return 17;
    }

    public String toString() {
        if (!this.d) {
            return "ConnectionSpec()";
        }
        String obj = this.f != null ? b().toString() : "[all enabled]";
        String obj2 = this.g != null ? c().toString() : "[all enabled]";
        StringBuilder sb = new StringBuilder();
        sb.append("ConnectionSpec(cipherSuites=");
        sb.append(obj);
        sb.append(", tlsVersions=");
        sb.append(obj2);
        sb.append(", supportsTlsExtensions=");
        sb.append(this.e);
        sb.append(")");
        return sb.toString();
    }
}
