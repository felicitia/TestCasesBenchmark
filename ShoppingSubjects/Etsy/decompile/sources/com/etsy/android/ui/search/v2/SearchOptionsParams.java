package com.etsy.android.ui.search.v2;

import com.etsy.android.lib.models.ResponseConstants;
import kotlin.jvm.internal.p;

/* compiled from: SearchOptions.kt */
enum SearchOptionsParams {
    MAX_PRICE_LEGACY(ResponseConstants.MAX_PRICE),
    MAX_PRICE(ResponseConstants.MAX),
    MIN_PRICE_LEGACY(ResponseConstants.MIN_PRICE),
    MIN_PRICE(ResponseConstants.MIN),
    LOCATION(ResponseConstants.LOCATION),
    SHIP_TO("ship_to"),
    MARKETPLACE("marketplace"),
    FREE_SHIPPING("free_shipping"),
    MAX_PROCESSING_DAYS("max_processing_days"),
    IS_DISCOUNTED("is_discounted"),
    CUSTOMIZABLE("customizable"),
    GIFT_WRAP("gift_wrap");
    
    private final String searchParam;

    protected SearchOptionsParams(String str) {
        p.b(str, "searchParam");
        this.searchParam = str;
    }

    public String toString() {
        return this.searchParam;
    }
}
