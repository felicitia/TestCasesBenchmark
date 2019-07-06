package com.etsy.android.ui.cart.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.IFullImage;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.CartReceipt;
import com.etsy.android.stylekit.EtsyButton;
import com.etsy.android.ui.cart.a.c;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.IconButton;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import java.util.List;

public class CartReceiptViewHolder extends BaseViewHolder<CartGroupItem> {
    /* access modifiers changed from: private */
    public final c mClickHandler;
    private final EtsyButton mContactShopButton = ((EtsyButton) findViewById(R.id.btn_contact_shop));
    private final TextView mDescription = ((TextView) findViewById(R.id.txt_description));
    private final IconButton mFavoriteShopButton = ((IconButton) findViewById(R.id.btn_favorite_shop));
    private final com.etsy.android.lib.core.img.c mImageBatch;
    private final ListingFullImageView mListingImage1 = ((ListingFullImageView) findViewById(R.id.listing_image_1));
    private final ListingFullImageView mListingImage2 = ((ListingFullImageView) findViewById(R.id.listing_image_2));
    private final ListingFullImageView mListingImage3 = ((ListingFullImageView) findViewById(R.id.listing_image_3));
    private final IconButton mShareButton = ((IconButton) findViewById(R.id.btn_share));
    private final TextView mSubtitle = ((TextView) findViewById(R.id.txt_subtitle));
    private final TextView mTitle = ((TextView) findViewById(R.id.txt_title));
    private final EtsyButton mViewReceiptButton = ((EtsyButton) findViewById(R.id.btn_view_receipt));

    public CartReceiptViewHolder(ViewGroup viewGroup, com.etsy.android.lib.core.img.c cVar, c cVar2) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_cart_receipt, viewGroup, false));
        this.mImageBatch = cVar;
        this.mClickHandler = cVar2;
    }

    public void bind(CartGroupItem cartGroupItem) {
        final CartReceipt cartReceipt = (CartReceipt) cartGroupItem.getData();
        if (cartReceipt != null) {
            this.mTitle.setText(cartReceipt.getTitle());
            this.mSubtitle.setText(cartReceipt.getSubtitle());
            this.mDescription.setText(cartReceipt.getDescription());
            int i = 8;
            this.mDescription.setVisibility(TextUtils.isEmpty(cartReceipt.getDescription()) ? 8 : 0);
            this.mViewReceiptButton.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    CartReceiptViewHolder.this.mClickHandler.a(view, cartReceipt);
                }
            });
            this.mContactShopButton.setVisibility(cartReceipt.isPrivateShop() ? 8 : 0);
            this.mContactShopButton.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    CartReceiptViewHolder.this.mClickHandler.b(view, cartReceipt);
                }
            });
            this.mFavoriteShopButton.setVisibility(cartReceipt.isPrivateShop() ? 8 : 0);
            this.mFavoriteShopButton.setShowAlt(cartReceipt.getIsFavorite());
            this.mFavoriteShopButton.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    CartReceiptViewHolder.this.mClickHandler.c(view, cartReceipt);
                }
            });
            IconButton iconButton = this.mShareButton;
            if (!(cartReceipt.getListingSocialShare() == null && cartReceipt.getShopSocialShare() == null)) {
                i = 0;
            }
            iconButton.setVisibility(i);
            IconButton iconButton2 = this.mShareButton;
            String str = cartReceipt.getListingSocialShare() != null ? cartReceipt.getListingSocialShare().getBtnText() : cartReceipt.getShopSocialShare() != null ? cartReceipt.getShopSocialShare().getBtnText() : "";
            iconButton2.setText(str);
            this.mShareButton.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    if (cartReceipt.getListingSocialShare() != null) {
                        CartReceiptViewHolder.this.mClickHandler.d(view, cartReceipt);
                    }
                    if (cartReceipt.getShopSocialShare() != null) {
                        CartReceiptViewHolder.this.mClickHandler.e(view, cartReceipt);
                    }
                }
            });
            if (cartReceipt.getListingSocialShare() != null) {
                this.mShareButton.setText(cartReceipt.getListingSocialShare().getBtnText());
            }
            if (cartReceipt.getShopSocialShare() != null) {
                this.mShareButton.setText(cartReceipt.getShopSocialShare().getBtnText());
            }
            this.mListingImage1.setVisibility(4);
            this.mListingImage2.setVisibility(4);
            this.mListingImage3.setVisibility(4);
            List listingImages = cartReceipt.getListingImages();
            if (listingImages.size() > 0) {
                this.mListingImage1.setVisibility(0);
                this.mListingImage1.setImageInfo((IFullImage) listingImages.get(0), this.mImageBatch);
                if (listingImages.size() > 1) {
                    this.mListingImage2.setVisibility(0);
                    this.mListingImage2.setImageInfo((IFullImage) listingImages.get(1), this.mImageBatch);
                    if (listingImages.size() > 2) {
                        this.mListingImage3.setVisibility(0);
                        this.mListingImage3.setImageInfo((IFullImage) listingImages.get(2), this.mImageBatch);
                    }
                }
            }
        }
    }
}
