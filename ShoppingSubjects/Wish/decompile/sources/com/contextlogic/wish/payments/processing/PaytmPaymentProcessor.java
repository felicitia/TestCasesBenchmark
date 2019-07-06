package com.contextlogic.wish.payments.processing;

import android.app.Activity;
import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.service.standalone.InitiatePaytmPaymentService;
import com.contextlogic.wish.api.service.standalone.InitiatePaytmPaymentService.FailureCallback;
import com.contextlogic.wish.api.service.standalone.InitiatePaytmPaymentService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.VerifyPaytmPaymentService;
import com.contextlogic.wish.application.ActivityLifeCycleCallbackManager;
import com.contextlogic.wish.application.ActivityLifeCycleCallbackManager.ActivityLifeCycleEventListener;
import com.contextlogic.wish.application.ActivityLifeCycleCallbackManager.EventType;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.FailureListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.PaymentContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.SuccessListener;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGActivity;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import java.util.HashMap;
import java.util.Map;

public class PaytmPaymentProcessor extends CartPaymentProcessor {
    private InitiatePaytmPaymentService mInitiatePaytmPaymentService = new InitiatePaytmPaymentService();
    /* access modifiers changed from: private */
    public VerifyPaytmPaymentService mVerifyPaytmPaymentService = new VerifyPaytmPaymentService();

    public PaytmPaymentProcessor(CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment) {
        super(cartPaymentProcessorServiceFragment);
    }

    private SuccessCallback createInitiatePaytmPaymentSuccessCallback(final HashMap<String, String> hashMap, SuccessListener successListener, FailureListener failureListener) {
        final PaytmPaymentTransactionCallback createPaytmPaymentTransactionCallback = createPaytmPaymentTransactionCallback(hashMap, successListener, failureListener);
        return new SuccessCallback() {
            public void onSuccess(Map<String, String> map) {
                final PaytmPGService paytmPGService;
                PaytmPaymentProcessor.this.handleSuccessfulPayment();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_PAYTM_INITIATE_PAYMENT_SUCCESS, hashMap);
                final boolean parseBoolean = Boolean.parseBoolean((String) map.get("is_prod"));
                map.remove("is_prod");
                if (parseBoolean) {
                    paytmPGService = PaytmPGService.getProductionService();
                } else {
                    paytmPGService = PaytmPGService.getStagingService();
                }
                final PaytmOrder paytmOrder = new PaytmOrder(map);
                paytmPGService.initialize(paytmOrder, null);
                PaytmPaymentProcessor.this.mServiceFragment.withActivity(new ActivityTask<CartActivity>() {
                    public void performTask(CartActivity cartActivity) {
                        ActivityLifeCycleCallbackManager.getInstance().addActivityLifeCycleEventListener(new ActivityLifeCycleEventListener() {
                            public void onActivityLifecycleEvent(EventType eventType, Activity activity, Bundle bundle) {
                                if (eventType == EventType.CREATED && (activity instanceof PaytmPGActivity) && bundle != null) {
                                    (parseBoolean ? PaytmPGService.getProductionService() : PaytmPGService.getStagingService()).initialize(paytmOrder, null);
                                }
                            }
                        });
                        paytmPGService.startPaymentTransaction(cartActivity, true, true, createPaytmPaymentTransactionCallback);
                    }
                });
            }
        };
    }

    private FailureCallback createInitiatePaytmPaymentFailureCallback(final HashMap<String, String> hashMap, SuccessListener successListener, final FailureListener failureListener) {
        return new FailureCallback() {
            public void onFailure(String str, int i) {
                PaytmPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_PAYTM_INITIATE_PAYMENT_FAILURE, hashMap);
                if (str == null) {
                    str = WishApplication.getInstance().getString(R.string.paytm_payment_error);
                }
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.errorMessage = str;
                paymentContext.errorCode = i;
                failureListener.onFailure(this, paymentContext);
            }
        };
    }

    private PaytmPaymentTransactionCallback createPaytmPaymentTransactionCallback(HashMap<String, String> hashMap, SuccessListener successListener, FailureListener failureListener) {
        final HashMap<String, String> hashMap2 = hashMap;
        final FailureListener failureListener2 = failureListener;
        final SuccessListener successListener2 = successListener;
        AnonymousClass3 r0 = new PaytmPaymentTransactionCallback() {
            public void someUIErrorOccurred(String str) {
                if (str == null) {
                    str = WishApplication.getInstance().getString(R.string.paytm_payment_error);
                }
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_PAYTM_UI_ERROR, hashMap2);
                PaytmPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.errorMessage = str;
                failureListener2.onFailure(this, paymentContext);
            }

            public void onTransactionResponse(Bundle bundle) {
                int parseInt = Integer.parseInt(bundle.getString("RESPCODE"));
                if (parseInt == 14111 || parseInt == 14112 || parseInt == 810) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_PAYTM_CANCEL, hashMap2);
                    failureListener2.onCancel(this);
                    return;
                }
                PaytmPaymentProcessor.this.mVerifyPaytmPaymentService.requestService(bundle, new VerifyPaytmPaymentService.SuccessCallback() {
                    public void onSuccess(String str) {
                        PaytmPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_PAYTM_COMPLETE_PAYMENT_SUCCESS, hashMap2);
                        PaymentContext paymentContext = new PaymentContext();
                        paymentContext.transactionId = str;
                        successListener2.onSuccess(this, paymentContext);
                    }
                }, new VerifyPaytmPaymentService.FailureCallback() {
                    public void onFailure(String str, int i) {
                        PaytmPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_PAYTM_COMPLETE_PAYMENT_FAILURE, hashMap2);
                        if (str == null) {
                            str = WishApplication.getInstance().getString(R.string.paytm_payment_error);
                        }
                        PaymentContext paymentContext = new PaymentContext();
                        paymentContext.errorMessage = str;
                        paymentContext.errorCode = i;
                        failureListener2.onFailure(this, paymentContext);
                    }
                });
            }

            public void networkNotAvailable() {
                String string = WishApplication.getInstance().getString(R.string.device_lost_network);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_PAYTM_NETWORK_UNAVAILABLE, hashMap2);
                PaytmPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.errorMessage = string;
                failureListener2.onFailure(this, paymentContext);
            }

            public void clientAuthenticationFailed(String str) {
                if (str == null) {
                    str = WishApplication.getInstance().getString(R.string.paytm_payment_error);
                }
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_PAYTM_CLIENT_AUTH_FAILURE, hashMap2);
                PaytmPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.errorMessage = str;
                failureListener2.onFailure(this, paymentContext);
            }

            public void onErrorLoadingWebPage(int i, String str, String str2) {
                if (str == null) {
                    str = WishApplication.getInstance().getString(R.string.paytm_payment_error);
                }
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_PAYTM_PAGE_LOADING_ERROR, hashMap2);
                PaytmPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.errorMessage = str;
                failureListener2.onFailure(this, paymentContext);
            }

            public void onBackPressedCancelTransaction() {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_PAYTM_CANCEL, hashMap2);
                failureListener2.onCancel(this);
            }
        };
        return r0;
    }

    public void checkout(SuccessListener successListener, FailureListener failureListener) {
        HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYTM_PLACE_ORDER, hashMap);
        this.mServiceFragment.showLoadingSpinner();
        this.mInitiatePaytmPaymentService.requestService(createInitiatePaytmPaymentSuccessCallback(hashMap, successListener, failureListener), createInitiatePaytmPaymentFailureCallback(hashMap, successListener, failureListener));
    }
}
