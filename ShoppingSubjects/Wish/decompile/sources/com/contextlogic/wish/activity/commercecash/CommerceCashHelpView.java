package com.contextlogic.wish.activity.commercecash;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCommerceCashHelpInfo;
import com.contextlogic.wish.api.model.WishCommerceCashHelpInfo.HelpQuestion;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.scrollview.ObservableScrollView;
import com.contextlogic.wish.ui.scrollview.ObservableScrollView.ScrollViewListener;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.Iterator;

public class CommerceCashHelpView extends CommerceCashPagerView {
    private CommerceCashFragment mFragment;
    private WishCommerceCashHelpInfo mHelpInfo;
    private LinearLayout mHelpInfoContainer;
    private LinearLayout mHelpInfoItems;
    private ThemedTextView mHelpInfoMessage;
    private ThemedTextView mHelpInfoTitle;
    private ObservableScrollView mScroller;
    private LinearLayout mStoreLogoContainer;
    private ThemedTextView mTermsAndConditionsText;

    public int getLoadingContentLayoutResourceId() {
        return R.layout.commerce_cash_fragment_help;
    }

    public CommerceCashHelpView(Context context) {
        super(context);
    }

    public void setup(int i, CommerceCashFragment commerceCashFragment) {
        super.setup(i, commerceCashFragment);
        this.mFragment = commerceCashFragment;
        this.mScroller = (ObservableScrollView) findViewById(R.id.commerce_cash_fragment_information_scroller);
        this.mScroller.setScrollViewListener(new ScrollViewListener() {
            public void onScrollChanged(int i, int i2) {
                CommerceCashHelpView.this.handleScrollChanged(i, i2);
            }
        });
        setupScroller(this.mScroller);
        this.mHelpInfoContainer = (LinearLayout) findViewById(R.id.commerce_cash_fragment_information_container);
        this.mHelpInfoTitle = (ThemedTextView) findViewById(R.id.commerce_cash_fragment_information_title);
        this.mHelpInfoMessage = (ThemedTextView) findViewById(R.id.commerce_cash_fragment_information_message);
        this.mTermsAndConditionsText = (ThemedTextView) findViewById(R.id.commerce_cash_fragment_terms_and_conditions);
        this.mStoreLogoContainer = (LinearLayout) findViewById(R.id.commerce_cash_fragment_store_logo_container);
        this.mHelpInfoItems = (LinearLayout) findViewById(R.id.commerce_cash_fragment_information_items);
        this.mHelpInfoContainer.setPadding(this.mHelpInfoContainer.getPaddingLeft(), this.mFragment.getTabAreaSize(), this.mHelpInfoContainer.getPaddingRight(), this.mHelpInfoContainer.getPaddingBottom());
    }

    public void handleReload() {
        super.handleReload();
        this.mHelpInfo = null;
        this.mFragment.loadPages();
    }

    public void handleLoadingSuccess(WishCommerceCashHelpInfo wishCommerceCashHelpInfo) {
        this.mHelpInfo = wishCommerceCashHelpInfo;
        if (wishCommerceCashHelpInfo != null) {
            if (!wishCommerceCashHelpInfo.shouldHideTitle()) {
                this.mHelpInfoTitle.setText(wishCommerceCashHelpInfo.getTitle());
                this.mHelpInfoTitle.setVisibility(0);
            } else {
                this.mHelpInfoTitle.setVisibility(8);
            }
            this.mHelpInfoMessage.setText(wishCommerceCashHelpInfo.getMessage());
            if (!wishCommerceCashHelpInfo.shouldHideTermsAndConditions()) {
                this.mTermsAndConditionsText.setText(R.string.terms_and_conditions);
                this.mTermsAndConditionsText.setVisibility(0);
                this.mTermsAndConditionsText.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        CommerceCashHelpView.this.showCommerceCashTerms();
                    }
                });
            } else {
                this.mTermsAndConditionsText.setVisibility(8);
            }
            if (ExperimentDataCenter.getInstance().shouldSeePayNearMe()) {
                this.mStoreLogoContainer.setVisibility(0);
            }
            this.mHelpInfoItems.removeAllViews();
            Iterator it = this.mHelpInfo.getHelpQuestions().iterator();
            while (it.hasNext()) {
                addParagraph((HelpQuestion) it.next());
            }
            markLoadingComplete();
        }
    }

    /* access modifiers changed from: private */
    public void showCommerceCashTerms() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_COMMERCE_CASH_TERMS_HELP_VIEW);
        this.mFragment.withActivity(new ActivityTask<CommerceCashActivity>() {
            public void performTask(CommerceCashActivity commerceCashActivity) {
                Intent intent = new Intent();
                intent.setClass(commerceCashActivity, CommerceCashTermsActivity.class);
                commerceCashActivity.startActivity(intent);
            }
        });
    }

    public void handleLoadingFailure() {
        markLoadingErrored();
    }

    public void addParagraph(HelpQuestion helpQuestion) {
        if (helpQuestion != null) {
            Resources resources = WishApplication.getInstance().getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.commerce_cash_help_question_padding);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.eight_padding);
            ThemedTextView themedTextView = new ThemedTextView(getContext());
            themedTextView.setPadding(0, dimensionPixelSize, 0, 0);
            themedTextView.setText(helpQuestion.getQuestion());
            themedTextView.setTextSize(0, (float) resources.getDimensionPixelSize(R.dimen.text_size_subtitle));
            themedTextView.setTypeface(1);
            themedTextView.setTextColor(resources.getColor(R.color.text_primary));
            ThemedTextView themedTextView2 = new ThemedTextView(getContext());
            themedTextView2.setPadding(0, dimensionPixelSize2, 0, 0);
            themedTextView2.setText(helpQuestion.getAnswer());
            themedTextView2.setTextSize(0, (float) resources.getDimensionPixelSize(R.dimen.text_size_body));
            themedTextView2.setTypeface(0);
            themedTextView2.setTextColor(resources.getColor(R.color.text_primary));
            this.mHelpInfoItems.addView(themedTextView);
            this.mHelpInfoItems.addView(themedTextView2);
        }
    }

    /* access modifiers changed from: protected */
    public void setupScroller(View view) {
        this.mPagerHelper.setupScroller(view);
    }

    public boolean hasItems() {
        return isLoadingComplete();
    }

    public void scrollPage(int i) {
        this.mScroller.scrollBy(0, -i);
    }

    public int getCurrentScrollY() {
        return this.mScroller.getScrollY();
    }
}
