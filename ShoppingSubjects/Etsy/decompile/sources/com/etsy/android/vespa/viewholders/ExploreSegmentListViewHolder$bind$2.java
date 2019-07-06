package com.etsy.android.vespa.viewholders;

import android.view.View;
import android.view.View.OnLayoutChangeListener;

/* compiled from: ExploreSegmentListViewHolder.kt */
public final class ExploreSegmentListViewHolder$bind$2 implements OnLayoutChangeListener {
    final /* synthetic */ ExploreSegmentListViewHolder this$0;

    ExploreSegmentListViewHolder$bind$2(ExploreSegmentListViewHolder exploreSegmentListViewHolder) {
        this.this$0 = exploreSegmentListViewHolder;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.this$0.itemView.removeOnLayoutChangeListener(this);
        this.this$0.scrollToSelectedSegment();
    }
}
