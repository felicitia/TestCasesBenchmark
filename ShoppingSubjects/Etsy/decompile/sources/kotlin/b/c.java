package kotlin.b;

/* compiled from: Ranges.kt */
public final class c extends a {
    public static final a b = new a(null);
    /* access modifiers changed from: private */
    public static final c c = new c(1, 0);

    /* compiled from: Ranges.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }

        public final c a() {
            return c.c;
        }
    }

    public c(int i, int i2) {
        super(i, i2, 1);
    }

    public Integer f() {
        return Integer.valueOf(a());
    }

    public Integer g() {
        return Integer.valueOf(b());
    }

    public boolean e() {
        return a() > b();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0027, code lost:
        if (b() == r3.b()) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof kotlin.b.c
            if (r0 == 0) goto L_0x002b
            boolean r0 = r2.e()
            if (r0 == 0) goto L_0x0013
            r0 = r3
            kotlin.b.c r0 = (kotlin.b.c) r0
            boolean r0 = r0.e()
            if (r0 != 0) goto L_0x0029
        L_0x0013:
            int r0 = r2.a()
            kotlin.b.c r3 = (kotlin.b.c) r3
            int r1 = r3.a()
            if (r0 != r1) goto L_0x002b
            int r0 = r2.b()
            int r3 = r3.b()
            if (r0 != r3) goto L_0x002b
        L_0x0029:
            r3 = 1
            goto L_0x002c
        L_0x002b:
            r3 = 0
        L_0x002c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.b.c.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        if (e()) {
            return -1;
        }
        return (31 * a()) + b();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(a());
        sb.append("..");
        sb.append(b());
        return sb.toString();
    }
}
