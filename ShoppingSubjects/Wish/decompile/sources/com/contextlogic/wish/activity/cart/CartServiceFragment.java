package com.contextlogic.wish.activity.cart;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.DatePicker;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.GooglePayment;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.DialogTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.activity.cart.billing.paymentform.PaymentFormServiceProvider;
import com.contextlogic.wish.activity.orderconfirmed.OrderConfirmedActivity;
import com.contextlogic.wish.activity.profile.wishlist.CreateWishlistDialogFragment;
import com.contextlogic.wish.activity.profile.wishlist.SelectWishlistDialogFragment;
import com.contextlogic.wish.activity.profile.wishlist.WishlistActivity;
import com.contextlogic.wish.activity.webview.KlarnaWebViewActivity;
import com.contextlogic.wish.activity.webview.WebViewActivity;
import com.contextlogic.wish.activity.webview.WebViewActivity.Source;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCart.PaymentMode;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.api.model.WishCommerceLoanBannerSpec;
import com.contextlogic.wish.api.model.WishCommerceLoanInfo;
import com.contextlogic.wish.api.model.WishCommerceLoanTabSpec;
import com.contextlogic.wish.api.model.WishCreditCardInfo;
import com.contextlogic.wish.api.model.WishLoanRepaymentBannerSpec;
import com.contextlogic.wish.api.model.WishPaymentStructureSelectionSpec;
import com.contextlogic.wish.api.model.WishSavedForLaterProduct;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.AddToSavedForLaterService;
import com.contextlogic.wish.api.service.standalone.AddToWishlistService;
import com.contextlogic.wish.api.service.standalone.ApplyPromoCodeService;
import com.contextlogic.wish.api.service.standalone.CartAbandonOfferClaimService;
import com.contextlogic.wish.api.service.standalone.CartAbandonOfferDismissService;
import com.contextlogic.wish.api.service.standalone.ChangeShippingOptionService;
import com.contextlogic.wish.api.service.standalone.DeleteShippingAddressService;
import com.contextlogic.wish.api.service.standalone.GetCartService;
import com.contextlogic.wish.api.service.standalone.GetCartService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetProductService;
import com.contextlogic.wish.api.service.standalone.GetSavedForLaterService;
import com.contextlogic.wish.api.service.standalone.GetUserShippingDetailsService;
import com.contextlogic.wish.api.service.standalone.GetUserWishlistsService;
import com.contextlogic.wish.api.service.standalone.GetWishlistNameSuggestionService;
import com.contextlogic.wish.api.service.standalone.OnfidoUpdateUserStateService;
import com.contextlogic.wish.api.service.standalone.RemoveFromSavedForLaterService;
import com.contextlogic.wish.api.service.standalone.UpdateCartService;
import com.contextlogic.wish.api.service.standalone.UpdateShippingInfoService;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.addtocartoffer.AddToCartOfferDialogFragment;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog.SuccessBottomSheetDialogDismissCallback;
import com.contextlogic.wish.dialog.commerceloan.CommerceLoanDatePickerDialog;
import com.contextlogic.wish.dialog.cvv.CVVConfirmationDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.ImageSize;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.dialog.promocode.PromoCodeDialog;
import com.contextlogic.wish.dialog.promocode.PromoCodeDialogFragment;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.CartContext.CartContextUpdatedCallback;
import com.contextlogic.wish.payments.braintree.BraintreeFragmentCallback;
import com.contextlogic.wish.payments.checkout.CartCheckoutUiController;
import com.contextlogic.wish.payments.google.GooglePayManager;
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
import com.contextlogic.wish.util.CreditCardUtil;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public class CartServiceFragment extends ServiceFragment<CartActivity> implements PaymentFormServiceProvider, CartContextUpdatedCallback, CartCheckoutUiController, CartPaymentProcessorServiceFragment<CartActivity>, CartPaymentVaultProcessorServiceFragment<CartActivity> {
    private AddToSavedForLaterService mAddToSavedForLaterService;
    /* access modifiers changed from: private */
    public AddToWishlistService mAddToWishListService;
    protected List<WishShippingInfo> mAddressBook;
    /* access modifiers changed from: private */
    public ApplyPromoCodeService mApplyPromoCodeService;
    private WebView mCachedBillingWebView;
    private WebView mCachedEmbeddedBillingWebView;
    private CartAbandonOfferClaimService mCartAbandonClaimService;
    private CartAbandonOfferDismissService mCartAbandonDismissService;
    protected CartContext mCartContext;
    private ChangeShippingOptionService mChangeShippingOptionService;
    private DeleteShippingAddressService mDeleteShippingAddressService;
    private GetCartService mGetCartService;
    private GetProductService mGetProductService;
    private GetSavedForLaterService mGetSavedForLaterService;
    private GetUserShippingDetailsService mGetUserShippingDetailsService;
    private GetUserWishlistsService mGetUserWishlistsService;
    private GetWishlistNameSuggestionService mGetWishlistNameSuggestionService;
    private OnfidoUpdateUserStateService mOnfidoUpdateUserStateService;
    /* access modifiers changed from: private */
    public PromoCodeDialog mPromoCodeDialog;
    private RemoveFromSavedForLaterService mRemoveFromSavedForLaterService;
    private UpdateCartService mUpdateCartService;
    private UpdateShippingInfoService mUpdateShippingInfoService;

    public interface UpdateAddressBookCallback {
        void onSuccess(List<WishShippingInfo> list, String str);
    }

    private enum WishlistDialogAction {
        CONTINUE,
        VIEW_WISHLIST
    }

    public boolean couldLeadToFreeGiftClaimedDialog() {
        return false;
    }

    public boolean couldLeadToOrderConfirmationPage() {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCartContext = new CartContext();
        this.mCartContext.setUpdatedCallback(this);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mUpdateCartService = new UpdateCartService();
        this.mChangeShippingOptionService = new ChangeShippingOptionService();
        this.mGetCartService = new GetCartService();
        this.mUpdateShippingInfoService = new UpdateShippingInfoService();
        this.mCartAbandonDismissService = new CartAbandonOfferDismissService();
        this.mCartAbandonClaimService = new CartAbandonOfferClaimService();
        this.mGetProductService = new GetProductService();
        this.mApplyPromoCodeService = new ApplyPromoCodeService();
        this.mAddToWishListService = new AddToWishlistService();
        this.mAddToSavedForLaterService = new AddToSavedForLaterService();
        this.mGetUserWishlistsService = new GetUserWishlistsService();
        this.mGetWishlistNameSuggestionService = new GetWishlistNameSuggestionService();
        this.mGetSavedForLaterService = new GetSavedForLaterService();
        this.mRemoveFromSavedForLaterService = new RemoveFromSavedForLaterService();
        this.mGetUserShippingDetailsService = new GetUserShippingDetailsService();
        this.mDeleteShippingAddressService = new DeleteShippingAddressService();
        this.mOnfidoUpdateUserStateService = new OnfidoUpdateUserStateService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mUpdateCartService.cancelAllRequests();
        this.mChangeShippingOptionService.cancelAllRequests();
        this.mGetCartService.cancelAllRequests();
        this.mUpdateShippingInfoService.cancelAllRequests();
        this.mCartAbandonDismissService.cancelAllRequests();
        this.mCartAbandonClaimService.cancelAllRequests();
        this.mGetProductService.cancelAllRequests();
        this.mApplyPromoCodeService.cancelAllRequests();
        this.mAddToWishListService.cancelAllRequests();
        this.mAddToSavedForLaterService.cancelAllRequests();
        this.mGetUserWishlistsService.cancelAllRequests();
        this.mGetWishlistNameSuggestionService.cancelAllRequests();
        this.mGetSavedForLaterService.cancelAllRequests();
        this.mRemoveFromSavedForLaterService.cancelAllRequests();
        this.mGetUserShippingDetailsService.cancelAllRequests();
        this.mDeleteShippingAddressService.cancelAllRequests();
        this.mOnfidoUpdateUserStateService.cancelAllRequests();
    }

    public void onDestroy() {
        super.onDestroy();
        cleanupCachedBillingWebView();
        cleanupCachedEmbeddedBillingWebView();
    }

    public void showUserBlockedDialog() {
        String string;
        final boolean shouldSeeOnfidoFlow = ExperimentDataCenter.getInstance().shouldSeeOnfidoFlow();
        final int i = shouldSeeOnfidoFlow ? R.string.contact_support_onfido : R.string.contact_support;
        final int i2 = shouldSeeOnfidoFlow ? R.string.please_contact_support_onfido : R.string.please_contact_support;
        if (shouldSeeOnfidoFlow) {
            string = getString(R.string.error_blocked_user_onfido);
        } else {
            string = getString(R.string.error_blocked_user, WebViewActivity.getPaymentIssueUrl());
        }
        final String str = string;
        AnonymousClass1 r1 = new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                if (shouldSeeOnfidoFlow) {
                    CartServiceFragment.this.showBrowser(WebViewActivity.getPaymentIssueUrl(), false, false);
                    return;
                }
                ArrayList arrayList = new ArrayList();
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, CartServiceFragment.this.getString(i), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                arrayList.add(multiButtonDialogChoice);
                cartActivity.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(CartServiceFragment.this.getString(i2)).setSubTitle(str).setButtons(arrayList).build(), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 0) {
                            CartServiceFragment.this.showBrowser(WebViewActivity.getPaymentIssueUrl(), false, false);
                        }
                    }
                });
            }
        };
        withVerifiedAuthenticationActivity(r1);
    }

    public void showConfirmCVVDialog(final SuccessListener successListener, final FailureListener failureListener, final CartPaymentProcessor cartPaymentProcessor) {
        withVerifiedAuthenticationActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                cartActivity.startDialog(CVVConfirmationDialogFragment.createCVVConfirmationDialog(CartServiceFragment.this.mCartContext), new BaseDialogCallback() {
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
        AnonymousClass3 r0 = new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                MultiButtonDialogFragment multiButtonDialogFragment;
                CartActivity cartActivity2 = cartActivity;
                ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < arrayList3.size(); i++) {
                    PaymentMode paymentMode = (PaymentMode) arrayList3.get(i);
                    if (i == 0) {
                        MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(arrayList2.size(), (String) arrayList4.get(i), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                        arrayList.add(multiButtonDialogChoice);
                    } else {
                        MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(arrayList2.size(), (String) arrayList4.get(i), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                        arrayList.add(multiButtonDialogChoice2);
                    }
                    arrayList2.add(paymentMode);
                }
                if (arrayList3.size() == 1) {
                    MultiButtonDialogChoice multiButtonDialogChoice3 = new MultiButtonDialogChoice(arrayList3.size(), cartActivity2.getString(R.string.cancel), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                    arrayList.add(multiButtonDialogChoice3);
                    multiButtonDialogFragment = new MultiButtonDialogFragmentBuilder().setButtons(arrayList).setTitle(str3).setCancelable(true).hideXButton().setSubTitle(str4).build();
                } else {
                    multiButtonDialogFragment = new MultiButtonDialogFragmentBuilder().setButtons(arrayList).setTitle(str3).setCancelable(true).setSubTitle(str4).build();
                }
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_CART_REDIRECT_BILLING_DIALOG);
                cartActivity2.startDialog(multiButtonDialogFragment, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CART_BILLING_REDIRECT_CLOSE);
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, final int i, Bundle bundle) {
                        if (i != arrayList2.size()) {
                            CartServiceFragment.this.showLoadingSpinner();
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CART_BILLING_REDIRECT_OPTION, Integer.toString(((PaymentMode) arrayList2.get(i)).ordinal()));
                            CartServiceFragment.this.refreshCart(new SuccessCallback() {
                                public void onSuccess(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishCommerceLoanTabSpec wishCommerceLoanTabSpec, WishCommerceLoanBannerSpec wishCommerceLoanBannerSpec, WishPaymentStructureSelectionSpec wishPaymentStructureSelectionSpec, WishLoanRepaymentBannerSpec wishLoanRepaymentBannerSpec) {
                                    CartServiceFragment.this.mCartContext.updateData(wishCart, wishShippingInfo, wishUserBillingInfo);
                                    CartServiceFragment.this.showBillingView(false, CartBaseBillingOptionSelectorView.getCartBillingSectionByPaymentMode((PaymentMode) arrayList2.get(i)));
                                    CartServiceFragment.this.hideLoadingSpinner();
                                }
                            }, new DefaultFailureCallback() {
                                public void onFailure(String str) {
                                    CartServiceFragment.this.hideLoadingSpinner();
                                }
                            });
                            return;
                        }
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CART_BILLING_REDIRECT_CLOSE);
                    }
                });
            }
        };
        withVerifiedAuthenticationActivity(r0);
    }

    public void withCartPaymentProcessorServiceFragment(CartPaymentProcessorServiceFragmentTask cartPaymentProcessorServiceFragmentTask) {
        cartPaymentProcessorServiceFragmentTask.performTask(this);
    }

    public CartContext getCartContext() {
        return this.mCartContext;
    }

    public void loadCart(String str, String str2, String str3, int i, String str4, boolean z) {
        if (str == null || str2 == null) {
            loadCart(null, false);
            return;
        }
        final boolean z2 = z;
        this.mUpdateCartService.requestService(str, str2, str3, i, true, z2, str4, new UpdateCartService.SuccessCallback() {
            public void onSuccess(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishCommerceLoanTabSpec wishCommerceLoanTabSpec, WishPaymentStructureSelectionSpec wishPaymentStructureSelectionSpec) {
                CartServiceFragment.this.mCartContext.setCommerceLoanTabSpec(wishCommerceLoanTabSpec);
                CartServiceFragment.this.mCartContext.setPaymentStructureSelectionSpec(wishPaymentStructureSelectionSpec);
                CartServiceFragment.this.mCartContext.updateData(wishCart, wishShippingInfo, wishUserBillingInfo);
                CartServiceFragment.this.checkGooglePayPaymentPreference(null, z2);
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.loadCart(str, false);
            }
        });
    }

    public void deleteShippingAddress(WishShippingInfo wishShippingInfo, final UpdateAddressBookCallback updateAddressBookCallback) {
        showLoadingSpinner();
        this.mDeleteShippingAddressService.requestService(wishShippingInfo, new DeleteShippingAddressService.SuccessCallback() {
            public void onSuccess(ArrayList<WishShippingInfo> arrayList, String str) {
                CartServiceFragment.this.mAddressBook = arrayList;
                CartServiceFragment.this.hideLoadingSpinner();
                updateAddressBookCallback.onSuccess(arrayList, str);
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.hideLoadingSpinner();
                if (str == null) {
                    str = CartServiceFragment.this.getString(R.string.something_went_wrong);
                }
                CartServiceFragment.this.showErrorMessage(str);
            }
        });
    }

    public void getUserShippingInfo(final UpdateAddressBookCallback updateAddressBookCallback) {
        showLoadingSpinner();
        this.mGetUserShippingDetailsService.requestService(new GetUserShippingDetailsService.SuccessCallback() {
            public void onSuccess(List<WishShippingInfo> list, String str) {
                CartServiceFragment.this.mAddressBook = list;
                updateAddressBookCallback.onSuccess(list, str);
                CartServiceFragment.this.hideLoadingSpinner();
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.hideLoadingSpinner();
                if (str == null) {
                    str = CartServiceFragment.this.getString(R.string.something_went_wrong);
                }
                CartServiceFragment.this.showErrorMessage(str);
            }
        });
    }

    public void addFromSavedForLaterToCart(final WishSavedForLaterProduct wishSavedForLaterProduct) {
        String optionId;
        showLoadingSpinner();
        if (wishSavedForLaterProduct.getSelectedShippingOption() == null) {
            optionId = "standard";
        } else {
            optionId = wishSavedForLaterProduct.getSelectedShippingOption().getOptionId();
        }
        this.mUpdateCartService.requestService(wishSavedForLaterProduct.getProductId(), wishSavedForLaterProduct.getVariationId(), optionId, 1, false, false, null, new UpdateCartService.SuccessCallback() {
            public void onSuccess(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishCommerceLoanTabSpec wishCommerceLoanTabSpec, WishPaymentStructureSelectionSpec wishPaymentStructureSelectionSpec) {
                CartServiceFragment.this.mCartContext.updateData(wishCart, wishShippingInfo, wishUserBillingInfo);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SAVED_FOR_LATER_ADD_TO_CART, wishSavedForLaterProduct.getProductId());
                CartServiceFragment.this.hideLoadingSpinner();
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.hideLoadingSpinner();
                if (str == null) {
                    str = CartServiceFragment.this.getString(R.string.error_updating_item_in_cart);
                }
                CartServiceFragment.this.showErrorMessage(str);
            }
        });
    }

    public void removeFromSaveForLater(WishSavedForLaterProduct wishSavedForLaterProduct, final boolean z) {
        showLoadingSpinner();
        this.mRemoveFromSavedForLaterService.requestService(wishSavedForLaterProduct, new RemoveFromSavedForLaterService.SuccessCallback() {
            public void onSuccess(ArrayList<WishSavedForLaterProduct> arrayList) {
                CartServiceFragment.this.mCartContext.updateSavedForLater(arrayList, z);
                CartServiceFragment.this.hideLoadingSpinner();
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.showErrorMessage(str);
            }
        });
    }

    public void removeFromSaveForLaterAndAddToCart(final WishSavedForLaterProduct wishSavedForLaterProduct, final boolean z) {
        showLoadingSpinner();
        this.mRemoveFromSavedForLaterService.requestService(wishSavedForLaterProduct, new RemoveFromSavedForLaterService.SuccessCallback() {
            public void onSuccess(ArrayList<WishSavedForLaterProduct> arrayList) {
                CartServiceFragment.this.mCartContext.updateSavedForLater(arrayList, z);
                CartServiceFragment.this.addFromSavedForLaterToCart(wishSavedForLaterProduct);
                CartServiceFragment.this.handleRemovedFromSavedForLaterSuccess(wishSavedForLaterProduct);
                CartServiceFragment.this.hideLoadingSpinner();
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.showErrorMessage(str);
            }
        });
    }

    public void loadSavedForLater() {
        if (ExperimentDataCenter.getInstance().shouldShowSaveForLater()) {
            this.mGetSavedForLaterService.requestService(new GetSavedForLaterService.SuccessCallback() {
                public void onSuccess(ArrayList<WishSavedForLaterProduct> arrayList) {
                    CartServiceFragment.this.mCartContext.updateSavedForLater(arrayList, true);
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                    CartServiceFragment.this.mCartContext.updateSavedForLater(null, true);
                }
            });
        }
    }

    public void loadCart(final String str, final boolean z) {
        this.mGetCartService.requestService(new SuccessCallback() {
            public void onSuccess(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishCommerceLoanTabSpec wishCommerceLoanTabSpec, WishCommerceLoanBannerSpec wishCommerceLoanBannerSpec, WishPaymentStructureSelectionSpec wishPaymentStructureSelectionSpec, WishLoanRepaymentBannerSpec wishLoanRepaymentBannerSpec) {
                CartServiceFragment.this.mCartContext.setCommerceLoanTabSpec(wishCommerceLoanTabSpec);
                CartServiceFragment.this.mCartContext.setCommerceLoanBannerSpec(wishCommerceLoanBannerSpec);
                CartServiceFragment.this.mCartContext.setPaymentStructureSelectionSpec(wishPaymentStructureSelectionSpec);
                CartServiceFragment.this.mCartContext.setLoanRepaymentBannerSpec(wishLoanRepaymentBannerSpec);
                CartServiceFragment.this.mCartContext.updateData(wishCart, wishShippingInfo, wishUserBillingInfo);
                CartServiceFragment.this.checkGooglePayPaymentPreference(str, z);
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CartFragment>() {
                    public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                        cartFragment.handleCartLoadError();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    /* access modifiers changed from: protected */
    public void checkGooglePayPaymentPreference(final String str, final boolean z) {
        if (this.mCartContext.getEffectivePaymentMode().equals("PaymentModeGoogle")) {
            withBraintreeFragment(new BraintreeFragmentCallback() {
                public void onBraintreeFragmentLoaded(BraintreeFragment braintreeFragment) {
                    GooglePayment.isReadyToPay(braintreeFragment, new BraintreeResponseListener<Boolean>() {
                        public void onResponse(Boolean bool) {
                            GooglePayManager.getInstance().setGooglePayReady(bool.booleanValue());
                            String string = PreferenceUtil.getString("LastPaidPaymentMethod", "");
                            if (!bool.booleanValue() && !string.equals("PaymentModeGoogle")) {
                                CartServiceFragment.this.mCartContext.updatePreferredPaymentMode("PaymentModeCC");
                            }
                            CartServiceFragment.this.completeCartLoading(str, z);
                        }
                    });
                }

                public void onBraintreeFragmentLoadFailed(String str) {
                    if (CartServiceFragment.this.mCartContext.getEffectivePaymentMode().equals("PaymentModeGoogle")) {
                        CartServiceFragment.this.mCartContext.updatePreferredPaymentMode("PaymentModeCC");
                    }
                    CartServiceFragment.this.completeCartLoading(str, z);
                }
            });
        } else {
            completeCartLoading(str, z);
        }
    }

    /* access modifiers changed from: private */
    public void completeCartLoading(String str, boolean z) {
        withUiFragment(new UiTask<BaseActivity, CartFragment>() {
            public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                cartFragment.handleCartLoadSuccess(CartServiceFragment.this.mCartContext);
            }
        }, "FragmentTagMainContent");
        if (str != null) {
            showErrorMessage(str);
        } else if (this.mCartContext.getCart() != null && this.mCartContext.getCart().getAddToCartOfferApplied() != null && !this.mCartContext.getCart().getAddToCartOfferApplied().isExpired()) {
            final AddToCartOfferDialogFragment createAddToCartOfferDialog = AddToCartOfferDialogFragment.createAddToCartOfferDialog(this.mCartContext.getCart().getAddToCartOfferApplied());
            if (createAddToCartOfferDialog != null) {
                withActivity(new ActivityTask<CartActivity>() {
                    public void performTask(CartActivity cartActivity) {
                        cartActivity.startDialog(createAddToCartOfferDialog);
                    }
                });
            }
        } else if (z && this.mCartContext.getCheckoutActionManager().getCheckoutButtonContext().allowExpressCheckout()) {
            this.mCartContext.getCheckoutActionManager().checkout(this, z);
        }
    }

    public void removeFromCartAndReloadSavedForLater(String str, String str2) {
        showLoadingSpinner();
        this.mUpdateCartService.requestService(str, str2, null, 0, false, false, null, new UpdateCartService.SuccessCallback() {
            public void onSuccess(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishCommerceLoanTabSpec wishCommerceLoanTabSpec, WishPaymentStructureSelectionSpec wishPaymentStructureSelectionSpec) {
                CartServiceFragment.this.hideLoadingSpinner();
                CartServiceFragment.this.mCartContext.updateData(wishCart, wishShippingInfo, wishUserBillingInfo);
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.hideLoadingSpinner();
                if (str == null) {
                    str = CartServiceFragment.this.getString(R.string.error_updating_item_in_cart);
                }
                CartServiceFragment.this.showErrorMessage(str);
            }
        });
    }

    public void updateCart(String str, String str2, String str3, int i) {
        showLoadingSpinner();
        this.mUpdateCartService.requestService(str, str2, str3, i, false, false, null, new UpdateCartService.SuccessCallback() {
            public void onSuccess(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishCommerceLoanTabSpec wishCommerceLoanTabSpec, WishPaymentStructureSelectionSpec wishPaymentStructureSelectionSpec) {
                CartServiceFragment.this.hideLoadingSpinner();
                CartServiceFragment.this.mCartContext.setCommerceLoanTabSpec(wishCommerceLoanTabSpec);
                CartServiceFragment.this.mCartContext.setPaymentStructureSelectionSpec(wishPaymentStructureSelectionSpec);
                CartServiceFragment.this.mCartContext.updateData(wishCart, wishShippingInfo, wishUserBillingInfo);
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.hideLoadingSpinner();
                if (str == null) {
                    str = CartServiceFragment.this.getString(R.string.error_updating_item_in_cart);
                }
                CartServiceFragment.this.showErrorMessage(str);
            }
        });
    }

    public void changeShippingOption(WishCartItem wishCartItem, String str) {
        showLoadingSpinner();
        this.mChangeShippingOptionService.requestService(wishCartItem.getProductId(), wishCartItem.getVariationId(), str, wishCartItem.getQuantity(), new ChangeShippingOptionService.SuccessCallback() {
            public void onSuccess(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo) {
                CartServiceFragment.this.hideLoadingSpinner();
                CartServiceFragment.this.mCartContext.updateData(wishCart, wishShippingInfo, wishUserBillingInfo);
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.hideLoadingSpinner();
                if (str == null) {
                    str = CartServiceFragment.this.getString(R.string.error_updating_item_in_cart);
                }
                CartServiceFragment.this.showErrorMessage(str);
            }
        });
    }

    public void onCartContextUpdated(CartContext cartContext) {
        withUiFragment(new UiTask<BaseActivity, CartFragment>() {
            public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                cartFragment.refreshUi();
            }
        }, "FragmentTagMainContent");
    }

    public void reInitializeCartContext(WishCart wishCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo, WishLoanRepaymentBannerSpec wishLoanRepaymentBannerSpec) {
        this.mCartContext.updateData(wishCart, wishShippingInfo, wishUserBillingInfo, wishLoanRepaymentBannerSpec);
        withUiFragment(new UiTask<BaseActivity, CartFragment>() {
            public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                cartFragment.handleCartLoadSuccess(CartServiceFragment.this.mCartContext);
            }
        }, "FragmentTagMainContent");
    }

    public void showExternalBrowser(final String str) {
        withVerifiedAuthenticationActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                cartActivity.startActivity(intent);
                cartActivity.finishActivity();
            }
        });
    }

    public void showBrowser(final String str, final boolean z, final boolean z2) {
        withVerifiedAuthenticationActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                Intent intent = new Intent();
                if (!z2 || !ExperimentDataCenter.getInstance().canUseKlarnaSDK()) {
                    intent.setClass(cartActivity, WebViewActivity.class);
                } else {
                    intent.setClass(cartActivity, KlarnaWebViewActivity.class);
                }
                intent.putExtra("ExtraUrl", str);
                intent.putExtra("ExtraSource", Source.CART);
                intent.putExtra("ExtraLoadKlarnaSDK", z2);
                if (z) {
                    String str = null;
                    try {
                        JSONArray jSONArray = new JSONArray();
                        HashSet hashSet = new HashSet();
                        Iterator it = CartServiceFragment.this.mCartContext.getCart().getItems().iterator();
                        while (it.hasNext()) {
                            WishCartItem wishCartItem = (WishCartItem) it.next();
                            if (!hashSet.contains(wishCartItem.getProductId())) {
                                hashSet.add(wishCartItem.getProductId());
                                jSONArray.put(wishCartItem.getProductId());
                            }
                        }
                        str = jSONArray.toString();
                    } catch (Throwable unused) {
                    }
                    if (str != null) {
                        intent.putExtra("ExtraTransactionCartItemIds", str);
                        intent.putExtra("ExtraTransactionCartAmount", CartServiceFragment.this.mCartContext.getCart().getTotal().getValue());
                        intent.putExtra("ExtraTransactionCurrencyCode", CartServiceFragment.this.mCartContext.getCart().getTotal().getLocalizedCurrencyCode());
                    }
                }
                cartActivity.startActivity(intent);
                if (z) {
                    cartActivity.finishActivity();
                }
            }
        });
    }

    public void showOrderConfirmedActivity(final String str, String str2) {
        withVerifiedAuthenticationActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                String str;
                Intent intent = new Intent();
                intent.setClass(cartActivity, OrderConfirmedActivity.class);
                intent.putExtra("ArgTransactionId", str);
                try {
                    JSONArray jSONArray = new JSONArray();
                    HashSet hashSet = new HashSet();
                    Iterator it = CartServiceFragment.this.mCartContext.getCart().getItems().iterator();
                    while (it.hasNext()) {
                        WishCartItem wishCartItem = (WishCartItem) it.next();
                        if (!hashSet.contains(wishCartItem.getProductId())) {
                            hashSet.add(wishCartItem.getProductId());
                            jSONArray.put(wishCartItem.getProductId());
                        }
                    }
                    str = jSONArray.toString();
                } catch (Throwable unused) {
                    str = null;
                }
                if (str != null) {
                    intent.putExtra("ExtraTransactionCartItemIds", str);
                    intent.putExtra("ExtraTransactionCartAmount", CartServiceFragment.this.mCartContext.getCart().getTotal().getValue());
                    intent.putExtra("ExtraTransactionCurrencyCode", CartServiceFragment.this.mCartContext.getCart().getTotal().getLocalizedCurrencyCode());
                    intent.putExtra("ExtraHasUpfrontLoanPayment", CartServiceFragment.this.mCartContext.getPayHalfLaterFlag());
                }
                cartActivity.startActivity(intent);
                cartActivity.finishActivity();
            }
        });
    }

    public void showShippingView(final boolean z) {
        withUiFragment(new UiTask<BaseActivity, CartFragment>() {
            public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                cartFragment.showShippingView(z, false, CartServiceFragment.this.getCartContext().getShippingInfo());
            }
        }, "FragmentTagMainContent");
    }

    public void showAddressBook(final String str) {
        withUiFragment(new UiTask<BaseActivity, CartFragment>() {
            public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                cartFragment.showAddressBook(str);
            }
        }, "FragmentTagMainContent");
    }

    public void showBillingView(boolean z) {
        showBillingView(z, null);
    }

    public void showBillingView(final boolean z, final CartBillingSection cartBillingSection) {
        withUiFragment(new UiTask<BaseActivity, CartFragment>() {
            public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                if (ExperimentDataCenter.getInstance().canCheckoutWithKlarnaOnly(CartServiceFragment.this.getCartContext())) {
                    CartServiceFragment.this.getCartContext().updatePreferredPaymentMode("PaymentModeKlarna");
                    cartFragment.checkout(false);
                    return;
                }
                cartFragment.showBillingView(z, cartBillingSection);
            }
        }, "FragmentTagMainContent");
    }

    public void showItemsView() {
        withUiFragment(new UiTask<BaseActivity, CartFragment>() {
            public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                cartFragment.showItemsView(false);
            }
        }, "FragmentTagMainContent");
    }

    public void showCommerceLoanCCBillingView(final boolean z) {
        withUiFragment(new UiTask<BaseActivity, CartFragment>() {
            public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                cartFragment.showCommerceLoanCCBillingView(z);
            }
        }, "FragmentTagMainContent");
    }

    public void refreshCart(SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback) {
        this.mGetCartService.requestService(successCallback, defaultFailureCallback);
    }

    public void removeCartItem(final WishCartItem wishCartItem) {
        withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                cartActivity.startDialog(MultiButtonDialogFragment.createMultiButtonYesNoDialog(cartActivity.getString(R.string.are_you_sure), cartActivity.getString(R.string.do_you_want_to_remove_item_from_cart)), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            CartServiceFragment.this.updateCart(wishCartItem.getProductId(), wishCartItem.getVariationId(), null, 0);
                        }
                    }
                });
            }
        });
    }

    public void cleanupCachedBillingWebView() {
        if (this.mCachedBillingWebView != null) {
            this.mCachedBillingWebView.stopLoading();
            this.mCachedBillingWebView.setWebViewClient(null);
            this.mCachedBillingWebView.loadUrl("about:blank");
            this.mCachedBillingWebView.onPause();
            this.mCachedBillingWebView = null;
        }
    }

    /* access modifiers changed from: private */
    public void navigateBack() {
        if ((this.mAddressBook == null || this.mAddressBook.isEmpty()) && !((CartActivity) getBaseActivity()).shouldStartInAddressBook()) {
            showItemsView();
        } else {
            showAddressBook(null);
        }
    }

    public void confirmShippingViewClosing(boolean z) {
        if (z) {
            navigateBack();
        } else {
            withActivity(new ActivityTask<CartActivity>() {
                public void performTask(CartActivity cartActivity) {
                    String string = cartActivity.getString(R.string.leave_form_question);
                    String string2 = cartActivity.getString(R.string.leave_form_description);
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_LEAVE_FORM_DIALOG);
                    cartActivity.startDialog(MultiButtonDialogFragment.createCustomMultiButtonYesNoDialog(string, string2, cartActivity.getString(R.string.continue_editing), cartActivity.getString(R.string.leave_form)), new BaseDialogCallback() {
                        public void onCancel(BaseDialogFragment baseDialogFragment) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_DISMISS_LEAVE_FORM_DIALOG);
                        }

                        public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                            if (i == 1) {
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_CONTINUE_EDITING);
                                return;
                            }
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_LEAVE_FORM);
                            CartServiceFragment.this.navigateBack();
                        }
                    });
                }
            });
        }
    }

    public void setShippingAddress(WishShippingInfo wishShippingInfo) {
        showLoadingSpinner();
        this.mUpdateShippingInfoService.requestService(wishShippingInfo.getName(), wishShippingInfo.getStreetAddressLineOne(), wishShippingInfo.getStreetAddressLineTwo(), wishShippingInfo.getCity(), wishShippingInfo.getState(), wishShippingInfo.getZipCode(), wishShippingInfo.getCountryCode(), wishShippingInfo.getPhoneNumber(), false, wishShippingInfo.getId(), new UpdateShippingInfoService.SuccessCallback() {
            public void onSuccess(WishShippingInfo wishShippingInfo, WishCart wishCart) {
                CartServiceFragment.this.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT_SUCCESS);
                CartServiceFragment.this.mCartContext.updateData(wishCart, wishShippingInfo, CartServiceFragment.this.mCartContext.getUserBillingInfo());
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT_FAILURE);
                CartServiceFragment.this.showErrorMessage(str);
            }
        });
    }

    public void saveEnteredShippingAddress(WishShippingInfo wishShippingInfo, boolean z, boolean z2) {
        showLoadingSpinner();
        final boolean z3 = z;
        final boolean z4 = z2;
        this.mUpdateShippingInfoService.requestService(wishShippingInfo.getName(), wishShippingInfo.getStreetAddressLineOne(), wishShippingInfo.getStreetAddressLineTwo(), wishShippingInfo.getCity(), wishShippingInfo.getState(), wishShippingInfo.getZipCode(), wishShippingInfo.getCountryCode(), wishShippingInfo.getPhoneNumber(), z4, wishShippingInfo.getId(), new UpdateShippingInfoService.SuccessCallback() {
            public void onSuccess(WishShippingInfo wishShippingInfo, WishCart wishCart) {
                if (CartServiceFragment.this.getActivity() != null) {
                    CartServiceFragment.this.hideLoadingSpinner();
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT_SUCCESS);
                    CartServiceFragment.this.mCartContext.updateData(wishCart, wishShippingInfo, CartServiceFragment.this.mCartContext.getUserBillingInfo());
                    String string = CartServiceFragment.this.getString(R.string.address_has_been_upated);
                    if (z4) {
                        string = CartServiceFragment.this.getString(R.string.address_has_been_added);
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_ADD_ADDRESS_SUCCESS_DIALOG);
                    } else {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_EDIT_ADDRESS_SUCCESS_DIALOG);
                    }
                    CartServiceFragment.this.showAddressBook(string);
                    if (z3) {
                        CartServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CartFragment>() {
                            public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                                cartFragment.checkout(false);
                            }
                        }, "FragmentTagMainContent");
                    }
                }
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                if (CartServiceFragment.this.getActivity() != null) {
                    CartServiceFragment.this.hideLoadingSpinner();
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT_FAILURE);
                    CartServiceFragment.this.showErrorMessage(str);
                }
            }
        });
    }

    public void confirmBillingViewClosing(final CartBillingSection cartBillingSection, final boolean z, boolean z2) {
        if (z2) {
            closeBillingView(cartBillingSection, z);
        } else {
            withActivity(new ActivityTask<CartActivity>() {
                public void performTask(CartActivity cartActivity) {
                    cartActivity.startDialog(MultiButtonDialogFragment.createMultiButtonYesNoDialog(cartActivity.getString(R.string.are_you_sure), cartActivity.getString(R.string.do_you_want_to_cancel_entering_payment)), new BaseDialogCallback() {
                        public void onCancel(BaseDialogFragment baseDialogFragment) {
                        }

                        public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                            if (i == 1) {
                                CartServiceFragment.this.closeBillingView(cartBillingSection, z);
                            }
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void closeBillingView(CartBillingSection cartBillingSection, boolean z) {
        if (cartBillingSection != null) {
            showBillingView(z, cartBillingSection);
        } else {
            showItemsView();
        }
    }

    public void selectBillingSection(final CartBillingSection cartBillingSection) {
        CartPaymentVaultProcessor paymentProcessor = CartPaymentVaultProcessorSelector.getPaymentProcessor(cartBillingSection, this.mCartContext, this);
        if (paymentProcessor != null) {
            paymentProcessor.prepareTab(new PrepareListener() {
                public void onTabPrepareCancelled(CartPaymentVaultProcessor cartPaymentVaultProcessor) {
                }

                public void onTabPrepared(CartPaymentVaultProcessor cartPaymentVaultProcessor) {
                    CartServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CartFragment>() {
                        public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                            cartFragment.completeBillingSectionSelected(cartBillingSection);
                        }
                    }, "FragmentTagMainContent");
                }

                public void onTabPrepareFailed(CartPaymentVaultProcessor cartPaymentVaultProcessor, String str) {
                    if (str != null) {
                        CartServiceFragment.this.showErrorMessage(str);
                    }
                }
            });
        }
    }

    public void saveBillingInformation(CartBillingSection cartBillingSection, Bundle bundle, final CartBillingSection cartBillingSection2, final boolean z) {
        CartPaymentVaultProcessor paymentProcessor = CartPaymentVaultProcessorSelector.getPaymentProcessor(cartBillingSection, this.mCartContext, this);
        if (paymentProcessor != null) {
            paymentProcessor.save(new SaveListener() {
                public void onSaveComplete(CartPaymentVaultProcessor cartPaymentVaultProcessor) {
                    if (cartBillingSection2 != null) {
                        CartServiceFragment.this.showBillingView(z, cartBillingSection2);
                        return;
                    }
                    CartServiceFragment.this.showItemsView();
                    if (z) {
                        CartServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CartFragment>() {
                            public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                                cartFragment.checkout(false);
                            }
                        }, "FragmentTagMainContent");
                    }
                }

                public void onSaveFailed(CartPaymentVaultProcessor cartPaymentVaultProcessor, String str) {
                    if (str == null) {
                        str = CartServiceFragment.this.getString(R.string.we_were_unable_to_update_your_billing_information);
                    }
                    CartServiceFragment.this.showErrorMessage(str);
                }
            }, bundle);
        }
    }

    public void claimCartAbandonOffer(String str) {
        this.mCartAbandonClaimService.requestService(str, new CartAbandonOfferClaimService.SuccessCallback() {
            public void onSuccess(final WishCart wishCart) {
                CartServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, BaseDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseDialogFragment baseDialogFragment) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("ResultCart", wishCart);
                        baseDialogFragment.makeSelection(bundle);
                        baseDialogFragment.hideProgressSpinner();
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, BaseDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseDialogFragment baseDialogFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(CartServiceFragment.this.getString(R.string.general_error)));
                        baseDialogFragment.hideProgressSpinner();
                    }
                });
            }
        });
    }

    public void dismissCartAbandonOffer(String str) {
        this.mCartAbandonDismissService.requestService(str, new DefaultSuccessCallback() {
            public void onSuccess() {
                CartServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, BaseDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseDialogFragment baseDialogFragment) {
                        baseActivity.finishActivity();
                        baseDialogFragment.hideProgressSpinner();
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, BaseDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseDialogFragment baseDialogFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(CartServiceFragment.this.getString(R.string.general_error)));
                        baseDialogFragment.hideProgressSpinner();
                    }
                });
            }
        });
    }

    public void showApplyPromoDialog() {
        final PromoCodeDialogFragment createPromoCodeDialogFragment = PromoCodeDialogFragment.createPromoCodeDialogFragment();
        withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                cartActivity.startDialog(createPromoCodeDialogFragment);
            }
        });
        this.mPromoCodeDialog = createPromoCodeDialogFragment;
    }

    public void applyPromoCodeService(final String str) {
        withUiFragment(new UiTask<BaseActivity, CartFragment>() {
            public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                CartServiceFragment.this.mApplyPromoCodeService.requestService(new ApplyPromoCodeService.SuccessCallback() {
                    public void onSuccess(WishCart wishCart) {
                        CartServiceFragment.this.mCartContext.updateData(wishCart, CartServiceFragment.this.getCartContext().getShippingInfo(), CartServiceFragment.this.getCartContext().getUserBillingInfo());
                        if (CartServiceFragment.this.mPromoCodeDialog != null) {
                            CartServiceFragment.this.mPromoCodeDialog.handleApplyPromoCodeSuccess(CartServiceFragment.this.getCartContext().getCart().getPromoCodeMessage());
                        }
                    }
                }, new DefaultFailureCallback() {
                    public void onFailure(String str) {
                        if (CartServiceFragment.this.mPromoCodeDialog != null) {
                            CartServiceFragment.this.mPromoCodeDialog.handleApplyPromoCodeFailure(str);
                        }
                    }
                }, cartFragment, str);
            }
        }, "FragmentTagMainContent");
    }

    public void setCachedEmbeddedBillingWebView(WebView webView) {
        this.mCachedEmbeddedBillingWebView = webView;
    }

    public WebView getCachedEmbeddedBillingWebView() {
        return this.mCachedEmbeddedBillingWebView;
    }

    public void cleanupCachedEmbeddedBillingWebView() {
        if (this.mCachedEmbeddedBillingWebView != null) {
            this.mCachedEmbeddedBillingWebView.stopLoading();
            this.mCachedEmbeddedBillingWebView.setWebViewClient(null);
            this.mCachedEmbeddedBillingWebView.loadUrl("about:blank");
            this.mCachedEmbeddedBillingWebView.onPause();
            this.mCachedEmbeddedBillingWebView = null;
        }
    }

    public boolean isApplyingCouponCode() {
        return this.mPromoCodeDialog != null && !this.mPromoCodeDialog.isCancelable();
    }

    public void showRemoveFromCartPrompt(final WishCartItem wishCartItem) {
        withUiFragment(new UiTask<CartActivity, CartFragment>() {
            public void performTask(CartActivity cartActivity, CartFragment cartFragment) {
                CartFragment cartFragment2 = cartFragment;
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, cartFragment2.getString(R.string.remove), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(1, cartFragment2.getString(R.string.save_to_wishlist), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                ArrayList arrayList = new ArrayList();
                arrayList.add(multiButtonDialogChoice);
                arrayList.add(multiButtonDialogChoice2);
                cartActivity.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(cartFragment2.getString(R.string.remove_this_item_from_cart)).setImageSize(ImageSize.SMALL).setButtons(arrayList).setWishImage(wishCartItem.getImage()).build(), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_FULL_SCREEN_REMOVE_FROM_CART_X, wishCartItem.getProductId());
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        switch (i) {
                            case 0:
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_FULL_SCREEN_REMOVE_FROM_CART_CONFIRM, wishCartItem.getProductId());
                                CartServiceFragment.this.updateCart(wishCartItem.getProductId(), wishCartItem.getVariationId(), null, 0);
                                return;
                            case 1:
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_FULL_SCREEN_REMOVE_FROM_CART_SAVE, wishCartItem.getProductId());
                                CartServiceFragment.this.addToWishlist(wishCartItem);
                                return;
                            default:
                                return;
                        }
                    }
                });
            }
        }, "FragmentTagMainContent");
    }

    public void showDailyGiveawayRemoveFromCartPrompt(final WishCartItem wishCartItem) {
        withUiFragment(new UiTask<CartActivity, CartFragment>() {
            public void performTask(CartActivity cartActivity, CartFragment cartFragment) {
                MultiButtonDialogFragment multiButtonDialogFragment;
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, cartFragment.getString(R.string.remove_item), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                ArrayList arrayList = new ArrayList();
                arrayList.add(multiButtonDialogChoice);
                final HashMap hashMap = new HashMap();
                if (ExperimentDataCenter.getInstance().shouldShowDailyRaffle()) {
                    multiButtonDialogFragment = new MultiButtonDialogFragmentBuilder().setTitle(cartFragment.getString(R.string.remove_daily_raffle_item)).setSubTitle(cartFragment.getString(R.string.remove_daily_raffle_confirmation)).setImageSize(ImageSize.SMALL).setButtons(arrayList).setWishImage(wishCartItem.getImage()).build();
                    hashMap.put("GiveawayType", "DailyRaffle");
                } else {
                    multiButtonDialogFragment = new MultiButtonDialogFragmentBuilder().setTitle(cartFragment.getString(R.string.remove_giveaway_item)).setSubTitle(cartFragment.getString(R.string.remove_giveaway_item_msg)).setImageSize(ImageSize.SMALL).setButtons(arrayList).setWishImage(wishCartItem.getImage()).build();
                    hashMap.put("GiveawayType", "DailyGiveaway");
                }
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_DAILY_GIVEAWAY_REMOVE_DIALOG, hashMap);
                cartActivity.startDialog(multiButtonDialogFragment, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_REMOVE_DIALOG_NO, hashMap);
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i != 0) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_REMOVE_DIALOG_NO, hashMap);
                            return;
                        }
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_DAILY_GIVEAWAY_REMOVE_DIALOG_YES, hashMap);
                        CartServiceFragment.this.updateCart(wishCartItem.getProductId(), wishCartItem.getVariationId(), null, 0);
                    }
                });
            }
        }, "FragmentTagMainContent");
    }

    public void createAndAddToWishlist(final WishCartItem wishCartItem) {
        final CreateWishlistDialogFragment createWishlistDialogFragment = new CreateWishlistDialogFragment();
        withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                cartActivity.startDialog(createWishlistDialogFragment, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SAVE_FOR_LATER_CREATE_WISHLIST, wishCartItem.getProductId());
                        CartServiceFragment.this.mAddToWishListService.requestService(wishCartItem.getProductId(), (String) null, false, bundle.getString("ResultName"), (AddToWishlistService.SuccessCallback) new AddToWishlistService.SuccessCallback() {
                            public void onSuccess(final WishWishlist wishWishlist) {
                                CartServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CartFragment>() {
                                    public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                                        CartServiceFragment.this.handleWishlistActionSuccess(wishCartItem, wishWishlist);
                                    }
                                }, "FragmentTagMainContent");
                            }
                        }, (DefaultFailureCallback) new DefaultFailureCallback() {
                            public void onFailure(String str) {
                                CartServiceFragment.this.handleWishlistActionFailure(str);
                            }
                        });
                    }
                });
            }
        });
        createWishlistDialogFragment.loadNameSuggestions(wishCartItem.getProductId());
    }

    public void addToWishlist(final WishCartItem wishCartItem) {
        final SelectWishlistDialogFragment selectWishlistDialogFragment = new SelectWishlistDialogFragment();
        withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                cartActivity.startDialog(selectWishlistDialogFragment, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 2000) {
                            CartServiceFragment.this.createAndAddToWishlist(wishCartItem);
                        } else if (bundle != null) {
                            WishWishlist wishWishlist = (WishWishlist) bundle.getParcelable("ResultWishlist");
                            if (wishWishlist != null) {
                                CartServiceFragment.this.addToWishlist(wishCartItem, wishWishlist.getWishlistId());
                            }
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleWishlistActionSuccess(final WishCartItem wishCartItem, final WishWishlist wishWishlist) {
        withUiFragment(new UiTask<CartActivity, CartFragment>() {
            public void performTask(CartActivity cartActivity, CartFragment cartFragment) {
                final CartActivity cartActivity2 = cartActivity;
                CartFragment cartFragment2 = cartFragment;
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(WishlistDialogAction.CONTINUE.ordinal(), cartFragment2.getString(R.string.continue_shopping), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(WishlistDialogAction.VIEW_WISHLIST.ordinal(), cartFragment2.getString(R.string.view_wishlist), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                ArrayList arrayList = new ArrayList();
                arrayList.add(multiButtonDialogChoice);
                arrayList.add(multiButtonDialogChoice2);
                cartActivity2.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(cartFragment2.getString(R.string.item_has_been_moved_to_wishlist, wishWishlist.getName())).setImageSize(ImageSize.SMALL).setButtons(arrayList).setWishImage(wishCartItem.getImage()).build(), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                        CartServiceFragment.this.updateCart(wishCartItem.getProductId(), wishCartItem.getVariationId(), null, 0);
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        CartServiceFragment.this.updateCart(wishCartItem.getProductId(), wishCartItem.getVariationId(), null, 0);
                        if (i == WishlistDialogAction.VIEW_WISHLIST.ordinal()) {
                            Intent intent = new Intent();
                            intent.setClass(cartActivity2, WishlistActivity.class);
                            IntentUtil.putParcelableExtra(intent, WishlistActivity.EXTRA_WISHLIST, wishWishlist);
                            intent.putExtra(WishlistActivity.EXTRA_CAN_EDIT_WISHLIST, true);
                            cartActivity2.startActivity(intent);
                        }
                    }
                });
            }
        }, "FragmentTagMainContent");
    }

    public void addFromCartToSavedForLater(final WishCartItem wishCartItem) {
        showLoadingSpinner();
        this.mAddToSavedForLaterService.requestService(wishCartItem, new AddToSavedForLaterService.SuccessCallback() {
            public void onSuccess(final ArrayList<WishSavedForLaterProduct> arrayList) {
                CartServiceFragment.this.hideLoadingSpinner();
                CartServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CartFragment>() {
                    public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                        CartServiceFragment.this.handleAddedToSavedForLaterSuccess(wishCartItem, arrayList);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.handleWishlistActionFailure(str);
            }
        });
    }

    public void handleRemovedFromSavedForLaterSuccess(final WishSavedForLaterProduct wishSavedForLaterProduct) {
        withUiFragment(new UiTask<CartActivity, CartFragment>() {
            public void performTask(CartActivity cartActivity, CartFragment cartFragment) {
                SuccessBottomSheetDialog.create(cartActivity).setTitle(CartServiceFragment.this.getResources().getQuantityString(R.plurals.item_added_to_cart, 1)).setImage(wishSavedForLaterProduct.getImage()).hideAfter(1500).autoDismiss().show();
            }
        }, "FragmentTagMainContent");
    }

    public void handleAddedToSavedForLaterSuccess(final WishCartItem wishCartItem, ArrayList<WishSavedForLaterProduct> arrayList) {
        this.mCartContext.updateSavedForLater(arrayList, false);
        withUiFragment(new UiTask<CartActivity, CartFragment>() {
            public void performTask(CartActivity cartActivity, final CartFragment cartFragment) {
                CartServiceFragment.this.removeFromCartAndReloadSavedForLater(wishCartItem.getProductId(), wishCartItem.getVariationId());
                cartFragment.refreshUi();
                SuccessBottomSheetDialog.create(cartActivity).setTitle(CartServiceFragment.this.getString(R.string.item_has_been_added_to_saved_for_later)).setImage(wishCartItem.getImage()).hideAfter(1500).setDismissCallback(new SuccessBottomSheetDialogDismissCallback() {
                    public void onDismiss() {
                        if (cartFragment != null && !PreferenceUtil.getBoolean("ScrollToSaveForLater")) {
                            cartFragment.scrollToSaveForLater();
                            PreferenceUtil.setBoolean("ScrollToSaveForLater", true);
                        }
                    }
                }).show();
            }
        }, "FragmentTagMainContent");
    }

    public void addToWishlist(final WishCartItem wishCartItem, String str) {
        this.mAddToWishListService.requestService(wishCartItem.getProductId(), str, (AddToWishlistService.SuccessCallback) new AddToWishlistService.SuccessCallback() {
            public void onSuccess(final WishWishlist wishWishlist) {
                CartServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CartFragment>() {
                    public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SAVE_FOR_LATER_ADD_TO_WISHLIST, wishCartItem.getProductId());
                        CartServiceFragment.this.handleWishlistActionSuccess(wishCartItem, wishWishlist);
                    }
                }, "FragmentTagMainContent");
            }
        }, (DefaultFailureCallback) new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.handleWishlistActionFailure(str);
            }
        });
    }

    public void handleWishlistActionFailure(final String str) {
        withUiFragment(new UiTask<BaseActivity, CartFragment>() {
            public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
            }
        }, "FragmentTagMainContent");
    }

    public void loadDialogWishlists(int i) {
        this.mGetUserWishlistsService.requestService(ProfileDataCenter.getInstance().getUserId(), i, 30, 1, true, new GetUserWishlistsService.SuccessCallback() {
            public void onSuccess(final ArrayList<WishWishlist> arrayList, final int i, final boolean z) {
                CartServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, BaseDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseDialogFragment baseDialogFragment) {
                        if (baseDialogFragment instanceof SelectWishlistDialogFragment) {
                            ((SelectWishlistDialogFragment) baseDialogFragment).handleLoadingSuccess(arrayList, i, z);
                        }
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                CartServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, BaseDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseDialogFragment baseDialogFragment) {
                        CartServiceFragment.this.handleWishlistActionFailure(str);
                        baseDialogFragment.cancel();
                    }
                });
            }
        });
    }

    public void getWishlistNameSuggestion(String str) {
        this.mGetWishlistNameSuggestionService.requestService(str, new GetWishlistNameSuggestionService.SuccessCallback() {
            public void onSuccess(final ArrayList<String> arrayList) {
                CartServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, BaseDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseDialogFragment baseDialogFragment) {
                        if (baseDialogFragment instanceof CreateWishlistDialogFragment) {
                            ((CreateWishlistDialogFragment) baseDialogFragment).updateSuggestions(arrayList);
                        }
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
            }
        });
    }

    public void handleInvalidCommerceLoan() {
        withUiFragment(new UiTask<BaseActivity, CartFragment>() {
            public void performTask(BaseActivity baseActivity, CartFragment cartFragment) {
                MultiButtonDialogChoice multiButtonDialogChoice;
                BaseActivity baseActivity2 = baseActivity;
                final CartFragment cartFragment2 = cartFragment;
                if (!ExperimentDataCenter.getInstance().canCheckoutWithCommerceLoan(CartServiceFragment.this.mCartContext)) {
                    ArrayList arrayList = new ArrayList();
                    if (CartServiceFragment.this.mCartContext.hasCreditCardBillingInfo()) {
                        WishCreditCardInfo defaultCreditCardInfo = CartServiceFragment.this.mCartContext.getUserBillingInfo().getDefaultCreditCardInfo(CartServiceFragment.this.mCartContext.getPaymentProcessor());
                        String creditCardTypeDisplayString = CreditCardUtil.getCreditCardTypeDisplayString(defaultCreditCardInfo.getCardType());
                        String string = CartServiceFragment.this.getString(R.string.pay_with);
                        StringBuilder sb = new StringBuilder();
                        sb.append(creditCardTypeDisplayString);
                        sb.append(" - ");
                        sb.append(defaultCreditCardInfo.getLastFourDigits());
                        MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(1, String.format(string, new Object[]{sb.toString()}), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                        MultiButtonDialogChoice multiButtonDialogChoice3 = new MultiButtonDialogChoice(2, CartServiceFragment.this.getString(R.string.choose_payment_method), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                        arrayList.add(multiButtonDialogChoice2);
                        multiButtonDialogChoice = multiButtonDialogChoice3;
                    } else {
                        multiButtonDialogChoice = new MultiButtonDialogChoice(2, CartServiceFragment.this.getString(R.string.choose_payment_method), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                    }
                    arrayList.add(multiButtonDialogChoice);
                    MultiButtonDialogFragmentBuilder buttons = new MultiButtonDialogFragmentBuilder().hideXButton().setCancelable(true).setButtons(arrayList);
                    if (CartServiceFragment.this.mCartContext.getCommerceLoanTabSpec() == null || CartServiceFragment.this.mCartContext.getCommerceLoanTabSpec().canPayLater()) {
                        buttons.setTitle(WishApplication.getInstance().getResources().getString(R.string.general_payment_error));
                    } else {
                        buttons.setTitle(CartServiceFragment.this.mCartContext.getCommerceLoanTabSpec().getInvalidPopupTitle());
                        buttons.setSubTitle(CartServiceFragment.this.mCartContext.getCommerceLoanTabSpec().getInvalidReason());
                    }
                    MultiButtonDialogFragment build = buttons.build();
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_COMMERCE_LOAN_INVALID_DIALOG);
                    baseActivity2.startDialog(build, new BaseDialogCallback() {
                        public void onCancel(BaseDialogFragment baseDialogFragment) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_COMMERCE_LOAN_INVALID_DIALOG_CANCEL);
                        }

                        public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                            switch (i) {
                                case 1:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_COMMERCE_LOAN_INVALID_DIALOG_CREDIT_CARD);
                                    CartServiceFragment.this.getCartContext().updatePreferredPaymentMode("PaymentModeCC");
                                    cartFragment2.refreshUi();
                                    return;
                                case 2:
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_COMMERCE_LOAN_INVALID_DIALOG_OTHER_METHOD);
                                    CartServiceFragment.this.showBillingView(false);
                                    return;
                                default:
                                    return;
                            }
                        }
                    });
                } else if (!CartServiceFragment.this.mCartContext.hasCommerceLoanBillingInfo(true)) {
                    CartServiceFragment.this.showBillingView(false, CartBillingSection.COMMERCE_LOAN);
                } else {
                    new CommerceLoanDatePickerDialog(baseActivity2, CartServiceFragment.this.mCartContext.getCommerceLoanTabSpec().getMaxPaymentDays(), Calendar.getInstance(), new OnDateSetListener() {
                        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                            Calendar instance = Calendar.getInstance();
                            instance.set(i, i2, i3);
                            CartServiceFragment.this.updateCommerceLoanPreferredDueDate(instance.getTime());
                            cartFragment2.refreshUi();
                        }
                    }).show();
                }
            }
        }, "FragmentTagMainContent");
    }

    public void updateCommerceLoanPreferredDueDate(Date date) {
        Bundle bundle = new Bundle();
        bundle.putString("paramDueDate", DateUtil.isoDateFromDate(date));
        WishCommerceLoanInfo commerceLoanInfo = this.mCartContext.getUserBillingInfo().getCommerceLoanInfo();
        PaymentProcessor paymentProcessor = commerceLoanInfo != null ? commerceLoanInfo.getCreditCardInfo().getPaymentProcessor() : this.mCartContext.getPayHalfLaterFlag() ? this.mCartContext.getPaymentProcessor() : null;
        bundle.putInt("paramPaymentProcessor", paymentProcessor.getValue());
        saveBillingInformation(CartBillingSection.COMMERCE_LOAN, bundle, null, false);
    }

    public void showCommerceLoanDatePicker(final int i, final Calendar calendar, final OnDateSetListener onDateSetListener) {
        withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                new CommerceLoanDatePickerDialog(cartActivity, i, calendar, onDateSetListener).show();
            }
        });
    }

    public void showSaveForLaterPrompt(final WishCartItem wishCartItem) {
        withUiFragment(new UiTask<CartActivity, CartFragment>() {
            public void performTask(CartActivity cartActivity, CartFragment cartFragment) {
                CartFragment cartFragment2 = cartFragment;
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, cartFragment2.getString(R.string.save_for_later), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(1, cartFragment2.getString(R.string.remove_item), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                ArrayList arrayList = new ArrayList();
                arrayList.add(multiButtonDialogChoice);
                arrayList.add(multiButtonDialogChoice2);
                MultiButtonDialogFragment build = new MultiButtonDialogFragmentBuilder().setTitle(cartFragment2.getString(R.string.save_this_item_for_later)).setImageSize(ImageSize.SMALL).setButtons(arrayList).setWishImage(wishCartItem.getImage()).build();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_SAVE_FOR_LATER_PROMPT);
                cartActivity.startDialog(build, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        switch (i) {
                            case 0:
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SAVE_FOR_LATER, wishCartItem.getProductId());
                                CartServiceFragment.this.addFromCartToSavedForLater(wishCartItem);
                                return;
                            case 1:
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SAVE_FOR_LATER_REMOVE_FROM_CART);
                                CartServiceFragment.this.updateCart(wishCartItem.getProductId(), wishCartItem.getVariationId(), null, 0);
                                return;
                            default:
                                return;
                        }
                    }
                });
            }
        }, "FragmentTagMainContent");
    }

    public void showSaveToWishListPrompt(final WishSavedForLaterProduct wishSavedForLaterProduct) {
        withUiFragment(new UiTask<BaseActivity, UiFragment>() {
            public void performTask(BaseActivity baseActivity, UiFragment uiFragment) {
                UiFragment uiFragment2 = uiFragment;
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, uiFragment2.getString(R.string.save_to_wishlist), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(1, uiFragment2.getString(R.string.remove_item), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                ArrayList arrayList = new ArrayList();
                arrayList.add(multiButtonDialogChoice);
                arrayList.add(multiButtonDialogChoice2);
                MultiButtonDialogFragment build = new MultiButtonDialogFragmentBuilder().setTitle(uiFragment2.getString(R.string.save_to_wishlist_instead)).setImageSize(ImageSize.SMALL).setButtons(arrayList).setWishImage(wishSavedForLaterProduct.getImage()).build();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_SAVE_FOR_LATER_REMOVE_PROMPT, wishSavedForLaterProduct.getProductId());
                baseActivity.startDialog(build, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        switch (i) {
                            case 0:
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SAVE_FOR_LATER_SAVE_TO_WISHLIST, wishSavedForLaterProduct.getProductId());
                                CartServiceFragment.this.addToWishlistAndRemoveFromSaveForLater(wishSavedForLaterProduct);
                                return;
                            case 1:
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SAVE_FOR_LATER_AND_REMOVE, wishSavedForLaterProduct.getProductId());
                                CartServiceFragment.this.removeFromSaveForLater(wishSavedForLaterProduct, true);
                                return;
                            default:
                                return;
                        }
                    }
                });
            }
        }, "FragmentTagMainContent");
    }

    public void removeFromSaveForLaterAfterAddingToWishlist(WishSavedForLaterProduct wishSavedForLaterProduct, final boolean z) {
        showLoadingSpinner();
        this.mRemoveFromSavedForLaterService.requestService(wishSavedForLaterProduct, new RemoveFromSavedForLaterService.SuccessCallback() {
            public void onSuccess(ArrayList<WishSavedForLaterProduct> arrayList) {
                CartServiceFragment.this.mCartContext.updateSavedForLater(arrayList, z);
                CartServiceFragment.this.hideLoadingSpinner();
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.hideLoadingSpinner();
                CartServiceFragment.this.showErrorMessage(str);
            }
        });
    }

    public void addToWishlistAndRemoveFromSaveForLater(final WishSavedForLaterProduct wishSavedForLaterProduct) {
        final SelectWishlistDialogFragment selectWishlistDialogFragment = new SelectWishlistDialogFragment();
        withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                cartActivity.startDialog(selectWishlistDialogFragment, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 2000) {
                            CartServiceFragment.this.createAndAddToWishlist(wishSavedForLaterProduct);
                        } else if (bundle != null) {
                            WishWishlist wishWishlist = (WishWishlist) bundle.getParcelable("ResultWishlist");
                            if (wishWishlist != null) {
                                CartServiceFragment.this.addToWishlist(wishSavedForLaterProduct, wishWishlist.getWishlistId());
                            }
                        }
                    }
                });
            }
        });
    }

    public void addToWishlist(final WishSavedForLaterProduct wishSavedForLaterProduct, String str) {
        this.mAddToWishListService.requestService(wishSavedForLaterProduct.getProductId(), str, (AddToWishlistService.SuccessCallback) new AddToWishlistService.SuccessCallback() {
            public void onSuccess(final WishWishlist wishWishlist) {
                CartServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UiFragment>() {
                    public void performTask(BaseActivity baseActivity, UiFragment uiFragment) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SAVE_FOR_LATER_ADD_TO_WISHLIST, wishSavedForLaterProduct.getProductId());
                        CartServiceFragment.this.handleWishlistActionSuccess(wishSavedForLaterProduct, wishWishlist);
                    }
                }, "FragmentTagMainContent");
            }
        }, (DefaultFailureCallback) new DefaultFailureCallback() {
            public void onFailure(String str) {
                CartServiceFragment.this.handleWishlistActionFailure(str);
            }
        });
    }

    public void createAndAddToWishlist(final WishSavedForLaterProduct wishSavedForLaterProduct) {
        final CreateWishlistDialogFragment createWishlistDialogFragment = new CreateWishlistDialogFragment();
        withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                cartActivity.startDialog(createWishlistDialogFragment, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SAVE_FOR_LATER_CREATE_WISHLIST, wishSavedForLaterProduct.getProductId());
                        CartServiceFragment.this.mAddToWishListService.requestService(wishSavedForLaterProduct.getProductId(), (String) null, false, bundle.getString("ResultName"), (AddToWishlistService.SuccessCallback) new AddToWishlistService.SuccessCallback() {
                            public void onSuccess(final WishWishlist wishWishlist) {
                                CartServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UiFragment>() {
                                    public void performTask(BaseActivity baseActivity, UiFragment uiFragment) {
                                        CartServiceFragment.this.handleWishlistActionSuccess(wishSavedForLaterProduct, wishWishlist);
                                    }
                                }, "FragmentTagMainContent");
                            }
                        }, (DefaultFailureCallback) new DefaultFailureCallback() {
                            public void onFailure(String str) {
                                CartServiceFragment.this.handleWishlistActionFailure(str);
                            }
                        });
                    }
                });
            }
        });
        createWishlistDialogFragment.loadNameSuggestions(wishSavedForLaterProduct.getProductId());
    }

    /* access modifiers changed from: private */
    public void handleWishlistActionSuccess(final WishSavedForLaterProduct wishSavedForLaterProduct, final WishWishlist wishWishlist) {
        withUiFragment(new UiTask<CartActivity, CartFragment>() {
            public void performTask(CartActivity cartActivity, CartFragment cartFragment) {
                final CartActivity cartActivity2 = cartActivity;
                CartFragment cartFragment2 = cartFragment;
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(WishlistDialogAction.CONTINUE.ordinal(), cartFragment2.getString(R.string.continue_shopping), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(WishlistDialogAction.VIEW_WISHLIST.ordinal(), cartFragment2.getString(R.string.view_wishlist), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                ArrayList arrayList = new ArrayList();
                arrayList.add(multiButtonDialogChoice);
                arrayList.add(multiButtonDialogChoice2);
                cartActivity2.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(cartFragment2.getString(R.string.item_has_been_moved_to_wishlist, wishWishlist.getName())).setImageSize(ImageSize.SMALL).setButtons(arrayList).setWishImage(wishSavedForLaterProduct.getImage()).build(), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                        CartServiceFragment.this.removeFromSaveForLaterAfterAddingToWishlist(wishSavedForLaterProduct, true);
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        CartServiceFragment.this.removeFromSaveForLaterAfterAddingToWishlist(wishSavedForLaterProduct, true);
                        if (i == WishlistDialogAction.VIEW_WISHLIST.ordinal()) {
                            Intent intent = new Intent();
                            intent.setClass(cartActivity2, WishlistActivity.class);
                            IntentUtil.putParcelableExtra(intent, WishlistActivity.EXTRA_WISHLIST, wishWishlist);
                            intent.putExtra(WishlistActivity.EXTRA_CAN_EDIT_WISHLIST, true);
                            cartActivity2.startActivity(intent);
                        }
                    }
                });
            }
        }, "FragmentTagMainContent");
    }
}
