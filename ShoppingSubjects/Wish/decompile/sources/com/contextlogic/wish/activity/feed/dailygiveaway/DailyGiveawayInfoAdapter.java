package com.contextlogic.wish.activity.feed.dailygiveaway;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishInfoDailyGiveawayInfo.WishInfoCategory;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;
import java.util.Iterator;

public class DailyGiveawayInfoAdapter extends BaseAdapter {
    private DrawerActivity mBaseActivity;
    private DataProvider mDataProvider;

    public interface DataProvider {
        ArrayList<WishInfoCategory> getData();
    }

    private static class Holder {
        ThemedTextView title;

        private Holder() {
        }
    }

    public long getItemId(int i) {
        return 0;
    }

    public DailyGiveawayInfoAdapter(DrawerActivity drawerActivity) {
        this.mBaseActivity = drawerActivity;
    }

    public void setDataProvider(DataProvider dataProvider) {
        this.mDataProvider = dataProvider;
    }

    public ArrayList<WishInfoCategory> getItems() {
        if (this.mDataProvider != null) {
            return this.mDataProvider.getData();
        }
        return null;
    }

    public final WishInfoCategory getItem(int i) {
        ArrayList items = getItems();
        if (items == null || i < 0 || i >= items.size()) {
            return null;
        }
        return (WishInfoCategory) items.get(i);
    }

    public final int getCount() {
        ArrayList items = getItems();
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout linearLayout;
        Holder holder;
        WishInfoCategory item = getItem(i);
        if (view == null || !(view instanceof LinearLayout)) {
            linearLayout = (LinearLayout) this.mBaseActivity.getLayoutInflater().inflate(R.layout.daily_giveaway_info_title, null);
            holder = new Holder();
            holder.title = (ThemedTextView) linearLayout.findViewById(R.id.daily_giveaway_info_title);
            linearLayout.setTag(holder);
        } else {
            linearLayout = (LinearLayout) view;
            holder = (Holder) linearLayout.getTag();
            while (linearLayout.getChildCount() > 1) {
                linearLayout.removeViewAt(1);
            }
        }
        if (ExperimentDataCenter.getInstance().shouldShowDailyRaffle()) {
            linearLayout.setBackgroundResource(R.color.gray6);
            holder.title.setBackgroundResource(R.color.gray6);
        }
        if (item != null) {
            holder.title.setText(item.getTitle());
            int i2 = item.getBulleted() ? R.layout.daily_giveaway_info_bulleted : R.layout.daily_giveaway_info_non_bulleted;
            Iterator it = item.getDescriptions().iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                View inflate = this.mBaseActivity.getLayoutInflater().inflate(i2, null);
                ((ThemedTextView) inflate.findViewById(R.id.daily_giveaway_info_description)).setText(str);
                linearLayout.addView(inflate);
            }
        }
        return linearLayout;
    }
}
