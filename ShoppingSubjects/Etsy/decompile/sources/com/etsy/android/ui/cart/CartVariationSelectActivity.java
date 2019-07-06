package com.etsy.android.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.core.TransparentActivity;

public class CartVariationSelectActivity extends TransparentActivity {
    public void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        Bundle bundle2 = new Bundle();
        if (intent != null) {
            bundle2 = getIntent().getExtras();
        }
        super.onCreate(bundle);
        if (bundle == null) {
            e.a((FragmentActivity) this).f().a(bundle2).h();
        }
    }
}
