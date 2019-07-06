package com.etsy.android.vespa.viewholders;

import android.view.View;
import android.widget.TextView;
import com.etsy.android.lib.models.apiv3.ExploreSegment;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import java.util.Collection;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: ExploreSegmentListViewHolder.kt */
final class ExploreSegmentListViewHolder$addSegmentChip$$inlined$apply$lambda$1 extends Lambda implements b<View, h> {
    final /* synthetic */ ServerDrivenAction $action$inlined;
    final /* synthetic */ ExploreSegment $segment$inlined;
    final /* synthetic */ Collection $segmentsList$inlined;
    final /* synthetic */ TextView receiver$0;
    final /* synthetic */ ExploreSegmentListViewHolder this$0;

    ExploreSegmentListViewHolder$addSegmentChip$$inlined$apply$lambda$1(TextView textView, ExploreSegmentListViewHolder exploreSegmentListViewHolder, ExploreSegment exploreSegment, ServerDrivenAction serverDrivenAction, Collection collection) {
        this.receiver$0 = textView;
        this.this$0 = exploreSegmentListViewHolder;
        this.$segment$inlined = exploreSegment;
        this.$action$inlined = serverDrivenAction;
        this.$segmentsList$inlined = collection;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return h.a;
    }

    public final void invoke(View view) {
        if (this.$action$inlined != null) {
            for (ExploreSegment exploreSegment : this.$segmentsList$inlined) {
                exploreSegment.setSelected(exploreSegment == this.$segment$inlined);
            }
            ExploreSegmentListViewHolder exploreSegmentListViewHolder = this.this$0;
            String key = this.$segment$inlined.getKey();
            p.a((Object) key, "segment.key");
            exploreSegmentListViewHolder.setChipSelected(key);
            this.$action$inlined.addParam("explore_segment_key", this.$segment$inlined.getKey());
            this.this$0.clickHandler.c(this.receiver$0.getRootView(), this.$action$inlined);
        }
    }
}
