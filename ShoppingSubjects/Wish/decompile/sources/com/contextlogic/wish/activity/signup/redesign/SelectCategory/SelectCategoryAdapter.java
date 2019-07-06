package com.contextlogic.wish.activity.signup.redesign.SelectCategory;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.api.model.WishSignupFlowCategory;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.listview.HorizontalListGridView.Adapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SelectCategoryAdapter extends Adapter {
    private BaseActivity mActivity;
    private ArrayList<WishSignupFlowCategory> mCategories;
    /* access modifiers changed from: private */
    public HashSet<Integer> mSelectedIndices = new HashSet<>();
    private SelectCategoryView mView;

    public SelectCategoryAdapter(BaseActivity baseActivity, SelectCategoryView selectCategoryView, int i) {
        this.mView = selectCategoryView;
        this.mActivity = baseActivity;
        this.mRowCount = i;
    }

    public int getCount() {
        if (this.mCategories != null) {
            return this.mCategories.size();
        }
        return 0;
    }

    public WishSignupFlowCategory getItem(int i) {
        if (i < getCount()) {
            return (WishSignupFlowCategory) this.mCategories.get(i);
        }
        return null;
    }

    public int getHorizontalMargin() {
        return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.eight_padding);
    }

    public int getVerticalMargin() {
        return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.eight_padding);
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        final SelectCategoryCellView selectCategoryCellView;
        if (view != null) {
            selectCategoryCellView = (SelectCategoryCellView) view;
        } else {
            selectCategoryCellView = new SelectCategoryCellView(this.mActivity);
        }
        if (this.mSelectedIndices == null) {
            return null;
        }
        if (getItem(i) != null) {
            selectCategoryCellView.setup(getItem(i));
            if (this.mSelectedIndices.contains(Integer.valueOf(i))) {
                selectCategoryCellView.setSelected(true);
            }
            selectCategoryCellView.getCategoryBorder().setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    selectCategoryCellView.toggleSelect();
                    if (selectCategoryCellView.isSelected()) {
                        SelectCategoryAdapter.this.mSelectedIndices.add(Integer.valueOf(i));
                    } else {
                        SelectCategoryAdapter.this.mSelectedIndices.remove(Integer.valueOf(i));
                    }
                }
            });
        }
        return selectCategoryCellView;
    }

    public void setCategories(ArrayList<WishSignupFlowCategory> arrayList) {
        this.mCategories = arrayList;
    }

    public ArrayList<String> getSelectedCategories() {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator it = this.mSelectedIndices.iterator();
        while (it.hasNext()) {
            arrayList.add(getItem(((Integer) it.next()).intValue()).getId());
        }
        return arrayList;
    }

    public ArrayList<Integer> getSelectedCategoryIndex() {
        return new ArrayList<>(this.mSelectedIndices);
    }

    public void setSelectedCategoryIndex(ArrayList<Integer> arrayList) {
        this.mSelectedIndices = new HashSet<>(arrayList);
    }

    public int getItemWidth(int i) {
        return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.signup_flow_select_category_spacing);
    }

    public int getItemHeight(int i) {
        return WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.signup_flow_select_category_spacing);
    }
}
