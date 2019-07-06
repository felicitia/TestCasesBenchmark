package com.etsy.android.ui.cardview.clickhandlers;

import com.etsy.android.lib.models.apiv3.cart.SavedCart;
import io.reactivex.functions.Consumer;

final /* synthetic */ class l implements Consumer {
    private final SavedCartClickHandler a;
    private final Action b;
    private final SavedCart c;
    private final int d;

    l(SavedCartClickHandler savedCartClickHandler, Action action, SavedCart savedCart, int i) {
        this.a = savedCartClickHandler;
        this.b = action;
        this.c = savedCart;
        this.d = i;
    }

    public void accept(Object obj) {
        this.a.a(this.b, this.c, this.d, (Throwable) obj);
    }
}
