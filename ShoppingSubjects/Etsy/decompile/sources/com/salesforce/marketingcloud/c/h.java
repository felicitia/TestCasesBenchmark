package com.salesforce.marketingcloud.c;

import android.support.annotation.Nullable;
import com.salesforce.marketingcloud.j;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

class h extends SSLSocketFactory {
    private static final String a = j.a(h.class);
    private final SSLSocketFactory b;

    h() {
        SSLContext instance = SSLContext.getInstance("TLSv1.2");
        instance.init(null, null, null);
        this.b = instance.getSocketFactory();
    }

    @Nullable
    private Socket a(Exception exc) {
        if (exc instanceof IOException) {
            throw ((IOException) exc);
        }
        j.c(a, exc, "Failed to patch socket for TLS.", new Object[0]);
        return null;
    }

    private Socket a(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            ((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1.1", "TLSv1.2"});
        }
        return socket;
    }

    public Socket createSocket() {
        try {
            return a(this.b.createSocket());
        } catch (Exception e) {
            return a(e);
        }
    }

    public Socket createSocket(String str, int i) {
        try {
            return a(this.b.createSocket(str, i));
        } catch (Exception e) {
            return a(e);
        }
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        try {
            return a(this.b.createSocket(str, i, inetAddress, i2));
        } catch (Exception e) {
            return a(e);
        }
    }

    public Socket createSocket(InetAddress inetAddress, int i) {
        try {
            return a(this.b.createSocket(inetAddress, i));
        } catch (Exception e) {
            return a(e);
        }
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        try {
            return a(this.b.createSocket(inetAddress, i, inetAddress2, i2));
        } catch (Exception e) {
            return a(e);
        }
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        try {
            return a(this.b.createSocket(socket, str, i, z));
        } catch (Exception e) {
            return a(e);
        }
    }

    public String[] getDefaultCipherSuites() {
        return this.b.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.b.getSupportedCipherSuites();
    }
}
