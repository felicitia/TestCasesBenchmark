package com.paypal.android.sdk.onetouch.core.metadata;

import android.net.Uri;
import java.util.Map;

public class z implements t {
    private static int g = 60000;
    private static int h = 60000;
    private final ae c = new ae(x.a());
    private byte[] d;
    private Uri e;
    private Map<String, String> f;

    static {
        z.class.getSimpleName();
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(byte[] r9) {
        /*
            r8 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ all -> 0x00b1 }
            android.net.Uri r2 = r8.e     // Catch:{ all -> 0x00b1 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00b1 }
            r1.<init>(r2)     // Catch:{ all -> 0x00b1 }
            java.net.URLConnection r1 = r1.openConnection()     // Catch:{ all -> 0x00b1 }
            java.lang.Object r1 = com.google.firebase.perf.network.FirebasePerfUrlConnection.instrument(r1)     // Catch:{ all -> 0x00b1 }
            java.net.URLConnection r1 = (java.net.URLConnection) r1     // Catch:{ all -> 0x00b1 }
            javax.net.ssl.HttpsURLConnection r1 = (javax.net.ssl.HttpsURLConnection) r1     // Catch:{ all -> 0x00b1 }
            int r2 = h     // Catch:{ all -> 0x00ae }
            r1.setReadTimeout(r2)     // Catch:{ all -> 0x00ae }
            int r2 = g     // Catch:{ all -> 0x00ae }
            r1.setConnectTimeout(r2)     // Catch:{ all -> 0x00ae }
            java.lang.String r2 = "POST"
            r1.setRequestMethod(r2)     // Catch:{ all -> 0x00ae }
            r2 = 1
            r1.setDoInput(r2)     // Catch:{ all -> 0x00ae }
            r1.setDoOutput(r2)     // Catch:{ all -> 0x00ae }
            com.paypal.android.sdk.onetouch.core.metadata.ae r2 = r8.c     // Catch:{ all -> 0x00ae }
            r1.setSSLSocketFactory(r2)     // Catch:{ all -> 0x00ae }
            java.util.Map<java.lang.String, java.lang.String> r2 = r8.f     // Catch:{ all -> 0x00ae }
            java.util.Set r2 = r2.entrySet()     // Catch:{ all -> 0x00ae }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00ae }
        L_0x003d:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x00ae }
            if (r3 == 0) goto L_0x005d
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x00ae }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x00ae }
            java.lang.Object r4 = r3.getKey()     // Catch:{ all -> 0x00ae }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00ae }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x00ae }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00ae }
            r1.setRequestProperty(r4, r3)     // Catch:{ all -> 0x00ae }
            goto L_0x003d
        L_0x005d:
            int r2 = r9.length     // Catch:{ all -> 0x00ae }
            r1.setFixedLengthStreamingMode(r2)     // Catch:{ all -> 0x00ae }
            java.io.OutputStream r2 = r1.getOutputStream()     // Catch:{ all -> 0x00ae }
            r2.write(r9)     // Catch:{ all -> 0x00ac }
            r2.flush()     // Catch:{ all -> 0x00ac }
            int r9 = r1.getResponseCode()     // Catch:{ all -> 0x00ac }
            r3 = 200(0xc8, float:2.8E-43)
            r4 = 0
            if (r9 != r3) goto L_0x009c
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ all -> 0x00ac }
            java.io.InputStream r5 = r1.getInputStream()     // Catch:{ all -> 0x00ac }
            r3.<init>(r5)     // Catch:{ all -> 0x00ac }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x0099 }
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0099 }
            r5.<init>()     // Catch:{ all -> 0x0099 }
        L_0x0086:
            int r6 = r3.read(r0)     // Catch:{ all -> 0x0099 }
            r7 = -1
            if (r6 == r7) goto L_0x0091
            r5.write(r0, r4, r6)     // Catch:{ all -> 0x0099 }
            goto L_0x0086
        L_0x0091:
            byte[] r0 = r5.toByteArray()     // Catch:{ all -> 0x0099 }
            r8.d = r0     // Catch:{ all -> 0x0099 }
            r0 = r3
            goto L_0x00a0
        L_0x0099:
            r9 = move-exception
            r0 = r3
            goto L_0x00b4
        L_0x009c:
            byte[] r3 = new byte[r4]     // Catch:{ all -> 0x00ac }
            r8.d = r3     // Catch:{ all -> 0x00ac }
        L_0x00a0:
            com.paypal.android.sdk.onetouch.core.metadata.af.a(r0)
            com.paypal.android.sdk.onetouch.core.metadata.af.a(r2)
            if (r1 == 0) goto L_0x00ab
            r1.disconnect()
        L_0x00ab:
            return r9
        L_0x00ac:
            r9 = move-exception
            goto L_0x00b4
        L_0x00ae:
            r9 = move-exception
            r2 = r0
            goto L_0x00b4
        L_0x00b1:
            r9 = move-exception
            r1 = r0
            r2 = r1
        L_0x00b4:
            com.paypal.android.sdk.onetouch.core.metadata.af.a(r0)
            com.paypal.android.sdk.onetouch.core.metadata.af.a(r2)
            if (r1 == 0) goto L_0x00bf
            r1.disconnect()
        L_0x00bf:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.paypal.android.sdk.onetouch.core.metadata.z.a(byte[]):int");
    }

    public final void a(Uri uri) {
        this.e = uri;
    }

    public final void a(Map<String, String> map) {
        this.f = map;
    }

    public final byte[] b() {
        return this.d;
    }
}
