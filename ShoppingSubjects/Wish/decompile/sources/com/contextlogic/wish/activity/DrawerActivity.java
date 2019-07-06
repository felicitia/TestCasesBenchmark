package com.contextlogic.wish.activity;

import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.actionbar.ActionBarManager;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.ActionBarUpdatedListener;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.DrawerListener;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.HomeButtonMode;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.bottomnavigation.BottomNavigationView;
import com.contextlogic.wish.util.DisplayUtil;

public abstract class DrawerActivity extends BaseActivity implements ActionBarUpdatedListener, DrawerListener {
    private FrameLayout mAuthenticatingView;
    protected BottomNavigationView mBottomNavigationView;
    private View mContentView;
    private DrawerLayout mDrawerLayout;
    private boolean mDrawersLocked;
    private boolean mHasRightDrawer;
    private boolean mMenuOpen;
    private boolean mRightDrawerOpen;
    private Toolbar mToolbar;
    private View mToolbarBadge;
    private View mToolbarContainer;
    private View mToolbarMask;
    private View mUnderToolbarContainer;

    /* access modifiers changed from: protected */
    public boolean canHaveActionBar() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean canHaveMenu() {
        return true;
    }

    public String getActionBarTitle() {
        return null;
    }

    public int getBottomNavigationTabIndex() {
        return -1;
    }

    public String getMenuKey() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void initializeCoreUi(Bundle bundle) {
        setContentView((int) R.layout.drawer_activity);
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_activity_drawer_layout);
        this.mDrawerLayout.setBackgroundColor(getBackgroundColor());
        if (showingAsModal()) {
            if (((double) getResources().getFraction(R.fraction.modal_width, 1, 1)) != 1.0d) {
                ViewGroup viewGroup = (ViewGroup) this.mDrawerLayout.getParent();
                viewGroup.removeView(this.mDrawerLayout);
                FrameLayout frameLayout = new FrameLayout(this);
                frameLayout.setFitsSystemWindows(true);
                frameLayout.addView(this.mDrawerLayout);
                viewGroup.addView(frameLayout, new LayoutParams(-1, -1));
                resizeTabletModalView();
            }
        } else if (canHaveActionBar()) {
            getWindow().getDecorView().setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.transparent));
        }
        getActionBarManager().initializeActionBarDrawerToggle(this.mDrawerLayout);
        getActionBarManager().addDrawerListener(this);
        getActionBarManager().setActionBarUpdatedListener(this);
        this.mContentView = findViewById(R.id.drawer_activity_content_view);
        this.mAuthenticatingView = (FrameLayout) findViewById(R.id.drawer_activity_authenticating_view);
        this.mToolbar = (Toolbar) findViewById(R.id.drawer_activity_toolbar);
        this.mToolbarBadge = findViewById(R.id.drawer_activity_toolbar_badge);
        this.mToolbarContainer = findViewById(R.id.drawer_activity_toolbar_container);
        this.mToolbarMask = findViewById(R.id.drawer_activity_toolbar_mask);
        this.mUnderToolbarContainer = findViewById(R.id.drawer_activity_under_toolbar_container);
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            View findViewById = this.mAuthenticatingView.findViewById(R.id.drawer_activity_primary_progress_bar);
            View findViewById2 = this.mAuthenticatingView.findViewById(R.id.drawer_activity_three_dot_progress_bar);
            if (!(findViewById2 == null || findViewById == null)) {
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
            }
        }
        if (canHaveActionBar()) {
            setSupportActionBar(this.mToolbar);
            getActionBarManager().initializeBadge(this.mToolbarBadge);
            if (!(this.mToolbar == null || this.mToolbar.getOverflowIcon() == null)) {
                getActionBarManager().setOverflowIcon(this.mToolbar.getOverflowIcon());
            }
            getSupportActionBar().setHomeButtonEnabled(false);
            if (getActionBarTitle() != null) {
                getActionBarManager().setTitle(getActionBarTitle());
            } else {
                getActionBarManager().setTitle("");
            }
        }
        updateActionBarLayout();
    }

    public View getToolbarContainer() {
        return this.mToolbarContainer;
    }

    public void onGlobalLayout() {
        super.onGlobalLayout();
        resizeTabletModalView();
    }

    public void onActionBarUpdated() {
        updateActionBarLayout();
    }

    private void updateActionBarLayout() {
        if (this.mToolbar != null) {
            int i = 0;
            if (canHaveActionBar()) {
                this.mToolbarContainer.setVisibility(0);
                if (getActionBarManager() == null || !getActionBarManager().hasTransparentActionBar()) {
                    TypedValue typedValue = new TypedValue();
                    if (getTheme().resolveAttribute(16843499, typedValue, true)) {
                        i = TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics());
                    }
                }
            } else {
                this.mToolbarContainer.setVisibility(8);
            }
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mUnderToolbarContainer.getLayoutParams();
            layoutParams.topMargin = i;
            this.mUnderToolbarContainer.setLayoutParams(layoutParams);
        }
    }

    private void resizeTabletModalView() {
        double fraction = (double) getResources().getFraction(R.fraction.modal_width, 1, 1);
        View rootView = getRootView();
        if (showingAsModal() && fraction != 1.0d && rootView != null) {
            int min = (int) (((double) Math.min(DisplayUtil.getDisplayWidth(), DisplayUtil.getDisplayHeight())) * fraction);
            int min2 = rootView.getHeight() != 0 ? Math.min(min, rootView.getHeight()) : min;
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mDrawerLayout.getLayoutParams();
            layoutParams.width = min;
            layoutParams.height = min2;
            layoutParams.gravity = 17;
            this.mDrawerLayout.setLayoutParams(layoutParams);
        }
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        try {
            getActionBarManager().getActionBarDrawerToggle().syncState();
        } catch (Throwable unused) {
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            getActionBarManager().getActionBarDrawerToggle().onConfigurationChanged(configuration);
        } catch (Throwable unused) {
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeFragments() {
        super.initializeFragments();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        MenuFragment menuFragment = (MenuFragment) supportFragmentManager.findFragmentByTag("FragmentTagMenu");
        boolean z = false;
        if (menuFragment == null) {
            menuFragment = createMenuFragment();
            if (menuFragment != null) {
                supportFragmentManager.beginTransaction().add(R.id.drawer_activity_menu_view, menuFragment, "FragmentTagMenu").commitAllowingStateLoss();
                z = true;
            }
        }
        if (menuFragment != null) {
            registerUiFragment("FragmentTagMenu", menuFragment);
        }
        UiFragment uiFragment = (UiFragment) supportFragmentManager.findFragmentByTag("FragmentTagMainContent");
        if (uiFragment == null) {
            uiFragment = createMainContentFragment();
            if (uiFragment != null) {
                supportFragmentManager.beginTransaction().add(R.id.drawer_activity_content_view, uiFragment, "FragmentTagMainContent").commitAllowingStateLoss();
                z = true;
            }
        }
        if (uiFragment != null) {
            registerUiFragment("FragmentTagMainContent", uiFragment);
        }
        UiFragment uiFragment2 = (UiFragment) supportFragmentManager.findFragmentByTag("FragmentTagRightDrawer");
        if (uiFragment2 == null) {
            uiFragment2 = createRightDrawerFragment();
        }
        if (uiFragment2 != null) {
            setRightDrawerFragment(uiFragment2);
        }
        if (z) {
            try {
                supportFragmentManager.executePendingTransactions();
            } catch (IllegalStateException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void showContentView() {
        lockDrawers(false);
        this.mContentView.setVisibility(0);
        this.mAuthenticatingView.setVisibility(8);
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation() && !showingAsModal()) {
            ActionBarManager actionBarManager = getActionBarManager();
            if (actionBarManager.getHomeButtonMode() == HomeButtonMode.MENU_INDICATOR) {
                actionBarManager.setHomeButtonMode(HomeButtonMode.NO_ICON);
            }
            this.mDrawerLayout.closeDrawers();
            this.mDrawerLayout.setDrawerLockMode(1, 8388611);
            if (this.mBottomNavigationView == null) {
                setupBottomNavigation();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void showAuthenticatingView() {
        lockDrawers(true);
        this.mContentView.setVisibility(8);
        this.mAuthenticatingView.setVisibility(0);
    }

    public void onBackPressed() {
        if (this.mDrawerLayout != null && this.mDrawerLayout.isDrawerOpen(8388611)) {
            this.mDrawerLayout.closeDrawer(8388611);
        } else if (this.mDrawerLayout == null || !this.mDrawerLayout.isDrawerOpen(8388613)) {
            super.onBackPressed();
        } else {
            this.mDrawerLayout.closeDrawer(8388613);
        }
    }

    /* access modifiers changed from: protected */
    public boolean handleActionBarHomeSelected(MenuItem menuItem) {
        try {
            return getActionBarManager().getActionBarDrawerToggle().onOptionsItemSelected(menuItem);
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void initializeActionBarManager(ActionBarManager actionBarManager) {
        super.initializeActionBarManager(actionBarManager);
        if (canShowDrawerToggle()) {
            actionBarManager.setHomeButtonMode(HomeButtonMode.MENU_INDICATOR);
        } else if (canGoBack()) {
            actionBarManager.setHomeButtonMode(HomeButtonMode.BACK_ARROW);
        } else {
            actionBarManager.setHomeButtonMode(HomeButtonMode.NO_ICON);
        }
    }

    public void closeMenu() {
        this.mDrawerLayout.closeDrawer(8388611);
    }

    public void closeRightDrawer() {
        this.mDrawerLayout.closeDrawer(8388613);
    }

    public void openRightDrawer() {
        this.mDrawerLayout.openDrawer(8388613);
    }

    public void lockDrawers(boolean z) {
        if (z) {
            this.mToolbarMask.setVisibility(0);
            this.mDrawersLocked = true;
            this.mDrawerLayout.setDrawerLockMode(1);
            return;
        }
        this.mDrawersLocked = false;
        this.mToolbarMask.setVisibility(8);
        if (isMenuEnabled()) {
            this.mDrawerLayout.setDrawerLockMode(0, 8388611);
        } else {
            this.mDrawerLayout.setDrawerLockMode(1, 8388611);
        }
        if (hasRightDrawer()) {
            this.mDrawerLayout.setDrawerLockMode(0, 8388613);
        } else {
            this.mDrawerLayout.setDrawerLockMode(1, 8388613);
        }
    }

    public void lockRightDrawer() {
        this.mDrawerLayout.setDrawerLockMode(1, 8388613);
    }

    public void unlockRightDrawer() {
        this.mDrawerLayout.setDrawerLockMode(0, 8388613);
    }

    /* access modifiers changed from: protected */
    public MenuFragment createMenuFragment() {
        if (isMenuEnabled()) {
            return new MenuFragment();
        }
        return null;
    }

    private boolean canShowDrawerToggle() {
        return getMenuKey() != null && isMenuEnabled();
    }

    /* access modifiers changed from: protected */
    public boolean isMenuEnabled() {
        return canHaveMenu() && !showingAsModal();
    }

    public void setRightDrawerFragment(final UiFragment uiFragment) {
        if (uiFragment == null) {
            this.mHasRightDrawer = false;
            UiFragment uiFragment2 = getUiFragment("FragmentTagRightDrawer");
            if (uiFragment2 != null) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                supportFragmentManager.beginTransaction().remove(uiFragment2).commitAllowingStateLoss();
                try {
                    supportFragmentManager.executePendingTransactions();
                } catch (IllegalStateException unused) {
                }
            }
            unregisterUiFragment("FragmentTagRightDrawer");
        } else {
            FragmentManager supportFragmentManager2 = getSupportFragmentManager();
            supportFragmentManager2.beginTransaction().replace(R.id.drawer_activity_right_drawer_view, uiFragment, "FragmentTagRightDrawer").commitAllowingStateLoss();
            try {
                supportFragmentManager2.executePendingTransactions();
                registerUiFragment("FragmentTagRightDrawer", uiFragment);
                withVerifiedAuthentication(new Runnable() {
                    public void run() {
                        uiFragment.initializeIfNeccessary();
                    }
                });
                this.mHasRightDrawer = true;
            } catch (IllegalStateException unused2) {
            }
        }
        if (this.mDrawersLocked) {
            return;
        }
        if (hasRightDrawer()) {
            this.mDrawerLayout.setDrawerLockMode(0, 8388613);
        } else {
            this.mDrawerLayout.setDrawerLockMode(1, 8388613);
        }
    }

    private boolean hasRightDrawer() {
        return this.mHasRightDrawer;
    }

    public void onRightDrawerOpened() {
        this.mRightDrawerOpen = true;
    }

    public void onRightDrawerClosed() {
        this.mRightDrawerOpen = false;
    }

    public void onMenuOpened() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_SIDE_NAV_MENU);
        this.mMenuOpen = true;
    }

    public void onMenuClosed() {
        this.mMenuOpen = false;
    }

    public boolean isMenuOpen() {
        return this.mMenuOpen;
    }

    public int getBackgroundColor() {
        return getResources().getColor(R.color.white);
    }

    public void setupBottomNavigation() {
        this.mBottomNavigationView = (BottomNavigationView) findViewById(shouldUseDynamicBottomNavigationLayout() ? R.id.drawer_activity_bottom_navigation_dynamic : R.id.drawer_activity_bottom_navigation);
        this.mBottomNavigationView.setVisibility(0);
        this.mBottomNavigationView.setup(this);
        this.mBottomNavigationView.updateState(this, StatusDataCenter.getInstance().getCartCount());
    }

    public void setBottomNavigationVisible(boolean z) {
        if (this.mBottomNavigationView != null) {
            this.mBottomNavigationView.setVisibility(z ? 0 : 8);
        }
    }

    /* access modifiers changed from: protected */
    public void handleOnCreate(Bundle bundle) {
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation() && VERSION.SDK_INT >= 21) {
            getWindow().setAllowEnterTransitionOverlap(false);
            getWindow().setAllowReturnTransitionOverlap(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation() && this.mBottomNavigationView != null) {
            this.mBottomNavigationView.updateState(this, StatusDataCenter.getInstance().getCartCount());
        }
    }

    public void popInBottomNavigation(boolean z) {
        if (this.mBottomNavigationView != null) {
            if (z) {
                this.mBottomNavigationView.popIn();
            } else {
                this.mBottomNavigationView.setVisibility(0);
            }
        }
    }

    public void popOutBottomNavigation(boolean z) {
        if (this.mBottomNavigationView != null) {
            if (z) {
                this.mBottomNavigationView.popOut();
            } else {
                this.mBottomNavigationView.setVisibility(8);
            }
        }
    }

    public boolean isBottomNavigationVisible() {
        return this.mBottomNavigationView != null && this.mBottomNavigationView.getVisibility() == 0 && !this.mBottomNavigationView.isOutOfWindow();
    }
}
