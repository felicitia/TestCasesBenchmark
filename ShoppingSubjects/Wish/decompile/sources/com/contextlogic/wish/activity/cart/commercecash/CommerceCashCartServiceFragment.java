package com.contextlogic.wish.activity.cart.commercecash;

import android.os.Bundle;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.cart.CartFragment;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCommerceCashCart;
import com.contextlogic.wish.api.model.WishLoanRepaymentBannerSpec;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.UpdateCommerceCashCartService;
import com.contextlogic.wish.api.service.standalone.UpdateCommerceCashCartService.SuccessCallback;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.payments.CommerceCashCartContext;

public class CommerceCashCartServiceFragment extends CartServiceFragment {
    private UpdateCommerceCashCartService mUpdateCommerceCashCartService;

    public void reInitializeCartContext(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishLoanRepaymentBannerSpec wishLoanRepaymentBannerSpec) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCartContext = new CommerceCashCartContext();
        this.mCartContext.setUpdatedCallback(this);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mUpdateCommerceCashCartService = new UpdateCommerceCashCartService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mUpdateCommerceCashCartService.cancelAllRequests();
    }

    public void showOrderConfirmedActivity(String str, String str2) {
        final String str3;
        if (str2 == "PaymentModeBoleto") {
            str3 = this.mCartContext.getCommerceCashCart().getBoletoSuccessMessage();
        } else {
            str3 = this.mCartContext.getCommerceCashCart().getSuccessMessage();
        }
        withUiFragment(new UiTask<BaseActivity, CommerceCashCartFragment>() {
            public void performTask(BaseActivity baseActivity, CommerceCashCartFragment commerceCashCartFragment) {
                commerceCashCartFragment.showCommerceCashDialogFragment(str3);
            }
        }, "FragmentTagMainContent");
    }

    public void loadCommerceCashCart(double d) {
        this.mUpdateCommerceCashCartService.requestService(d, new SuccessCallback() {
            public void onSuccess(final WishCommerceCashCart wishCommerceCashCart, final WishUserBillingInfo wishUserBillingInfo, final WishShippingInfo wishShippingInfo) {
                CommerceCashCartServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CartFragment>() {
                    public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                        ((CommerceCashCartContext) CommerceCashCartServiceFragment.this.mCartContext).updateData(wishCommerceCashCart, wishUserBillingInfo, wishShippingInfo);
                        CommerceCashCartServiceFragment.this.checkGooglePayPaymentPreference(null, false);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                CommerceCashCartServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CartFragment>() {
                    public void performTask(final BaseActivity baseActivity, CartFragment cartFragment) {
                        cartFragment.handleCartLoadError();
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str), new BaseDialogCallback() {
                            public void onCancel(BaseDialogFragment baseDialogFragment) {
                                baseActivity.finishActivity();
                            }

                            public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                                baseActivity.finishActivity();
                            }
                        });
                    }
                }, "FragmentTagMainContent");
            }
        });
    }
}
