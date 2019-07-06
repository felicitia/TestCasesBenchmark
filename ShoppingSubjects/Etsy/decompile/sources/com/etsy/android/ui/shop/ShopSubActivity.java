package com.etsy.android.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.etsy.android.R;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Shop;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.core.CoreActivity;
import com.etsy.android.ui.nav.e;

public class ShopSubActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            launchFragment();
        }
    }

    private void launchFragment() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle bundle = extras.getBundle("referral_args");
            if (extras.containsKey(ResponseConstants.SHOP)) {
                e.a((FragmentActivity) this).f().a(bundle).a((Shop) extras.getSerializable(ResponseConstants.SHOP));
            } else if (extras.containsKey("shop_id")) {
                e.a((FragmentActivity) this).f().a(bundle).b((EtsyId) extras.getSerializable("shop_id"));
            }
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(R.menu.explore_action_bar, menu);
        menu.removeItem(R.id.menu_share);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332 && !isTopLevelActivity()) {
            return upNavigationToShop();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* access modifiers changed from: protected */
    public boolean upNavigationToShop() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1 || getIntent() == null || !getIntent().getBooleanExtra("NAV_UP_TO_PARENT", false)) {
            return popOrGoBack();
        }
        Intent intent = new Intent(this, CoreActivity.class);
        Shop shop = (Shop) getIntent().getSerializableExtra(ResponseConstants.SHOP);
        if (shop != null) {
            intent.putExtra("shop_id", shop.getShopId());
        }
        intent.putExtras(getIntent().getExtras());
        e.a((FragmentActivity) this).a(intent);
        return true;
    }
}
