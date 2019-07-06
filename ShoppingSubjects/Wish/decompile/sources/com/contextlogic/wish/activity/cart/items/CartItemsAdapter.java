package com.contextlogic.wish.activity.cart.items;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.BaseAdapter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.payments.CartContext;

public class CartItemsAdapter extends BaseAdapter {
    private CartContext mCartContext;
    private CartItemsView mCartItemsView;
    private Context mContext;
    private boolean mIsHeaderVisible;

    public long getItemId(int i) {
        return 0;
    }

    public CartItemsAdapter(Context context, CartItemsView cartItemsView, CartContext cartContext) {
        this.mContext = context;
        this.mCartItemsView = cartItemsView;
        this.mCartContext = cartContext;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.mContext;
    }

    public int getCount() {
        if (this.mCartContext.getCart() == null || this.mCartContext.getCart().getItems() == null) {
            return 0;
        }
        return this.mCartContext.getCart().getItems().size();
    }

    public Object getItem(int i) {
        return this.mCartContext.getCart().getItems().get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        CartItemView cartItemView = (CartItemView) view;
        if (cartItemView == null) {
            cartItemView = new CartItemView(this.mContext, this.mCartItemsView.getCartFragment());
        }
        cartItemView.setItem((WishCartItem) getItem(i));
        if (i != 0) {
            cartItemView.showTopSeparator();
        } else {
            cartItemView.showHeader(this.mIsHeaderVisible);
        }
        if (ExperimentDataCenter.getInstance().shouldSeeRedesignedCartNotices() && i == getCount() - 1) {
            cartItemView.hideSeperator();
        }
        return cartItemView;
    }

    public void updateCartContext(CartContext cartContext) {
        this.mCartContext = cartContext;
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.mCartItemsView.getCartListView().getLayoutParams();
        this.mCartItemsView.getCheckoutContainer().measure(-1, 0);
        marginLayoutParams.bottomMargin = this.mCartItemsView.getCheckoutContainer().getMeasuredHeight();
        this.mCartItemsView.getCartListView().setLayoutParams(marginLayoutParams);
        notifyDataSetChanged();
    }

    public CartContext getCartContext() {
        return this.mCartContext;
    }

    public void setIsHeaderVisible(boolean z) {
        this.mIsHeaderVisible = z;
    }

    public CartItemsView getCartItemsView() {
        return this.mCartItemsView;
    }
}
