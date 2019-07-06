package com.salesforce.marketingcloud.analytics.b;

import java.util.Date;
import org.json.JSONObject;

abstract class g extends h {
    g() {
    }

    public static g a(Date date) {
        return new d("track_event", "app_close", date);
    }

    public abstract JSONObject d();
}
