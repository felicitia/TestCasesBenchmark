package com.contextlogic.wish.activity.login.signin;

import android.content.Intent;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;

public class SignInActivity extends FullScreenActivity {
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
    public UiFragment createMainContentFragment() {
        return new SignInFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new SignInServiceFragment();
    }

    public boolean getOnlyEmail() {
        return getIntent().getBooleanExtra("ExtraEmailOnly", false);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.SIGN_IN;
    }

    public ArrayList<WishImage> getSigninAdapterImages() {
        return getIntent().getParcelableArrayListExtra("ProductGridProducts");
    }

    public final boolean canBeTaskRoot() {
        return isTaskRoot();
    }

    public boolean showFreeGiftErrorMessage() {
        return getIntent().getBooleanExtra("ExtraShowFreeGiftErrorMessage", false);
    }

    public String getPrefilledEmailAddress() {
        return getIntent().getStringExtra("ExtraPrefilledEmailAddress");
    }

    public Intent getPreLoginIntent() {
        return (Intent) IntentUtil.getParcelableExtra(getIntent(), "ExtraPreLoginIntent");
    }
}
