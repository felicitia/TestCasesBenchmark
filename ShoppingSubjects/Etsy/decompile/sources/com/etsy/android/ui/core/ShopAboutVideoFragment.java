package com.etsy.android.ui.core;

import android.os.Bundle;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.a.a;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.uikit.ui.core.VideoFragment;
import java.util.HashMap;

public class ShopAboutVideoFragment extends VideoFragment {
    /* access modifiers changed from: private */
    public EtsyId mShopId;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mShopId = new EtsyId(getArguments().getString("shop_id"));
    }

    /* access modifiers changed from: protected */
    public void onVideoPrepared() {
        w analyticsContext = getAnalyticsContext();
        if (analyticsContext != null) {
            analyticsContext.a("shop_about_video_played", new HashMap<AnalyticsLogAttribute, Object>() {
                {
                    put(AnalyticsLogAttribute.SHOP_ID, ShopAboutVideoFragment.this.mShopId);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onVideoError() {
        super.onVideoError();
        w analyticsContext = getAnalyticsContext();
        if (analyticsContext != null) {
            analyticsContext.a("shop_about_video_playback_error", new HashMap<AnalyticsLogAttribute, Object>() {
                {
                    put(AnalyticsLogAttribute.SHOP_ID, ShopAboutVideoFragment.this.mShopId);
                }
            });
        }
        a.a("shop.about.video.playback_error");
    }
}
