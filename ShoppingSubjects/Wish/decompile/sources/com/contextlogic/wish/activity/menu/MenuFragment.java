package com.contextlogic.wish.activity.menu;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.DrawerListener;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.commercecash.CommerceCashActivity;
import com.contextlogic.wish.activity.crosspromo.CrossPromoActivity;
import com.contextlogic.wish.activity.dailybonus.DailyLoginBonusActivity;
import com.contextlogic.wish.activity.notifications.NotificationsActivity;
import com.contextlogic.wish.activity.pricewatch.PriceWatchActivity;
import com.contextlogic.wish.activity.profile.ProfileActivity;
import com.contextlogic.wish.activity.promocode.PromoCodeActivity;
import com.contextlogic.wish.activity.referralprogram.ReferralProgramActivity;
import com.contextlogic.wish.activity.rewards.RewardsActivity;
import com.contextlogic.wish.activity.search.SearchActivity;
import com.contextlogic.wish.activity.settings.SettingsActivity;
import com.contextlogic.wish.activity.share.ShareActivity;
import com.contextlogic.wish.activity.webview.WebViewActivity;
import com.contextlogic.wish.activity.wishpartner.dashboard.WishPartnerDashboardActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.ApplicationEventBundle;
import com.contextlogic.wish.application.ApplicationEventManager.ApplicationEventCallback;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.PreferenceUtil;

public class MenuFragment extends UiFragment<DrawerActivity> implements ApplicationEventCallback {
    public static String MENU_KEY_BROWSE = "MenuKeyBrowse";
    public static String MENU_KEY_CART = "MenuKeyCart";
    public static String MENU_KEY_COMMERCE_CASH = "MenuKeyCommerceCash";
    public static String MENU_KEY_CUSTOMER_SUPPORT = "MenuKeyCustomerSupport";
    public static String MENU_KEY_DAILY_LOGIN_BONUS = "MenuKeyDailyLoginBonus";
    public static String MENU_KEY_FAQ = "MenuKeyFAQ";
    public static String MENU_KEY_INVITE_FRIENDS = "MenuKeyInviteFriends";
    public static String MENU_KEY_MORE = "MenuKeyMore";
    public static String MENU_KEY_MORE_APPS = "MenuKeyMoreApps";
    public static String MENU_KEY_NOTIFICATIONS = "MenuKeyNotifications";
    public static String MENU_KEY_ORDER_HISTORY = "MenuKeyOrderHistory";
    public static String MENU_KEY_PRICE_WATCH = "MenuKeyPriceWatch";
    public static String MENU_KEY_PROFILE = "MenuKeyProfile";
    public static String MENU_KEY_PROMO_CODE = "MenuKeyPromoCode";
    public static String MENU_KEY_REFERRAL_PROGRAM = "MenuKeyReferralProgram";
    public static String MENU_KEY_REWARDS = "MenuKeyRewards";
    public static String MENU_KEY_SEARCH = "MenuKeySearch";
    public static String MENU_KEY_SETTINGS = "MenuKeySettings";
    public static String MENU_KEY_WISH_PARTNER = "MenuKeyWishPartner";
    /* access modifiers changed from: private */
    public MenuAdapter mAdapter;
    /* access modifiers changed from: private */
    public String mCurrentMenuKey;
    private ListView mListView;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.menu_fragment;
    }

    public void handleResume() {
        super.handleResume();
        if (this.mAdapter != null) {
            this.mAdapter.setupMenu();
            this.mAdapter.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mCurrentMenuKey = ((DrawerActivity) getBaseActivity()).getMenuKey();
        this.mListView = (ListView) findViewById(R.id.menu_fragment_listview);
        this.mAdapter = new MenuAdapter(getBaseActivity(), this.mCurrentMenuKey);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                DrawerActivity drawerActivity = (DrawerActivity) MenuFragment.this.getBaseActivity();
                if (drawerActivity != null) {
                    String item = MenuFragment.this.mAdapter.getItem(i);
                    if (MenuFragment.this.mCurrentMenuKey == null || !item.equals(MenuFragment.this.mCurrentMenuKey)) {
                        Intent intent = new Intent();
                        if (item.equals(MenuFragment.MENU_KEY_PROFILE)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_PROFILE);
                            intent.setClass(drawerActivity, ProfileActivity.class);
                            intent.putExtra("ExtraNoAnimationIntent", true);
                        } else if (item.equals(MenuFragment.MENU_KEY_BROWSE)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_HOME);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, BrowseActivity.class);
                            intent.putExtra("ExtraNoAnimationIntent", true);
                            if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
                                intent.setFlags(131072);
                            }
                        } else if (item.equals(MenuFragment.MENU_KEY_SEARCH)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_SEARCH);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, SearchActivity.class);
                            intent.putExtra("ExtraNoAnimationIntent", true);
                        } else if (item.equals(MenuFragment.MENU_KEY_NOTIFICATIONS)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_NOTIFICATIONS);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, NotificationsActivity.class);
                            intent.putExtra("ExtraNoAnimationIntent", true);
                        } else if (item.equals(MenuFragment.MENU_KEY_CART)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_CART);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, CartActivity.class);
                            intent.putExtra("ExtraNoAnimationIntent", true);
                            intent.putExtra(CartActivity.EXTRA_IS_OPEN_FROM_MENU, true);
                        } else if (item.equals(MenuFragment.MENU_KEY_ORDER_HISTORY)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_ORDER_HISTORY);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, WebViewActivity.class);
                            intent.putExtra("ExtraUrl", WebViewActivity.getOrderHistoryUrl());
                            intent.putExtra("ExtraActionBarTitle", WishApplication.getInstance().getResources().getString(R.string.order_history));
                            intent.putExtra("ExtraNoAnimationIntent", true);
                        } else if (item.equals(MenuFragment.MENU_KEY_COMMERCE_CASH)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_COMMERCE_CASH);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, CommerceCashActivity.class);
                            intent.putExtra("ExtraNoAnimationIntent", true);
                        } else if (item.equals(MenuFragment.MENU_KEY_REWARDS)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_REWARDS);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, RewardsActivity.class);
                            intent.putExtra("ExtraNoAnimationIntent", true);
                        } else if (item.equals(MenuFragment.MENU_KEY_MORE_APPS)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_OTHER_APPS);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, CrossPromoActivity.class);
                            intent.putExtra("ExtraNoAnimationIntent", true);
                        } else if (item.equals(MenuFragment.MENU_KEY_INVITE_FRIENDS)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_INVITE);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, ShareActivity.class);
                            intent.putExtra(ShareActivity.EXTRA_USE_DEFAULT_INVITE_MESSAGE, true);
                            intent.putExtra("ExtraNoAnimationIntent", true);
                        } else if (item.equals(MenuFragment.MENU_KEY_CUSTOMER_SUPPORT)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_HELP);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, WebViewActivity.class);
                            intent.putExtra("ExtraUrl", WebViewActivity.getCustomerSupportUrl());
                            intent.putExtra("ExtraActionBarTitle", WishApplication.getInstance().getResources().getString(R.string.customer_support));
                            intent.putExtra("ExtraNoAnimationIntent", true);
                        } else if (item.equals(MenuFragment.MENU_KEY_FAQ)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_FAQ);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, WebViewActivity.class);
                            intent.putExtra("ExtraUrl", WebViewActivity.getFAQUrl());
                            intent.putExtra("ExtraActionBarTitle", WishApplication.getInstance().getResources().getString(R.string.frequently_asked_questions));
                            intent.putExtra("ExtraNoAnimationIntent", true);
                        } else if (item.equals(MenuFragment.MENU_KEY_SETTINGS)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_SETTINGS);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, SettingsActivity.class);
                            intent.putExtra("ExtraNoAnimationIntent", true);
                        } else if (item.equals(MenuFragment.MENU_KEY_DAILY_LOGIN_BONUS)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_DAILY_LOGIN_BONUS);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, DailyLoginBonusActivity.class);
                            intent.putExtra("ExtraNoAnimationIntent", true);
                        } else if (item.equals(MenuFragment.MENU_KEY_REFERRAL_PROGRAM)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_INVITE);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, ReferralProgramActivity.class);
                        } else if (item.equals(MenuFragment.MENU_KEY_WISH_PARTNER)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_SIDE_NAV);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, WishPartnerDashboardActivity.class);
                        } else if (item.equals(MenuFragment.MENU_KEY_PROMO_CODE)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_APPLY_COUPON_SIDE_NAV);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, PromoCodeActivity.class);
                        } else if (item.equals(MenuFragment.MENU_KEY_PRICE_WATCH)) {
                            StatusDataCenter.getInstance().updateShowPriceWatchNotification(false);
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_PRICE_WATCH);
                            drawerActivity.closeMenu();
                            intent.setClass(drawerActivity, PriceWatchActivity.class);
                            intent.putExtra("ExtraNoAnimationIntent", true);
                        } else {
                            return;
                        }
                        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
                            intent.removeExtra("ExtraNoAnimationIntent");
                        }
                        drawerActivity.startActivity(intent);
                        return;
                    }
                    drawerActivity.closeMenu();
                }
            }
        });
        ApplicationEventManager.getInstance().addCallback(EventType.DATA_CENTER_UPDATED, StatusDataCenter.getInstance().getClass().toString(), this);
        ApplicationEventManager.getInstance().addCallback(EventType.DATA_CENTER_UPDATED, ExperimentDataCenter.getInstance().getClass().toString(), this);
        ApplicationEventManager.getInstance().addCallback(EventType.BADGE_SECTION_VIEWED, MENU_KEY_INVITE_FRIENDS, this);
        ((DrawerActivity) getBaseActivity()).getActionBarManager().addDrawerListener(new DrawerListener() {
            public void onMenuOpened() {
            }

            public void onRightDrawerClosed() {
            }

            public void onRightDrawerOpened() {
            }

            public void onMenuClosed() {
                if (ExperimentDataCenter.getInstance().shouldDismissRedDotOnMenuOpen()) {
                    StatusDataCenter.getInstance().updateShowPriceWatchNotification(false);
                    ((DrawerActivity) MenuFragment.this.getBaseActivity()).getServiceFragment().markRedDotNotificationsSeenRemotely(null, null);
                    PreferenceUtil.setBoolean("SawCashReferral", true);
                    PreferenceUtil.setBoolean("SawInviteCouponScreen", true);
                    PreferenceUtil.setBoolean("SawDailyLoginScreen", true);
                    if (!PreferenceUtil.getBoolean("HideNotificationRedDot", false)) {
                        PreferenceUtil.setBoolean("HideNotificationRedDot", true);
                    }
                    MenuFragment.this.mAdapter.notifyDataSetChanged();
                    ((DrawerActivity) MenuFragment.this.getBaseActivity()).getActionBarManager().apply();
                }
            }
        });
    }

    public void restoreImages() {
        if (this.mAdapter != null) {
            this.mAdapter.restoreImages(this.mListView);
        }
    }

    public void releaseImages() {
        if (this.mAdapter != null) {
            this.mAdapter.releaseImages(this.mListView);
        }
    }

    public void onApplicationEventReceived(EventType eventType, String str, ApplicationEventBundle applicationEventBundle) {
        if (this.mAdapter != null && isResumed()) {
            this.mAdapter.setupMenu();
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
