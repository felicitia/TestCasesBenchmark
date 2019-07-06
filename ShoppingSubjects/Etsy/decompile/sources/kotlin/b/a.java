package kotlin.b;

import kotlin.collections.ad;

/* compiled from: Progressions.kt */
public class a implements Iterable<Integer> {
    public static final C0191a a = new C0191a(null);
    private final int b;
    private final int c;
    private final int d;

    /* renamed from: kotlin.b.a$a reason: collision with other inner class name */
    /* compiled from: Progressions.kt */
    public static final class C0191a {
        private C0191a() {
        }

        public /* synthetic */ C0191a(o oVar) {
            this();
        }

        public final a a(int i, int i2, int i3) {
            return new a(i, i2, i3);
        }
    }

    public a(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero");
        }
        this.b = i;
        this.c = kotlin.internal.a.a(i, i2, i3);
        this.d = i3;
    }

    public final int a() {
        return this.b;
    }

    public final int b() {
        return this.c;
    }

    public final int c() {
        return this.d;
    }

    /* renamed from: d */
    public ad iterator() {
        return new b(this.b, this.c, this.d);
    }

    public boolean e() {
        if (this.d > 0) {
            if (this.b <= this.c) {
                return false;
            }
        } else if (this.b >= this.c) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        if (r2.d == r3.d) goto L_0x0027;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof kotlin.b.a
            if (r0 == 0) goto L_0x0029
            boolean r0 = r2.e()
            if (r0 == 0) goto L_0x0013
            r0 = r3
            kotlin.b.a r0 = (kotlin.b.a) r0
            boolean r0 = r0.e()
            if (r0 != 0) goto L_0x0027
        L_0x0013:
            int r0 = r2.b
            kotlin.b.a r3 = (kotlin.b.a) r3
            int r1 = r3.b
            if (r0 != r1) goto L_0x0029
            int r0 = r2.c
            int r1 = r3.c
            if (r0 != r1) goto L_0x0029
            int r0 = r2.d
            int r3 = r3.d
            if (r0 != r3) goto L_0x0029
        L_0x0027:
            r3 = 1
            goto L_0x002a
        L_0x0029:
            r3 = 0
        L_0x002a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.b.a.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        if (e()) {
            return -1;
        }
        return this.d + (31 * ((this.b * 31) + this.c));
    }

    public String toString() {
        StringBuilder sb;
        int i;
        if (this.d > 0) {
            sb = new StringBuilder();
            sb.append(this.b);
            sb.append("..");
            sb.append(this.c);
            sb.append(" step ");
            i = this.d;
        } else {
            sb = new StringBuilder();
            sb.append(this.b);
            sb.append(" downTo ");
            sb.append(this.c);
            sb.append(" step ");
            i = -this.d;
        }
        sb.append(i);
        return sb.toString();
    }
}
