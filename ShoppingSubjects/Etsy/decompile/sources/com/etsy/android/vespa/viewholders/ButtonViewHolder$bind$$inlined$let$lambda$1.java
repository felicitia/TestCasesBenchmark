package com.etsy.android.vespa.viewholders;

import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.etsy.android.lib.models.apiv3.Button;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: ButtonViewHolder.kt */
final class ButtonViewHolder$bind$$inlined$let$lambda$1 extends Lambda implements b<View, h> {
    final /* synthetic */ Button $data$inlined;
    final /* synthetic */ Button $it$inlined;
    final /* synthetic */ LayoutParams $lp$inlined;
    final /* synthetic */ ButtonViewHolder this$0;

    ButtonViewHolder$bind$$inlined$let$lambda$1(Button button, LayoutParams layoutParams, ButtonViewHolder buttonViewHolder, Button button2) {
        this.$it$inlined = button;
        this.$lp$inlined = layoutParams;
        this.this$0 = buttonViewHolder;
        this.$data$inlined = button2;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return h.a;
    }

    public final void invoke(View view) {
        this.this$0.getClickHandler().a(this.$data$inlined);
    }
}
