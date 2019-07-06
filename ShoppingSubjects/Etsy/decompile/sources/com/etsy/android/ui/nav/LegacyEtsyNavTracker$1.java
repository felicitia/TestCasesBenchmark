package com.etsy.android.ui.nav;

import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import java.util.HashMap;

class LegacyEtsyNavTracker$1 extends HashMap<AnalyticsLogAttribute, Object> {
    final /* synthetic */ d this$0;
    final /* synthetic */ String val$searchQuery;

    LegacyEtsyNavTracker$1(d dVar, String str) {
        this.this$0 = dVar;
        this.val$searchQuery = str;
        put(AnalyticsLogAttribute.QUERY, this.val$searchQuery);
    }
}
