package com.facebook.login;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginClient.Request;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LoginLogger */
class c {
    private final AppEventsLogger a;
    private String b;
    private String c;

    c(Context context, String str) {
        this.b = str;
        this.a = AppEventsLogger.b(context, str);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                PackageInfo packageInfo = packageManager.getPackageInfo("com.facebook.katana", 0);
                if (packageInfo != null) {
                    this.c = packageInfo.versionName;
                }
            }
        } catch (NameNotFoundException unused) {
        }
    }

    public String a() {
        return this.b;
    }

    static Bundle a(String str) {
        Bundle bundle = new Bundle();
        bundle.putLong("1_timestamp_ms", System.currentTimeMillis());
        bundle.putString("0_auth_logger_id", str);
        bundle.putString("3_method", "");
        bundle.putString("2_result", "");
        bundle.putString("5_error_message", "");
        bundle.putString("4_error_code", "");
        bundle.putString("6_extras", "");
        return bundle;
    }

    public void a(Request request) {
        Bundle a2 = a(request.getAuthId());
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("login_behavior", request.getLoginBehavior().toString());
            jSONObject.put("request_code", LoginClient.d());
            jSONObject.put("permissions", TextUtils.join(",", request.getPermissions()));
            jSONObject.put("default_audience", request.getDefaultAudience().toString());
            jSONObject.put("isReauthorize", request.isRerequest());
            if (this.c != null) {
                jSONObject.put("facebookVersion", this.c);
            }
            a2.putString("6_extras", jSONObject.toString());
        } catch (JSONException unused) {
        }
        this.a.a("fb_mobile_login_start", (Double) null, a2);
    }

    public void a(String str, Map<String, String> map, Code code, Map<String, String> map2, Exception exc) {
        Bundle a2 = a(str);
        if (code != null) {
            a2.putString("2_result", code.getLoggingValue());
        }
        if (!(exc == null || exc.getMessage() == null)) {
            a2.putString("5_error_message", exc.getMessage());
        }
        JSONObject jSONObject = !map.isEmpty() ? new JSONObject(map) : null;
        if (map2 != null) {
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            try {
                for (Entry entry : map2.entrySet()) {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                }
            } catch (JSONException unused) {
            }
        }
        if (jSONObject != null) {
            a2.putString("6_extras", jSONObject.toString());
        }
        this.a.a("fb_mobile_login_complete", (Double) null, a2);
    }

    public void a(String str, String str2) {
        Bundle a2 = a(str);
        a2.putString("3_method", str2);
        this.a.a("fb_mobile_login_method_start", (Double) null, a2);
    }

    public void a(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        Bundle a2 = a(str);
        if (str3 != null) {
            a2.putString("2_result", str3);
        }
        if (str4 != null) {
            a2.putString("5_error_message", str4);
        }
        if (str5 != null) {
            a2.putString("4_error_code", str5);
        }
        if (map != null && !map.isEmpty()) {
            a2.putString("6_extras", new JSONObject(map).toString());
        }
        a2.putString("3_method", str2);
        this.a.a("fb_mobile_login_method_complete", (Double) null, a2);
    }

    public void b(String str, String str2) {
        Bundle a2 = a(str);
        a2.putString("3_method", str2);
        this.a.a("fb_mobile_login_method_not_tried", (Double) null, a2);
    }

    public void c(String str, String str2) {
        a(str, str2, "");
    }

    public void a(String str, String str2, String str3) {
        Bundle a2 = a("");
        a2.putString("2_result", Code.ERROR.getLoggingValue());
        a2.putString("5_error_message", str2);
        a2.putString("3_method", str3);
        this.a.a(str, (Double) null, a2);
    }
}
