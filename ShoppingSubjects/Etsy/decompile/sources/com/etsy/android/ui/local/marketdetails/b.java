package com.etsy.android.ui.local.marketdetails;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.uikit.util.TrackingOnClickListener;

/* compiled from: LocalDetailsFooterBrowseLinkViewHolder */
class b extends ViewHolder {
    /* access modifiers changed from: private */
    public a a;

    /* compiled from: LocalDetailsFooterBrowseLinkViewHolder */
    interface a {
        void a();
    }

    public b(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup, @NonNull a aVar) {
        super(layoutInflater.inflate(R.layout.footer_local_market_browse, viewGroup, false));
        this.a = aVar;
        this.itemView.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                b.this.a.a();
            }
        });
    }
}
