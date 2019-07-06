package com.etsy.android.ui.user.auth;

import com.etsy.android.lib.auth.f.a;
import io.reactivex.functions.Consumer;

final /* synthetic */ class o implements Consumer {
    private final SignInFragment a;

    o(SignInFragment signInFragment) {
        this.a = signInFragment;
    }

    public void accept(Object obj) {
        this.a.lambda$onStart$0$SignInFragment((a) obj);
    }
}
