package com.etsy.android.ui.nav;

import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

class EtsyActivityNavigator$2 extends HashMap<AnalyticsLogAttribute, Object> {
    final /* synthetic */ b this$0;
    final /* synthetic */ EtsyId val$shopId;

    EtsyActivityNavigator$2(b bVar, EtsyId etsyId) {
        this.this$0 = bVar;
        this.val$shopId = etsyId;
        put(AnalyticsLogAttribute.SHOP_ID, this.val$shopId);
    }
}
