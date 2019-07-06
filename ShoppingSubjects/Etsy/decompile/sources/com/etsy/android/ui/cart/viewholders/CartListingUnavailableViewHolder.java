package com.etsy.android.ui.cart.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.CartListing;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.ui.cart.a.b;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ListingFullImageView;

public class CartListingUnavailableViewHolder extends BaseCartGroupItemViewHolder {
    private final Button mBtnRemove = ((Button) findViewById(R.id.btn_remove));
    private final Button mBtnSaveForLater = ((Button) findViewById(R.id.btn_save_for_later));
    /* access modifiers changed from: private */
    public b mClickHandler;
    private TextView mDescription = ((TextView) findViewById(R.id.txt_listing_description));
    private final ListingFullImageView mImage = ((ListingFullImageView) findViewById(R.id.image_listing));
    private c mImageBatch;
    private final TextView mTitle = ((TextView) findViewById(R.id.txt_listing_title));

    public CartListingUnavailableViewHolder(ViewGroup viewGroup, c cVar, b bVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_cart_listing_unavailable, viewGroup, false));
        this.mImageBatch = cVar;
        this.mClickHandler = bVar;
        this.mImage.setAlpha(0.5f);
        this.mTitle.setAlpha(0.5f);
    }

    public void bindCartGroupItem(final CartGroupItem cartGroupItem) {
        final CartListing cartListing = (CartListing) cartGroupItem.getData();
        this.mTitle.setText(cartListing.getTitle());
        BaseModelImage listingImage = cartListing.getListingImage();
        if (listingImage != null) {
            this.mImage.setImageInfo(listingImage, this.mImageBatch);
        }
        if (this.mClickHandler != null) {
            this.itemView.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    CartListingUnavailableViewHolder.this.mClickHandler.a(cartListing);
                }
            });
            this.mBtnRemove.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    CartListingUnavailableViewHolder.this.mClickHandler.c(CartListingUnavailableViewHolder.this.itemView, cartGroupItem.getAction("remove"));
                }
            });
            this.mBtnSaveForLater.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    CartListingUnavailableViewHolder.this.mClickHandler.c(CartListingUnavailableViewHolder.this.itemView, cartGroupItem.getAction(ServerDrivenAction.TYPE_SAVE_CART_LISTING));
                }
            });
        }
        String complianceDescription = cartListing.getComplianceDescription();
        if (!TextUtils.isEmpty(complianceDescription)) {
            this.mDescription.setVisibility(0);
            this.mDescription.setText(complianceDescription);
            return;
        }
        this.mDescription.setVisibility(8);
    }
}
