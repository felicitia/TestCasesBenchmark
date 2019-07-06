package com.contextlogic.wish.activity.cart.items;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.cart.CartFragment;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.activity.cart.CartUiView;
import com.contextlogic.wish.activity.cart.billing.CartBaseBillingOptionSelectorView.CartBillingSection;
import com.contextlogic.wish.activity.returnpolicy.ReturnPolicyActivity;
import com.contextlogic.wish.activity.termspolicy.TermsPolicyTextView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.CartContext.CartType;
import com.contextlogic.wish.payments.checkout.CartCheckoutActionManager.CheckoutButtonContext.CheckoutButtonMode;
import com.contextlogic.wish.ui.button.SliderButton;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.timer.BaseCountdownTimerView.DoneCallback;
import com.contextlogic.wish.ui.timer.CountdownTimerView;
import com.crashlytics.android.Crashlytics;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CartItemsView extends CartUiView {
    private TextView mAddFromSavedForLater;
    protected CartItemsAdapter mCartAdapter;
    protected CartItemsAdapterModular mCartAdapterModular;
    protected CartItemsAdapterModular mCartAdapterModularEmpty;
    private FrameLayout mCartButtonContainer;
    protected CartItemsFooterView mCartItemsFooter;
    protected CartItemsHeaderView mCartItemsHeader;
    protected ListView mCartListView;
    private View mCartView;
    private TextView mCheckoutButton;
    private FrameLayout mCheckoutOfferContainer;
    /* access modifiers changed from: private */
    public TextView mCheckoutOfferText;
    protected CountdownTimerView mCheckoutOfferTimer;
    protected View mCheckoutOfferView;
    private View mEmptyBrowseButton;
    private TextView mEmptyCartText;
    private View mEmptyView;
    protected RelativeLayout mEmptyViewLayout;
    private View mGooglePayButton;
    private boolean mIsParentView;
    private TextView mReturnPolicyLink;
    private ListView mSaveForLaterListView;
    private LinearLayout mSaveForLaterWrapper;
    private SliderButton mSlideButton;
    /* access modifiers changed from: private */
    public TextView mTimerExpiredTextView;

    public boolean isParentView() {
        return true;
    }

    public boolean onBackPressed() {
        return false;
    }

    public CartItemsView(CartFragment cartFragment, CartActivity cartActivity, Bundle bundle) {
        super(cartFragment, cartActivity, bundle);
    }

    public void initializeUi(Bundle bundle) {
        this.mIsParentView = isParentView();
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.cart_fragment_cart_items, this);
        this.mCartButtonContainer = (FrameLayout) inflate.findViewById(R.id.cart_button_container);
        this.mAddFromSavedForLater = (TextView) inflate.findViewById(R.id.add_from_saved_for_later);
        this.mSaveForLaterWrapper = (LinearLayout) inflate.findViewById(R.id.save_for_later_wrapper);
        this.mSaveForLaterListView = (ListView) inflate.findViewById(R.id.save_for_later_only_listview);
        this.mEmptyView = inflate.findViewById(R.id.cart_fragment_cart_items_no_items_view);
        this.mEmptyViewLayout = (RelativeLayout) inflate.findViewById(R.id.cart_fragment_cart_items_no_items_layout);
        this.mEmptyCartText = (TextView) inflate.findViewById(R.id.empty_cart_text);
        this.mEmptyCartText.setCompoundDrawablePadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding));
        this.mEmptyCartText.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.cart_empty_state_icon_56), null, null);
        this.mEmptyBrowseButton = inflate.findViewById(R.id.cart_fragment_cart_items_no_items_view_browse_button);
        this.mEmptyBrowseButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartItemsView.this.getCartFragment().withActivity(new ActivityTask<CartActivity>() {
                    public void performTask(CartActivity cartActivity) {
                        cartActivity.startHomeActivity();
                    }
                });
            }
        });
        this.mCartView = inflate.findViewById(R.id.cart_fragment_cart_items_cart_view);
        this.mGooglePayButton = inflate.findViewById(R.id.cart_fragment_cart_items_google_button);
        this.mGooglePayButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartItemsView.this.getCartFragment().checkout(false);
            }
        });
        this.mCheckoutButton = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_checkout_button);
        this.mCheckoutButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CartItemsView.this.getCartFragment().getCartContext() != null && CartItemsView.this.getCartFragment().getCartContext().getCartType() == CartType.COMMERCE_CASH) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_CASH_CART_CHECKOUT_BUTTON, CartItemsView.this.getCartFragment().getCartContext().getEffectivePaymentMode());
                }
                CartItemsView.this.getCartFragment().checkout(false);
            }
        });
        this.mSlideButton = (SliderButton) inflate.findViewById(R.id.cart_fragment_cart_items_checkout_slider_button);
        this.mSlideButton.setSlideListener(new OnClickListener() {
            public void onClick(View view) {
                if (CartItemsView.this.getCartFragment().getCartContext() != null && CartItemsView.this.getCartFragment().getCartContext().getCartType() == CartType.COMMERCE_CASH) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_CASH_CART_SLIDE_BUTTON, CartItemsView.this.getCartFragment().getCartContext().getEffectivePaymentMode());
                }
                CartItemsView.this.getCartFragment().checkout(false);
            }
        });
        this.mCheckoutOfferView = inflate.findViewById(R.id.cart_fragment_cart_items_checkout_offer);
        this.mCheckoutOfferText = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_checkout_offer_text);
        this.mCheckoutOfferContainer = (FrameLayout) inflate.findViewById(R.id.cart_fragment_cart_items_checkout_offer_timer_container);
        this.mTimerExpiredTextView = (TextView) this.mCheckoutOfferContainer.findViewById(R.id.checkout_timer_expired_text_view);
        this.mReturnPolicyLink = (TextView) findViewById(R.id.return_policy_link_textview);
        setupCartItemsView(inflate);
        refreshUi();
        this.mCheckoutOfferView.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.cool_gray1));
        if (getCartFragment().getCartContext() != null && getCartFragment().getCartContext().shouldReloadCartOnReenter()) {
            getCartFragment().getCartContext().setReloadCartOnReenter(false);
            getCartFragment().withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
                public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                    baseActivity.showLoadingDialog();
                    cartServiceFragment.loadCart(null, false);
                }
            });
        }
    }

    public void setupTimerView(CartContext cartContext) {
        if (ExperimentDataCenter.getInstance().shouldSeeDifferentExpiredText() || ExperimentDataCenter.getInstance().shouldHideOfferView()) {
            this.mCheckoutOfferTimer.disableExpiredText();
            this.mCheckoutOfferTimer.setup(cartContext.getCart().getCheckoutOffer().getExpiry(), WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.cart_timer_height), getResources().getColor(R.color.white), getResources().getColor(R.color.black), (DoneCallback) new DoneCallback() {
                public void onCountdownEnd() {
                    if (ExperimentDataCenter.getInstance().shouldHideOfferView()) {
                        if (CartItemsView.this.mCheckoutOfferTimer != null) {
                            CartItemsView.this.animateOfferGone();
                        }
                    } else if (ExperimentDataCenter.getInstance().shouldSeeDifferentExpiredText() && CartItemsView.this.mCheckoutOfferTimer != null) {
                        CartItemsView.this.mCheckoutOfferTimer.setBlankBackgroundColorResId(R.color.expired_timer_dark_blue);
                        CartItemsView.this.mTimerExpiredTextView.setVisibility(0);
                        CartItemsView.this.mTimerExpiredTextView.bringToFront();
                        CartItemsView.this.mCheckoutOfferText.setTextColor(CartItemsView.this.getResources().getColor(R.color.expired_timer_message_text_color));
                    }
                }
            });
            return;
        }
        this.mCheckoutOfferTimer.setup(cartContext.getCart().getCheckoutOffer().getExpiry(), WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.cart_timer_height), WishApplication.getInstance().getResources().getColor(R.color.white), WishApplication.getInstance().getResources().getColor(R.color.black));
    }

    public void setupCartItemsView(View view) {
        this.mCartItemsHeader = new CartItemsHeaderView(getContext(), this, getCartFragment().getCartContext());
        this.mCartItemsFooter = new CartItemsFooterView(getContext(), this, getCartFragment().getCartContext());
        this.mCartListView = (ListView) view.findViewById(R.id.cart_fragment_cart_items_cart_listview);
        this.mCartListView.addHeaderView(this.mCartItemsHeader);
        this.mCartAdapterModularEmpty = createModuldarAdapter(this.mCartItemsFooter, this.mSaveForLaterListView);
        if (!ExperimentDataCenter.getInstance().shouldShowSaveForLater() || !this.mIsParentView) {
            this.mCartListView.addFooterView(this.mCartItemsFooter);
            this.mCartAdapter = createAdapter();
            this.mCartAdapter.setIsHeaderVisible(this.mCartItemsHeader.hasContent());
            this.mCartListView.setAdapter(this.mCartAdapter);
        } else {
            this.mCartAdapterModular = createModuldarAdapter(this.mCartItemsFooter, this.mCartListView);
            this.mCartAdapterModular.setIsHeaderVisible(this.mCartItemsHeader.hasContent());
            this.mCartListView.setAdapter(this.mCartAdapterModular);
            this.mSaveForLaterListView.setAdapter(this.mCartAdapterModularEmpty);
        }
        if (ExperimentDataCenter.getInstance().shouldShowTermsOfUseReaffirmation()) {
            showTermsofUseReaffirmation();
        }
        setupReturnPolicyLink();
        updateCartContext(getCartFragment().getCartContext());
    }

    public void refreshUi() {
        if (this.mCheckoutOfferTimer != null) {
            this.mCheckoutOfferTimer.pauseTimer();
            this.mCheckoutOfferContainer.removeView(this.mCheckoutOfferTimer);
            this.mTimerExpiredTextView.setVisibility(8);
        }
        CartContext cartContext = getCartFragment().getCartContext();
        if (cartContext == null || (cartContext.getCommerceLoanCart() == null && cartContext.getCommerceCashCart() == null && (cartContext.getCart() == null || cartContext.getCart().getItems() == null || cartContext.getCart().getItems().size() <= 0))) {
            this.mEmptyView.setVisibility(0);
            this.mCartView.setVisibility(8);
            this.mSaveForLaterListView.setVisibility(0);
            this.mCartAdapterModularEmpty.updateCartContext(cartContext);
            if (cartContext.getSavedForLaterProducts() == null || cartContext.getSavedForLaterProducts().isEmpty()) {
                this.mSaveForLaterWrapper.setVisibility(8);
                this.mAddFromSavedForLater.setVisibility(8);
                this.mEmptyBrowseButton.setVisibility(0);
                return;
            }
            this.mSaveForLaterWrapper.setVisibility(0);
            this.mAddFromSavedForLater.setVisibility(0);
            this.mEmptyBrowseButton.setVisibility(8);
            return;
        }
        this.mEmptyView.setVisibility(8);
        this.mSaveForLaterListView.setVisibility(8);
        this.mCartView.setVisibility(0);
        this.mGooglePayButton.setVisibility(8);
        this.mCheckoutButton.setVisibility(8);
        this.mSlideButton.setVisibility(8);
        setCartButtonVisibility(0);
        if (ExperimentDataCenter.getInstance().shouldShowSaveForLater() && this.mIsParentView) {
            this.mCartAdapterModular.updateCartButtonVisibility();
        }
        CheckoutButtonMode checkoutButtonMode = cartContext.getCheckoutActionManager().getCheckoutButtonContext().getCheckoutButtonMode();
        String checkoutButtonText = cartContext.getCheckoutActionManager().getCheckoutButtonContext().getCheckoutButtonText();
        if (checkoutButtonMode == CheckoutButtonMode.SLIDER) {
            this.mSlideButton.resetState();
            this.mSlideButton.setConfirmedText(getContext().getString(R.string.confirmed));
            this.mSlideButton.setSlideText(checkoutButtonText);
        } else if (checkoutButtonMode == CheckoutButtonMode.BUTTON) {
            this.mCheckoutButton.setText(checkoutButtonText);
        }
        this.mCartItemsHeader.updateCartContext(cartContext);
        this.mCartItemsFooter.updateCartContext(cartContext);
        if (!ExperimentDataCenter.getInstance().shouldShowSaveForLater() || !this.mIsParentView) {
            if (this.mCartAdapter == null) {
                this.mCartListView.addFooterView(this.mCartItemsFooter);
                this.mCartAdapter = createAdapter();
                this.mCartAdapter.setIsHeaderVisible(this.mCartItemsHeader.hasContent());
                this.mCartListView.setAdapter(this.mCartAdapter);
            }
            this.mCartAdapter.updateCartContext(cartContext);
        } else {
            if (this.mCartAdapterModular == null) {
                this.mCartAdapterModular = createModuldarAdapter(this.mCartItemsFooter, this.mCartListView);
                this.mCartAdapterModular.setIsHeaderVisible(this.mCartItemsHeader.hasContent());
                this.mCartListView.setAdapter(this.mCartAdapterModular);
                this.mSaveForLaterListView.setAdapter(this.mCartAdapterModularEmpty);
            }
            this.mCartAdapterModular.updateCartContext(cartContext);
        }
        updateCartContext(cartContext);
        if (cartContext.getCart() == null || cartContext.getCart().getCheckoutOffer() == null || cartContext.getCart().getCheckoutOffer().isExpired()) {
            this.mCheckoutOfferView.setVisibility(8);
            return;
        }
        this.mCheckoutOfferView.setVisibility(0);
        this.mCheckoutOfferTimer = new CountdownTimerView(getContext());
        this.mCheckoutOfferContainer.addView(this.mCheckoutOfferTimer);
        setupTimerView(cartContext);
        this.mCheckoutOfferTimer.startTimer();
        this.mCheckoutOfferText.setText(cartContext.getCart().getCheckoutOffer().getMessage());
    }

    public CartItemsFooterView getCartItemsFooter() {
        return this.mCartItemsFooter;
    }

    public View getCartButton() {
        if (getButtonMode() == CheckoutButtonMode.SLIDER) {
            return this.mSlideButton;
        }
        if (getButtonMode() == CheckoutButtonMode.GOOGLE_PAY) {
            return this.mGooglePayButton;
        }
        return this.mCheckoutButton;
    }

    public CheckoutButtonMode getButtonMode() {
        return getCartFragment().getCartContext().getCheckoutActionManager().getCheckoutButtonContext().getCheckoutButtonMode();
    }

    public void setCartButtonVisibility(int i) {
        CheckoutButtonMode buttonMode = getButtonMode();
        if (buttonMode == CheckoutButtonMode.SLIDER) {
            this.mSlideButton.setVisibility(i);
        } else if (buttonMode == CheckoutButtonMode.GOOGLE_PAY) {
            this.mGooglePayButton.setVisibility(i);
        } else {
            this.mCheckoutButton.setVisibility(i);
        }
    }

    public void setCheckoutContainerVisibility(int i) {
        this.mCartButtonContainer.setVisibility(i);
    }

    public FrameLayout getCheckoutContainer() {
        return this.mCartButtonContainer;
    }

    public void updateActionBar() {
        getCartFragment().withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                cartActivity.getActionBarManager().setTitle(WishApplication.getInstance().getResources().getString(R.string.cart));
                cartActivity.setHomeButtonToDefault();
            }
        });
    }

    public void releaseImages() {
        if (this.mCheckoutOfferTimer != null) {
            this.mCheckoutOfferTimer.pauseTimer();
        }
        if (this.mSaveForLaterListView != null) {
            for (int i = 0; i < this.mSaveForLaterListView.getChildCount(); i++) {
                if (this.mSaveForLaterListView.getChildAt(i) instanceof ImageRestorable) {
                    ((ImageRestorable) this.mSaveForLaterListView.getChildAt(i)).releaseImages();
                }
            }
        }
        if (this.mCartListView != null) {
            for (int i2 = 0; i2 < this.mCartListView.getChildCount(); i2++) {
                if (this.mCartListView.getChildAt(i2) instanceof ImageRestorable) {
                    ((ImageRestorable) this.mCartListView.getChildAt(i2)).releaseImages();
                }
            }
        }
    }

    public void restoreImages() {
        if (this.mCheckoutOfferTimer != null) {
            this.mCheckoutOfferTimer.startTimer();
        }
        if (this.mSaveForLaterListView != null) {
            for (int i = 0; i < this.mSaveForLaterListView.getChildCount(); i++) {
                if (this.mSaveForLaterListView.getChildAt(i) instanceof ImageRestorable) {
                    ((ImageRestorable) this.mSaveForLaterListView.getChildAt(i)).restoreImages();
                }
            }
        }
        if (this.mCartListView != null) {
            for (int i2 = 0; i2 < this.mCartListView.getChildCount(); i2++) {
                if (this.mCartListView.getChildAt(i2) instanceof ImageRestorable) {
                    ((ImageRestorable) this.mCartListView.getChildAt(i2)).restoreImages();
                }
            }
        }
    }

    public void recycle() {
        releaseImages();
    }

    public void editShipping() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_MANAGE_ADDRESSES_FROM_CART);
        getCartFragment().showAddressBook(null);
    }

    public void editBilling() {
        if (getCartFragment().getCartContext() != null && getCartFragment().getCartContext().getEffectivePaymentMode().equals("PaymentModeCommerceLoan")) {
            getCartFragment().showBillingView(false, CartBillingSection.COMMERCE_LOAN);
        } else if (getCartFragment().getCartContext() == null || !ExperimentDataCenter.getInstance().canCheckoutWithKlarnaPaypal() || !getCartFragment().getCartContext().getEffectivePaymentMode().equals("PaymentModePayPal")) {
            getCartFragment().showBillingView(false);
        } else {
            getCartFragment().getCartContext().updatePreferredPaymentMode("PaymentModeKlarna");
            getCartFragment().checkout(false);
        }
    }

    public void updateCommerceLoanPreferredDueDate(Date date) {
        getCartFragment().updateCommerceLoanPreferredDueDate(date);
    }

    public void handleInvalidCommerceLoan() {
        getCartFragment().handleInvalidCommerceLoan();
    }

    public void onStop() {
        if (this.mCartItemsFooter != null) {
            this.mCartItemsFooter.onStop();
        }
    }

    public void updateCartContext(CartContext cartContext) {
        if (this.mCartItemsHeader != null) {
            int dimensionPixelSize = ExperimentDataCenter.getInstance().shouldSeeConfidenceShieldReturnPolicyAboveCheckout() ? getResources().getDimensionPixelSize(R.dimen.twenty_padding) : 0;
            if (this.mCartItemsHeader.hasContent()) {
                if (this.mCartListView.getHeaderViewsCount() == 0) {
                    this.mCartListView.addHeaderView(this.mCartItemsHeader);
                }
                this.mCartListView.setPadding(0, 0, 0, dimensionPixelSize);
                return;
            }
            this.mCartListView.removeHeaderView(this.mCartItemsHeader);
        }
    }

    public ListView getCartListView() {
        return this.mCartListView;
    }

    public List<WishAnalyticsEvent> getWishAnalyticImpressionEvents() {
        return Collections.singletonList(WishAnalyticsEvent.IMPRESSION_MOBILE_NATIVE_CART);
    }

    /* access modifiers changed from: protected */
    public CartItemsAdapter createAdapter() {
        return new CartItemsAdapter(getContext(), this, getCartFragment().getCartContext());
    }

    /* access modifiers changed from: protected */
    public CartItemsAdapterModular createModuldarAdapter(CartItemsFooterView cartItemsFooterView, ListView listView) {
        CartItemsAdapterModular cartItemsAdapterModular = new CartItemsAdapterModular(getContext(), this, getCartFragment().getCartContext(), cartItemsFooterView, listView);
        return cartItemsAdapterModular;
    }

    public void scrollToSaveForLater() {
        if (this.mCartListView != null) {
            this.mCartListView.smoothScrollToPosition(this.mCartAdapterModular.getCount() - 1);
        }
    }

    private void showTermsofUseReaffirmation() {
        int i;
        CartContext cartContext = getCartFragment().getCartContext();
        if (cartContext == null) {
            return;
        }
        if (cartContext.getCommerceLoanCart() != null || cartContext.getCommerceCashCart() != null || (cartContext.getCart() != null && cartContext.getCart().getItems() != null && cartContext.getCart().getItems().size() > 0)) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(1);
            linearLayout.setLayoutParams(new LayoutParams(-1, -2));
            View view = new View(getContext());
            view.setLayoutParams(new LinearLayout.LayoutParams(-1, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.divide_small)));
            view.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.gray6));
            linearLayout.addView(view);
            String checkoutButtonText = cartContext.getCheckoutActionManager().getCheckoutButtonContext().getCheckoutButtonText();
            TermsPolicyTextView termsPolicyTextView = new TermsPolicyTextView(getContext(), WishApplication.getInstance().getResources().getString(R.string.terms_policy_placeholder_cart_reaffirmation, new Object[]{checkoutButtonText, "%1$s"}));
            termsPolicyTextView.setGravity(8388611);
            termsPolicyTextView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.gray3));
            termsPolicyTextView.setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.text_size_caption));
            termsPolicyTextView.setLineSpacing(0.0f, 1.2f);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            if (ExperimentDataCenter.getInstance().shouldSeeConfidenceShieldReturnPolicyAboveCheckout()) {
                i = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.thirty_two_padding);
            } else {
                i = WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.twelve_padding);
            }
            layoutParams.setMargins(WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.sixteen_padding), WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.twelve_padding), WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.sixteen_padding), i);
            termsPolicyTextView.setLayoutParams(layoutParams);
            linearLayout.addView(termsPolicyTextView);
            this.mCartListView.addFooterView(linearLayout);
        }
    }

    private void setupReturnPolicyLink() {
        if (ExperimentDataCenter.getInstance().shouldSeeConfidenceShieldReturnPolicyBottomCart() || ExperimentDataCenter.getInstance().shouldSeeRegularReturnPolicyBottomCart()) {
            this.mCartItemsFooter.hideReturnPolicyText();
            showReturnPolicyLinkAtBottomOfCart();
        } else if (ExperimentDataCenter.getInstance().shouldSeeConfidenceShieldReturnPolicyAboveCheckout()) {
            this.mCartItemsFooter.hideReturnPolicyText();
            showReturnPolicyLinkAboveCheckoutButton();
        }
    }

    private void showReturnPolicyLinkAtBottomOfCart() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.four_padding);
        ThemedTextView themedTextView = new ThemedTextView(getContext());
        themedTextView.setPadding(0, dimensionPixelSize, 0, dimensionPixelSize);
        themedTextView.setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.text_size_twelve));
        themedTextView.setGravity(1);
        if (ExperimentDataCenter.getInstance().shouldSeeConfidenceShieldReturnPolicyBottomCart()) {
            themedTextView.setText(R.string.thirty_day_free_return_and_refund);
            themedTextView.setTextColor(getResources().getColor(R.color.main_primary));
            Drawable drawable = getResources().getDrawable(R.drawable.confidence_shield);
            drawable.setBounds(0, 0, getResources().getDimensionPixelSize(R.dimen.confidence_shield_return_policy_link_width), getResources().getDimensionPixelSize(R.dimen.sixteen_padding));
            themedTextView.setCompoundDrawables(drawable, null, null, null);
            themedTextView.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.eight_padding));
            themedTextView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RETURN_POLICY_BOTTOM_CART_WITH_BADGE);
                    CartItemsView.this.showReturnPolicy();
                }
            });
        } else if (ExperimentDataCenter.getInstance().shouldSeeRegularReturnPolicyBottomCart()) {
            String string = getResources().getString(R.string.return_policy);
            String format = String.format(getResources().getString(R.string.free_return_up_to_30_days_view_return_policy), new Object[]{string});
            SpannableString spannableString = new SpannableString(format);
            int lastIndexOf = format.lastIndexOf(string);
            themedTextView.setTextColor(getResources().getColor(R.color.gray3));
            if (lastIndexOf != -1) {
                spannableString.setSpan(new ClickableSpan() {
                    public void onClick(View view) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RETURN_POLICY_BOTTOM_CART_NORMAL);
                        CartItemsView.this.showReturnPolicy();
                    }

                    public void updateDrawState(TextPaint textPaint) {
                        super.updateDrawState(textPaint);
                        textPaint.setUnderlineText(false);
                    }
                }, lastIndexOf, string.length() + lastIndexOf, 33);
            } else {
                themedTextView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RETURN_POLICY_BOTTOM_CART_NORMAL);
                        CartItemsView.this.showReturnPolicy();
                    }
                });
                StringBuilder sb = new StringBuilder();
                sb.append("Indexing error, ");
                sb.append(string);
                sb.append(" not found in the string ");
                sb.append(format);
                Crashlytics.logException(new Exception(sb.toString()));
            }
            themedTextView.setText(spannableString);
            themedTextView.setLinkTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
            themedTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        View view = new View(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.divide_small));
        layoutParams.rightMargin = getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
        layoutParams.leftMargin = getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(getResources().getColor(R.color.gray6));
        linearLayout.addView(view);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 8388611;
        layoutParams2.setMargins(WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.sixteen_padding), WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.eight_padding), WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.sixteen_padding), WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.eight_padding));
        themedTextView.setLayoutParams(layoutParams2);
        linearLayout.addView(themedTextView);
        this.mCartListView.addFooterView(linearLayout);
    }

    private void showReturnPolicyLinkAboveCheckoutButton() {
        Drawable drawable = getResources().getDrawable(R.drawable.confidence_shield);
        drawable.setBounds(0, 0, getResources().getDimensionPixelSize(R.dimen.confidence_shield_return_policy_link_width), getResources().getDimensionPixelSize(R.dimen.sixteen_padding));
        this.mReturnPolicyLink.setCompoundDrawables(drawable, null, null, null);
        this.mReturnPolicyLink.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.twelve_padding));
        this.mReturnPolicyLink.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_RETURN_POLICY_CHECKOUT_BUTTON_WITH_BADGE);
                CartItemsView.this.showReturnPolicy();
            }
        });
        this.mReturnPolicyLink.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void showReturnPolicy() {
        getCartFragment().withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                Intent intent = new Intent();
                intent.setClass(cartActivity, ReturnPolicyActivity.class);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_RETURN_POLICY_FROM_CART);
                cartActivity.startActivity(intent);
            }
        });
    }

    public void disableEditingPaymentMethod() {
        if (this.mCartItemsHeader != null) {
            this.mCartItemsHeader.setEditingPaymentInfoDisabled(true);
        }
    }

    /* access modifiers changed from: private */
    public void animateOfferGone() {
        if (this.mCheckoutOfferTimer != null && this.mCheckoutOfferText != null) {
            ObjectAnimator duration = ObjectAnimator.ofFloat(this.mCheckoutOfferText, "alpha", new float[]{1.0f, 0.0f}).setDuration(600);
            duration.setStartDelay(1000);
            ObjectAnimator duration2 = ObjectAnimator.ofFloat(this.mCheckoutOfferText, "alpha", new float[]{0.0f, 1.0f}).setDuration(600);
            duration2.setStartDelay(250);
            duration2.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    CartItemsView.this.mCheckoutOfferText.setText(R.string.instant_offer_expired);
                }
            });
            this.mCheckoutOfferView.setPivotY(0.0f);
            ObjectAnimator duration3 = ObjectAnimator.ofFloat(this.mCheckoutOfferView, "scaleY", new float[]{0.0f}).setDuration(600);
            duration3.setStartDelay(2000);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(new Animator[]{duration, duration2, duration3});
            animatorSet.start();
        }
    }
}
