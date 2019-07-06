package com.contextlogic.wish.activity.feed.outlet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishCategory;
import java.util.ArrayList;

public class BrandedCategoryListAdapter extends BaseAdapter {
    private BrandedCategoryListActivity mBaseActivity;
    private ArrayList<WishCategory> mCategories;
    private BrandedCategoryListFragment mFragment;

    static class ItemRowHolder {
        TextView rowTitle;

        ItemRowHolder() {
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public BrandedCategoryListAdapter(BrandedCategoryListActivity brandedCategoryListActivity, BrandedCategoryListFragment brandedCategoryListFragment) {
        this.mBaseActivity = brandedCategoryListActivity;
        this.mFragment = brandedCategoryListFragment;
    }

    public int getCount() {
        if (this.mCategories == null) {
            return 0;
        }
        return this.mCategories.size();
    }

    public WishCategory getItem(int i) {
        return (WishCategory) this.mCategories.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        if (view == null) {
            LayoutInflater layoutInflater = this.mBaseActivity.getLayoutInflater();
            itemRowHolder = new ItemRowHolder();
            view = layoutInflater.inflate(R.layout.branded_category_list_cell, viewGroup, false);
            itemRowHolder.rowTitle = (TextView) view.findViewById(R.id.branded_category_list_cell_text);
            view.setTag(itemRowHolder);
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        itemRowHolder.rowTitle.setText(getItem(i).getName());
        return view;
    }

    public void setCategories(ArrayList<WishCategory> arrayList) {
        this.mCategories = arrayList;
        notifyDataSetChanged();
    }
}
