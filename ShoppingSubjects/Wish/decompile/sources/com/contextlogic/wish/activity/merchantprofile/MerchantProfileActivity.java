package com.contextlogic.wish.activity.merchantprofile;

import android.content.Intent;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.application.WishApplication;

public class MerchantProfileActivity extends DrawerActivity {
    public static String EXTRA_MERCHANT = "ExtraMerchant";
    public static String EXTRA_MERCHANT_ID = "ExtraMerchantId";

    public int getBottomNavigationTabIndex() {
        return 1;
    }

    public String getMenuKey() {
        return null;
    }

    public boolean shouldUseDynamicBottomNavigationLayout() {
        return true;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new MerchantProfileServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new MerchantProfileFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.store);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.MERCHANT_NEW;
    }

    public String getMerchant() {
        return getIntent().getStringExtra(EXTRA_MERCHANT);
    }

    public String getMerchantId() {
        return getIntent().getStringExtra(EXTRA_MERCHANT_ID);
    }

    public static Intent createIntent(String str, String str2) {
        Intent intent = new Intent();
        intent.setClass(WishApplication.getInstance(), MerchantProfileActivity.class);
        intent.putExtra(EXTRA_MERCHANT, str2);
        intent.putExtra(EXTRA_MERCHANT_ID, str);
        return intent;
    }
}
