package com.contextlogic.wish.payments.checkout;

import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.api.model.WishCommerceLoanBannerSpec;
import com.contextlogic.wish.api.model.WishCommerceLoanTabSpec;
import com.contextlogic.wish.api.model.WishLoanRepaymentBannerSpec;
import com.contextlogic.wish.api.model.WishPaymentStructureSelectionSpec;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetCartService.SuccessCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ServerConfig;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.checkout.CartCheckoutActionManager.CheckoutButtonContext;
import com.contextlogic.wish.payments.checkout.CartCheckoutActionManager.CheckoutButtonContext.CheckoutButtonMode;
import com.contextlogic.wish.payments.processing.AdyenCreditCardPaymentProcessor;
import com.contextlogic.wish.payments.processing.BoletoPaymentProcessor;
import com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.FailureListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.PaymentContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.SuccessListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessorServiceFragment;
import com.contextlogic.wish.payments.processing.CartPaymentProcessorServiceFragmentTask;
import com.contextlogic.wish.payments.processing.CommerceLoanPaymentProcessor;
import com.contextlogic.wish.payments.processing.EbanxCreditCardPaymentProcessor;
import com.contextlogic.wish.payments.processing.FreeOrderPaymentProcessor;
import com.contextlogic.wish.payments.processing.FuturePayPalPaymentProcessor;
import com.contextlogic.wish.payments.processing.GooglePayPaymentProcessor;
import com.contextlogic.wish.payments.processing.IdealPaymentProcessor;
import com.contextlogic.wish.payments.processing.KlarnaPaymentProcessor;
import com.contextlogic.wish.payments.processing.OxxoPaymentProcessor;
import com.contextlogic.wish.payments.processing.PayPalPaymentProcessor;
import com.contextlogic.wish.payments.processing.PaytmPaymentProcessor;
import com.contextlogic.wish.payments.processing.StripeCreditCardPaymentProcessor;
import com.contextlogic.wish.util.PreferenceUtil;

public class PlaceOrderCheckoutActionManager extends CartCheckoutActionManager {
    /* access modifiers changed from: private */
    public boolean mIsCheckoutPending = false;

    public boolean canShowPaymentCredentials() {
        return true;
    }

    public PlaceOrderCheckoutActionManager(CartContext cartContext) {
        super(cartContext);
    }

    public CheckoutButtonContext getCheckoutButtonContext() {
        return new CheckoutButtonContext() {
            public boolean allowExpressCheckout() {
                return true;
            }

            public String getCheckoutButtonText() {
                if (getCheckoutButtonMode() == CheckoutButtonMode.SLIDER) {
                    return WishApplication.getInstance().getString(R.string.slide_to_pay);
                }
                return WishApplication.getInstance().getString(R.string.place_order);
            }

            public CheckoutButtonMode getCheckoutButtonMode() {
                String effectivePaymentMode = PlaceOrderCheckoutActionManager.this.mCartContext.getEffectivePaymentMode();
                return !PlaceOrderCheckoutActionManager.this.mCartContext.isFreeOrder() && (effectivePaymentMode.equals("PaymentModeBoleto") || effectivePaymentMode.equals("PaymentModeOxxo") || effectivePaymentMode.equals("PaymentModeCC") || effectivePaymentMode.equals("PaymentModeGoogle") || ((PlaceOrderCheckoutActionManager.this.mCartContext.hasValidBillingInfo() && effectivePaymentMode.equals("PaymentModeKlarna")) || (ExperimentDataCenter.getInstance().canCheckoutWithFuturePayPal(PlaceOrderCheckoutActionManager.this.mCartContext) && effectivePaymentMode.equals("PaymentModePayPal")))) ? CheckoutButtonMode.SLIDER : CheckoutButtonMode.BUTTON;
            }

            public String getCartAbandonModalActionText() {
                return WishApplication.getInstance().getString(R.string.place_order);
            }
        };
    }

    public boolean alwaysShowPaymentCredentials() {
        return this.mCartContext.getCommerceLoanCart() != null;
    }

    private boolean canCheckoutWithPayHalf() {
        return ExperimentDataCenter.getInstance().canSeePayHalfBillingOption() && this.mCartContext.getPaymentStructureSelectionSpec() != null && this.mCartContext.getPaymentStructureSelectionSpec().getCanPayLater() && this.mCartContext.getPayHalfLaterFlag();
    }

    /* access modifiers changed from: private */
    public CartPaymentProcessor getPaymentProcessor(CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment) {
        String effectivePaymentMode = this.mCartContext.getEffectivePaymentMode();
        if (effectivePaymentMode.equals("PaymentModeCC") && canCheckoutWithPayHalf()) {
            effectivePaymentMode = "PaymentModeCommerceLoan";
        }
        if (this.mCartContext.isFreeOrder()) {
            return new FreeOrderPaymentProcessor(cartPaymentProcessorServiceFragment);
        }
        if (effectivePaymentMode.equals("PaymentModeGoogle")) {
            return new GooglePayPaymentProcessor(cartPaymentProcessorServiceFragment);
        }
        if (effectivePaymentMode.equals("PaymentModeKlarna")) {
            return new KlarnaPaymentProcessor(cartPaymentProcessorServiceFragment);
        }
        if (effectivePaymentMode.equals("PaymentModePayPal")) {
            if (ExperimentDataCenter.getInstance().canCheckoutWithFuturePayPal(this.mCartContext)) {
                return new FuturePayPalPaymentProcessor(cartPaymentProcessorServiceFragment);
            }
            return new PayPalPaymentProcessor(cartPaymentProcessorServiceFragment);
        } else if (effectivePaymentMode.equals("PaymentModeBoleto")) {
            return new BoletoPaymentProcessor(cartPaymentProcessorServiceFragment);
        } else {
            if (effectivePaymentMode.equals("PaymentModeOxxo")) {
                return new OxxoPaymentProcessor(cartPaymentProcessorServiceFragment);
            }
            if (effectivePaymentMode.equals("PaymentModeIdeal")) {
                return new IdealPaymentProcessor(cartPaymentProcessorServiceFragment);
            }
            if (effectivePaymentMode.equals("PaymentModeCommerceLoan")) {
                return new CommerceLoanPaymentProcessor(cartPaymentProcessorServiceFragment);
            }
            if (effectivePaymentMode.equals("PaymentModePaytm")) {
                return new PaytmPaymentProcessor(cartPaymentProcessorServiceFragment);
            }
            if (effectivePaymentMode.equals("PaymentModeCC")) {
                if (this.mCartContext.getPaymentProcessor() == PaymentProcessor.Stripe) {
                    return new StripeCreditCardPaymentProcessor(cartPaymentProcessorServiceFragment);
                }
                if (this.mCartContext.getPaymentProcessor() == PaymentProcessor.Ebanx) {
                    return new EbanxCreditCardPaymentProcessor(cartPaymentProcessorServiceFragment);
                }
                if (this.mCartContext.getPaymentProcessor() == PaymentProcessor.Braintree) {
                    return new BraintreeCreditCardPaymentProcessor(cartPaymentProcessorServiceFragment);
                }
                if (this.mCartContext.getPaymentProcessor() == PaymentProcessor.Adyen) {
                    return new AdyenCreditCardPaymentProcessor(cartPaymentProcessorServiceFragment);
                }
            }
            return null;
        }
    }

    public void checkout(final CartCheckoutUiController cartCheckoutUiController, boolean z) {
        if (!this.mIsCheckoutPending) {
            this.mIsCheckoutPending = true;
            cartCheckoutUiController.withCartPaymentProcessorServiceFragment(new CartPaymentProcessorServiceFragmentTask() {
                public void performTask(CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment) {
                    CartPaymentProcessor access$000 = PlaceOrderCheckoutActionManager.this.getPaymentProcessor(cartPaymentProcessorServiceFragment);
                    final AnonymousClass1 r0 = new SuccessListener() {
                        public void onSuccess(CartPaymentProcessor cartPaymentProcessor, PaymentContext paymentContext) {
                            PlaceOrderCheckoutActionManager.this.mIsCheckoutPending = false;
                            cartCheckoutUiController.hideLoadingSpinner();
                            PreferenceUtil.setString("LastPaidPaymentMethod", PlaceOrderCheckoutActionManager.this.mCartContext.getEffectivePaymentMode());
                            PreferenceUtil.setLong("LastTransactionTime", System.currentTimeMillis());
                            if (!cartCheckoutUiController.couldLeadToOrderConfirmationPage() || paymentContext.transactionId == null) {
                                if (cartCheckoutUiController.couldLeadToFreeGiftClaimedDialog() && paymentContext.transactionId != null) {
                                    StatusDataCenter.getInstance().updateCartCount(0);
                                    StatusDataCenter.getInstance().refresh();
                                }
                                boolean z = cartPaymentProcessor instanceof KlarnaPaymentProcessor;
                                if (paymentContext.buyUrl != null) {
                                    if (paymentContext.showInExternalBrowser) {
                                        cartCheckoutUiController.showExternalBrowser(paymentContext.buyUrl);
                                    } else {
                                        cartCheckoutUiController.showBrowser(paymentContext.buyUrl, true, z);
                                    }
                                } else if (paymentContext.transactionId != null) {
                                    cartCheckoutUiController.showBrowser(String.format("https://%s/m/purchase-confirmation?tid=%s", new Object[]{ServerConfig.getInstance().getServerHost(), paymentContext.transactionId}), true, false);
                                }
                                return;
                            }
                            StatusDataCenter.getInstance().updateCartCount(0);
                            StatusDataCenter.getInstance().refresh();
                            cartCheckoutUiController.showOrderConfirmedActivity(paymentContext.transactionId, PlaceOrderCheckoutActionManager.this.mCartContext.getEffectivePaymentMode());
                        }
                    };
                    access$000.checkout(r0, new FailureListener() {
                        public void onFailure(CartPaymentProcessor cartPaymentProcessor, PaymentContext paymentContext) {
                            String str = paymentContext.errorMessage;
                            if (str == null) {
                                str = WishApplication.getInstance().getString(R.string.general_payment_error);
                            }
                            if (paymentContext.errorCode == 10) {
                                cartCheckoutUiController.hideLoadingSpinner();
                                cartCheckoutUiController.showUserBlockedDialog();
                            } else if (paymentContext.errorCode == 19) {
                                cartCheckoutUiController.showConfirmCVVDialog(r0, this, cartPaymentProcessor);
                            } else {
                                cartCheckoutUiController.hideLoadingSpinner();
                                if (paymentContext.errorCode == 18) {
                                    cartCheckoutUiController.showErrorMessage(str);
                                    cartCheckoutUiController.refreshCart(new SuccessCallback() {
                                        public void onSuccess(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishCommerceLoanTabSpec wishCommerceLoanTabSpec, WishCommerceLoanBannerSpec wishCommerceLoanBannerSpec, WishPaymentStructureSelectionSpec wishPaymentStructureSelectionSpec, WishLoanRepaymentBannerSpec wishLoanRepaymentBannerSpec) {
                                            PlaceOrderCheckoutActionManager.this.mCartContext.updateData(wishCart, wishShippingInfo, wishUserBillingInfo);
                                            cartCheckoutUiController.showBillingView(false);
                                        }
                                    }, new DefaultFailureCallback() {
                                        public void onFailure(String str) {
                                        }
                                    });
                                } else if (paymentContext.errorCode == 28) {
                                    cartCheckoutUiController.showErrorMessage(str);
                                    cartCheckoutUiController.refreshCart(new SuccessCallback() {
                                        public void onSuccess(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishCommerceLoanTabSpec wishCommerceLoanTabSpec, WishCommerceLoanBannerSpec wishCommerceLoanBannerSpec, WishPaymentStructureSelectionSpec wishPaymentStructureSelectionSpec, WishLoanRepaymentBannerSpec wishLoanRepaymentBannerSpec) {
                                            PlaceOrderCheckoutActionManager.this.mCartContext.updateData(wishCart, wishShippingInfo, wishUserBillingInfo);
                                            cartCheckoutUiController.showShippingView(false);
                                        }
                                    }, new DefaultFailureCallback() {
                                        public void onFailure(String str) {
                                        }
                                    });
                                } else if (paymentContext.declineRedirectInfo != null) {
                                    String redirectTitle = paymentContext.declineRedirectInfo.getRedirectTitle() != null ? paymentContext.declineRedirectInfo.getRedirectTitle() : WishApplication.getInstance().getString(R.string.oops);
                                    if (paymentContext.declineRedirectInfo.getRedirectSubtitle() != null) {
                                        str = paymentContext.declineRedirectInfo.getRedirectSubtitle();
                                    }
                                    cartCheckoutUiController.showBillingRedirectDialog(redirectTitle, str, paymentContext.declineRedirectInfo.getRedirectModes(), paymentContext.declineRedirectInfo.getRedirectButtonTitles());
                                } else {
                                    cartCheckoutUiController.showErrorMessage(str);
                                }
                            }
                            PlaceOrderCheckoutActionManager.this.mIsCheckoutPending = false;
                        }

                        public void onCancel(CartPaymentProcessor cartPaymentProcessor) {
                            cartCheckoutUiController.hideLoadingSpinner();
                            PlaceOrderCheckoutActionManager.this.mIsCheckoutPending = false;
                        }
                    });
                }
            });
        }
    }
}
