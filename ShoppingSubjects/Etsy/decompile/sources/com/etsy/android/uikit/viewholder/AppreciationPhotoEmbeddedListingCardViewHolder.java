package com.etsy.android.uikit.viewholder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.stylekit.e;
import com.etsy.android.uikit.viewholder.a.a;

public class AppreciationPhotoEmbeddedListingCardViewHolder extends ExtensibleListingCardViewHolder {
    public AppreciationPhotoEmbeddedListingCardViewHolder(@NonNull View view, @Nullable a aVar, @NonNull c cVar, boolean z, boolean z2) {
        super(view, aVar, cVar, z, z2);
    }

    public void toggleAvailability(boolean z) {
        if (!z) {
            e.a(this.mTextPrice, o.sk_typeface_bold);
            this.mTextPrice.setTextColor(this.itemView.getResources().getColor(com.etsy.android.lib.a.e.sk_gray_60));
            this.mTextPrice.setText(o.unavailable);
            this.mFavIcon.setVisibility(8);
            this.mMenuIcon.setVisibility(8);
        }
    }
}
