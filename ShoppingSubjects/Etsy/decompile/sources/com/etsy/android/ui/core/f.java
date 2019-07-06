package com.etsy.android.ui.core;

import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.apiv3.cart.SingleListingCart;

final /* synthetic */ class f implements c {
    private final ListingFragment a;
    private final Listing b;

    f(ListingFragment listingFragment, Listing listing) {
        this.a = listingFragment;
        this.b = listing;
    }

    public void a(SingleListingCart singleListingCart) {
        this.a.lambda$setUpAndroidPayButton$1$ListingFragment(this.b, singleListingCart);
    }
}
