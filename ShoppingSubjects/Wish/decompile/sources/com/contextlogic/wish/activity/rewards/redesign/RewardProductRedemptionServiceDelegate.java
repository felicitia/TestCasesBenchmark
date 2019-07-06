package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.cart.shipping.StandaloneShippingInfoActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetUserShippingDetailsService;
import com.contextlogic.wish.api.service.standalone.GetUserShippingDetailsService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.RedeemRewardProductService;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.addtocart.AddToCartDialogFragment;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.dialog.addtocart.sizecolorselector.SizeColorSelectorDialogFragment;
import com.contextlogic.wish.dialog.bottomsheet.OrderConfirmedBottomSheetDialog;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog;
import com.crashlytics.android.Crashlytics;
import java.util.Iterator;
import java.util.List;

public class RewardProductRedemptionServiceDelegate<A extends BaseActivity> {
    private GetUserShippingDetailsService mGetUserShippingDetailsService = new GetUserShippingDetailsService();
    private RedeemRewardProductService mRedeemRewardProductService = new RedeemRewardProductService();
    /* access modifiers changed from: private */
    public ServiceFragment<A> mServiceFragment;

    public RewardProductRedemptionServiceDelegate(ServiceFragment<A> serviceFragment) {
        this.mServiceFragment = serviceFragment;
    }

    public void cancelAllRequests() {
        this.mRedeemRewardProductService.cancelAllRequests();
        this.mGetUserShippingDetailsService.cancelAllRequests();
    }

    public void redeemRewardProduct(final WishProduct wishProduct, final DefaultSuccessCallback defaultSuccessCallback) {
        this.mServiceFragment.withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                BaseDialogFragment baseDialogFragment;
                if (!wishProduct.canShowAddToCartModal()) {
                    baseDialogFragment = null;
                } else if (ExperimentDataCenter.getInstance().shouldShowSizeColorSelector() || ExperimentDataCenter.getInstance().shouldShowSizeColorSelectorWithTextSwatchesOnly()) {
                    baseDialogFragment = SizeColorSelectorDialogFragment.createSizeColorSelectorDialogFragment(wishProduct, Source.POINTS_REDEMPTION, true);
                } else {
                    baseDialogFragment = AddToCartDialogFragment.createAddToCartDialog(wishProduct, Source.POINTS_REDEMPTION, true);
                }
                if (baseDialogFragment == null) {
                    RewardProductRedemptionServiceDelegate.this.confirmRewardProductRedemption(wishProduct, wishProduct.getCommerceDefaultVariationId(), wishProduct.getDefaultShippingOptionId(wishProduct.getDefaultCommerceVariationId()), defaultSuccessCallback);
                } else {
                    a.startDialog(baseDialogFragment, new BaseDialogCallback() {
                        public void onCancel(BaseDialogFragment baseDialogFragment) {
                        }

                        public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                            String str;
                            String string = bundle == null ? null : bundle.getString("ResultVariationId");
                            if (string == null) {
                                str = null;
                            } else {
                                str = wishProduct.getDefaultShippingOptionId(string);
                            }
                            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(str)) {
                                Crashlytics.logException(new Exception("Reward Redemption variation selection failed!"));
                                RewardProductRedemptionServiceDelegate.this.mServiceFragment.showErrorMessage(null);
                                return;
                            }
                            RewardProductRedemptionServiceDelegate.this.confirmRewardProductRedemption(wishProduct, string, str, defaultSuccessCallback);
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void confirmRewardProductRedemption(WishProduct wishProduct, String str, String str2, DefaultSuccessCallback defaultSuccessCallback) {
        this.mServiceFragment.showLoadingSpinner();
        GetUserShippingDetailsService getUserShippingDetailsService = this.mGetUserShippingDetailsService;
        final WishProduct wishProduct2 = wishProduct;
        final String str3 = str;
        final String str4 = str2;
        final DefaultSuccessCallback defaultSuccessCallback2 = defaultSuccessCallback;
        AnonymousClass2 r1 = new SuccessCallback() {
            public void onSuccess(List<WishShippingInfo> list, String str) {
                WishShippingInfo wishShippingInfo;
                RewardProductRedemptionServiceDelegate.this.mServiceFragment.hideLoadingSpinner();
                if (list.isEmpty()) {
                    RewardProductRedemptionServiceDelegate.this.showShippingViewForRewardItemRedemption(wishProduct2, str3, str4, defaultSuccessCallback2);
                    return;
                }
                WishShippingInfo wishShippingInfo2 = (WishShippingInfo) list.get(0);
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        wishShippingInfo = wishShippingInfo2;
                        break;
                    }
                    WishShippingInfo wishShippingInfo3 = (WishShippingInfo) it.next();
                    if (TextUtils.equals(str, wishShippingInfo3.getId())) {
                        wishShippingInfo = wishShippingInfo3;
                        break;
                    }
                }
                RewardProductRedemptionServiceDelegate.this.showRewardProductRedemptionConfirmationDialog(wishProduct2, str3, str4, wishShippingInfo, defaultSuccessCallback2);
            }
        };
        getUserShippingDetailsService.requestService(r1, new DefaultFailureCallback() {
            public void onFailure(String str) {
                RewardProductRedemptionServiceDelegate.this.mServiceFragment.hideLoadingSpinner();
                RewardProductRedemptionServiceDelegate.this.mServiceFragment.showErrorMessage(str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void showRewardProductRedemptionConfirmationDialog(WishProduct wishProduct, String str, String str2, WishShippingInfo wishShippingInfo, DefaultSuccessCallback defaultSuccessCallback) {
        ServiceFragment<A> serviceFragment = this.mServiceFragment;
        final WishProduct wishProduct2 = wishProduct;
        final String str3 = str;
        final WishShippingInfo wishShippingInfo2 = wishShippingInfo;
        final String str4 = str2;
        final DefaultSuccessCallback defaultSuccessCallback2 = defaultSuccessCallback;
        AnonymousClass4 r1 = new ActivityTask<A>() {
            public void performTask(A a) {
                a.startDialog(ConfirmRewardProductRedemptionDialog.create(wishProduct2.getValueInPointsForVariation(str3), wishProduct2.getImage().getBaseUrlString(), wishShippingInfo2), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            RewardProductRedemptionServiceDelegate.this.completeRewardProductRedemption(wishProduct2, str3, str4, defaultSuccessCallback2);
                        }
                    }
                });
            }
        };
        serviceFragment.withActivity(r1);
    }

    /* access modifiers changed from: private */
    public void completeRewardProductRedemption(WishProduct wishProduct, String str, String str2, DefaultSuccessCallback defaultSuccessCallback) {
        this.mServiceFragment.showLoadingSpinner();
        RedeemRewardProductService redeemRewardProductService = this.mRedeemRewardProductService;
        String commerceProductId = wishProduct.getCommerceProductId();
        final DefaultSuccessCallback defaultSuccessCallback2 = defaultSuccessCallback;
        AnonymousClass5 r11 = new RedeemRewardProductService.SuccessCallback() {
            public void onSuccess(String str, String str2, String str3, String str4, WishShippingInfo wishShippingInfo) {
                RewardProductRedemptionServiceDelegate.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_REDEEM_REWARD_PRODUCT_SUCCESS_DIALOG);
                WishImage wishImage = TextUtils.isEmpty(str) ? null : new WishImage(str);
                if (TextUtils.isEmpty(str4) || wishShippingInfo == null) {
                    SuccessBottomSheetDialog.create(RewardProductRedemptionServiceDelegate.this.mServiceFragment.getBaseActivity()).setImage(wishImage).setTitle(str2).setMessage(str3).autoDismiss().show();
                    return;
                }
                OrderConfirmedBottomSheetDialog.create(RewardProductRedemptionServiceDelegate.this.mServiceFragment.getBaseActivity()).setImageUrl(str).setTitle(str2).setOrderConfirmedInformation(str4, wishShippingInfo.getFormattedString(false)).autoDismiss().show();
                if (defaultSuccessCallback2 != null) {
                    defaultSuccessCallback2.onSuccess();
                }
            }
        };
        final WishProduct wishProduct2 = wishProduct;
        final String str3 = str;
        final String str4 = str2;
        AnonymousClass6 r0 = new DefaultCodeFailureCallback() {
            public void onFailure(String str, int i) {
                RewardProductRedemptionServiceDelegate.this.mServiceFragment.hideLoadingSpinner();
                if (i == 12) {
                    RewardProductRedemptionServiceDelegate.this.showShippingViewForRewardItemRedemption(wishProduct2, str3, str4, defaultSuccessCallback2);
                } else if (TextUtils.isEmpty(str) || i < 10) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_REDEEM_REWARD_PRODUCT_ERROR_DIALOG);
                    RewardProductRedemptionServiceDelegate.this.mServiceFragment.showErrorMessage(RewardProductRedemptionServiceDelegate.this.mServiceFragment.getString(R.string.general_error));
                } else {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_REDEEM_REWARD_PRODUCT_ERROR_DIALOG);
                    RewardProductRedemptionServiceDelegate.this.mServiceFragment.showErrorMessage(str);
                }
            }
        };
        redeemRewardProductService.requestService(commerceProductId, str, str2, r11, r0);
    }

    /* access modifiers changed from: private */
    public void showShippingViewForRewardItemRedemption(WishProduct wishProduct, String str, String str2, DefaultSuccessCallback defaultSuccessCallback) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_REDEEM_REWARD_SHIPPING_INFO_VIEW);
        ServiceFragment<A> serviceFragment = this.mServiceFragment;
        final WishProduct wishProduct2 = wishProduct;
        final String str3 = str;
        final String str4 = str2;
        final DefaultSuccessCallback defaultSuccessCallback2 = defaultSuccessCallback;
        AnonymousClass7 r1 = new ActivityTask<A>() {
            public void performTask(A a) {
                int addResultCodeCallback = a.addResultCodeCallback(new ActivityResultCallback() {
                    public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                        if (i2 == -1) {
                            RewardProductRedemptionServiceDelegate.this.confirmRewardProductRedemption(wishProduct2, str3, str4, defaultSuccessCallback2);
                        }
                    }
                });
                Intent intent = new Intent();
                intent.setClass(a, StandaloneShippingInfoActivity.class);
                intent.putExtra("ExtraCancelWarning", a.getString(R.string.redeem_shipping_enter_cancel));
                a.startActivityForResult(intent, addResultCodeCallback);
            }
        };
        serviceFragment.withActivity(r1);
    }
}
