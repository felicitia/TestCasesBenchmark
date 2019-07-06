package com.contextlogic.wish.activity.productreview;

import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishProductReviewItem;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.grid.StaggeredGridView.Adapter;
import java.util.ArrayList;

public class ProductReviewItemsAdapter extends Adapter {
    private final ProductReviewActivity mActivity;
    private final ProductReviewFragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher;
    private ArrayList<WishProductReviewItem> mItems;

    public int getColumnCount() {
        return 2;
    }

    public ProductReviewItemsAdapter(ProductReviewActivity productReviewActivity, ProductReviewFragment productReviewFragment) {
        this.mActivity = productReviewActivity;
        this.mFragment = productReviewFragment;
    }

    public int getCount() {
        if (this.mItems == null) {
            return 0;
        }
        return this.mItems.size();
    }

    public WishProductReviewItem getItem(int i) {
        if (i < getCount()) {
            return (WishProductReviewItem) this.mItems.get(i);
        }
        return null;
    }

    public int getItemHeight(int i, int i2) {
        return (int) (((float) i2) + WishApplication.getInstance().getResources().getDimension(R.dimen.product_review_feed_tile_bottom_area_height));
    }

    public ProductReviewItemTileView getView(int i, View view, ViewGroup viewGroup) {
        ProductReviewItemTileView productReviewItemTileView;
        if (view == null || !(view instanceof ProductReviewItemTileView)) {
            productReviewItemTileView = new ProductReviewItemTileView(this.mActivity);
            if (this.mImagePrefetcher != null) {
                productReviewItemTileView.setImagePrefetcher(this.mImagePrefetcher);
            }
        } else {
            productReviewItemTileView = (ProductReviewItemTileView) view;
        }
        productReviewItemTileView.setItem(getItem(i));
        return productReviewItemTileView;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public void setItems(ArrayList<WishProductReviewItem> arrayList) {
        this.mItems = arrayList;
        notifyDataSetChanged();
    }
}
