package com.salesforce.marketingcloud.location;

import com.salesforce.marketingcloud.j;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class b {
    /* access modifiers changed from: private */
    public static final String a = j.a(b.class);

    public static class a {
        public final b a(JSONObject jSONObject, String str) {
            try {
                return b.a(jSONObject.getJSONObject(str));
            } catch (JSONException e) {
                j.c(b.a, e, "Failed to read %s from json", str);
                return null;
            }
        }
    }

    public static final b a(double d, double d2) {
        return new d(d, d2);
    }

    public static b a(JSONObject jSONObject) {
        return d.b(jSONObject);
    }

    public abstract double a();

    public abstract double b();
}
