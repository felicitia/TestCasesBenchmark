package com.etsy.android.vespa.viewholders;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.etsy.android.lib.a.k;

public class LoadingCardViewHolder extends BaseViewHolder {
    public LoadingCardViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(k.list_item_loading, viewGroup, false));
    }
}
