package com.contextlogic.wish.api.datacenter;

import android.os.Bundle;
import com.contextlogic.wish.api.model.GeocodingRequest;
import com.contextlogic.wish.api.model.WishCommerceCashUserInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.UrlExternalJsonApiService;
import com.contextlogic.wish.api.service.UrlExternalJsonApiService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetUserStatusService;
import com.contextlogic.wish.api.service.standalone.StoreGeocodeResultService;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class StatusDataCenter extends DataCenter {
    private static StatusDataCenter sInstance = new StatusDataCenter();
    private int mCartCount;
    private WishCommerceCashUserInfo mCommerceCashUserInfo;
    private boolean mDataInitialized;
    private GetUserStatusService mGetUserStatusService = new GetUserStatusService();
    private boolean mHasPurchasedBefore;
    private Object mLock = new Object();
    private int mRewardCount;
    private int mRewardLevel;
    private int mRewardPoints;
    private boolean mShowPriceWatchNewBadge;
    private boolean mShowPriceWatchNotification;
    /* access modifiers changed from: private */
    public StoreGeocodeResultService mStoreGeocodeResultService = new StoreGeocodeResultService();
    private int mUnviewedNotificationCount;
    private UrlExternalJsonApiService mUrlExternalJsonApiService = new UrlExternalJsonApiService();

    /* access modifiers changed from: protected */
    public String getSerializationFileName() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getSerializationPreferenceName() {
        return "StatusDataCenter";
    }

    private StatusDataCenter() {
        clearData();
    }

    public static StatusDataCenter getInstance() {
        return sInstance;
    }

    public boolean isDataInitialized() {
        return this.mDataInitialized;
    }

    public void initializeData(int i, int i2, int i3, int i4, int i5, WishCommerceCashUserInfo wishCommerceCashUserInfo, boolean z, boolean z2, boolean z3) {
        synchronized (this.mLock) {
            this.mUnviewedNotificationCount = i;
            this.mCartCount = i2;
            this.mRewardCount = i3;
            this.mRewardLevel = i4;
            this.mRewardPoints = i5;
            this.mDataInitialized = true;
            this.mCommerceCashUserInfo = wishCommerceCashUserInfo;
            this.mHasPurchasedBefore = z;
            this.mShowPriceWatchNewBadge = z2;
            this.mShowPriceWatchNotification = z3;
        }
        ApplicationEventManager.getInstance().triggerEvent(EventType.DATA_CENTER_UPDATED, getClass().toString(), null);
        markForSerialization();
    }

    public WishCommerceCashUserInfo getCommerceCashUserInfo() {
        return this.mCommerceCashUserInfo;
    }

    public int getUnviewedNotificationCount() {
        return this.mUnviewedNotificationCount;
    }

    public int getCartCount() {
        return this.mCartCount;
    }

    public int getRewardCount() {
        return this.mRewardCount;
    }

    public int getRewardPoints() {
        return this.mRewardPoints;
    }

    public boolean userHasPurchasedBefore() {
        return this.mHasPurchasedBefore;
    }

    public boolean showPriceWatchNewBadge() {
        return this.mShowPriceWatchNewBadge;
    }

    public boolean showPriceWatchNotification() {
        return this.mShowPriceWatchNotification;
    }

    public void updateShowPriceWatchNotification(boolean z) {
        this.mShowPriceWatchNotification = z;
    }

    public void updateCartCount(int i) {
        synchronized (this.mLock) {
            this.mCartCount = i;
        }
        ApplicationEventManager.getInstance().triggerEvent(EventType.DATA_CENTER_UPDATED, getClass().toString(), null);
        markForSerialization();
    }

    public void decrementUnviewedNotificationCount() {
        synchronized (this.mLock) {
            this.mUnviewedNotificationCount = Math.max(0, this.mUnviewedNotificationCount - 1);
        }
        ApplicationEventManager.getInstance().triggerEvent(EventType.DATA_CENTER_UPDATED, getClass().toString(), null);
        markForSerialization();
    }

    /* access modifiers changed from: protected */
    public boolean processSerializedData(JSONObject jSONObject, Bundle bundle) {
        try {
            int optInt = jSONObject.optInt("unviewedNotificationCount");
            int optInt2 = jSONObject.optInt("cartCount");
            int optInt3 = jSONObject.optInt("rewardCount");
            int optInt4 = jSONObject.optInt("rewardLevel");
            int optInt5 = jSONObject.optInt("rewardPoints");
            WishCommerceCashUserInfo wishCommerceCashUserInfo = null;
            if (JsonUtil.hasValue(jSONObject, "commerceCashUserInfo")) {
                wishCommerceCashUserInfo = new WishCommerceCashUserInfo(jSONObject.getJSONObject("commerceCashUserInfo"));
            }
            initializeData(optInt, optInt2, optInt3, optInt4, optInt5, wishCommerceCashUserInfo, jSONObject.optBoolean("hasPurchasedBefore", false), jSONObject.optBoolean("showPriceWatchNew", false), jSONObject.optBoolean("showPriceWatchNotification", false));
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean canDeserialize() {
        return !PreferenceUtil.getBoolean("ForceRelogin");
    }

    /* access modifiers changed from: protected */
    public void clearData() {
        synchronized (this.mLock) {
            this.mUnviewedNotificationCount = 0;
            this.mCartCount = 0;
            this.mRewardCount = 0;
            this.mRewardLevel = 1;
            this.mDataInitialized = false;
            this.mRewardPoints = 0;
            this.mCommerceCashUserInfo = null;
            this.mHasPurchasedBefore = false;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0050 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject getSerializationData() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            r1 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Throwable -> 0x004f }
            r2.<init>()     // Catch:{ Throwable -> 0x004f }
            java.lang.String r1 = "unviewedNotificationCount"
            int r3 = r4.mUnviewedNotificationCount     // Catch:{ Throwable -> 0x0050 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = "cartCount"
            int r3 = r4.mCartCount     // Catch:{ Throwable -> 0x0050 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = "rewardLevel"
            int r3 = r4.mRewardLevel     // Catch:{ Throwable -> 0x0050 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = "rewardCount"
            int r3 = r4.mRewardCount     // Catch:{ Throwable -> 0x0050 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = "rewardPoints"
            int r3 = r4.mRewardPoints     // Catch:{ Throwable -> 0x0050 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = "commerceCashUserInfo"
            com.contextlogic.wish.api.model.WishCommerceCashUserInfo r3 = r4.mCommerceCashUserInfo     // Catch:{ Throwable -> 0x0050 }
            org.json.JSONObject r3 = r3.toJSON()     // Catch:{ Throwable -> 0x0050 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = "hasPurchasedBefore"
            boolean r3 = r4.mHasPurchasedBefore     // Catch:{ Throwable -> 0x0050 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = "showPriceWatchNew"
            boolean r3 = r4.mShowPriceWatchNewBadge     // Catch:{ Throwable -> 0x0050 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = "showPriceWatchNotification"
            boolean r3 = r4.mShowPriceWatchNotification     // Catch:{ Throwable -> 0x0050 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x0050 }
            goto L_0x0050
        L_0x004d:
            r1 = move-exception
            goto L_0x0052
        L_0x004f:
            r2 = r1
        L_0x0050:
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            return r2
        L_0x0052:
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.datacenter.StatusDataCenter.getSerializationData():org.json.JSONObject");
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        this.mGetUserStatusService.cancelAllRequests();
        this.mStoreGeocodeResultService.cancelAllRequests();
    }

    /* access modifiers changed from: protected */
    public RefreshMode getRefreshMode() {
        return RefreshMode.PERIODIC;
    }

    public void refresh() {
        if (AuthenticationDataCenter.getInstance().isLoggedIn()) {
            this.mGetUserStatusService.requestService(new DefaultSuccessCallback() {
                public void onSuccess() {
                    StatusDataCenter.this.saveGeocodingResults(ConfigDataCenter.getInstance().getGeocodingRequest(), true);
                }
            }, null);
        }
    }

    private void cleanUpGeocodingRequestTimestampMap(JSONObject jSONObject) {
        long currentTimeMillis = System.currentTimeMillis();
        Iterator keys = jSONObject.keys();
        boolean z = false;
        while (keys.hasNext()) {
            if (currentTimeMillis - jSONObject.optLong((String) keys.next(), 0) > 86400000) {
                keys.remove();
                z = true;
            }
        }
        if (z) {
            PreferenceUtil.setInsecureJSONObject("GeoCodingTimestamps", jSONObject);
        }
    }

    public void saveGeocodingResults(GeocodingRequest geocodingRequest, boolean z) {
        if (geocodingRequest != null) {
            JSONObject insecureJSONObject = PreferenceUtil.getInsecureJSONObject("GeoCodingTimestamps");
            if (insecureJSONObject == null) {
                insecureJSONObject = new JSONObject();
            }
            final JSONObject jSONObject = insecureJSONObject;
            cleanUpGeocodingRequestTimestampMap(jSONObject);
            final String keyForHashAndSource = getKeyForHashAndSource(geocodingRequest.getHash(), geocodingRequest.getSource());
            if (!jSONObject.has(keyForHashAndSource)) {
                UrlExternalJsonApiService urlExternalJsonApiService = this.mUrlExternalJsonApiService;
                String url = geocodingRequest.getUrl();
                final boolean z2 = z;
                final GeocodingRequest geocodingRequest2 = geocodingRequest;
                AnonymousClass2 r1 = new SuccessCallback() {
                    public void onSuccess(JSONObject jSONObject) {
                        if (z2) {
                            try {
                                jSONObject.put(keyForHashAndSource, System.currentTimeMillis());
                            } catch (JSONException unused) {
                            }
                            PreferenceUtil.setInsecureJSONObject("GeoCodingTimestamps", jSONObject);
                        }
                        StatusDataCenter.this.mStoreGeocodeResultService.requestService(geocodingRequest2, jSONObject.toString(), null, null);
                    }
                };
                urlExternalJsonApiService.requestService(url, r1, null);
            }
        }
    }

    private static String getKeyForHashAndSource(String str, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(":");
        sb.append(String.valueOf(i));
        return sb.toString();
    }
}
