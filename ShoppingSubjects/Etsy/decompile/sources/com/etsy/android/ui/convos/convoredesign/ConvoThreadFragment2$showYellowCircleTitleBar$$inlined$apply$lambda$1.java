package com.etsy.android.ui.convos.convoredesign;

import android.view.View;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;

/* compiled from: ConvoThreadFragment2.kt */
final class ConvoThreadFragment2$showYellowCircleTitleBar$$inlined$apply$lambda$1 extends Lambda implements b<View, h> {
    final /* synthetic */ String $stateAction$inlined;
    final /* synthetic */ String $stateText$inlined;
    final /* synthetic */ ConvoThreadFragment2 this$0;

    ConvoThreadFragment2$showYellowCircleTitleBar$$inlined$apply$lambda$1(ConvoThreadFragment2 convoThreadFragment2, String str, String str2) {
        this.this$0 = convoThreadFragment2;
        this.$stateText$inlined = str;
        this.$stateAction$inlined = str2;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return h.a;
    }

    public final void invoke(View view) {
        this.this$0.getPresenter().h();
    }
}
