package com.contextlogic.wish.activity.cart.commercecash;

import android.os.Bundle;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.cart.items.CartItemsAdapter;
import com.contextlogic.wish.activity.cart.items.CartItemsView;

public class CommerceCashCartItemsView extends CartItemsView {
    public boolean isParentView() {
        return false;
    }

    public CommerceCashCartItemsView(CommerceCashCartFragment commerceCashCartFragment, CartActivity cartActivity, Bundle bundle) {
        super(commerceCashCartFragment, cartActivity, bundle);
    }

    /* access modifiers changed from: protected */
    public CartItemsAdapter createAdapter() {
        return new CommerceCashCartItemsAdapter(getContext(), this, getCartFragment().getCartContext());
    }
}
