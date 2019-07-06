package com.contextlogic.wish.payments.vault;

import android.os.Bundle;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.PrepareListener;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.SaveListener;

public class KlarnaNativePaymentVaultProcessor extends CartPaymentVaultProcessor {
    public KlarnaNativePaymentVaultProcessor(CartPaymentVaultProcessorServiceFragment cartPaymentVaultProcessorServiceFragment) {
        super(cartPaymentVaultProcessorServiceFragment);
    }

    public void prepareTab(PrepareListener prepareListener) {
        prepareListener.onTabPrepared(this);
    }

    public void save(SaveListener saveListener, Bundle bundle) {
        saveListener.onSaveComplete(this);
    }
}
