package com.etsy.android.ui.cart.viewholders;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.SimpleKVPMoneyItem;
import com.etsy.android.vespa.b;

public class PaymentTotalsLineItemViewHolder extends BaseCartGroupItemViewHolder {
    private final TextView mTitle;
    private final TextView mValue;

    public PaymentTotalsLineItemViewHolder(ViewGroup viewGroup, b bVar, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        int i2 = i == R.id.view_type_grand_total_line_item ? R.layout.list_item_msco_totals_line_grand_item : i == R.id.view_type_discount_total_line_item ? R.layout.list_item_msco_totals_line_discount_item : R.layout.list_item_msco_totals_line_item;
        super(from.inflate(i2, viewGroup, false));
        this.mTitle = (TextView) findViewById(R.id.txt_title);
        this.mValue = (TextView) findViewById(R.id.txt_value);
    }

    public void bindCartGroupItem(CartGroupItem cartGroupItem) {
        SimpleKVPMoneyItem simpleKVPMoneyItem = (SimpleKVPMoneyItem) cartGroupItem.getData();
        this.mTitle.setText(simpleKVPMoneyItem.getTitle());
        if (simpleKVPMoneyItem.getFormattedMoney() != null) {
            this.mValue.setText(simpleKVPMoneyItem.getFormattedMoney().toString());
        } else if (simpleKVPMoneyItem.getMoney() != null) {
            this.mValue.setText(simpleKVPMoneyItem.getMoney().toString());
        }
    }
}
