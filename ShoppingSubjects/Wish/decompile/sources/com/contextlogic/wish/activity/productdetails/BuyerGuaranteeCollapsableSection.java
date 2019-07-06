package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.buyerguarantee.BuyerGuaranteeActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.BuyerGuaranteeInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class BuyerGuaranteeCollapsableSection extends CollapsableSection {
    private ThemedTextView mLearnMoreButton;
    private ThemedTextView mSubtitle;
    private LinearLayout mTextContainer;

    public BuyerGuaranteeCollapsableSection(Context context) {
        this(context, null);
    }

    public BuyerGuaranteeCollapsableSection(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void init(final BaseFragment baseFragment, BuyerGuaranteeInfo buyerGuaranteeInfo) {
        setTitle(WishApplication.getInstance().getString(R.string.collapsable_section_buyer_guarantee));
        final View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.product_details_buyer_guarantee_collapsable, null);
        addContent(inflate);
        this.mSubtitle = (ThemedTextView) inflate.findViewById(R.id.product_details_buyer_guarantee_subtitle);
        this.mLearnMoreButton = (ThemedTextView) inflate.findViewById(R.id.product_details_buyer_guarantee_more);
        this.mTextContainer = (LinearLayout) inflate.findViewById(R.id.buyer_guarantee_text_container);
        this.mSubtitle.setText(buyerGuaranteeInfo.getSectionSubtitle());
        setBodyText(buyerGuaranteeInfo.getSectionBody());
        this.mLearnMoreButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                baseFragment.withActivity(new ActivityTask<BaseActivity>() {
                    public void performTask(BaseActivity baseActivity) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BUYER_GUARANTEE_LEARN_MORE_COLLAPSABLE);
                        Intent intent = new Intent();
                        intent.setClass(BuyerGuaranteeCollapsableSection.this.getContext(), BuyerGuaranteeActivity.class);
                        baseActivity.startActivity(intent);
                    }
                });
            }
        });
        openSection();
        inflate.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                inflate.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                BuyerGuaranteeCollapsableSection.this.setTargetHeight(BuyerGuaranteeCollapsableSection.this.getHeight());
                BuyerGuaranteeCollapsableSection.this.closeSection();
            }
        });
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_BUYER_GUARANTEE_COLLAPSABLE);
        this.mCloseClickSectionEvent = WishAnalyticsEvent.CLICK_BUYER_GUARANTEE_CLOSE_COLLAPSABLE;
        this.mOpenClickSectionEvent = WishAnalyticsEvent.CLICK_BUYER_GUARANTEE_OPEN_COLLAPSABLE;
    }

    private void setBodyText(String str) {
        String[] split = str.split("\n");
        int i = 0;
        while (i < split.length) {
            ThemedTextView themedTextView = new ThemedTextView(getContext());
            themedTextView.setTextSize(0, (float) getResources().getDimensionPixelOffset(R.dimen.text_size_body));
            themedTextView.setText(split[i]);
            themedTextView.setLineSpacing(0.0f, 1.2f);
            themedTextView.setTextColor(getResources().getColor(R.color.cool_gray1));
            if (i < split.length - 1) {
                LayoutParams layoutParams = new LayoutParams(-2, -2);
                layoutParams.setMargins(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.eight_padding));
                themedTextView.setLayoutParams(layoutParams);
            }
            i++;
            this.mTextContainer.addView(themedTextView, i);
        }
    }
}
