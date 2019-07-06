package com.contextlogic.wish.activity.productreview;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.text.ThemedEditText;
import com.contextlogic.wish.util.ViewUtil;

public class RatingDescriptionView extends ProductReviewPagerView implements LoadingPageManager {
    /* access modifiers changed from: private */
    public static int COMMENT_MAX_LENGTH = 100;
    /* access modifiers changed from: private */
    public static int COMMENT_MIN_LENGTH = 1;
    private boolean keyboardHidden = true;
    /* access modifiers changed from: private */
    public TextView mDescriptionCountView;
    private ThemedEditText mDescriptionView;
    private ProductReviewSectionTitle mPageTitle;

    public int getContentLayoutResourceId() {
        return R.layout.product_review_rating_description_view;
    }

    public RatingDescriptionView(ProductReviewActivity productReviewActivity, ProductReviewFragment productReviewFragment, int i) {
        super(productReviewActivity, productReviewFragment, i);
        setLoadingPageManager(this);
    }

    public void initializeContent(View view) {
        this.mPageTitle = (ProductReviewSectionTitle) view.findViewById(R.id.rating_description_page_title);
        this.mPageTitle.setup(getPageTitle());
        this.mDescriptionView = (ThemedEditText) view.findViewById(R.id.rating_description_view_description);
        this.mDescriptionCountView = (TextView) view.findViewById(R.id.rating_description_view_description_count);
        this.mDescriptionView.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                int length = editable.length();
                TextView access$100 = RatingDescriptionView.this.mDescriptionCountView;
                StringBuilder sb = new StringBuilder();
                sb.append(length);
                sb.append("/");
                sb.append(RatingDescriptionView.COMMENT_MAX_LENGTH);
                access$100.setText(sb.toString());
                if (length < RatingDescriptionView.COMMENT_MIN_LENGTH) {
                    RatingDescriptionView.this.setButtonState(true, false);
                } else {
                    RatingDescriptionView.this.setButtonState(true, true);
                }
            }
        });
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
                this.mFragment.showImageContainer(true);
                this.keyboardHidden = true;
            }
        }
    }

    public void initButtonState() {
        if (this.mDescriptionView.getText().length() < COMMENT_MIN_LENGTH) {
            setButtonState(true, false);
        } else {
            setButtonState(true, true);
        }
    }

    /* access modifiers changed from: protected */
    public String getPageTitle() {
        return WishApplication.getInstance().getString(R.string.complete_for_additional_points);
    }

    /* access modifiers changed from: protected */
    public Bundle getStateBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(PRODUCT_RATING_COMMENT_KEY, ViewUtil.extractEditTextValue(this.mDescriptionView));
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onNextButtonClicked() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_PRODUCT_REVIEW_RATING_DESCRIPTION_NEXT_BUTTON);
        this.mFragment.updateRatingInfo(getStateBundle());
        this.mFragment.goToNextPager();
    }

    public void handlePageSelected() {
        this.keyboardHidden = true;
        initButtonState();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_PRODUCT_REVIEW_RATING_DESCRIPTION_TAB);
    }
}
