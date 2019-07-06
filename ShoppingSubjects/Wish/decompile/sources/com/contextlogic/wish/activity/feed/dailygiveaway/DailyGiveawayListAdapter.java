package com.contextlogic.wish.activity.feed.dailygiveaway;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedAdapter;
import com.contextlogic.wish.activity.feed.ProductFeedFragment;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayFeedView.DailyGiveawayState;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import java.util.HashMap;

public class DailyGiveawayListAdapter extends BaseProductFeedAdapter {
    private DrawerActivity mBaseActivity;
    private HashMap<String, String> mExtraInfo = new HashMap<>();
    private DailyGiveawayFeedView mFeedView;
    private ProductFeedFragment mFragment;
    private ListView mListView;
    private DailyGiveawayState mState;

    DailyGiveawayListAdapter(DrawerActivity drawerActivity, ProductFeedFragment productFeedFragment, DailyGiveawayFeedView dailyGiveawayFeedView, ListView listView, ImageHttpPrefetcher imageHttpPrefetcher) {
        super(drawerActivity, productFeedFragment);
        this.mBaseActivity = drawerActivity;
        this.mFragment = productFeedFragment;
        this.mFeedView = dailyGiveawayFeedView;
        this.mListView = listView;
        this.mImagePrefetcher = imageHttpPrefetcher;
        refreshState();
    }

    static boolean shouldExpandTile(DailyGiveawayState dailyGiveawayState) {
        return dailyGiveawayState == DailyGiveawayState.ONGOING || dailyGiveawayState == DailyGiveawayState.ALREADY_CLAIMED || dailyGiveawayState == DailyGiveawayState.ENDED;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        DailyGiveawayListCellView dailyGiveawayListCellView;
        if (view == null || !(view instanceof DailyGiveawayListCellView)) {
            dailyGiveawayListCellView = new DailyGiveawayListCellView(this.mBaseActivity, this.mBaseActivity, this.mFragment, this);
            dailyGiveawayListCellView.setImagePrefetcher(this.mImagePrefetcher);
        } else {
            dailyGiveawayListCellView = (DailyGiveawayListCellView) view;
            dailyGiveawayListCellView.refreshState(this);
        }
        dailyGiveawayListCellView.setProduct(getItem(i));
        return dailyGiveawayListCellView;
    }

    public DailyGiveawayState getState() {
        return this.mState;
    }

    public void releaseImages() {
        for (int i = 0; i < this.mListView.getChildCount(); i++) {
            View childAt = this.mListView.getChildAt(i);
            if (childAt != null && (childAt instanceof DailyGiveawayListCellView)) {
                ((DailyGiveawayListCellView) childAt).releaseImages();
            }
        }
    }

    public void restoreImages() {
        for (int i = 0; i < this.mListView.getChildCount(); i++) {
            View childAt = this.mListView.getChildAt(i);
            if (childAt != null && (childAt instanceof DailyGiveawayListCellView)) {
                ((DailyGiveawayListCellView) childAt).restoreImages();
            }
        }
    }

    public HashMap<String, String> getExtraInfo() {
        return this.mExtraInfo;
    }

    /* access modifiers changed from: 0000 */
    public void refreshState() {
        this.mState = this.mFeedView.getState();
        if (this.mState != null) {
            this.mExtraInfo.put("daily_giveaway_state", this.mState.name());
        }
        this.mExtraInfo.put("GiveawayType", "DailyGiveaway");
    }
}
