package com.etsy.android.ui.local.marketdetails;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.util.l;
import com.etsy.android.uikit.adapter.PaddedColumnRecyclerViewAdapter;
import com.etsy.android.uikit.viewholder.TextViewHolder;

/* compiled from: LocalProductCategoryAdapter */
class f extends PaddedColumnRecyclerViewAdapter<String> {
    private int a;

    /* access modifiers changed from: protected */
    public int getCoreItemViewType(int i) {
        return 0;
    }

    public f(FragmentActivity fragmentActivity, c cVar) {
        super(fragmentActivity, cVar, fragmentActivity.getResources().getInteger(R.integer.local_market_category_columns), l.d((Activity) fragmentActivity), true);
        this.a = fragmentActivity.getResources().getInteger(R.integer.local_market_category_columns);
    }

    public int getSpanSize(int i, int i2) {
        if (getItemViewType(i) != 0) {
            return super.getSpanSize(i, i2);
        }
        return i2 / this.a;
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
        return new TextViewHolder(this.mInflater, viewGroup, R.layout.list_item_local_store_category, R.id.text);
    }

    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
        TextView textView = ((TextViewHolder) viewHolder).getTextView();
        if (this.mShowLeftTitleColumn) {
            textView.setVisibility(8);
        } else {
            textView.setText(R.string.this_store_sells);
        }
    }

    /* access modifiers changed from: protected */
    public void bindLeftTitleItemViewType(ViewHolder viewHolder, int i) {
        ((TextViewHolder) viewHolder).getTextView().setText(R.string.this_store_sells);
    }

    /* access modifiers changed from: protected */
    public void bindCoreItemViewType(ViewHolder viewHolder, int i) {
        ((TextViewHolder) viewHolder).getTextView().setText((CharSequence) getItem(i));
    }
}
