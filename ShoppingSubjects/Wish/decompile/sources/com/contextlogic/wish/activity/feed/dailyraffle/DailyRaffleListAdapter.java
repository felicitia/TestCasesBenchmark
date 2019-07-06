package com.contextlogic.wish.activity.feed.dailyraffle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.feed.BaseProductFeedAdapter;
import com.contextlogic.wish.activity.feed.BaseProductFeedFragment;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayFeedView.DailyGiveawayState;
import com.contextlogic.wish.activity.feed.dailyraffle.DailyRaffleFeedView.DailyRaffleWinState;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import java.util.HashMap;
import java.util.List;

public class DailyRaffleListAdapter extends BaseProductFeedAdapter {
    private HashMap<String, String> mExtraInfo = new HashMap<>();
    private DailyRaffleFeedView mFeedView;
    private ListView mListView;
    private String mSelectedProductCid = null;
    private DailyGiveawayState mState;
    private DailyRaffleWinState mWinState;

    DailyRaffleListAdapter(DrawerActivity drawerActivity, BaseProductFeedFragment baseProductFeedFragment, DailyRaffleFeedView dailyRaffleFeedView, ListView listView, ImageHttpPrefetcher imageHttpPrefetcher) {
        super(drawerActivity, baseProductFeedFragment);
        this.mFeedView = dailyRaffleFeedView;
        this.mListView = listView;
        this.mImagePrefetcher = imageHttpPrefetcher;
        refreshState();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        DailyRaffleListCellView dailyRaffleListCellView;
        if (view == null || !(view instanceof DailyRaffleListCellView)) {
            dailyRaffleListCellView = new DailyRaffleListCellView(getBaseActivity());
            dailyRaffleListCellView.setup(getBaseActivity(), getFragment(), this, this.mImagePrefetcher);
        } else {
            dailyRaffleListCellView = (DailyRaffleListCellView) view;
            dailyRaffleListCellView.refreshState(this);
        }
        WishProduct item = getItem(i);
        if (item == null) {
            return dailyRaffleListCellView;
        }
        dailyRaffleListCellView.setProduct(item);
        if (this.mSelectedProductCid != null && item.getCommerceProductId().equals(this.mSelectedProductCid)) {
            if (this.mWinState == DailyRaffleWinState.FREE) {
                dailyRaffleListCellView.setRaffleWon(this.mFeedView.getRaffleBannerText());
                dailyRaffleListCellView.setTimeToClaim(this.mFeedView.getTimeLeftToClaimPrize());
            } else if (this.mWinState == DailyRaffleWinState.LOSE) {
                dailyRaffleListCellView.setRaffleLost(this.mFeedView.getRaffleBannerText());
            } else {
                dailyRaffleListCellView.setSelected();
            }
        }
        dailyRaffleListCellView.refreshButtonState();
        return dailyRaffleListCellView;
    }

    public DailyGiveawayState getState() {
        return this.mState;
    }

    /* access modifiers changed from: 0000 */
    public DailyRaffleWinState getWinState() {
        return this.mWinState;
    }

    public void releaseImages() {
        for (int i = 0; i < this.mListView.getChildCount(); i++) {
            View childAt = this.mListView.getChildAt(i);
            if (childAt != null && (childAt instanceof DailyRaffleListCellView)) {
                ((DailyRaffleListCellView) childAt).releaseImages();
            }
        }
    }

    public void restoreImages() {
        for (int i = 0; i < this.mListView.getChildCount(); i++) {
            View childAt = this.mListView.getChildAt(i);
            if (childAt != null && (childAt instanceof DailyRaffleListCellView)) {
                ((DailyRaffleListCellView) childAt).restoreImages();
            }
        }
    }

    public HashMap<String, String> getExtraInfo() {
        return this.mExtraInfo;
    }

    /* access modifiers changed from: 0000 */
    public void refreshState() {
        this.mWinState = this.mFeedView.getWinState();
        this.mState = this.mFeedView.getState();
        if (this.mState != null) {
            this.mExtraInfo.put("daily_giveaway_state", this.mState.name());
        }
        if (this.mWinState != null) {
            this.mExtraInfo.put("daily_raffle_win_state", this.mWinState.name());
        }
        this.mExtraInfo.put("GiveawayType", "DailyRaffle");
    }

    public void setSelected(String str) {
        if (str != null) {
            this.mSelectedProductCid = str;
        }
    }

    /* access modifiers changed from: 0000 */
    public List<WishUser> getWinnerList(String str) {
        if (this.mFeedView.getWinnerDict() == null || str == null) {
            return null;
        }
        return (List) this.mFeedView.getWinnerDict().get(str);
    }

    /* access modifiers changed from: 0000 */
    public DailyRaffleFeedView getFeedView() {
        return this.mFeedView;
    }

    public void reload() {
        this.mFeedView.reload();
    }
}
