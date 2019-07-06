package com.etsy.android.ui.convos.convolistredesign;

import com.etsy.android.ui.convos.convoredesign.s;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: ConvosListPresenter.kt */
final class ConvosListPresenter$startListeningForNotifications$1 extends Lambda implements b<s, h> {
    final /* synthetic */ m this$0;

    ConvosListPresenter$startListeningForNotifications$1(m mVar) {
        this.this$0 = mVar;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((s) obj);
        return h.a;
    }

    public final void invoke(s sVar) {
        this.this$0.f.refreshConvos();
    }
}
