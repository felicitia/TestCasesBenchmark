package com.contextlogic.wish.activity.cart.shipping;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishShippingInfo;
import java.util.ArrayList;
import java.util.List;

public class AddressBookAdapter extends BaseAdapter {
    private List<WishShippingInfo> mAddressBookEntries;
    private AddressBookAdapterCallback mCallback;
    private Context mContext;
    private int mSelectedAddressPosition = -1;
    private AddressBookType mType;

    public interface AddressBookAdapterCallback {
        void deleteAddress(WishShippingInfo wishShippingInfo);

        void editAddress(WishShippingInfo wishShippingInfo);

        void setAddress(WishShippingInfo wishShippingInfo);
    }

    public enum AddressBookType {
        cart,
        accountSettings,
        rewardConfirmation,
        standalone
    }

    public long getItemId(int i) {
        return 0;
    }

    public AddressBookAdapter(Context context, AddressBookAdapterCallback addressBookAdapterCallback, AddressBookType addressBookType) {
        this.mContext = context;
        this.mCallback = addressBookAdapterCallback;
        this.mType = addressBookType;
        this.mAddressBookEntries = new ArrayList();
    }

    public AddressBookRowView getView(int i, View view, ViewGroup viewGroup) {
        AddressBookRowView addressBookRowView;
        WishShippingInfo item = getItem(i);
        if (view == null) {
            addressBookRowView = new AddressBookRowView(this.mContext);
            addressBookRowView.setBackgroundColor(-1);
            int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.sixteen_padding);
            addressBookRowView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        } else {
            addressBookRowView = (AddressBookRowView) view;
        }
        addressBookRowView.setup(item, this.mType, this.mCallback);
        addressBookRowView.setChecked(this.mSelectedAddressPosition == i);
        return addressBookRowView;
    }

    public void updateAddressBook(List<WishShippingInfo> list, String str) {
        this.mAddressBookEntries = list;
        updateDefaultId(str);
    }

    public void updateDefaultId(String str) {
        for (int i = 0; i < getCount(); i++) {
            if (getItem(i).getId().equals(str)) {
                this.mSelectedAddressPosition = i;
            }
        }
        notifyDataSetChanged();
    }

    public WishShippingInfo getCurrentlySelectedItem() {
        if (getCount() <= 0 || this.mSelectedAddressPosition < 0) {
            return null;
        }
        return getItem(this.mSelectedAddressPosition);
    }

    public int getCount() {
        if (this.mAddressBookEntries == null) {
            return 0;
        }
        return this.mAddressBookEntries.size();
    }

    public WishShippingInfo getItem(int i) {
        return (WishShippingInfo) this.mAddressBookEntries.get(i);
    }
}
