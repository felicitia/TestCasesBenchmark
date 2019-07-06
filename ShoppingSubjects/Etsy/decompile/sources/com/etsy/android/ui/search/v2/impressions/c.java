package com.etsy.android.ui.search.v2.impressions;

import kotlin.jvm.internal.p;

/* compiled from: SearchImpressionDbModel.kt */
public final class c {
    private final String a;
    private final String b;
    private final String c;

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
        if (kotlin.jvm.internal.p.a((java.lang.Object) r2.c, (java.lang.Object) r3.c) != false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0029
            boolean r0 = r3 instanceof com.etsy.android.ui.search.v2.impressions.c
            if (r0 == 0) goto L_0x0027
            com.etsy.android.ui.search.v2.impressions.c r3 = (com.etsy.android.ui.search.v2.impressions.c) r3
            java.lang.String r0 = r2.a
            java.lang.String r1 = r3.a
            boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
            if (r0 == 0) goto L_0x0027
            java.lang.String r0 = r2.b
            java.lang.String r1 = r3.b
            boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
            if (r0 == 0) goto L_0x0027
            java.lang.String r0 = r2.c
            java.lang.String r3 = r3.c
            boolean r3 = kotlin.jvm.internal.p.a(r0, r3)
            if (r3 == 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r3 = 0
            return r3
        L_0x0029:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.search.v2.impressions.c.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.c;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SearchImpressionDbModel(displayLocation=");
        sb.append(this.a);
        sb.append(", loggingKey=");
        sb.append(this.b);
        sb.append(", data=");
        sb.append(this.c);
        sb.append(")");
        return sb.toString();
    }

    public c(String str, String str2, String str3) {
        p.b(str, "displayLocation");
        p.b(str2, "loggingKey");
        p.b(str3, "data");
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }
}
