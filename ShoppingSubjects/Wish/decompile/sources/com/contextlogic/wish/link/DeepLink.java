package com.contextlogic.wish.link;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ServerConfig;
import com.crashlytics.android.Crashlytics;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class DeepLink {
    private String mApplicantId;
    private String mBodyText;
    private String mBrand;
    private String mCategoryId;
    private String mCredit;
    private HashMap<String, String> mExtraParams;
    private boolean mKlarnaPayPal;
    private Uri mLinkUri;
    private String mMerchant;
    private String mPrice;
    private String mProductId;
    private String mProductRatingId;
    private String mProducts;
    private String mQuery;
    private boolean mShowCartError;
    private String mSubjectText;
    private String mTagId;
    private TargetType mTargetType;
    private String mTitle;
    private String mToEmail;
    private Uri mUri;
    private String mUserId;
    private String mVariationId;
    private String mWishlistId;

    private static class DeepLinkException extends Exception {
        DeepLinkException(String str) {
            super(str);
        }
    }

    public enum TargetType {
        NONE,
        BROWSE,
        WEBSITE,
        OTHER_APP,
        SEND_EMAIL,
        CHANGE_PASSWORD,
        CHANGE_EMAIL,
        PUSH_NOTIFICATION_SETTINGS,
        NOTIFICATION_SETTINGS,
        EMAIL_NOTIFICATION_SETTINGS,
        ACCOUNT_SETTINGS,
        RATE_APP,
        DATA_CONTROL_SETTINGS,
        SETTINGS,
        INVITE_FRIENDS,
        UPDATE_PROFILE,
        CHANGE_CURRENCY,
        MERCHANT_FEED,
        BRAND_FEED,
        CART,
        COMMERCE_LOAN_CART,
        REWARDS,
        FEED,
        PRODUCT,
        PROFILE,
        CHANGE_PROFILE_PICTURE,
        NOTIFICATIONS,
        SEARCH,
        SEARCH_FEED,
        SIGNUP_FREE_GIFT,
        EXTERNAL_LINK,
        GET_GIVE_COUPON,
        COMMERCE_CASH,
        MANAGE_ADDRESSES,
        MANAGE_PAYMENTS,
        PRICE_WATCH,
        WISHLIST,
        CHANGE_PHONE_NUMBER,
        ONFIDO
    }

    public DeepLink() {
        StringBuilder sb = new StringBuilder();
        sb.append(WishApplication.getDeepLinkProtocol());
        sb.append("://");
        this(sb.toString());
    }

    public DeepLink(String str) {
        this(Uri.parse(str));
    }

    public DeepLink(Uri uri) {
        this.mTargetType = TargetType.NONE;
        parseUri(transformUri(uri));
    }

    public Uri getUri() {
        return this.mUri;
    }

    public Uri getLinkUri() {
        return this.mLinkUri;
    }

    public String getWebUrl() {
        String uri = this.mUri.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(WishApplication.getDeepLinkProtocol());
        sb.append("://");
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("http://");
        sb3.append(ServerConfig.getInstance().getServerHost());
        sb3.append("/");
        return uri.replace(sb2, sb3.toString());
    }

    public TargetType getTargetType() {
        return this.mTargetType;
    }

    public String getToEmail() {
        return this.mToEmail;
    }

    public String getSubjectText() {
        return this.mSubjectText;
    }

    public String getBodyText() {
        return this.mBodyText;
    }

    private boolean isWishUriHost(Uri uri) {
        String str;
        boolean z = false;
        if (uri == null || uri.getHost() == null) {
            if (uri == null) {
                str = "Uri is null.";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Uri host is null. Uri: ");
                sb.append(uri.toString());
                str = sb.toString();
            }
            Crashlytics.logException(new DeepLinkException(str));
            return false;
        }
        String host = uri.getHost();
        if (host.equalsIgnoreCase("wish.com") || host.equalsIgnoreCase("www.wish.com") || host.equalsIgnoreCase(ServerConfig.getInstance().getServerHost()) || host.equalsIgnoreCase(WishApplication.getInstance().getString(R.string.server_host))) {
            z = true;
        }
        return z;
    }

    private Uri transformUri(Uri uri) {
        String str;
        if (!isWishUriHost(uri)) {
            return uri;
        }
        String uri2 = uri.toString();
        String substring = uri2.substring(uri2.indexOf(".com") + 4);
        if (substring.startsWith("/")) {
            StringBuilder sb = new StringBuilder();
            sb.append(WishApplication.getDeepLinkProtocol());
            sb.append(":/");
            sb.append(substring);
            str = sb.toString();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(WishApplication.getDeepLinkProtocol());
            sb2.append("://");
            sb2.append(substring);
            str = sb2.toString();
        }
        return Uri.parse(str);
    }

    public String getMerchant() {
        return this.mMerchant;
    }

    public String getBrand() {
        return this.mBrand;
    }

    public String getProductId() {
        return this.mProductId;
    }

    public String getVariationId() {
        return this.mVariationId;
    }

    public String getCredit() {
        return this.mCredit;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getCategoryId() {
        return this.mCategoryId;
    }

    public String getProducts() {
        return this.mProducts;
    }

    public String getTagId() {
        return this.mTagId;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getProductRatingId() {
        return this.mProductRatingId;
    }

    public String getOnfidoApplicantId() {
        return this.mApplicantId;
    }

    public String getQuery() {
        return this.mQuery;
    }

    public boolean getKlarnaPayPal() {
        return this.mKlarnaPayPal;
    }

    public boolean getShowCartError() {
        return this.mShowCartError;
    }

    public HashMap<String, String> getExtraParams() {
        return this.mExtraParams;
    }

    public String getWishlistId() {
        return this.mWishlistId;
    }

    private void parseUri(Uri uri) {
        String str;
        if (uri == null || uri.getScheme() == null) {
            if (uri == null) {
                str = "Uri is null.";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Uri scheme is null. Uri: ");
                sb.append(uri.toString());
                str = sb.toString();
            }
            Crashlytics.logException(new DeepLinkException(str));
        } else if (uri.getScheme().equals(WishApplication.getDeepLinkProtocol())) {
            this.mUri = uri;
            String path = this.mUri.getPath();
            String host = this.mUri.getHost();
            Bundle parseParams = parseParams(this.mUri.getFragment());
            boolean z = false;
            if (host == null || host.isEmpty() || host.equals("home")) {
                String param = getParam("next", parseParams);
                if (param != null) {
                    if (!param.startsWith("/")) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("/");
                        sb2.append(param);
                        param = sb2.toString();
                    }
                    String replaceFirst = param.replaceFirst("/m/", "/").replaceFirst("/m\\?", "/?").replaceFirst("/m#", "/#");
                    if (replaceFirst.endsWith("/m")) {
                        replaceFirst = replaceFirst.substring(0, replaceFirst.length() - 2);
                    }
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("https://");
                    sb3.append(ServerConfig.getInstance().getServerHost());
                    sb3.append(replaceFirst);
                    parseUri(transformUri(Uri.parse(sb3.toString())));
                    if (this.mTargetType == TargetType.WEBSITE) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("https://");
                        sb4.append(ServerConfig.getInstance().getServerHost());
                        sb4.append(param);
                        parseUri(transformUri(Uri.parse(sb4.toString())));
                    }
                } else {
                    this.mTargetType = TargetType.BROWSE;
                    this.mCategoryId = getParam("category_id", parseParams);
                    this.mTagId = getParam("tag", parseParams);
                    if (this.mTagId == null) {
                        this.mTagId = getParam("filter", parseParams);
                    }
                }
            } else if (host.equals("send-email")) {
                this.mTargetType = TargetType.SEND_EMAIL;
                this.mToEmail = getParam("to", parseParams);
                this.mSubjectText = getParam("subject", parseParams);
                this.mBodyText = getParam("body", parseParams);
            } else if (host.equals("app") && getParam("l", parseParams) != null) {
                this.mLinkUri = Uri.parse(getParam("l", parseParams));
                if (this.mLinkUri != null) {
                    this.mTargetType = TargetType.OTHER_APP;
                }
            } else if (host.equals("settings")) {
                this.mTargetType = TargetType.SETTINGS;
            } else if (host.equals("change-password")) {
                this.mTargetType = TargetType.CHANGE_PASSWORD;
            } else if (host.equals("notifications")) {
                this.mTargetType = TargetType.NOTIFICATIONS;
            } else if (host.equals("push-notifications")) {
                this.mTargetType = TargetType.PUSH_NOTIFICATION_SETTINGS;
            } else if (host.equals("notification-settings")) {
                this.mTargetType = TargetType.NOTIFICATION_SETTINGS;
            } else if (host.equals("email-noti-settings")) {
                this.mTargetType = TargetType.EMAIL_NOTIFICATION_SETTINGS;
            } else if (host.equals("account-settings")) {
                this.mTargetType = TargetType.ACCOUNT_SETTINGS;
            } else if (host.equals("rate-app")) {
                this.mTargetType = TargetType.RATE_APP;
            } else if (host.equals("data-control")) {
                this.mTargetType = TargetType.DATA_CONTROL_SETTINGS;
            } else if (host.equals("invite")) {
                this.mTargetType = TargetType.INVITE_FRIENDS;
            } else if (host.equals("update-profile")) {
                this.mTargetType = TargetType.UPDATE_PROFILE;
            } else if (host.equals("change-profile-pic")) {
                this.mTargetType = TargetType.CHANGE_PROFILE_PICTURE;
            } else if (host.equals("merchant") && path != null && !path.equals("") && !path.equals("/")) {
                this.mTargetType = TargetType.MERCHANT_FEED;
                this.mMerchant = path.substring(1);
            } else if (host.equals("brand") && path != null && !path.equals("") && !path.equals("/")) {
                this.mTargetType = TargetType.BRAND_FEED;
                this.mBrand = path.substring(1);
            } else if (host.equals("search") && path != null && !path.equals("") && !path.equals("/")) {
                this.mTargetType = TargetType.SEARCH_FEED;
                this.mQuery = path.substring(1);
            } else if (host.equals("search") && (path == null || path.equals("") || path.equals("/"))) {
                this.mTargetType = TargetType.SEARCH;
            } else if (host.equals("shopping")) {
                this.mTargetType = TargetType.BRAND_FEED;
                String param2 = getParam("brand", parseParams);
                if (param2 == null) {
                    param2 = "wishwall";
                }
                this.mBrand = param2;
                this.mTagId = getParam("tag", parseParams);
                this.mProducts = getParam("cids", parseParams);
                this.mPrice = getParam("price", parseParams);
                this.mCredit = getParam("credit", parseParams);
                this.mTitle = getParam("force_title", parseParams);
            } else if (host.equals("cart")) {
                this.mTargetType = TargetType.CART;
                this.mProductId = getParam("add_product", parseParams);
                this.mVariationId = getParam("add_variation", parseParams);
                String param3 = getParam("klarna_paypal", parseParams);
                this.mKlarnaPayPal = param3 != null && param3.equalsIgnoreCase("true");
                String param4 = getParam("show_error_message", parseParams);
                if (param4 != null && param4.equalsIgnoreCase("true")) {
                    z = true;
                }
                this.mShowCartError = z;
            } else if (host.equals("commerce_loan_cart")) {
                this.mTargetType = TargetType.COMMERCE_LOAN_CART;
            } else if (host.equals("wallet")) {
                this.mTargetType = TargetType.REWARDS;
                if (ExperimentDataCenter.getInstance().turnOffRewards()) {
                    this.mTargetType = TargetType.BROWSE;
                }
            } else if (host.equals("category-home")) {
                this.mTargetType = TargetType.FEED;
                this.mCategoryId = getParam("category", parseParams);
            } else if (host.equals("feed") && path != null && path.length() > 1) {
                this.mTargetType = TargetType.FEED;
                this.mCategoryId = path.substring(1);
            } else if ((host.equals("c") || host.equals("contest") || host.equals("product")) && path != null) {
                String str2 = null;
                if (path.length() >= 25 && path.charAt(path.length() - 25) == '-') {
                    str2 = path.substring(path.length() - 24);
                } else if (path.length() >= 25 && path.charAt(path.length() - 25) == '/') {
                    str2 = path.substring(path.length() - 24);
                }
                if (str2 != null) {
                    String lowerCase = str2.toLowerCase();
                    if (validateHexString(lowerCase)) {
                        this.mTargetType = TargetType.PRODUCT;
                        this.mProductId = lowerCase;
                        this.mCredit = getParam("credit", parseParams);
                        this.mProductRatingId = getParam("product_rating_id", parseParams);
                        this.mExtraParams = getParamsMap(parseParams);
                    }
                }
            } else if (host.equals("profile")) {
                this.mTargetType = TargetType.PROFILE;
                String param5 = getParam("uid", parseParams);
                if (param5 != null && validateHexString(param5)) {
                    this.mUserId = param5;
                }
            } else if (host.equals("signup-gift")) {
                this.mTargetType = TargetType.SIGNUP_FREE_GIFT;
            } else if (host.equals("external-link")) {
                this.mTargetType = TargetType.EXTERNAL_LINK;
                this.mQuery = getParam("link", parseParams);
            } else if (host.equals("get_give_coupon")) {
                this.mTargetType = TargetType.GET_GIVE_COUPON;
            } else if (host.equals("m") && path != null && path.equals("/settings/email-settings")) {
                this.mTargetType = TargetType.CHANGE_EMAIL;
            } else if (host.equals("cash")) {
                this.mTargetType = TargetType.COMMERCE_CASH;
            } else if (host.equals("m") && path != null && path.equals("/currency-settings")) {
                this.mTargetType = TargetType.CHANGE_CURRENCY;
            } else if (host.equals("manage-addresses")) {
                this.mTargetType = TargetType.MANAGE_ADDRESSES;
            } else if (host.equals("manage-payment-methods")) {
                this.mTargetType = TargetType.MANAGE_PAYMENTS;
            } else if (host.equals("price-watch")) {
                this.mTargetType = TargetType.PRICE_WATCH;
            } else if (host.equals("wishlist") && !TextUtils.isEmpty(path)) {
                this.mTargetType = TargetType.WISHLIST;
                this.mWishlistId = path.substring(1);
            } else if (host.equals("change-phone-number")) {
                this.mTargetType = TargetType.CHANGE_PHONE_NUMBER;
            } else if (!host.equals("onfido") || path == null) {
                this.mTargetType = TargetType.WEBSITE;
            } else {
                this.mApplicantId = path.replaceFirst("/", "");
                this.mTargetType = TargetType.ONFIDO;
            }
            if (TextUtils.equals(getParam("from_ad", parseParams), "msgr_bot")) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MESSENGER_INBOUND_LINK, getParamsMap(parseParams));
            }
            String param6 = getParam("utm", parseParams);
            String param7 = getParam("utm_medium", parseParams);
            if (!(param6 == null && param7 == null)) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_CAMPAIGN_TRACKING, getParamsMap(parseParams));
            }
        }
    }

    public static boolean validateHexString(String str) {
        String lowerCase = str.toLowerCase();
        for (int i = 0; i < lowerCase.length(); i++) {
            char charAt = lowerCase.charAt(i);
            if (!Character.isDefined(charAt) && charAt != 'a' && charAt != 'b' && charAt != 'c' && charAt != 'd' && charAt != 'e' && charAt != 'f') {
                return false;
            }
        }
        return true;
    }

    private String getParam(String str, Bundle bundle) {
        String queryParameter = this.mUri != null ? this.mUri.getQueryParameter(str) : null;
        return (queryParameter != null || bundle == null) ? queryParameter : bundle.getString(str);
    }

    private HashMap<String, String> getParamsMap(Bundle bundle) {
        String[] split;
        try {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (String str : this.mUri.getQuery().split("&")) {
                int indexOf = str.indexOf("=");
                linkedHashMap.put(URLDecoder.decode(str.substring(0, indexOf), "UTF-8"), URLDecoder.decode(str.substring(indexOf + 1), "UTF-8"));
            }
            return linkedHashMap;
        } catch (Exception unused) {
            return null;
        }
    }

    private Bundle parseParams(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                if (split2.length >= 2) {
                    try {
                        String decode = URLDecoder.decode(split2[1], "UTF-8");
                        if (!(decode == null || split2[0] == null)) {
                            bundle.putString(split2[0], decode);
                        }
                    } catch (Throwable unused) {
                    }
                }
            }
        }
        return bundle;
    }
}
