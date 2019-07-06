package com.etsy.android.vespa.viewholders;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

/* compiled from: FullBleedBannerViewHolder.kt */
public final class FullBleedBannerViewHolder$animateBubbles$1 implements OnGlobalLayoutListener {
    final /* synthetic */ FullBleedBannerViewHolder this$0;

    FullBleedBannerViewHolder$animateBubbles$1(FullBleedBannerViewHolder fullBleedBannerViewHolder) {
        this.this$0 = fullBleedBannerViewHolder;
    }

    public void onGlobalLayout() {
        this.this$0.mImageLeft.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        this.this$0.animateImages(this.this$0.mImageLeft, true);
        this.this$0.animationMap.put(this.this$0.mImageLeft, Boolean.valueOf(true));
    }
}
