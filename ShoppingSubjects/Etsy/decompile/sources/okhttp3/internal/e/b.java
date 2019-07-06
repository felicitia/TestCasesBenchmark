package okhttp3.internal.e;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import org.conscrypt.Conscrypt;
import org.conscrypt.OpenSSLProvider;

/* compiled from: ConscryptPlatform */
public class b extends f {
    private b() {
    }

    private Provider e() {
        return new OpenSSLProvider();
    }

    public X509TrustManager a(SSLSocketFactory sSLSocketFactory) {
        if (!Conscrypt.isConscrypt(sSLSocketFactory)) {
            return super.a(sSLSocketFactory);
        }
        try {
            Object a = a((Object) sSLSocketFactory, Object.class, "sslParameters");
            if (a != null) {
                return (X509TrustManager) a(a, X509TrustManager.class, "x509TrustManager");
            }
            return null;
        } catch (Exception e) {
            throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on Conscrypt", e);
        }
    }

    public void a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        if (Conscrypt.isConscrypt(sSLSocket)) {
            if (str != null) {
                Conscrypt.setUseSessionTickets(sSLSocket, true);
                Conscrypt.setHostname(sSLSocket, str);
            }
            Conscrypt.setApplicationProtocols(sSLSocket, (String[]) f.a(list).toArray(new String[0]));
            return;
        }
        super.a(sSLSocket, str, list);
    }

    public String a(SSLSocket sSLSocket) {
        if (Conscrypt.isConscrypt(sSLSocket)) {
            return Conscrypt.getApplicationProtocol(sSLSocket);
        }
        return super.a(sSLSocket);
    }

    public SSLContext m_() {
        try {
            return SSLContext.getInstance("TLS", e());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No TLS provider", e);
        }
    }

    public static f b() {
        try {
            Class.forName("org.conscrypt.ConscryptEngineSocket");
            if (!Conscrypt.isAvailable()) {
                return null;
            }
            Conscrypt.setUseEngineSocketByDefault(true);
            return new b();
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
