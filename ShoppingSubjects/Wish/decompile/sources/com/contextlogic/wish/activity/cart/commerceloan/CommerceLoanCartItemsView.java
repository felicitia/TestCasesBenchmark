package com.contextlogic.wish.activity.cart.commerceloan;

import android.os.Bundle;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.cart.items.CartItemsAdapter;
import com.contextlogic.wish.activity.cart.items.CartItemsView;

public class CommerceLoanCartItemsView extends CartItemsView {
    public boolean isParentView() {
        return false;
    }

    public CommerceLoanCartItemsView(CommerceLoanCartFragment commerceLoanCartFragment, CartActivity cartActivity, Bundle bundle) {
        super(commerceLoanCartFragment, cartActivity, bundle);
    }

    /* access modifiers changed from: protected */
    public CartItemsAdapter createAdapter() {
        return new CommerceLoanCartItemsAdapter(getContext(), this, getCartFragment().getCartContext());
    }
}
