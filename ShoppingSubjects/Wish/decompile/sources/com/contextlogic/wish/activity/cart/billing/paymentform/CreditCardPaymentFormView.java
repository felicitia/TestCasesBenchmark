package com.contextlogic.wish.activity.cart.billing.paymentform;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.activity.cart.billing.creditcardform.CreditCardFormCreditCardField;
import com.contextlogic.wish.activity.cart.billing.creditcardform.CreditCardFormCvvEditText;
import com.contextlogic.wish.activity.cart.billing.creditcardform.CreditCardFormExpiryDateEditText;
import com.contextlogic.wish.activity.cart.billing.creditcardform.CreditCardFormFieldsDelegate;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormView.PaymentFormShownContext;
import com.contextlogic.wish.activity.cart.shipping.ShippingAddressFormView;
import com.contextlogic.wish.activity.cart.shipping.ShippingAddressFormView.EntryCompletedCallback;
import com.contextlogic.wish.analytics.CommerceLogger;
import com.contextlogic.wish.analytics.CommerceLogger.Action;
import com.contextlogic.wish.analytics.CommerceLogger.Result;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.CreditCardUtil.CardType;
import com.contextlogic.wish.util.KeyboardUtil;
import com.contextlogic.wish.util.StringUtil;
import com.contextlogic.wish.util.ViewUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class CreditCardPaymentFormView extends PaymentFormView {
    /* access modifiers changed from: private */
    public EditText mCpfText;
    private CreditCardFormCreditCardField mCreditCardForm;
    /* access modifiers changed from: private */
    public ThemedTextView mCvvHintTextView;
    /* access modifiers changed from: private */
    public CreditCardFormCvvEditText mCvvText;
    /* access modifiers changed from: private */
    public EditText mEmailText;
    /* access modifiers changed from: private */
    public CreditCardFormExpiryDateEditText mExpiryDateText;
    private View mFreeModeText;
    /* access modifiers changed from: private */
    public View mFullAddressArea;
    protected ShippingAddressFormView mFullBillingAddressFormView;
    /* access modifiers changed from: private */
    public EditText mNameOnCardText;
    protected CheckBox mShippingCheckmark;
    protected View mShippingCheckmarkLayout;
    private ThemedTextView mTrustedCheckoutTextView;
    private ThemedTextView mTrustedCheckoutTitleView;
    private View mTrustedCheckoutView;
    /* access modifiers changed from: private */
    public View mZipCodeArea;
    /* access modifiers changed from: private */
    public EditText mZipCodeText;

    public int getLayoutId() {
        return R.layout.cart_fragment_payment_form_credit_card;
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

    public CreditCardPaymentFormView(Context context) {
        super(context);
    }

    public CreditCardPaymentFormView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CreditCardPaymentFormView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void initializeUi() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(getLayoutId(), this);
        TextView textView = (TextView) inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_credit_card_title);
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            textView.setText(WishApplication.getInstance().getResources().getString(R.string.credit_slash_debit_card));
        }
        this.mTrustedCheckoutTitleView = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_security_title);
        this.mTrustedCheckoutTextView = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_security_text);
        this.mTrustedCheckoutView = inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_trusted_layout);
        String billingSecurityTitle = ConfigDataCenter.getInstance().getBillingSecurityTitle();
        String billingSecurityText = ConfigDataCenter.getInstance().getBillingSecurityText();
        boolean z = (getUiConnector().getCartContext().getTotal() == null || getUiConnector().getCartContext().getTotal().getValue() == 0.0d) ? false : true;
        if (billingSecurityTitle == null || billingSecurityText == null || !z) {
            this.mTrustedCheckoutView.setVisibility(8);
        } else {
            this.mTrustedCheckoutTitleView.setText(billingSecurityTitle);
            this.mTrustedCheckoutTextView.setText(billingSecurityText);
            this.mTrustedCheckoutView.setVisibility(0);
        }
        this.mFreeModeText = inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_free_mode_text);
        if (getUiConnector().getCartContext().getCart() == null || getUiConnector().getCartContext().getCart().getTotal().getUsdValue() != 0.0d) {
            this.mFreeModeText.setVisibility(8);
        } else {
            this.mFreeModeText.setVisibility(0);
        }
        this.mCreditCardForm = (CreditCardFormCreditCardField) inflate.findViewById(R.id.fragment_billing_credit_card_form);
        this.mCreditCardForm.setDelegate(new CreditCardFormFieldsDelegate() {
            public void onEntryComplete() {
                if (CreditCardPaymentFormView.this.mNameOnCardText.getVisibility() == 0) {
                    KeyboardUtil.requestKeyboardFocus(CreditCardPaymentFormView.this.mNameOnCardText);
                } else {
                    KeyboardUtil.requestKeyboardFocus(CreditCardPaymentFormView.this.mCvvText);
                }
            }

            public void onCardTypeChanged(CardType cardType) {
                CreditCardPaymentFormView.this.mCvvText.setCardType(cardType);
                switch (cardType) {
                    case Visa:
                    case MasterCard:
                    case Discover:
                    case DinersClub:
                    case HiperCard:
                    case JCB:
                    case Aura:
                    case Elo:
                    case Carnet:
                        CreditCardPaymentFormView.this.mCvvHintTextView.setText(R.string.the_three_digit_security_code);
                        return;
                    case Amex:
                        CreditCardPaymentFormView.this.mCvvHintTextView.setText(R.string.the_four_digit_security_code);
                        return;
                    default:
                        CreditCardPaymentFormView.this.mCvvHintTextView.setText(R.string.the_three_or_four_digit_security_code);
                        return;
                }
            }
        });
        this.mExpiryDateText = (CreditCardFormExpiryDateEditText) inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_expiry_date);
        this.mExpiryDateText.setDelegate(new CreditCardFormFieldsDelegate() {
            public void onCardTypeChanged(CardType cardType) {
            }

            public void onEntryComplete() {
                if (CreditCardPaymentFormView.this.mCpfText.getVisibility() == 0) {
                    KeyboardUtil.requestKeyboardFocus(CreditCardPaymentFormView.this.mCpfText);
                } else if (CreditCardPaymentFormView.this.mEmailText.getVisibility() == 0) {
                    KeyboardUtil.requestKeyboardFocus(CreditCardPaymentFormView.this.mEmailText);
                } else if (CreditCardPaymentFormView.this.mZipCodeArea.getVisibility() == 0) {
                    KeyboardUtil.requestKeyboardFocus(CreditCardPaymentFormView.this.mZipCodeText);
                } else if (CreditCardPaymentFormView.this.mFullAddressArea.getVisibility() == 0) {
                    CreditCardPaymentFormView.this.mFullBillingAddressFormView.requestKeyboardFocus();
                }
            }
        });
        this.mCvvText = (CreditCardFormCvvEditText) inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_security_code);
        this.mCvvText.setDelegate(new CreditCardFormFieldsDelegate() {
            public void onCardTypeChanged(CardType cardType) {
            }

            public void onEntryComplete() {
                KeyboardUtil.requestKeyboardFocus(CreditCardPaymentFormView.this.mExpiryDateText);
            }
        });
        this.mCvvHintTextView = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_security_hint);
        this.mCvvHintTextView.setText(R.string.the_three_or_four_digit_security_code);
        this.mNameOnCardText = (EditText) inflate.findViewById(R.id.fragment_billing_credit_card_name_on_card);
        this.mCpfText = (EditText) inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_cpf_text);
        this.mEmailText = (EditText) inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_email_text);
        this.mEmailText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                CreditCardPaymentFormView.this.getUiConnector().handleFormComplete();
                return true;
            }
        });
        this.mFullAddressArea = inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_full_address_area);
        this.mFullBillingAddressFormView = (ShippingAddressFormView) inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_full_address_form);
        if (!ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            this.mFullBillingAddressFormView.setupSingleNameField();
        }
        this.mFullBillingAddressFormView.setEntryCompletedCallback(new EntryCompletedCallback() {
            public void onEntryCompletion() {
                CreditCardPaymentFormView.this.getUiConnector().handleFormComplete();
            }
        });
        this.mZipCodeArea = inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_zip_code_area);
        this.mZipCodeText = (EditText) inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_zip_code);
        this.mZipCodeText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                CreditCardPaymentFormView.this.getUiConnector().handleFormComplete();
                return true;
            }
        });
        this.mShippingCheckmarkLayout = inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_use_shipping_layout);
        this.mShippingCheckmark = (CheckBox) inflate.findViewById(R.id.cart_fragment_payment_form_credit_card_use_shipping_checkbox);
        updateShippingCheckmark();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.screen_padding);
        setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
    }

    /* access modifiers changed from: protected */
    public void updateShippingCheckmark() {
        if (getUiConnector().getCartContext().getShippingInfo() != null) {
            this.mShippingCheckmarkLayout.setVisibility(0);
            this.mShippingCheckmarkLayout.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    CreditCardPaymentFormView.this.mShippingCheckmark.toggle();
                }
            });
            this.mShippingCheckmark.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z && CreditCardPaymentFormView.this.getUiConnector().getCartContext().getShippingInfo() != null) {
                        WishShippingInfo shippingInfo = CreditCardPaymentFormView.this.getUiConnector().getCartContext().getShippingInfo();
                        if (shippingInfo != null) {
                            CreditCardPaymentFormView.this.mFullBillingAddressFormView.prefillAddress(shippingInfo);
                        }
                    } else if (!z) {
                        CreditCardPaymentFormView.this.mFullBillingAddressFormView.clearAddress();
                    }
                }
            });
            return;
        }
        this.mShippingCheckmarkLayout.setVisibility(8);
    }

    public void showRedesignedBackground() {
        this.mCreditCardForm.showRedesignedBackground();
        this.mExpiryDateText.showRedesignedBackground();
        this.mCvvText.showRedesignedBackground();
    }

    public void restoreState(Bundle bundle) {
        this.mCpfText.setText(bundle.getString("SavedStateCpfText"));
        this.mEmailText.setText(bundle.getString("SavedStateEmailText"));
        this.mNameOnCardText.setText(bundle.getString("SavedStateNameOnCardText"));
        this.mShippingCheckmark.setChecked(bundle.getBoolean("SavedStateShippingCheckmark"));
        this.mCreditCardForm.setText(bundle.getString("SavedStateCreditCardNumberText"));
        this.mExpiryDateText.setText(bundle.getString("SavedStateExpiryDateText"));
        this.mCvvText.setText(bundle.getString("SavedStateCvvText"));
        this.mZipCodeText.setText(bundle.getString("SavedStateZipCodeText"));
        WishShippingInfo wishShippingInfo = (WishShippingInfo) StateStoreCache.getInstance().getParcelable(bundle, "SavedStateFullAddress", WishShippingInfo.class);
        if (wishShippingInfo != null) {
            this.mFullBillingAddressFormView.prefillAddress(wishShippingInfo);
        }
    }

    public boolean populateAndValidateParameters(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        if (!this.mCreditCardForm.isValid()) {
            arrayList.add("credit_card_number");
        }
        if (!this.mExpiryDateText.isValid()) {
            arrayList.add("credit_card_expiry");
        }
        if (!this.mCvvText.isValid()) {
            arrayList.add("credit_card_cvv");
        }
        if (arrayList.size() > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("affected_fields", StringUtil.join(arrayList, ","));
            CommerceLogger.logError(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.INVALID_FIELD_DATA, hashMap);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("cart_type", getUiConnector().getCartContext().getCartType().toString());
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_FAILURE, hashMap2);
            getUiConnector().showErrorMessage(getContext().getString(R.string.please_enter_valid_credit_card_information));
            return false;
        }
        bundle.putString("ParamCreditCardNumber", this.mCreditCardForm.getText().toString());
        bundle.putString("ParamCreditCardExpiry", this.mExpiryDateText.getText().toString());
        bundle.putString("ParamCreditCardCvv", this.mCvvText.getText().toString());
        ArrayList populateCreditCardExtraParameters = populateCreditCardExtraParameters(bundle);
        if (populateCreditCardExtraParameters.size() <= 0) {
            return true;
        }
        HashMap hashMap3 = new HashMap();
        hashMap3.put("affected_fields", StringUtil.join(populateCreditCardExtraParameters, ","));
        CommerceLogger.logError(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.MISSING_FIELDS, hashMap3);
        HashMap hashMap4 = new HashMap();
        hashMap4.put("cart_type", getUiConnector().getCartContext().getCartType().toString());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_FAILURE, hashMap4);
        getUiConnector().showErrorMessage(getContext().getString(R.string.please_provide_information_in_all_required_fields));
        return false;
    }

    private ArrayList<String> populateCreditCardExtraParameters(Bundle bundle) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (this.mFullAddressArea.getVisibility() == 0) {
            ArrayList missingFields = this.mFullBillingAddressFormView.getMissingFields();
            arrayList.addAll(missingFields);
            if (missingFields.size() == 0) {
                WishShippingInfo enteredShippingAddress = this.mFullBillingAddressFormView.getEnteredShippingAddress();
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
        if (this.mCpfText.getVisibility() == 0) {
            if (ViewUtil.extractEditTextValue(this.mCpfText) == null) {
                arrayList.add("cpf");
            } else {
                bundle.putString("ParamIdentityNumber", ViewUtil.extractEditTextValue(this.mCpfText));
            }
        }
        if (this.mNameOnCardText.getVisibility() == 0) {
            if (ViewUtil.extractEditTextValue(this.mNameOnCardText) == null) {
                arrayList.add("name");
            } else {
                bundle.putString("ParamName", ViewUtil.extractEditTextValue(this.mNameOnCardText));
            }
        }
        if (this.mZipCodeArea.getVisibility() == 0) {
            if (ViewUtil.extractEditTextValue(this.mZipCodeText) == null) {
                arrayList.add("zip");
            } else {
                bundle.putString("paramZip", ViewUtil.extractEditTextValue(this.mZipCodeText));
            }
        }
        if (this.mEmailText.getVisibility() == 0) {
            if (ViewUtil.extractEditTextValue(this.mEmailText) == null) {
                arrayList.add("email");
            } else {
                bundle.putString("ParamEmail", ViewUtil.extractEditTextValue(this.mEmailText));
            }
        }
        return arrayList;
    }

    public void handleSaveInstanceState(Bundle bundle) {
        bundle.putString("SavedStateCpfText", ViewUtil.extractEditTextValue(this.mCpfText));
        bundle.putString("SavedStateEmailText", ViewUtil.extractEditTextValue(this.mEmailText));
        bundle.putString("SavedStateNameOnCardText", ViewUtil.extractEditTextValue(this.mNameOnCardText));
        bundle.putBoolean("SavedStateShippingCheckmark", this.mShippingCheckmark.isChecked());
        bundle.putString("SavedStateCreditCardNumberText", this.mCreditCardForm.getText());
        bundle.putString("SavedStateExpiryDateText", ViewUtil.extractEditTextValue(this.mExpiryDateText));
        bundle.putString("SavedStateCvvText", ViewUtil.extractEditTextValue(this.mCvvText));
        bundle.putString("SavedStateZipCodeText", ViewUtil.extractEditTextValue(this.mZipCodeText));
        bundle.putString("SavedStateFullAddress", StateStoreCache.getInstance().storeParcelable(this.mFullBillingAddressFormView.getEnteredShippingAddress()));
    }

    public void prepareToShow(PaymentFormShownContext paymentFormShownContext) {
        this.mCpfText.setVisibility(8);
        this.mEmailText.setVisibility(8);
        this.mZipCodeArea.setVisibility(8);
        this.mNameOnCardText.setVisibility(8);
        if (getUiConnector().getCartContext().getRequiresFullBillingAddress()) {
            this.mFullAddressArea.setVisibility(0);
        } else {
            this.mFullAddressArea.setVisibility(8);
            this.mZipCodeArea.setVisibility(0);
        }
        if (getUiConnector().getCartContext().getPaymentProcessor() == PaymentProcessor.Ebanx) {
            if (!getUiConnector().getCartContext().getRequiresFullBillingAddress()) {
                this.mNameOnCardText.setVisibility(0);
            }
            if (ProfileDataCenter.getInstance().getCountryCode() != null && ProfileDataCenter.getInstance().getCountryCode().equals("BR")) {
                this.mCpfText.setVisibility(0);
            }
            this.mEmailText.setVisibility(0);
            if (ProfileDataCenter.getInstance().getEmail() != null) {
                this.mEmailText.setText(ProfileDataCenter.getInstance().getEmail());
            }
            this.mZipCodeArea.setVisibility(8);
        } else if (getUiConnector().getCartContext().getPaymentProcessor() == PaymentProcessor.Adyen && !getUiConnector().getCartContext().getRequiresFullBillingAddress()) {
            this.mNameOnCardText.setVisibility(0);
        }
    }

    public boolean isFormEmpty() {
        return TextUtils.isEmpty(this.mCreditCardForm.getText()) && TextUtils.isEmpty(this.mCvvText.getText()) && TextUtils.isEmpty(this.mExpiryDateText.getText()) && TextUtils.isEmpty(this.mNameOnCardText.getText()) && TextUtils.isEmpty(this.mEmailText.getText()) && !this.mShippingCheckmark.isChecked() && this.mFullBillingAddressFormView.isFormEmpty() && TextUtils.isEmpty(this.mZipCodeText.getText());
    }

    public String getPaymentModeName() {
        return CartBillingSection.CREDIT_CARD.name();
    }

    public void requestFocusOnCardNumberField() {
        this.mCreditCardForm.getEditText().requestFocus();
    }
}
