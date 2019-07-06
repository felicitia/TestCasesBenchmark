package com.contextlogic.wish.activity.signup.SignupFreeGift.tabbed;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.signup.SignupFreeGift.SignupFreeGiftFragment;
import com.contextlogic.wish.activity.signup.SignupFreeGift.SignupFreeGiftServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishSignupFreeGifts;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class SignupFreeGiftHeaderView extends LinearLayout {
    private ThemedTextView mAppreciationTextView;
    private ThemedTextView mChooseGiftTextView;
    private SignupFreeGiftFragment mFragment;
    private WishSignupFreeGifts mFreeGifts;
    private ThemedTextView mSkip;
    private ThemedTextView mThanksTextView;

    public SignupFreeGiftHeaderView(Context context) {
        super(context);
        init();
    }

    public SignupFreeGiftHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SignupFreeGiftHeaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        View view;
        LayoutInflater from = LayoutInflater.from(getContext());
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            view = from.inflate(R.layout.signup_free_gift_view_header_redesign, this);
        } else {
            view = from.inflate(R.layout.signup_free_gift_view_header, this);
        }
        this.mThanksTextView = (ThemedTextView) view.findViewById(R.id.signup_free_gift_view_header_thanks_textview);
        this.mAppreciationTextView = (ThemedTextView) view.findViewById(R.id.signup_free_gift_view_header_appreciation_textview);
        this.mChooseGiftTextView = (ThemedTextView) view.findViewById(R.id.signup_free_gift_view_header_choose_gift_textview);
        this.mSkip = (ThemedTextView) view.findViewById(R.id.signup_free_gift_view_header_no_thanks_textview);
        if (ExperimentDataCenter.getInstance().shouldSeeFreeGiftBanner()) {
            view.findViewById(R.id.choose_gift_header).setVisibility(0);
        }
    }

    public void setup(SignupFreeGiftFragment signupFreeGiftFragment, WishSignupFreeGifts wishSignupFreeGifts) {
        this.mFragment = signupFreeGiftFragment;
        this.mFreeGifts = wishSignupFreeGifts;
        this.mThanksTextView.setText(wishSignupFreeGifts.getTitle());
        this.mAppreciationTextView.setText(wishSignupFreeGifts.getSubtitle());
        this.mChooseGiftTextView.setText(wishSignupFreeGifts.getMessage());
        this.mSkip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignupFreeGiftHeaderView.this.handleCancel();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleCancel() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_CLAIM_CANCEL_BUTTON);
        if (this.mFreeGifts == null || this.mFreeGifts.getAbandonInfo() == null) {
            this.mFragment.handleClose();
            return;
        }
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_FREE_GIFTS_ABANDONMENT_MODAL);
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                signupFreeGiftServiceFragment.showFreeGiftAbandonDialog(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_ABANDONMENT_MODAL_RETURN, WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_ABANDONMENT_MODAL_PROCEED);
            }
        });
    }
}
