package com.onfido.android.sdk.capture.ui.camera;

import com.onfido.a.a;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.utils.ImageUtils;

public final class CaptureActivity_MembersInjector implements a<CaptureActivity> {
    static final /* synthetic */ boolean a = true;
    private final com.onfido.b.a.a<CapturePresenter> b;
    private final com.onfido.b.a.a<ImageUtils> c;
    private final com.onfido.b.a.a<IdentityInteractor> d;

    public CaptureActivity_MembersInjector(com.onfido.b.a.a<CapturePresenter> aVar, com.onfido.b.a.a<ImageUtils> aVar2, com.onfido.b.a.a<IdentityInteractor> aVar3) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                if (a || aVar3 != null) {
                    this.d = aVar3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static a<CaptureActivity> create(com.onfido.b.a.a<CapturePresenter> aVar, com.onfido.b.a.a<ImageUtils> aVar2, com.onfido.b.a.a<IdentityInteractor> aVar3) {
        return new CaptureActivity_MembersInjector(aVar, aVar2, aVar3);
    }

    public void injectMembers(CaptureActivity captureActivity) {
        if (captureActivity == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        captureActivity.a = (CapturePresenter) this.b.get();
        captureActivity.b = (ImageUtils) this.c.get();
        captureActivity.c = (IdentityInteractor) this.d.get();
    }
}
