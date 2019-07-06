package com.etsy.android.deeplinking;

import android.content.Context;
import android.content.Intent;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.util.b;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.usebutton.merchant.e;
import com.usebutton.merchant.n;
import kotlin.jvm.internal.p;

/* compiled from: Button.kt */
public final class a {
    private final Context a;

    public a(Context context) {
        p.b(context, ResponseConstants.CONTEXT);
        this.a = context;
    }

    public final void a() {
        e.a(this.a, b.g);
    }

    public final void a(n nVar) {
        p.b(nVar, CastExtraArgs.LISTENER);
        e.a(this.a, nVar);
    }

    public final void a(Intent intent) {
        p.b(intent, "intent");
        e.a(this.a, intent);
    }
}
