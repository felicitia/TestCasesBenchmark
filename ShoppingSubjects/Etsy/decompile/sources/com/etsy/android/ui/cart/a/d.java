package com.etsy.android.ui.cart.a;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.models.apiv3.cart.CheckoutSection;

/* compiled from: CheckoutClickHandler */
public class d extends a {
    public d(@NonNull com.etsy.android.ui.cart.d dVar, @NonNull FragmentActivity fragmentActivity, @NonNull b bVar) {
        super(dVar, fragmentActivity, bVar);
    }

    public void a(@NonNull CheckoutSection checkoutSection) {
        this.a.proceedToCheckout(checkoutSection.getCartGroupId(), checkoutSection.getButtonType());
    }
}
