package com.contextlogic.wish.activity.login;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.social.google.GoogleManager;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class SignInSignUpSocialButtonsLayout extends LinearLayout {
    private ThemedTextView mFacebookButton;
    private ThemedTextView mGoogleButton;
    private View mSpacer;
    private int mSpacerSize;

    public SignInSignUpSocialButtonsLayout(Context context) {
        this(context, null);
    }

    public SignInSignUpSocialButtonsLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignInSignUpSocialButtonsLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.signin_signup_social_buttons_layout, this, true);
        this.mFacebookButton = (ThemedTextView) findViewById(R.id.signin_signup_social_button_facebook);
        this.mGoogleButton = (ThemedTextView) findViewById(R.id.signin_signup_social_button_google);
        this.mSpacer = findViewById(R.id.signin_signup_social_spacer);
        this.mSpacerSize = getContext().getResources().getDimensionPixelSize(R.dimen.eight_padding);
        if (!isInEditMode() && !GoogleManager.getInstance().isPlayServicesAvailable()) {
            this.mGoogleButton.setVisibility(8);
            this.mSpacer.setVisibility(8);
        }
        handleOrientationChanged(getOrientation());
    }

    public void setOrientation(int i) {
        super.setOrientation(i);
        handleOrientationChanged(i);
    }

    private void handleOrientationChanged(int i) {
        if (this.mFacebookButton != null && this.mGoogleButton != null && this.mSpacer != null && this.mFacebookButton.getLayoutParams() != null && this.mGoogleButton.getLayoutParams() != null && this.mSpacer.getLayoutParams() != null) {
            LayoutParams layoutParams = (LayoutParams) this.mFacebookButton.getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) this.mGoogleButton.getLayoutParams();
            LayoutParams layoutParams3 = (LayoutParams) this.mSpacer.getLayoutParams();
            if (i == 0) {
                this.mFacebookButton.setText(R.string.facebook);
                layoutParams.weight = this.mGoogleButton.getVisibility() == 0 ? 0.5f : 1.0f;
                layoutParams.width = 0;
                this.mGoogleButton.setText(R.string.google);
                layoutParams2.weight = 1.0f - layoutParams.weight;
                layoutParams2.width = 0;
                layoutParams3.width = this.mSpacerSize;
                layoutParams3.height = -1;
            } else {
                this.mFacebookButton.setText(R.string.continue_with_facebook);
                layoutParams.weight = 0.0f;
                layoutParams.width = -1;
                this.mGoogleButton.setText(R.string.continue_with_google);
                layoutParams2.weight = 0.0f;
                layoutParams2.width = -1;
                layoutParams3.width = -1;
                layoutParams3.height = this.mSpacerSize;
            }
            this.mFacebookButton.setLayoutParams(layoutParams);
            this.mGoogleButton.setLayoutParams(layoutParams2);
            this.mSpacer.setLayoutParams(layoutParams3);
        }
    }

    public void setFacebookClickListener(OnClickListener onClickListener) {
        if (this.mFacebookButton != null) {
            this.mFacebookButton.setOnClickListener(onClickListener);
        }
    }

    public void setGoogleClickListener(OnClickListener onClickListener) {
        if (this.mGoogleButton != null) {
            this.mGoogleButton.setOnClickListener(onClickListener);
        }
    }
}
