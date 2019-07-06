package com.etsy.android.ui.user;

import com.etsy.android.lib.c.e;
import com.etsy.android.lib.logger.l;
import dagger.a;

/* compiled from: ReceiptFragment_MembersInjector */
public final class g implements a<ReceiptFragment> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<e> b;
    private final javax.a.a<com.etsy.android.lib.f.a> c;
    private final javax.a.a<l> d;

    public g(javax.a.a<e> aVar, javax.a.a<com.etsy.android.lib.f.a> aVar2, javax.a.a<l> aVar3) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                if (a || aVar3 != null) {
                    this.d = aVar3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static a<ReceiptFragment> a(javax.a.a<e> aVar, javax.a.a<com.etsy.android.lib.f.a> aVar2, javax.a.a<l> aVar3) {
        return new g(aVar, aVar2, aVar3);
    }

    /* renamed from: a */
    public void injectMembers(ReceiptFragment receiptFragment) {
        if (receiptFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        receiptFragment.retrofit = (e) this.b.b();
        receiptFragment.schedulers = (com.etsy.android.lib.f.a) this.c.b();
        receiptFragment.logcat = (l) this.d.b();
    }
}
