package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import okhttp3.internal.c;
import okio.ByteString;
import okio.d;
import org.apache.http.entity.mime.MIME;

/* compiled from: MultipartBody */
public final class v extends z {
    public static final u a = u.a("multipart/mixed");
    public static final u b = u.a("multipart/alternative");
    public static final u c = u.a("multipart/digest");
    public static final u d = u.a("multipart/parallel");
    public static final u e = u.a("multipart/form-data");
    private static final byte[] f = {58, 32};
    private static final byte[] g = {13, 10};
    private static final byte[] h = {45, 45};
    private final ByteString i;
    private final u j;
    private final u k;
    private final List<b> l;
    private long m = -1;

    /* compiled from: MultipartBody */
    public static final class a {
        private final ByteString a;
        private u b;
        private final List<b> c;

        public a() {
            this(UUID.randomUUID().toString());
        }

        public a(String str) {
            this.b = v.a;
            this.c = new ArrayList();
            this.a = ByteString.encodeUtf8(str);
        }

        public a a(u uVar) {
            if (uVar == null) {
                throw new NullPointerException("type == null");
            } else if (!uVar.a().equals("multipart")) {
                StringBuilder sb = new StringBuilder();
                sb.append("multipart != ");
                sb.append(uVar);
                throw new IllegalArgumentException(sb.toString());
            } else {
                this.b = uVar;
                return this;
            }
        }

        public a a(s sVar, z zVar) {
            return a(b.a(sVar, zVar));
        }

        public a a(String str, String str2) {
            return a(b.a(str, str2));
        }

        public a a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException("part == null");
            }
            this.c.add(bVar);
            return this;
        }

        public v a() {
            if (!this.c.isEmpty()) {
                return new v(this.a, this.b, this.c);
            }
            throw new IllegalStateException("Multipart body must have at least one part.");
        }
    }

    /* compiled from: MultipartBody */
    public static final class b {
        final s a;
        final z b;

        public static b a(s sVar, z zVar) {
            if (zVar == null) {
                throw new NullPointerException("body == null");
            } else if (sVar != null && sVar.a(MIME.CONTENT_TYPE) != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            } else if (sVar == null || sVar.a("Content-Length") == null) {
                return new b(sVar, zVar);
            } else {
                throw new IllegalArgumentException("Unexpected header: Content-Length");
            }
        }

        public static b a(String str, String str2) {
            return a(str, null, z.a((u) null, str2));
        }

        public static b a(String str, String str2, z zVar) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            StringBuilder sb = new StringBuilder("form-data; name=");
            v.a(sb, str);
            if (str2 != null) {
                sb.append("; filename=");
                v.a(sb, str2);
            }
            return a(s.a(MIME.CONTENT_DISPOSITION, sb.toString()), zVar);
        }

        private b(s sVar, z zVar) {
            this.a = sVar;
            this.b = zVar;
        }
    }

    v(ByteString byteString, u uVar, List<b> list) {
        this.i = byteString;
        this.j = uVar;
        StringBuilder sb = new StringBuilder();
        sb.append(uVar);
        sb.append("; boundary=");
        sb.append(byteString.utf8());
        this.k = u.a(sb.toString());
        this.l = c.a(list);
    }

    public u a() {
        return this.k;
    }

    public long b() throws IOException {
        long j2 = this.m;
        if (j2 != -1) {
            return j2;
        }
        long a2 = a((d) null, true);
        this.m = a2;
        return a2;
    }

    public void a(d dVar) throws IOException {
        a(dVar, false);
    }

    /* JADX WARNING: type inference failed for: r13v1, types: [okio.d] */
    /* JADX WARNING: type inference failed for: r0v1 */
    /* JADX WARNING: type inference failed for: r13v3, types: [okio.c] */
    /* JADX WARNING: type inference failed for: r13v4 */
    /* JADX WARNING: type inference failed for: r13v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long a(okio.d r13, boolean r14) throws java.io.IOException {
        /*
            r12 = this;
            if (r14 == 0) goto L_0x0009
            okio.c r13 = new okio.c
            r13.<init>()
            r0 = r13
            goto L_0x000a
        L_0x0009:
            r0 = 0
        L_0x000a:
            java.util.List<okhttp3.v$b> r1 = r12.l
            int r1 = r1.size()
            r2 = 0
            r3 = 0
            r4 = r3
            r3 = r2
        L_0x0015:
            if (r3 >= r1) goto L_0x00a9
            java.util.List<okhttp3.v$b> r6 = r12.l
            java.lang.Object r6 = r6.get(r3)
            okhttp3.v$b r6 = (okhttp3.v.b) r6
            okhttp3.s r7 = r6.a
            okhttp3.z r6 = r6.b
            byte[] r8 = h
            r13.c(r8)
            okio.ByteString r8 = r12.i
            r13.c(r8)
            byte[] r8 = g
            r13.c(r8)
            if (r7 == 0) goto L_0x0059
            int r8 = r7.a()
            r9 = r2
        L_0x0039:
            if (r9 >= r8) goto L_0x0059
            java.lang.String r10 = r7.a(r9)
            okio.d r10 = r13.b(r10)
            byte[] r11 = f
            okio.d r10 = r10.c(r11)
            java.lang.String r11 = r7.b(r9)
            okio.d r10 = r10.b(r11)
            byte[] r11 = g
            r10.c(r11)
            int r9 = r9 + 1
            goto L_0x0039
        L_0x0059:
            okhttp3.u r7 = r6.a()
            if (r7 == 0) goto L_0x0072
            java.lang.String r8 = "Content-Type: "
            okio.d r8 = r13.b(r8)
            java.lang.String r7 = r7.toString()
            okio.d r7 = r8.b(r7)
            byte[] r8 = g
            r7.c(r8)
        L_0x0072:
            long r7 = r6.b()
            r9 = -1
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 == 0) goto L_0x008c
            java.lang.String r9 = "Content-Length: "
            okio.d r9 = r13.b(r9)
            okio.d r9 = r9.m(r7)
            byte[] r10 = g
            r9.c(r10)
            goto L_0x0092
        L_0x008c:
            if (r14 == 0) goto L_0x0092
            r0.t()
            return r9
        L_0x0092:
            byte[] r9 = g
            r13.c(r9)
            if (r14 == 0) goto L_0x009d
            long r9 = r4 + r7
            r4 = r9
            goto L_0x00a0
        L_0x009d:
            r6.a(r13)
        L_0x00a0:
            byte[] r6 = g
            r13.c(r6)
            int r3 = r3 + 1
            goto L_0x0015
        L_0x00a9:
            byte[] r1 = h
            r13.c(r1)
            okio.ByteString r1 = r12.i
            r13.c(r1)
            byte[] r1 = h
            r13.c(r1)
            byte[] r1 = g
            r13.c(r1)
            if (r14 == 0) goto L_0x00c9
            long r13 = r0.b()
            long r1 = r4 + r13
            r0.t()
            goto L_0x00ca
        L_0x00c9:
            r1 = r4
        L_0x00ca:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.v.a(okio.d, boolean):long");
    }

    static StringBuilder a(StringBuilder sb, String str) {
        sb.append('\"');
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == 10) {
                sb.append("%0A");
            } else if (charAt == 13) {
                sb.append("%0D");
            } else if (charAt != '\"') {
                sb.append(charAt);
            } else {
                sb.append("%22");
            }
        }
        sb.append('\"');
        return sb;
    }
}
