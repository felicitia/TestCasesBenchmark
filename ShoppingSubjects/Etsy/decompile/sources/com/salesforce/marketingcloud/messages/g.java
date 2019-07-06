package com.salesforce.marketingcloud.messages;

import com.salesforce.marketingcloud.e.g.a;
import com.salesforce.marketingcloud.e.g.b;
import java.util.Date;
import java.util.Map;

final class g extends a {
    private static final C0171c a = new C0171c();
    private static final a b = new a();
    private static final b c = new b();

    g(String str, String str2, String str3, String str4, c.a aVar, Date date, Date date2, int i, int i2, String str5, int i3, int i4, int i5, boolean z, int i6, int i7, String str6, Map<String, String> map, String str7) {
        super(str, str2, str3, str4, aVar, date, date2, i, i2, str5, i3, i4, i5, z, i6, i7, str6, map, str7);
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.salesforce.marketingcloud.messages.g b(org.json.JSONObject r25) {
        /*
            r0 = r25
            java.util.Iterator r1 = r25.keys()
            r2 = -1
            r3 = 0
            r4 = 0
            r16 = r2
            r17 = r16
            r20 = r17
            r13 = r3
            r14 = r13
            r18 = r14
            r19 = r18
            r21 = r19
            r6 = r4
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r15 = r12
            r22 = r15
            r23 = r22
            r24 = r23
        L_0x0025:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x01b0
            java.lang.Object r4 = r1.next()
            java.lang.String r4 = (java.lang.String) r4
            boolean r5 = r0.isNull(r4)
            if (r5 == 0) goto L_0x0038
            goto L_0x0025
        L_0x0038:
            int r5 = r4.hashCode()
            switch(r5) {
                case -1349088399: goto L_0x0109;
                case -1303951372: goto L_0x00fe;
                case -873093151: goto L_0x00f4;
                case -490041217: goto L_0x00e9;
                case -389131437: goto L_0x00de;
                case -91009325: goto L_0x00d3;
                case 3355: goto L_0x00c9;
                case 116079: goto L_0x00be;
                case 3288564: goto L_0x00b3;
                case 92899676: goto L_0x00a8;
                case 103772132: goto L_0x009d;
                case 109627663: goto L_0x0092;
                case 110371416: goto L_0x0087;
                case 384348315: goto L_0x007b;
                case 919804764: goto L_0x006f;
                case 1022935986: goto L_0x0063;
                case 1308858452: goto L_0x0058;
                case 1332118968: goto L_0x004c;
                case 1670810043: goto L_0x0041;
                default: goto L_0x003f;
            }
        L_0x003f:
            goto L_0x0114
        L_0x0041:
            java.lang.String r5 = "endDateUtc"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 6
            goto L_0x0115
        L_0x004c:
            java.lang.String r5 = "messageObjectPerPeriod"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 10
            goto L_0x0115
        L_0x0058:
            java.lang.String r5 = "startDateUtc"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 5
            goto L_0x0115
        L_0x0063:
            java.lang.String r5 = "numberOfPeriods"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 11
            goto L_0x0115
        L_0x006f:
            java.lang.String r5 = "isRollingPeriod"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 13
            goto L_0x0115
        L_0x007b:
            java.lang.String r5 = "periodType"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 12
            goto L_0x0115
        L_0x0087:
            java.lang.String r5 = "title"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 1
            goto L_0x0115
        L_0x0092:
            java.lang.String r5 = "sound"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 3
            goto L_0x0115
        L_0x009d:
            java.lang.String r5 = "media"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 4
            goto L_0x0115
        L_0x00a8:
            java.lang.String r5 = "alert"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 2
            goto L_0x0115
        L_0x00b3:
            java.lang.String r5 = "keys"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 17
            goto L_0x0115
        L_0x00be:
            java.lang.String r5 = "url"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 9
            goto L_0x0115
        L_0x00c9:
            java.lang.String r5 = "id"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = r3
            goto L_0x0115
        L_0x00d3:
            java.lang.String r5 = "openDirect"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 16
            goto L_0x0115
        L_0x00de:
            java.lang.String r5 = "contentType"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 8
            goto L_0x0115
        L_0x00e9:
            java.lang.String r5 = "proximity"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 15
            goto L_0x0115
        L_0x00f4:
            java.lang.String r5 = "messageType"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 7
            goto L_0x0115
        L_0x00fe:
            java.lang.String r5 = "messageLimit"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 14
            goto L_0x0115
        L_0x0109:
            java.lang.String r5 = "custom"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0114
            r5 = 18
            goto L_0x0115
        L_0x0114:
            r5 = r2
        L_0x0115:
            switch(r5) {
                case 0: goto L_0x01a9;
                case 1: goto L_0x01a2;
                case 2: goto L_0x019b;
                case 3: goto L_0x0194;
                case 4: goto L_0x018b;
                case 5: goto L_0x0182;
                case 6: goto L_0x0179;
                case 7: goto L_0x0172;
                case 8: goto L_0x016b;
                case 9: goto L_0x0164;
                case 10: goto L_0x015c;
                case 11: goto L_0x0154;
                case 12: goto L_0x014c;
                case 13: goto L_0x0144;
                case 14: goto L_0x013c;
                case 15: goto L_0x0134;
                case 16: goto L_0x012c;
                case 17: goto L_0x0122;
                case 18: goto L_0x011a;
                default: goto L_0x0118;
            }
        L_0x0118:
            goto L_0x0025
        L_0x011a:
            java.lang.String r4 = r0.getString(r4)
            r24 = r4
            goto L_0x0025
        L_0x0122:
            com.salesforce.marketingcloud.e.g$b r5 = c
            java.util.Map r4 = r5.a(r0, r4)
            r23 = r4
            goto L_0x0025
        L_0x012c:
            java.lang.String r4 = r0.getString(r4)
            r22 = r4
            goto L_0x0025
        L_0x0134:
            int r4 = r0.getInt(r4)
            r21 = r4
            goto L_0x0025
        L_0x013c:
            int r4 = r0.getInt(r4)
            r20 = r4
            goto L_0x0025
        L_0x0144:
            boolean r4 = r0.getBoolean(r4)
            r19 = r4
            goto L_0x0025
        L_0x014c:
            int r4 = r0.getInt(r4)
            r18 = r4
            goto L_0x0025
        L_0x0154:
            int r4 = r0.getInt(r4)
            r17 = r4
            goto L_0x0025
        L_0x015c:
            int r4 = r0.getInt(r4)
            r16 = r4
            goto L_0x0025
        L_0x0164:
            java.lang.String r4 = r0.getString(r4)
            r15 = r4
            goto L_0x0025
        L_0x016b:
            int r4 = r0.getInt(r4)
            r14 = r4
            goto L_0x0025
        L_0x0172:
            int r4 = r0.getInt(r4)
            r13 = r4
            goto L_0x0025
        L_0x0179:
            com.salesforce.marketingcloud.e.g$a r5 = b
            java.util.Date r4 = r5.a(r0, r4)
            r12 = r4
            goto L_0x0025
        L_0x0182:
            com.salesforce.marketingcloud.e.g$a r5 = b
            java.util.Date r4 = r5.a(r0, r4)
            r11 = r4
            goto L_0x0025
        L_0x018b:
            com.salesforce.marketingcloud.messages.c$c r5 = a
            com.salesforce.marketingcloud.messages.c$a r4 = r5.a(r0, r4)
            r10 = r4
            goto L_0x0025
        L_0x0194:
            java.lang.String r4 = r0.getString(r4)
            r9 = r4
            goto L_0x0025
        L_0x019b:
            java.lang.String r4 = r0.getString(r4)
            r8 = r4
            goto L_0x0025
        L_0x01a2:
            java.lang.String r4 = r0.getString(r4)
            r7 = r4
            goto L_0x0025
        L_0x01a9:
            java.lang.String r4 = r0.getString(r4)
            r6 = r4
            goto L_0x0025
        L_0x01b0:
            com.salesforce.marketingcloud.messages.g r0 = new com.salesforce.marketingcloud.messages.g
            r5 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.g.b(org.json.JSONObject):com.salesforce.marketingcloud.messages.g");
    }
}
