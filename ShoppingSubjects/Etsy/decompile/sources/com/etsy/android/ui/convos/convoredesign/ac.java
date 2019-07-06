package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.ConversationUser;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: ConvoThreadFragmentModule_ProvidesCurrentUserFactory */
public final class ac implements c<ConversationUser> {
    static final /* synthetic */ boolean a = true;
    private final a<ConvoActivity> b;

    public ac(a<ConvoActivity> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public ConversationUser b() {
        return (ConversationUser) f.a(x.a((ConvoActivity) this.b.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<ConversationUser> a(a<ConvoActivity> aVar) {
        return new ac(aVar);
    }
}
