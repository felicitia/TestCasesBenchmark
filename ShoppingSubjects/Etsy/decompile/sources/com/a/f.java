package com.a;

import com.etsy.android.ui.nav.NotificationActivity;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Response */
public class f {
    private int a;
    private String b;
    private String c;
    private String d;
    private String e;

    f(JSONObject jSONObject) throws JSONException {
        this.a = jSONObject.optInt("status_code");
        this.b = jSONObject.optString("status_txt");
        if (jSONObject.optJSONObject("data") != null) {
            this.c = jSONObject.getJSONObject("data").optString("url");
            this.d = jSONObject.getJSONObject("data").optString("long_url");
            if (jSONObject.optJSONObject("data").optJSONObject(NotificationActivity.ETSY_DEEPLINK_PARAM) != null) {
                this.e = jSONObject.getJSONObject("data").getJSONObject(NotificationActivity.ETSY_DEEPLINK_PARAM).optString("applink");
            }
        }
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }
}
