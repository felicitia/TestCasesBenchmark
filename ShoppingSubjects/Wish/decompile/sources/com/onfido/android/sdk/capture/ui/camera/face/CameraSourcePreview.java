package com.onfido.android.sdk.capture.ui.camera.face;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.Parameters;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.onfido.android.sdk.capture.ui.Size;
import com.onfido.android.sdk.capture.ui.camera.FrameCallback;
import com.onfido.android.sdk.capture.ui.camera.face.CameraSource.PictureCallback;
import com.onfido.android.sdk.capture.ui.camera.face.CameraSource.SetParametersCallback;
import com.onfido.android.sdk.capture.ui.camera.face.CameraSource.ShutterCallback;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CameraSourcePreview extends ViewGroup {
    CameraSourceFactory a;
    private Context b;
    private SurfaceView c;
    private final a d;
    /* access modifiers changed from: private */
    public CameraSource e;
    /* access modifiers changed from: private */
    public List<Area> f = null;
    private boolean g = false;
    private double h = 1.0d;
    private double i = 1.0d;
    private int j = 720;
    private ErrorListener k;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private float p = 1.0f;
    /* access modifiers changed from: private */
    public boolean q = false;
    /* access modifiers changed from: private */
    public boolean r = false;

    public interface ErrorListener {
        void onCameraNotFound();

        void onCameraUnavailable();

        void onUnknownCameraError(UnknownCameraException unknownCameraException);
    }

    private class a implements Callback {
        boolean a;

        private a() {
            this.a = false;
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            this.a = true;
            if (CameraSourcePreview.this.r) {
                CameraSourcePreview.this.start(CameraSourcePreview.this.q);
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            this.a = false;
        }
    }

    public CameraSourcePreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        this.c = new SurfaceView(context);
        this.c.setZOrderMediaOverlay(true);
        this.d = new a();
        this.c.getHolder().addCallback(this.d);
        addView(this.c);
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CameraSourcePreview.this.e.autoFocusOnce(CameraSourcePreview.this.f, null);
            }
        });
        this.a = CameraSourceFactory.newInstance();
    }

    private void a(boolean z) {
        stop();
        release();
        Size pictureSize = getPictureSize();
        Size viewSize = getViewSize();
        CameraSourceFactory cameraSourceFactory = this.a;
        Context context = getContext();
        boolean z2 = this.g;
        boolean z3 = z;
        this.e = cameraSourceFactory.createCameraSource(context, z2 ? 1 : 0, viewSize.getWidth(), viewSize.getHeight(), pictureSize.getWidth(), pictureSize.getHeight(), a(), z3, 4, 1600000, 25000, this.c.getHolder());
    }

    private boolean a() {
        int i2 = this.b.getResources().getConfiguration().orientation;
        if (i2 == 2) {
            return false;
        }
        if (i2 == 1) {
            return true;
        }
        Log.d("CameraSourcePreview", "isPortraitMode returning false by default");
        return false;
    }

    private Size getPictureSize() {
        Size viewSize = getViewSize();
        StringBuilder sb = new StringBuilder();
        sb.append("mHorizontalWeight:");
        sb.append(this.h);
        sb.append(" mVerticalWeight:");
        sb.append(this.i);
        Log.d("CameraSourcePreview", sb.toString());
        double width = ((double) viewSize.getWidth()) / ((double) viewSize.getHeight());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("previewSizeRatio:");
        sb2.append(width);
        Log.d("CameraSourcePreview", sb2.toString());
        int i2 = (int) (((double) this.j) / this.i);
        return new Size((int) (((double) i2) * width), i2);
    }

    private Size getViewSize() {
        WindowManager windowManager = (WindowManager) this.b.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return new Size(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public void finishRecording(boolean z) {
        this.e.stopVideo();
        this.e.notifyFinishRecording(z);
    }

    public int getHorizontalOffset(int i2, int i3) {
        float f2 = (float) i2;
        float height = ((float) getHeight()) / ((float) i3);
        if (((float) getWidth()) / f2 > height) {
            return 0;
        }
        return (((int) (f2 * height)) - getWidth()) / 2;
    }

    public boolean getIsFront() {
        return this.g;
    }

    public int getPreviewHeight() {
        return this.o;
    }

    public int getPreviewHorizontalOffset() {
        return this.l;
    }

    public int getPreviewVerticalOffset() {
        return this.m;
    }

    public int getPreviewWidth() {
        return this.n;
    }

    public float getPreviewZoomFactor() {
        return this.p;
    }

    public int getVerticalOffset(int i2, int i3) {
        float width = ((float) getWidth()) / ((float) i2);
        float f2 = (float) i3;
        if (width > ((float) getHeight()) / f2) {
            return (((int) (f2 * width)) - getHeight()) / 2;
        }
        return 0;
    }

    public float getZoomFactor(int i2, int i3) {
        float width = ((float) getWidth()) / ((float) i2);
        float height = ((float) getHeight()) / ((float) i3);
        return width > height ? width : height;
    }

    public boolean isRecording() {
        return this.e != null && this.e.isRecording();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0058 A[LOOP:0: B:14:0x0052->B:16:0x0058, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r7, int r8, int r9, int r10, int r11) {
        /*
            r6 = this;
            int r10 = r10 - r8
            int r11 = r11 - r9
            com.onfido.android.sdk.capture.ui.camera.face.CameraSource r7 = r6.e
            if (r7 == 0) goto L_0x0017
            com.onfido.android.sdk.capture.ui.camera.face.CameraSource r7 = r6.e
            com.onfido.android.sdk.capture.ui.Size r7 = r7.getPreviewSize()
            if (r7 == 0) goto L_0x0017
            int r8 = r7.getWidth()
            int r7 = r7.getHeight()
            goto L_0x001b
        L_0x0017:
            r8 = 320(0x140, float:4.48E-43)
            r7 = 240(0xf0, float:3.36E-43)
        L_0x001b:
            boolean r9 = r6.a()
            if (r9 == 0) goto L_0x0022
            goto L_0x0025
        L_0x0022:
            r5 = r8
            r8 = r7
            r7 = r5
        L_0x0025:
            float r9 = (float) r10
            float r0 = (float) r7
            float r9 = r9 / r0
            float r1 = (float) r11
            float r2 = (float) r8
            float r1 = r1 / r2
            float r3 = r6.getZoomFactor(r7, r8)
            r6.p = r3
            int r3 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            r4 = 0
            if (r3 <= 0) goto L_0x0040
            float r2 = r2 * r9
            int r9 = (int) r2
            int r11 = r9 - r11
            int r11 = r11 / 2
            r0 = r10
            r10 = 0
            goto L_0x004a
        L_0x0040:
            float r0 = r0 * r1
            int r9 = (int) r0
            int r10 = r9 - r10
            int r10 = r10 / 2
            r0 = r9
            r9 = r11
            r11 = 0
        L_0x004a:
            r6.l = r10
            r6.m = r11
            r6.n = r7
            r6.o = r8
        L_0x0052:
            int r7 = r6.getChildCount()
            if (r4 >= r7) goto L_0x006a
            android.view.View r7 = r6.getChildAt(r4)
            int r8 = r10 * -1
            int r1 = r11 * -1
            int r2 = r0 - r10
            int r3 = r9 - r11
            r7.layout(r8, r1, r2, r3)
            int r4 = r4 + 1
            goto L_0x0052
        L_0x006a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.face.CameraSourcePreview.onLayout(boolean, int, int, int, int):void");
    }

    public void release() {
        if (this.e != null) {
            this.e.release();
            this.e = null;
        }
    }

    public void setErrorListener(ErrorListener errorListener) {
        this.k = errorListener;
    }

    public void setFocusMeterAreaWeight(double d2, double d3) {
        StringBuilder sb = new StringBuilder();
        sb.append("setFocusMeterAreaWeight:");
        sb.append(d2);
        sb.append(",");
        sb.append(d3);
        Log.d("CameraSourcePreview", sb.toString());
        int i2 = (int) (d3 * 1000.0d);
        int i3 = (int) (d2 * 1000.0d);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Area(new Rect(-i3, -i2, i3, i2), 800));
        this.f = arrayList;
    }

    public void setFrameCallback(FrameCallback frameCallback) {
        this.e.a(frameCallback);
    }

    public void setIsFront(boolean z) {
        if (!CameraSourceFactory.isFrontCameraSupported()) {
            z = false;
        }
        if (this.g != z) {
            this.g = z;
        }
    }

    public void setPictureWeightSize(double d2, double d3) {
        this.h = d2;
        this.i = d3;
    }

    public void start(boolean z) {
        try {
            startWithException(z);
        } catch (IOException e2) {
            Log.e("CameraSourcePreview", "Unable to startWithException camera source.", e2);
            release();
        } catch (c e3) {
            Log.e("CameraSourcePreview", "Camera unavailable", e3);
            if (this.k != null) {
                this.k.onCameraUnavailable();
            }
        } catch (d e4) {
            Log.e("CameraSourcePreview", "Camera not found", e4);
            if (this.k != null) {
                this.k.onCameraNotFound();
            }
        } catch (UnknownCameraException e5) {
            if (this.k != null) {
                this.k.onUnknownCameraError(e5);
            }
        }
    }

    public void startVideo(MediaCaptureCallback mediaCaptureCallback) {
        try {
            this.e.startVideo(mediaCaptureCallback);
        } catch (j unused) {
            mediaCaptureCallback.onErrorTakingPicture();
        }
    }

    public void startWithException(boolean z) {
        if (this.e == null) {
            this.q = z;
            a(z);
        }
        this.r = true;
        if (this.e != null && this.d.a) {
            this.e.start(this.c.getHolder());
            this.e.setParameters(new SetParametersCallback() {
                public Parameters call(Parameters parameters, Camera camera) {
                    if (CameraSourcePreview.this.f.size() <= parameters.getMaxNumMeteringAreas()) {
                        parameters.setMeteringAreas(CameraSourcePreview.this.f);
                    }
                    return parameters;
                }
            });
            requestLayout();
            invalidate();
        }
    }

    public void stop() {
        if (this.e != null) {
            this.e.stop();
            this.r = false;
        }
    }

    public void stopRecording() {
        this.e.stopVideo();
        this.e.notifyCancelRecording();
    }

    public void takePicture(ShutterCallback shutterCallback, final MediaCaptureCallback mediaCaptureCallback) {
        try {
            this.e.takePicture(shutterCallback, new PictureCallback() {
                public void onPictureTaken(byte[] bArr, int i, int i2) {
                    if (bArr != null) {
                        mediaCaptureCallback.onPictureCaptured(bArr, i, i2);
                    } else {
                        mediaCaptureCallback.onErrorTakingPicture();
                    }
                }
            }, false);
        } catch (j unused) {
            mediaCaptureCallback.onErrorTakingPicture();
        }
    }
}
