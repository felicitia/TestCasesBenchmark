package com.etsy.android.ui.convos.convolistredesign;

import android.view.View;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.convos.convolistredesign.ConvoAdapter.ConvoItemViewHolder;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: ConvoAdapter.kt */
final class ConvoAdapter$ConvoItemViewHolder$bind$2 extends Lambda implements b<View, h> {
    final /* synthetic */ boolean $isGuest;
    final /* synthetic */ EtsyId $userId;
    final /* synthetic */ ConvoItemViewHolder this$0;

    ConvoAdapter$ConvoItemViewHolder$bind$2(ConvoItemViewHolder convoItemViewHolder, boolean z, EtsyId etsyId) {
        this.this$0 = convoItemViewHolder;
        this.$isGuest = z;
        this.$userId = etsyId;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return h.a;
    }

    public final void invoke(View view) {
        this.this$0.this$0.getOnUserClicked().invoke(Boolean.valueOf(this.$isGuest), this.$userId);
    }
}
