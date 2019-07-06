package com.etsy.android.ui.cart.viewholders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroup;
import com.etsy.android.ui.cart.MultiShopCartGroupAdapter;
import com.etsy.android.ui.cart.e;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class VerticalCartGroupViewHolder extends BaseViewHolder<CartGroup> {
    private final MultiShopCartGroupAdapter mAdapter;
    private final RecyclerView mRecyclerView = ((RecyclerView) this.itemView);

    public VerticalCartGroupViewHolder(ViewGroup viewGroup, e eVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_vert_cart_group, viewGroup, false));
        this.mRecyclerView.setRecycledViewPool(eVar.e());
        this.mRecyclerView.setNestedScrollingEnabled(false);
        this.mRecyclerView.setDescendantFocusability(131072);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(viewGroup.getContext()));
        this.mAdapter = new MultiShopCartGroupAdapter(eVar);
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    public void bind(CartGroup cartGroup) {
        ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).setInitialPrefetchItemCount(cartGroup.getItems().size() + cartGroup.getPaymentItems().size());
        this.mAdapter.setCartGroup(cartGroup);
    }
}
