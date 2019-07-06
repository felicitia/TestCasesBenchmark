package com.otaliastudios.cameraview;

import android.graphics.ImageFormat;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.location.Location;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.view.SurfaceHolder;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Camera1 extends CameraController implements ErrorCallback, PreviewCallback {
    /* access modifiers changed from: private */
    public static final CameraLogger LOG = CameraLogger.create(TAG);
    private static final String TAG = "Camera1";
    /* access modifiers changed from: private */
    public Camera mCamera;
    /* access modifiers changed from: private */
    public boolean mIsBound;
    private final int mPostFocusResetDelay;
    /* access modifiers changed from: private */
    public Runnable mPostFocusResetRunnable;

    Camera1(CameraCallbacks cameraCallbacks) {
        super(cameraCallbacks);
        this.mIsBound = false;
        this.mPostFocusResetDelay = 3000;
        this.mPostFocusResetRunnable = new Runnable() {
            public void run() {
                if (Camera1.this.isCameraAvailable()) {
                    Camera1.this.mCamera.cancelAutoFocus();
                    Parameters parameters = Camera1.this.mCamera.getParameters();
                    parameters.setFocusAreas(null);
                    parameters.setMeteringAreas(null);
                    Camera1.this.applyDefaultFocus(parameters);
                    Camera1.this.mCamera.setParameters(parameters);
                }
            }
        };
        this.mMapper = new Mapper1();
    }

    private void schedule(final Task<Void> task, final boolean z, final Runnable runnable) {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (!z || Camera1.this.isCameraAvailable()) {
                    runnable.run();
                    if (task != null) {
                        task.end(null);
                    }
                } else if (task != null) {
                    task.end(null);
                }
            }
        });
    }

    public void onSurfaceAvailable() {
        LOG.i("onSurfaceAvailable:", "Size is", this.mPreview.getSurfaceSize());
        schedule(null, false, new Runnable() {
            public void run() {
                if (Camera1.this.shouldBindToSurface()) {
                    Camera1.LOG.i("onSurfaceAvailable:", "Inside handler. About to bind.");
                    try {
                        Camera1.this.bindToSurface();
                    } catch (Exception e) {
                        Camera1.LOG.e("onSurfaceAvailable:", "Exception while binding camera to preview.", e);
                        throw new CameraException(e);
                    }
                }
            }
        });
    }

    public void onSurfaceChanged() {
        LOG.i("onSurfaceChanged, size is", this.mPreview.getSurfaceSize());
        schedule(null, true, new Runnable() {
            public void run() {
                if (Camera1.this.mIsBound) {
                    Size computePreviewSize = Camera1.this.computePreviewSize(Camera1.this.sizesFromList(Camera1.this.mCamera.getParameters().getSupportedPreviewSizes()));
                    if (!computePreviewSize.equals(Camera1.this.mPreviewSize)) {
                        Camera1.LOG.i("onSurfaceChanged:", "Computed a new preview size. Going on.");
                        Camera1.this.mPreviewSize = computePreviewSize;
                        Camera1.this.mCamera.stopPreview();
                        Camera1.this.applySizesAndStartPreview("onSurfaceChanged:");
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean shouldBindToSurface() {
        return isCameraAvailable() && this.mPreview != null && this.mPreview.isReady() && !this.mIsBound;
    }

    /* access modifiers changed from: private */
    public void bindToSurface() {
        LOG.i("bindToSurface:", "Started");
        Object output = this.mPreview.getOutput();
        try {
            if (this.mPreview.getOutputClass() == SurfaceHolder.class) {
                this.mCamera.setPreviewDisplay((SurfaceHolder) output);
            } else {
                this.mCamera.setPreviewTexture((SurfaceTexture) output);
            }
            this.mPictureSize = computePictureSize();
            this.mPreviewSize = computePreviewSize(sizesFromList(this.mCamera.getParameters().getSupportedPreviewSizes()));
            applySizesAndStartPreview("bindToSurface:");
            this.mIsBound = true;
        } catch (IOException e) {
            throw new CameraException(e);
        }
    }

    /* access modifiers changed from: private */
    public void applySizesAndStartPreview(String str) {
        LOG.i(str, "Dispatching onCameraPreviewSizeChanged.");
        this.mCameraCallbacks.onCameraPreviewSizeChanged();
        boolean shouldFlipSizes = shouldFlipSizes();
        this.mPreview.setDesiredSize(shouldFlipSizes ? this.mPreviewSize.getHeight() : this.mPreviewSize.getWidth(), shouldFlipSizes ? this.mPreviewSize.getWidth() : this.mPreviewSize.getHeight());
        Parameters parameters = this.mCamera.getParameters();
        this.mPreviewFormat = parameters.getPreviewFormat();
        parameters.setPreviewSize(this.mPreviewSize.getWidth(), this.mPreviewSize.getHeight());
        parameters.setPictureSize(this.mPictureSize.getWidth(), this.mPictureSize.getHeight());
        this.mCamera.setParameters(parameters);
        this.mCamera.setPreviewCallbackWithBuffer(null);
        this.mCamera.setPreviewCallbackWithBuffer(this);
        this.mFrameManager.allocate(ImageFormat.getBitsPerPixel(this.mPreviewFormat), this.mPreviewSize);
        LOG.i(str, "Starting preview with startPreview().");
        this.mCamera.startPreview();
        LOG.i(str, "Started preview.");
    }

    /* access modifiers changed from: 0000 */
    public void onStart() {
        if (isCameraAvailable()) {
            LOG.w("onStart:", "Camera not available. Should not happen.");
            onStop();
        }
        if (collectCameraId()) {
            this.mCamera = Camera.open(this.mCameraId);
            this.mCamera.setErrorCallback(this);
            LOG.i("onStart:", "Applying default parameters.");
            Parameters parameters = this.mCamera.getParameters();
            this.mExtraProperties = new ExtraProperties(parameters);
            this.mCameraOptions = new CameraOptions(parameters, shouldFlipSizes());
            applyDefaultFocus(parameters);
            mergeFlash(parameters, Flash.DEFAULT);
            mergeLocation(parameters, null);
            mergeWhiteBalance(parameters, WhiteBalance.DEFAULT);
            mergeHdr(parameters, Hdr.DEFAULT);
            parameters.setRecordingHint(this.mSessionType == SessionType.VIDEO);
            this.mCamera.setParameters(parameters);
            this.mCamera.setDisplayOrientation(computeSensorToViewOffset());
            if (shouldBindToSurface()) {
                bindToSurface();
            }
            LOG.i("onStart:", "Ended");
        }
    }

    /* access modifiers changed from: 0000 */
    public void onStop() {
        LOG.i("onStop:", "About to clean up.");
        this.mHandler.get().removeCallbacks(this.mPostFocusResetRunnable);
        this.mFrameManager.release();
        if (this.mCamera != null) {
            LOG.i("onStop:", "Clean up.", "Ending video.");
            endVideoImmediately();
            try {
                LOG.i("onStop:", "Clean up.", "Stopping preview.");
                this.mCamera.setPreviewCallbackWithBuffer(null);
                this.mCamera.stopPreview();
                LOG.i("onStop:", "Clean up.", "Stopped preview.");
                e = null;
            } catch (Exception e) {
                e = e;
                LOG.w("onStop:", "Clean up.", "Exception while stopping preview.", e);
            }
            try {
                LOG.i("onStop:", "Clean up.", "Releasing camera.");
                this.mCamera.release();
                LOG.i("onStop:", "Clean up.", "Released camera.");
            } catch (Exception e2) {
                e = e2;
                LOG.w("onStop:", "Clean up.", "Exception while releasing camera.", e);
            }
        } else {
            e = null;
        }
        this.mExtraProperties = null;
        this.mCameraOptions = null;
        this.mCamera = null;
        this.mPreviewSize = null;
        this.mPictureSize = null;
        this.mIsBound = false;
        LOG.w("onStop:", "Clean up.", "Returning.");
        if (e != null) {
            throw new CameraException(e);
        }
    }

    /* access modifiers changed from: private */
    public boolean collectCameraId() {
        int intValue = ((Integer) this.mMapper.map(this.mFacing)).intValue();
        CameraInfo cameraInfo = new CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == intValue) {
                this.mSensorOffset = cameraInfo.orientation;
                this.mCameraId = i;
                return true;
            }
        }
        return false;
    }

    public void onBufferAvailable(byte[] bArr) {
        if (isCameraAvailable()) {
            this.mCamera.addCallbackBuffer(bArr);
        }
    }

    public void onError(int i, Camera camera) {
        if (i == 100) {
            LOG.w("Recoverable error inside the onError callback.", "CAMERA_ERROR_SERVER_DIED");
            stopImmediately();
            start();
            return;
        }
        LOG.e("Error inside the onError callback.", Integer.valueOf(i));
        throw new CameraException(new RuntimeException(CameraLogger.lastMessage));
    }

    /* access modifiers changed from: 0000 */
    public void setSessionType(SessionType sessionType) {
        if (sessionType != this.mSessionType) {
            this.mSessionType = sessionType;
            schedule(null, true, new Runnable() {
                public void run() {
                    Camera1.this.restart();
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public void setLocation(Location location) {
        final Location location2 = this.mLocation;
        this.mLocation = location;
        schedule(this.mLocationTask, true, new Runnable() {
            public void run() {
                Parameters parameters = Camera1.this.mCamera.getParameters();
                if (Camera1.this.mergeLocation(parameters, location2)) {
                    Camera1.this.mCamera.setParameters(parameters);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean mergeLocation(Parameters parameters, Location location) {
        if (this.mLocation != null) {
            parameters.setGpsLatitude(this.mLocation.getLatitude());
            parameters.setGpsLongitude(this.mLocation.getLongitude());
            parameters.setGpsAltitude(this.mLocation.getAltitude());
            parameters.setGpsTimestamp(this.mLocation.getTime());
            parameters.setGpsProcessingMethod(this.mLocation.getProvider());
            if (this.mIsCapturingVideo && this.mMediaRecorder != null) {
                this.mMediaRecorder.setLocation((float) this.mLocation.getLatitude(), (float) this.mLocation.getLongitude());
            }
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void setFacing(Facing facing) {
        if (facing != this.mFacing) {
            this.mFacing = facing;
            schedule(null, true, new Runnable() {
                public void run() {
                    if (Camera1.this.collectCameraId()) {
                        Camera1.this.restart();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public void setWhiteBalance(WhiteBalance whiteBalance) {
        final WhiteBalance whiteBalance2 = this.mWhiteBalance;
        this.mWhiteBalance = whiteBalance;
        schedule(this.mWhiteBalanceTask, true, new Runnable() {
            public void run() {
                Parameters parameters = Camera1.this.mCamera.getParameters();
                if (Camera1.this.mergeWhiteBalance(parameters, whiteBalance2)) {
                    Camera1.this.mCamera.setParameters(parameters);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean mergeWhiteBalance(Parameters parameters, WhiteBalance whiteBalance) {
        if (this.mCameraOptions.supports(this.mWhiteBalance)) {
            parameters.setWhiteBalance((String) this.mMapper.map(this.mWhiteBalance));
            return true;
        }
        this.mWhiteBalance = whiteBalance;
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void setHdr(Hdr hdr) {
        final Hdr hdr2 = this.mHdr;
        this.mHdr = hdr;
        schedule(this.mHdrTask, true, new Runnable() {
            public void run() {
                Parameters parameters = Camera1.this.mCamera.getParameters();
                if (Camera1.this.mergeHdr(parameters, hdr2)) {
                    Camera1.this.mCamera.setParameters(parameters);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean mergeHdr(Parameters parameters, Hdr hdr) {
        if (this.mCameraOptions.supports(this.mHdr)) {
            parameters.setSceneMode((String) this.mMapper.map(this.mHdr));
            return true;
        }
        this.mHdr = hdr;
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void setAudio(Audio audio) {
        if (this.mAudio != audio) {
            if (this.mIsCapturingVideo) {
                LOG.w("Audio setting was changed while recording. Changes will take place starting from next video");
            }
            this.mAudio = audio;
        }
    }

    /* access modifiers changed from: 0000 */
    public void setFlash(Flash flash) {
        final Flash flash2 = this.mFlash;
        this.mFlash = flash;
        schedule(this.mFlashTask, true, new Runnable() {
            public void run() {
                Parameters parameters = Camera1.this.mCamera.getParameters();
                if (Camera1.this.mergeFlash(parameters, flash2)) {
                    Camera1.this.mCamera.setParameters(parameters);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean mergeFlash(Parameters parameters, Flash flash) {
        if (this.mCameraOptions.supports(this.mFlash)) {
            parameters.setFlashMode((String) this.mMapper.map(this.mFlash));
            return true;
        }
        this.mFlash = flash;
        return false;
    }

    /* access modifiers changed from: private */
    public void applyDefaultFocus(Parameters parameters) {
        List supportedFocusModes = parameters.getSupportedFocusModes();
        if (this.mSessionType == SessionType.VIDEO && supportedFocusModes.contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        } else if (supportedFocusModes.contains("continuous-picture")) {
            parameters.setFocusMode("continuous-picture");
        } else if (supportedFocusModes.contains("infinity")) {
            parameters.setFocusMode("infinity");
        } else if (supportedFocusModes.contains("fixed")) {
            parameters.setFocusMode("fixed");
        }
    }

    /* access modifiers changed from: 0000 */
    public void setVideoQuality(VideoQuality videoQuality) {
        final VideoQuality videoQuality2 = this.mVideoQuality;
        this.mVideoQuality = videoQuality;
        schedule(this.mVideoQualityTask, true, new Runnable() {
            public void run() {
                if (Camera1.this.mIsCapturingVideo) {
                    Camera1.this.mVideoQuality = videoQuality2;
                    throw new IllegalStateException("Can't change video quality while recording a video.");
                } else if (Camera1.this.mSessionType == SessionType.VIDEO) {
                    Size size = Camera1.this.mPictureSize;
                    Camera1.this.mPictureSize = Camera1.this.computePictureSize();
                    if (!Camera1.this.mPictureSize.equals(size)) {
                        Parameters parameters = Camera1.this.mCamera.getParameters();
                        parameters.setPictureSize(Camera1.this.mPictureSize.getWidth(), Camera1.this.mPictureSize.getHeight());
                        Camera1.this.mCamera.setParameters(parameters);
                        Camera1.this.onSurfaceChanged();
                    }
                    Camera1.LOG.i("setVideoQuality:", "captureSize:", Camera1.this.mPictureSize);
                    Camera1.LOG.i("setVideoQuality:", "previewSize:", Camera1.this.mPreviewSize);
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void capturePicture() {
        LOG.v("capturePicture: scheduling");
        schedule(null, true, new Runnable() {
            public void run() {
                final boolean z = false;
                Camera1.LOG.v("capturePicture: performing.", Boolean.valueOf(Camera1.this.mIsCapturingImage));
                if (!Camera1.this.mIsCapturingImage) {
                    if (!Camera1.this.mIsCapturingVideo || Camera1.this.mCameraOptions.isVideoSnapshotSupported()) {
                        Camera1.this.mIsCapturingImage = true;
                        int computeSensorToOutputOffset = Camera1.this.computeSensorToOutputOffset();
                        final boolean z2 = ((Camera1.this.computeSensorToViewOffset() + computeSensorToOutputOffset) + 180) % 180 == 0;
                        if (Camera1.this.mFacing == Facing.FRONT) {
                            z = true;
                        }
                        Parameters parameters = Camera1.this.mCamera.getParameters();
                        parameters.setRotation(computeSensorToOutputOffset);
                        Camera1.this.mCamera.setParameters(parameters);
                        Camera1.this.mCamera.takePicture(new ShutterCallback() {
                            public void onShutter() {
                                Camera1.this.mCameraCallbacks.onShutter(false);
                            }
                        }, null, null, new PictureCallback() {
                            public void onPictureTaken(byte[] bArr, Camera camera) {
                                Camera1.this.mIsCapturingImage = false;
                                Camera1.this.mCameraCallbacks.processImage(bArr, z2, z);
                                camera.startPreview();
                            }
                        });
                    }
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void captureSnapshot() {
        LOG.v("captureSnapshot: scheduling");
        schedule(null, true, new Runnable() {
            public void run() {
                Camera1.LOG.v("captureSnapshot: performing.", Boolean.valueOf(Camera1.this.mIsCapturingImage));
                if (!Camera1.this.mIsCapturingImage) {
                    if (Camera1.this.mIsCapturingVideo) {
                        Camera1.this.capturePicture();
                        return;
                    }
                    Camera1.this.mIsCapturingImage = true;
                    Camera1.this.mCamera.setOneShotPreviewCallback(new PreviewCallback() {
                        public void onPreviewFrame(byte[] bArr, Camera camera) {
                            boolean z = true;
                            Camera1.this.mCameraCallbacks.onShutter(true);
                            final int computeSensorToOutputOffset = Camera1.this.computeSensorToOutputOffset();
                            final boolean z2 = ((Camera1.this.computeSensorToViewOffset() + computeSensorToOutputOffset) + 180) % 180 == 0;
                            final boolean z3 = Camera1.this.mFacing == Facing.FRONT;
                            if (computeSensorToOutputOffset % 180 == 0) {
                                z = false;
                            }
                            final int width = Camera1.this.mPreviewSize.getWidth();
                            final int height = Camera1.this.mPreviewSize.getHeight();
                            final int i = z ? height : width;
                            final int i2 = z ? width : height;
                            final int i3 = Camera1.this.mPreviewFormat;
                            final byte[] bArr2 = bArr;
                            AnonymousClass1 r1 = new Runnable() {
                                public void run() {
                                    Camera1.LOG.v("captureSnapshot: rotating.");
                                    byte[] rotate = RotationHelper.rotate(bArr2, width, height, computeSensorToOutputOffset);
                                    Camera1.LOG.v("captureSnapshot: rotated.");
                                    YuvImage yuvImage = new YuvImage(rotate, i3, i, i2, null);
                                    Camera1.this.mCameraCallbacks.processSnapshot(yuvImage, z2, z3);
                                    Camera1.this.mIsCapturingImage = false;
                                }
                            };
                            WorkerHandler.run(r1);
                            Camera1.this.mCamera.setPreviewCallbackWithBuffer(null);
                            Camera1.this.mCamera.setPreviewCallbackWithBuffer(Camera1.this);
                            Camera1.this.mFrameManager.allocate(ImageFormat.getBitsPerPixel(Camera1.this.mPreviewFormat), Camera1.this.mPreviewSize);
                        }
                    });
                }
            }
        });
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        this.mCameraCallbacks.dispatchFrame(this.mFrameManager.getFrame(bArr, System.currentTimeMillis(), computeSensorToOutputOffset(), this.mPreviewSize, this.mPreviewFormat));
    }

    /* access modifiers changed from: private */
    public boolean isCameraAvailable() {
        boolean z = true;
        switch (this.mState) {
            case -1:
                return false;
            case 0:
                return false;
            case 1:
                if (this.mCamera == null) {
                    z = false;
                }
                return z;
            case 2:
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public void startVideo(final File file) {
        schedule(this.mStartVideoTask, true, new Runnable() {
            public void run() {
                if (!Camera1.this.mIsCapturingVideo) {
                    if (Camera1.this.mSessionType == SessionType.VIDEO) {
                        Camera1.this.mVideoFile = file;
                        Camera1.this.mIsCapturingVideo = true;
                        Camera1.this.initMediaRecorder();
                        try {
                            Camera1.this.mMediaRecorder.prepare();
                            Camera1.this.mMediaRecorder.start();
                        } catch (Exception e) {
                            Camera1.LOG.e("Error while starting MediaRecorder. Swallowing.", e);
                            Camera1.this.mVideoFile = null;
                            Camera1.this.mCamera.lock();
                            Camera1.this.endVideoImmediately();
                        }
                        return;
                    }
                    throw new IllegalStateException("Can't record video while session type is picture");
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void endVideo() {
        schedule(null, false, new Runnable() {
            public void run() {
                Camera1.this.endVideoImmediately();
            }
        });
    }

    /* access modifiers changed from: private */
    public void endVideoImmediately() {
        LOG.i("endVideoImmediately:", "is capturing:", Boolean.valueOf(this.mIsCapturingVideo));
        this.mIsCapturingVideo = false;
        if (this.mMediaRecorder != null) {
            try {
                this.mMediaRecorder.stop();
            } catch (Exception e) {
                LOG.w("endVideoImmediately:", "Error while closing media recorder. Swallowing", e);
            }
            this.mMediaRecorder.release();
            this.mMediaRecorder = null;
        }
        if (this.mVideoFile != null) {
            this.mCameraCallbacks.dispatchOnVideoTaken(this.mVideoFile);
            this.mVideoFile = null;
        }
    }

    /* access modifiers changed from: private */
    public void initMediaRecorder() {
        this.mMediaRecorder = new MediaRecorder();
        this.mCamera.unlock();
        this.mMediaRecorder.setCamera(this.mCamera);
        this.mMediaRecorder.setVideoSource(1);
        if (this.mAudio == Audio.ON) {
            this.mMediaRecorder.setAudioSource(0);
        }
        CamcorderProfile camcorderProfile = getCamcorderProfile();
        this.mMediaRecorder.setOutputFormat(camcorderProfile.fileFormat);
        this.mMediaRecorder.setVideoFrameRate(camcorderProfile.videoFrameRate);
        this.mMediaRecorder.setVideoSize(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
        this.mMediaRecorder.setVideoEncoder(camcorderProfile.videoCodec);
        this.mMediaRecorder.setVideoEncodingBitRate(camcorderProfile.videoBitRate);
        if (this.mAudio == Audio.ON) {
            this.mMediaRecorder.setAudioChannels(camcorderProfile.audioChannels);
            this.mMediaRecorder.setAudioSamplingRate(camcorderProfile.audioSampleRate);
            this.mMediaRecorder.setAudioEncoder(camcorderProfile.audioCodec);
            this.mMediaRecorder.setAudioEncodingBitRate(camcorderProfile.audioBitRate);
        }
        if (this.mLocation != null) {
            this.mMediaRecorder.setLocation((float) this.mLocation.getLatitude(), (float) this.mLocation.getLongitude());
        }
        this.mMediaRecorder.setOutputFile(this.mVideoFile.getAbsolutePath());
        this.mMediaRecorder.setOrientationHint(computeSensorToOutputOffset());
    }

    /* access modifiers changed from: 0000 */
    public void setZoom(final float f, final PointF[] pointFArr, final boolean z) {
        schedule(this.mZoomTask, true, new Runnable() {
            public void run() {
                if (Camera1.this.mCameraOptions.isZoomSupported()) {
                    Camera1.this.mZoomValue = f;
                    Parameters parameters = Camera1.this.mCamera.getParameters();
                    parameters.setZoom((int) (f * ((float) parameters.getMaxZoom())));
                    Camera1.this.mCamera.setParameters(parameters);
                    if (z) {
                        Camera1.this.mCameraCallbacks.dispatchOnZoomChanged(f, pointFArr);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void setExposureCorrection(float f, float[] fArr, PointF[] pointFArr, boolean z) {
        Task task = this.mExposureCorrectionTask;
        final float f2 = f;
        final boolean z2 = z;
        final float[] fArr2 = fArr;
        final PointF[] pointFArr2 = pointFArr;
        AnonymousClass17 r1 = new Runnable() {
            public void run() {
                if (Camera1.this.mCameraOptions.isExposureCorrectionSupported()) {
                    float f = f2;
                    float exposureCorrectionMaxValue = Camera1.this.mCameraOptions.getExposureCorrectionMaxValue();
                    float exposureCorrectionMinValue = Camera1.this.mCameraOptions.getExposureCorrectionMinValue();
                    if (f < exposureCorrectionMinValue) {
                        f = exposureCorrectionMinValue;
                    } else if (f > exposureCorrectionMaxValue) {
                        f = exposureCorrectionMaxValue;
                    }
                    Camera1.this.mExposureCorrectionValue = f;
                    Parameters parameters = Camera1.this.mCamera.getParameters();
                    parameters.setExposureCompensation((int) (f / parameters.getExposureCompensationStep()));
                    Camera1.this.mCamera.setParameters(parameters);
                    if (z2) {
                        Camera1.this.mCameraCallbacks.dispatchOnExposureCorrectionChanged(f, fArr2, pointFArr2);
                    }
                }
            }
        };
        schedule(task, true, r1);
    }

    /* access modifiers changed from: 0000 */
    public void startAutoFocus(Gesture gesture, PointF pointF) {
        final int i;
        final int i2;
        if (this.mPreview == null || !this.mPreview.isReady()) {
            i2 = 0;
            i = 0;
        } else {
            int width = this.mPreview.getView().getWidth();
            i = this.mPreview.getView().getHeight();
            i2 = width;
        }
        final PointF pointF2 = pointF;
        final Gesture gesture2 = gesture;
        AnonymousClass18 r2 = new Runnable() {
            public void run() {
                if (Camera1.this.mCameraOptions.isAutoFocusSupported()) {
                    final PointF pointF = new PointF(pointF2.x, pointF2.y);
                    List access$1600 = Camera1.computeMeteringAreas((double) pointF.x, (double) pointF.y, i2, i, Camera1.this.computeSensorToViewOffset());
                    List subList = access$1600.subList(0, 1);
                    Parameters parameters = Camera1.this.mCamera.getParameters();
                    int maxNumFocusAreas = parameters.getMaxNumFocusAreas();
                    int maxNumMeteringAreas = parameters.getMaxNumMeteringAreas();
                    if (maxNumFocusAreas > 0) {
                        parameters.setFocusAreas(maxNumFocusAreas > 1 ? access$1600 : subList);
                    }
                    if (maxNumMeteringAreas > 0) {
                        if (maxNumMeteringAreas <= 1) {
                            access$1600 = subList;
                        }
                        parameters.setMeteringAreas(access$1600);
                    }
                    parameters.setFocusMode("auto");
                    Camera1.this.mCamera.setParameters(parameters);
                    Camera1.this.mCameraCallbacks.dispatchOnFocusStart(gesture2, pointF);
                    Camera1.this.mCamera.autoFocus(new AutoFocusCallback() {
                        public void onAutoFocus(boolean z, Camera camera) {
                            Camera1.this.mCameraCallbacks.dispatchOnFocusEnd(gesture2, z, pointF);
                            Camera1.this.mHandler.get().removeCallbacks(Camera1.this.mPostFocusResetRunnable);
                            Camera1.this.mHandler.get().postDelayed(Camera1.this.mPostFocusResetRunnable, 3000);
                        }
                    });
                }
            }
        };
        schedule(null, true, r2);
    }

    /* access modifiers changed from: private */
    public static List<Area> computeMeteringAreas(double d, double d2, int i, int i2, int i3) {
        double d3 = ((d / ((double) i)) * 2000.0d) - 0.00408935546875d;
        double d4 = ((d2 / ((double) i2)) * 2000.0d) - 0.00408935546875d;
        double d5 = (((double) (-i3)) * 3.141592653589793d) / 180.0d;
        double cos = (Math.cos(d5) * d3) - (Math.sin(d5) * d4);
        double cos2 = (Math.cos(d5) * d4) + (Math.sin(d5) * d3);
        LOG.i("focus:", "viewClickX:", Double.valueOf(d3), "viewClickY:", Double.valueOf(d4));
        LOG.i("focus:", "sensorClickX:", Double.valueOf(cos), "sensorClickY:", Double.valueOf(cos2));
        double d6 = cos;
        double d7 = cos2;
        Rect computeMeteringArea = computeMeteringArea(d6, d7, 150.0d);
        Rect computeMeteringArea2 = computeMeteringArea(d6, d7, 300.0d);
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new Area(computeMeteringArea, 1000));
        arrayList.add(new Area(computeMeteringArea2, 100));
        return arrayList;
    }

    private static Rect computeMeteringArea(double d, double d2, double d3) {
        double d4 = d3 / 2.0d;
        int max = (int) Math.max(d2 - d4, -1000.0d);
        int min = (int) Math.min(d2 + d4, 1000.0d);
        int max2 = (int) Math.max(d - d4, -1000.0d);
        int min2 = (int) Math.min(d + d4, 1000.0d);
        LOG.i("focus:", "computeMeteringArea:", "top:", Integer.valueOf(max), "left:", Integer.valueOf(max2), "bottom:", Integer.valueOf(min), "right:", Integer.valueOf(min2));
        return new Rect(max2, max, min2, min);
    }

    /* access modifiers changed from: private */
    public List<Size> sizesFromList(List<Size> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (Size size : list) {
            Size size2 = new Size(size.width, size.height);
            if (!arrayList.contains(size2)) {
                arrayList.add(size2);
            }
        }
        LOG.i("size:", "sizesFromList:", arrayList);
        return arrayList;
    }
}
