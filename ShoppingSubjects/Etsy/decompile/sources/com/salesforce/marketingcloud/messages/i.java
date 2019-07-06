package com.salesforce.marketingcloud.messages;

import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.location.b;
import com.salesforce.marketingcloud.location.b.a;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

final class i extends b {
    private static final a a = new a();
    private static final d b = new d();

    i(String str, b bVar, int i, String str2, int i2, int i3, int i4, String str3, String str4, List<c> list) {
        super(str, bVar, i, str2, i2, i3, i4, str3, str4, list);
    }

    static i b(JSONObject jSONObject) {
        List emptyList = Collections.emptyList();
        Iterator keys = jSONObject.keys();
        List list = emptyList;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        String str = null;
        b bVar = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (keys.hasNext()) {
            String str5 = (String) keys.next();
            if (!jSONObject.isNull(str5)) {
                char c = 65535;
                switch (str5.hashCode()) {
                    case -1724546052:
                        if (str5.equals("description")) {
                            c = 8;
                            break;
                        }
                        break;
                    case -1364013995:
                        if (str5.equals("center")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -938578798:
                        if (str5.equals(ResponseConstants.RADIUS)) {
                            c = 2;
                            break;
                        }
                        break;
                    case -462094004:
                        if (str5.equals(ResponseConstants.MESSAGES)) {
                            c = 9;
                            break;
                        }
                        break;
                    case -58277745:
                        if (str5.equals("locationType")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 3355:
                        if (str5.equals("id")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 3373707:
                        if (str5.equals(ResponseConstants.NAME)) {
                            c = 7;
                            break;
                        }
                        break;
                    case 103658937:
                        if (str5.equals("major")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 103901109:
                        if (str5.equals("minor")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 1646829786:
                        if (str5.equals("proximityUuid")) {
                            c = 3;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        str = jSONObject.getString(str5);
                        break;
                    case 1:
                        bVar = a.a(jSONObject, str5);
                        break;
                    case 2:
                        i = jSONObject.getInt(str5);
                        break;
                    case 3:
                        str2 = jSONObject.getString(str5);
                        break;
                    case 4:
                        i2 = jSONObject.getInt(str5);
                        break;
                    case 5:
                        i3 = jSONObject.getInt(str5);
                        break;
                    case 6:
                        i4 = jSONObject.getInt(str5);
                        break;
                    case 7:
                        str3 = jSONObject.getString(str5);
                        break;
                    case 8:
                        str4 = jSONObject.getString(str5);
                        break;
                    case 9:
                        list = b.a(jSONObject, str5);
                        break;
                }
            }
        }
        i iVar = new i(str, bVar, i, str2, i2, i3, i4, str3, str4, list);
        return iVar;
    }
}
