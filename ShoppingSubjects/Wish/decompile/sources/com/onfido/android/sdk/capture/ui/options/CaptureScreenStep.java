package com.onfido.android.sdk.capture.ui.options;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import kotlin.jvm.internal.Intrinsics;

public final class CaptureScreenStep extends FlowStep {
    public CaptureScreenStep(DocumentType documentType, CountryCode countryCode) {
        Intrinsics.checkParameterIsNotNull(documentType, "documentType");
        Intrinsics.checkParameterIsNotNull(countryCode, "country");
        super(FlowAction.CAPTURE_DOCUMENT);
        setOptions(new CaptureScreenOptions(documentType, countryCode));
    }
}
