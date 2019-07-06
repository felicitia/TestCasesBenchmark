package okhttp3.internal.http2;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.internal.b.c;
import okhttp3.internal.b.e;
import okhttp3.internal.b.i;
import okhttp3.internal.b.k;
import okhttp3.internal.connection.f;
import okhttp3.w;
import okhttp3.y;
import okio.ByteString;
import okio.h;
import okio.m;
import okio.s;
import okio.t;
import org.apache.http.entity.mime.MIME;

/* compiled from: Http2Codec */
public final class d implements c {
    private static final ByteString b = ByteString.encodeUtf8("connection");
    private static final ByteString c = ByteString.encodeUtf8("host");
    private static final ByteString d = ByteString.encodeUtf8("keep-alive");
    private static final ByteString e = ByteString.encodeUtf8("proxy-connection");
    private static final ByteString f = ByteString.encodeUtf8("transfer-encoding");
    private static final ByteString g = ByteString.encodeUtf8("te");
    private static final ByteString h = ByteString.encodeUtf8("encoding");
    private static final ByteString i = ByteString.encodeUtf8("upgrade");
    private static final List<ByteString> j = okhttp3.internal.c.a((T[]) new ByteString[]{b, c, d, e, g, f, h, i, a.c, a.d, a.e, a.f});
    private static final List<ByteString> k = okhttp3.internal.c.a((T[]) new ByteString[]{b, c, d, e, g, f, h, i});
    final f a;
    private final w l;
    private final okhttp3.t.a m;
    private final e n;
    private g o;

    /* compiled from: Http2Codec */
    class a extends h {
        boolean a = false;
        long b = 0;

        a(t tVar) {
            super(tVar);
        }

        public long a(okio.c cVar, long j) throws IOException {
            try {
                long a2 = b().a(cVar, j);
                if (a2 > 0) {
                    this.b += a2;
                }
                return a2;
            } catch (IOException e) {
                a(e);
                throw e;
            }
        }

        public void close() throws IOException {
            super.close();
            a(null);
        }

        private void a(IOException iOException) {
            if (!this.a) {
                this.a = true;
                d.this.a.a(false, d.this, this.b, iOException);
            }
        }
    }

    public d(w wVar, okhttp3.t.a aVar, f fVar, e eVar) {
        this.l = wVar;
        this.m = aVar;
        this.a = fVar;
        this.n = eVar;
    }

    public s a(y yVar, long j2) {
        return this.o.h();
    }

    public void a(y yVar) throws IOException {
        if (this.o == null) {
            this.o = this.n.a(b(yVar), yVar.d() != null);
            this.o.e().a((long) this.m.d(), TimeUnit.MILLISECONDS);
            this.o.f().a((long) this.m.e(), TimeUnit.MILLISECONDS);
        }
    }

    public void a() throws IOException {
        this.n.b();
    }

    public void b() throws IOException {
        this.o.h().close();
    }

    public okhttp3.aa.a a(boolean z) throws IOException {
        okhttp3.aa.a a2 = a(this.o.d());
        if (!z || okhttp3.internal.a.a.a(a2) != 100) {
            return a2;
        }
        return null;
    }

    public static List<a> b(y yVar) {
        okhttp3.s c2 = yVar.c();
        ArrayList arrayList = new ArrayList(c2.a() + 4);
        arrayList.add(new a(a.c, yVar.b()));
        arrayList.add(new a(a.d, i.a(yVar.a())));
        String a2 = yVar.a("Host");
        if (a2 != null) {
            arrayList.add(new a(a.f, a2));
        }
        arrayList.add(new a(a.e, yVar.a().b()));
        int a3 = c2.a();
        for (int i2 = 0; i2 < a3; i2++) {
            ByteString encodeUtf8 = ByteString.encodeUtf8(c2.a(i2).toLowerCase(Locale.US));
            if (!j.contains(encodeUtf8)) {
                arrayList.add(new a(encodeUtf8, c2.b(i2)));
            }
        }
        return arrayList;
    }

    public static okhttp3.aa.a a(List<a> list) throws IOException {
        okhttp3.s.a aVar = new okhttp3.s.a();
        int size = list.size();
        okhttp3.s.a aVar2 = aVar;
        k kVar = null;
        for (int i2 = 0; i2 < size; i2++) {
            a aVar3 = (a) list.get(i2);
            if (aVar3 != null) {
                ByteString byteString = aVar3.g;
                String utf8 = aVar3.h.utf8();
                if (byteString.equals(a.b)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("HTTP/1.1 ");
                    sb.append(utf8);
                    kVar = k.a(sb.toString());
                } else if (!k.contains(byteString)) {
                    okhttp3.internal.a.a.a(aVar2, byteString.utf8(), utf8);
                }
            } else if (kVar != null && kVar.b == 100) {
                aVar2 = new okhttp3.s.a();
                kVar = null;
            }
        }
        if (kVar != null) {
            return new okhttp3.aa.a().a(Protocol.HTTP_2).a(kVar.b).a(kVar.c).a(aVar2.a());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    public ab a(aa aaVar) throws IOException {
        this.a.c.f(this.a.b);
        return new okhttp3.internal.b.h(aaVar.a(MIME.CONTENT_TYPE), e.a(aaVar), m.a((t) new a(this.o.g())));
    }

    public void c() {
        if (this.o != null) {
            this.o.b(ErrorCode.CANCEL);
        }
    }
}
