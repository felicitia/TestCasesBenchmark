package com.contextlogic.wish.activity.signup.SignupFreeGift;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.cart.shipping.ShippingAddressFormView;
import com.contextlogic.wish.activity.cart.shipping.ShippingAddressFormView.EntryCompletedCallback;
import com.contextlogic.wish.analytics.CommerceLogger;
import com.contextlogic.wish.analytics.CommerceLogger.Action;
import com.contextlogic.wish.analytics.CommerceLogger.Result;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.api.model.WishLocalizedCurrencyValue;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.KeyboardUtil;
import com.contextlogic.wish.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class SignupFreeGiftShippingView extends SignupFreeGiftUiView {
    private boolean mAutoCheckoutOnCompletion;
    /* access modifiers changed from: private */
    public CheckBox mBillingCheckmark;
    private View mBillingCheckmarkLayout;
    private TextView mCancelButton;
    private TextView mFloatingDoneButton;
    private View mFloatingDoneButtonContainer;
    private TextView mInlineDoneButton;
    private NetworkImageView mProductImageView;
    /* access modifiers changed from: private */
    public ShippingAddressFormView mShippingAddressFormView;

    public void refreshUi() {
    }

    public SignupFreeGiftShippingView(SignupFreeGiftFragment signupFreeGiftFragment, SignupFreeGiftActivity signupFreeGiftActivity, Bundle bundle, boolean z) {
        super(signupFreeGiftFragment, signupFreeGiftActivity, bundle);
        if (bundle != null) {
            this.mAutoCheckoutOnCompletion = bundle.getBoolean("SavedStateAutoCheckoutOnCompletion");
        } else {
            this.mAutoCheckoutOnCompletion = z;
        }
        if (this.mAutoCheckoutOnCompletion) {
            this.mFloatingDoneButton.setText(WishApplication.getInstance().getResources().getString(R.string.done));
            this.mInlineDoneButton.setText(WishApplication.getInstance().getResources().getString(R.string.done));
            return;
        }
        this.mFloatingDoneButton.setText(WishApplication.getInstance().getResources().getString(R.string.next));
        this.mInlineDoneButton.setText(WishApplication.getInstance().getResources().getString(R.string.next));
    }

    /* access modifiers changed from: protected */
    public void initializeUi(Bundle bundle) {
        View view;
        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.IMPRESSION_FIRST_FREE_GIFT_SHIPPING);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            view = layoutInflater.inflate(R.layout.signup_free_gift_shipping_view_redesign, this);
            this.mShippingAddressFormView = (ShippingAddressFormView) view.findViewById(R.id.signup_free_gift_shipping_view_shipping_form_view);
        } else {
            view = layoutInflater.inflate(R.layout.signup_free_gift_shipping_view, this);
            this.mShippingAddressFormView = (ShippingAddressFormView) view.findViewById(R.id.signup_free_gift_shipping_view_shipping_form_view);
        }
        this.mShippingAddressFormView.setEntryCompletedCallback(new EntryCompletedCallback() {
            public void onEntryCompletion() {
                SignupFreeGiftShippingView.this.handleDone();
            }
        });
        this.mCancelButton = (TextView) view.findViewById(R.id.signup_free_gift_shipping_view_cancel);
        if (!ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView()) {
            this.mCancelButton.setPaintFlags(this.mCancelButton.getPaintFlags() | 8);
        }
        this.mCancelButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_SHIPPING_ABANDONMENT_MODAL);
                SignupFreeGiftShippingView.this.getFreeGiftFragment().withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                        signupFreeGiftServiceFragment.showFreeGiftAbandonDialog(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_SHIPPING_ABANDONMENT_MODAL_RETURN, WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_SHIPPING_ABANDONMENT_MODAL_PROCEED);
                    }
                });
            }
        });
        this.mBillingCheckmarkLayout = view.findViewById(R.id.signup_free_gift_shipping_view_use_billing_layout);
        this.mBillingCheckmark = (CheckBox) view.findViewById(R.id.signup_free_gift_shipping_view_use_billing_checkbox);
        if (!getFreeGiftFragment().getCartContext().getRequiresFullBillingAddress() || getFreeGiftFragment().getCartContext().getBillingAddress() == null) {
            this.mBillingCheckmarkLayout.setVisibility(8);
        } else {
            this.mBillingCheckmarkLayout.setVisibility(0);
            this.mBillingCheckmarkLayout.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    SignupFreeGiftShippingView.this.mBillingCheckmark.toggle();
                }
            });
            this.mBillingCheckmark.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        WishShippingInfo billingAddress = SignupFreeGiftShippingView.this.getFreeGiftFragment().getCartContext().getBillingAddress();
                        if (billingAddress != null) {
                            SignupFreeGiftShippingView.this.mShippingAddressFormView.prefillAddress(billingAddress);
                        }
                    }
                }
            });
        }
        this.mFloatingDoneButton = (TextView) view.findViewById(R.id.signup_free_gift_shipping_view_floating_done_button);
        this.mFloatingDoneButtonContainer = view.findViewById(R.id.signup_free_gift_shipping_view_floating_done_button_container);
        this.mInlineDoneButton = (TextView) view.findViewById(R.id.signup_free_gift_shipping_view_inline_done_button);
        this.mFloatingDoneButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignupFreeGiftShippingView.this.handleDone();
            }
        });
        this.mInlineDoneButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignupFreeGiftShippingView.this.handleDone();
            }
        });
        getFreeGiftFragment().withActivity(new ActivityTask<SignupFreeGiftActivity>() {
            public void performTask(SignupFreeGiftActivity signupFreeGiftActivity) {
                SignupFreeGiftShippingView.this.updateDoneButtonVisibility(signupFreeGiftActivity.isKeyboardVisible());
            }
        });
        WishCartItem wishCartItem = (WishCartItem) getFreeGiftFragment().getCartContext().getCart().getItems().get(0);
        this.mProductImageView = (NetworkImageView) view.findViewById(R.id.signup_free_gift_shipping_view_product_image);
        this.mProductImageView.setImage(wishCartItem.getImage());
        ((ThemedTextView) view.findViewById(R.id.signup_free_gift_shipping_view_where_to_ship_textview)).setText(getFreeGiftFragment().getWishSignupFreeGiftCart().getWhereToShipTitle());
        ThemedTextView themedTextView = (ThemedTextView) view.findViewById(R.id.signup_free_gift_shipping_view_price_main_text);
        ThemedTextView themedTextView2 = (ThemedTextView) view.findViewById(R.id.signup_free_gift_shipping_view_price_sub_text);
        if (ExperimentDataCenter.getInstance().shouldShowV2ShippingCostUpFront()) {
            themedTextView2.setVisibility(8);
            themedTextView.setText(getFreeGiftFragment().getWishSignupFreeGiftCart().getFreeGiftShippingText());
        } else {
            themedTextView2.setPaintFlags(themedTextView2.getPaintFlags() | 16);
            WishLocalizedCurrencyValue retailPrice = wishCartItem.getRetailPrice();
            if (retailPrice.getValue() > wishCartItem.getProductSubtotal().getValue()) {
                themedTextView2.setVisibility(0);
                if (retailPrice.getValue() > 0.0d) {
                    themedTextView2.setText(retailPrice.toFormattedString());
                } else {
                    themedTextView2.setText(getContext().getString(R.string.free));
                }
            } else {
                themedTextView2.setVisibility(8);
            }
            if (wishCartItem.getProductSubtotal().getValue() > 0.0d) {
                themedTextView.setText(getFreeGiftFragment().getCartContext().getCart().getSubtotal().toFormattedString());
            } else {
                themedTextView.setText(getContext().getString(R.string.free));
            }
        }
        if (bundle != null) {
            WishShippingInfo wishShippingInfo = (WishShippingInfo) StateStoreCache.getInstance().getParcelable(bundle, "SavedStateEnteredData", WishShippingInfo.class);
            if (wishShippingInfo != null) {
                this.mShippingAddressFormView.prefillAddress(wishShippingInfo);
                return;
            }
            return;
        }
        this.mShippingAddressFormView.prefillNameFromProfile();
        this.mShippingAddressFormView.populateCountry("US");
    }

    /* access modifiers changed from: private */
    public void handleDone() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_FREE_GIFT_SHIPPING_NEXT_BUTTON);
        ArrayList missingFields = this.mShippingAddressFormView.getMissingFields();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT);
        if (!missingFields.isEmpty()) {
            HashMap hashMap = new HashMap();
            hashMap.put("affected_fields", StringUtil.join(missingFields, ","));
            CommerceLogger.logError(Action.NATIVE_SAVE_SHIPPING_INFO, Result.MISSING_FIELDS, hashMap);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT_FAILURE);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_FREE_GIFT_SHIPPING_ERROR_MODAL);
            getFreeGiftFragment().withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
                public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                    signupFreeGiftServiceFragment.showErrorMessage(WishApplication.getInstance().getResources().getString(R.string.please_provide_information_in_all_required_fields));
                }
            });
            return;
        }
        getFreeGiftFragment().withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                KeyboardUtil.hideKeyboard((Activity) baseActivity);
                signupFreeGiftServiceFragment.saveEnteredShippingAddress(SignupFreeGiftShippingView.this.mShippingAddressFormView.getEnteredShippingAddress());
            }
        });
    }

    public void onKeyboardVisiblityChanged(boolean z) {
        super.onKeyboardVisiblityChanged(z);
        updateDoneButtonVisibility(z);
    }

    /* access modifiers changed from: private */
    public void updateDoneButtonVisibility(boolean z) {
        if (ExperimentDataCenter.getInstance().shouldSeeNewFreeGiftView() && !ExperimentDataCenter.getInstance().shouldSeeNewFloatingButton()) {
            this.mFloatingDoneButtonContainer.setVisibility(8);
            this.mInlineDoneButton.setVisibility(0);
        } else if (z) {
            this.mFloatingDoneButtonContainer.setVisibility(8);
            this.mInlineDoneButton.setVisibility(0);
        } else {
            this.mFloatingDoneButtonContainer.setVisibility(0);
            this.mInlineDoneButton.setVisibility(4);
        }
    }

    public boolean onBackPressed() {
        getFreeGiftFragment().withServiceFragment(new ServiceTask<BaseActivity, SignupFreeGiftServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFreeGiftServiceFragment signupFreeGiftServiceFragment) {
                signupFreeGiftServiceFragment.confirmShippingViewClosing();
            }
        });
        return true;
    }

    public void releaseImages() {
        if (this.mProductImageView != null) {
            this.mProductImageView.releaseImages();
        }
    }

    public void restoreImages() {
        if (this.mProductImageView != null) {
            this.mProductImageView.restoreImages();
        }
    }

    public void recycle() {
        if (this.mProductImageView != null) {
            this.mProductImageView.releaseImages();
            this.mProductImageView.setImage(null);
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("SavedStateAutoCheckoutOnCompletion", this.mAutoCheckoutOnCompletion);
        if (this.mShippingAddressFormView != null) {
            bundle.putString("SavedStateEnteredData", StateStoreCache.getInstance().storeParcelable(this.mShippingAddressFormView.getEnteredShippingAddress()));
        }
    }
}
