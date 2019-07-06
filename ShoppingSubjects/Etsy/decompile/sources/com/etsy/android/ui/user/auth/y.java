package com.etsy.android.ui.user.auth;

import com.etsy.android.lib.auth.f.a;
import io.reactivex.functions.Consumer;

final /* synthetic */ class y implements Consumer {
    private final SignInTwoFactorFragment a;

    y(SignInTwoFactorFragment signInTwoFactorFragment) {
        this.a = signInTwoFactorFragment;
    }

    public void accept(Object obj) {
        this.a.lambda$onStart$0$SignInTwoFactorFragment((a) obj);
    }
}
