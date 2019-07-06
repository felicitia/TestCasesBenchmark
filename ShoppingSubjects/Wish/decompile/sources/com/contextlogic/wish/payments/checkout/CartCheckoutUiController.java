package com.contextlogic.wish.payments.checkout;

import com.contextlogic.wish.api.model.WishCart.PaymentMode;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetCartService.SuccessCallback;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.FailureListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.SuccessListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessorServiceFragmentTask;
import java.util.ArrayList;

public interface CartCheckoutUiController {
    boolean couldLeadToFreeGiftClaimedDialog();

    boolean couldLeadToOrderConfirmationPage();

    void handleInvalidCommerceLoan();

    void hideLoadingSpinner();

    void refreshCart(SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback);

    void showBillingRedirectDialog(String str, String str2, ArrayList<PaymentMode> arrayList, ArrayList<String> arrayList2);

    void showBillingView(boolean z);

    void showBrowser(String str, boolean z, boolean z2);

    void showConfirmCVVDialog(SuccessListener successListener, FailureListener failureListener, CartPaymentProcessor cartPaymentProcessor);

    void showErrorMessage(String str);

    void showExternalBrowser(String str);

    void showItemsView();

    void showOrderConfirmedActivity(String str, String str2);

    void showShippingView(boolean z);

    void showUserBlockedDialog();

    void withCartPaymentProcessorServiceFragment(CartPaymentProcessorServiceFragmentTask cartPaymentProcessorServiceFragmentTask);
}
