package com.etsy.android.ui.cart.a;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.cart.CartReceipt;
import com.etsy.android.lib.models.interfaces.BasicListingLike;
import com.etsy.android.lib.models.interfaces.ShopShareable;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.view.IconButton;
import com.etsy.android.vespa.b;

/* compiled from: CartReceiptClickHandler */
public class c extends b<CartReceipt> {
    public void a(CartReceipt cartReceipt) {
    }

    public c(@NonNull FragmentActivity fragmentActivity, @NonNull com.etsy.android.lib.logger.b bVar) {
        super(fragmentActivity, bVar);
    }

    public void a(@NonNull View view, @NonNull CartReceipt cartReceipt) {
        e.a(d()).a().d(cartReceipt.getReceiptId());
    }

    public void b(@NonNull View view, @NonNull CartReceipt cartReceipt) {
        Bundle bundle = new Bundle();
        bundle.putString(ResponseConstants.USERNAME, cartReceipt.getLoginName());
        e.a((Activity) d()).e(bundle);
    }

    public void c(@NonNull View view, @NonNull final CartReceipt cartReceipt) {
        boolean isFavorite = cartReceipt.getIsFavorite();
        if (view instanceof IconButton) {
            final IconButton iconButton = (IconButton) view;
            iconButton.setShowAlt(!isFavorite);
            new com.etsy.android.ui.util.e(d(), view, c()).a(cartReceipt.getShopUserId(), (com.etsy.android.ui.util.e.b) new com.etsy.android.ui.util.e.b() {
                public void a() {
                    cartReceipt.setFavorite(true);
                    if (iconButton != null) {
                        iconButton.setShowAlt(true);
                    }
                }

                public void b() {
                    cartReceipt.setFavorite(false);
                    if (iconButton != null) {
                        iconButton.setShowAlt(false);
                    }
                }
            }, isFavorite);
        }
    }

    public void d(View view, CartReceipt cartReceipt) {
        e.a(d()).a().a((BasicListingLike) cartReceipt.getListingSocialShare());
    }

    public void e(View view, CartReceipt cartReceipt) {
        e.a(d()).a().a((ShopShareable) cartReceipt.getShopSocialShare());
    }
}
