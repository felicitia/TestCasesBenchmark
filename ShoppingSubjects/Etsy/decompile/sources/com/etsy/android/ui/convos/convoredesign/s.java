package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.ResponseConstants;
import kotlin.jvm.internal.p;

/* compiled from: ConvoNotificationRepo.kt */
public final class s {
    private final String a;

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.p.a((java.lang.Object) r1.a, (java.lang.Object) ((com.etsy.android.ui.convos.convoredesign.s) r2).a) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof com.etsy.android.ui.convos.convoredesign.s
            if (r0 == 0) goto L_0x0013
            com.etsy.android.ui.convos.convoredesign.s r2 = (com.etsy.android.ui.convos.convoredesign.s) r2
            java.lang.String r0 = r1.a
            java.lang.String r2 = r2.a
            boolean r2 = kotlin.jvm.internal.p.a(r0, r2)
            if (r2 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r2 = 0
            return r2
        L_0x0015:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.convos.convoredesign.s.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.a;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ConvoNotification(username=");
        sb.append(this.a);
        sb.append(")");
        return sb.toString();
    }

    public s(String str) {
        p.b(str, ResponseConstants.USERNAME);
        this.a = str;
    }

    public final String a() {
        return this.a;
    }
}
