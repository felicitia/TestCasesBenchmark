package com.onfido.api.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class OnfidoTLSSocketFactory extends SSLSocketFactory {
    private SSLSocketFactory ssLSocketFactory;

    public OnfidoTLSSocketFactory() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, null, null);
        this.ssLSocketFactory = instance.getSocketFactory();
    }

    public String[] getDefaultCipherSuites() {
        return this.ssLSocketFactory.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.ssLSocketFactory.getSupportedCipherSuites();
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return disableTLS1Dot0(this.ssLSocketFactory.createSocket(socket, str, i, z));
    }

    public Socket createSocket(String str, int i) throws IOException {
        return disableTLS1Dot0(this.ssLSocketFactory.createSocket(str, i));
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return disableTLS1Dot0(this.ssLSocketFactory.createSocket(str, i, inetAddress, i2));
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return disableTLS1Dot0(this.ssLSocketFactory.createSocket(inetAddress, i));
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return disableTLS1Dot0(this.ssLSocketFactory.createSocket(inetAddress, i, inetAddress2, i2));
    }

    private Socket disableTLS1Dot0(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            ((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1.2"});
        }
        return socket;
    }
}
