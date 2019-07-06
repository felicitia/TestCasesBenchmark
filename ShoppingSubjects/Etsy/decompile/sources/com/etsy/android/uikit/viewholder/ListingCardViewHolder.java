package com.etsy.android.uikit.viewholder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.uikit.util.TrackingOnLongClickListener;
import com.etsy.android.uikit.viewholder.a.a;

public class ListingCardViewHolder extends ExtensibleListingCardViewHolder {
    public ListingCardViewHolder(@NonNull ViewGroup viewGroup, @Nullable a aVar, @NonNull c cVar) {
        this(viewGroup, aVar, cVar, false, false);
    }

    public ListingCardViewHolder(@NonNull ViewGroup viewGroup, @Nullable a aVar, @NonNull c cVar, boolean z, boolean z2) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(k.list_item_card_view_listing_clean_actions, viewGroup, false), aVar, cVar, z, z2);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public TrackingOnLongClickListener getOnLongClickListener(final ListingCard listingCard) {
        if (this.mClickHandler != null) {
            return new TrackingOnLongClickListener() {
                public boolean onViewLongClick(View view) {
                    ListingCardViewHolder.this.mClickHandler.d(listingCard);
                    return true;
                }
            };
        }
        return null;
    }
}
