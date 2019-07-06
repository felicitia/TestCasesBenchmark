package com.contextlogic.wish.activity.productreview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishProductReviewItem;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.DateUtil;
import java.util.Date;

public class ProductReviewItemTileView extends FrameLayout implements ImageRestorable {
    private ImageView mCheckmark;
    private NetworkImageView mItemImage;
    private ThemedTextView mPurchaseDateText;

    public ProductReviewItemTileView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.product_review_item_tile, this);
        this.mCheckmark = (ImageView) inflate.findViewById(R.id.product_review_item_selected_checkmark);
        this.mItemImage = (NetworkImageView) inflate.findViewById(R.id.product_review_item_image);
        this.mPurchaseDateText = (ThemedTextView) inflate.findViewById(R.id.product_review_item_purchase_date_text);
    }

    public void setSelected(boolean z) {
        this.mCheckmark.setVisibility(z ? 0 : 8);
        this.mItemImage.setSelected(z);
        this.mItemImage.setBackgroundColor(getContext().getResources().getColor(z ? R.color.main_primary : R.color.transparent));
    }

    public boolean isSelected() {
        return this.mItemImage.isSelected();
    }

    public void setItem(WishProductReviewItem wishProductReviewItem) {
        this.mItemImage.setImage(wishProductReviewItem.getProductImage());
        this.mPurchaseDateText.setText(getPurchaseDate(wishProductReviewItem.getPurchaseTime()));
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mItemImage.setImagePrefetcher(imageHttpPrefetcher);
    }

    private String getPurchaseDate(Date date) {
        String localizedDate = DateUtil.getLocalizedDate(date);
        return getContext().getString(R.string.bought_on, new Object[]{localizedDate});
    }

    public void releaseImages() {
        if (this.mItemImage != null) {
            this.mItemImage.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mItemImage != null) {
            this.mItemImage.restoreImages();
        }
    }
}
