package com.contextlogic.wish.activity.commercecash;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishCommerceCashUserInfo;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class CommerceCashBannerView extends LinearLayout {
    ThemedTextView mAvailableBalanceAmount;
    ThemedTextView mAvailableBalanceText;

    public CommerceCashBannerView(Context context) {
        this(context, null);
    }

    public CommerceCashBannerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CommerceCashBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.commerce_cash_banner, this);
        this.mAvailableBalanceText = (ThemedTextView) inflate.findViewById(R.id.commerce_cash_fragment_available_balance_title);
        this.mAvailableBalanceAmount = (ThemedTextView) inflate.findViewById(R.id.commerce_cash_fragment_available_balance_amount_text);
    }

    public void setup(WishCommerceCashUserInfo wishCommerceCashUserInfo) {
        WishLocalizedCurrencyValue balance = wishCommerceCashUserInfo.getBalance();
        if (balance != null) {
            this.mAvailableBalanceAmount.setText(balance.toFormattedString(false, false));
        }
    }
}
