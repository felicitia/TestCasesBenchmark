package com.etsy.android.ui.cart;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.models.apiv3.OfferingOption;
import com.etsy.android.ui.cart.viewholders.CartVariationSelectOptionViewHolder;
import com.etsy.android.ui.cart.viewholders.CartVariationSelectOptionViewHolder.a;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;

public class CartVariationSelectAdapter extends BaseRecyclerViewAdapter<OfferingOption> {
    private final a mVariationOptionClickListener;

    /* access modifiers changed from: protected */
    public int getListItemViewType(int i) {
        return 0;
    }

    public CartVariationSelectAdapter(FragmentActivity fragmentActivity, a aVar) {
        super(fragmentActivity, null);
        this.mVariationOptionClickListener = aVar;
    }

    /* access modifiers changed from: protected */
    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        return new CartVariationSelectOptionViewHolder(this.mInflater, viewGroup, this.mVariationOptionClickListener);
    }

    /* access modifiers changed from: protected */
    public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        ((CartVariationSelectOptionViewHolder) viewHolder).bind((OfferingOption) getItem(i));
    }
}
