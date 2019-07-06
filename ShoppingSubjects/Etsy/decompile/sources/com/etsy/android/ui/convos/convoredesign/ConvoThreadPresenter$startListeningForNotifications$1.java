package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.Conversation3;
import com.etsy.android.lib.models.ConvoUser;
import com.etsy.android.lib.models.convo.ConversationThread2;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.text.m;

/* compiled from: ConvoThreadPresenter.kt */
final class ConvoThreadPresenter$startListeningForNotifications$1 extends Lambda implements b<s, h> {
    final /* synthetic */ af this$0;

    ConvoThreadPresenter$startListeningForNotifications$1(af afVar) {
        this.this$0 = afVar;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((s) obj);
        return h.a;
    }

    public final void invoke(s sVar) {
        CharSequence charSequence;
        ConversationThread2 c = this.this$0.f;
        if (c != null) {
            Conversation3 conversation = c.getConversation();
            if (conversation != null) {
                ConvoUser otherUser = conversation.getOtherUser();
                if (otherUser != null) {
                    charSequence = otherUser.getUsername();
                    if (charSequence != null && m.a((CharSequence) sVar.a(), charSequence, false, 2, (Object) null)) {
                        this.this$0.m.refreshConvo();
                        return;
                    }
                }
            }
        }
        charSequence = null;
        if (charSequence != null) {
        }
    }
}
