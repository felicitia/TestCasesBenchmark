package com.contextlogic.wish.dialog.promotion.rotating;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishTag;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PromoRotatingDialogTagAdapter extends BaseAdapter {
    private Context mContext;
    /* access modifiers changed from: private */
    public TagSelectView mSelectAllHeader;
    /* access modifiers changed from: private */
    public Set<String> mSelectedTagIds;
    private ArrayList<WishTag> mTags;

    static class TagSelectView extends LinearLayout {
        public TextView categoryText;
        public CheckBox checkBox;

        public TagSelectView(Context context) {
            super(context);
            init();
        }

        private void init() {
            View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.promo_rotating_dialog_tag_row, this);
            this.categoryText = (TextView) inflate.findViewById(R.id.promo_rotating_dialog_category_text);
            this.checkBox = (CheckBox) inflate.findViewById(R.id.promo_rotating_dialog_category_checkbox);
        }
    }

    public long getItemId(int i) {
        return 0;
    }

    public PromoRotatingDialogTagAdapter(Context context, ArrayList<WishTag> arrayList, ArrayList<WishTag> arrayList2) {
        if (context != null && arrayList2 != null) {
            this.mContext = context;
            this.mTags = arrayList2;
            this.mSelectedTagIds = new HashSet();
            if (arrayList != null && !arrayList.isEmpty()) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    this.mSelectedTagIds.add(((WishTag) it.next()).getTagId());
                }
            }
        }
    }

    public TagSelectView getSelectAllListHeader() {
        this.mSelectAllHeader = new TagSelectView(this.mContext);
        this.mSelectAllHeader.categoryText.setText(this.mContext.getString(R.string.select_all));
        this.mSelectAllHeader.categoryText.setTypeface(this.mSelectAllHeader.categoryText.getTypeface(), 1);
        this.mSelectAllHeader.checkBox.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PromoRotatingDialogTagAdapter.this.mSelectAllHeader != null && PromoRotatingDialogTagAdapter.this.mSelectAllHeader.checkBox != null) {
                    if (PromoRotatingDialogTagAdapter.this.mSelectAllHeader.checkBox.isChecked()) {
                        PromoRotatingDialogTagAdapter.this.selectAll();
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ROTATING_PROMO_NOTI_DIALOG_SELECT_ALL);
                    } else {
                        PromoRotatingDialogTagAdapter.this.deselectAll();
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ROTATING_PROMO_NOTI_DIALOG_DESELECT_ALL);
                    }
                }
            }
        });
        if (this.mSelectedTagIds.size() == getCount()) {
            this.mSelectAllHeader.checkBox.setChecked(true);
        }
        return this.mSelectAllHeader;
    }

    public int getCount() {
        if (this.mTags == null) {
            return 0;
        }
        return this.mTags.size();
    }

    public WishTag getItem(int i) {
        if (i < getCount()) {
            return (WishTag) this.mTags.get(i);
        }
        return null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        final TagSelectView tagSelectView;
        final WishTag item = getItem(i);
        if (item == null) {
            return null;
        }
        if (view == null) {
            tagSelectView = new TagSelectView(this.mContext);
        } else {
            tagSelectView = (TagSelectView) view;
        }
        tagSelectView.checkBox.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (tagSelectView.checkBox != null && PromoRotatingDialogTagAdapter.this.mSelectedTagIds != null) {
                    if (tagSelectView.checkBox.isChecked()) {
                        PromoRotatingDialogTagAdapter.this.mSelectedTagIds.add(item.getTagId());
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ROTATING_PROMO_NOTI_DIALOG_CATEGORY_CHECK, item.getTagId());
                    } else {
                        PromoRotatingDialogTagAdapter.this.mSelectedTagIds.remove(item.getTagId());
                        if (!(PromoRotatingDialogTagAdapter.this.mSelectAllHeader == null || PromoRotatingDialogTagAdapter.this.mSelectAllHeader.checkBox == null)) {
                            PromoRotatingDialogTagAdapter.this.mSelectAllHeader.checkBox.setChecked(false);
                        }
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ROTATING_PROMO_NOTI_DIALOG_CATEGORY_UNCHECK, item.getTagId());
                    }
                }
            }
        });
        tagSelectView.categoryText.setText(item.getName());
        tagSelectView.checkBox.setChecked(this.mSelectedTagIds.contains(item.getTagId()));
        return tagSelectView;
    }

    /* access modifiers changed from: private */
    public void selectAll() {
        if (this.mSelectedTagIds != null && this.mTags != null) {
            Iterator it = this.mTags.iterator();
            while (it.hasNext()) {
                this.mSelectedTagIds.add(((WishTag) it.next()).getTagId());
            }
            notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: private */
    public void deselectAll() {
        if (this.mSelectedTagIds != null) {
            this.mSelectedTagIds.clear();
            notifyDataSetChanged();
        }
    }

    public ArrayList<String> getSelectedTagIds() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (this.mSelectedTagIds != null) {
            arrayList.addAll(this.mSelectedTagIds);
        }
        return arrayList;
    }
}
