package com.onfido.android.sdk.capture.ui;

import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import kotlin.jvm.internal.Intrinsics;

public final class FaceIntroPresenter {
    private final AnalyticsInteractor a;

    public FaceIntroPresenter(AnalyticsInteractor analyticsInteractor) {
        Intrinsics.checkParameterIsNotNull(analyticsInteractor, "analyticsInteractor");
        this.a = analyticsInteractor;
    }

    public final void trackFaceIntro(boolean z) {
        this.a.trackFaceIntro(z, CaptureType.FACE);
    }
}
