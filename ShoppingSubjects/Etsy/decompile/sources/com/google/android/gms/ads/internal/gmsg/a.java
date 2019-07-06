package com.google.android.gms.ads.internal.gmsg;

import android.os.Bundle;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.gv;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class a implements ae<Object> {
    private final k a;

    public a(k kVar) {
        this.a = kVar;
    }

    private static Bundle a(String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            Bundle bundle = new Bundle();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                Object obj = jSONObject.get(str2);
                if (obj != null) {
                    if (obj instanceof Boolean) {
                        bundle.putBoolean(str2, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof Double) {
                        bundle.putDouble(str2, ((Double) obj).doubleValue());
                    } else if (obj instanceof Integer) {
                        bundle.putInt(str2, ((Integer) obj).intValue());
                    } else if (obj instanceof Long) {
                        bundle.putLong(str2, ((Long) obj).longValue());
                    } else if (obj instanceof String) {
                        bundle.putString(str2, (String) obj);
                    } else {
                        String str3 = "Unsupported type for key:";
                        String valueOf = String.valueOf(str2);
                        gv.e(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                    }
                }
            }
            return bundle;
        } catch (JSONException e) {
            gv.b("Failed to convert ad metadata to JSON.", e);
            return null;
        }
    }

    public final void zza(Object obj, Map<String, String> map) {
        if (this.a != null) {
            String str = (String) map.get(ResponseConstants.NAME);
            if (str == null) {
                gv.d("Ad metadata with no name parameter.");
                str = "";
            }
            Bundle a2 = a((String) map.get("info"));
            if (a2 == null) {
                gv.c("Failed to convert ad metadata to Bundle.");
            } else {
                this.a.zza(str, a2);
            }
        }
    }
}
