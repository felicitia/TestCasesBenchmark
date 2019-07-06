package com.etsy.android.ui.user;

import com.etsy.android.lib.requests.ReceiptId;
import io.reactivex.functions.Consumer;

final /* synthetic */ class d implements Consumer {
    private final ReceiptFragment a;

    d(ReceiptFragment receiptFragment) {
        this.a = receiptFragment;
    }

    public void accept(Object obj) {
        this.a.lambda$getReceipt$0$ReceiptFragment((ReceiptId) obj);
    }
}
