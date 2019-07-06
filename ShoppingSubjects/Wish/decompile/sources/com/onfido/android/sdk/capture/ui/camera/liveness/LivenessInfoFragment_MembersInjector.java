package com.onfido.android.sdk.capture.ui.camera.liveness;

import com.onfido.a.a;

public final class LivenessInfoFragment_MembersInjector implements a<LivenessInfoFragment> {
    static final /* synthetic */ boolean a = true;
    private final com.onfido.b.a.a<LivenessInfoPresenter> b;

    public LivenessInfoFragment_MembersInjector(com.onfido.b.a.a<LivenessInfoPresenter> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<LivenessInfoFragment> create(com.onfido.b.a.a<LivenessInfoPresenter> aVar) {
        return new LivenessInfoFragment_MembersInjector(aVar);
    }

    public void injectMembers(LivenessInfoFragment livenessInfoFragment) {
        if (livenessInfoFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        livenessInfoFragment.presenter = (LivenessInfoPresenter) this.b.get();
    }
}
