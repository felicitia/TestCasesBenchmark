package com.contextlogic.wish.activity.managepayments;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.databinding.AddEditPaymentsAddressCellBinding;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.recyclerview.BindingHolder;
import com.contextlogic.wish.ui.recyclerview.adapter.BindingItemModel;
import com.contextlogic.wish.util.AddressUtil;
import com.contextlogic.wish.util.StringUtil;

public class BillingAddressItemModel extends BindingItemModel<AddEditPaymentsAddressCellBinding> {
    /* access modifiers changed from: private */
    public BillingAddressItemModelCallback mCallback;
    private boolean mIsLastCell = false;
    private boolean mSelectable = false;
    private boolean mSelected = false;
    private WishShippingInfo mWishShippingInfo;

    public interface BillingAddressItemModelCallback {
        void onSelected(BillingAddressItemModel billingAddressItemModel);
    }

    public int getLayoutResId() {
        return R.layout.add_edit_payments_address_cell;
    }

    public void onViewRecycled(BindingHolder<AddEditPaymentsAddressCellBinding> bindingHolder) {
    }

    public BillingAddressItemModel(WishShippingInfo wishShippingInfo) {
        this.mWishShippingInfo = wishShippingInfo;
    }

    public WishShippingInfo getWishShippingInfo() {
        return this.mWishShippingInfo;
    }

    public void setSelectable(boolean z) {
        this.mSelectable = z;
    }

    public void setIsSelected(boolean z) {
        this.mSelected = z;
    }

    public void setIsLastCell(boolean z) {
        this.mIsLastCell = z;
    }

    public void setCallback(BillingAddressItemModelCallback billingAddressItemModelCallback) {
        this.mCallback = billingAddressItemModelCallback;
    }

    public boolean isSelected() {
        return this.mSelectable && this.mSelected;
    }

    public void onBindViewHolder(BindingHolder<AddEditPaymentsAddressCellBinding> bindingHolder) {
        Context context = bindingHolder.itemView.getContext();
        AddEditPaymentsAddressCellBinding addEditPaymentsAddressCellBinding = (AddEditPaymentsAddressCellBinding) bindingHolder.getBinding();
        addEditPaymentsAddressCellBinding.paymentBillingAddressRadioButton.setChecked(this.mSelected);
        int i = 8;
        addEditPaymentsAddressCellBinding.paymentBillingAddressRadioButton.setVisibility(this.mSelectable ? 0 : 8);
        AutoReleasableImageView autoReleasableImageView = addEditPaymentsAddressCellBinding.paymentBillingAddressPin;
        if (!this.mSelectable) {
            i = 0;
        }
        autoReleasableImageView.setVisibility(i);
        if (this.mSelectable) {
            addEditPaymentsAddressCellBinding.paymentBillingAddressText.setLineSpacing(0.0f, 1.0f);
            addEditPaymentsAddressCellBinding.paymentBillingAddressText.setText(getShortAddressText(context));
            addEditPaymentsAddressCellBinding.paymentBillingAddressLayout.setGravity(16);
        } else {
            addEditPaymentsAddressCellBinding.paymentBillingAddressText.setLineSpacing(0.0f, 1.2f);
            addEditPaymentsAddressCellBinding.paymentBillingAddressText.setText(getFullAddressText(context));
            addEditPaymentsAddressCellBinding.paymentBillingAddressLayout.setGravity(48);
        }
        addEditPaymentsAddressCellBinding.paymentBillingAddressBottomDivider.setBackgroundColor(ContextCompat.getColor(context, this.mIsLastCell ? R.color.gray5 : R.color.gray6));
        OnClickListener createSelectClickListener = createSelectClickListener();
        addEditPaymentsAddressCellBinding.getRoot().setOnClickListener(createSelectClickListener);
        addEditPaymentsAddressCellBinding.paymentBillingAddressRadioButton.setOnClickListener(createSelectClickListener);
    }

    private OnClickListener createSelectClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                if (BillingAddressItemModel.this.mCallback != null) {
                    BillingAddressItemModel.this.mCallback.onSelected(BillingAddressItemModel.this);
                }
            }
        };
    }

    private CharSequence getFullAddressText(Context context) {
        return StringUtil.boldSubstring(context.getString(R.string.address_format_full, new Object[]{this.mWishShippingInfo.getName(), AddressUtil.getJoinedAddressLine(context, this.mWishShippingInfo.getStreetAddressLineOne(), this.mWishShippingInfo.getStreetAddressLineTwo()), this.mWishShippingInfo.getCity(), this.mWishShippingInfo.getState(), AddressUtil.getCountryName(this.mWishShippingInfo.getCountryCode()), this.mWishShippingInfo.getZipCode()}), this.mWishShippingInfo.getName());
    }

    private CharSequence getShortAddressText(Context context) {
        return StringUtil.boldSubstring(context.getString(R.string.address_format_short, new Object[]{this.mWishShippingInfo.getName(), AddressUtil.getJoinedAddressLine(context, this.mWishShippingInfo.getStreetAddressLineOne(), this.mWishShippingInfo.getStreetAddressLineTwo()), this.mWishShippingInfo.getZipCode()}), this.mWishShippingInfo.getName());
    }
}
