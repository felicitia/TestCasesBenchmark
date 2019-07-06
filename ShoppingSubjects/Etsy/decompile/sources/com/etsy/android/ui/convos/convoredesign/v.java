package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.logger.l;
import dagger.a;

/* compiled from: ConvoThreadFragment2_MembersInjector */
public final class v implements a<ConvoThreadFragment2> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<ae> b;
    private final javax.a.a<af> c;
    private final javax.a.a<l> d;

    public v(javax.a.a<ae> aVar, javax.a.a<af> aVar2, javax.a.a<l> aVar3) {
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

    public static a<ConvoThreadFragment2> a(javax.a.a<ae> aVar, javax.a.a<af> aVar2, javax.a.a<l> aVar3) {
        return new v(aVar, aVar2, aVar3);
    }

    /* renamed from: a */
    public void injectMembers(ConvoThreadFragment2 convoThreadFragment2) {
        if (convoThreadFragment2 == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        convoThreadFragment2.imageHelper = (ae) this.b.b();
        convoThreadFragment2.presenter = (af) this.c.b();
        convoThreadFragment2.logCat = (l) this.d.b();
    }
}
