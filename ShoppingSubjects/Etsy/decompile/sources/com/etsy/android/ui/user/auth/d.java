package com.etsy.android.ui.user.auth;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final /* synthetic */ class d implements OnFocusChangeListener {
    private final RegisterFragment a;

    d(RegisterFragment registerFragment) {
        this.a = registerFragment;
    }

    public void onFocusChange(View view, boolean z) {
        this.a.lambda$setupUsernameSuggestionListeners$1$RegisterFragment(view, z);
    }
}
