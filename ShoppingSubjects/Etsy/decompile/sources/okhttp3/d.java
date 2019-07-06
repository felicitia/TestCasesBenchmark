package okhttp3;

import java.util.concurrent.TimeUnit;

/* compiled from: CacheControl */
public final class d {
    public static final d a = new a().a().c();
    public static final d b = new a().b().a(Integer.MAX_VALUE, TimeUnit.SECONDS).c();
    String c;
    private final boolean d;
    private final boolean e;
    private final int f;
    private final int g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final int k;
    private final int l;
    private final boolean m;
    private final boolean n;
    private final boolean o;

    /* compiled from: CacheControl */
    public static final class a {
        boolean a;
        boolean b;
        int c = -1;
        int d = -1;
        int e = -1;
        boolean f;
        boolean g;
        boolean h;

        public a a() {
            this.a = true;
            return this;
        }

        public a a(int i, TimeUnit timeUnit) {
            if (i < 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("maxStale < 0: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
            }
            long seconds = timeUnit.toSeconds((long) i);
            this.d = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
            return this;
        }

        public a b() {
            this.f = true;
            return this;
        }

        public d c() {
            return new d(this);
        }
    }

    private d(boolean z, boolean z2, int i2, int i3, boolean z3, boolean z4, boolean z5, int i4, int i5, boolean z6, boolean z7, boolean z8, String str) {
        this.d = z;
        this.e = z2;
        this.f = i2;
        this.g = i3;
        this.h = z3;
        this.i = z4;
        this.j = z5;
        this.k = i4;
        this.l = i5;
        this.m = z6;
        this.n = z7;
        this.o = z8;
        this.c = str;
    }

    d(a aVar) {
        this.d = aVar.a;
        this.e = aVar.b;
        this.f = aVar.c;
        this.g = -1;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = aVar.d;
        this.l = aVar.e;
        this.m = aVar.f;
        this.n = aVar.g;
        this.o = aVar.h;
    }

    public boolean a() {
        return this.d;
    }

    public boolean b() {
        return this.e;
    }

    public int c() {
        return this.f;
    }

    public boolean d() {
        return this.h;
    }

    public boolean e() {
        return this.i;
    }

    public boolean f() {
        return this.j;
    }

    public int g() {
        return this.k;
    }

    public int h() {
        return this.l;
    }

    public boolean i() {
        return this.m;
    }

    public boolean j() {
        return this.o;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static okhttp3.d a(okhttp3.s r24) {
        /*
            r0 = r24
            int r1 = r24.a()
            r6 = 0
            r7 = 1
            r8 = 0
            r10 = 0
            r11 = 0
            r12 = -1
            r13 = -1
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = -1
            r18 = -1
            r19 = 0
            r20 = 0
            r21 = 0
        L_0x001b:
            if (r6 >= r1) goto L_0x014a
            java.lang.String r9 = r0.a(r6)
            java.lang.String r2 = r0.b(r6)
            java.lang.String r4 = "Cache-Control"
            boolean r4 = r9.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x0033
            if (r8 == 0) goto L_0x0031
        L_0x002f:
            r7 = 0
            goto L_0x003c
        L_0x0031:
            r8 = r2
            goto L_0x003c
        L_0x0033:
            java.lang.String r4 = "Pragma"
            boolean r4 = r9.equalsIgnoreCase(r4)
            if (r4 == 0) goto L_0x0143
            goto L_0x002f
        L_0x003c:
            r4 = 0
        L_0x003d:
            int r9 = r2.length()
            if (r4 >= r9) goto L_0x0143
            java.lang.String r9 = "=,;"
            int r9 = okhttp3.internal.b.e.a(r2, r4, r9)
            java.lang.String r4 = r2.substring(r4, r9)
            java.lang.String r4 = r4.trim()
            int r3 = r2.length()
            if (r9 == r3) goto L_0x009e
            char r3 = r2.charAt(r9)
            r5 = 44
            if (r3 == r5) goto L_0x009e
            char r3 = r2.charAt(r9)
            r5 = 59
            if (r3 != r5) goto L_0x0068
            goto L_0x009e
        L_0x0068:
            int r9 = r9 + 1
            int r3 = okhttp3.internal.b.e.a(r2, r9)
            int r5 = r2.length()
            if (r3 >= r5) goto L_0x008d
            char r5 = r2.charAt(r3)
            r9 = 34
            if (r5 != r9) goto L_0x008d
            int r3 = r3 + 1
            java.lang.String r5 = "\""
            int r5 = okhttp3.internal.b.e.a(r2, r3, r5)
            java.lang.String r3 = r2.substring(r3, r5)
            r22 = 1
            int r5 = r5 + 1
            goto L_0x00a4
        L_0x008d:
            r22 = 1
            java.lang.String r5 = ",;"
            int r5 = okhttp3.internal.b.e.a(r2, r3, r5)
            java.lang.String r3 = r2.substring(r3, r5)
            java.lang.String r3 = r3.trim()
            goto L_0x00a4
        L_0x009e:
            r22 = 1
            int r9 = r9 + 1
            r5 = r9
            r3 = 0
        L_0x00a4:
            java.lang.String r9 = "no-cache"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x00b1
            r10 = r22
        L_0x00ae:
            r9 = -1
            goto L_0x0140
        L_0x00b1:
            java.lang.String r9 = "no-store"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x00bc
            r11 = r22
            goto L_0x00ae
        L_0x00bc:
            java.lang.String r9 = "max-age"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x00cc
            r9 = -1
            int r3 = okhttp3.internal.b.e.b(r3, r9)
            r12 = r3
            goto L_0x0140
        L_0x00cc:
            java.lang.String r9 = "s-maxage"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x00dc
            r9 = -1
            int r3 = okhttp3.internal.b.e.b(r3, r9)
            r13 = r3
            goto L_0x0140
        L_0x00dc:
            java.lang.String r9 = "private"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x00e7
            r14 = r22
            goto L_0x00ae
        L_0x00e7:
            java.lang.String r9 = "public"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x00f2
            r15 = r22
            goto L_0x00ae
        L_0x00f2:
            java.lang.String r9 = "must-revalidate"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x00fd
            r16 = r22
            goto L_0x00ae
        L_0x00fd:
            java.lang.String r9 = "max-stale"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x010f
            r4 = 2147483647(0x7fffffff, float:NaN)
            int r3 = okhttp3.internal.b.e.b(r3, r4)
            r17 = r3
            goto L_0x00ae
        L_0x010f:
            java.lang.String r9 = "min-fresh"
            boolean r9 = r9.equalsIgnoreCase(r4)
            if (r9 == 0) goto L_0x011f
            r9 = -1
            int r3 = okhttp3.internal.b.e.b(r3, r9)
            r18 = r3
            goto L_0x0140
        L_0x011f:
            r9 = -1
            java.lang.String r3 = "only-if-cached"
            boolean r3 = r3.equalsIgnoreCase(r4)
            if (r3 == 0) goto L_0x012b
            r19 = r22
            goto L_0x0140
        L_0x012b:
            java.lang.String r3 = "no-transform"
            boolean r3 = r3.equalsIgnoreCase(r4)
            if (r3 == 0) goto L_0x0136
            r20 = r22
            goto L_0x0140
        L_0x0136:
            java.lang.String r3 = "immutable"
            boolean r3 = r3.equalsIgnoreCase(r4)
            if (r3 == 0) goto L_0x0140
            r21 = r22
        L_0x0140:
            r4 = r5
            goto L_0x003d
        L_0x0143:
            r9 = -1
            r22 = 1
            int r6 = r6 + 1
            goto L_0x001b
        L_0x014a:
            if (r7 != 0) goto L_0x014f
            r22 = 0
            goto L_0x0151
        L_0x014f:
            r22 = r8
        L_0x0151:
            okhttp3.d r0 = new okhttp3.d
            r9 = r0
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.d.a(okhttp3.s):okhttp3.d");
    }

    public String toString() {
        String str = this.c;
        if (str != null) {
            return str;
        }
        String k2 = k();
        this.c = k2;
        return k2;
    }

    private String k() {
        StringBuilder sb = new StringBuilder();
        if (this.d) {
            sb.append("no-cache, ");
        }
        if (this.e) {
            sb.append("no-store, ");
        }
        if (this.f != -1) {
            sb.append("max-age=");
            sb.append(this.f);
            sb.append(", ");
        }
        if (this.g != -1) {
            sb.append("s-maxage=");
            sb.append(this.g);
            sb.append(", ");
        }
        if (this.h) {
            sb.append("private, ");
        }
        if (this.i) {
            sb.append("public, ");
        }
        if (this.j) {
            sb.append("must-revalidate, ");
        }
        if (this.k != -1) {
            sb.append("max-stale=");
            sb.append(this.k);
            sb.append(", ");
        }
        if (this.l != -1) {
            sb.append("min-fresh=");
            sb.append(this.l);
            sb.append(", ");
        }
        if (this.m) {
            sb.append("only-if-cached, ");
        }
        if (this.n) {
            sb.append("no-transform, ");
        }
        if (this.o) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}
