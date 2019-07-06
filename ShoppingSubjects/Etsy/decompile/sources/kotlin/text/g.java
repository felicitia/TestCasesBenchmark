package kotlin.text;

import com.etsy.android.lib.models.ResponseConstants;
import kotlin.b.c;
import kotlin.jvm.internal.p;

/* compiled from: Regex.kt */
public final class g {
    private final String a;
    private final c b;

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.p.a((java.lang.Object) r2.b, (java.lang.Object) r3.b) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof kotlin.text.g
            if (r0 == 0) goto L_0x001d
            kotlin.text.g r3 = (kotlin.text.g) r3
            java.lang.String r0 = r2.a
            java.lang.String r1 = r3.a
            boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
            if (r0 == 0) goto L_0x001d
            kotlin.b.c r0 = r2.b
            kotlin.b.c r3 = r3.b
            boolean r3 = kotlin.jvm.internal.p.a(r0, r3)
            if (r3 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r3 = 0
            return r3
        L_0x001f:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.g.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        c cVar = this.b;
        if (cVar != null) {
            i = cVar.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MatchGroup(value=");
        sb.append(this.a);
        sb.append(", range=");
        sb.append(this.b);
        sb.append(")");
        return sb.toString();
    }

    public g(String str, c cVar) {
        p.b(str, ResponseConstants.VALUE);
        p.b(cVar, "range");
        this.a = str;
        this.b = cVar;
    }
}
