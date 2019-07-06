package com.contextlogic.wish.activity.cart.commerceloan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.cart.CartFragment;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCommerceLoanCart;
import com.contextlogic.wish.api.model.WishLoanRepaymentBannerSpec;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.UpdateCommerceLoanCartService;
import com.contextlogic.wish.api.service.standalone.UpdateCommerceLoanCartService.SuccessCallback;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog.SuccessBottomSheetDialogDismissCallback;
import com.contextlogic.wish.dialog.prompt.PromptDialogFragment;
import com.contextlogic.wish.payments.CommerceLoanCartContext;
import com.contextlogic.wish.util.IntentUtil;

public class CommerceLoanCartServiceFragment extends CartServiceFragment {
    /* access modifiers changed from: private */
    public SuccessBottomSheetDialog mDialog;
    private UpdateCommerceLoanCartService mUpdateCommerceLoanCartService;

    public void reInitializeCartContext(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishLoanRepaymentBannerSpec wishLoanRepaymentBannerSpec) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCartContext = new CommerceLoanCartContext();
        this.mCartContext.setUpdatedCallback(this);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mUpdateCommerceLoanCartService = new UpdateCommerceLoanCartService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mUpdateCommerceLoanCartService.cancelAllRequests();
    }

    public void showOrderConfirmedActivity(final String str, String str2) {
        withVerifiedAuthenticationActivity(new ActivityTask<CartActivity>() {
            public void performTask(final CartActivity cartActivity) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_REPAY_LOAN_CHECKOUT_BUTTON);
                String successSheetTitle = ((CommerceLoanCartActivity) cartActivity).getSuccessSheetTitle();
                if (successSheetTitle == null) {
                    Intent intent = new Intent();
                    intent.setClass(cartActivity, OrderConfirmedActivity.class);
                    intent.putExtra("ArgTransactionId", str);
                    cartActivity.startActivityForResult(intent, 999);
                    cartActivity.addResultCallback(new ActivityResultCallback() {
                        public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                            if (IntentUtil.safeToUnparcel(intent) && intent.getBooleanExtra("ExtraRequiresReload", false)) {
                                new Intent().putExtra("ExtraRequiresReload", true);
                                cartActivity.setResult(-1, intent);
                            }
                            cartActivity.finishActivity();
                        }
                    });
                    return;
                }
                CommerceLoanCartServiceFragment.this.mDialog = SuccessBottomSheetDialog.create(cartActivity).setTitle(successSheetTitle).setMessage(CommerceLoanCartServiceFragment.this.getString(R.string.view_order_details)).setSubtitleTextColor(CommerceLoanCartServiceFragment.this.getResources().getColor(R.color.main_primary)).autoDismiss().setDismissCallback(new SuccessBottomSheetDialogDismissCallback() {
                    public void onDismiss() {
                        Intent intent = new Intent();
                        intent.putExtra("ExtraRequiresReload", true);
                        cartActivity.setResult(-1, intent);
                        cartActivity.finishActivity();
                        CommerceLoanCartServiceFragment.this.mDialog = null;
                    }
                }).setClickCallback(new OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setClass(cartActivity, OrderConfirmedActivity.class);
                        intent.putExtra("ArgTransactionId", str);
                        cartActivity.startActivity(intent);
                    }
                });
                CommerceLoanCartServiceFragment.this.mDialog.show();
            }
        });
    }

    public void getCommerceLoanCart() {
        this.mUpdateCommerceLoanCartService.requestService(new SuccessCallback() {
            public void onSuccess(WishCommerceLoanCart wishCommerceLoanCart, WishUserBillingInfo wishUserBillingInfo) {
                ((CommerceLoanCartContext) CommerceLoanCartServiceFragment.this.mCartContext).updateData(wishCommerceLoanCart, wishUserBillingInfo);
                CommerceLoanCartServiceFragment.this.checkGooglePayPaymentPreference(null, false);
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                CommerceLoanCartServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CartFragment>() {
                    public void performTask(final BaseActivity baseActivity, CartFragment cartFragment) {
                        cartFragment.handleCartLoadError();
                        baseActivity.startDialog(PromptDialogFragment.createErrorDialog(str), new BaseDialogCallback() {
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

    public void onDestroy() {
        super.onDestroy();
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }
    }
}
