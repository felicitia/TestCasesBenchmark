package com.contextlogic.wish.api.datacenter;

import android.os.Bundle;
import com.contextlogic.wish.api.model.GeocodingRequest;
import com.contextlogic.wish.api.model.WishDailyGiveawayNotificationInfo;
import com.contextlogic.wish.api.model.WishInviteCouponSpec;
import com.contextlogic.wish.api.model.WishLocalNotification;
import com.contextlogic.wish.api.model.WishPaymentProcessorData;
import com.contextlogic.wish.api.model.WishSettingItem;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.contextlogic.wish.util.PreferenceUtil;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConfigDataCenter extends DataCenter {
    private static ConfigDataCenter sInstance = new ConfigDataCenter();
    private ArrayList<WishSettingItem> mAccountSettings;
    private LinkedHashMap<String, String> mAllCountries;
    private ArrayList<Long> mApiRetryDelays;
    private HashSet<String> mApiRetryPaths;
    private String mBillingSecurityText;
    private String mBillingSecurityTitle;
    private HashMap<String, String> mCountrySubdivisionNames;
    private WishDailyGiveawayNotificationInfo mDailyGiveawayNotiInfo;
    private boolean mDataInitialized;
    private GeocodingRequest mGeocodingRequest;
    private WishInviteCouponSpec mInviteCouponSpec;
    private String mInviteMessage;
    private String mInviteSubject;
    private String mKlarnaCountryCode;
    private WishLocalNotification mLocalNotification;
    private Object mLock = new Object();
    private WishPaymentProcessorData mPaymentProcessorData;
    private String mProductImageResizeUrl;
    private String mReferralMenuTitle;
    private ArrayList<String> mResizedProductImagePaths;
    private ArrayList<WishSettingItem> mSettings;
    private LinkedHashMap<String, String> mShippingCountries;
    private boolean mShippingDataRefreshed;
    private HashMap<String, ArrayList<String>> mShippingLocations;
    private String mUserPhoneNumber;
    private String mVideoUploadUrl;

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
    }

    /* access modifiers changed from: protected */
    public String getSerializationFileName() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getSerializationPreferenceName() {
        return "ConfigDataCenter";
    }

    public void refresh() {
    }

    private ConfigDataCenter() {
        clearData();
    }

    public static ConfigDataCenter getInstance() {
        return sInstance;
    }

    public WishLocalNotification getLocalNotification() {
        return this.mLocalNotification;
    }

    public boolean isDataInitialized() {
        return this.mDataInitialized;
    }

    /* JADX INFO: finally extract failed */
    public void initializeData(WishLocalNotification wishLocalNotification, ArrayList<WishSettingItem> arrayList, ArrayList<WishSettingItem> arrayList2, ArrayList<String> arrayList3, String str, String str2, String str3, HashMap<String, ArrayList<String>> hashMap, LinkedHashMap<String, String> linkedHashMap, LinkedHashMap<String, String> linkedHashMap2, HashMap<String, String> hashMap2, String str4, String str5, String str6, WishPaymentProcessorData wishPaymentProcessorData, ArrayList<Long> arrayList4, HashSet<String> hashSet, WishInviteCouponSpec wishInviteCouponSpec, WishDailyGiveawayNotificationInfo wishDailyGiveawayNotificationInfo, String str7, String str8, String str9, GeocodingRequest geocodingRequest) {
        synchronized (this.mLock) {
            try {
                this.mLocalNotification = wishLocalNotification;
                this.mSettings.clear();
                this.mSettings.addAll(arrayList);
                this.mAccountSettings.clear();
                this.mAccountSettings.addAll(arrayList2);
                this.mResizedProductImagePaths.clear();
                this.mResizedProductImagePaths.addAll(arrayList3);
                this.mProductImageResizeUrl = str;
                this.mBillingSecurityTitle = str2;
                this.mBillingSecurityText = str3;
                if (getShippingDataNeedsRefresh() || !hashMap.isEmpty()) {
                    this.mShippingLocations.clear();
                    this.mShippingLocations.putAll(hashMap);
                }
                this.mAllCountries.clear();
                this.mAllCountries.putAll(linkedHashMap);
                this.mShippingCountries.clear();
                this.mShippingCountries.putAll(linkedHashMap2);
                this.mCountrySubdivisionNames.clear();
                this.mCountrySubdivisionNames.putAll(hashMap2);
                this.mInviteMessage = str5;
                this.mInviteSubject = str4;
                this.mKlarnaCountryCode = str6;
                this.mPaymentProcessorData = wishPaymentProcessorData;
                this.mApiRetryDelays.clear();
                this.mApiRetryDelays.addAll(arrayList4);
                this.mApiRetryPaths.clear();
                this.mApiRetryPaths.addAll(hashSet);
                this.mInviteCouponSpec = wishInviteCouponSpec;
                this.mDailyGiveawayNotiInfo = wishDailyGiveawayNotificationInfo;
                this.mVideoUploadUrl = str7;
                this.mReferralMenuTitle = str8;
                this.mUserPhoneNumber = str9;
                this.mGeocodingRequest = geocodingRequest;
                this.mDataInitialized = true;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        ApplicationEventManager.getInstance().triggerEvent(EventType.DATA_CENTER_UPDATED, getClass().toString(), null);
        markForSerialization();
    }

    public GeocodingRequest getGeocodingRequest() {
        return this.mGeocodingRequest;
    }

    public ArrayList<String> getResizedProductImagePaths() {
        ArrayList<String> arrayList;
        synchronized (this.mLock) {
            arrayList = this.mResizedProductImagePaths != null ? (ArrayList) this.mResizedProductImagePaths.clone() : null;
        }
        return arrayList;
    }

    public ArrayList<WishSettingItem> getSettings() {
        ArrayList<WishSettingItem> arrayList;
        synchronized (this.mLock) {
            arrayList = this.mResizedProductImagePaths != null ? (ArrayList) this.mSettings.clone() : null;
        }
        return arrayList;
    }

    public ArrayList<WishSettingItem> getAccountSettings() {
        ArrayList<WishSettingItem> arrayList;
        synchronized (this.mLock) {
            arrayList = this.mResizedProductImagePaths != null ? (ArrayList) this.mAccountSettings.clone() : null;
        }
        return arrayList;
    }

    public String getProductImageResizeUrl() {
        return this.mProductImageResizeUrl;
    }

    public HashMap<String, ArrayList<String>> getShippingLocations() {
        HashMap<String, ArrayList<String>> hashMap;
        synchronized (this.mLock) {
            hashMap = this.mShippingLocations != null ? (HashMap) this.mShippingLocations.clone() : null;
        }
        return hashMap;
    }

    public LinkedHashMap<String, String> getAllCountries() {
        LinkedHashMap<String, String> linkedHashMap;
        synchronized (this.mLock) {
            linkedHashMap = this.mAllCountries != null ? (LinkedHashMap) this.mAllCountries.clone() : null;
        }
        return linkedHashMap;
    }

    public HashMap<String, String> getCountrySubdivisionNames() {
        HashMap<String, String> hashMap;
        synchronized (this.mLock) {
            hashMap = this.mCountrySubdivisionNames != null ? (HashMap) this.mCountrySubdivisionNames.clone() : null;
        }
        return hashMap;
    }

    public String getBillingSecurityTitle() {
        return this.mBillingSecurityTitle;
    }

    public String getBillingSecurityText() {
        return this.mBillingSecurityText;
    }

    public String getInviteSubject() {
        return this.mInviteSubject;
    }

    public String getInviteMessage() {
        return this.mInviteMessage;
    }

    public String getKlarnaCountryCode() {
        return this.mKlarnaCountryCode;
    }

    public String getVideoUploadUrl() {
        return this.mVideoUploadUrl;
    }

    public WishPaymentProcessorData getPaymentProcessorData() {
        return this.mPaymentProcessorData;
    }

    public WishInviteCouponSpec getInviteCouponSpec() {
        return this.mInviteCouponSpec;
    }

    public WishDailyGiveawayNotificationInfo getDailyGiveawayNotiInfo() {
        return this.mDailyGiveawayNotiInfo;
    }

    public String getReferralMenuTitle() {
        return this.mReferralMenuTitle;
    }

    public String getUserPhoneNumber() {
        return this.mUserPhoneNumber;
    }

    public void setShippingDataRefreshed(boolean z) {
        this.mShippingDataRefreshed = z;
    }

    public boolean getShippingDataNeedsRefresh() {
        return !this.mShippingDataRefreshed || this.mShippingLocations == null || this.mShippingLocations.isEmpty();
    }

    public ArrayList<Long> getApiRetryDelays() {
        ArrayList<Long> arrayList;
        synchronized (this.mLock) {
            arrayList = this.mApiRetryDelays != null ? (ArrayList) this.mApiRetryDelays.clone() : null;
        }
        return arrayList;
    }

    public HashSet<String> getApiRetryPaths() {
        HashSet<String> hashSet;
        synchronized (this.mLock) {
            hashSet = this.mApiRetryPaths != null ? (HashSet) this.mApiRetryPaths.clone() : null;
        }
        return hashSet;
    }

    /* access modifiers changed from: protected */
    public boolean processSerializedData(JSONObject jSONObject, Bundle bundle) {
        String str;
        String str2;
        JSONObject jSONObject2 = jSONObject;
        try {
            WishLocalNotification wishLocalNotification = JsonUtil.hasValue(jSONObject2, "localNotification") ? new WishLocalNotification(jSONObject2.getJSONObject("localNotification")) : null;
            ArrayList parseArray = JsonUtil.parseArray(jSONObject2, "settings", new DataParser<WishSettingItem, JSONObject>() {
                public WishSettingItem parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishSettingItem(jSONObject);
                }
            });
            ArrayList parseArray2 = JsonUtil.parseArray(jSONObject2, "accountSettings", new DataParser<WishSettingItem, JSONObject>() {
                public WishSettingItem parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishSettingItem(jSONObject);
                }
            });
            ArrayList parseArray3 = JsonUtil.parseArray(jSONObject2, "resizedProductImagePaths", new DataParser<String, Object>() {
                public String parseData(Object obj) throws JSONException {
                    return String.valueOf(obj);
                }
            });
            String optString = JsonUtil.optString(jSONObject2, "productImageResizeUrl");
            String optString2 = JsonUtil.optString(jSONObject2, "billingSecurityTitle");
            String optString3 = JsonUtil.optString(jSONObject2, "billingSecurityText");
            String optString4 = JsonUtil.optString(jSONObject2, "klarnaCountryCode");
            String optString5 = JsonUtil.optString(jSONObject2, "inviteMessage");
            String optString6 = JsonUtil.optString(jSONObject2, "inviteSubject");
            String optString7 = JsonUtil.optString(jSONObject2, "videoUploadUrl");
            WishPaymentProcessorData wishPaymentProcessorData = JsonUtil.hasValue(jSONObject2, "paymentProcessorData") ? new WishPaymentProcessorData(jSONObject2.getJSONObject("paymentProcessorData")) : null;
            HashMap hashMap = new HashMap();
            if (JsonUtil.hasValue(jSONObject2, "shippingLocations")) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("shippingLocations");
                Iterator keys = jSONObject3.keys();
                while (keys.hasNext()) {
                    String str3 = (String) keys.next();
                    Iterator it = keys;
                    JSONArray jSONArray = jSONObject3.getJSONArray(str3);
                    JSONObject jSONObject4 = jSONObject3;
                    ArrayList arrayList = new ArrayList();
                    if (jSONArray != null) {
                        str = optString5;
                        str2 = optString4;
                        for (int i = 0; i < jSONArray.length(); i++) {
                            arrayList.add(jSONArray.get(i).toString());
                        }
                    } else {
                        str = optString5;
                        str2 = optString4;
                    }
                    hashMap.put(str3, arrayList);
                    keys = it;
                    jSONObject3 = jSONObject4;
                    optString4 = str2;
                    optString5 = str;
                }
            }
            String str4 = optString5;
            String str5 = optString4;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            if (JsonUtil.hasValue(jSONObject2, "allCountries")) {
                JSONObject jSONObject5 = jSONObject2.getJSONObject("allCountries");
                Iterator keys2 = jSONObject5.keys();
                while (keys2.hasNext()) {
                    String str6 = (String) keys2.next();
                    linkedHashMap.put(str6, jSONObject5.getString(str6));
                }
            }
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            if (JsonUtil.hasValue(jSONObject2, "shippingCountries")) {
                JSONObject jSONObject6 = jSONObject2.getJSONObject("shippingCountries");
                Iterator keys3 = jSONObject6.keys();
                while (keys3.hasNext()) {
                    String str7 = (String) keys3.next();
                    Iterator it2 = keys3;
                    linkedHashMap2.put(str7, jSONObject6.getString(str7));
                    keys3 = it2;
                }
            }
            HashMap hashMap2 = new HashMap();
            if (JsonUtil.hasValue(jSONObject2, "countrySubdivisionNames")) {
                JSONObject jSONObject7 = jSONObject2.getJSONObject("countrySubdivisionNames");
                Iterator keys4 = jSONObject7.keys();
                while (keys4.hasNext()) {
                    Iterator it3 = keys4;
                    String str8 = (String) keys4.next();
                    String str9 = optString6;
                    hashMap2.put(str8, jSONObject7.getString(str8));
                    keys4 = it3;
                    optString6 = str9;
                }
            }
            initializeData(wishLocalNotification, parseArray, parseArray2, parseArray3, optString, optString2, optString3, hashMap, linkedHashMap, linkedHashMap2, hashMap2, optString6, str4, str5, wishPaymentProcessorData, JsonUtil.parseArray(jSONObject2, "apiRetryDelays", new DataParser<Long, Long>() {
                public Long parseData(Long l) throws JSONException {
                    return l;
                }
            }), new HashSet(JsonUtil.parseArray(jSONObject2, "apiRetryPaths", new DataParser<String, String>() {
                public String parseData(String str) throws JSONException {
                    return str;
                }
            })), JsonUtil.hasValue(jSONObject2, "inviteCouponSpec") ? new WishInviteCouponSpec(jSONObject2.getJSONObject("inviteCouponSpec")) : null, JsonUtil.hasValue(jSONObject2, "dailyGiveawayNoti") ? new WishDailyGiveawayNotificationInfo(jSONObject2.getJSONObject("dailyGiveawayNoti")) : null, optString7, jSONObject2.optString("referralMenuTitle", null), jSONObject2.optString("userPhoneNumber", null), JsonUtil.hasValue(jSONObject2, "geocodingRequest") ? new GeocodingRequest(jSONObject2.getJSONObject("geocodingRequest")) : null);
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
            this.mLocalNotification = null;
            this.mSettings = new ArrayList<>();
            this.mAccountSettings = new ArrayList<>();
            this.mResizedProductImagePaths = new ArrayList<>();
            this.mBillingSecurityTitle = null;
            this.mBillingSecurityText = null;
            this.mShippingLocations = new HashMap<>();
            this.mAllCountries = new LinkedHashMap<>();
            this.mShippingCountries = new LinkedHashMap<>();
            this.mCountrySubdivisionNames = new HashMap<>();
            this.mInviteSubject = null;
            this.mInviteMessage = null;
            this.mKlarnaCountryCode = null;
            this.mPaymentProcessorData = null;
            this.mApiRetryDelays = new ArrayList<>();
            this.mApiRetryPaths = new HashSet<>();
            this.mDataInitialized = false;
            this.mInviteCouponSpec = null;
            this.mDailyGiveawayNotiInfo = null;
            this.mVideoUploadUrl = null;
            this.mReferralMenuTitle = null;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:80:0x01b7 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject getSerializationData() {
        /*
            r7 = this;
            java.lang.Object r0 = r7.mLock
            monitor-enter(r0)
            r1 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Throwable -> 0x01b6 }
            r2.<init>()     // Catch:{ Throwable -> 0x01b6 }
            java.util.ArrayList<com.contextlogic.wish.api.model.WishSettingItem> r1 = r7.mSettings     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x0031
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ Throwable -> 0x01b7 }
            r1.<init>()     // Catch:{ Throwable -> 0x01b7 }
            java.util.ArrayList<com.contextlogic.wish.api.model.WishSettingItem> r3 = r7.mSettings     // Catch:{ Throwable -> 0x01b7 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Throwable -> 0x01b7 }
        L_0x0018:
            boolean r4 = r3.hasNext()     // Catch:{ Throwable -> 0x01b7 }
            if (r4 == 0) goto L_0x002c
            java.lang.Object r4 = r3.next()     // Catch:{ Throwable -> 0x01b7 }
            com.contextlogic.wish.api.model.WishSettingItem r4 = (com.contextlogic.wish.api.model.WishSettingItem) r4     // Catch:{ Throwable -> 0x01b7 }
            org.json.JSONObject r4 = r4.getJSONObject()     // Catch:{ Throwable -> 0x01b7 }
            r1.put(r4)     // Catch:{ Throwable -> 0x01b7 }
            goto L_0x0018
        L_0x002c:
            java.lang.String r3 = "settings"
            r2.put(r3, r1)     // Catch:{ Throwable -> 0x01b7 }
        L_0x0031:
            java.util.ArrayList<com.contextlogic.wish.api.model.WishSettingItem> r1 = r7.mAccountSettings     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x0059
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ Throwable -> 0x01b7 }
            r1.<init>()     // Catch:{ Throwable -> 0x01b7 }
            java.util.ArrayList<com.contextlogic.wish.api.model.WishSettingItem> r3 = r7.mAccountSettings     // Catch:{ Throwable -> 0x01b7 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Throwable -> 0x01b7 }
        L_0x0040:
            boolean r4 = r3.hasNext()     // Catch:{ Throwable -> 0x01b7 }
            if (r4 == 0) goto L_0x0054
            java.lang.Object r4 = r3.next()     // Catch:{ Throwable -> 0x01b7 }
            com.contextlogic.wish.api.model.WishSettingItem r4 = (com.contextlogic.wish.api.model.WishSettingItem) r4     // Catch:{ Throwable -> 0x01b7 }
            org.json.JSONObject r4 = r4.getJSONObject()     // Catch:{ Throwable -> 0x01b7 }
            r1.put(r4)     // Catch:{ Throwable -> 0x01b7 }
            goto L_0x0040
        L_0x0054:
            java.lang.String r3 = "accountSettings"
            r2.put(r3, r1)     // Catch:{ Throwable -> 0x01b7 }
        L_0x0059:
            java.util.ArrayList<java.lang.String> r1 = r7.mResizedProductImagePaths     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x007d
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ Throwable -> 0x01b7 }
            r1.<init>()     // Catch:{ Throwable -> 0x01b7 }
            java.util.ArrayList<java.lang.String> r3 = r7.mResizedProductImagePaths     // Catch:{ Throwable -> 0x01b7 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Throwable -> 0x01b7 }
        L_0x0068:
            boolean r4 = r3.hasNext()     // Catch:{ Throwable -> 0x01b7 }
            if (r4 == 0) goto L_0x0078
            java.lang.Object r4 = r3.next()     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Throwable -> 0x01b7 }
            r1.put(r4)     // Catch:{ Throwable -> 0x01b7 }
            goto L_0x0068
        L_0x0078:
            java.lang.String r3 = "resizedProductImagePaths"
            r2.put(r3, r1)     // Catch:{ Throwable -> 0x01b7 }
        L_0x007d:
            java.lang.String r1 = "productImageResizeUrl"
            java.lang.String r3 = r7.mProductImageResizeUrl     // Catch:{ Throwable -> 0x01b7 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r1 = "billingSecurityTitle"
            java.lang.String r3 = r7.mBillingSecurityTitle     // Catch:{ Throwable -> 0x01b7 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r1 = "billingSecurityText"
            java.lang.String r3 = r7.mBillingSecurityText     // Catch:{ Throwable -> 0x01b7 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r1 = "inviteSubject"
            java.lang.String r3 = r7.mInviteSubject     // Catch:{ Throwable -> 0x01b7 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r1 = "inviteMessage"
            java.lang.String r3 = r7.mInviteMessage     // Catch:{ Throwable -> 0x01b7 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r1 = "klarnaCountryCode"
            java.lang.String r3 = r7.mKlarnaCountryCode     // Catch:{ Throwable -> 0x01b7 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r1 = "videoUploadUrl"
            java.lang.String r3 = r7.mVideoUploadUrl     // Catch:{ Throwable -> 0x01b7 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x01b7 }
            com.contextlogic.wish.api.model.WishPaymentProcessorData r1 = r7.mPaymentProcessorData     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x00bf
            com.contextlogic.wish.api.model.WishPaymentProcessorData r1 = r7.mPaymentProcessorData     // Catch:{ Throwable -> 0x01b7 }
            org.json.JSONObject r1 = r1.getJSONObject()     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x00bf
            java.lang.String r3 = "paymentProcessorData"
            r2.put(r3, r1)     // Catch:{ Throwable -> 0x01b7 }
        L_0x00bf:
            java.util.HashMap<java.lang.String, java.util.ArrayList<java.lang.String>> r1 = r7.mShippingLocations     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x00fa
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Throwable -> 0x01b7 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<java.lang.String>> r3 = r7.mShippingLocations     // Catch:{ Throwable -> 0x01b7 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x01b7 }
            java.util.HashMap<java.lang.String, java.util.ArrayList<java.lang.String>> r3 = r7.mShippingLocations     // Catch:{ Throwable -> 0x01b7 }
            java.util.Set r3 = r3.entrySet()     // Catch:{ Throwable -> 0x01b7 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Throwable -> 0x01b7 }
        L_0x00d4:
            boolean r4 = r3.hasNext()     // Catch:{ Throwable -> 0x01b7 }
            if (r4 == 0) goto L_0x00f5
            java.lang.Object r4 = r3.next()     // Catch:{ Throwable -> 0x01b7 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ Throwable -> 0x01b7 }
            org.json.JSONArray r5 = new org.json.JSONArray     // Catch:{ Throwable -> 0x01b7 }
            java.lang.Object r6 = r4.getValue()     // Catch:{ Throwable -> 0x01b7 }
            java.util.Collection r6 = (java.util.Collection) r6     // Catch:{ Throwable -> 0x01b7 }
            r5.<init>(r6)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.Object r4 = r4.getKey()     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Throwable -> 0x01b7 }
            r1.put(r4, r5)     // Catch:{ Throwable -> 0x01b7 }
            goto L_0x00d4
        L_0x00f5:
            java.lang.String r3 = "shippingLocations"
            r2.put(r3, r1)     // Catch:{ Throwable -> 0x01b7 }
        L_0x00fa:
            java.util.LinkedHashMap<java.lang.String, java.lang.String> r1 = r7.mAllCountries     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x010a
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Throwable -> 0x01b7 }
            java.util.LinkedHashMap<java.lang.String, java.lang.String> r3 = r7.mAllCountries     // Catch:{ Throwable -> 0x01b7 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r3 = "allCountries"
            r2.put(r3, r1)     // Catch:{ Throwable -> 0x01b7 }
        L_0x010a:
            java.util.LinkedHashMap<java.lang.String, java.lang.String> r1 = r7.mShippingCountries     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x011a
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Throwable -> 0x01b7 }
            java.util.LinkedHashMap<java.lang.String, java.lang.String> r3 = r7.mShippingCountries     // Catch:{ Throwable -> 0x01b7 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r3 = "shippingCountries"
            r2.put(r3, r1)     // Catch:{ Throwable -> 0x01b7 }
        L_0x011a:
            java.util.HashMap<java.lang.String, java.lang.String> r1 = r7.mCountrySubdivisionNames     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x012a
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Throwable -> 0x01b7 }
            java.util.HashMap<java.lang.String, java.lang.String> r3 = r7.mCountrySubdivisionNames     // Catch:{ Throwable -> 0x01b7 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r3 = "countrySubdivisionNames"
            r2.put(r3, r1)     // Catch:{ Throwable -> 0x01b7 }
        L_0x012a:
            java.util.ArrayList<java.lang.Long> r1 = r7.mApiRetryDelays     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x014e
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ Throwable -> 0x01b7 }
            r1.<init>()     // Catch:{ Throwable -> 0x01b7 }
            java.util.ArrayList<java.lang.Long> r3 = r7.mApiRetryDelays     // Catch:{ Throwable -> 0x01b7 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Throwable -> 0x01b7 }
        L_0x0139:
            boolean r4 = r3.hasNext()     // Catch:{ Throwable -> 0x01b7 }
            if (r4 == 0) goto L_0x0149
            java.lang.Object r4 = r3.next()     // Catch:{ Throwable -> 0x01b7 }
            java.lang.Long r4 = (java.lang.Long) r4     // Catch:{ Throwable -> 0x01b7 }
            r1.put(r4)     // Catch:{ Throwable -> 0x01b7 }
            goto L_0x0139
        L_0x0149:
            java.lang.String r3 = "apiRetryDelays"
            r2.put(r3, r1)     // Catch:{ Throwable -> 0x01b7 }
        L_0x014e:
            java.util.HashSet<java.lang.String> r1 = r7.mApiRetryPaths     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x0172
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ Throwable -> 0x01b7 }
            r1.<init>()     // Catch:{ Throwable -> 0x01b7 }
            java.util.HashSet<java.lang.String> r3 = r7.mApiRetryPaths     // Catch:{ Throwable -> 0x01b7 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Throwable -> 0x01b7 }
        L_0x015d:
            boolean r4 = r3.hasNext()     // Catch:{ Throwable -> 0x01b7 }
            if (r4 == 0) goto L_0x016d
            java.lang.Object r4 = r3.next()     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Throwable -> 0x01b7 }
            r1.put(r4)     // Catch:{ Throwable -> 0x01b7 }
            goto L_0x015d
        L_0x016d:
            java.lang.String r3 = "apiRetryPaths"
            r2.put(r3, r1)     // Catch:{ Throwable -> 0x01b7 }
        L_0x0172:
            com.contextlogic.wish.api.model.WishInviteCouponSpec r1 = r7.mInviteCouponSpec     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x0183
            com.contextlogic.wish.api.model.WishInviteCouponSpec r1 = r7.mInviteCouponSpec     // Catch:{ Throwable -> 0x01b7 }
            org.json.JSONObject r1 = r1.getJSONObject()     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x0183
            java.lang.String r3 = "inviteCouponSpec"
            r2.put(r3, r1)     // Catch:{ Throwable -> 0x01b7 }
        L_0x0183:
            com.contextlogic.wish.api.model.WishDailyGiveawayNotificationInfo r1 = r7.mDailyGiveawayNotiInfo     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x0194
            com.contextlogic.wish.api.model.WishDailyGiveawayNotificationInfo r1 = r7.mDailyGiveawayNotiInfo     // Catch:{ Throwable -> 0x01b7 }
            org.json.JSONObject r1 = r1.getJSONObject()     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x0194
            java.lang.String r3 = "dailyGiveawayNoti"
            r2.put(r3, r1)     // Catch:{ Throwable -> 0x01b7 }
        L_0x0194:
            java.lang.String r1 = "referralMenuTitle"
            java.lang.String r3 = r7.mReferralMenuTitle     // Catch:{ Throwable -> 0x01b7 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x01b7 }
            java.lang.String r1 = "userPhoneNumber"
            java.lang.String r3 = r7.mUserPhoneNumber     // Catch:{ Throwable -> 0x01b7 }
            r2.put(r1, r3)     // Catch:{ Throwable -> 0x01b7 }
            com.contextlogic.wish.api.model.GeocodingRequest r1 = r7.mGeocodingRequest     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x01b7
            com.contextlogic.wish.api.model.GeocodingRequest r1 = r7.mGeocodingRequest     // Catch:{ Throwable -> 0x01b7 }
            org.json.JSONObject r1 = r1.getJSONObject()     // Catch:{ Throwable -> 0x01b7 }
            if (r1 == 0) goto L_0x01b7
            java.lang.String r3 = "geocodingRequest"
            r2.put(r3, r1)     // Catch:{ Throwable -> 0x01b7 }
            goto L_0x01b7
        L_0x01b4:
            r1 = move-exception
            goto L_0x01b9
        L_0x01b6:
            r2 = r1
        L_0x01b7:
            monitor-exit(r0)     // Catch:{ all -> 0x01b4 }
            return r2
        L_0x01b9:
            monitor-exit(r0)     // Catch:{ all -> 0x01b4 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.datacenter.ConfigDataCenter.getSerializationData():org.json.JSONObject");
    }

    /* access modifiers changed from: protected */
    public RefreshMode getRefreshMode() {
        return RefreshMode.MANUAL;
    }
}
