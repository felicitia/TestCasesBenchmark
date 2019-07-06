package com.contextlogic.wish.activity.managepayments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.cart.shipping.StandaloneShippingInfoActivity;
import com.contextlogic.wish.activity.managepayments.BillingAddressFooterAddItemModel.Callback;
import com.contextlogic.wish.activity.managepayments.BillingAddressItemModel.BillingAddressItemModelCallback;
import com.contextlogic.wish.analytics.CommerceLogger;
import com.contextlogic.wish.analytics.CommerceLogger.Action;
import com.contextlogic.wish.analytics.CommerceLogger.Result;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.api.model.WishCreditCardInfo;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.databinding.AddEditPaymentsFooterCellBinding;
import com.contextlogic.wish.databinding.AddEditPaymentsHeaderCellBinding;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.SaveListener;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessorSelector;
import com.contextlogic.wish.ui.recyclerview.adapter.ItemModel;
import com.contextlogic.wish.ui.recyclerview.adapter.ItemModelAdapter;
import com.contextlogic.wish.util.CreditCardUtil;
import com.contextlogic.wish.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddEditPaymentsFragment extends UiFragment<AddEditPaymentsActivity> {
    /* access modifiers changed from: private */
    public ItemModelAdapter<ItemModel> mAdapter;
    /* access modifiers changed from: private */
    public AddEditPaymentsFooterCellBinding mFooterBinding;
    private AddEditPaymentsHeaderCellBinding mHeaderBinding;
    private RecyclerView mRecyclerView;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.add_edit_payments_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mAdapter = new ItemModelAdapter<>();
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateTitle();
        setupPaymentSection();
        setupBillingAddresses();
        setupAddNewPaymentButton();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_EDIT_PAYMENT_FORM);
    }

    private void updateTitle() {
        String str;
        if (((AddEditPaymentsActivity) getBaseActivity()).isEditMode()) {
            str = getString(R.string.edit);
        } else {
            str = getString(R.string.payment_add_new);
        }
        ((AddEditPaymentsActivity) getBaseActivity()).getActionBarManager().setTitle(str);
    }

    private void setupAddNewPaymentButton() {
        this.mFooterBinding = AddEditPaymentsFooterCellBinding.inflate(LayoutInflater.from(getContext()), this.mRecyclerView, false);
        this.mAdapter.setFooterView(this.mFooterBinding.getRoot());
        this.mFooterBinding.paymentSaveButton.setEnabled(false);
        final PaymentProcessor paymentProcessor = PaymentProcessor.Unknown;
        if (((AddEditPaymentsActivity) getBaseActivity()).isEditMode()) {
            this.mFooterBinding.paymentSaveButton.setText(R.string.save);
            if (((AddEditPaymentsActivity) getBaseActivity()).getCreditCardInfo() != null) {
                paymentProcessor = ((AddEditPaymentsActivity) getBaseActivity()).getCreditCardInfo().getPaymentProcessor();
            }
        } else {
            this.mFooterBinding.paymentSaveButton.setText(R.string.add_new_payment_method);
            paymentProcessor = ((AddEditPaymentsActivity) getBaseActivity()).getAddNewPaymentType();
        }
        if (paymentProcessor != null) {
            this.mFooterBinding.paymentSaveButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (((AddEditPaymentsActivity) AddEditPaymentsFragment.this.getBaseActivity()).isEditMode()) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SAVE_EDITED_PAYMENT);
                    } else {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SAVE_NEW_PAYMENT_BUTTON);
                    }
                    if (AddEditPaymentsFragment.this.validateFields()) {
                        AddEditPaymentsFragment.this.savePaymentInformation(paymentProcessor);
                    }
                }
            });
        }
    }

    private Bundle getPaymentParameters() {
        Bundle bundle = new Bundle();
        if (((AddEditPaymentsActivity) getBaseActivity()).isEditMode() && ((AddEditPaymentsActivity) getBaseActivity()).getCreditCardInfo() != null) {
            bundle.putString("ParamCreditCardId", ((AddEditPaymentsActivity) getBaseActivity()).getCreditCardInfo().getId());
        }
        bundle.putString("ParamCreditCardNumber", this.mHeaderBinding.paymentCreditCardNumber.getText());
        bundle.putString("ParamCreditCardExpiry", this.mHeaderBinding.paymentExpiryDate.getText().toString());
        bundle.putString("ParamCreditCardCvv", this.mHeaderBinding.paymentSecurityCode.getText().toString());
        WishShippingInfo billingAddressInfo = getBillingAddressInfo();
        if (billingAddressInfo != null) {
            bundle.putString("ParamName", billingAddressInfo.getName());
            bundle.putString("paramAddressLineOne", billingAddressInfo.getStreetAddressLineOne());
            bundle.putString("paramAddressLineTwo", billingAddressInfo.getStreetAddressLineTwo());
            bundle.putString("paramCity", billingAddressInfo.getCity());
            bundle.putString("paramZip", billingAddressInfo.getZipCode());
            bundle.putString("ParamPhone", billingAddressInfo.getPhoneNumber());
            bundle.putString("ParamState", billingAddressInfo.getState());
            bundle.putString("paramCountry", billingAddressInfo.getCountryCode());
        }
        return bundle;
    }

    private WishShippingInfo getBillingAddressInfo() {
        if (!((AddEditPaymentsActivity) getBaseActivity()).isEditMode() || ((AddEditPaymentsActivity) getBaseActivity()).getCreditCardInfo() == null) {
            return getSelectedBillingShippingInfo();
        }
        return ((AddEditPaymentsActivity) getBaseActivity()).getCreditCardInfo().getBillingAddress();
    }

    private WishShippingInfo getSelectedBillingShippingInfo() {
        for (ItemModel itemModel : this.mAdapter.getValues()) {
            if (itemModel instanceof BillingAddressItemModel) {
                BillingAddressItemModel billingAddressItemModel = (BillingAddressItemModel) itemModel;
                if (billingAddressItemModel.isSelected()) {
                    return billingAddressItemModel.getWishShippingInfo();
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void savePaymentInformation(PaymentProcessor paymentProcessor) {
        CartPaymentVaultProcessor creditCardPaymentProcessor = CartPaymentVaultProcessorSelector.getCreditCardPaymentProcessor(paymentProcessor, (AddEditPaymentsServiceFragment) ((AddEditPaymentsActivity) getBaseActivity()).getServiceFragment());
        if (creditCardPaymentProcessor != null) {
            creditCardPaymentProcessor.save(new SaveListener() {
                public void onSaveComplete(CartPaymentVaultProcessor cartPaymentVaultProcessor) {
                    AddEditPaymentsFragment.this.withActivity(new ActivityTask<AddEditPaymentsActivity>() {
                        public void performTask(AddEditPaymentsActivity addEditPaymentsActivity) {
                            addEditPaymentsActivity.setResult(1001);
                            addEditPaymentsActivity.finishActivity();
                        }
                    });
                }

                public void onSaveFailed(CartPaymentVaultProcessor cartPaymentVaultProcessor, final String str) {
                    AddEditPaymentsFragment.this.withServiceFragment(new ServiceTask<BaseActivity, AddEditPaymentsServiceFragment>() {
                        public void performTask(BaseActivity baseActivity, AddEditPaymentsServiceFragment addEditPaymentsServiceFragment) {
                            addEditPaymentsServiceFragment.showErrorMessage(str != null ? str : AddEditPaymentsFragment.this.getString(R.string.we_were_unable_to_update_your_billing_information));
                        }
                    });
                }
            }, getPaymentParameters());
        }
    }

    /* access modifiers changed from: private */
    public boolean validateFields() {
        AddEditPaymentsServiceFragment addEditPaymentsServiceFragment = (AddEditPaymentsServiceFragment) ((AddEditPaymentsActivity) getBaseActivity()).getServiceFragment();
        ArrayList arrayList = new ArrayList();
        if (!this.mHeaderBinding.paymentCreditCardNumber.isValid()) {
            arrayList.add("credit_card_number");
        }
        if (!this.mHeaderBinding.paymentExpiryDate.isValid()) {
            arrayList.add("credit_card_expiry");
        }
        if (!this.mHeaderBinding.paymentSecurityCode.isValid()) {
            arrayList.add("credit_card_cvv");
        }
        if (arrayList.size() <= 0) {
            return true;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("affected_fields", StringUtil.join(arrayList, ","));
        CommerceLogger.logError(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.INVALID_FIELD_DATA, hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("cart_type", addEditPaymentsServiceFragment.getCartContext().getCartType().toString());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_FAILURE, hashMap2);
        addEditPaymentsServiceFragment.showErrorMessage(requireContext().getString(R.string.please_enter_valid_credit_card_information));
        return false;
    }

    private void setupBillingAddresses() {
        if (((AddEditPaymentsActivity) getBaseActivity()).isEditMode()) {
            setupNonEditableBillingAddress();
        } else {
            loadBillingAddresses();
        }
    }

    /* access modifiers changed from: private */
    public void loadBillingAddresses() {
        withServiceFragment(new ServiceTask<BaseActivity, AddEditPaymentsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, AddEditPaymentsServiceFragment addEditPaymentsServiceFragment) {
                addEditPaymentsServiceFragment.loadBillingAddresses();
            }
        });
    }

    private void setupNonEditableBillingAddress() {
        WishCreditCardInfo creditCardInfo = ((AddEditPaymentsActivity) getBaseActivity()).getCreditCardInfo();
        if (creditCardInfo != null && creditCardInfo.getBillingAddress() != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new BillingAddressHeaderItemModel());
            BillingAddressItemModel billingAddressItemModel = new BillingAddressItemModel(creditCardInfo.getBillingAddress());
            billingAddressItemModel.setIsLastCell(true);
            billingAddressItemModel.setSelectable(false);
            arrayList.add(billingAddressItemModel);
            this.mAdapter.setValues(arrayList);
        }
    }

    public void handleBillingAddressesLoaded(List<WishShippingInfo> list, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BillingAddressHeaderItemModel());
        for (WishShippingInfo wishShippingInfo : list) {
            BillingAddressItemModel billingAddressItemModel = new BillingAddressItemModel(wishShippingInfo);
            billingAddressItemModel.setCallback(handleBillingAddressSelected());
            billingAddressItemModel.setSelectable(true);
            billingAddressItemModel.setIsLastCell(false);
            if (TextUtils.equals(str, wishShippingInfo.getId())) {
                billingAddressItemModel.setIsSelected(true);
            }
            arrayList.add(billingAddressItemModel);
        }
        arrayList.add(getFooterItem());
        this.mAdapter.setValues(arrayList);
    }

    private BillingAddressFooterAddItemModel getFooterItem() {
        BillingAddressFooterAddItemModel billingAddressFooterAddItemModel = new BillingAddressFooterAddItemModel();
        billingAddressFooterAddItemModel.setCallback(new Callback() {
            public void onAddAddressClicked() {
                ((AddEditPaymentsActivity) AddEditPaymentsFragment.this.getBaseActivity()).startActivityForResult(new Intent(AddEditPaymentsFragment.this.getBaseActivity(), StandaloneShippingInfoActivity.class), AddEditPaymentsFragment.this.createActivityResultCallback());
            }
        });
        return billingAddressFooterAddItemModel;
    }

    /* access modifiers changed from: private */
    public int createActivityResultCallback() {
        return ((AddEditPaymentsActivity) getBaseActivity()).addResultCodeCallback(new ActivityResultCallback() {
            public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                if (i2 == -1 && intent != null && intent.getBooleanExtra("ExtraRequiresReload", false)) {
                    AddEditPaymentsFragment.this.loadBillingAddresses();
                }
            }
        });
    }

    private BillingAddressItemModelCallback handleBillingAddressSelected() {
        return new BillingAddressItemModelCallback() {
            public void onSelected(BillingAddressItemModel billingAddressItemModel) {
                for (ItemModel itemModel : AddEditPaymentsFragment.this.mAdapter.getValues()) {
                    if (itemModel instanceof BillingAddressItemModel) {
                        ((BillingAddressItemModel) itemModel).setIsSelected(false);
                    }
                }
                billingAddressItemModel.setIsSelected(true);
                AddEditPaymentsFragment.this.mAdapter.notifyDataSetChanged();
            }
        };
    }

    private void setupPaymentSection() {
        this.mHeaderBinding = AddEditPaymentsHeaderCellBinding.inflate(LayoutInflater.from(requireContext()), this.mRecyclerView, false);
        this.mAdapter.setHeaderView(this.mHeaderBinding.getRoot());
        WishCreditCardInfo creditCardInfo = ((AddEditPaymentsActivity) getBaseActivity()).getCreditCardInfo();
        if (creditCardInfo != null) {
            this.mHeaderBinding.paymentCreditCardNumber.getEditText().setHint(getString(R.string.payment_visa_text, creditCardInfo.getLastFourDigits()));
            if (creditCardInfo.hasExpiryMonth() && creditCardInfo.hasExpiryYear()) {
                this.mHeaderBinding.paymentExpiryDate.setHint(CreditCardUtil.getFormattedExpiryDate(creditCardInfo.getExpiryMonth(), creditCardInfo.getExpiryYear()));
            }
        }
        this.mHeaderBinding.paymentCreditCardNumber.showRedesignedBackground();
        this.mHeaderBinding.paymentExpiryDate.showRedesignedBackground();
        this.mHeaderBinding.paymentSecurityCode.showRedesignedBackground();
        TextWatcher hasChangedTextWatcher = getHasChangedTextWatcher();
        this.mHeaderBinding.paymentCreditCardNumber.getEditText().addTextChangedListener(hasChangedTextWatcher);
        this.mHeaderBinding.paymentSecurityCode.addTextChangedListener(hasChangedTextWatcher);
        this.mHeaderBinding.paymentExpiryDate.addTextChangedListener(hasChangedTextWatcher);
    }

    private TextWatcher getHasChangedTextWatcher() {
        return new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                AddEditPaymentsFragment.this.mFooterBinding.paymentSaveButton.setEnabled(true);
            }
        };
    }
}
