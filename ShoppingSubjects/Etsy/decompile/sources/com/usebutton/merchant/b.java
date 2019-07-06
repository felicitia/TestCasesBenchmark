package com.usebutton.merchant;

import android.support.annotation.VisibleForTesting;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.requests.HttpUtil;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import org.apache.http.entity.mime.MIME;

/* compiled from: ButtonApiImpl */
final class b implements a {
    private static final String b = "b";
    private static final int c = ((int) TimeUnit.SECONDS.toMillis(5));
    private static final int d = ((int) TimeUnit.SECONDS.toMillis(15));
    private static a f;
    @VisibleForTesting
    String a = "https://api.usebutton.com";
    private final String e;

    static a a(String str) {
        if (f == null) {
            f = new b(str);
        }
        return f;
    }

    @VisibleForTesting
    b(String str) {
        this.e = str;
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r5v1, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r0v1 */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r5v8 */
    /* JADX WARNING: type inference failed for: r5v14, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: type inference failed for: r0v6, types: [com.usebutton.merchant.o$a] */
    /* JADX WARNING: type inference failed for: r0v7, types: [com.usebutton.merchant.o$a] */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r5v15 */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1
      assigns: []
      uses: []
      mth insns count: 128
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0160  */
    /* JADX WARNING: Unknown variable types count: 8 */
    @android.support.annotation.Nullable
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.usebutton.merchant.o a(java.lang.String r5, java.lang.String r6, boolean r7, java.util.Map<java.lang.String, java.lang.String> r8) throws com.usebutton.merchant.exception.ButtonNetworkException {
        /*
            r4 = this;
            r0 = 0
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            r1.<init>()     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            java.lang.String r2 = "application_id"
            r1.put(r2, r5)     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            java.lang.String r5 = "ifa"
            r1.put(r5, r6)     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            java.lang.String r5 = "ifa_limited"
            r1.put(r5, r7)     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            java.lang.String r5 = "signals"
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            r6.<init>(r8)     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            r1.put(r5, r6)     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            java.net.URL r5 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            r6.<init>()     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            java.lang.String r7 = r4.a     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            r6.append(r7)     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            java.lang.String r7 = "/v1/web/deferred-deeplink"
            r6.append(r7)     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            java.lang.String r6 = r6.toString()     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            r5.<init>(r6)     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            java.net.URLConnection r5 = r5.openConnection()     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ MalformedURLException -> 0x0150, IOException -> 0x0142, JSONException -> 0x0134 }
            r4.a(r5)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.io.OutputStreamWriter r6 = new java.io.OutputStreamWriter     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.io.OutputStream r7 = r5.getOutputStream()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r8 = "UTF-8"
            r6.<init>(r7, r8)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r7 = r1.toString()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r6.write(r7)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r6.close()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r6 = b     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r7.<init>()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r8 = "Request Body: "
            r7.append(r8)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r7.append(r1)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r7 = r7.toString()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            android.util.Log.d(r6, r7)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r6 = b     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r7.<init>()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r8 = "Response Code: "
            r7.append(r8)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            int r8 = r5.getResponseCode()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r7.append(r8)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r7 = r7.toString()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            android.util.Log.d(r6, r7)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            int r6 = r5.getResponseCode()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r7 = 400(0x190, float:5.6E-43)
            if (r6 >= r7) goto L_0x0106
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.io.InputStream r7 = r5.getInputStream()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r6.<init>(r7)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.io.InputStreamReader r8 = new java.io.InputStreamReader     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r1 = "UTF-8"
            r8.<init>(r6, r1)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r7.<init>(r8)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r6.<init>()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
        L_0x00a7:
            java.lang.String r8 = r7.readLine()     // Catch:{ all -> 0x0101 }
            if (r8 == 0) goto L_0x00b1
            r6.append(r8)     // Catch:{ all -> 0x0101 }
            goto L_0x00a7
        L_0x00b1:
            r7.close()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r6 = r6.toString()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r7.<init>(r6)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r6 = "object"
            org.json.JSONObject r6 = r7.optJSONObject(r6)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            if (r6 == 0) goto L_0x00fb
            java.lang.String r7 = "match"
            boolean r7 = r6.getBoolean(r7)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r8 = "id"
            java.lang.String r8 = r6.getString(r8)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r1 = "action"
            java.lang.String r1 = r6.getString(r1)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r2 = "attribution"
            org.json.JSONObject r6 = r6.optJSONObject(r2)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            if (r6 == 0) goto L_0x00f0
            java.lang.String r2 = "btn_ref"
            java.lang.String r2 = r6.getString(r2)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r3 = "utm_source"
            java.lang.String r6 = r6.optString(r3, r0)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            com.usebutton.merchant.o$a r0 = new com.usebutton.merchant.o$a     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r0.<init>(r2, r6)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
        L_0x00f0:
            com.usebutton.merchant.o r6 = new com.usebutton.merchant.o     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r6.<init>(r7, r8, r1, r0)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            if (r5 == 0) goto L_0x00fa
            r5.disconnect()
        L_0x00fa:
            return r6
        L_0x00fb:
            if (r5 == 0) goto L_0x0100
            r5.disconnect()
        L_0x0100:
            return r0
        L_0x0101:
            r6 = move-exception
            r7.close()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            throw r6     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
        L_0x0106:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r6.<init>()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r7 = "Unsuccessful Request. HTTP StatusCode: "
            r6.append(r7)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            int r7 = r5.getResponseCode()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r6.append(r7)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r6 = r6.toString()     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            java.lang.String r7 = b     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            android.util.Log.e(r7, r6)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            com.usebutton.merchant.exception.ButtonNetworkException r7 = new com.usebutton.merchant.exception.ButtonNetworkException     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            r7.<init>(r6)     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
            throw r7     // Catch:{ MalformedURLException -> 0x012e, IOException -> 0x012b, JSONException -> 0x0128, all -> 0x0126 }
        L_0x0126:
            r6 = move-exception
            goto L_0x015e
        L_0x0128:
            r6 = move-exception
            r0 = r5
            goto L_0x0135
        L_0x012b:
            r6 = move-exception
            r0 = r5
            goto L_0x0143
        L_0x012e:
            r6 = move-exception
            r0 = r5
            goto L_0x0151
        L_0x0131:
            r6 = move-exception
            r5 = r0
            goto L_0x015e
        L_0x0134:
            r6 = move-exception
        L_0x0135:
            java.lang.String r5 = b     // Catch:{ all -> 0x0131 }
            java.lang.String r7 = "JSONException has occurred"
            android.util.Log.e(r5, r7, r6)     // Catch:{ all -> 0x0131 }
            com.usebutton.merchant.exception.ButtonNetworkException r5 = new com.usebutton.merchant.exception.ButtonNetworkException     // Catch:{ all -> 0x0131 }
            r5.<init>(r6)     // Catch:{ all -> 0x0131 }
            throw r5     // Catch:{ all -> 0x0131 }
        L_0x0142:
            r6 = move-exception
        L_0x0143:
            java.lang.String r5 = b     // Catch:{ all -> 0x0131 }
            java.lang.String r7 = "IOException has occurred"
            android.util.Log.e(r5, r7, r6)     // Catch:{ all -> 0x0131 }
            com.usebutton.merchant.exception.ButtonNetworkException r5 = new com.usebutton.merchant.exception.ButtonNetworkException     // Catch:{ all -> 0x0131 }
            r5.<init>(r6)     // Catch:{ all -> 0x0131 }
            throw r5     // Catch:{ all -> 0x0131 }
        L_0x0150:
            r6 = move-exception
        L_0x0151:
            java.lang.String r5 = b     // Catch:{ all -> 0x0131 }
            java.lang.String r7 = "MalformedURLException has occurred"
            android.util.Log.e(r5, r7, r6)     // Catch:{ all -> 0x0131 }
            com.usebutton.merchant.exception.ButtonNetworkException r5 = new com.usebutton.merchant.exception.ButtonNetworkException     // Catch:{ all -> 0x0131 }
            r5.<init>(r6)     // Catch:{ all -> 0x0131 }
            throw r5     // Catch:{ all -> 0x0131 }
        L_0x015e:
            if (r5 == 0) goto L_0x0163
            r5.disconnect()
        L_0x0163:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.usebutton.merchant.b.a(java.lang.String, java.lang.String, boolean, java.util.Map):com.usebutton.merchant.o");
    }

    private void a(HttpURLConnection httpURLConnection) throws ProtocolException {
        httpURLConnection.setConnectTimeout(c);
        httpURLConnection.setReadTimeout(d);
        httpURLConnection.setRequestProperty("User-Agent", a());
        httpURLConnection.setRequestProperty("Accept", HttpUtil.JSON_CONTENT_TYPE);
        httpURLConnection.setRequestProperty(MIME.CONTENT_TYPE, HttpUtil.JSON_CONTENT_TYPE);
        httpURLConnection.setRequestMethod(BaseHttpRequest.POST);
        httpURLConnection.setDoOutput(true);
    }

    private String a() {
        return this.e;
    }
}
