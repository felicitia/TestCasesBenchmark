package com.etsy.android.e;

import com.etsy.android.lib.models.apiv3.cart.CartPage;
import io.reactivex.q;
import retrofit2.b.b;
import retrofit2.b.s;

/* compiled from: SavedCartEndpoint.kt */
public interface a {
    @b(a = "/etsyapps/v3/bespoke/member/carts/saved-for-later/{cart_id}")
    q<CartPage> a(@s(a = "cart_id") String str);

    @b(a = "/etsyapps/v3/bespoke/member/carts/saved-for-later/{cart_id}/to-favorites")
    q<CartPage> b(@s(a = "cart_id") String str);

    @b(a = "/etsyapps/v3/bespoke/member/carts/saved-for-later/{cart_id}/to-cart")
    q<CartPage> c(@s(a = "cart_id") String str);
}
