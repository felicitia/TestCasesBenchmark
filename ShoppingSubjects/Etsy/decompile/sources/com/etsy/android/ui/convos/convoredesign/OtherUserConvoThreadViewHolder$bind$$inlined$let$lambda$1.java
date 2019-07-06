package com.etsy.android.ui.convos.convoredesign;

import android.view.View;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: OtherUserConvoThreadViewHolder.kt */
final class OtherUserConvoThreadViewHolder$bind$$inlined$let$lambda$1 extends Lambda implements b<View, h> {
    final /* synthetic */ ak $linkCard;
    final /* synthetic */ OtherUserConvoThreadViewHolder this$0;

    OtherUserConvoThreadViewHolder$bind$$inlined$let$lambda$1(ak akVar, OtherUserConvoThreadViewHolder otherUserConvoThreadViewHolder) {
        this.$linkCard = akVar;
        this.this$0 = otherUserConvoThreadViewHolder;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return h.a;
    }

    public final void invoke(View view) {
        this.this$0.linkCardClickListener.a(this.$linkCard.d());
    }
}
