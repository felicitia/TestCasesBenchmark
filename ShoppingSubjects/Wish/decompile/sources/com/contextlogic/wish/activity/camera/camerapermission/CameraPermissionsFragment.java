package com.contextlogic.wish.activity.camera.camerapermission;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.ServiceFragment.PermissionCallback;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.activity.camera.camerapreview.CameraActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;

public class CameraPermissionsFragment extends UiFragment<CameraPermissionsActivity> {
    private TextView mMediaPermissionsButton;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.camera_permissions_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        ((CameraPermissionsActivity) getBaseActivity()).getActionBarManager().setTheme(Theme.TRANSPARENT_BACKGROUND_LIGHT_BUTTONS);
        ((CameraPermissionsActivity) getBaseActivity()).getActionBarManager().setBadgeVisible(false);
        this.mMediaPermissionsButton = (TextView) findViewById(R.id.media_permissions_button);
        this.mMediaPermissionsButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CAMERA_PERMISSIONS_BUTTON);
                CameraPermissionsFragment.this.requestCameraPermissions();
            }
        });
    }

    /* access modifiers changed from: private */
    public void requestCameraPermissions() {
        withActivity(new ActivityTask<CameraPermissionsActivity>() {
            public void performTask(CameraPermissionsActivity cameraPermissionsActivity) {
                cameraPermissionsActivity.requestPermission("android.permission.CAMERA", new PermissionCallback() {
                    public void onPermissionDenied() {
                    }

                    public void onPermissionGranted() {
                        CameraPermissionsFragment.this.requestStoragePermissions();
                    }
                });
            }
        });
    }

    public void requestStoragePermissions() {
        withActivity(new ActivityTask<CameraPermissionsActivity>() {
            public void performTask(final CameraPermissionsActivity cameraPermissionsActivity) {
                cameraPermissionsActivity.requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", new PermissionCallback() {
                    public void onPermissionDenied() {
                    }

                    public void onPermissionGranted() {
                        cameraPermissionsActivity.startActivity(new Intent(cameraPermissionsActivity, CameraActivity.class));
                        cameraPermissionsActivity.finishActivity();
                    }
                });
            }
        });
    }
}
