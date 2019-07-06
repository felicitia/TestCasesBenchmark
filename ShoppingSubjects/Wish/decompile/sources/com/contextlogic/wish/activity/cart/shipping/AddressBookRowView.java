package com.contextlogic.wish.activity.cart.shipping;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.cart.shipping.AddressBookAdapter.AddressBookAdapterCallback;
import com.contextlogic.wish.activity.cart.shipping.AddressBookAdapter.AddressBookType;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.util.AddressUtil;
import com.contextlogic.wish.util.DrawableUtil;

public class AddressBookRowView extends ConstraintLayout {
    private AddressBookAdapterCallback mCallback;
    private TextView mCityAndState;
    private TextView mCountry;
    private TextView mDelete;
    private TextView mEdit;
    private TextView mFullName;
    private ImageView mLocationTag;
    private TextView mOneLineAddress;
    private TextView mPrimaryAddress;
    private RadioButton mRadioButton;
    private TextView mZip;

    public AddressBookRowView(Context context) {
        this(context, null);
    }

    public AddressBookRowView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AddressBookRowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.cart_fragment_address_book_entry, this, true);
        this.mRadioButton = (RadioButton) findViewById(R.id.address_book_radio_button);
        this.mLocationTag = (ImageView) findViewById(R.id.address_book_location_tag);
        this.mPrimaryAddress = (TextView) findViewById(R.id.address_book_primary_address_indicator);
        this.mFullName = (TextView) findViewById(R.id.address_book_entry_full_name);
        this.mOneLineAddress = (TextView) findViewById(R.id.address_book_one_line_address);
        this.mCityAndState = (TextView) findViewById(R.id.address_book_city_and_state);
        this.mCountry = (TextView) findViewById(R.id.address_book_country);
        this.mZip = (TextView) findViewById(R.id.address_book_zipcode);
        this.mEdit = (TextView) findViewById(R.id.address_book_edit_address);
        this.mDelete = (TextView) findViewById(R.id.address_book_delete_address);
        DrawableUtil.tintCompoundDrawables(this.mEdit, getResources().getColor(R.color.main_primary));
        DrawableUtil.tintCompoundDrawables(this.mDelete, getResources().getColor(R.color.main_primary));
    }

    public void setup(final WishShippingInfo wishShippingInfo, AddressBookType addressBookType, AddressBookAdapterCallback addressBookAdapterCallback) {
        this.mCallback = addressBookAdapterCallback;
        this.mFullName.setText(wishShippingInfo.getName());
        this.mOneLineAddress.setText(wishShippingInfo.getStreetAddressLineOne());
        this.mCityAndState.setText(wishShippingInfo.getCityAndState());
        this.mCountry.setText(AddressUtil.getCountryName(wishShippingInfo.getCountryCode()));
        this.mZip.setText(wishShippingInfo.getZipCode());
        if (addressBookType == AddressBookType.cart || addressBookType == AddressBookType.standalone) {
            this.mDelete.setVisibility(8);
            this.mLocationTag.setVisibility(8);
            this.mRadioButton.setVisibility(0);
            this.mRadioButton.setClickable(false);
            this.mEdit.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AddressBookRowView.this.editAddress(wishShippingInfo);
                }
            });
            setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AddressBookRowView.this.selectItem(wishShippingInfo);
                }
            });
        } else if (addressBookType == AddressBookType.rewardConfirmation) {
            this.mDelete.setVisibility(8);
            this.mLocationTag.setVisibility(8);
            this.mRadioButton.setVisibility(8);
        } else {
            this.mDelete.setVisibility(0);
            this.mLocationTag.setVisibility(0);
            this.mRadioButton.setVisibility(8);
            this.mEdit.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AddressBookRowView.this.editAddress(wishShippingInfo);
                }
            });
            this.mDelete.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AddressBookRowView.this.deleteAddress(wishShippingInfo);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void selectItem(WishShippingInfo wishShippingInfo) {
        if (this.mRadioButton != null && !this.mRadioButton.isChecked()) {
            setAddress(wishShippingInfo);
        }
    }

    private void setAddress(WishShippingInfo wishShippingInfo) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_USE_THIS_ADDRESS_BUTTON);
        if (this.mCallback != null) {
            this.mCallback.setAddress(wishShippingInfo);
        }
    }

    /* access modifiers changed from: private */
    public void editAddress(WishShippingInfo wishShippingInfo) {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_ADDRESS_CELL_EDIT_BUTTON);
        if (this.mCallback != null) {
            this.mCallback.editAddress(wishShippingInfo);
        }
    }

    /* access modifiers changed from: private */
    public void deleteAddress(WishShippingInfo wishShippingInfo) {
        if (this.mCallback != null) {
            this.mCallback.deleteAddress(wishShippingInfo);
        }
    }

    public void setChecked(boolean z) {
        this.mPrimaryAddress.setVisibility(z ? 0 : 8);
        this.mRadioButton.setChecked(z);
    }
}
