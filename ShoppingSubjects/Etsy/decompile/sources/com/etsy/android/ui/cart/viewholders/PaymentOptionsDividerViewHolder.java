package com.etsy.android.ui.cart.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.SimpleKVPMoneyItem;

public class PaymentOptionsDividerViewHolder extends BaseCartGroupItemViewHolder {
    private final TextView mText = ((TextView) findViewById(R.id.txt_text));

    public PaymentOptionsDividerViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_payment_option_divider, viewGroup, false));
    }

    public void bindCartGroupItem(CartGroupItem cartGroupItem) {
        String text = ((SimpleKVPMoneyItem) cartGroupItem.getData()).getText();
        if (TextUtils.isEmpty(text)) {
            this.mText.setVisibility(4);
            return;
        }
        this.mText.setVisibility(0);
        this.mText.setText(text);
    }
}
