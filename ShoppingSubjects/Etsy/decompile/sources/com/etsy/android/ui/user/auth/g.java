package com.etsy.android.ui.user.auth;

import io.reactivex.functions.Consumer;

final /* synthetic */ class g implements Consumer {
    private final RegisterFragment a;

    g(RegisterFragment registerFragment) {
        this.a = registerFragment;
    }

    public void accept(Object obj) {
        this.a.lambda$fetchUsernameSuggestions$3$RegisterFragment((Throwable) obj);
    }
}
