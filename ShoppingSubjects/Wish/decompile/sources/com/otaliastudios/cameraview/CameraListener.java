package com.otaliastudios.cameraview;

import android.graphics.PointF;
import java.io.File;

public abstract class CameraListener {
    public void onCameraClosed() {
    }

    public void onCameraError(CameraException cameraException) {
    }

    public void onCameraOpened(CameraOptions cameraOptions) {
    }

    public void onExposureCorrectionChanged(float f, float[] fArr, PointF[] pointFArr) {
    }

    public void onFocusEnd(boolean z, PointF pointF) {
    }

    public void onFocusStart(PointF pointF) {
    }

    public void onOrientationChanged(int i) {
    }

    public void onPictureTaken(byte[] bArr) {
    }

    public void onVideoTaken(File file) {
    }

    public void onZoomChanged(float f, float[] fArr, PointF[] pointFArr) {
    }
}
