package com.etsy.android.vespa.a;

import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.apiv3.Button;
import kotlin.jvm.internal.p;

/* compiled from: ButtonClickHandler.kt */
public abstract class b extends com.etsy.android.vespa.b<Button> {
    public b(FragmentActivity fragmentActivity, com.etsy.android.lib.logger.b bVar) {
        p.b(fragmentActivity, "fragmentActivity");
        p.b(bVar, "analyticsTracker");
        super(fragmentActivity, bVar);
    }
}
