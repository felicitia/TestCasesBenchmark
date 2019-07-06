package com.contextlogic.wish.activity.productdetails.relateditemsrow;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishProductBadge;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.List;

public class BadgeDescriptionListViewAdapter extends ArrayAdapter<WishProductBadge> {
    private List<WishProductBadge> mBadgeList;

    private static class ViewHolder {
        TextView description;
        ImageView icon;
        ThemedTextView title;

        private ViewHolder() {
        }
    }

    public BadgeDescriptionListViewAdapter(Context context, List<WishProductBadge> list) {
        super(context, R.layout.badge_description_list_item);
        this.mBadgeList = list;
    }

    public WishProductBadge getItem(int i) {
        if (i < getCount()) {
            return (WishProductBadge) this.mBadgeList.get(i);
        }
        return null;
    }

    public int getCount() {
        if (this.mBadgeList != null) {
            return this.mBadgeList.size();
        }
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = LayoutInflater.from(getContext()).inflate(R.layout.badge_description_list_item, viewGroup, false);
            viewHolder.icon = (ImageView) view2.findViewById(R.id.badge_description_icon);
            viewHolder.title = (ThemedTextView) view2.findViewById(R.id.badge_description_title);
            viewHolder.description = (TextView) view2.findViewById(R.id.badge_description_text);
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        WishProductBadge wishProductBadge = (WishProductBadge) this.mBadgeList.get(i);
        viewHolder.title.setText(wishProductBadge.getTitle());
        viewHolder.icon.setImageDrawable(ContextCompat.getDrawable(getContext(), wishProductBadge.getCondensedBadgeIcon()));
        viewHolder.title.setTextColor(ContextCompat.getColor(getContext(), wishProductBadge.getCondensedBadgeColor()));
        viewHolder.description.setText(wishProductBadge.getMessage());
        return view2;
    }
}
