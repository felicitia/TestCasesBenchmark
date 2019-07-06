package com.paytm.pgsdk;

import android.app.IntentService;
import android.content.Intent;

public class IntentServicePostNotification extends IntentService {
    public IntentServicePostNotification() {
        super("IntentServicePostNotification");
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            callHttp(intent.getExtras().getString("url"));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void callHttp(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            r1.<init>(r7)     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            java.net.URLConnection r7 = r1.openConnection()     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            java.lang.Object r7 = com.google.firebase.perf.network.FirebasePerfUrlConnection.instrument(r7)     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            java.net.URLConnection r7 = (java.net.URLConnection) r7     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            java.io.InputStream r0 = r7.getInputStream()     // Catch:{ Exception -> 0x0047 }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0047 }
            r1.<init>(r0)     // Catch:{ Exception -> 0x0047 }
            int r0 = r1.read()     // Catch:{ Exception -> 0x0047 }
        L_0x001f:
            r2 = -1
            if (r0 == r2) goto L_0x0044
            char r0 = (char) r0     // Catch:{ Exception -> 0x0047 }
            int r2 = r1.read()     // Catch:{ Exception -> 0x0047 }
            java.io.PrintStream r3 = java.lang.System.out     // Catch:{ Exception -> 0x0047 }
            r3.print(r0)     // Catch:{ Exception -> 0x0047 }
            java.lang.String r3 = "postnotification called response"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0047 }
            r4.<init>()     // Catch:{ Exception -> 0x0047 }
            r4.append(r0)     // Catch:{ Exception -> 0x0047 }
            java.lang.String r0 = ""
            r4.append(r0)     // Catch:{ Exception -> 0x0047 }
            java.lang.String r0 = r4.toString()     // Catch:{ Exception -> 0x0047 }
            com.paytm.pgsdk.Log.v(r3, r0)     // Catch:{ Exception -> 0x0047 }
            r0 = r2
            goto L_0x001f
        L_0x0044:
            if (r7 == 0) goto L_0x005a
            goto L_0x0057
        L_0x0047:
            r0 = move-exception
            goto L_0x0052
        L_0x0049:
            r7 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
            goto L_0x005c
        L_0x004e:
            r7 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
        L_0x0052:
            r0.printStackTrace()     // Catch:{ all -> 0x005b }
            if (r7 == 0) goto L_0x005a
        L_0x0057:
            r7.disconnect()
        L_0x005a:
            return
        L_0x005b:
            r0 = move-exception
        L_0x005c:
            if (r7 == 0) goto L_0x0061
            r7.disconnect()
        L_0x0061:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.paytm.pgsdk.IntentServicePostNotification.callHttp(java.lang.String):void");
    }
}
