package com.contextlogic.wish.activity.feed.filter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishFilterGroup;
import com.contextlogic.wish.api.model.WishFilterOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class FilterAdapter extends ArrayAdapter<WishFilterOption> {
    private FilterFragment mFilterFragment;
    private ArrayList<WishFilterOption> mFilters = new ArrayList<>();
    private int mPosition;
    private ArrayList<WishFilterGroup> mRootFilters;
    private HashMap<Integer, HashSet<String>> mSelectedFilters = new HashMap<>();

    static class ItemRowHolder {
        ImageView checkmark;
        View leftSpacer;
        TextView mainText;
        ImageView moreItems;
        TextView selectedText;

        ItemRowHolder() {
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public FilterAdapter(Context context, FilterFragment filterFragment) {
        super(context, R.layout.filter_fragment_row);
        this.mFilterFragment = filterFragment;
        this.mPosition = 0;
        this.mSelectedFilters.put(Integer.valueOf(this.mPosition), new HashSet());
    }

    public int getCount() {
        return this.mFilters.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        if (view == null) {
            view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.filter_fragment_row, viewGroup, false);
            itemRowHolder = new ItemRowHolder();
            itemRowHolder.leftSpacer = view.findViewById(R.id.filter_fragment_row_left_spacer);
            itemRowHolder.mainText = (TextView) view.findViewById(R.id.filter_fragment_row_main_text);
            itemRowHolder.selectedText = (TextView) view.findViewById(R.id.filter_fragment_row_selected);
            itemRowHolder.checkmark = (ImageView) view.findViewById(R.id.filter_fragment_row_check_mark);
            itemRowHolder.moreItems = (ImageView) view.findViewById(R.id.filter_fragment_row_more);
            itemRowHolder.moreItems.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    FilterAdapter.this.handleNextLevel(view);
                }
            });
            view.setTag(itemRowHolder);
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        itemRowHolder.checkmark.setVisibility(8);
        itemRowHolder.selectedText.setVisibility(8);
        itemRowHolder.moreItems.setVisibility(8);
        itemRowHolder.leftSpacer.setVisibility(8);
        WishFilterOption wishFilterOption = (WishFilterOption) this.mFilters.get(i);
        itemRowHolder.mainText.setText(wishFilterOption.getName());
        if (wishFilterOption.getChildFilters() != null && wishFilterOption.getChildFilters().size() > 0) {
            itemRowHolder.moreItems.setVisibility(0);
            itemRowHolder.moreItems.setTag(Integer.valueOf(i));
        } else if (((HashSet) this.mSelectedFilters.get(Integer.valueOf(this.mPosition))).contains(wishFilterOption.getFilterId())) {
            itemRowHolder.checkmark.setVisibility(0);
        }
        if (i > 0 && hasParentChild()) {
            itemRowHolder.leftSpacer.setVisibility(0);
        }
        ArrayList allSelectedChildren = getAllSelectedChildren(wishFilterOption);
        if (allSelectedChildren != null && allSelectedChildren.size() > 0) {
            String str = "";
            Iterator it = allSelectedChildren.iterator();
            while (it.hasNext()) {
                WishFilterOption wishFilterOption2 = (WishFilterOption) it.next();
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(wishFilterOption2.getName());
                sb.append(", ");
                str = sb.toString();
            }
            String substring = str.substring(0, str.length() - 2);
            itemRowHolder.selectedText.setVisibility(0);
            if (substring.equals(wishFilterOption.getName())) {
                itemRowHolder.selectedText.setText(getContext().getString(R.string.all));
            } else {
                itemRowHolder.selectedText.setText(substring);
            }
        }
        return view;
    }

    public WishFilterOption getItem(int i) {
        return (WishFilterOption) this.mFilters.get(i);
    }

    public void setRootFilter(ArrayList<WishFilterGroup> arrayList) {
        if (arrayList != null && arrayList.size() >= 1) {
            this.mRootFilters = arrayList;
            this.mFilters = new ArrayList<>();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.mFilters.add(new WishFilterOption((WishFilterGroup) it.next()));
            }
            notifyDataSetChanged();
        }
    }

    public void setFilters(ArrayList<WishFilterOption> arrayList) {
        this.mFilters = arrayList;
        notifyDataSetChanged();
    }

    public void backToParent(boolean z) {
        if (z) {
            setRootFilter(this.mRootFilters);
            this.mFilterFragment.refreshFilters();
        } else if (getCount() > 0) {
            WishFilterOption item = getItem(0);
            if (item != null) {
                WishFilterOption parentFilter = item.getParentFilter().getParentFilter();
                if (parentFilter != null && parentFilter.getChildFilters() != null && parentFilter.getChildFilters().size() > 0) {
                    setFilters(parentFilter.getChildFilters());
                }
            }
        }
    }

    public void selectFilter(WishFilterOption wishFilterOption) {
        if (((HashSet) this.mSelectedFilters.get(Integer.valueOf(this.mPosition))).contains(wishFilterOption.getFilterId())) {
            ((HashSet) this.mSelectedFilters.get(Integer.valueOf(this.mPosition))).remove(wishFilterOption.getFilterId());
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_FEED_FILTER_REMOVE);
        } else {
            if (wishFilterOption.getParentFilter().isExclusive()) {
                Iterator it = wishFilterOption.getParentFilter().getChildFilters().iterator();
                while (it.hasNext()) {
                    ((HashSet) this.mSelectedFilters.get(Integer.valueOf(this.mPosition))).remove(((WishFilterOption) it.next()).getFilterId());
                }
            }
            ((HashSet) this.mSelectedFilters.get(Integer.valueOf(this.mPosition))).add(wishFilterOption.getFilterId());
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_FEED_FILTER_SELECT);
        }
        notifyDataSetChanged();
    }

    public void selectScreenshotFilters(ArrayList<String> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((HashSet) this.mSelectedFilters.get(Integer.valueOf(this.mPosition))).add((String) it.next());
        }
    }

    public void clearAllFilters() {
        ((HashSet) this.mSelectedFilters.get(Integer.valueOf(this.mPosition))).clear();
        backToParent(true);
    }

    public void handleNextLevel(View view) {
        WishFilterOption wishFilterOption = (WishFilterOption) this.mFilters.get(((Integer) view.getTag()).intValue());
        if (wishFilterOption.getChildFilters() != null && wishFilterOption.getChildFilters().size() > 0) {
            this.mFilterFragment.handleNextLevel();
            setFilters(wishFilterOption.getChildFilters());
        }
    }

    public ArrayList<WishFilterOption> getAllSelectedChildren(WishFilterOption wishFilterOption) {
        ArrayList<WishFilterOption> allSelectedChildrenRecursive = getAllSelectedChildrenRecursive(wishFilterOption);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = allSelectedChildrenRecursive.iterator();
        while (it.hasNext()) {
            WishFilterOption wishFilterOption2 = (WishFilterOption) it.next();
            linkedHashMap.put(wishFilterOption2.getFilterId(), wishFilterOption2);
        }
        allSelectedChildrenRecursive.clear();
        allSelectedChildrenRecursive.addAll(linkedHashMap.values());
        return allSelectedChildrenRecursive;
    }

    public ArrayList<WishFilterOption> getAllSelectedChildrenRecursive(WishFilterOption wishFilterOption) {
        ArrayList<WishFilterOption> arrayList = new ArrayList<>();
        if (wishFilterOption.getChildFilters() == null || wishFilterOption.getChildFilters().size() < 1) {
            return arrayList;
        }
        Iterator it = wishFilterOption.getChildFilters().iterator();
        while (it.hasNext()) {
            WishFilterOption wishFilterOption2 = (WishFilterOption) it.next();
            if (((HashSet) this.mSelectedFilters.get(Integer.valueOf(this.mPosition))).contains(wishFilterOption2.getFilterId())) {
                arrayList.add(wishFilterOption2);
            }
            arrayList.addAll(getAllSelectedChildrenRecursive(wishFilterOption2));
        }
        return arrayList;
    }

    public void setPosition(int i) {
        if (this.mSelectedFilters.get(Integer.valueOf(i)) == null) {
            this.mSelectedFilters.put(Integer.valueOf(i), new HashSet());
        }
        this.mPosition = i;
        notifyDataSetChanged();
    }

    public HashMap<Integer, HashSet<String>> getSelectedFilters() {
        return this.mSelectedFilters;
    }

    public void setSelectedFilters(HashMap<Integer, HashSet<String>> hashMap) {
        this.mSelectedFilters = hashMap;
        notifyDataSetChanged();
    }

    private boolean hasParentChild() {
        if (this.mFilters.size() <= 0 || !((WishFilterOption) this.mFilters.get(0)).isParentChild()) {
            return false;
        }
        return true;
    }
}
