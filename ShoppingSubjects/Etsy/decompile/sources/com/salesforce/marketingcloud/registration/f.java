package com.salesforce.marketingcloud.registration;

import com.salesforce.marketingcloud.e.g.c;
import com.salesforce.marketingcloud.e.g.d;
import java.util.Map;
import java.util.Set;

final class f extends a {
    private static final d a = new d();
    private static final c b = new c();

    f(String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, String str6, boolean z4, int i, String str7, String str8, String str9, String str10, String str11, Set<String> set, Map<String, String> map) {
        super(str, str2, str3, str4, str5, z, z2, z3, str6, z4, i, str7, str8, str9, str10, str11, set, map);
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.salesforce.marketingcloud.registration.f b(org.json.JSONObject r25) {
        /*
            r0 = r25
            java.util.Iterator r1 = r25.keys()
            r3 = 0
            r5 = r3
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r13 = r9
            r16 = r13
            r17 = r16
            r18 = r17
            r19 = r18
            r20 = r19
            r21 = r20
            r22 = r21
            r10 = 0
            r11 = 0
            r12 = 0
            r14 = 0
            r15 = 0
        L_0x0020:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0193
            java.lang.Object r3 = r1.next()
            java.lang.String r3 = (java.lang.String) r3
            boolean r4 = r0.isNull(r3)
            if (r4 == 0) goto L_0x0033
            goto L_0x0020
        L_0x0033:
            r4 = -1
            int r23 = r3.hashCode()
            switch(r23) {
                case -2077180903: goto L_0x00f9;
                case -1753553769: goto L_0x00ef;
                case -1466951763: goto L_0x00e4;
                case -1097462182: goto L_0x00d9;
                case -1081149875: goto L_0x00cf;
                case -816799508: goto L_0x00c4;
                case -614157648: goto L_0x00ba;
                case -220152068: goto L_0x00af;
                case 98757: goto L_0x00a5;
                case 3215978: goto L_0x0099;
                case 3552281: goto L_0x008d;
                case 352676705: goto L_0x0082;
                case 405645655: goto L_0x0076;
                case 762782874: goto L_0x006b;
                case 1109191153: goto L_0x0060;
                case 1287929267: goto L_0x0055;
                case 1572937943: goto L_0x0049;
                case 1874684019: goto L_0x003d;
                default: goto L_0x003b;
            }
        L_0x003b:
            goto L_0x0104
        L_0x003d:
            java.lang.String r2 = "platform"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 12
            goto L_0x0105
        L_0x0049:
            java.lang.String r2 = "subscriberKey"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 11
            goto L_0x0105
        L_0x0055:
            java.lang.String r2 = "sdk_Version"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 3
            goto L_0x0105
        L_0x0060:
            java.lang.String r2 = "deviceID"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 1
            goto L_0x0105
        L_0x006b:
            java.lang.String r2 = "app_Version"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 4
            goto L_0x0105
        L_0x0076:
            java.lang.String r2 = "attributes"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 17
            goto L_0x0105
        L_0x0082:
            java.lang.String r2 = "proximity_Enabled"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 7
            goto L_0x0105
        L_0x008d:
            java.lang.String r2 = "tags"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 16
            goto L_0x0105
        L_0x0099:
            java.lang.String r2 = "hwid"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 13
            goto L_0x0105
        L_0x00a5:
            java.lang.String r2 = "dST"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 5
            goto L_0x0105
        L_0x00af:
            java.lang.String r2 = "push_Enabled"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 9
            goto L_0x0105
        L_0x00ba:
            java.lang.String r2 = "device_Token"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 2
            goto L_0x0105
        L_0x00c4:
            java.lang.String r2 = "platform_Version"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 8
            goto L_0x0105
        L_0x00cf:
            java.lang.String r2 = "signedString"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 0
            goto L_0x0105
        L_0x00d9:
            java.lang.String r2 = "locale"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 15
            goto L_0x0105
        L_0x00e4:
            java.lang.String r2 = "etAppId"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 14
            goto L_0x0105
        L_0x00ef:
            java.lang.String r2 = "location_Enabled"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 6
            goto L_0x0105
        L_0x00f9:
            java.lang.String r2 = "timeZone"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0104
            r2 = 10
            goto L_0x0105
        L_0x0104:
            r2 = r4
        L_0x0105:
            switch(r2) {
                case 0: goto L_0x018c;
                case 1: goto L_0x0185;
                case 2: goto L_0x017e;
                case 3: goto L_0x0177;
                case 4: goto L_0x0170;
                case 5: goto L_0x0169;
                case 6: goto L_0x0162;
                case 7: goto L_0x015b;
                case 8: goto L_0x0154;
                case 9: goto L_0x014d;
                case 10: goto L_0x0146;
                case 11: goto L_0x013e;
                case 12: goto L_0x0136;
                case 13: goto L_0x012e;
                case 14: goto L_0x0126;
                case 15: goto L_0x011e;
                case 16: goto L_0x0114;
                case 17: goto L_0x010a;
                default: goto L_0x0108;
            }
        L_0x0108:
            goto L_0x0020
        L_0x010a:
            com.salesforce.marketingcloud.e.g$c r2 = b
            java.util.Map r2 = r2.a(r0, r3)
            r22 = r2
            goto L_0x0020
        L_0x0114:
            com.salesforce.marketingcloud.e.g$d r2 = a
            java.util.Set r2 = r2.a(r0, r3)
            r21 = r2
            goto L_0x0020
        L_0x011e:
            java.lang.String r2 = r0.getString(r3)
            r20 = r2
            goto L_0x0020
        L_0x0126:
            java.lang.String r2 = r0.getString(r3)
            r19 = r2
            goto L_0x0020
        L_0x012e:
            java.lang.String r2 = r0.getString(r3)
            r18 = r2
            goto L_0x0020
        L_0x0136:
            java.lang.String r2 = r0.getString(r3)
            r17 = r2
            goto L_0x0020
        L_0x013e:
            java.lang.String r2 = r0.getString(r3)
            r16 = r2
            goto L_0x0020
        L_0x0146:
            int r2 = r0.getInt(r3)
            r15 = r2
            goto L_0x0020
        L_0x014d:
            boolean r2 = r0.getBoolean(r3)
            r14 = r2
            goto L_0x0020
        L_0x0154:
            java.lang.String r2 = r0.getString(r3)
            r13 = r2
            goto L_0x0020
        L_0x015b:
            boolean r2 = r0.getBoolean(r3)
            r12 = r2
            goto L_0x0020
        L_0x0162:
            boolean r2 = r0.getBoolean(r3)
            r11 = r2
            goto L_0x0020
        L_0x0169:
            boolean r2 = r0.getBoolean(r3)
            r10 = r2
            goto L_0x0020
        L_0x0170:
            java.lang.String r2 = r0.getString(r3)
            r9 = r2
            goto L_0x0020
        L_0x0177:
            java.lang.String r2 = r0.getString(r3)
            r8 = r2
            goto L_0x0020
        L_0x017e:
            java.lang.String r2 = r0.getString(r3)
            r7 = r2
            goto L_0x0020
        L_0x0185:
            java.lang.String r2 = r0.getString(r3)
            r6 = r2
            goto L_0x0020
        L_0x018c:
            java.lang.String r2 = r0.getString(r3)
            r5 = r2
            goto L_0x0020
        L_0x0193:
            com.salesforce.marketingcloud.registration.f r0 = new com.salesforce.marketingcloud.registration.f
            r4 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.registration.f.b(org.json.JSONObject):com.salesforce.marketingcloud.registration.f");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(41:0|(3:1|2|(1:4))|5|7|8|9|10|(1:12)|13|(2:15|16)|17|19|20|21|(2:23|24)|25|(2:27|28)|29|(2:31|32)|33|35|36|37|(2:39|40)|41|43|44|45|46|(1:48)|49|(2:51|52)|53|(2:55|56)|57|59|60|61|(2:63|64)|65|67) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x0074 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001d */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0023 A[Catch:{ JSONException -> 0x002c }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x007a A[Catch:{ JSONException -> 0x0083 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject u() {
        /*
            r4 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = r4.a()     // Catch:{ JSONException -> 0x0014 }
            if (r1 == 0) goto L_0x0014
            java.lang.String r1 = "signedString"
            java.lang.String r2 = r4.a()     // Catch:{ JSONException -> 0x0014 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0014 }
        L_0x0014:
            java.lang.String r1 = "deviceID"
            java.lang.String r2 = r4.b()     // Catch:{ JSONException -> 0x001d }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x001d }
        L_0x001d:
            java.lang.String r1 = r4.c()     // Catch:{ JSONException -> 0x002c }
            if (r1 == 0) goto L_0x002c
            java.lang.String r1 = "device_Token"
            java.lang.String r2 = r4.c()     // Catch:{ JSONException -> 0x002c }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x002c }
        L_0x002c:
            java.lang.String r1 = "sdk_Version"
            java.lang.String r2 = r4.d()     // Catch:{ JSONException -> 0x0035 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0035 }
        L_0x0035:
            java.lang.String r1 = "app_Version"
            java.lang.String r2 = r4.e()     // Catch:{ JSONException -> 0x003e }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x003e }
        L_0x003e:
            java.lang.String r1 = "dST"
            boolean r2 = r4.f()     // Catch:{ JSONException -> 0x0047 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0047 }
        L_0x0047:
            java.lang.String r1 = "location_Enabled"
            boolean r2 = r4.g()     // Catch:{ JSONException -> 0x0050 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0050 }
        L_0x0050:
            java.lang.String r1 = "proximity_Enabled"
            boolean r2 = r4.h()     // Catch:{ JSONException -> 0x0059 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0059 }
        L_0x0059:
            java.lang.String r1 = "platform_Version"
            java.lang.String r2 = r4.i()     // Catch:{ JSONException -> 0x0062 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0062 }
        L_0x0062:
            java.lang.String r1 = "push_Enabled"
            boolean r2 = r4.j()     // Catch:{ JSONException -> 0x006b }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x006b }
        L_0x006b:
            java.lang.String r1 = "timeZone"
            int r2 = r4.k()     // Catch:{ JSONException -> 0x0074 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0074 }
        L_0x0074:
            java.lang.String r1 = r4.l()     // Catch:{ JSONException -> 0x0083 }
            if (r1 == 0) goto L_0x0083
            java.lang.String r1 = "subscriberKey"
            java.lang.String r2 = r4.l()     // Catch:{ JSONException -> 0x0083 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0083 }
        L_0x0083:
            java.lang.String r1 = "platform"
            java.lang.String r2 = r4.m()     // Catch:{ JSONException -> 0x008c }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x008c }
        L_0x008c:
            java.lang.String r1 = "hwid"
            java.lang.String r2 = r4.n()     // Catch:{ JSONException -> 0x0095 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x0095 }
        L_0x0095:
            java.lang.String r1 = "etAppId"
            java.lang.String r2 = r4.o()     // Catch:{ JSONException -> 0x009e }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x009e }
        L_0x009e:
            java.lang.String r1 = "locale"
            java.lang.String r2 = r4.p()     // Catch:{ JSONException -> 0x00a7 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00a7 }
        L_0x00a7:
            com.salesforce.marketingcloud.e.g$d r1 = a
            java.lang.String r2 = "tags"
            java.util.Set r3 = r4.q()
            r1.a(r0, r2, r3)
            com.salesforce.marketingcloud.e.g$c r1 = b
            java.lang.String r2 = "attributes"
            java.util.Map r3 = r4.r()
            r1.a(r0, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.registration.f.u():org.json.JSONObject");
    }
}
