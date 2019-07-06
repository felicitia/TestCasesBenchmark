package com.onfido.android.sdk.capture.ui.camera;

import com.onfido.android.sdk.capture.upload.UploadErrorType;
import com.onfido.api.client.data.DocumentUpload;
import com.onfido.api.client.data.LivePhotoUpload;

public interface CaptureUploadServiceListener {
    void onDocumentUploaded(DocumentUpload documentUpload);

    void onLivePhotoUploaded(LivePhotoUpload livePhotoUpload);

    void onUploadError(UploadErrorType uploadErrorType);
}
