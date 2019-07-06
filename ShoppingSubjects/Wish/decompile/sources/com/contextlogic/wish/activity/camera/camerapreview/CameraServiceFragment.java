package com.contextlogic.wish.activity.camera.camerapreview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.camera.MediaInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.CreateVideoFileService;
import com.contextlogic.wish.api.service.standalone.ParseImageChooserIntentService;
import com.contextlogic.wish.api.service.standalone.SaveImageService;
import com.contextlogic.wish.api.service.standalone.SaveImageService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.UploadImageService;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.util.BitmapUtil;
import com.contextlogic.wish.util.FileUtil;
import java.io.File;

public class CameraServiceFragment extends ServiceFragment {
    private static String IMAGE_BUCKET_NAME = "ticket-image-uploads";
    private CreateVideoFileService mCreateVideoFileService;
    /* access modifiers changed from: private */
    public ParseImageChooserIntentService mImageChooserService;
    private SaveImageService mSaveImageService;
    private UploadImageService mUploadImageService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mSaveImageService = new SaveImageService();
        this.mUploadImageService = new UploadImageService();
        this.mCreateVideoFileService = new CreateVideoFileService();
        this.mImageChooserService = new ParseImageChooserIntentService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mSaveImageService.cancelAllRequests();
        this.mUploadImageService.cancelAllRequests();
        this.mCreateVideoFileService.cancelAllRequests();
        this.mImageChooserService.cancelAllRequests();
    }

    public void saveImage(byte[] bArr) {
        showLoadingSpinner();
        this.mSaveImageService.requestService(bArr, null, new SuccessCallback() {
            public void onSuccess(final Uri uri) {
                CameraServiceFragment.this.hideLoadingSpinner();
                CameraServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CameraFragment>() {
                    public void performTask(BaseActivity baseActivity, CameraFragment cameraFragment) {
                        cameraFragment.handleImageSaveSuccess(uri, false);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                CameraServiceFragment.this.hideLoadingSpinner();
                CameraServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CameraFragment>() {
                    public void performTask(BaseActivity baseActivity, CameraFragment cameraFragment) {
                        cameraFragment.handleImageSaveFailure(str);
                    }
                });
            }
        });
    }

    public void saveImage(Bitmap bitmap) {
        showLoadingSpinner();
        this.mSaveImageService.requestService(null, bitmap, new SuccessCallback() {
            public void onSuccess(final Uri uri) {
                CameraServiceFragment.this.hideLoadingSpinner();
                CameraServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CameraFragment>() {
                    public void performTask(BaseActivity baseActivity, CameraFragment cameraFragment) {
                        cameraFragment.handleImageSaveSuccess(uri, true);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                CameraServiceFragment.this.hideLoadingSpinner();
                CameraServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CameraFragment>() {
                    public void performTask(BaseActivity baseActivity, CameraFragment cameraFragment) {
                        cameraFragment.handleImageSaveFailure(str);
                    }
                });
            }
        });
    }

    public void createVideoFile() {
        this.mCreateVideoFileService.requestService(new CreateVideoFileService.SuccessCallback() {
            public void onSuccess(final File file) {
                CameraServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CameraFragment>() {
                    public void performTask(BaseActivity baseActivity, CameraFragment cameraFragment) {
                        cameraFragment.handleVideoFileCreationSuccess(file);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                CameraServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CameraFragment>() {
                    public void performTask(BaseActivity baseActivity, CameraFragment cameraFragment) {
                        cameraFragment.handleVideoFileCreationFailure(str);
                    }
                });
            }
        });
    }

    public void openPictureChooser() {
        withActivity(new ActivityTask<CameraActivity>() {
            public void performTask(final CameraActivity cameraActivity) {
                final Intent intent = new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                cameraActivity.startActivityForResult(intent, cameraActivity.addResultCodeCallback(new ActivityResultCallback() {
                    public void onActivityResult(BaseActivity baseActivity, int i, int i2, final Intent intent) {
                        if (i2 != 0) {
                            if (i2 == -1) {
                                CameraServiceFragment.this.showLoadingSpinner();
                                CameraServiceFragment.this.mImageChooserService.requestService(intent, intent, new ParseImageChooserIntentService.SuccessCallback() {
                                    public void onSuccess(Bitmap bitmap) {
                                        CameraServiceFragment.this.hideLoadingSpinner();
                                        Uri data = intent.getData();
                                        if (!FileUtil.isNewGooglePhotosUri(data) || !data.getPath().contains("mediakey")) {
                                            final MediaInfo mediaInfo = new MediaInfo(0, data);
                                            CameraServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CameraFragment>() {
                                                public void performTask(BaseActivity baseActivity, CameraFragment cameraFragment) {
                                                    cameraFragment.uploadImage(mediaInfo);
                                                }
                                            });
                                            return;
                                        }
                                        CameraServiceFragment.this.saveImage(bitmap);
                                    }
                                }, new DefaultFailureCallback() {
                                    public void onFailure(String str) {
                                        CameraServiceFragment.this.hideLoadingSpinner();
                                        cameraActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(cameraActivity.getString(R.string.problem_opening_selected_image)));
                                    }
                                });
                            } else {
                                cameraActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(cameraActivity.getString(R.string.problem_opening_selected_image)));
                            }
                        }
                    }
                }));
            }
        });
    }

    public void uploadImage(Uri uri) {
        this.mUploadImageService.requestService(BitmapUtil.openImageUri(uri), IMAGE_BUCKET_NAME, new UploadImageService.SuccessCallback() {
            public void onSuccess(final String str, final String str2) {
                CameraServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CameraFragment>() {
                    public void performTask(BaseActivity baseActivity, CameraFragment cameraFragment) {
                        cameraFragment.handleImageUploadSuccess(str, str2);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CameraServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CameraFragment>() {
                    public void performTask(BaseActivity baseActivity, CameraFragment cameraFragment) {
                        cameraFragment.handleImageUploadFailed();
                    }
                });
            }
        });
    }
}
