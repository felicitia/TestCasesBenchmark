package com.contextlogic.wish.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishSignupVideoPopupSpec;
import com.contextlogic.wish.api.model.WishVideoPopupSpec;
import com.contextlogic.wish.dialog.promotion.SplashPromotionDialogFragment;
import com.contextlogic.wish.video.signup.SignupVideoPopupDialogView;

public class VideoPopupDialogFragment<A extends BaseActivity> extends SplashPromotionDialogFragment<A> {
    private static DialogType[] mEnumValues = DialogType.values();
    private VideoPopupDialogView mDialogView;

    private enum DialogType {
        LoginAction,
        Signup
    }

    private static VideoPopupDialogFragment createDialogHelper(WishVideoPopupSpec wishVideoPopupSpec, DialogType dialogType) {
        VideoPopupDialogFragment videoPopupDialogFragment = new VideoPopupDialogFragment();
        if (videoPopupDialogFragment.setDialogArguments(wishVideoPopupSpec, dialogType)) {
            return videoPopupDialogFragment;
        }
        return null;
    }

    public static VideoPopupDialogFragment createDialog(WishVideoPopupSpec wishVideoPopupSpec) {
        return createDialogHelper(wishVideoPopupSpec, DialogType.LoginAction);
    }

    public static VideoPopupDialogFragment createDialog(WishSignupVideoPopupSpec wishSignupVideoPopupSpec) {
        return createDialogHelper(wishSignupVideoPopupSpec, DialogType.Signup);
    }

    private boolean setDialogArguments(WishVideoPopupSpec wishVideoPopupSpec, DialogType dialogType) {
        Bundle bundle = new Bundle();
        if (wishVideoPopupSpec == null) {
            return false;
        }
        bundle.putParcelable("VideoPopup", wishVideoPopupSpec);
        bundle.putInt("VideoPopupDialogType", dialogType.ordinal());
        setArguments(bundle);
        return true;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return null;
        }
        WishVideoPopupSpec wishVideoPopupSpec = (WishVideoPopupSpec) arguments.getParcelable("VideoPopup");
        if (wishVideoPopupSpec == null || !wishVideoPopupSpec.canDisplay()) {
            return null;
        }
        switch (mEnumValues[arguments.getInt("VideoPopupDialogType")]) {
            case LoginAction:
                LoginActionVideoPopupDialogView loginActionVideoPopupDialogView = new LoginActionVideoPopupDialogView(this);
                loginActionVideoPopupDialogView.setup(wishVideoPopupSpec, 4, true);
                this.mDialogView = loginActionVideoPopupDialogView;
                break;
            case Signup:
                SignupVideoPopupDialogView signupVideoPopupDialogView = new SignupVideoPopupDialogView(this);
                signupVideoPopupDialogView.setup((WishSignupVideoPopupSpec) wishVideoPopupSpec, 4, true);
                this.mDialogView = signupVideoPopupDialogView;
                break;
            default:
                return null;
        }
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_VIDEO_SPLASH, this.mDialogView.getLoggingExtras());
        return this.mDialogView;
    }

    public void releaseImages() {
        super.releaseImages();
        if (this.mDialogView != null) {
            this.mDialogView.releaseImages();
        }
    }
}
