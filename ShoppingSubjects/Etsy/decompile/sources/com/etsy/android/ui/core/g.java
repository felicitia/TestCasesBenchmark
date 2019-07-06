package com.etsy.android.ui.core;

import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.apiv3.cart.SingleListingCart;

final /* synthetic */ class g implements c {
    private final AnonymousClass8 a;
    private final Listing b;

    g(AnonymousClass8 r1, Listing listing) {
        this.a = r1;
        this.b = listing;
    }

    public void a(SingleListingCart singleListingCart) {
        this.a.a(this.b, singleListingCart);
    }
}
