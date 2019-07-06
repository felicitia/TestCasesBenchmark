package com.etsy.android.ui.finds.cardview.listener;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.finds.FindsUrl;
import com.etsy.android.lib.util.af;
import com.etsy.android.ui.nav.NotificationActivity;
import com.etsy.android.ui.nav.e;
import com.etsy.android.vespa.b;

/* compiled from: FindsUrlClickHandler */
public class a extends b<FindsUrl> {
    public a(FragmentActivity fragmentActivity, @NonNull com.etsy.android.lib.logger.b bVar) {
        super(fragmentActivity, bVar);
    }

    public void a(FindsUrl findsUrl) {
        if (findsUrl != null) {
            TaxonomyNode taxonomyNode = findsUrl.getTaxonomyNode();
            if (!findsUrl.hasCategoryLandingPage() || taxonomyNode == null) {
                e.a((Activity) d()).a(findsUrl);
            } else {
                e.a((Activity) d()).a(taxonomyNode, findsUrl.getAnchorListingId() != null ? findsUrl.getAnchorListingId().getId() : null);
            }
        }
    }

    public void a(String str) {
        if (af.b(str)) {
            FragmentActivity d = d();
            Intent intent = new Intent(d, NotificationActivity.class);
            intent.setData(Uri.parse(str));
            d.startActivity(intent);
        }
    }
}
