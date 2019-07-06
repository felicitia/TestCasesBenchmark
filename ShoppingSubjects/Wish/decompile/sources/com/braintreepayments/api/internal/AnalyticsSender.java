package com.braintreepayments.api.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.models.Authorization;
import com.braintreepayments.api.models.ClientToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnalyticsSender {
    public static void send(Context context, Authorization authorization, BraintreeHttpClient braintreeHttpClient, String str, boolean z) {
        final AnalyticsDatabase instance = AnalyticsDatabase.getInstance(context);
        try {
            for (final List list : instance.getPendingRequests()) {
                JSONObject serializeEvents = serializeEvents(context, authorization, list);
                if (z) {
                    try {
                        braintreeHttpClient.post(str, serializeEvents.toString());
                        instance.removeEvents(list);
                    } catch (Exception unused) {
                    }
                } else {
                    braintreeHttpClient.post(str, serializeEvents.toString(), new HttpResponseCallback() {
                        public void failure(Exception exc) {
                        }

                        public void success(String str) {
                            instance.removeEvents(list);
                        }
                    });
                }
            }
        } catch (JSONException unused2) {
        }
    }

    private static JSONObject serializeEvents(Context context, Authorization authorization, List<AnalyticsEvent> list) throws JSONException {
        AnalyticsEvent analyticsEvent = (AnalyticsEvent) list.get(0);
        JSONObject jSONObject = new JSONObject();
        if (authorization instanceof ClientToken) {
            jSONObject.put("authorization_fingerprint", ((ClientToken) authorization).getAuthorizationFingerprint());
        } else {
            jSONObject.put("tokenization_key", authorization.toString());
        }
        jSONObject.put("_meta", analyticsEvent.metadata.put("platform", "Android").put("integrationType", analyticsEvent.getIntegrationType()).put("platformVersion", Integer.toString(VERSION.SDK_INT)).put("sdkVersion", "2.7.0").put("merchantAppId", context.getPackageName()).put("merchantAppName", getAppName(context)).put("deviceRooted", isDeviceRooted()).put("deviceManufacturer", Build.MANUFACTURER).put("deviceModel", Build.MODEL).put("androidId", getAndroidId(context)).put("deviceAppGeneratedPersistentUuid", UUIDHelper.getPersistentUUID(context)).put("isSimulator", detectEmulator()));
        JSONArray jSONArray = new JSONArray();
        for (AnalyticsEvent analyticsEvent2 : list) {
            jSONArray.put(new JSONObject().put("kind", analyticsEvent2.event).put("timestamp", analyticsEvent2.timestamp));
        }
        jSONObject.put("analytics", jSONArray);
        return jSONObject;
    }

    private static String detectEmulator() {
        return ("google_sdk".equalsIgnoreCase(Build.PRODUCT) || "sdk".equalsIgnoreCase(Build.PRODUCT) || "Genymotion".equalsIgnoreCase(Build.MANUFACTURER) || Build.FINGERPRINT.contains("generic")) ? "true" : "false";
    }

    private static String getAppName(Context context) {
        ApplicationInfo applicationInfo;
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        String str = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
        } catch (NameNotFoundException unused) {
            applicationInfo = null;
        }
        if (applicationInfo != null) {
            str = (String) packageManager.getApplicationLabel(applicationInfo);
        }
        return str == null ? "ApplicationNameUnknown" : str;
    }

    private static String isDeviceRooted() {
        boolean z;
        boolean z2;
        String str = Build.TAGS;
        boolean z3 = true;
        boolean z4 = str != null && str.contains("test-keys");
        try {
            z = new File("/system/app/Superuser.apk").exists();
        } catch (Exception unused) {
            z = false;
        }
        try {
            if (new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"}).getInputStream())).readLine() != null) {
                z2 = true;
                if (!z4 && !z && !z2) {
                    z3 = false;
                }
                return Boolean.toString(z3);
            }
        } catch (Exception unused2) {
        }
        z2 = false;
        z3 = false;
        return Boolean.toString(z3);
    }

    private static String getAndroidId(Context context) {
        String string = Secure.getString(context.getContentResolver(), "android_id");
        return string == null ? "AndroidIdUnknown" : string;
    }
}
