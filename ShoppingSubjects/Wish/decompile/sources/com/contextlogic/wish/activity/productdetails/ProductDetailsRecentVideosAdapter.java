package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishProductExtraImage;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.listview.HorizontalListView.Adapter;

public class ProductDetailsRecentVideosAdapter extends Adapter {
    private final Context mContext;
    private SparseArray<WishProductExtraImage> mExtraVideos;
    private ProductDetailsFragment mFragment;

    public int getItemMargin() {
        return 0;
    }

    public ProductDetailsRecentVideosAdapter(Context context, ProductDetailsFragment productDetailsFragment) {
        this.mContext = context;
        this.mFragment = productDetailsFragment;
    }

    public void setExtraVideos(SparseArray<WishProductExtraImage> sparseArray) {
        this.mExtraVideos = sparseArray;
        notifyDataSetChanged();
    }

    public int getItemWidth(int i) {
        return (int) WishApplication.getInstance().getResources().getDimension(R.dimen.product_details_recent_videos_item_width);
    }

    public int getItemHeight(int i) {
        return (int) WishApplication.getInstance().getResources().getDimension(R.dimen.product_details_recent_videos_item_height);
    }

    public int getCount() {
        if (this.mExtraVideos != null) {
            return this.mExtraVideos.size();
        }
        return 0;
    }

    public WishProductExtraImage getItem(int i) {
        if (i < getCount()) {
            return (WishProductExtraImage) this.mExtraVideos.valueAt(i);
        }
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ProductDetailsRecentVideosHorizontalListViewItem productDetailsRecentVideosHorizontalListViewItem;
        if (view != null) {
            productDetailsRecentVideosHorizontalListViewItem = (ProductDetailsRecentVideosHorizontalListViewItem) view;
        } else {
            productDetailsRecentVideosHorizontalListViewItem = new ProductDetailsRecentVideosHorizontalListViewItem(this.mContext);
        }
        if (!(getItem(i) == null || getItem(i).getThumbnail() == null)) {
            productDetailsRecentVideosHorizontalListViewItem.setup(getItem(i), this.mExtraVideos.keyAt(i), this.mFragment);
        }
        return productDetailsRecentVideosHorizontalListViewItem;
    }
}
