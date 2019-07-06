package com.etsy.android.ui.adapters;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.ui.adapters.a.C0086a;
import com.etsy.android.uikit.util.TrackingOnClickListener;

/* compiled from: ListingRowGenerator */
public class b extends a<Listing> {
    /* access modifiers changed from: private */
    public a m;

    /* compiled from: ListingRowGenerator */
    public interface a {
        void a(Listing listing);
    }

    public b(FragmentActivity fragmentActivity, c cVar, int i) {
        super(fragmentActivity, cVar, i);
    }

    public b(FragmentActivity fragmentActivity, c cVar, int i, int i2) {
        super(fragmentActivity, cVar, i, i2);
    }

    public void a(a aVar) {
        this.m = aVar;
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2, C0086a<Listing> aVar, final Listing listing) {
        aVar.f.setText(listing.getTitle());
        if (aVar.g != null) {
            if (listing.isSoldOut()) {
                aVar.g.setText(R.string.sold_out);
            } else {
                aVar.g.setText(listing.getPrice().formatWithConditionalCurrencyCode());
            }
        }
        ListingImage image = listing.getImage();
        String str = null;
        if (image != null) {
            str = image.get4to3ImageUrlForPixelWidth(i);
        }
        String str2 = str;
        if (image == null || !image.hasImageColor()) {
            j().a(str2, aVar.c, i, i2);
        } else {
            j().a(str2, aVar.c, i, i2, image.getImageColor());
        }
        aVar.b.setOnClickListener(new TrackingOnClickListener(new i[]{listing}) {
            public void onViewClick(View view) {
                if (b.this.m != null) {
                    b.this.m.a(listing);
                }
            }
        });
        if (!(aVar.h == null || listing.getShop() == null)) {
            aVar.h.setText(listing.getShop().getShopName());
        }
        aVar.b.setBackgroundResource(this.a);
        aVar.b.setVisibility(0);
    }
}
