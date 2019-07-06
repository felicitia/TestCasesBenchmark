package com.contextlogic.wish.activity.feed.recentlyviewed;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.ViewGroup;
import com.contextlogic.wish.api.model.WishProduct;
import java.util.ArrayList;
import java.util.List;

public class MerchantCellProductsAdapter extends Adapter<MerchantCellProductViewHolder> {
    private List<WishProduct> mProducts = new ArrayList();

    public void setProducts(List<WishProduct> list) {
        this.mProducts = list;
        notifyDataSetChanged();
    }

    public MerchantCellProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MerchantCellProductViewHolder(new MerchantCellProductView(viewGroup.getContext()));
    }

    public void onBindViewHolder(MerchantCellProductViewHolder merchantCellProductViewHolder, int i) {
        merchantCellProductViewHolder.itemView.setProduct((WishProduct) this.mProducts.get(i));
    }

    public int getItemCount() {
        return this.mProducts.size();
    }
}
