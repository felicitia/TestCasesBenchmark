package com.contextlogic.wish.activity.feed.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.listview.HorizontalListView.Adapter;
import java.util.ArrayList;

public class HomePageProductAdapter extends Adapter {
    private Context mContext;
    private ImageHttpPrefetcher mImagePrefetcher;
    private final HorizontalListView mListView;
    private ArrayList<WishProduct> mProducts;
    private final boolean mShowDetailedView;

    public int getItemWidth(int i) {
        if (this.mShowDetailedView) {
            return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_detailed_product_list_view_width);
        }
        return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_product_list_view_height);
    }

    public int getItemHeight(int i) {
        if (this.mShowDetailedView) {
            return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_detailed_product_list_view_height);
        }
        return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_product_list_view_height);
    }

    public int getCount() {
        return this.mProducts.size();
    }

    public WishProduct getItem(int i) {
        return (WishProduct) this.mProducts.get(i);
    }

    public HomePageProductAdapter(Context context, ArrayList<WishProduct> arrayList, HorizontalListView horizontalListView, boolean z) {
        this.mProducts = arrayList;
        this.mContext = context;
        this.mListView = horizontalListView;
        this.mShowDetailedView = z;
        notifyDataSetChanged();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        HomePageProductCellView homePageProductCellView;
        HomePageDetailedProductCellView homePageDetailedProductCellView;
        if (this.mShowDetailedView) {
            if (!(view instanceof HomePageDetailedProductCellView)) {
                homePageDetailedProductCellView = new HomePageDetailedProductCellView(this.mContext);
                if (this.mImagePrefetcher != null) {
                    homePageDetailedProductCellView.setImagePrefetcher(this.mImagePrefetcher);
                }
            } else {
                homePageDetailedProductCellView = (HomePageDetailedProductCellView) view;
            }
            homePageDetailedProductCellView.setProduct((WishProduct) this.mProducts.get(i));
            return homePageDetailedProductCellView;
        }
        if (!(view instanceof HomePageProductCellView)) {
            homePageProductCellView = new HomePageProductCellView(this.mContext);
            if (this.mImagePrefetcher != null) {
                homePageProductCellView.setImagePrefetcher(this.mImagePrefetcher);
            }
        } else {
            homePageProductCellView = (HomePageProductCellView) view;
        }
        homePageProductCellView.setProduct((WishProduct) this.mProducts.get(i));
        return homePageProductCellView;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public void releaseImages() {
        for (int i = 0; i < this.mListView.getChildCount(); i++) {
            Object tag = this.mListView.getChildAt(i).getTag();
            if (tag != null && (tag instanceof HomePageProductCellView)) {
                ((HomePageProductCellView) tag).releaseImages();
            }
        }
    }

    public void restoreImages() {
        for (int i = 0; i < this.mListView.getChildCount(); i++) {
            Object tag = this.mListView.getChildAt(i).getTag();
            if (tag != null && (tag instanceof HomePageProductCellView)) {
                ((HomePageProductCellView) tag).releaseImages();
            }
        }
    }
}
