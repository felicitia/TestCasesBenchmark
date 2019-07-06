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
import com.contextlogic.wish.activity.cart.shipping.ShippingAddressFormView;
import com.contextlogic.wish.activity.cart.shipping.ShippingAddressFormView.EntryCompletedCallback;
import com.contextlogic.wish.analytics.CommerceLogger;
import com.contextlogic.wish.analytics.CommerceLogger.Action;
import com.contextlogic.wish.analytics.CommerceLogger.Result;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.payments.CartContext.CartType;
import com.contextlogic.wish.util.StringUtil;
import com.contextlogic.wish.util.ViewUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class BoletoPaymentFormView extends PaymentFormView {
    private EditText mBoletoEmailText;
    private EditText mBoletoIdentityNumberText;
    private EditText mBoletoNameText;
    private ShippingAddressFormView mFullAddressFormView;

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

    public BoletoPaymentFormView(Context context) {
        super(context);
    }

    public BoletoPaymentFormView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BoletoPaymentFormView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void initializeUi() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.cart_fragment_payment_form_boleto, this);
        this.mBoletoNameText = (EditText) inflate.findViewById(R.id.cart_fragment_payment_form_boleto_name);
        if (!(getUiConnector().getCartContext().getShippingInfo() == null || getUiConnector().getCartContext().getShippingInfo().getName() == null)) {
            this.mBoletoNameText.setText(getUiConnector().getCartContext().getShippingInfo().getName());
        }
        this.mFullAddressFormView = (ShippingAddressFormView) inflate.findViewById(R.id.cart_fragment_payment_form_boleto_full_address_form);
        if (getUiConnector().getCartContext().getCartType() == CartType.COMMERCE_CASH) {
            ((TextView) inflate.findViewById(R.id.cart_fragment_payment_form_boleto_name_label)).setText(getResources().getString(R.string.full_name));
            WishShippingInfo shippingInfo = getUiConnector().getCartContext().getShippingInfo();
            if (shippingInfo == null || !shippingInfo.getCountryCode().equalsIgnoreCase("BR")) {
                inflate.findViewById(R.id.cart_fragment_payment_form_boleto_full_address_label).setVisibility(0);
                this.mFullAddressFormView.setVisibility(0);
                this.mFullAddressFormView.hideNameFields();
                this.mFullAddressFormView.setEntryCompletedCallback(new EntryCompletedCallback() {
                    public void onEntryCompletion() {
                        BoletoPaymentFormView.this.getUiConnector().handleFormComplete();
                    }
                });
            }
        }
        this.mBoletoIdentityNumberText = (EditText) inflate.findViewById(R.id.cart_fragment_payment_form_boleto_identity_number);
        this.mBoletoEmailText = (EditText) inflate.findViewById(R.id.cart_fragment_payment_form_boleto_email);
        if (ProfileDataCenter.getInstance().getEmail() != null) {
            this.mBoletoEmailText.setText(ProfileDataCenter.getInstance().getEmail());
        }
        this.mBoletoEmailText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                BoletoPaymentFormView.this.getUiConnector().handleFormComplete();
                return true;
            }
        });
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.screen_padding);
        setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
    }

    public void restoreState(Bundle bundle) {
        this.mBoletoNameText.setText(bundle.getString("SavedStateNameText"));
        this.mBoletoIdentityNumberText.setText(bundle.getString("SavedStateIdentityNumberText"));
        this.mBoletoEmailText.setText(bundle.getString("SavedStateEmailText"));
        WishShippingInfo wishShippingInfo = (WishShippingInfo) StateStoreCache.getInstance().getParcelable(bundle, "SavedStateFullAddress", WishShippingInfo.class);
        if (wishShippingInfo != null && this.mFullAddressFormView.getVisibility() == 0) {
            this.mFullAddressFormView.prefillAddress(wishShippingInfo);
        }
    }

    public boolean populateAndValidateParameters(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        if (ViewUtil.extractEditTextValue(this.mBoletoNameText) == null) {
            arrayList.add("name");
        } else {
            bundle.putString("ParamName", ViewUtil.extractEditTextValue(this.mBoletoNameText));
        }
        if (ViewUtil.extractEditTextValue(this.mBoletoIdentityNumberText) == null) {
            arrayList.add("identity_number");
        } else {
            bundle.putString("ParamIdentityNumber", ViewUtil.extractEditTextValue(this.mBoletoIdentityNumberText));
        }
        if (ViewUtil.extractEditTextValue(this.mBoletoEmailText) == null) {
            arrayList.add("email");
        } else {
            bundle.putString("ParamEmail", ViewUtil.extractEditTextValue(this.mBoletoEmailText));
        }
        if (this.mFullAddressFormView.getVisibility() == 0) {
            ArrayList missingFields = this.mFullAddressFormView.getMissingFields();
            arrayList.addAll(missingFields);
            if (missingFields.size() == 0) {
                WishShippingInfo enteredShippingAddress = this.mFullAddressFormView.getEnteredShippingAddress();
                if (enteredShippingAddress.getName() != null) {
                    bundle.putString("ParamName", enteredShippingAddress.getName());
                }
                if (enteredShippingAddress.getStreetAddressLineOne() != null) {
                    bundle.putString("paramAddressLineOne", enteredShippingAddress.getStreetAddressLineOne());
                }
                if (enteredShippingAddress.getStreetAddressLineTwo() != null) {
                    bundle.putString("paramAddressLineTwo", enteredShippingAddress.getStreetAddressLineTwo());
                }
                if (enteredShippingAddress.getCity() != null) {
                    bundle.putString("paramCity", enteredShippingAddress.getCity());
                }
                if (enteredShippingAddress.getZipCode() != null) {
                    bundle.putString("paramZip", enteredShippingAddress.getZipCode());
                }
                if (enteredShippingAddress.getPhoneNumber() != null) {
                    bundle.putString("ParamPhone", enteredShippingAddress.getPhoneNumber());
                }
                if (enteredShippingAddress.getState() != null) {
                    bundle.putString("ParamState", enteredShippingAddress.getState());
                }
                if (enteredShippingAddress.getCountryCode() != null) {
                    bundle.putString("paramCountry", enteredShippingAddress.getCountryCode());
                }
            }
        }
        if (arrayList.size() <= 0) {
            return true;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("affected_fields", StringUtil.join(arrayList, ","));
        CommerceLogger.logError(Action.UPDATE_BOLETO_BILLING_INFO, Result.MISSING_FIELDS, hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("cart_type", getUiConnector().getCartContext().getCartType().toString());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_BOLETO_FAILURE, hashMap2);
        getUiConnector().showErrorMessage(getContext().getString(R.string.please_provide_information_in_all_required_fields));
        return false;
    }

    public void handleSaveInstanceState(Bundle bundle) {
        bundle.putString("SavedStateNameText", ViewUtil.extractEditTextValue(this.mBoletoNameText));
        bundle.putString("SavedStateIdentityNumberText", ViewUtil.extractEditTextValue(this.mBoletoIdentityNumberText));
        bundle.putString("SavedStateEmailText", ViewUtil.extractEditTextValue(this.mBoletoEmailText));
        if (this.mFullAddressFormView.getVisibility() == 0) {
            bundle.putString("SavedStateFullAddress", StateStoreCache.getInstance().storeParcelable(this.mFullAddressFormView.getEnteredShippingAddress()));
        }
    }

    public String getPaymentModeName() {
        return CartBillingSection.BOLETO.name();
    }
}
