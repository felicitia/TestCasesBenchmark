package com.contextlogic.wish.activity.managepayments;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormView;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormView.PaymentFormShownContext;
import com.contextlogic.wish.activity.managepayments.ManagePaymentCellItemModel.ManagePaymentCellItemModelCallback;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.BillingDetailsResponse;
import com.contextlogic.wish.api.model.WishCreditCardInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.BillingDetailsService;
import com.contextlogic.wish.api.service.standalone.BillingDetailsService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.DeleteCreditCardService;
import com.contextlogic.wish.api.service.standalone.SetPrimaryPaymentService;
import com.contextlogic.wish.databinding.ManagePaymentFooterCellBinding;
import com.contextlogic.wish.databinding.ManagePaymentHeaderCellBinding;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.ui.recyclerview.adapter.ItemModelAdapter;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

public class ManagePaymentsView extends PaymentFormView {
    private ItemModelAdapter<ManagePaymentCellItemModel> mAdapter;
    /* access modifiers changed from: private */
    public BillingDetailsResponse mBillingDetailsResponse;
    private BillingDetailsService mBillingDetailsService = new BillingDetailsService();
    private DeleteCreditCardService mDeleteCreditCardService = new DeleteCreditCardService();
    /* access modifiers changed from: private */
    public boolean mFromCart;
    private RecyclerView mRecyclerView;
    private SetPrimaryPaymentService mSetPrimaryCardService = new SetPrimaryPaymentService();

    public boolean populateAndValidateParameters(Bundle bundle) {
        return false;
    }

    public void prepareToShow(PaymentFormShownContext paymentFormShownContext) {
    }

    public void recycle() {
    }

    public void refreshUi() {
    }

    public void releaseImages() {
    }

    public boolean requiresNextButton() {
        return false;
    }

    public void restoreImages() {
    }

    public void restoreState(Bundle bundle) {
    }

    public ManagePaymentsView(Context context) {
        super(context);
    }

    public ManagePaymentsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ManagePaymentsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setFromCart(boolean z) {
        this.mFromCart = z;
    }

    public void initializeUi() {
        inflate(getContext(), R.layout.manage_payments_layout, this);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mAdapter = new ItemModelAdapter<>();
        this.mRecyclerView.setAdapter(this.mAdapter);
        loadData();
    }

    /* access modifiers changed from: private */
    public void loadData() {
        ((BaseActivity) getContext()).showLoadingDialog();
        this.mBillingDetailsService.requestService(new SuccessCallback() {
            public void onSuccess(BillingDetailsResponse billingDetailsResponse) {
                if (ManagePaymentsView.this.getContext() != null) {
                    ((BaseActivity) ManagePaymentsView.this.getContext()).hideLoadingDialog();
                }
                ManagePaymentsView.this.mBillingDetailsResponse = billingDetailsResponse;
                ManagePaymentsView.this.handleBillingDetailsLoaded(billingDetailsResponse);
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                if (ManagePaymentsView.this.getContext() != null) {
                    ((BaseActivity) ManagePaymentsView.this.getContext()).hideLoadingDialog();
                }
                ManagePaymentsView.this.showErrorDialog(str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleSelected(ManagePaymentCellItemModel managePaymentCellItemModel) {
        for (ManagePaymentCellItemModel isSelected : this.mAdapter.getValues()) {
            isSelected.setIsSelected(false);
        }
        managePaymentCellItemModel.setIsSelected(true);
        this.mAdapter.notifyDataSetChanged();
        updatePrimaryCard(managePaymentCellItemModel);
    }

    private void updatePrimaryCard(ManagePaymentCellItemModel managePaymentCellItemModel) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_USE_THIS_PAYMENT_BUTTON);
        this.mSetPrimaryCardService.requestService(managePaymentCellItemModel.getCreditCardInfo().getId(), null, new DefaultFailureCallback() {
            public void onFailure(String str) {
                ManagePaymentsView.this.showErrorDialog(str);
            }
        });
        markCartItemsViewReloadRequired();
    }

    /* access modifiers changed from: private */
    public void markCartItemsViewReloadRequired() {
        if (getUiConnector() != null) {
            getUiConnector().getCartContext().setReloadCartOnReenter(true);
        }
    }

    /* access modifiers changed from: private */
    public void handleEditClicked(ManagePaymentCellItemModel managePaymentCellItemModel) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYMENT_CELL_EDIT_BUTTON);
        ((BaseActivity) getContext()).startActivityForResult(AddEditPaymentsActivity.create(getContext(), managePaymentCellItemModel.getCreditCardInfo()), createActivityResultCallback());
    }

    /* access modifiers changed from: private */
    public void handleOnDeleteClicked(final ManagePaymentCellItemModel managePaymentCellItemModel) {
        MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, getContext().getString(R.string.delete_payment), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
        ArrayList arrayList = new ArrayList();
        arrayList.add(multiButtonDialogChoice);
        MultiButtonDialogFragment build = new MultiButtonDialogFragmentBuilder().setTitle(getContext().getString(R.string.delete_payment_method_question)).setSubTitle(getContext().getString(R.string.delete_payment_description)).setButtons(arrayList).build();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_MANAGE_PAYMENTS_DELETE_DIALOG);
        ((BaseActivity) getContext()).startDialog(build, new BaseDialogCallback() {
            public void onCancel(BaseDialogFragment baseDialogFragment) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_MANAGE_PAYMENTS_DELETE_CANCEL);
            }

            public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_MANAGE_PAYMENTS_DELETE_CONFIRM);
                ManagePaymentsView.this.deletePaymentMethod(managePaymentCellItemModel);
            }
        });
    }

    private void updateFooterSpacing(ManagePaymentFooterCellBinding managePaymentFooterCellBinding) {
        managePaymentFooterCellBinding.paymentAddNewSpacing.setVisibility(this.mAdapter.getValues().size() > 0 ? 0 : 8);
    }

    /* access modifiers changed from: private */
    public void deletePaymentMethod(final ManagePaymentCellItemModel managePaymentCellItemModel) {
        this.mAdapter.getValues().remove(managePaymentCellItemModel);
        if (this.mAdapter.getFooterView() != null) {
            ManagePaymentFooterCellBinding managePaymentFooterCellBinding = (ManagePaymentFooterCellBinding) DataBindingUtil.getBinding(this.mAdapter.getFooterView());
            if (managePaymentFooterCellBinding != null) {
                updateFooterSpacing(managePaymentFooterCellBinding);
            }
        }
        this.mAdapter.notifyDataSetChanged();
        this.mDeleteCreditCardService.requestService(managePaymentCellItemModel.getCreditCardInfo().getId(), new DeleteCreditCardService.SuccessCallback() {
            public void onSuccess(BillingDetailsResponse billingDetailsResponse) {
                if (managePaymentCellItemModel.isSelected()) {
                    ManagePaymentsView.this.loadData();
                }
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                ManagePaymentsView.this.showErrorDialog(str);
            }
        });
        markCartItemsViewReloadRequired();
    }

    /* access modifiers changed from: private */
    public void showErrorDialog(String str) {
        BaseActivity baseActivity = (BaseActivity) getContext();
        if (baseActivity != null) {
            baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
        }
    }

    private ManagePaymentCellItemModelCallback handleManagePaymentClicked() {
        return new ManagePaymentCellItemModelCallback() {
            public void onSelected(ManagePaymentCellItemModel managePaymentCellItemModel) {
                ManagePaymentsView.this.handleSelected(managePaymentCellItemModel);
            }

            public void onEditClicked(ManagePaymentCellItemModel managePaymentCellItemModel) {
                ManagePaymentsView.this.handleEditClicked(managePaymentCellItemModel);
            }

            public void onDeleteClicked(ManagePaymentCellItemModel managePaymentCellItemModel) {
                ManagePaymentsView.this.handleOnDeleteClicked(managePaymentCellItemModel);
            }
        };
    }

    private View createHeaderView() {
        return ManagePaymentHeaderCellBinding.inflate(LayoutInflater.from(getContext())).getRoot();
    }

    private View createFooterView() {
        ManagePaymentFooterCellBinding inflate = ManagePaymentFooterCellBinding.inflate(LayoutInflater.from(getContext()));
        updateFooterSpacing(inflate);
        inflate.paymentAddNewText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_MANAGE_PAYMENTS_ADD_NEW);
                if (ManagePaymentsView.this.mFromCart) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_ADD_NEW_PAYMENT_FROM_CART_NO_ADDRESS);
                } else {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_ADD_NEW_PAYMENT_WITH_NO_ADDRESS);
                }
                if (ManagePaymentsView.this.mBillingDetailsResponse != null) {
                    ((BaseActivity) ManagePaymentsView.this.getContext()).startActivityForResult(AddEditPaymentsActivity.create(ManagePaymentsView.this.getContext(), ManagePaymentsView.this.mBillingDetailsResponse.getPaymentType()), ManagePaymentsView.this.createActivityResultCallback());
                }
            }
        });
        return inflate.getRoot();
    }

    /* access modifiers changed from: private */
    public int createActivityResultCallback() {
        return ((BaseActivity) getContext()).addResultCodeCallback(new ActivityResultCallback() {
            public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                if (i2 == 1001) {
                    ManagePaymentsView.this.loadData();
                    ManagePaymentsView.this.markCartItemsViewReloadRequired();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleBillingDetailsLoaded(BillingDetailsResponse billingDetailsResponse) {
        WishUserBillingInfo wishUserBillingInfo = billingDetailsResponse.getWishUserBillingInfo();
        List creditCardInfoList = wishUserBillingInfo.getCreditCardInfoList(billingDetailsResponse.getPaymentType());
        if (!CollectionUtils.isEmpty(creditCardInfoList) || getUiConnector() == null) {
            renderView(wishUserBillingInfo, creditCardInfoList);
        } else {
            getUiConnector().showCreditCardPaymentFormView();
        }
    }

    private void renderView(WishUserBillingInfo wishUserBillingInfo, List<WishCreditCardInfo> list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        boolean z = false;
        while (true) {
            boolean z2 = true;
            if (i >= list.size()) {
                break;
            }
            WishCreditCardInfo wishCreditCardInfo = (WishCreditCardInfo) list.get(i);
            ManagePaymentCellItemModel managePaymentCellItemModel = new ManagePaymentCellItemModel(wishCreditCardInfo, handleManagePaymentClicked());
            managePaymentCellItemModel.setSelectable(this.mFromCart);
            managePaymentCellItemModel.setShowDeleteButton(!this.mFromCart);
            if (TextUtils.equals(wishUserBillingInfo.getDefaultCardId(), wishCreditCardInfo.getId())) {
                managePaymentCellItemModel.setIsSelected(true);
                z = true;
            }
            if (i != list.size() - 1) {
                z2 = false;
            }
            managePaymentCellItemModel.setHideBottomBorder(z2);
            arrayList.add(managePaymentCellItemModel);
            i++;
        }
        if (!z && arrayList.size() > 0) {
            ((ManagePaymentCellItemModel) arrayList.get(0)).setIsSelected(true);
        }
        this.mAdapter.setValues(arrayList);
        this.mAdapter.setHeaderView(createHeaderView());
        this.mAdapter.setFooterView(createFooterView());
    }

    public void cancelAllRequests() {
        this.mBillingDetailsService.cancelAllRequests();
        this.mSetPrimaryCardService.cancelAllRequests();
        this.mDeleteCreditCardService.cancelAllRequests();
    }
}
