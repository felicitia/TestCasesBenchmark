package com.etsy.android.ui.convos.convoredesign;

import java.util.ArrayList;
import kotlin.h;
import kotlin.jvm.a.a;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref.IntRef;

/* compiled from: ConvoThreadPresenter.kt */
final class ConvoThreadPresenter$sendUnsentDrafts$1 extends Lambda implements a<h> {
    final /* synthetic */ DraftMessage $draft;
    final /* synthetic */ IntRef $draftsToSend;
    final /* synthetic */ ArrayList $unsentList;
    final /* synthetic */ af this$0;

    ConvoThreadPresenter$sendUnsentDrafts$1(af afVar, ArrayList arrayList, DraftMessage draftMessage, IntRef intRef) {
        this.this$0 = afVar;
        this.$unsentList = arrayList;
        this.$draft = draftMessage;
        this.$draftsToSend = intRef;
        super(0);
    }

    public final void invoke() {
        this.$unsentList.remove(this.$draft);
        this.this$0.m.hideLoadingDialog();
        this.$draftsToSend.element--;
        if (this.$draftsToSend.element == 0) {
            this.this$0.d.setSending(false);
        }
    }
}
