package com.etsy.android.ui.cart.googlewallet;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MotionEventCompat;
import com.etsy.android.lib.logger.j;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import com.etsy.android.ui.cart.CartWithSavedActivity;
import com.etsy.android.ui.nav.e;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.d;
import com.google.android.gms.wallet.fragment.SupportWalletFragment;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;
import com.google.android.gms.wallet.fragment.WalletFragmentStyle;

/* compiled from: GoogleWalletCartHelper */
public class a extends GoogleWalletHelperBase {
    private AndroidPayDataContract e;

    /* renamed from: com.etsy.android.ui.cart.googlewallet.a$a reason: collision with other inner class name */
    /* compiled from: GoogleWalletCartHelper */
    public interface C0087a {
        a getGoogleWalletHelper();
    }

    public static int a(int i) {
        return i & 255;
    }

    private static int c(int i) {
        return i & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
    }

    public a(Context context) {
        super(context);
    }

    private MaskedWalletRequest b(@NonNull AndroidPayDataContract androidPayDataContract) {
        PaymentMethodTokenizationParameters a = PaymentMethodTokenizationParameters.newBuilder().a(2).a("publicKey", d() == 1 ? "BOvPTBFK0ZYJzH8ehGD37IGh2q7AjPXVQLugapnb+7ioVvKE8rAE1bVf5ZKe563bXLK5NsMPRYlz+qmrR0K6pdY=" : "BJFHlxSuUgY60QyWiYKGbKNvalpzTEg8OKLtopbv3DeV/mDfNPi/xmgILtmcMlHIZp9+HFCfWXtbn1ScwIXVSo8=").a();
        String currencyCode = androidPayDataContract.getTotal().getCurrency().getCurrencyCode();
        String bigDecimal = androidPayDataContract.getTotal().getAmount().toString();
        com.google.android.gms.wallet.MaskedWalletRequest.a newBuilder = MaskedWalletRequest.newBuilder();
        StringBuilder sb = new StringBuilder();
        sb.append("Etsy - ");
        sb.append(androidPayDataContract.getShopName());
        return newBuilder.c(sb.toString()).b(currencyCode).a(bigDecimal).a(a).a();
    }

    public void a(@NonNull FragmentActivity fragmentActivity, @NonNull AndroidPayDataContract androidPayDataContract, @IdRes int i) {
        this.e = androidPayDataContract;
        MaskedWalletRequest b = b(androidPayDataContract);
        SupportWalletFragment newInstance = SupportWalletFragment.newInstance(WalletFragmentOptions.newBuilder().a(d()).a(new WalletFragmentStyle().setBuyButtonText(5).setBuyButtonAppearance(6).setBuyButtonHeight(-1).setBuyButtonWidth(-1)).b(1).c(1).a());
        newInstance.initialize(WalletFragmentInitParams.newBuilder().a(b).a(44032).a());
        fragmentActivity.getSupportFragmentManager().beginTransaction().replace(i, newInstance).commit();
    }

    public void a(ResultCallback<BooleanResult> resultCallback) {
        if (!this.b.isConnected()) {
            resultCallback.onResult(new BooleanResult(new Status(100, "Api client is not connected. Call connectWallet first."), false));
        } else {
            d.b.a(this.b).setResultCallback(resultCallback);
        }
    }

    public static boolean b(int i) {
        int c = c(i);
        return c == 43776 || c == 44032;
    }

    public void a(FragmentActivity fragmentActivity, AndroidPayDataContract androidPayDataContract, int i, int i2, Intent intent) {
        int c = c(i);
        if (c == 43776) {
            a((Context) fragmentActivity, i2, intent);
        } else if (c == 44032) {
            a(fragmentActivity, androidPayDataContract, i2, intent);
        }
    }

    public void a(FragmentActivity fragmentActivity, AndroidPayDataContract androidPayDataContract, int i, int i2, Intent intent, Listing listing, String str, String str2) {
        int c = c(i);
        if (c == 44032) {
            MaskedWallet a = a(intent);
            if (i2 == -1 && a != null) {
                com.etsy.android.ui.cart.a.b(fragmentActivity instanceof j ? ((j) fragmentActivity).getAnalyticsContext() : null, androidPayDataContract, i2);
                e.a(fragmentActivity).a().a(androidPayDataContract, a, listing, str, str2);
            } else if (i2 != 0) {
                a(b(intent), fragmentActivity, androidPayDataContract != null ? androidPayDataContract.getCartId() : 0);
            }
        } else if (c == 43776) {
            a((Context) fragmentActivity, i2, intent);
        }
    }

    private void a(FragmentActivity fragmentActivity, AndroidPayDataContract androidPayDataContract, int i, Intent intent) {
        com.etsy.android.ui.cart.a.b(fragmentActivity instanceof j ? ((j) fragmentActivity).getAnalyticsContext() : null, androidPayDataContract, i);
        int i2 = 0;
        switch (i) {
            case -1:
                MaskedWallet a = a(intent);
                if (a != null) {
                    e.a(fragmentActivity).a().a(androidPayDataContract, a, intent.getExtras().getBoolean(CartWithSavedActivity.CHECKED_OUT_IS_MSCO, false));
                    return;
                }
                return;
            case 0:
                return;
            default:
                int b = b(intent);
                if (androidPayDataContract != null) {
                    i2 = androidPayDataContract.getCartId();
                }
                a(b, fragmentActivity, i2);
                return;
        }
    }

    private void a(Context context, int i, Intent intent) {
        int i2;
        switch (i) {
            case -1:
            case 0:
                if (!this.b.isConnected() && !this.b.isConnecting()) {
                    a();
                    return;
                }
                return;
            default:
                int b = b(intent);
                if (this.e != null) {
                    i2 = this.e.getCartId();
                } else {
                    i2 = 0;
                }
                a(b, context, i2);
                return;
        }
    }
}
