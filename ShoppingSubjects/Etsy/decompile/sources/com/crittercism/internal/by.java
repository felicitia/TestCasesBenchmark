package com.crittercism.internal;

import android.support.annotation.NonNull;
import com.crittercism.internal.ap.e;
import com.crittercism.internal.cd.a;

public final class by implements a {
    private ap a;
    private as b;
    private bo<ax> c;

    public by(@NonNull ap apVar, @NonNull as asVar, @NonNull bo<ax> boVar) {
        this.a = apVar;
        this.b = asVar;
        this.c = boVar;
    }

    private void a() {
        this.a.a((e<T>) ap.L, Boolean.valueOf(false));
        this.a.a((e<T>) ap.S, "");
    }

    private void b() {
        this.a.a((e<T>) ap.ad, Boolean.valueOf(false));
        this.a.a((e<T>) ap.T, "");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0088, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0089, code lost:
        com.crittercism.internal.cm.d("throttling due to missing events endpoint");
        b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0091, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b3, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b4, code lost:
        com.crittercism.internal.cm.d("throttling due to missing config endpoint");
        a();
        b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00bf, code lost:
        throw r7;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0088 A[Catch:{ JSONException -> 0x00c0, a -> 0x0092, JSONException -> 0x00b3, JSONException -> 0x00b3, a -> 0x0096, a -> 0x006a, JSONException -> 0x0088, JSONException -> 0x0088, a -> 0x006e, JSONException -> 0x00d7 }, ExcHandler: JSONException (r7v15 'e' org.json.JSONException A[CUSTOM_DECLARE, Catch:{  }]), Splitter:B:20:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b3 A[Catch:{ JSONException -> 0x00c0, a -> 0x0092, JSONException -> 0x00b3, JSONException -> 0x00b3, a -> 0x0096, a -> 0x006a, JSONException -> 0x0088, JSONException -> 0x0088, a -> 0x006e, JSONException -> 0x00d7 }, ExcHandler: JSONException (r7v12 'e' org.json.JSONException A[CUSTOM_DECLARE, Catch:{  }]), Splitter:B:12:0x0030] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.crittercism.internal.cb r7) {
        /*
            r6 = this;
            if (r7 == 0) goto L_0x00e1
            byte[] r0 = r7.b
            if (r0 != 0) goto L_0x0008
            goto L_0x00e1
        L_0x0008:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{  }
            java.lang.String r1 = new java.lang.String     // Catch:{  }
            byte[] r7 = r7.b     // Catch:{  }
            r1.<init>(r7)     // Catch:{  }
            r0.<init>(r1)     // Catch:{  }
            java.lang.String r7 = "refresh_interval_seconds"
            long r1 = r0.getLong(r7)     // Catch:{ JSONException -> 0x00cd }
            com.crittercism.internal.ap r7 = r6.a     // Catch:{ JSONException -> 0x00cd }
            com.crittercism.internal.ap$d r3 = com.crittercism.internal.ap.R     // Catch:{ JSONException -> 0x00cd }
            r4 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 * r4
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ JSONException -> 0x00cd }
            r7.a(r3, r1)     // Catch:{ JSONException -> 0x00cd }
            java.lang.String r7 = "endpoints"
            org.json.JSONObject r7 = r0.getJSONObject(r7)     // Catch:{ JSONException -> 0x00c0 }
            java.lang.String r0 = "config"
            java.lang.String r0 = r7.getString(r0)     // Catch:{ JSONException -> 0x00b3, a -> 0x0096 }
            com.crittercism.internal.as r1 = r6.b     // Catch:{ JSONException -> 0x00b3, a -> 0x0096 }
            r2 = 0
            java.lang.String r3 = "com.crittercism.dhubConfigUrl"
            java.net.URL r3 = com.crittercism.internal.as.a(r3, r0)     // Catch:{ a -> 0x0092, JSONException -> 0x00b3 }
            r1.g = r3     // Catch:{ a -> 0x0092, JSONException -> 0x00b3 }
            com.crittercism.internal.ap r1 = r6.a     // Catch:{ JSONException -> 0x00b3, a -> 0x0096 }
            com.crittercism.internal.ap$f r3 = com.crittercism.internal.ap.S     // Catch:{ JSONException -> 0x00b3, a -> 0x0096 }
            r1.a(r3, r0)     // Catch:{ JSONException -> 0x00b3, a -> 0x0096 }
            com.crittercism.internal.ap r0 = r6.a     // Catch:{ JSONException -> 0x00b3, a -> 0x0096 }
            com.crittercism.internal.ap$a r1 = com.crittercism.internal.ap.L     // Catch:{ JSONException -> 0x00b3, a -> 0x0096 }
            r3 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ JSONException -> 0x00b3, a -> 0x0096 }
            r0.a(r1, r3)     // Catch:{ JSONException -> 0x00b3, a -> 0x0096 }
            java.lang.String r0 = "events"
            java.lang.String r7 = r7.getString(r0)     // Catch:{ JSONException -> 0x0088, a -> 0x006e }
            com.crittercism.internal.as r0 = r6.b     // Catch:{ JSONException -> 0x0088, a -> 0x006e }
            java.lang.String r1 = "com.crittercism.dhubEventsUrl"
            java.net.URL r1 = com.crittercism.internal.as.a(r1, r7)     // Catch:{ a -> 0x006a, JSONException -> 0x0088 }
            r0.h = r1     // Catch:{ a -> 0x006a, JSONException -> 0x0088 }
            com.crittercism.internal.ap r0 = r6.a     // Catch:{ JSONException -> 0x0088, a -> 0x006e }
            com.crittercism.internal.ap$f r1 = com.crittercism.internal.ap.T     // Catch:{ JSONException -> 0x0088, a -> 0x006e }
            r0.a(r1, r7)     // Catch:{ JSONException -> 0x0088, a -> 0x006e }
            goto L_0x00d7
        L_0x006a:
            r7 = move-exception
            r0.h = r2     // Catch:{ JSONException -> 0x0088, a -> 0x006e }
            throw r7     // Catch:{ JSONException -> 0x0088, a -> 0x006e }
        L_0x006e:
            r7 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00d7 }
            java.lang.String r1 = "throttling due to invalid events endpoint: "
            r0.<init>(r1)     // Catch:{ JSONException -> 0x00d7 }
            java.lang.String r1 = r7.getMessage()     // Catch:{ JSONException -> 0x00d7 }
            r0.append(r1)     // Catch:{ JSONException -> 0x00d7 }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x00d7 }
            com.crittercism.internal.cm.d(r0)     // Catch:{ JSONException -> 0x00d7 }
            r6.b()     // Catch:{ JSONException -> 0x00d7 }
            throw r7     // Catch:{ JSONException -> 0x00d7 }
        L_0x0088:
            r7 = move-exception
            java.lang.String r0 = "throttling due to missing events endpoint"
            com.crittercism.internal.cm.d(r0)     // Catch:{ JSONException -> 0x00d7 }
            r6.b()     // Catch:{ JSONException -> 0x00d7 }
            throw r7     // Catch:{ JSONException -> 0x00d7 }
        L_0x0092:
            r7 = move-exception
            r1.g = r2     // Catch:{ JSONException -> 0x00b3, a -> 0x0096 }
            throw r7     // Catch:{ JSONException -> 0x00b3, a -> 0x0096 }
        L_0x0096:
            r7 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00d7 }
            java.lang.String r1 = "throttling due to invalid config endpoint: "
            r0.<init>(r1)     // Catch:{ JSONException -> 0x00d7 }
            java.lang.String r1 = r7.getMessage()     // Catch:{ JSONException -> 0x00d7 }
            r0.append(r1)     // Catch:{ JSONException -> 0x00d7 }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x00d7 }
            com.crittercism.internal.cm.d(r0)     // Catch:{ JSONException -> 0x00d7 }
            r6.a()     // Catch:{ JSONException -> 0x00d7 }
            r6.b()     // Catch:{ JSONException -> 0x00d7 }
            throw r7     // Catch:{ JSONException -> 0x00d7 }
        L_0x00b3:
            r7 = move-exception
            java.lang.String r0 = "throttling due to missing config endpoint"
            com.crittercism.internal.cm.d(r0)     // Catch:{ JSONException -> 0x00d7 }
            r6.a()     // Catch:{ JSONException -> 0x00d7 }
            r6.b()     // Catch:{ JSONException -> 0x00d7 }
            throw r7     // Catch:{ JSONException -> 0x00d7 }
        L_0x00c0:
            r7 = move-exception
            java.lang.String r0 = "no valid region endpoints"
            com.crittercism.internal.cm.d(r0)     // Catch:{ JSONException -> 0x00d7 }
            r6.a()     // Catch:{ JSONException -> 0x00d7 }
            r6.b()     // Catch:{ JSONException -> 0x00d7 }
            throw r7     // Catch:{ JSONException -> 0x00d7 }
        L_0x00cd:
            java.lang.String r7 = "error parsing config ttl"
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{  }
            r0.<init>()     // Catch:{  }
            com.crittercism.internal.cm.c(r7, r0)     // Catch:{  }
        L_0x00d7:
            com.crittercism.internal.bo<com.crittercism.internal.ax> r7 = r6.c
            if (r7 == 0) goto L_0x00e0
            com.crittercism.internal.bo<com.crittercism.internal.ax> r7 = r6.c
            r7.a()
        L_0x00e0:
            return
        L_0x00e1:
            com.crittercism.internal.bo<com.crittercism.internal.ax> r7 = r6.c
            if (r7 == 0) goto L_0x00ea
            com.crittercism.internal.bo<com.crittercism.internal.ax> r7 = r6.c
            r7.a()
        L_0x00ea:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.by.a(com.crittercism.internal.cb):void");
    }
}
