package com.crittercism.internal;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public final class ca {
    public SSLSocketFactory a;

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.crittercism.internal.cb a(com.crittercism.internal.bz r7) {
        /*
            r6 = this;
            com.crittercism.internal.cm.a(r7)
            r0 = 0
            javax.net.ssl.SSLSocketFactory r1 = r6.a     // Catch:{ Exception -> 0x009b }
            java.net.URL r2 = r7.a     // Catch:{ Exception -> 0x009b }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ Exception -> 0x009b }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ Exception -> 0x009b }
            java.util.Map<java.lang.String, java.lang.String> r3 = r7.c     // Catch:{ Exception -> 0x009b }
            java.util.Set r3 = r3.entrySet()     // Catch:{ Exception -> 0x009b }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Exception -> 0x009b }
        L_0x0018:
            boolean r4 = r3.hasNext()     // Catch:{ Exception -> 0x009b }
            if (r4 == 0) goto L_0x0034
            java.lang.Object r4 = r3.next()     // Catch:{ Exception -> 0x009b }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ Exception -> 0x009b }
            java.lang.Object r5 = r4.getKey()     // Catch:{ Exception -> 0x009b }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x009b }
            java.lang.Object r4 = r4.getValue()     // Catch:{ Exception -> 0x009b }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x009b }
            r2.addRequestProperty(r5, r4)     // Catch:{ Exception -> 0x009b }
            goto L_0x0018
        L_0x0034:
            r3 = 2500(0x9c4, float:3.503E-42)
            r2.setConnectTimeout(r3)     // Catch:{ Exception -> 0x009b }
            r2.setReadTimeout(r3)     // Catch:{ Exception -> 0x009b }
            java.lang.String r3 = "POST"
            java.lang.String r4 = r7.b     // Catch:{ Exception -> 0x009b }
            boolean r3 = r3.equals(r4)     // Catch:{ Exception -> 0x009b }
            r2.setDoOutput(r3)     // Catch:{ Exception -> 0x009b }
            java.lang.String r3 = r7.b     // Catch:{ Exception -> 0x009b }
            r2.setRequestMethod(r3)     // Catch:{ Exception -> 0x009b }
            boolean r3 = r2 instanceof javax.net.ssl.HttpsURLConnection     // Catch:{ Exception -> 0x009b }
            if (r3 == 0) goto L_0x0056
            r3 = r2
            javax.net.ssl.HttpsURLConnection r3 = (javax.net.ssl.HttpsURLConnection) r3     // Catch:{ Exception -> 0x009b }
            r3.setSSLSocketFactory(r1)     // Catch:{ Exception -> 0x009b }
        L_0x0056:
            java.lang.String r0 = "POST"
            java.lang.String r1 = r7.b     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            boolean r0 = r0.equalsIgnoreCase(r1)     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            if (r0 == 0) goto L_0x006b
            java.io.OutputStream r0 = r2.getOutputStream()     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            byte[] r1 = r7.a()     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            r0.write(r1)     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
        L_0x006b:
            int r0 = r2.getResponseCode()     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            r1 = 200(0xc8, float:2.8E-43)
            if (r0 < r1) goto L_0x0080
            r1 = 300(0x12c, float:4.2E-43)
            if (r0 >= r1) goto L_0x0080
            java.io.InputStream r1 = r2.getInputStream()     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            byte[] r1 = com.crittercism.internal.cn.a(r1)     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            goto L_0x0088
        L_0x0080:
            java.io.InputStream r1 = r2.getErrorStream()     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            byte[] r1 = com.crittercism.internal.cn.a(r1)     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
        L_0x0088:
            com.crittercism.internal.cb r3 = new com.crittercism.internal.cb     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            r3.<init>(r0, r1)     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            if (r2 == 0) goto L_0x00a6
            r2.disconnect()
            goto L_0x00a6
        L_0x0093:
            r7 = move-exception
            r0 = r2
            goto L_0x00ac
        L_0x0096:
            r1 = move-exception
            r0 = r2
            goto L_0x009c
        L_0x0099:
            r7 = move-exception
            goto L_0x00ac
        L_0x009b:
            r1 = move-exception
        L_0x009c:
            com.crittercism.internal.cb r3 = new com.crittercism.internal.cb     // Catch:{ all -> 0x0099 }
            r3.<init>(r1)     // Catch:{ all -> 0x0099 }
            if (r0 == 0) goto L_0x00a6
            r0.disconnect()
        L_0x00a6:
            java.net.URL r7 = r7.a
            com.crittercism.internal.cm.a(r7, r3)
            return r3
        L_0x00ac:
            if (r0 == 0) goto L_0x00b1
            r0.disconnect()
        L_0x00b1:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.ca.a(com.crittercism.internal.bz):com.crittercism.internal.cb");
    }

    public static SSLSocketFactory a() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, null, null);
            SSLSocketFactory socketFactory = instance.getSocketFactory();
            if (socketFactory instanceof k) {
                socketFactory = ((k) socketFactory).a();
            }
            return socketFactory;
        } catch (NoSuchAlgorithmException e) {
            cm.a(cl.NoTLSContext.a());
            cm.c("Error retrieving SSLSocketFactory", e);
            return null;
        } catch (KeyManagementException e2) {
            cm.a(cl.TLSContextInit.a());
            cm.c("Error retrieving SSLSocketFactory", e2);
            return null;
        }
    }
}
