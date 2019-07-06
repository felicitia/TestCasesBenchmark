package com.contextlogic.wish.activity.exampleugc;

import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.grid.StaggeredGridView.Adapter;
import java.util.ArrayList;

public class ExampleUgcItemsAdapter extends Adapter {
    private ExampleUgcItemsActivity mActivity;
    private ImageHttpPrefetcher mImagePrefetcher;
    private ArrayList<WishRating> mRatings;

    public int getColumnCount() {
        return 2;
    }

    public ExampleUgcItemsAdapter(ExampleUgcItemsActivity exampleUgcItemsActivity) {
        this.mActivity = exampleUgcItemsActivity;
    }

    public int getCount() {
        if (this.mRatings == null) {
            return 0;
        }
        return this.mRatings.size();
    }

    public WishRating getItem(int i) {
        if (i < getCount()) {
            return (WishRating) this.mRatings.get(i);
        }
        return null;
    }

    public int getItemHeight(int i, int i2) {
        return (int) (((float) i2) + WishApplication.getInstance().getResources().getDimension(R.dimen.example_ugc_item_feed_tile_bottom_area_height));
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ExampleUgcItemTileView exampleUgcItemTileView;
        if (view != null) {
            exampleUgcItemTileView = (ExampleUgcItemTileView) view;
        } else {
            exampleUgcItemTileView = new ExampleUgcItemTileView(this.mActivity);
            if (this.mImagePrefetcher != null) {
                exampleUgcItemTileView.setImagePrefetcher(this.mImagePrefetcher);
            }
        }
        exampleUgcItemTileView.setRating(getItem(i));
        return exampleUgcItemTileView;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public void setRatings(ArrayList<WishRating> arrayList) {
        this.mRatings = arrayList;
        notifyDataSetChanged();
    }
}
