package com.contextlogic.wish.activity.feed.dailyraffle;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.util.IntentUtil;

public class DailyRaffleWinnersListActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new DailyRaffleWinnersListFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.winners);
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new DailyRaffleWinnersServiceFragment();
    }

    public WishProduct getProduct() {
        return (WishProduct) IntentUtil.getLargeParcelableExtra(getIntent(), "DailyRaffleListProduct", WishProduct.class);
    }
}
