package com.contextlogic.wish.activity.feed.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishHomePageInfo.HomePageOrderStatusItemHolder;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.listview.HorizontalListView.Adapter;
import java.util.ArrayList;

public class HomePageOrderStatusAdapter extends Adapter {
    private Context mContext;
    private ImageHttpPrefetcher mImageHttPrefetcher;
    private HorizontalListView mListView;
    private ArrayList<HomePageOrderStatusItemHolder> mOrderStatuses;

    public HomePageOrderStatusAdapter(Context context, ArrayList<HomePageOrderStatusItemHolder> arrayList, HorizontalListView horizontalListView) {
        this.mOrderStatuses = arrayList;
        this.mListView = horizontalListView;
        this.mContext = context;
    }

    public int getItemWidth(int i) {
        return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_order_status_list_view_width);
    }

    public int getItemHeight(int i) {
        return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_order_status_list_view_height);
    }

    public int getCount() {
        return this.mOrderStatuses.size();
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        HomePageOrderStatusCellView homePageOrderStatusCellView;
        if (!(view instanceof HomePageOrderStatusCellView)) {
            homePageOrderStatusCellView = new HomePageOrderStatusCellView(this.mContext);
            if (this.mImageHttPrefetcher != null) {
                homePageOrderStatusCellView.setImagePrefetcher(this.mImageHttPrefetcher);
            }
        } else {
            homePageOrderStatusCellView = (HomePageOrderStatusCellView) view;
        }
        homePageOrderStatusCellView.setOrderStatus((HomePageOrderStatusItemHolder) this.mOrderStatuses.get(i));
        return homePageOrderStatusCellView;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImageHttPrefetcher = imageHttpPrefetcher;
    }

    public void releaseImages() {
        for (int i = 0; i < this.mListView.getChildCount(); i++) {
            Object tag = this.mListView.getChildAt(i).getTag();
            if (tag != null && (tag instanceof HomePageOrderStatusCellView)) {
                ((HomePageOrderStatusCellView) tag).releaseImages();
            }
        }
    }

    public void restoreImages() {
        for (int i = 0; i < this.mListView.getChildCount(); i++) {
            Object tag = this.mListView.getChildAt(i).getTag();
            if (tag != null && (tag instanceof HomePageOrderStatusCellView)) {
                ((HomePageOrderStatusCellView) tag).releaseImages();
            }
        }
    }
}
