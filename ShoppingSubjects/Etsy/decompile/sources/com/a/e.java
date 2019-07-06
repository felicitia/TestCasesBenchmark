package com.a;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;

/* compiled from: HttpAsyncTask */
abstract class e extends AsyncTask<Void, Void, Void> {
    private final Uri a;
    private boolean b = false;

    /* access modifiers changed from: 0000 */
    public abstract void a(f fVar);

    /* access modifiers changed from: 0000 */
    public abstract void a(Exception exc);

    public e(Uri uri) {
        this.a = uri;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0053, code lost:
        if (r0 == null) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x007c, code lost:
        if (r0 == null) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x007e, code lost:
        r0.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0081, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0070 A[SYNTHETIC, Splitter:B:30:0x0070] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0085 A[SYNTHETIC, Splitter:B:39:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0093  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Void doInBackground(java.lang.Void... r7) {
        /*
            r6 = this;
            r7 = 0
            java.net.URL r0 = new java.net.URL     // Catch:{ Exception -> 0x0065, all -> 0x0060 }
            android.net.Uri r1 = r6.a     // Catch:{ Exception -> 0x0065, all -> 0x0060 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0065, all -> 0x0060 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0065, all -> 0x0060 }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ Exception -> 0x0065, all -> 0x0060 }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Exception -> 0x0065, all -> 0x0060 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            java.io.InputStream r4 = r0.getInputStream()     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x005d, all -> 0x0058 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0056 }
            r2.<init>()     // Catch:{ Exception -> 0x0056 }
        L_0x002a:
            java.lang.String r3 = r1.readLine()     // Catch:{ Exception -> 0x0056 }
            if (r3 == 0) goto L_0x0034
            r2.append(r3)     // Catch:{ Exception -> 0x0056 }
            goto L_0x002a
        L_0x0034:
            com.a.f r3 = new com.a.f     // Catch:{ Exception -> 0x0056 }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Exception -> 0x0056 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0056 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x0056 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0056 }
            r6.a(r3)     // Catch:{ Exception -> 0x0056 }
            if (r1 == 0) goto L_0x0053
            r1.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x0053
        L_0x004b:
            r1 = move-exception
            java.lang.String r2 = "BitlySDK"
            java.lang.String r3 = "Bitly SDK failed to close reader"
            android.util.Log.e(r2, r3, r1)
        L_0x0053:
            if (r0 == 0) goto L_0x0081
            goto L_0x007e
        L_0x0056:
            r2 = move-exception
            goto L_0x0068
        L_0x0058:
            r1 = move-exception
            r5 = r1
            r1 = r7
            r7 = r5
            goto L_0x0083
        L_0x005d:
            r2 = move-exception
            r1 = r7
            goto L_0x0068
        L_0x0060:
            r0 = move-exception
            r1 = r7
            r7 = r0
            r0 = r1
            goto L_0x0083
        L_0x0065:
            r2 = move-exception
            r0 = r7
            r1 = r0
        L_0x0068:
            r3 = 1
            r6.b = r3     // Catch:{ all -> 0x0082 }
            r6.a(r2)     // Catch:{ all -> 0x0082 }
            if (r1 == 0) goto L_0x007c
            r1.close()     // Catch:{ IOException -> 0x0074 }
            goto L_0x007c
        L_0x0074:
            r1 = move-exception
            java.lang.String r2 = "BitlySDK"
            java.lang.String r3 = "Bitly SDK failed to close reader"
            android.util.Log.e(r2, r3, r1)
        L_0x007c:
            if (r0 == 0) goto L_0x0081
        L_0x007e:
            r0.disconnect()
        L_0x0081:
            return r7
        L_0x0082:
            r7 = move-exception
        L_0x0083:
            if (r1 == 0) goto L_0x0091
            r1.close()     // Catch:{ IOException -> 0x0089 }
            goto L_0x0091
        L_0x0089:
            r1 = move-exception
            java.lang.String r2 = "BitlySDK"
            java.lang.String r3 = "Bitly SDK failed to close reader"
            android.util.Log.e(r2, r3, r1)
        L_0x0091:
            if (r0 == 0) goto L_0x0096
            r0.disconnect()
        L_0x0096:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.e.doInBackground(java.lang.Void[]):java.lang.Void");
    }

    public final void a() {
        if (VERSION.SDK_INT < 11 || AsyncTask.THREAD_POOL_EXECUTOR == null) {
            execute(new Void[0]);
        } else {
            executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public boolean b() {
        return this.b;
    }
}
