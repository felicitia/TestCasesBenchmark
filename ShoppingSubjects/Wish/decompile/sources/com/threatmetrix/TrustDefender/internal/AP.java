package com.threatmetrix.TrustDefender.internal;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

class AP extends SSLSocketFactory {

    /* renamed from: int reason: not valid java name */
    private static final String f49int = TL.m331if(AP.class);
    private SSLSocketFactory delegate;

    public AP() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, null, null);
            this.delegate = instance.getSocketFactory();
        } catch (GeneralSecurityException unused) {
        }
    }

    public String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return m12int(this.delegate.createSocket(socket, str, i, z));
    }

    public Socket createSocket(String str, int i) throws IOException {
        return m12int(this.delegate.createSocket(str, i));
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return m12int(this.delegate.createSocket(str, i, inetAddress, i2));
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return m12int(this.delegate.createSocket(inetAddress, i));
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return m12int(this.delegate.createSocket(inetAddress, i, inetAddress2, i2));
    }

    /* renamed from: int reason: not valid java name */
    private static Socket m12int(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            try {
                ((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1.2"});
            } catch (IllegalArgumentException unused) {
                return socket;
            }
        }
        return socket;
    }
}
