package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.R;
import com.etsy.android.ui.convos.convoredesign.af.f;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: ConvoThreadPresenter.kt */
final class ConvoThreadPresenter$linkCardBoundListener$1$onLinkCardBound$2 extends Lambda implements b<Throwable, h> {
    final /* synthetic */ f this$0;

    ConvoThreadPresenter$linkCardBoundListener$1$onLinkCardBound$2(f fVar) {
        this.this$0 = fVar;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return h.a;
    }

    public final void invoke(Throwable th) {
        p.b(th, "it");
        this.this$0.a.m.showErrorSnackbar(R.string.network_unavailable);
    }
}
