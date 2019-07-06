package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.R;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: ConvoThreadPresenter.kt */
final class ConvoThreadPresenter$loadContent$2 extends Lambda implements b<Throwable, h> {
    final /* synthetic */ af this$0;

    ConvoThreadPresenter$loadContent$2(af afVar) {
        this.this$0 = afVar;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return h.a;
    }

    public final void invoke(Throwable th) {
        p.b(th, "it");
        this.this$0.m.showErrorSnackbar(R.string.no_internet);
        this.this$0.m.stopRefreshing();
    }
}
