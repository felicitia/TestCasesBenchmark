package com.contextlogic.wish.activity.commercecash;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.application.WishApplication;

public class CommerceCashTermsActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new CommerceCashTermsServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new CommerceCashTermsFragment();
    }

    public String getActionBarTitle() {
        return WishApplication.getInstance().getString(R.string.terms_and_conditions);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.COMMERCE_CASH_TERMS;
    }
}
