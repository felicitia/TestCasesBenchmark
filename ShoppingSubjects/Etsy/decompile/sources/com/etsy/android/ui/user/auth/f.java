package com.etsy.android.ui.user.auth;

import com.etsy.android.lib.models.SuggestUsernameResult;
import io.reactivex.functions.Consumer;

final /* synthetic */ class f implements Consumer {
    private final RegisterFragment a;

    f(RegisterFragment registerFragment) {
        this.a = registerFragment;
    }

    public void accept(Object obj) {
        this.a.handleSuggestedUsernameResults((SuggestUsernameResult) obj);
    }
}
