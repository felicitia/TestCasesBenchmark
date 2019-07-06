package com.etsy.android.uikit.viewholder.a;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.vespa.b;

/* compiled from: BaseListingCardClickHandler */
public abstract class a extends b<ListingLike> {
    public abstract void a(ListingLike listingLike);

    public abstract void a(ListingLike listingLike, ImageView imageView, int i);

    public abstract void b(ListingLike listingLike);

    public abstract void d(ListingLike listingLike);

    public a(FragmentActivity fragmentActivity, @NonNull com.etsy.android.lib.logger.b bVar) {
        super(fragmentActivity, bVar);
    }
}
