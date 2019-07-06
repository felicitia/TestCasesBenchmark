package com.contextlogic.wish.activity.managepayments;

import com.contextlogic.wish.R;
import com.contextlogic.wish.databinding.AddEditPaymentsAddressHeaderCellBinding;
import com.contextlogic.wish.ui.recyclerview.BindingHolder;
import com.contextlogic.wish.ui.recyclerview.adapter.BindingItemModel;

public class BillingAddressHeaderItemModel extends BindingItemModel<AddEditPaymentsAddressHeaderCellBinding> {
    public int getLayoutResId() {
        return R.layout.add_edit_payments_address_header_cell;
    }

    public void onBindViewHolder(BindingHolder<AddEditPaymentsAddressHeaderCellBinding> bindingHolder) {
    }

    public void onViewRecycled(BindingHolder<AddEditPaymentsAddressHeaderCellBinding> bindingHolder) {
    }
}
