package com.onfido.android.sdk.capture.native_detector;

import com.onfido.android.sdk.capture.edge_detector.EdgeDetectionResults;
import com.onfido.android.sdk.capture.ui.camera.PreviewFrameData;
import io.reactivex.subjects.PublishSubject;

public class NativeDetector {
    private static boolean a = false;
    private PublishSubject<PreviewFrameData> b = PublishSubject.create();

    static {
        try {
            System.loadLibrary("NativeBridge");
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    private native void clearEdgesNative();

    private native boolean detectBlurCaptureNative(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7);

    private native boolean shouldAutocaptureNative(int i);

    public void clearEdges() {
        if (hasNativeLibrary()) {
            clearEdgesNative();
        }
    }

    public native boolean detectBlur(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6);

    public boolean detectBlurCapture(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return hasNativeLibrary() && detectBlurCaptureNative(bArr, i, i2, i3, i4, i5, i6, i7);
    }

    public native EdgeDetectionResults detectEdges(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6);

    public native boolean detectGlare(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6);

    public PublishSubject<PreviewFrameData> getFrameData() {
        return this.b;
    }

    public boolean hasNativeLibrary() {
        return a;
    }

    public boolean shouldAutocapture(int i) {
        return hasNativeLibrary() && shouldAutocaptureNative(i);
    }
}
