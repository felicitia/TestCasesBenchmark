package com.contextlogic.wish.activity.cart.commercecash;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.activity.cart.items.CartItemsAdapter;
import com.contextlogic.wish.api.model.WishCommerceCashCart;
import com.contextlogic.wish.payments.CartContext;

public class CommerceCashCartItemsAdapter extends CartItemsAdapter {
    public CommerceCashCartItemsAdapter(Context context, CommerceCashCartItemsView commerceCashCartItemsView, CartContext cartContext) {
        super(context, commerceCashCartItemsView, cartContext);
    }

    public int getCount() {
        return getCartContext().getCommerceCashCart() != null ? 1 : 0;
    }

    public Object getItem(int i) {
        return getCartContext().getCommerceCashCart();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        CommerceCashCartItemView commerceCashCartItemView = (CommerceCashCartItemView) view;
        if (commerceCashCartItemView == null) {
            commerceCashCartItemView = new CommerceCashCartItemView(getContext(), getCartContext());
        }
        commerceCashCartItemView.setItem((WishCommerceCashCart) getItem(i));
        return commerceCashCartItemView;
    }
}
