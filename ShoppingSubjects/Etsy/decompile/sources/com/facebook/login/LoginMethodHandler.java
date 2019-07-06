package com.facebook.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.z;
import com.facebook.login.LoginClient.Request;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

abstract class LoginMethodHandler implements Parcelable {
    protected LoginClient loginClient;
    Map<String, String> methodLoggingExtras;

    /* access modifiers changed from: 0000 */
    public void cancel() {
    }

    /* access modifiers changed from: 0000 */
    public abstract String getNameForLogging();

    /* access modifiers changed from: 0000 */
    public boolean needsInternetPermission() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public boolean onActivityResult(int i, int i2, Intent intent) {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void putChallengeParam(JSONObject jSONObject) throws JSONException {
    }

    /* access modifiers changed from: 0000 */
    public abstract boolean tryAuthorize(Request request);

    LoginMethodHandler(LoginClient loginClient2) {
        this.loginClient = loginClient2;
    }

    LoginMethodHandler(Parcel parcel) {
        this.methodLoggingExtras = z.a(parcel);
    }

    /* access modifiers changed from: 0000 */
    public void setLoginClient(LoginClient loginClient2) {
        if (this.loginClient != null) {
            throw new FacebookException("Can't set LoginClient if it is already set.");
        }
        this.loginClient = loginClient2;
    }

    /* access modifiers changed from: protected */
    public String getClientState(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0_auth_logger_id", str);
            jSONObject.put("3_method", getNameForLogging());
            putChallengeParam(jSONObject);
        } catch (JSONException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error creating client state json: ");
            sb.append(e.getMessage());
            Log.w("LoginMethodHandler", sb.toString());
        }
        return jSONObject.toString();
    }

    /* access modifiers changed from: protected */
    public void addLoggingExtra(String str, Object obj) {
        if (this.methodLoggingExtras == null) {
            this.methodLoggingExtras = new HashMap();
        }
        this.methodLoggingExtras.put(str, obj == null ? null : obj.toString());
    }

    /* access modifiers changed from: protected */
    public void logWebLoginCompleted(String str) {
        String applicationId = this.loginClient.c().getApplicationId();
        AppEventsLogger b = AppEventsLogger.b(this.loginClient.b(), applicationId);
        Bundle bundle = new Bundle();
        bundle.putString("fb_web_login_e2e", str);
        bundle.putLong("fb_web_login_switchback_time", System.currentTimeMillis());
        bundle.putString("app_id", applicationId);
        b.a("fb_dialogs_web_login_dialog_complete", (Double) null, bundle);
    }

    static AccessToken createAccessTokenFromNativeLogin(Bundle bundle, AccessTokenSource accessTokenSource, String str) {
        Date a = z.a(bundle, "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH", new Date(0));
        ArrayList stringArrayList = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
        String string = bundle.getString("com.facebook.platform.extra.ACCESS_TOKEN");
        if (z.a(string)) {
            return null;
        }
        AccessToken accessToken = new AccessToken(string, str, bundle.getString("com.facebook.platform.extra.USER_ID"), stringArrayList, null, accessTokenSource, a, new Date());
        return accessToken;
    }

    public static AccessToken createAccessTokenFromWebBundle(Collection<String> collection, Bundle bundle, AccessTokenSource accessTokenSource, String str) throws FacebookException {
        Date a = z.a(bundle, AccessToken.EXPIRES_IN_KEY, new Date());
        String string = bundle.getString(AccessToken.ACCESS_TOKEN_KEY);
        String string2 = bundle.getString("granted_scopes");
        if (!z.a(string2)) {
            collection = new ArrayList<>(Arrays.asList(string2.split(",")));
        }
        Collection<String> collection2 = collection;
        String string3 = bundle.getString("denied_scopes");
        Collection arrayList = !z.a(string3) ? new ArrayList(Arrays.asList(string3.split(","))) : null;
        if (z.a(string)) {
            return null;
        }
        AccessToken accessToken = new AccessToken(string, str, getUserIDFromSignedRequest(bundle.getString("signed_request")), collection2, arrayList, accessTokenSource, a, new Date());
        return accessToken;
    }

    static String getUserIDFromSignedRequest(String str) throws FacebookException {
        if (str == null || str.isEmpty()) {
            throw new FacebookException("Authorization response does not contain the signed_request");
        }
        try {
            String[] split = str.split("\\.");
            if (split.length == 2) {
                return new JSONObject(new String(Base64.decode(split[1], 0), "UTF-8")).getString("user_id");
            }
        } catch (UnsupportedEncodingException | JSONException unused) {
        }
        throw new FacebookException("Failed to retrieve user_id from signed_request");
    }

    public void writeToParcel(Parcel parcel, int i) {
        z.a(parcel, this.methodLoggingExtras);
    }
}
