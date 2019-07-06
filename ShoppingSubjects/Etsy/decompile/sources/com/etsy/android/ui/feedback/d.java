package com.etsy.android.ui.feedback;

import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.apiv3.Feedback;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: FeedbackActivityModule_ProvideFeedbackRepositoryFactory */
public final class d implements c<i> {
    static final /* synthetic */ boolean a = true;
    private final a b;
    private final a<EtsyApiV3Request.a<Feedback>> c;
    private final a<com.etsy.android.lib.core.http.request.d.a<Feedback>> d;
    private final a<v> e;

    public d(a aVar, a<EtsyApiV3Request.a<Feedback>> aVar2, a<com.etsy.android.lib.core.http.request.d.a<Feedback>> aVar3, a<v> aVar4) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                if (a || aVar3 != null) {
                    this.d = aVar3;
                    if (a || aVar4 != null) {
                        this.e = aVar4;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public i b() {
        return (i) f.a(this.b.a((EtsyApiV3Request.a) this.c.b(), (com.etsy.android.lib.core.http.request.d.a) this.d.b(), (v) this.e.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<i> a(a aVar, a<EtsyApiV3Request.a<Feedback>> aVar2, a<com.etsy.android.lib.core.http.request.d.a<Feedback>> aVar3, a<v> aVar4) {
        return new d(aVar, aVar2, aVar3, aVar4);
    }
}
