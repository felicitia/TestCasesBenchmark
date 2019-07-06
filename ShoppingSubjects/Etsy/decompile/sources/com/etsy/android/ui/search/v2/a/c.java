package com.etsy.android.ui.search.v2.a;

import android.content.Context;
import com.etsy.android.ui.search.v2.impressions.SearchImpressionsDatabase;
import dagger.internal.f;
import javax.a.a;

/* compiled from: SearchImpressionsDbModule_ProvideSearchImpressionsDatabaseFactory */
public final class c implements dagger.internal.c<SearchImpressionsDatabase> {
    static final /* synthetic */ boolean a = true;
    private final a b;
    private final a<Context> c;

    public c(a aVar, a<Context> aVar2) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public SearchImpressionsDatabase b() {
        return (SearchImpressionsDatabase) f.a(this.b.a((Context) this.c.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static dagger.internal.c<SearchImpressionsDatabase> a(a aVar, a<Context> aVar2) {
        return new c(aVar, aVar2);
    }
}
