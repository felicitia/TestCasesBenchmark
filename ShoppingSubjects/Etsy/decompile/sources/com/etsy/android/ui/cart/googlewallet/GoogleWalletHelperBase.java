package com.etsy.android.ui.cart.googlewallet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.etsy.android.R;
import com.etsy.android.lib.config.a;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.legacy.b;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayLineItemContract;
import com.etsy.android.lib.util.aj;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.identity.intents.model.UserAddress;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.LineItem;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.d;
import com.google.android.gms.wallet.d.a.C0148a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class GoogleWalletHelperBase implements ConnectionCallbacks, OnConnectionFailedListener {
    protected static final String a = f.a(GoogleWalletHelperBase.class);
    protected GoogleApiClient b;
    protected ConnectionResult c;
    protected b d;

    public enum ReadyState {
        UNKNOWN,
        READY,
        NOT_READY,
        CANCELED,
        INTERRUPTED
    }

    public void onConnectionSuspended(int i) {
    }

    public GoogleWalletHelperBase(Context context) {
        this.b = new Builder(context).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(d.a, new C0148a().a(d()).b(1).a()).build();
    }

    public void a() {
        this.b.connect();
    }

    public void a(b bVar) {
        this.d = bVar;
        a();
    }

    public boolean b() {
        return this.b.isConnected();
    }

    public void c() {
        this.b.disconnect();
    }

    public void onConnected(Bundle bundle) {
        this.c = null;
        if (this.d != null) {
            this.d.a();
        }
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.c = connectionResult;
        if (this.d != null) {
            this.d.b();
        }
    }

    static int d() {
        return (com.etsy.android.util.d.c() || a.a().c()) ? 3 : 1;
    }

    public static HashMap<String, String> a(String str, UserAddress userAddress) {
        HashMap<String, String> hashMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("[");
        sb.append(ResponseConstants.COUNTRY_CODE);
        sb.append("]");
        hashMap.put(sb.toString(), userAddress.getCountryCode());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("[");
        sb2.append(ResponseConstants.NAME);
        sb2.append("]");
        hashMap.put(sb2.toString(), userAddress.getName());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        sb3.append("[");
        sb3.append(ResponseConstants.FIRST_LINE);
        sb3.append("]");
        hashMap.put(sb3.toString(), userAddress.getAddress1());
        StringBuilder sb4 = new StringBuilder();
        sb4.append(str);
        sb4.append("[");
        sb4.append(ResponseConstants.SECOND_LINE);
        sb4.append("]");
        hashMap.put(sb4.toString(), userAddress.getAddress2());
        StringBuilder sb5 = new StringBuilder();
        sb5.append(str);
        sb5.append("[");
        sb5.append(ResponseConstants.CITY);
        sb5.append("]");
        hashMap.put(sb5.toString(), userAddress.getLocality());
        StringBuilder sb6 = new StringBuilder();
        sb6.append(str);
        sb6.append("[");
        sb6.append(ResponseConstants.STATE);
        sb6.append("]");
        hashMap.put(sb6.toString(), userAddress.getAdministrativeArea());
        StringBuilder sb7 = new StringBuilder();
        sb7.append(str);
        sb7.append("[");
        sb7.append(ResponseConstants.ZIP);
        sb7.append("]");
        hashMap.put(sb7.toString(), userAddress.getPostalCode());
        return hashMap;
    }

    public static MaskedWallet a(Intent intent) {
        if (intent != null) {
            return (MaskedWallet) intent.getParcelableExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET");
        }
        return null;
    }

    public static HashMap<String, String> a(MaskedWallet maskedWallet) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (maskedWallet != null) {
            hashMap.put("google_wallet[email]", maskedWallet.getEmail());
            hashMap.put("google_wallet[transaction_id]", maskedWallet.getGoogleTransactionId());
            for (String put : maskedWallet.getPaymentDescriptions()) {
                hashMap.put("google_wallet[payment][]", put);
            }
            hashMap.put("supports_android_pay", String.valueOf(true));
        }
        return hashMap;
    }

    public int b(Intent intent) {
        if (intent == null) {
            return -1;
        }
        return intent.getIntExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", -1);
    }

    public void a(int i, Context context, int i2) {
        if (i != 406) {
            switch (i) {
                case 7:
                case 8:
                    aj.a(context, context.getString(R.string.network_unavailable));
                    String str = a;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Network error: ");
                    sb.append(i);
                    f.b(str, sb.toString());
                    b a2 = b.a();
                    String str2 = a;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Network error: ");
                    sb2.append(i);
                    a2.b(str2, sb2.toString());
                    break;
                default:
                    a(context);
                    String str3 = a;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Unrecoverable error when checking out with Google Wallet: ");
                    sb3.append(i);
                    sb3.append(". Cart id: ");
                    sb3.append(Integer.toString(i2));
                    f.b(str3, sb3.toString());
                    b a3 = b.a();
                    String str4 = a;
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Unrecoverable error when checking out with Google Wallet: ");
                    sb4.append(i);
                    a3.b(str4, sb4.toString());
                    break;
            }
        } else {
            aj.a(context, context.getString(R.string.google_wallet_error_spending_limit_exceeded));
        }
        StringBuilder sb5 = new StringBuilder();
        sb5.append("android_pay.error.");
        sb5.append(i);
        com.etsy.android.lib.logger.a.a.a(sb5.toString());
    }

    public static void a(Context context) {
        aj.b(context, (int) R.string.google_wallet_error_unavailable);
    }

    protected static Cart a(AndroidPayDataContract androidPayDataContract) {
        ArrayList arrayList = new ArrayList();
        for (AndroidPayLineItemContract androidPayLineItemContract : androidPayDataContract.getLineItems()) {
            LineItem.a a2 = LineItem.newBuilder().e(androidPayLineItemContract.getCurrencyCode()).a(androidPayLineItemContract.getRole());
            if (androidPayLineItemContract.getRole() == 0) {
                a2.b(Integer.toString(androidPayLineItemContract.getQuantity())).a(androidPayLineItemContract.getDescription()).c(androidPayLineItemContract.getPrice().getAmount().toString());
            } else {
                a2.d(androidPayLineItemContract.getPrice().getAmount().toString());
            }
            arrayList.add(a2.a());
        }
        return Cart.newBuilder().b(androidPayDataContract.getTotal().getCurrency().getCurrencyCode()).a(androidPayDataContract.getTotal().getAmount().toString()).a((List<LineItem>) arrayList).a();
    }

    public static ReadyState a(BooleanResult booleanResult) {
        if (booleanResult.getStatus().isSuccess() && booleanResult.getValue()) {
            com.etsy.android.lib.logger.a.a.a("android_pay.ready_state.ready");
            return ReadyState.READY;
        } else if (booleanResult.getStatus().isCanceled()) {
            com.etsy.android.lib.logger.a.a.a("android_pay.ready_state.canceled");
            return ReadyState.CANCELED;
        } else if (booleanResult.getStatus().isInterrupted()) {
            com.etsy.android.lib.logger.a.a.a("android_pay.ready_state.interrupted");
            return ReadyState.INTERRUPTED;
        } else {
            com.etsy.android.lib.logger.a.a.a("android_pay.ready_state.not_ready");
            return ReadyState.NOT_READY;
        }
    }
}
