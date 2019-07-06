package com.etsy.android.ui.convos.convoredesign;

import kotlin.h;
import kotlin.jvm.a.a;
import kotlin.jvm.internal.Lambda;

/* compiled from: ConvoThreadPresenter.kt */
final class ConvoThreadPresenter$markAsTrash$1 extends Lambda implements a<h> {
    final /* synthetic */ af this$0;

    ConvoThreadPresenter$markAsTrash$1(af afVar) {
        this.this$0 = afVar;
        super(0);
    }

    public final void invoke() {
        this.this$0.m.setShouldRefresh();
        this.this$0.m.goBack();
        this.this$0.m.hideLoadingDialog();
    }
}
