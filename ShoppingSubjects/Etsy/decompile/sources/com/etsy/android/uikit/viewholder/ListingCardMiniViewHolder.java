package com.etsy.android.uikit.viewholder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.apiv3.ListingCard;
import com.etsy.android.uikit.viewholder.a.a;

public class ListingCardMiniViewHolder extends ListingCardViewHolder {
    public ListingCardMiniViewHolder(@NonNull ViewGroup viewGroup, @Nullable a aVar, @NonNull c cVar, boolean z, boolean z2) {
        super(viewGroup, aVar, cVar, z, z2);
    }

    public void bind(ListingCard listingCard) {
        super.bind(listingCard);
        if (com.etsy.android.lib.config.a.a().d().c(b.bA)) {
            hideAllText();
        } else {
            hideTitleAndShopName();
        }
    }
}
