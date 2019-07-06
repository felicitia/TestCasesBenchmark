package de.tavendo.autobahn;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.etsy.android.lib.convos.Draft;
import de.tavendo.autobahn.g.C0178g;
import de.tavendo.autobahn.g.b;
import de.tavendo.autobahn.g.c;
import de.tavendo.autobahn.g.d;
import de.tavendo.autobahn.g.h;
import de.tavendo.autobahn.g.i;
import de.tavendo.autobahn.g.j;
import de.tavendo.autobahn.g.k;
import de.tavendo.autobahn.g.l;
import de.tavendo.autobahn.g.m;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.channels.SocketChannel;

/* compiled from: WebSocketConnection */
public class e {
    /* access modifiers changed from: private */
    public static final String g = "de.tavendo.autobahn.e";
    protected Handler a;
    protected i b;
    protected WebSocketWriter c;
    protected HandlerThread d;
    protected SocketChannel e;
    protected h f;
    private URI h;
    private String i;
    /* access modifiers changed from: private */
    public String j;
    /* access modifiers changed from: private */
    public int k;
    /* access modifiers changed from: private */
    public String l;
    /* access modifiers changed from: private */
    public String m;
    /* access modifiers changed from: private */
    public String[] n;
    /* access modifiers changed from: private */
    public f o;

    /* compiled from: WebSocketConnection */
    private class a extends AsyncTask<Void, Void, String> {
        private a() {
        }

        /* synthetic */ a(e eVar, a aVar) {
            this();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public String doInBackground(Void... voidArr) {
            Thread.currentThread().setName("WebSocketConnector");
            try {
                e.this.e = SocketChannel.open();
                e.this.e.socket().connect(new InetSocketAddress(e.this.j, e.this.k), e.this.f.f());
                e.this.e.socket().setSoTimeout(e.this.f.e());
                e.this.e.socket().setTcpNoDelay(e.this.f.d());
                return null;
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(String str) {
            if (str != null) {
                e.this.o.a(2, str);
            } else if (e.this.e.isConnected()) {
                try {
                    e.this.b();
                    e.this.d();
                    e.this.c();
                    StringBuilder sb = new StringBuilder(String.valueOf(e.this.j));
                    sb.append(Draft.IMAGE_DELIMITER);
                    sb.append(e.this.k);
                    b bVar = new b(sb.toString());
                    bVar.b = e.this.l;
                    bVar.c = e.this.m;
                    bVar.e = e.this.n;
                    e.this.c.forward(bVar);
                } catch (Exception e) {
                    e.this.o.a(5, e.getMessage());
                }
            } else {
                e.this.o.a(2, "could not connect to WebSockets server");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(Object obj) {
    }

    public e() {
        Log.d(g, "created");
    }

    /* access modifiers changed from: private */
    public void a(int i2, String str) {
        String str2 = g;
        StringBuilder sb = new StringBuilder("fail connection [code = ");
        sb.append(i2);
        sb.append(", reason = ");
        sb.append(str);
        Log.d(str2, sb.toString());
        if (this.b != null) {
            this.b.a();
            try {
                this.b.join();
            } catch (InterruptedException e2) {
                com.google.a.a.a.a.a.a.a(e2);
            }
        } else {
            Log.d(g, "mReader already NULL");
        }
        if (this.c != null) {
            this.c.forward(new j());
            try {
                this.d.join();
            } catch (InterruptedException e3) {
                com.google.a.a.a.a.a.a.a(e3);
            }
        } else {
            Log.d(g, "mWriter already NULL");
        }
        if (this.e != null) {
            try {
                this.e.close();
            } catch (IOException e4) {
                com.google.a.a.a.a.a.a.a(e4);
            }
        } else {
            Log.d(g, "mTransportChannel already NULL");
        }
        if (this.o != null) {
            try {
                this.o.a(i2, str);
            } catch (Exception e5) {
                com.google.a.a.a.a.a.a.a(e5);
            }
        } else {
            Log.d(g, "mWsHandler already NULL");
        }
        Log.d(g, "worker threads stopped");
    }

    public void a(String str, f fVar) throws WebSocketException {
        a(str, null, fVar, new h());
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c7 A[Catch:{ URISyntaxException -> 0x00f8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r3, java.lang.String[] r4, de.tavendo.autobahn.f r5, de.tavendo.autobahn.h r6) throws de.tavendo.autobahn.WebSocketException {
        /*
            r2 = this;
            java.nio.channels.SocketChannel r0 = r2.e
            if (r0 == 0) goto L_0x0014
            java.nio.channels.SocketChannel r0 = r2.e
            boolean r0 = r0.isConnected()
            if (r0 == 0) goto L_0x0014
            de.tavendo.autobahn.WebSocketException r3 = new de.tavendo.autobahn.WebSocketException
            java.lang.String r4 = "already connected"
            r3.<init>(r4)
            throw r3
        L_0x0014:
            java.net.URI r0 = new java.net.URI     // Catch:{ URISyntaxException -> 0x00f8 }
            r0.<init>(r3)     // Catch:{ URISyntaxException -> 0x00f8 }
            r2.h = r0     // Catch:{ URISyntaxException -> 0x00f8 }
            java.net.URI r3 = r2.h     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r3 = r3.getScheme()     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r0 = "ws"
            boolean r3 = r3.equals(r0)     // Catch:{ URISyntaxException -> 0x00f8 }
            if (r3 != 0) goto L_0x003f
            java.net.URI r3 = r2.h     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r3 = r3.getScheme()     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r0 = "wss"
            boolean r3 = r3.equals(r0)     // Catch:{ URISyntaxException -> 0x00f8 }
            if (r3 != 0) goto L_0x003f
            de.tavendo.autobahn.WebSocketException r3 = new de.tavendo.autobahn.WebSocketException     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r4 = "unsupported scheme for WebSockets URI"
            r3.<init>(r4)     // Catch:{ URISyntaxException -> 0x00f8 }
            throw r3     // Catch:{ URISyntaxException -> 0x00f8 }
        L_0x003f:
            java.net.URI r3 = r2.h     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r3 = r3.getScheme()     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r0 = "wss"
            boolean r3 = r3.equals(r0)     // Catch:{ URISyntaxException -> 0x00f8 }
            if (r3 == 0) goto L_0x0055
            de.tavendo.autobahn.WebSocketException r3 = new de.tavendo.autobahn.WebSocketException     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r4 = "secure WebSockets not implemented"
            r3.<init>(r4)     // Catch:{ URISyntaxException -> 0x00f8 }
            throw r3     // Catch:{ URISyntaxException -> 0x00f8 }
        L_0x0055:
            java.net.URI r3 = r2.h     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r3 = r3.getScheme()     // Catch:{ URISyntaxException -> 0x00f8 }
            r2.i = r3     // Catch:{ URISyntaxException -> 0x00f8 }
            java.net.URI r3 = r2.h     // Catch:{ URISyntaxException -> 0x00f8 }
            int r3 = r3.getPort()     // Catch:{ URISyntaxException -> 0x00f8 }
            r0 = -1
            if (r3 != r0) goto L_0x007a
            java.lang.String r3 = r2.i     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r0 = "ws"
            boolean r3 = r3.equals(r0)     // Catch:{ URISyntaxException -> 0x00f8 }
            if (r3 == 0) goto L_0x0075
            r3 = 80
            r2.k = r3     // Catch:{ URISyntaxException -> 0x00f8 }
            goto L_0x0082
        L_0x0075:
            r3 = 443(0x1bb, float:6.21E-43)
            r2.k = r3     // Catch:{ URISyntaxException -> 0x00f8 }
            goto L_0x0082
        L_0x007a:
            java.net.URI r3 = r2.h     // Catch:{ URISyntaxException -> 0x00f8 }
            int r3 = r3.getPort()     // Catch:{ URISyntaxException -> 0x00f8 }
            r2.k = r3     // Catch:{ URISyntaxException -> 0x00f8 }
        L_0x0082:
            java.net.URI r3 = r2.h     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r3 = r3.getHost()     // Catch:{ URISyntaxException -> 0x00f8 }
            if (r3 != 0) goto L_0x0092
            de.tavendo.autobahn.WebSocketException r3 = new de.tavendo.autobahn.WebSocketException     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r4 = "no host specified in WebSockets URI"
            r3.<init>(r4)     // Catch:{ URISyntaxException -> 0x00f8 }
            throw r3     // Catch:{ URISyntaxException -> 0x00f8 }
        L_0x0092:
            java.net.URI r3 = r2.h     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r3 = r3.getHost()     // Catch:{ URISyntaxException -> 0x00f8 }
            r2.j = r3     // Catch:{ URISyntaxException -> 0x00f8 }
            java.net.URI r3 = r2.h     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r3 = r3.getPath()     // Catch:{ URISyntaxException -> 0x00f8 }
            if (r3 == 0) goto L_0x00ba
            java.net.URI r3 = r2.h     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r3 = r3.getPath()     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r0 = ""
            boolean r3 = r3.equals(r0)     // Catch:{ URISyntaxException -> 0x00f8 }
            if (r3 == 0) goto L_0x00b1
            goto L_0x00ba
        L_0x00b1:
            java.net.URI r3 = r2.h     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r3 = r3.getPath()     // Catch:{ URISyntaxException -> 0x00f8 }
            r2.l = r3     // Catch:{ URISyntaxException -> 0x00f8 }
            goto L_0x00be
        L_0x00ba:
            java.lang.String r3 = "/"
            r2.l = r3     // Catch:{ URISyntaxException -> 0x00f8 }
        L_0x00be:
            java.net.URI r3 = r2.h     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r3 = r3.getQuery()     // Catch:{ URISyntaxException -> 0x00f8 }
            r0 = 0
            if (r3 == 0) goto L_0x00df
            java.net.URI r3 = r2.h     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r3 = r3.getQuery()     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r1 = ""
            boolean r3 = r3.equals(r1)     // Catch:{ URISyntaxException -> 0x00f8 }
            if (r3 == 0) goto L_0x00d6
            goto L_0x00df
        L_0x00d6:
            java.net.URI r3 = r2.h     // Catch:{ URISyntaxException -> 0x00f8 }
            java.lang.String r3 = r3.getQuery()     // Catch:{ URISyntaxException -> 0x00f8 }
            r2.m = r3     // Catch:{ URISyntaxException -> 0x00f8 }
            goto L_0x00e1
        L_0x00df:
            r2.m = r0     // Catch:{ URISyntaxException -> 0x00f8 }
        L_0x00e1:
            r2.n = r4
            r2.o = r5
            de.tavendo.autobahn.h r3 = new de.tavendo.autobahn.h
            r3.<init>(r6)
            r2.f = r3
            de.tavendo.autobahn.e$a r3 = new de.tavendo.autobahn.e$a
            r3.<init>(r2, r0)
            r4 = 0
            java.lang.Void[] r4 = new java.lang.Void[r4]
            r3.execute(r4)
            return
        L_0x00f8:
            de.tavendo.autobahn.WebSocketException r3 = new de.tavendo.autobahn.WebSocketException
            java.lang.String r4 = "invalid WebSockets URI"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: de.tavendo.autobahn.e.a(java.lang.String, java.lang.String[], de.tavendo.autobahn.f, de.tavendo.autobahn.h):void");
    }

    public void a() {
        if (this.c != null) {
            this.c.forward(new c(1000));
        } else {
            Log.d(g, "could not send Close .. writer already NULL");
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.a = new Handler() {
            public void handleMessage(Message message) {
                if (message.obj instanceof m) {
                    m mVar = (m) message.obj;
                    if (e.this.o != null) {
                        e.this.o.a(mVar.a);
                    } else {
                        Log.d(e.g, "could not call onTextMessage() .. handler already NULL");
                    }
                } else if (message.obj instanceof k) {
                    k kVar = (k) message.obj;
                    if (e.this.o != null) {
                        e.this.o.a(kVar.a);
                    } else {
                        Log.d(e.g, "could not call onRawTextMessage() .. handler already NULL");
                    }
                } else if (message.obj instanceof de.tavendo.autobahn.g.a) {
                    de.tavendo.autobahn.g.a aVar = (de.tavendo.autobahn.g.a) message.obj;
                    if (e.this.o != null) {
                        e.this.o.b(aVar.a);
                    } else {
                        Log.d(e.g, "could not call onBinaryMessage() .. handler already NULL");
                    }
                } else if (message.obj instanceof C0178g) {
                    C0178g gVar = (C0178g) message.obj;
                    Log.d(e.g, "WebSockets Ping received");
                    h hVar = new h();
                    hVar.a = gVar.a;
                    e.this.c.forward(hVar);
                } else if (message.obj instanceof h) {
                    h hVar2 = (h) message.obj;
                    Log.d(e.g, "WebSockets Pong received");
                } else if (message.obj instanceof c) {
                    c cVar = (c) message.obj;
                    String e = e.g;
                    StringBuilder sb = new StringBuilder("WebSockets Close received (");
                    sb.append(cVar.a);
                    sb.append(" - ");
                    sb.append(cVar.b);
                    sb.append(")");
                    Log.d(e, sb.toString());
                    e.this.c.forward(new c(1000));
                } else if (message.obj instanceof l) {
                    l lVar = (l) message.obj;
                    Log.d(e.g, "opening handshake received");
                    if (e.this.o != null) {
                        e.this.o.a();
                    } else {
                        Log.d(e.g, "could not call onOpen() .. handler already NULL");
                    }
                } else if (message.obj instanceof d) {
                    d dVar = (d) message.obj;
                    e.this.a(3, "WebSockets connection lost");
                } else if (message.obj instanceof i) {
                    i iVar = (i) message.obj;
                    e.this.a(4, "WebSockets protocol violation");
                } else if (message.obj instanceof de.tavendo.autobahn.g.e) {
                    de.tavendo.autobahn.g.e eVar = (de.tavendo.autobahn.g.e) message.obj;
                    e eVar2 = e.this;
                    StringBuilder sb2 = new StringBuilder("WebSockets internal error (");
                    sb2.append(eVar.a.toString());
                    sb2.append(")");
                    eVar2.a(5, sb2.toString());
                } else {
                    e.this.a(message.obj);
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public void c() {
        this.d = new HandlerThread("WebSocketWriter");
        this.d.start();
        this.c = new WebSocketWriter(this.d.getLooper(), this.a, this.e, this.f);
        Log.d(g, "WS writer created and started");
    }

    /* access modifiers changed from: protected */
    public void d() {
        this.b = new i(this.a, this.e, this.f, "WebSocketReader");
        this.b.start();
        Log.d(g, "WS reader created and started");
    }
}
