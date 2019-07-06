package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.b;
import com.facebook.appevents.internal.c;
import com.facebook.appevents.internal.d;
import com.facebook.f;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.j.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: FetchedAppSettingsManager */
public final class k {
    /* access modifiers changed from: private */
    public static final String a = k.class.getCanonicalName();
    private static final String[] b = {"supports_implicit_sdk_logging", "gdpv4_nux_content", "gdpv4_nux_enabled", "gdpv4_chrome_custom_tabs_enabled", "android_dialog_configs", "android_sdk_error_categories", "app_events_session_timeout", "app_events_feature_bitmask", "auto_event_mapping_android", "seamless_login", "smart_login_bookmark_icon_url", "smart_login_menu_icon_url"};
    private static Map<String, j> c = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public static AtomicBoolean d = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public static boolean e = false;

    public static void a() {
        final Context f = f.f();
        final String j = f.j();
        boolean compareAndSet = d.compareAndSet(false, true);
        if (!z.a(j) && !c.containsKey(j) && compareAndSet) {
            final String format = String.format("com.facebook.internal.APP_SETTINGS.%s", new Object[]{j});
            f.d().execute(new Runnable() {
                public void run() {
                    JSONObject jSONObject;
                    SharedPreferences sharedPreferences = f.getSharedPreferences("com.facebook.internal.preferences.APP_SETTINGS", 0);
                    j jVar = null;
                    String string = sharedPreferences.getString(format, null);
                    if (!z.a(string)) {
                        try {
                            jSONObject = new JSONObject(string);
                        } catch (JSONException e) {
                            z.a("FacebookSDK", (Exception) e);
                            jSONObject = null;
                        }
                        if (jSONObject != null) {
                            jVar = k.b(j, jSONObject);
                        }
                    }
                    JSONObject b2 = k.c(j);
                    if (b2 != null) {
                        k.b(j, b2);
                        sharedPreferences.edit().putString(format, b2.toString()).apply();
                    }
                    if (jVar != null) {
                        String m = jVar.m();
                        if (!k.e && m != null && m.length() > 0) {
                            k.e = true;
                            Log.w(k.a, m);
                        }
                    }
                    c.a();
                    k.b(f);
                    k.d.set(false);
                }
            });
        }
    }

    public static j a(String str) {
        if (str != null) {
            return (j) c.get(str);
        }
        return null;
    }

    public static j a(String str, boolean z) {
        if (!z && c.containsKey(str)) {
            return (j) c.get(str);
        }
        JSONObject c2 = c(str);
        if (c2 == null) {
            return null;
        }
        return b(str, c2);
    }

    /* access modifiers changed from: private */
    public static j b(String str, JSONObject jSONObject) {
        h a2;
        JSONObject jSONObject2 = jSONObject;
        JSONArray optJSONArray = jSONObject2.optJSONArray("android_sdk_error_categories");
        if (optJSONArray == null) {
            a2 = h.a();
        } else {
            a2 = h.a(optJSONArray);
        }
        h hVar = a2;
        int optInt = jSONObject2.optInt("app_events_feature_bitmask", 0);
        j jVar = new j(jSONObject2.optBoolean("supports_implicit_sdk_logging", false), jSONObject2.optString("gdpv4_nux_content", ""), jSONObject2.optBoolean("gdpv4_nux_enabled", false), jSONObject2.optBoolean("gdpv4_chrome_custom_tabs_enabled", false), jSONObject2.optInt("app_events_session_timeout", d.a()), SmartLoginOption.parseOptions(jSONObject2.optLong("seamless_login")), a(jSONObject2.optJSONObject("android_dialog_configs")), (optInt & 8) != 0, hVar, jSONObject2.optString("smart_login_bookmark_icon_url"), jSONObject2.optString("smart_login_menu_icon_url"), (optInt & 16) != 0, (optInt & 32) != 0, jSONObject2.optJSONArray("auto_event_mapping_android"), jSONObject2.optString("sdk_update_message"));
        c.put(str, jVar);
        return jVar;
    }

    /* access modifiers changed from: private */
    public static JSONObject c(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", TextUtils.join(",", new ArrayList(Arrays.asList(b))));
        GraphRequest a2 = GraphRequest.a((AccessToken) null, str, (b) null);
        a2.a(true);
        a2.a(bundle);
        return a2.i().b();
    }

    private static Map<String, Map<String, a>> a(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    a a2 = a.a(optJSONArray.optJSONObject(i));
                    if (a2 != null) {
                        String a3 = a2.a();
                        Map map = (Map) hashMap.get(a3);
                        if (map == null) {
                            map = new HashMap();
                            hashMap.put(a3, map);
                        }
                        map.put(a2.b(), a2);
                    }
                }
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public static void b(final Context context) {
        CallbackManagerImpl.a(RequestCodeOffset.InAppPurchase.toRequestCode(), new CallbackManagerImpl.a() {
            public boolean a(final int i, final Intent intent) {
                f.d().execute(new Runnable() {
                    public void run() {
                        c.a(context, i, intent);
                    }
                });
                return true;
            }
        });
    }
}
