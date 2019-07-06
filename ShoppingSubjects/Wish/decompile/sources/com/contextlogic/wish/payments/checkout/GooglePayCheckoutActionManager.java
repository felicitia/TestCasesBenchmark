package com.contextlogic.wish.payments.checkout;

import android.content.Intent;
import com.braintreepayments.api.BraintreeFragment;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.braintree.BraintreeFragmentCallback;
import com.contextlogic.wish.payments.checkout.CartCheckoutActionManager.CheckoutButtonContext;
import com.contextlogic.wish.payments.checkout.CartCheckoutActionManager.CheckoutButtonContext.CheckoutButtonMode;
import com.contextlogic.wish.payments.google.GooglePayManager;
import com.contextlogic.wish.payments.processing.CartPaymentProcessorServiceFragment;
import com.contextlogic.wish.payments.processing.CartPaymentProcessorServiceFragmentTask;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import java.util.HashMap;

public class GooglePayCheckoutActionManager extends CartCheckoutActionManager {
    public boolean alwaysShowPaymentCredentials() {
        return true;
    }

    public boolean canShowPaymentCredentials() {
        return true;
    }

    public GooglePayCheckoutActionManager(CartContext cartContext) {
        super(cartContext);
    }

    public CheckoutButtonContext getCheckoutButtonContext() {
        return new CheckoutButtonContext() {
            public boolean allowExpressCheckout() {
                return true;
            }

            public String getCheckoutButtonText() {
                return WishApplication.getInstance().getString(R.string.place_order);
            }

            public CheckoutButtonMode getCheckoutButtonMode() {
                if (GooglePayCheckoutActionManager.this.mCartContext.getGooglePayPaymentData() == null) {
                    return CheckoutButtonMode.GOOGLE_PAY;
                }
                return CheckoutButtonMode.BUTTON;
            }

            public String getCartAbandonModalActionText() {
                if (GooglePayCheckoutActionManager.this.mCartContext.getGooglePayPaymentData() != null) {
                    return WishApplication.getInstance().getString(R.string.place_order);
                }
                return WishApplication.getInstance().getString(R.string.checkout);
            }
        };
    }

    public void checkout(final CartCheckoutUiController cartCheckoutUiController, final boolean z) {
        cartCheckoutUiController.withCartPaymentProcessorServiceFragment(new CartPaymentProcessorServiceFragmentTask() {
            public void performTask(final CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment) {
                if (cartPaymentProcessorServiceFragment.getCartContext().getGooglePayPaymentData() == null) {
                    cartPaymentProcessorServiceFragment.showLoadingSpinner();
                    final HashMap hashMap = new HashMap();
                    hashMap.put("cart_type", cartPaymentProcessorServiceFragment.getCartContext().getCartType().toString());
                    cartPaymentProcessorServiceFragment.withBraintreeFragment(new BraintreeFragmentCallback() {
                        public void onBraintreeFragmentLoaded(BraintreeFragment braintreeFragment) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_GWALLET_FETCH, hashMap);
                            final PaymentDataRequest createPaymentDataRequest = GooglePayManager.getInstance().createPaymentDataRequest(cartPaymentProcessorServiceFragment.getCartContext(), braintreeFragment);
                            cartPaymentProcessorServiceFragment.withActivity(new ActivityTask<BaseActivity>() {
                                public void performTask(BaseActivity baseActivity) {
                                    baseActivity.getServiceFragment().loadPaymentData(createPaymentDataRequest, baseActivity.addResultCodeCallback(new ActivityResultCallback() {
                                        public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                                            if (i2 == -1) {
                                                PaymentData fromIntent = PaymentData.getFromIntent(intent);
                                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_GWALLET_FETCH_SUCCESS, hashMap);
                                                cartPaymentProcessorServiceFragment.hideLoadingSpinner();
                                                if (fromIntent == null) {
                                                    Crashlytics.logException(new Exception("Google Pay loadPaymentData returned null payment data with RESULT_OK"));
                                                    return;
                                                }
                                                cartPaymentProcessorServiceFragment.getCartContext().updateGooglePayPaymentData(fromIntent);
                                                cartPaymentProcessorServiceFragment.getCartContext().updatePreferredPaymentMode("PaymentModeGoogle");
                                                cartCheckoutUiController.showItemsView();
                                                if (z) {
                                                    cartPaymentProcessorServiceFragment.getCartContext().getCheckoutActionManager().checkout(cartCheckoutUiController, false);
                                                }
                                            } else if (i2 == 0) {
                                                cartPaymentProcessorServiceFragment.hideLoadingSpinner();
                                            } else {
                                                cartPaymentProcessorServiceFragment.hideLoadingSpinner();
                                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_GWALLET_FETCH_FAILURE, hashMap);
                                                cartCheckoutUiController.showErrorMessage(GooglePayManager.getInstance().getGooglePayErrorMessage(intent.getIntExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", 413)));
                                            }
                                        }
                                    }));
                                }
                            });
                        }

                        public void onBraintreeFragmentLoadFailed(String str) {
                            cartPaymentProcessorServiceFragment.hideLoadingSpinner();
                            if (str == null) {
                                str = WishApplication.getInstance().getString(R.string.general_payment_error);
                            }
                            cartCheckoutUiController.showErrorMessage(str);
                        }
                    });
                    return;
                }
                cartCheckoutUiController.showItemsView();
                if (z) {
                    cartPaymentProcessorServiceFragment.getCartContext().getCheckoutActionManager().checkout(cartCheckoutUiController, false);
                }
            }
        });
    }
}
