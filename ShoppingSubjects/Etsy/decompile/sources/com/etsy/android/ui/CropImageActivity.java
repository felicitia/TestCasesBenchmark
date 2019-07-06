package com.etsy.android.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.ui.nav.e;

public class CropImageActivity extends BOENavDrawerActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            e.a((FragmentActivity) this).f().a(getIntent().getExtras()).y();
        }
    }
}
