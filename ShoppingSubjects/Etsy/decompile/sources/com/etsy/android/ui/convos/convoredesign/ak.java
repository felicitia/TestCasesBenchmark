package com.etsy.android.ui.convos.convoredesign;

import kotlin.jvm.internal.p;

/* compiled from: ConvoListItem.kt */
public final class ak {
    private String a;
    private String b;
    private String c;
    private String d;
    private final String e;

    public ak() {
        this(null, null, null, null, null, 31, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0038, code lost:
        if (kotlin.jvm.internal.p.a((java.lang.Object) r2.e, (java.lang.Object) r3.e) != false) goto L_0x003d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x003d
            boolean r0 = r3 instanceof com.etsy.android.ui.convos.convoredesign.ak
            if (r0 == 0) goto L_0x003b
            com.etsy.android.ui.convos.convoredesign.ak r3 = (com.etsy.android.ui.convos.convoredesign.ak) r3
            java.lang.String r0 = r2.a
            java.lang.String r1 = r3.a
            boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
            if (r0 == 0) goto L_0x003b
            java.lang.String r0 = r2.b
            java.lang.String r1 = r3.b
            boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
            if (r0 == 0) goto L_0x003b
            java.lang.String r0 = r2.c
            java.lang.String r1 = r3.c
            boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
            if (r0 == 0) goto L_0x003b
            java.lang.String r0 = r2.d
            java.lang.String r1 = r3.d
            boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
            if (r0 == 0) goto L_0x003b
            java.lang.String r0 = r2.e
            java.lang.String r3 = r3.e
            boolean r3 = kotlin.jvm.internal.p.a(r0, r3)
            if (r3 == 0) goto L_0x003b
            goto L_0x003d
        L_0x003b:
            r3 = 0
            return r3
        L_0x003d:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.convos.convoredesign.ak.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.c;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.d;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.e;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkCard(title=");
        sb.append(this.a);
        sb.append(", subtitle=");
        sb.append(this.b);
        sb.append(", imageUrl=");
        sb.append(this.c);
        sb.append(", url=");
        sb.append(this.d);
        sb.append(", listingId=");
        sb.append(this.e);
        sb.append(")");
        return sb.toString();
    }

    public ak(String str, String str2, String str3, String str4, String str5) {
        p.b(str, "title");
        p.b(str2, "subtitle");
        p.b(str3, "imageUrl");
        p.b(str4, "url");
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
    }

    public final String a() {
        return this.a;
    }

    public final void a(String str) {
        p.b(str, "<set-?>");
        this.a = str;
    }

    public final String b() {
        return this.b;
    }

    public final void b(String str) {
        p.b(str, "<set-?>");
        this.b = str;
    }

    public final String c() {
        return this.c;
    }

    public final void c(String str) {
        p.b(str, "<set-?>");
        this.c = str;
    }

    public final String d() {
        return this.d;
    }

    public /* synthetic */ ak(String str, String str2, String str3, String str4, String str5, int i, o oVar) {
        if ((i & 1) != 0) {
            str = "";
        }
        if ((i & 2) != 0) {
            str2 = "";
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = "";
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = "";
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = null;
        }
        this(str, str6, str7, str8, str5);
    }

    public final String e() {
        return this.e;
    }
}
