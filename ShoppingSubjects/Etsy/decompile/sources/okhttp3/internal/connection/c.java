package okhttp3.internal.connection;

import com.etsy.android.lib.convos.Draft;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.a;
import okhttp3.aa;
import okhttp3.ac;
import okhttp3.i;
import okhttp3.internal.e.f;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.e;
import okhttp3.internal.http2.e.b;
import okhttp3.internal.http2.g;
import okhttp3.j;
import okhttp3.p;
import okhttp3.r;
import okhttp3.w;
import okhttp3.y;
import okio.d;
import okio.m;
import okio.t;

/* compiled from: RealConnection */
public final class c extends b implements i {
    public boolean a;
    public int b;
    public int c = 1;
    public final List<Reference<f>> d = new ArrayList();
    public long e = Long.MAX_VALUE;
    private final j g;
    private final ac h;
    private Socket i;
    private Socket j;
    private r k;
    private Protocol l;
    private e m;
    private okio.e n;
    private d o;

    public c(j jVar, ac acVar) {
        this.g = jVar;
        this.h = acVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0137 A[EDGE_INSN: B:59:0x0137->B:57:0x0137 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:62:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r18, int r19, int r20, int r21, boolean r22, okhttp3.e r23, okhttp3.p r24) {
        /*
            r17 = this;
            r7 = r17
            r8 = r23
            r9 = r24
            okhttp3.Protocol r1 = r7.l
            if (r1 == 0) goto L_0x0012
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "already connected"
            r1.<init>(r2)
            throw r1
        L_0x0012:
            okhttp3.ac r1 = r7.h
            okhttp3.a r1 = r1.a()
            java.util.List r1 = r1.f()
            okhttp3.internal.connection.b r10 = new okhttp3.internal.connection.b
            r10.<init>(r1)
            okhttp3.ac r2 = r7.h
            okhttp3.a r2 = r2.a()
            javax.net.ssl.SSLSocketFactory r2 = r2.i()
            if (r2 != 0) goto L_0x007b
            okhttp3.k r2 = okhttp3.k.c
            boolean r1 = r1.contains(r2)
            if (r1 != 0) goto L_0x0042
            okhttp3.internal.connection.RouteException r1 = new okhttp3.internal.connection.RouteException
            java.net.UnknownServiceException r2 = new java.net.UnknownServiceException
            java.lang.String r3 = "CLEARTEXT communication not enabled for client"
            r2.<init>(r3)
            r1.<init>(r2)
            throw r1
        L_0x0042:
            okhttp3.ac r1 = r7.h
            okhttp3.a r1 = r1.a()
            okhttp3.HttpUrl r1 = r1.a()
            java.lang.String r1 = r1.f()
            okhttp3.internal.e.f r2 = okhttp3.internal.e.f.c()
            boolean r2 = r2.b(r1)
            if (r2 != 0) goto L_0x007b
            okhttp3.internal.connection.RouteException r2 = new okhttp3.internal.connection.RouteException
            java.net.UnknownServiceException r3 = new java.net.UnknownServiceException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "CLEARTEXT communication to "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = " not permitted by network security policy"
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r3.<init>(r1)
            r2.<init>(r3)
            throw r2
        L_0x007b:
            r11 = 0
            r12 = r11
        L_0x007d:
            okhttp3.ac r1 = r7.h     // Catch:{ IOException -> 0x00eb }
            boolean r1 = r1.d()     // Catch:{ IOException -> 0x00eb }
            if (r1 == 0) goto L_0x009b
            r1 = r7
            r2 = r18
            r3 = r19
            r4 = r20
            r5 = r8
            r6 = r9
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ IOException -> 0x00eb }
            java.net.Socket r1 = r7.i     // Catch:{ IOException -> 0x00eb }
            if (r1 != 0) goto L_0x0096
            goto L_0x00b8
        L_0x0096:
            r13 = r18
            r14 = r19
            goto L_0x00a2
        L_0x009b:
            r13 = r18
            r14 = r19
            r7.a(r13, r14, r8, r9)     // Catch:{ IOException -> 0x00e9 }
        L_0x00a2:
            r15 = r21
            r7.a(r10, r15, r8, r9)     // Catch:{ IOException -> 0x00e7 }
            okhttp3.ac r1 = r7.h     // Catch:{ IOException -> 0x00e7 }
            java.net.InetSocketAddress r1 = r1.c()     // Catch:{ IOException -> 0x00e7 }
            okhttp3.ac r2 = r7.h     // Catch:{ IOException -> 0x00e7 }
            java.net.Proxy r2 = r2.b()     // Catch:{ IOException -> 0x00e7 }
            okhttp3.Protocol r3 = r7.l     // Catch:{ IOException -> 0x00e7 }
            r9.a(r8, r1, r2, r3)     // Catch:{ IOException -> 0x00e7 }
        L_0x00b8:
            okhttp3.ac r1 = r7.h
            boolean r1 = r1.d()
            if (r1 == 0) goto L_0x00d1
            java.net.Socket r1 = r7.i
            if (r1 != 0) goto L_0x00d1
            java.net.ProtocolException r1 = new java.net.ProtocolException
            java.lang.String r2 = "Too many tunnel connections attempted: 21"
            r1.<init>(r2)
            okhttp3.internal.connection.RouteException r2 = new okhttp3.internal.connection.RouteException
            r2.<init>(r1)
            throw r2
        L_0x00d1:
            okhttp3.internal.http2.e r1 = r7.m
            if (r1 == 0) goto L_0x00e6
            okhttp3.j r1 = r7.g
            monitor-enter(r1)
            okhttp3.internal.http2.e r2 = r7.m     // Catch:{ all -> 0x00e2 }
            int r2 = r2.a()     // Catch:{ all -> 0x00e2 }
            r7.c = r2     // Catch:{ all -> 0x00e2 }
            monitor-exit(r1)     // Catch:{ all -> 0x00e2 }
            goto L_0x00e6
        L_0x00e2:
            r0 = move-exception
            r2 = r0
            monitor-exit(r1)     // Catch:{ all -> 0x00e2 }
            throw r2
        L_0x00e6:
            return
        L_0x00e7:
            r0 = move-exception
            goto L_0x00f2
        L_0x00e9:
            r0 = move-exception
            goto L_0x00f0
        L_0x00eb:
            r0 = move-exception
            r13 = r18
            r14 = r19
        L_0x00f0:
            r15 = r21
        L_0x00f2:
            r6 = r0
            java.net.Socket r1 = r7.j
            okhttp3.internal.c.a(r1)
            java.net.Socket r1 = r7.i
            okhttp3.internal.c.a(r1)
            r7.j = r11
            r7.i = r11
            r7.n = r11
            r7.o = r11
            r7.k = r11
            r7.l = r11
            r7.m = r11
            okhttp3.ac r1 = r7.h
            java.net.InetSocketAddress r3 = r1.c()
            okhttp3.ac r1 = r7.h
            java.net.Proxy r4 = r1.b()
            r5 = 0
            r1 = r9
            r2 = r8
            r16 = r6
            r1.a(r2, r3, r4, r5, r6)
            if (r12 != 0) goto L_0x012a
            okhttp3.internal.connection.RouteException r1 = new okhttp3.internal.connection.RouteException
            r2 = r16
            r1.<init>(r2)
            r12 = r1
            goto L_0x012f
        L_0x012a:
            r2 = r16
            r12.addConnectException(r2)
        L_0x012f:
            if (r22 == 0) goto L_0x0137
            boolean r2 = r10.a(r2)
            if (r2 != 0) goto L_0x007d
        L_0x0137:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.c.a(int, int, int, int, boolean, okhttp3.e, okhttp3.p):void");
    }

    private void a(int i2, int i3, int i4, okhttp3.e eVar, p pVar) throws IOException {
        y g2 = g();
        HttpUrl a2 = g2.a();
        int i5 = 0;
        while (i5 < 21) {
            a(i2, i3, eVar, pVar);
            g2 = a(i3, i4, g2, a2);
            if (g2 != null) {
                okhttp3.internal.c.a(this.i);
                this.i = null;
                this.o = null;
                this.n = null;
                pVar.a(eVar, this.h.c(), this.h.b(), null);
                i5++;
            } else {
                return;
            }
        }
    }

    private void a(int i2, int i3, okhttp3.e eVar, p pVar) throws IOException {
        Socket socket;
        Proxy b2 = this.h.b();
        a a2 = this.h.a();
        if (b2.type() == Type.DIRECT || b2.type() == Type.HTTP) {
            socket = a2.c().createSocket();
        } else {
            socket = new Socket(b2);
        }
        this.i = socket;
        pVar.a(eVar, this.h.c(), b2);
        this.i.setSoTimeout(i3);
        try {
            f.c().a(this.i, this.h.c(), i2);
            try {
                this.n = m.a(m.b(this.i));
                this.o = m.a(m.a(this.i));
            } catch (NullPointerException e2) {
                if ("throw with null exception".equals(e2.getMessage())) {
                    throw new IOException(e2);
                }
            }
        } catch (ConnectException e3) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to connect to ");
            sb.append(this.h.c());
            ConnectException connectException = new ConnectException(sb.toString());
            connectException.initCause(e3);
            throw connectException;
        }
    }

    private void a(b bVar, int i2, okhttp3.e eVar, p pVar) throws IOException {
        if (this.h.a().i() == null) {
            this.l = Protocol.HTTP_1_1;
            this.j = this.i;
            return;
        }
        pVar.b(eVar);
        a(bVar);
        pVar.a(eVar, this.k);
        if (this.l == Protocol.HTTP_2) {
            this.j.setSoTimeout(0);
            this.m = new e.a(true).a(this.j, this.h.a().a().f(), this.n, this.o).a((b) this).a(i2).a();
            this.m.c();
        }
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.net.Socket, javax.net.ssl.SSLSocket] */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x011f A[Catch:{ all -> 0x0115 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0125 A[Catch:{ all -> 0x0115 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0128  */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(okhttp3.internal.connection.b r8) throws java.io.IOException {
        /*
            r7 = this;
            okhttp3.ac r0 = r7.h
            okhttp3.a r0 = r0.a()
            javax.net.ssl.SSLSocketFactory r1 = r0.i()
            r2 = 0
            java.net.Socket r3 = r7.i     // Catch:{ AssertionError -> 0x0118 }
            okhttp3.HttpUrl r4 = r0.a()     // Catch:{ AssertionError -> 0x0118 }
            java.lang.String r4 = r4.f()     // Catch:{ AssertionError -> 0x0118 }
            okhttp3.HttpUrl r5 = r0.a()     // Catch:{ AssertionError -> 0x0118 }
            int r5 = r5.g()     // Catch:{ AssertionError -> 0x0118 }
            r6 = 1
            java.net.Socket r1 = r1.createSocket(r3, r4, r5, r6)     // Catch:{ AssertionError -> 0x0118 }
            javax.net.ssl.SSLSocket r1 = (javax.net.ssl.SSLSocket) r1     // Catch:{ AssertionError -> 0x0118 }
            okhttp3.k r8 = r8.a(r1)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            boolean r3 = r8.d()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            if (r3 == 0) goto L_0x0041
            okhttp3.internal.e.f r3 = okhttp3.internal.e.f.c()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            okhttp3.HttpUrl r4 = r0.a()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.lang.String r4 = r4.f()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.util.List r5 = r0.e()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            r3.a(r1, r4, r5)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
        L_0x0041:
            r1.startHandshake()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            javax.net.ssl.SSLSession r3 = r1.getSession()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            boolean r4 = r7.a(r3)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            if (r4 != 0) goto L_0x0056
            java.io.IOException r8 = new java.io.IOException     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.lang.String r0 = "a valid ssl session was not established"
            r8.<init>(r0)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            throw r8     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
        L_0x0056:
            okhttp3.r r4 = okhttp3.r.a(r3)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            javax.net.ssl.HostnameVerifier r5 = r0.j()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            okhttp3.HttpUrl r6 = r0.a()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.lang.String r6 = r6.f()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            boolean r3 = r5.verify(r6, r3)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            if (r3 != 0) goto L_0x00be
            java.util.List r8 = r4.b()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            r2 = 0
            java.lang.Object r8 = r8.get(r2)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.security.cert.X509Certificate r8 = (java.security.cert.X509Certificate) r8     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            javax.net.ssl.SSLPeerUnverifiedException r2 = new javax.net.ssl.SSLPeerUnverifiedException     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            r3.<init>()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.lang.String r4 = "Hostname "
            r3.append(r4)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            okhttp3.HttpUrl r0 = r0.a()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.lang.String r0 = r0.f()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            r3.append(r0)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.lang.String r0 = " not verified:\n    certificate: "
            r3.append(r0)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.lang.String r0 = okhttp3.g.a(r8)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            r3.append(r0)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.lang.String r0 = "\n    DN: "
            r3.append(r0)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.security.Principal r0 = r8.getSubjectDN()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.lang.String r0 = r0.getName()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            r3.append(r0)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.lang.String r0 = "\n    subjectAltNames: "
            r3.append(r0)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.util.List r8 = okhttp3.internal.g.d.a(r8)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            r3.append(r8)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.lang.String r8 = r3.toString()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            r2.<init>(r8)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            throw r2     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
        L_0x00be:
            okhttp3.g r3 = r0.k()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            okhttp3.HttpUrl r0 = r0.a()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.lang.String r0 = r0.f()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.util.List r5 = r4.b()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            r3.a(r0, r5)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            boolean r8 = r8.d()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            if (r8 == 0) goto L_0x00df
            okhttp3.internal.e.f r8 = okhttp3.internal.e.f.c()     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.lang.String r2 = r8.a(r1)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
        L_0x00df:
            r7.j = r1     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.net.Socket r8 = r7.j     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            okio.t r8 = okio.m.b(r8)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            okio.e r8 = okio.m.a(r8)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            r7.n = r8     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            java.net.Socket r8 = r7.j     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            okio.s r8 = okio.m.a(r8)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            okio.d r8 = okio.m.a(r8)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            r7.o = r8     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            r7.k = r4     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            if (r2 == 0) goto L_0x0102
            okhttp3.Protocol r8 = okhttp3.Protocol.get(r2)     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            goto L_0x0104
        L_0x0102:
            okhttp3.Protocol r8 = okhttp3.Protocol.HTTP_1_1     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
        L_0x0104:
            r7.l = r8     // Catch:{ AssertionError -> 0x0112, all -> 0x0110 }
            if (r1 == 0) goto L_0x010f
            okhttp3.internal.e.f r8 = okhttp3.internal.e.f.c()
            r8.b(r1)
        L_0x010f:
            return
        L_0x0110:
            r8 = move-exception
            goto L_0x0126
        L_0x0112:
            r8 = move-exception
            r2 = r1
            goto L_0x0119
        L_0x0115:
            r8 = move-exception
            r1 = r2
            goto L_0x0126
        L_0x0118:
            r8 = move-exception
        L_0x0119:
            boolean r0 = okhttp3.internal.c.a(r8)     // Catch:{ all -> 0x0115 }
            if (r0 == 0) goto L_0x0125
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0115 }
            r0.<init>(r8)     // Catch:{ all -> 0x0115 }
            throw r0     // Catch:{ all -> 0x0115 }
        L_0x0125:
            throw r8     // Catch:{ all -> 0x0115 }
        L_0x0126:
            if (r1 == 0) goto L_0x012f
            okhttp3.internal.e.f r0 = okhttp3.internal.e.f.c()
            r0.b(r1)
        L_0x012f:
            okhttp3.internal.c.a(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.c.a(okhttp3.internal.connection.b):void");
    }

    private boolean a(SSLSession sSLSession) {
        return !"NONE".equals(sSLSession.getProtocol()) && !"SSL_NULL_WITH_NULL_NULL".equals(sSLSession.getCipherSuite());
    }

    private y a(int i2, int i3, y yVar, HttpUrl httpUrl) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("CONNECT ");
        sb.append(okhttp3.internal.c.a(httpUrl, true));
        sb.append(" HTTP/1.1");
        String sb2 = sb.toString();
        while (true) {
            okhttp3.internal.c.a aVar = new okhttp3.internal.c.a(null, null, this.n, this.o);
            this.n.a().a((long) i2, TimeUnit.MILLISECONDS);
            this.o.a().a((long) i3, TimeUnit.MILLISECONDS);
            aVar.a(yVar.c(), sb2);
            aVar.b();
            aa a2 = aVar.a(false).a(yVar).a();
            long a3 = okhttp3.internal.b.e.a(a2);
            if (a3 == -1) {
                a3 = 0;
            }
            t b2 = aVar.b(a3);
            okhttp3.internal.c.b(b2, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            b2.close();
            int b3 = a2.b();
            if (b3 != 200) {
                if (b3 != 407) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Unexpected response code for CONNECT: ");
                    sb3.append(a2.b());
                    throw new IOException(sb3.toString());
                }
                y a4 = this.h.a().d().a(this.h, a2);
                if (a4 == null) {
                    throw new IOException("Failed to authenticate with proxy");
                } else if ("close".equalsIgnoreCase(a2.a("Connection"))) {
                    return a4;
                } else {
                    yVar = a4;
                }
            } else if (this.n.c().f() && this.o.c().f()) {
                return null;
            } else {
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
        }
    }

    private y g() {
        return new y.a().a(this.h.a().a()).a("Host", okhttp3.internal.c.a(this.h.a().a(), true)).a("Proxy-Connection", "Keep-Alive").a("User-Agent", okhttp3.internal.d.a()).a();
    }

    public boolean a(a aVar, ac acVar) {
        if (this.d.size() >= this.c || this.a || !okhttp3.internal.a.a.a(this.h.a(), aVar)) {
            return false;
        }
        if (aVar.a().f().equals(b().a().a().f())) {
            return true;
        }
        if (this.m == null || acVar == null || acVar.b().type() != Type.DIRECT || this.h.b().type() != Type.DIRECT || !this.h.c().equals(acVar.c()) || acVar.a().j() != okhttp3.internal.g.d.a || !a(aVar.a())) {
            return false;
        }
        try {
            aVar.k().a(aVar.a().f(), e().b());
            return true;
        } catch (SSLPeerUnverifiedException unused) {
            return false;
        }
    }

    public boolean a(HttpUrl httpUrl) {
        if (httpUrl.g() != this.h.a().a().g()) {
            return false;
        }
        boolean z = true;
        if (httpUrl.f().equals(this.h.a().a().f())) {
            return true;
        }
        if (this.k == null || !okhttp3.internal.g.d.a.a(httpUrl.f(), (X509Certificate) this.k.b().get(0))) {
            z = false;
        }
        return z;
    }

    public okhttp3.internal.b.c a(w wVar, okhttp3.t.a aVar, f fVar) throws SocketException {
        if (this.m != null) {
            return new okhttp3.internal.http2.d(wVar, aVar, fVar, this.m);
        }
        this.j.setSoTimeout(aVar.d());
        this.n.a().a((long) aVar.d(), TimeUnit.MILLISECONDS);
        this.o.a().a((long) aVar.e(), TimeUnit.MILLISECONDS);
        return new okhttp3.internal.c.a(wVar, fVar, this.n, this.o);
    }

    public ac b() {
        return this.h;
    }

    public void c() {
        okhttp3.internal.c.a(this.i);
    }

    public Socket d() {
        return this.j;
    }

    public boolean a(boolean z) {
        int soTimeout;
        if (this.j.isClosed() || this.j.isInputShutdown() || this.j.isOutputShutdown()) {
            return false;
        }
        if (this.m != null) {
            return !this.m.d();
        }
        if (z) {
            try {
                soTimeout = this.j.getSoTimeout();
                this.j.setSoTimeout(1);
                if (this.n.f()) {
                    this.j.setSoTimeout(soTimeout);
                    return false;
                }
                this.j.setSoTimeout(soTimeout);
                return true;
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            } catch (Throwable th) {
                this.j.setSoTimeout(soTimeout);
                throw th;
            }
        }
        return true;
    }

    public void a(g gVar) throws IOException {
        gVar.a(ErrorCode.REFUSED_STREAM);
    }

    public void a(e eVar) {
        synchronized (this.g) {
            this.c = eVar.a();
        }
    }

    public r e() {
        return this.k;
    }

    public boolean f() {
        return this.m != null;
    }

    public Protocol a() {
        return this.l;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.h.a().a().f());
        sb.append(Draft.IMAGE_DELIMITER);
        sb.append(this.h.a().a().g());
        sb.append(", proxy=");
        sb.append(this.h.b());
        sb.append(" hostAddress=");
        sb.append(this.h.c());
        sb.append(" cipherSuite=");
        sb.append(this.k != null ? this.k.a() : "none");
        sb.append(" protocol=");
        sb.append(this.l);
        sb.append('}');
        return sb.toString();
    }
}
