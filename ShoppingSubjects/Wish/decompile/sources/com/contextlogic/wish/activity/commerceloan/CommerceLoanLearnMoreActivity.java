package com.contextlogic.wish.activity.commerceloan;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.application.WishApplication;

public class CommerceLoanLearnMoreActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new CommerceLoanLearnMoreFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new CommerceLoanLearnMoreServiceFragment();
    }

    public String getActionBarTitle() {
        return WishApplication.getInstance().getString(R.string.learn_more);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.COMMERCE_LOAN_LEARN_MORE;
    }
}
