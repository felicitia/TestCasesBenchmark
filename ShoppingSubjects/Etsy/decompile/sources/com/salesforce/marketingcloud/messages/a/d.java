package com.salesforce.marketingcloud.messages.a;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.ShopHomeSortOption;
import com.salesforce.marketingcloud.e.g.a;
import com.salesforce.marketingcloud.e.g.b;
import com.salesforce.marketingcloud.messages.a.b.c;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang3.CharUtils;
import org.json.JSONObject;

final class d extends a {
    private static final b a = new b();
    private static final c b = new c();
    private static final a c = new a();

    d(String str, String str2, String str3, Map<String, String> map, String str4, String str5, String str6, String str7, b.a aVar, String str8, Date date, Date date2, int i, int i2, String str9) {
        super(str, str2, str3, map, str4, str5, str6, str7, aVar, str8, date, date2, i, i2, str9);
    }

    static d b(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        Date date = new Date();
        Iterator keys = jSONObject.keys();
        Date date2 = date;
        int i = 0;
        int i2 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        Map map = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        b.a aVar = null;
        String str8 = null;
        Date date3 = null;
        String str9 = null;
        while (keys.hasNext()) {
            String str10 = (String) keys.next();
            if (!jSONObject2.isNull(str10)) {
                char c2 = 65535;
                switch (str10.hashCode()) {
                    case -1867885268:
                        if (str10.equals(ResponseConstants.SUBJECT)) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case -1349088399:
                        if (str10.equals(ShopHomeSortOption.SORT_CUSTOM)) {
                            c2 = 4;
                            break;
                        }
                        break;
                    case -873093151:
                        if (str10.equals("messageType")) {
                            c2 = 12;
                            break;
                        }
                        break;
                    case -389131437:
                        if (str10.equals("contentType")) {
                            c2 = CharUtils.CR;
                            break;
                        }
                        break;
                    case 3355:
                        if (str10.equals("id")) {
                            c2 = 9;
                            break;
                        }
                        break;
                    case 116079:
                        if (str10.equals("url")) {
                            c2 = 14;
                            break;
                        }
                        break;
                    case 3195150:
                        if (str10.equals("hash")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case 3288564:
                        if (str10.equals("keys")) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case 92899676:
                        if (str10.equals("alert")) {
                            c2 = 6;
                            break;
                        }
                        break;
                    case 103772132:
                        if (str10.equals(ResponseConstants.MEDIA)) {
                            c2 = 8;
                            break;
                        }
                        break;
                    case 109627663:
                        if (str10.equals("sound")) {
                            c2 = 7;
                            break;
                        }
                        break;
                    case 110371416:
                        if (str10.equals("title")) {
                            c2 = 5;
                            break;
                        }
                        break;
                    case 693933066:
                        if (str10.equals("requestId")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case 1308858452:
                        if (str10.equals("startDateUtc")) {
                            c2 = 10;
                            break;
                        }
                        break;
                    case 1670810043:
                        if (str10.equals("endDateUtc")) {
                            c2 = 11;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        str = jSONObject2.getString(str10);
                        break;
                    case 1:
                        str2 = jSONObject2.getString(str10);
                        break;
                    case 2:
                        str3 = jSONObject2.getString(str10);
                        break;
                    case 3:
                        map = a.a(jSONObject2, str10);
                        break;
                    case 4:
                        str4 = jSONObject2.getString(str10);
                        break;
                    case 5:
                        str5 = jSONObject2.getString(str10);
                        break;
                    case 6:
                        str6 = jSONObject2.getString(str10);
                        break;
                    case 7:
                        str7 = jSONObject2.getString(str10);
                        break;
                    case 8:
                        aVar = b.a(jSONObject2, str10);
                        break;
                    case 9:
                        str8 = jSONObject2.getString(str10);
                        break;
                    case 10:
                        date2 = c.a(jSONObject2, str10);
                        break;
                    case 11:
                        date3 = c.a(jSONObject2, str10);
                        break;
                    case 12:
                        i = jSONObject2.getInt(str10);
                        break;
                    case 13:
                        i2 = jSONObject2.getInt(str10);
                        break;
                    case 14:
                        str9 = jSONObject2.getString(str10);
                        break;
                }
            }
        }
        d dVar = new d(str, str2, str3, map, str4, str5, str6, str7, aVar, str8, date2, date3, i, i2, str9);
        return dVar;
    }
}
