package com.onfido.android.sdk.capture.analytics;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.upload.UploadErrorType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.api.client.data.DocSide;
import com.onfido.c.a.a;
import com.onfido.c.a.n;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public class SegmentInteractor implements AnalyticsInteractor {
    public static final Companion Companion = new Companion(null);
    private final n a;
    private final a b;
    private final IdentityInteractor c;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SegmentInteractor(a aVar, IdentityInteractor identityInteractor) {
        Intrinsics.checkParameterIsNotNull(aVar, "analyticsApi");
        Intrinsics.checkParameterIsNotNull(identityInteractor, "identityInteractor");
        this.b = aVar;
        this.c = identityInteractor;
        n nVar = new n();
        nVar.c("locale", this.c.getCurrentLocale());
        nVar.c("host_app_name", this.c.getAppPackageName());
        nVar.c("sdk_version", "4.3.0");
        this.a = nVar;
        if (this.c.getGooglePlayServicesVersion() >= 0) {
            this.a.put("play_services_version", Integer.valueOf(this.c.getGooglePlayServicesVersion()));
        }
    }

    private final String a(CaptureType captureType) {
        return Intrinsics.areEqual(captureType, CaptureType.VIDEO) ? "VIDEO_" : "";
    }

    private final void a(String str, n nVar) {
    }

    public void identifyUser() {
        a aVar = this.b;
    }

    public void trackBarcodeNotReadable(DocumentType documentType, CountryCode countryCode) {
        Intrinsics.checkParameterIsNotNull(documentType, "docType");
        n nVar = this.a;
        Map map = nVar;
        map.put("name", "BARCODE_NOT_READABLE");
        map.put("doc_type", documentType.getId());
        if (countryCode != null) {
            map.put("country", countryCode.getAlpha2());
        }
        a("SCREEN", nVar);
    }

    public void trackCameraPermission(boolean z, Boolean bool) {
        n nVar = this.a;
        Map map = nVar;
        map.put("granted", bool);
        map.put("request", Boolean.valueOf(z));
        map.put("required_hardware", "CAMERA");
        a("PERMISSION", nVar);
    }

    public void trackChallenge(int i, String str) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        n nVar = this.a;
        Map map = nVar;
        StringBuilder sb = new StringBuilder();
        sb.append("VIDEO_FACIAL_CAPTURE_STEP_");
        sb.append(i);
        map.put("name", sb.toString());
        map.put("lifecycle", "VISIBLE");
        map.put("challenge_type", str);
        a("SCREEN", nVar);
    }

    public void trackCountrySelection(boolean z) {
        n nVar = this.a;
        Map map = nVar;
        map.put("lifecycle", z ? "VISIBLE" : "NOT_VISIBLE");
        map.put("name", "COUNTRY_SELECTION");
        a("SCREEN", nVar);
    }

    public void trackDocumentCapture(boolean z, boolean z2, boolean z3, DocumentType documentType, CountryCode countryCode, DocSide docSide) {
        Intrinsics.checkParameterIsNotNull(documentType, "docType");
        String str = (docSide != null || z2) ? (docSide != null || !z2) ? (docSide == null || !Intrinsics.areEqual(docSide, DocSide.FRONT) || z2) ? (docSide == null || !Intrinsics.areEqual(docSide, DocSide.BACK) || z2) ? (docSide == null || !Intrinsics.areEqual(docSide, DocSide.FRONT) || !z2) ? "DOCUMENT_CAPTURE_CONFIRMATION_BACK" : "DOCUMENT_CAPTURE_CONFIRMATION_FRONT" : "DOCUMENT_CAPTURE_BACK" : "DOCUMENT_CAPTURE_FRONT" : "DOCUMENT_CAPTURE_CONFIRMATION" : "DOCUMENT_CAPTURE";
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("orientation", z3 ? "PORTRAIT" : "LANDSCAPE");
        n nVar = this.a;
        Map map = nVar;
        map.put("lifecycle", z ? "VISIBLE" : "NOT_VISIBLE");
        map.put("name", str);
        map.put("mode", jSONObject.toString());
        map.put("doc_type", documentType.getId());
        if (countryCode != null) {
            map.put("country", countryCode.getAlpha2());
        }
        a("SCREEN", nVar);
    }

    public void trackDocumentCaptureError(boolean z, UploadErrorType uploadErrorType) {
        Intrinsics.checkParameterIsNotNull(uploadErrorType, "errorType");
        n nVar = this.a;
        Map map = nVar;
        map.put("lifecycle", z ? "VISIBLE" : "NOT_VISIBLE");
        map.put("name", "DOCUMENT_CAPTURE_CONFIRMATION_ERROR");
        map.put("cause", uploadErrorType.getKey());
        a("SCREEN", nVar);
    }

    public void trackDocumentTypeSelection(boolean z) {
        n nVar = this.a;
        Map map = nVar;
        map.put("lifecycle", z ? "VISIBLE" : "NOT_VISIBLE");
        map.put("name", "DOCUMENT_TYPE_SELECTION");
        a("SCREEN", nVar);
    }

    public void trackFaceCapture(boolean z, boolean z2, boolean z3, CaptureType captureType) {
        StringBuilder sb;
        String str;
        Intrinsics.checkParameterIsNotNull(captureType, "captureType");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("orientation", z3 ? "PORTRAIT" : "LANDSCAPE");
        n nVar = this.a;
        Map map = nVar;
        map.put("lifecycle", z ? "VISIBLE" : "NOT_VISIBLE");
        String str2 = "name";
        if (z2) {
            sb = new StringBuilder();
            sb.append("");
            sb.append(a(captureType));
            str = "FACIAL_CAPTURE_CONFIRMATION";
        } else {
            sb = new StringBuilder();
            sb.append("");
            sb.append(a(captureType));
            str = "FACIAL_CAPTURE";
        }
        sb.append(str);
        map.put(str2, sb.toString());
        map.put("mode", jSONObject.toString());
        a("SCREEN", nVar);
    }

    public void trackFaceCaptureError(boolean z, CaptureType captureType, UploadErrorType uploadErrorType) {
        Intrinsics.checkParameterIsNotNull(captureType, "captureType");
        Intrinsics.checkParameterIsNotNull(uploadErrorType, "errorType");
        n nVar = this.a;
        Map map = nVar;
        map.put("lifecycle", z ? "VISIBLE" : "NOT_VISIBLE");
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(a(captureType));
        sb.append("FACIAL_CAPTURE_CONFIRMATION_ERROR");
        map.put("name", sb.toString());
        map.put("cause", uploadErrorType.getKey());
        a("SCREEN", nVar);
    }

    public void trackFaceIntro(boolean z, CaptureType captureType) {
        Intrinsics.checkParameterIsNotNull(captureType, "captureType");
        n nVar = this.a;
        Map map = nVar;
        map.put("lifecycle", z ? "VISIBLE" : "NOT_VISIBLE");
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(a(captureType));
        sb.append("FACIAL_INTRO");
        map.put("name", sb.toString());
        a("SCREEN", nVar);
    }

    public void trackFinalScreen(boolean z) {
        n nVar = this.a;
        Map map = nVar;
        map.put("lifecycle", z ? "VISIBLE" : "NOT_VISIBLE");
        map.put("name", "FINAL");
        a("SCREEN", nVar);
    }

    public void trackFlowCancellation() {
        n nVar = this.a;
        nVar.put("state", "CANCELED");
        a("FLOW", nVar);
    }

    public void trackFlowCompletion() {
        n nVar = this.a;
        nVar.put("state", "COMPLETED");
        a("FLOW", nVar);
    }

    public void trackFlowError() {
        n nVar = this.a;
        nVar.put("state", "ERROR");
        a("FLOW", nVar);
    }

    public void trackFlowStart() {
        n nVar = this.a;
        nVar.put("state", "START");
        a("FLOW", nVar);
    }

    public void trackUploadingScreen(CaptureType captureType) {
        Intrinsics.checkParameterIsNotNull(captureType, "captureType");
        n nVar = this.a;
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(captureType.name());
        sb.append("_UPLOAD");
        nVar.put("name", sb.toString());
        a("SCREEN", nVar);
    }

    public void trackWelcome(boolean z) {
        n nVar = this.a;
        Map map = nVar;
        map.put("lifecycle", z ? "VISIBLE" : "NOT_VISIBLE");
        map.put("name", "WELCOME");
        a("SCREEN", nVar);
    }
}
