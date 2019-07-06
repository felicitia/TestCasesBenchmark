package com.otaliastudios.cameraview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

abstract class CameraPreview<T extends View, Output> {
    /* access modifiers changed from: private */
    public static final CameraLogger LOG = CameraLogger.create(CameraPreview.class.getSimpleName());
    Task<Void> mCropTask = new Task<>();
    /* access modifiers changed from: private */
    public boolean mCropping;
    /* access modifiers changed from: private */
    public int mDesiredHeight;
    /* access modifiers changed from: private */
    public int mDesiredWidth;
    private SurfaceCallback mSurfaceCallback;
    /* access modifiers changed from: private */
    public int mSurfaceHeight;
    /* access modifiers changed from: private */
    public int mSurfaceWidth;
    private T mView;

    interface SurfaceCallback {
        void onSurfaceAvailable();

        void onSurfaceChanged();
    }

    /* access modifiers changed from: 0000 */
    public abstract Output getOutput();

    /* access modifiers changed from: 0000 */
    public abstract Class<Output> getOutputClass();

    /* access modifiers changed from: protected */
    public abstract T onCreateView(Context context, ViewGroup viewGroup);

    /* access modifiers changed from: 0000 */
    public boolean supportsCropping() {
        return true;
    }

    CameraPreview(Context context, ViewGroup viewGroup, SurfaceCallback surfaceCallback) {
        this.mView = onCreateView(context, viewGroup);
        this.mSurfaceCallback = surfaceCallback;
    }

    /* access modifiers changed from: 0000 */
    public final T getView() {
        return this.mView;
    }

    /* access modifiers changed from: 0000 */
    public void setDesiredSize(int i, int i2) {
        LOG.i("setDesiredSize:", "desiredW=", Integer.valueOf(i), "desiredH=", Integer.valueOf(i2));
        this.mDesiredWidth = i;
        this.mDesiredHeight = i2;
        crop();
    }

    /* access modifiers changed from: 0000 */
    public final Size getSurfaceSize() {
        return new Size(this.mSurfaceWidth, this.mSurfaceHeight);
    }

    /* access modifiers changed from: 0000 */
    public final void setSurfaceCallback(SurfaceCallback surfaceCallback) {
        this.mSurfaceCallback = surfaceCallback;
        if (this.mSurfaceWidth != 0 || this.mSurfaceHeight != 0) {
            this.mSurfaceCallback.onSurfaceAvailable();
        }
    }

    /* access modifiers changed from: protected */
    public final void onSurfaceAvailable(int i, int i2) {
        LOG.i("onSurfaceAvailable:", "w=", Integer.valueOf(i), "h=", Integer.valueOf(i2));
        this.mSurfaceWidth = i;
        this.mSurfaceHeight = i2;
        crop();
        this.mSurfaceCallback.onSurfaceAvailable();
    }

    /* access modifiers changed from: protected */
    public final void onSurfaceSizeChanged(int i, int i2) {
        LOG.i("onSurfaceSizeChanged:", "w=", Integer.valueOf(i), "h=", Integer.valueOf(i2));
        if (i != this.mSurfaceWidth || i2 != this.mSurfaceHeight) {
            this.mSurfaceWidth = i;
            this.mSurfaceHeight = i2;
            crop();
            this.mSurfaceCallback.onSurfaceChanged();
        }
    }

    /* access modifiers changed from: protected */
    public final void onSurfaceDestroyed() {
        this.mSurfaceWidth = 0;
        this.mSurfaceHeight = 0;
    }

    /* access modifiers changed from: 0000 */
    public final boolean isReady() {
        return this.mSurfaceWidth > 0 && this.mSurfaceHeight > 0;
    }

    private final void crop() {
        this.mCropTask.start();
        if (!supportsCropping()) {
            this.mCropTask.end(null);
        } else {
            getView().post(new Runnable() {
                public void run() {
                    float f;
                    if (CameraPreview.this.mDesiredHeight == 0 || CameraPreview.this.mDesiredWidth == 0 || CameraPreview.this.mSurfaceHeight == 0 || CameraPreview.this.mSurfaceWidth == 0) {
                        CameraPreview.this.mCropTask.end(null);
                        return;
                    }
                    AspectRatio of = AspectRatio.of(CameraPreview.this.mSurfaceWidth, CameraPreview.this.mSurfaceHeight);
                    AspectRatio of2 = AspectRatio.of(CameraPreview.this.mDesiredWidth, CameraPreview.this.mDesiredHeight);
                    float f2 = 1.0f;
                    if (of.toFloat() >= of2.toFloat()) {
                        f2 = of.toFloat() / of2.toFloat();
                        f = 1.0f;
                    } else {
                        f = of2.toFloat() / of.toFloat();
                    }
                    CameraPreview.this.applyCrop(f, f2);
                    CameraPreview.this.mCropping = f > 1.02f || f2 > 1.02f;
                    CameraPreview.LOG.i("crop:", "applied scaleX=", Float.valueOf(f));
                    CameraPreview.LOG.i("crop:", "applied scaleY=", Float.valueOf(f2));
                    CameraPreview.this.mCropTask.end(null);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void applyCrop(float f, float f2) {
        getView().setScaleX(f);
        getView().setScaleY(f2);
    }

    /* access modifiers changed from: 0000 */
    public boolean isCropping() {
        return this.mCropping;
    }
}
