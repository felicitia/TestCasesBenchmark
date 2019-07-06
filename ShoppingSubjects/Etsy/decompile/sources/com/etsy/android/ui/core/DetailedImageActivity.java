package com.etsy.android.ui.core;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.R;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.util.p;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.adapter.FullImagesPagerAdapter;
import com.etsy.android.uikit.ui.core.TransparentActivity;
import java.util.List;

public class DetailedImageActivity extends TransparentActivity implements a, FullImagesPagerAdapter.a {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_frame);
        if (bundle == null) {
            if (!hasImages()) {
                finish();
            }
            navToDetailedImageFragment();
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasImages() {
        List list = (List) getIntent().getExtras().getSerializable("image_list");
        return list != null && !list.isEmpty();
    }

    /* access modifiers changed from: protected */
    public void navToDetailedImageFragment() {
        e.a((FragmentActivity) this).f().a(getIntent().getExtras()).t();
    }

    public void onViewUnsupportedImage(String str) {
        p.d(this, str);
    }
}
