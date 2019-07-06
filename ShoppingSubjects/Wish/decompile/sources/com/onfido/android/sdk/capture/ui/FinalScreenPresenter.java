package com.onfido.android.sdk.capture.ui;

import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import kotlin.jvm.internal.Intrinsics;

public final class FinalScreenPresenter {
    private final AnalyticsInteractor a;

    public FinalScreenPresenter(AnalyticsInteractor analyticsInteractor) {
        Intrinsics.checkParameterIsNotNull(analyticsInteractor, "analyticsInteractor");
        this.a = analyticsInteractor;
    }

    public final void trackFinalScreen(boolean z) {
        this.a.trackFinalScreen(z);
    }
}
