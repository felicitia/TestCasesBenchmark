package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;
import okhttp3.internal.a;
import okhttp3.k;

/* compiled from: ConnectionSpecSelector */
public final class b {
    private final List<k> a;
    private int b = 0;
    private boolean c;
    private boolean d;

    public b(List<k> list) {
        this.a = list;
    }

    public k a(SSLSocket sSLSocket) throws IOException {
        k kVar;
        int i = this.b;
        int size = this.a.size();
        while (true) {
            if (i >= size) {
                kVar = null;
                break;
            }
            kVar = (k) this.a.get(i);
            if (kVar.a(sSLSocket)) {
                this.b = i + 1;
                break;
            }
            i++;
        }
        if (kVar == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to find acceptable protocols. isFallback=");
            sb.append(this.d);
            sb.append(", modes=");
            sb.append(this.a);
            sb.append(", supported protocols=");
            sb.append(Arrays.toString(sSLSocket.getEnabledProtocols()));
            throw new UnknownServiceException(sb.toString());
        }
        this.c = b(sSLSocket);
        a.a.a(kVar, sSLSocket, this.d);
        return kVar;
    }

    public boolean a(IOException iOException) {
        boolean z = true;
        this.d = true;
        if (!this.c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        boolean z2 = iOException instanceof SSLHandshakeException;
        if ((z2 && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        if (!z2 && !(iOException instanceof SSLProtocolException)) {
            z = false;
        }
        return z;
    }

    private boolean b(SSLSocket sSLSocket) {
        for (int i = this.b; i < this.a.size(); i++) {
            if (((k) this.a.get(i)).a(sSLSocket)) {
                return true;
            }
        }
        return false;
    }
}
