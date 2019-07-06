package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.util.CameraHelper;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: ConvoThreadFragmentModule_ProvidesConvoThreadImageHelperFactory */
public final class aa implements c<ae> {
    static final /* synthetic */ boolean a = true;
    private final a<af> b;
    private final a<ConvoThreadFragment2> c;
    private final a<CameraHelper> d;
    private final a<l> e;

    public aa(a<af> aVar, a<ConvoThreadFragment2> aVar2, a<CameraHelper> aVar3, a<l> aVar4) {
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
    public ae b() {
        return (ae) f.a(x.a((af) this.b.b(), (ConvoThreadFragment2) this.c.b(), (CameraHelper) this.d.b(), (l) this.e.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<ae> a(a<af> aVar, a<ConvoThreadFragment2> aVar2, a<CameraHelper> aVar3, a<l> aVar4) {
        return new aa(aVar, aVar2, aVar3, aVar4);
    }
}
