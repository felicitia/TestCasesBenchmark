package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.models.ConversationUser;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.lib.util.sharedprefs.b;
import dagger.internal.c;
import javax.a.a;

/* compiled from: ConvoThreadPresenter_Factory */
public final class ah implements c<af> {
    static final /* synthetic */ boolean a = true;
    private final a<c> b;
    private final a<ai> c;
    private final a<com.etsy.android.lib.f.a> d;
    private final a<EtsyId> e;
    private final a<ConversationUser> f;
    private final a<com.etsy.android.lib.util.b.a> g;
    private final a<NetworkUtils> h;
    private final a<b> i;
    private final a<t> j;

    public ah(a<c> aVar, a<ai> aVar2, a<com.etsy.android.lib.f.a> aVar3, a<EtsyId> aVar4, a<ConversationUser> aVar5, a<com.etsy.android.lib.util.b.a> aVar6, a<NetworkUtils> aVar7, a<b> aVar8, a<t> aVar9) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                if (a || aVar3 != null) {
                    this.d = aVar3;
                    if (a || aVar4 != null) {
                        this.e = aVar4;
                        if (a || aVar5 != null) {
                            this.f = aVar5;
                            if (a || aVar6 != null) {
                                this.g = aVar6;
                                if (a || aVar7 != null) {
                                    this.h = aVar7;
                                    if (a || aVar8 != null) {
                                        this.i = aVar8;
                                        if (a || aVar9 != null) {
                                            this.j = aVar9;
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
                        throw new AssertionError();
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
    public af b() {
        af afVar = new af((c) this.b.b(), (ai) this.c.b(), (com.etsy.android.lib.f.a) this.d.b(), (EtsyId) this.e.b(), (ConversationUser) this.f.b(), (com.etsy.android.lib.util.b.a) this.g.b(), (NetworkUtils) this.h.b(), (b) this.i.b(), (t) this.j.b());
        return afVar;
    }

    public static c<af> a(a<c> aVar, a<ai> aVar2, a<com.etsy.android.lib.f.a> aVar3, a<EtsyId> aVar4, a<ConversationUser> aVar5, a<com.etsy.android.lib.util.b.a> aVar6, a<NetworkUtils> aVar7, a<b> aVar8, a<t> aVar9) {
        ah ahVar = new ah(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9);
        return ahVar;
    }
}
