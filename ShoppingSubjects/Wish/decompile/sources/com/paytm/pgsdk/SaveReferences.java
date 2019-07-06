package com.paytm.pgsdk;

public class SaveReferences {
    private static SaveReferences keepCallbackReference;
    private boolean isProduction;
    private PaytmPaymentTransactionCallback paytmPaymentTransactionCallback;

    private SaveReferences() {
    }

    public static synchronized SaveReferences getInstance() {
        SaveReferences saveReferences;
        synchronized (SaveReferences.class) {
            if (keepCallbackReference == null) {
                keepCallbackReference = new SaveReferences();
            }
            saveReferences = keepCallbackReference;
        }
        return saveReferences;
    }

    public PaytmPaymentTransactionCallback getPaytmPaymentTransactionCallback() {
        return this.paytmPaymentTransactionCallback;
    }

    public void setPaytmPaymentTransactionCallback(PaytmPaymentTransactionCallback paytmPaymentTransactionCallback2) {
        this.paytmPaymentTransactionCallback = paytmPaymentTransactionCallback2;
    }

    public boolean isProduction() {
        return this.isProduction;
    }

    public void setProduction(boolean z) {
        this.isProduction = z;
    }
}
