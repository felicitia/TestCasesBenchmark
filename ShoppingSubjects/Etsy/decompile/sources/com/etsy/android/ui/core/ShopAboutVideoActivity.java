package com.etsy.android.ui.core;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.nav.TrackingBaseActivity;

public class ShopAboutVideoActivity extends TrackingBaseActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getAppBarHelper().hideAppBar();
        if (bundle == null) {
            e.a((FragmentActivity) this).f().f(getIntent().getExtras());
        }
    }
}
