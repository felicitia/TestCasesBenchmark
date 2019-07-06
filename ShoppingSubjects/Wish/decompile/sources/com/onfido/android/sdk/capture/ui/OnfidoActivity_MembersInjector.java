package com.onfido.android.sdk.capture.ui;

import com.onfido.a.a;

public final class OnfidoActivity_MembersInjector implements a<OnfidoActivity> {
    static final /* synthetic */ boolean a = true;
    private final com.onfido.b.a.a<OnfidoPresenter> b;

    public OnfidoActivity_MembersInjector(com.onfido.b.a.a<OnfidoPresenter> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<OnfidoActivity> create(com.onfido.b.a.a<OnfidoPresenter> aVar) {
        return new OnfidoActivity_MembersInjector(aVar);
    }

    public void injectMembers(OnfidoActivity onfidoActivity) {
        if (onfidoActivity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        onfidoActivity.presenter = (OnfidoPresenter) this.b.get();
    }
}
