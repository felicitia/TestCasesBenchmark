package com.crittercism.internal;

import com.crittercism.internal.f.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import javax.net.ssl.SSLSocket;
import org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl;
import org.apache.harmony.xnet.provider.jsse.SSLParametersImpl;

public final class n extends OpenSSLSocketImpl implements u {
    private r a;

    protected n(d dVar, c cVar, SSLParametersImpl sSLParametersImpl) {
        super(sSLParametersImpl);
        this.a = new r(a.HTTPS, dVar, cVar);
    }

    protected n(d dVar, c cVar, String str, int i, SSLParametersImpl sSLParametersImpl) {
        super(str, i, sSLParametersImpl);
        this.a = new r(a.HTTPS, dVar, cVar);
    }

    protected n(d dVar, c cVar, InetAddress inetAddress, int i, SSLParametersImpl sSLParametersImpl) {
        super(inetAddress, i, sSLParametersImpl);
        this.a = new r(a.HTTPS, dVar, cVar);
    }

    protected n(d dVar, c cVar, String str, int i, InetAddress inetAddress, int i2, SSLParametersImpl sSLParametersImpl) {
        super(str, i, inetAddress, i2, sSLParametersImpl);
        this.a = new r(a.HTTPS, dVar, cVar);
    }

    protected n(d dVar, c cVar, InetAddress inetAddress, int i, InetAddress inetAddress2, int i2, SSLParametersImpl sSLParametersImpl) {
        super(inetAddress, i, inetAddress2, i2, sSLParametersImpl);
        this.a = new r(a.HTTPS, dVar, cVar);
    }

    public final b a() {
        return this.a.a(getInetAddress());
    }

    public final void a(b bVar) {
        this.a.a(bVar);
    }

    public final b b() {
        return this.a.b();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [javax.net.ssl.SSLSocket, org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl, com.crittercism.internal.n] */
    public final void startHandshake() {
        try {
            n.super.startHandshake();
        } catch (IOException e) {
            this.a.a(e, (SSLSocket) this);
            throw e;
        }
    }

    public final void close() {
        n.super.close();
        this.a.a();
    }

    public final InputStream getInputStream() {
        return this.a.a((u) this, n.super.getInputStream());
    }

    public final OutputStream getOutputStream() {
        return this.a.a((u) this, n.super.getOutputStream());
    }

    public final synchronized void setSoTimeout(int i) {
        n.super.setSoTimeout(i);
    }

    public final synchronized int getSoTimeout() {
        return n.super.getSoTimeout();
    }
}
