package com.onfido.android.sdk.capture.ui;

import com.onfido.a.a;

public final class FinalScreenFragment_MembersInjector implements a<FinalScreenFragment> {
    static final /* synthetic */ boolean a = true;
    private final com.onfido.b.a.a<FinalScreenPresenter> b;

    public FinalScreenFragment_MembersInjector(com.onfido.b.a.a<FinalScreenPresenter> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<FinalScreenFragment> create(com.onfido.b.a.a<FinalScreenPresenter> aVar) {
        return new FinalScreenFragment_MembersInjector(aVar);
    }

    public void injectMembers(FinalScreenFragment finalScreenFragment) {
        if (finalScreenFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        finalScreenFragment.presenter = (FinalScreenPresenter) this.b.get();
    }
}
