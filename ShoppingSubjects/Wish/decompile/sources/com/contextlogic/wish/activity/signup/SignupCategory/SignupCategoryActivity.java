package com.contextlogic.wish.activity.signup.SignupCategory;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LoginMode;
import com.contextlogic.wish.util.IntentUtil;

public class SignupCategoryActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public boolean canGoBack() {
        return false;
    }

    public boolean canShowDailyGiveawayNotification() {
        return false;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new SignupCategoryServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new SignupCategoryFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.welcome);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.SIGNUP_CATEGORY;
    }

    public LoginMode getLoginMode() {
        return (LoginMode) IntentUtil.getParcelableExtra(getIntent(), "ExtraLoginMode");
    }

    public boolean showSuccessMessage() {
        return getIntent().getBooleanExtra("ArgShowSuccess", false);
    }
}
