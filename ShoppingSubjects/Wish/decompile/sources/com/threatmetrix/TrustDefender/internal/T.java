package com.threatmetrix.TrustDefender.internal;

import com.threatmetrix.TrustDefender.internal.P.O;

class T {

    /* renamed from: int reason: not valid java name */
    private static final String f528int = TL.m331if(T.class);

    T() {
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0057 */
    /* renamed from: for reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m318for(java.lang.String r5) throws java.lang.InterruptedException {
        /*
            com.threatmetrix.TrustDefender.internal.PH r0 = com.threatmetrix.TrustDefender.internal.PH.m275do()
            boolean r0 = r0.f494char
            if (r0 == 0) goto L_0x0011
            com.threatmetrix.TrustDefender.internal.PH r0 = com.threatmetrix.TrustDefender.internal.PH.m275do()
            java.lang.String r5 = r0.m285for(r5)
            return r5
        L_0x0011:
            r0 = 0
            java.lang.String r1 = "MD5"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch:{ NoSuchAlgorithmException -> 0x005d }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x005c }
            r2.<init>(r5)     // Catch:{ FileNotFoundException -> 0x005c }
            r5 = 8192(0x2000, float:1.14794E-41)
            byte[] r5 = new byte[r5]
        L_0x0021:
            int r3 = r2.read(r5)     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            r4 = 0
            if (r3 <= 0) goto L_0x002c
            r1.update(r5, r4, r3)     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            goto L_0x0021
        L_0x002c:
            byte[] r5 = r1.digest()     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            java.math.BigInteger r1 = new java.math.BigInteger     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            r3 = 1
            r1.<init>(r3, r5)     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            r5 = 16
            java.lang.String r5 = r1.toString(r5)     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            java.lang.String r1 = "%32s"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            r3[r4] = r5     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            java.lang.String r5 = java.lang.String.format(r1, r3)     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            r1 = 32
            r3 = 48
            java.lang.String r5 = r5.replace(r1, r3)     // Catch:{ IOException -> 0x0057, all -> 0x0052 }
            r2.close()     // Catch:{ IOException -> 0x005b }
            goto L_0x005b
        L_0x0052:
            r5 = move-exception
            r2.close()     // Catch:{ IOException -> 0x0056 }
        L_0x0056:
            throw r5
        L_0x0057:
            r2.close()     // Catch:{ IOException -> 0x005a }
        L_0x005a:
            r5 = r0
        L_0x005b:
            return r5
        L_0x005c:
            return r0
        L_0x005d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.T.m318for(java.lang.String):java.lang.String");
    }

    /* renamed from: int reason: not valid java name */
    static String m319int(O o) throws InterruptedException {
        String str = P.m239catch(o);
        TL.m338new(f528int, "sourceDir: ".concat(String.valueOf(str)));
        if (NK.m203byte(str)) {
            return m318for(str);
        }
        return null;
    }
}
