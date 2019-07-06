package com.facebook.appevents.internal;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.b;
import com.facebook.internal.t;
import com.facebook.internal.z;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class AppEventsLoggerUtility {
    private static final Map<GraphAPIActivityType, String> a = new HashMap<GraphAPIActivityType, String>() {
        {
            put(GraphAPIActivityType.MOBILE_INSTALL_EVENT, "MOBILE_APP_INSTALL");
            put(GraphAPIActivityType.CUSTOM_APP_EVENTS, "CUSTOM_APP_EVENTS");
        }
    };

    public enum GraphAPIActivityType {
        MOBILE_INSTALL_EVENT,
        CUSTOM_APP_EVENTS
    }

    public static JSONObject a(GraphAPIActivityType graphAPIActivityType, b bVar, String str, boolean z, Context context) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(NotificationCompat.CATEGORY_EVENT, a.get(graphAPIActivityType));
        String e = AppEventsLogger.e();
        if (e != null) {
            jSONObject.put("app_user_id", e);
        }
        z.a(jSONObject, bVar, str, z);
        try {
            z.a(jSONObject, context);
        } catch (Exception e2) {
            t.a(LoggingBehavior.APP_EVENTS, "AppEvents", "Fetching extended device info parameters failed: '%s'", e2.toString());
        }
        jSONObject.put("application_package_name", context.getPackageName());
        return jSONObject;
    }
}
