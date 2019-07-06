package com.contextlogic.wish.payments.vault;

import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.payments.CartContext;

public final class CartPaymentVaultProcessorSelector {
    public static CartPaymentVaultProcessor getPaymentProcessor(CartBillingSection cartBillingSection, CartContext cartContext, CartPaymentVaultProcessorServiceFragment cartPaymentVaultProcessorServiceFragment) {
        switch (cartBillingSection) {
            case CREDIT_CARD:
                return getCreditCardPaymentProcessor(cartContext.getPaymentProcessor(), cartPaymentVaultProcessorServiceFragment);
            case KLARNA:
                return new KlarnaNativePaymentVaultProcessor(cartPaymentVaultProcessorServiceFragment);
            case GOOGLE_PAY:
                return new GooglePayPaymentVaultProcessor(cartPaymentVaultProcessorServiceFragment);
            case BOLETO:
                return new BoletoPaymentVaultProcessor(cartPaymentVaultProcessorServiceFragment);
            case OXXO:
                return new OxxoPaymentVaultProcessor(cartPaymentVaultProcessorServiceFragment);
            case PAYPAL:
                if (ExperimentDataCenter.getInstance().canCheckoutWithFuturePayPal(cartContext)) {
                    return new FuturePayPalPaymentVaultProcessor(cartPaymentVaultProcessorServiceFragment);
                }
                return new PayPalPaymentVaultProcessor(cartPaymentVaultProcessorServiceFragment);
            case IDEAL:
                return new IdealPaymentVaultProcessor(cartPaymentVaultProcessorServiceFragment);
            case COMMERCE_LOAN:
                return new CommerceLoanPaymentVaultProcessor(cartPaymentVaultProcessorServiceFragment);
            case PAYTM:
                return new PaytmPaymentVaultProcessor(cartPaymentVaultProcessorServiceFragment);
            default:
                return null;
        }
    }

    public static CartPaymentVaultProcessor getCreditCardPaymentProcessor(PaymentProcessor paymentProcessor, CartPaymentVaultProcessorServiceFragment cartPaymentVaultProcessorServiceFragment) {
        if (paymentProcessor == PaymentProcessor.Stripe) {
            return new StripeCreditCardPaymentVaultProcessor(cartPaymentVaultProcessorServiceFragment);
        }
        if (paymentProcessor == PaymentProcessor.Braintree) {
            return new BraintreeCreditCardPaymentVaultProcessor(cartPaymentVaultProcessorServiceFragment);
        }
        if (paymentProcessor == PaymentProcessor.Adyen) {
            return new AdyenCreditCardPaymentVaultProcessor(cartPaymentVaultProcessorServiceFragment);
        }
        if (paymentProcessor == PaymentProcessor.Ebanx) {
            return new EbanxCreditCardPaymentVaultProcessor(cartPaymentVaultProcessorServiceFragment);
        }
        return null;
    }
}
