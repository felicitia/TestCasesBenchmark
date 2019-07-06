package com.etsy.android.vespa.viewholders;

import android.view.View;
import com.etsy.android.lib.models.apiv3.Segment;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: DeepLinkSegmentListViewHolder.kt */
final class DeepLinkSegmentListViewHolder$addSegmentChip$$inlined$apply$lambda$1 extends Lambda implements b<View, h> {
    final /* synthetic */ Segment $segment$inlined;
    final /* synthetic */ DeepLinkSegmentListViewHolder this$0;

    DeepLinkSegmentListViewHolder$addSegmentChip$$inlined$apply$lambda$1(DeepLinkSegmentListViewHolder deepLinkSegmentListViewHolder, Segment segment) {
        this.this$0 = deepLinkSegmentListViewHolder;
        this.$segment$inlined = segment;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return h.a;
    }

    public final void invoke(View view) {
        this.this$0.getClickHandler().a(this.$segment$inlined);
    }
}
