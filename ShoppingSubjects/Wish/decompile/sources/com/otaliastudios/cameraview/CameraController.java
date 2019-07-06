package com.otaliastudios.cameraview;

import android.graphics.PointF;
import android.location.Location;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Looper;
import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;

abstract class CameraController implements SurfaceCallback, BufferCallback, UncaughtExceptionHandler {
    /* access modifiers changed from: private */
    public static final CameraLogger LOG = CameraLogger.create(TAG);
    private static final String TAG = "CameraController";
    protected Audio mAudio;
    protected final CameraCallbacks mCameraCallbacks;
    protected int mCameraId;
    protected CameraOptions mCameraOptions;
    Handler mCrashHandler;
    private int mDeviceOrientation;
    private int mDisplayOffset;
    Task<Void> mExposureCorrectionTask = new Task<>();
    protected float mExposureCorrectionValue;
    protected ExtraProperties mExtraProperties;
    protected Facing mFacing;
    protected Flash mFlash;
    Task<Void> mFlashTask = new Task<>();
    protected FrameManager mFrameManager;
    protected WorkerHandler mHandler;
    protected Hdr mHdr;
    Task<Void> mHdrTask = new Task<>();
    protected boolean mIsCapturingImage = false;
    protected boolean mIsCapturingVideo = false;
    protected Location mLocation;
    Task<Void> mLocationTask = new Task<>();
    protected Mapper mMapper;
    protected MediaRecorder mMediaRecorder;
    protected Size mPictureSize;
    protected SizeSelector mPictureSizeSelector;
    protected CameraPreview mPreview;
    protected int mPreviewFormat;
    protected Size mPreviewSize;
    protected int mSensorOffset;
    protected SessionType mSessionType;
    Task<Void> mStartVideoTask = new Task<>();
    protected int mState = 0;
    protected File mVideoFile;
    protected VideoQuality mVideoQuality;
    Task<Void> mVideoQualityTask = new Task<>();
    protected WhiteBalance mWhiteBalance;
    Task<Void> mWhiteBalanceTask = new Task<>();
    Task<Void> mZoomTask = new Task<>();
    protected float mZoomValue;

    private static class NoOpExceptionHandler implements UncaughtExceptionHandler {
        public void uncaughtException(Thread thread, Throwable th) {
        }

        private NoOpExceptionHandler() {
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract void capturePicture();

    /* access modifiers changed from: 0000 */
    public abstract void captureSnapshot();

    /* access modifiers changed from: 0000 */
    public abstract void endVideo();

    /* access modifiers changed from: 0000 */
    public abstract void onStart();

    /* access modifiers changed from: 0000 */
    public abstract void onStop();

    /* access modifiers changed from: 0000 */
    public abstract void setAudio(Audio audio);

    /* access modifiers changed from: 0000 */
    public abstract void setExposureCorrection(float f, float[] fArr, PointF[] pointFArr, boolean z);

    /* access modifiers changed from: 0000 */
    public abstract void setFacing(Facing facing);

    /* access modifiers changed from: 0000 */
    public abstract void setFlash(Flash flash);

    /* access modifiers changed from: 0000 */
    public abstract void setHdr(Hdr hdr);

    /* access modifiers changed from: 0000 */
    public abstract void setLocation(Location location);

    /* access modifiers changed from: 0000 */
    public abstract void setSessionType(SessionType sessionType);

    /* access modifiers changed from: 0000 */
    public abstract void setVideoQuality(VideoQuality videoQuality);

    /* access modifiers changed from: 0000 */
    public abstract void setWhiteBalance(WhiteBalance whiteBalance);

    /* access modifiers changed from: 0000 */
    public abstract void setZoom(float f, PointF[] pointFArr, boolean z);

    /* access modifiers changed from: 0000 */
    public abstract void startAutoFocus(Gesture gesture, PointF pointF);

    /* access modifiers changed from: 0000 */
    public abstract void startVideo(File file);

    CameraController(CameraCallbacks cameraCallbacks) {
        this.mCameraCallbacks = cameraCallbacks;
        this.mCrashHandler = new Handler(Looper.getMainLooper());
        this.mHandler = WorkerHandler.get("CameraViewController");
        this.mHandler.getThread().setUncaughtExceptionHandler(this);
        this.mFrameManager = new FrameManager(2, this);
    }

    /* access modifiers changed from: 0000 */
    public void setPreview(CameraPreview cameraPreview) {
        this.mPreview = cameraPreview;
        this.mPreview.setSurfaceCallback(this);
    }

    public void uncaughtException(Thread thread, final Throwable th) {
        if (!(th instanceof CameraException)) {
            LOG.e("uncaughtException:", "Unexpected exception:", th);
            destroy();
            this.mCrashHandler.post(new Runnable() {
                public void run() {
                    RuntimeException runtimeException;
                    if (th instanceof RuntimeException) {
                        runtimeException = (RuntimeException) th;
                    } else {
                        runtimeException = new RuntimeException(th);
                    }
                    throw runtimeException;
                }
            });
            return;
        }
        final CameraException cameraException = (CameraException) th;
        LOG.e("uncaughtException:", "Interrupting thread with state:", ss(), "due to CameraException:", cameraException);
        thread.interrupt();
        this.mHandler = WorkerHandler.get("CameraViewController");
        this.mHandler.getThread().setUncaughtExceptionHandler(this);
        LOG.i("uncaughtException:", "Calling stopImmediately and notifying.");
        this.mHandler.post(new Runnable() {
            public void run() {
                CameraController.this.stopImmediately();
                CameraController.this.mCameraCallbacks.dispatchError(cameraException);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public final void destroy() {
        LOG.i("destroy:", "state:", ss());
        this.mHandler.getThread().setUncaughtExceptionHandler(new NoOpExceptionHandler());
        stopImmediately();
    }

    /* access modifiers changed from: private */
    public String ss() {
        switch (this.mState) {
            case -1:
                return "STATE_STOPPING";
            case 0:
                return "STATE_STOPPED";
            case 1:
                return "STATE_STARTING";
            case 2:
                return "STATE_STARTED";
            default:
                return "null";
        }
    }

    /* access modifiers changed from: 0000 */
    public final void start() {
        LOG.i("Start:", "posting runnable. State:", ss());
        this.mHandler.post(new Runnable() {
            public void run() {
                CameraController.LOG.i("Start:", "executing. State:", CameraController.this.ss());
                if (CameraController.this.mState < 1) {
                    CameraController.this.mState = 1;
                    CameraController.LOG.i("Start:", "about to call onStart()", CameraController.this.ss());
                    CameraController.this.onStart();
                    CameraController.LOG.i("Start:", "returned from onStart().", "Dispatching.", CameraController.this.ss());
                    CameraController.this.mState = 2;
                    CameraController.this.mCameraCallbacks.dispatchOnCameraOpened(CameraController.this.mCameraOptions);
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public final void stop() {
        LOG.i("Stop:", "posting runnable. State:", ss());
        this.mHandler.post(new Runnable() {
            public void run() {
                CameraController.LOG.i("Stop:", "executing. State:", CameraController.this.ss());
                if (CameraController.this.mState > 0) {
                    CameraController.this.mState = -1;
                    CameraController.LOG.i("Stop:", "about to call onStop()");
                    CameraController.this.onStop();
                    CameraController.LOG.i("Stop:", "returned from onStop().", "Dispatching.");
                    CameraController.this.mState = 0;
                    CameraController.this.mCameraCallbacks.dispatchOnCameraClosed();
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public final void stopImmediately() {
        try {
            LOG.i("stopImmediately:", "State was:", ss());
            if (this.mState != 0) {
                this.mState = -1;
                onStop();
                this.mState = 0;
                LOG.i("stopImmediately:", "Stopped. State is:", ss());
            }
        } catch (Exception e) {
            LOG.i("stopImmediately:", "Swallowing exception while stopping.", e);
            this.mState = 0;
        }
    }

    /* access modifiers changed from: protected */
    public final void restart() {
        LOG.i("Restart:", "posting runnable");
        this.mHandler.post(new Runnable() {
            public void run() {
                CameraLogger access$200 = CameraController.LOG;
                Object[] objArr = new Object[4];
                objArr[0] = "Restart:";
                objArr[1] = "executing. Needs stopping:";
                objArr[2] = Boolean.valueOf(CameraController.this.mState > 0);
                objArr[3] = CameraController.this.ss();
                access$200.i(objArr);
                if (CameraController.this.mState > 0) {
                    CameraController.this.mState = -1;
                    CameraController.this.onStop();
                    CameraController.this.mState = 0;
                    CameraController.LOG.i("Restart:", "stopped. Dispatching.", CameraController.this.ss());
                    CameraController.this.mCameraCallbacks.dispatchOnCameraClosed();
                }
                CameraController.LOG.i("Restart: about to start. State:", CameraController.this.ss());
                CameraController.this.mState = 1;
                CameraController.this.onStart();
                CameraController.this.mState = 2;
                CameraController.LOG.i("Restart: returned from start. Dispatching. State:", CameraController.this.ss());
                CameraController.this.mCameraCallbacks.dispatchOnCameraOpened(CameraController.this.mCameraOptions);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public final int getState() {
        return this.mState;
    }

    /* access modifiers changed from: 0000 */
    public final void setDisplayOffset(int i) {
        this.mDisplayOffset = i;
    }

    /* access modifiers changed from: 0000 */
    public final void setDeviceOrientation(int i) {
        this.mDeviceOrientation = i;
    }

    /* access modifiers changed from: 0000 */
    public final void setPictureSizeSelector(SizeSelector sizeSelector) {
        this.mPictureSizeSelector = sizeSelector;
    }

    /* access modifiers changed from: 0000 */
    public final ExtraProperties getExtraProperties() {
        return this.mExtraProperties;
    }

    /* access modifiers changed from: 0000 */
    public final CameraOptions getCameraOptions() {
        return this.mCameraOptions;
    }

    /* access modifiers changed from: 0000 */
    public final Facing getFacing() {
        return this.mFacing;
    }

    /* access modifiers changed from: 0000 */
    public final Flash getFlash() {
        return this.mFlash;
    }

    /* access modifiers changed from: 0000 */
    public final WhiteBalance getWhiteBalance() {
        return this.mWhiteBalance;
    }

    /* access modifiers changed from: 0000 */
    public final VideoQuality getVideoQuality() {
        return this.mVideoQuality;
    }

    /* access modifiers changed from: 0000 */
    public final SessionType getSessionType() {
        return this.mSessionType;
    }

    /* access modifiers changed from: 0000 */
    public final Hdr getHdr() {
        return this.mHdr;
    }

    /* access modifiers changed from: 0000 */
    public final Location getLocation() {
        return this.mLocation;
    }

    /* access modifiers changed from: 0000 */
    public final Audio getAudio() {
        return this.mAudio;
    }

    /* access modifiers changed from: 0000 */
    public final Size getPictureSize() {
        return this.mPictureSize;
    }

    /* access modifiers changed from: 0000 */
    public final float getZoomValue() {
        return this.mZoomValue;
    }

    /* access modifiers changed from: 0000 */
    public final float getExposureCorrectionValue() {
        return this.mExposureCorrectionValue;
    }

    /* access modifiers changed from: 0000 */
    public final Size getPreviewSize() {
        return this.mPreviewSize;
    }

    /* access modifiers changed from: 0000 */
    public final boolean shouldFlipSizes() {
        int computeSensorToViewOffset = computeSensorToViewOffset();
        LOG.i("shouldFlipSizes:", "displayOffset=", Integer.valueOf(this.mDisplayOffset), "sensorOffset=", Integer.valueOf(this.mSensorOffset));
        LOG.i("shouldFlipSizes:", "sensorToDisplay=", Integer.valueOf(computeSensorToViewOffset));
        if (computeSensorToViewOffset % 180 != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final int computeSensorToViewOffset() {
        if (this.mFacing == Facing.FRONT) {
            return (360 - ((this.mSensorOffset + this.mDisplayOffset) % 360)) % 360;
        }
        return ((this.mSensorOffset - this.mDisplayOffset) + 360) % 360;
    }

    /* access modifiers changed from: protected */
    public final int computeSensorToOutputOffset() {
        if (this.mFacing == Facing.FRONT) {
            return ((this.mSensorOffset - this.mDeviceOrientation) + 360) % 360;
        }
        return (this.mSensorOffset + this.mDeviceOrientation) % 360;
    }

    /* access modifiers changed from: protected */
    public final Size computePictureSize() {
        SizeSelector sizeSelector;
        boolean shouldFlipSizes = shouldFlipSizes();
        if (this.mSessionType == SessionType.PICTURE) {
            sizeSelector = SizeSelectors.or(this.mPictureSizeSelector, SizeSelectors.biggest());
        } else {
            CamcorderProfile camcorderProfile = getCamcorderProfile();
            AspectRatio of = AspectRatio.of(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
            if (shouldFlipSizes) {
                of = of.inverse();
            }
            LOG.i("size:", "computeCaptureSize:", "videoQuality:", this.mVideoQuality, "targetRatio:", of);
            SizeSelector aspectRatio = SizeSelectors.aspectRatio(of, 0.0f);
            sizeSelector = SizeSelectors.or(SizeSelectors.and(aspectRatio, this.mPictureSizeSelector), SizeSelectors.and(aspectRatio), this.mPictureSizeSelector);
        }
        Size size = (Size) sizeSelector.select(new ArrayList(this.mCameraOptions.getSupportedPictureSizes())).get(0);
        LOG.i("computePictureSize:", "result:", size, "flip:", Boolean.valueOf(shouldFlipSizes));
        return shouldFlipSizes ? size.flip() : size;
    }

    /* access modifiers changed from: protected */
    public final Size computePreviewSize(List<Size> list) {
        boolean shouldFlipSizes = shouldFlipSizes();
        AspectRatio of = AspectRatio.of(this.mPictureSize.getWidth(), this.mPictureSize.getHeight());
        Size surfaceSize = this.mPreview.getSurfaceSize();
        if (shouldFlipSizes) {
            surfaceSize = surfaceSize.flip();
        }
        LOG.i("size:", "computePreviewSize:", "targetRatio:", of, "targetMinSize:", surfaceSize);
        SizeSelector aspectRatio = SizeSelectors.aspectRatio(of, 0.0f);
        Size size = (Size) SizeSelectors.or(SizeSelectors.and(aspectRatio, SizeSelectors.and(SizeSelectors.minHeight(surfaceSize.getHeight()), SizeSelectors.minWidth(surfaceSize.getWidth()))), aspectRatio, SizeSelectors.biggest()).select(list).get(0);
        LOG.i("computePreviewSize:", "result:", size, "flip:", Boolean.valueOf(shouldFlipSizes));
        return size;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0032, code lost:
        return android.media.CamcorderProfile.get(r2.mCameraId, 6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        if (android.media.CamcorderProfile.hasProfile(r2.mCameraId, 5) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        return android.media.CamcorderProfile.get(r2.mCameraId, 5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004a, code lost:
        if (android.media.CamcorderProfile.hasProfile(r2.mCameraId, 4) == false) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        return android.media.CamcorderProfile.get(r2.mCameraId, 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005a, code lost:
        if (android.media.CamcorderProfile.hasProfile(r2.mCameraId, 7) == false) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0062, code lost:
        return android.media.CamcorderProfile.get(r2.mCameraId, 7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0072, code lost:
        return android.media.CamcorderProfile.get(r2.mCameraId, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002a, code lost:
        if (android.media.CamcorderProfile.hasProfile(r2.mCameraId, 6) == false) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.media.CamcorderProfile getCamcorderProfile() {
        /*
            r2 = this;
            int[] r0 = com.otaliastudios.cameraview.CameraController.AnonymousClass6.$SwitchMap$com$otaliastudios$cameraview$VideoQuality
            com.otaliastudios.cameraview.VideoQuality r1 = r2.mVideoQuality
            int r1 = r1.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L_0x0063;
                case 2: goto L_0x000e;
                case 3: goto L_0x0023;
                case 4: goto L_0x0033;
                case 5: goto L_0x0043;
                case 6: goto L_0x0053;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x006b
        L_0x000e:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            if (r0 < r1) goto L_0x0023
            r0 = 8
            boolean r1 = android.media.CamcorderProfile.hasProfile(r0)
            if (r1 == 0) goto L_0x0023
            int r1 = r2.mCameraId
            android.media.CamcorderProfile r0 = android.media.CamcorderProfile.get(r1, r0)
            return r0
        L_0x0023:
            int r0 = r2.mCameraId
            r1 = 6
            boolean r0 = android.media.CamcorderProfile.hasProfile(r0, r1)
            if (r0 == 0) goto L_0x0033
            int r0 = r2.mCameraId
            android.media.CamcorderProfile r0 = android.media.CamcorderProfile.get(r0, r1)
            return r0
        L_0x0033:
            int r0 = r2.mCameraId
            r1 = 5
            boolean r0 = android.media.CamcorderProfile.hasProfile(r0, r1)
            if (r0 == 0) goto L_0x0043
            int r0 = r2.mCameraId
            android.media.CamcorderProfile r0 = android.media.CamcorderProfile.get(r0, r1)
            return r0
        L_0x0043:
            int r0 = r2.mCameraId
            r1 = 4
            boolean r0 = android.media.CamcorderProfile.hasProfile(r0, r1)
            if (r0 == 0) goto L_0x0053
            int r0 = r2.mCameraId
            android.media.CamcorderProfile r0 = android.media.CamcorderProfile.get(r0, r1)
            return r0
        L_0x0053:
            int r0 = r2.mCameraId
            r1 = 7
            boolean r0 = android.media.CamcorderProfile.hasProfile(r0, r1)
            if (r0 == 0) goto L_0x006b
            int r0 = r2.mCameraId
            android.media.CamcorderProfile r0 = android.media.CamcorderProfile.get(r0, r1)
            return r0
        L_0x0063:
            int r0 = r2.mCameraId
            r1 = 1
            android.media.CamcorderProfile r0 = android.media.CamcorderProfile.get(r0, r1)
            return r0
        L_0x006b:
            int r0 = r2.mCameraId
            r1 = 0
            android.media.CamcorderProfile r0 = android.media.CamcorderProfile.get(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.otaliastudios.cameraview.CameraController.getCamcorderProfile():android.media.CamcorderProfile");
    }
}
