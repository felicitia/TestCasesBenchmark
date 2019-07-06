package com.etsy.android.ui.convos.convoredesign;

import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: ConvoThreadFragment2.kt */
final class ConvoThreadFragment2$onCreateView$4 extends Lambda implements b<Integer, h> {
    final /* synthetic */ ConvoThreadFragment2 this$0;

    ConvoThreadFragment2$onCreateView$4(ConvoThreadFragment2 convoThreadFragment2) {
        this.this$0 = convoThreadFragment2;
        super(1);
    }

    public /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).intValue());
        return h.a;
    }

    public final void invoke(int i) {
        this.this$0.getPresenter().a(i);
    }
}
