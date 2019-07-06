package com.crittercism.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import com.crittercism.internal.ap.b;
import com.crittercism.internal.ap.d;
import com.crittercism.internal.ap.e;
import com.crittercism.internal.cd.a;
import com.etsy.android.lib.models.ResponseConstants;
import java.net.URL;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public final class bw implements a {
    private av a;
    private Context b;
    private URL c;
    private ca d;
    private boolean e;
    private ap f;
    private bo<au> g;

    public bw(@NonNull URL url, @NonNull Context context, @NonNull av avVar, @NonNull ap apVar, ca caVar, @NonNull bo<au> boVar) {
        this.c = url;
        this.b = context;
        this.a = avVar;
        this.f = apVar;
        this.d = caVar;
        this.g = boVar;
    }

    private void a(JSONObject jSONObject, d dVar, ap.a aVar, ap.a aVar2, b bVar) {
        try {
            this.f.a((e<T>) dVar, Long.valueOf(jSONObject.getLong("interval") * 1000));
        } catch (JSONException unused) {
        }
        try {
            boolean z = jSONObject.getBoolean(ResponseConstants.ENABLED);
            this.f.a((e<T>) aVar2, Boolean.valueOf(z));
            this.f.a((e<T>) aVar, Boolean.valueOf(z));
        } catch (JSONException unused2) {
        }
        try {
            this.f.a((e<T>) bVar, Float.valueOf((float) jSONObject.getDouble(ResponseConstants.RATE)));
        } catch (JSONException unused3) {
        }
    }

    private void a(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("txnConfig");
            a(jSONObject2, ap.ak, ap.ai, ap.ah, ap.al);
            long optLong = jSONObject2.optLong("defaultTimeout", ((Long) ap.ar.b()).longValue());
            this.f.a((e<T>) ap.ar, Long.valueOf(optLong));
            JSONObject optJSONObject = jSONObject2.optJSONObject(ResponseConstants.TRANSACTIONS);
            if (optJSONObject != null) {
                Iterator keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    this.f.b(str, optJSONObject.getJSONObject(str).optLong("timeout", optLong));
                }
            }
        } catch (JSONException unused) {
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0088 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:56:0x014c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.crittercism.internal.cb r8) {
        /*
            r7 = this;
            if (r8 == 0) goto L_0x0159
            byte[] r0 = r8.b
            if (r0 != 0) goto L_0x0008
            goto L_0x0159
        L_0x0008:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x014f }
            java.lang.String r1 = new java.lang.String     // Catch:{ JSONException -> 0x014f }
            byte[] r8 = r8.b     // Catch:{ JSONException -> 0x014f }
            r1.<init>(r8)     // Catch:{ JSONException -> 0x014f }
            r0.<init>(r1)     // Catch:{ JSONException -> 0x014f }
            java.lang.String r8 = "internalExceptionReporting"
            r1 = 0
            boolean r8 = r0.optBoolean(r8, r1)     // Catch:{ JSONException -> 0x014f }
            if (r8 == 0) goto L_0x0022
            int r8 = com.crittercism.internal.cm.a.b     // Catch:{ JSONException -> 0x014f }
            com.crittercism.internal.cm.a = r8     // Catch:{ JSONException -> 0x014f }
            goto L_0x0026
        L_0x0022:
            int r8 = com.crittercism.internal.cm.a.c     // Catch:{ JSONException -> 0x014f }
            com.crittercism.internal.cm.a = r8     // Catch:{ JSONException -> 0x014f }
        L_0x0026:
            java.lang.String r8 = "needPkg"
            int r8 = r0.optInt(r8, r1)     // Catch:{ JSONException -> 0x014f }
            r1 = 1
            if (r8 != r1) goto L_0x008a
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ IOException | JSONException -> 0x0088 }
            r8.<init>()     // Catch:{ IOException | JSONException -> 0x0088 }
            java.lang.String r2 = "device_name"
            java.lang.String r3 = "Android"
            r8.putOpt(r2, r3)     // Catch:{ IOException | JSONException -> 0x0088 }
            java.lang.String r2 = "pkg"
            android.content.Context r3 = r7.b     // Catch:{ IOException | JSONException -> 0x0088 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ IOException | JSONException -> 0x0088 }
            r8.putOpt(r2, r3)     // Catch:{ IOException | JSONException -> 0x0088 }
            java.lang.String r2 = "app_id"
            com.crittercism.internal.av r3 = r7.a     // Catch:{ IOException | JSONException -> 0x0088 }
            java.lang.String r3 = r3.e     // Catch:{ IOException | JSONException -> 0x0088 }
            r8.putOpt(r2, r3)     // Catch:{ IOException | JSONException -> 0x0088 }
            java.lang.String r2 = "hashed_device_id"
            com.crittercism.internal.av r3 = r7.a     // Catch:{ IOException | JSONException -> 0x0088 }
            java.lang.String r3 = r3.h()     // Catch:{ IOException | JSONException -> 0x0088 }
            r8.putOpt(r2, r3)     // Catch:{ IOException | JSONException -> 0x0088 }
            java.lang.String r2 = "library_version"
            java.lang.String r3 = "5.8.10"
            r8.putOpt(r2, r3)     // Catch:{ IOException | JSONException -> 0x0088 }
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException | JSONException -> 0x0088 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException | JSONException -> 0x0088 }
            r3.<init>()     // Catch:{ IOException | JSONException -> 0x0088 }
            java.net.URL r4 = r7.c     // Catch:{ IOException | JSONException -> 0x0088 }
            r3.append(r4)     // Catch:{ IOException | JSONException -> 0x0088 }
            java.lang.String r4 = "/android_v2/update_package_name"
            r3.append(r4)     // Catch:{ IOException | JSONException -> 0x0088 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException | JSONException -> 0x0088 }
            r2.<init>(r3)     // Catch:{ IOException | JSONException -> 0x0088 }
            com.crittercism.internal.av r3 = r7.a     // Catch:{ IOException | JSONException -> 0x0088 }
            java.util.Map r3 = com.crittercism.internal.bz.a(r3)     // Catch:{ IOException | JSONException -> 0x0088 }
            com.crittercism.internal.bz r8 = com.crittercism.internal.bz.a(r2, r8, r3)     // Catch:{ IOException | JSONException -> 0x0088 }
            com.crittercism.internal.ca r2 = r7.d     // Catch:{ IOException | JSONException -> 0x0088 }
            r2.a(r8)     // Catch:{ IOException | JSONException -> 0x0088 }
        L_0x0088:
            r7.e = r1     // Catch:{ JSONException -> 0x014f }
        L_0x008a:
            java.lang.String r8 = "globalConfig"
            org.json.JSONObject r8 = r0.getJSONObject(r8)     // Catch:{ JSONException -> 0x00af }
            java.lang.String r1 = "configTTL"
            long r1 = r8.getLong(r1)     // Catch:{ JSONException -> 0x00a5 }
            com.crittercism.internal.ap r8 = r7.f     // Catch:{ JSONException -> 0x00a5 }
            com.crittercism.internal.ap$d r3 = com.crittercism.internal.ap.J     // Catch:{ JSONException -> 0x00a5 }
            r4 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 * r4
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ JSONException -> 0x00a5 }
            r8.a(r3, r1)     // Catch:{ JSONException -> 0x00a5 }
            goto L_0x00af
        L_0x00a5:
            java.lang.String r8 = "error parsing config ttl"
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ JSONException -> 0x00af }
            r1.<init>()     // Catch:{ JSONException -> 0x00af }
            com.crittercism.internal.cm.c(r8, r1)     // Catch:{ JSONException -> 0x00af }
        L_0x00af:
            java.lang.String r8 = "appLoadConfig"
            org.json.JSONObject r8 = r0.getJSONObject(r8)     // Catch:{ JSONException -> 0x00cf }
            com.crittercism.internal.ap$d r3 = com.crittercism.internal.ap.ab     // Catch:{ JSONException -> 0x00cf }
            com.crittercism.internal.ap$a r4 = com.crittercism.internal.ap.Z     // Catch:{ JSONException -> 0x00cf }
            com.crittercism.internal.ap$a r5 = com.crittercism.internal.ap.U     // Catch:{ JSONException -> 0x00cf }
            com.crittercism.internal.ap$b r6 = com.crittercism.internal.ap.Y     // Catch:{ JSONException -> 0x00cf }
            r1 = r7
            r2 = r8
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ JSONException -> 0x00cf }
            com.crittercism.internal.ap$d r3 = com.crittercism.internal.ap.X     // Catch:{ JSONException -> 0x00cf }
            com.crittercism.internal.ap$a r4 = com.crittercism.internal.ap.V     // Catch:{ JSONException -> 0x00cf }
            com.crittercism.internal.ap$a r5 = com.crittercism.internal.ap.U     // Catch:{ JSONException -> 0x00cf }
            com.crittercism.internal.ap$b r6 = com.crittercism.internal.ap.Y     // Catch:{ JSONException -> 0x00cf }
            r1 = r7
            r2 = r8
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ JSONException -> 0x00cf }
        L_0x00cf:
            java.lang.String r8 = "crashConfig"
            org.json.JSONObject r2 = r0.getJSONObject(r8)     // Catch:{ JSONException -> 0x00e1 }
            com.crittercism.internal.ap$d r3 = com.crittercism.internal.ap.o     // Catch:{ JSONException -> 0x00e1 }
            com.crittercism.internal.ap$a r4 = com.crittercism.internal.ap.n     // Catch:{ JSONException -> 0x00e1 }
            com.crittercism.internal.ap$a r5 = com.crittercism.internal.ap.m     // Catch:{ JSONException -> 0x00e1 }
            com.crittercism.internal.ap$b r6 = com.crittercism.internal.ap.q     // Catch:{ JSONException -> 0x00e1 }
            r1 = r7
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ JSONException -> 0x00e1 }
        L_0x00e1:
            java.lang.String r8 = "ndkConfig"
            org.json.JSONObject r2 = r0.getJSONObject(r8)     // Catch:{ JSONException -> 0x00f3 }
            com.crittercism.internal.ap$d r3 = com.crittercism.internal.ap.z     // Catch:{ JSONException -> 0x00f3 }
            com.crittercism.internal.ap$a r4 = com.crittercism.internal.ap.x     // Catch:{ JSONException -> 0x00f3 }
            com.crittercism.internal.ap$a r5 = com.crittercism.internal.ap.w     // Catch:{ JSONException -> 0x00f3 }
            com.crittercism.internal.ap$b r6 = com.crittercism.internal.ap.A     // Catch:{ JSONException -> 0x00f3 }
            r1 = r7
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ JSONException -> 0x00f3 }
        L_0x00f3:
            java.lang.String r8 = "heConfig"
            org.json.JSONObject r2 = r0.getJSONObject(r8)     // Catch:{ JSONException -> 0x0105 }
            com.crittercism.internal.ap$d r3 = com.crittercism.internal.ap.u     // Catch:{ JSONException -> 0x0105 }
            com.crittercism.internal.ap$a r4 = com.crittercism.internal.ap.s     // Catch:{ JSONException -> 0x0105 }
            com.crittercism.internal.ap$a r5 = com.crittercism.internal.ap.r     // Catch:{ JSONException -> 0x0105 }
            com.crittercism.internal.ap$b r6 = com.crittercism.internal.ap.v     // Catch:{ JSONException -> 0x0105 }
            r1 = r7
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ JSONException -> 0x0105 }
        L_0x0105:
            java.lang.String r8 = "metadataConfig"
            org.json.JSONObject r2 = r0.getJSONObject(r8)     // Catch:{ JSONException -> 0x0117 }
            com.crittercism.internal.ap$d r3 = com.crittercism.internal.ap.E     // Catch:{ JSONException -> 0x0117 }
            com.crittercism.internal.ap$a r4 = com.crittercism.internal.ap.C     // Catch:{ JSONException -> 0x0117 }
            com.crittercism.internal.ap$a r5 = com.crittercism.internal.ap.B     // Catch:{ JSONException -> 0x0117 }
            com.crittercism.internal.ap$b r6 = com.crittercism.internal.ap.F     // Catch:{ JSONException -> 0x0117 }
            r1 = r7
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ JSONException -> 0x0117 }
        L_0x0117:
            java.lang.String r8 = "apmConfig"
            org.json.JSONObject r8 = r0.getJSONObject(r8)     // Catch:{ JSONException -> 0x014c }
            com.crittercism.internal.ap$d r3 = com.crittercism.internal.ap.f     // Catch:{ JSONException -> 0x014c }
            com.crittercism.internal.ap$a r4 = com.crittercism.internal.ap.d     // Catch:{ JSONException -> 0x014c }
            com.crittercism.internal.ap$a r5 = com.crittercism.internal.ap.c     // Catch:{ JSONException -> 0x014c }
            com.crittercism.internal.ap$b r6 = com.crittercism.internal.ap.g     // Catch:{ JSONException -> 0x014c }
            r1 = r7
            r2 = r8
            r1.a(r2, r3, r4, r5, r6)     // Catch:{ JSONException -> 0x014c }
            java.lang.String r1 = "instrumentationEnabled"
            boolean r1 = r8.getBoolean(r1)     // Catch:{ JSONException -> 0x013b }
            com.crittercism.internal.ap r2 = r7.f     // Catch:{ JSONException -> 0x013b }
            com.crittercism.internal.ap$a r3 = com.crittercism.internal.ap.a     // Catch:{ JSONException -> 0x013b }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ JSONException -> 0x013b }
            r2.a(r3, r1)     // Catch:{ JSONException -> 0x013b }
        L_0x013b:
            java.lang.String r1 = "nougatEnabled"
            boolean r8 = r8.getBoolean(r1)     // Catch:{ JSONException -> 0x014c }
            com.crittercism.internal.ap r1 = r7.f     // Catch:{ JSONException -> 0x014c }
            com.crittercism.internal.ap$a r2 = com.crittercism.internal.ap.b     // Catch:{ JSONException -> 0x014c }
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)     // Catch:{ JSONException -> 0x014c }
            r1.a(r2, r8)     // Catch:{ JSONException -> 0x014c }
        L_0x014c:
            r7.a(r0)     // Catch:{ JSONException -> 0x014f }
        L_0x014f:
            com.crittercism.internal.bo<com.crittercism.internal.au> r8 = r7.g
            if (r8 == 0) goto L_0x0158
            com.crittercism.internal.bo<com.crittercism.internal.au> r8 = r7.g
            r8.a()
        L_0x0158:
            return
        L_0x0159:
            com.crittercism.internal.bo<com.crittercism.internal.au> r8 = r7.g
            if (r8 == 0) goto L_0x0162
            com.crittercism.internal.bo<com.crittercism.internal.au> r8 = r7.g
            r8.a()
        L_0x0162:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.bw.a(com.crittercism.internal.cb):void");
    }
}
