package com.etsy.android.ui.convos.convolistredesign;

import com.etsy.android.lib.models.Conversation3;
import com.etsy.android.lib.models.ResponseConstants;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: ConvosListFragment.kt */
final class ConvosListFragment$onCreateView$2 extends Lambda implements b<Conversation3, h> {
    final /* synthetic */ ConvosListFragment this$0;

    ConvosListFragment$onCreateView$2(ConvosListFragment convosListFragment) {
        this.this$0 = convosListFragment;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Conversation3) obj);
        return h.a;
    }

    public final void invoke(Conversation3 conversation3) {
        p.b(conversation3, ResponseConstants.CONVO);
        this.this$0.getPresenter().a(conversation3);
    }
}
