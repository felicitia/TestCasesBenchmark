package com.contextlogic.wish.activity.cart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.activity.cart.billing.CartBillingView;
import com.contextlogic.wish.activity.cart.billing.commerceloanform.CartCommerceLoanCreditCardBillingView;
import com.contextlogic.wish.activity.cart.items.CartItemsFooterView;
import com.contextlogic.wish.activity.cart.items.CartItemsView;
import com.contextlogic.wish.activity.cart.shipping.AddressBookView;
import com.contextlogic.wish.activity.cart.shipping.CartShippingView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishFreeGiftTabInfo;
import com.contextlogic.wish.api.model.WishLoanRepaymentBannerSpec;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog;
import com.contextlogic.wish.dialog.cartabandon.CartAbandonOfferDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.ImageSize;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.KeyboardUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CartFragment extends UiFragment<CartActivity> implements LoadingPageManager {
    /* access modifiers changed from: private */
    public CartContext mCartContext;
    /* access modifiers changed from: private */
    public CartItemsView mCartItemsView;
    private FrameLayout mContentContainer;
    protected CartUiView mCurrentUiView;
    private UiViewType mCurrentUiViewType;
    /* access modifiers changed from: private */
    public boolean mExpressCheckoutProcessed;
    protected LoadingPageView mLoadingView;

    protected enum UiViewType {
        ITEMS,
        SHIPPING,
        ADDRESS_BOOK,
        BILLING
    }

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLayoutResourceId() {
        return R.layout.cart_fragment;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.cart_fragment_content_container;
    }

    public void showItemsView(boolean z) {
        if (this.mCurrentUiView == null || !(this.mCurrentUiView instanceof CartItemsView) || z) {
            withActivity(new ActivityTask<CartActivity>() {
                public void performTask(CartActivity cartActivity) {
                    Bundle savedInstanceState = CartFragment.this.mCurrentUiView == null ? CartFragment.this.getSavedInstanceState() : null;
                    CartFragment.this.mCartItemsView = new CartItemsView(CartFragment.this, cartActivity, savedInstanceState);
                    CartFragment.this.updateUiView(CartFragment.this.mCartItemsView, UiViewType.ITEMS, savedInstanceState);
                    CartFragment.this.mCurrentUiView.updateActionBar();
                }
            });
        } else {
            this.mCurrentUiView.updateActionBar();
        }
    }

    public void initialize() {
        this.mLoadingView = (LoadingPageView) findViewById(R.id.cart_fragment_loading_view);
        this.mLoadingView.setLoadingPageManager(this);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.cart_fragment_max_cart_width);
        if (DisplayUtil.getDisplayWidth() > dimensionPixelSize) {
            LayoutParams layoutParams = this.mLoadingView.getLayoutParams();
            layoutParams.width = dimensionPixelSize;
            this.mLoadingView.setLayoutParams(layoutParams);
        }
        if (((CartActivity) getBaseActivity()).shouldStartInAddressBook()) {
            this.mCurrentUiViewType = UiViewType.ADDRESS_BOOK;
        }
        initializeValues();
        withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                if (cartActivity.showCartError()) {
                    cartActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(cartActivity.getString(R.string.general_payment_error)));
                }
            }
        });
    }

    private void initializeValues() {
        if (getSavedInstanceState() != null) {
            this.mExpressCheckoutProcessed = getSavedInstanceState().getBoolean("SavedStateExpressCheckoutProcessed");
            this.mCurrentUiViewType = (UiViewType) getSavedInstanceState().getSerializable("SavedStateCurrentUiViewType");
            final WishCart wishCart = (WishCart) StateStoreCache.getInstance().getParcelable(getSavedInstanceState(), "SavedStateCart", WishCart.class);
            final WishShippingInfo wishShippingInfo = (WishShippingInfo) StateStoreCache.getInstance().getParcelable(getSavedInstanceState(), "SavedStateShippingInfo", WishShippingInfo.class);
            final WishUserBillingInfo wishUserBillingInfo = (WishUserBillingInfo) StateStoreCache.getInstance().getParcelable(getSavedInstanceState(), "SavedStateUserBillingInfo", WishUserBillingInfo.class);
            final WishLoanRepaymentBannerSpec wishLoanRepaymentBannerSpec = (WishLoanRepaymentBannerSpec) StateStoreCache.getInstance().getParcelable(getSavedInstanceState(), "SavedStateLoanRepaymentSpec", WishLoanRepaymentBannerSpec.class);
            if (wishCart != null) {
                AnonymousClass4 r1 = new ServiceTask<CartActivity, CartServiceFragment>() {
                    public void performTask(CartActivity cartActivity, CartServiceFragment cartServiceFragment) {
                        cartServiceFragment.loadSavedForLater();
                        cartServiceFragment.reInitializeCartContext(wishCart, wishShippingInfo, wishUserBillingInfo, wishLoanRepaymentBannerSpec);
                    }
                };
                withServiceFragment(r1);
            }
        }
    }

    public void handleResume() {
        super.handleResume();
        if (!this.mLoadingView.isLoadingComplete()) {
            handleReload();
        }
    }

    public void restoreImages() {
        if (this.mCurrentUiView != null) {
            this.mCurrentUiView.restoreImages();
        }
    }

    public void releaseImages() {
        if (this.mCurrentUiView != null) {
            this.mCurrentUiView.releaseImages();
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        if (this.mLoadingView != null && this.mLoadingView.isLoadingComplete()) {
            bundle.putBoolean("SavedStateExpressCheckoutProcessed", this.mExpressCheckoutProcessed);
            bundle.putString("SavedStateCart", StateStoreCache.getInstance().storeParcelable(this.mCartContext.getCart()));
            bundle.putString("SavedStateShippingInfo", StateStoreCache.getInstance().storeParcelable(this.mCartContext.getShippingInfo()));
            bundle.putString("SavedStateUserBillingInfo", StateStoreCache.getInstance().storeParcelable(this.mCartContext.getUserBillingInfo()));
            bundle.putString("SavedStateLoanRepaymentSpec", StateStoreCache.getInstance().storeParcelable(this.mCartContext.getLoanRepaymentBannerSpec()));
            bundle.putSerializable("SavedStateCurrentUiViewType", this.mCurrentUiViewType);
            if (this.mCurrentUiView != null) {
                this.mCurrentUiView.handleSaveInstanceState(bundle);
            }
        }
    }

    public void handleCartLoadError() {
        this.mLoadingView.markLoadingErrored();
    }

    public void handleCartLoadSuccess(CartContext cartContext) {
        this.mCartContext = cartContext;
        if (this.mCurrentUiViewType == UiViewType.ITEMS) {
            showItemsView(true);
        } else if (this.mCurrentUiViewType == UiViewType.SHIPPING) {
            showShippingView(false, false, getCartContext().getShippingInfo());
        } else if (this.mCurrentUiViewType == UiViewType.ADDRESS_BOOK) {
            showAddressBook(null);
        } else if (this.mCurrentUiViewType == UiViewType.BILLING) {
            showBillingView(false);
        } else {
            showItemsView(true);
        }
        handlePayPalChosenFromKlarnaIfNecessary();
        this.mLoadingView.markLoadingComplete();
        this.mExpressCheckoutProcessed = true;
        if (this.mCartContext.hasFreeGift()) {
            WishFreeGiftTabInfo.logFreeGiftTabEvent(WishAnalyticsEvent.IMPRESSION_FREE_GIFT_TAB_CART);
        }
    }

    public void refreshUi() {
        ((CartActivity) getBaseActivity()).hideLoadingDialog();
        this.mLoadingView.refreshViewState();
        if (this.mCurrentUiView != null) {
            this.mCurrentUiView.refreshUi();
        }
    }

    public void onStop() {
        super.onStop();
        if (this.mCurrentUiView != null && (this.mCurrentUiView instanceof CartItemsView)) {
            ((CartItemsView) this.mCurrentUiView).onStop();
        }
    }

    public void handleReload() {
        withServiceFragment(new ServiceTask<CartActivity, CartServiceFragment>() {
            public void performTask(CartActivity cartActivity, CartServiceFragment cartServiceFragment) {
                if (((CartActivity) CartFragment.this.getBaseActivity()).shouldStartInAddressBook()) {
                    CartFragment.this.showAddressBook(null);
                }
                cartServiceFragment.loadSavedForLater();
                cartServiceFragment.loadCart(cartActivity.getAddToCartProductId(), cartActivity.getAddToCartVariationId(), cartActivity.getAddToCartShippingOptionId(), cartActivity.getAddToCartQuantity(), cartActivity.getAddToCartOfferId(), cartActivity.isExpressCheckout() && !CartFragment.this.mExpressCheckoutProcessed);
            }
        });
    }

    public void initializeLoadingContentView(View view) {
        this.mContentContainer = (FrameLayout) view.findViewById(R.id.cart_fragment_content_container);
    }

    public boolean hasItems() {
        return this.mCartContext != null;
    }

    public void showAddressBook(final String str) {
        withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                Bundle savedInstanceState = CartFragment.this.mCurrentUiView == null ? CartFragment.this.getSavedInstanceState() : null;
                CartFragment.this.updateUiView(new AddressBookView(CartFragment.this, cartActivity, savedInstanceState), UiViewType.ADDRESS_BOOK, savedInstanceState);
                CartFragment.this.mCurrentUiView.updateActionBar();
                if (str != null) {
                    SuccessBottomSheetDialog.create(cartActivity).setTitle(str).autoDismiss().show();
                }
            }
        });
    }

    public void showShippingView(final boolean z, final boolean z2, final WishShippingInfo wishShippingInfo) {
        if (this.mCurrentUiView == null || !(this.mCurrentUiView instanceof CartShippingView)) {
            if (this.mCartContext.hasFreeGift()) {
                WishFreeGiftTabInfo.logFreeGiftTabEvent(WishAnalyticsEvent.IMPRESSION_FREE_GIFT_TAB_SHIPPING);
            }
            withActivity(new ActivityTask<CartActivity>() {
                public void performTask(CartActivity cartActivity) {
                    Bundle savedInstanceState = CartFragment.this.mCurrentUiView == null ? CartFragment.this.getSavedInstanceState() : null;
                    CartShippingView cartShippingView = new CartShippingView(CartFragment.this, cartActivity, savedInstanceState, z, z2, wishShippingInfo);
                    CartFragment.this.updateUiView(cartShippingView, UiViewType.SHIPPING, savedInstanceState);
                    CartFragment.this.mCurrentUiView.updateActionBar();
                }
            });
            return;
        }
        this.mCurrentUiView.updateActionBar();
    }

    public void showBillingView(boolean z) {
        showBillingView(z, null);
    }

    public void showBillingView(final boolean z, final CartBillingSection cartBillingSection) {
        if (this.mCurrentUiView == null || !(this.mCurrentUiView instanceof CartShippingView)) {
            if (this.mCartContext.hasFreeGift()) {
                WishFreeGiftTabInfo.logFreeGiftTabEvent(WishAnalyticsEvent.IMPRESSION_FREE_GIFT_TAB_BILLING);
            }
            withActivity(new ActivityTask<CartActivity>() {
                public void performTask(CartActivity cartActivity) {
                    Bundle savedInstanceState = CartFragment.this.mCurrentUiView == null ? CartFragment.this.getSavedInstanceState() : null;
                    CartFragment cartFragment = CartFragment.this;
                    CartBillingView cartBillingView = new CartBillingView(CartFragment.this, cartActivity, savedInstanceState, z, cartBillingSection);
                    cartFragment.updateUiView(cartBillingView, UiViewType.BILLING, savedInstanceState);
                    CartFragment.this.mCurrentUiView.updateActionBar();
                }
            });
            return;
        }
        this.mCurrentUiView.updateActionBar();
    }

    public void showCommerceLoanCCBillingView(final boolean z) {
        if (this.mCurrentUiView == null || !(this.mCurrentUiView instanceof CartCommerceLoanCreditCardBillingView)) {
            withActivity(new ActivityTask<CartActivity>() {
                public void performTask(CartActivity cartActivity) {
                    Bundle savedInstanceState = CartFragment.this.mCurrentUiView == null ? CartFragment.this.getSavedInstanceState() : null;
                    CartFragment.this.updateUiView(new CartCommerceLoanCreditCardBillingView(CartFragment.this, cartActivity, savedInstanceState, z), UiViewType.BILLING, savedInstanceState);
                    CartFragment.this.mCurrentUiView.updateActionBar();
                }
            });
        } else {
            this.mCurrentUiView.updateActionBar();
        }
    }

    public void updateUiView(CartUiView cartUiView, UiViewType uiViewType, Bundle bundle) {
        if (this.mCurrentUiView != null) {
            this.mCurrentUiView.recycle();
            this.mCurrentUiView = null;
            this.mContentContainer.removeAllViews();
        }
        KeyboardUtil.hideKeyboard((Fragment) this);
        this.mCurrentUiViewType = uiViewType;
        this.mCurrentUiView = cartUiView;
        cartUiView.initializeUi(bundle);
        this.mContentContainer.addView(this.mCurrentUiView, new FrameLayout.LayoutParams(-1, -1));
        if (this.mCurrentUiView.getWishAnalyticImpressionEvents() != null) {
            HashMap hashMap = new HashMap();
            if (this.mCartContext != null) {
                hashMap.put("cart_type", this.mCartContext.getCartType().toString());
            }
            List<WishAnalyticsEvent> wishAnalyticImpressionEvents = this.mCurrentUiView.getWishAnalyticImpressionEvents();
            if (wishAnalyticImpressionEvents != null) {
                for (WishAnalyticsEvent trackEvent : wishAnalyticImpressionEvents) {
                    WishAnalyticsLogger.trackEvent(trackEvent, hashMap);
                }
            }
        }
    }

    private boolean handleCartAbandonOffer() {
        if (this.mCartContext == null || this.mCartContext.getCart() == null || this.mCartContext.getCart().getCartAbandonOffer() == null || this.mCartContext.getCart().getCartAbandonOffer().isExpired()) {
            return false;
        }
        final CartAbandonOfferDialogFragment createAbandonOfferDialog = CartAbandonOfferDialogFragment.createAbandonOfferDialog(this.mCartContext);
        if (createAbandonOfferDialog != null) {
            KeyboardUtil.hideKeyboard((Fragment) this);
            withActivity(new ActivityTask<CartActivity>() {
                public void performTask(CartActivity cartActivity) {
                    cartActivity.startDialog(createAbandonOfferDialog, new BaseDialogCallback() {
                        public void onCancel(BaseDialogFragment baseDialogFragment) {
                        }

                        public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                            if (bundle != null) {
                                CartFragment.this.mCartContext.updateData((WishCart) bundle.getParcelable("ResultCart"), CartFragment.this.mCartContext.getShippingInfo(), CartFragment.this.mCartContext.getUserBillingInfo());
                                CartFragment.this.checkout(false);
                            }
                        }
                    });
                }
            });
        }
        return true;
    }

    private boolean handleFreeGiftsAbandon() {
        if (!this.mCartContext.hasFreeGift()) {
            return false;
        }
        WishFreeGiftTabInfo.logFreeGiftTabEvent(WishAnalyticsEvent.IMPRESSION_FREE_GIFT_TAB_ABANDON);
        withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                final CartActivity cartActivity2 = cartActivity;
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(200, cartActivity2.getString(R.string.claim_gift), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(201, cartActivity2.getString(R.string.continue_shopping), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                ArrayList arrayList = new ArrayList();
                arrayList.add(multiButtonDialogChoice);
                arrayList.add(multiButtonDialogChoice2);
                cartActivity2.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(cartActivity2.getString(R.string.time_is_running_out)).setSubTitle(cartActivity2.getString(R.string.free_gifts_abandon)).setImageSize(ImageSize.SMALL).setWishImage(CartFragment.this.mCartContext.getFreeGift().getImage()).setButtons(arrayList).build(), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i != 200) {
                            WishFreeGiftTabInfo.logFreeGiftTabEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_TAB_ABANDON_CONTINUE_SHOPPING);
                            cartActivity2.finishActivity();
                            return;
                        }
                        WishFreeGiftTabInfo.logFreeGiftTabEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_TAB_ABANDON_CHECKOUT);
                        CartFragment.this.checkout(false);
                    }
                });
            }
        });
        return true;
    }

    public void handleInvalidCommerceLoan() {
        withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.handleInvalidCommerceLoan();
            }
        });
    }

    public void updateCommerceLoanPreferredDueDate(final Date date) {
        withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.updateCommerceLoanPreferredDueDate(date);
                CartFragment.this.refreshUi();
            }
        });
    }

    public boolean onBackPressed() {
        if ((this.mCurrentUiView instanceof AddressBookView) && ((CartActivity) getBaseActivity()).shouldStartInAddressBook()) {
            ((CartActivity) getBaseActivity()).finishActivity();
        }
        if (this.mCartContext != null) {
            final boolean[] zArr = new boolean[1];
            withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
                public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                    zArr[0] = cartServiceFragment.isApplyingCouponCode();
                }
            });
            if (zArr[0]) {
                return true;
            }
            if (this.mCurrentUiView != null && this.mCurrentUiView.onBackPressed()) {
                return true;
            }
            if (!(this.mCurrentUiView instanceof CartItemsView)) {
                if (((CartActivity) getBaseActivity()).shouldStartInAddressBook()) {
                    showAddressBook(null);
                    return true;
                }
                showItemsView(false);
                return true;
            } else if (handleCartAbandonOffer() || handleFreeGiftsAbandon()) {
                return true;
            }
        }
        return false;
    }

    public CartContext getCartContext() {
        return this.mCartContext;
    }

    public void checkout(final boolean z) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_CHECKOUT);
        withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                CartFragment.this.mCartContext.getCheckoutActionManager().checkout(cartServiceFragment, z);
            }
        });
    }

    public void onKeyboardVisiblityChanged(boolean z) {
        super.onKeyboardVisiblityChanged(z);
        if (this.mCurrentUiView != null) {
            this.mCurrentUiView.onKeyboardVisiblityChanged(z);
        }
    }

    public void completeBillingSectionSelected(CartBillingSection cartBillingSection) {
        if (this.mCurrentUiView != null && (this.mCurrentUiView instanceof CartBillingView)) {
            ((CartBillingView) this.mCurrentUiView).completeBillingSectionSelected(cartBillingSection);
        }
    }

    private void handlePayPalChosenFromKlarnaIfNecessary() {
        withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                if (cartActivity.chosePayPalFromKlarna() && CartFragment.this.getCartContext() != null) {
                    CartFragment.this.getCartContext().updatePreferredPaymentMode("PaymentModePayPal");
                    CartFragment.this.checkout(false);
                }
            }
        });
    }

    public void scrollToSaveForLater() {
        if (this.mCartItemsView != null) {
            this.mCartItemsView.scrollToSaveForLater();
        }
    }

    public void editAddressFromAddressBook(WishShippingInfo wishShippingInfo) {
        showShippingView(false, false, wishShippingInfo);
    }

    public void addAddressToAddressBook() {
        showShippingView(false, true, null);
    }

    public CartItemsFooterView getCartItemsFooter() {
        return this.mCartItemsView.getCartItemsFooter();
    }
}
