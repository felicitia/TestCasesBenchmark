package com.contextlogic.wish.activity.wishpartner.dashboard;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.wishpartner.cashout.WishPartnerCashOutActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishPartnerSummary;
import com.contextlogic.wish.api.model.WishPartnerSummary.WishPartnerDashboardInfo;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.IntentUtil;

public class WishPartnerHeaderView extends LinearLayout {
    private ThemedTextView mCashOutButton;
    private ThemedTextView mTitle;
    private ThemedTextView mTotalAmount;

    public WishPartnerHeaderView(Context context) {
        this(context, null);
    }

    public WishPartnerHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        setOrientation(1);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.wish_partner_header_view, this);
        this.mTotalAmount = (ThemedTextView) inflate.findViewById(R.id.wish_partner_header_amount);
        this.mTotalAmount.setFontResizable(true);
        this.mTitle = (ThemedTextView) inflate.findViewById(R.id.wish_partner_header_title);
        this.mCashOutButton = (ThemedTextView) inflate.findViewById(R.id.wish_partner_header_cash_out_text);
    }

    public void update(final WishPartnerSummary wishPartnerSummary, final BaseFragment baseFragment) {
        WishPartnerDashboardInfo dashboardInfo = wishPartnerSummary.getDashboardInfo();
        if (dashboardInfo != null) {
            this.mTotalAmount.setText(dashboardInfo.getBalance().toFormattedString(false, false));
            this.mTitle.setText(dashboardInfo.getBalanceTitle());
            this.mCashOutButton.setText(dashboardInfo.getCashOutButtonText());
            if (dashboardInfo.getBalance().getValue() == 0.0d) {
                this.mCashOutButton.setEnabled(false);
                this.mCashOutButton.setAlpha(0.5f);
                return;
            }
            this.mCashOutButton.setEnabled(true);
            this.mCashOutButton.setAlpha(1.0f);
            this.mCashOutButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    baseFragment.withActivity(new ActivityTask<BaseActivity>() {
                        public void performTask(BaseActivity baseActivity) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_CASH_OUT);
                            Intent intent = new Intent();
                            intent.setClass(baseActivity, WishPartnerCashOutActivity.class);
                            IntentUtil.putParcelableExtra(intent, "ExtraCashOutInfo", wishPartnerSummary.getCashOutInfo());
                            baseActivity.startActivity(intent);
                        }
                    });
                }
            });
        }
    }

    public void setup(final WishPartnerSummary wishPartnerSummary, final BaseFragment baseFragment) {
        WishPartnerDashboardInfo dashboardInfo = wishPartnerSummary.getDashboardInfo();
        if (dashboardInfo != null) {
            this.mTotalAmount.setText(dashboardInfo.getBalance().toFormattedString(false, false));
            this.mTitle.setText(dashboardInfo.getBalanceTitle());
            this.mCashOutButton.setText(dashboardInfo.getCashOutButtonText());
            if (dashboardInfo.getBalance().getValue() == 0.0d) {
                this.mCashOutButton.setEnabled(false);
                this.mCashOutButton.setAlpha(0.5f);
                return;
            }
            this.mCashOutButton.setEnabled(true);
            this.mCashOutButton.setAlpha(1.0f);
            this.mCashOutButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    baseFragment.withActivity(new ActivityTask<BaseActivity>() {
                        public void performTask(BaseActivity baseActivity) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_WISH_PARTNER_CASH_OUT);
                            Intent intent = new Intent();
                            intent.setClass(baseActivity, WishPartnerCashOutActivity.class);
                            IntentUtil.putParcelableExtra(intent, "ExtraCashOutInfo", wishPartnerSummary.getCashOutInfo());
                            baseActivity.startActivity(intent);
                        }
                    });
                }
            });
        }
    }
}
