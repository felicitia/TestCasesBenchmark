package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.Conversation3;
import com.etsy.android.lib.models.convo.ConversationThread2;
import kotlin.h;
import kotlin.jvm.a.a;
import kotlin.jvm.internal.Lambda;

/* compiled from: ConvoThreadPresenter.kt */
final class ConvoThreadPresenter$toggleReadState$$inlined$let$lambda$1 extends Lambda implements a<h> {
    final /* synthetic */ boolean $isRead;
    final /* synthetic */ boolean $shouldGoBack$inlined;
    final /* synthetic */ af this$0;

    ConvoThreadPresenter$toggleReadState$$inlined$let$lambda$1(boolean z, af afVar, boolean z2) {
        this.$isRead = z;
        this.this$0 = afVar;
        this.$shouldGoBack$inlined = z2;
        super(0);
    }

    public final void invoke() {
        ConversationThread2 c = this.this$0.f;
        if (c != null) {
            Conversation3 conversation = c.getConversation();
            if (conversation != null) {
                conversation.setRead(!this.$isRead);
            }
        }
        this.this$0.m.setShouldRefresh();
        if (this.$shouldGoBack$inlined) {
            this.this$0.m.goBack();
        } else {
            this.this$0.m.reloadOptionsMenu();
        }
    }
}
