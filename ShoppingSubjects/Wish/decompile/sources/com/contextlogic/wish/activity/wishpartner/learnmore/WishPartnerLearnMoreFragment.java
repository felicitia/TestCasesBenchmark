package com.contextlogic.wish.activity.wishpartner.learnmore;

import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishPartnerLearnMoreSpec;
import com.contextlogic.wish.api.model.WishPartnerLearnMoreSpec.WishPartnerLearnMoreFAQItem;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.List;

public class WishPartnerLearnMoreFragment extends UiFragment<WishPartnerLearnMoreActivity> {
    private ThemedTextView mApplyPromoButtonExample;
    private ThemedTextView mBodyText;
    private View mContent;
    private ThemedTextView mExampleButton;
    private View mExampleLinkContainer;
    private ThemedTextView mExampleLinkText;
    private LinearLayout mFAQContainer;
    private ThemedTextView mInstructionsHeader;
    /* access modifiers changed from: private */
    public View mProgressView;
    private ThemedTextView mStepOneText;
    private ThemedTextView mStepThreeText;
    private ThemedTextView mStepTwoText;
    private ThemedTextView mTitleText;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.wish_partner_learn_more_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mProgressView = findViewById(R.id.wish_partner_learn_more_progress);
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            View findViewById = this.mProgressView.findViewById(R.id.wish_partner_learn_more_three_dot_progress);
            View findViewById2 = this.mProgressView.findViewById(R.id.wish_partner_learn_more_primary_progress);
            if (!(findViewById2 == null || findViewById == null)) {
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
            }
        }
        this.mTitleText = (ThemedTextView) findViewById(R.id.wish_partner_learn_more_steps_title);
        this.mBodyText = (ThemedTextView) findViewById(R.id.wish_partner_learn_more_steps_body);
        this.mInstructionsHeader = (ThemedTextView) findViewById(R.id.wish_partner_learn_more_instructions_header);
        this.mStepOneText = (ThemedTextView) findViewById(R.id.wish_partner_learn_more_step_1);
        this.mStepTwoText = (ThemedTextView) findViewById(R.id.wish_partner_learn_more_step_2);
        this.mStepThreeText = (ThemedTextView) findViewById(R.id.wish_partner_learn_more_step_3);
        this.mExampleButton = (ThemedTextView) findViewById(R.id.wish_partner_learn_more_button_example);
        this.mExampleLinkContainer = findViewById(R.id.wish_partner_learn_more_example_link_container);
        this.mExampleLinkText = (ThemedTextView) findViewById(R.id.wish_partner_learn_more_example_link_text);
        this.mContent = findViewById(R.id.wish_partner_learn_more_content);
        this.mApplyPromoButtonExample = (ThemedTextView) findViewById(R.id.wish_partner_learn_more_menu_apply_promo_button);
        this.mFAQContainer = (LinearLayout) findViewById(R.id.wish_partner_learn_more_extra_text_container);
        showProgressSpinner();
        withServiceFragment(new ServiceTask<BaseActivity, WishPartnerLearnMoreServiceFragment>() {
            public void performTask(BaseActivity baseActivity, WishPartnerLearnMoreServiceFragment wishPartnerLearnMoreServiceFragment) {
                wishPartnerLearnMoreServiceFragment.loadLearnMoreInfo();
            }
        });
    }

    public void updateActionBarTitle(final String str) {
        withActivity(new ActivityTask<WishPartnerLearnMoreActivity>() {
            public void performTask(WishPartnerLearnMoreActivity wishPartnerLearnMoreActivity) {
                wishPartnerLearnMoreActivity.getActionBarManager().setTitle(str);
            }
        });
    }

    public void showProgressSpinner() {
        this.mProgressView.setVisibility(0);
        this.mContent.setVisibility(8);
    }

    public void hideProgressSpinner() {
        this.mProgressView.setVisibility(8);
        this.mContent.setVisibility(0);
    }

    public void handleLoadingInfoFailure() {
        getHandler().post(new Runnable() {
            public void run() {
                WishPartnerLearnMoreFragment.this.mProgressView.setVisibility(8);
            }
        });
    }

    public void handleLoadingInfoSuccess(final WishPartnerLearnMoreSpec wishPartnerLearnMoreSpec) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_WISH_PARTNER_LEARN_MORE_PAGE);
        this.mTitleText.setText(wishPartnerLearnMoreSpec.getTitle());
        this.mBodyText.setText(wishPartnerLearnMoreSpec.getSubtitle());
        this.mInstructionsHeader.setText(wishPartnerLearnMoreSpec.getInstructionsHeader());
        this.mStepOneText.setText(wishPartnerLearnMoreSpec.getStepOneText());
        this.mStepTwoText.setText(wishPartnerLearnMoreSpec.getStepTwoText());
        this.mStepThreeText.setText(wishPartnerLearnMoreSpec.getStepThreeText());
        this.mExampleButton.setText(wishPartnerLearnMoreSpec.getGenerateButtonText());
        this.mApplyPromoButtonExample.setText(wishPartnerLearnMoreSpec.getMenuItemLable());
        updateActionBarTitle(wishPartnerLearnMoreSpec.getScreenTitle());
        if (wishPartnerLearnMoreSpec.getExampleLink() == null || wishPartnerLearnMoreSpec.getExampleText() == null) {
            this.mExampleLinkContainer.setVisibility(8);
        } else {
            this.mExampleLinkContainer.setVisibility(0);
            this.mExampleLinkText.setText(wishPartnerLearnMoreSpec.getExampleText());
            this.mExampleLinkContainer.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishPartnerLearnMoreFragment.this.withActivity(new ActivityTask<WishPartnerLearnMoreActivity>() {
                        public void performTask(WishPartnerLearnMoreActivity wishPartnerLearnMoreActivity) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_LEARN_MORE_EXAMPLE_LINK);
                            String exampleLink = wishPartnerLearnMoreSpec.getExampleLink();
                            if (exampleLink != null) {
                                DeepLinkManager.processDeepLink(wishPartnerLearnMoreActivity, new DeepLink(exampleLink));
                            }
                        }
                    });
                }
            });
        }
        if (wishPartnerLearnMoreSpec.getFAQItems() != null && wishPartnerLearnMoreSpec.getFAQItems().size() > 0) {
            setUpFAQ(wishPartnerLearnMoreSpec.getFAQItems());
        }
        hideProgressSpinner();
    }

    private void setUpFAQ(List<WishPartnerLearnMoreFAQItem> list) {
        for (WishPartnerLearnMoreFAQItem wishPartnerLearnMoreFAQItem : list) {
            ThemedTextView themedTextView = new ThemedTextView(getContext());
            themedTextView.setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.text_size_subtitle));
            themedTextView.setTypeface(Typeface.DEFAULT_BOLD);
            themedTextView.setTextColor(getResources().getColor(R.color.gray1));
            themedTextView.setText(wishPartnerLearnMoreFAQItem.getQuestion());
            themedTextView.setPadding(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.eight_padding));
            this.mFAQContainer.addView(themedTextView);
            ThemedTextView themedTextView2 = new ThemedTextView(getContext());
            themedTextView2.setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.text_size_body));
            themedTextView2.setTextColor(getResources().getColor(R.color.gray2));
            themedTextView2.setText(wishPartnerLearnMoreFAQItem.getAnswer());
            themedTextView2.setPadding(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.twenty_four_padding));
            this.mFAQContainer.addView(themedTextView2);
        }
    }
}
