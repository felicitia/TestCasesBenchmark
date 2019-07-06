package com.etsy.android.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.ui.nav.e;

public class BOEVespaDemoActivity extends BOENavDrawerActivity {
    public static final String MOCK_FILE_NAME = "mock_file";

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle("BOE Vespa Demo");
        if (bundle == null) {
            e.a((FragmentActivity) this).f().A();
        }
    }
}
