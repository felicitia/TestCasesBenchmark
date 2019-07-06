package com.onfido.android.sdk.capture.analytics;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.upload.UploadErrorType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.DocSide;

public interface AnalyticsInteractor {
    void identifyUser();

    void trackBarcodeNotReadable(DocumentType documentType, CountryCode countryCode);

    void trackCameraPermission(boolean z, Boolean bool);

    void trackChallenge(int i, String str);

    void trackCountrySelection(boolean z);

    void trackDocumentCapture(boolean z, boolean z2, boolean z3, DocumentType documentType, CountryCode countryCode, DocSide docSide);

    void trackDocumentCaptureError(boolean z, UploadErrorType uploadErrorType);

    void trackDocumentTypeSelection(boolean z);

    void trackFaceCapture(boolean z, boolean z2, boolean z3, CaptureType captureType);

    void trackFaceCaptureError(boolean z, CaptureType captureType, UploadErrorType uploadErrorType);

    void trackFaceIntro(boolean z, CaptureType captureType);

    void trackFinalScreen(boolean z);

    void trackFlowCancellation();

    void trackFlowCompletion();

    void trackFlowError();

    void trackFlowStart();

    void trackUploadingScreen(CaptureType captureType);

    void trackWelcome(boolean z);
}
