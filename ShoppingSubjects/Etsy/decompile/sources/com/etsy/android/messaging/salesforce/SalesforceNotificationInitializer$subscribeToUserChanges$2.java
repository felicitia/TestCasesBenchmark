package com.etsy.android.messaging.salesforce;

import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: SalesforceNotificationInitializer.kt */
final class SalesforceNotificationInitializer$subscribeToUserChanges$2 extends Lambda implements b<Throwable, h> {
    final /* synthetic */ c this$0;

    SalesforceNotificationInitializer$subscribeToUserChanges$2(c cVar) {
        this.this$0 = cVar;
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return h.a;
    }

    public final void invoke(Throwable th) {
        p.b(th, "it");
        this.this$0.b.a(th);
    }
}
