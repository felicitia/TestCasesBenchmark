package com.etsy.android.ui.core.listingpanel;

import io.reactivex.functions.Consumer;

final /* synthetic */ class t implements Consumer {
    private final q a;

    t(q qVar) {
        this.a = qVar;
    }

    public void accept(Object obj) {
        this.a.b((Throwable) obj);
    }
}
