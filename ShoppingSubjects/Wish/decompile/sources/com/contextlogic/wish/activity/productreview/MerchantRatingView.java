package com.contextlogic.wish.activity.productreview;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.starrating.AppStarRatingView;
import com.contextlogic.wish.ui.starrating.StarRatingView.Callback;
import com.contextlogic.wish.ui.starrating.StarRatingView.Size;
import com.contextlogic.wish.ui.text.ThemedEditText;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.ViewUtil;

public class MerchantRatingView extends ProductReviewPagerView implements LoadingPageManager, Callback {
    private boolean keyboardHidden = true;
    private ThemedEditText mCommentView;
    private ThemedTextView mMerchantRatingQuestion;
    private ProductReviewSectionTitle mPageTitle;
    private AppStarRatingView mStarRatingView;

    public int getContentLayoutResourceId() {
        return R.layout.product_review_merchant_rating_view;
    }

    public MerchantRatingView(ProductReviewActivity productReviewActivity, ProductReviewFragment productReviewFragment, int i) {
        super(productReviewActivity, productReviewFragment, i);
        setLoadingPageManager(this);
    }

    public void initializeContent(View view) {
        this.mPageTitle = (ProductReviewSectionTitle) view.findViewById(R.id.merchant_rating_page_title);
        this.mPageTitle.setup(getPageTitle());
        this.mMerchantRatingQuestion = (ThemedTextView) view.findViewById(R.id.merchant_rating_question);
        this.mStarRatingView = (AppStarRatingView) view.findViewById(R.id.merchant_rating_star_rating_view);
        this.mCommentView = (ThemedEditText) view.findViewById(R.id.merchant_rating_comment_view);
        this.mStarRatingView.setup(0, Size.LARGE, this);
        this.mMerchantRatingQuestion.setText(getStoreQuestionString());
        markLoadingComplete();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i4 != 0 && this.mFragment.getCurrentItem() == this.mPosition) {
            if (this.keyboardHidden && i2 < i4) {
                this.mFragment.hideImageContainer(true);
                this.keyboardHidden = false;
            } else if (!this.keyboardHidden && i2 > i4) {
                this.keyboardHidden = true;
            }
        }
    }

    public void onRatingClick(double d) {
        if (d > 0.0d) {
            setButtonState(true, true);
        }
    }

    private SpannableString getStoreQuestionString() {
        String merchantDisplayName = this.mFragment.getSelectedItem().getMerchantDisplayName();
        if (merchantDisplayName == null) {
            return new SpannableString(this.mActivity.getString(R.string.how_was_the_store_question));
        }
        String string = this.mActivity.getString(R.string.how_was_the_store, new Object[]{merchantDisplayName});
        SpannableString spannableString = new SpannableString(string);
        int indexOf = string.indexOf(merchantDisplayName);
        spannableString.setSpan(new StyleSpan(1), indexOf, merchantDisplayName.length() + indexOf, 33);
        return spannableString;
    }

    /* access modifiers changed from: protected */
    public String getPageTitle() {
        return WishApplication.getInstance().getString(R.string.complete_for_additional_points);
    }

    /* access modifiers changed from: protected */
    public Bundle getStateBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(MERCHANT_STAR_RATING_KEY, this.mStarRatingView.getSelection());
        bundle.putString(MERCHANT_RATING_COMMENT_KEY, ViewUtil.extractEditTextValue(this.mCommentView));
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onNextButtonClicked() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRODUCT_REVIEW_MERCHANT_RATING_NEXT_BUTTON);
        this.mFragment.updateRatingInfo(getStateBundle());
        submitRating();
    }

    public void initButtonState() {
        if (this.mStarRatingView.getSelection() != 0) {
            setButtonState(true, true);
        } else {
            setButtonState(true, false);
        }
    }

    private void submitRating() {
        this.mFragment.goToNextPager();
    }

    public void handlePageSelected() {
        this.keyboardHidden = true;
        initButtonState();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PRODUCT_REVIEW_MERCHANT_RATING_TAB);
    }
}
