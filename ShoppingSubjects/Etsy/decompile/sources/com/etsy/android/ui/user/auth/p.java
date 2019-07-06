package com.etsy.android.ui.user.auth;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class p implements OnEditorActionListener {
    private final SignInFragment a;

    p(SignInFragment signInFragment) {
        this.a = signInFragment;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return this.a.lambda$onCreateView$1$SignInFragment(textView, i, keyEvent);
    }
}
