package com.contextlogic.wish.payments.google;

import com.contextlogic.wish.api.service.standalone.InitiateGooglePayStripePaymentService;

public class StripeGooglePayPaymentCollector extends GooglePayPaymentCollector {
    private InitiateGooglePayStripePaymentService mInitiateGooglePayStripePaymentService = new InitiateGooglePayStripePaymentService();

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void collectPayment(com.contextlogic.wish.payments.CartContext r9, com.google.android.gms.wallet.PaymentData r10, com.braintreepayments.api.BraintreeFragment r11, final com.contextlogic.wish.payments.google.GooglePayPaymentCollector.SuccessListener r12, final com.contextlogic.wish.payments.google.GooglePayPaymentCollector.FailureListener r13) {
        /*
            r8 = this;
            com.google.android.gms.wallet.PaymentMethodToken r11 = r10.getPaymentMethodToken()
            if (r11 == 0) goto L_0x0024
            com.google.android.gms.wallet.PaymentMethodToken r11 = r10.getPaymentMethodToken()
            java.lang.String r11 = r11.getToken()
            if (r11 == 0) goto L_0x0024
            org.json.JSONObject r11 = new org.json.JSONObject     // Catch:{ Exception -> 0x0024 }
            com.google.android.gms.wallet.PaymentMethodToken r0 = r10.getPaymentMethodToken()     // Catch:{ Exception -> 0x0024 }
            java.lang.String r0 = r0.getToken()     // Catch:{ Exception -> 0x0024 }
            r11.<init>(r0)     // Catch:{ Exception -> 0x0024 }
            java.lang.String r0 = "id"
            java.lang.String r11 = r11.getString(r0)     // Catch:{ Exception -> 0x0024 }
            goto L_0x0025
        L_0x0024:
            r11 = 0
        L_0x0025:
            r2 = r11
            if (r2 != 0) goto L_0x003e
            com.contextlogic.wish.application.WishApplication r9 = com.contextlogic.wish.application.WishApplication.getInstance()
            r10 = 2131755628(0x7f10026c, float:1.914214E38)
            java.lang.String r9 = r9.getString(r10)
            com.contextlogic.wish.payments.processing.CartPaymentProcessor$PaymentContext r10 = new com.contextlogic.wish.payments.processing.CartPaymentProcessor$PaymentContext
            r10.<init>()
            r10.errorMessage = r9
            r13.onFailure(r8, r10)
            return
        L_0x003e:
            com.contextlogic.wish.api.service.standalone.InitiateGooglePayStripePaymentService r0 = r8.mInitiateGooglePayStripePaymentService
            java.lang.String r1 = r9.getCurrencyCode()
            java.lang.String r4 = r9.getCheckoutOfferId()
            com.contextlogic.wish.payments.CartContext$CartType r9 = r9.getCartType()
            int r5 = r9.getValue()
            com.contextlogic.wish.payments.google.StripeGooglePayPaymentCollector$1 r6 = new com.contextlogic.wish.payments.google.StripeGooglePayPaymentCollector$1
            r6.<init>(r12, r8)
            com.contextlogic.wish.payments.google.StripeGooglePayPaymentCollector$2 r7 = new com.contextlogic.wish.payments.google.StripeGooglePayPaymentCollector$2
            r7.<init>(r13, r8)
            r3 = r10
            r0.requestService(r1, r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.payments.google.StripeGooglePayPaymentCollector.collectPayment(com.contextlogic.wish.payments.CartContext, com.google.android.gms.wallet.PaymentData, com.braintreepayments.api.BraintreeFragment, com.contextlogic.wish.payments.google.GooglePayPaymentCollector$SuccessListener, com.contextlogic.wish.payments.google.GooglePayPaymentCollector$FailureListener):void");
    }
}
