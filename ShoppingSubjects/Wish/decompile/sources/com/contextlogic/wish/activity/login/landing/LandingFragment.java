package com.contextlogic.wish.activity.login.landing;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.login.createaccount.CreateAccountActivity;
import com.contextlogic.wish.activity.login.signin.SignInActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.LoggedOutCountdownCoupon;
import com.contextlogic.wish.api.model.WishReloginInfo;
import com.contextlogic.wish.api.model.WishSignupVideoPopupSpec;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class LandingFragment extends UiFragment<LandingActivity> {
    private boolean mAlreadyHasApp;
    private Map<String, String> mExperiments;
    protected View mLogoContainer;
    /* access modifiers changed from: private */
    public LoggedOutCountdownCoupon mSignupGiftCoupon;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.landing_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public void handleSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("SavedStateAlreadyHasApp", this.mAlreadyHasApp);
        bundle.putParcelable("SavedStateSignupTimedGift", this.mSignupGiftCoupon);
        if (this.mExperiments != null && !this.mExperiments.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (Entry entry : this.mExperiments.entrySet()) {
                arrayList.add(entry.getKey());
                bundle.putString((String) entry.getKey(), (String) entry.getValue());
            }
            bundle.putStringArrayList("SavedStateExperimentKeys", arrayList);
        }
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mLogoContainer = findViewById(R.id.login_fragment_logo_container);
        if (getSavedInstanceState() != null) {
            Bundle savedInstanceState = getSavedInstanceState();
            this.mAlreadyHasApp = savedInstanceState.getBoolean("SavedStateAlreadyHasApp", false);
            this.mExperiments = new HashMap();
            this.mSignupGiftCoupon = (LoggedOutCountdownCoupon) savedInstanceState.getParcelable("SavedStateSignupTimedGift");
            ArrayList stringArrayList = savedInstanceState.getStringArrayList("SavedStateExperimentKeys");
            if (stringArrayList != null) {
                Iterator it = stringArrayList.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    this.mExperiments.put(str, savedInstanceState.getString(str));
                }
                handleLoggedOutExperimentLoadSuccess(this.mExperiments, this.mAlreadyHasApp, null, this.mSignupGiftCoupon, null);
                return;
            }
        }
        loadLoggedOutExperiments();
    }

    private void loadLoggedOutExperiments() {
        withServiceFragment(new ServiceTask<BaseActivity, LandingServiceFragment>() {
            public void performTask(BaseActivity baseActivity, LandingServiceFragment landingServiceFragment) {
                LandingFragment.this.mLogoContainer.setVisibility(0);
                LandingFragment.this.mLogoContainer.startAnimation(AnimationUtils.loadAnimation(baseActivity, R.anim.alpha_translate_animation));
                landingServiceFragment.getLoggedOutExperiments();
            }
        });
    }

    public void handleLoggedOutExperimentLoadSuccess(Map<String, String> map, boolean z, WishReloginInfo wishReloginInfo, LoggedOutCountdownCoupon loggedOutCountdownCoupon, WishSignupVideoPopupSpec wishSignupVideoPopupSpec) {
        this.mExperiments = map;
        this.mAlreadyHasApp = z;
        this.mSignupGiftCoupon = loggedOutCountdownCoupon;
        ExperimentDataCenter.getInstance().initializeData(map);
        if (ExperimentDataCenter.getInstance().shouldShowLastLoggedInForReinstall() && wishReloginInfo != null) {
            PreferenceUtil.setString("login_mode", wishReloginInfo.getLoginMode());
            PreferenceUtil.setString("LoggedInUser", wishReloginInfo.getUserId());
            PreferenceUtil.setString("LoggedInUserName", wishReloginInfo.getUserName());
            PreferenceUtil.setString("LoggedInUserImage", wishReloginInfo.getProfilePicture());
            PreferenceUtil.setBoolean("LoggedInUserDeleted", wishReloginInfo.getIsDeleted());
            PreferenceUtil.setString("user_login_email", wishReloginInfo.getEmail());
        }
        String string = PreferenceUtil.getString("LoggedInUserName");
        boolean z2 = PreferenceUtil.getBoolean("LoggedInUserDeleted");
        boolean z3 = PreferenceUtil.getBoolean("HasSeenLogin");
        if (!TextUtils.isEmpty(string) && !z2) {
            if (!z3 && ExperimentDataCenter.getInstance().shouldShowLastLoggedInForReinstall() && wishReloginInfo != null) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_REINSTALLED_RELOGIN_SCREEN);
            }
            goToCreateAccount();
        } else if (z) {
            goToSignIn();
            if (wishReloginInfo != null && !PreferenceUtil.getBoolean("HasSeenLogin")) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_REINSTALLED_NO_REDIRECT);
            }
        } else {
            goToCreateAccount(wishSignupVideoPopupSpec);
        }
        if (!z3) {
            PreferenceUtil.setBoolean("HasSeenLogin", true);
        }
    }

    public void handleLoggedOutExperimentLoadFailure() {
        goToCreateAccount();
    }

    private void goToSignIn() {
        withActivity(new ActivityTask<LandingActivity>() {
            public void performTask(LandingActivity landingActivity) {
                Intent intent = new Intent();
                intent.setClass(landingActivity, SignInActivity.class);
                IntentUtil.putParcelableExtra(intent, "ExtraPreLoginIntent", landingActivity.getPreLoginIntent());
                landingActivity.startActivity(intent, true);
            }
        });
    }

    private void goToCreateAccount() {
        goToCreateAccount(null);
    }

    private void goToCreateAccount(final WishSignupVideoPopupSpec wishSignupVideoPopupSpec) {
        withActivity(new ActivityTask<LandingActivity>() {
            public void performTask(LandingActivity landingActivity) {
                Intent intent = new Intent();
                intent.setClass(landingActivity, CreateAccountActivity.class);
                IntentUtil.putParcelableExtra(intent, "ExtraPreLoginIntent", landingActivity.getPreLoginIntent());
                IntentUtil.putParcelableExtra(intent, "ExtraSignupTimedGift", LandingFragment.this.mSignupGiftCoupon);
                if (wishSignupVideoPopupSpec != null) {
                    IntentUtil.putParcelableExtra(intent, "ExtraSignupPopup", wishSignupVideoPopupSpec);
                }
                landingActivity.startActivity(intent, true);
            }
        });
    }
}
