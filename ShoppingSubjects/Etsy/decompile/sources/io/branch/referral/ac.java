package io.branch.referral;

import android.content.Context;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.ConnectionResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UniversalResourceAnalyser */
class ac {
    /* access modifiers changed from: private */
    public static JSONObject a;
    private static ac d;
    private final ArrayList<String> b;
    private final JSONObject c = new JSONObject();

    /* compiled from: UniversalResourceAnalyser */
    private static class a extends BranchAsyncTask<Void, Void, JSONObject> {
        private final m a;
        private final int b;

        private a(Context context) {
            this.b = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
            this.a = m.a(context);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0057, code lost:
            if (r1 != null) goto L_0x0059;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0059, code lost:
            r1.disconnect();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0068, code lost:
            if (r1 != null) goto L_0x0059;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x006b, code lost:
            return r7;
         */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0063  */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.json.JSONObject doInBackground(java.lang.Void... r7) {
            /*
                r6 = this;
                org.json.JSONObject r7 = new org.json.JSONObject
                r7.<init>()
                r0 = 0
                java.net.URL r1 = new java.net.URL     // Catch:{ Throwable -> 0x0067, all -> 0x005f }
                java.lang.String r2 = "https://cdn.branch.io/sdk/uriskiplist_v#.json"
                java.lang.String r3 = "#"
                org.json.JSONObject r4 = io.branch.referral.ac.a     // Catch:{ Throwable -> 0x0067, all -> 0x005f }
                java.lang.String r5 = "version"
                int r4 = r4.optInt(r5)     // Catch:{ Throwable -> 0x0067, all -> 0x005f }
                int r4 = r4 + 1
                java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ Throwable -> 0x0067, all -> 0x005f }
                java.lang.String r2 = r2.replace(r3, r4)     // Catch:{ Throwable -> 0x0067, all -> 0x005f }
                r1.<init>(r2)     // Catch:{ Throwable -> 0x0067, all -> 0x005f }
                java.net.URLConnection r1 = r1.openConnection()     // Catch:{ Throwable -> 0x0067, all -> 0x005f }
                javax.net.ssl.HttpsURLConnection r1 = (javax.net.ssl.HttpsURLConnection) r1     // Catch:{ Throwable -> 0x0067, all -> 0x005f }
                r0 = 1500(0x5dc, float:2.102E-42)
                r1.setConnectTimeout(r0)     // Catch:{ Throwable -> 0x0068, all -> 0x005d }
                r1.setReadTimeout(r0)     // Catch:{ Throwable -> 0x0068, all -> 0x005d }
                int r0 = r1.getResponseCode()     // Catch:{ Throwable -> 0x0068, all -> 0x005d }
                r2 = 200(0xc8, float:2.8E-43)
                if (r0 != r2) goto L_0x0057
                java.io.InputStream r0 = r1.getInputStream()     // Catch:{ Throwable -> 0x0068, all -> 0x005d }
                if (r0 == 0) goto L_0x0057
                java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0068, all -> 0x005d }
                java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0068, all -> 0x005d }
                java.io.InputStream r3 = r1.getInputStream()     // Catch:{ Throwable -> 0x0068, all -> 0x005d }
                r2.<init>(r3)     // Catch:{ Throwable -> 0x0068, all -> 0x005d }
                r0.<init>(r2)     // Catch:{ Throwable -> 0x0068, all -> 0x005d }
                org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0068, all -> 0x005d }
                java.lang.String r0 = r0.readLine()     // Catch:{ Throwable -> 0x0068, all -> 0x005d }
                r2.<init>(r0)     // Catch:{ Throwable -> 0x0068, all -> 0x005d }
                r7 = r2
            L_0x0057:
                if (r1 == 0) goto L_0x006b
            L_0x0059:
                r1.disconnect()
                goto L_0x006b
            L_0x005d:
                r7 = move-exception
                goto L_0x0061
            L_0x005f:
                r7 = move-exception
                r1 = r0
            L_0x0061:
                if (r1 == 0) goto L_0x0066
                r1.disconnect()
            L_0x0066:
                throw r7
            L_0x0067:
                r1 = r0
            L_0x0068:
                if (r1 == 0) goto L_0x006b
                goto L_0x0059
            L_0x006b:
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.ac.a.doInBackground(java.lang.Void[]):org.json.JSONObject");
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(JSONObject jSONObject) {
            super.onPostExecute(jSONObject);
            if (jSONObject.optInt(ResponseConstants.VERSION) > ac.a.optInt(ResponseConstants.VERSION)) {
                ac.a = jSONObject;
                this.a.a("skip_url_format_key", ac.a.toString());
            }
        }
    }

    public static ac a(Context context) {
        if (d == null) {
            d = new ac(context);
        }
        return d;
    }

    private ac(Context context) {
        try {
            this.c.putOpt(ResponseConstants.VERSION, Integer.valueOf(0));
            JSONArray jSONArray = new JSONArray();
            this.c.putOpt("uri_skip_list", jSONArray);
            jSONArray.put("^fb\\d+:");
            jSONArray.put("^li\\d+:");
            jSONArray.put("^pdk\\d+:");
            jSONArray.put("^twitterkit-.*:");
            jSONArray.put("^com\\.googleusercontent\\.apps\\.\\d+-.*:\\/oauth");
            jSONArray.put("^(?i)(?!(http|https):).*(:|:.*\\b)(password|o?auth|o?auth.?token|access|access.?token)\\b");
            jSONArray.put("^(?i)((http|https):\\/\\/).*[\\/|?|#].*\\b(password|o?auth|o?auth.?token|access|access.?token)\\b");
        } catch (JSONException unused) {
        }
        a = c(context);
        this.b = new ArrayList<>();
    }

    private JSONObject c(Context context) {
        m a2 = m.a(context);
        JSONObject jSONObject = new JSONObject();
        String v = a2.v("skip_url_format_key");
        if (TextUtils.isEmpty(v) || "bnc_no_value".equals(v)) {
            return this.c;
        }
        try {
            return new JSONObject(v);
        } catch (JSONException unused) {
            return jSONObject;
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(Context context) {
        try {
            new a(context).executeTask(new Void[0]);
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: 0000 */
    public String a(String str) {
        String str2;
        try {
            JSONArray optJSONArray = a.optJSONArray("uri_skip_list");
            if (optJSONArray != null) {
                int i = 0;
                while (true) {
                    if (i >= optJSONArray.length()) {
                        break;
                    }
                    try {
                        String string = optJSONArray.getString(i);
                        if (Pattern.compile(string).matcher(str).find()) {
                            str2 = string;
                            break;
                        }
                        i++;
                    } catch (JSONException unused) {
                    }
                }
            }
            str2 = null;
            if (str2 == null) {
                if (this.b.size() <= 0) {
                    return str;
                }
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    if (str.matches((String) it.next())) {
                        return str;
                    }
                }
            }
            return str2;
        } catch (Exception unused2) {
            return str;
        }
    }
}
