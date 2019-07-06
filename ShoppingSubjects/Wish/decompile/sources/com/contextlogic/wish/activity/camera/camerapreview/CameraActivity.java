package com.contextlogic.wish.activity.camera.camerapreview;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.camera.MediaInfo;
import com.contextlogic.wish.activity.camera.camerapermission.CameraPermissionsActivity;
import com.contextlogic.wish.activity.camera.videoplayer.VideoPlayerActivity;
import com.contextlogic.wish.activity.productreview.ProductReviewActivity;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.model.WishProductReviewItem;
import com.contextlogic.wish.util.IntentUtil;

public class CameraActivity extends DrawerActivity {
    public static String EXTRA_MEDIA_INFO = "ExtraMediaInfo";
    public static String EXTRA_REVIEW_ITEM = "ExtraReviewItem";
    public static String EXTRA_UPLOADED_IMAGE_INFO = "ExtraUploadedImageInfo";
    private CameraFragment mCameraFragment;

    /* access modifiers changed from: protected */
    public boolean canHaveMenu() {
        return false;
    }

    public int getBottomNavigationTabIndex() {
        return -1;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        this.mCameraFragment = new CameraFragment();
        return this.mCameraFragment;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new CameraServiceFragment();
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.CAMERA;
    }

    /* access modifiers changed from: protected */
    public boolean canHaveActionBar() {
        if (this.mCameraFragment == null) {
            return true;
        }
        return this.mCameraFragment.canShowActionBar();
    }

    public WishProductReviewItem getReviewItem() {
        if (getIntent() == null) {
            return null;
        }
        return (WishProductReviewItem) IntentUtil.getParcelableExtra(getIntent(), EXTRA_REVIEW_ITEM);
    }

    public void startProductReviewActivity(MediaInfo mediaInfo, String str, String str2) {
        Intent intent = new Intent(this, ProductReviewActivity.class);
        IntentUtil.putParcelableExtra(intent, EXTRA_MEDIA_INFO, mediaInfo);
        IntentUtil.putParcelableExtra(intent, EXTRA_REVIEW_ITEM, getReviewItem());
        intent.putExtra(EXTRA_UPLOADED_IMAGE_INFO, new String[]{str, str2});
        startActivity(intent);
    }

    public void startVideoPlayerActivity(MediaInfo mediaInfo) {
        Intent intent = new Intent(this, VideoPlayerActivity.class);
        IntentUtil.putParcelableExtra(intent, EXTRA_MEDIA_INFO, mediaInfo);
        IntentUtil.putParcelableExtra(intent, EXTRA_REVIEW_ITEM, getReviewItem());
        startActivity(intent);
    }

    public static void startCameraActivityIfPermissionsGranted(BaseActivity baseActivity) {
        Intent intent;
        if (ContextCompat.checkSelfPermission(baseActivity, "android.permission.CAMERA") == 0 && ContextCompat.checkSelfPermission(baseActivity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            intent = new Intent(baseActivity, CameraActivity.class);
        } else {
            intent = new Intent(baseActivity, CameraPermissionsActivity.class);
        }
        baseActivity.startActivity(intent);
    }
}
