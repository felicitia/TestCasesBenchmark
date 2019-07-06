package com.contextlogic.wish.activity.cart.commerceloan;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class CommerceLoanCartActivity extends CartActivity {
    public boolean canHaveActionBar() {
        return false;
    }

    public boolean immediatelyEnforceNotTaskRoot() {
        return true;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new CommerceLoanCartFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new CommerceLoanCartServiceFragment();
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.COMMERCE_LOAN_CART;
    }

    public int getBackgroundColor() {
        return getResources().getColor(R.color.transparent);
    }

    public final String getSuccessSheetTitle() {
        return getIntent().getStringExtra("ArgSuccessSheetTitle");
    }
}
