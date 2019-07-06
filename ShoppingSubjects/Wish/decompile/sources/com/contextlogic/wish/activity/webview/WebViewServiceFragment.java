package com.contextlogic.wish.activity.webview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.webkit.WebView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.ServiceFragment.PermissionCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.ParseImageChooserIntentService;
import com.contextlogic.wish.api.service.standalone.ParseImageChooserIntentService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.ParseVideoChooserIntentService;
import com.contextlogic.wish.api.service.standalone.ParseVideoChooserIntentService.FailureCallback;
import com.contextlogic.wish.api.service.standalone.UploadImageService;
import com.contextlogic.wish.api.service.standalone.UploadVideoService;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.VideoUtil.VideoSpecification;

public class WebViewServiceFragment extends ServiceFragment<WebViewActivity> {
    private WebView mCachedWebView;
    /* access modifiers changed from: private */
    public ParseImageChooserIntentService mParseImageChooserIntentService;
    /* access modifiers changed from: private */
    public ParseVideoChooserIntentService mParseVideoChooserIntentService;
    private UploadImageService mUploadImageService;
    private UploadVideoService mUploadVideoService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mUploadImageService = new UploadImageService();
        this.mUploadVideoService = new UploadVideoService();
        this.mParseImageChooserIntentService = new ParseImageChooserIntentService();
        this.mParseVideoChooserIntentService = new ParseVideoChooserIntentService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mUploadImageService.cancelAllRequests();
        this.mUploadVideoService.cancelAllRequests();
        this.mParseImageChooserIntentService.cancelAllRequests();
        this.mParseVideoChooserIntentService.cancelAllRequests();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mCachedWebView != null) {
            this.mCachedWebView.stopLoading();
            this.mCachedWebView.setWebViewClient(null);
            this.mCachedWebView.loadUrl("about:blank");
            this.mCachedWebView.onPause();
            this.mCachedWebView = null;
        }
    }

    public void uploadImage(final String str, final String str2) {
        withActivity(new ActivityTask<WebViewActivity>() {
            public void performTask(WebViewActivity webViewActivity) {
                webViewActivity.requestPermission("android.permission.CAMERA", new PermissionCallback() {
                    public void onPermissionGranted() {
                        WebViewServiceFragment.this.requestExternalFilePermissions(str, str2);
                    }

                    public void onPermissionDenied() {
                        WebViewServiceFragment.this.requestExternalFilePermissions(str, str2);
                    }
                });
            }
        });
    }

    public void requestExternalFilePermissions(final String str, final String str2) {
        withActivity(new ActivityTask<WebViewActivity>() {
            public void performTask(WebViewActivity webViewActivity) {
                webViewActivity.requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", new PermissionCallback() {
                    public void onPermissionGranted() {
                        WebViewServiceFragment.this.openPictureChooser(str, str2);
                    }

                    public void onPermissionDenied() {
                        WebViewServiceFragment.this.withActivity(new ActivityTask<WebViewActivity>() {
                            public void performTask(WebViewActivity webViewActivity) {
                                webViewActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(webViewActivity.getString(R.string.please_enable_file_access_permissions_for_images)));
                            }
                        });
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void openPictureChooser(final String str, final String str2) {
        withActivity(new ActivityTask<WebViewActivity>() {
            public void performTask(WebViewActivity webViewActivity) {
                final Intent imageChooserIntent = IntentUtil.getImageChooserIntent();
                webViewActivity.startActivityForResult(imageChooserIntent, webViewActivity.addResultCodeCallback(new ActivityResultCallback() {
                    public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                        if (i2 != 0) {
                            if (i2 == -1) {
                                WebViewServiceFragment.this.withActivity(new ActivityTask<WebViewActivity>() {
                                    public void performTask(WebViewActivity webViewActivity) {
                                        webViewActivity.showLoadingDialog();
                                    }
                                });
                                WebViewServiceFragment.this.mParseImageChooserIntentService.requestService(imageChooserIntent, intent, new SuccessCallback() {
                                    public void onSuccess(Bitmap bitmap) {
                                        WebViewServiceFragment.this.uploadImage(bitmap, str, str2);
                                    }
                                }, new DefaultFailureCallback() {
                                    public void onFailure(String str) {
                                        WebViewServiceFragment.this.withActivity(new ActivityTask<WebViewActivity>() {
                                            public void performTask(WebViewActivity webViewActivity) {
                                                webViewActivity.hideLoadingDialog();
                                                webViewActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(webViewActivity.getString(R.string.problem_opening_selected_image)));
                                            }
                                        });
                                    }
                                });
                            } else {
                                WebViewServiceFragment.this.withActivity(new ActivityTask<WebViewActivity>() {
                                    public void performTask(WebViewActivity webViewActivity) {
                                        webViewActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(webViewActivity.getString(R.string.problem_opening_selected_image)));
                                    }
                                });
                            }
                        }
                    }
                }));
            }
        });
    }

    /* access modifiers changed from: private */
    public void uploadImage(Bitmap bitmap, final String str, String str2) {
        this.mUploadImageService.requestService(bitmap, str2, new UploadImageService.SuccessCallback() {
            public void onSuccess(final String str, final String str2) {
                WebViewServiceFragment.this.withUiFragment(new UiTask<WebViewActivity, WebViewFragment>() {
                    public void performTask(WebViewActivity webViewActivity, WebViewFragment webViewFragment) {
                        webViewActivity.hideLoadingDialog();
                        webViewFragment.completeImageUpload(str, str, str2);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                WebViewServiceFragment.this.withActivity(new ActivityTask<WebViewActivity>() {
                    public void performTask(WebViewActivity webViewActivity) {
                        webViewActivity.hideLoadingDialog();
                        webViewActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                });
            }
        });
    }

    public void uploadVideo(final String str, final VideoSpecification videoSpecification) {
        withActivity(new ActivityTask<WebViewActivity>() {
            public void performTask(WebViewActivity webViewActivity) {
                webViewActivity.requestPermission("android.permission.CAMERA", new PermissionCallback() {
                    public void onPermissionGranted() {
                        WebViewServiceFragment.this.requestExternalFilePermissions(str, videoSpecification);
                    }

                    public void onPermissionDenied() {
                        WebViewServiceFragment.this.requestExternalFilePermissions(str, videoSpecification);
                    }
                });
            }
        });
    }

    public void requestExternalFilePermissions(final String str, final VideoSpecification videoSpecification) {
        withActivity(new ActivityTask<WebViewActivity>() {
            public void performTask(WebViewActivity webViewActivity) {
                webViewActivity.requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", new PermissionCallback() {
                    public void onPermissionGranted() {
                        WebViewServiceFragment.this.openVideoChooser(str, videoSpecification);
                    }

                    public void onPermissionDenied() {
                        WebViewServiceFragment.this.withActivity(new ActivityTask<WebViewActivity>() {
                            public void performTask(WebViewActivity webViewActivity) {
                                webViewActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(webViewActivity.getString(R.string.please_enable_file_access_permissions_for_images)));
                            }
                        });
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void openVideoChooser(final String str, final VideoSpecification videoSpecification) {
        withActivity(new ActivityTask<WebViewActivity>() {
            public void performTask(WebViewActivity webViewActivity) {
                final Intent videoChooserIntent = IntentUtil.getVideoChooserIntent();
                webViewActivity.startActivityForResult(videoChooserIntent, webViewActivity.addResultCodeCallback(new ActivityResultCallback() {
                    public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                        if (i2 != 0) {
                            if (i2 == -1) {
                                WebViewServiceFragment.this.withActivity(new ActivityTask<WebViewActivity>() {
                                    public void performTask(WebViewActivity webViewActivity) {
                                        webViewActivity.showLoadingDialog();
                                    }
                                });
                                WebViewServiceFragment.this.mParseVideoChooserIntentService.requestService(videoChooserIntent, intent, videoSpecification, new ParseVideoChooserIntentService.SuccessCallback() {
                                    public void onSuccess(String str) {
                                        WebViewServiceFragment.this.uploadVideo(str, str);
                                    }
                                }, new FailureCallback() {
                                    public void onFailure(final boolean z) {
                                        WebViewServiceFragment.this.withActivity(new ActivityTask<WebViewActivity>() {
                                            public void performTask(WebViewActivity webViewActivity) {
                                                webViewActivity.hideLoadingDialog();
                                                if (z) {
                                                    webViewActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(webViewActivity.getString(R.string.video_too_long, new Object[]{Long.valueOf(videoSpecification.maxDuration / 1000)})));
                                                    return;
                                                }
                                                webViewActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(webViewActivity.getString(R.string.problem_opening_selected_video)));
                                            }
                                        });
                                    }
                                });
                            } else {
                                WebViewServiceFragment.this.withActivity(new ActivityTask<WebViewActivity>() {
                                    public void performTask(WebViewActivity webViewActivity) {
                                        webViewActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(webViewActivity.getString(R.string.problem_opening_selected_video)));
                                    }
                                });
                            }
                        }
                    }
                }));
            }
        });
    }

    /* access modifiers changed from: private */
    public void uploadVideo(String str, String str2) {
        this.mUploadVideoService.requestService(str, str2, new UploadVideoService.SuccessCallback() {
            public void onSuccess(final String str) {
                WebViewServiceFragment.this.withUiFragment(new UiTask<WebViewActivity, WebViewFragment>() {
                    public void performTask(WebViewActivity webViewActivity, WebViewFragment webViewFragment) {
                        webViewActivity.hideLoadingDialog();
                        webViewFragment.completeVideoUpload(str);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                WebViewServiceFragment.this.withActivity(new ActivityTask<WebViewActivity>() {
                    public void performTask(WebViewActivity webViewActivity) {
                        webViewActivity.hideLoadingDialog();
                        webViewActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                });
            }
        });
    }

    public void setCachedWebView(WebView webView) {
        this.mCachedWebView = webView;
    }

    public WebView getCachedWebView() {
        return this.mCachedWebView;
    }
}
