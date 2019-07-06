package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.datatypes.EtsyId;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: ConvoThreadFragmentModule_ProvidesEtsyIdFactory */
public final class ad implements c<EtsyId> {
    static final /* synthetic */ boolean a = true;
    private final a<ConvoThreadFragment2> b;

    public ad(a<ConvoThreadFragment2> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public EtsyId b() {
        return (EtsyId) f.a(x.a((ConvoThreadFragment2) this.b.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<EtsyId> a(a<ConvoThreadFragment2> aVar) {
        return new ad(aVar);
    }
}
