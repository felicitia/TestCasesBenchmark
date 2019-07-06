package com.otaliastudios.cameraview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.location.Location;
import android.media.MediaActionSound;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CameraView extends FrameLayout {
    private static final CameraLogger LOG = CameraLogger.create(TAG);
    private static final String TAG = "CameraView";
    CameraCallbacks mCameraCallbacks;
    /* access modifiers changed from: private */
    public CameraController mCameraController;
    /* access modifiers changed from: private */
    public CameraPreview mCameraPreview;
    /* access modifiers changed from: private */
    public boolean mCropOutput;
    List<FrameProcessor> mFrameProcessors = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */
    public WorkerHandler mFrameProcessorsHandler;
    /* access modifiers changed from: private */
    public HashMap<Gesture, GestureAction> mGestureMap = new HashMap<>(4);
    GridLinesLayout mGridLinesLayout;
    /* access modifiers changed from: private */
    public int mJpegQuality;
    /* access modifiers changed from: private */
    public boolean mKeepScreenOn;
    List<CameraListener> mListeners = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */
    public OrientationHelper mOrientationHelper;
    PinchGestureLayout mPinchGestureLayout;
    /* access modifiers changed from: private */
    public boolean mPlaySounds;
    ScrollGestureLayout mScrollGestureLayout;
    private MediaActionSound mSound;
    TapGestureLayout mTapGestureLayout;
    /* access modifiers changed from: private */
    public Handler mUiHandler;
    /* access modifiers changed from: private */
    public WorkerHandler mWorkerHandler;

    /* renamed from: com.otaliastudios.cameraview.CameraView$4 reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$otaliastudios$cameraview$Facing = new int[Facing.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$otaliastudios$cameraview$Flash = new int[Flash.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(37:0|(2:1|2)|3|5|6|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|23|24|25|26|27|28|29|30|31|(2:33|34)|35|37|38|39|40|41|42|43|44|45|46|48) */
        /* JADX WARNING: Can't wrap try/catch for region: R(38:0|(2:1|2)|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|20|21|23|24|25|26|27|28|29|30|31|(2:33|34)|35|37|38|39|40|41|42|43|44|45|46|48) */
        /* JADX WARNING: Can't wrap try/catch for region: R(39:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|20|21|23|24|25|26|27|28|29|30|31|(2:33|34)|35|37|38|39|40|41|42|43|44|45|46|48) */
        /* JADX WARNING: Can't wrap try/catch for region: R(40:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|20|21|23|24|25|26|27|28|29|30|31|33|34|35|37|38|39|40|41|42|43|44|45|46|48) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0065 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0079 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00a1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00ab */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00b5 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00bf */
        static {
            /*
                com.otaliastudios.cameraview.Flash[] r0 = com.otaliastudios.cameraview.Flash.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$otaliastudios$cameraview$Flash = r0
                r0 = 1
                int[] r1 = $SwitchMap$com$otaliastudios$cameraview$Flash     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.otaliastudios.cameraview.Flash r2 = com.otaliastudios.cameraview.Flash.OFF     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = $SwitchMap$com$otaliastudios$cameraview$Flash     // Catch:{ NoSuchFieldError -> 0x001f }
                com.otaliastudios.cameraview.Flash r3 = com.otaliastudios.cameraview.Flash.ON     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = $SwitchMap$com$otaliastudios$cameraview$Flash     // Catch:{ NoSuchFieldError -> 0x002a }
                com.otaliastudios.cameraview.Flash r4 = com.otaliastudios.cameraview.Flash.AUTO     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                r3 = 4
                int[] r4 = $SwitchMap$com$otaliastudios$cameraview$Flash     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.otaliastudios.cameraview.Flash r5 = com.otaliastudios.cameraview.Flash.TORCH     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                com.otaliastudios.cameraview.Facing[] r4 = com.otaliastudios.cameraview.Facing.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$com$otaliastudios$cameraview$Facing = r4
                int[] r4 = $SwitchMap$com$otaliastudios$cameraview$Facing     // Catch:{ NoSuchFieldError -> 0x0048 }
                com.otaliastudios.cameraview.Facing r5 = com.otaliastudios.cameraview.Facing.BACK     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                int[] r4 = $SwitchMap$com$otaliastudios$cameraview$Facing     // Catch:{ NoSuchFieldError -> 0x0052 }
                com.otaliastudios.cameraview.Facing r5 = com.otaliastudios.cameraview.Facing.FRONT     // Catch:{ NoSuchFieldError -> 0x0052 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0052 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0052 }
            L_0x0052:
                com.otaliastudios.cameraview.GestureAction[] r4 = com.otaliastudios.cameraview.GestureAction.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$com$otaliastudios$cameraview$GestureAction = r4
                int[] r4 = $SwitchMap$com$otaliastudios$cameraview$GestureAction     // Catch:{ NoSuchFieldError -> 0x0065 }
                com.otaliastudios.cameraview.GestureAction r5 = com.otaliastudios.cameraview.GestureAction.CAPTURE     // Catch:{ NoSuchFieldError -> 0x0065 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0065 }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x0065 }
            L_0x0065:
                int[] r4 = $SwitchMap$com$otaliastudios$cameraview$GestureAction     // Catch:{ NoSuchFieldError -> 0x006f }
                com.otaliastudios.cameraview.GestureAction r5 = com.otaliastudios.cameraview.GestureAction.FOCUS     // Catch:{ NoSuchFieldError -> 0x006f }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x006f }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x006f }
            L_0x006f:
                int[] r4 = $SwitchMap$com$otaliastudios$cameraview$GestureAction     // Catch:{ NoSuchFieldError -> 0x0079 }
                com.otaliastudios.cameraview.GestureAction r5 = com.otaliastudios.cameraview.GestureAction.FOCUS_WITH_MARKER     // Catch:{ NoSuchFieldError -> 0x0079 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0079 }
                r4[r5] = r2     // Catch:{ NoSuchFieldError -> 0x0079 }
            L_0x0079:
                int[] r4 = $SwitchMap$com$otaliastudios$cameraview$GestureAction     // Catch:{ NoSuchFieldError -> 0x0083 }
                com.otaliastudios.cameraview.GestureAction r5 = com.otaliastudios.cameraview.GestureAction.ZOOM     // Catch:{ NoSuchFieldError -> 0x0083 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0083 }
            L_0x0083:
                r4 = 5
                int[] r5 = $SwitchMap$com$otaliastudios$cameraview$GestureAction     // Catch:{ NoSuchFieldError -> 0x008e }
                com.otaliastudios.cameraview.GestureAction r6 = com.otaliastudios.cameraview.GestureAction.EXPOSURE_CORRECTION     // Catch:{ NoSuchFieldError -> 0x008e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x008e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x008e }
            L_0x008e:
                com.otaliastudios.cameraview.Gesture[] r5 = com.otaliastudios.cameraview.Gesture.values()
                int r5 = r5.length
                int[] r5 = new int[r5]
                $SwitchMap$com$otaliastudios$cameraview$Gesture = r5
                int[] r5 = $SwitchMap$com$otaliastudios$cameraview$Gesture     // Catch:{ NoSuchFieldError -> 0x00a1 }
                com.otaliastudios.cameraview.Gesture r6 = com.otaliastudios.cameraview.Gesture.PINCH     // Catch:{ NoSuchFieldError -> 0x00a1 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a1 }
                r5[r6] = r0     // Catch:{ NoSuchFieldError -> 0x00a1 }
            L_0x00a1:
                int[] r0 = $SwitchMap$com$otaliastudios$cameraview$Gesture     // Catch:{ NoSuchFieldError -> 0x00ab }
                com.otaliastudios.cameraview.Gesture r5 = com.otaliastudios.cameraview.Gesture.TAP     // Catch:{ NoSuchFieldError -> 0x00ab }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ab }
                r0[r5] = r1     // Catch:{ NoSuchFieldError -> 0x00ab }
            L_0x00ab:
                int[] r0 = $SwitchMap$com$otaliastudios$cameraview$Gesture     // Catch:{ NoSuchFieldError -> 0x00b5 }
                com.otaliastudios.cameraview.Gesture r1 = com.otaliastudios.cameraview.Gesture.LONG_TAP     // Catch:{ NoSuchFieldError -> 0x00b5 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b5 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b5 }
            L_0x00b5:
                int[] r0 = $SwitchMap$com$otaliastudios$cameraview$Gesture     // Catch:{ NoSuchFieldError -> 0x00bf }
                com.otaliastudios.cameraview.Gesture r1 = com.otaliastudios.cameraview.Gesture.SCROLL_HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x00bf }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00bf }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x00bf }
            L_0x00bf:
                int[] r0 = $SwitchMap$com$otaliastudios$cameraview$Gesture     // Catch:{ NoSuchFieldError -> 0x00c9 }
                com.otaliastudios.cameraview.Gesture r1 = com.otaliastudios.cameraview.Gesture.SCROLL_VERTICAL     // Catch:{ NoSuchFieldError -> 0x00c9 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c9 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x00c9 }
            L_0x00c9:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.otaliastudios.cameraview.CameraView.AnonymousClass4.<clinit>():void");
        }
    }

    private class Callbacks implements CameraCallbacks {
        /* access modifiers changed from: private */
        public CameraLogger mLogger = CameraLogger.create(CameraCallbacks.class.getSimpleName());

        Callbacks() {
        }

        public void dispatchOnCameraOpened(final CameraOptions cameraOptions) {
            this.mLogger.i("dispatchOnCameraOpened", cameraOptions);
            CameraView.this.mUiHandler.post(new Runnable() {
                public void run() {
                    for (CameraListener onCameraOpened : CameraView.this.mListeners) {
                        onCameraOpened.onCameraOpened(cameraOptions);
                    }
                }
            });
        }

        public void dispatchOnCameraClosed() {
            this.mLogger.i("dispatchOnCameraClosed");
            CameraView.this.mUiHandler.post(new Runnable() {
                public void run() {
                    for (CameraListener onCameraClosed : CameraView.this.mListeners) {
                        onCameraClosed.onCameraClosed();
                    }
                }
            });
        }

        public void onCameraPreviewSizeChanged() {
            this.mLogger.i("onCameraPreviewSizeChanged");
            CameraView.this.mUiHandler.post(new Runnable() {
                public void run() {
                    CameraView.this.requestLayout();
                }
            });
        }

        public void onShutter(boolean z) {
            if (z && CameraView.this.mPlaySounds) {
                CameraView.this.playSound(0);
            }
        }

        public void processImage(final byte[] bArr, final boolean z, boolean z2) {
            this.mLogger.i("processImage");
            CameraView.this.mWorkerHandler.post(new Runnable() {
                public void run() {
                    byte[] bArr = bArr;
                    if (CameraView.this.mCropOutput && CameraView.this.mCameraPreview.isCropping()) {
                        AspectRatio of = AspectRatio.of(z ? CameraView.this.getWidth() : CameraView.this.getHeight(), z ? CameraView.this.getHeight() : CameraView.this.getWidth());
                        Callbacks.this.mLogger.i("processImage", "is consistent?", Boolean.valueOf(z));
                        Callbacks.this.mLogger.i("processImage", "viewWidth?", Integer.valueOf(CameraView.this.getWidth()), "viewHeight?", Integer.valueOf(CameraView.this.getHeight()));
                        bArr = CropHelper.cropToJpeg(bArr, of, CameraView.this.mJpegQuality);
                    }
                    Callbacks.this.dispatchOnPictureTaken(bArr);
                }
            });
        }

        public void processSnapshot(final YuvImage yuvImage, final boolean z, boolean z2) {
            this.mLogger.i("processSnapshot");
            CameraView.this.mWorkerHandler.post(new Runnable() {
                public void run() {
                    byte[] bArr;
                    if (!CameraView.this.mCropOutput || !CameraView.this.mCameraPreview.isCropping()) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        yuvImage.compressToJpeg(new Rect(0, 0, yuvImage.getWidth(), yuvImage.getHeight()), CameraView.this.mJpegQuality, byteArrayOutputStream);
                        bArr = byteArrayOutputStream.toByteArray();
                    } else {
                        AspectRatio of = AspectRatio.of(z ? CameraView.this.getWidth() : CameraView.this.getHeight(), z ? CameraView.this.getHeight() : CameraView.this.getWidth());
                        Callbacks.this.mLogger.i("processSnapshot", "is consistent?", Boolean.valueOf(z));
                        Callbacks.this.mLogger.i("processSnapshot", "viewWidth?", Integer.valueOf(CameraView.this.getWidth()), "viewHeight?", Integer.valueOf(CameraView.this.getHeight()));
                        bArr = CropHelper.cropToJpeg(yuvImage, of, CameraView.this.mJpegQuality);
                    }
                    Callbacks.this.dispatchOnPictureTaken(bArr);
                }
            });
        }

        /* access modifiers changed from: private */
        public void dispatchOnPictureTaken(final byte[] bArr) {
            this.mLogger.i("dispatchOnPictureTaken");
            CameraView.this.mUiHandler.post(new Runnable() {
                public void run() {
                    for (CameraListener onPictureTaken : CameraView.this.mListeners) {
                        onPictureTaken.onPictureTaken(bArr);
                    }
                }
            });
        }

        public void dispatchOnVideoTaken(final File file) {
            this.mLogger.i("dispatchOnVideoTaken", file);
            CameraView.this.mUiHandler.post(new Runnable() {
                public void run() {
                    for (CameraListener onVideoTaken : CameraView.this.mListeners) {
                        onVideoTaken.onVideoTaken(file);
                    }
                }
            });
        }

        public void dispatchOnFocusStart(final Gesture gesture, final PointF pointF) {
            this.mLogger.i("dispatchOnFocusStart", gesture, pointF);
            CameraView.this.mUiHandler.post(new Runnable() {
                public void run() {
                    if (gesture != null && CameraView.this.mGestureMap.get(gesture) == GestureAction.FOCUS_WITH_MARKER) {
                        CameraView.this.mTapGestureLayout.onFocusStart(pointF);
                    }
                    for (CameraListener onFocusStart : CameraView.this.mListeners) {
                        onFocusStart.onFocusStart(pointF);
                    }
                }
            });
        }

        public void dispatchOnFocusEnd(final Gesture gesture, final boolean z, final PointF pointF) {
            this.mLogger.i("dispatchOnFocusEnd", gesture, Boolean.valueOf(z), pointF);
            CameraView.this.mUiHandler.post(new Runnable() {
                public void run() {
                    if (z && CameraView.this.mPlaySounds) {
                        CameraView.this.playSound(1);
                    }
                    if (gesture != null && CameraView.this.mGestureMap.get(gesture) == GestureAction.FOCUS_WITH_MARKER) {
                        CameraView.this.mTapGestureLayout.onFocusEnd(z);
                    }
                    for (CameraListener onFocusEnd : CameraView.this.mListeners) {
                        onFocusEnd.onFocusEnd(z, pointF);
                    }
                }
            });
        }

        public void onDeviceOrientationChanged(int i) {
            this.mLogger.i("onDeviceOrientationChanged", Integer.valueOf(i));
            CameraView.this.mCameraController.setDeviceOrientation(i);
            final int displayOffset = (i + CameraView.this.mOrientationHelper.getDisplayOffset()) % 360;
            CameraView.this.mUiHandler.post(new Runnable() {
                public void run() {
                    for (CameraListener onOrientationChanged : CameraView.this.mListeners) {
                        onOrientationChanged.onOrientationChanged(displayOffset);
                    }
                }
            });
        }

        public void dispatchOnZoomChanged(final float f, final PointF[] pointFArr) {
            this.mLogger.i("dispatchOnZoomChanged", Float.valueOf(f));
            CameraView.this.mUiHandler.post(new Runnable() {
                public void run() {
                    for (CameraListener onZoomChanged : CameraView.this.mListeners) {
                        onZoomChanged.onZoomChanged(f, new float[]{0.0f, 1.0f}, pointFArr);
                    }
                }
            });
        }

        public void dispatchOnExposureCorrectionChanged(final float f, final float[] fArr, final PointF[] pointFArr) {
            this.mLogger.i("dispatchOnExposureCorrectionChanged", Float.valueOf(f));
            CameraView.this.mUiHandler.post(new Runnable() {
                public void run() {
                    for (CameraListener onExposureCorrectionChanged : CameraView.this.mListeners) {
                        onExposureCorrectionChanged.onExposureCorrectionChanged(f, fArr, pointFArr);
                    }
                }
            });
        }

        public void dispatchFrame(final Frame frame) {
            if (CameraView.this.mFrameProcessors.isEmpty()) {
                frame.release();
                return;
            }
            this.mLogger.v("dispatchFrame:", Long.valueOf(frame.getTime()), "processors:", Integer.valueOf(CameraView.this.mFrameProcessors.size()));
            CameraView.this.mFrameProcessorsHandler.post(new Runnable() {
                public void run() {
                    for (FrameProcessor process : CameraView.this.mFrameProcessors) {
                        process.process(frame);
                    }
                    frame.release();
                }
            });
        }

        public void dispatchError(final CameraException cameraException) {
            this.mLogger.i("dispatchError", cameraException);
            CameraView.this.mUiHandler.post(new Runnable() {
                public void run() {
                    for (CameraListener onCameraError : CameraView.this.mListeners) {
                        onCameraError.onCameraError(cameraException);
                    }
                }
            });
        }
    }

    interface CameraCallbacks extends Callback {
        void dispatchError(CameraException cameraException);

        void dispatchFrame(Frame frame);

        void dispatchOnCameraClosed();

        void dispatchOnCameraOpened(CameraOptions cameraOptions);

        void dispatchOnExposureCorrectionChanged(float f, float[] fArr, PointF[] pointFArr);

        void dispatchOnFocusEnd(Gesture gesture, boolean z, PointF pointF);

        void dispatchOnFocusStart(Gesture gesture, PointF pointF);

        void dispatchOnVideoTaken(File file);

        void dispatchOnZoomChanged(float f, PointF[] pointFArr);

        void onCameraPreviewSizeChanged();

        void onShutter(boolean z);

        void processImage(byte[] bArr, boolean z, boolean z2);

        void processSnapshot(YuvImage yuvImage, boolean z, boolean z2);
    }

    private String ms(int i) {
        if (i == Integer.MIN_VALUE) {
            return "AT_MOST";
        }
        if (i == 0) {
            return "UNSPECIFIED";
        }
        if (i != 1073741824) {
            return null;
        }
        return "EXACTLY";
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public CameraView(Context context) {
        super(context, null);
        init(context, null);
    }

    public CameraView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        Audio audio;
        int i;
        SizeSelector sizeSelector;
        Context context2 = context;
        setWillNotDraw(false);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.CameraView, 0, 0);
        int integer = obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraJpegQuality, 100);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.CameraView_cameraCropOutput, false);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.CameraView_cameraPlaySounds, true);
        Facing fromValue = Facing.fromValue(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraFacing, Facing.DEFAULT.value()));
        Flash fromValue2 = Flash.fromValue(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraFlash, Flash.DEFAULT.value()));
        Grid fromValue3 = Grid.fromValue(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraGrid, Grid.DEFAULT.value()));
        WhiteBalance fromValue4 = WhiteBalance.fromValue(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraWhiteBalance, WhiteBalance.DEFAULT.value()));
        VideoQuality fromValue5 = VideoQuality.fromValue(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraVideoQuality, VideoQuality.DEFAULT.value()));
        SessionType fromValue6 = SessionType.fromValue(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraSessionType, SessionType.DEFAULT.value()));
        Hdr fromValue7 = Hdr.fromValue(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraHdr, Hdr.DEFAULT.value()));
        Audio fromValue8 = Audio.fromValue(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraAudio, Audio.DEFAULT.value()));
        ArrayList arrayList = new ArrayList(3);
        if (obtainStyledAttributes.hasValue(R.styleable.CameraView_cameraPictureSizeMinWidth)) {
            audio = fromValue8;
            i = 0;
            arrayList.add(SizeSelectors.minWidth(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraPictureSizeMinWidth, 0)));
        } else {
            audio = fromValue8;
            i = 0;
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CameraView_cameraPictureSizeMaxWidth)) {
            arrayList.add(SizeSelectors.maxWidth(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraPictureSizeMaxWidth, i)));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CameraView_cameraPictureSizeMinHeight)) {
            arrayList.add(SizeSelectors.minHeight(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraPictureSizeMinHeight, i)));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CameraView_cameraPictureSizeMaxHeight)) {
            arrayList.add(SizeSelectors.maxHeight(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraPictureSizeMaxHeight, i)));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CameraView_cameraPictureSizeMinArea)) {
            arrayList.add(SizeSelectors.minArea(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraPictureSizeMinArea, i)));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CameraView_cameraPictureSizeMaxArea)) {
            arrayList.add(SizeSelectors.maxArea(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraPictureSizeMaxArea, i)));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.CameraView_cameraPictureSizeAspectRatio)) {
            arrayList.add(SizeSelectors.aspectRatio(AspectRatio.parse(obtainStyledAttributes.getString(R.styleable.CameraView_cameraPictureSizeAspectRatio)), 0.0f));
        }
        if (obtainStyledAttributes.getBoolean(R.styleable.CameraView_cameraPictureSizeSmallest, false)) {
            arrayList.add(SizeSelectors.smallest());
        }
        if (obtainStyledAttributes.getBoolean(R.styleable.CameraView_cameraPictureSizeBiggest, false)) {
            arrayList.add(SizeSelectors.biggest());
        }
        if (!arrayList.isEmpty()) {
            sizeSelector = SizeSelectors.and((SizeSelector[]) arrayList.toArray(new SizeSelector[arrayList.size()]));
        } else {
            sizeSelector = SizeSelectors.biggest();
        }
        GestureAction fromValue9 = GestureAction.fromValue(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraGestureTap, GestureAction.DEFAULT_TAP.value()));
        GestureAction fromValue10 = GestureAction.fromValue(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraGestureLongTap, GestureAction.DEFAULT_LONG_TAP.value()));
        GestureAction fromValue11 = GestureAction.fromValue(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraGesturePinch, GestureAction.DEFAULT_PINCH.value()));
        GestureAction fromValue12 = GestureAction.fromValue(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraGestureScrollHorizontal, GestureAction.DEFAULT_SCROLL_HORIZONTAL.value()));
        GestureAction fromValue13 = GestureAction.fromValue(obtainStyledAttributes.getInteger(R.styleable.CameraView_cameraGestureScrollVertical, GestureAction.DEFAULT_SCROLL_VERTICAL.value()));
        obtainStyledAttributes.recycle();
        this.mCameraCallbacks = new Callbacks();
        this.mCameraController = instantiateCameraController(this.mCameraCallbacks);
        this.mUiHandler = new Handler(Looper.getMainLooper());
        this.mWorkerHandler = WorkerHandler.get("CameraViewWorker");
        this.mFrameProcessorsHandler = WorkerHandler.get("FrameProcessorsWorker");
        this.mGridLinesLayout = new GridLinesLayout(context2);
        this.mPinchGestureLayout = new PinchGestureLayout(context2);
        this.mTapGestureLayout = new TapGestureLayout(context2);
        this.mScrollGestureLayout = new ScrollGestureLayout(context2);
        addView(this.mGridLinesLayout);
        addView(this.mPinchGestureLayout);
        addView(this.mTapGestureLayout);
        addView(this.mScrollGestureLayout);
        setCropOutput(z);
        setJpegQuality(integer);
        setPlaySounds(z2);
        setFacing(fromValue);
        setFlash(fromValue2);
        setSessionType(fromValue6);
        setVideoQuality(fromValue5);
        setWhiteBalance(fromValue4);
        setGrid(fromValue3);
        setHdr(fromValue7);
        setAudio(audio);
        setPictureSize(sizeSelector);
        mapGesture(Gesture.TAP, fromValue9);
        mapGesture(Gesture.LONG_TAP, fromValue10);
        mapGesture(Gesture.PINCH, fromValue11);
        mapGesture(Gesture.SCROLL_HORIZONTAL, fromValue12);
        mapGesture(Gesture.SCROLL_VERTICAL, fromValue13);
        if (!isInEditMode()) {
            this.mOrientationHelper = new OrientationHelper(context2, this.mCameraCallbacks);
        }
    }

    /* access modifiers changed from: protected */
    public CameraController instantiateCameraController(CameraCallbacks cameraCallbacks) {
        return new Camera1(cameraCallbacks);
    }

    /* access modifiers changed from: protected */
    public CameraPreview instantiatePreview(Context context, ViewGroup viewGroup) {
        LOG.w("preview:", "isHardwareAccelerated:", Boolean.valueOf(isHardwareAccelerated()));
        return isHardwareAccelerated() ? new TextureCameraPreview(context, viewGroup, null) : new SurfaceCameraPreview(context, viewGroup, null);
    }

    /* access modifiers changed from: 0000 */
    public void instantiatePreview() {
        this.mCameraPreview = instantiatePreview(getContext(), this);
        this.mCameraController.setPreview(this.mCameraPreview);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mCameraPreview == null) {
            instantiatePreview();
        }
        if (!isInEditMode()) {
            this.mOrientationHelper.enable(getContext());
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        if (!isInEditMode()) {
            this.mOrientationHelper.disable();
        }
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        Size previewSize = getPreviewSize();
        if (previewSize == null) {
            LOG.w("onMeasure:", "surface is not ready. Calling default behavior.");
            super.onMeasure(i, i2);
            return;
        }
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        boolean shouldFlipSizes = this.mCameraController.shouldFlipSizes();
        float height = (float) (shouldFlipSizes ? previewSize.getHeight() : previewSize.getWidth());
        float width = (float) (shouldFlipSizes ? previewSize.getWidth() : previewSize.getHeight());
        LayoutParams layoutParams = getLayoutParams();
        if (!this.mCameraPreview.supportsCropping()) {
            if (mode == 1073741824) {
                mode = Integer.MIN_VALUE;
            }
            if (mode2 == 1073741824) {
                mode2 = Integer.MIN_VALUE;
            }
        } else {
            if (mode == Integer.MIN_VALUE && layoutParams.width == -1) {
                mode = 1073741824;
            }
            if (mode2 == Integer.MIN_VALUE && layoutParams.height == -1) {
                mode2 = 1073741824;
            }
        }
        CameraLogger cameraLogger = LOG;
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(size);
        sb.append("[");
        sb.append(ms(mode));
        sb.append("]x");
        sb.append(size2);
        sb.append("[");
        sb.append(ms(mode2));
        sb.append("])");
        cameraLogger.i("onMeasure:", "requested dimensions are", sb.toString());
        CameraLogger cameraLogger2 = LOG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("(");
        sb2.append(height);
        sb2.append("x");
        sb2.append(width);
        sb2.append(")");
        cameraLogger2.i("onMeasure:", "previewSize is", sb2.toString());
        if (mode == 1073741824 && mode2 == 1073741824) {
            CameraLogger cameraLogger3 = LOG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("(");
            sb3.append(size);
            sb3.append("x");
            sb3.append(size2);
            sb3.append(")");
            cameraLogger3.w("onMeasure:", "both are MATCH_PARENT or fixed value. We adapt.", "This means CROP_CENTER.", sb3.toString());
            super.onMeasure(i, i2);
        } else if (mode == 0 && mode2 == 0) {
            CameraLogger cameraLogger4 = LOG;
            StringBuilder sb4 = new StringBuilder();
            sb4.append("(");
            sb4.append(height);
            sb4.append("x");
            sb4.append(width);
            sb4.append(")");
            cameraLogger4.i("onMeasure:", "both are completely free.", "We respect that and extend to the whole preview size.", sb4.toString());
            super.onMeasure(MeasureSpec.makeMeasureSpec((int) height, 1073741824), MeasureSpec.makeMeasureSpec((int) width, 1073741824));
        } else {
            float f = width / height;
            if (mode == 0 || mode2 == 0) {
                if (mode == 0) {
                    size = (int) (((float) size2) / f);
                } else {
                    size2 = (int) (((float) size) * f);
                }
                CameraLogger cameraLogger5 = LOG;
                StringBuilder sb5 = new StringBuilder();
                sb5.append("(");
                sb5.append(size);
                sb5.append("x");
                sb5.append(size2);
                sb5.append(")");
                cameraLogger5.i("onMeasure:", "one dimension was free, we adapted it to fit the aspect ratio.", sb5.toString());
                super.onMeasure(MeasureSpec.makeMeasureSpec(size, 1073741824), MeasureSpec.makeMeasureSpec(size2, 1073741824));
            } else if (mode == 1073741824 || mode2 == 1073741824) {
                if (mode == Integer.MIN_VALUE) {
                    size = Math.min((int) (((float) size2) / f), size);
                } else {
                    size2 = Math.min((int) (((float) size) * f), size2);
                }
                CameraLogger cameraLogger6 = LOG;
                StringBuilder sb6 = new StringBuilder();
                sb6.append("(");
                sb6.append(size);
                sb6.append("x");
                sb6.append(size2);
                sb6.append(")");
                cameraLogger6.i("onMeasure:", "one dimension was EXACTLY, another AT_MOST.", "We have TRIED to fit the aspect ratio, but it's not guaranteed.", sb6.toString());
                super.onMeasure(MeasureSpec.makeMeasureSpec(size, 1073741824), MeasureSpec.makeMeasureSpec(size2, 1073741824));
            } else {
                float f2 = (float) size2;
                float f3 = (float) size;
                if (f2 / f3 >= f) {
                    size2 = (int) (f3 * f);
                } else {
                    size = (int) (f2 / f);
                }
                CameraLogger cameraLogger7 = LOG;
                StringBuilder sb7 = new StringBuilder();
                sb7.append("(");
                sb7.append(size);
                sb7.append("x");
                sb7.append(size2);
                sb7.append(")");
                cameraLogger7.i("onMeasure:", "both dimension were AT_MOST.", "We fit the preview aspect ratio.", sb7.toString());
                super.onMeasure(MeasureSpec.makeMeasureSpec(size, 1073741824), MeasureSpec.makeMeasureSpec(size2, 1073741824));
            }
        }
    }

    public boolean mapGesture(Gesture gesture, GestureAction gestureAction) {
        GestureAction gestureAction2 = GestureAction.NONE;
        boolean z = false;
        if (gesture.isAssignableTo(gestureAction)) {
            this.mGestureMap.put(gesture, gestureAction);
            switch (gesture) {
                case PINCH:
                    PinchGestureLayout pinchGestureLayout = this.mPinchGestureLayout;
                    if (this.mGestureMap.get(Gesture.PINCH) != gestureAction2) {
                        z = true;
                    }
                    pinchGestureLayout.enable(z);
                    break;
                case TAP:
                case LONG_TAP:
                    TapGestureLayout tapGestureLayout = this.mTapGestureLayout;
                    if (!(this.mGestureMap.get(Gesture.TAP) == gestureAction2 && this.mGestureMap.get(Gesture.LONG_TAP) == gestureAction2)) {
                        z = true;
                    }
                    tapGestureLayout.enable(z);
                    break;
                case SCROLL_HORIZONTAL:
                case SCROLL_VERTICAL:
                    ScrollGestureLayout scrollGestureLayout = this.mScrollGestureLayout;
                    if (!(this.mGestureMap.get(Gesture.SCROLL_HORIZONTAL) == gestureAction2 && this.mGestureMap.get(Gesture.SCROLL_VERTICAL) == gestureAction2)) {
                        z = true;
                    }
                    scrollGestureLayout.enable(z);
                    break;
            }
            return true;
        }
        mapGesture(gesture, gestureAction2);
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isStarted()) {
            return true;
        }
        CameraOptions cameraOptions = this.mCameraController.getCameraOptions();
        if (this.mPinchGestureLayout.onTouchEvent(motionEvent)) {
            LOG.i("onTouchEvent", "pinch!");
            onGesture(this.mPinchGestureLayout, cameraOptions);
        } else if (this.mScrollGestureLayout.onTouchEvent(motionEvent)) {
            LOG.i("onTouchEvent", "scroll!");
            onGesture(this.mScrollGestureLayout, cameraOptions);
        } else if (this.mTapGestureLayout.onTouchEvent(motionEvent)) {
            LOG.i("onTouchEvent", "tap!");
            onGesture(this.mTapGestureLayout, cameraOptions);
        }
        return true;
    }

    private void onGesture(GestureLayout gestureLayout, CameraOptions cameraOptions) {
        Gesture gestureType = gestureLayout.getGestureType();
        GestureAction gestureAction = (GestureAction) this.mGestureMap.get(gestureType);
        PointF[] points = gestureLayout.getPoints();
        switch (gestureAction) {
            case CAPTURE:
                this.mCameraController.capturePicture();
                return;
            case FOCUS:
            case FOCUS_WITH_MARKER:
                this.mCameraController.startAutoFocus(gestureType, points[0]);
                return;
            case ZOOM:
                this.mCameraController.setZoom(gestureLayout.scaleValue(this.mCameraController.getZoomValue(), 0.0f, 1.0f), points, true);
                return;
            case EXPOSURE_CORRECTION:
                float exposureCorrectionValue = this.mCameraController.getExposureCorrectionValue();
                float exposureCorrectionMinValue = cameraOptions.getExposureCorrectionMinValue();
                float exposureCorrectionMaxValue = cameraOptions.getExposureCorrectionMaxValue();
                this.mCameraController.setExposureCorrection(gestureLayout.scaleValue(exposureCorrectionValue, exposureCorrectionMinValue, exposureCorrectionMaxValue), new float[]{exposureCorrectionMinValue, exposureCorrectionMaxValue}, points, true);
                return;
            default:
                return;
        }
    }

    public boolean isStarted() {
        return this.mCameraController.getState() >= 2;
    }

    private boolean isStopped() {
        return this.mCameraController.getState() == 0;
    }

    public void start() {
        if (isEnabled() && checkPermissions(getSessionType(), getAudio())) {
            this.mOrientationHelper.enable(getContext());
            this.mCameraController.setDisplayOffset(this.mOrientationHelper.getDisplayOffset());
            this.mCameraController.start();
        }
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public boolean checkPermissions(SessionType sessionType, Audio audio) {
        checkPermissionsManifestOrThrow(sessionType, audio);
        if (VERSION.SDK_INT < 23) {
            return true;
        }
        Context context = getContext();
        boolean z = sessionType == SessionType.VIDEO && audio == Audio.ON;
        boolean z2 = context.checkSelfPermission("android.permission.CAMERA") != 0;
        boolean z3 = z && context.checkSelfPermission("android.permission.RECORD_AUDIO") != 0;
        if (!z2 && !z3) {
            return true;
        }
        requestPermissions(z2, z3);
        return false;
    }

    private void checkPermissionsManifestOrThrow(SessionType sessionType, Audio audio) {
        if (sessionType == SessionType.VIDEO && audio == Audio.ON) {
            try {
                String[] strArr = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 4096).requestedPermissions;
                int length = strArr.length;
                int i = 0;
                while (i < length) {
                    if (!strArr[i].equals("android.permission.RECORD_AUDIO")) {
                        i++;
                    } else {
                        return;
                    }
                }
                LOG.e("Permission error:", "When the session type is set to video,", "the RECORD_AUDIO permission should be added to the app manifest file.");
                throw new IllegalStateException(CameraLogger.lastMessage);
            } catch (NameNotFoundException unused) {
            }
        }
    }

    public void stop() {
        this.mCameraController.stop();
    }

    public void destroy() {
        clearCameraListeners();
        clearFrameProcessors();
        this.mCameraController.destroy();
    }

    public void set(Control control) {
        if (control instanceof Audio) {
            setAudio((Audio) control);
        } else if (control instanceof Facing) {
            setFacing((Facing) control);
        } else if (control instanceof Flash) {
            setFlash((Flash) control);
        } else if (control instanceof Grid) {
            setGrid((Grid) control);
        } else if (control instanceof Hdr) {
            setHdr((Hdr) control);
        } else if (control instanceof SessionType) {
            setSessionType((SessionType) control);
        } else if (control instanceof VideoQuality) {
            setVideoQuality((VideoQuality) control);
        } else if (control instanceof WhiteBalance) {
            setWhiteBalance((WhiteBalance) control);
        }
    }

    public CameraOptions getCameraOptions() {
        return this.mCameraController.getCameraOptions();
    }

    public ExtraProperties getExtraProperties() {
        return this.mCameraController.getExtraProperties();
    }

    public void setExposureCorrection(float f) {
        CameraOptions cameraOptions = getCameraOptions();
        if (cameraOptions != null) {
            float exposureCorrectionMinValue = cameraOptions.getExposureCorrectionMinValue();
            float exposureCorrectionMaxValue = cameraOptions.getExposureCorrectionMaxValue();
            if (f < exposureCorrectionMinValue) {
                f = exposureCorrectionMinValue;
            }
            if (f > exposureCorrectionMaxValue) {
                f = exposureCorrectionMaxValue;
            }
            this.mCameraController.setExposureCorrection(f, null, null, false);
        }
    }

    public float getExposureCorrection() {
        return this.mCameraController.getExposureCorrectionValue();
    }

    public void setZoom(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        this.mCameraController.setZoom(f, null, false);
    }

    public float getZoom() {
        return this.mCameraController.getZoomValue();
    }

    public void setGrid(Grid grid) {
        this.mGridLinesLayout.setGridMode(grid);
    }

    public Grid getGrid() {
        return this.mGridLinesLayout.getGridMode();
    }

    public void setHdr(Hdr hdr) {
        this.mCameraController.setHdr(hdr);
    }

    public Hdr getHdr() {
        return this.mCameraController.getHdr();
    }

    public void setLocation(Location location) {
        this.mCameraController.setLocation(location);
    }

    public Location getLocation() {
        return this.mCameraController.getLocation();
    }

    public void setWhiteBalance(WhiteBalance whiteBalance) {
        this.mCameraController.setWhiteBalance(whiteBalance);
    }

    public WhiteBalance getWhiteBalance() {
        return this.mCameraController.getWhiteBalance();
    }

    public void setFacing(Facing facing) {
        this.mCameraController.setFacing(facing);
    }

    public Facing getFacing() {
        return this.mCameraController.getFacing();
    }

    public void setFlash(Flash flash) {
        this.mCameraController.setFlash(flash);
    }

    public Flash getFlash() {
        return this.mCameraController.getFlash();
    }

    public void setAudio(Audio audio) {
        if (audio == getAudio() || isStopped()) {
            this.mCameraController.setAudio(audio);
        } else if (checkPermissions(getSessionType(), audio)) {
            this.mCameraController.setAudio(audio);
        } else {
            stop();
        }
    }

    public Audio getAudio() {
        return this.mCameraController.getAudio();
    }

    public void setSessionType(SessionType sessionType) {
        if (sessionType == getSessionType() || isStopped()) {
            this.mCameraController.setSessionType(sessionType);
        } else if (checkPermissions(sessionType, getAudio())) {
            this.mCameraController.setSessionType(sessionType);
        } else {
            stop();
        }
    }

    public SessionType getSessionType() {
        return this.mCameraController.getSessionType();
    }

    public void setPictureSize(SizeSelector sizeSelector) {
        this.mCameraController.setPictureSizeSelector(sizeSelector);
    }

    public void setVideoQuality(VideoQuality videoQuality) {
        this.mCameraController.setVideoQuality(videoQuality);
    }

    public VideoQuality getVideoQuality() {
        return this.mCameraController.getVideoQuality();
    }

    public void setJpegQuality(int i) {
        if (i <= 0 || i > 100) {
            throw new IllegalArgumentException("JPEG quality should be > 0 and <= 100");
        }
        this.mJpegQuality = i;
    }

    public int getJpegQuality() {
        return this.mJpegQuality;
    }

    public void setCropOutput(boolean z) {
        this.mCropOutput = z;
    }

    public boolean getCropOutput() {
        return this.mCropOutput;
    }

    @Deprecated
    public void setCameraListener(CameraListener cameraListener) {
        this.mListeners.clear();
        addCameraListener(cameraListener);
    }

    public void addCameraListener(CameraListener cameraListener) {
        if (cameraListener != null) {
            this.mListeners.add(cameraListener);
        }
    }

    public void clearCameraListeners() {
        this.mListeners.clear();
    }

    public void clearFrameProcessors() {
        this.mFrameProcessors.clear();
    }

    public void captureSnapshot() {
        this.mCameraController.captureSnapshot();
    }

    public void startCapturingVideo(File file) {
        if (file == null) {
            file = new File(getContext().getFilesDir(), "video.mp4");
        }
        this.mCameraController.startVideo(file);
        this.mUiHandler.post(new Runnable() {
            public void run() {
                CameraView.this.mKeepScreenOn = CameraView.this.getKeepScreenOn();
                if (!CameraView.this.mKeepScreenOn) {
                    CameraView.this.setKeepScreenOn(true);
                }
            }
        });
    }

    public void stopCapturingVideo() {
        this.mCameraController.endVideo();
        this.mUiHandler.post(new Runnable() {
            public void run() {
                if (CameraView.this.getKeepScreenOn() != CameraView.this.mKeepScreenOn) {
                    CameraView.this.setKeepScreenOn(CameraView.this.mKeepScreenOn);
                }
            }
        });
    }

    public Size getPreviewSize() {
        if (this.mCameraController != null) {
            return this.mCameraController.getPreviewSize();
        }
        return null;
    }

    public Size getPictureSize() {
        if (this.mCameraController != null) {
            return this.mCameraController.getPictureSize();
        }
        return null;
    }

    public Size getSnapshotSize() {
        return getPreviewSize();
    }

    @TargetApi(23)
    private void requestPermissions(boolean z, boolean z2) {
        Activity activity = null;
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                activity = (Activity) context;
            }
        }
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add("android.permission.CAMERA");
        }
        if (z2) {
            arrayList.add("android.permission.RECORD_AUDIO");
        }
        if (activity != null) {
            activity.requestPermissions((String[]) arrayList.toArray(new String[arrayList.size()]), 16);
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void playSound(int i) {
        if (this.mPlaySounds) {
            if (this.mSound == null) {
                this.mSound = new MediaActionSound();
            }
            this.mSound.play(i);
        }
    }

    public void setPlaySounds(boolean z) {
        this.mPlaySounds = z && VERSION.SDK_INT >= 16;
    }

    public boolean getPlaySounds() {
        return this.mPlaySounds;
    }

    @Deprecated
    public Size getCaptureSize() {
        return getPictureSize();
    }
}
