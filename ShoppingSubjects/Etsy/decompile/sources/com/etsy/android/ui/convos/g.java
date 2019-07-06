package com.etsy.android.ui.convos;

import com.etsy.android.lib.c.e;
import com.etsy.android.lib.requests.apiv3.UserEndpoint;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: ConvoComposeFragmentModule_ProvidesPublicUserByIdFactory */
public final class g implements c<UserEndpoint> {
    static final /* synthetic */ boolean a = true;
    private final f b;
    private final a<e> c;

    public g(f fVar, a<e> aVar) {
        if (a || fVar != null) {
            this.b = fVar;
            if (a || aVar != null) {
                this.c = aVar;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public UserEndpoint b() {
        return (UserEndpoint) f.a(this.b.a((e) this.c.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<UserEndpoint> a(f fVar, a<e> aVar) {
        return new g(fVar, aVar);
    }
}
