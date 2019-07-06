package com.etsy.android.ui.core;

import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.ShopAboutVideo;
import com.etsy.android.ui.nav.e;
import org.parceler.d;

public class ShopAboutDetailedImageActivity extends DetailedImageActivity {
    /* access modifiers changed from: protected */
    public boolean hasImages() {
        ShopAboutVideo shopAboutVideo = (ShopAboutVideo) d.a(getIntent().getParcelableExtra("shop_about_video"));
        return super.hasImages() || (shopAboutVideo != null && shopAboutVideo.videoIsReady());
    }

    /* access modifiers changed from: protected */
    public void navToDetailedImageFragment() {
        e.a((FragmentActivity) this).f().a(getIntent().getExtras()).u();
    }
}
