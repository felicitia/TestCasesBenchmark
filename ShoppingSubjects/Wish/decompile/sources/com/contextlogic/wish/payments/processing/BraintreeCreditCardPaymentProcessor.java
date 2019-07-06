package com.contextlogic.wish.payments.processing;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.DataCollector;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.service.standalone.InitiateBraintreePaymentService;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.braintree.BraintreeFragmentCallback;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.FailureListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.PaymentContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.SuccessListener;
import java.util.HashMap;

public class BraintreeCreditCardPaymentProcessor extends CartPaymentProcessor {
    /* access modifiers changed from: private */
    public InitiateBraintreePaymentService mInitiateBraintreePaymentService = new InitiateBraintreePaymentService();

    public BraintreeCreditCardPaymentProcessor(CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment) {
        super(cartPaymentProcessorServiceFragment);
    }

    public void checkout(SuccessListener successListener, FailureListener failureListener) {
        HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PLACE_ORDER, hashMap);
        checkoutWithCVV(successListener, failureListener, null);
    }

    public void checkoutWithCVV(SuccessListener successListener, FailureListener failureListener, String str) {
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        this.mServiceFragment.showLoadingSpinner();
        CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment = this.mServiceFragment;
        final String str2 = str;
        final SuccessListener successListener2 = successListener;
        final FailureListener failureListener2 = failureListener;
        AnonymousClass1 r0 = new BraintreeFragmentCallback() {
            public void onBraintreeFragmentLoaded(BraintreeFragment braintreeFragment) {
                DataCollector.collectDeviceData(braintreeFragment, new BraintreeResponseListener<String>() {
                    /* JADX WARNING: Removed duplicated region for block: B:20:0x0075  */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void onResponse(java.lang.String r13) {
                        /*
                            r12 = this;
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor$1 r0 = com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor.AnonymousClass1.this
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor r0 = com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor.this
                            com.contextlogic.wish.payments.processing.CartPaymentProcessorServiceFragment r0 = r0.mServiceFragment
                            com.contextlogic.wish.payments.CartContext r0 = r0.getCartContext()
                            com.contextlogic.wish.api.model.WishUserBillingInfo r0 = r0.getUserBillingInfo()
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor$1 r1 = com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor.AnonymousClass1.this
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor r1 = com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor.this
                            com.contextlogic.wish.payments.processing.CartPaymentProcessorServiceFragment r1 = r1.mServiceFragment
                            com.contextlogic.wish.payments.CartContext r1 = r1.getCartContext()
                            r2 = 0
                            if (r0 == 0) goto L_0x006e
                            com.contextlogic.wish.api.model.WishCart$PaymentProcessor r3 = r1.getPaymentProcessor()
                            com.contextlogic.wish.api.model.WishCreditCardInfo r3 = r0.getDefaultCreditCardInfo(r3)
                            if (r3 == 0) goto L_0x006e
                            com.contextlogic.wish.payments.braintree.BraintreeManager r3 = com.contextlogic.wish.payments.braintree.BraintreeManager.getInstance()
                            com.contextlogic.wish.api.model.WishCachedBillingInfo r3 = r3.getCachedBillingInfo()
                            if (r3 == 0) goto L_0x006e
                            com.contextlogic.wish.api.model.WishCart r3 = r1.getCart()
                            if (r3 != 0) goto L_0x003b
                            com.contextlogic.wish.api.model.WishCommerceCashCart r3 = r1.getCommerceCashCart()
                            if (r3 == 0) goto L_0x006e
                        L_0x003b:
                            com.contextlogic.wish.api.model.WishCart$PaymentProcessor r1 = r1.getPaymentProcessor()
                            com.contextlogic.wish.api.model.WishCreditCardInfo r0 = r0.getDefaultCreditCardInfo(r1)
                            com.contextlogic.wish.payments.braintree.BraintreeManager r1 = com.contextlogic.wish.payments.braintree.BraintreeManager.getInstance()
                            com.contextlogic.wish.api.model.WishCachedBillingInfo r1 = r1.getCachedBillingInfo()
                            java.lang.String r3 = r0.getLastFourDigits()
                            if (r3 == 0) goto L_0x006e
                            java.lang.String r3 = r1.getLastFourDigits()
                            if (r3 == 0) goto L_0x006e
                            java.lang.String r0 = r0.getLastFourDigits()
                            java.lang.String r3 = r1.getLastFourDigits()
                            boolean r0 = r0.equals(r3)
                            if (r0 == 0) goto L_0x006e
                            java.lang.String r0 = r1.getCardNonce()
                            r1.clearNonce()
                            r5 = r0
                            goto L_0x006f
                        L_0x006e:
                            r5 = r2
                        L_0x006f:
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor$1 r0 = com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor.AnonymousClass1.this
                            java.lang.String r0 = r2
                            if (r0 == 0) goto L_0x0090
                            com.braintreegateway.encryption.Braintree r0 = new com.braintreegateway.encryption.Braintree
                            com.contextlogic.wish.api.datacenter.ConfigDataCenter r1 = com.contextlogic.wish.api.datacenter.ConfigDataCenter.getInstance()
                            com.contextlogic.wish.api.model.WishPaymentProcessorData r1 = r1.getPaymentProcessorData()
                            java.lang.String r1 = r1.getBraintreeKey()
                            r0.<init>(r1)
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor$1 r1 = com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor.AnonymousClass1.this     // Catch:{ Throwable -> 0x0090 }
                            java.lang.String r1 = r2     // Catch:{ Throwable -> 0x0090 }
                            java.lang.String r0 = r0.encrypt(r1)     // Catch:{ Throwable -> 0x0090 }
                            r6 = r0
                            goto L_0x0091
                        L_0x0090:
                            r6 = r2
                        L_0x0091:
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor$1 r0 = com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor.AnonymousClass1.this
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor r0 = com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor.this
                            com.contextlogic.wish.api.service.standalone.InitiateBraintreePaymentService r3 = r0.mInitiateBraintreePaymentService
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor$1 r0 = com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor.AnonymousClass1.this
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor r0 = com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor.this
                            com.contextlogic.wish.payments.processing.CartPaymentProcessorServiceFragment r0 = r0.mServiceFragment
                            com.contextlogic.wish.payments.CartContext r0 = r0.getCartContext()
                            java.lang.String r4 = r0.getCurrencyCode()
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor$1 r0 = com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor.AnonymousClass1.this
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor r0 = com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor.this
                            com.contextlogic.wish.payments.processing.CartPaymentProcessorServiceFragment r0 = r0.mServiceFragment
                            com.contextlogic.wish.payments.CartContext r0 = r0.getCartContext()
                            java.lang.String r7 = r0.getCheckoutOfferId()
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor$1 r0 = com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor.AnonymousClass1.this
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor r0 = com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor.this
                            com.contextlogic.wish.payments.processing.CartPaymentProcessorServiceFragment r0 = r0.mServiceFragment
                            com.contextlogic.wish.payments.CartContext r0 = r0.getCartContext()
                            com.contextlogic.wish.payments.CartContext$CartType r0 = r0.getCartType()
                            int r9 = r0.getValue()
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor$1$1$1 r10 = new com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor$1$1$1
                            r10.<init>()
                            com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor$1$1$2 r11 = new com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor$1$1$2
                            r11.<init>()
                            r8 = r13
                            r3.requestService(r4, r5, r6, r7, r8, r9, r10, r11)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.payments.processing.BraintreeCreditCardPaymentProcessor.AnonymousClass1.AnonymousClass1.onResponse(java.lang.String):void");
                    }
                });
            }

            public void onBraintreeFragmentLoadFailed(String str) {
                BraintreeCreditCardPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PLACE_ORDER_FAILURE, hashMap);
                if (str == null) {
                    str = WishApplication.getInstance().getString(R.string.general_payment_error);
                }
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.errorMessage = str;
                failureListener2.onFailure(this, paymentContext);
            }
        };
        cartPaymentProcessorServiceFragment.withBraintreeFragment(r0);
    }
}
