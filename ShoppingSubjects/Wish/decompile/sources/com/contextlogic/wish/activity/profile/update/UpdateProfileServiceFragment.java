package com.contextlogic.wish.activity.profile.update;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.ServiceFragment.PermissionCallback;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.ParseImageChooserIntentService;
import com.contextlogic.wish.api.service.standalone.ParseImageChooserIntentService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.UpdateProfileService;
import com.contextlogic.wish.api.service.standalone.UploadProfileImageService;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;

public class UpdateProfileServiceFragment extends ServiceFragment<UpdateProfileActivity> {
    /* access modifiers changed from: private */
    public ParseImageChooserIntentService mParseImageChooserIntentService;
    private UpdateProfileService mUpdateProfileService;
    private UploadProfileImageService mUploadProfileImageService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mUpdateProfileService = new UpdateProfileService();
        this.mUploadProfileImageService = new UploadProfileImageService();
        this.mParseImageChooserIntentService = new ParseImageChooserIntentService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mUpdateProfileService.cancelAllRequests();
        this.mUploadProfileImageService.cancelAllRequests();
        this.mParseImageChooserIntentService.cancelAllRequests();
    }

    public void changeProfileImage() {
        withActivity(new ActivityTask<UpdateProfileActivity>() {
            public void performTask(UpdateProfileActivity updateProfileActivity) {
                updateProfileActivity.requestPermission("android.permission.CAMERA", new PermissionCallback() {
                    public void onPermissionGranted() {
                        UpdateProfileServiceFragment.this.requestExternalFilePermissions();
                    }

                    public void onPermissionDenied() {
                        UpdateProfileServiceFragment.this.requestExternalFilePermissions();
                    }
                });
            }
        });
    }

    public void requestExternalFilePermissions() {
        withActivity(new ActivityTask<UpdateProfileActivity>() {
            public void performTask(UpdateProfileActivity updateProfileActivity) {
                updateProfileActivity.requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", new PermissionCallback() {
                    public void onPermissionGranted() {
                        UpdateProfileServiceFragment.this.openPictureChooser();
                    }

                    public void onPermissionDenied() {
                        UpdateProfileServiceFragment.this.withActivity(new ActivityTask<UpdateProfileActivity>() {
                            public void performTask(UpdateProfileActivity updateProfileActivity) {
                                updateProfileActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(updateProfileActivity.getString(R.string.please_enable_file_access_permissions_for_images)));
                            }
                        });
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void openPictureChooser() {
        withActivity(new ActivityTask<UpdateProfileActivity>() {
            public void performTask(UpdateProfileActivity updateProfileActivity) {
                final Intent imageChooserIntent = IntentUtil.getImageChooserIntent();
                updateProfileActivity.startActivityForResult(imageChooserIntent, updateProfileActivity.addResultCodeCallback(new ActivityResultCallback() {
                    public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                        if (i2 != 0) {
                            if (i2 == -1) {
                                UpdateProfileServiceFragment.this.withActivity(new ActivityTask<UpdateProfileActivity>() {
                                    public void performTask(UpdateProfileActivity updateProfileActivity) {
                                        updateProfileActivity.showLoadingDialog();
                                    }
                                });
                                UpdateProfileServiceFragment.this.mParseImageChooserIntentService.requestService(imageChooserIntent, intent, new SuccessCallback() {
                                    public void onSuccess(Bitmap bitmap) {
                                        UpdateProfileServiceFragment.this.uploadProfileImage(bitmap);
                                    }
                                }, new DefaultFailureCallback() {
                                    public void onFailure(String str) {
                                        UpdateProfileServiceFragment.this.withActivity(new ActivityTask<UpdateProfileActivity>() {
                                            public void performTask(UpdateProfileActivity updateProfileActivity) {
                                                updateProfileActivity.hideLoadingDialog();
                                                updateProfileActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(updateProfileActivity.getString(R.string.problem_opening_selected_image)));
                                            }
                                        });
                                    }
                                });
                            } else {
                                UpdateProfileServiceFragment.this.withActivity(new ActivityTask<UpdateProfileActivity>() {
                                    public void performTask(UpdateProfileActivity updateProfileActivity) {
                                        updateProfileActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(updateProfileActivity.getString(R.string.problem_opening_selected_image)));
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
    public void uploadProfileImage(Bitmap bitmap) {
        this.mUploadProfileImageService.requestService(bitmap, getUploadImageSuccessCallback(), new DefaultFailureCallback() {
            public void onFailure(final String str) {
                UpdateProfileServiceFragment.this.withActivity(new ActivityTask<UpdateProfileActivity>() {
                    public void performTask(UpdateProfileActivity updateProfileActivity) {
                        updateProfileActivity.hideLoadingDialog();
                        updateProfileActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                });
            }
        });
    }

    public void updateProfile(String str, String str2, int i, int i2, int i3, String str3, boolean z) {
        withActivity(new ActivityTask<UpdateProfileActivity>() {
            public void performTask(UpdateProfileActivity updateProfileActivity) {
                updateProfileActivity.showLoadingDialog();
            }
        });
        this.mUpdateProfileService.requestService(str, str2, i, i2, i3, str3, z, getUpdateProfileSuccessCallback(), new DefaultFailureCallback() {
            public void onFailure(final String str) {
                UpdateProfileServiceFragment.this.withActivity(new ActivityTask<UpdateProfileActivity>() {
                    public void performTask(UpdateProfileActivity updateProfileActivity) {
                        String str;
                        if (str != null) {
                            str = str;
                        } else {
                            str = updateProfileActivity.getString(R.string.error_updating_your_profile);
                        }
                        updateProfileActivity.hideLoadingDialog();
                        updateProfileActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                });
            }
        });
    }

    public void updateProfile(String str, String str2, String str3, boolean z) {
        updateProfile(str, str2, -1, -1, -1, str3, z);
    }

    public void confirmClosing() {
        withActivity(new ActivityTask<UpdateProfileActivity>() {
            public void performTask(UpdateProfileActivity updateProfileActivity) {
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, UpdateProfileServiceFragment.this.getString(R.string.continue_editing), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(1, UpdateProfileServiceFragment.this.getString(R.string.leave_form), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                ArrayList arrayList = new ArrayList();
                arrayList.add(multiButtonDialogChoice);
                arrayList.add(multiButtonDialogChoice2);
                MultiButtonDialogFragment build = new MultiButtonDialogFragmentBuilder().setTitle(UpdateProfileServiceFragment.this.getString(R.string.you_have_unsaved_changes)).setSubTitle(UpdateProfileServiceFragment.this.getString(R.string.are_you_sure_you_want_to_leave)).hideXButton().setButtons(arrayList).build();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_UPDATE_PROFILE_REDESIGN_SAVED_CHANGES_CONFIRMATION);
                updateProfileActivity.startDialog(build, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_UPDATE_PROFILE_REDESIGN_POPUP_LEAVE_FORM);
                            UpdateProfileServiceFragment.this.withActivity(new ActivityTask<UpdateProfileActivity>() {
                                public void performTask(UpdateProfileActivity updateProfileActivity) {
                                    updateProfileActivity.finishActivity();
                                }
                            });
                        } else if (i == 0) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_UPDATE_PROFILE_REDESIGN_POPUP_CONTINUE_EDITING);
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public UploadProfileImageService.SuccessCallback getUploadImageSuccessCallback() {
        return new UploadProfileImageService.SuccessCallback() {
            public void onSuccess(final WishUser wishUser) {
                UpdateProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UpdateProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, UpdateProfileFragment updateProfileFragment) {
                        baseActivity.hideLoadingDialog();
                        updateProfileFragment.updateUserImage(wishUser);
                    }
                }, "FragmentTagMainContent");
            }
        };
    }

    /* access modifiers changed from: protected */
    public DefaultSuccessCallback getUpdateProfileSuccessCallback() {
        return new DefaultSuccessCallback() {
            public void onSuccess() {
                UpdateProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UpdateProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, UpdateProfileFragment updateProfileFragment) {
                        baseActivity.hideLoadingDialog();
                        updateProfileFragment.handleUpdateSuccess();
                    }
                }, "FragmentTagMainContent");
            }
        };
    }
}
