package com.etsy.android.ui.search.v2;

import androidx.work.j;
import com.etsy.android.lib.logger.l;
import com.etsy.android.ui.search.v2.impressions.d;
import dagger.a;

/* compiled from: SearchResultsListingsFragment_MembersInjector */
public final class k implements a<SearchResultsListingsFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<d> b;
    private final javax.a.a<com.etsy.android.lib.f.a> c;
    private final javax.a.a<l> d;
    private final javax.a.a<j> e;

    public k(javax.a.a<d> aVar, javax.a.a<com.etsy.android.lib.f.a> aVar2, javax.a.a<l> aVar3, javax.a.a<j> aVar4) {
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

    public static a<SearchResultsListingsFragment> a(javax.a.a<d> aVar, javax.a.a<com.etsy.android.lib.f.a> aVar2, javax.a.a<l> aVar3, javax.a.a<j> aVar4) {
        return new k(aVar, aVar2, aVar3, aVar4);
    }

    /* renamed from: a */
    public void injectMembers(SearchResultsListingsFragment searchResultsListingsFragment) {
        if (searchResultsListingsFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        searchResultsListingsFragment.searchImpressionRepository = (d) this.b.b();
        searchResultsListingsFragment.schedulers = (com.etsy.android.lib.f.a) this.c.b();
        searchResultsListingsFragment.logCat = (l) this.d.b();
        searchResultsListingsFragment.workManager = (j) this.e.b();
    }
}
