package com.contextlogic.wish.ui.recyclerview.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.ui.recyclerview.adapter.ItemModel;
import java.util.ArrayList;
import java.util.List;

public final class ItemModelAdapter<MODEL extends ItemModel> extends Adapter<ViewHolder> {
    private View mFooterView;
    private View mHeaderView;
    private LayoutInflater mInflater;
    private List<MODEL> mValues = new ArrayList();
    private SparseArray<ViewHolderCreator> mViewTypeToViewHolderCreator = new SparseArray<>();

    private static class HeaderFooterViewHolder extends ViewHolder {
        private HeaderFooterViewHolder(View view) {
            super(view);
        }
    }

    private void updateCreators() {
        this.mViewTypeToViewHolderCreator.clear();
        for (MODEL model : this.mValues) {
            if (this.mViewTypeToViewHolderCreator.indexOfKey(model.getLayoutResId()) < 0) {
                this.mViewTypeToViewHolderCreator.put(model.getLayoutResId(), model.getViewHolderCreator());
            }
        }
    }

    public void setHeaderView(View view) {
        this.mHeaderView = view;
        notifyDataSetChanged();
    }

    public void setFooterView(View view) {
        this.mFooterView = view;
        notifyDataSetChanged();
    }

    public View getFooterView() {
        return this.mFooterView;
    }

    public void setValues(List<MODEL> list) {
        this.mValues = list;
        updateCreators();
        notifyDataSetChanged();
    }

    public List<MODEL> getValues() {
        return this.mValues;
    }

    private int getPositionWithoutHeader(int i) {
        return this.mHeaderView != null ? i - 1 : i;
    }

    private boolean isHeaderViewType(int i) {
        return i == 0 && this.mHeaderView != null;
    }

    private boolean isFooterViewType(int i) {
        return i == getItemCount() - 1 && this.mFooterView != null;
    }

    public int getItemViewType(int i) {
        if (isHeaderViewType(i)) {
            return this.mHeaderView.hashCode();
        }
        if (isFooterViewType(i)) {
            return this.mFooterView.hashCode();
        }
        return ((ItemModel) this.mValues.get(getPositionWithoutHeader(i))).getLayoutResId();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.mHeaderView != null && i == this.mHeaderView.hashCode()) {
            return new HeaderFooterViewHolder(this.mHeaderView);
        }
        if (this.mFooterView != null && i == this.mFooterView.hashCode()) {
            return new HeaderFooterViewHolder(this.mFooterView);
        }
        if (this.mInflater == null) {
            this.mInflater = LayoutInflater.from(viewGroup.getContext());
        }
        return ((ViewHolderCreator) this.mViewTypeToViewHolderCreator.get(i)).createViewHolder(this.mInflater.inflate(i, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (!isHeaderViewType(i) && !isFooterViewType(i)) {
            ((ItemModel) this.mValues.get(getPositionWithoutHeader(i))).onBindViewHolder(viewHolder);
        }
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        int adapterPosition = viewHolder.getAdapterPosition();
        if (adapterPosition != -1) {
            int positionWithoutHeader = getPositionWithoutHeader(adapterPosition);
            if (positionWithoutHeader < this.mValues.size()) {
                ((ItemModel) this.mValues.get(positionWithoutHeader)).onViewRecycled(viewHolder);
            }
        }
    }

    public int getItemCount() {
        int i = 0;
        int size = this.mValues.size() + (this.mHeaderView != null ? 1 : 0);
        if (this.mFooterView != null) {
            i = 1;
        }
        return size + i;
    }
}
