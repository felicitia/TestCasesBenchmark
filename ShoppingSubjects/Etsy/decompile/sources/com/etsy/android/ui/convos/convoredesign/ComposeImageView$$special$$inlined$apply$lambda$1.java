package com.etsy.android.ui.convos.convoredesign;

import android.view.View;
import com.etsy.android.ui.convos.convoredesign.ComposeImageView.a;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: ComposeImageView.kt */
final class ComposeImageView$$special$$inlined$apply$lambda$1 extends Lambda implements b<View, h> {
    final /* synthetic */ ComposeImageView this$0;

    ComposeImageView$$special$$inlined$apply$lambda$1(ComposeImageView composeImageView) {
        this.this$0 = composeImageView;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return h.a;
    }

    public final void invoke(View view) {
        a removeClickListener = this.this$0.getRemoveClickListener();
        if (removeClickListener != null) {
            removeClickListener.a();
        }
    }
}
