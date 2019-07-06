package com.etsy.android.ui.local.marketdetails;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import com.etsy.android.R;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;
import org.parceler.d;

public class LocalEventActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.containsKey(ResponseConstants.LOCAL_MARKET)) {
                e.a((FragmentActivity) this).f().a((LocalMarket) d.a(extras.getParcelable(ResponseConstants.LOCAL_MARKET)), extras.getBoolean("show_local_browse_link"));
            } else if (extras != null) {
                e.a((FragmentActivity) this).f().a((EtsyId) extras.getSerializable(ResponseConstants.LOCAL_MARKET_ID), extras.getBoolean("show_local_browse_link"));
            }
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(R.menu.explore_action_bar, menu);
        return true;
    }
}
