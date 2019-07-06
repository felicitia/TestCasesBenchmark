package com.contextlogic.wish.activity.cart.billing.paymentform;

import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.payments.CartContext;

public interface PaymentFormUiConnector {
    CartContext getCartContext();

    void handleFormComplete();

    void hideLoadingDialog();

    void loadGooglePayPaymentData();

    void openActivity(Class cls);

    void refreshNextButtonState();

    void selectBillingSection(CartBillingSection cartBillingSection, boolean z);

    void showBrowser(String str, boolean z, boolean z2);

    void showCommerceLoanCCBillingView();

    void showCreditCardPaymentFormView();

    void showErrorMessage(String str);

    void showItemsView();

    void showLoadingDialog();

    void withServiceProvider(PaymentFormServiceProviderTask paymentFormServiceProviderTask);
}
