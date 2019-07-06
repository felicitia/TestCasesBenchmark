package com.klarna.checkout.internal;

import android.webkit.WebView;

public final class c extends WebView {
    private final a a;

    public c(a aVar) {
        super(aVar.j);
        this.a = aVar;
    }

    /* access modifiers changed from: protected */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a.a();
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.a.b();
    }

    public final void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0) {
            this.a.a();
        } else {
            this.a.b();
        }
    }
}
