package okhttp3;

import com.etsy.android.lib.convos.Draft;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.g.c;
import okio.ByteString;

/* compiled from: CertificatePinner */
public final class g {
    public static final g a = new a().a();
    private final Set<b> b;
    private final c c;

    /* compiled from: CertificatePinner */
    public static final class a {
        private final List<b> a = new ArrayList();

        public g a() {
            return new g(new LinkedHashSet(this.a), null);
        }
    }

    /* compiled from: CertificatePinner */
    static final class b {
        final String a;
        final String b;
        final String c;
        final ByteString d;

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0030, code lost:
            if (r11.regionMatches(false, r0 + 1, r10.b, 0, r10.b.length()) != false) goto L_0x0034;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(java.lang.String r11) {
            /*
                r10 = this;
                java.lang.String r0 = r10.a
                java.lang.String r1 = "*."
                boolean r0 = r0.startsWith(r1)
                if (r0 == 0) goto L_0x0035
                r0 = 46
                int r0 = r11.indexOf(r0)
                int r1 = r11.length()
                int r1 = r1 - r0
                r2 = 1
                int r1 = r1 - r2
                java.lang.String r3 = r10.b
                int r3 = r3.length()
                if (r1 != r3) goto L_0x0033
                r5 = 0
                int r6 = r0 + 1
                java.lang.String r7 = r10.b
                r8 = 0
                java.lang.String r0 = r10.b
                int r9 = r0.length()
                r4 = r11
                boolean r11 = r4.regionMatches(r5, r6, r7, r8, r9)
                if (r11 == 0) goto L_0x0033
                goto L_0x0034
            L_0x0033:
                r2 = 0
            L_0x0034:
                return r2
            L_0x0035:
                java.lang.String r0 = r10.b
                boolean r11 = r11.equals(r0)
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.g.b.a(java.lang.String):boolean");
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                if (this.a.equals(bVar.a) && this.c.equals(bVar.c) && this.d.equals(bVar.d)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return (31 * (((527 + this.a.hashCode()) * 31) + this.c.hashCode())) + this.d.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.c);
            sb.append(this.d.base64());
            return sb.toString();
        }
    }

    g(Set<b> set, c cVar) {
        this.b = set;
        this.c = cVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        if (r3.b.equals(r4.b) != false) goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            r0 = 1
            if (r4 != r3) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r4 instanceof okhttp3.g
            if (r1 == 0) goto L_0x001f
            okhttp3.internal.g.c r1 = r3.c
            okhttp3.g r4 = (okhttp3.g) r4
            okhttp3.internal.g.c r2 = r4.c
            boolean r1 = okhttp3.internal.c.a(r1, r2)
            if (r1 == 0) goto L_0x001f
            java.util.Set<okhttp3.g$b> r1 = r3.b
            java.util.Set<okhttp3.g$b> r4 = r4.b
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x001f
            goto L_0x0020
        L_0x001f:
            r0 = 0
        L_0x0020:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.g.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return (31 * (this.c != null ? this.c.hashCode() : 0)) + this.b.hashCode();
    }

    public void a(String str, List<Certificate> list) throws SSLPeerUnverifiedException {
        List a2 = a(str);
        if (!a2.isEmpty()) {
            if (this.c != null) {
                list = this.c.a(list, str);
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                X509Certificate x509Certificate = (X509Certificate) list.get(i);
                int size2 = a2.size();
                ByteString byteString = null;
                ByteString byteString2 = null;
                for (int i2 = 0; i2 < size2; i2++) {
                    b bVar = (b) a2.get(i2);
                    if (bVar.c.equals("sha256/")) {
                        if (byteString == null) {
                            byteString = b(x509Certificate);
                        }
                        if (bVar.d.equals(byteString)) {
                            return;
                        }
                    } else if (bVar.c.equals("sha1/")) {
                        if (byteString2 == null) {
                            byteString2 = a(x509Certificate);
                        }
                        if (bVar.d.equals(byteString2)) {
                            return;
                        }
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("unsupported hashAlgorithm: ");
                        sb.append(bVar.c);
                        throw new AssertionError(sb.toString());
                    }
                }
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Certificate pinning failure!");
            sb2.append("\n  Peer certificate chain:");
            int size3 = list.size();
            for (int i3 = 0; i3 < size3; i3++) {
                X509Certificate x509Certificate2 = (X509Certificate) list.get(i3);
                sb2.append("\n    ");
                sb2.append(a((Certificate) x509Certificate2));
                sb2.append(": ");
                sb2.append(x509Certificate2.getSubjectDN().getName());
            }
            sb2.append("\n  Pinned certificates for ");
            sb2.append(str);
            sb2.append(Draft.IMAGE_DELIMITER);
            int size4 = a2.size();
            for (int i4 = 0; i4 < size4; i4++) {
                b bVar2 = (b) a2.get(i4);
                sb2.append("\n    ");
                sb2.append(bVar2);
            }
            throw new SSLPeerUnverifiedException(sb2.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public List<b> a(String str) {
        List<b> emptyList = Collections.emptyList();
        for (b bVar : this.b) {
            if (bVar.a(str)) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList<>();
                }
                emptyList.add(bVar);
            }
        }
        return emptyList;
    }

    /* access modifiers changed from: 0000 */
    public g a(c cVar) {
        if (okhttp3.internal.c.a((Object) this.c, (Object) cVar)) {
            return this;
        }
        return new g(this.b, cVar);
    }

    public static String a(Certificate certificate) {
        if (!(certificate instanceof X509Certificate)) {
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("sha256/");
        sb.append(b((X509Certificate) certificate).base64());
        return sb.toString();
    }

    static ByteString a(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha1();
    }

    static ByteString b(X509Certificate x509Certificate) {
        return ByteString.of(x509Certificate.getPublicKey().getEncoded()).sha256();
    }
}
