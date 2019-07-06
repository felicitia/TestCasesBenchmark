package com.contextlogic.wish.activity.signup.SignupFreeGift;

import android.os.Bundle;
import com.contextlogic.wish.api.model.WishSignupFreeGifts;

public abstract class SignupFreeGiftBaseView extends SignupFreeGiftUiView {
    public abstract void setupFreeGifts(WishSignupFreeGifts wishSignupFreeGifts);

    public SignupFreeGiftBaseView(SignupFreeGiftFragment signupFreeGiftFragment, SignupFreeGiftActivity signupFreeGiftActivity, Bundle bundle) {
        super(signupFreeGiftFragment, signupFreeGiftActivity, bundle);
    }
}
