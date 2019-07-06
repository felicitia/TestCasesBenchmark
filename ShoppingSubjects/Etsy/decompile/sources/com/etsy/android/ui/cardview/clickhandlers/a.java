package com.etsy.android.ui.cardview.clickhandlers;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.logger.s;
import com.etsy.android.lib.models.cardviewelement.PageLink;
import com.etsy.android.lib.models.homescreen.LandingPageInfo;
import com.etsy.android.lib.models.homescreen.LandingPageLink;
import com.etsy.android.ui.nav.e;
import com.etsy.android.vespa.a.d;
import java.util.HashMap;

/* compiled from: BOEListSectionFooterLinkClickHandler */
public class a extends d {
    public a(FragmentActivity fragmentActivity, @NonNull b bVar) {
        super(fragmentActivity, bVar);
    }

    public void a(PageLink pageLink) {
        if (pageLink instanceof LandingPageLink) {
            a((LandingPageLink) pageLink);
        } else {
            super.a(pageLink);
        }
    }

    public void a(LandingPageLink landingPageLink) {
        HashMap hashMap = new HashMap();
        hashMap.put(AnalyticsLogAttribute.PAGE, landingPageLink.getEventName());
        b c = c();
        StringBuilder sb = new StringBuilder();
        sb.append(c().b());
        sb.append("_tapped_view_all");
        c.a(sb.toString(), hashMap);
        if (landingPageLink.getPageType().equals(LandingPageLink.PAGE_TYPE_ORLOJ_RECENTLY_VIEWED_LISTINGS)) {
            e.a(d()).a().b((LandingPageInfo) landingPageLink);
        } else if (landingPageLink.getPageType().equals(LandingPageLink.PAGE_TYPE_RECENTLY_VIEWED_LISTINGS)) {
            landingPageLink.setRequestMethod(1);
            e.a(d()).a().b((LandingPageInfo) landingPageLink);
        } else if (landingPageLink.getPageType().equals("listings") || landingPageLink.getPageType().equals("shops")) {
            e.a(d()).a().a((LandingPageInfo) landingPageLink);
        } else if (landingPageLink.getPageType().equals("shopShareCard")) {
            s.a("shop_more_photos.tapped", b());
            e.a(d()).a().c((LandingPageInfo) landingPageLink);
        }
    }
}
