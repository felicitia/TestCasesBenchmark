package com.onfido.android.sdk.capture.ui.camera;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.upload.UploadErrorType;
import com.onfido.api.client.OnfidoAPI;
import com.onfido.api.client.OnfidoAPI.Listener;
import com.onfido.api.client.ValidationLevel;
import com.onfido.api.client.ValidationType;
import com.onfido.api.client.data.Applicant;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocType;
import com.onfido.api.client.data.DocumentUpload;
import com.onfido.api.client.data.ErrorData;
import com.onfido.api.client.data.LivePhotoUpload;
import java.util.List;
import java.util.Map;

class a {
    private final OnfidoAPI a;
    /* access modifiers changed from: private */
    public final CaptureType b;
    private final IdentityInteractor c;
    /* access modifiers changed from: private */
    public CaptureUploadServiceListener d;
    private DocumentType e;
    private boolean f = true;

    /* renamed from: com.onfido.android.sdk.capture.ui.camera.a$a reason: collision with other inner class name */
    private abstract class C0008a<T> implements Listener<T> {
        private C0008a() {
        }

        public void onError(int i, String str, ErrorData errorData) {
            CaptureUploadServiceListener a;
            UploadErrorType uploadErrorType;
            if (!(errorData == null || errorData.getError() == null || errorData.getError().getFields() == null)) {
                Map fields = errorData.getError().getFields();
                if (a.this.b == CaptureType.DOCUMENT && fields.containsKey("document_detection")) {
                    a = a.this.d;
                    uploadErrorType = UploadErrorType.DOCUMENT;
                } else if (fields.containsKey("face_detection")) {
                    String str2 = (String) ((List) fields.get("face_detection")).get(0);
                    if (str2.contains("Face not detected")) {
                        a = a.this.d;
                        uploadErrorType = UploadErrorType.NO_FACE;
                    } else if (str2.contains("Multiple faces")) {
                        a = a.this.d;
                        uploadErrorType = UploadErrorType.MULTIPLE_FACES;
                    }
                }
                a.onUploadError(uploadErrorType);
                return;
            }
            a.this.d.onUploadError(UploadErrorType.GENERIC);
        }

        public void onFailure() {
            a.this.d.onUploadError(UploadErrorType.NETWORK);
        }
    }

    a(CaptureType captureType, OnfidoAPI onfidoAPI, IdentityInteractor identityInteractor, CaptureUploadServiceListener captureUploadServiceListener) {
        this.b = captureType;
        this.a = onfidoAPI;
        this.d = captureUploadServiceListener;
        this.c = identityInteractor;
    }

    private void a(Applicant applicant, Map<ValidationType, ValidationLevel> map, byte[] bArr) {
        DocType docType;
        AnonymousClass1 r6 = new C0008a<DocumentUpload>() {
            /* renamed from: a */
            public void onSuccess(DocumentUpload documentUpload) {
                a.this.d.onDocumentUploaded(documentUpload);
            }
        };
        DocSide docSide = this.f ? DocSide.FRONT : DocSide.BACK;
        switch (this.e) {
            case PASSPORT:
                docType = DocType.PASSPORT;
                break;
            case DRIVING_LICENCE:
                docType = DocType.DRIVING_LICENSE;
                break;
            case NATIONAL_IDENTITY_CARD:
                docType = DocType.NATIONAL_ID_CARD;
                break;
            default:
                docType = DocType.UNKNOWN;
                break;
        }
        this.a.upload(applicant, "onfido_captured_image.jpg", docType, "image/jpeg", bArr, r6, map, docSide, this.c.getSdkSource(), this.c.getSdkVersion());
    }

    private void a(Applicant applicant, boolean z, byte[] bArr) {
        Applicant applicant2 = applicant;
        this.a.uploadLivePhoto(applicant2, "onfido_captured_image.jpg", "image/jpeg", bArr, z, new C0008a<LivePhotoUpload>() {
            /* renamed from: a */
            public void onSuccess(LivePhotoUpload livePhotoUpload) {
                a.this.d.onLivePhotoUploaded(livePhotoUpload);
            }
        }, this.c.getSdkSource(), this.c.getSdkVersion());
    }

    /* access modifiers changed from: 0000 */
    public void a(DocumentType documentType) {
        this.e = documentType;
    }

    /* access modifiers changed from: 0000 */
    public void a(Applicant applicant, Map<ValidationType, ValidationLevel> map, boolean z, byte[] bArr) {
        if (this.b == CaptureType.DOCUMENT) {
            a(applicant, map, bArr);
        } else {
            a(applicant, z, bArr);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z) {
        this.f = z;
    }
}
