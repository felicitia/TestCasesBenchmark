package com.paypal.android.sdk.onetouch.core.metadata;

import android.os.Handler;
import android.os.Message;

public class s extends aa {
    private static final String a = "s";
    private Handler b;
    private String c;
    private String d;
    private String e;
    private a f;

    public s(String str, String str2, String str3, a aVar, Handler handler) {
        this.b = handler;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = aVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x00ae, code lost:
        if (r11 != null) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00b0, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00b3, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00d2, code lost:
        if (r11 == null) goto L_0x00d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00d5, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00dc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.lang.String r11) {
        /*
            r10 = this;
            java.lang.String r0 = ""
            r1 = 1
            r2 = 0
            java.net.URL r3 = new java.net.URL     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            r3.<init>(r11)     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            java.net.URLConnection r11 = r3.openConnection()     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            java.lang.Object r11 = com.google.firebase.perf.network.FirebasePerfUrlConnection.instrument(r11)     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            java.net.URLConnection r11 = (java.net.URLConnection) r11     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            java.net.HttpURLConnection r11 = (java.net.HttpURLConnection) r11     // Catch:{ Exception -> 0x00c1, all -> 0x00be }
            r4 = 60000(0xea60, float:8.4078E-41)
            r11.setReadTimeout(r4)     // Catch:{ Exception -> 0x00bc }
            r11.setConnectTimeout(r4)     // Catch:{ Exception -> 0x00bc }
            java.lang.String r4 = "GET"
            r11.setRequestMethod(r4)     // Catch:{ Exception -> 0x00bc }
            java.lang.String r4 = "User-Agent"
            java.lang.String r5 = "%s/%s/%s/%s/Android"
            r6 = 4
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x00bc }
            r7 = 0
            com.paypal.android.sdk.onetouch.core.metadata.a r8 = r10.f     // Catch:{ Exception -> 0x00bc }
            java.lang.String r8 = r8.a()     // Catch:{ Exception -> 0x00bc }
            r6[r7] = r8     // Catch:{ Exception -> 0x00bc }
            com.paypal.android.sdk.onetouch.core.metadata.a r7 = r10.f     // Catch:{ Exception -> 0x00bc }
            java.lang.String r7 = r7.b()     // Catch:{ Exception -> 0x00bc }
            r6[r1] = r7     // Catch:{ Exception -> 0x00bc }
            r7 = 2
            java.lang.String r8 = r10.e     // Catch:{ Exception -> 0x00bc }
            r6[r7] = r8     // Catch:{ Exception -> 0x00bc }
            r7 = 3
            java.lang.String r8 = r10.d     // Catch:{ Exception -> 0x00bc }
            r6[r7] = r8     // Catch:{ Exception -> 0x00bc }
            java.lang.String r5 = java.lang.String.format(r5, r6)     // Catch:{ Exception -> 0x00bc }
            r11.setRequestProperty(r4, r5)     // Catch:{ Exception -> 0x00bc }
            java.lang.String r4 = "Accept-Language"
            java.lang.String r5 = "en-us"
            r11.setRequestProperty(r4, r5)     // Catch:{ Exception -> 0x00bc }
            int r4 = r11.getResponseCode()     // Catch:{ Exception -> 0x00bc }
            java.lang.String r5 = a     // Catch:{ Exception -> 0x00bc }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00bc }
            java.lang.String r7 = "\nSending 'GET' request to URL : "
            r6.<init>(r7)     // Catch:{ Exception -> 0x00bc }
            r6.append(r3)     // Catch:{ Exception -> 0x00bc }
            java.lang.String r3 = r6.toString()     // Catch:{ Exception -> 0x00bc }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r5, r3)     // Catch:{ Exception -> 0x00bc }
            java.lang.String r3 = a     // Catch:{ Exception -> 0x00bc }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00bc }
            java.lang.String r6 = "Response Code : "
            r5.<init>(r6)     // Catch:{ Exception -> 0x00bc }
            r5.append(r4)     // Catch:{ Exception -> 0x00bc }
            java.lang.String r4 = r5.toString()     // Catch:{ Exception -> 0x00bc }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r3, r4)     // Catch:{ Exception -> 0x00bc }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00bc }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00bc }
            java.io.InputStream r5 = r11.getInputStream()     // Catch:{ Exception -> 0x00bc }
            r4.<init>(r5)     // Catch:{ Exception -> 0x00bc }
            r3.<init>(r4)     // Catch:{ Exception -> 0x00bc }
        L_0x008b:
            java.lang.String r2 = r3.readLine()     // Catch:{ Exception -> 0x00b7, all -> 0x00b4 }
            if (r2 == 0) goto L_0x00a2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b7, all -> 0x00b4 }
            r4.<init>()     // Catch:{ Exception -> 0x00b7, all -> 0x00b4 }
            r4.append(r0)     // Catch:{ Exception -> 0x00b7, all -> 0x00b4 }
            r4.append(r2)     // Catch:{ Exception -> 0x00b7, all -> 0x00b4 }
            java.lang.String r2 = r4.toString()     // Catch:{ Exception -> 0x00b7, all -> 0x00b4 }
            r0 = r2
            goto L_0x008b
        L_0x00a2:
            java.lang.String r2 = a     // Catch:{ Exception -> 0x00b7, all -> 0x00b4 }
            java.lang.String r4 = r0.toString()     // Catch:{ Exception -> 0x00b7, all -> 0x00b4 }
            com.paypal.android.sdk.onetouch.core.metadata.ai.a(r2, r4)     // Catch:{ Exception -> 0x00b7, all -> 0x00b4 }
            com.paypal.android.sdk.onetouch.core.metadata.af.a(r3)
            if (r11 == 0) goto L_0x00d5
        L_0x00b0:
            r11.disconnect()
            return r0
        L_0x00b4:
            r0 = move-exception
            r2 = r3
            goto L_0x00d7
        L_0x00b7:
            r2 = move-exception
            r9 = r3
            r3 = r2
            r2 = r9
            goto L_0x00c4
        L_0x00bc:
            r3 = move-exception
            goto L_0x00c4
        L_0x00be:
            r0 = move-exception
            r11 = r2
            goto L_0x00d7
        L_0x00c1:
            r11 = move-exception
            r3 = r11
            r11 = r2
        L_0x00c4:
            android.os.Handler r4 = r10.b     // Catch:{ all -> 0x00d6 }
            android.os.Handler r5 = r10.b     // Catch:{ all -> 0x00d6 }
            android.os.Message r1 = android.os.Message.obtain(r5, r1, r3)     // Catch:{ all -> 0x00d6 }
            r4.sendMessage(r1)     // Catch:{ all -> 0x00d6 }
            com.paypal.android.sdk.onetouch.core.metadata.af.a(r2)
            if (r11 == 0) goto L_0x00d5
            goto L_0x00b0
        L_0x00d5:
            return r0
        L_0x00d6:
            r0 = move-exception
        L_0x00d7:
            com.paypal.android.sdk.onetouch.core.metadata.af.a(r2)
            if (r11 == 0) goto L_0x00df
            r11.disconnect()
        L_0x00df:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.paypal.android.sdk.onetouch.core.metadata.s.a(java.lang.String):java.lang.String");
    }

    public void run() {
        if (this.b != null) {
            try {
                this.b.sendMessage(Message.obtain(this.b, 20, this.c));
                String a2 = a(this.c);
                ai.a(a, String.format("%s/%s/%s/%s/Android", new Object[]{this.f.a(), this.f.b(), this.e, this.d}));
                this.b.sendMessage(Message.obtain(this.b, 22, a2.toString()));
            } catch (Exception e2) {
                this.b.sendMessage(Message.obtain(this.b, 21, e2));
            } catch (Throwable th) {
                ab.a().b(this);
                throw th;
            }
            ab.a().b(this);
        }
    }
}
