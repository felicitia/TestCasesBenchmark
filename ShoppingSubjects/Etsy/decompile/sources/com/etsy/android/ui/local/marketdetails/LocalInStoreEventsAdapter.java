package com.etsy.android.ui.local.marketdetails;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.cardview.viewholders.LocalInStoreEventViewHolder;
import com.etsy.android.ui.cardview.viewholders.LocalInStoreEventViewHolder.a;
import com.etsy.android.uikit.adapter.PaddedColumnRecyclerViewAdapter;
import com.etsy.android.uikit.viewholder.TextViewHolder;

public class LocalInStoreEventsAdapter extends PaddedColumnRecyclerViewAdapter<LocalMarketCard> {
    private static final int VIEW_TYPE_EVENT = 0;
    private a mMarketListener;

    /* access modifiers changed from: protected */
    public int getCoreItemViewType(int i) {
        return 0;
    }

    public LocalInStoreEventsAdapter(FragmentActivity fragmentActivity, c cVar, @NonNull a aVar) {
        super(fragmentActivity, cVar, fragmentActivity.getResources().getInteger(R.integer.local_market_in_store_events_columns), l.d((Activity) fragmentActivity), true);
        this.mMarketListener = aVar;
    }

    public int getSpanSize(int i, int i2) {
        if (getItemViewType(i) != 0) {
            return super.getSpanSize(i, i2);
        }
        return i2 / this.mColumns;
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
        return new TextViewHolder(this.mInflater, viewGroup, R.layout.section_title_local_market, R.id.title);
    }

    /* access modifiers changed from: protected */
    public ViewHolder onCreateLeftTitleItemViewHolder(ViewGroup viewGroup, int i) {
        return new TextViewHolder(this.mInflater, viewGroup, R.layout.section_title_left_col_local_market, R.id.title);
    }

    /* access modifiers changed from: protected */
    public ViewHolder onCreateCoreItemViewHolder(ViewGroup viewGroup, int i) {
        return new LocalInStoreEventViewHolder(viewGroup, this.mMarketListener);
    }

    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
        TextView textView = ((TextViewHolder) viewHolder).getTextView();
        if (this.mShowLeftTitleColumn) {
            textView.setVisibility(8);
        } else {
            textView.setText(((FragmentActivity) this.mContext).getResources().getString(R.string.upcoming_in_store_events));
        }
    }

    /* access modifiers changed from: protected */
    public void bindLeftTitleItemViewType(ViewHolder viewHolder, int i) {
        ((TextViewHolder) viewHolder).getTextView().setText(((FragmentActivity) this.mContext).getResources().getString(R.string.upcoming_in_store_events));
    }

    /* access modifiers changed from: protected */
    public void bindCoreItemViewType(ViewHolder viewHolder, int i) {
        ((LocalInStoreEventViewHolder) viewHolder).bind(this.mImageBatch, (LocalMarketCard) getItem(i));
    }
}
