package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
import com.contextlogic.wish.ui.text.ThemedTextView;

public class BuyerGuaranteeView extends LinearLayout {
    private ThemedTextView mLearnMore;
    private View mRootLayout;
    private ThemedTextView mSubtitle;
    private LinearLayout mTextContainer;

    public BuyerGuaranteeView(Context context) {
        this(context, null);
    }

    public BuyerGuaranteeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.mRootLayout = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.product_details_buyer_guarantee_full, this);
        setOrientation(1);
        this.mSubtitle = (ThemedTextView) this.mRootLayout.findViewById(R.id.product_details_buyer_guarantee_subtitle);
        this.mLearnMore = (ThemedTextView) this.mRootLayout.findViewById(R.id.product_details_buyer_guarantee_more);
        this.mTextContainer = (LinearLayout) this.mRootLayout.findViewById(R.id.buyer_guarantee_text_container);
    }

    public void setup(final BaseFragment baseFragment, BuyerGuaranteeInfo buyerGuaranteeInfo) {
        this.mSubtitle.setText(buyerGuaranteeInfo.getSectionSubtitle());
        setBodyText(buyerGuaranteeInfo.getSectionBody());
        setLayoutParams(new LayoutParams(-1, -2));
        this.mLearnMore.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                baseFragment.withActivity(new ActivityTask<BaseActivity>() {
                    public void performTask(BaseActivity baseActivity) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BUYER_GUARANTEE_LEARN_MORE_FULL_VIEW);
                        Intent intent = new Intent();
                        intent.setClass(BuyerGuaranteeView.this.getContext(), BuyerGuaranteeActivity.class);
                        baseActivity.startActivity(intent);
                    }
                });
            }
        });
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_BUYER_GUARANTEE_FULL_VIEW);
    }

    private void setBodyText(String str) {
        String[] split = str.split("\n");
        for (int i = 0; i < split.length; i++) {
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
            this.mTextContainer.addView(themedTextView);
        }
    }
}
