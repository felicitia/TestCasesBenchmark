package com.etsy.android.ui.local.marketdetails;

import android.app.Activity;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.lib.util.k;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.cardview.viewholders.LocalAttendeeShopViewHolder;
import com.etsy.android.ui.cardview.viewholders.LocalInStoreEventViewHolder;

/* compiled from: LocalDetailsMarginDecoration */
class e extends ItemDecoration {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final SpanSizeLookup f;
    private final Activity g;

    public e(Activity activity, SpanSizeLookup spanSizeLookup, int i, int i2, int i3) {
        this.g = activity;
        this.f = spanSizeLookup;
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = activity.getResources().getDimensionPixelOffset(R.dimen.padding_large);
        this.e = activity.getResources().getDimensionPixelOffset(R.dimen.local_market_details_card_internal_side_padding);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        ViewHolder childViewHolder = recyclerView.getChildViewHolder(view);
        if (!(childViewHolder instanceof c)) {
            view.setBackgroundColor(view.getResources().getColor(R.color.sk_white));
            if (k.c()) {
                view.setElevation(view.getResources().getDimension(R.dimen.elevation_level_1));
            }
        }
        if (childViewHolder instanceof LocalAttendeeShopViewHolder) {
            a(view, recyclerView, this.b);
        } else if (childViewHolder instanceof LocalInStoreEventViewHolder) {
            a(view, recyclerView, this.c);
        }
    }

    private void a(View view, RecyclerView recyclerView, int i) {
        int spanIndex = this.f.getSpanIndex(recyclerView.getChildAdapterPosition(view), this.a);
        if (i == 1) {
            view.setPadding(this.e, 0, this.e, this.d);
        } else if (i == 2 && spanIndex == 0) {
            view.setPadding(this.e, 0, this.e / 2, this.d);
        } else if (i == 2 && spanIndex > 0) {
            view.setPadding(this.e / 2, 0, this.e, this.d);
        } else if (l.d(this.g)) {
            view.setPadding(0, 0, this.e, this.d);
        } else {
            view.setPadding(this.e, 0, this.e, this.d);
        }
    }
}
