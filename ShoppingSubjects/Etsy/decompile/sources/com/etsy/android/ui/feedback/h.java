package com.etsy.android.ui.feedback;

import dagger.internal.c;
import javax.a.a;

/* compiled from: FeedbackPresenter_Factory */
public final class h implements c<g> {
    static final /* synthetic */ boolean a = true;
    private final a<i> b;

    public h(a<i> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public g b() {
        return new g((i) this.b.b());
    }

    public static c<g> a(a<i> aVar) {
        return new h(aVar);
    }
}
