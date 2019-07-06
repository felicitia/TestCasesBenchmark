package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.ConversationMessage2;
import com.etsy.android.ui.convos.convoredesign.o.c;
import kotlin.jvm.a.r;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: ConvoMessageListItemOrganizers.kt */
final class ConvoMessageListItemOrganizersKt$buildConvoListItemFactory$1 extends Lambda implements r<ConversationMessage2, aj, String, ak, c> {
    public static final ConvoMessageListItemOrganizersKt$buildConvoListItemFactory$1 INSTANCE = new ConvoMessageListItemOrganizersKt$buildConvoListItemFactory$1();

    ConvoMessageListItemOrganizersKt$buildConvoListItemFactory$1() {
        super(4);
    }

    public final c invoke(ConversationMessage2 conversationMessage2, aj ajVar, String str, ak akVar) {
        p.b(conversationMessage2, "message");
        p.b(ajVar, "groupedMessageItem");
        p.b(str, "avatarUrl");
        return new c(p.a(akVar, ajVar), conversationMessage2.getImages(), str, akVar);
    }
}
