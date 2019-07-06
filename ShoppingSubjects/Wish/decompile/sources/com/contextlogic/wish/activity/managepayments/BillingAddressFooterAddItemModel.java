package com.contextlogic.wish.activity.managepayments;

import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.databinding.AddEditPaymentsAddressFooterAddCellBinding;
import com.contextlogic.wish.ui.recyclerview.BindingHolder;
import com.contextlogic.wish.ui.recyclerview.adapter.BindingItemModel;

public class BillingAddressFooterAddItemModel extends BindingItemModel<AddEditPaymentsAddressFooterAddCellBinding> {
    /* access modifiers changed from: private */
    public Callback mCallback;

    public interface Callback {
        void onAddAddressClicked();
    }

    public int getLayoutResId() {
        return R.layout.add_edit_payments_address_footer_add_cell;
    }

    public void onViewRecycled(BindingHolder<AddEditPaymentsAddressFooterAddCellBinding> bindingHolder) {
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void onBindViewHolder(BindingHolder<AddEditPaymentsAddressFooterAddCellBinding> bindingHolder) {
        ((AddEditPaymentsAddressFooterAddCellBinding) bindingHolder.getBinding()).paymentAddAddressText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (BillingAddressFooterAddItemModel.this.mCallback != null) {
                    BillingAddressFooterAddItemModel.this.mCallback.onAddAddressClicked();
                }
            }
        });
    }
}
