package com.contextlogic.wish.activity.productreview;

import android.content.Intent;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.camera.MediaInfo;
import com.contextlogic.wish.activity.camera.camerapreview.CameraActivity;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.api.model.WishProductReviewItem;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.IntentUtil;

public class ProductReviewActivity extends DrawerActivity {
    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new ProductReviewFragment();
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new ProductReviewServiceFragment();
    }

    public String getActionBarTitle() {
        return WishApplication.getInstance().getResources().getString(R.string.share_your_purchase);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.PRODUCT_REVIEW;
    }

    public void startCameraActivity(WishProductReviewItem wishProductReviewItem) {
        Intent intent = new Intent(this, CameraActivity.class);
        IntentUtil.putParcelableExtra(intent, CameraActivity.EXTRA_REVIEW_ITEM, wishProductReviewItem);
        startActivity(intent);
    }

    public MediaInfo getMediaInfo() {
        return (MediaInfo) IntentUtil.getParcelableExtra(getIntent(), CameraActivity.EXTRA_MEDIA_INFO);
    }

    public WishProductReviewItem getReviewItem() {
        return (WishProductReviewItem) IntentUtil.getParcelableExtra(getIntent(), CameraActivity.EXTRA_REVIEW_ITEM);
    }

    public String getUploadedImageName() {
        String[] stringArrayExtra = getIntent().getStringArrayExtra(CameraActivity.EXTRA_UPLOADED_IMAGE_INFO);
        if (stringArrayExtra != null) {
            return stringArrayExtra[0];
        }
        return null;
    }

    public String getUploadedVideoId() {
        return getIntent().getStringExtra("ExtraVideoId");
    }
}
