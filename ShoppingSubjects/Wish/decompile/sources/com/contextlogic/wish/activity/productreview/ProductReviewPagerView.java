package com.contextlogic.wish.activity.productreview;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.viewpager.BasePagerViewInterface;

public abstract class ProductReviewPagerView extends LoadingPageView implements LoadingPageManager, BasePagerViewInterface {
    public static String MERCHANT_RATING_COMMENT_KEY = "MerchantRatingCommentKey";
    public static String MERCHANT_STAR_RATING_KEY = "MerchantStarRatingKey";
    public static String PRODUCT_KEY = "ProductKey";
    public static String PRODUCT_RATING_COMMENT_KEY = "ProductRatingCommentKey";
    public static String PRODUCT_SIZE_CHOICE_KEY = "ProductRatingSizeChoiceKey";
    public static String PRODUCT_STAR_RATING_KEY = "ProductStarRatingKey";
    public static String PRODUCT_UPLOADED_IMAGE_NAME_KEY = "ProductImageNameKey";
    public static String PRODUCT_UPLOADED_VIDEO_ID_KEY = "ProductVideoIdKey";
    protected ProductReviewActivity mActivity;
    protected ProductReviewFragment mFragment;
    protected int mPosition;
    private NestedScrollView mScroller;

    public boolean canPullToRefresh() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract int getContentLayoutResourceId();

    public int getLoadingContentLayoutResourceId() {
        return R.layout.product_review_base_pager_view;
    }

    /* access modifiers changed from: protected */
    public abstract String getPageTitle();

    /* access modifiers changed from: protected */
    public abstract Bundle getStateBundle();

    public abstract void handlePageSelected();

    public void handleReload() {
    }

    public boolean hasItems() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract void initializeContent(View view);

    /* access modifiers changed from: protected */
    public abstract void onNextButtonClicked();

    public void refreshWishStates(boolean z) {
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public ProductReviewPagerView(ProductReviewActivity productReviewActivity, ProductReviewFragment productReviewFragment, int i) {
        super(productReviewActivity);
        this.mActivity = productReviewActivity;
        this.mFragment = productReviewFragment;
        this.mPosition = i;
    }

    public void initializeLoadingContentView(View view) {
        this.mScroller = (NestedScrollView) findViewById(R.id.product_review_base_pager_scroller);
        initializeContent(this.mActivity.getLayoutInflater().inflate(getContentLayoutResourceId(), this.mScroller));
    }

    public void cleanup() {
        releaseImages();
    }

    public void setButtonState(boolean z, boolean z2) {
        this.mFragment.setButtonState(z, z2);
    }
}
