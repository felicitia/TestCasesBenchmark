package com.contextlogic.wish.api.datacenter;

import android.app.Activity;
import android.os.Bundle;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.social.google.GoogleManager;
import com.contextlogic.wish.util.PreferenceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

public class ExperimentDataCenter extends DataCenter {
    private static ExperimentDataCenter sInstance = new ExperimentDataCenter();
    private boolean mDataInitialized;
    private HashMap<String, String> mExperimentMapping = new HashMap<>();
    private Object mLock = new Object();
    private String mSocialButtonBucket;

    public static class ExperimentDefinition {
        private ArrayList<String> mBuckets = new ArrayList<>();
        private String mName;

        public ExperimentDefinition(String str) {
            this.mName = str;
        }

        public void addBucket(String str) {
            this.mBuckets.add(str);
        }

        public String getName() {
            return this.mName;
        }

        public ArrayList<String> getBuckets(boolean z) {
            ArrayList<String> arrayList = new ArrayList<>(this.mBuckets);
            if (z) {
                arrayList.add(0, String.format("Default (%1$s)", new Object[]{ExperimentDataCenter.getInstance().getBucketForExperiment(this.mName)}));
            }
            return arrayList;
        }
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
    }

    /* access modifiers changed from: protected */
    public String getSerializationFileName() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getSerializationPreferenceName() {
        return "ExperimentDataCenter";
    }

    public void refresh() {
    }

    public boolean showNewSignupFlow() {
        return false;
    }

    private ExperimentDataCenter() {
        clearData();
    }

    public static ExperimentDataCenter getInstance() {
        return sInstance;
    }

    public boolean isDataInitialized() {
        return this.mDataInitialized;
    }

    public void initializeData(Map<String, String> map) {
        synchronized (this.mLock) {
            this.mExperimentMapping.clear();
            this.mExperimentMapping.putAll(map);
            this.mDataInitialized = true;
        }
        ApplicationEventManager.getInstance().triggerEvent(EventType.DATA_CENTER_UPDATED, getClass().toString(), null);
        markForSerialization();
    }

    private boolean isShowBucket(String str) {
        return getBucketForExperiment(str).equals("show");
    }

    private boolean isShowV2Bucket(String str) {
        return getBucketForExperiment(str).equals("show-v2");
    }

    private boolean isShowV3Bucket(String str) {
        return getBucketForExperiment(str).equals("show-v3");
    }

    private boolean isShowV4Bucket(String str) {
        return getBucketForExperiment(str).equals("show-v4");
    }

    private boolean isHideBucket(String str) {
        return getBucketForExperiment(str).equals("hide");
    }

    public String getBucketForExperiment(String str) {
        String str2;
        synchronized (this.mLock) {
            str2 = (String) this.mExperimentMapping.get(str);
        }
        return str2 != null ? str2 : "control";
    }

    /* access modifiers changed from: protected */
    public boolean processSerializedData(JSONObject jSONObject, Bundle bundle) {
        try {
            Iterator keys = jSONObject.keys();
            HashMap hashMap = new HashMap();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                hashMap.put(str, jSONObject.getString(str));
            }
            initializeData(hashMap);
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
            this.mExperimentMapping.clear();
            this.mDataInitialized = false;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x002c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject getSerializationData() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            r1 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Throwable -> 0x002b }
            r2.<init>()     // Catch:{ Throwable -> 0x002b }
            java.util.HashMap<java.lang.String, java.lang.String> r1 = r5.mExperimentMapping     // Catch:{ Throwable -> 0x002c }
            java.util.Set r1 = r1.keySet()     // Catch:{ Throwable -> 0x002c }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Throwable -> 0x002c }
        L_0x0013:
            boolean r3 = r1.hasNext()     // Catch:{ Throwable -> 0x002c }
            if (r3 == 0) goto L_0x002c
            java.lang.Object r3 = r1.next()     // Catch:{ Throwable -> 0x002c }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Throwable -> 0x002c }
            java.util.HashMap<java.lang.String, java.lang.String> r4 = r5.mExperimentMapping     // Catch:{ Throwable -> 0x002c }
            java.lang.Object r4 = r4.get(r3)     // Catch:{ Throwable -> 0x002c }
            r2.put(r3, r4)     // Catch:{ Throwable -> 0x002c }
            goto L_0x0013
        L_0x0029:
            r1 = move-exception
            goto L_0x002e
        L_0x002b:
            r2 = r1
        L_0x002c:
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            return r2
        L_0x002e:
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.datacenter.ExperimentDataCenter.getSerializationData():org.json.JSONObject");
    }

    /* access modifiers changed from: protected */
    public RefreshMode getRefreshMode() {
        return RefreshMode.MANUAL;
    }

    public ArrayList<ExperimentDefinition> getExperimentDefinitions() {
        ArrayList<ExperimentDefinition> arrayList = new ArrayList<>();
        ExperimentDefinition experimentDefinition = new ExperimentDefinition("mobile_red_dot");
        experimentDefinition.addBucket("control");
        experimentDefinition.addBucket("show");
        experimentDefinition.addBucket("show-v2");
        experimentDefinition.addBucket("show-v3");
        experimentDefinition.addBucket("show-v4");
        arrayList.add(experimentDefinition);
        ExperimentDefinition experimentDefinition2 = new ExperimentDefinition("mobile_price_chop_message");
        experimentDefinition2.addBucket("control");
        experimentDefinition2.addBucket("show");
        experimentDefinition2.addBucket("show-v2");
        arrayList.add(experimentDefinition2);
        ExperimentDefinition experimentDefinition3 = new ExperimentDefinition("android_fb_messenger_bot_sharing");
        experimentDefinition3.addBucket("control");
        experimentDefinition3.addBucket("show");
        experimentDefinition3.addBucket("show-v2");
        arrayList.add(experimentDefinition3);
        ExperimentDefinition experimentDefinition4 = new ExperimentDefinition("mobile_disable_filter_duplicates");
        experimentDefinition4.addBucket("control");
        experimentDefinition4.addBucket("show");
        arrayList.add(experimentDefinition4);
        ExperimentDefinition experimentDefinition5 = new ExperimentDefinition("mobile_multiple_payments");
        experimentDefinition5.addBucket("control");
        experimentDefinition5.addBucket("show");
        arrayList.add(experimentDefinition5);
        ExperimentDefinition experimentDefinition6 = new ExperimentDefinition("mobile_rewards_wallet");
        experimentDefinition6.addBucket("control");
        experimentDefinition6.addBucket("show");
        arrayList.add(experimentDefinition6);
        ExperimentDefinition experimentDefinition7 = new ExperimentDefinition("mobile_turn_off_rewards");
        experimentDefinition7.addBucket("control");
        experimentDefinition7.addBucket("show");
        arrayList.add(experimentDefinition7);
        ExperimentDefinition experimentDefinition8 = new ExperimentDefinition("mobile_notifications_settings_banner");
        experimentDefinition8.addBucket("control");
        experimentDefinition8.addBucket("show");
        arrayList.add(experimentDefinition8);
        ExperimentDefinition experimentDefinition9 = new ExperimentDefinition("commerce_loan");
        experimentDefinition9.addBucket("control");
        experimentDefinition9.addBucket("show");
        arrayList.add(experimentDefinition9);
        ExperimentDefinition experimentDefinition10 = new ExperimentDefinition("mobile_invite_friend_coupon");
        experimentDefinition10.addBucket("control");
        experimentDefinition10.addBucket("show");
        experimentDefinition10.addBucket("show-v2");
        experimentDefinition10.addBucket("show-v3");
        experimentDefinition10.addBucket("show-v4");
        arrayList.add(experimentDefinition10);
        ExperimentDefinition experimentDefinition11 = new ExperimentDefinition("mobile_signup_redesign");
        experimentDefinition11.addBucket("control");
        experimentDefinition11.addBucket("show");
        experimentDefinition11.addBucket("show-v2");
        arrayList.add(experimentDefinition11);
        ExperimentDefinition experimentDefinition12 = new ExperimentDefinition("mobile_split_name_field");
        experimentDefinition12.addBucket("control");
        experimentDefinition12.addBucket("show");
        arrayList.add(experimentDefinition12);
        ExperimentDefinition experimentDefinition13 = new ExperimentDefinition("commerce_cash");
        experimentDefinition13.addBucket("control");
        experimentDefinition13.addBucket("show");
        arrayList.add(experimentDefinition13);
        ExperimentDefinition experimentDefinition14 = new ExperimentDefinition("commerce_cash_br");
        experimentDefinition14.addBucket("control");
        experimentDefinition14.addBucket("show");
        arrayList.add(experimentDefinition14);
        ExperimentDefinition experimentDefinition15 = new ExperimentDefinition("mobile_wish_express_search");
        experimentDefinition15.addBucket("control");
        experimentDefinition15.addBucket("show");
        arrayList.add(experimentDefinition15);
        ExperimentDefinition experimentDefinition16 = new ExperimentDefinition("mobile_group_buy_4");
        experimentDefinition16.addBucket("control");
        experimentDefinition16.addBucket("show");
        arrayList.add(experimentDefinition16);
        ExperimentDefinition experimentDefinition17 = new ExperimentDefinition("mobile_localized_currency");
        experimentDefinition17.addBucket("control");
        experimentDefinition17.addBucket("show");
        arrayList.add(experimentDefinition17);
        ExperimentDefinition experimentDefinition18 = new ExperimentDefinition("mobile_psuedo_localized_currency");
        experimentDefinition18.addBucket("control");
        experimentDefinition18.addBucket("show");
        arrayList.add(experimentDefinition18);
        ExperimentDefinition experimentDefinition19 = new ExperimentDefinition("mobile_boleto_checkout");
        experimentDefinition19.addBucket("control");
        experimentDefinition19.addBucket("show");
        arrayList.add(experimentDefinition19);
        ExperimentDefinition experimentDefinition20 = new ExperimentDefinition("mobile_oxxo_checkout");
        experimentDefinition20.addBucket("control");
        experimentDefinition20.addBucket("show");
        arrayList.add(experimentDefinition20);
        ExperimentDefinition experimentDefinition21 = new ExperimentDefinition("mobile_ideal_checkout");
        experimentDefinition21.addBucket("control");
        experimentDefinition21.addBucket("show-v2");
        arrayList.add(experimentDefinition21);
        ExperimentDefinition experimentDefinition22 = new ExperimentDefinition("mobile_klarna_checkout");
        experimentDefinition22.addBucket("control");
        experimentDefinition22.addBucket("show");
        experimentDefinition22.addBucket("show-v2");
        experimentDefinition22.addBucket("show-v3");
        arrayList.add(experimentDefinition22);
        ExperimentDefinition experimentDefinition23 = new ExperimentDefinition("mobile_google_checkout");
        experimentDefinition23.addBucket("control");
        experimentDefinition23.addBucket("show");
        arrayList.add(experimentDefinition23);
        ExperimentDefinition experimentDefinition24 = new ExperimentDefinition("mobile_default_google_wallet");
        experimentDefinition24.addBucket("control");
        experimentDefinition24.addBucket("show");
        arrayList.add(experimentDefinition24);
        ExperimentDefinition experimentDefinition25 = new ExperimentDefinition("india_paytm");
        experimentDefinition25.addBucket("control");
        experimentDefinition25.addBucket("show");
        experimentDefinition25.addBucket("show-v2");
        experimentDefinition25.addBucket("show-v3");
        arrayList.add(experimentDefinition25);
        ExperimentDefinition experimentDefinition26 = new ExperimentDefinition("mobile_hide_cc_checkout");
        experimentDefinition26.addBucket("control");
        experimentDefinition26.addBucket("hide");
        arrayList.add(experimentDefinition26);
        ExperimentDefinition experimentDefinition27 = new ExperimentDefinition("mobile_hide_paypal");
        experimentDefinition27.addBucket("control");
        experimentDefinition27.addBucket("hide");
        arrayList.add(experimentDefinition27);
        ExperimentDefinition experimentDefinition28 = new ExperimentDefinition("mobile_group_buy_redesign");
        experimentDefinition28.addBucket("control");
        experimentDefinition28.addBucket("show");
        arrayList.add(experimentDefinition28);
        ExperimentDefinition experimentDefinition29 = new ExperimentDefinition("mobile_reorder_item_colors_sizes");
        experimentDefinition29.addBucket("control");
        experimentDefinition29.addBucket("show");
        experimentDefinition29.addBucket("show-v2");
        arrayList.add(experimentDefinition29);
        ExperimentDefinition experimentDefinition30 = new ExperimentDefinition("mobile_feed_tile_logger");
        experimentDefinition30.addBucket("control");
        experimentDefinition30.addBucket("show");
        arrayList.add(experimentDefinition30);
        ExperimentDefinition experimentDefinition31 = new ExperimentDefinition("mobile_wish_express_tab");
        experimentDefinition31.addBucket("control");
        experimentDefinition31.addBucket("show");
        experimentDefinition31.addBucket("show-v2");
        experimentDefinition31.addBucket("show-v3");
        arrayList.add(experimentDefinition31);
        ExperimentDefinition experimentDefinition32 = new ExperimentDefinition("mobile_feed_outlet_banner");
        experimentDefinition32.addBucket("control");
        experimentDefinition32.addBucket("show");
        experimentDefinition32.addBucket("show-v2");
        experimentDefinition32.addBucket("show-v3");
        experimentDefinition32.addBucket("show-v4");
        experimentDefinition32.addBucket("show-v5");
        arrayList.add(experimentDefinition32);
        ExperimentDefinition experimentDefinition33 = new ExperimentDefinition("mobile_day_prizes");
        experimentDefinition33.addBucket("control");
        experimentDefinition33.addBucket("show");
        experimentDefinition33.addBucket("show-v2");
        experimentDefinition33.addBucket("show-v3");
        experimentDefinition33.addBucket("show-v4");
        arrayList.add(experimentDefinition33);
        ExperimentDefinition experimentDefinition34 = new ExperimentDefinition("mobile_single_signup_onboarding");
        experimentDefinition34.addBucket("control");
        experimentDefinition34.addBucket("show");
        experimentDefinition34.addBucket("show-v2");
        experimentDefinition34.addBucket("show-v3");
        arrayList.add(experimentDefinition34);
        ExperimentDefinition experimentDefinition35 = new ExperimentDefinition("mobile_tab_space_optimization");
        experimentDefinition35.addBucket("control");
        experimentDefinition35.addBucket("show");
        experimentDefinition35.addBucket("show-v2");
        experimentDefinition35.addBucket("show-v3");
        experimentDefinition35.addBucket("show-v4");
        experimentDefinition35.addBucket("show-v5");
        arrayList.add(experimentDefinition35);
        ExperimentDefinition experimentDefinition36 = new ExperimentDefinition("mobile_buyer_guarantee");
        experimentDefinition36.addBucket("control");
        experimentDefinition36.addBucket("show");
        experimentDefinition36.addBucket("show-v2");
        arrayList.add(experimentDefinition36);
        ExperimentDefinition experimentDefinition37 = new ExperimentDefinition("mobile_product_review_filters_header");
        experimentDefinition37.addBucket("control");
        experimentDefinition37.addBucket("show");
        experimentDefinition37.addBucket("show-v2");
        arrayList.add(experimentDefinition37);
        ExperimentDefinition experimentDefinition38 = new ExperimentDefinition("mobile_paynearme");
        experimentDefinition38.addBucket("control");
        experimentDefinition38.addBucket("show");
        arrayList.add(experimentDefinition38);
        ExperimentDefinition experimentDefinition39 = new ExperimentDefinition("mobile_show_top_reviews");
        experimentDefinition39.addBucket("control");
        experimentDefinition39.addBucket("show");
        arrayList.add(experimentDefinition39);
        ExperimentDefinition experimentDefinition40 = new ExperimentDefinition("mobile_daily_raffle_v2");
        experimentDefinition40.addBucket("control");
        experimentDefinition40.addBucket("show");
        experimentDefinition40.addBucket("show-v2");
        arrayList.add(experimentDefinition40);
        ExperimentDefinition experimentDefinition41 = new ExperimentDefinition("mobile_show_daily_raffle");
        experimentDefinition41.addBucket("control");
        experimentDefinition41.addBucket("show");
        arrayList.add(experimentDefinition41);
        ExperimentDefinition experimentDefinition42 = new ExperimentDefinition("mobile_future_paypal_checkout_new");
        experimentDefinition42.addBucket("control");
        experimentDefinition42.addBucket("show");
        arrayList.add(experimentDefinition42);
        ExperimentDefinition experimentDefinition43 = new ExperimentDefinition("mobile_future_paypal_checkout_old");
        experimentDefinition43.addBucket("control");
        experimentDefinition43.addBucket("show");
        arrayList.add(experimentDefinition43);
        ExperimentDefinition experimentDefinition44 = new ExperimentDefinition("mobile_camera_feature");
        experimentDefinition44.addBucket("control");
        experimentDefinition44.addBucket("show");
        arrayList.add(experimentDefinition44);
        ExperimentDefinition experimentDefinition45 = new ExperimentDefinition("mobile_save_for_later_v3");
        experimentDefinition45.addBucket("control");
        experimentDefinition45.addBucket("show");
        arrayList.add(experimentDefinition45);
        ExperimentDefinition experimentDefinition46 = new ExperimentDefinition("klarna_paypal_checkout");
        experimentDefinition46.addBucket("control");
        experimentDefinition46.addBucket("show");
        arrayList.add(experimentDefinition46);
        ExperimentDefinition experimentDefinition47 = new ExperimentDefinition("mobile_free_gift_show_shipping_cost");
        experimentDefinition47.addBucket("control");
        experimentDefinition47.addBucket("show");
        experimentDefinition47.addBucket("show-v2");
        arrayList.add(experimentDefinition47);
        ExperimentDefinition experimentDefinition48 = new ExperimentDefinition("mobile_new_user_gift_pack_2");
        experimentDefinition48.addBucket("control");
        experimentDefinition48.addBucket("show");
        experimentDefinition48.addBucket("show-v2");
        experimentDefinition48.addBucket("show-v3");
        arrayList.add(experimentDefinition48);
        ExperimentDefinition experimentDefinition49 = new ExperimentDefinition("mobile_merchant_feed_tabs");
        experimentDefinition49.addBucket("control");
        experimentDefinition49.addBucket("show");
        experimentDefinition49.addBucket("show-v2");
        arrayList.add(experimentDefinition49);
        ExperimentDefinition experimentDefinition50 = new ExperimentDefinition("mobile_free_gift_ui_redesign");
        experimentDefinition50.addBucket("control");
        experimentDefinition50.addBucket("show");
        experimentDefinition50.addBucket("show-v2");
        experimentDefinition50.addBucket("show-v3");
        experimentDefinition50.addBucket("show-v4");
        arrayList.add(experimentDefinition50);
        ExperimentDefinition experimentDefinition51 = new ExperimentDefinition("mobile_shake_to_feedback");
        experimentDefinition51.addBucket("control");
        experimentDefinition51.addBucket("show");
        arrayList.add(experimentDefinition51);
        ExperimentDefinition experimentDefinition52 = new ExperimentDefinition("mobile_product_detail_transition");
        experimentDefinition52.addBucket("control");
        experimentDefinition52.addBucket("show");
        experimentDefinition52.addBucket("show-v2");
        arrayList.add(experimentDefinition52);
        ExperimentDefinition experimentDefinition53 = new ExperimentDefinition("mobile_referral_program");
        experimentDefinition53.addBucket("control");
        experimentDefinition53.addBucket("show");
        experimentDefinition53.addBucket("show-v2");
        arrayList.add(experimentDefinition53);
        ExperimentDefinition experimentDefinition54 = new ExperimentDefinition("mobile_sms_settings_control");
        experimentDefinition54.addBucket("control");
        experimentDefinition54.addBucket("show");
        arrayList.add(experimentDefinition54);
        ExperimentDefinition experimentDefinition55 = new ExperimentDefinition("mobile_related_pb_row_v4");
        experimentDefinition55.addBucket("control");
        experimentDefinition55.addBucket("show");
        experimentDefinition55.addBucket("show-v2");
        experimentDefinition55.addBucket("show-v3");
        experimentDefinition55.addBucket("show-v4");
        arrayList.add(experimentDefinition55);
        ExperimentDefinition experimentDefinition56 = new ExperimentDefinition("mobile_cart_quantity_dropdown");
        experimentDefinition56.addBucket("control");
        experimentDefinition56.addBucket("show");
        experimentDefinition56.addBucket("show-v2");
        arrayList.add(experimentDefinition56);
        ExperimentDefinition experimentDefinition57 = new ExperimentDefinition("mobile_bottom_navigation_v2");
        experimentDefinition57.addBucket("control");
        experimentDefinition57.addBucket("show");
        arrayList.add(experimentDefinition57);
        ExperimentDefinition experimentDefinition58 = new ExperimentDefinition("crossed_out_price_left_v2");
        experimentDefinition58.addBucket("control");
        experimentDefinition58.addBucket("show");
        experimentDefinition58.addBucket("show-v2");
        experimentDefinition58.addBucket("show-v3");
        arrayList.add(experimentDefinition58);
        ExperimentDefinition experimentDefinition59 = new ExperimentDefinition("mobile_hide_social_signup");
        experimentDefinition59.addBucket("control");
        experimentDefinition59.addBucket("show");
        experimentDefinition59.addBucket("show-v2");
        experimentDefinition59.addBucket("show-v3");
        experimentDefinition59.addBucket("show-v4");
        experimentDefinition59.addBucket("show-v5");
        arrayList.add(experimentDefinition59);
        ExperimentDefinition experimentDefinition60 = new ExperimentDefinition("mobile_commerce_loan_pay_half");
        experimentDefinition60.addBucket("control");
        experimentDefinition60.addBucket("show");
        arrayList.add(experimentDefinition60);
        ExperimentDefinition experimentDefinition61 = new ExperimentDefinition("mobile_tou_reaffirmation_purchase");
        experimentDefinition61.addBucket("control");
        experimentDefinition61.addBucket("show");
        arrayList.add(experimentDefinition61);
        ExperimentDefinition experimentDefinition62 = new ExperimentDefinition("mobile_loading_spinner_update");
        experimentDefinition62.addBucket("control");
        experimentDefinition62.addBucket("show");
        arrayList.add(experimentDefinition62);
        ExperimentDefinition experimentDefinition63 = new ExperimentDefinition("mobile_shipping_price_range");
        experimentDefinition63.addBucket("control");
        experimentDefinition63.addBucket("show");
        arrayList.add(experimentDefinition63);
        ExperimentDefinition experimentDefinition64 = new ExperimentDefinition("mobile_size_color_selector_v2");
        experimentDefinition64.addBucket("control");
        experimentDefinition64.addBucket("show");
        experimentDefinition64.addBucket("show-v2");
        arrayList.add(experimentDefinition64);
        ExperimentDefinition experimentDefinition65 = new ExperimentDefinition("mobile_tou_update_login_action");
        experimentDefinition65.addBucket("control");
        experimentDefinition65.addBucket("show");
        arrayList.add(experimentDefinition65);
        ExperimentDefinition experimentDefinition66 = new ExperimentDefinition("android_tou_account_creation");
        experimentDefinition66.addBucket("control");
        experimentDefinition66.addBucket("show");
        arrayList.add(experimentDefinition66);
        ExperimentDefinition experimentDefinition67 = new ExperimentDefinition("mobile_price_chopper");
        experimentDefinition67.addBucket("control");
        experimentDefinition67.addBucket("show");
        arrayList.add(experimentDefinition67);
        ExperimentDefinition experimentDefinition68 = new ExperimentDefinition("mobile_following_zero_state");
        experimentDefinition68.addBucket("control");
        experimentDefinition68.addBucket("show");
        arrayList.add(experimentDefinition68);
        ExperimentDefinition experimentDefinition69 = new ExperimentDefinition("mobile_get_email_social_signup");
        experimentDefinition69.addBucket("control");
        experimentDefinition69.addBucket("show");
        experimentDefinition69.addBucket("show-v2");
        experimentDefinition69.addBucket("show-v3");
        arrayList.add(experimentDefinition69);
        ExperimentDefinition experimentDefinition70 = new ExperimentDefinition("mobile_return_policy_ui_change");
        experimentDefinition70.addBucket("control");
        experimentDefinition70.addBucket("show");
        experimentDefinition70.addBucket("show-v2");
        arrayList.add(experimentDefinition70);
        ExperimentDefinition experimentDefinition71 = new ExperimentDefinition("mobile_redesign_signup_page");
        experimentDefinition71.addBucket("control");
        experimentDefinition71.addBucket("show");
        experimentDefinition71.addBucket("show-v2");
        arrayList.add(experimentDefinition71);
        ExperimentDefinition experimentDefinition72 = new ExperimentDefinition("mobile_show_login_redirect");
        experimentDefinition72.addBucket("control");
        experimentDefinition72.addBucket("show");
        arrayList.add(experimentDefinition72);
        ExperimentDefinition experimentDefinition73 = new ExperimentDefinition("mobile_remove_cart_icon");
        experimentDefinition73.addBucket("control");
        experimentDefinition73.addBucket("show");
        experimentDefinition73.addBucket("show-v2");
        experimentDefinition73.addBucket("show-v3");
        arrayList.add(experimentDefinition73);
        ExperimentDefinition experimentDefinition74 = new ExperimentDefinition("mobile_expired_offer_timer_ui_change");
        experimentDefinition74.addBucket("control");
        experimentDefinition74.addBucket("show");
        experimentDefinition74.addBucket("show-v2");
        arrayList.add(experimentDefinition74);
        ExperimentDefinition experimentDefinition75 = new ExperimentDefinition("mobile_log_firebase_events");
        experimentDefinition75.addBucket("control");
        experimentDefinition75.addBucket("show");
        arrayList.add(experimentDefinition75);
        ExperimentDefinition experimentDefinition76 = new ExperimentDefinition("mobile_express_shipping_larger_tiles");
        experimentDefinition76.addBucket("control");
        experimentDefinition76.addBucket("show");
        arrayList.add(experimentDefinition76);
        ExperimentDefinition experimentDefinition77 = new ExperimentDefinition("mobile_daily_login_reward_link");
        experimentDefinition77.addBucket("control");
        experimentDefinition77.addBucket("show");
        arrayList.add(experimentDefinition77);
        ExperimentDefinition experimentDefinition78 = new ExperimentDefinition("mobile_commerce_cash_history");
        experimentDefinition78.addBucket("control");
        experimentDefinition78.addBucket("show");
        arrayList.add(experimentDefinition78);
        ExperimentDefinition experimentDefinition79 = new ExperimentDefinition("mobile_update_cart_offer_modal");
        experimentDefinition79.addBucket("control");
        experimentDefinition79.addBucket("show");
        arrayList.add(experimentDefinition79);
        ExperimentDefinition experimentDefinition80 = new ExperimentDefinition("mobile_no_internet_error_view");
        experimentDefinition80.addBucket("control");
        experimentDefinition80.addBucket("show");
        arrayList.add(experimentDefinition80);
        ExperimentDefinition experimentDefinition81 = new ExperimentDefinition("apply_promo_side_menu");
        experimentDefinition81.addBucket("control");
        experimentDefinition81.addBucket("show");
        arrayList.add(experimentDefinition81);
        ExperimentDefinition experimentDefinition82 = new ExperimentDefinition("mobile_signup_gift_timer");
        experimentDefinition82.addBucket("control");
        experimentDefinition82.addBucket("show");
        experimentDefinition82.addBucket("show-v2");
        experimentDefinition82.addBucket("show-v3");
        arrayList.add(experimentDefinition82);
        ExperimentDefinition experimentDefinition83 = new ExperimentDefinition("mobile_show_detail_wishlist_tile_view");
        experimentDefinition83.addBucket("control");
        experimentDefinition83.addBucket("show");
        experimentDefinition83.addBucket("show-v2");
        arrayList.add(experimentDefinition83);
        ExperimentDefinition experimentDefinition84 = new ExperimentDefinition("mobile_price_watch");
        experimentDefinition84.addBucket("control");
        experimentDefinition84.addBucket("show");
        experimentDefinition84.addBucket("show-v2");
        arrayList.add(experimentDefinition84);
        ExperimentDefinition experimentDefinition85 = new ExperimentDefinition("partner_account");
        experimentDefinition85.addBucket("control");
        experimentDefinition85.addBucket("show");
        arrayList.add(experimentDefinition85);
        ExperimentDefinition experimentDefinition86 = new ExperimentDefinition("mobile_recent_wishlist_items_feed");
        experimentDefinition86.addBucket("control");
        experimentDefinition86.addBucket("show");
        experimentDefinition86.addBucket("show-v2");
        experimentDefinition86.addBucket("show-v3");
        experimentDefinition86.addBucket("show-v4");
        arrayList.add(experimentDefinition86);
        ExperimentDefinition experimentDefinition87 = new ExperimentDefinition("mobile_deal_dash_coupon_code");
        experimentDefinition87.addBucket("control");
        experimentDefinition87.addBucket("show");
        experimentDefinition87.addBucket("show-v2");
        experimentDefinition87.addBucket("show-v3");
        experimentDefinition87.addBucket("show-v4");
        arrayList.add(experimentDefinition87);
        ExperimentDefinition experimentDefinition88 = new ExperimentDefinition("mobile_wishlist_share");
        experimentDefinition88.addBucket("control");
        experimentDefinition88.addBucket("show");
        arrayList.add(experimentDefinition88);
        ExperimentDefinition experimentDefinition89 = new ExperimentDefinition("mobile_new_user_gift_pack_4");
        experimentDefinition89.addBucket("control");
        experimentDefinition89.addBucket("show");
        experimentDefinition89.addBucket("show-v2");
        experimentDefinition89.addBucket("show-v3");
        experimentDefinition89.addBucket("show-v4");
        arrayList.add(experimentDefinition89);
        ExperimentDefinition experimentDefinition90 = new ExperimentDefinition("mobile_cart_notices_redesign");
        experimentDefinition90.addBucket("control");
        experimentDefinition90.addBucket("show");
        arrayList.add(experimentDefinition90);
        ExperimentDefinition experimentDefinition91 = new ExperimentDefinition("mobile_condensed_badges_v2");
        experimentDefinition91.addBucket("control");
        experimentDefinition91.addBucket("show");
        experimentDefinition91.addBucket("show-v2");
        arrayList.add(experimentDefinition91);
        ExperimentDefinition experimentDefinition92 = new ExperimentDefinition("mobile_webview_smart_actionbar");
        experimentDefinition92.addBucket("control");
        experimentDefinition92.addBucket("show");
        arrayList.add(experimentDefinition92);
        ExperimentDefinition experimentDefinition93 = new ExperimentDefinition("mobile_use_api_guard");
        experimentDefinition93.addBucket("control");
        experimentDefinition93.addBucket("show");
        arrayList.add(experimentDefinition93);
        ExperimentDefinition experimentDefinition94 = new ExperimentDefinition("mobile_load_feed_after_20_mins");
        experimentDefinition94.addBucket("control");
        experimentDefinition94.addBucket("show");
        experimentDefinition94.addBucket("show-v2");
        arrayList.add(experimentDefinition94);
        return arrayList;
    }

    public boolean shouldSeeNotificationSettingsBannerView() {
        return isShowBucket("mobile_notifications_settings_banner");
    }

    public boolean shouldUsePsuedoLocalizedCurrency() {
        return isShowBucket("mobile_psuedo_localized_currency");
    }

    public boolean canLocalizeCurrency() {
        return shouldUsePsuedoLocalizedCurrency() || isShowBucket("mobile_localized_currency");
    }

    public boolean isValueGreaterThanZero(CartContext cartContext) {
        boolean z = true;
        if (cartContext.getCart() != null) {
            if (cartContext.getCart().getTotal().getValue() <= 0.0d) {
                z = false;
            }
            return z;
        } else if (cartContext.getCommerceCashCart() != null) {
            if (cartContext.getCommerceCashCart().getAmount().getValue() <= 0.0d) {
                z = false;
            }
            return z;
        } else if (cartContext.getCommerceLoanCart() == null) {
            return false;
        } else {
            if (cartContext.getCommerceLoanCart().getAmount().getValue() <= 0.0d) {
                z = false;
            }
            return z;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0052, code lost:
        if (r7.getCommerceCashCart().getAmount().getValue() <= 1800.0d) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0023, code lost:
        if (r7.getCart().getTotal().getValue() <= 1800.0d) goto L_0x0057;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0060 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean canCheckoutWithGooglePay(com.contextlogic.wish.payments.CartContext r7) {
        /*
            r6 = this;
            r0 = 0
            r1 = 1
            if (r7 == 0) goto L_0x0057
            boolean r2 = r6.isValueGreaterThanZero(r7)
            com.contextlogic.wish.api.model.WishCart r3 = r7.getCart()
            r4 = 4655631299166339072(0x409c200000000000, double:1800.0)
            if (r3 == 0) goto L_0x0028
            if (r2 == 0) goto L_0x0026
            com.contextlogic.wish.api.model.WishCart r7 = r7.getCart()
            com.contextlogic.wish.api.model.WishLocalizedCurrencyValue r7 = r7.getTotal()
            double r2 = r7.getValue()
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 > 0) goto L_0x0026
            goto L_0x0057
        L_0x0026:
            r7 = 0
            goto L_0x0058
        L_0x0028:
            com.contextlogic.wish.api.model.WishCommerceCashCart r3 = r7.getCommerceCashCart()
            if (r3 == 0) goto L_0x0055
            if (r2 == 0) goto L_0x0026
            com.contextlogic.wish.api.model.WishCommerceCashCart r2 = r7.getCommerceCashCart()
            com.contextlogic.wish.api.model.WishLocalizedCurrencyValue r2 = r2.getAmount()
            java.lang.String r2 = r2.getLocalizedCurrencyCode()
            java.lang.String r3 = "USD"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0026
            com.contextlogic.wish.api.model.WishCommerceCashCart r7 = r7.getCommerceCashCart()
            com.contextlogic.wish.api.model.WishLocalizedCurrencyValue r7 = r7.getAmount()
            double r2 = r7.getValue()
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 > 0) goto L_0x0026
            goto L_0x0057
        L_0x0055:
            r7 = r2
            goto L_0x0058
        L_0x0057:
            r7 = 1
        L_0x0058:
            java.lang.String r2 = "mobile_google_checkout"
            boolean r2 = r6.isShowBucket(r2)
            if (r2 == 0) goto L_0x006d
            if (r7 == 0) goto L_0x006d
            com.contextlogic.wish.social.google.GoogleManager r7 = com.contextlogic.wish.social.google.GoogleManager.getInstance()
            boolean r7 = r7.isPlayServicesAvailable()
            if (r7 == 0) goto L_0x006d
            r0 = 1
        L_0x006d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.api.datacenter.ExperimentDataCenter.canCheckoutWithGooglePay(com.contextlogic.wish.payments.CartContext):boolean");
    }

    public boolean canCheckoutWithCreditCard(CartContext cartContext) {
        return !isHideBucket("mobile_hide_cc_checkout");
    }

    public boolean canCheckoutWithBoleto(CartContext cartContext) {
        boolean isValueGreaterThanZero = cartContext != null ? isValueGreaterThanZero(cartContext) : true;
        if (!isShowBucket("mobile_boleto_checkout") || !isValueGreaterThanZero) {
            return false;
        }
        return true;
    }

    public boolean canCheckoutWithOxxo(CartContext cartContext) {
        boolean isValueGreaterThanZero = cartContext != null ? isValueGreaterThanZero(cartContext) : true;
        if (!isShowBucket("mobile_oxxo_checkout") || !isValueGreaterThanZero) {
            return false;
        }
        return true;
    }

    public boolean canCheckoutWithIdeal(CartContext cartContext) {
        boolean isValueGreaterThanZero = cartContext != null ? isValueGreaterThanZero(cartContext) : true;
        if (!isShowV2Bucket("mobile_ideal_checkout") || !isValueGreaterThanZero) {
            return false;
        }
        return true;
    }

    public boolean canCheckoutWithPayPal(CartContext cartContext) {
        boolean isValueGreaterThanZero = cartContext != null ? isValueGreaterThanZero(cartContext) : true;
        if (isHideBucket("mobile_hide_paypal") || !isValueGreaterThanZero) {
            return false;
        }
        return true;
    }

    public boolean canCheckoutWithFuturePayPal(CartContext cartContext) {
        boolean isValueGreaterThanZero = cartContext != null ? isValueGreaterThanZero(cartContext) : true;
        if ((isShowBucket("mobile_future_paypal_checkout_new") || isShowBucket("mobile_future_paypal_checkout_old")) && isValueGreaterThanZero) {
            return true;
        }
        return false;
    }

    public boolean canCheckoutWithKlarnaOnly(CartContext cartContext) {
        boolean z = cartContext != null ? cartContext.getCommerceCashCart() != null ? false : isValueGreaterThanZero(cartContext) : true;
        if (!isShowBucket("mobile_klarna_checkout") || !z) {
            return false;
        }
        return true;
    }

    public boolean canCheckoutWithKlarnaNative(CartContext cartContext) {
        boolean z;
        if (cartContext != null) {
            z = isValueGreaterThanZero(cartContext);
            if (cartContext.getCommerceCashCart() != null) {
                z = false;
            }
        } else {
            z = true;
        }
        if (!isShowV3Bucket("mobile_klarna_checkout") || !z) {
            return false;
        }
        return true;
    }

    public boolean canCheckoutWithCommerceLoan(CartContext cartContext) {
        boolean z;
        if (cartContext == null || (cartContext.getCommerceCashCart() == null && isValueGreaterThanZero(cartContext) && cartContext.getCommerceLoanTabSpec() != null && cartContext.getCommerceLoanTabSpec().canPayLater())) {
            z = true;
        } else {
            z = false;
        }
        if (!canSeeCommerceLoanBillingOption() || !z) {
            return false;
        }
        return true;
    }

    public boolean canCheckoutWithPaytm(CartContext cartContext) {
        boolean isValueGreaterThanZero = cartContext != null ? isValueGreaterThanZero(cartContext) : true;
        if (!isShowBucket("india_paytm") || !isValueGreaterThanZero) {
            return false;
        }
        return true;
    }

    public boolean canUseKlarnaSDK() {
        return isShowBucket("mobile_use_klarna_SDK");
    }

    public boolean canUseGooglePlayAsDefault(CartContext cartContext) {
        return isShowBucket("mobile_default_android_pay");
    }

    public boolean canSeeInviteCouponDialog() {
        return isShowV2Bucket("mobile_invite_friend_coupon");
    }

    public boolean canSeeInviteCouponButton() {
        return isShowV3Bucket("mobile_invite_friend_coupon");
    }

    public boolean canSeeInviteCouponBanner() {
        return isShowV4Bucket("mobile_invite_friend_coupon");
    }

    public boolean shouldSeeGroupBuy() {
        return getBucketForExperiment("mobile_group_buy_4").startsWith("show");
    }

    public boolean shouldSeeWishExpressSearchTabs() {
        return isShowBucket("mobile_wish_express_search");
    }

    public boolean canSeeCommerceCash() {
        return canSeeCommerceCashUS() || canSeeCommerceCashBR();
    }

    public boolean canSeeCommerceCashUS() {
        return isShowBucket("commerce_cash");
    }

    public boolean canSeeCommerceCashBR() {
        return isShowBucket("commerce_cash_br");
    }

    public boolean shouldSeeGroupBuyRedesign() {
        return isShowBucket("mobile_group_buy_redesign");
    }

    public boolean canSeeCommerceLoanBillingOption() {
        return isShowBucket("commerce_loan");
    }

    public boolean shouldRemoveUnavailableItemSelections() {
        return isShowBucket("mobile_reorder_item_colors_sizes");
    }

    public boolean shouldPushDownUnavailableItemSelections() {
        return isShowV2Bucket("mobile_reorder_item_colors_sizes");
    }

    public boolean shouldRunFeedTileLogger() {
        return isShowBucket("mobile_feed_tile_logger");
    }

    public boolean shouldSeeNewLayoutOutletBanner() {
        return getBucketForExperiment("mobile_feed_outlet_banner").startsWith("show");
    }

    public boolean shouldShowDayPrizes() {
        return getBucketForExperiment("mobile_day_prizes").startsWith("show");
    }

    public boolean shouldShowDayPrizesFiftyPercent() {
        return isShowV3Bucket("mobile_day_prizes") || isShowV4Bucket("mobile_day_prizes");
    }

    public boolean shouldShowOutletIcon() {
        return isShowV2Bucket("mobile_tab_space_optimization") || isShowV4Bucket("mobile_tab_space_optimization");
    }

    public boolean shouldSeeCollapsableBuyerGuarantee() {
        return isShowBucket("mobile_buyer_guarantee");
    }

    public boolean shouldSeeFullSectionBuyerGuarantee() {
        return isShowV2Bucket("mobile_buyer_guarantee");
    }

    public boolean shouldSeeOnboardingGender() {
        return isShowBucket("mobile_single_signup_onboarding");
    }

    public boolean shouldSeeOnboardingInterests() {
        return isShowV2Bucket("mobile_single_signup_onboarding");
    }

    public boolean shouldSeeOnboardingBirthday() {
        return isShowV3Bucket("mobile_single_signup_onboarding");
    }

    public boolean canSeeNewSingleOnboardingFlow() {
        return shouldSeeOnboardingGender() || shouldSeeOnboardingInterests() || shouldSeeOnboardingBirthday();
    }

    public boolean shouldSeePayNearMe() {
        return isShowBucket("mobile_paynearme");
    }

    public boolean showTopReviews() {
        return isShowBucket("mobile_show_top_reviews");
    }

    public boolean shouldSeeProductRatingFilter() {
        return isShowBucket("mobile_product_review_filters_header") || isShowV2Bucket("mobile_product_review_filters_header");
    }

    public boolean shouldSeeNewProductRatingHeader() {
        return isShowV2Bucket("mobile_product_review_filters_header");
    }

    public boolean shouldShowDailyRaffle() {
        return (isShowBucket("mobile_daily_raffle_v2") || isShowV2Bucket("mobile_daily_raffle_v2")) && isShowBucket("mobile_show_daily_raffle");
    }

    public boolean shouldShowGenderedFreegift() {
        return isShowBucket("mobile_gendered_free_gift");
    }

    public boolean shouldShowV2ShippingCostUpFront() {
        return isShowV2Bucket("mobile_free_gift_show_shipping_cost");
    }

    public boolean shouldShowLoginRedesignProductFeed() {
        return isShowBucket("mobile_login_redesign_feed") || isShowV2Bucket("mobile_login_redesign_feed");
    }

    public boolean shouldShowLoginRedesignProductFeed2Col() {
        return isShowBucket("mobile_login_redesign_feed");
    }

    public boolean shouldShowLoginRedesignProductFeed3Col() {
        return isShowV2Bucket("mobile_login_redesign_feed");
    }

    public boolean shouldShowSaveForLater() {
        return isShowBucket("mobile_save_for_later_v3");
    }

    public boolean canCheckoutWithKlarnaPaypal() {
        return isShowBucket("klarna_paypal_checkout");
    }

    public boolean showMerchantFeedTabs() {
        return isShowBucket("mobile_merchant_feed_tabs") || isShowV2Bucket("mobile_merchant_feed_tabs");
    }

    public boolean shouldSeeCamera() {
        return isShowBucket("mobile_camera_feature");
    }

    public boolean turnOffRewards() {
        return isShowBucket("mobile_turn_off_rewards");
    }

    public boolean shouldSeeNewFreeGiftView() {
        return isShowBucket("mobile_free_gift_ui_redesign") || shouldSeeNewFloatingButton() || shouldSeeNewBigClaimButton() || shouldSeeFreeGiftBanner();
    }

    public boolean shouldSeeNewBigClaimButton() {
        return isShowV2Bucket("mobile_free_gift_ui_redesign");
    }

    public boolean shouldSeeNewFloatingButton() {
        return isShowV3Bucket("mobile_free_gift_ui_redesign");
    }

    public boolean shouldSeeFreeGiftBanner() {
        return isShowV4Bucket("mobile_free_gift_ui_redesign");
    }

    public boolean shouldShowProductDetailTransition() {
        return getBucketForExperiment("mobile_product_detail_transition").startsWith("show");
    }

    public boolean shouldShowFeedToProductDetailTransition() {
        return isShowV2Bucket("mobile_product_detail_transition");
    }

    public boolean shouldSeeReferralProgram() {
        return getBucketForExperiment("mobile_referral_program").startsWith("show");
    }

    public boolean shouldSeeSmsSettings() {
        return isShowBucket("mobile_sms_settings_control");
    }

    public boolean shouldReloadFeedOnBackPress() {
        return getBucketForExperiment("mobile_refresh_feed_on_back_press").startsWith("show");
    }

    public boolean shouldReloadAllFeedOnBackPress() {
        return isShowBucket("mobile_refresh_feed_on_back_press");
    }

    public boolean shouldReloadFeedOrGoBackToBrowse() {
        return isShowV2Bucket("mobile_refresh_feed_on_back_press");
    }

    public boolean shouldGoBackToBrowseAndReload() {
        return isShowV3Bucket("mobile_refresh_feed_on_back_press");
    }

    public boolean shouldSeeRelatedPbRow() {
        return getBucketForExperiment("mobile_related_pb_row_v4").startsWith("show");
    }

    public boolean shouldReplaceExpressRowWithPbRow() {
        return isShowV2Bucket("mobile_related_pb_row_v4") || isShowV4Bucket("mobile_related_pb_row_v4");
    }

    public boolean oneSecondDealDashSpinTime() {
        return isShowBucket("mobile_change_deal_dash_spin_time");
    }

    public boolean threeSecondDealDashSpinTime() {
        return isShowV2Bucket("mobile_change_deal_dash_spin_time");
    }

    public boolean tenSecondDealDashSpinTime() {
        return isShowV3Bucket("mobile_change_deal_dash_spin_time");
    }

    public boolean shouldShowBottomNavigation() {
        return isShowBucket("mobile_bottom_navigation_v2");
    }

    public boolean shouldShowCrossedOutPriceToLeftInFeed() {
        return isShowBucket("crossed_out_price_left_v2") || isShowV3Bucket("crossed_out_price_left_v2");
    }

    public boolean shouldShowCrossedOutPriceToLeftInProductDetail() {
        return isShowV2Bucket("crossed_out_price_left_v2") || isShowV3Bucket("crossed_out_price_left_v2");
    }

    public boolean showShakeToFeedback() {
        return isShowBucket("mobile_shake_to_feedback");
    }

    public boolean shouldHideSocialSignupButtons() {
        setupHideSocialExperimentBucket();
        return showMoreOptionsNearTop() || showMoreOptionsNearBottom() || this.mSocialButtonBucket.equals("show-v3");
    }

    public boolean showMoreOptionsNearTop() {
        setupHideSocialExperimentBucket();
        return this.mSocialButtonBucket.equals("show");
    }

    public boolean showMoreOptionsNearBottom() {
        setupHideSocialExperimentBucket();
        return this.mSocialButtonBucket.equals("show-v2");
    }

    public boolean shouldSeeTopFreeGiftText() {
        setupHideSocialExperimentBucket();
        return this.mSocialButtonBucket.equals("show-v4");
    }

    public boolean shouldSeeBottomFreeGiftText() {
        setupHideSocialExperimentBucket();
        return this.mSocialButtonBucket.equals("show-v5");
    }

    public boolean shouldSeeFreeGiftTextOnSignup() {
        return shouldSeeBottomFreeGiftText() || shouldSeeTopFreeGiftText() || shouldShowSignupFreeGiftHeader();
    }

    private void setupHideSocialExperimentBucket() {
        if (this.mSocialButtonBucket == null) {
            this.mSocialButtonBucket = getInstance().getBucketForExperiment("mobile_hide_social_signup");
        }
    }

    public boolean shouldShowDropdownQuantity() {
        return isShowBucket("mobile_cart_quantity_dropdown") || isShowV2Bucket("mobile_cart_quantity_dropdown");
    }

    public boolean shouldShowDropdownQuantityWithRemoveView() {
        return isShowV2Bucket("mobile_cart_quantity_dropdown");
    }

    public boolean shouldSeeHeartAfterWishListAdd() {
        return isShowBucket("mobile_add_to_wishlist_animation");
    }

    public boolean shouldSeeItemAfterWishListAdd() {
        return isShowV2Bucket("mobile_add_to_wishlist_animation");
    }

    public boolean shouldSeeVisuallySimilarItems() {
        return isShowBucket("mobile_show_visually_similar_products");
    }

    public boolean canSeePayHalfBillingOption() {
        return isShowBucket("mobile_commerce_loan_pay_half");
    }

    public boolean shouldShowTermsOfUseReaffirmation() {
        return isShowBucket("mobile_tou_reaffirmation_purchase");
    }

    public boolean shouldShowTermsOfUseUpdateLoginAction() {
        return isShowBucket("mobile_tou_update_login_action");
    }

    public boolean shouldShowTermsOfUseAccountCreation() {
        return isShowBucket("android_tou_account_creation");
    }

    public boolean shouldSeeNewProgressBar() {
        return isShowBucket("mobile_loading_spinner_update");
    }

    public boolean shouldShowShippingPriceRange() {
        return isShowBucket("mobile_shipping_price_range");
    }

    public boolean shouldShowSizeColorSelector() {
        return isShowBucket("mobile_size_color_selector_v2");
    }

    public boolean shouldShowSizeColorSelectorWithTextSwatchesOnly() {
        return isShowV2Bucket("mobile_size_color_selector_v2");
    }

    public boolean shouldSeeFollowingZeroState() {
        return isShowBucket("mobile_following_zero_state");
    }

    public boolean shouldSeeSecureGetEmail() {
        return isShowBucket("mobile_get_email_social_signup");
    }

    public boolean shouldSeeOrderConfirmationGetEmail() {
        return isShowV2Bucket("mobile_get_email_social_signup");
    }

    public boolean shouldSeeDiscountGetEmail() {
        return isShowV3Bucket("mobile_get_email_social_signup");
    }

    public boolean shouldSeeGetEmailOnBoardingScreen() {
        return shouldSeeSecureGetEmail() || shouldSeeOrderConfirmationGetEmail() || shouldSeeDiscountGetEmail();
    }

    public boolean shouldShowLastLoggedInForReinstall() {
        return isShowBucket("mobile_show_login_redirect");
    }

    public boolean shouldSeeConfidenceShieldReturnPolicyAboveCheckout() {
        return isShowBucket("mobile_return_policy_ui_change");
    }

    public boolean shouldSeeConfidenceShieldReturnPolicyBottomCart() {
        return isShowV2Bucket("mobile_return_policy_ui_change");
    }

    public boolean shouldSeeRegularReturnPolicyBottomCart() {
        return isShowV3Bucket("mobile_return_policy_ui_change");
    }

    public boolean shouldRemoveCartFromProfile() {
        return isShowBucket("mobile_remove_cart_icon") || isShowV3Bucket("mobile_remove_cart_icon");
    }

    public boolean shouldRemoveCartFromWishlist() {
        return isShowV2Bucket("mobile_remove_cart_icon") || isShowV3Bucket("mobile_remove_cart_icon") || shouldShowWishlistShare();
    }

    public boolean shouldHideOfferView() {
        return isShowV2Bucket("mobile_expired_offer_timer_ui_change");
    }

    public boolean shouldSeeDifferentExpiredText() {
        return isShowBucket("mobile_expired_offer_timer_ui_change");
    }

    public boolean shouldEnableFirebaseEventLogging() {
        return isShowBucket("mobile_log_firebase_events");
    }

    public boolean shouldSeeLargerExpressShippingTiles() {
        return isShowBucket("mobile_express_shipping_larger_tiles");
    }

    public boolean shouldShowCommerceCashHistory() {
        return isShowBucket("mobile_commerce_cash_history");
    }

    public boolean shouldShowPriceChopMessage() {
        return getBucketForExperiment("mobile_price_chop_message").startsWith("show");
    }

    public boolean shouldShowPriceChopMessageV2() {
        return isShowV2Bucket("mobile_price_chop_message");
    }

    public boolean shouldDisableFilterDuplicateProducts() {
        return isShowBucket("mobile_disable_filter_duplicates");
    }

    public boolean isRewardWalletEnabled() {
        return isShowBucket("mobile_rewards_wallet");
    }

    public boolean showDailyLoginBonusLinkToReward() {
        return isShowBucket("mobile_daily_login_reward_link");
    }

    public boolean shouldSeeMessengerShareButtonInProductDetails() {
        return (isShowBucket("android_fb_messenger_bot_sharing") || isShowV2Bucket("android_fb_messenger_bot_sharing")) && GoogleManager.getInstance().isPlayServicesAvailable();
    }

    public boolean shouldReplaceShareButtonInProductDetails() {
        return isShowV2Bucket("android_fb_messenger_bot_sharing") && GoogleManager.getInstance().isPlayServicesAvailable();
    }

    public boolean shouldSeeNewSignUpScreen() {
        return isShowBucket("mobile_redesign_signup_page") || isShowV2Bucket("mobile_redesign_signup_page");
    }

    public boolean shouldSeeVerticalSocialButtons() {
        return isShowBucket("mobile_redesign_signup_page");
    }

    public boolean shouldShowUpdatedCartOfferModal() {
        return isShowBucket("mobile_update_cart_offer_modal");
    }

    public boolean shouldSeeNewInternetErrorView() {
        return isShowBucket("mobile_no_internet_error_view");
    }

    public boolean shouldShowSignupFreeGiftHeader() {
        return getBucketForExperiment("mobile_signup_gift_timer").startsWith("show");
    }

    public boolean shouldShowSignupFreeGiftHeaderTimer() {
        return isShowV2Bucket("mobile_signup_gift_timer") || isShowV3Bucket("mobile_signup_gift_timer");
    }

    public boolean shouldSeePromoSideNav() {
        return isShowBucket("apply_promo_side_menu");
    }

    public boolean shouldSeePartnerAccounts() {
        return isShowBucket("partner_account");
    }

    public boolean shouldSeeProductDetailsInWishlist() {
        return isShowBucket("mobile_show_detail_wishlist_tile_view");
    }

    public boolean shouldSeeAddToCartInWishlistTile() {
        return isShowV2Bucket("mobile_show_detail_wishlist_tile_view");
    }

    public boolean shouldSeePriceWatch() {
        return getBucketForExperiment("mobile_price_watch").startsWith("show");
    }

    public boolean canAddFivePriceWatch() {
        return isShowBucket("mobile_price_watch");
    }

    public boolean shouldSeeRecentWishlistAsTab() {
        return isShowV2Bucket("mobile_recent_wishlist_items_feed") || isShowV4Bucket("mobile_recent_wishlist_items_feed");
    }

    public boolean shouldSeeRecentWishlistAsModal() {
        return isShowBucket("mobile_recent_wishlist_items_feed") || isShowV3Bucket("mobile_recent_wishlist_items_feed");
    }

    public boolean shouldSeeRecentWishlist() {
        return getBucketForExperiment("mobile_recent_wishlist_items_feed").startsWith("show");
    }

    public boolean shouldSeeSearchHistoryInAutocomplete() {
        return isShowV3Bucket("mobile_recent_wishlist_items_feed") || isShowV4Bucket("mobile_recent_wishlist_items_feed");
    }

    public boolean shouldShowWishlistShare() {
        return isShowBucket("mobile_wishlist_share");
    }

    public boolean shouldSeeOnfidoFlow() {
        return isShowBucket("mobile_onfido_ebanx");
    }

    public boolean shouldSeeDailyLoginAfterNewUserSplash() {
        return isShowV2Bucket("mobile_new_user_gift_pack_4");
    }

    public boolean shouldSeeNonAnimatedDailyLoginAfterNewUserSplash() {
        return isShowV4Bucket("mobile_new_user_gift_pack_4");
    }

    public boolean shouldSeeDealDashCoupon() {
        return getBucketForExperiment("mobile_deal_dash_coupon_code").startsWith("show");
    }

    public boolean shouldSeeRedesignedCartNotices() {
        return isShowBucket("mobile_cart_notices_redesign");
    }

    public boolean shouldSeeSmallCondensedBadges() {
        return isShowBucket("mobile_condensed_badges_v2");
    }

    public boolean shouldSeeLongCondensedBadges() {
        return isShowV2Bucket("mobile_condensed_badges_v2");
    }

    public boolean shouldEnableMultiplePayments() {
        return isShowBucket("mobile_multiple_payments");
    }

    public boolean shouldShowRedDot() {
        return !isShowBucket("mobile_red_dot") && !isShowV2Bucket("mobile_red_dot");
    }

    public boolean shouldShowRedDotOnCartActionBarIcon() {
        return !isShowBucket("mobile_red_dot");
    }

    public boolean shouldShowRedDotOnMenuIcon() {
        return shouldShowRedDot() && !isShowV4Bucket("mobile_red_dot");
    }

    public boolean shouldDismissRedDotOnMenuOpen() {
        return isShowV3Bucket("mobile_red_dot");
    }

    public boolean shouldShowWebviewSmartActionbar() {
        return isShowBucket("mobile_webview_smart_actionbar");
    }

    public boolean shouldUseApiGuard() {
        return isShowBucket("mobile_use_api_guard");
    }

    public boolean shouldLoadFeedAfter20Mins(Activity activity) {
        return isShowBucket("mobile_load_feed_after_20_mins") || (isShowV2Bucket("mobile_load_feed_after_20_mins") && !(activity instanceof BrowseActivity));
    }
}
