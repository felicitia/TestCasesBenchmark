package com.etsy.android.ui.feedback;

import com.etsy.android.lib.core.http.request.d.a;
import com.etsy.android.lib.models.apiv3.Feedback;
import dagger.internal.f;

/* compiled from: FeedbackActivityModule_ProvideEtsyV3RequestJobFeedbackBuilderFactory */
public final class c implements dagger.internal.c<a<Feedback>> {
    static final /* synthetic */ boolean a = true;
    private final a b;

    public c(a aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public a<Feedback> b() {
        return (a) f.a(this.b.b(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static dagger.internal.c<a<Feedback>> a(a aVar) {
        return new c(aVar);
    }
}
