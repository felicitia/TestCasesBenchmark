package com.etsy.android.ui.util;

import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.ui.promos.VersionPromo;
import java.util.HashMap;

class EtsyPromoUtil$1 extends HashMap<AnalyticsLogAttribute, Object> {
    final /* synthetic */ VersionPromo val$promo;

    EtsyPromoUtil$1(VersionPromo versionPromo) {
        this.val$promo = versionPromo;
        put(AnalyticsLogAttribute.PROMO_VERSION, this.val$promo.getUniqueName());
    }
}
