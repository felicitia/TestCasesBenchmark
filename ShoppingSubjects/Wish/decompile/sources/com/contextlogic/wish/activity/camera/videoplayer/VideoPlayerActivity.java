package com.contextlogic.wish.activity.camera.videoplayer;

import android.content.Intent;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.camera.MediaInfo;
import com.contextlogic.wish.activity.camera.camerapreview.CameraActivity;
import com.contextlogic.wish.activity.productreview.ProductReviewActivity;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.model.WishProductReviewItem;
import com.contextlogic.wish.util.IntentUtil;

public class VideoPlayerActivity extends DrawerActivity {
    /* access modifiers changed from: protected */
    public boolean canHaveActionBar() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean canHaveMenu() {
        return false;
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new VideoPlayerFragment();
    }

    /* access modifiers changed from: protected */
    public VideoPlayerServiceFragment createServiceFragment() {
        return new VideoPlayerServiceFragment();
    }

    public MediaInfo getVideoInfo() {
        return (MediaInfo) IntentUtil.getParcelableExtra(getIntent(), CameraActivity.EXTRA_MEDIA_INFO);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.VIDEO_PLAYER;
    }

    public WishProductReviewItem getReviewItem() {
        if (getIntent() == null) {
            return null;
        }
        return (WishProductReviewItem) IntentUtil.getParcelableExtra(getIntent(), CameraActivity.EXTRA_REVIEW_ITEM);
    }

    public void startProductReviewActivity(MediaInfo mediaInfo, String str) {
        Intent intent = new Intent(this, ProductReviewActivity.class);
        IntentUtil.putParcelableExtra(intent, CameraActivity.EXTRA_MEDIA_INFO, mediaInfo);
        IntentUtil.putParcelableExtra(intent, CameraActivity.EXTRA_REVIEW_ITEM, getReviewItem());
        intent.putExtra("ExtraVideoId", str);
        startActivity(intent);
        finishActivity();
    }
}
