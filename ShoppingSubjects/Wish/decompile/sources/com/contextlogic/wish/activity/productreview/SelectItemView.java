package com.contextlogic.wish.activity.productreview;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.productreview.ProductReviewPagerAdapter.ProductReviewSection;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProductReviewItem;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.grid.StaggeredGridView;
import com.contextlogic.wish.ui.grid.StaggeredGridView.OnItemClickListener;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import java.util.ArrayList;

public class SelectItemView extends ProductReviewPagerView implements LoadingPageManager {
    private ProductReviewItemsAdapter mAdapter;
    private ImageHttpPrefetcher mImagePrefetcher;
    /* access modifiers changed from: private */
    public ArrayList<WishProductReviewItem> mItems;
    private StaggeredGridView mItemsView;
    private ProductReviewSectionTitle mPageTitle;
    /* access modifiers changed from: private */
    public int mSelectedItemPosition = -1;
    /* access modifiers changed from: private */
    public ProductReviewItemTileView mSelectedItemView;

    public int getContentLayoutResourceId() {
        return R.layout.product_review_select_item_view;
    }

    public SelectItemView(ProductReviewActivity productReviewActivity, ProductReviewFragment productReviewFragment, int i, ImageHttpPrefetcher imageHttpPrefetcher) {
        super(productReviewActivity, productReviewFragment, i);
        this.mImagePrefetcher = imageHttpPrefetcher;
        this.mAdapter = new ProductReviewItemsAdapter(productReviewActivity, productReviewFragment);
        setLoadingPageManager(this);
    }

    public void initializeContent(View view) {
        this.mItemsView = (StaggeredGridView) view.findViewById(R.id.product_review_select_item_gridview);
        this.mAdapter.setImagePrefetcher(this.mImagePrefetcher);
        this.mItemsView.setAdapter(this.mAdapter);
        setupHeader();
        this.mItemsView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(int i, View view) {
                ProductReviewItemTileView productReviewItemTileView = (ProductReviewItemTileView) view;
                if (SelectItemView.this.mItems.get(i) != null && productReviewItemTileView != null) {
                    if (SelectItemView.this.mSelectedItemPosition != i) {
                        if (SelectItemView.this.mSelectedItemView != null) {
                            SelectItemView.this.mSelectedItemView.setSelected(false);
                        }
                        SelectItemView.this.mSelectedItemView = productReviewItemTileView;
                        SelectItemView.this.mSelectedItemPosition = i;
                        productReviewItemTileView.setSelected(true);
                        SelectItemView.this.setButtonState(true, true);
                    } else if (productReviewItemTileView.isSelected()) {
                        SelectItemView.this.mSelectedItemPosition = -1;
                        SelectItemView.this.mSelectedItemView = null;
                        SelectItemView.this.setButtonState(true, false);
                        productReviewItemTileView.setSelected(false);
                    } else {
                        SelectItemView.this.setButtonState(true, true);
                        productReviewItemTileView.setSelected(true);
                    }
                }
            }
        });
    }

    public boolean hasItems() {
        return isLoadingComplete();
    }

    /* access modifiers changed from: protected */
    public void onNextButtonClicked() {
        WishProductReviewItem wishProductReviewItem = (WishProductReviewItem) this.mItems.get(this.mSelectedItemPosition);
        this.mFragment.updateRatingInfo(getStateBundle());
        if (wishProductReviewItem.hasRating()) {
            submitRating();
            return;
        }
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRODUCT_REVIEW_SELECT_ITEM_NEXT_BUTTON);
        this.mFragment.goToNextPager();
    }

    private void submitRating() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRODUCT_REVIEW_SELECT_ITEM_SUBMIT_RATING);
        this.mFragment.scrollTo(ProductReviewSection.REVIEW_MORE.ordinal(), false);
    }

    public Bundle getStateBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PRODUCT_KEY, (Parcelable) this.mItems.get(this.mSelectedItemPosition));
        return bundle;
    }

    public void initButtonState() {
        if (this.mSelectedItemPosition == -1) {
            setButtonState(false, false);
        } else {
            setButtonState(true, true);
        }
    }

    public void handleLoadingSuccess(ArrayList<WishProductReviewItem> arrayList) {
        this.mItems = arrayList;
        if (this.mItems.size() == 0) {
            markLoadingErrored();
            setErrorMessage(this.mActivity.getResources().getString(R.string.no_items_to_review), false);
            return;
        }
        setupHeader();
        this.mAdapter.setItems(arrayList);
        this.mItemsView.notifyDataSetChanged();
        markLoadingComplete();
    }

    public void handleLoadingFailure(String str) {
        if (str == null) {
            str = WishApplication.getInstance().getString(R.string.product_review_error);
        }
        markLoadingErrored();
        setErrorMessage(str);
    }

    public void releaseImages() {
        if (this.mItemsView != null) {
            this.mItemsView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mItemsView != null) {
            this.mItemsView.restoreImages();
        }
    }

    private void setupHeader() {
        this.mPageTitle = new ProductReviewSectionTitle(this.mActivity);
        this.mPageTitle.setup(getPageTitle());
        this.mItemsView.setHeaderView(this.mPageTitle);
    }

    /* access modifiers changed from: protected */
    public String getPageTitle() {
        if (this.mActivity.getMediaInfo().getType() == 0) {
            return WishApplication.getInstance().getString(R.string.which_item_is_your_photo);
        }
        return WishApplication.getInstance().getString(R.string.which_item_is_your_video);
    }

    public void handlePageSelected() {
        initButtonState();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PRODUCT_REVIEW_SELECT_ITEM_TAB);
    }

    public void handleReload() {
        this.mItems = null;
        this.mFragment.loadProducts();
    }
}
