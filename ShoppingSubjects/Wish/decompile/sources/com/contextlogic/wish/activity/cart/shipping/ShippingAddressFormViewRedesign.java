package com.contextlogic.wish.activity.cart.shipping;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;

public class ShippingAddressFormViewRedesign extends ShippingAddressFormView {
    private LinearLayout mNameContainer;

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.shipping_address_form_redesign;
    }

    public ShippingAddressFormViewRedesign(Context context) {
        super(context);
    }

    public ShippingAddressFormViewRedesign(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ShippingAddressFormViewRedesign(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.mNameContainer = (LinearLayout) findViewById(R.id.name_container);
    }

    public void setupSingleNameField() {
        this.mNameContainer.setVisibility(8);
        this.mFirstNameText.setVisibility(8);
        this.mLastNameText.setVisibility(8);
        this.mNameText.setVisibility(0);
    }
}
