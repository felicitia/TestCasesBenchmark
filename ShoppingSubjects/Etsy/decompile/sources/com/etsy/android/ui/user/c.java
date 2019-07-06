package com.etsy.android.ui.user;

import com.etsy.android.lib.logger.l;
import dagger.a;

/* compiled from: PhabletsFragment_MembersInjector */
public final class c implements a<PhabletsFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<l> b;

    public c(javax.a.a<l> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<PhabletsFragment> a(javax.a.a<l> aVar) {
        return new c(aVar);
    }

    /* renamed from: a */
    public void injectMembers(PhabletsFragment phabletsFragment) {
        if (phabletsFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        phabletsFragment.log = (l) this.b.b();
    }
}
