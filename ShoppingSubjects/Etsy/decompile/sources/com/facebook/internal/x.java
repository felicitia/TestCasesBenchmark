package com.facebook.internal;

import android.os.Bundle;
import com.etsy.android.lib.models.ResponseConstants;
import com.facebook.LoggingBehavior;
import com.facebook.f;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ServerProtocol */
public final class x {
    public static final Collection<String> a = z.a((T[]) new String[]{"service_disabled", "AndroidAuthKillSwitchException"});
    public static final Collection<String> b = z.a((T[]) new String[]{"access_denied", "OAuthAccessDeniedException"});
    private static final String c = "com.facebook.internal.x";

    public static final String d() {
        return "v3.1";
    }

    public static final String a() {
        return String.format("m.%s", new Object[]{f.e()});
    }

    public static final String b() {
        return String.format("https://graph.%s", new Object[]{f.e()});
    }

    public static final String c() {
        return String.format("https://graph-video.%s", new Object[]{f.e()});
    }

    public static Bundle a(String str, int i, Bundle bundle) {
        String d = f.d(f.f());
        if (z.a(d)) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("android_key_hash", d);
        bundle2.putString("app_id", f.j());
        bundle2.putInt(ResponseConstants.VERSION, i);
        bundle2.putString("display", "touch");
        Bundle bundle3 = new Bundle();
        bundle3.putString("action_id", str);
        if (bundle == null) {
            bundle = new Bundle();
        }
        try {
            JSONObject a2 = c.a(bundle3);
            JSONObject a3 = c.a(bundle);
            if (a2 != null) {
                if (a3 != null) {
                    bundle2.putString("bridge_args", a2.toString());
                    bundle2.putString("method_args", a3.toString());
                    return bundle2;
                }
            }
            return null;
        } catch (JSONException e) {
            LoggingBehavior loggingBehavior = LoggingBehavior.DEVELOPER_ERRORS;
            String str2 = c;
            StringBuilder sb = new StringBuilder();
            sb.append("Error creating Url -- ");
            sb.append(e);
            t.a(loggingBehavior, 6, str2, sb.toString());
            bundle2 = null;
        }
    }
}
