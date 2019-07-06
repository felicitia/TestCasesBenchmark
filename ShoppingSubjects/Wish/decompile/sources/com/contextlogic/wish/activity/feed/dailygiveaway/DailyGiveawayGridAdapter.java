package com.contextlogic.wish.activity.feed.dailygiveaway;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import java.util.ArrayList;

public class DailyGiveawayGridAdapter extends BaseAdapter {
    private DrawerActivity mBaseActivity;
    private DataProvider mDataProvider;
    private BaseProductFeedFragment mFragment;
    private ImageHttpPrefetcher mImagePrefetcher;
    private ListView mListView;

    public interface DataProvider {
        ArrayList<WishProductPair> getData();
    }

    public static class WishProductPair {
        public WishProduct leftProduct;
        public WishProduct rightProduct;
    }

    public long getItemId(int i) {
        return 0;
    }

    public DailyGiveawayGridAdapter(DrawerActivity drawerActivity, BaseProductFeedFragment baseProductFeedFragment, ListView listView, ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mBaseActivity = drawerActivity;
        this.mFragment = baseProductFeedFragment;
        this.mImagePrefetcher = imageHttpPrefetcher;
        this.mListView = listView;
    }

    public void setDataProvider(DataProvider dataProvider) {
        this.mDataProvider = dataProvider;
    }

    public ArrayList<WishProductPair> getItems() {
        if (this.mDataProvider != null) {
            return this.mDataProvider.getData();
        }
        return null;
    }

    public final WishProductPair getItem(int i) {
        ArrayList items = getItems();
        if (i < 0 || i >= items.size()) {
            return null;
        }
        return (WishProductPair) items.get(i);
    }

    public final int getCount() {
        ArrayList items = getItems();
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        DailyGiveawayGridCellView dailyGiveawayGridCellView;
        if (view == null || !(view instanceof DailyGiveawayGridCellView)) {
            dailyGiveawayGridCellView = new DailyGiveawayGridCellView(this.mBaseActivity, this.mFragment);
        } else {
            dailyGiveawayGridCellView = (DailyGiveawayGridCellView) view;
        }
        dailyGiveawayGridCellView.setImagePrefetcher(this.mImagePrefetcher);
        dailyGiveawayGridCellView.setProducts(getItem(i));
        return dailyGiveawayGridCellView;
    }

    public void releaseImages() {
        for (int i = 0; i < this.mListView.getChildCount(); i++) {
            View childAt = this.mListView.getChildAt(i);
            if (childAt != null && (childAt instanceof DailyGiveawayGridCellView)) {
                ((DailyGiveawayGridCellView) childAt).releaseImages();
            }
        }
    }

    public void restoreImages() {
        for (int i = 0; i < this.mListView.getChildCount(); i++) {
            View childAt = this.mListView.getChildAt(i);
            if (childAt != null && (childAt instanceof DailyGiveawayGridCellView)) {
                ((DailyGiveawayGridCellView) childAt).restoreImages();
            }
        }
    }
}
