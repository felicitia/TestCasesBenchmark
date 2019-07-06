package com.etsy.android.messaging.salesforce;

import com.etsy.android.lib.models.datatypes.EtsyId;
import com.salesforce.marketingcloud.c;
import com.salesforce.marketingcloud.registration.d.a;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: SalesforceNotificationInitializer.kt */
final class SalesforceNotificationInitializer$subscribeToUserChanges$1 extends Lambda implements b<EtsyId, h> {
    public static final SalesforceNotificationInitializer$subscribeToUserChanges$1 INSTANCE = new SalesforceNotificationInitializer$subscribeToUserChanges$1();

    SalesforceNotificationInitializer$subscribeToUserChanges$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((EtsyId) obj);
        return h.a;
    }

    public final void invoke(final EtsyId etsyId) {
        c.a((c.b) new c.b() {
            public final void a(c cVar) {
                p.b(cVar, "sdk");
                a d = cVar.g().d();
                EtsyId etsyId = etsyId;
                p.a((Object) etsyId, "it");
                d.a(etsyId.getId()).a();
            }
        });
    }
}
