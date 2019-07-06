package com.etsy.android.ui.cardview.clickhandlers;

import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.apiv3.ExploreBanner;
import com.etsy.android.ui.nav.e;
import com.etsy.android.vespa.b;
import kotlin.jvm.internal.p;

/* compiled from: ExploreBannerClickHandler.kt */
public final class d extends b<ExploreBanner> {
    public d(FragmentActivity fragmentActivity, com.etsy.android.lib.logger.b bVar) {
        p.b(fragmentActivity, "fragmentActivity");
        p.b(bVar, "analyticsTracker");
        super(fragmentActivity, bVar);
    }

    public void a(ExploreBanner exploreBanner) {
        p.b(exploreBanner, "data");
        e.a(d()).a().j(exploreBanner.getDeepLinkUrl());
    }
}
