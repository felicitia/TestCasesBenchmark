package com.google.android.gms.ads.internal.gmsg;

import android.content.Context;
import android.support.annotation.Keep;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.aoe;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hb;
import com.google.android.gms.internal.ads.zzang;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
@KeepName
@bu
public class HttpClient implements ae<aoe> {
    private final Context mContext;
    private final zzang zzyf;

    @bu
    @VisibleForTesting
    static class a {
        private final String a;
        private final String b;

        public a(String str, String str2) {
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

    @bu
    @VisibleForTesting
    static class b {
        private final String a;
        private final URL b;
        private final ArrayList<a> c;
        private final String d;

        b(String str, URL url, ArrayList<a> arrayList, String str2) {
            this.a = str;
            this.b = url;
            this.c = arrayList;
            this.d = str2;
        }

        public final String a() {
            return this.a;
        }

        public final URL b() {
            return this.b;
        }

        public final ArrayList<a> c() {
            return this.c;
        }

        public final String d() {
            return this.d;
        }
    }

    @bu
    @VisibleForTesting
    class c {
        private final d a;
        private final boolean b;
        private final String c;

        public c(HttpClient httpClient, boolean z, d dVar, String str) {
            this.b = z;
            this.a = dVar;
            this.c = str;
        }

        public final String a() {
            return this.c;
        }

        public final d b() {
            return this.a;
        }

        public final boolean c() {
            return this.b;
        }
    }

    @bu
    @VisibleForTesting
    static class d {
        private final String a;
        private final int b;
        private final List<a> c;
        private final String d;

        d(String str, int i, List<a> list, String str2) {
            this.a = str;
            this.b = i;
            this.c = list;
            this.d = str2;
        }

        public final String a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }

        public final Iterable<a> c() {
            return this.c;
        }

        public final String d() {
            return this.d;
        }
    }

    public HttpClient(Context context, zzang zzang) {
        this.mContext = context;
        this.zzyf = zzang;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0104  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.ads.internal.gmsg.HttpClient.c zza(com.google.android.gms.ads.internal.gmsg.HttpClient.b r13) {
        /*
            r12 = this;
            r0 = 0
            r1 = 0
            java.net.URL r2 = r13.b()     // Catch:{ Exception -> 0x00f0, all -> 0x00ed }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ Exception -> 0x00f0, all -> 0x00ed }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ Exception -> 0x00f0, all -> 0x00ed }
            com.google.android.gms.internal.ads.hd r3 = com.google.android.gms.ads.internal.ao.e()     // Catch:{ Exception -> 0x00eb }
            android.content.Context r4 = r12.mContext     // Catch:{ Exception -> 0x00eb }
            com.google.android.gms.internal.ads.zzang r5 = r12.zzyf     // Catch:{ Exception -> 0x00eb }
            java.lang.String r5 = r5.zzcw     // Catch:{ Exception -> 0x00eb }
            r3.a(r4, r5, r0, r2)     // Catch:{ Exception -> 0x00eb }
            java.util.ArrayList r3 = r13.c()     // Catch:{ Exception -> 0x00eb }
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ Exception -> 0x00eb }
            int r4 = r3.size()     // Catch:{ Exception -> 0x00eb }
            r5 = r0
        L_0x0024:
            if (r5 >= r4) goto L_0x003a
            java.lang.Object r6 = r3.get(r5)     // Catch:{ Exception -> 0x00eb }
            int r5 = r5 + 1
            com.google.android.gms.ads.internal.gmsg.HttpClient$a r6 = (com.google.android.gms.ads.internal.gmsg.HttpClient.a) r6     // Catch:{ Exception -> 0x00eb }
            java.lang.String r7 = r6.a()     // Catch:{ Exception -> 0x00eb }
            java.lang.String r6 = r6.b()     // Catch:{ Exception -> 0x00eb }
            r2.addRequestProperty(r7, r6)     // Catch:{ Exception -> 0x00eb }
            goto L_0x0024
        L_0x003a:
            java.lang.String r3 = r13.d()     // Catch:{ Exception -> 0x00eb }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x00eb }
            r4 = 1
            if (r3 != 0) goto L_0x0064
            r2.setDoOutput(r4)     // Catch:{ Exception -> 0x00eb }
            java.lang.String r3 = r13.d()     // Catch:{ Exception -> 0x00eb }
            byte[] r3 = r3.getBytes()     // Catch:{ Exception -> 0x00eb }
            int r5 = r3.length     // Catch:{ Exception -> 0x00eb }
            r2.setFixedLengthStreamingMode(r5)     // Catch:{ Exception -> 0x00eb }
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x00eb }
            java.io.OutputStream r6 = r2.getOutputStream()     // Catch:{ Exception -> 0x00eb }
            r5.<init>(r6)     // Catch:{ Exception -> 0x00eb }
            r5.write(r3)     // Catch:{ Exception -> 0x00eb }
            r5.close()     // Catch:{ Exception -> 0x00eb }
            goto L_0x0065
        L_0x0064:
            r3 = r1
        L_0x0065:
            com.google.android.gms.internal.ads.jt r5 = new com.google.android.gms.internal.ads.jt     // Catch:{ Exception -> 0x00eb }
            r5.<init>()     // Catch:{ Exception -> 0x00eb }
            r5.a(r2, r3)     // Catch:{ Exception -> 0x00eb }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x00eb }
            r3.<init>()     // Catch:{ Exception -> 0x00eb }
            java.util.Map r6 = r2.getHeaderFields()     // Catch:{ Exception -> 0x00eb }
            if (r6 == 0) goto L_0x00b5
            java.util.Map r6 = r2.getHeaderFields()     // Catch:{ Exception -> 0x00eb }
            java.util.Set r6 = r6.entrySet()     // Catch:{ Exception -> 0x00eb }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Exception -> 0x00eb }
        L_0x0084:
            boolean r7 = r6.hasNext()     // Catch:{ Exception -> 0x00eb }
            if (r7 == 0) goto L_0x00b5
            java.lang.Object r7 = r6.next()     // Catch:{ Exception -> 0x00eb }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ Exception -> 0x00eb }
            java.lang.Object r8 = r7.getValue()     // Catch:{ Exception -> 0x00eb }
            java.util.List r8 = (java.util.List) r8     // Catch:{ Exception -> 0x00eb }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ Exception -> 0x00eb }
        L_0x009a:
            boolean r9 = r8.hasNext()     // Catch:{ Exception -> 0x00eb }
            if (r9 == 0) goto L_0x0084
            java.lang.Object r9 = r8.next()     // Catch:{ Exception -> 0x00eb }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Exception -> 0x00eb }
            com.google.android.gms.ads.internal.gmsg.HttpClient$a r10 = new com.google.android.gms.ads.internal.gmsg.HttpClient$a     // Catch:{ Exception -> 0x00eb }
            java.lang.Object r11 = r7.getKey()     // Catch:{ Exception -> 0x00eb }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ Exception -> 0x00eb }
            r10.<init>(r11, r9)     // Catch:{ Exception -> 0x00eb }
            r3.add(r10)     // Catch:{ Exception -> 0x00eb }
            goto L_0x009a
        L_0x00b5:
            com.google.android.gms.ads.internal.gmsg.HttpClient$d r6 = new com.google.android.gms.ads.internal.gmsg.HttpClient$d     // Catch:{ Exception -> 0x00eb }
            java.lang.String r13 = r13.a()     // Catch:{ Exception -> 0x00eb }
            int r7 = r2.getResponseCode()     // Catch:{ Exception -> 0x00eb }
            com.google.android.gms.ads.internal.ao.e()     // Catch:{ Exception -> 0x00eb }
            java.io.InputStreamReader r8 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00eb }
            java.io.InputStream r9 = r2.getInputStream()     // Catch:{ Exception -> 0x00eb }
            r8.<init>(r9)     // Catch:{ Exception -> 0x00eb }
            java.lang.String r8 = com.google.android.gms.internal.ads.hd.a(r8)     // Catch:{ Exception -> 0x00eb }
            r6.<init>(r13, r7, r3, r8)     // Catch:{ Exception -> 0x00eb }
            int r13 = r6.b()     // Catch:{ Exception -> 0x00eb }
            r5.a(r2, r13)     // Catch:{ Exception -> 0x00eb }
            java.lang.String r13 = r6.d()     // Catch:{ Exception -> 0x00eb }
            r5.a(r13)     // Catch:{ Exception -> 0x00eb }
            com.google.android.gms.ads.internal.gmsg.HttpClient$c r13 = new com.google.android.gms.ads.internal.gmsg.HttpClient$c     // Catch:{ Exception -> 0x00eb }
            r13.<init>(r12, r4, r6, r1)     // Catch:{ Exception -> 0x00eb }
            if (r2 == 0) goto L_0x00ea
            r2.disconnect()
        L_0x00ea:
            return r13
        L_0x00eb:
            r13 = move-exception
            goto L_0x00f2
        L_0x00ed:
            r13 = move-exception
            r2 = r1
            goto L_0x0102
        L_0x00f0:
            r13 = move-exception
            r2 = r1
        L_0x00f2:
            com.google.android.gms.ads.internal.gmsg.HttpClient$c r3 = new com.google.android.gms.ads.internal.gmsg.HttpClient$c     // Catch:{ all -> 0x0101 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x0101 }
            r3.<init>(r12, r0, r1, r13)     // Catch:{ all -> 0x0101 }
            if (r2 == 0) goto L_0x0100
            r2.disconnect()
        L_0x0100:
            return r3
        L_0x0101:
            r13 = move-exception
        L_0x0102:
            if (r2 == 0) goto L_0x0107
            r2.disconnect()
        L_0x0107:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.gmsg.HttpClient.zza(com.google.android.gms.ads.internal.gmsg.HttpClient$b):com.google.android.gms.ads.internal.gmsg.HttpClient$c");
    }

    private static JSONObject zza(d dVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("http_request_id", dVar.a());
            if (dVar.d() != null) {
                jSONObject.put("body", dVar.d());
            }
            JSONArray jSONArray = new JSONArray();
            for (a aVar : dVar.c()) {
                jSONArray.put(new JSONObject().put(ResponseConstants.KEY, aVar.a()).put(ResponseConstants.VALUE, aVar.b()));
            }
            jSONObject.put("headers", jSONArray);
            jSONObject.put("response_code", dVar.b());
            return jSONObject;
        } catch (JSONException e) {
            gv.b("Error constructing JSON for http response.", e);
            return jSONObject;
        }
    }

    private static b zzc(JSONObject jSONObject) {
        String optString = jSONObject.optString("http_request_id");
        String optString2 = jSONObject.optString("url");
        URL url = null;
        String optString3 = jSONObject.optString("post_body", null);
        try {
            url = new URL(optString2);
        } catch (MalformedURLException e) {
            gv.b("Error constructing http request.", e);
        }
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("headers");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new a(optJSONObject.optString(ResponseConstants.KEY), optJSONObject.optString(ResponseConstants.VALUE)));
            }
        }
        return new b(optString, url, arrayList, optString3);
    }

    @Keep
    @KeepName
    public JSONObject send(JSONObject jSONObject) {
        String str;
        JSONObject jSONObject2 = new JSONObject();
        try {
            str = jSONObject.optString("http_request_id");
            try {
                c zza = zza(zzc(jSONObject));
                if (zza.c()) {
                    jSONObject2.put(ResponseConstants.RESPONSE, zza(zza.b()));
                    jSONObject2.put("success", true);
                    return jSONObject2;
                }
                jSONObject2.put(ResponseConstants.RESPONSE, new JSONObject().put("http_request_id", str));
                jSONObject2.put("success", false);
                jSONObject2.put(ResponseConstants.REASON, zza.a());
                return jSONObject2;
            } catch (Exception e) {
                e = e;
                gv.b("Error executing http request.", e);
                try {
                    jSONObject2.put(ResponseConstants.RESPONSE, new JSONObject().put("http_request_id", str));
                    jSONObject2.put("success", false);
                    jSONObject2.put(ResponseConstants.REASON, e.toString());
                    return jSONObject2;
                } catch (JSONException e2) {
                    gv.b("Error executing http request.", e2);
                    return jSONObject2;
                }
            }
        } catch (Exception e3) {
            e = e3;
            str = "";
            gv.b("Error executing http request.", e);
            jSONObject2.put(ResponseConstants.RESPONSE, new JSONObject().put("http_request_id", str));
            jSONObject2.put("success", false);
            jSONObject2.put(ResponseConstants.REASON, e.toString());
            return jSONObject2;
        }
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        hb.a((Runnable) new af(this, map, (aoe) obj));
    }
}
