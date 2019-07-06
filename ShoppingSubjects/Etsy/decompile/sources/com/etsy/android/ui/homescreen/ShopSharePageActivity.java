package com.etsy.android.ui.homescreen;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;

public class ShopSharePageActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            e.a((FragmentActivity) this).f().e(getIntent().getExtras());
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        return navigateUpAsBack();
    }
}
