package com.etsy.android.ui.nav;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import java.util.HashMap;

class LegacyEtsyNavTracker$3 extends HashMap<String, Object> {
    final /* synthetic */ d this$0;
    final /* synthetic */ AndroidPayDataContract val$data;
    final /* synthetic */ StringBuilder val$listingIdsBuilder;
    final /* synthetic */ String val$paymentMethodType;

    LegacyEtsyNavTracker$3(d dVar, AndroidPayDataContract androidPayDataContract, StringBuilder sb, String str) {
        this.this$0 = dVar;
        this.val$data = androidPayDataContract;
        this.val$listingIdsBuilder = sb;
        this.val$paymentMethodType = str;
        put("cart_id", Integer.valueOf(this.val$data.getCartId()));
        put(ResponseConstants.TOTAL, this.val$data.getTotal());
        put(ResponseConstants.LISTING_IDS, this.val$listingIdsBuilder.toString());
        put("payment_method", this.val$paymentMethodType);
        put("is_giftcard_cart", Boolean.valueOf(false));
    }
}
