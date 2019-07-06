package com.contextlogic.wish.dialog.bottomsheet;

import android.support.design.widget.BottomSheetDialog;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.login.SignInSignUpSocialButtonsLayout;

public class SignUpSocialButtonsBottomSheet extends BottomSheetDialog {
    private SignInSignUpSocialButtonsLayout mSocialButtonsLayout;

    private SignUpSocialButtonsBottomSheet(BaseActivity baseActivity) {
        super(baseActivity);
        init();
    }

    private void init() {
        setContentView((int) R.layout.signup_social_buttons_bottom_sheet);
        this.mSocialButtonsLayout = (SignInSignUpSocialButtonsLayout) findViewById(R.id.signup_social_bottom_sheet_buttons_layout);
    }

    public static SignUpSocialButtonsBottomSheet create(BaseActivity baseActivity) {
        SignUpSocialButtonsBottomSheet signUpSocialButtonsBottomSheet = new SignUpSocialButtonsBottomSheet(baseActivity);
        BottomSheetUtil.expandDialogFully(signUpSocialButtonsBottomSheet);
        return signUpSocialButtonsBottomSheet;
    }

    public SignUpSocialButtonsBottomSheet setFacebookClickListener(OnClickListener onClickListener) {
        this.mSocialButtonsLayout.setFacebookClickListener(onClickListener);
        return this;
    }

    public SignUpSocialButtonsBottomSheet setGoogleClickListener(OnClickListener onClickListener) {
        this.mSocialButtonsLayout.setGoogleClickListener(onClickListener);
        return this;
    }
}
