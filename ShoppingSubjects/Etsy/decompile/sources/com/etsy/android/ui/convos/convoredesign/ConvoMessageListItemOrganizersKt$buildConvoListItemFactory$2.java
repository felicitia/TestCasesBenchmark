package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.ConversationMessage2;
import com.etsy.android.ui.convos.convoredesign.o.a;
import kotlin.jvm.a.r;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: ConvoMessageListItemOrganizers.kt */
final class ConvoMessageListItemOrganizersKt$buildConvoListItemFactory$2 extends Lambda implements r<ConversationMessage2, aj, String, ak, a> {
    public static final ConvoMessageListItemOrganizersKt$buildConvoListItemFactory$2 INSTANCE = new ConvoMessageListItemOrganizersKt$buildConvoListItemFactory$2();

    ConvoMessageListItemOrganizersKt$buildConvoListItemFactory$2() {
        super(4);
    }

    public final a invoke(ConversationMessage2 conversationMessage2, aj ajVar, String str, ak akVar) {
        p.b(conversationMessage2, "message");
        p.b(ajVar, "groupedMessageItem");
        p.b(str, "<anonymous parameter 2>");
        return new a(p.a(akVar, ajVar), conversationMessage2.getImages(), akVar);
    }
}
