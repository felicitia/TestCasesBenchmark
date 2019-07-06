package com.contextlogic.wish.activity.signup;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LoginMode;
import com.contextlogic.wish.api.service.standalone.LoginService.SignupFlowContext;
import com.contextlogic.wish.util.IntentUtil;

public class SignupProfileUpdateActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public boolean canGoBack() {
        return false;
    }

    public boolean canShowDailyGiveawayNotification() {
        return false;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new SignupProfileUpdateFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new SignupProfileUpdateServiceFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.update_profile);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.SIGNUP_UPDATE_PROFILE;
    }

    public SignupFlowContext getSignupFlowContext() {
        return (SignupFlowContext) IntentUtil.getParcelableExtra(getIntent(), "ArgSignupFlowContext");
    }

    public LoginMode getLoginMode() {
        return (LoginMode) IntentUtil.getParcelableExtra(getIntent(), "ExtraLoginMode");
    }
}
