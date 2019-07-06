package com.etsy.android.ui.local.marketdetails;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;

/* compiled from: LocalDetailsFooterDisclaimerViewHolder */
class c extends ViewHolder {
    View a = this.itemView.findViewById(R.id.powered_by_foursquare);
    TextView b = ((TextView) this.itemView.findViewById(R.id.disclaimer));

    public c(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        super(layoutInflater.inflate(R.layout.footer_local_market_disclaimer, viewGroup, false));
    }

    public void a(boolean z) {
        this.a.setVisibility(z ? 8 : 0);
        this.b.setText(z ? R.string.store_items_pictured_disclaimer : R.string.event_items_pictured_disclaimer);
    }
}
