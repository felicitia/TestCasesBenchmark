package com.etsy.android.ui.convos.convoredesign;

import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref.IntRef;
import kotlin.jvm.internal.p;

/* compiled from: ConvoThreadPresenter.kt */
final class ConvoThreadPresenter$sendUnsentDrafts$2 extends Lambda implements b<Throwable, h> {
    final /* synthetic */ IntRef $draftsToSend;
    final /* synthetic */ af this$0;

    ConvoThreadPresenter$sendUnsentDrafts$2(af afVar, IntRef intRef) {
        this.this$0 = afVar;
        this.$draftsToSend = intRef;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return h.a;
    }

    public final void invoke(Throwable th) {
        p.b(th, "it");
        this.$draftsToSend.element--;
        if (this.$draftsToSend.element == 0) {
            this.this$0.d.setSending(false);
        }
    }
}
