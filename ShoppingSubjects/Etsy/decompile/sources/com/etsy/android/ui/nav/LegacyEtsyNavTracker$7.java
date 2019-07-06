package com.etsy.android.ui.nav;

import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

class LegacyEtsyNavTracker$7 extends HashMap<AnalyticsLogAttribute, Object> {
    final /* synthetic */ d this$0;
    final /* synthetic */ EtsyId val$receiptId;

    LegacyEtsyNavTracker$7(d dVar, EtsyId etsyId) {
        this.this$0 = dVar;
        this.val$receiptId = etsyId;
        put(AnalyticsLogAttribute.RECEIPT_ID, this.val$receiptId);
    }
}
