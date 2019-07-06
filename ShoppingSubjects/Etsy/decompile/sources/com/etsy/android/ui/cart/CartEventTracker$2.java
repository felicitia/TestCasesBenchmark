package com.etsy.android.ui.cart;

import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import java.util.HashMap;
import java.util.Map;

class CartEventTracker$2 extends HashMap<AnalyticsLogAttribute, Object> {
    final /* synthetic */ int val$resultCode;

    CartEventTracker$2(Map map, int i) {
        this.val$resultCode = i;
        super(map);
        put(AnalyticsLogAttribute.ACTIVITY_RESULT_CODE, Integer.valueOf(this.val$resultCode));
    }
}
