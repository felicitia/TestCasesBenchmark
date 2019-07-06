package com.contextlogic.wish.activity.facebook;

import android.content.Intent;
import android.os.Bundle;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.link.DeepLinkActivity;

public class WishFacebookDeepLinkActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isHeadlessActivity() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean requiresAuthentication() {
        return false;
    }

    public void handleOnCreate(Bundle bundle) {
        super.handleOnCreate(bundle);
        Intent intent = new Intent();
        try {
            intent.setData(getIntent().getData());
        } catch (Throwable unused) {
        }
        intent.setClass(this, DeepLinkActivity.class);
        startActivity(intent);
        finish();
    }
}
