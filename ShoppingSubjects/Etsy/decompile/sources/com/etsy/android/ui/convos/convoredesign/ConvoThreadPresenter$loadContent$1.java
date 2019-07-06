package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.convo.ConversationThread2;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: ConvoThreadPresenter.kt */
final class ConvoThreadPresenter$loadContent$1 extends Lambda implements b<ConversationThread2, h> {
    final /* synthetic */ af this$0;

    ConvoThreadPresenter$loadContent$1(af afVar) {
        this.this$0 = afVar;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ConversationThread2) obj);
        return h.a;
    }

    public final void invoke(ConversationThread2 conversationThread2) {
        af afVar = this.this$0;
        p.a((Object) conversationThread2, "result");
        afVar.a(conversationThread2);
        if (!this.this$0.g.isEmpty()) {
            this.this$0.a(this.this$0.g);
        }
    }
}
