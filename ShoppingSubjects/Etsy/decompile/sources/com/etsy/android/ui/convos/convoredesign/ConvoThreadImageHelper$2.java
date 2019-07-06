package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.util.CameraHelper;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.s;

/* compiled from: ConvoThreadImageHelper.kt */
final class ConvoThreadImageHelper$2 extends Lambda implements b<Throwable, h> {
    final /* synthetic */ ae this$0;

    ConvoThreadImageHelper$2(ae aeVar) {
        this.this$0 = aeVar;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return h.a;
    }

    public final void invoke(Throwable th) {
        p.b(th, "it");
        l d = this.this$0.e;
        StringBuilder sb = new StringBuilder();
        sb.append("Received error from ");
        sb.append(s.a(CameraHelper.class));
        sb.append(": ");
        sb.append(th);
        d.e(sb.toString());
    }
}
