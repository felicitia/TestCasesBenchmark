package com.contextlogic.wish.activity.cart.commercecash;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class CommerceCashCartActivity extends CartActivity {
    public static String EXTRA_COMMERCE_CASH_CART_AMOUNT = "ExtraCommerceCashCartAmount";

    public boolean canHaveActionBar() {
        return false;
    }

    public boolean immediatelyEnforceNotTaskRoot() {
        return true;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new CommerceCashCartFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new CommerceCashCartServiceFragment();
    }

    public double getCommerceCashCartAmount() {
        return getIntent().getDoubleExtra(EXTRA_COMMERCE_CASH_CART_AMOUNT, 0.0d);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.COMMERCE_CASH_CART;
    }

    public int getBackgroundColor() {
        return getResources().getColor(R.color.transparent);
    }
}
