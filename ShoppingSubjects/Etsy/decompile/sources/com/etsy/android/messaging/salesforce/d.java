package com.etsy.android.messaging.salesforce;

import com.etsy.android.lib.logger.l;
import com.google.android.gms.common.GoogleApiAvailability;
import dagger.internal.c;
import javax.a.a;

/* compiled from: SalesforceNotificationInitializer_Factory */
public final class d implements c<c> {
    static final /* synthetic */ boolean a = true;
    private final a<l> b;
    private final a<com.etsy.android.lib.util.sharedprefs.d> c;
    private final a<a> d;
    private final a<GoogleApiAvailability> e;

    public d(a<l> aVar, a<com.etsy.android.lib.util.sharedprefs.d> aVar2, a<a> aVar3, a<GoogleApiAvailability> aVar4) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                if (a || aVar3 != null) {
                    this.d = aVar3;
                    if (a || aVar4 != null) {
                        this.e = aVar4;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public c b() {
        return new c((l) this.b.b(), (com.etsy.android.lib.util.sharedprefs.d) this.c.b(), (a) this.d.b(), (GoogleApiAvailability) this.e.b());
    }

    public static c<c> a(a<l> aVar, a<com.etsy.android.lib.util.sharedprefs.d> aVar2, a<a> aVar3, a<GoogleApiAvailability> aVar4) {
        return new d(aVar, aVar2, aVar3, aVar4);
    }
}
