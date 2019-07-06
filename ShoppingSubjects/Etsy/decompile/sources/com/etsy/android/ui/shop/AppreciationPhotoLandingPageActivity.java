package com.etsy.android.ui.shop;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import com.etsy.android.R;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;

public class AppreciationPhotoLandingPageActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(R.string.appreciation_photo_landing_page_title);
        if (bundle == null) {
            e.a((FragmentActivity) this).f().f((EtsyId) getIntent().getSerializableExtra(ResponseConstants.TRANSACTION_ID));
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(R.menu.explore_action_bar, menu);
        return true;
    }
}
