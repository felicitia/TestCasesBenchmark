package com.etsy.android.ui.nav;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

class LegacyEtsyNavTracker$4 extends HashMap<String, Object> {
    final /* synthetic */ d this$0;
    final /* synthetic */ EtsyId val$marketId;

    LegacyEtsyNavTracker$4(d dVar, EtsyId etsyId) {
        this.this$0 = dVar;
        this.val$marketId = etsyId;
        put(ResponseConstants.LOCAL_MARKET_ID, this.val$marketId.toString());
    }
}
