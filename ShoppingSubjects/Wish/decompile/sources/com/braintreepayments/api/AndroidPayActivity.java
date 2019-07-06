package com.braintreepayments.api;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.Wallet.WalletOptions;

@Deprecated
public class AndroidPayActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener {
    private GoogleApiClient mGoogleApiClient;

    public void onConnected(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setupGoogleApiClient();
        if (bundle == null || !bundle.getBoolean("com.braintreepayments.api.EXTRA_RECREATING")) {
            int intExtra = getIntent().getIntExtra("com.braintreepayments.api.EXTRA_REQUEST_TYPE", -1);
            switch (intExtra) {
                case 1:
                    loadMaskedWallet();
                    break;
                case 2:
                    changeMaskedWallet();
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("EXTRA_REQUEST_TYPE contained an unexpected type: ");
                    sb.append(intExtra);
                    setResult(2, new Intent().putExtra("com.braintreepayments.api.EXTRA_ERROR", sb.toString()));
                    finish();
                    break;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("com.braintreepayments.api.EXTRA_RECREATING", true);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mGoogleApiClient.disconnect();
    }

    private void loadMaskedWallet() {
        Wallet.Payments.loadMaskedWallet(this.mGoogleApiClient, MaskedWalletRequest.newBuilder().setMerchantName(getIntent().getStringExtra("com.braintreepayments.api.EXTRA_MERCHANT_NAME")).setCurrencyCode(getCart().getCurrencyCode()).setCart(getCart()).setEstimatedTotalPrice(getCart().getTotalPrice()).setShippingAddressRequired(getIntent().getBooleanExtra("com.braintreepayments.api.EXTRA_SHIPPING_ADDRESS_REQUIRED", false)).setPhoneNumberRequired(getIntent().getBooleanExtra("com.braintreepayments.api.EXTRA_PHONE_NUMBER_REQUIRED", false)).setPaymentMethodTokenizationParameters((PaymentMethodTokenizationParameters) getIntent().getParcelableExtra("com.braintreepayments.api.EXTRA_TOKENIZATION_PARAMETERS")).addAllowedCardNetworks(getIntent().getIntegerArrayListExtra("com.braintreepayments.api.EXTRA_ALLOWED_CARD_NETWORKS")).addAllowedCountrySpecificationsForShipping(getIntent().getParcelableArrayListExtra("com.braintreepayments.api.EXTRA_ALLOWED_COUNTRIES")).build(), 1);
    }

    private void changeMaskedWallet() {
        Wallet.Payments.changeMaskedWallet(this.mGoogleApiClient, getIntent().getStringExtra("com.braintreepayments.api.EXTRA_GOOGLE_TRANSACTION_ID"), null, 2);
    }

    private void loadFullWallet(String str) {
        Wallet.Payments.loadFullWallet(this.mGoogleApiClient, FullWalletRequest.newBuilder().setCart(getCart()).setGoogleTransactionId(str).build(), 3);
    }

    private void setupGoogleApiClient() {
        this.mGoogleApiClient = new Builder(this).addApi(Wallet.API, new WalletOptions.Builder().setEnvironment(getIntent().getIntExtra("com.braintreepayments.api.EXTRA_ENVIRONMENT", 3)).setTheme(1).build()).build();
        this.mGoogleApiClient.registerConnectionCallbacks(this);
        this.mGoogleApiClient.registerConnectionFailedListener(this);
        this.mGoogleApiClient.connect();
    }

    public void onConnectionSuspended(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection suspended: ");
        sb.append(i);
        setResult(2, new Intent().putExtra("com.braintreepayments.api.EXTRA_ERROR", sb.toString()));
        finish();
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection failed. ");
        sb.append(connectionResult.getErrorMessage());
        sb.append(". Code: ");
        sb.append(connectionResult.getErrorCode());
        setResult(2, new Intent().putExtra("com.braintreepayments.api.EXTRA_ERROR", sb.toString()));
        finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && (i == 1 || i == 2)) {
            loadFullWallet(((MaskedWallet) intent.getParcelableExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET")).getGoogleTransactionId());
        } else if (i2 == -1 && i == 3) {
            intent.putExtra("com.braintreepayments.api.EXTRA_CART", getCart());
            setResult(i2, intent);
            finish();
        } else {
            setResult(i2, intent);
            finish();
        }
    }

    private Cart getCart() {
        return (Cart) getIntent().getParcelableExtra("com.braintreepayments.api.EXTRA_CART");
    }

    public void finish() {
        super.finish();
        overridePendingTransition(17432576, 17432577);
    }
}
