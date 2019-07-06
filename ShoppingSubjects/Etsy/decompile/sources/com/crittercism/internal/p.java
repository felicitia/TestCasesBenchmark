package com.crittercism.internal;

import com.crittercism.internal.f.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImplWrapper;
import org.apache.harmony.xnet.provider.jsse.SSLParametersImpl;

public final class p extends OpenSSLSocketImplWrapper implements u {
    private r a;

    protected p(d dVar, c cVar, Socket socket, String str, int i, boolean z, SSLParametersImpl sSLParametersImpl) {
        super(socket, str, i, z, sSLParametersImpl);
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

    /* JADX WARNING: type inference failed for: r2v0, types: [javax.net.ssl.SSLSocket, org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImplWrapper, com.crittercism.internal.p] */
    public final void startHandshake() {
        try {
            p.super.startHandshake();
        } catch (IOException e) {
            this.a.a(e, (SSLSocket) this);
            throw e;
        }
    }

    public final void close() {
        p.super.close();
        this.a.a();
    }

    public final InputStream getInputStream() {
        return this.a.a((u) this, p.super.getInputStream());
    }

    public final OutputStream getOutputStream() {
        return this.a.a((u) this, p.super.getOutputStream());
    }
}
