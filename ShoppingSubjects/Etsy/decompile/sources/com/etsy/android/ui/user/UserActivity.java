package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import com.etsy.android.R;
import com.etsy.android.lib.core.v;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;

public class UserActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!v.a().e()) {
            finish();
            return;
        }
        if (bundle == null) {
            e.a((FragmentActivity) this).f().c(v.a().l());
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(R.menu.default_action_bar, menu);
        return true;
    }
}
