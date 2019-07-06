package com.etsy.android.ui.convos.convolistredesign;

import android.view.View;
import com.etsy.android.lib.models.Conversation3;
import com.etsy.android.ui.convos.convolistredesign.ConvoAdapter.ConvoItemViewHolder;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: ConvoAdapter.kt */
final class ConvoAdapter$ConvoItemViewHolder$bind$1 extends Lambda implements b<View, h> {
    final /* synthetic */ Conversation3 $conversation;
    final /* synthetic */ ConvoItemViewHolder this$0;

    ConvoAdapter$ConvoItemViewHolder$bind$1(ConvoItemViewHolder convoItemViewHolder, Conversation3 conversation3) {
        this.this$0 = convoItemViewHolder;
        this.$conversation = conversation3;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return h.a;
    }

    public final void invoke(View view) {
        this.this$0.this$0.getOnConvoClicked().invoke(this.$conversation);
    }
}
