package com.onfido.android.sdk.capture.ui;

import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import kotlin.jvm.internal.Intrinsics;

public final class WelcomePresenter {
    private final AnalyticsInteractor a;

    public WelcomePresenter(AnalyticsInteractor analyticsInteractor) {
        Intrinsics.checkParameterIsNotNull(analyticsInteractor, "analyticsInteractor");
        this.a = analyticsInteractor;
    }

    public final void trackWelcome(boolean z) {
        this.a.trackWelcome(z);
    }
}
