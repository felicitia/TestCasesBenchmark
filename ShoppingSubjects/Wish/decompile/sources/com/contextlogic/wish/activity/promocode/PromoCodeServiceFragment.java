package com.contextlogic.wish.activity.promocode;

import android.os.Bundle;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.ApplyPromoCodeService;
import com.contextlogic.wish.api.service.standalone.ApplyPromoCodeService.SuccessCallback;

public class PromoCodeServiceFragment extends ServiceFragment<PromoCodeActivity> {
    /* access modifiers changed from: private */
    public ApplyPromoCodeService mApplyPromoCodeService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mApplyPromoCodeService = new ApplyPromoCodeService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mApplyPromoCodeService.cancelAllRequests();
    }

    public void applyPromoCodeService(final String str) {
        withUiFragment(new UiTask<PromoCodeActivity, PromoCodeFragment>() {
            public void performTask(PromoCodeActivity promoCodeActivity, final PromoCodeFragment promoCodeFragment) {
                PromoCodeServiceFragment.this.mApplyPromoCodeService.requestService(new SuccessCallback() {
                    public void onSuccess(WishCart wishCart) {
                        promoCodeFragment.handleApplyPromoCodeSuccess(wishCart);
                    }
                }, new DefaultFailureCallback() {
                    public void onFailure(String str) {
                        promoCodeFragment.handleApplyPromoCodeFailure(str);
                    }
                }, promoCodeFragment, str);
            }
        }, "FragmentTagMainContent");
    }
}
