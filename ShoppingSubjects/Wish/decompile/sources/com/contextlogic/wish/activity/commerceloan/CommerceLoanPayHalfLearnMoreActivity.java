package com.contextlogic.wish.activity.commerceloan;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class CommerceLoanPayHalfLearnMoreActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new CommerceLoanPayHalfLearnMoreFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new CommerceLoanPayHalfLearnMoreServiceFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.learn_more);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.PAY_HALF_LEARN_MORE;
    }
}
