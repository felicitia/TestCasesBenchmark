package com.onfido.android.sdk.capture.ui.camera.face;

public interface MediaCaptureCallback {
    void onErrorTakingPicture();

    void onPictureCaptured(byte[] bArr, int i, int i2);

    void onVideoCanceled();

    void onVideoCaptured(boolean z, String str);

    void onVideoTimeoutExceeded();
}
