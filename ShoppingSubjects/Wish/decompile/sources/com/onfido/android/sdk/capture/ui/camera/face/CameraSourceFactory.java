package com.onfido.android.sdk.capture.ui.camera.face;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.util.Log;
import android.view.SurfaceHolder;
import com.onfido.android.sdk.capture.ui.camera.face.CameraSource.Builder;

public class CameraSourceFactory {
    CameraSourceFactory() {
    }

    public static boolean isFrontCameraSupported() {
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            CameraInfo cameraInfo = new CameraInfo();
            Camera.getCameraInfo(i, cameraInfo);
            if (1 == cameraInfo.facing) {
                return true;
            }
        }
        return false;
    }

    public static CameraSourceFactory newInstance() {
        return new CameraSourceFactory();
    }

    public CameraSource createCameraSource(Context context, int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, int i6, int i7, int i8, SurfaceHolder surfaceHolder) {
        if (i == 1 && !isFrontCameraSupported()) {
            Log.w("CameraSourceFactory", "CAMERA_FACING_FRONT not supported by this camera, reverting to back camera.");
            i = 0;
        }
        Builder builder = new Builder(context);
        builder.setFacing(i);
        builder.setFocusMode("continuous-picture");
        if (i == 0) {
            builder.setFlashMode("auto");
        }
        if (z) {
            builder.setRequestedPreviewSize(i5, i4);
            builder.setRequestedPictureSize(i5, i4);
        } else {
            builder.setRequestedPreviewSize(i4, i5);
            builder.setRequestedPictureSize(i4, i5);
        }
        if (z2) {
            builder.setupRecording(i6, i7, i8, surfaceHolder);
        }
        return builder.build();
    }
}
