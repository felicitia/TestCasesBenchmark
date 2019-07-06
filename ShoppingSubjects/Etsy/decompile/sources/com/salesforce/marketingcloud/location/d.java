package com.salesforce.marketingcloud.location;

import com.etsy.android.lib.models.ResponseConstants;
import java.util.Iterator;
import org.json.JSONObject;

final class d extends a {
    d(double d, double d2) {
        super(d, d2);
    }

    static d b(JSONObject jSONObject) {
        Iterator keys = jSONObject.keys();
        double d = 0.0d;
        double d2 = 0.0d;
        while (keys.hasNext()) {
            String str = (String) keys.next();
            if (!jSONObject.isNull(str)) {
                char c = 65535;
                int hashCode = str.hashCode();
                if (hashCode != -1439978388) {
                    if (hashCode == 137365935 && str.equals(ResponseConstants.LONGITUDE)) {
                        c = 1;
                    }
                } else if (str.equals(ResponseConstants.LATITUDE)) {
                    c = 0;
                }
                switch (c) {
                    case 0:
                        d = jSONObject.getDouble(str);
                        break;
                    case 1:
                        d2 = jSONObject.getDouble(str);
                        break;
                }
            }
        }
        return new d(d, d2);
    }
}
