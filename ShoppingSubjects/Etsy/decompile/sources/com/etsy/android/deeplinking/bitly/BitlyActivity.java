package com.etsy.android.deeplinking.bitly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.a.a;
import com.etsy.android.lib.logger.f;

public class BitlyActivity extends FragmentActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f.d("onCreate");
        handleIntent(getIntent());
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        f.d("onNewIntent");
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (a.a()) {
            a.a(intent);
        }
        finish();
    }
}
