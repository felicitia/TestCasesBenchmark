package com.etsy.android.ui.feedback;

import com.etsy.android.lib.logger.f;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: FeedbackPresenter.kt */
final class FeedbackPresenter$sendFeedback$1$2 extends Lambda implements b<Throwable, h> {
    public static final FeedbackPresenter$sendFeedback$1$2 INSTANCE = new FeedbackPresenter$sendFeedback$1$2();

    FeedbackPresenter$sendFeedback$1$2() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return h.a;
    }

    public final void invoke(Throwable th) {
        p.b(th, "it");
        f.c(th);
    }
}
