package com.etsy.android.deeplinking.bitly;

import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import java.util.HashMap;

class BitlyManager$2 extends HashMap<AnalyticsLogAttribute, Object> {
    final /* synthetic */ String val$etsyUrl;

    BitlyManager$2(String str) {
        this.val$etsyUrl = str;
        put(AnalyticsLogAttribute.ETSY_URL, this.val$etsyUrl);
    }
}
