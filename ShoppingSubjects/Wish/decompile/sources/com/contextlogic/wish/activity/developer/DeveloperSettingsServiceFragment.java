package com.contextlogic.wish.activity.developer;

import android.support.design.widget.Snackbar;
import android.view.View;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.AdminClearDeviceSeenHistoryService;
import com.contextlogic.wish.api.service.standalone.AdminUpdateExperimentBucketService;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.http.ImageHttpCache;
import com.contextlogic.wish.http.ImageHttpCache.CacheClearCallback;

public class DeveloperSettingsServiceFragment extends ServiceFragment<DeveloperSettingsActivity> {
    private AdminClearDeviceSeenHistoryService mClearDeviceSeenHistoryService;
    private AdminUpdateExperimentBucketService mUpdateExperimentBucketService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mUpdateExperimentBucketService = new AdminUpdateExperimentBucketService();
        this.mClearDeviceSeenHistoryService = new AdminClearDeviceSeenHistoryService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        if (this.mUpdateExperimentBucketService != null) {
            this.mUpdateExperimentBucketService.cancelAllRequests();
        }
        if (this.mClearDeviceSeenHistoryService != null) {
            this.mClearDeviceSeenHistoryService.cancelAllRequests();
        }
    }

    public void clearImageCache() {
        withActivity(new ActivityTask<DeveloperSettingsActivity>() {
            public void performTask(DeveloperSettingsActivity developerSettingsActivity) {
                developerSettingsActivity.showLoadingDialog();
            }
        });
        ImageHttpCache.getInstance().clearCache(new CacheClearCallback() {
            public void onCacheCleared() {
                DeveloperSettingsServiceFragment.this.completeClearImageCache();
            }
        });
    }

    /* access modifiers changed from: private */
    public void completeClearImageCache() {
        withActivity(new ActivityTask<DeveloperSettingsActivity>() {
            public void performTask(DeveloperSettingsActivity developerSettingsActivity) {
                developerSettingsActivity.hideLoadingDialog();
                developerSettingsActivity.startDialog(MultiButtonDialogFragment.createMultiButtonOkDialog("Done", "In memory image cache cleared. File system cache cleared."));
            }
        });
    }

    public void setExperimentBucket(final String str, final String str2) {
        if (this.mUpdateExperimentBucketService != null) {
            this.mUpdateExperimentBucketService.requestService(str, str2, new DefaultSuccessCallback() {
                public void onSuccess() {
                    ApplicationEventManager.getInstance().triggerEvent(EventType.DATA_CENTER_UPDATED, getClass().toString(), null);
                    DeveloperSettingsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UiFragment>() {
                        public void performTask(BaseActivity baseActivity, UiFragment uiFragment) {
                            if (uiFragment.getView() != null) {
                                View view = uiFragment.getView();
                                StringBuilder sb = new StringBuilder();
                                sb.append("Successfully set ");
                                sb.append(str);
                                sb.append(" to ");
                                sb.append(str2);
                                Snackbar.make(view, sb.toString(), -1).show();
                            }
                        }
                    });
                }
            }, new DefaultFailureCallback() {
                public void onFailure(final String str) {
                    DeveloperSettingsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UiFragment>() {
                        public void performTask(BaseActivity baseActivity, UiFragment uiFragment) {
                            if (uiFragment.getView() != null) {
                                Snackbar.make(uiFragment.getView(), str == null ? "Failed to update bucket!" : str, -1).show();
                            }
                        }
                    });
                }
            });
        }
    }

    public void clearDeviceSeenHistory() {
        if (this.mClearDeviceSeenHistoryService != null) {
            this.mClearDeviceSeenHistoryService.requestService(new DefaultSuccessCallback() {
                public void onSuccess() {
                    DeveloperSettingsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UiFragment>() {
                        public void performTask(BaseActivity baseActivity, UiFragment uiFragment) {
                            if (uiFragment.getView() != null) {
                                Snackbar.make(uiFragment.getView(), "Successfully reset seen device history.", -1).show();
                            }
                        }
                    });
                }
            }, new DefaultFailureCallback() {
                public void onFailure(final String str) {
                    DeveloperSettingsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UiFragment>() {
                        public void performTask(BaseActivity baseActivity, UiFragment uiFragment) {
                            if (uiFragment.getView() != null) {
                                Snackbar.make(uiFragment.getView(), str == null ? "Failed to reset seen device history!" : str, -1).show();
                            }
                        }
                    });
                }
            });
        }
    }
}
