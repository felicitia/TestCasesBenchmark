package com.contextlogic.wish.activity.cart.commerceloan;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.activity.cart.items.CartItemsAdapter;
import com.contextlogic.wish.api.model.WishCommerceLoanCart;
import com.contextlogic.wish.payments.CartContext;

public class CommerceLoanCartItemsAdapter extends CartItemsAdapter {
    public CommerceLoanCartItemsAdapter(Context context, CommerceLoanCartItemsView commerceLoanCartItemsView, CartContext cartContext) {
        super(context, commerceLoanCartItemsView, cartContext);
    }

    public int getCount() {
        return getCartContext().getCommerceLoanCart() != null ? 1 : 0;
    }

    public WishCommerceLoanCart getItem(int i) {
        return getCartContext().getCommerceLoanCart();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        CommerceLoanCartItemView commerceLoanCartItemView = (CommerceLoanCartItemView) view;
        if (commerceLoanCartItemView == null) {
            commerceLoanCartItemView = new CommerceLoanCartItemView(getContext(), getCartContext(), getCartItemsView());
        }
        commerceLoanCartItemView.setItem(getItem(i));
        commerceLoanCartItemView.showHeader();
        return commerceLoanCartItemView;
    }
}
