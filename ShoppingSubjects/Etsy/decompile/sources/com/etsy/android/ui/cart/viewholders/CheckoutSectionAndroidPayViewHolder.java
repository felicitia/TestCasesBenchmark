package com.etsy.android.ui.cart.viewholders;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayData;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.CheckoutSection;
import com.etsy.android.ui.cart.a.d;
import com.etsy.android.ui.cart.googlewallet.a;

public class CheckoutSectionAndroidPayViewHolder extends CheckoutSectionViewHolder {
    private ViewGroup mBtnCheckoutAndroidPay;
    private a mGoogleWalletHelper;

    public CheckoutSectionAndroidPayViewHolder(ViewGroup viewGroup, d dVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_checkout_android_pay, viewGroup, false), dVar);
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mBtnCheckoutAndroidPay = (ViewGroup) findViewById(R.id.btn_checkout_android_pay);
    }

    public void bindCartGroupItem(CartGroupItem cartGroupItem) {
        CheckoutSection checkoutSection = (CheckoutSection) cartGroupItem.getData();
        if (checkoutSection != null) {
            if (this.mBtnCheckoutAndroidPay.getChildCount() == 0) {
                FragmentActivity fragmentActivity = (FragmentActivity) getRootView().getContext();
                AndroidPayData androidPayData = checkoutSection.getAndroidPayData();
                if (androidPayData != null) {
                    this.mGoogleWalletHelper = new a(fragmentActivity);
                    this.mBtnCheckoutAndroidPay.post(new a(this, fragmentActivity, androidPayData));
                }
            }
            setupClickListener(this.mBtnCheckoutAndroidPay, checkoutSection);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$bindCartGroupItem$0$CheckoutSectionAndroidPayViewHolder(FragmentActivity fragmentActivity, AndroidPayData androidPayData) {
        this.mGoogleWalletHelper.a(fragmentActivity, (AndroidPayDataContract) androidPayData, (int) R.id.btn_checkout_android_pay);
    }
}
