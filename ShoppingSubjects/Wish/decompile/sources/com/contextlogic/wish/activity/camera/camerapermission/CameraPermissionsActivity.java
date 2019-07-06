package com.contextlogic.wish.activity.camera.camerapermission;

import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class CameraPermissionsActivity extends FullScreenActivity {
    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new CameraPermissionsFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return super.createServiceFragment();
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.CAMERA_PERMISSIONS;
    }
}
