package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.ui.convos.convoredesign.DraftMessage.Status;
import kotlin.h;
import kotlin.jvm.a.a;
import kotlin.jvm.internal.Lambda;

/* compiled from: ConvoThreadPresenter.kt */
final class ConvoThreadPresenter$sendMessage$2 extends Lambda implements a<h> {
    final /* synthetic */ af this$0;

    ConvoThreadPresenter$sendMessage$2(af afVar) {
        this.this$0 = afVar;
        super(0);
    }

    public final void invoke() {
        this.this$0.d.setSending(false);
        this.this$0.e.a(Status.IN_DRAFT);
        this.this$0.q();
        this.this$0.m.addDraftToAdapter(this.this$0.e.b());
        this.this$0.m.hideLoadingDialog();
        this.this$0.m.clearMessageInput();
    }
}
