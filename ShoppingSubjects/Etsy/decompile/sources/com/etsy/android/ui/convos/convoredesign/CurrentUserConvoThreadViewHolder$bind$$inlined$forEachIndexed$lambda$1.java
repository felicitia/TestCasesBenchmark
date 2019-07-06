package com.etsy.android.ui.convos.convoredesign;

import android.view.View;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.ImageInfo;
import com.etsy.android.ui.convos.convoredesign.o.a;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import kotlin.jvm.internal.p;

/* compiled from: CurrentUserConvoThreadViewHolder.kt */
public final class CurrentUserConvoThreadViewHolder$bind$$inlined$forEachIndexed$lambda$1 extends TrackingOnClickListener {
    final /* synthetic */ ImageInfo $image;
    final /* synthetic */ int $index;
    final /* synthetic */ a $item$inlined;
    final /* synthetic */ CurrentUserConvoThreadViewHolder this$0;

    CurrentUserConvoThreadViewHolder$bind$$inlined$forEachIndexed$lambda$1(int i, ImageInfo imageInfo, i[] iVarArr, CurrentUserConvoThreadViewHolder currentUserConvoThreadViewHolder, a aVar) {
        this.$index = i;
        this.$image = imageInfo;
        this.this$0 = currentUserConvoThreadViewHolder;
        this.$item$inlined = aVar;
        super(iVarArr);
    }

    public void onViewClick(View view) {
        p.b(view, "view");
        this.this$0.imageClickListener.a(this.$index, this.$item$inlined.b());
    }
}
