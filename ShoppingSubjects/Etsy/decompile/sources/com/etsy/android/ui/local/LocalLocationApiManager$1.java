package com.etsy.android.ui.local;

import android.location.Location;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import java.util.HashMap;

class LocalLocationApiManager$1 extends HashMap<AnalyticsLogAttribute, Object> {
    final /* synthetic */ d this$0;
    final /* synthetic */ Location val$location;

    LocalLocationApiManager$1(d dVar, Location location) {
        this.this$0 = dVar;
        this.val$location = location;
        put(AnalyticsLogAttribute.LAT, Double.valueOf(this.val$location.getLatitude()));
        put(AnalyticsLogAttribute.LON, Double.valueOf(this.val$location.getLongitude()));
    }
}
