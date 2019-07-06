package com.etsy.android.ui.search.v2.a;

import com.etsy.android.ui.search.v2.impressions.SearchImpressionsDatabase;
import com.etsy.android.ui.search.v2.impressions.a;
import dagger.internal.c;
import dagger.internal.f;

/* compiled from: SearchImpressionsDbModule_ProvideSearchImpressionDaoFactory */
public final class b implements c<a> {
    static final /* synthetic */ boolean a = true;
    private final a b;
    private final javax.a.a<SearchImpressionsDatabase> c;

    public b(a aVar, javax.a.a<SearchImpressionsDatabase> aVar2) {
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
    public a b() {
        return (a) f.a(this.b.a((SearchImpressionsDatabase) this.c.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<a> a(a aVar, javax.a.a<SearchImpressionsDatabase> aVar2) {
        return new b(aVar, aVar2);
    }
}
