package com.contextlogic.wish.activity.cart.items;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.cart.CartFragment;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.api.model.WishCartNotice;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishShippingOption;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.cartexpiry.CartExpiryDialogFragment;
import com.contextlogic.wish.dialog.quantitydropdown.QuantityDropdownDialogFragment;
import com.contextlogic.wish.dialog.quantitydropdown.QuantityDropdownView;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.timer.CartOfferTimerView;
import com.contextlogic.wish.ui.timer.CountdownTimerView;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public class CartItemView extends RelativeLayout implements ImageRestorable {
    private LinearLayout mAdjustQuantityContainer;
    private Context mContext;
    private LinearLayout mCountdownContainer;
    private CountdownTimerView mCountdownTimer;
    /* access modifiers changed from: private */
    public CartFragment mFragment;
    private NetworkImageView mImageView;
    private TextView mListPriceText;
    private LinearLayout mNoLongerAvailableContainer;
    private CartOfferTimerView mPriceExpiryTimer;
    private TextView mPriceText;
    private ThemedTextView mPromotionMessage;
    private LinearLayout mPromotionMessageContainer;
    private TextView mQuantity;
    private QuantityDropdownView mQuantityDropdown;
    private TextView mRemoveButton;
    private View mRemoveCartItemButton;
    private View mRowSpacer;
    /* access modifiers changed from: private */
    public TextView mRowTitle;
    private TextView mSectionTitle;
    private View mShadowBottom;
    private View mShadowTop;
    private TextView mShippingDateText;
    private View mShippingOptionsContainer;
    private LinearLayout mShippingOptionsView;
    private TextView mShippingText;
    private LinearLayout mSizeColorContainer;
    private TextView mSizeColorText;
    private LinearLayout mTimersContainer;
    private TextView mUrgencyText;
    private AutoReleasableImageView mWantLess;
    private View mWantLessContainer;
    private AutoReleasableImageView mWantMore;
    private View mWantMoreContainer;
    private LinearLayout mWarningMessageContainer;

    public CartItemView(Context context, CartFragment cartFragment) {
        super(context);
        this.mFragment = cartFragment;
        this.mContext = context;
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(ExperimentDataCenter.getInstance().shouldSeeRedesignedCartNotices() ? R.layout.cart_fragment_full_cart_item_row_redesigned : R.layout.cart_fragment_full_cart_items_item_row, this, true);
        this.mImageView = (NetworkImageView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_image);
        this.mRowTitle = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_title);
        this.mUrgencyText = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_urgency_text);
        this.mSizeColorContainer = (LinearLayout) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_size_color_container);
        this.mSizeColorText = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_size_color_text);
        this.mShippingText = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_shipping_text);
        this.mShippingDateText = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_shipping_date_text);
        this.mPriceText = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_your_price);
        this.mListPriceText = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_list_price);
        this.mListPriceText.setPaintFlags(this.mListPriceText.getPaintFlags() | 16);
        this.mWarningMessageContainer = (LinearLayout) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_warning_container);
        this.mCountdownContainer = (LinearLayout) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_countdown_container);
        this.mShippingOptionsContainer = inflate.findViewById(R.id.cart_fragment_cart_items_item_row_shipping_options_view);
        this.mShippingOptionsView = (LinearLayout) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_shipping_options_container);
        this.mAdjustQuantityContainer = (LinearLayout) inflate.findViewById(R.id.cart_fragment_cart_items_adjust_quantity_container);
        this.mWantLessContainer = inflate.findViewById(R.id.cart_fragment_cart_items_item_row_want_less_container);
        this.mWantLess = (AutoReleasableImageView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_want_less);
        this.mWantMoreContainer = inflate.findViewById(R.id.cart_fragment_cart_items_item_row_want_more_container);
        this.mWantMore = (AutoReleasableImageView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_want_more);
        this.mQuantity = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_quantity_text);
        this.mQuantityDropdown = (QuantityDropdownView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_quantity_dropdown);
        this.mRemoveButton = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_remove_button);
        this.mRowSpacer = inflate.findViewById(R.id.cart_fragment_cart_items_item_row_spacer);
        this.mNoLongerAvailableContainer = (LinearLayout) inflate.findViewById(R.id.cart_fragment_cart_items_item_no_longer_available_container);
        this.mRemoveCartItemButton = inflate.findViewById(R.id.cart_fragment_cart_items_remove_item_button);
        this.mPromotionMessageContainer = (LinearLayout) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_promotion_container);
        this.mPromotionMessage = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_promotion);
        this.mTimersContainer = new LinearLayout(getContext());
        this.mShadowTop = findViewById(R.id.shadow_top);
        this.mSectionTitle = (TextView) findViewById(R.id.header_view_section_title);
        this.mShadowBottom = findViewById(R.id.shadow_bottom);
        if (ExperimentDataCenter.getInstance().shouldSeeRedesignedCartNotices()) {
            this.mRemoveButton = new TextView(getContext());
            this.mCountdownContainer = new LinearLayout(getContext());
            this.mTimersContainer = (LinearLayout) inflate.findViewById(R.id.redesign_cart_items_timers_container);
        }
    }

    public void modifyQuantity(final int i, final WishCartItem wishCartItem) {
        if (i > 0) {
            this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
                public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                    cartServiceFragment.updateCart(wishCartItem.getProductId(), wishCartItem.getVariationId(), wishCartItem.getSelectedShippingOption() != null ? wishCartItem.getSelectedShippingOption().getOptionId() : null, i);
                }
            });
            return;
        }
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_FULL_SCREEN_REMOVE_FROM_CART, wishCartItem.getProductId());
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                if (wishCartItem.isDailyGiveaway()) {
                    cartServiceFragment.showDailyGiveawayRemoveFromCartPrompt(wishCartItem);
                } else if (ExperimentDataCenter.getInstance().shouldShowSaveForLater()) {
                    cartServiceFragment.showSaveForLaterPrompt(wishCartItem);
                } else {
                    cartServiceFragment.showRemoveFromCartPrompt(wishCartItem);
                }
            }
        });
    }

    public void updateQuantityUI(WishCartItem wishCartItem) {
        boolean shouldSeeRedesignedCartNotices = ExperimentDataCenter.getInstance().shouldSeeRedesignedCartNotices();
        int i = 4;
        if (ExperimentDataCenter.getInstance().shouldShowDropdownQuantity()) {
            this.mQuantityDropdown.setVisibility(0);
            LinearLayout linearLayout = this.mAdjustQuantityContainer;
            if (shouldSeeRedesignedCartNotices) {
                i = 8;
            }
            linearLayout.setVisibility(i);
            this.mWantMoreContainer.setEnabled(false);
            this.mWantLessContainer.setEnabled(false);
            if (ExperimentDataCenter.getInstance().shouldShowDropdownQuantityWithRemoveView()) {
                this.mRemoveButton.setVisibility(0);
            } else {
                this.mRemoveButton.setVisibility(8);
            }
            this.mQuantityDropdown.setText(String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(wishCartItem.getQuantity())}));
            return;
        }
        QuantityDropdownView quantityDropdownView = this.mQuantityDropdown;
        if (shouldSeeRedesignedCartNotices) {
            i = 8;
        }
        quantityDropdownView.setVisibility(i);
        this.mAdjustQuantityContainer.setVisibility(0);
        this.mWantMore.setImageResource(R.drawable.gray_add);
        if (wishCartItem.getQuantity() >= wishCartItem.getMaxOrderQuantity() || wishCartItem.isFreeGift() || wishCartItem.isDailyGiveaway()) {
            this.mWantMore.setAlpha(0.15f);
        } else {
            this.mWantMore.setAlpha(1.0f);
        }
        this.mWantMoreContainer.setEnabled(true);
        this.mWantLessContainer.setEnabled(true);
        this.mQuantity.setText(Integer.toString(wishCartItem.getQuantity()));
        this.mWantLess.setImageResource(R.drawable.gray_minus);
    }

    public void showHeader(boolean z) {
        if (!z) {
            int dimensionPixelSize = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
            int dimensionPixelSize2 = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.twelve_padding);
            this.mSectionTitle.setPadding(dimensionPixelSize, dimensionPixelSize2, 0, dimensionPixelSize2);
        }
        this.mShadowTop.setVisibility(0);
        this.mSectionTitle.setVisibility(0);
        this.mShadowBottom.setVisibility(0);
    }

    public void setItem(WishCartItem wishCartItem) {
        setItem(wishCartItem, true);
    }

    public void setItem(final WishCartItem wishCartItem, final boolean z) {
        LinearLayout linearLayout;
        boolean shouldUsePsuedoLocalizedCurrency = ExperimentDataCenter.getInstance().shouldUsePsuedoLocalizedCurrency();
        this.mShadowTop.setVisibility(8);
        this.mSectionTitle.setVisibility(8);
        this.mShadowBottom.setVisibility(8);
        View findViewById = findViewById(R.id.notices_layout);
        this.mWantLessContainer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                hashMap.put("quantity", Integer.toString(wishCartItem.getQuantity() - 1));
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CART_REDUCE_QUANTITY.getValue(), wishCartItem.getProductId(), hashMap);
                CartItemView.this.modifyQuantity(wishCartItem.getQuantity() - 1, wishCartItem);
            }
        });
        this.mWantMoreContainer.setVisibility(0);
        this.mWantMoreContainer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (wishCartItem.getQuantity() < wishCartItem.getMaxOrderQuantity() && !wishCartItem.isFreeGift() && !wishCartItem.isDailyGiveaway()) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("quantity", Integer.toString(wishCartItem.getQuantity() + 1));
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CART_INCREASE_QUANTITY, wishCartItem.getProductId(), hashMap);
                    CartItemView.this.modifyQuantity(wishCartItem.getQuantity() + 1, wishCartItem);
                }
            }
        });
        this.mQuantityDropdown.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                HashMap hashMap = new HashMap();
                hashMap.put("quantity", Integer.toString(wishCartItem.getQuantity()));
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_CART_QUANTITY_DROPDOWN, wishCartItem.getProductId(), hashMap);
                if (CartItemView.this.mFragment != null) {
                    CartItemView.this.mFragment.withActivity(new ActivityTask<CartActivity>() {
                        public void performTask(CartActivity cartActivity) {
                            QuantityDropdownDialogFragment quantityDropdownDialogFragment;
                            if (wishCartItem.isFreeGift() || wishCartItem.isDailyGiveaway()) {
                                quantityDropdownDialogFragment = QuantityDropdownDialogFragment.createQuantityDropdownDialogFragment(Math.min(1, wishCartItem.getMaxOrderQuantity()));
                            } else {
                                quantityDropdownDialogFragment = QuantityDropdownDialogFragment.createQuantityDropdownDialogFragment(Math.min(wishCartItem.getInventory(), wishCartItem.getMaxOrderQuantity()));
                            }
                            cartActivity.startDialog(quantityDropdownDialogFragment, new BaseDialogCallback() {
                                public void onCancel(BaseDialogFragment baseDialogFragment) {
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_CART_QUANTITY_CANCEL_DROPDOWN);
                                }

                                public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                                    if (bundle != null) {
                                        int i2 = bundle.getInt("QUANTITY_KEY");
                                        if (wishCartItem.getQuantity() < i2) {
                                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CART_INCREASE_QUANTITY);
                                        } else if (wishCartItem.getQuantity() > i2) {
                                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CART_REDUCE_QUANTITY);
                                        } else {
                                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_CART_QUANTITY_CANCEL_DROPDOWN);
                                        }
                                        if (wishCartItem.getQuantity() != i2) {
                                            CartItemView.this.modifyQuantity(i2, wishCartItem);
                                        }
                                    }
                                }
                            });
                        }
                    });
                }
            }
        });
        this.mRemoveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartItemView.this.modifyQuantity(0, wishCartItem);
            }
        });
        this.mFragment.withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                if (!z) {
                    CartItemView.this.mRowTitle.setText(CartItemView.this.getContext().getString(R.string.item_has_been_added_to_cart));
                } else {
                    CartItemView.this.mRowTitle.setText(wishCartItem.getName());
                }
            }
        });
        if (wishCartItem.getProductSubtotal().getValue() <= 0.0d) {
            this.mPriceText.setText(getContext().getString(R.string.free));
        } else {
            this.mPriceText.setText(wishCartItem.getProductSubtotal().toFormattedString(shouldUsePsuedoLocalizedCurrency, false));
        }
        if (wishCartItem.getRetailPrice().getValue() <= 0.0d || wishCartItem.getRetailPrice().getValue() <= wishCartItem.getProductSubtotal().getValue()) {
            this.mListPriceText.setVisibility(8);
        } else {
            this.mListPriceText.setText(wishCartItem.getRetailPrice().toFormattedString(shouldUsePsuedoLocalizedCurrency, false));
            this.mListPriceText.setVisibility(0);
        }
        this.mImageView.setImage(wishCartItem.getImage());
        this.mImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartItemView.this.showProduct(wishCartItem);
            }
        });
        this.mImageView.setClickable(true);
        if (wishCartItem.getCartNotices() == null || wishCartItem.getCartNotices().size() <= 0) {
            this.mWarningMessageContainer.setVisibility(8);
        } else {
            if (findViewById != null) {
                findViewById.setVisibility(0);
            }
            setupCartNotices(wishCartItem.getCartNotices());
        }
        if (wishCartItem.getUrgencyText() != null) {
            showUrgencyText(wishCartItem.getUrgencyText());
        } else {
            this.mUrgencyText.setVisibility(8);
        }
        String createSizeAndColorText = wishCartItem.createSizeAndColorText();
        if (createSizeAndColorText.equals("")) {
            this.mSizeColorContainer.setVisibility(8);
        } else {
            this.mSizeColorContainer.setVisibility(0);
            this.mSizeColorText.setText(createSizeAndColorText);
        }
        showShippingText(wishCartItem);
        this.mShippingDateText.setText(wishCartItem.getShippingTimeString());
        updateCartTimer(wishCartItem);
        this.mShippingOptionsContainer.setVisibility(8);
        this.mShippingOptionsView.removeAllViews();
        this.mShippingText.setVisibility(0);
        ArrayList shippingOptions = wishCartItem.getShippingOptions();
        if (shippingOptions != null && shippingOptions.size() > 0) {
            this.mShippingOptionsContainer.setVisibility(0);
            Iterator it = shippingOptions.iterator();
            while (it.hasNext()) {
                final WishShippingOption wishShippingOption = (WishShippingOption) it.next();
                if (ExperimentDataCenter.getInstance().shouldSeeRedesignedCartNotices()) {
                    linearLayout = new CartItemsShippingViewRedesigned(getContext());
                    ((CartItemsShippingViewRedesigned) linearLayout).setShippingOption(wishShippingOption);
                } else {
                    linearLayout = new CartItemsShippingOptionView(getContext());
                    ((CartItemsShippingOptionView) linearLayout).setShippingOption(wishShippingOption);
                }
                linearLayout.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (!wishShippingOption.isSelected()) {
                            CartItemView.this.changeShippingOption(wishCartItem, wishShippingOption.getOptionId());
                        }
                    }
                });
                this.mShippingOptionsView.addView(linearLayout);
            }
            if (!ExperimentDataCenter.getInstance().shouldSeeRedesignedCartNotices()) {
                this.mShippingText.setVisibility(8);
            }
        }
        this.mRowSpacer.setVisibility(8);
        updateQuantityUI(wishCartItem);
        if (wishCartItem.getPromotionCartSpec() == null || wishCartItem.getPromotionCartSpec().getCartText() == null) {
            this.mPromotionMessageContainer.setVisibility(8);
        } else {
            if (findViewById != null) {
                findViewById.setVisibility(0);
            }
            this.mPromotionMessageContainer.setVisibility(0);
            this.mPromotionMessage.setText(wishCartItem.getPromotionCartSpec().getCartText());
        }
        if (!wishCartItem.isAvailable()) {
            showItemNoLongerAvailableUI(wishCartItem);
        } else {
            resetRowViews(wishCartItem);
        }
    }

    private void showUrgencyText(String str) {
        if (ExperimentDataCenter.getInstance().shouldSeeRedesignedCartNotices()) {
            findViewById(R.id.notices_layout).setVisibility(0);
            this.mWarningMessageContainer.setVisibility(0);
            Drawable drawable = getResources().getDrawable(R.drawable.general_notice_18);
            drawable.setBounds(0, 0, getResources().getDimensionPixelSize(R.dimen.cart_redesign_general_warning_icon_size), getResources().getDimensionPixelSize(R.dimen.cart_redesign_general_warning_icon_size));
            this.mUrgencyText.setCompoundDrawables(drawable, null, null, null);
            this.mUrgencyText.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.eight_padding));
        }
        this.mUrgencyText.setText(str);
        this.mUrgencyText.setVisibility(0);
    }

    private void setupCartNotices(ArrayList<WishCartNotice> arrayList) {
        this.mWarningMessageContainer.removeAllViews();
        this.mWarningMessageContainer.setVisibility(0);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            WishCartNotice wishCartNotice = (WishCartNotice) it.next();
            this.mWarningMessageContainer.addView(new CartItemsRowWarningView(WishApplication.getInstance().getBaseContext(), wishCartNotice.getTitle(), wishCartNotice.getMessage(), wishCartNotice.getIconURL()));
        }
    }

    private void updateCartTimer(final WishCartItem wishCartItem) {
        if (ExperimentDataCenter.getInstance().shouldSeeRedesignedCartNotices()) {
            this.mTimersContainer.removeAllViews();
            this.mTimersContainer.setVisibility(8);
            if (wishCartItem.getPriceExpiryInfo() != null && !wishCartItem.getPriceExpiryInfo().isExpired() && wishCartItem.getPriceExpiryInfo().getExpiry().before(new Date(System.currentTimeMillis() + 3600000))) {
                this.mPriceExpiryTimer = new CartOfferTimerView(getContext());
                this.mPriceExpiryTimer.setupTimer(wishCartItem.getPriceExpiryInfo().getExpiry());
                StringBuilder sb = new StringBuilder();
                sb.append(wishCartItem.getPriceExpiryInfo().getTitle());
                sb.append(": ");
                SpannableString spannableString = new SpannableString(sb.toString());
                spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 17);
                this.mPriceExpiryTimer.setDescription(TextUtils.concat(new CharSequence[]{spannableString, wishCartItem.getPriceExpiryInfo().getMessage()}));
                this.mTimersContainer.addView(this.mPriceExpiryTimer);
                this.mTimersContainer.setVisibility(0);
                this.mTimersContainer.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        final CartExpiryDialogFragment createCartExpiryDialog = CartExpiryDialogFragment.createCartExpiryDialog(wishCartItem);
                        if (createCartExpiryDialog != null) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CART_COUNTER);
                            CartItemView.this.mFragment.withActivity(new ActivityTask<CartActivity>() {
                                public void performTask(CartActivity cartActivity) {
                                    cartActivity.startDialog(createCartExpiryDialog);
                                }
                            });
                        }
                    }
                });
                return;
            }
            return;
        }
        if (this.mCountdownTimer != null) {
            this.mCountdownTimer.pauseTimer();
        }
        this.mCountdownContainer.removeAllViews();
        this.mCountdownContainer.setVisibility(8);
        if (wishCartItem.getPriceExpiryInfo() != null && !wishCartItem.getPriceExpiryInfo().isExpired() && wishCartItem.getPriceExpiryInfo().getExpiry().before(new Date(System.currentTimeMillis() + 3600000))) {
            this.mCountdownTimer = new CountdownTimerView(getContext());
            this.mCountdownTimer.setGravity(17);
            this.mCountdownTimer.setDigitPadding(0).setColonPadding(0).setPadding(0);
            this.mCountdownTimer.setup(wishCartItem.getPriceExpiryInfo().getExpiry(), getResources().getDimensionPixelSize(R.dimen.full_screen_cart_timer_height), getResources().getColor(R.color.cool_gray1), getResources().getColor(R.color.white), getResources().getColor(R.color.white));
            this.mCountdownTimer.startTimer();
            this.mCountdownContainer.addView(this.mCountdownTimer);
            this.mCountdownContainer.setVisibility(0);
            this.mCountdownContainer.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    final CartExpiryDialogFragment createCartExpiryDialog = CartExpiryDialogFragment.createCartExpiryDialog(wishCartItem);
                    if (createCartExpiryDialog != null) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CART_COUNTER);
                        CartItemView.this.mFragment.withActivity(new ActivityTask<CartActivity>() {
                            public void performTask(CartActivity cartActivity) {
                                cartActivity.startDialog(createCartExpiryDialog);
                            }
                        });
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void changeShippingOption(final WishCartItem wishCartItem, final String str) {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.changeShippingOption(wishCartItem, str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void showProduct(final WishCartItem wishCartItem) {
        this.mFragment.withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                Intent intent = new Intent();
                intent.setClass(cartActivity, ProductDetailsActivity.class);
                ProductDetailsActivity.addInitialProduct(intent, new WishProduct(wishCartItem.getProductId()));
                cartActivity.startActivity(intent);
            }
        });
    }

    private void showItemNoLongerAvailableUI(final WishCartItem wishCartItem) {
        this.mImageView.setAlpha(0.25f);
        this.mImageView.setClickable(false);
        this.mPriceText.setAlpha(0.25f);
        this.mListPriceText.setAlpha(0.25f);
        this.mRowTitle.setAlpha(0.25f);
        this.mSizeColorContainer.setVisibility(8);
        this.mShippingText.setVisibility(8);
        this.mShippingDateText.setVisibility(8);
        this.mWarningMessageContainer.setVisibility(8);
        this.mAdjustQuantityContainer.setVisibility(8);
        this.mShippingOptionsContainer.setVisibility(8);
        this.mQuantityDropdown.setVisibility(8);
        this.mTimersContainer.setVisibility(8);
        this.mUrgencyText.setVisibility(8);
        this.mCountdownContainer.removeAllViews();
        this.mCountdownContainer.setVisibility(8);
        this.mNoLongerAvailableContainer.setVisibility(0);
        this.mRemoveCartItemButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartItemView.this.removeProduct(wishCartItem);
            }
        });
    }

    private void resetRowViews(WishCartItem wishCartItem) {
        this.mImageView.setAlpha(1.0f);
        this.mPriceText.setAlpha(1.0f);
        this.mListPriceText.setAlpha(1.0f);
        this.mRowTitle.setAlpha(1.0f);
        this.mShippingDateText.setVisibility(0);
        updateQuantityUI(wishCartItem);
        this.mNoLongerAvailableContainer.setVisibility(8);
        this.mRemoveCartItemButton.setOnClickListener(null);
    }

    public void releaseImages() {
        this.mImageView.releaseImages();
        if (this.mCountdownTimer != null) {
            this.mCountdownTimer.pauseTimer();
        }
        if (this.mWarningMessageContainer != null) {
            for (int i = 0; i < this.mWarningMessageContainer.getChildCount(); i++) {
                View childAt = this.mWarningMessageContainer.getChildAt(i);
                if (childAt instanceof CartItemsRowWarningView) {
                    ((CartItemsRowWarningView) childAt).releaseImages();
                }
            }
        }
    }

    public void restoreImages() {
        this.mImageView.restoreImages();
        if (this.mCountdownTimer != null) {
            this.mCountdownTimer.startTimer();
        }
        if (this.mWarningMessageContainer != null) {
            for (int i = 0; i < this.mWarningMessageContainer.getChildCount(); i++) {
                View childAt = this.mWarningMessageContainer.getChildAt(i);
                if (childAt instanceof CartItemsRowWarningView) {
                    ((CartItemsRowWarningView) childAt).restoreImages();
                }
            }
        }
    }

    public void showShippingText(WishCartItem wishCartItem) {
        String str;
        boolean shouldUsePsuedoLocalizedCurrency = ExperimentDataCenter.getInstance().shouldUsePsuedoLocalizedCurrency();
        if (wishCartItem.getShippingPrice().getValue() <= 0.0d) {
            str = getContext().getString(R.string.free);
        } else {
            str = wishCartItem.getShippingPrice().multiply((double) wishCartItem.getQuantity()).toFormattedString(shouldUsePsuedoLocalizedCurrency, false);
        }
        this.mShippingText.setText(WishApplication.getInstance().getResources().getString(R.string.shipping_colon_with_price, new Object[]{str}));
    }

    public void showTopSeparator() {
        this.mRowSpacer.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void removeProduct(final WishCartItem wishCartItem) {
        this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                cartServiceFragment.removeCartItem(wishCartItem);
            }
        });
    }

    public void hideSeperator() {
        findViewById(R.id.cart_item_redesigned_divider_1).setVisibility(8);
        findViewById(R.id.cart_item_redesigned_divider_2).setVisibility(8);
        findViewById(R.id.cart_item_redesigned_divider_3).setVisibility(8);
    }
}
