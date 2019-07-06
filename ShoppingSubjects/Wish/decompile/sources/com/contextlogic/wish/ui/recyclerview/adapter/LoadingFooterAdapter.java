package com.contextlogic.wish.ui.recyclerview.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import java.util.ArrayList;
import java.util.List;

public abstract class LoadingFooterAdapter<ITEM, HOLDER extends ViewHolder> extends Adapter {
    private boolean isLoading;
    private List<ITEM> items = new ArrayList();
    private boolean mNoMoreItems;

    private static class LoadingViewHolder extends ViewHolder {
        TextView mNoMoreItemsText;
        View mProgressBar;

        private LoadingViewHolder(View view) {
            super(view);
            this.mNoMoreItemsText = (TextView) view.findViewById(R.id.no_more_items_text);
            this.mProgressBar = view.findViewById(R.id.progress_bar_container);
            if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
                View findViewById = this.mProgressBar.findViewById(R.id.loading_footer_progress_bar);
                View findViewById2 = this.mProgressBar.findViewById(R.id.loading_footer_three_dot_loading_spinner);
                if (findViewById2 != null && findViewById != null) {
                    findViewById.setVisibility(8);
                    findViewById2.setVisibility(0);
                }
            }
        }
    }

    public abstract int getMyItemViewType(int i);

    public abstract void onBindMyViewHolder(HOLDER holder, int i);

    public abstract HOLDER onCreateMyViewHolder(ViewGroup viewGroup, int i);

    public abstract void onMyViewRecycled(HOLDER holder, int i);

    public void setValues(List<ITEM> list) {
        this.items.clear();
        this.items.addAll(list);
        notifyDataSetChanged();
    }

    public void appendValues(List<ITEM> list) {
        if (!list.isEmpty()) {
            int size = this.items.size();
            this.items.addAll(list);
            notifyItemRangeInserted(size, list.size());
        }
    }

    public void setLoading(boolean z) {
        if (!this.isLoading && z) {
            notifyItemInserted(this.items.size());
        } else if (this.isLoading && !z) {
            notifyItemRemoved(this.items.size());
        }
        this.isLoading = z;
    }

    public void setNoMoreItems(boolean z) {
        this.mNoMoreItems = z;
        notifyItemChanged(this.items.size());
    }

    public int getItemViewType(int i) {
        if (i == this.items.size()) {
            return -1;
        }
        return getMyItemViewType(i);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != -1) {
            return onCreateMyViewHolder(viewGroup, i);
        }
        LoadingViewHolder loadingViewHolder = new LoadingViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.loading_footer_item, viewGroup, false));
        if (this.mNoMoreItems) {
            loadingViewHolder.mProgressBar.setVisibility(8);
            loadingViewHolder.mNoMoreItemsText.setVisibility(0);
        } else {
            loadingViewHolder.mProgressBar.setVisibility(0);
            loadingViewHolder.mNoMoreItemsText.setVisibility(8);
        }
        return loadingViewHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (i < this.items.size()) {
            onBindMyViewHolder(viewHolder, i);
        } else if (!(viewHolder instanceof LoadingViewHolder)) {
        } else {
            if (this.mNoMoreItems) {
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) viewHolder;
                loadingViewHolder.mProgressBar.setVisibility(8);
                loadingViewHolder.mNoMoreItemsText.setVisibility(0);
                return;
            }
            LoadingViewHolder loadingViewHolder2 = (LoadingViewHolder) viewHolder;
            loadingViewHolder2.mProgressBar.setVisibility(0);
            loadingViewHolder2.mNoMoreItemsText.setVisibility(8);
        }
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        int adapterPosition = viewHolder.getAdapterPosition();
        if (adapterPosition != -1 && adapterPosition < this.items.size()) {
            onMyViewRecycled(viewHolder, adapterPosition);
        }
    }

    public int getItemCount() {
        return this.items.size() + (this.isLoading ? 1 : 0);
    }

    public void clean() {
        int valuesCount = getValuesCount();
        this.items.clear();
        notifyItemRangeRemoved(0, valuesCount);
    }

    public int getValuesCount() {
        return this.items.size();
    }

    public ITEM getValueAt(int i) {
        return this.items.get(i);
    }
}
