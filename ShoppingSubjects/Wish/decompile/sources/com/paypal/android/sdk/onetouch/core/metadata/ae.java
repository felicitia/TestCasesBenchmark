package com.paypal.android.sdk.onetouch.core.metadata;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public final class ae extends SSLSocketFactory {
    private SSLSocketFactory a;

    public ae() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, null, null);
            this.a = instance.getSocketFactory();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new SSLException(e.getMessage());
        }
    }

    public ae(InputStream inputStream) {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            for (Certificate certificate : CertificateFactory.getInstance("X.509").generateCertificates(inputStream)) {
                if (certificate instanceof X509Certificate) {
                    instance.setCertificateEntry(((X509Certificate) certificate).getSubjectDN().getName(), certificate);
                }
            }
            TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance2.init(instance);
            SSLContext instance3 = SSLContext.getInstance("TLS");
            instance3.init(null, instance2.getTrustManagers(), null);
            this.a = instance3.getSocketFactory();
            try {
                inputStream.close();
            } catch (IOException | NullPointerException unused) {
            }
        } catch (Exception e) {
            throw new SSLException(e.getMessage());
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException | NullPointerException unused2) {
            }
            throw th;
        }
    }

    private static Socket a(Socket socket) {
        if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            ArrayList arrayList = new ArrayList(Arrays.asList(sSLSocket.getSupportedProtocols()));
            arrayList.retainAll(Arrays.asList(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1"}));
            sSLSocket.setEnabledProtocols((String[]) arrayList.toArray(new String[arrayList.size()]));
        }
        return socket;
    }

    public final Socket createSocket(String str, int i) {
        return a(this.a.createSocket(str, i));
    }

    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return a(this.a.createSocket(str, i, inetAddress, i2));
    }

    public final Socket createSocket(InetAddress inetAddress, int i) {
        return a(this.a.createSocket(inetAddress, i));
    }

    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return a(this.a.createSocket(inetAddress, i, inetAddress2, i2));
    }

    public final Socket createSocket(Socket socket, String str, int i, boolean z) {
        return a(this.a.createSocket(socket, str, i, z));
    }

    public final String[] getDefaultCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }

    public final String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }
}
