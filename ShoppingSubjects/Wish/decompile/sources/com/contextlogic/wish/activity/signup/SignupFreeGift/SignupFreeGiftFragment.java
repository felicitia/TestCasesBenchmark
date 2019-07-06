package com.contextlogic.wish.activity.signup.SignupFreeGift;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.activity.signup.SignupFreeGift.tabbed.TabbedSignupFreeGiftView;
import com.contextlogic.wish.activity.signup.SignupProfileUpdateActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishSignupFreeGiftCart;
import com.contextlogic.wish.api.model.WishSignupFreeGifts;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.ui.loading.LoadingPageView;
import com.contextlogic.wish.ui.loading.LoadingPageView.LoadingPageManager;
import com.contextlogic.wish.ui.viewpager.BaseTabStripInterface;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.KeyboardUtil;

public class SignupFreeGiftFragment extends UiFragment<SignupFreeGiftActivity> implements LoadingPageManager, BaseTabStripInterface {
    /* access modifiers changed from: private */
    public CartContext mCartContext;
    private FrameLayout mContentContainer;
    /* access modifiers changed from: private */
    public SignupFreeGiftUiView mCurrentUiView;
    private UiViewType mCurrentUiViewType;
    /* access modifiers changed from: private */
    public WishSignupFreeGifts mFreeGifts;
    /* access modifiers changed from: private */
    public LoadingPageView mLoadingView;
    /* access modifiers changed from: private */
    public boolean mStartedFromNotification;
    private WishSignupFreeGiftCart mWishSignupFreeGiftCart;

    private enum UiViewType {
        GIFTS,
        SHIPPING,
        BILLING
    }

    public boolean canPullToRefresh() {
        return false;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.signup_free_gift_fragment;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.signup_free_gift_fragment_content_container;
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.IMPRESSION_FIRST_FREE_GIFT);
        this.mLoadingView = (LoadingPageView) findViewById(R.id.signup_free_gift_fragment_loading_view);
        this.mLoadingView.setLoadingPageManager(this);
        initializeValues();
    }

    private void initializeValues() {
        if (getSavedInstanceState() != null) {
            this.mCurrentUiViewType = (UiViewType) getSavedInstanceState().getSerializable("SavedStateCurrentUiViewType");
            this.mWishSignupFreeGiftCart = (WishSignupFreeGiftCart) StateStoreCache.getInstance().getParcelable(getSavedInstanceState(), "SavedStateSignupFreeGiftCart", WishSignupFreeGiftCart.class);
            final WishCart wishCart = (WishCart) StateStoreCache.getInstance().getParcelable(getSavedInstanceState(), "SavedStateCart", WishCart.class);
            final WishShippingInfo wishShippingInfo = (WishShippingInfo) StateStoreCache.getInstance().getParcelable(getSavedInstanceState(), "SavedStateShippingInfo", WishShippingInfo.class);
            final WishUserBillingInfo wishUserBillingInfo = (WishUserBillingInfo) StateStoreCache.getInstance().getParcelable(getSavedInstanceState(), "SavedStateUserBillingInfo", WishUserBillingInfo.class);
            withServiceFragment(new ServiceTask<SignupFreeGiftActivity, SignupFreeGiftServiceFragment>() {
                public void performTask(SignupFreeGiftActivity signupFreeGiftActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                    signupFreeGiftServiceFragment.reInitializeCartContext(wishCart, wishShippingInfo, wishUserBillingInfo);
                }
            });
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        if (!(this.mLoadingView == null || !this.mLoadingView.isLoadingComplete() || this.mCartContext == null)) {
            bundle.putString("SavedStateCart", StateStoreCache.getInstance().storeParcelable(this.mCartContext.getCart()));
            bundle.putString("SavedStateShippingInfo", StateStoreCache.getInstance().storeParcelable(this.mCartContext.getShippingInfo()));
            bundle.putString("SavedStateUserBillingInfo", StateStoreCache.getInstance().storeParcelable(this.mCartContext.getUserBillingInfo()));
            bundle.putSerializable("SavedStateCurrentUiViewType", this.mCurrentUiViewType);
            bundle.putString("SavedStateSignupFreeGiftCart", StateStoreCache.getInstance().storeParcelable(this.mWishSignupFreeGiftCart));
        }
        if (this.mCurrentUiView != null) {
            this.mCurrentUiView.handleSaveInstanceState(bundle);
        }
    }

    public void handleResume() {
        super.handleResume();
        handleReload();
    }

    public boolean onBackPressed() {
        if (this.mCurrentUiView != null && this.mCurrentUiView.onBackPressed()) {
            return true;
        }
        if (this.mCurrentUiView instanceof SignupFreeGiftBillingView) {
            showShippingView();
            return true;
        } else if (!(this.mCurrentUiView instanceof SignupFreeGiftShippingView)) {
            return false;
        } else {
            showFreeGiftView();
            return true;
        }
    }

    public void handleReload() {
        withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                if (!SignupFreeGiftFragment.this.mLoadingView.isLoadingComplete()) {
                    SignupFreeGiftFragment.this.setCurrentView();
                }
                SignupFreeGiftFragment.this.mLoadingView.refreshViewState();
            }
        });
    }

    public void initializeLoadingContentView(View view) {
        this.mContentContainer = (FrameLayout) view.findViewById(R.id.signup_free_gift_fragment_content_container);
        withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                SignupFreeGiftFragment.this.mStartedFromNotification = signupFreeGiftActivity.startedFromNotification();
            }
        });
    }

    public boolean hasItems() {
        return this.mCurrentUiView != null;
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

    public void showFreeGiftView() {
        if (this.mCurrentUiView == null || !(this.mCurrentUiView instanceof SignupFreeGiftBaseView)) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_FREE_GIFTS_SCREEN);
            withActivity(new ActivityTask<SignupFreeGiftActivity>() {
                public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                    SignupFreeGiftUiView signupFreeGiftUiView;
                    Bundle savedInstanceState = SignupFreeGiftFragment.this.mCurrentUiView == null ? SignupFreeGiftFragment.this.getSavedInstanceState() : null;
                    if (ExperimentDataCenter.getInstance().shouldShowGenderedFreegift()) {
                        signupFreeGiftUiView = new TabbedSignupFreeGiftView(signupFreeGiftActivity, this, savedInstanceState);
                    } else {
                        signupFreeGiftUiView = new SignupFreeGiftView(signupFreeGiftActivity, this, savedInstanceState);
                    }
                    SignupFreeGiftFragment.this.updateUiView(signupFreeGiftUiView, UiViewType.GIFTS);
                    if (SignupFreeGiftFragment.this.mFreeGifts != null) {
                        signupFreeGiftUiView.refreshUi();
                        SignupFreeGiftFragment.this.mLoadingView.markLoadingComplete();
                        return;
                    }
                    SignupFreeGiftFragment.this.loadFreeGifts();
                }
            });
        }
    }

    public void loadFreeGifts() {
        withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                signupFreeGiftServiceFragment.getSignupFreeGifts();
            }
        });
    }

    public void showShippingView() {
        if (this.mCurrentUiView == null || !(this.mCurrentUiView instanceof SignupFreeGiftShippingView)) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_FREE_GIFT_SHIPPING_INFO);
            withActivity(new ActivityTask<SignupFreeGiftActivity>() {
                public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                    SignupFreeGiftFragment.this.updateUiView(new SignupFreeGiftShippingView(this, signupFreeGiftActivity, SignupFreeGiftFragment.this.mCurrentUiView == null ? SignupFreeGiftFragment.this.getSavedInstanceState() : null, false), UiViewType.SHIPPING);
                }
            });
        }
    }

    public void showBillingView() {
        showBillingView(null);
    }

    public void showBillingView(final CartBillingSection cartBillingSection) {
        if (this.mCurrentUiView == null || !(this.mCurrentUiView instanceof SignupFreeGiftBaseView)) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_FREE_GIFT_BILLING_INFO);
            withActivity(new ActivityTask<SignupFreeGiftActivity>() {
                public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                    Bundle savedInstanceState = SignupFreeGiftFragment.this.mCurrentUiView == null ? SignupFreeGiftFragment.this.getSavedInstanceState() : null;
                    SignupFreeGiftFragment.this.updateUiView(new SignupFreeGiftBillingView(this, signupFreeGiftActivity, savedInstanceState), UiViewType.GIFTS);
                    ((SignupFreeGiftBillingView) SignupFreeGiftFragment.this.mCurrentUiView).activatePaymentSections(savedInstanceState, cartBillingSection);
                }
            });
        }
    }

    public void updateUiView(SignupFreeGiftUiView signupFreeGiftUiView, UiViewType uiViewType) {
        if (this.mCurrentUiView != null) {
            this.mCurrentUiView.recycle();
            this.mCurrentUiView = null;
            this.mContentContainer.removeAllViews();
        }
        KeyboardUtil.hideKeyboard((Fragment) this);
        this.mCurrentUiViewType = uiViewType;
        this.mCurrentUiView = signupFreeGiftUiView;
        this.mContentContainer.addView(this.mCurrentUiView, new LayoutParams(-1, -1));
        if (this.mCurrentUiView.getWishAnalyticImpressionEvent() != null) {
            WishAnalyticsLogger.trackEvent(this.mCurrentUiView.getWishAnalyticImpressionEvent());
        }
    }

    public void refreshUi() {
        if (this.mCurrentUiView != null) {
            this.mCurrentUiView.refreshUi();
        }
    }

    public void setCurrentView() {
        if (this.mCurrentUiViewType == UiViewType.GIFTS) {
            showFreeGiftView();
        } else if (this.mCurrentUiViewType == UiViewType.SHIPPING) {
            showShippingView();
        } else if (this.mCurrentUiViewType == UiViewType.BILLING) {
            showBillingView();
        } else {
            showFreeGiftView();
        }
    }

    public void onKeyboardVisiblityChanged(boolean z) {
        super.onKeyboardVisiblityChanged(z);
        if (this.mCurrentUiView != null) {
            this.mCurrentUiView.onKeyboardVisiblityChanged(z);
        }
    }

    public CartContext getCartContext() {
        return this.mCartContext;
    }

    public void setCartContext(CartContext cartContext) {
        this.mCartContext = cartContext;
    }

    public WishSignupFreeGiftCart getWishSignupFreeGiftCart() {
        return this.mWishSignupFreeGiftCart;
    }

    public void completeBillingSectionSelected(CartBillingSection cartBillingSection) {
        if (this.mCurrentUiView != null && (this.mCurrentUiView instanceof SignupFreeGiftBillingView)) {
            ((SignupFreeGiftBillingView) this.mCurrentUiView).completeBillingSectionSelected(cartBillingSection);
        }
    }

    public void checkout() {
        withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                SignupFreeGiftFragment.this.mCartContext.getCheckoutActionManager().checkout(signupFreeGiftServiceFragment, false);
            }
        });
    }

    public WishSignupFreeGifts getFreeGifts() {
        return this.mFreeGifts;
    }

    public boolean startedFromNotification() {
        return this.mStartedFromNotification;
    }

    public void handleClose() {
        withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                if (signupFreeGiftActivity.startedFromNotification()) {
                    signupFreeGiftActivity.finishActivity();
                    return;
                }
                Intent intent = new Intent();
                if (!ExperimentDataCenter.getInstance().canSeeNewSingleOnboardingFlow()) {
                    IntentUtil.putParcelableExtra(intent, "ArgSignupFlowContext", signupFreeGiftActivity.getSignupFlowContext());
                    IntentUtil.putParcelableExtra(intent, "ExtraLoginMode", ((SignupFreeGiftActivity) SignupFreeGiftFragment.this.getBaseActivity()).getLoginMode());
                    intent.setClass(signupFreeGiftActivity, SignupProfileUpdateActivity.class);
                    signupFreeGiftActivity.startActivity(intent, true);
                    return;
                }
                signupFreeGiftActivity.startHomeActivity();
            }
        });
    }

    public void handleLoadingSuccess(WishSignupFreeGifts wishSignupFreeGifts) {
        if (this.mCurrentUiView instanceof SignupFreeGiftBaseView) {
            this.mFreeGifts = wishSignupFreeGifts;
            ((SignupFreeGiftBaseView) this.mCurrentUiView).setupFreeGifts(wishSignupFreeGifts);
            this.mLoadingView.markLoadingComplete();
        }
    }

    public void handleCartLoadSuccess(CartContext cartContext) {
        this.mCartContext = cartContext;
        setCurrentView();
    }

    public void setWishSignupFreeGiftCart(WishSignupFreeGiftCart wishSignupFreeGiftCart) {
        this.mWishSignupFreeGiftCart = wishSignupFreeGiftCart;
    }

    public void showTabArea(boolean z) {
        if (this.mCurrentUiView != null && (this.mCurrentUiView instanceof TabbedSignupFreeGiftView)) {
            ((TabbedSignupFreeGiftView) this.mCurrentUiView).showTabArea(z);
        }
    }

    public void hideTabArea(boolean z) {
        if (this.mCurrentUiView != null && (this.mCurrentUiView instanceof TabbedSignupFreeGiftView)) {
            ((TabbedSignupFreeGiftView) this.mCurrentUiView).hideTabArea(z);
        }
    }

    public int getTabAreaOffset() {
        if (this.mCurrentUiView == null || !(this.mCurrentUiView instanceof TabbedSignupFreeGiftView)) {
            return 0;
        }
        return ((TabbedSignupFreeGiftView) this.mCurrentUiView).getTabAreaOffset();
    }

    public void setTabAreaOffset(int i) {
        if (this.mCurrentUiView != null && (this.mCurrentUiView instanceof TabbedSignupFreeGiftView)) {
            ((TabbedSignupFreeGiftView) this.mCurrentUiView).setTabAreaOffset(i);
        }
    }

    public int getTabAreaSize() {
        if (this.mCurrentUiView == null || !(this.mCurrentUiView instanceof TabbedSignupFreeGiftView)) {
            return 0;
        }
        return ((TabbedSignupFreeGiftView) this.mCurrentUiView).getTabAreaSize();
    }

    public int getCurrentIndex() {
        if (this.mCurrentUiView == null || !(this.mCurrentUiView instanceof TabbedSignupFreeGiftView)) {
            return 0;
        }
        return ((TabbedSignupFreeGiftView) this.mCurrentUiView).getCurrentIndex();
    }
}
