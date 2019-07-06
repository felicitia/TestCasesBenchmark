package com.contextlogic.wish.activity.cart;

import android.content.Intent;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext.CartType;
import com.contextlogic.wish.util.IntentUtil;
import java.util.HashMap;

public class CartActivity extends DrawerActivity {
    public static String EXTRA_ADD_TO_CART_OFFER_ID = "ExtraAddToCartOfferId";
    public static String EXTRA_ADD_TO_CART_PRODUCT_ID = "ExtraAddToCartProductId";
    public static String EXTRA_ADD_TO_CART_QUANTITY = "ExtraAddToCartQuantity";
    public static String EXTRA_ADD_TO_CART_SHIPPING_OPTION_ID = "ExtraAddToCartShippingOptionId";
    public static String EXTRA_ADD_TO_CART_VARIATION_ID = "ExtraAddToCartVariationId";
    public static String EXTRA_CHOSE_PAYPAL_FROM_KLARNA = "ExtraChosePaypalFromKlarna";
    public static String EXTRA_IS_EXPRESS_CHECKOUT = "ExtraIsExpressCheckout";
    public static String EXTRA_IS_OPEN_FROM_MENU = "ExtraIsOpenFromMenu";
    public static String EXTRA_RETURNING_FROM_CART = "ExtraReturningFromCart";
    public static String EXTRA_SHOW_CART_ERROR = "ExtraShowCartError";
    public static String EXTRA_START_ON_ADDRESS_BOOK = "ExtraStartOnShippingview";

    public boolean canHaveActionBar() {
        return true;
    }

    public boolean canShowDailyGiveawayNotification() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean requiresNoInterruption() {
        return true;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new CartServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new CartFragment();
    }

    public String getMenuKey() {
        return MenuFragment.MENU_KEY_CART;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.CART;
    }

    public String getAddToCartProductId() {
        return getIntent().getStringExtra(EXTRA_ADD_TO_CART_PRODUCT_ID);
    }

    public String getAddToCartVariationId() {
        return getIntent().getStringExtra(EXTRA_ADD_TO_CART_VARIATION_ID);
    }

    public String getAddToCartShippingOptionId() {
        return getIntent().getStringExtra(EXTRA_ADD_TO_CART_SHIPPING_OPTION_ID);
    }

    public int getAddToCartQuantity() {
        return getIntent().getIntExtra(EXTRA_ADD_TO_CART_QUANTITY, 1);
    }

    public String getAddToCartOfferId() {
        return getIntent().getStringExtra(EXTRA_ADD_TO_CART_OFFER_ID);
    }

    public boolean isExpressCheckout() {
        return getIntent().getBooleanExtra(EXTRA_IS_EXPRESS_CHECKOUT, false);
    }

    public boolean chosePayPalFromKlarna() {
        return getIntent() != null && getIntent().getBooleanExtra(EXTRA_CHOSE_PAYPAL_FROM_KLARNA, false);
    }

    public boolean showCartError() {
        return getIntent() != null && getIntent().getBooleanExtra(EXTRA_SHOW_CART_ERROR, false);
    }

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        actionBarManager.setTitle(WishApplication.getInstance().getResources().getString(R.string.cart));
        setHomeButtonToDefault();
    }

    public void onActivityHandlingBackPress() {
        super.onActivityHandlingBackPress();
        if (!shouldStartInAddressBook()) {
            HashMap hashMap = new HashMap();
            hashMap.put("cart_type", CartType.COMMERCE_GOODS.toString());
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CART_CLOSE, hashMap);
        }
    }

    public void onBackPressed() {
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_RETURNING_FROM_CART, true);
            setResult(-1, intent);
        }
        super.onBackPressed();
    }

    public boolean shouldStartInAddressBook() {
        return getIntent().getBooleanExtra(EXTRA_START_ON_ADDRESS_BOOK, false);
    }

    public void setHomeButtonToDefault() {
        if (getIntent().getExtras() == null || getIntent().getExtras().isEmpty() || !getIntent().getExtras().getBoolean(EXTRA_IS_OPEN_FROM_MENU) || ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
            getActionBarManager().setHomeButtonMode(HomeButtonMode.X_ICON);
        } else {
            getActionBarManager().setHomeButtonMode(HomeButtonMode.MENU_INDICATOR);
        }
    }

    /* access modifiers changed from: protected */
    public ActivityAnimationTypes getDefaultActivityAnimation() {
        return ActivityAnimationTypes.SLIDING;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (IntentUtil.safeToUnparcel(intent) && intent.getBooleanExtra("ExtraRequiresReload", false)) {
            ((CartFragment) getUiFragment("FragmentTagMainContent")).handleReload();
        }
    }
}
