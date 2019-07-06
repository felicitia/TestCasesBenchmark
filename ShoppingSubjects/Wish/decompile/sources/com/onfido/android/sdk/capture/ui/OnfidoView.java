package com.onfido.android.sdk.capture.ui;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.ExitCode;
import com.onfido.android.sdk.capture.flow.FlowStepDirection;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessUploadChallenge;
import com.onfido.android.sdk.capture.ui.options.WelcomeScreenOptions;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.Applicant;

public interface OnfidoView {

    public static final class DefaultImpls {
        public static /* synthetic */ void showDocumentCapture$default(OnfidoView onfidoView, Applicant applicant, boolean z, DocumentType documentType, CountryCode countryCode, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showDocumentCapture");
            }
            if ((i & 8) != 0) {
                countryCode = null;
            }
            onfidoView.showDocumentCapture(applicant, z, documentType, countryCode);
        }
    }

    void completeFlow();

    void exitFlow(ExitCode exitCode);

    void onDocumentCaptureBackPressed(DocumentType documentType);

    void showCaptureFaceMessage(FlowStepDirection flowStepDirection);

    void showCaptureLivenessConfirmation(FlowStepDirection flowStepDirection, String str, LivenessUploadChallenge[] livenessUploadChallengeArr);

    void showCaptureLivenessMessage(FlowStepDirection flowStepDirection);

    void showDocumentCapture(Applicant applicant, boolean z, DocumentType documentType, CountryCode countryCode);

    void showDocumentCountrySelection(DocumentType documentType, FlowStepDirection flowStepDirection);

    void showDocumentTypeSelection(Applicant applicant, FlowStepDirection flowStepDirection);

    void showError(String str);

    void showFaceCapture(Applicant applicant);

    void showFinalScreen(FlowStepDirection flowStepDirection);

    void showLivenessCapture(Applicant applicant);

    void showLoading();

    void showMessageScreen(String str, String str2, FlowStepDirection flowStepDirection);

    void showNetworkError();

    void showWelcomeScreen(WelcomeScreenOptions welcomeScreenOptions, FlowStepDirection flowStepDirection);
}
