package com.contextlogic.wish.activity.wishpartner.cashout;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.DialogTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishPartnerSummary.WishPartnerCashOutOption;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.CashOutPaypalService;
import com.contextlogic.wish.api.service.standalone.CashOutPaypalService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.CashOutWishCashService;
import com.contextlogic.wish.api.service.standalone.PreverifyPaypalEmailService;
import com.contextlogic.wish.api.service.standalone.StorePaypalEmailService;
import java.util.ArrayList;

public class WishPartnerCashOutServiceFragment extends ServiceFragment<WishPartnerCashOutActivity> {
    private CashOutPaypalService mCashOutPaypalService;
    private CashOutWishCashService mCashOutWishCashService;
    private PreverifyPaypalEmailService mPreverifyPaypalEmailService;
    private StorePaypalEmailService mStorePaypalEmailService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mCashOutWishCashService = new CashOutWishCashService();
        this.mCashOutPaypalService = new CashOutPaypalService();
        this.mPreverifyPaypalEmailService = new PreverifyPaypalEmailService();
        this.mStorePaypalEmailService = new StorePaypalEmailService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mCashOutWishCashService.cancelAllRequests();
        this.mCashOutPaypalService.cancelAllRequests();
        this.mPreverifyPaypalEmailService.cancelAllRequests();
        this.mStorePaypalEmailService.cancelAllRequests();
    }

    public void cashOutWithPaypal(String str, String str2) {
        this.mCashOutPaypalService.requestService(str, str2, new SuccessCallback() {
            public void onSuccess(final String str, final String str2) {
                WishPartnerCashOutServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishPartnerCashOutFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerCashOutFragment wishPartnerCashOutFragment) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_WISH_PARTNER_CASH_OUT_PAYPAL_SUCCESS_MODULE);
                        wishPartnerCashOutFragment.handleLoadingSuccess(str, str2);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                WishPartnerCashOutServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishPartnerCashOutFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerCashOutFragment wishPartnerCashOutFragment) {
                        wishPartnerCashOutFragment.handleLoadingFailed(str == null ? WishPartnerCashOutServiceFragment.this.getString(R.string.failed_to_cash_out_with_paypal) : str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void cashOutWithWishCash(String str) {
        this.mCashOutWishCashService.requestService(str, new CashOutWishCashService.SuccessCallback() {
            public void onSuccess(final String str, final String str2) {
                WishPartnerCashOutServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishPartnerCashOutFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerCashOutFragment wishPartnerCashOutFragment) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_WISH_PARTNER_CASH_OUT_WISH_CASH_SUCCESS_MODULE);
                        wishPartnerCashOutFragment.handleLoadingSuccess(str, str2);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                WishPartnerCashOutServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishPartnerCashOutFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerCashOutFragment wishPartnerCashOutFragment) {
                        wishPartnerCashOutFragment.handleLoadingFailed(str == null ? WishPartnerCashOutServiceFragment.this.getString(R.string.failed_to_cash_out_with_wish_cash) : str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void preverifyEmailAddress(String str) {
        this.mPreverifyPaypalEmailService.requestService(str, new PreverifyPaypalEmailService.SuccessCallback() {
            public void onSuccess(final String str, final String str2) {
                WishPartnerCashOutServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, WishPartnerChangeEmailDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerChangeEmailDialogFragment wishPartnerChangeEmailDialogFragment) {
                        wishPartnerChangeEmailDialogFragment.handleEmailVerified(str, str2);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                WishPartnerCashOutServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, WishPartnerChangeEmailDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerChangeEmailDialogFragment wishPartnerChangeEmailDialogFragment) {
                        wishPartnerChangeEmailDialogFragment.handleFailure(str == null ? WishPartnerCashOutServiceFragment.this.getString(R.string.failed_to_verify_email) : str);
                    }
                });
            }
        });
    }

    public void storePaypalEmail(String str, String str2) {
        this.mStorePaypalEmailService.requestService(str, str2, new StorePaypalEmailService.SuccessCallback() {
            public void onSuccess(final ArrayList<WishPartnerCashOutOption> arrayList, final String str, final String str2) {
                WishPartnerCashOutServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, WishPartnerChangeEmailDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerChangeEmailDialogFragment wishPartnerChangeEmailDialogFragment) {
                        wishPartnerChangeEmailDialogFragment.handlePaypalEmailStored(arrayList, str, str2);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                WishPartnerCashOutServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, WishPartnerChangeEmailDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerChangeEmailDialogFragment wishPartnerChangeEmailDialogFragment) {
                        wishPartnerChangeEmailDialogFragment.handleFailure(str == null ? WishPartnerCashOutServiceFragment.this.getString(R.string.failed_to_save_paypal_account) : str);
                    }
                });
            }
        });
    }
}
