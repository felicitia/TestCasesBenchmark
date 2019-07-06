package com.etsy.android.vespa.a;

import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.apiv3.Segment;
import com.etsy.android.vespa.b;
import kotlin.jvm.internal.p;

/* compiled from: DeepLinkSegmentListClickHandler.kt */
public abstract class c extends b<Segment> {
    public c(FragmentActivity fragmentActivity, com.etsy.android.lib.logger.b bVar) {
        p.b(fragmentActivity, "fragmentActivity");
        p.b(bVar, "analyticsTracker");
        super(fragmentActivity, bVar);
    }
}
