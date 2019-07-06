package de.tavendo.autobahn;

import android.os.Handler;
import android.os.Message;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import de.tavendo.autobahn.g.C0178g;
import de.tavendo.autobahn.g.c;
import de.tavendo.autobahn.g.d;
import de.tavendo.autobahn.g.e;
import de.tavendo.autobahn.g.h;
import de.tavendo.autobahn.g.k;
import de.tavendo.autobahn.g.l;
import de.tavendo.autobahn.g.m;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import org.apache.commons.math3.dfp.Dfp;

/* compiled from: WebSocketReader */
public class i extends Thread {
    private static final String a = "de.tavendo.autobahn.i";
    private final Handler b;
    private final SocketChannel c;
    private final h d;
    private final ByteBuffer e;
    private c f;
    private boolean g = false;
    private int h;
    private boolean i = false;
    private int j;
    private a k;
    private d l = new d();

    /* compiled from: WebSocketReader */
    private static class a {
        public int a;
        public boolean b;
        public int c;
        public int d;
        public int e;
        public int f;
        public byte[] g;

        private a() {
        }

        /* synthetic */ a(a aVar) {
            this();
        }
    }

    public i(Handler handler, SocketChannel socketChannel, h hVar, String str) {
        super(str);
        this.b = handler;
        this.c = socketChannel;
        this.d = hVar;
        this.e = ByteBuffer.allocateDirect(hVar.b() + 14);
        this.f = new c(hVar.c());
        this.k = null;
        this.h = 1;
        Log.d(a, "created");
    }

    public void a() {
        this.g = true;
        Log.d(a, "quit");
    }

    /* access modifiers changed from: protected */
    public void a(Object obj) {
        Message obtainMessage = this.b.obtainMessage();
        obtainMessage.obj = obj;
        this.b.sendMessage(obtainMessage);
    }

    private boolean c() throws Exception {
        String str;
        int i2;
        int i3;
        long j2;
        if (this.k == null) {
            if (this.e.position() < 2) {
                return false;
            }
            byte b2 = this.e.get(0);
            boolean z = (b2 & 128) != 0;
            int i4 = (b2 & 112) >> 4;
            byte b3 = b2 & 15;
            byte b4 = this.e.get(1);
            boolean z2 = (b4 & 128) != 0;
            byte b5 = b4 & Byte.MAX_VALUE;
            if (i4 != 0) {
                throw new WebSocketException("RSV != 0 and no extension negotiated");
            } else if (z2) {
                throw new WebSocketException("masked server frame");
            } else {
                if (b3 > 7) {
                    if (!z) {
                        throw new WebSocketException("fragmented control frame");
                    } else if (b5 > 125) {
                        throw new WebSocketException("control frame with payload length > 125 octets");
                    } else if (b3 != 8 && b3 != 9 && b3 != 10) {
                        StringBuilder sb = new StringBuilder("control frame using reserved opcode ");
                        sb.append(b3);
                        throw new WebSocketException(sb.toString());
                    } else if (b3 == 8 && b5 == 1) {
                        throw new WebSocketException("received close control frame with payload len 1");
                    }
                } else if (b3 != 0 && b3 != 1 && b3 != 2) {
                    StringBuilder sb2 = new StringBuilder("data frame using reserved opcode ");
                    sb2.append(b3);
                    throw new WebSocketException(sb2.toString());
                } else if (!this.i && b3 == 0) {
                    throw new WebSocketException("received continuation data frame outside fragmented message");
                } else if (this.i && b3 != 0) {
                    throw new WebSocketException("received non-continuation data frame while inside fragmented message");
                }
                int i5 = z2 ? 4 : 0;
                if (b5 < 126) {
                    i2 = i5 + 2;
                } else if (b5 == 126) {
                    i2 = i5 + 4;
                } else if (b5 == Byte.MAX_VALUE) {
                    i2 = i5 + 10;
                } else {
                    throw new Exception("logic error");
                }
                if (this.e.position() < i2) {
                    return false;
                }
                if (b5 == 126) {
                    j2 = (long) (((this.e.get(2) & 255) << 8) | (this.e.get(3) & 255));
                    if (j2 < 126) {
                        throw new WebSocketException("invalid data frame length (not using minimal length encoding)");
                    }
                    i3 = 4;
                } else if (b5 != Byte.MAX_VALUE) {
                    j2 = (long) b5;
                    i3 = 2;
                } else if ((this.e.get(2) & 128) != 0) {
                    throw new WebSocketException("invalid data frame length (> 2^63)");
                } else {
                    j2 = (long) ((this.e.get(9) & 255) | ((this.e.get(7) & 255) << 16) | ((this.e.get(3) & 255) << 48) | ((this.e.get(2) & 255) << 56) | ((this.e.get(4) & 255) << 40) | ((this.e.get(5) & 255) << 32) | ((this.e.get(6) & 255) << 24) | ((this.e.get(8) & 255) << 8));
                    if (j2 < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                        throw new WebSocketException("invalid data frame length (not using minimal length encoding)");
                    }
                    i3 = 10;
                }
                if (j2 > ((long) this.d.b())) {
                    throw new WebSocketException("frame payload too large");
                }
                this.k = new a(null);
                this.k.a = b3;
                this.k.b = z;
                this.k.c = i4;
                this.k.e = (int) j2;
                this.k.d = i2;
                this.k.f = this.k.d + this.k.e;
                if (z2) {
                    this.k.g = new byte[4];
                    for (int i6 = 0; i6 < 4; i6++) {
                        this.k.g[i3] = (byte) (this.e.get(i3 + i6) & 255);
                    }
                } else {
                    this.k.g = null;
                }
                return this.k.e == 0 || this.e.position() >= this.k.f;
            }
        } else if (this.e.position() < this.k.f) {
            return false;
        } else {
            byte[] bArr = null;
            int position = this.e.position();
            if (this.k.e > 0) {
                bArr = new byte[this.k.e];
                this.e.position(this.k.d);
                this.e.get(bArr, 0, this.k.e);
            }
            this.e.position(this.k.f);
            this.e.limit(position);
            this.e.compact();
            if (this.k.a <= 7) {
                if (!this.i) {
                    this.i = true;
                    this.j = this.k.a;
                    if (this.j == 1 && this.d.g()) {
                        this.l.a();
                    }
                }
                if (bArr != null) {
                    if (this.f.size() + bArr.length > this.d.c()) {
                        throw new WebSocketException("message payload too large");
                    } else if (this.j != 1 || !this.d.g() || this.l.a(bArr)) {
                        this.f.write(bArr);
                    } else {
                        throw new WebSocketException("invalid UTF-8 in text message payload");
                    }
                }
                if (this.k.b) {
                    if (this.j == 1) {
                        if (this.d.g() && !this.l.b()) {
                            throw new WebSocketException("UTF-8 text message payload ended within Unicode code point");
                        } else if (this.d.a()) {
                            c(this.f.toByteArray());
                        } else {
                            a(new String(this.f.toByteArray(), "UTF-8"));
                        }
                    } else if (this.j == 2) {
                        d(this.f.toByteArray());
                    } else {
                        throw new Exception("logic error");
                    }
                    this.i = false;
                    this.f.reset();
                }
            } else if (this.k.a == 8) {
                int i7 = 1005;
                if (this.k.e >= 2) {
                    i7 = ((bArr[0] & 255) * Dfp.FINITE) + (bArr[1] & 255);
                    if (i7 < 1000 || (!(i7 < 1000 || i7 > 2999 || i7 == 1000 || i7 == 1001 || i7 == 1002 || i7 == 1003 || i7 == 1007 || i7 == 1008 || i7 == 1009 || i7 == 1010) || i7 >= 5000)) {
                        StringBuilder sb3 = new StringBuilder("invalid close code ");
                        sb3.append(i7);
                        throw new WebSocketException(sb3.toString());
                    } else if (this.k.e > 2) {
                        byte[] bArr2 = new byte[(this.k.e - 2)];
                        System.arraycopy(bArr, 2, bArr2, 0, this.k.e - 2);
                        d dVar = new d();
                        dVar.a(bArr2);
                        if (!dVar.b()) {
                            throw new WebSocketException("invalid close reasons (not UTF-8)");
                        }
                        str = new String(bArr2, "UTF-8");
                        a(i7, str);
                    }
                }
                str = null;
                a(i7, str);
            } else if (this.k.a == 9) {
                a(bArr);
            } else if (this.k.a == 10) {
                b(bArr);
            } else {
                throw new Exception("logic error");
            }
            this.k = null;
            return this.e.position() > 0;
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        a((Object) new l());
    }

    /* access modifiers changed from: protected */
    public void a(int i2, String str) {
        a((Object) new c(i2, str));
    }

    /* access modifiers changed from: protected */
    public void a(byte[] bArr) {
        a((Object) new C0178g(bArr));
    }

    /* access modifiers changed from: protected */
    public void b(byte[] bArr) {
        a((Object) new h(bArr));
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        a((Object) new m(str));
    }

    /* access modifiers changed from: protected */
    public void c(byte[] bArr) {
        a((Object) new k(bArr));
    }

    /* access modifiers changed from: protected */
    public void d(byte[] bArr) {
        a((Object) new de.tavendo.autobahn.g.a(bArr));
    }

    private boolean d() throws UnsupportedEncodingException {
        boolean z;
        int position = this.e.position() - 4;
        while (true) {
            z = false;
            if (position < 0) {
                break;
            } else if (this.e.get(position + 0) == 13 && this.e.get(position + 1) == 10 && this.e.get(position + 2) == 13 && this.e.get(position + 3) == 10) {
                b();
                int position2 = this.e.position();
                this.e.position(position + 4);
                this.e.limit(position2);
                this.e.compact();
                if (this.e.position() > 0) {
                    z = true;
                }
                this.h = 3;
            } else {
                position--;
            }
        }
        return z;
    }

    private boolean e() throws Exception {
        if (this.h == 3 || this.h == 2) {
            return c();
        }
        if (this.h == 1) {
            return d();
        }
        return this.h == 0 ? false : false;
    }

    public void run() {
        Log.d(a, "running");
        try {
            this.e.clear();
            do {
                int read = this.c.read(this.e);
                if (read > 0) {
                    do {
                    } while (e());
                } else if (read < 0) {
                    Log.d(a, "run() : ConnectionLost");
                    a((Object) new d());
                    this.g = true;
                }
            } while (!this.g);
        } catch (WebSocketException e2) {
            String str = a;
            StringBuilder sb = new StringBuilder("run() : WebSocketException (");
            sb.append(e2.toString());
            sb.append(")");
            Log.d(str, sb.toString());
            a((Object) new de.tavendo.autobahn.g.i(e2));
        } catch (Exception e3) {
            String str2 = a;
            StringBuilder sb2 = new StringBuilder("run() : Exception (");
            sb2.append(e3.toString());
            sb2.append(")");
            Log.d(str2, sb2.toString());
            a((Object) new e(e3));
        } catch (Throwable th) {
            this.g = true;
            throw th;
        }
        this.g = true;
        Log.d(a, "ended");
    }
}
