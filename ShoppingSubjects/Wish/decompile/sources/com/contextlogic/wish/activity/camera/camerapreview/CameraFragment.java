package com.contextlogic.wish.activity.camera.camerapreview;

import android.content.Intent;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarItem;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.activity.camera.MediaInfo;
import com.contextlogic.wish.activity.exampleugc.exampleugcintro.ExampleUgcIntroActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraUtils;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.Facing;
import com.otaliastudios.cameraview.Flash;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class CameraFragment extends UiFragment<CameraActivity> {
    private static int ACTION_ID_FLASH = 0;
    private static int ACTION_ID_FLASH_OFF = 1;
    private static int ACTION_ID_INFO = 2;
    /* access modifiers changed from: private */
    public static String CAMERA_FACING_KEY = "camera_facing";
    private static int FLASH_ANIMATION_DURATION_MS = 500;
    /* access modifiers changed from: private */
    public static int MIN_VIDEO_LENGTH_MS = 7000;
    private static int VIDEO_LOOP_DURATION_MS = 5000;
    private ArrayList<ActionBarItem> mActionBarItems;
    private View mCameraStoppedView;
    private ImageView mCameraSwitchIcon;
    /* access modifiers changed from: private */
    public CameraView mCameraView;
    private boolean mCanShowActionBar;
    private View mCaptureButton;
    private boolean mDeviceHasFlash;
    private ThemedButton mFinishButton;
    /* access modifiers changed from: private */
    public AlphaAnimation mFlashAnimation;
    /* access modifiers changed from: private */
    public FrameLayout mFlashView;
    private ImageView mGalleryIcon;
    private RelativeLayout mImageUploadingView;
    /* access modifiers changed from: private */
    public boolean mIsRecording;
    /* access modifiers changed from: private */
    public MediaInfo mMediaInfo;
    private FrameLayout mPostCaptureControls;
    /* access modifiers changed from: private */
    public LinearLayout mPreCaptureControls;
    private ImageView mRecordingVideoIcon;
    private Animation mVideoProgressAnimation;
    /* access modifiers changed from: private */
    public VideoRecordingProgressBar mVideoProgressBar;
    /* access modifiers changed from: private */
    public long mVideoRecordingStartTime;
    private ImageView mXButton;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.camera_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mDeviceHasFlash = ((CameraActivity) getBaseActivity()).getPackageManager().hasSystemFeature("android.hardware.camera.flash");
        this.mCameraView = (CameraView) findViewById(R.id.camera_fragment_camera_view);
        this.mCameraStoppedView = findViewById(R.id.camera_stopped_view);
        this.mXButton = (ImageView) findViewById(R.id.camera_fragment_x_button);
        this.mPreCaptureControls = (LinearLayout) findViewById(R.id.camera_fragment_pre_capture_controls);
        this.mPostCaptureControls = (FrameLayout) findViewById(R.id.camera_fragment_post_capture_controls);
        this.mGalleryIcon = (ImageView) findViewById(R.id.camera_fragment_gallery_icon);
        this.mCaptureButton = findViewById(R.id.camera_fragment_capture_button);
        this.mCameraSwitchIcon = (ImageView) findViewById(R.id.camera_fragment_camera_switch_icon);
        this.mFinishButton = (ThemedButton) findViewById(R.id.camera_finish_button);
        this.mImageUploadingView = (RelativeLayout) findViewById(R.id.camera_fragment_image_uploading_view);
        this.mFlashView = (FrameLayout) findViewById(R.id.camera_fragment_flash_view);
        this.mRecordingVideoIcon = (ImageView) findViewById(R.id.camera_fragment_recording_video_icon);
        this.mVideoProgressBar = (VideoRecordingProgressBar) findViewById(R.id.camera_fragment_video_progress_bar);
        initActionBarItems();
        setupClickListeners();
        setupAnimations();
        setupCameraCallbacks();
        performPreCaptureActions();
    }

    private void initActionBarItems() {
        this.mActionBarItems = new ArrayList<>();
        this.mActionBarItems.add(new ActionBarItem(getContext().getString(R.string.flash_on), ACTION_ID_FLASH, (int) R.drawable.flash_24));
        this.mActionBarItems.add(new ActionBarItem(getContext().getString(R.string.flash_off), ACTION_ID_FLASH_OFF, (int) R.drawable.flashoff_24));
        this.mActionBarItems.add(new ActionBarItem(getContext().getString(R.string.info), ACTION_ID_INFO, (int) R.drawable.moreinfo_24));
        withActivity(new ActivityTask<CameraActivity>() {
            public void performTask(CameraActivity cameraActivity) {
                cameraActivity.getActionBarManager().setTheme(Theme.TRANSPARENT_BACKGROUND_LIGHT_BUTTONS);
                cameraActivity.getActionBarManager().setBadgeVisible(false);
            }
        });
    }

    public boolean canShowActionBar() {
        return this.mCanShowActionBar;
    }

    public boolean handleActionBarItemSelected(int i) {
        if (i == ACTION_ID_FLASH) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_TURN_ON_FLASH);
            this.mCameraView.setFlash(Flash.TORCH);
            configureActionBar(ACTION_ID_FLASH_OFF, ACTION_ID_INFO);
            return true;
        } else if (i == ACTION_ID_FLASH_OFF) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_TURN_OFF_FLASH);
            this.mCameraView.setFlash(Flash.OFF);
            configureActionBar(ACTION_ID_FLASH, ACTION_ID_INFO);
            return true;
        } else if (i != ACTION_ID_INFO) {
            return super.handleActionBarItemSelected(i);
        } else {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_INFO_ICON);
            withActivity(new ActivityTask<CameraActivity>() {
                public void performTask(CameraActivity cameraActivity) {
                    Intent intent = new Intent(cameraActivity, ExampleUgcIntroActivity.class);
                    intent.putExtra(ExampleUgcIntroActivity.EXTRA_OPENED_FROM_CAMERA, true);
                    cameraActivity.startActivity(intent);
                }
            });
            return true;
        }
    }

    private void configureActionBar(int... iArr) {
        ((CameraActivity) getBaseActivity()).getActionBarManager().clearActionBarItems();
        for (int i : iArr) {
            ((CameraActivity) getBaseActivity()).getActionBarManager().addActionBarItem((ActionBarItem) this.mActionBarItems.get(i));
        }
    }

    public void setupClickListeners() {
        this.mCaptureButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!CameraFragment.this.mIsRecording) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(CameraFragment.CAMERA_FACING_KEY, CameraFragment.this.mCameraView.getFacing().name());
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_TAKE_PICTURE, hashMap);
                    CameraFragment.this.mFlashView.startAnimation(CameraFragment.this.mFlashAnimation);
                    CameraFragment.this.mCameraView.captureSnapshot();
                }
            }
        });
        this.mCaptureButton.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_START_VIDEO_RECORDING);
                CameraFragment.this.startVideoRecording();
                return true;
            }
        });
        this.mCaptureButton.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 1 || !CameraFragment.this.mIsRecording) {
                    return false;
                }
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_END_VIDEO_RECORDING);
                CameraFragment.this.mCameraView.stopCapturingVideo();
                CameraFragment.this.mIsRecording = false;
                return false;
            }
        });
        if (CameraUtils.hasCameraFacing(getBaseActivity(), Facing.BACK)) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_SWITCH_CAMERA_ICON);
            this.mCameraSwitchIcon.setVisibility(0);
            this.mCameraSwitchIcon.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (CameraFragment.this.mCameraView.getFacing() == Facing.BACK) {
                        CameraFragment.this.mCameraView.setFacing(Facing.FRONT);
                    } else {
                        CameraFragment.this.mCameraView.setFacing(Facing.BACK);
                    }
                }
            });
        }
        this.mGalleryIcon.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_OPEN_GALLERY_ICON);
                CameraFragment.this.withServiceFragment(new ServiceTask<BaseActivity, CameraServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, CameraServiceFragment cameraServiceFragment) {
                        cameraServiceFragment.openPictureChooser();
                    }
                });
            }
        });
        this.mFinishButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_USE_PICTURE);
                if (CameraFragment.this.mMediaInfo != null) {
                    CameraFragment.this.uploadImage(CameraFragment.this.mMediaInfo);
                }
            }
        });
        this.mXButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_DISCARD_PICTURE);
                CameraFragment.this.startCamera();
                CameraFragment.this.performPreCaptureActions();
            }
        });
    }

    private void startLoopingVideoAnimation() {
        this.mVideoProgressBar.startAnimation(this.mVideoProgressAnimation);
    }

    private void performPostImageCaptureActions() {
        this.mCanShowActionBar = false;
        ((CameraActivity) getBaseActivity()).getActionBarManager().apply();
        this.mPreCaptureControls.setVisibility(8);
        this.mPostCaptureControls.setVisibility(0);
        stopCamera();
    }

    /* access modifiers changed from: private */
    public void performPostVideoCaptureActions(final MediaInfo mediaInfo, long j) {
        this.mRecordingVideoIcon.setVisibility(8);
        this.mVideoProgressBar.clearAnimation();
        if (j > ((long) MIN_VIDEO_LENGTH_MS)) {
            this.mCanShowActionBar = false;
            ((CameraActivity) getBaseActivity()).getActionBarManager().apply();
            this.mPreCaptureControls.setVisibility(8);
            withActivity(new ActivityTask<CameraActivity>() {
                public void performTask(CameraActivity cameraActivity) {
                    cameraActivity.startVideoPlayerActivity(mediaInfo);
                }
            });
            return;
        }
        handleVideoTooShort();
    }

    /* access modifiers changed from: private */
    public void performPreCaptureActions() {
        this.mMediaInfo = null;
        this.mCanShowActionBar = true;
        this.mPreCaptureControls.setVisibility(0);
        this.mPostCaptureControls.setVisibility(8);
        if (!this.mDeviceHasFlash) {
            configureActionBar(ACTION_ID_INFO);
        } else if (this.mCameraView.getFlash() == Flash.TORCH || this.mCameraView.getFlash() == Flash.ON) {
            configureActionBar(ACTION_ID_FLASH_OFF, ACTION_ID_INFO);
        } else {
            configureActionBar(ACTION_ID_FLASH, ACTION_ID_INFO);
        }
    }

    private void setupCameraCallbacks() {
        this.mCameraView.addCameraListener(new CameraListener() {
            public void onPictureTaken(final byte[] bArr) {
                CameraFragment.this.stopCamera();
                CameraFragment.this.mPreCaptureControls.setVisibility(8);
                CameraFragment.this.withServiceFragment(new ServiceTask<BaseActivity, CameraServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, CameraServiceFragment cameraServiceFragment) {
                        cameraServiceFragment.saveImage(bArr);
                    }
                });
            }

            public void onVideoTaken(File file) {
                CameraFragment.this.mMediaInfo = new MediaInfo(1, Uri.fromFile(file));
                CameraFragment.this.performPostVideoCaptureActions(CameraFragment.this.mMediaInfo, System.currentTimeMillis() - CameraFragment.this.mVideoRecordingStartTime);
            }
        });
    }

    private void setupAnimations() {
        this.mFlashAnimation = new AlphaAnimation(1.0f, 0.0f);
        this.mFlashAnimation.setDuration((long) FLASH_ANIMATION_DURATION_MS);
        this.mFlashAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                if (CameraFragment.this.mFlashView != null) {
                    CameraFragment.this.mFlashView.setVisibility(0);
                }
            }

            public void onAnimationEnd(Animation animation) {
                if (CameraFragment.this.mFlashView != null) {
                    CameraFragment.this.mFlashView.setVisibility(8);
                }
            }
        });
        this.mVideoProgressAnimation = new Animation() {
            /* access modifiers changed from: protected */
            public void applyTransformation(float f, Transformation transformation) {
                CameraFragment.this.mVideoProgressBar.setProgress(f * 100.0f);
            }
        };
        this.mVideoProgressAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                CameraFragment.this.mVideoProgressBar.reset();
            }

            public void onAnimationRepeat(Animation animation) {
                CameraFragment.this.mVideoProgressBar.onCycleCompleted();
            }
        });
        this.mVideoProgressAnimation.setDuration((long) VIDEO_LOOP_DURATION_MS);
        this.mVideoProgressAnimation.setRepeatCount(-1);
    }

    /* access modifiers changed from: private */
    public void startVideoRecording() {
        withServiceFragment(new ServiceTask<BaseActivity, CameraServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CameraServiceFragment cameraServiceFragment) {
                cameraServiceFragment.createVideoFile();
            }
        });
    }

    private void handleVideoTooShort() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_VIDEO_TOO_SHORT);
        withActivity(new ActivityTask<CameraActivity>() {
            public void performTask(CameraActivity cameraActivity) {
                cameraActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(cameraActivity.getString(R.string.video_too_short, new Object[]{Integer.valueOf(CameraFragment.MIN_VIDEO_LENGTH_MS / 1000)})));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void handleResume() {
        super.handleResume();
        startCamera();
        performPreCaptureActions();
        this.mCameraStoppedView.setVisibility(8);
    }

    public void onPause() {
        stopCamera();
        if (this.mCameraStoppedView != null) {
            this.mCameraStoppedView.setVisibility(0);
        }
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mCameraView != null) {
            this.mCameraView.destroy();
        }
    }

    /* access modifiers changed from: private */
    public void startCamera() {
        if (this.mCameraView != null && !this.mCameraView.isStarted()) {
            this.mCameraView.start();
        }
    }

    /* access modifiers changed from: private */
    public void stopCamera() {
        if (this.mCameraView != null && this.mCameraView.isStarted()) {
            this.mCameraView.stop();
        }
    }

    public void handleVideoFileCreationSuccess(File file) {
        this.mCameraView.startCapturingVideo(file);
        this.mIsRecording = true;
        this.mRecordingVideoIcon.setVisibility(0);
        this.mVideoRecordingStartTime = System.currentTimeMillis();
        startLoopingVideoAnimation();
    }

    public void handleVideoFileCreationFailure(final String str) {
        this.mIsRecording = false;
        withActivity(new ActivityTask<CameraActivity>() {
            public void performTask(CameraActivity cameraActivity) {
                cameraActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
            }
        });
    }

    public void handleImageSaveSuccess(Uri uri, boolean z) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_SAVE_IMAGE_SUCCESS);
        this.mMediaInfo = new MediaInfo(0, uri);
        if (z) {
            uploadImage(this.mMediaInfo);
            return;
        }
        performPostImageCaptureActions();
        refreshGallery(uri);
    }

    public void handleImageSaveFailure(final String str) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_SAVE_IMAGE_FAILURE);
        performPreCaptureActions();
        startCamera();
        withActivity(new ActivityTask<CameraActivity>() {
            public void performTask(CameraActivity cameraActivity) {
                cameraActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
            }
        });
    }

    public void handleImageUploadSuccess(final String str, final String str2) {
        this.mImageUploadingView.setVisibility(8);
        withActivity(new ActivityTask<CameraActivity>() {
            public void performTask(CameraActivity cameraActivity) {
                cameraActivity.startProductReviewActivity(CameraFragment.this.mMediaInfo, str2, str);
            }
        });
    }

    public void handleImageUploadFailed() {
        this.mImageUploadingView.setVisibility(8);
        if (this.mCameraView.isStarted()) {
            this.mPreCaptureControls.setVisibility(0);
        } else {
            this.mPostCaptureControls.setVisibility(0);
        }
        withActivity(new ActivityTask<CameraActivity>() {
            public void performTask(CameraActivity cameraActivity) {
                cameraActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(CameraFragment.this.getContext().getString(R.string.failed_to_upload_image)));
            }
        });
    }

    public void uploadImage(final MediaInfo mediaInfo) {
        this.mMediaInfo = mediaInfo;
        this.mImageUploadingView.setVisibility(0);
        this.mPreCaptureControls.setVisibility(8);
        this.mPostCaptureControls.setVisibility(8);
        withServiceFragment(new ServiceTask<BaseActivity, CameraServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CameraServiceFragment cameraServiceFragment) {
                cameraServiceFragment.uploadImage(mediaInfo.getUri());
            }
        });
    }

    private void refreshGallery(final Uri uri) {
        withActivity(new ActivityTask<CameraActivity>() {
            public void performTask(CameraActivity cameraActivity) {
                Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                intent.setData(uri);
                cameraActivity.sendBroadcast(intent);
            }
        });
    }
}
