package com.onfido.android.sdk.capture.ui;

import com.onfido.a.a;

public final class FaceIntroFragment_MembersInjector implements a<FaceIntroFragment> {
    static final /* synthetic */ boolean a = true;
    private final com.onfido.b.a.a<FaceIntroPresenter> b;

    public FaceIntroFragment_MembersInjector(com.onfido.b.a.a<FaceIntroPresenter> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<FaceIntroFragment> create(com.onfido.b.a.a<FaceIntroPresenter> aVar) {
        return new FaceIntroFragment_MembersInjector(aVar);
    }

    public void injectMembers(FaceIntroFragment faceIntroFragment) {
        if (faceIntroFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        faceIntroFragment.presenter = (FaceIntroPresenter) this.b.get();
    }
}
