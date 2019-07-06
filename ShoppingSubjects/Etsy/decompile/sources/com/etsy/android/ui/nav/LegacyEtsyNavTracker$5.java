package com.etsy.android.ui.nav;

import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

class LegacyEtsyNavTracker$5 extends HashMap<AnalyticsLogAttribute, Object> {
    final /* synthetic */ d this$0;
    final /* synthetic */ EtsyId val$listingId;

    LegacyEtsyNavTracker$5(d dVar, EtsyId etsyId) {
        this.this$0 = dVar;
        this.val$listingId = etsyId;
        put(AnalyticsLogAttribute.LISTING_ID, this.val$listingId);
    }
}
