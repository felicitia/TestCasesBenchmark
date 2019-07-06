package com.etsy.android.ui.cart.viewholders;

import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.GiftCardInfo;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.CartListing;
import com.etsy.android.ui.cart.a.b;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ListingFullImageView;

public class CartListingGiftCardViewHolder extends BaseCartGroupItemViewHolder {
    private final Button mBtnRemove = ((Button) findViewById(R.id.btn_remove));
    /* access modifiers changed from: private */
    public b mClickHandler;
    private final TextView mEmail = ((TextView) findViewById(R.id.txt_email));
    private final TextView mFrom = ((TextView) findViewById(R.id.txt_from));
    private final ListingFullImageView mImage = ((ListingFullImageView) findViewById(R.id.image_listing));
    private c mImageBatch;
    private final TextView mMessage = ((TextView) findViewById(R.id.txt_message));
    private final TextView mRecipient = ((TextView) findViewById(R.id.txt_recipient));
    private final TextView mTitle = ((TextView) findViewById(R.id.txt_listing_title));
    private final TextView mTotalPrice = ((TextView) findViewById(R.id.txt_total_price));

    public CartListingGiftCardViewHolder(ViewGroup viewGroup, c cVar, b bVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_cart_listing_gift_card, viewGroup, false));
        this.mImageBatch = cVar;
        this.mClickHandler = bVar;
    }

    public void bindCartGroupItem(final CartGroupItem cartGroupItem) {
        CartListing cartListing = (CartListing) cartGroupItem.getData();
        this.mTitle.setText(cartListing.getTitle());
        IFullImage giftCardDesign = cartListing.getGiftCardInfo().getGiftCardDesign();
        if (giftCardDesign == null) {
            giftCardDesign = cartListing.getListingImage();
        }
        if (giftCardDesign != null) {
            this.mImage.setImageInfo(giftCardDesign, this.mImageBatch);
        }
        this.mTotalPrice.setText(cartListing.getTotalPriceString());
        GiftCardInfo giftCardInfo = cartListing.getGiftCardInfo();
        Resources resources = this.itemView.getResources();
        this.mRecipient.setText(resources.getString(R.string.giftcard_cart_listing_recipient, new Object[]{giftCardInfo.getRecipientName()}));
        this.mFrom.setText(resources.getString(R.string.giftcard_cart_listing_from, new Object[]{giftCardInfo.getSenderName()}));
        String recipientEmail = giftCardInfo.getRecipientEmail();
        if (TextUtils.isEmpty(recipientEmail)) {
            this.mEmail.setVisibility(8);
        } else {
            this.mEmail.setVisibility(0);
            this.mEmail.setText(resources.getString(R.string.giftcard_cart_listing_email, new Object[]{recipientEmail}));
        }
        String message = giftCardInfo.getMessage();
        if (TextUtils.isEmpty(message)) {
            this.mMessage.setVisibility(8);
        } else {
            this.mMessage.setVisibility(0);
            this.mMessage.setText(resources.getString(R.string.giftcard_cart_listing_message, new Object[]{message}));
        }
        if (this.mClickHandler != null) {
            this.mBtnRemove.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    CartListingGiftCardViewHolder.this.mClickHandler.c(CartListingGiftCardViewHolder.this.itemView, cartGroupItem.getAction("remove"));
                }
            });
        }
    }
}
