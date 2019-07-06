package okhttp3;

import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import okhttp3.internal.c;

/* compiled from: Handshake */
public final class r {
    private final TlsVersion a;
    private final h b;
    private final List<Certificate> c;
    private final List<Certificate> d;

    private r(TlsVersion tlsVersion, h hVar, List<Certificate> list, List<Certificate> list2) {
        this.a = tlsVersion;
        this.b = hVar;
        this.c = list;
        this.d = list2;
    }

    public static r a(SSLSession sSLSession) {
        Certificate[] certificateArr;
        List list;
        List list2;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        h a2 = h.a(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        TlsVersion forJavaName = TlsVersion.forJavaName(protocol);
        try {
            certificateArr = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException unused) {
            certificateArr = null;
        }
        if (certificateArr != null) {
            list = c.a((T[]) certificateArr);
        } else {
            list = Collections.emptyList();
        }
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        if (localCertificates != null) {
            list2 = c.a((T[]) localCertificates);
        } else {
            list2 = Collections.emptyList();
        }
        return new r(forJavaName, a2, list, list2);
    }

    public h a() {
        return this.b;
    }

    public List<Certificate> b() {
        return this.c;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        if (this.a.equals(rVar.a) && this.b.equals(rVar.b) && this.c.equals(rVar.c) && this.d.equals(rVar.d)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (31 * (((((527 + this.a.hashCode()) * 31) + this.b.hashCode()) * 31) + this.c.hashCode())) + this.d.hashCode();
    }
}
