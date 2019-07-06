package com.etsy.android.vespa.viewholders;

import android.view.View;
import com.etsy.android.lib.models.apiv3.BannerButton;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.vespa.a.a;
import kotlin.jvm.internal.p;

/* compiled from: FullBleedBannerViewHolder.kt */
public final class FullBleedBannerViewHolder$bindButton$trackingOnClickListener$1 extends TrackingOnClickListener {
    final /* synthetic */ BannerButton $buttonData;
    final /* synthetic */ FullBleedBannerViewHolder this$0;

    FullBleedBannerViewHolder$bindButton$trackingOnClickListener$1(FullBleedBannerViewHolder fullBleedBannerViewHolder, BannerButton bannerButton) {
        this.this$0 = fullBleedBannerViewHolder;
        this.$buttonData = bannerButton;
    }

    public void onViewClick(View view) {
        p.b(view, "v");
        this.this$0.trackUserClickedButtonEvent();
        a access$getMClickListener$p = this.this$0.mClickListener;
        if (access$getMClickListener$p != null) {
            BannerButton bannerButton = this.$buttonData;
            if (bannerButton == null) {
                p.a();
            }
            String url = bannerButton.getUrl();
            p.a((Object) url, "buttonData!!.url");
            a.a(access$getMClickListener$p, url, false, null, 6, null);
        }
    }
}
