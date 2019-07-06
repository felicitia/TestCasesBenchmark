package com.etsy.android.ui.nav;

import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

class LegacyEtsyNavTracker$6 extends HashMap<AnalyticsLogAttribute, Object> {
    final /* synthetic */ d this$0;
    final /* synthetic */ EtsyId val$shopId;

    LegacyEtsyNavTracker$6(d dVar, EtsyId etsyId) {
        this.this$0 = dVar;
        this.val$shopId = etsyId;
        put(AnalyticsLogAttribute.SHOP_ID, this.val$shopId);
    }
}
