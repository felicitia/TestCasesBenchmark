package com.contextlogic.wish.api.datacenter;

import android.os.Bundle;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.http.HttpCookieManager;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import org.json.JSONObject;

public class AuthenticationDataCenter extends DataCenter {
    private static AuthenticationDataCenter sInstance = new AuthenticationDataCenter();
    private String mEmail;
    private String mFbId;
    private String mGoogleId;
    private Object mLock = new Object();
    private String mSessionId;
    private long mSessionStartTime;
    private String mUserId;

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
    }

    /* access modifiers changed from: protected */
    public String getLegacySerializationFileName() {
        return "serialized_session";
    }

    /* access modifiers changed from: protected */
    public String getSerializationFileName() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getSerializationPreferenceName() {
        return "AuthenticationDataCenter";
    }

    public void refresh() {
    }

    private AuthenticationDataCenter() {
        clearData();
    }

    public static AuthenticationDataCenter getInstance() {
        return sInstance;
    }

    public boolean isLoggedIn() {
        return isDataInitialized() && isSessionValid() && ProfileDataCenter.getInstance().isDataInitialized();
    }

    public boolean isDataInitialized() {
        return this.mUserId != null;
    }

    public void initializeData(String str, String str2, long j, String str3, String str4, String str5) {
        if (str2.startsWith("\"") && str2.endsWith("\"")) {
            str2 = str2.substring(1, str2.length() - 1);
        }
        HttpCookieManager.getInstance().setSessionCookie(str2);
        PreferenceUtil.setString("LoggedInUser", str);
        synchronized (this.mLock) {
            this.mUserId = str;
            this.mSessionId = str2;
            this.mSessionStartTime = j;
            this.mFbId = str3;
            this.mGoogleId = str5;
            this.mEmail = str4;
        }
        markForSerialization();
        ApplicationEventManager.getInstance().triggerEvent(EventType.DATA_CENTER_UPDATED, getClass().toString(), null);
    }

    /* access modifiers changed from: protected */
    public boolean processSerializedData(JSONObject jSONObject, Bundle bundle) {
        String string = bundle.getString("FbId");
        String optString = JsonUtil.optString(jSONObject, "FbId");
        boolean z = string == null || (optString != null && string.equals(optString));
        String string2 = bundle.getString("Email");
        String optString2 = JsonUtil.optString(jSONObject, "Email");
        if (string2 != null && (optString2 == null || !string2.equals(optString2))) {
            z &= false;
        }
        String string3 = bundle.getString("GoogleId");
        String optString3 = JsonUtil.optString(jSONObject, "GoogleId");
        if (string3 != null && (optString3 == null || !string3.equals(optString3))) {
            z &= false;
        }
        String optString4 = JsonUtil.optString(jSONObject, "UserId");
        if (optString4 == null || optString4.isEmpty()) {
            z &= false;
        }
        String optString5 = JsonUtil.optString(jSONObject, "SessionId");
        if (optString5 == null || optString5.isEmpty()) {
            z &= false;
        }
        long optLong = jSONObject.optLong("SessionStartTime");
        if (System.currentTimeMillis() - optLong > 518400000) {
            z &= false;
        }
        if (z) {
            initializeData(optString4, optString5, optLong, optString, optString2, optString3);
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public boolean processLegacySerializedData(JSONObject jSONObject, Bundle bundle) {
        String string = bundle.getString("fbId");
        String optString = JsonUtil.optString(jSONObject, "fbId");
        boolean z = string == null || (optString != null && string.equals(optString));
        String string2 = bundle.getString("email");
        String optString2 = JsonUtil.optString(jSONObject, "email");
        if (string2 != null && (optString2 == null || !string2.equals(optString2))) {
            z &= false;
        }
        String string3 = bundle.getString("googlePlusId");
        String optString3 = JsonUtil.optString(jSONObject, "googlePlusId");
        if (string3 != null && (optString3 == null || !string3.equals(optString3))) {
            z &= false;
        }
        String optString4 = JsonUtil.optString(jSONObject, "userId");
        if (optString4 == null || optString4.isEmpty()) {
            z &= false;
        }
        String optString5 = JsonUtil.optString(jSONObject, "sessionCookie");
        if (optString5 == null || optString5.isEmpty()) {
            z &= false;
        }
        long optLong = jSONObject.optLong("timestamp");
        if (System.currentTimeMillis() - optLong > 518400000) {
            z &= false;
        }
        if (z) {
            initializeData(optString4, optString5, optLong, optString, optString2, optString3);
        }
        return z;
    }

    private boolean isSessionValid() {
        return System.currentTimeMillis() - this.mSessionStartTime < 518400000;
    }

    public String getUserId() {
        return this.mUserId;
    }

    /* access modifiers changed from: protected */
    public boolean canDeserialize() {
        return !PreferenceUtil.getBoolean("ForceRelogin");
    }

    /* access modifiers changed from: protected */
    public void clearData() {
        synchronized (this.mLock) {
            this.mUserId = null;
            this.mSessionId = null;
            this.mSessionStartTime = -1;
            this.mFbId = null;
            this.mGoogleId = null;
            this.mEmail = null;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0037 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject getSerializationData() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            r1 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0036 }
            r2.<init>()     // Catch:{ Throwable -> 0x0036 }
            java.lang.String r1 = "UserId"
            java.lang.String r3 = r5.mUserId     // Catch:{ Throwable -> 0x0037 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x0037 }
            java.lang.String r1 = "SessionId"
            java.lang.String r3 = r5.mSessionId     // Catch:{ Throwable -> 0x0037 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x0037 }
            java.lang.String r1 = "SessionStartTime"
            long r3 = r5.mSessionStartTime     // Catch:{ Throwable -> 0x0037 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x0037 }
            java.lang.String r1 = "FbId"
            java.lang.String r3 = r5.mFbId     // Catch:{ Throwable -> 0x0037 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x0037 }
            java.lang.String r1 = "GoogleId"
            java.lang.String r3 = r5.mGoogleId     // Catch:{ Throwable -> 0x0037 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x0037 }
            java.lang.String r1 = "Email"
            java.lang.String r3 = r5.mEmail     // Catch:{ Throwable -> 0x0037 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x0037 }
            goto L_0x0037
        L_0x0034:
            r1 = move-exception
            goto L_0x0039
        L_0x0036:
            r2 = r1
        L_0x0037:
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return r2
        L_0x0039:
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.datacenter.AuthenticationDataCenter.getSerializationData():org.json.JSONObject");
    }

    /* access modifiers changed from: protected */
    public boolean shouldCheckLegacySerialization() {
        return !PreferenceUtil.getBoolean("V3SessionMigrated", false);
    }

    /* access modifiers changed from: protected */
    public void onDeserializationCompleted() {
        PreferenceUtil.setBoolean("V3SessionMigrated", true);
    }

    /* access modifiers changed from: protected */
    public RefreshMode getRefreshMode() {
        return RefreshMode.MANUAL;
    }
}
