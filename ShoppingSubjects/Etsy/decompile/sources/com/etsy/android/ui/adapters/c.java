package com.etsy.android.ui.adapters;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.adapters.a.C0086a;
import com.etsy.android.uikit.util.TrackingOnClickListener;

/* compiled from: SimilarItemsRowGenerator */
public class c extends a<ListingCard> {
    /* access modifiers changed from: private */
    public a m;
    private String n;

    /* compiled from: SimilarItemsRowGenerator */
    public interface a {
        void a(ListingCard listingCard);
    }

    public int d() {
        return 0;
    }

    public c(FragmentActivity fragmentActivity, com.etsy.android.lib.core.img.c cVar) {
        super(fragmentActivity, cVar, R.layout.list_item_listing_similar_item);
        this.n = SharedPreferencesUtility.g(fragmentActivity);
    }

    /* access modifiers changed from: protected */
    public int e() {
        return g().getDimensionPixelSize(R.dimen.fixed_small);
    }

    /* access modifiers changed from: protected */
    public void f() {
        super.f();
        float f = l.b((Activity) h()) ? l.e((Activity) h()) ? 5.67f : 3.67f : 2.67f;
        this.j = (int) (((float) c()) / f);
        this.l = this.j - (this.d * 2);
        this.k = (int) (((float) this.l) * 0.75f);
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2, C0086a<ListingCard> aVar, final ListingCard listingCard) {
        BaseModelImage listingImage = listingCard.getListingImage();
        if (listingImage != null) {
            j().a(listingImage.getImageUrlForPixelWidth(i), aVar.c, i, i2, listingImage.getImageColor());
        }
        aVar.f.setText(listingCard.getTitle());
        if (listingCard.isSoldOut()) {
            aVar.g.setText(R.string.sold_out);
        } else {
            aVar.g.setText(listingCard.getPrice().formatWithConditionalCurrencyCode());
        }
        if (aVar.h != null) {
            aVar.h.setText(listingCard.getShopName());
        }
        aVar.b.setOnClickListener(new TrackingOnClickListener(new i[]{listingCard}) {
            public void onViewClick(View view) {
                if (c.this.m != null) {
                    c.this.m.a(listingCard);
                }
            }
        });
        aVar.b.setVisibility(0);
    }

    public void a(a aVar) {
        this.m = aVar;
    }
}
