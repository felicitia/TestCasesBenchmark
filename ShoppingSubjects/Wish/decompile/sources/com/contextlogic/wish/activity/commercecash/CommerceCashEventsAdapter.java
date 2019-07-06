package com.contextlogic.wish.activity.commercecash;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.contextlogic.wish.api.model.WishCommerceCashEvent;
import com.contextlogic.wish.api.model.WishCommerceCashHistory;
import java.util.ArrayList;
import java.util.List;

public class CommerceCashEventsAdapter extends BaseAdapter {
    private CommerceCashActivity mBaseActivity;
    private List<WishCommerceCashEvent> mEvents = new ArrayList();

    public long getItemId(int i) {
        return 0;
    }

    CommerceCashEventsAdapter(CommerceCashActivity commerceCashActivity) {
        this.mBaseActivity = commerceCashActivity;
    }

    public void setEvents(WishCommerceCashHistory wishCommerceCashHistory) {
        if (wishCommerceCashHistory != null) {
            this.mEvents.addAll(wishCommerceCashHistory.getEvents());
            notifyDataSetChanged();
        }
    }

    public void clearEvents() {
        this.mEvents.clear();
    }

    public int getCount() {
        if (this.mEvents != null) {
            return this.mEvents.size();
        }
        return 0;
    }

    public WishCommerceCashEvent getItem(int i) {
        if (i < 0 || i >= getCount()) {
            return null;
        }
        return (WishCommerceCashEvent) this.mEvents.get(i);
    }

    public CommerceCashEventView getView(int i, View view, ViewGroup viewGroup) {
        CommerceCashEventView commerceCashEventView;
        if (view instanceof CommerceCashEventView) {
            commerceCashEventView = (CommerceCashEventView) view;
        } else {
            commerceCashEventView = new CommerceCashEventView(this.mBaseActivity);
        }
        WishCommerceCashEvent item = getItem(i);
        if (item != null) {
            commerceCashEventView.setupEvent(item);
        }
        return commerceCashEventView;
    }
}
