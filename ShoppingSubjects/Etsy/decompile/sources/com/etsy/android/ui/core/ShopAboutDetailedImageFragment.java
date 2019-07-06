package com.etsy.android.ui.core;

import android.app.Activity;
import android.os.Bundle;
import com.etsy.android.lib.logger.j;
import com.etsy.android.lib.models.ShopAboutVideo;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.nav.b;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.adapter.FullImagesPagerAdapter;
import com.etsy.android.uikit.nav.ActivityNavigator.AnimationMode;
import org.parceler.d;

public class ShopAboutDetailedImageFragment extends DetailedImageFragment {
    private EtsyId mShopId;
    private ShopAboutVideo mVideo;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mVideo = (ShopAboutVideo) d.a(arguments.getParcelable("shop_about_video"));
        this.mShopId = (EtsyId) d.a(arguments.getParcelable("shop_id"));
        if (this.mVideo != null) {
            addImage(0, this.mVideo.getThumbnail());
        }
    }

    public void onImageClick(int i) {
        if (i == 0 && this.mVideo != null) {
            ((b) e.a((Activity) this.mActivity).a(AnimationMode.FADE_IN_OUT).a((j) this)).a(this.mShopId, this.mVideo.getVideoUrl());
        }
    }

    /* access modifiers changed from: protected */
    public FullImagesPagerAdapter createAdapter() {
        FullImagesPagerAdapter createAdapter = super.createAdapter();
        createAdapter.setHasVideo(this.mVideo != null);
        return createAdapter;
    }
}
