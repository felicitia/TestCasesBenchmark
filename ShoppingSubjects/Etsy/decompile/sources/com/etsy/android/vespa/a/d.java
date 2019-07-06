package com.etsy.android.vespa.a;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.messaging.c;
import com.etsy.android.lib.models.cardviewelement.PageDeepLink;
import com.etsy.android.lib.models.cardviewelement.PageLink;
import com.etsy.android.vespa.b;
import java.util.HashMap;

/* compiled from: ListSectionFooterLinkClickHandler */
public class d extends b<PageLink> {
    public d(FragmentActivity fragmentActivity, @NonNull com.etsy.android.lib.logger.b bVar) {
        super(fragmentActivity, bVar);
    }

    public void a(PageLink pageLink) {
        if (pageLink instanceof PageDeepLink) {
            String url = ((PageDeepLink) pageLink).getUrl();
            HashMap hashMap = new HashMap();
            hashMap.put(AnalyticsLogAttribute.URL, url);
            com.etsy.android.lib.logger.b c = c();
            StringBuilder sb = new StringBuilder();
            sb.append(c().b());
            sb.append("_tapped_view_all");
            c.a(sb.toString(), hashMap);
            Intent intent = new Intent("android.intent.action.VIEW");
            if (c.a(Uri.parse(url)) != null) {
                intent.setClass(d(), EtsyApplication.get().getDeepLinkRoutingActivity());
            }
            intent.setData(Uri.parse(url));
            d().startActivity(intent);
        }
    }
}
