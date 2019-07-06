package com.contextlogic.wish.activity.cart.shipping;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.cart.CartFragment;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.activity.cart.CartUiView;
import com.contextlogic.wish.activity.cart.shipping.ShippingAddressFormView.EntryCompletedCallback;
import com.contextlogic.wish.analytics.CommerceLogger;
import com.contextlogic.wish.analytics.CommerceLogger.Action;
import com.contextlogic.wish.analytics.CommerceLogger.Result;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.util.KeyboardUtil;
import com.contextlogic.wish.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartShippingView extends CartUiView {
    /* access modifiers changed from: private */
    public boolean mAddAddress;
    /* access modifiers changed from: private */
    public boolean mAutoCheckoutOnCompletion;
    private TextView mFloatingDoneButton;
    private View mFloatingDoneButtonContainer;
    private TextView mInlineDoneButton;
    /* access modifiers changed from: private */
    public WishShippingInfo mProvidedWishShippingInfo;
    private TextView mRequiredFeildsIndicator;
    /* access modifiers changed from: private */
    public ShippingAddressFormView mShippingAddressFormView;

    public void recycle() {
    }

    public void refreshUi() {
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public CartShippingView(CartFragment cartFragment, CartActivity cartActivity, Bundle bundle, boolean z, boolean z2, WishShippingInfo wishShippingInfo) {
        super(cartFragment, cartActivity, bundle);
        this.mProvidedWishShippingInfo = wishShippingInfo;
        if (bundle != null) {
            this.mAutoCheckoutOnCompletion = bundle.getBoolean("SavedStateAutoCheckoutOnCompletion");
        } else {
            this.mAutoCheckoutOnCompletion = z;
        }
        this.mAddAddress = z2;
    }

    public void initializeUi(Bundle bundle) {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.cart_fragment_cart_shipping, this);
        this.mRequiredFeildsIndicator = (TextView) inflate.findViewById(R.id.required_fields_indicator);
        this.mShippingAddressFormView = (ShippingAddressFormView) inflate.findViewById(R.id.cart_fragment_cart_shipping_shipping_form_view);
        this.mRequiredFeildsIndicator.setVisibility(8);
        this.mShippingAddressFormView.setVisibility(0);
        this.mShippingAddressFormView.setEntryCompletedCallback(new EntryCompletedCallback() {
            public void onEntryCompletion() {
                CartShippingView.this.handleDone();
            }
        });
        this.mFloatingDoneButton = (TextView) inflate.findViewById(R.id.cart_fragment_cart_shipping_floating_done_button);
        this.mFloatingDoneButtonContainer = inflate.findViewById(R.id.cart_fragment_cart_shipping_floating_done_button_container);
        this.mInlineDoneButton = (TextView) inflate.findViewById(R.id.cart_fragment_cart_shipping_inline_done_button);
        this.mFloatingDoneButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartShippingView.this.handleDone();
            }
        });
        this.mInlineDoneButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartShippingView.this.handleDone();
            }
        });
        getCartFragment().withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                CartShippingView.this.updateDoneButtonVisibility(cartActivity.isKeyboardVisible());
            }
        });
        if (bundle != null) {
            this.mProvidedWishShippingInfo = (WishShippingInfo) StateStoreCache.getInstance().getParcelable(bundle, "SavedStateProvidedShippingInfo", WishShippingInfo.class);
            WishShippingInfo wishShippingInfo = (WishShippingInfo) StateStoreCache.getInstance().getParcelable(bundle, "SavedStateEnteredData", WishShippingInfo.class);
            if (wishShippingInfo != null) {
                this.mShippingAddressFormView.prefillAddress(wishShippingInfo);
            }
        } else if (this.mProvidedWishShippingInfo != null) {
            this.mShippingAddressFormView.prefillAddress(this.mProvidedWishShippingInfo);
        } else {
            this.mShippingAddressFormView.prefillNameFromProfile();
            if (ProfileDataCenter.getInstance().getCountryCode() != null) {
                this.mShippingAddressFormView.populateCountry(ProfileDataCenter.getInstance().getCountryCode());
            } else {
                this.mShippingAddressFormView.populateCountry("US");
            }
        }
        if (this.mAutoCheckoutOnCompletion) {
            this.mFloatingDoneButton.setText(WishApplication.getInstance().getResources().getString(R.string.done));
            this.mInlineDoneButton.setText(WishApplication.getInstance().getResources().getString(R.string.done));
            return;
        }
        this.mFloatingDoneButton.setText(WishApplication.getInstance().getResources().getString(R.string.save_info));
        this.mInlineDoneButton.setText(WishApplication.getInstance().getResources().getString(R.string.save_info));
    }

    public void onKeyboardVisiblityChanged(boolean z) {
        super.onKeyboardVisiblityChanged(z);
        updateDoneButtonVisibility(z);
    }

    /* access modifiers changed from: private */
    public void updateDoneButtonVisibility(boolean z) {
        if (z) {
            this.mFloatingDoneButtonContainer.setVisibility(8);
            this.mInlineDoneButton.setVisibility(0);
            return;
        }
        this.mFloatingDoneButtonContainer.setVisibility(0);
        this.mInlineDoneButton.setVisibility(4);
    }

    public boolean onBackPressed() {
        getCartFragment().withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                WishShippingInfo enteredShippingAddress = CartShippingView.this.mShippingAddressFormView.getEnteredShippingAddress();
                if (CartShippingView.this.mProvidedWishShippingInfo != null) {
                    enteredShippingAddress.setId(CartShippingView.this.mProvidedWishShippingInfo.getId());
                }
                cartServiceFragment.confirmShippingViewClosing(CartShippingView.this.mProvidedWishShippingInfo != null && CartShippingView.this.mProvidedWishShippingInfo.equals(enteredShippingAddress));
            }
        });
        return true;
    }

    public List<WishAnalyticsEvent> getWishAnalyticImpressionEvents() {
        ArrayList arrayList = new ArrayList();
        if (this.mAddAddress) {
            arrayList.add(WishAnalyticsEvent.IMPRESSION_MOBILE_ADD_NEW_ADDRESS_FORM);
        } else {
            arrayList.add(WishAnalyticsEvent.IMPRESSION_MOBILE_EDIT_ADDRESS_FORM);
        }
        arrayList.add(WishAnalyticsEvent.IMPRESSION_MOBILE_NATIVE_SHIPPING);
        return arrayList;
    }

    public void updateActionBar() {
        getCartFragment().withActivity(new ActivityTask<CartActivity>() {
            public void performTask(CartActivity cartActivity) {
                cartActivity.getActionBarManager().setTitle(WishApplication.getInstance().getResources().getString(R.string.add_new_address));
                cartActivity.getActionBarManager().setHomeButtonMode(HomeButtonMode.BACK_ARROW);
            }
        });
    }

    public void handleSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("SavedStateAutoCheckoutOnCompletion", this.mAutoCheckoutOnCompletion);
        if (this.mShippingAddressFormView != null) {
            bundle.putString("SavedStateEnteredData", StateStoreCache.getInstance().storeParcelable(this.mShippingAddressFormView.getEnteredShippingAddress()));
        }
        if (this.mProvidedWishShippingInfo != null) {
            bundle.putString("SavedStateProvidedShippingInfo", StateStoreCache.getInstance().storeParcelable(this.mProvidedWishShippingInfo));
        }
    }

    /* access modifiers changed from: private */
    public void handleDone() {
        ArrayList missingFields = this.mShippingAddressFormView.getMissingFields();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT);
        if (!missingFields.isEmpty()) {
            HashMap hashMap = new HashMap();
            hashMap.put("affected_fields", StringUtil.join(missingFields, ","));
            CommerceLogger.logError(Action.NATIVE_SAVE_SHIPPING_INFO, Result.MISSING_FIELDS, hashMap);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT_FAILURE);
            getCartFragment().withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
                public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                    cartServiceFragment.showErrorMessage(WishApplication.getInstance().getResources().getString(R.string.please_provide_information_in_all_required_fields));
                }
            });
            return;
        }
        getCartFragment().withServiceFragment(new ServiceTask<BaseActivity, CartServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CartServiceFragment cartServiceFragment) {
                KeyboardUtil.hideKeyboard((Activity) baseActivity);
                WishShippingInfo enteredShippingAddress = CartShippingView.this.mShippingAddressFormView.getEnteredShippingAddress();
                if (CartShippingView.this.mProvidedWishShippingInfo != null) {
                    enteredShippingAddress.setId(CartShippingView.this.mProvidedWishShippingInfo.getId());
                }
                if (CartShippingView.this.mAddAddress) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SAVE_NEW_ADDRESS_BUTTON);
                } else {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SAVE_EDITED_ADDRESS);
                }
                cartServiceFragment.saveEnteredShippingAddress(enteredShippingAddress, CartShippingView.this.mAutoCheckoutOnCompletion, CartShippingView.this.mAddAddress);
            }
        });
    }
}
