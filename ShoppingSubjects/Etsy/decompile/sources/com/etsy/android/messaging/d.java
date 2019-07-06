package com.etsy.android.messaging;

import dagger.a;

/* compiled from: EtsyGcmListenerService_MembersInjector */
public final class d implements a<EtsyGcmListenerService> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<h> b;

    public d(javax.a.a<h> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<EtsyGcmListenerService> a(javax.a.a<h> aVar) {
        return new d(aVar);
    }

    /* renamed from: a */
    public void injectMembers(EtsyGcmListenerService etsyGcmListenerService) {
        if (etsyGcmListenerService == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        etsyGcmListenerService.notificationRepo = (h) this.b.b();
    }
}
