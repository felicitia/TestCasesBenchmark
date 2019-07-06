package com.contextlogic.wish.activity.feed.home;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishImage.ImageSize;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.view.Recyclable;

public class HomePageProductCellView extends LinearLayout implements ImageRestorable, Recyclable {
    private NetworkImageView mImageView;
    private WishProduct mProduct;

    public HomePageProductCellView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.mImageView = new NetworkImageView(getContext());
        int dimensionPixelSize = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_product_cell_view_height);
        this.mImageView.setLayoutParams(new LayoutParams(dimensionPixelSize, dimensionPixelSize));
        addView(this.mImageView);
    }

    public void setProduct(WishProduct wishProduct) {
        this.mProduct = wishProduct;
        this.mImageView.setImage(new WishImage(this.mProduct.getImage().getUrlString(ImageSize.MEDIUM)));
    }

    public void releaseImages() {
        this.mImageView.releaseImages();
    }

    public void restoreImages() {
        this.mImageView.restoreImages();
    }

    public void recycle() {
        this.mImageView.releaseImages();
        this.mImageView.setImage(null);
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImageView.setImagePrefetcher(imageHttpPrefetcher);
    }
}
