package com.contextlogic.wish.activity.cart.billing.paymentform;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormView.PaymentFormShownContext;
import com.contextlogic.wish.analytics.CommerceLogger;
import com.contextlogic.wish.analytics.CommerceLogger.Action;
import com.contextlogic.wish.analytics.CommerceLogger.Result;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.util.StringUtil;
import com.contextlogic.wish.util.ViewUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class OxxoPaymentFormView extends PaymentFormView {
    private EditText mOxxoEmailText;
    private EditText mOxxoNameText;

    public void prepareToShow(PaymentFormShownContext paymentFormShownContext) {
    }

    public void recycle() {
    }

    public void refreshUi() {
    }

    public void releaseImages() {
    }

    public boolean requiresNextButton() {
        return true;
    }

    public void restoreImages() {
    }

    public OxxoPaymentFormView(Context context) {
        super(context);
    }

    public OxxoPaymentFormView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public OxxoPaymentFormView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void initializeUi() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.cart_fragment_payment_form_oxxo, this);
        this.mOxxoNameText = (EditText) inflate.findViewById(R.id.cart_fragment_payment_form_oxxo_name);
        if (!(getUiConnector().getCartContext().getShippingInfo() == null || getUiConnector().getCartContext().getShippingInfo().getName() == null)) {
            this.mOxxoNameText.setText(getUiConnector().getCartContext().getShippingInfo().getName());
        }
        this.mOxxoEmailText = (EditText) inflate.findViewById(R.id.cart_fragment_payment_form_oxxo_email);
        if (ProfileDataCenter.getInstance().getEmail() != null) {
            this.mOxxoEmailText.setText(ProfileDataCenter.getInstance().getEmail());
        }
        this.mOxxoEmailText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                OxxoPaymentFormView.this.getUiConnector().handleFormComplete();
                return true;
            }
        });
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.screen_padding);
        setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
    }

    public void restoreState(Bundle bundle) {
        this.mOxxoNameText.setText(bundle.getString("SavedStateNameText"));
        this.mOxxoEmailText.setText(bundle.getString("SavedStateEmailText"));
    }

    public boolean populateAndValidateParameters(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        if (ViewUtil.extractEditTextValue(this.mOxxoNameText) == null) {
            arrayList.add("name");
        } else {
            bundle.putString("ParamName", ViewUtil.extractEditTextValue(this.mOxxoNameText));
        }
        if (ViewUtil.extractEditTextValue(this.mOxxoEmailText) == null) {
            arrayList.add("email");
        } else {
            bundle.putString("ParamEmail", ViewUtil.extractEditTextValue(this.mOxxoEmailText));
        }
        if (arrayList.size() <= 0) {
            return true;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("affected_fields", StringUtil.join(arrayList, ","));
        CommerceLogger.logError(Action.UPDATE_OXXO_BILLING_INFO, Result.MISSING_FIELDS, hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("cart_type", getUiConnector().getCartContext().getCartType().toString());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_OXXO_FAILURE, hashMap2);
        getUiConnector().showErrorMessage(getContext().getString(R.string.please_provide_information_in_all_required_fields));
        return false;
    }

    public void handleSaveInstanceState(Bundle bundle) {
        bundle.putString("SavedStateNameText", ViewUtil.extractEditTextValue(this.mOxxoNameText));
        bundle.putString("SavedStateEmailText", ViewUtil.extractEditTextValue(this.mOxxoEmailText));
    }

    public String getPaymentModeName() {
        return CartBillingSection.OXXO.name();
    }
}
