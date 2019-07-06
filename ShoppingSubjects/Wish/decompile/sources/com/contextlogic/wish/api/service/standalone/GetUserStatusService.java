package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.api.model.GeocodingRequest;
import com.contextlogic.wish.api.model.WishCommerceCashUserInfo;
import com.contextlogic.wish.api.model.WishDailyGiveawayNotificationInfo;
import com.contextlogic.wish.api.model.WishInviteCouponSpec;
import com.contextlogic.wish.api.model.WishLocalNotification;
import com.contextlogic.wish.api.model.WishPaymentProcessorData;
import com.contextlogic.wish.api.model.WishSettingItem;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.SingleApiService;
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

public class GetUserStatusService extends SingleApiService {
    /* access modifiers changed from: protected */
    public boolean shouldSampleConnection() {
        return true;
    }

    public void requestService(final DefaultSuccessCallback defaultSuccessCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("user/status");
        boolean shippingDataNeedsRefresh = ConfigDataCenter.getInstance().getShippingDataNeedsRefresh();
        apiRequest.addParameter("supports_mobile_action_dict", true);
        if (shippingDataNeedsRefresh) {
            apiRequest.addParameter("shipping_location_data", true);
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetUserStatusService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                WishDailyGiveawayNotificationInfo wishDailyGiveawayNotificationInfo;
                StatusDataCenter.getInstance().initializeData(apiResponse.getData().getInt("unviewed_noti_count"), apiResponse.getData().optInt("cart_items"), apiResponse.getData().optInt("reward_item_count"), apiResponse.getData().optInt("user_reward_level", 1), apiResponse.getData().optInt("user_reward_points", 0), new WishCommerceCashUserInfo(apiResponse.getData().optJSONObject("commerce_cash")), apiResponse.getData().optBoolean("has_purchased_before"), apiResponse.getData().optBoolean("show_price_watch_new_badge", false), apiResponse.getData().optBoolean("show_price_watch_noti_badge", false));
                JSONObject optJSONObject = apiResponse.getData().optJSONObject("mobile_experiments");
                HashMap hashMap = new HashMap();
                if (optJSONObject != null) {
                    Iterator keys = optJSONObject.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        hashMap.put(str, optJSONObject.getString(str));
                    }
                }
                ExperimentDataCenter.getInstance().initializeData(hashMap);
                GeocodingRequest geocodingRequest = null;
                WishLocalNotification wishLocalNotification = JsonUtil.hasValue(apiResponse.getData(), "local_background_notification_spec") ? new WishLocalNotification(apiResponse.getData().getJSONObject("local_background_notification_spec")) : null;
                ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "settings", new DataParser<WishSettingItem, JSONObject>() {
                    public WishSettingItem parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishSettingItem(jSONObject);
                    }
                });
                ArrayList parseArray2 = JsonUtil.parseArray(apiResponse.getData(), "account_settings", new DataParser<WishSettingItem, JSONObject>() {
                    public WishSettingItem parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishSettingItem(jSONObject);
                    }
                });
                ArrayList arrayList = new ArrayList();
                if (JsonUtil.hasValue(apiResponse.getData(), "resized_product_image_paths")) {
                    arrayList = JsonUtil.parseArray(apiResponse.getData(), "resized_product_image_paths", new DataParser<String, Object>() {
                        public String parseData(Object obj) {
                            return String.valueOf(obj);
                        }
                    });
                }
                ArrayList arrayList2 = arrayList;
                String optString = JsonUtil.optString(apiResponse.getData(), "lemmings_url");
                String optString2 = JsonUtil.optString(apiResponse.getData(), "billing_security_title");
                String optString3 = JsonUtil.optString(apiResponse.getData(), "billing_security_text");
                String optString4 = JsonUtil.optString(apiResponse.getData(), "klarna_country_code");
                String optString5 = JsonUtil.optString(apiResponse.getData(), "invite_message");
                String optString6 = JsonUtil.optString(apiResponse.getData(), "invite_subject");
                WishPaymentProcessorData wishPaymentProcessorData = JsonUtil.hasValue(apiResponse.getData(), "payment_processor_data") ? new WishPaymentProcessorData(apiResponse.getData().getJSONObject("payment_processor_data")) : null;
                HashMap hashMap2 = new HashMap();
                if (JsonUtil.hasValue(apiResponse.getData(), "shipping_location_data")) {
                    JSONObject jSONObject = apiResponse.getData().getJSONObject("shipping_location_data");
                    Iterator keys2 = jSONObject.keys();
                    while (keys2.hasNext()) {
                        String str2 = (String) keys2.next();
                        JSONArray jSONArray = jSONObject.getJSONArray(str2);
                        ArrayList arrayList3 = new ArrayList();
                        if (jSONArray != null) {
                            for (int i = 0; i < jSONArray.length(); i++) {
                                arrayList3.add(jSONArray.get(i).toString());
                            }
                        }
                        hashMap2.put(str2, arrayList3);
                    }
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                if (JsonUtil.hasValue(apiResponse.getData(), "all_countries")) {
                    JSONObject jSONObject2 = apiResponse.getData().getJSONObject("all_countries");
                    Iterator keys3 = jSONObject2.keys();
                    while (keys3.hasNext()) {
                        String str3 = (String) keys3.next();
                        linkedHashMap.put(str3, jSONObject2.getString(str3));
                    }
                }
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                if (JsonUtil.hasValue(apiResponse.getData(), "shipping_countries")) {
                    JSONObject jSONObject3 = apiResponse.getData().getJSONObject("shipping_countries");
                    Iterator keys4 = jSONObject3.keys();
                    while (keys4.hasNext()) {
                        String str4 = (String) keys4.next();
                        linkedHashMap2.put(str4, jSONObject3.getString(str4));
                    }
                }
                HashMap hashMap3 = new HashMap();
                if (JsonUtil.hasValue(apiResponse.getData(), "country_subdivision_names")) {
                    JSONObject jSONObject4 = apiResponse.getData().getJSONObject("country_subdivision_names");
                    Iterator keys5 = jSONObject4.keys();
                    while (keys5.hasNext()) {
                        String str5 = (String) keys5.next();
                        hashMap3.put(str5, jSONObject4.getString(str5));
                    }
                }
                ArrayList arrayList4 = new ArrayList();
                if (JsonUtil.hasValue(apiResponse.getData(), "retry_delays")) {
                    arrayList4 = JsonUtil.parseArray(apiResponse.getData(), "retry_delays", new DataParser<Long, Long>() {
                        public Long parseData(Long l) throws JSONException {
                            return l;
                        }
                    });
                }
                ArrayList arrayList5 = arrayList4;
                ArrayList arrayList6 = new ArrayList();
                if (JsonUtil.hasValue(apiResponse.getData(), "paths")) {
                    arrayList6 = JsonUtil.parseArray(apiResponse.getData(), "paths", new DataParser<String, String>() {
                        public String parseData(String str) throws JSONException {
                            return str;
                        }
                    });
                }
                HashSet hashSet = new HashSet(arrayList6);
                WishInviteCouponSpec wishInviteCouponSpec = JsonUtil.hasValue(apiResponse.getData(), "invite_coupon_spec") ? new WishInviteCouponSpec(apiResponse.getData().getJSONObject("invite_coupon_spec")) : null;
                if (JsonUtil.hasValue(apiResponse.getData(), "daily_giveaways_noti_info")) {
                    WishDailyGiveawayNotificationInfo wishDailyGiveawayNotificationInfo2 = new WishDailyGiveawayNotificationInfo(apiResponse.getData().getJSONObject("daily_giveaways_noti_info"));
                    if (!PreferenceUtil.getString("LastDailyGiveawayKey", "").equals(wishDailyGiveawayNotificationInfo2.getSeenKey())) {
                        PreferenceUtil.setString("LastDailyGiveawayKey", wishDailyGiveawayNotificationInfo2.getSeenKey());
                        ApplicationEventManager.getInstance().triggerEvent(EventType.DAILY_GIVEAWAY_SPLASH_NOTIFICATION, null, null);
                    }
                    wishDailyGiveawayNotificationInfo = wishDailyGiveawayNotificationInfo2;
                } else {
                    wishDailyGiveawayNotificationInfo = null;
                }
                String optString7 = JsonUtil.hasValue(apiResponse.getData(), "video_upload_url") ? JsonUtil.optString(apiResponse.getData(), "video_upload_url") : null;
                String optString8 = apiResponse.getData().optString("referral_menu_title", null);
                String optString9 = apiResponse.getData().optString("user_phone_number", null);
                if (JsonUtil.hasValue(apiResponse.getData(), "geocoding_request")) {
                    geocodingRequest = new GeocodingRequest(apiResponse.getData().getJSONObject("geocoding_request"));
                }
                ConfigDataCenter.getInstance().initializeData(wishLocalNotification, parseArray, parseArray2, arrayList2, optString, optString2, optString3, hashMap2, linkedHashMap, linkedHashMap2, hashMap3, optString6, optString5, optString4, wishPaymentProcessorData, arrayList5, hashSet, wishInviteCouponSpec, wishDailyGiveawayNotificationInfo, optString7, optString8, optString9, geocodingRequest);
                ConfigDataCenter.getInstance().setShippingDataRefreshed(true);
                if (defaultSuccessCallback != null) {
                    GetUserStatusService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultSuccessCallback.onSuccess();
                        }
                    });
                }
            }
        });
    }
}
