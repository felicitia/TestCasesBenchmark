package com.etsy.android.messaging;

import com.etsy.android.lib.models.ResponseConstants;
import kotlin.jvm.internal.p;

/* compiled from: NotificationRepo.kt */
public final class f {
    private final String a;
    private final String b;

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.p.a((java.lang.Object) r2.b, (java.lang.Object) r3.b) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.etsy.android.messaging.f
            if (r0 == 0) goto L_0x001d
            com.etsy.android.messaging.f r3 = (com.etsy.android.messaging.f) r3
            java.lang.String r0 = r2.a
            java.lang.String r1 = r3.a
            boolean r0 = kotlin.jvm.internal.p.a(r0, r1)
            if (r0 == 0) goto L_0x001d
            java.lang.String r0 = r2.b
            java.lang.String r3 = r3.b
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
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.messaging.f.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NotificationData(type=");
        sb.append(this.a);
        sb.append(", username=");
        sb.append(this.b);
        sb.append(")");
        return sb.toString();
    }

    public f(String str, String str2) {
        p.b(str, "type");
        p.b(str2, ResponseConstants.USERNAME);
        this.a = str;
        this.b = str2;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }
}
