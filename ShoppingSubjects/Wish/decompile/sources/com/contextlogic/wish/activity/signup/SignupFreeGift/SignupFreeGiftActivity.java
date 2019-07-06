package com.contextlogic.wish.activity.signup.SignupFreeGift;

import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LoginMode;
import com.contextlogic.wish.api.service.standalone.LoginService.SignupFlowContext;
import com.contextlogic.wish.util.IntentUtil;

public class SignupFreeGiftActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public boolean canHaveActionBar() {
        return false;
    }

    public boolean canShowDailyGiveawayNotification() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean requiresNoInterruption() {
        return true;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new SignupFreeGiftServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new SignupFreeGiftFragment();
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.SIGNUP_FREE_GIFT;
    }

    public SignupFlowContext getSignupFlowContext() {
        return (SignupFlowContext) IntentUtil.getParcelableExtra(getIntent(), "ArgSignupFlowContext");
    }

    public boolean startedFromNotification() {
        return getIntent().getBooleanExtra("ArgStartedFomNotification", false);
    }

    /* access modifiers changed from: protected */
    public boolean canGoBack() {
        return startedFromNotification();
    }

    public LoginMode getLoginMode() {
        return (LoginMode) IntentUtil.getParcelableExtra(getIntent(), "ExtraLoginMode");
    }
}
