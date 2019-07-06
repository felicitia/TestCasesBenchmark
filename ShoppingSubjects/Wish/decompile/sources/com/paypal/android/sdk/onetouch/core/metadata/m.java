package com.paypal.android.sdk.onetouch.core.metadata;

public enum m {
    UNKNOWN(0),
    PAYPAL(10),
    EBAY(11),
    MSDK(12);
    
    private int e;

    private m(int i) {
        this.e = i;
    }

    public final int a() {
        return this.e;
    }
}
