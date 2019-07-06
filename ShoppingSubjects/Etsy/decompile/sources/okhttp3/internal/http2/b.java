package okhttp3.internal.http2;

import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.models.ResponseConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okio.ByteString;
import okio.c;
import okio.e;
import okio.m;
import okio.t;

/* compiled from: Hpack */
final class b {
    static final a[] a = {new a(a.f, ""), new a(a.c, BaseHttpRequest.GET), new a(a.c, BaseHttpRequest.POST), new a(a.d, "/"), new a(a.d, "/index.html"), new a(a.e, "http"), new a(a.e, "https"), new a(a.b, "200"), new a(a.b, "204"), new a(a.b, "206"), new a(a.b, "304"), new a(a.b, "400"), new a(a.b, "404"), new a(a.b, "500"), new a("accept-charset", ""), new a("accept-encoding", "gzip, deflate"), new a("accept-language", ""), new a("accept-ranges", ""), new a(ResponseConstants.ACCEPT, ""), new a("access-control-allow-origin", ""), new a("age", ""), new a("allow", ""), new a("authorization", ""), new a("cache-control", ""), new a("content-disposition", ""), new a("content-encoding", ""), new a("content-language", ""), new a("content-length", ""), new a("content-location", ""), new a("content-range", ""), new a("content-type", ""), new a("cookie", ""), new a("date", ""), new a("etag", ""), new a("expect", ""), new a("expires", ""), new a(ResponseConstants.FROM, ""), new a("host", ""), new a("if-match", ""), new a("if-modified-since", ""), new a("if-none-match", ""), new a("if-range", ""), new a("if-unmodified-since", ""), new a("last-modified", ""), new a("link", ""), new a(ResponseConstants.LOCATION, ""), new a("max-forwards", ""), new a("proxy-authenticate", ""), new a("proxy-authorization", ""), new a("range", ""), new a("referer", ""), new a("refresh", ""), new a("retry-after", ""), new a("server", ""), new a("set-cookie", ""), new a("strict-transport-security", ""), new a("transfer-encoding", ""), new a("user-agent", ""), new a("vary", ""), new a("via", ""), new a("www-authenticate", "")};
    static final Map<ByteString, Integer> b = a();

    /* compiled from: Hpack */
    static final class a {
        a[] a;
        int b;
        int c;
        int d;
        private final List<a> e;
        private final e f;
        private final int g;
        private int h;

        a(int i, t tVar) {
            this(i, i, tVar);
        }

        a(int i, int i2, t tVar) {
            this.e = new ArrayList();
            this.a = new a[8];
            this.b = this.a.length - 1;
            this.c = 0;
            this.d = 0;
            this.g = i;
            this.h = i2;
            this.f = m.a(tVar);
        }

        private void d() {
            if (this.h >= this.d) {
                return;
            }
            if (this.h == 0) {
                e();
            } else {
                a(this.d - this.h);
            }
        }

        private void e() {
            Arrays.fill(this.a, null);
            this.b = this.a.length - 1;
            this.c = 0;
            this.d = 0;
        }

        private int a(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.a.length;
                while (true) {
                    length--;
                    if (length < this.b || i <= 0) {
                        System.arraycopy(this.a, this.b + 1, this.a, this.b + 1 + i2, this.c);
                        this.b += i2;
                    } else {
                        i -= this.a[length].i;
                        this.d -= this.a[length].i;
                        this.c--;
                        i2++;
                    }
                }
                System.arraycopy(this.a, this.b + 1, this.a, this.b + 1 + i2, this.c);
                this.b += i2;
            }
            return i2;
        }

        /* access modifiers changed from: 0000 */
        public void a() throws IOException {
            while (!this.f.f()) {
                byte i = this.f.i() & 255;
                if (i == 128) {
                    throw new IOException("index == 0");
                } else if ((i & 128) == 128) {
                    b(a((int) i, 127) - 1);
                } else if (i == 64) {
                    g();
                } else if ((i & 64) == 64) {
                    e(a((int) i, 63) - 1);
                } else if ((i & 32) == 32) {
                    this.h = a((int) i, 31);
                    if (this.h < 0 || this.h > this.g) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Invalid dynamic table size update ");
                        sb.append(this.h);
                        throw new IOException(sb.toString());
                    }
                    d();
                } else if (i == 16 || i == 0) {
                    f();
                } else {
                    d(a((int) i, 15) - 1);
                }
            }
        }

        public List<a> b() {
            ArrayList arrayList = new ArrayList(this.e);
            this.e.clear();
            return arrayList;
        }

        private void b(int i) throws IOException {
            if (g(i)) {
                this.e.add(b.a[i]);
                return;
            }
            int c2 = c(i - b.a.length);
            if (c2 < 0 || c2 >= this.a.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("Header index too large ");
                sb.append(i + 1);
                throw new IOException(sb.toString());
            }
            this.e.add(this.a[c2]);
        }

        private int c(int i) {
            return this.b + 1 + i;
        }

        private void d(int i) throws IOException {
            this.e.add(new a(f(i), c()));
        }

        private void f() throws IOException {
            this.e.add(new a(b.a(c()), c()));
        }

        private void e(int i) throws IOException {
            a(-1, new a(f(i), c()));
        }

        private void g() throws IOException {
            a(-1, new a(b.a(c()), c()));
        }

        private ByteString f(int i) throws IOException {
            if (g(i)) {
                return b.a[i].g;
            }
            int c2 = c(i - b.a.length);
            if (c2 >= 0 && c2 < this.a.length) {
                return this.a[c2].g;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Header index too large ");
            sb.append(i + 1);
            throw new IOException(sb.toString());
        }

        private boolean g(int i) {
            return i >= 0 && i <= b.a.length - 1;
        }

        private void a(int i, a aVar) {
            this.e.add(aVar);
            int i2 = aVar.i;
            if (i != -1) {
                i2 -= this.a[c(i)].i;
            }
            if (i2 > this.h) {
                e();
                return;
            }
            int a2 = a((this.d + i2) - this.h);
            if (i == -1) {
                if (this.c + 1 > this.a.length) {
                    a[] aVarArr = new a[(this.a.length * 2)];
                    System.arraycopy(this.a, 0, aVarArr, this.a.length, this.a.length);
                    this.b = this.a.length - 1;
                    this.a = aVarArr;
                }
                int i3 = this.b;
                this.b = i3 - 1;
                this.a[i3] = aVar;
                this.c++;
            } else {
                this.a[i + c(i) + a2] = aVar;
            }
            this.d += i2;
        }

        private int h() throws IOException {
            return this.f.i() & 255;
        }

        /* access modifiers changed from: 0000 */
        public int a(int i, int i2) throws IOException {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                int h2 = h();
                if ((h2 & 128) == 0) {
                    return i2 + (h2 << i4);
                }
                i2 += (h2 & 127) << i4;
                i4 += 7;
            }
        }

        /* access modifiers changed from: 0000 */
        public ByteString c() throws IOException {
            int h2 = h();
            boolean z = (h2 & 128) == 128;
            int a2 = a(h2, 127);
            if (z) {
                return ByteString.of(i.a().a(this.f.h((long) a2)));
            }
            return this.f.d((long) a2);
        }
    }

    /* renamed from: okhttp3.internal.http2.b$b reason: collision with other inner class name */
    /* compiled from: Hpack */
    static final class C0196b {
        int a;
        int b;
        a[] c;
        int d;
        int e;
        int f;
        private final c g;
        private final boolean h;
        private int i;
        private boolean j;

        C0196b(c cVar) {
            this(4096, true, cVar);
        }

        C0196b(int i2, boolean z, c cVar) {
            this.i = Integer.MAX_VALUE;
            this.c = new a[8];
            this.d = this.c.length - 1;
            this.e = 0;
            this.f = 0;
            this.a = i2;
            this.b = i2;
            this.h = z;
            this.g = cVar;
        }

        private void a() {
            Arrays.fill(this.c, null);
            this.d = this.c.length - 1;
            this.e = 0;
            this.f = 0;
        }

        private int b(int i2) {
            int i3 = 0;
            if (i2 > 0) {
                int length = this.c.length;
                while (true) {
                    length--;
                    if (length < this.d || i2 <= 0) {
                        System.arraycopy(this.c, this.d + 1, this.c, this.d + 1 + i3, this.e);
                        Arrays.fill(this.c, this.d + 1, this.d + 1 + i3, null);
                        this.d += i3;
                    } else {
                        i2 -= this.c[length].i;
                        this.f -= this.c[length].i;
                        this.e--;
                        i3++;
                    }
                }
                System.arraycopy(this.c, this.d + 1, this.c, this.d + 1 + i3, this.e);
                Arrays.fill(this.c, this.d + 1, this.d + 1 + i3, null);
                this.d += i3;
            }
            return i3;
        }

        private void a(a aVar) {
            int i2 = aVar.i;
            if (i2 > this.b) {
                a();
                return;
            }
            b((this.f + i2) - this.b);
            if (this.e + 1 > this.c.length) {
                a[] aVarArr = new a[(this.c.length * 2)];
                System.arraycopy(this.c, 0, aVarArr, this.c.length, this.c.length);
                this.d = this.c.length - 1;
                this.c = aVarArr;
            }
            int i3 = this.d;
            this.d = i3 - 1;
            this.c[i3] = aVar;
            this.e++;
            this.f += i2;
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0074  */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x00ab  */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x00b3  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(java.util.List<okhttp3.internal.http2.a> r14) throws java.io.IOException {
            /*
                r13 = this;
                boolean r0 = r13.j
                r1 = 0
                if (r0 == 0) goto L_0x0020
                int r0 = r13.i
                int r2 = r13.b
                r3 = 32
                r4 = 31
                if (r0 >= r2) goto L_0x0014
                int r0 = r13.i
                r13.a(r0, r4, r3)
            L_0x0014:
                r13.j = r1
                r0 = 2147483647(0x7fffffff, float:NaN)
                r13.i = r0
                int r0 = r13.b
                r13.a(r0, r4, r3)
            L_0x0020:
                int r0 = r14.size()
                r2 = r1
            L_0x0025:
                if (r2 >= r0) goto L_0x00ee
                java.lang.Object r3 = r14.get(r2)
                okhttp3.internal.http2.a r3 = (okhttp3.internal.http2.a) r3
                okio.ByteString r4 = r3.g
                okio.ByteString r4 = r4.toAsciiLowercase()
                okio.ByteString r5 = r3.h
                java.util.Map<okio.ByteString, java.lang.Integer> r6 = okhttp3.internal.http2.b.b
                java.lang.Object r6 = r6.get(r4)
                java.lang.Integer r6 = (java.lang.Integer) r6
                r7 = -1
                r8 = 1
                if (r6 == 0) goto L_0x0070
                int r6 = r6.intValue()
                int r6 = r6 + r8
                if (r6 <= r8) goto L_0x006d
                r9 = 8
                if (r6 >= r9) goto L_0x006d
                okhttp3.internal.http2.a[] r9 = okhttp3.internal.http2.b.a
                int r10 = r6 + -1
                r9 = r9[r10]
                okio.ByteString r9 = r9.h
                boolean r9 = okhttp3.internal.c.a(r9, r5)
                if (r9 == 0) goto L_0x005b
                goto L_0x0071
            L_0x005b:
                okhttp3.internal.http2.a[] r9 = okhttp3.internal.http2.b.a
                r9 = r9[r6]
                okio.ByteString r9 = r9.h
                boolean r9 = okhttp3.internal.c.a(r9, r5)
                if (r9 == 0) goto L_0x006d
                int r9 = r6 + 1
                r12 = r9
                r9 = r6
                r6 = r12
                goto L_0x0072
            L_0x006d:
                r9 = r6
                r6 = r7
                goto L_0x0072
            L_0x0070:
                r6 = r7
            L_0x0071:
                r9 = r6
            L_0x0072:
                if (r6 != r7) goto L_0x00a9
                int r10 = r13.d
                int r10 = r10 + r8
                okhttp3.internal.http2.a[] r8 = r13.c
                int r8 = r8.length
            L_0x007a:
                if (r10 >= r8) goto L_0x00a9
                okhttp3.internal.http2.a[] r11 = r13.c
                r11 = r11[r10]
                okio.ByteString r11 = r11.g
                boolean r11 = okhttp3.internal.c.a(r11, r4)
                if (r11 == 0) goto L_0x00a6
                okhttp3.internal.http2.a[] r11 = r13.c
                r11 = r11[r10]
                okio.ByteString r11 = r11.h
                boolean r11 = okhttp3.internal.c.a(r11, r5)
                if (r11 == 0) goto L_0x009c
                int r6 = r13.d
                int r10 = r10 - r6
                okhttp3.internal.http2.a[] r6 = okhttp3.internal.http2.b.a
                int r6 = r6.length
                int r6 = r6 + r10
                goto L_0x00a9
            L_0x009c:
                if (r9 != r7) goto L_0x00a6
                int r9 = r13.d
                int r9 = r10 - r9
                okhttp3.internal.http2.a[] r11 = okhttp3.internal.http2.b.a
                int r11 = r11.length
                int r9 = r9 + r11
            L_0x00a6:
                int r10 = r10 + 1
                goto L_0x007a
            L_0x00a9:
                if (r6 == r7) goto L_0x00b3
                r3 = 127(0x7f, float:1.78E-43)
                r4 = 128(0x80, float:1.794E-43)
                r13.a(r6, r3, r4)
                goto L_0x00ea
            L_0x00b3:
                r6 = 64
                if (r9 != r7) goto L_0x00c6
                okio.c r7 = r13.g
                r7.k(r6)
                r13.a(r4)
                r13.a(r5)
                r13.a(r3)
                goto L_0x00ea
            L_0x00c6:
                okio.ByteString r7 = okhttp3.internal.http2.a.a
                boolean r7 = r4.startsWith(r7)
                if (r7 == 0) goto L_0x00df
                okio.ByteString r7 = okhttp3.internal.http2.a.f
                boolean r4 = r7.equals(r4)
                if (r4 != 0) goto L_0x00df
                r3 = 15
                r13.a(r9, r3, r1)
                r13.a(r5)
                goto L_0x00ea
            L_0x00df:
                r4 = 63
                r13.a(r9, r4, r6)
                r13.a(r5)
                r13.a(r3)
            L_0x00ea:
                int r2 = r2 + 1
                goto L_0x0025
            L_0x00ee:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.b.C0196b.a(java.util.List):void");
        }

        /* access modifiers changed from: 0000 */
        public void a(int i2, int i3, int i4) {
            if (i2 < i3) {
                this.g.k(i2 | i4);
                return;
            }
            this.g.k(i4 | i3);
            int i5 = i2 - i3;
            while (i5 >= 128) {
                this.g.k(128 | (i5 & 127));
                i5 >>>= 7;
            }
            this.g.k(i5);
        }

        /* access modifiers changed from: 0000 */
        public void a(ByteString byteString) throws IOException {
            if (!this.h || i.a().a(byteString) >= byteString.size()) {
                a(byteString.size(), 127, 0);
                this.g.c(byteString);
                return;
            }
            c cVar = new c();
            i.a().a(byteString, cVar);
            ByteString o = cVar.o();
            a(o.size(), 127, 128);
            this.g.c(o);
        }

        /* access modifiers changed from: 0000 */
        public void a(int i2) {
            this.a = i2;
            int min = Math.min(i2, 16384);
            if (this.b != min) {
                if (min < this.b) {
                    this.i = Math.min(this.i, min);
                }
                this.j = true;
                this.b = min;
                b();
            }
        }

        private void b() {
            if (this.b >= this.f) {
                return;
            }
            if (this.b == 0) {
                a();
            } else {
                b(this.f - this.b);
            }
        }
    }

    private static Map<ByteString, Integer> a() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(a.length);
        for (int i = 0; i < a.length; i++) {
            if (!linkedHashMap.containsKey(a[i].g)) {
                linkedHashMap.put(a[i].g, Integer.valueOf(i));
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    static ByteString a(ByteString byteString) throws IOException {
        int size = byteString.size();
        int i = 0;
        while (i < size) {
            byte b2 = byteString.getByte(i);
            if (b2 < 65 || b2 > 90) {
                i++;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("PROTOCOL_ERROR response malformed: mixed case name: ");
                sb.append(byteString.utf8());
                throw new IOException(sb.toString());
            }
        }
        return byteString;
    }
}
