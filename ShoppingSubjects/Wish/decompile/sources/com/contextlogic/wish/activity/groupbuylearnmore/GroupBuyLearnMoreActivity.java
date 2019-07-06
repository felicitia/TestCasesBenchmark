package com.contextlogic.wish.activity.groupbuylearnmore;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.api.model.WishGroupBuyInfo;
import com.contextlogic.wish.api.model.WishGroupBuyRowInfo;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.IntentUtil;

public class GroupBuyLearnMoreActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new GroupBuyLearnMoreFragment();
    }

    public String getActionBarTitle() {
        return WishApplication.getInstance().getResources().getString(R.string.learn_more);
    }

    public WishGroupBuyInfo getGroupBuyInfo() {
        return (WishGroupBuyInfo) IntentUtil.getParcelableExtra(getIntent(), "ArgGroupBuyInfo");
    }

    public WishGroupBuyRowInfo getGroupBuy() {
        return (WishGroupBuyRowInfo) IntentUtil.getParcelableExtra(getIntent(), "ArgGroupBuy");
    }

    public WishProduct getProduct() {
        return (WishProduct) IntentUtil.getParcelableExtra(getIntent(), "ArgProduct");
    }
}
