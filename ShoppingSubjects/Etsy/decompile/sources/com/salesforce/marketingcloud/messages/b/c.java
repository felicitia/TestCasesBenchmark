package com.salesforce.marketingcloud.messages.b;

import com.salesforce.marketingcloud.location.b;
import com.salesforce.marketingcloud.location.b.a;
import com.salesforce.marketingcloud.messages.e;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

final class c extends a {
    private static final a a = new a();
    private static final com.salesforce.marketingcloud.messages.e.c b = new com.salesforce.marketingcloud.messages.e.c();

    c(b bVar, int i, List<e> list) {
        super(bVar, i, list);
    }

    static c b(JSONObject jSONObject) {
        List emptyList = Collections.emptyList();
        Iterator keys = jSONObject.keys();
        b bVar = null;
        List list = emptyList;
        int i = 0;
        while (keys.hasNext()) {
            String str = (String) keys.next();
            if (!jSONObject.isNull(str)) {
                char c = 65535;
                int hashCode = str.hashCode();
                if (hashCode != -1822090419) {
                    if (hashCode != -1278142878) {
                        if (hashCode == 2047441680 && str.equals("refreshCenter")) {
                            c = 0;
                        }
                    } else if (str.equals("fences")) {
                        c = 2;
                    }
                } else if (str.equals("refreshRadius")) {
                    c = 1;
                }
                switch (c) {
                    case 0:
                        bVar = a.a(jSONObject, str);
                        break;
                    case 1:
                        i = jSONObject.getInt(str);
                        break;
                    case 2:
                        list = b.a(jSONObject, str);
                        break;
                }
            }
        }
        return new c(bVar, i, list);
    }
}
