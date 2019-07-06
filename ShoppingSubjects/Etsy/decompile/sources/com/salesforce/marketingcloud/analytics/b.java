package com.salesforce.marketingcloud.analytics;

abstract class b extends C$$AutoValue_PiCartItem {
    b(String str, int i, double d, String str2) {
        super(str, i, d, str2);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|(2:5|6)|7|9|10|11|12|(1:14)|15) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0020 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0026 A[Catch:{ JSONException -> 0x002f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject a() {
        /*
            r4 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "item"
            java.lang.String r2 = r4.item()     // Catch:{ JSONException -> 0x000e }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x000e }
        L_0x000e:
            java.lang.String r1 = "quantity"
            int r2 = r4.quantity()     // Catch:{ JSONException -> 0x0017 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0017 }
        L_0x0017:
            java.lang.String r1 = "price"
            double r2 = r4.price()     // Catch:{ JSONException -> 0x0020 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0020 }
        L_0x0020:
            java.lang.String r1 = r4.uniqueId()     // Catch:{ JSONException -> 0x002f }
            if (r1 == 0) goto L_0x002f
            java.lang.String r1 = "unique_id"
            java.lang.String r2 = r4.uniqueId()     // Catch:{ JSONException -> 0x002f }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x002f }
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.analytics.b.a():org.json.JSONObject");
    }
}
