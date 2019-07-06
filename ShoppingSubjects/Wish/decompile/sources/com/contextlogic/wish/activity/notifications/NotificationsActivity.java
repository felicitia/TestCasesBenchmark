package com.contextlogic.wish.activity.notifications;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.analytics.GoogleAnalyticsLogger.PageViewType;

public class NotificationsActivity extends DrawerActivity {
    public int getBottomNavigationTabIndex() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public ServiceFragment createServiceFragment() {
        return new NotificationsServiceFragment();
    }

    /* access modifiers changed from: protected */
    public UiFragment createMainContentFragment() {
        return new NotificationsFragment();
    }

    public String getActionBarTitle() {
        return getString(R.string.notification);
    }

    public String getMenuKey() {
        return MenuFragment.MENU_KEY_NOTIFICATIONS;
    }

    public PageViewType getGoogleAnalyticsPageViewType() {
        return PageViewType.NOTIFICATIONS;
    }

    public int getAutoscrollNotificationType() {
        return getIntent().getIntExtra("ExtraAutoscrollNotificationType", -1);
    }
}
