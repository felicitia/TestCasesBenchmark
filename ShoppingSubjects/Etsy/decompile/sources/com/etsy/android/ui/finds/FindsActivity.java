package com.etsy.android.ui.finds;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import com.etsy.android.R;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;

public class FindsActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getString(R.string.editors_picks));
        if (bundle == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                e.a((FragmentActivity) this).f().a(extras).h(extras.getString("finds_slug"));
            }
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(R.menu.explore_action_bar, menu);
        return true;
    }
}
