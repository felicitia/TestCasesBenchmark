package com.etsy.android.uikit;

import android.os.Bundle;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;

public class CustomViewDemoActivity extends BaseActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(k.activity_custom_view_demo);
        setTitle(getString(o.custom_view_demo));
    }
}
