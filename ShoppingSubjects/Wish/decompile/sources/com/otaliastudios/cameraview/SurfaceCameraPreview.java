package com.otaliastudios.cameraview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

class SurfaceCameraPreview extends CameraPreview<View, SurfaceHolder> {
    /* access modifiers changed from: private */
    public static final CameraLogger LOG = CameraLogger.create(SurfaceCameraPreview.class.getSimpleName());
    private SurfaceView mSurfaceView;

    /* access modifiers changed from: protected */
    public void applyCrop(float f, float f2) {
    }

    /* access modifiers changed from: 0000 */
    public boolean supportsCropping() {
        return false;
    }

    SurfaceCameraPreview(Context context, ViewGroup viewGroup, SurfaceCallback surfaceCallback) {
        super(context, viewGroup, surfaceCallback);
    }

    /* access modifiers changed from: protected */
    public View onCreateView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.surface_view, viewGroup, false);
        viewGroup.addView(inflate, 0);
        this.mSurfaceView = (SurfaceView) inflate.findViewById(R.id.surface_view);
        SurfaceHolder holder = this.mSurfaceView.getHolder();
        holder.setType(3);
        holder.addCallback(new Callback() {
            private boolean mFirstTime = true;

            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                SurfaceCameraPreview.LOG.i("callback:", "surfaceCreated");
            }

            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                SurfaceCameraPreview.LOG.i("callback:", "surfaceChanged", "w:", Integer.valueOf(i2), "h:", Integer.valueOf(i3), "firstTime:", Boolean.valueOf(this.mFirstTime));
                if (this.mFirstTime) {
                    SurfaceCameraPreview.this.onSurfaceAvailable(i2, i3);
                    this.mFirstTime = false;
                    return;
                }
                SurfaceCameraPreview.this.onSurfaceSizeChanged(i2, i3);
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                SurfaceCameraPreview.LOG.i("callback:", "surfaceDestroyed");
                SurfaceCameraPreview.this.onSurfaceDestroyed();
                this.mFirstTime = true;
            }
        });
        return inflate.findViewById(R.id.surface_view_root);
    }

    /* access modifiers changed from: 0000 */
    public SurfaceHolder getOutput() {
        return this.mSurfaceView.getHolder();
    }

    /* access modifiers changed from: 0000 */
    public Class<SurfaceHolder> getOutputClass() {
        return SurfaceHolder.class;
    }
}
