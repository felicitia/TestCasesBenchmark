package com.etsy.android.ui.cardview.clickhandlers;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.homescreen.CategoryRecommendationCard;
import com.etsy.android.ui.nav.e;
import com.etsy.android.vespa.b;

/* compiled from: CategoryRecCardClickHandler */
public class c extends b<CategoryRecommendationCard> {
    public c(FragmentActivity fragmentActivity, @NonNull com.etsy.android.lib.logger.b bVar) {
        super(fragmentActivity, bVar);
    }

    public void a(CategoryRecommendationCard categoryRecommendationCard) {
        b(categoryRecommendationCard);
    }

    public void b(CategoryRecommendationCard categoryRecommendationCard) {
        if (categoryRecommendationCard.getListingLink() != null) {
            e.a(d()).a().a(categoryRecommendationCard.getListingLink());
        }
    }
}
