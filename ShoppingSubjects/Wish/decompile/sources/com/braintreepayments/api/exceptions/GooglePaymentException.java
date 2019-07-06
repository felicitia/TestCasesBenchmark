package com.braintreepayments.api.exceptions;

import com.google.android.gms.common.api.Status;

public class GooglePaymentException extends BraintreeException {
    private Status mStatus;

    public GooglePaymentException(String str, Status status) {
        super(str);
        this.mStatus = status;
    }
}
