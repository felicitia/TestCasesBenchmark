package com.etsy.android.ui.convos.convoredesign;

import android.view.View;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.ImageInfo;
import com.etsy.android.ui.convos.convoredesign.o.c;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import kotlin.jvm.internal.p;

/* compiled from: OtherUserConvoThreadViewHolder.kt */
public final class OtherUserConvoThreadViewHolder$bind$$inlined$forEachIndexed$lambda$1 extends TrackingOnClickListener {
    final /* synthetic */ ImageInfo $image;
    final /* synthetic */ int $index;
    final /* synthetic */ c $item$inlined;
    final /* synthetic */ OtherUserConvoThreadViewHolder this$0;

    OtherUserConvoThreadViewHolder$bind$$inlined$forEachIndexed$lambda$1(int i, ImageInfo imageInfo, i[] iVarArr, OtherUserConvoThreadViewHolder otherUserConvoThreadViewHolder, c cVar) {
        this.$index = i;
        this.$image = imageInfo;
        this.this$0 = otherUserConvoThreadViewHolder;
        this.$item$inlined = cVar;
        super(iVarArr);
    }

    public void onViewClick(View view) {
        p.b(view, "view");
        this.this$0.imageClickListener.a(this.$index, this.$item$inlined.b());
    }
}
