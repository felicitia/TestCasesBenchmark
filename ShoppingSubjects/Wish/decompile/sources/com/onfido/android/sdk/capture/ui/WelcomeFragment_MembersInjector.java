package com.onfido.android.sdk.capture.ui;

import com.onfido.a.a;

public final class WelcomeFragment_MembersInjector implements a<WelcomeFragment> {
    static final /* synthetic */ boolean a = true;
    private final com.onfido.b.a.a<WelcomePresenter> b;

    public WelcomeFragment_MembersInjector(com.onfido.b.a.a<WelcomePresenter> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<WelcomeFragment> create(com.onfido.b.a.a<WelcomePresenter> aVar) {
        return new WelcomeFragment_MembersInjector(aVar);
    }

    public void injectMembers(WelcomeFragment welcomeFragment) {
        if (welcomeFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        welcomeFragment.presenter = (WelcomePresenter) this.b.get();
    }
}
