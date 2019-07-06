package com.contextlogic.wish.activity.feed.outlet;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;
import com.contextlogic.wish.api.model.WishCategory;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.listview.HorizontalListView.Adapter;
import java.util.ArrayList;

public class BrandedCategoryAdapter extends Adapter {
    private ArrayList<WishCategory> mCategories;
    private final Context mContext;
    private final BaseProductFeedFragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher;
    private HorizontalListView mListView;

    public BrandedCategoryAdapter(BaseProductFeedFragment baseProductFeedFragment, Context context, HorizontalListView horizontalListView) {
        this.mFragment = baseProductFeedFragment;
        this.mListView = horizontalListView;
        this.mContext = context;
    }

    public void setCategories(ArrayList<WishCategory> arrayList) {
        this.mCategories = arrayList;
        notifyDataSetChanged();
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public int getItemWidth(int i) {
        return this.mFragment.getResources().getDimensionPixelOffset(R.dimen.branded_category_cell_width);
    }

    public int getItemHeight(int i) {
        return this.mFragment.getResources().getDimensionPixelOffset(R.dimen.branded_category_cell_height);
    }

    public int getCount() {
        return this.mCategories.size();
    }

    public WishCategory getItem(int i) {
        if (i < 0 || i >= this.mCategories.size()) {
            return null;
        }
        return (WishCategory) this.mCategories.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        BrandedCategoryCellView brandedCategoryCellView;
        if (view != null) {
            brandedCategoryCellView = (BrandedCategoryCellView) view;
        } else {
            brandedCategoryCellView = new BrandedCategoryCellView(this.mContext);
            if (this.mImagePrefetcher != null) {
                brandedCategoryCellView.setImagePrefetcher(this.mImagePrefetcher);
            }
        }
        brandedCategoryCellView.setCategory((WishCategory) this.mCategories.get(i));
        return brandedCategoryCellView;
    }

    public void releaseImages() {
        for (int i = 0; i < this.mListView.getChildCount(); i++) {
            Object tag = this.mListView.getChildAt(i).getTag();
            if (tag != null && (tag instanceof BrandedCategoryCellView)) {
                ((BrandedCategoryCellView) tag).releaseImages();
            }
        }
    }

    public void restoreImages() {
        for (int i = 0; i < this.mListView.getChildCount(); i++) {
            Object tag = this.mListView.getChildAt(i).getTag();
            if (tag != null && (tag instanceof BrandedCategoryCellView)) {
                ((BrandedCategoryCellView) tag).releaseImages();
            }
        }
    }
}
