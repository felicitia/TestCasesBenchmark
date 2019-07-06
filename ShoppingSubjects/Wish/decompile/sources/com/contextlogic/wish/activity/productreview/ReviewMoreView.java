package com.contextlogic.wish.activity.productreview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProductReviewItem;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import java.util.ArrayList;

public class ReviewMoreView extends ProductReviewPagerView implements LoadingPageManager {
    private ReviewMoreItemsAdapter mAdapter;
    private ImageHttpPrefetcher mImagePrefetcher;
    private LinearLayoutManager mItemsLayoutManager;
    private RecyclerView mItemsRecyclerView;
    private LinearLayout mMainContainer;
    private ProductReviewSectionTitle mPageTitle;

    public int getContentLayoutResourceId() {
        return R.layout.product_review_review_more_view;
    }

    /* access modifiers changed from: protected */
    public void onNextButtonClicked() {
    }

    public ReviewMoreView(ProductReviewActivity productReviewActivity, ProductReviewFragment productReviewFragment, int i, ImageHttpPrefetcher imageHttpPrefetcher) {
        super(productReviewActivity, productReviewFragment, i);
        this.mImagePrefetcher = imageHttpPrefetcher;
        this.mItemsLayoutManager = new LinearLayoutManager(productReviewActivity);
        this.mAdapter = new ReviewMoreItemsAdapter(productReviewActivity, productReviewFragment);
        setLoadingPageManager(this);
    }

    public void initializeContent(View view) {
        this.mMainContainer = (LinearLayout) view.findViewById(R.id.review_more_main_container);
        this.mPageTitle = (ProductReviewSectionTitle) view.findViewById(R.id.review_more_page_title);
        this.mItemsRecyclerView = (RecyclerView) view.findViewById(R.id.review_more_recycler_view);
        this.mAdapter.setImagePrefetcher(this.mImagePrefetcher);
        this.mItemsRecyclerView.setAdapter(this.mAdapter);
        this.mItemsLayoutManager.setAutoMeasureEnabled(true);
        this.mItemsRecyclerView.setLayoutManager(this.mItemsLayoutManager);
        this.mItemsRecyclerView.setNestedScrollingEnabled(false);
    }

    /* access modifiers changed from: protected */
    public String getPageTitle() {
        return WishApplication.getInstance().getString(R.string.share_another_review);
    }

    /* access modifiers changed from: protected */
    public Bundle getStateBundle() {
        return new Bundle();
    }

    public void handleLoadingSuccess(ArrayList<WishProductReviewItem> arrayList, int i) {
        this.mPageTitle.setup(getPageTitle());
        ReviewMoreHeader reviewMoreHeader = new ReviewMoreHeader(this.mActivity);
        reviewMoreHeader.setup(i, this.mFragment.getThumbnailImage(), this.mActivity.getMediaInfo().getType(), this.mFragment.getSelectedItem(), arrayList.isEmpty());
        this.mAdapter.setItems(arrayList);
        if (arrayList.size() == 0) {
            reviewMoreHeader.setLayoutParams(new LayoutParams(-1, -1));
            this.mFragment.hideImageContainer(false);
            this.mPageTitle.setVisibility(8);
            this.mItemsRecyclerView.setVisibility(8);
            this.mMainContainer.addView(reviewMoreHeader, 0);
            this.mFragment.resetHeader(null);
        } else {
            this.mFragment.showImageContainer(false);
            this.mFragment.resetHeader(reviewMoreHeader);
        }
        markLoadingComplete();
    }

    public void handleLoadingFailure(String str) {
        if (str == null) {
            str = WishApplication.getInstance().getString(R.string.review_submission_failure);
        }
        this.mActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
        markLoadingErrored();
        setErrorMessage(WishApplication.getInstance().getString(R.string.review_submission_failure));
    }

    public void initButtonState() {
        setButtonState(false, false);
    }

    public void handlePageSelected() {
        initButtonState();
        this.mFragment.hideImageContainer(false);
        this.mFragment.uploadRating();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PRODUCT_REVIEW_REVIEW_MORE_TAB);
    }

    public boolean hasItems() {
        return isLoadingComplete();
    }

    public void releaseImages() {
        for (int i = 0; i < this.mItemsRecyclerView.getChildCount(); i++) {
            ((ItemRowHolder) this.mItemsRecyclerView.getChildViewHolder(this.mItemsRecyclerView.getChildAt(i))).releaseImages();
        }
    }

    public void restoreImages() {
        for (int i = 0; i < this.mItemsRecyclerView.getChildCount(); i++) {
            ((ItemRowHolder) this.mItemsRecyclerView.getChildViewHolder(this.mItemsRecyclerView.getChildAt(i))).restoreImages();
        }
    }

    public void handleReload() {
        this.mFragment.uploadRating();
    }
}
