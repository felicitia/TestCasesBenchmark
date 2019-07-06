package com.crittercism.internal;

import com.crittercism.internal.f.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.Queue;
import javax.net.ssl.SSLSocket;

public final class r {
    a a;
    private d b;
    private c c;
    private final Queue<b> d = new LinkedList();
    private g e;
    private h f;

    public r(a aVar, d dVar, c cVar) {
        this.a = aVar;
        this.b = dVar;
        this.c = cVar;
    }

    public final void a() {
        try {
            if (this.f != null) {
                h hVar = this.f;
                if (hVar.a != null) {
                    bm bmVar = hVar.a.k;
                    bl blVar = bl.OK;
                    boolean z = true;
                    if (bmVar.a != bn.d - 1 || bmVar.b != blVar.C) {
                        z = false;
                    }
                    if (z && hVar.c != null) {
                        hVar.c.f();
                    }
                }
            }
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    public final void a(IOException iOException, SSLSocket sSLSocket) {
        try {
            a(iOException, sSLSocket.getInetAddress(), sSLSocket.getPort(), null, this.a);
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable th) {
            cm.b(th);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(IOException iOException, InetAddress inetAddress, int i, String str, a aVar) {
        b a2 = a(inetAddress, Integer.valueOf(i), aVar);
        if (str != null) {
            a2.b(str);
        }
        a2.c();
        a2.d();
        a2.m.f = true;
        a2.a((Throwable) iOException);
        this.b.a(a2);
    }

    public final b a(InetAddress inetAddress) {
        return a(inetAddress, null, this.a);
    }

    /* access modifiers changed from: 0000 */
    public final b a(InetAddress inetAddress, Integer num, a aVar) {
        b bVar = new b();
        if (inetAddress != null) {
            bVar.n = null;
            bVar.m.a = inetAddress;
        }
        if (num != null && num.intValue() > 0) {
            int intValue = num.intValue();
            f fVar = bVar.m;
            if (intValue > 0) {
                fVar.e = intValue;
            }
        }
        if (aVar != null) {
            bVar.m.d = aVar;
        }
        if (this.c != null) {
            bVar.o = a.a(this.c.a);
        }
        if (an.b()) {
            bVar.a(an.a());
        }
        return bVar;
    }

    public final void a(b bVar) {
        synchronized (this.d) {
            this.d.add(bVar);
        }
    }

    public final b b() {
        b bVar;
        synchronized (this.d) {
            bVar = (b) this.d.poll();
        }
        return bVar;
    }

    public final InputStream a(u uVar, InputStream inputStream) {
        InputStream inputStream2;
        if (inputStream != null) {
            try {
                if (this.f != null) {
                    if (this.f.b == inputStream) {
                        inputStream2 = this.f;
                    }
                }
                this.f = new h(uVar, inputStream, this.b);
                inputStream2 = this.f;
            } catch (ThreadDeath e2) {
                throw e2;
            } catch (Throwable th) {
                cm.b(th);
                return inputStream;
            }
        } else {
            inputStream2 = inputStream;
        }
        return inputStream2;
    }

    public final OutputStream a(u uVar, OutputStream outputStream) {
        OutputStream outputStream2;
        if (outputStream != null) {
            try {
                if (this.e != null) {
                    if (this.e.a == outputStream) {
                        outputStream2 = this.e;
                    }
                }
                this.e = new g(uVar, outputStream);
                outputStream2 = this.e;
            } catch (ThreadDeath e2) {
                throw e2;
            } catch (Throwable th) {
                cm.b(th);
                return outputStream;
            }
        } else {
            outputStream2 = outputStream;
        }
        return outputStream2;
    }
}
