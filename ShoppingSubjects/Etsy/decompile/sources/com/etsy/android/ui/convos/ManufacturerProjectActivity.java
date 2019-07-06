package com.etsy.android.ui.convos;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.R;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.models.convo.context.ManufacturerProject;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;
import org.parceler.d;

public class ManufacturerProjectActivity extends BOENavDrawerActivity implements a {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(R.string.manufacturer_project);
        if (bundle == null) {
            e.a((FragmentActivity) this).f().a("manufacturer_project").a((ManufacturerProject) d.a(getIntent().getExtras().getParcelable("manufacturer_project")));
        }
    }
}
