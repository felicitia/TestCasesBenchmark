package com.onfido.android.sdk.capture.ui;

import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import kotlin.jvm.internal.Intrinsics;

public final class DocumentSelectionPresenter {
    private final AnalyticsInteractor a;

    public DocumentSelectionPresenter(AnalyticsInteractor analyticsInteractor) {
        Intrinsics.checkParameterIsNotNull(analyticsInteractor, "analyticsInteractor");
        this.a = analyticsInteractor;
    }

    public final void trackDocumentSelection(boolean z) {
        this.a.trackDocumentTypeSelection(z);
    }
}
