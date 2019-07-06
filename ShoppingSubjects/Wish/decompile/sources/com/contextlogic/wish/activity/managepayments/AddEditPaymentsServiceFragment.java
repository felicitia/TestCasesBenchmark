package com.contextlogic.wish.activity.managepayments;

import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.service.standalone.GetUserShippingDetailsService;
import com.contextlogic.wish.api.service.standalone.GetUserShippingDetailsService.SuccessCallback;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessorServiceFragment;
import java.util.List;

public class AddEditPaymentsServiceFragment extends ServiceFragment<AddEditPaymentsActivity> implements CartPaymentVaultProcessorServiceFragment<AddEditPaymentsActivity> {
    private GetUserShippingDetailsService mGetUserShippingDetailsService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetUserShippingDetailsService = new GetUserShippingDetailsService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetUserShippingDetailsService.cancelAllRequests();
    }

    public void loadBillingAddresses() {
        this.mGetUserShippingDetailsService.requestService(new SuccessCallback() {
            public void onSuccess(final List<WishShippingInfo> list, final String str) {
                AddEditPaymentsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, AddEditPaymentsFragment>() {
                    public void performTask(BaseActivity baseActivity, AddEditPaymentsFragment addEditPaymentsFragment) {
                        addEditPaymentsFragment.handleBillingAddressesLoaded(list, str);
                    }
                });
            }
        }, null);
    }

    public void showLoadingSpinner() {
        withVerifiedAuthenticationActivity(new ActivityTask<AddEditPaymentsActivity>() {
            public void performTask(AddEditPaymentsActivity addEditPaymentsActivity) {
                addEditPaymentsActivity.showLoadingDialog();
            }
        });
    }

    public void hideLoadingSpinner() {
        withVerifiedAuthenticationActivity(new ActivityTask<AddEditPaymentsActivity>() {
            public void performTask(AddEditPaymentsActivity addEditPaymentsActivity) {
                addEditPaymentsActivity.hideLoadingDialog();
            }
        });
    }

    public CartContext getCartContext() {
        return new CartContext();
    }
}
