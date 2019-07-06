package com.contextlogic.wish.activity.search;

import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.listview.HorizontalListView.Adapter;
import com.contextlogic.wish.util.ValueUtil;
import java.util.ArrayList;

public class SearchRecentlyViewedAdapter extends Adapter {
    private SearchActivity mBaseActivity;
    private SearchFragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher;
    private ArrayList<WishProduct> mRecentlyViewedProducts;

    public SearchRecentlyViewedAdapter(SearchActivity searchActivity, SearchFragment searchFragment, ArrayList<WishProduct> arrayList) {
        this.mBaseActivity = searchActivity;
        this.mFragment = searchFragment;
        this.mRecentlyViewedProducts = arrayList;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public int getItemWidth(int i) {
        return (int) ValueUtil.convertDpToPx(90.0f);
    }

    public int getItemHeight(int i) {
        return (int) ValueUtil.convertDpToPx(90.0f);
    }

    public int getCount() {
        return this.mRecentlyViewedProducts.size();
    }

    public WishProduct getItem(int i) {
        if (i < 0 || i >= this.mRecentlyViewedProducts.size()) {
            return null;
        }
        return (WishProduct) this.mRecentlyViewedProducts.get(i);
    }

    public int getLeadMargin() {
        return WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.zero_padding);
    }

    public int getItemMargin() {
        return WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.eight_padding);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        NetworkImageView networkImageView;
        if (view != null) {
            networkImageView = (NetworkImageView) view;
        } else {
            networkImageView = new NetworkImageView(this.mBaseActivity);
            if (this.mImagePrefetcher != null) {
                networkImageView.setImagePrefetcher(this.mImagePrefetcher);
            }
        }
        WishProduct item = getItem(i);
        if (item != null) {
            networkImageView.setImage(item.getImage());
        }
        return networkImageView;
    }
}
