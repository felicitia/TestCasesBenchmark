package com.contextlogic.wish.analytics;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.application.WishApplication;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.HitBuilders.ScreenViewBuilder;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class GoogleAnalyticsLogger {
    private static GoogleAnalyticsLogger sInstance = new GoogleAnalyticsLogger();
    /* access modifiers changed from: private */
    public FirebaseAnalytics mFirebaseAnalytics;
    private boolean mStarted;
    /* access modifiers changed from: private */
    public Tracker mTracker;
    private ExecutorService mTrackerPool = Executors.newFixedThreadPool(1, new ThreadFactory() {
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(1);
            return thread;
        }
    });

    public enum EventType {
        LAUNCH_APP
    }

    public enum PageViewType {
        APP,
        UNSPECIFIED,
        WEBVIEW,
        LOGIN,
        SIGN_IN,
        CREATE_ACCOUNT,
        SIGNUP_CATEGORY,
        SIGNUP_FREE_GIFT,
        SIGNUP_UPDATE_PROFILE,
        FORGOT_PASSWORD,
        BROWSE,
        SEARCH,
        REWARDS,
        CROSS_PROMO,
        ACCOUNT_SETTINGS,
        COUNTRY_SETTINGS,
        SETTINGS,
        DEVELOPER_SETTINGS,
        DEVELOPER_SETTINGS_EXPERIMENTS,
        CHANGE_PASSWORD,
        CHANGE_EMAIL,
        CHANGE_CURRENCY,
        CHANGE_PHONE_NUMBER,
        DATA_CONTROL_SETTINGS,
        PUSH_NOTIFICATION_SETTINGS,
        NOTIFICATION_SETTINGS,
        CART,
        TEXT_VIEWER,
        UPDATE_PROFILE,
        PROFILE,
        WISHLIST,
        MERCHANT,
        MERCHANT_NEW,
        BRAND,
        UserList,
        NOTIFICATIONS,
        IMAGE_VIEWER,
        WEB_VIEW,
        PRODUCT_DETAILS,
        TAG,
        EARN_MONEY,
        THUMBNAIL_VIEWER,
        ORDER_CONFIRMED,
        ORDER_HISTORY,
        TICKET_INFO,
        BRANDED_CATEGORIES,
        BRANDED_FEED,
        INVITE_COUPON,
        COMMERCE_CASH_CART,
        COMMERCE_CASH,
        COMMERCE_LOAN_LEARN_MORE,
        PAY_HALF_LEARN_MORE,
        COMMERCE_CASH_TERMS,
        DAILY_LOGIN_BONUS,
        BUYER_GUARANTEE,
        PHOTO_VIDEO_VIEWER,
        EMAIL_NOTIFICATION_SETTINGS,
        CAMERA,
        CAMERA_PERMISSIONS,
        VIDEO_PLAYER,
        PRODUCT_REVIEW,
        EXAMPLE_UGC_INTRO,
        EXAMPLE_UGC_ITEMS,
        COMMERCE_LOAN_CART,
        MORE,
        WISH_PARTNER_INFO,
        WISH_PARTNER_CASH_OUT_INFO,
        WISH_PARTNER_LEARN_MORE,
        PRICE_WATCH
    }

    private GoogleAnalyticsLogger() {
    }

    public static GoogleAnalyticsLogger getInstance() {
        return sInstance;
    }

    public boolean isStarted() {
        return this.mStarted;
    }

    public void startAnalytics() {
        if (this.mTracker == null) {
            this.mStarted = true;
            this.mTrackerPool.execute(new Runnable() {
                public void run() {
                    try {
                        GoogleAnalyticsLogger.this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(WishApplication.getInstance());
                        GoogleAnalytics instance = GoogleAnalytics.getInstance(WishApplication.getInstance());
                        GoogleAnalyticsLogger.this.mTracker = instance.newTracker(WishApplication.getInstance().getString(R.string.google_analytics_id));
                        instance.setLocalDispatchPeriod(30);
                        GoogleAnalyticsLogger.this.mTracker.setSampleRate(1.0d);
                        EventBuilder eventBuilder = new EventBuilder();
                        eventBuilder.setCategory(PageViewType.APP.name());
                        eventBuilder.setAction(EventType.LAUNCH_APP.name());
                        eventBuilder.setLabel(WishApplication.getInstance().getVersionNumber());
                        eventBuilder.setNewSession();
                        GoogleAnalyticsLogger.this.mTracker.send(eventBuilder.build());
                    } catch (Throwable unused) {
                    }
                }
            });
        }
    }

    private String preparePageViewName(PageViewType pageViewType) {
        if (pageViewType == null) {
            pageViewType = PageViewType.UNSPECIFIED;
        }
        String name = pageViewType.name();
        if (AuthenticationDataCenter.getInstance().isLoggedIn()) {
            return name;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("LoggedOut-");
        sb.append(name);
        return sb.toString();
    }

    public void trackPageView(PageViewType pageViewType) {
        trackPageView(preparePageViewName(pageViewType));
    }

    public void trackPageView(final String str) {
        this.mTrackerPool.execute(new Runnable() {
            public void run() {
                try {
                    Crashlytics.setString("LastPageView", str);
                    if (GoogleAnalyticsLogger.this.mTracker != null) {
                        ScreenViewBuilder screenViewBuilder = new ScreenViewBuilder();
                        GoogleAnalyticsLogger.this.mTracker.setScreenName(str);
                        GoogleAnalyticsLogger.this.mTracker.send(screenViewBuilder.build());
                    }
                } catch (Throwable unused) {
                }
            }
        });
    }

    public void logLastApiRequest(ApiRequest apiRequest) {
        try {
            String apiEndpointPath = apiRequest.getApiEndpointPath();
            if (apiEndpointPath != null && !apiEndpointPath.contains("user/status") && !apiEndpointPath.contains("mobile/log") && !apiEndpointPath.contains("mobile/batch-log")) {
                Crashlytics.setString("LastEndPoint", apiRequest.getApiEndpointPath());
            }
        } catch (Throwable unused) {
        }
    }

    public void logLastActivity(BaseActivity baseActivity) {
        try {
            Crashlytics.setString("LastActivity", baseActivity.getClass().getSimpleName());
        } catch (Throwable unused) {
        }
    }

    public void logPurchase(String str, double d, String str2) {
        ExecutorService executorService = this.mTrackerPool;
        final String str3 = str;
        final double d2 = d;
        final String str4 = str2;
        AnonymousClass6 r1 = new Runnable() {
            public void run() {
                try {
                    Bundle bundle = new Bundle();
                    if (str3 != null) {
                        bundle.putString("transaction_id", str3);
                    }
                    bundle.putDouble("value", d2);
                    bundle.putString("currency", str4);
                    GoogleAnalyticsLogger.this.mFirebaseAnalytics.logEvent("ecommerce_purchase", bundle);
                } catch (Throwable unused) {
                }
            }
        };
        executorService.execute(r1);
    }

    public void logSearch(final String str) {
        this.mTrackerPool.execute(new Runnable() {
            public void run() {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("search_term", str);
                    GoogleAnalyticsLogger.this.mFirebaseAnalytics.logEvent("view_search_results", bundle);
                } catch (Throwable unused) {
                }
            }
        });
    }

    public void logProductView(final String str) {
        this.mTrackerPool.execute(new Runnable() {
            public void run() {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("item_id", str);
                    GoogleAnalyticsLogger.this.mFirebaseAnalytics.logEvent("view_item", bundle);
                    Crashlytics.setString("LastProduct", str);
                } catch (Throwable unused) {
                }
            }
        });
    }

    public void logProductWish(final String str) {
        this.mTrackerPool.execute(new Runnable() {
            public void run() {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("item_id", str);
                    GoogleAnalyticsLogger.this.mFirebaseAnalytics.logEvent("add_to_wishlist", bundle);
                } catch (Throwable unused) {
                }
            }
        });
    }

    public void logAddToCart(final WishCartItem wishCartItem) {
        this.mTrackerPool.execute(new Runnable() {
            public void run() {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("item_id", wishCartItem.getProductId());
                    bundle.putDouble("value", wishCartItem.getProductSubtotal().getValue());
                    bundle.putDouble("price", wishCartItem.getProductSubtotal().getValue());
                    bundle.putString("currency", wishCartItem.getProductSubtotal().getLocalizedCurrencyCode());
                    GoogleAnalyticsLogger.this.mFirebaseAnalytics.logEvent("add_to_cart", bundle);
                } catch (Throwable unused) {
                }
            }
        });
    }

    public void logUserIdentifiers() {
        try {
            if (ProfileDataCenter.getInstance().getUserId() != null) {
                Crashlytics.setUserIdentifier(ProfileDataCenter.getInstance().getUserId());
            }
            if (ProfileDataCenter.getInstance().getCountryCode() != null) {
                Crashlytics.setString("CountryCode", ProfileDataCenter.getInstance().getCountryCode());
            }
            if (ProfileDataCenter.getInstance().getGender() != null) {
                Crashlytics.setString("Gender", ProfileDataCenter.getInstance().getGender());
            }
        } catch (Throwable unused) {
        }
    }
}
