package com.otaliastudios.cameraview;

import android.content.Context;
import android.view.OrientationEventListener;
import android.view.WindowManager;

class OrientationHelper {
    /* access modifiers changed from: private */
    public final Callback mCallback;
    /* access modifiers changed from: private */
    public int mDeviceOrientation = -1;
    private int mDisplayOffset = -1;
    final OrientationEventListener mListener;

    interface Callback {
        void onDeviceOrientationChanged(int i);
    }

    OrientationHelper(Context context, Callback callback) {
        this.mCallback = callback;
        this.mListener = new OrientationEventListener(context, 3) {
            public void onOrientationChanged(int i) {
                int i2 = 0;
                if (i != -1 && i < 315 && i >= 45) {
                    if (i >= 45 && i < 135) {
                        i2 = 90;
                    } else if (i >= 135 && i < 225) {
                        i2 = 180;
                    } else if (i >= 225 && i < 315) {
                        i2 = 270;
                    }
                }
                if (i2 != OrientationHelper.this.mDeviceOrientation) {
                    OrientationHelper.this.mDeviceOrientation = i2;
                    OrientationHelper.this.mCallback.onDeviceOrientationChanged(OrientationHelper.this.mDeviceOrientation);
                }
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public void enable(Context context) {
        switch (((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 0:
                this.mDisplayOffset = 0;
                break;
            case 1:
                this.mDisplayOffset = 90;
                break;
            case 2:
                this.mDisplayOffset = 180;
                break;
            case 3:
                this.mDisplayOffset = 270;
                break;
            default:
                this.mDisplayOffset = 0;
                break;
        }
        this.mListener.enable();
    }

    /* access modifiers changed from: 0000 */
    public void disable() {
        this.mListener.disable();
        this.mDisplayOffset = -1;
        this.mDeviceOrientation = -1;
    }

    /* access modifiers changed from: 0000 */
    public int getDisplayOffset() {
        return this.mDisplayOffset;
    }
}
