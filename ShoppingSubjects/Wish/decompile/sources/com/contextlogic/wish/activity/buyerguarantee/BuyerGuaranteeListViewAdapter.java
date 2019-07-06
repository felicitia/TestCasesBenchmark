package com.contextlogic.wish.activity.buyerguarantee;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.contextlogic.wish.activity.BaseFragment;
import com.contextlogic.wish.api.model.PageItemHolder;
import com.contextlogic.wish.ui.image.ImageRestorable;
import java.util.List;

public class BuyerGuaranteeListViewAdapter extends BaseAdapter implements ImageRestorable {
    private Context mContext;
    private BaseFragment mFragment;
    private List<PageItemHolder> mItems;
    private ListView mListView;

    public long getItemId(int i) {
        return 0;
    }

    public BuyerGuaranteeListViewAdapter(Context context, BaseFragment baseFragment, ListView listView, List<PageItemHolder> list) {
        this.mItems = list;
        this.mContext = context;
        this.mFragment = baseFragment;
        this.mListView = listView;
    }

    public int getCount() {
        if (this.mItems != null) {
            return this.mItems.size();
        }
        return 0;
    }

    public PageItemHolder getItem(int i) {
        if (i < getCount()) {
            return (PageItemHolder) this.mItems.get(i);
        }
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        BuyerGuaranteeItemRowView buyerGuaranteeItemRowView = (BuyerGuaranteeItemRowView) view;
        if (buyerGuaranteeItemRowView == null) {
            buyerGuaranteeItemRowView = new BuyerGuaranteeItemRowView(this.mContext, this.mFragment);
        }
        buyerGuaranteeItemRowView.setup(getItem(i));
        return buyerGuaranteeItemRowView;
    }

    public void releaseImages() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                if (this.mListView.getChildAt(i) instanceof BuyerGuaranteeItemRowView) {
                    ((BuyerGuaranteeItemRowView) this.mListView.getChildAt(i)).releaseImages();
                }
            }
        }
    }

    public void restoreImages() {
        if (this.mListView != null) {
            for (int i = 0; i < this.mListView.getChildCount(); i++) {
                if (this.mListView.getChildAt(i) instanceof BuyerGuaranteeItemRowView) {
                    ((BuyerGuaranteeItemRowView) this.mListView.getChildAt(i)).restoreImages();
                }
            }
        }
    }
}
