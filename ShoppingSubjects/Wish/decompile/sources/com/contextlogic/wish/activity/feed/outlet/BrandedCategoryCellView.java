package com.contextlogic.wish.activity.feed.outlet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishCategory;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.Recyclable;

public class BrandedCategoryCellView extends LinearLayout implements ImageRestorable, Recyclable {
    private NetworkImageView mImageView;
    private ThemedTextView mTextView;

    public BrandedCategoryCellView(Context context) {
        super(context);
        init();
    }

    public void init() {
        setOrientation(1);
        setBackgroundResource(R.drawable.rounded_bordered_tile_bg);
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.branded_category_cell, this);
        this.mImageView = (NetworkImageView) inflate.findViewById(R.id.branded_category_cell_image);
        this.mTextView = (ThemedTextView) inflate.findViewById(R.id.branded_category_cell_text);
    }

    public void setCategory(WishCategory wishCategory) {
        this.mImageView.setImageUrl(wishCategory.getImageUrl());
        this.mTextView.setText(wishCategory.getName());
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
