package com.etsy.android.ui.cardview.clickhandlers;

import com.etsy.android.lib.models.apiv3.cart.CartPage;
import com.etsy.android.lib.models.apiv3.cart.SavedCart;
import io.reactivex.functions.Consumer;

final /* synthetic */ class k implements Consumer {
    private final SavedCartClickHandler a;
    private final SavedCart b;
    private final String c;
    private final int d;

    k(SavedCartClickHandler savedCartClickHandler, SavedCart savedCart, String str, int i) {
        this.a = savedCartClickHandler;
        this.b = savedCart;
        this.c = str;
        this.d = i;
    }

    public void accept(Object obj) {
        this.a.a(this.b, this.c, this.d, (CartPage) obj);
    }
}
