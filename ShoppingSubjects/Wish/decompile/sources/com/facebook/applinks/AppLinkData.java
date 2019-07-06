package com.facebook.applinks;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppLinkData {
    private static final String TAG = AppLinkData.class.getCanonicalName();
    private Bundle argumentBundle;
    private JSONObject arguments;
    private String promotionCode;
    private String ref;
    private Uri targetUri;

    public interface CompletionHandler {
        void onDeferredAppLinkDataFetched(AppLinkData appLinkData);
    }

    public static void fetchDeferredAppLinkData(Context context, final String str, final CompletionHandler completionHandler) {
        Validate.notNull(context, "context");
        Validate.notNull(completionHandler, "completionHandler");
        if (str == null) {
            str = Utility.getMetadataApplicationId(context);
        }
        Validate.notNull(str, "applicationId");
        final Context applicationContext = context.getApplicationContext();
        FacebookSdk.getExecutor().execute(new Runnable() {
            public void run() {
                AppLinkData.fetchDeferredAppLinkFromServer(applicationContext, str, completionHandler);
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:21|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        android.util.Log.d(TAG, "Unable to put tap time in AppLinkData.arguments");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        android.util.Log.d(TAG, "Unable to put tap time in AppLinkData.arguments");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        android.util.Log.d(TAG, "Unable to put tap time in AppLinkData.arguments");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0084 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00a4 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00c4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void fetchDeferredAppLinkFromServer(android.content.Context r7, java.lang.String r8, com.facebook.applinks.AppLinkData.CompletionHandler r9) {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "event"
            java.lang.String r2 = "DEFERRED_APP_LINK"
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00d8 }
            com.facebook.internal.AttributionIdentifiers r1 = com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(r7)     // Catch:{ JSONException -> 0x00d8 }
            java.lang.String r2 = com.facebook.appevents.AppEventsLogger.getAnonymousAppDeviceGUID(r7)     // Catch:{ JSONException -> 0x00d8 }
            boolean r3 = com.facebook.FacebookSdk.getLimitEventAndDataUsage(r7)     // Catch:{ JSONException -> 0x00d8 }
            com.facebook.internal.Utility.setAppEventAttributionParameters(r0, r1, r2, r3)     // Catch:{ JSONException -> 0x00d8 }
            java.lang.String r1 = "application_package_name"
            java.lang.String r7 = r7.getPackageName()     // Catch:{ JSONException -> 0x00d8 }
            r0.put(r1, r7)     // Catch:{ JSONException -> 0x00d8 }
            java.lang.String r7 = "%s/activities"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            r1[r2] = r8
            java.lang.String r7 = java.lang.String.format(r7, r1)
            r8 = 0
            com.facebook.GraphRequest r7 = com.facebook.GraphRequest.newPostRequest(r8, r7, r0, r8)     // Catch:{ Exception -> 0x00cd }
            com.facebook.GraphResponse r7 = r7.executeAndWait()     // Catch:{ Exception -> 0x00cd }
            org.json.JSONObject r7 = r7.getJSONObject()     // Catch:{ Exception -> 0x00cd }
            if (r7 == 0) goto L_0x00d4
            java.lang.String r0 = "applink_args"
            java.lang.String r0 = r7.optString(r0)     // Catch:{ Exception -> 0x00cd }
            java.lang.String r1 = "click_time"
            r2 = -1
            long r4 = r7.optLong(r1, r2)     // Catch:{ Exception -> 0x00cd }
            java.lang.String r1 = "applink_class"
            java.lang.String r1 = r7.optString(r1)     // Catch:{ Exception -> 0x00cd }
            java.lang.String r6 = "applink_url"
            java.lang.String r7 = r7.optString(r6)     // Catch:{ Exception -> 0x00cd }
            boolean r6 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x00cd }
            if (r6 != 0) goto L_0x00d4
            com.facebook.applinks.AppLinkData r0 = createFromJson(r0)     // Catch:{ Exception -> 0x00cd }
            int r8 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r8 == 0) goto L_0x008b
            org.json.JSONObject r8 = r0.arguments     // Catch:{ JSONException -> 0x0084 }
            if (r8 == 0) goto L_0x0072
            org.json.JSONObject r8 = r0.arguments     // Catch:{ JSONException -> 0x0084 }
            java.lang.String r2 = "com.facebook.platform.APPLINK_TAP_TIME_UTC"
            r8.put(r2, r4)     // Catch:{ JSONException -> 0x0084 }
        L_0x0072:
            android.os.Bundle r8 = r0.argumentBundle     // Catch:{ JSONException -> 0x0084 }
            if (r8 == 0) goto L_0x008b
            android.os.Bundle r8 = r0.argumentBundle     // Catch:{ JSONException -> 0x0084 }
            java.lang.String r2 = "com.facebook.platform.APPLINK_TAP_TIME_UTC"
            java.lang.String r3 = java.lang.Long.toString(r4)     // Catch:{ JSONException -> 0x0084 }
            r8.putString(r2, r3)     // Catch:{ JSONException -> 0x0084 }
            goto L_0x008b
        L_0x0082:
            r8 = r0
            goto L_0x00cd
        L_0x0084:
            java.lang.String r8 = TAG     // Catch:{ Exception -> 0x0082 }
            java.lang.String r2 = "Unable to put tap time in AppLinkData.arguments"
            android.util.Log.d(r8, r2)     // Catch:{ Exception -> 0x0082 }
        L_0x008b:
            if (r1 == 0) goto L_0x00ab
            org.json.JSONObject r8 = r0.arguments     // Catch:{ JSONException -> 0x00a4 }
            if (r8 == 0) goto L_0x0098
            org.json.JSONObject r8 = r0.arguments     // Catch:{ JSONException -> 0x00a4 }
            java.lang.String r2 = "com.facebook.platform.APPLINK_NATIVE_CLASS"
            r8.put(r2, r1)     // Catch:{ JSONException -> 0x00a4 }
        L_0x0098:
            android.os.Bundle r8 = r0.argumentBundle     // Catch:{ JSONException -> 0x00a4 }
            if (r8 == 0) goto L_0x00ab
            android.os.Bundle r8 = r0.argumentBundle     // Catch:{ JSONException -> 0x00a4 }
            java.lang.String r2 = "com.facebook.platform.APPLINK_NATIVE_CLASS"
            r8.putString(r2, r1)     // Catch:{ JSONException -> 0x00a4 }
            goto L_0x00ab
        L_0x00a4:
            java.lang.String r8 = TAG     // Catch:{ Exception -> 0x0082 }
            java.lang.String r1 = "Unable to put tap time in AppLinkData.arguments"
            android.util.Log.d(r8, r1)     // Catch:{ Exception -> 0x0082 }
        L_0x00ab:
            if (r7 == 0) goto L_0x00cb
            org.json.JSONObject r8 = r0.arguments     // Catch:{ JSONException -> 0x00c4 }
            if (r8 == 0) goto L_0x00b8
            org.json.JSONObject r8 = r0.arguments     // Catch:{ JSONException -> 0x00c4 }
            java.lang.String r1 = "com.facebook.platform.APPLINK_NATIVE_URL"
            r8.put(r1, r7)     // Catch:{ JSONException -> 0x00c4 }
        L_0x00b8:
            android.os.Bundle r8 = r0.argumentBundle     // Catch:{ JSONException -> 0x00c4 }
            if (r8 == 0) goto L_0x00cb
            android.os.Bundle r8 = r0.argumentBundle     // Catch:{ JSONException -> 0x00c4 }
            java.lang.String r1 = "com.facebook.platform.APPLINK_NATIVE_URL"
            r8.putString(r1, r7)     // Catch:{ JSONException -> 0x00c4 }
            goto L_0x00cb
        L_0x00c4:
            java.lang.String r7 = TAG     // Catch:{ Exception -> 0x0082 }
            java.lang.String r8 = "Unable to put tap time in AppLinkData.arguments"
            android.util.Log.d(r7, r8)     // Catch:{ Exception -> 0x0082 }
        L_0x00cb:
            r8 = r0
            goto L_0x00d4
        L_0x00cd:
            java.lang.String r7 = TAG
            java.lang.String r0 = "Unable to fetch deferred applink from server"
            com.facebook.internal.Utility.logd(r7, r0)
        L_0x00d4:
            r9.onDeferredAppLinkDataFetched(r8)
            return
        L_0x00d8:
            r7 = move-exception
            com.facebook.FacebookException r8 = new com.facebook.FacebookException
            java.lang.String r9 = "An error occurred while preparing deferred app link"
            r8.<init>(r9, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.applinks.AppLinkData.fetchDeferredAppLinkFromServer(android.content.Context, java.lang.String, com.facebook.applinks.AppLinkData$CompletionHandler):void");
    }

    private static AppLinkData createFromJson(String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("version");
            if (jSONObject.getJSONObject("bridge_args").getString("method").equals("applink") && string.equals("2")) {
                AppLinkData appLinkData = new AppLinkData();
                appLinkData.arguments = jSONObject.getJSONObject("method_args");
                if (appLinkData.arguments.has("ref")) {
                    appLinkData.ref = appLinkData.arguments.getString("ref");
                } else if (appLinkData.arguments.has("referer_data")) {
                    JSONObject jSONObject2 = appLinkData.arguments.getJSONObject("referer_data");
                    if (jSONObject2.has("fb_ref")) {
                        appLinkData.ref = jSONObject2.getString("fb_ref");
                    }
                }
                if (appLinkData.arguments.has("target_url")) {
                    appLinkData.targetUri = Uri.parse(appLinkData.arguments.getString("target_url"));
                }
                if (appLinkData.arguments.has("extras")) {
                    JSONObject jSONObject3 = appLinkData.arguments.getJSONObject("extras");
                    if (jSONObject3.has("deeplink_context")) {
                        JSONObject jSONObject4 = jSONObject3.getJSONObject("deeplink_context");
                        if (jSONObject4.has("promo_code")) {
                            appLinkData.promotionCode = jSONObject4.getString("promo_code");
                        }
                    }
                }
                appLinkData.argumentBundle = toBundle(appLinkData.arguments);
                return appLinkData;
            }
        } catch (JSONException e) {
            Log.d(TAG, "Unable to parse AppLink JSON", e);
        } catch (FacebookException e2) {
            Log.d(TAG, "Unable to parse AppLink JSON", e2);
        }
        return null;
    }

    private static Bundle toBundle(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object obj = jSONObject.get(str);
            if (obj instanceof JSONObject) {
                bundle.putBundle(str, toBundle((JSONObject) obj));
            } else if (obj instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj;
                int i = 0;
                if (jSONArray.length() == 0) {
                    bundle.putStringArray(str, new String[0]);
                } else {
                    Object obj2 = jSONArray.get(0);
                    if (obj2 instanceof JSONObject) {
                        Bundle[] bundleArr = new Bundle[jSONArray.length()];
                        while (i < jSONArray.length()) {
                            bundleArr[i] = toBundle(jSONArray.getJSONObject(i));
                            i++;
                        }
                        bundle.putParcelableArray(str, bundleArr);
                    } else if (obj2 instanceof JSONArray) {
                        throw new FacebookException("Nested arrays are not supported.");
                    } else {
                        String[] strArr = new String[jSONArray.length()];
                        while (i < jSONArray.length()) {
                            strArr[i] = jSONArray.get(i).toString();
                            i++;
                        }
                        bundle.putStringArray(str, strArr);
                    }
                }
            } else {
                bundle.putString(str, obj.toString());
            }
        }
        return bundle;
    }

    private AppLinkData() {
    }

    public Uri getTargetUri() {
        return this.targetUri;
    }
}
