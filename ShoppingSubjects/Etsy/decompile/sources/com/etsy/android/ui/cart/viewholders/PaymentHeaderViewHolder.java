package com.etsy.android.ui.cart.viewholders;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.SimpleKVPMoneyItem;

public class PaymentHeaderViewHolder extends BaseCartGroupItemViewHolder {
    private final TextView mTitle = ((TextView) findViewById(R.id.txt_title));

    public PaymentHeaderViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_payment_header, viewGroup, false));
    }

    public void bindCartGroupItem(CartGroupItem cartGroupItem) {
        this.mTitle.setText(((SimpleKVPMoneyItem) cartGroupItem.getData()).getTitle());
    }
}
