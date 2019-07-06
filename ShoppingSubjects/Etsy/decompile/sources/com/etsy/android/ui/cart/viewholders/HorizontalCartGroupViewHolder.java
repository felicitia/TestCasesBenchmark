package com.etsy.android.ui.cart.viewholders;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroup;
import com.etsy.android.ui.cart.MultiShopCartGroupAdapter;
import com.etsy.android.ui.cart.e;
import com.etsy.android.vespa.viewholders.BaseViewHolder;

public class HorizontalCartGroupViewHolder extends BaseViewHolder<CartGroup> {
    private final MultiShopCartGroupAdapter mLeftAdapter;
    private final RecyclerView mRecyclerViewLeft = ((RecyclerView) findViewById(R.id.items_left));
    private final RecyclerView mRecyclerViewRight = ((RecyclerView) findViewById(R.id.items_right));
    private final MultiShopCartGroupAdapter mRightAdapter;

    public HorizontalCartGroupViewHolder(ViewGroup viewGroup, e eVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_horz_cart_group, viewGroup, false));
        this.mRecyclerViewLeft.setRecycledViewPool(eVar.e());
        this.mRecyclerViewRight.setRecycledViewPool(eVar.e());
        this.mRecyclerViewLeft.setNestedScrollingEnabled(false);
        this.mRecyclerViewRight.setNestedScrollingEnabled(false);
        Context context = viewGroup.getContext();
        this.mRecyclerViewLeft.setLayoutManager(new LinearLayoutManager(context));
        this.mRecyclerViewRight.setLayoutManager(new LinearLayoutManager(context));
        this.mLeftAdapter = new MultiShopCartGroupAdapter(eVar);
        this.mRightAdapter = new MultiShopCartGroupAdapter(eVar);
        this.mRecyclerViewLeft.setAdapter(this.mLeftAdapter);
        this.mRecyclerViewRight.setAdapter(this.mRightAdapter);
        this.mRecyclerViewLeft.setDescendantFocusability(131072);
        this.mRecyclerViewRight.setDescendantFocusability(131072);
    }

    public void bind(CartGroup cartGroup) {
        ((LinearLayoutManager) this.mRecyclerViewLeft.getLayoutManager()).setInitialPrefetchItemCount(cartGroup.getItems().size());
        ((LinearLayoutManager) this.mRecyclerViewRight.getLayoutManager()).setInitialPrefetchItemCount(cartGroup.getPaymentItems().size());
        this.mLeftAdapter.setCartGroupItems(cartGroup.getItems());
        this.mRightAdapter.setCartGroupItems(cartGroup.getPaymentItems());
    }
}
