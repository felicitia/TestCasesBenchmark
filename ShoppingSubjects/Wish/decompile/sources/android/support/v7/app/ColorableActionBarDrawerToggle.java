package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle.DelegateProvider;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;

public class ColorableActionBarDrawerToggle extends ActionBarDrawerToggle {
    private final DrawerArrowDrawable mSlider = getDrawerArrowDrawable();

    public ColorableActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, int i, int i2) {
        super(activity, null, drawerLayout, new DrawerArrowDrawable(getActionBarThemedContext(activity)), i, i2);
    }

    private static Context getActionBarThemedContext(Activity activity) {
        if (activity instanceof DelegateProvider) {
            return ((DelegateProvider) activity).getDrawerToggleDelegate().getActionBarThemedContext();
        }
        ActionBar actionBar = activity.getActionBar();
        return actionBar != null ? actionBar.getThemedContext() : activity;
    }

    public void setColor(int i) {
        if (this.mSlider != null) {
            this.mSlider.setColor(i);
        }
    }
}
