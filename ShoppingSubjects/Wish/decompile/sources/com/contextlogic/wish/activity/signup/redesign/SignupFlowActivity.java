package com.contextlogic.wish.activity.signup.redesign;

import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LoginMode;
import com.contextlogic.wish.api.service.standalone.LoginService.SignupFlowContext;
import com.contextlogic.wish.util.IntentUtil;

public class SignupFlowActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public boolean canGoBack() {
        return false;
    }

    public final boolean canHaveActionBar() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean requiresNoInterruption() {
        return true;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new SignupFlowServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new SignupFlowFragment();
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

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        super.initializeActionBarManager(actionBarManager);
        actionBarManager.setHomeButtonMode(HomeButtonMode.BACK_ARROW);
        actionBarManager.setTheme(Theme.TRANSPARENT_BACKGROUND_DARK_BUTTONS);
        actionBarManager.setBadgeVisible(false);
    }
}
