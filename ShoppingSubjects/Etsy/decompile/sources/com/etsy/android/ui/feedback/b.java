package com.etsy.android.ui.feedback;

import com.etsy.android.lib.core.http.request.EtsyApiV3Request.a;
import com.etsy.android.lib.models.apiv3.Feedback;
import dagger.internal.c;
import dagger.internal.f;

/* compiled from: FeedbackActivityModule_ProvideEtsyApiV3FeedbackBuilderFactory */
public final class b implements c<a<Feedback>> {
    static final /* synthetic */ boolean a = true;
    private final a b;

    public b(a aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public a<Feedback> b() {
        return (a) f.a(this.b.a(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<a<Feedback>> a(a aVar) {
        return new b(aVar);
    }
}
