package com.contextlogic.wish.payments.vault;

import android.os.Bundle;
import com.contextlogic.wish.api.model.WishShippingInfo;

public abstract class CartPaymentVaultProcessor {
    protected CartPaymentVaultProcessorServiceFragment mServiceFragment;

    public interface PrepareListener {
        void onTabPrepareCancelled(CartPaymentVaultProcessor cartPaymentVaultProcessor);

        void onTabPrepareFailed(CartPaymentVaultProcessor cartPaymentVaultProcessor, String str);

        void onTabPrepared(CartPaymentVaultProcessor cartPaymentVaultProcessor);
    }

    public interface SaveListener {
        void onSaveComplete(CartPaymentVaultProcessor cartPaymentVaultProcessor);

        void onSaveFailed(CartPaymentVaultProcessor cartPaymentVaultProcessor, String str);
    }

    public abstract void prepareTab(PrepareListener prepareListener);

    public abstract void save(SaveListener saveListener, Bundle bundle);

    public CartPaymentVaultProcessor(CartPaymentVaultProcessorServiceFragment cartPaymentVaultProcessorServiceFragment) {
        this.mServiceFragment = cartPaymentVaultProcessorServiceFragment;
    }

    /* access modifiers changed from: protected */
    public WishShippingInfo parseBillingAddress(Bundle bundle) {
        WishShippingInfo wishShippingInfo = new WishShippingInfo();
        if (bundle.getString("ParamName") != null) {
            wishShippingInfo.setName(bundle.getString("ParamName"));
        }
        if (bundle.getString("paramAddressLineOne") != null) {
            wishShippingInfo.setStreetAddressLineOne(bundle.getString("paramAddressLineOne"));
        }
        if (bundle.getString("paramAddressLineTwo") != null) {
            wishShippingInfo.setStreetAddressLineTwo(bundle.getString("paramAddressLineTwo"));
        }
        if (bundle.getString("paramCity") != null) {
            wishShippingInfo.setCity(bundle.getString("paramCity"));
        }
        if (bundle.getString("paramZip") != null) {
            wishShippingInfo.setZipCode(bundle.getString("paramZip"));
        }
        if (bundle.getString("ParamPhone") != null) {
            wishShippingInfo.setPhoneNumber(bundle.getString("ParamPhone"));
        }
        if (bundle.getString("paramCountry") != null) {
            wishShippingInfo.setCountryCode(bundle.getString("paramCountry"));
        }
        if (bundle.getString("ParamState") != null) {
            wishShippingInfo.setState(bundle.getString("ParamState"));
        }
        return wishShippingInfo;
    }
}
