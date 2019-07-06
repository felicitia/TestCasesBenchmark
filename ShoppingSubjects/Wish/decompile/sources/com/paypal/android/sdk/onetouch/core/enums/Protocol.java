package com.paypal.android.sdk.onetouch.core.enums;

public enum Protocol {
    v0("0.0"),
    v1("1.0"),
    v2("2.0"),
    v3("3.0");
    
    private final String mVersion;

    private Protocol(String str) {
        this.mVersion = str;
    }

    public String getVersion() {
        return this.mVersion;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.paypal.android.sdk.onetouch.core.enums.Protocol getProtocol(java.lang.String r1) {
        /*
            int r0 = r1.hashCode()
            switch(r0) {
                case 48: goto L_0x0026;
                case 49: goto L_0x001c;
                case 50: goto L_0x0012;
                case 51: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0030
        L_0x0008:
            java.lang.String r0 = "3"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0030
            r1 = 3
            goto L_0x0031
        L_0x0012:
            java.lang.String r0 = "2"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0030
            r1 = 2
            goto L_0x0031
        L_0x001c:
            java.lang.String r0 = "1"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0030
            r1 = 1
            goto L_0x0031
        L_0x0026:
            java.lang.String r0 = "0"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0030
            r1 = 0
            goto L_0x0031
        L_0x0030:
            r1 = -1
        L_0x0031:
            switch(r1) {
                case 0: goto L_0x0045;
                case 1: goto L_0x0042;
                case 2: goto L_0x003f;
                case 3: goto L_0x003c;
                default: goto L_0x0034;
            }
        L_0x0034:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "invalid protocol"
            r1.<init>(r0)
            throw r1
        L_0x003c:
            com.paypal.android.sdk.onetouch.core.enums.Protocol r1 = v3
            return r1
        L_0x003f:
            com.paypal.android.sdk.onetouch.core.enums.Protocol r1 = v2
            return r1
        L_0x0042:
            com.paypal.android.sdk.onetouch.core.enums.Protocol r1 = v1
            return r1
        L_0x0045:
            com.paypal.android.sdk.onetouch.core.enums.Protocol r1 = v0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.paypal.android.sdk.onetouch.core.enums.Protocol.getProtocol(java.lang.String):com.paypal.android.sdk.onetouch.core.enums.Protocol");
    }
}
