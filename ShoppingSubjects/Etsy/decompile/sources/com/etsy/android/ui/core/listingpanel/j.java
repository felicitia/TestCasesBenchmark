package com.etsy.android.ui.core.listingpanel;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class j implements OnEditorActionListener {
    private final h a;

    j(h hVar) {
        this.a = hVar;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return this.a.a(textView, i, keyEvent);
    }
}
