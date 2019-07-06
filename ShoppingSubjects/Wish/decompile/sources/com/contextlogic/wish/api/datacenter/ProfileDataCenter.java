package com.contextlogic.wish.api.datacenter;

import android.os.Bundle;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.service.standalone.GetProfileService;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import java.util.Date;
import org.json.JSONObject;

public class ProfileDataCenter extends DataCenter {
    private static ProfileDataCenter sInstance = new ProfileDataCenter();
    private Date mBirthday;
    private String mCountryCode;
    private String mEmail;
    private String mFbId;
    private String mFirstName;
    private String mGender;
    private GetProfileService mGetProfileService = new GetProfileService();
    private String mGoogleId;
    private boolean mIsAdmin;
    private Object mLock = new Object();
    private String mName;
    private WishImage mProfileImage;
    private String mUserId;

    /* access modifiers changed from: protected */
    public boolean canDeserialize() {
        return true;
    }

    /* access modifiers changed from: protected */
    public String getSerializationFileName() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getSerializationPreferenceName() {
        return "ProfileDataCenter";
    }

    private ProfileDataCenter() {
        clearData();
    }

    public static ProfileDataCenter getInstance() {
        return sInstance;
    }

    public boolean isDataInitialized() {
        return this.mUserId != null;
    }

    public void initializeData(WishUser wishUser) {
        initializeData(wishUser.getUserId(), wishUser.getFbId(), wishUser.getGoogleId(), wishUser.getEmail(), wishUser.getCountryCode(), wishUser.getName(), wishUser.getFirstName(), wishUser.getProfileImage(), wishUser.getGender(), wishUser.isAdmin(), wishUser.getBirthday());
    }

    public void initializeData(String str, String str2, String str3, String str4, String str5, String str6, String str7, WishImage wishImage, String str8, boolean z, Date date) {
        synchronized (this.mLock) {
            this.mUserId = str;
            this.mFbId = str2;
            this.mGoogleId = str3;
            this.mEmail = str4;
            this.mCountryCode = str5;
            this.mName = str6;
            this.mFirstName = str7;
            this.mProfileImage = wishImage;
            this.mGender = str8;
            this.mIsAdmin = z;
            this.mBirthday = date;
        }
        PreferenceUtil.setString("LoggedInUserName", str6);
        if (wishImage != null) {
            PreferenceUtil.setInsecureJSONObject("LoggedInUserImage", wishImage.getJSONObject());
        } else {
            PreferenceUtil.setInsecureJSONObject("LoggedInUserImage", null);
        }
        ApplicationEventManager.getInstance().triggerEvent(EventType.DATA_CENTER_UPDATED, getClass().toString(), null);
        markForSerialization();
        GoogleAnalyticsLogger.getInstance().logUserIdentifiers();
    }

    /* access modifiers changed from: protected */
    public boolean processSerializedData(JSONObject jSONObject, Bundle bundle) {
        try {
            initializeData(JsonUtil.optString(jSONObject, "userId"), JsonUtil.optString(jSONObject, "fbId"), JsonUtil.optString(jSONObject, "googleId"), JsonUtil.optString(jSONObject, "email"), JsonUtil.optString(jSONObject, "countryCode"), JsonUtil.optString(jSONObject, "name"), JsonUtil.optString(jSONObject, "firstName"), JsonUtil.hasValue(jSONObject, "profileImage") ? new WishImage(jSONObject.getJSONObject("profileImage")) : null, JsonUtil.optString(jSONObject, "gender"), jSONObject.optBoolean("isAdmin", false), JsonUtil.hasValue(jSONObject, "birthday") ? new Date(jSONObject.getLong("birthday")) : null);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void clearData() {
        synchronized (this.mLock) {
            this.mUserId = null;
            this.mFbId = null;
            this.mGoogleId = null;
            this.mEmail = null;
            this.mCountryCode = null;
            this.mName = null;
            this.mFirstName = null;
            this.mProfileImage = null;
            this.mGender = null;
            this.mIsAdmin = false;
            this.mBirthday = null;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x006a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject getSerializationData() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            r1 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Throwable -> 0x0069 }
            r2.<init>()     // Catch:{ Throwable -> 0x0069 }
            java.lang.String r1 = "userId"
            java.lang.String r3 = r5.mUserId     // Catch:{ Throwable -> 0x006a }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x006a }
            java.lang.String r1 = "fbId"
            java.lang.String r3 = r5.mFbId     // Catch:{ Throwable -> 0x006a }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x006a }
            java.lang.String r1 = "googleId"
            java.lang.String r3 = r5.mGoogleId     // Catch:{ Throwable -> 0x006a }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x006a }
            java.lang.String r1 = "email"
            java.lang.String r3 = r5.mEmail     // Catch:{ Throwable -> 0x006a }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x006a }
            java.lang.String r1 = "countryCode"
            java.lang.String r3 = r5.mCountryCode     // Catch:{ Throwable -> 0x006a }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x006a }
            java.lang.String r1 = "name"
            java.lang.String r3 = r5.mName     // Catch:{ Throwable -> 0x006a }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x006a }
            java.lang.String r1 = "firstName"
            java.lang.String r3 = r5.mFirstName     // Catch:{ Throwable -> 0x006a }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x006a }
            java.lang.String r1 = "isAdmin"
            boolean r3 = r5.mIsAdmin     // Catch:{ Throwable -> 0x006a }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x006a }
            java.util.Date r1 = r5.mBirthday     // Catch:{ Throwable -> 0x006a }
            if (r1 == 0) goto L_0x0050
            java.lang.String r1 = "birthday"
            java.util.Date r3 = r5.mBirthday     // Catch:{ Throwable -> 0x006a }
            long r3 = r3.getTime()     // Catch:{ Throwable -> 0x006a }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x006a }
        L_0x0050:
            com.contextlogic.wish.api.model.WishImage r1 = r5.mProfileImage     // Catch:{ Throwable -> 0x006a }
            if (r1 == 0) goto L_0x005f
            java.lang.String r1 = "profileImage"
            com.contextlogic.wish.api.model.WishImage r3 = r5.mProfileImage     // Catch:{ Throwable -> 0x006a }
            org.json.JSONObject r3 = r3.getJSONObject()     // Catch:{ Throwable -> 0x006a }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x006a }
        L_0x005f:
            java.lang.String r1 = "gender"
            java.lang.String r3 = r5.mGender     // Catch:{ Throwable -> 0x006a }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x006a }
            goto L_0x006a
        L_0x0067:
            r1 = move-exception
            goto L_0x006c
        L_0x0069:
            r2 = r1
        L_0x006a:
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            return r2
        L_0x006c:
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.datacenter.ProfileDataCenter.getSerializationData():org.json.JSONObject");
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        this.mGetProfileService.cancelAllRequests();
    }

    /* access modifiers changed from: protected */
    public RefreshMode getRefreshMode() {
        return RefreshMode.ON_FOREGROUND;
    }

    public void refresh() {
        if (AuthenticationDataCenter.getInstance().isLoggedIn()) {
            this.mGetProfileService.requestService(AuthenticationDataCenter.getInstance().getUserId(), null, null);
        }
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getName() {
        return this.mName;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public String getLastName() {
        return this.mName.replaceFirst(this.mFirstName, "").trim();
    }

    public WishImage getProfileImage() {
        return this.mProfileImage;
    }

    public boolean isAdmin() {
        return this.mIsAdmin;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public String getGender() {
        return this.mGender;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public Date getBirthday() {
        return this.mBirthday;
    }
}
