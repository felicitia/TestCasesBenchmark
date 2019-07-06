package com.contextlogic.wish.activity.feed.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.api.model.WishHomePageInfo.HomePageNotificationItemHolder;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.listview.HorizontalListView;
import com.contextlogic.wish.ui.listview.HorizontalListView.Adapter;
import java.util.ArrayList;

public class HomePageNotificationAdapter extends Adapter {
    private Context mContext;
    private final ProductFeedFragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher;
    private HorizontalListView mListView;
    private ArrayList<HomePageNotificationItemHolder> mNotifications;

    public int getItemWidth(int i) {
        return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_notification_list_view_width);
    }

    public int getItemHeight(int i) {
        return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.home_page_notification_list_view_height);
    }

    public int getCount() {
        return this.mNotifications.size();
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public HomePageNotificationAdapter(Context context, ArrayList<HomePageNotificationItemHolder> arrayList, HorizontalListView horizontalListView, ProductFeedFragment productFeedFragment) {
        this.mNotifications = arrayList;
        this.mContext = context;
        this.mListView = horizontalListView;
        this.mFragment = productFeedFragment;
        notifyDataSetChanged();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        HomePageNotificationCellView homePageNotificationCellView;
        if (!(view instanceof HomePageNotificationCellView)) {
            homePageNotificationCellView = new HomePageNotificationCellView(this.mContext);
            if (this.mImagePrefetcher != null) {
                homePageNotificationCellView.setImagePrefetcher(this.mImagePrefetcher);
            }
        } else {
            homePageNotificationCellView = (HomePageNotificationCellView) view;
        }
        homePageNotificationCellView.setNotification((HomePageNotificationItemHolder) this.mNotifications.get(i));
        return homePageNotificationCellView;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public void releaseImages() {
        for (int i = 0; i < this.mListView.getChildCount(); i++) {
            Object tag = this.mListView.getChildAt(i).getTag();
            if (tag != null && (tag instanceof HomePageNotificationCellView)) {
                ((HomePageNotificationCellView) tag).releaseImages();
            }
        }
    }

    public void restoreImages() {
        for (int i = 0; i < this.mListView.getChildCount(); i++) {
            Object tag = this.mListView.getChildAt(i).getTag();
            if (tag != null && (tag instanceof HomePageNotificationCellView)) {
                ((HomePageNotificationCellView) tag).releaseImages();
            }
        }
    }
}
