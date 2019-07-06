package com.etsy.android.ui.cart;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.ViewGroup;
import com.etsy.android.lib.models.apiv3.cart.CartGroup;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.vespa.k;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import java.util.ArrayList;
import java.util.List;

public class MultiShopCartGroupAdapter extends Adapter<BaseViewHolder<k>> {
    protected ArrayList<k> mItems = new ArrayList<>();
    protected e mViewHolderFactory;

    public MultiShopCartGroupAdapter(e eVar) {
        this.mViewHolderFactory = eVar;
    }

    public void addCartGroupItems(List<CartGroupItem> list) {
        int size = this.mItems.size();
        this.mItems.addAll(list);
        notifyItemRangeInserted(size, list.size());
    }

    public BaseViewHolder<k> onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.mViewHolderFactory.a(viewGroup, i);
    }

    public void onBindViewHolder(BaseViewHolder<k> baseViewHolder, int i) {
        baseViewHolder.bind(this.mItems.get(i));
    }

    public int getItemViewType(int i) {
        return this.mViewHolderFactory.a((k) this.mItems.get(i));
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public void clearData() {
        notifyItemRangeRemoved(0, this.mItems.size());
        this.mItems.clear();
    }

    public void setCartGroup(@NonNull CartGroup cartGroup) {
        this.mItems.clear();
        this.mItems.addAll(cartGroup.getItems());
        this.mItems.addAll(cartGroup.getPaymentItems());
        notifyDataSetChanged();
    }

    public void setCartGroupItems(@NonNull List<? extends k> list) {
        this.mItems.clear();
        this.mItems.addAll(list);
        notifyDataSetChanged();
    }
}
