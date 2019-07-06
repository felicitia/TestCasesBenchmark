package com.contextlogic.wish.activity.exampleugc.exampleugcintro;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.exampleugc.ExampleUgcItemTileView;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.listview.HorizontalListView.Adapter;
import java.util.ArrayList;

public class ExampleUgcIntroItemsAdapter extends Adapter {
    private ExampleUgcIntroFragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher;
    private int mItemHeight = -1;
    private int mItemWidth = -1;
    private ArrayList<WishRating> mRatings;
    private Resources mResources;

    public int getGravity() {
        return 16;
    }

    public ExampleUgcIntroItemsAdapter(ExampleUgcIntroFragment exampleUgcIntroFragment, ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mFragment = exampleUgcIntroFragment;
        this.mImagePrefetcher = imageHttpPrefetcher;
        this.mResources = ((ExampleUgcIntroActivity) exampleUgcIntroFragment.getBaseActivity()).getResources();
    }

    public int getItemWidth(int i) {
        if (this.mItemWidth == -1) {
            computeItemDimensions();
        }
        return this.mItemWidth;
    }

    public int getItemHeight(int i) {
        if (this.mItemHeight == -1) {
            computeItemDimensions();
        }
        return this.mItemHeight;
    }

    private void computeItemDimensions() {
        if (this.mFragment.getHorizontalListViewHeight() < this.mResources.getDimensionPixelSize(R.dimen.example_ugc_item_cell_height_large)) {
            this.mItemWidth = this.mResources.getDimensionPixelSize(R.dimen.example_ugc_item_cell_width_small);
            this.mItemHeight = this.mResources.getDimensionPixelSize(R.dimen.example_ugc_item_cell_height_small);
            return;
        }
        this.mItemWidth = this.mResources.getDimensionPixelSize(R.dimen.example_ugc_item_cell_width_large);
        this.mItemHeight = this.mResources.getDimensionPixelSize(R.dimen.example_ugc_item_cell_height_large);
    }

    public WishRating getItem(int i) {
        if (i < getCount()) {
            return (WishRating) this.mRatings.get(i);
        }
        return null;
    }

    public int getCount() {
        if (this.mRatings == null) {
            return 0;
        }
        return this.mRatings.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ExampleUgcItemTileView exampleUgcItemTileView;
        if (view == null || !(view instanceof ExampleUgcItemTileView)) {
            exampleUgcItemTileView = new ExampleUgcItemTileView(this.mFragment.getBaseActivity());
            if (this.mImagePrefetcher != null) {
                exampleUgcItemTileView.setImagePrefetcher(this.mImagePrefetcher);
            }
        } else {
            exampleUgcItemTileView = (ExampleUgcItemTileView) view;
        }
        exampleUgcItemTileView.setRating(getItem(i));
        return exampleUgcItemTileView;
    }

    public void setRatings(ArrayList<WishRating> arrayList) {
        this.mRatings = arrayList;
        notifyDataSetChanged();
    }
}
