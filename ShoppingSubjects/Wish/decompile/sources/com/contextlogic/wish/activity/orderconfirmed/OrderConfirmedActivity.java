package com.contextlogic.wish.activity.orderconfirmed;

import android.content.Intent;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class OrderConfirmedActivity extends DrawerActivity {
    public String getMenuKey() {
        return null;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new OrderConfirmedServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new OrderConfirmedFragment();
    }

    public String getTransactionId() {
        return getIntent().getStringExtra("ArgTransactionId");
    }

    public double getTransactionCartAmount() {
        return getIntent().getDoubleExtra("ExtraTransactionCartAmount", -1.0d);
    }

    public String getTransactionCurrencyCode() {
        return getIntent().getStringExtra("ExtraTransactionCurrencyCode");
    }

    public String getTransactionCartItemIds() {
        return getIntent().getStringExtra("ExtraTransactionCartItemIds");
    }

    public boolean hasUpfrontLoanPayment() {
        return getIntent().getBooleanExtra("ExtraHasUpfrontLoanPayment", false);
    }

    public String getActionBarTitle() {
        return getString(R.string.order_confirmed);
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.ORDER_CONFIRMED;
    }

    public void onBackPressed() {
        if (((OrderConfirmedFragment) getUiFragment("FragmentTagMainContent")).isCommerceLoanRepayment()) {
            Intent intent = new Intent();
            intent.putExtra("ExtraRequiresReload", true);
            setResult(-1, intent);
            finish();
        }
        super.onBackPressed();
    }
}
