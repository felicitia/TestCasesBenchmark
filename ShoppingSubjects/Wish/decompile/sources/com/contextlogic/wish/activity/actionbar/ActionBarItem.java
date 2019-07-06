package com.contextlogic.wish.activity.actionbar;

import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.application.WishApplication;

public class ActionBarItem {
    private int mActionId;
    private int mBadgeCount;
    private boolean mCanAnimate;
    private boolean mHasAnimated;
    private Drawable mIconDrawable;
    private boolean mShowAsOverflow;
    private String mTitle;

    public ActionBarItem(String str, int i, int i2) {
        this(str, i, ContextCompat.getDrawable(WishApplication.getInstance(), i2), 0, false);
    }

    public ActionBarItem(String str, int i, Drawable drawable, int i2, boolean z) {
        this.mTitle = str;
        this.mActionId = i;
        this.mIconDrawable = drawable;
        this.mBadgeCount = i2;
        this.mCanAnimate = z;
        this.mHasAnimated = false;
    }

    public ActionBarItem(String str, int i) {
        this.mTitle = str;
        this.mActionId = i;
        this.mIconDrawable = null;
        this.mShowAsOverflow = true;
        this.mBadgeCount = 0;
        this.mCanAnimate = false;
        this.mHasAnimated = false;
    }

    public ActionBarItem(String str, int i, Drawable drawable) {
        this.mTitle = str;
        this.mActionId = i;
        this.mIconDrawable = drawable;
        this.mShowAsOverflow = false;
        this.mBadgeCount = 0;
        this.mCanAnimate = false;
        this.mHasAnimated = false;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public Drawable getIconDrawable() {
        return this.mIconDrawable;
    }

    public boolean shouldShowAsOverflow() {
        return this.mShowAsOverflow;
    }

    public int getActionId() {
        return this.mActionId;
    }

    public int getBadgeCount() {
        return this.mBadgeCount;
    }

    public boolean canAnimate() {
        return this.mCanAnimate && !this.mHasAnimated;
    }

    public void markAsAnimated() {
        this.mHasAnimated = true;
    }

    public static ActionBarItem createCartActionBarItem(ActionBarManager actionBarManager, boolean z) {
        Drawable drawable = ContextCompat.getDrawable(WishApplication.getInstance(), R.drawable.action_bar_cart);
        drawable.setColorFilter(actionBarManager.getTextColor(), Mode.SRC_ATOP);
        ActionBarItem actionBarItem = new ActionBarItem(WishApplication.getInstance().getString(R.string.cart), 1000, drawable, ExperimentDataCenter.getInstance().shouldShowRedDotOnCartActionBarIcon() ? StatusDataCenter.getInstance().getCartCount() : 0, z);
        return actionBarItem;
    }

    public static ActionBarItem createSearchActionBarItem(ActionBarManager actionBarManager) {
        Drawable drawable = ContextCompat.getDrawable(WishApplication.getInstance(), R.drawable.action_bar_search);
        drawable.setColorFilter(actionBarManager.getTextColor(), Mode.SRC_ATOP);
        return new ActionBarItem(WishApplication.getInstance().getString(R.string.search), -1000, drawable);
    }

    public static ActionBarItem createFilterActionBarItem(ActionBarManager actionBarManager) {
        Drawable drawable = ContextCompat.getDrawable(WishApplication.getInstance(), R.drawable.action_bar_filter);
        drawable.setColorFilter(actionBarManager.getTextColor(), Mode.SRC_ATOP);
        return new ActionBarItem(WishApplication.getInstance().getString(R.string.filter), 2000, drawable);
    }

    public static ActionBarItem createCameraActionBarItem(ActionBarManager actionBarManager) {
        Drawable drawable = ContextCompat.getDrawable(WishApplication.getInstance(), R.drawable.camera_icon);
        drawable.setColorFilter(actionBarManager.getTextColor(), Mode.SRC_ATOP);
        return new ActionBarItem(WishApplication.getInstance().getString(R.string.camera), 2001, drawable);
    }

    public static ActionBarItem createLeranMoreBarItem(ActionBarManager actionBarManager) {
        Drawable drawable = ContextCompat.getDrawable(WishApplication.getInstance(), R.drawable.learnmore_16);
        drawable.setColorFilter(actionBarManager.getTextColor(), Mode.SRC_ATOP);
        return new ActionBarItem(WishApplication.getInstance().getString(R.string.learn_more), 2002, drawable);
    }
}
