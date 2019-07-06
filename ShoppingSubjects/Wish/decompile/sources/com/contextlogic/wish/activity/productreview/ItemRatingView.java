package com.contextlogic.wish.activity.productreview;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.productreview.ProductReviewSizeSelectorView.SizeChoice;
import com.contextlogic.wish.activity.productreview.ProductReviewSizeSelectorView.SizeSelectionListener;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.starrating.AppStarRatingView;
import com.contextlogic.wish.ui.starrating.StarRatingView.Callback;
import com.contextlogic.wish.ui.starrating.StarRatingView.Size;

public class ItemRatingView extends ProductReviewPagerView implements LoadingPageManager, Callback {
    private ProductReviewSectionTitle mPageTitle;
    private ProductReviewSizeSelectorView mSizeSelectorView;
    private TextView mSizingQuestionTextView;
    /* access modifiers changed from: private */
    public AppStarRatingView mStarRatingView;

    public int getContentLayoutResourceId() {
        return R.layout.product_review_item_rating_view;
    }

    public ItemRatingView(ProductReviewActivity productReviewActivity, ProductReviewFragment productReviewFragment, int i) {
        super(productReviewActivity, productReviewFragment, i);
        setLoadingPageManager(this);
    }

    public void initializeContent(View view) {
        this.mPageTitle = (ProductReviewSectionTitle) view.findViewById(R.id.item_rating_page_title);
        this.mPageTitle.setup(getPageTitle());
        this.mStarRatingView = (AppStarRatingView) view.findViewById(R.id.item_rating_star_rating_view);
        this.mStarRatingView.setup(0, Size.LARGE, this);
        this.mSizeSelectorView = (ProductReviewSizeSelectorView) view.findViewById(R.id.item_rating_size_selector_view);
        this.mSizeSelectorView.setup(new SizeSelectionListener() {
            public void onSelection(SizeChoice sizeChoice) {
                if (ItemRatingView.this.mStarRatingView.getSelection() != 0 && sizeChoice.getChoice() != 0) {
                    ItemRatingView.this.setButtonState(true, true);
                }
            }
        });
        this.mSizingQuestionTextView = (TextView) view.findViewById(R.id.item_rating_sizing_question_text);
        markLoadingComplete();
    }

    public void onRatingClick(double d) {
        if (d <= 0.0d) {
            return;
        }
        if (!shouldRateSize() || this.mSizeSelectorView.getSelection().getChoice() != 0) {
            setButtonState(true, true);
        }
    }

    /* access modifiers changed from: protected */
    public String getPageTitle() {
        return getContext().getString(R.string.complete_for_additional_points);
    }

    /* access modifiers changed from: protected */
    public Bundle getStateBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(PRODUCT_STAR_RATING_KEY, this.mStarRatingView.getSelection());
        bundle.putInt(PRODUCT_SIZE_CHOICE_KEY, this.mSizeSelectorView.getSelection().getChoice());
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onNextButtonClicked() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRODUCT_REVIEW_ITEM_RATING_NEXT_BUTTON);
        this.mFragment.updateRatingInfo(getStateBundle());
        this.mFragment.goToNextPager();
    }

    private boolean shouldRateSize() {
        return (this.mFragment.getSelectedItem() == null || this.mFragment.getSelectedItem().getSize() == null) ? false : true;
    }

    public void handlePageSelected() {
        initButtonState();
        this.mFragment.showImageContainer(false);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PRODUCT_REVIEW_ITEM_RATING_TAB);
        if (!shouldRateSize()) {
            this.mSizingQuestionTextView.setVisibility(8);
            this.mSizeSelectorView.setVisibility(8);
        }
    }

    public void initButtonState() {
        if (this.mStarRatingView.getSelection() == 0) {
            setButtonState(true, false);
        } else if (!shouldRateSize() || this.mSizeSelectorView.getSelection() != SizeChoice.DEFAULT) {
            setButtonState(true, true);
        } else {
            setButtonState(true, false);
        }
    }
}
