package com.etsy.android.ui.convos;

import com.etsy.android.lib.requests.apiv3.UserEndpoint;
import dagger.internal.c;
import javax.a.a;

/* compiled from: ConvoRepository_Factory */
public final class j implements c<i> {
    static final /* synthetic */ boolean a = true;
    private final a<UserEndpoint> b;

    public j(a<UserEndpoint> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public i b() {
        return new i((UserEndpoint) this.b.b());
    }

    public static c<i> a(a<UserEndpoint> aVar) {
        return new j(aVar);
    }
}
