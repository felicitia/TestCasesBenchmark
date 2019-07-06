package com.contextlogic.wish.activity.feed.dailyraffle;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.listview.ListeningListView;
import java.util.ArrayList;

public class DailyRaffleWinnersListFragment extends UiFragment<DailyRaffleWinnersListActivity> {
    /* access modifiers changed from: private */
    public NetworkImageView mProductImage;
    private ListeningListView mUserListView;
    private DailyRaffleWinnerListAdapter mWinnersAdapter;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.daily_raffle_winners_list_fragment;
    }

    public void releaseImages() {
        if (this.mProductImage != null) {
            this.mProductImage.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mProductImage != null) {
            this.mProductImage.restoreImages();
        }
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mProductImage = (NetworkImageView) findViewById(R.id.daily_raffle_winners_list_product_image);
        withActivity(new ActivityTask<DailyRaffleWinnersListActivity>() {
            public void performTask(DailyRaffleWinnersListActivity dailyRaffleWinnersListActivity) {
                if (dailyRaffleWinnersListActivity.getProduct() != null && dailyRaffleWinnersListActivity.getProduct().getImage() != null) {
                    DailyRaffleWinnersListFragment.this.mProductImage.setImage(dailyRaffleWinnersListActivity.getProduct().getImage());
                }
            }
        });
        this.mUserListView = (ListeningListView) findViewById(R.id.daily_raffle_user_list_view);
        withServiceFragment(new ServiceTask<DailyRaffleWinnersListActivity, DailyRaffleWinnersServiceFragment>() {
            public void performTask(DailyRaffleWinnersListActivity dailyRaffleWinnersListActivity, DailyRaffleWinnersServiceFragment dailyRaffleWinnersServiceFragment) {
                if (dailyRaffleWinnersListActivity.getProduct() == null) {
                    dailyRaffleWinnersListActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(DailyRaffleWinnersListFragment.this.getString(R.string.something_went_wrong)));
                } else {
                    dailyRaffleWinnersServiceFragment.getRaffleWinners(dailyRaffleWinnersListActivity.getProduct().getCommerceProductId());
                }
            }
        });
    }

    public void handleLoadingSuccess(ArrayList<WishUser> arrayList, ArrayList<WishUser> arrayList2) {
        if (arrayList != null) {
            this.mWinnersAdapter = new DailyRaffleWinnerListAdapter(getBaseActivity(), arrayList, false);
            this.mUserListView.setAdapter(this.mWinnersAdapter);
        }
    }
}
