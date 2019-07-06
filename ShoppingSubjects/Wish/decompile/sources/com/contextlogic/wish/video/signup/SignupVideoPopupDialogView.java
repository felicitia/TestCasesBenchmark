package com.contextlogic.wish.video.signup;

import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishSignupVideoPopupSpec;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.video.VideoPopupDialogView;

public class SignupVideoPopupDialogView extends VideoPopupDialogView {
    private View mActionButton;
    private ThemedTextView mButtonText;
    private ThemedTextView mSubtitleText;
    private ThemedTextView mTitleText;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.signup_video_popup_dialog_view;
    }

    public SignupVideoPopupDialogView(BaseDialogFragment baseDialogFragment) {
        super(baseDialogFragment);
    }

    /* access modifiers changed from: protected */
    public View init() {
        View init = super.init();
        this.mActionButton = init.findViewById(R.id.signup_video_dialog_button);
        this.mTitleText = (ThemedTextView) init.findViewById(R.id.signup_video_dialog_title_text);
        this.mSubtitleText = (ThemedTextView) init.findViewById(R.id.signup_video_dialog_subtitle_text);
        this.mButtonText = (ThemedTextView) init.findViewById(R.id.signup_video_dialog_button_text);
        return init;
    }

    public VideoMode getVideoMode() {
        return VideoMode.RAW_VIDEO;
    }

    public void setup(WishSignupVideoPopupSpec wishSignupVideoPopupSpec, int i, boolean z) {
        super.setup(wishSignupVideoPopupSpec, i, z);
        this.mVideoView.hideProgressBar();
        this.mActionButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SIGNUP_VIDEO_BUTTON, SignupVideoPopupDialogView.this.getTimeElapsedLoggingExtras());
                SignupVideoPopupDialogView.this.dismiss();
            }
        });
        if (wishSignupVideoPopupSpec.getTitleText() != null) {
            this.mTitleText.setText(wishSignupVideoPopupSpec.getTitleText());
        } else {
            this.mTitleText.setVisibility(8);
        }
        if (wishSignupVideoPopupSpec.getSubtitleText() != null) {
            this.mSubtitleText.setText(wishSignupVideoPopupSpec.getSubtitleText());
        } else {
            this.mSubtitleText.setVisibility(8);
        }
        if (wishSignupVideoPopupSpec.getButtonText() != null) {
            this.mButtonText.setText(wishSignupVideoPopupSpec.getButtonText());
        } else {
            this.mButtonText.setVisibility(8);
        }
    }
}
