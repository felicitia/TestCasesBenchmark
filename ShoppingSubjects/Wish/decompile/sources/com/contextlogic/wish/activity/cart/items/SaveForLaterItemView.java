package com.contextlogic.wish.activity.cart.items;

import android.content.Context;
import android.content.Intent;
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
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishSavedForLaterProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.cartexpiry.CartExpiryDialogFragment;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.timer.CountdownTimerView;
import java.util.Date;

public class SaveForLaterItemView extends RelativeLayout implements ImageRestorable {
    private TextView mAddToCart;
    private LinearLayout mCountdownContainer;
    private CountdownTimerView mCountdownTimer;
    /* access modifiers changed from: private */
    public CartFragment mFragment;
    private NetworkImageView mItemImageView;
    private TextView mItemTitle;
    private TextView mListPrice;
    private TextView mRemoveFromSFL;
    private View mRowDivider;
    private TextView mSectionTitle;
    private View mShadowBottom;
    private View mShadowTop;
    private TextView mSizeAndColor;
    private TextView mYourPrice;

    public SaveForLaterItemView(Context context, CartFragment cartFragment, CartItemsAdapterModular cartItemsAdapterModular) {
        super(context);
        this.mFragment = cartFragment;
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.save_for_later_cart_item, this, true);
        this.mShadowTop = inflate.findViewById(R.id.shadow_top);
        this.mSectionTitle = (TextView) inflate.findViewById(R.id.header_view_section_title);
        this.mShadowBottom = inflate.findViewById(R.id.shadow_bottom);
        this.mItemImageView = (NetworkImageView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_image);
        this.mCountdownContainer = (LinearLayout) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_countdown_container);
        this.mItemTitle = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_title);
        this.mSizeAndColor = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_size_color_text);
        this.mRemoveFromSFL = (TextView) inflate.findViewById(R.id.remove_from_sfl);
        this.mRemoveFromSFL.setCompoundDrawablePadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.eight_padding));
        this.mRemoveFromSFL.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.remove_icon_default), null, null, null);
        this.mAddToCart = (TextView) inflate.findViewById(R.id.add_to_cart_button);
        this.mYourPrice = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_your_price);
        this.mListPrice = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_item_row_list_price);
        this.mListPrice.setPaintFlags(this.mListPrice.getPaintFlags() | 16);
        this.mRowDivider = inflate.findViewById(R.id.cart_fragment_cart_items_item_row_spacer);
    }

    public void setup(final WishSavedForLaterProduct wishSavedForLaterProduct, boolean z, boolean z2) {
        if (z) {
            this.mShadowTop.setVisibility(0);
            this.mSectionTitle.setVisibility(0);
            this.mShadowBottom.setVisibility(0);
        } else {
            this.mShadowTop.setVisibility(8);
            this.mSectionTitle.setVisibility(8);
            this.mShadowBottom.setVisibility(8);
        }
        this.mItemImageView.setImage(wishSavedForLaterProduct.getImage());
        this.mItemImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SaveForLaterItemView.this.showProduct(wishSavedForLaterProduct);
            }
        });
        this.mItemImageView.setClickable(true);
        this.mItemTitle.setText(wishSavedForLaterProduct.getName());
        this.mSizeAndColor.setText(wishSavedForLaterProduct.createSizeAndColorText());
        this.mAddToCart.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SaveForLaterItemView.this.mFragment.withServiceFragment(new ServiceTask<CartActivity, CartServiceFragment>() {
                    public void performTask(CartActivity cartActivity, CartServiceFragment cartServiceFragment) {
                        cartServiceFragment.removeFromSaveForLaterAndAddToCart(wishSavedForLaterProduct, false);
                    }
                });
            }
        });
        this.mRemoveFromSFL.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SaveForLaterItemView.this.mFragment.withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_REMOVE_FROM_SAVE_FOR_LATER);
                        if (ExperimentDataCenter.getInstance().shouldShowSaveForLater()) {
                            cartServiceFragment.showSaveToWishListPrompt(wishSavedForLaterProduct);
                        } else {
                            cartServiceFragment.removeFromSaveForLater(wishSavedForLaterProduct, true);
                        }
                    }
                });
            }
        });
        updateCartTimer(wishSavedForLaterProduct);
        WishLocalizedCurrencyValue productSubtotal = wishSavedForLaterProduct.getProductSubtotal();
        WishLocalizedCurrencyValue retailPrice = wishSavedForLaterProduct.getRetailPrice();
        boolean shouldUsePsuedoLocalizedCurrency = ExperimentDataCenter.getInstance().shouldUsePsuedoLocalizedCurrency();
        if (productSubtotal.getValue() <= 0.0d) {
            this.mYourPrice.setText(R.string.free);
        } else {
            this.mYourPrice.setText(productSubtotal.toFormattedString(shouldUsePsuedoLocalizedCurrency, false));
        }
        if (retailPrice.getValue() <= 0.0d || retailPrice.getValue() <= productSubtotal.getValue()) {
            this.mListPrice.setVisibility(8);
        } else {
            this.mListPrice.setText(retailPrice.toFormattedString(shouldUsePsuedoLocalizedCurrency, false));
            this.mListPrice.setVisibility(0);
        }
        if (z2) {
            this.mRowDivider.setVisibility(8);
        } else {
            this.mRowDivider.setVisibility(0);
        }
    }

    private void updateCartTimer(final WishSavedForLaterProduct wishSavedForLaterProduct) {
        if (this.mCountdownTimer != null) {
            this.mCountdownTimer.pauseTimer();
        }
        this.mCountdownContainer.removeAllViews();
        this.mCountdownContainer.setVisibility(8);
        if (wishSavedForLaterProduct.getPriceExpiryInfo() != null && !wishSavedForLaterProduct.getPriceExpiryInfo().isExpired() && wishSavedForLaterProduct.getPriceExpiryInfo().getExpiry().before(new Date(System.currentTimeMillis() + 3600000))) {
            this.mCountdownTimer = new CountdownTimerView(getContext());
            this.mCountdownTimer.setGravity(17);
            this.mCountdownTimer.setDigitPadding(0).setColonPadding(0).setPadding(0);
            this.mCountdownTimer.setup(wishSavedForLaterProduct.getPriceExpiryInfo().getExpiry(), getResources().getDimensionPixelSize(R.dimen.full_screen_cart_timer_height), getResources().getColor(R.color.cool_gray1), getResources().getColor(R.color.white), getResources().getColor(R.color.white));
            this.mCountdownTimer.startTimer();
            this.mCountdownContainer.addView(this.mCountdownTimer);
            this.mCountdownContainer.setVisibility(0);
            this.mCountdownContainer.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    final CartExpiryDialogFragment createCartExpiryDialog = CartExpiryDialogFragment.createCartExpiryDialog(wishSavedForLaterProduct);
                    if (createCartExpiryDialog != null) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CART_COUNTER);
                        SaveForLaterItemView.this.mFragment.withActivity(new ActivityTask<CartActivity>() {
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
    public void showProduct(final WishSavedForLaterProduct wishSavedForLaterProduct) {
        this.mFragment.withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                Intent intent = new Intent();
                intent.setClass(cartActivity, ProductDetailsActivity.class);
                ProductDetailsActivity.addInitialProduct(intent, new WishProduct(wishSavedForLaterProduct.getProductId()));
                cartActivity.startActivity(intent);
            }
        });
    }

    public void releaseImages() {
        if (this.mItemImageView != null) {
            this.mItemImageView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mItemImageView != null) {
            this.mItemImageView.restoreImages();
        }
    }
}
