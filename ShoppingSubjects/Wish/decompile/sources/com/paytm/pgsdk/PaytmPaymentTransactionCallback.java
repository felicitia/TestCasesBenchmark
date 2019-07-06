package com.paytm.pgsdk;

import android.os.Bundle;

public interface PaytmPaymentTransactionCallback {
    void clientAuthenticationFailed(String str);

    void networkNotAvailable();

    void onBackPressedCancelTransaction();

    void onErrorLoadingWebPage(int i, String str, String str2);

    void onTransactionResponse(Bundle bundle);

    void someUIErrorOccurred(String str);
}
