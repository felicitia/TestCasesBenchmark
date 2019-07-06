package com.google.android.gms.wallet;

import android.app.Activity;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.Wallet.WalletOptions;

public class PaymentsClient extends GoogleApi<WalletOptions> {
    PaymentsClient(Activity activity, WalletOptions walletOptions) {
        super(activity, Wallet.API, walletOptions, Settings.DEFAULT_SETTINGS);
    }

    public Task<Boolean> isReadyToPay(IsReadyToPayRequest isReadyToPayRequest) {
        return doRead((TaskApiCall<A, TResult>) new zzai<A,TResult>(this, isReadyToPayRequest));
    }

    public Task<PaymentData> loadPaymentData(PaymentDataRequest paymentDataRequest) {
        return doWrite((TaskApiCall<A, TResult>) new zzaj<A,TResult>(this, paymentDataRequest));
    }
}
