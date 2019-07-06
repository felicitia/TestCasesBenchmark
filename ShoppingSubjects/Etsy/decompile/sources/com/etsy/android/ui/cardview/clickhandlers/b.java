package com.etsy.android.ui.cardview.clickhandlers;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.models.apiv3.TaxonomyCategory;
import com.etsy.android.ui.nav.e;
import java.util.HashMap;

/* compiled from: CategoryCardClickHandler */
public class b extends com.etsy.android.vespa.b<TaxonomyCategory> {
    public b(FragmentActivity fragmentActivity, @NonNull com.etsy.android.lib.logger.b bVar) {
        super(fragmentActivity, bVar);
    }

    public void a(TaxonomyCategory taxonomyCategory) {
        b(taxonomyCategory);
    }

    private void b(TaxonomyCategory taxonomyCategory) {
        TaxonomyNode buildTaxonomyNode = taxonomyCategory.buildTaxonomyNode();
        if (buildTaxonomyNode != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(AnalyticsLogAttribute.TAXONOMY_ID, buildTaxonomyNode.getTaxonomyNodeId());
            com.etsy.android.lib.logger.b c = c();
            StringBuilder sb = new StringBuilder();
            sb.append(c().b());
            sb.append("_tapped_category");
            c.a(sb.toString(), hashMap);
            e.a(d()).a().a(buildTaxonomyNode, (String) null);
        }
    }
}
