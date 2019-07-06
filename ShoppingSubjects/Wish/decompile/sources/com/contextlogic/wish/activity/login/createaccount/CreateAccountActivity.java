package com.contextlogic.wish.activity.login.createaccount;

import android.content.Intent;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.model.LoggedOutCountdownCoupon;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishSignupVideoPopupSpec;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;

public class CreateAccountActivity extends FullScreenActivity {
    private static boolean firstTime = true;

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
        updateFirstTime();
        return new CreateAccountServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new CreateAccountFragment();
    }

    private void updateFirstTime() {
        firstTime = getIntent().getBooleanExtra("ExtraShowRedesignSignup", firstTime);
    }

    public static void setIsFirstTime(boolean z) {
        firstTime = z;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.CREATE_ACCOUNT;
    }

    public ArrayList<WishImage> getSigninAdapterImages() {
        return getIntent().getParcelableArrayListExtra("ProductGridProducts");
    }

    public final boolean canBeTaskRoot() {
        return isTaskRoot();
    }

    public Intent getPreLoginIntent() {
        return (Intent) IntentUtil.getParcelableExtra(getIntent(), "ExtraPreLoginIntent");
    }

    public LoggedOutCountdownCoupon getSignupTimedGift() {
        return (LoggedOutCountdownCoupon) IntentUtil.getParcelableExtra(getIntent(), "ExtraSignupTimedGift");
    }

    public WishSignupVideoPopupSpec getSignupPopupVideo() {
        return (WishSignupVideoPopupSpec) IntentUtil.getParcelableExtra(getIntent(), "ExtraSignupPopup");
    }
}
