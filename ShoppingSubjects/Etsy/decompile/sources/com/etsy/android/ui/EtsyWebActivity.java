package com.etsy.android.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import com.etsy.android.R;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.models.PaymentOption;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.CrashUtil;
import com.etsy.android.lib.util.aj;
import com.etsy.android.ui.cart.CartWithSavedActivity;
import com.etsy.android.ui.cart.googlewallet.c;
import com.etsy.android.ui.core.SingleListingCheckoutActivity;
import com.etsy.android.ui.nav.e;
import com.google.android.gms.wallet.MaskedWallet;

public class EtsyWebActivity extends BOENavDrawerActivity {
    private static final String FRAGMENT_TAG_CART = "cartWebFragment";
    public static final int TYPE_ABOUT = 0;
    public static final int TYPE_CART = 2;
    public static final int TYPE_ELECTRONIC_COMMUNICATIONS_POLICY = 7;
    public static final int TYPE_ETSY_LOCAL_HELP = 4;
    public static final int TYPE_GENERIC_ETSY_HELP = 11;
    public static final int TYPE_LEGAL = 10;
    public static final int TYPE_NOTIFICATIONS = 1;
    public static final int TYPE_ORDER_TRACKING = 3;
    public static final int TYPE_PRIVACY_POLICY = 6;
    public static final int TYPE_QUALTRICS_SURVEY = 12;
    public static final int TYPE_TERMS_OF_USE = 5;
    public static final int TYPE_TESTING = -1;
    public static final int TYPE_USER_ADDRESSES = 13;
    private c mGoogleWalletWebViewHelper;
    private int mType;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mType = getIntent().getExtras().getInt("type");
        if (bundle == null) {
            switch (this.mType) {
                case -1:
                    e.a((FragmentActivity) this).f().d("https://www.etsy.com");
                    break;
                case 0:
                    e.a((FragmentActivity) this).f().i();
                    break;
                case 1:
                    e.a((FragmentActivity) this).f().o();
                    break;
                case 2:
                    com.etsy.android.ui.nav.c a = e.a((FragmentActivity) this).f().a(FRAGMENT_TAG_CART);
                    if (!getIntent().getExtras().getBoolean(SingleListingCheckoutActivity.CHECKED_OUT_SINGLE_LISTING)) {
                        addCheckoutFragment(a);
                        break;
                    } else {
                        addSingleListingCheckoutFragment(a);
                        break;
                    }
                case 3:
                    if (!getIntent().getExtras().containsKey("url")) {
                        e.a((FragmentActivity) this).f().b((EtsyId) getIntent().getExtras().getSerializable(ResponseConstants.RECEIPT_ID), (EtsyId) getIntent().getExtras().getSerializable(ResponseConstants.RECEIPT_SHIPPING_ID));
                        break;
                    } else {
                        e.a((FragmentActivity) this).f().c(getIntent().getExtras().getString("url"));
                        break;
                    }
                case 4:
                    e.a((FragmentActivity) this).f().f(getIntent().getExtras().getString("url"));
                    break;
                case 5:
                    e.a((FragmentActivity) this).f().j();
                    break;
                case 6:
                    e.a((FragmentActivity) this).f().k();
                    break;
                case 7:
                    e.a((FragmentActivity) this).f().n();
                    break;
                case 10:
                    e.a((FragmentActivity) this).f().l();
                    break;
                case 11:
                    e.a((FragmentActivity) this).f().e(getIntent().getExtras().getString("url"));
                    break;
                case 12:
                    e.a((FragmentActivity) this).f().d(getIntent().getExtras().getString("url"));
                    break;
                case 13:
                    e.a((FragmentActivity) this).f().m();
                    break;
            }
        }
        if (shouldUseNavStyleModal()) {
            setNavStyleModal();
        }
        setTitle();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void addCheckoutFragment(@NonNull com.etsy.android.ui.nav.c cVar) {
        AndroidPayDataContract androidPayDataContract = (AndroidPayDataContract) getIntent().getExtras().getSerializable(CartWithSavedActivity.CHECKED_OUT_CART);
        boolean z = getIntent().getExtras().getBoolean(CartWithSavedActivity.CHECKED_OUT_IS_MSCO);
        boolean z2 = false;
        boolean z3 = !z && getConfigMap().c(b.e.a);
        if (z && getConfigMap().c(b.e.b)) {
            z2 = true;
        }
        MaskedWallet maskedWallet = null;
        if (androidPayDataContract != null && androidPayDataContract.getLastPaymentMethod() != null && androidPayDataContract.getLastPaymentMethod().isAndroidPay() && (z3 || z2)) {
            maskedWallet = (MaskedWallet) getIntent().getExtras().getParcelable("android_pay_masked_wallet");
            if (maskedWallet != null) {
                createGoogleWalletWebViewHelper(maskedWallet, androidPayDataContract);
            }
        }
        if (z) {
            String stringExtra = getIntent().getStringExtra(CartWithSavedActivity.CHECKED_OUT_CART_GROUP_ID);
            String stringExtra2 = getIntent().getStringExtra(CartWithSavedActivity.CHECKED_OUT_PAYMENT_METHOD);
            if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2)) {
                com.etsy.android.lib.logger.legacy.b.a().b("cart_checkout", "MultishopCart EtsyWebActivity requested with no cart_group_id or payment_method");
            } else {
                if (getConfigMap().c(b.e.b)) {
                    cVar.a(stringExtra, stringExtra2, androidPayDataContract, maskedWallet);
                } else {
                    cVar.a(stringExtra, stringExtra2);
                }
                return;
            }
        }
        if (androidPayDataContract != null) {
            cVar.a(androidPayDataContract, maskedWallet);
        } else {
            com.etsy.android.lib.logger.legacy.b.a().b("cart_checkout", "Cart EtsyWebActivity request with no Cart");
        }
    }

    private void addSingleListingCheckoutFragment(@NonNull com.etsy.android.ui.nav.c cVar) {
        Bundle extras = getIntent().getExtras();
        String string = extras.getString("listing_id");
        String string2 = extras.getString("quantity");
        PaymentOption paymentOption = (PaymentOption) extras.get("payment_option");
        String string3 = extras.getString(ResponseConstants.OFFERING_ID);
        String string4 = extras.getString(SingleListingCheckoutActivity.PARAM_LISTING_VARIATION);
        if (TextUtils.isEmpty(string) || string.equals("listing_id")) {
            StringBuilder sb = new StringBuilder("EtsyWebActivity: problem building single listing checkout url");
            sb.append(" - listingId = ");
            sb.append(string);
            sb.append(" - quantity = ");
            sb.append(string2);
            sb.append(" - offeringId = ");
            sb.append(string3);
            sb.append(" - variations = ");
            sb.append(string4);
            CrashUtil.a().a(new Throwable(sb.toString()));
            aj.b((Context) this, (int) R.string.single_listing_checkout_listing_error);
            setResult(0);
            finish();
            return;
        }
        AndroidPayDataContract androidPayDataContract = (AndroidPayDataContract) getIntent().getExtras().getSerializable(CartWithSavedActivity.CHECKED_OUT_CART);
        MaskedWallet maskedWallet = (MaskedWallet) getIntent().getExtras().getParcelable("android_pay_masked_wallet");
        if (androidPayDataContract == null || maskedWallet == null) {
            cVar.a(string, string2, paymentOption, string3, string4);
        } else {
            createGoogleWalletWebViewHelper(maskedWallet, androidPayDataContract);
            cVar.a(string, string2, string3, string4, androidPayDataContract, maskedWallet);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.mGoogleWalletWebViewHelper != null) {
            this.mGoogleWalletWebViewHelper.a();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (this.mGoogleWalletWebViewHelper != null) {
            this.mGoogleWalletWebViewHelper.c();
        }
    }

    public void onDestroy() {
        this.mGoogleWalletWebViewHelper = null;
        super.onDestroy();
    }

    private void setTitle() {
        switch (this.mType) {
            case 0:
                setTitle(R.string.about_app);
                return;
            case 1:
                setTitle(R.string.notifications);
                return;
            case 2:
                setTitle(R.string.checkout);
                return;
            case 3:
                setTitle(R.string.track_package);
                return;
            case 4:
                setTitle(R.string.local_help_page_title);
                return;
            case 5:
                setTitle(R.string.etsy_terms_of_use);
                return;
            case 6:
                setTitle(R.string.privacy_policy);
                return;
            case 7:
                setTitle(R.string.comms_policy);
                return;
            case 10:
                setTitle(R.string.legal);
                return;
            case 11:
                setTitle(R.string.help);
                return;
            case 12:
                setTitle(R.string.qualtrics_survey_page_title);
                return;
            default:
                return;
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.mType != 2 || menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        Intent intent = null;
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_CART);
        if (findFragmentByTag != null && (findFragmentByTag instanceof EtsyWebFragment)) {
            EtsyWebFragment etsyWebFragment = (EtsyWebFragment) findFragmentByTag;
            String cartId = etsyWebFragment.getCartId();
            boolean isPayPalCheckout = etsyWebFragment.isPayPalCheckout();
            Intent intent2 = new Intent();
            intent2.putExtra("cart_id", cartId);
            intent2.putExtra("is_paypal", isPayPalCheckout);
            intent = intent2;
        }
        setResult(0, intent);
        return com.etsy.android.uikit.util.e.b(getSupportFragmentManager(), e.a((FragmentActivity) this));
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        EtsyWebFragment etsyWebFragment = (EtsyWebFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_CART);
        if (this.mGoogleWalletWebViewHelper != null && etsyWebFragment != null) {
            etsyWebFragment.handleGoogleWalletResult(i, i2, intent);
        }
    }

    public boolean isTopLevelActivity() {
        return !shouldUseNavStyleModal() && super.isTopLevelActivity();
    }

    /* access modifiers changed from: protected */
    public void createGoogleWalletWebViewHelper(@NonNull MaskedWallet maskedWallet, @NonNull AndroidPayDataContract androidPayDataContract) {
        this.mGoogleWalletWebViewHelper = new c(this, maskedWallet, androidPayDataContract);
    }

    private boolean shouldUseNavStyleModal() {
        int i = getIntent().getExtras().getInt("type");
        return i == 2 || i == 11;
    }

    public c getGoogleWalletHelper() {
        return this.mGoogleWalletWebViewHelper;
    }
}
