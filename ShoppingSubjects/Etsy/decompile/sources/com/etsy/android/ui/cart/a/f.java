package com.etsy.android.ui.cart.a;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.b;
import com.etsy.android.ui.cart.d;
import com.etsy.android.ui.core.listingpanel.EstimatedDeliveryDateLegaleseDialogFragment;
import com.etsy.android.ui.nav.e;

/* compiled from: ShippingDetailsClickHandler */
public class f extends a {
    public f(@NonNull d dVar, FragmentActivity fragmentActivity, @NonNull b bVar) {
        super(dVar, fragmentActivity, bVar);
    }

    public void a(@NonNull String str) {
        e.a(d()).a().a(str, new Bundle());
    }

    public void a() {
        new EstimatedDeliveryDateLegaleseDialogFragment().show(d().getSupportFragmentManager(), "Bottom sheet dialog");
    }
}
