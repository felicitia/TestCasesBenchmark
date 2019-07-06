package com.etsy.android.ui.convos;

import com.etsy.android.lib.c.e;
import com.etsy.android.lib.requests.apiv3.UserEndpoint;
import kotlin.jvm.internal.p;

/* compiled from: ConvoComposeFragmentModule.kt */
public final class f {
    public final UserEndpoint a(e eVar) {
        p.b(eVar, "configuredRetrofit");
        Object a = eVar.a().a(UserEndpoint.class);
        p.a(a, "configuredRetrofit.v3mos…UserEndpoint::class.java)");
        return (UserEndpoint) a;
    }
}
