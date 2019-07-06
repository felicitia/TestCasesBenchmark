package com.contextlogic.wish.activity.notifications;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.api.model.WishTag;
import java.util.ArrayList;
import java.util.Iterator;

public class NotificationsPagerAdapter extends PagerAdapter {
    private DrawerActivity mBaseActivity;
    private ArrayList<WishTag> mCategories;
    private ArrayList<NotificationsView> mNotificationViews = new ArrayList<>();
    private NotificationsFragment mNotificationsFragment;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public NotificationsPagerAdapter(DrawerActivity drawerActivity, NotificationsFragment notificationsFragment) {
        this.mBaseActivity = drawerActivity;
        this.mNotificationsFragment = notificationsFragment;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        NotificationsView notificationsView = new NotificationsView(this.mBaseActivity, this.mNotificationsFragment, (WishTag) this.mCategories.get(i), i);
        notificationsView.setTag(Integer.valueOf(i));
        viewGroup.addView(notificationsView, new LayoutParams(-1, -1));
        this.mNotificationViews.add(notificationsView);
        if (this.mNotificationsFragment.getCurrentIndex() == i) {
            notificationsView.handleResume();
        }
        return notificationsView;
    }

    public void updateCategories(ArrayList<WishTag> arrayList) {
        this.mCategories = arrayList;
        notifyDataSetChanged();
    }

    public void onDestroy() {
        Iterator it = this.mNotificationViews.iterator();
        while (it.hasNext()) {
            ((NotificationsView) it.next()).handleDestroy();
        }
    }

    public int getCount() {
        if (this.mCategories != null) {
            return this.mCategories.size();
        }
        return 0;
    }

    public String getPageTitle(int i) {
        return (this.mNotificationsFragment.getCategories() == null || i >= this.mNotificationsFragment.getCategories().size()) ? "" : ((WishTag) this.mNotificationsFragment.getCategories().get(i)).getName();
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        NotificationsView notificationsView = (NotificationsView) obj;
        notificationsView.handleDestroy();
        this.mNotificationViews.remove(notificationsView);
        viewGroup.removeView(notificationsView);
    }

    public NotificationsView getNotificationView(int i) {
        Iterator it = this.mNotificationViews.iterator();
        while (it.hasNext()) {
            NotificationsView notificationsView = (NotificationsView) it.next();
            if (notificationsView.getCurrentIndex() == i) {
                return notificationsView;
            }
        }
        return null;
    }

    public void handleSaveInstanceState(Bundle bundle) {
        for (int i = 0; i < this.mNotificationViews.size(); i++) {
            NotificationsView notificationsView = (NotificationsView) this.mNotificationViews.get(i);
            Bundle savedInstanceState = notificationsView.getSavedInstanceState();
            if (savedInstanceState != null) {
                bundle.putBundle(this.mNotificationsFragment.getPagedDataSavedInstanceStateKey(notificationsView.getCurrentIndex()), savedInstanceState);
            }
        }
    }

    public ArrayList<NotificationsView> getNotificationsViews() {
        return this.mNotificationViews;
    }
}
