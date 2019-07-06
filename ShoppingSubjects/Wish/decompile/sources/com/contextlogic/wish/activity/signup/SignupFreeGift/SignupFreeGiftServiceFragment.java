package com.contextlogic.wish.activity.signup.SignupFreeGift;

import android.content.Intent;
import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.activity.webview.WebViewActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCart.PaymentMode;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishSignupFreeGiftCart;
import com.contextlogic.wish.api.model.WishSignupFreeGifts;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.ClaimFreeSignupGiftService;
import com.contextlogic.wish.api.service.standalone.GetCartService;
import com.contextlogic.wish.api.service.standalone.GetCartService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetProductService;
import com.contextlogic.wish.api.service.standalone.GetSignupFreeGiftsService;
import com.contextlogic.wish.api.service.standalone.GetSignupGenderedFreeGiftsService;
import com.contextlogic.wish.api.service.standalone.UpdateShippingInfoService;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.addtocart.AddToCartDialogFragment;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.dialog.cvv.CVVConfirmationDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.CartContext.CartContextUpdatedCallback;
import com.contextlogic.wish.payments.checkout.CartCheckoutUiController;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.FailureListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.SuccessListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessorServiceFragment;
import com.contextlogic.wish.payments.processing.CartPaymentProcessorServiceFragmentTask;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.PrepareListener;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.SaveListener;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessorSelector;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessorServiceFragment;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;

public class SignupFreeGiftServiceFragment extends ServiceFragment<SignupFreeGiftActivity> implements CartContextUpdatedCallback, CartCheckoutUiController, CartPaymentProcessorServiceFragment<SignupFreeGiftActivity>, CartPaymentVaultProcessorServiceFragment<SignupFreeGiftActivity> {
    /* access modifiers changed from: private */
    public CartContext mCartContext;
    private ClaimFreeSignupGiftService mClaimFreeSignupGiftService;
    private GetCartService mGetCartService;
    private GetProductService mGetProductService;
    /* access modifiers changed from: private */
    public GetSignupFreeGiftsService mGetSignupFreeGiftService;
    /* access modifiers changed from: private */
    public WishProduct mProduct;
    /* access modifiers changed from: private */
    public WishSignupFreeGiftCart mSignupFreeGiftCart;
    private UpdateShippingInfoService mUpdateShippingInfoService;

    public boolean couldLeadToFreeGiftClaimedDialog() {
        return true;
    }

    public boolean couldLeadToOrderConfirmationPage() {
        return false;
    }

    public void handleInvalidCommerceLoan() {
    }

    public void refreshCart(SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback) {
    }

    public void showExternalBrowser(String str) {
    }

    public void showOrderConfirmedActivity(String str, String str2) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCartContext = new CartContext();
        this.mCartContext.setUpdatedCallback(this);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        if (ExperimentDataCenter.getInstance().shouldShowGenderedFreegift()) {
            this.mGetSignupFreeGiftService = new GetSignupGenderedFreeGiftsService();
        } else {
            this.mGetSignupFreeGiftService = new GetSignupFreeGiftsService();
        }
        this.mClaimFreeSignupGiftService = new ClaimFreeSignupGiftService();
        this.mUpdateShippingInfoService = new UpdateShippingInfoService();
        this.mGetCartService = new GetCartService();
        this.mGetProductService = new GetProductService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        this.mGetSignupFreeGiftService.cancelAllRequests();
        this.mClaimFreeSignupGiftService.cancelAllRequests();
        this.mUpdateShippingInfoService.cancelAllRequests();
        this.mGetCartService.cancelAllRequests();
        this.mGetProductService.cancelAllRequests();
    }

    public void getSignupFreeGifts() {
        withUiFragment(new UiTask<BaseActivity, SignupFreeGiftFragment>() {
            public void performTask(BaseActivity baseActivity, final SignupFreeGiftFragment signupFreeGiftFragment) {
                SignupFreeGiftServiceFragment.this.mGetSignupFreeGiftService.requestService(new GetSignupFreeGiftsService.SuccessCallback() {
                    public void onSuccess(WishSignupFreeGifts wishSignupFreeGifts) {
                        signupFreeGiftFragment.handleLoadingSuccess(wishSignupFreeGifts);
                    }
                }, new DefaultFailureCallback() {
                    public void onFailure(String str) {
                        SignupFreeGiftServiceFragment.this.hideLoadingSpinner();
                        SignupFreeGiftServiceFragment.this.showErrorMessage(str);
                    }
                });
            }
        }, "FragmentTagMainContent");
    }

    public void confirmShippingViewClosing() {
        withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                signupFreeGiftActivity.startDialog(MultiButtonDialogFragment.createMultiButtonYesNoDialog(signupFreeGiftActivity.getString(R.string.are_you_sure), signupFreeGiftActivity.getString(R.string.do_you_want_to_cancel_entering_shipping)), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            SignupFreeGiftServiceFragment.this.showItemsView();
                        }
                    }
                });
            }
        });
    }

    public void confirmBillingViewClosing() {
        withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                signupFreeGiftActivity.startDialog(MultiButtonDialogFragment.createMultiButtonYesNoDialog(signupFreeGiftActivity.getString(R.string.are_you_sure), signupFreeGiftActivity.getString(R.string.do_you_want_to_cancel_entering_payment)), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            SignupFreeGiftServiceFragment.this.showShippingView(false);
                        }
                    }
                });
            }
        });
    }

    public void claimFreeSignupGift(String str, String str2) {
        showLoadingSpinner();
        this.mClaimFreeSignupGiftService.requestService(str, str2, false, false, new ClaimFreeSignupGiftService.SuccessCallback() {
            public void onSuccess(WishSignupFreeGiftCart wishSignupFreeGiftCart) {
                SignupFreeGiftServiceFragment.this.mSignupFreeGiftCart = wishSignupFreeGiftCart;
                SignupFreeGiftServiceFragment.this.mCartContext.updateData(wishSignupFreeGiftCart.getCart(), SignupFreeGiftServiceFragment.this.mCartContext.getShippingInfo(), SignupFreeGiftServiceFragment.this.mCartContext.getUserBillingInfo(), true);
                SignupFreeGiftServiceFragment.this.hideLoadingSpinner();
                SignupFreeGiftServiceFragment.this.showShippingView(wishSignupFreeGiftCart);
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                SignupFreeGiftServiceFragment.this.hideLoadingSpinner();
                SignupFreeGiftServiceFragment.this.showErrorMessage(str);
            }
        });
    }

    public void saveEnteredShippingAddress(WishShippingInfo wishShippingInfo) {
        showLoadingSpinner();
        this.mUpdateShippingInfoService.requestService(wishShippingInfo.getName(), wishShippingInfo.getStreetAddressLineOne(), wishShippingInfo.getStreetAddressLineTwo(), wishShippingInfo.getCity(), wishShippingInfo.getState(), wishShippingInfo.getZipCode(), wishShippingInfo.getCountryCode(), wishShippingInfo.getPhoneNumber(), new UpdateShippingInfoService.SuccessCallback() {
            public void onSuccess(WishShippingInfo wishShippingInfo, WishCart wishCart) {
                SignupFreeGiftServiceFragment.this.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT_SUCCESS);
                SignupFreeGiftServiceFragment.this.mCartContext.updateData(wishCart, wishShippingInfo, (WishUserBillingInfo) null, true);
                SignupFreeGiftServiceFragment.this.showBillingView(false);
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                SignupFreeGiftServiceFragment.this.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT_FAILURE);
                SignupFreeGiftServiceFragment.this.showErrorMessage(str);
            }
        });
    }

    public void selectBillingSection(final CartBillingSection cartBillingSection) {
        CartPaymentVaultProcessor paymentProcessor = CartPaymentVaultProcessorSelector.getPaymentProcessor(cartBillingSection, this.mCartContext, this);
        if (paymentProcessor != null) {
            paymentProcessor.prepareTab(new PrepareListener() {
                public void onTabPrepareCancelled(CartPaymentVaultProcessor cartPaymentVaultProcessor) {
                }

                public void onTabPrepared(CartPaymentVaultProcessor cartPaymentVaultProcessor) {
                    SignupFreeGiftServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupFreeGiftFragment>() {
                        public void performTask(BaseActivity baseActivity, SignupFreeGiftFragment signupFreeGiftFragment) {
                            signupFreeGiftFragment.completeBillingSectionSelected(cartBillingSection);
                        }
                    }, "FragmentTagMainContent");
                }

                public void onTabPrepareFailed(CartPaymentVaultProcessor cartPaymentVaultProcessor, String str) {
                    if (str != null) {
                        SignupFreeGiftServiceFragment.this.showErrorMessage(str);
                    }
                }
            });
        }
    }

    public void saveBillingInformation(CartBillingSection cartBillingSection, Bundle bundle) {
        CartPaymentVaultProcessor paymentProcessor = CartPaymentVaultProcessorSelector.getPaymentProcessor(cartBillingSection, this.mCartContext, this);
        if (paymentProcessor != null) {
            paymentProcessor.save(new SaveListener() {
                public void onSaveComplete(CartPaymentVaultProcessor cartPaymentVaultProcessor) {
                    SignupFreeGiftServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupFreeGiftFragment>() {
                        public void performTask(BaseActivity baseActivity, SignupFreeGiftFragment signupFreeGiftFragment) {
                            signupFreeGiftFragment.checkout();
                        }
                    }, "FragmentTagMainContent");
                }

                public void onSaveFailed(CartPaymentVaultProcessor cartPaymentVaultProcessor, String str) {
                    if (str == null) {
                        str = WishApplication.getInstance().getString(R.string.we_were_unable_to_update_your_billing_information);
                    }
                    SignupFreeGiftServiceFragment.this.showErrorMessage(str);
                }
            }, bundle);
        }
    }

    public void showBrowser(String str, boolean z, boolean z2) {
        withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                Intent intent = new Intent();
                intent.setClass(signupFreeGiftActivity, BrowseActivity.class);
                IntentUtil.putLargeParcelableExtra(intent, "ExtraGiftConfirmedProduct", SignupFreeGiftServiceFragment.this.mProduct);
                intent.putExtra("ExtraGiftConfirmedSignupCart", SignupFreeGiftServiceFragment.this.mSignupFreeGiftCart);
                intent.putExtra("ExtraOrderConfirmedShippingInfo", SignupFreeGiftServiceFragment.this.mCartContext.getShippingInfo());
                signupFreeGiftActivity.startActivity(intent, true);
            }
        });
    }

    public void showShippingView(boolean z) {
        withUiFragment(new UiTask<BaseActivity, SignupFreeGiftFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftFragment signupFreeGiftFragment) {
                signupFreeGiftFragment.showShippingView();
            }
        }, "FragmentTagMainContent");
    }

    public void showShippingView(final WishSignupFreeGiftCart wishSignupFreeGiftCart) {
        withUiFragment(new UiTask<BaseActivity, SignupFreeGiftFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftFragment signupFreeGiftFragment) {
                signupFreeGiftFragment.setCartContext(SignupFreeGiftServiceFragment.this.mCartContext);
                signupFreeGiftFragment.setWishSignupFreeGiftCart(wishSignupFreeGiftCart);
                signupFreeGiftFragment.checkout();
            }
        }, "FragmentTagMainContent");
    }

    public void showBillingView(boolean z) {
        showBillingView((CartBillingSection) null);
    }

    public void showBillingView(final CartBillingSection cartBillingSection) {
        withUiFragment(new UiTask<BaseActivity, SignupFreeGiftFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftFragment signupFreeGiftFragment) {
                signupFreeGiftFragment.showBillingView(cartBillingSection);
            }
        }, "FragmentTagMainContent");
    }

    public void showItemsView() {
        withUiFragment(new UiTask<BaseActivity, SignupFreeGiftFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftFragment signupFreeGiftFragment) {
                signupFreeGiftFragment.showFreeGiftView();
            }
        }, "FragmentTagMainContent");
    }

    public void withCartPaymentProcessorServiceFragment(CartPaymentProcessorServiceFragmentTask cartPaymentProcessorServiceFragmentTask) {
        cartPaymentProcessorServiceFragmentTask.performTask(this);
    }

    public void onCartContextUpdated(CartContext cartContext) {
        withUiFragment(new UiTask<BaseActivity, SignupFreeGiftFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftFragment signupFreeGiftFragment) {
                signupFreeGiftFragment.refreshUi();
            }
        }, "FragmentTagMainContent");
    }

    public void reInitializeCartContext(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo) {
        this.mCartContext.updateData(wishCart, wishShippingInfo, wishUserBillingInfo);
        withUiFragment(new UiTask<BaseActivity, SignupFreeGiftFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftFragment signupFreeGiftFragment) {
                signupFreeGiftFragment.handleCartLoadSuccess(SignupFreeGiftServiceFragment.this.mCartContext);
            }
        }, "FragmentTagMainContent");
    }

    public void showUserBlockedDialog() {
        withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                ArrayList arrayList = new ArrayList();
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, WishApplication.getInstance().getString(R.string.contact_support), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                arrayList.add(multiButtonDialogChoice);
                signupFreeGiftActivity.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(WishApplication.getInstance().getString(R.string.please_contact_support)).setSubTitle(WishApplication.getInstance().getString(R.string.error_blocked_user, new Object[]{WebViewActivity.getPaymentIssueUrl()})).setButtons(arrayList).build(), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 0) {
                            SignupFreeGiftServiceFragment.this.showBrowser(WebViewActivity.getPaymentIssueUrl(), false, false);
                        }
                    }
                });
            }
        });
    }

    public void showConfirmCVVDialog(final SuccessListener successListener, final FailureListener failureListener, final CartPaymentProcessor cartPaymentProcessor) {
        withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                signupFreeGiftActivity.startDialog(CVVConfirmationDialogFragment.createCVVConfirmationDialog(SignupFreeGiftServiceFragment.this.mCartContext), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (bundle != null) {
                            cartPaymentProcessor.checkoutWithCVV(successListener, failureListener, bundle.getString("ResultCVV"));
                        }
                    }
                });
            }
        });
    }

    public void showBillingRedirectDialog(String str, String str2, ArrayList<PaymentMode> arrayList, ArrayList<String> arrayList2) {
        final ArrayList<PaymentMode> arrayList3 = arrayList;
        final ArrayList<String> arrayList4 = arrayList2;
        final String str3 = str;
        final String str4 = str2;
        AnonymousClass19 r0 = new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                MultiButtonDialogFragment multiButtonDialogFragment;
                SignupFreeGiftActivity signupFreeGiftActivity2 = signupFreeGiftActivity;
                ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < arrayList3.size(); i++) {
                    PaymentMode paymentMode = (PaymentMode) arrayList3.get(i);
                    if (paymentMode == PaymentMode.CreditCard || paymentMode == PaymentMode.PayPal || paymentMode == PaymentMode.Default) {
                        if (i == 0) {
                            MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(arrayList2.size(), (String) arrayList4.get(i), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                            arrayList.add(multiButtonDialogChoice);
                        } else {
                            MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(arrayList2.size(), (String) arrayList4.get(i), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                            arrayList.add(multiButtonDialogChoice2);
                        }
                        arrayList2.add(paymentMode);
                    }
                }
                if (arrayList2.size() == 1) {
                    MultiButtonDialogChoice multiButtonDialogChoice3 = new MultiButtonDialogChoice(arrayList2.size(), signupFreeGiftActivity2.getString(R.string.cancel), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                    arrayList.add(multiButtonDialogChoice3);
                    multiButtonDialogFragment = new MultiButtonDialogFragmentBuilder().setButtons(arrayList).setTitle(str3).setCancelable(true).hideXButton().setSubTitle(str4).build();
                } else {
                    multiButtonDialogFragment = new MultiButtonDialogFragmentBuilder().setButtons(arrayList).setTitle(str3).setCancelable(true).setSubTitle(str4).build();
                }
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_SIGNUP_REDIRECT_BILLING_DIALOG);
                signupFreeGiftActivity2.startDialog(multiButtonDialogFragment, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SIGNUP_BILLING_REDIRECT_CLOSE);
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i != arrayList2.size()) {
                            SignupFreeGiftServiceFragment.this.showLoadingSpinner();
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SIGNUP_BILLING_REDIRECT_OPTION, Integer.toString(((PaymentMode) arrayList2.get(i)).ordinal()));
                            SignupFreeGiftServiceFragment.this.showBillingView(CartBaseBillingOptionSelectorView.getCartBillingSectionByPaymentMode((PaymentMode) arrayList2.get(i)));
                            SignupFreeGiftServiceFragment.this.hideLoadingSpinner();
                            return;
                        }
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SIGNUP_BILLING_REDIRECT_CLOSE);
                    }
                });
            }
        };
        withActivity(r0);
    }

    public CartContext getCartContext() {
        return this.mCartContext;
    }

    public void onProductSelected(WishProduct wishProduct) {
        showAddToCart(wishProduct);
    }

    public void showAddToCart(final WishProduct wishProduct) {
        this.mProduct = wishProduct;
        withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                signupFreeGiftActivity.hideLoadingDialog();
                AddToCartDialogFragment createAddToCartDialog = AddToCartDialogFragment.createAddToCartDialog(wishProduct, Source.FREE_GIFT, true);
                WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.IMPRESSION_FIRST_FREE_GIFT_VARIATION_MODAL);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_FREE_GIFT_ADD_TO_CART_MODAL);
                if (createAddToCartDialog != null) {
                    signupFreeGiftActivity.startDialog(createAddToCartDialog, new BaseDialogCallback() {
                        public void onCancel(BaseDialogFragment baseDialogFragment) {
                        }

                        public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                            SignupFreeGiftServiceFragment.this.claimFreeSignupGift(bundle.getString("ResultProductId"), bundle.getString("ResultVariationId"));
                        }
                    });
                }
            }
        });
    }

    public void showFreeGiftAbandonDialog(final WishAnalyticsEvent wishAnalyticsEvent, final WishAnalyticsEvent wishAnalyticsEvent2) {
        withUiFragment(new UiTask<SignupFreeGiftActivity, SignupFreeGiftFragment>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity, SignupFreeGiftFragment signupFreeGiftFragment) {
                if (signupFreeGiftFragment.getFreeGifts() != null) {
                    MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, signupFreeGiftFragment.getFreeGifts().getAbandonInfo().getActionButtonText(), R.color.white, R.drawable.main_dark_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                    MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(1, signupFreeGiftFragment.getFreeGifts().getAbandonInfo().getCancelButtonText(), R.color.text_hint, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(multiButtonDialogChoice);
                    arrayList.add(multiButtonDialogChoice2);
                    final SignupFreeGiftFragment signupFreeGiftFragment2 = signupFreeGiftFragment;
                    signupFreeGiftActivity.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(signupFreeGiftFragment.getFreeGifts().getAbandonInfo().getTitle()).setSubTitle(signupFreeGiftFragment.getFreeGifts().getAbandonInfo().getMessage()).setButtons(arrayList).build(), new BaseDialogCallback() {
                        public void onCancel(BaseDialogFragment baseDialogFragment) {
                        }

                        public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                            switch (i) {
                                case 0:
                                    WishAnalyticsLogger.trackEvent(wishAnalyticsEvent);
                                    return;
                                case 1:
                                    WishAnalyticsLogger.trackEvent(wishAnalyticsEvent2);
                                    signupFreeGiftFragment2.handleClose();
                                    return;
                                default:
                                    return;
                            }
                        }
                    });
                }
            }
        }, "FragmentTagMainContent");
    }
}
