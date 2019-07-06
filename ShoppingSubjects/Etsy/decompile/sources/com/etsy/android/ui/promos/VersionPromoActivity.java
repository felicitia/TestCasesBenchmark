package com.etsy.android.ui.promos;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.ui.nav.e;
import com.etsy.android.ui.util.c;
import com.etsy.android.uikit.nav.FragmentNavigator.AnimationMode;
import com.etsy.android.uikit.ui.core.TransparentActivity;

public class VersionPromoActivity extends TransparentActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            e.a((FragmentActivity) this).f().B().a(AnimationMode.NONE).a(c.b(this));
        }
    }
}
