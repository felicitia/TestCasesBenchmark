package com.etsy.android.ui.cart.a;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.etsy.android.lib.models.apiv3.cart.CartListing;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.ui.cart.d;
import com.etsy.android.ui.nav.e;

/* compiled from: CartListingClickHandler */
public class b extends a {
    public b(@NonNull d dVar, @NonNull FragmentActivity fragmentActivity, @NonNull com.etsy.android.lib.logger.b bVar) {
        super(dVar, fragmentActivity, bVar);
    }

    public void a(@NonNull CartListing cartListing) {
        if (!cartListing.isGiftCard()) {
            e.a(d()).a().a(cartListing.getListingId());
        }
    }

    public void b(View view, @NonNull ServerDrivenAction serverDrivenAction) {
        this.a.showVariationSelectDialog(a(view), serverDrivenAction);
    }
}
