package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.R;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;

public class LegalInfoActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(R.string.legal);
        if (bundle == null && getIntent().getExtras() != null) {
            e.a((FragmentActivity) this).f().p();
        }
    }
}
