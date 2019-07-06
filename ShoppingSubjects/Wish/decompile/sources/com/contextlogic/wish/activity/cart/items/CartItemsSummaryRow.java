package com.contextlogic.wish.activity.cart.items;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCartSummaryItem;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class CartItemsSummaryRow extends LinearLayout {
    public CartItemsSummaryRow(Context context, CartContext cartContext, WishCartSummaryItem wishCartSummaryItem, boolean z) {
        super(context);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.cart_fragment_cart_items_summary_row, this);
        setOrientation(1);
        ThemedTextView themedTextView = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_cart_items_summary_row_name);
        ThemedTextView themedTextView2 = (ThemedTextView) inflate.findViewById(R.id.cart_fragment_cart_items_summary_row_value);
        themedTextView.setText(wishCartSummaryItem.getName());
        themedTextView2.setText(wishCartSummaryItem.getValue());
        if (wishCartSummaryItem.getType() == 4) {
            if (z) {
                themedTextView2.setTypeface(1);
                themedTextView.setTypeface(1);
                themedTextView2.setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.text_size_small_title));
                themedTextView.setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.text_size_small_title));
            }
            TextView textView = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_summary_row_subtext_1);
            TextView textView2 = (TextView) inflate.findViewById(R.id.cart_fragment_cart_items_summary_row_subtext_2);
            if (ExperimentDataCenter.getInstance().shouldUsePsuedoLocalizedCurrency() && cartContext.getTotal().isLocalized()) {
                textView.setText(String.format(getContext().getString(R.string.approx_value), new Object[]{cartContext.getTotal().toFormattedString(false, false)}));
                textView.setVisibility(0);
            }
            if (cartContext.getCart() != null && cartContext.getCart().getTaxText() != null) {
                textView2.setText(cartContext.getCart().getTaxText());
                textView2.setVisibility(0);
            } else if (!(cartContext.getCommerceCashCart() == null || cartContext.getCommerceCashCart().getTaxText() == null)) {
                textView2.setText(cartContext.getCommerceCashCart().getTaxText());
                textView2.setVisibility(0);
            }
        }
        themedTextView.setTextColor(wishCartSummaryItem.getDisplayColor());
        themedTextView2.setTextColor(wishCartSummaryItem.getDisplayColor());
        if (wishCartSummaryItem.shouldShowIcon() && wishCartSummaryItem.getIcon() != null) {
            Drawable icon = wishCartSummaryItem.getIcon();
            icon.setBounds(0, 0, getResources().getDimensionPixelSize(R.dimen.cart_summary_item_icon_size), getResources().getDimensionPixelSize(R.dimen.cart_summary_item_icon_size));
            themedTextView2.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.four_padding));
            themedTextView2.setCompoundDrawables(icon, null, null, null);
        }
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.cart_fragment_cart_items_summary_row_name_container);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.setMargins(0, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.eight_padding), 0, 0);
        linearLayout.setLayoutParams(layoutParams);
    }
}
