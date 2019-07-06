package com.etsy.android.ui.nav;

import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.messaging.EtsyAction;
import java.util.HashMap;

class EtsyActivityNavigator$1 extends HashMap<AnalyticsLogAttribute, Object> {
    final /* synthetic */ b this$0;
    final /* synthetic */ EtsyAction val$action;

    EtsyActivityNavigator$1(b bVar, EtsyAction etsyAction) {
        this.this$0 = bVar;
        this.val$action = etsyAction;
        put(AnalyticsLogAttribute.REFERRER, this.val$action.getName());
    }
}
