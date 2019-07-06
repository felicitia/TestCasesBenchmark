package com.contextlogic.wish.activity.webview;

import android.content.Intent;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.http.ServerConfig;
import com.contextlogic.wish.util.IntentUtil;
import com.crashlytics.android.Crashlytics;

public class WebViewActivity extends DrawerActivity {

    public enum Source {
        CART
    }

    public int getBottomNavigationTabIndex() {
        return 4;
    }

    public static String getPriceChopRules() {
        return ServerConfig.getInstance().generateUrl("m/price_chop_rules");
    }

    public static String getCustomerSupportUrl() {
        return ServerConfig.getInstance().generateUrl("customer-support-center");
    }

    public static String getTermsUrl() {
        return ServerConfig.getInstance().generateUrl("m/terms");
    }

    public static String getPrivacyPolicyUrl() {
        return ServerConfig.getInstance().generateUrl("m/privacy_policy");
    }

    public static String getFAQUrl() {
        return ServerConfig.getInstance().generateUrl("m/help");
    }

    public static String getOrderHistoryUrl() {
        return ServerConfig.getInstance().generateUrl("m/transaction");
    }

    public static String getOrderUrl(String str) {
        ServerConfig instance = ServerConfig.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("m/transaction/");
        sb.append(str);
        return instance.generateUrl(sb.toString());
    }

    public static String getPaymentIssueUrl() {
        return ServerConfig.getInstance().generateUrl("m/payment-issue");
    }

    public static String getBoletoViewUrl(String str) {
        ServerConfig instance = ServerConfig.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("m/view-boleto?tid=");
        sb.append(str);
        return instance.generateUrl(sb.toString());
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new WebViewServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new WebViewFragment();
    }

    public String getFirstUrl() {
        String stringExtra = getIntent().getStringExtra("ExtraUrl");
        return stringExtra != null ? stringExtra.replaceAll("http://", "https://") : stringExtra;
    }

    /* access modifiers changed from: protected */
    public boolean requiresAuthentication() {
        String firstUrl = getFirstUrl();
        if (firstUrl == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("firstUrl == null in WebViewActivity. Source: ");
            sb.append(getIntent().getStringExtra("ExtraSourceActivity"));
            Crashlytics.logException(new Exception(sb.toString()));
            return true;
        } else if (firstUrl.equals(getTermsUrl()) || firstUrl.equals(getPrivacyPolicyUrl())) {
            return false;
        } else {
            return true;
        }
    }

    public Source getSource() {
        return (Source) getIntent().getSerializableExtra("ExtraSource");
    }

    public boolean getLoadKlarnaSDK() {
        return getIntent().getBooleanExtra("ExtraLoadKlarnaSDK", false);
    }

    public boolean getHideActionBarItems() {
        return getIntent().getBooleanExtra("ExtraHideActionBarItems", false);
    }

    public String getActionBarTitle() {
        return getIntent().getStringExtra("ExtraActionBarTitle");
    }

    public double getTransactionCartAmount() {
        return getIntent().getDoubleExtra("ExtraTransactionCartAmount", -1.0d);
    }

    public String getTransactionId() {
        return getIntent().getStringExtra("ExtraTransactionId");
    }

    public String getTransactionCurrencyCode() {
        return getIntent().getStringExtra("ExtraTransactionCurrencyCode");
    }

    public String getTransactionCartItemIds() {
        return getIntent().getStringExtra("ExtraTransactionCartItemIds");
    }

    public String getMenuKey() {
        String firstUrl = getFirstUrl();
        if (firstUrl != null && firstUrl.equals(getCustomerSupportUrl())) {
            return MenuFragment.MENU_KEY_CUSTOMER_SUPPORT;
        }
        if (firstUrl != null && firstUrl.equals(getFAQUrl())) {
            return MenuFragment.MENU_KEY_FAQ;
        }
        if (firstUrl == null || !firstUrl.equals(getOrderHistoryUrl())) {
            return null;
        }
        return MenuFragment.MENU_KEY_ORDER_HISTORY;
    }

    public final boolean canHaveMenu() {
        return getSource() != Source.CART;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.WEBVIEW;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (IntentUtil.safeToUnparcel(intent) && intent.getBooleanExtra("ExtraRequiresReload", false)) {
            ((WebViewFragment) getUiFragment("FragmentTagMainContent")).refreshWebView();
        }
    }
}
