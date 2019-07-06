package com.contextlogic.wish.social.facebook;

import android.content.Context;
import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.PreferenceUtil;
import com.facebook.appevents.AppEventsLogger;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;

public class FacebookManager {
    private static FacebookManager sInstance = new FacebookManager();
    private boolean mActivateTrackingInstalled = false;
    private AppEventsLogger mDefaultAppEventLogger;
    private FacebookLoginSession mFacebookLoginSession = new FacebookLoginSession();

    public enum ProfileImageSize {
        SMALL,
        MEDIUM,
        LARGE
    }

    private FacebookManager() {
    }

    public static FacebookManager getInstance() {
        return sInstance;
    }

    public static String getAppId() {
        if (PreferenceUtil.getBoolean("DevSettingsUseDevFbApp")) {
            return WishApplication.getInstance().getString(R.string.facebook_dev_app_id);
        }
        return WishApplication.getInstance().getString(R.string.facebook_prod_app_id);
    }

    public FacebookLoginSession getLoginSession() {
        return this.mFacebookLoginSession;
    }

    public void logPurchase(double d, String str, String str2) {
        if (d == -1.0d || str == null || str2 == null) {
            getLogger().logPurchase(new BigDecimal("0.00"), Currency.getInstance("USD"));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("fb_content_type", "product");
        bundle.putString("fb_content_id", str2);
        getLogger().logPurchase(new BigDecimal(String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(d)})), Currency.getInstance(str), bundle);
    }

    public void logSearch(List<WishProduct> list, String str) {
        JSONArray jSONArray = new JSONArray();
        for (WishProduct productId : list) {
            jSONArray.put(productId.getProductId());
        }
        Bundle bundle = new Bundle();
        bundle.putString("fb_search_string", str);
        bundle.putString("fb_content_type", "product");
        bundle.putString("fb_content_id", jSONArray.toString());
        getLogger().logEvent("fb_mobile_search", bundle);
    }

    public AppEventsLogger getLogger() {
        if (this.mFacebookLoginSession.getLogger() != null) {
            return this.mFacebookLoginSession.getLogger();
        }
        if (this.mDefaultAppEventLogger == null || !this.mDefaultAppEventLogger.getApplicationId().equals(getAppId())) {
            this.mDefaultAppEventLogger = AppEventsLogger.newLogger((Context) WishApplication.getInstance(), getAppId());
        }
        return this.mDefaultAppEventLogger;
    }

    public void trackResume() {
        if (!this.mActivateTrackingInstalled) {
            this.mActivateTrackingInstalled = true;
            AppEventsLogger.activateApp(WishApplication.getInstance(), getAppId());
        }
    }

    public static String getProfileImageUrlString(String str, ProfileImageSize profileImageSize) {
        String str2;
        switch (profileImageSize) {
            case SMALL:
                str2 = "width=50&height=50";
                break;
            case MEDIUM:
                str2 = "width=100&height=100";
                break;
            case LARGE:
                str2 = "width=200&height=200";
                break;
            default:
                str2 = null;
                break;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("http://graph.facebook.com/");
        sb.append(str);
        sb.append("/picture?");
        sb.append(str2);
        return sb.toString();
    }
}
