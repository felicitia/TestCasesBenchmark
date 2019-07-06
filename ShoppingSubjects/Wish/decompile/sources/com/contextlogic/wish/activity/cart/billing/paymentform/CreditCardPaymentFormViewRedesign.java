package com.contextlogic.wish.activity.cart.billing.paymentform;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.util.AddressUtil;

public class CreditCardPaymentFormViewRedesign extends CreditCardPaymentFormView {
    private TextView mCompactShippingAddress;
    private TextView mCompactShippingCityAndState;
    /* access modifiers changed from: private */
    public LinearLayout mCompactShippingConatiner;
    private TextView mCompactShippingCountry;
    private TextView mCompactShippingName;
    private TextView mCompactShippingZip;

    public int getLayoutId() {
        return R.layout.cart_fragment_payment_form_credit_card_redesign;
    }

    public CreditCardPaymentFormViewRedesign(Context context) {
        super(context);
    }

    public CreditCardPaymentFormViewRedesign(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CreditCardPaymentFormViewRedesign(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void updateShippingCheckmark() {
        this.mCompactShippingConatiner = (LinearLayout) findViewById(R.id.compact_shipping_container);
        this.mCompactShippingName = (TextView) findViewById(R.id.compact_shipping_name);
        this.mCompactShippingAddress = (TextView) findViewById(R.id.compact_shipping_address_line_one);
        this.mCompactShippingCityAndState = (TextView) findViewById(R.id.compact_shipping_city_state);
        this.mCompactShippingCountry = (TextView) findViewById(R.id.compact_shipping_country);
        this.mCompactShippingZip = (TextView) findViewById(R.id.compact_shipping_zip);
        WishShippingInfo shippingInfo = getUiConnector().getCartContext().getShippingInfo();
        this.mFullBillingAddressFormView.prefillAddress(shippingInfo);
        this.mCompactShippingName.setText(shippingInfo.getName());
        this.mCompactShippingAddress.setText(shippingInfo.getStreetAddressLineOne());
        TextView textView = this.mCompactShippingCityAndState;
        StringBuilder sb = new StringBuilder();
        sb.append(shippingInfo.getCity());
        sb.append(", ");
        sb.append(shippingInfo.getState());
        textView.setText(sb.toString());
        this.mCompactShippingCountry.setText(AddressUtil.getCountryName(shippingInfo.getCountryCode()));
        this.mCompactShippingZip.setText(shippingInfo.getZipCode());
        this.mShippingCheckmarkLayout.setVisibility(0);
        this.mShippingCheckmark.setChecked(true);
        this.mFullBillingAddressFormView.setVisibility(8);
        this.mCompactShippingConatiner.setVisibility(0);
        this.mShippingCheckmarkLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CreditCardPaymentFormViewRedesign.this.mShippingCheckmark.toggle();
            }
        });
        this.mShippingCheckmark.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z && CreditCardPaymentFormViewRedesign.this.getUiConnector().getCartContext().getShippingInfo() != null) {
                    WishShippingInfo shippingInfo = CreditCardPaymentFormViewRedesign.this.getUiConnector().getCartContext().getShippingInfo();
                    if (shippingInfo != null) {
                        CreditCardPaymentFormViewRedesign.this.mFullBillingAddressFormView.prefillAddress(shippingInfo);
                    }
                } else if (!z) {
                    CreditCardPaymentFormViewRedesign.this.mCompactShippingConatiner.setVisibility(8);
                    CreditCardPaymentFormViewRedesign.this.mFullBillingAddressFormView.setVisibility(0);
                    CreditCardPaymentFormViewRedesign.this.mFullBillingAddressFormView.clearAddress();
                }
            }
        });
    }
}
