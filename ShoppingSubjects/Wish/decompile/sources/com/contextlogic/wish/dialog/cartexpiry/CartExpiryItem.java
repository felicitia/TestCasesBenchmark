package com.contextlogic.wish.dialog.cartexpiry;

import android.os.Parcelable;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishPriceExpiryInfo;

public interface CartExpiryItem extends Parcelable {
    WishImage getImage();

    WishPriceExpiryInfo getPriceExpiryInfo();
}
