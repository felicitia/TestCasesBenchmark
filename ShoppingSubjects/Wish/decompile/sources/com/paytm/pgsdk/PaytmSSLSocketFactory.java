package com.paytm.pgsdk;

import android.content.Context;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class PaytmSSLSocketFactory extends SSLSocketFactory {
    private volatile SSLContext mSSLContext;

    protected PaytmSSLSocketFactory(Context context, PaytmClientCertificate paytmClientCertificate) {
        AnonymousClass1 r0 = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        if (paytmClientCertificate != null) {
            try {
                if (paytmClientCertificate.mFileName != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Reading the certificate ");
                    sb.append(paytmClientCertificate.mFileName);
                    sb.append(".p12");
                    PaytmUtility.debugLog(sb.toString());
                    KeyStore instance = KeyStore.getInstance("pkcs12");
                    instance.load(context.getResources().openRawResource(context.getResources().getIdentifier(paytmClientCertificate.mFileName, "raw", context.getPackageName())), paytmClientCertificate.mPassword.toCharArray());
                    Enumeration aliases = instance.aliases();
                    while (aliases.hasMoreElements()) {
                        PaytmUtility.debugLog(instance.getCertificate(((String) aliases.nextElement()).toString()).toString());
                    }
                    KeyManagerFactory instance2 = KeyManagerFactory.getInstance("X509");
                    instance2.init(instance, paytmClientCertificate.mPassword.toCharArray());
                    this.mSSLContext = SSLContext.getInstance("TLS");
                    this.mSSLContext.init(instance2.getKeyManagers(), new TrustManager[]{r0}, null);
                    PaytmUtility.debugLog("Client certificate attached.");
                    return;
                }
            } catch (Exception e) {
                PaytmUtility.debugLog("Exception while attaching Client certificate.");
                PaytmUtility.printStackTrace(e);
                try {
                    PaytmUtility.debugLog("so, setting only the trust manager");
                    this.mSSLContext = SSLContext.getInstance("TLS");
                    this.mSSLContext.init(null, new TrustManager[]{r0}, null);
                    PaytmUtility.debugLog("set trust manager");
                    return;
                } catch (Exception e2) {
                    PaytmUtility.debugLog("Exception while setting the trust manager");
                    PaytmUtility.printStackTrace(e2);
                    return;
                }
            }
        }
        PaytmUtility.debugLog("Client certificate is not found");
        PaytmUtility.debugLog("so, setting only the trust manager");
        this.mSSLContext = SSLContext.getInstance("TLS");
        this.mSSLContext.init(null, new TrustManager[]{r0}, null);
        PaytmUtility.debugLog("set trust manager");
    }

    public synchronized Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        if (this.mSSLContext != null) {
            return this.mSSLContext.getSocketFactory().createSocket(socket, str, i, z);
        }
        return getDefault().createSocket(str, i);
    }

    public synchronized String[] getDefaultCipherSuites() {
        return null;
    }

    public synchronized String[] getSupportedCipherSuites() {
        return null;
    }

    public synchronized Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        if (this.mSSLContext != null) {
            return this.mSSLContext.getSocketFactory().createSocket(str, i);
        }
        return getDefault().createSocket(str, i);
    }

    public synchronized Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        if (this.mSSLContext != null) {
            return this.mSSLContext.getSocketFactory().createSocket(inetAddress, i);
        }
        return getDefault().createSocket(inetAddress, i);
    }

    public synchronized Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        if (this.mSSLContext != null) {
            return this.mSSLContext.getSocketFactory().createSocket(str, i, inetAddress, i2);
        }
        return getDefault().createSocket(str, i);
    }

    public synchronized Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        if (this.mSSLContext != null) {
            return this.mSSLContext.getSocketFactory().createSocket(inetAddress, i, inetAddress2, i2);
        }
        return getDefault().createSocket(inetAddress, i, inetAddress2, i2);
    }
}
