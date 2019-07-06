package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class DescriptionCollapsableSection extends CollapsableSection {
    /* access modifiers changed from: private */
    public boolean mDescriptionExpanded;
    /* access modifiers changed from: private */
    public boolean mFullyExpandedByDefault;

    public DescriptionCollapsableSection(Context context) {
        this(context, null);
    }

    public DescriptionCollapsableSection(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void init(WishProduct wishProduct) {
        if (wishProduct != null) {
            final View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.product_details_description, null);
            setTitle(getContext().getString(R.string.product_details_main_tab_description));
            addContent(inflate);
            final ThemedTextView themedTextView = (ThemedTextView) inflate.findViewById(R.id.product_details_description);
            themedTextView.setText(wishProduct.getDescription());
            final ThemedTextView themedTextView2 = (ThemedTextView) inflate.findViewById(R.id.product_details_description_more);
            openSection();
            inflate.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    inflate.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    if (themedTextView.getLineCount() <= 8) {
                        themedTextView.setMaxLines(8);
                        themedTextView2.setVisibility(4);
                    } else {
                        themedTextView.setMaxLines(8);
                        themedTextView2.setText(WishApplication.getInstance().getString(R.string.more));
                        DescriptionCollapsableSection.this.mDescriptionExpanded = false;
                        themedTextView2.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                if (DescriptionCollapsableSection.this.mDescriptionExpanded) {
                                    themedTextView.setMaxLines(8);
                                    themedTextView2.setText(WishApplication.getInstance().getString(R.string.more));
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PRODUCT_DETAILS_ITEM_DESCRIPTION_LESS);
                                } else {
                                    themedTextView.setMaxLines(Integer.MAX_VALUE);
                                    themedTextView2.setText(WishApplication.getInstance().getString(R.string.less));
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PRODUCT_DETAILS_ITEM_DESCRIPTION_MORE);
                                }
                                DescriptionCollapsableSection.this.mDescriptionExpanded = !DescriptionCollapsableSection.this.mDescriptionExpanded;
                            }
                        });
                    }
                    DescriptionCollapsableSection.this.measure(-1, -2);
                    DescriptionCollapsableSection.this.setTargetHeight(DescriptionCollapsableSection.this.getMeasuredHeight());
                    if (DescriptionCollapsableSection.this.mFullyExpandedByDefault) {
                        themedTextView2.performClick();
                    } else {
                        DescriptionCollapsableSection.this.closeSection();
                    }
                }
            });
            this.mOpenClickSectionEvent = WishAnalyticsEvent.CLICK_MOBILE_PRODUCT_DETAILS_ITEM_DESCRIPTION_OPEN;
            this.mCloseClickSectionEvent = WishAnalyticsEvent.CLICK_MOBILE_PRODUCT_DETAILS_ITEM_DESCRIPTION_CLOSE;
            return;
        }
        setVisibility(8);
    }
}
