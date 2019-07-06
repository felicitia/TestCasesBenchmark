package com.crittercism.internal;

import com.crittercism.internal.f.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PlainSocketImpl;
import java.net.SocketAddress;

public final class s extends PlainSocketImpl implements u {
    private r a;

    public s(d dVar, c cVar) {
        this.a = new r(a.HTTP, dVar, cVar);
    }

    public final void close() {
        s.super.close();
        this.a.a();
    }

    public final void connect(String str, int i) {
        try {
            s.super.connect(str, i);
        } catch (IOException e) {
            InetAddress inetAddress = getInetAddress();
            r rVar = this.a;
            if (str != null) {
                try {
                    rVar.a(e, inetAddress, i, str, null);
                } catch (ThreadDeath e2) {
                    throw e2;
                } catch (Throwable th) {
                    cm.b(th);
                }
            }
            throw e;
        }
    }

    public final void connect(InetAddress inetAddress, int i) {
        try {
            s.super.connect(inetAddress, i);
        } catch (IOException e) {
            r rVar = this.a;
            if (inetAddress != null) {
                try {
                    rVar.a(e, inetAddress, i, null, null);
                } catch (ThreadDeath e2) {
                    throw e2;
                } catch (Throwable th) {
                    cm.b(th);
                }
            }
            throw e;
        }
    }

    public final void connect(SocketAddress socketAddress, int i) {
        try {
            s.super.connect(socketAddress, i);
        } catch (IOException e) {
            r rVar = this.a;
            if (socketAddress != null) {
                try {
                    if (socketAddress instanceof InetSocketAddress) {
                        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
                        InetAddress address = inetSocketAddress.getAddress();
                        int port = inetSocketAddress.getPort();
                        if (address != null) {
                            rVar.a(e, address, port, null, null);
                        }
                    }
                } catch (ThreadDeath e2) {
                    throw e2;
                } catch (Throwable th) {
                    cm.b(th);
                }
            }
            throw e;
        }
    }

    public final InputStream getInputStream() {
        return this.a.a((u) this, s.super.getInputStream());
    }

    public final OutputStream getOutputStream() {
        return this.a.a((u) this, s.super.getOutputStream());
    }

    public final b a() {
        InetAddress inetAddress = getInetAddress();
        int port = getPort();
        r rVar = this.a;
        return rVar.a(inetAddress, Integer.valueOf(port), rVar.a);
    }

    public final void a(b bVar) {
        this.a.a(bVar);
    }

    public final b b() {
        return this.a.b();
    }

    public final Object getOption(int i) {
        return s.super.getOption(i);
    }

    public final void setOption(int i, Object obj) {
        s.super.setOption(i, obj);
    }
}
