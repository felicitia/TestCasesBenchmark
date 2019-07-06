package com.contextlogic.wish.activity.cart.shipping;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;

public class StandaloneManageAddressesActivity extends DrawerActivity {
    /* access modifiers changed from: protected */
    public boolean canHaveMenu() {
        return false;
    }

    public String getActionBarTitle() {
        String stringExtra = getIntent().getStringExtra("ExtraActivityTitle");
        return TextUtils.isEmpty(stringExtra) ? getString(R.string.shipping_info) : stringExtra;
    }

    /* access modifiers changed from: protected */
    public StandaloneManageAddressesFragment createMainContentFragment() {
        return new StandaloneManageAddressesFragment();
    }

    /* access modifiers changed from: protected */
    public StandaloneManageAddressesServiceFragment createServiceFragment() {
        return new StandaloneManageAddressesServiceFragment();
    }

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        super.initializeActionBarManager(actionBarManager);
        actionBarManager.setHomeButtonMode(HomeButtonMode.X_ICON);
    }

    public int getBackgroundColor() {
        return ContextCompat.getColor(this, R.color.gray6);
    }
}
