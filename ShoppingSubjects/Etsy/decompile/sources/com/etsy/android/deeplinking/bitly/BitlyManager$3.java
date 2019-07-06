package com.etsy.android.deeplinking.bitly;

import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import java.util.HashMap;

class BitlyManager$3 extends HashMap<AnalyticsLogAttribute, Object> {
    final /* synthetic */ int val$code;
    final /* synthetic */ String val$errorMessage;
    final /* synthetic */ String val$originalBitlink;

    BitlyManager$3(int i, String str, String str2) {
        this.val$code = i;
        this.val$errorMessage = str;
        this.val$originalBitlink = str2;
        put(AnalyticsLogAttribute.CODE, Integer.valueOf(this.val$code));
        put(AnalyticsLogAttribute.ERROR_MESSAGE, this.val$errorMessage);
        put(AnalyticsLogAttribute.ORIGINAL_BITLINK, this.val$originalBitlink);
    }
}
