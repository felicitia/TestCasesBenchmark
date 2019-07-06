package com.contextlogic.wish.activity.cart.shipping;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.util.IntentUtil;

public class StandaloneShippingInfoActivity extends DrawerActivity {
    /* access modifiers changed from: protected */
    public boolean canHaveMenu() {
        return false;
    }

    public String getActionBarTitle() {
        return getString(getEditAddressShippingInfo() != null ? R.string.edit_address : R.string.add_new_address);
    }

    /* access modifiers changed from: protected */
    public StandaloneShippingInfoFragment createMainContentFragment() {
        return new StandaloneShippingInfoFragment();
    }

    /* access modifiers changed from: protected */
    public StandaloneShippingInfoServiceFragment createServiceFragment() {
        return new StandaloneShippingInfoServiceFragment();
    }

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        super.initializeActionBarManager(actionBarManager);
        actionBarManager.setHomeButtonMode(HomeButtonMode.X_ICON);
    }

    public String getCancelWarning() {
        return getIntent().getStringExtra("ExtraCancelWarning");
    }

    public WishShippingInfo getEditAddressShippingInfo() {
        return (WishShippingInfo) IntentUtil.getParcelableExtra(getIntent(), "ExtraEditAddressShippingInfo");
    }
}
