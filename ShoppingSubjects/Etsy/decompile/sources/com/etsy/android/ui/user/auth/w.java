package com.etsy.android.ui.user.auth;

import com.etsy.android.lib.auth.f.a;
import io.reactivex.functions.Consumer;

final /* synthetic */ class w implements Consumer {
    private final SignInNagFragment a;

    w(SignInNagFragment signInNagFragment) {
        this.a = signInNagFragment;
    }

    public void accept(Object obj) {
        this.a.lambda$onStart$0$SignInNagFragment((a) obj);
    }
}
