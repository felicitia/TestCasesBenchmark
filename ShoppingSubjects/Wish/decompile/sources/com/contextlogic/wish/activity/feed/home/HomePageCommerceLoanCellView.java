package com.contextlogic.wish.activity.feed.home;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.cart.commerceloan.CommerceLoanCartActivity;
import com.contextlogic.wish.activity.webview.WebViewActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishHomePageInfo;
import com.contextlogic.wish.api.model.WishHomePageInfo.HomePageCommerceLoanItemHolder;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.crashlytics.android.Crashlytics;

public class HomePageCommerceLoanCellView extends LinearLayout {
    private ThemedTextView mDescription;
    private ThemedButton mOrderDetailsButton;
    private ThemedButton mPayButton;
    private ThemedTextView mTitle;

    public HomePageCommerceLoanCellView(Context context) {
        this(context, null);
    }

    public HomePageCommerceLoanCellView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public void init() {
        setOrientation(1);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.home_page_commerce_loan_view, this);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.home_page_commerce_loan_view_title);
        this.mDescription = (ThemedTextView) inflate.findViewById(R.id.home_page_commerce_loan_view_description);
        this.mOrderDetailsButton = (ThemedButton) inflate.findViewById(R.id.home_page_commerce_loan_order_details_button);
        this.mPayButton = (ThemedButton) inflate.findViewById(R.id.home_page_commerce_loan_pay_button);
    }

    public void setCommerceLoan(final HomePageCommerceLoanItemHolder homePageCommerceLoanItemHolder) {
        this.mTitle.setText(homePageCommerceLoanItemHolder.getTitle());
        this.mDescription.setText(homePageCommerceLoanItemHolder.getDescription());
        this.mOrderDetailsButton.setText(getResources().getString(R.string.order_details));
        this.mOrderDetailsButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(HomePageCommerceLoanCellView.this.getContext(), WebViewActivity.class);
                intent.putExtra("ExtraUrl", WebViewActivity.getOrderUrl(homePageCommerceLoanItemHolder.getTransactionId()));
                intent.putExtra("ExtraActionBarTitle", HomePageCommerceLoanCellView.this.getResources().getString(R.string.order_details));
                HomePageCommerceLoanCellView.this.getContext().startActivity(intent);
            }
        });
        this.mPayButton.setText(homePageCommerceLoanItemHolder.getButtonText());
        this.mPayButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_HOME_PAGE_REPAY_LOAN_BANNER);
                Intent intent = new Intent();
                intent.setClass(WishApplication.getInstance(), CommerceLoanCartActivity.class);
                intent.putExtra("ArgSuccessSheetTitle", homePageCommerceLoanItemHolder.getSuccessSheetTitle());
                HomePageCommerceLoanCellView.this.getContext().startActivity(intent);
            }
        });
    }

    public void setup(WishHomePageInfo wishHomePageInfo) {
        if (!wishHomePageInfo.getCommerceLoans().isEmpty()) {
            setCommerceLoan((HomePageCommerceLoanItemHolder) wishHomePageInfo.getCommerceLoans().get(0));
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_REPAY_LOAN_HOME_BANNER);
            return;
        }
        setVisibility(8);
        Crashlytics.log("Empty commerce loan when repay banner is shown");
    }
}
