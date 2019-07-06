package com.contextlogic.wish.activity.login.forgotpassword;

import android.content.Intent;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.login.signin.SignInActivity;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class ForgotPasswordActivity extends FullScreenActivity {
    public final boolean canHaveActionBar() {
        return false;
    }

    public boolean canShowDailyGiveawayNotification() {
        return false;
    }

    public boolean requiresAuthentication() {
        return false;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new ForgotPasswordServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new ForgotPasswordFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.forgot_password);
    }

    public Intent getTaskRootIntent() {
        Intent intent = new Intent();
        intent.setClass(this, SignInActivity.class);
        return intent;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.FORGOT_PASSWORD;
    }
}
