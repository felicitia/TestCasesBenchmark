package com.contextlogic.wish.activity.actionbar;

import android.content.res.Configuration;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ColorableActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.SearchView.OnSuggestionListener;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.search.SearchBarCallback;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.ApplicationEventBundle;
import com.contextlogic.wish.application.ApplicationEventManager.ApplicationEventCallback;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.ActionBarItemView;
import com.contextlogic.wish.ui.view.SlidingTabStrip.TabType;
import com.contextlogic.wish.ui.viewpager.PagerSlidingTabStrip;
import com.contextlogic.wish.util.FontUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import com.crashlytics.android.Crashlytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActionBarManager implements ApplicationEventCallback {
    private ColorableActionBarDrawerToggle mActionBarDrawerToggle;
    private ArrayList<ActionBarItem> mActionBarItems = new ArrayList<>();
    /* access modifiers changed from: private */
    public ActionBarMode mActionBarMode;
    private ActionBarUpdatedListener mActionBarUpdatedListener;
    private Drawable mBackgroundDrawable;
    private View mBadge;
    /* access modifiers changed from: private */
    public BaseActivity mBaseActivity;
    /* access modifiers changed from: private */
    public List<DrawerListener> mDrawerListeners;
    private Drawable mFadeToBackgroundDrawable;
    private Theme mFadeToTheme;
    private boolean mHideStatusBar;
    private Drawable mHomeButtonDrawable;
    private HomeButtonMode mHomeButtonMode;
    private String mInitialSearchQuery;
    private float mLastInterpolationValue;
    private Menu mMenu;
    private Drawable mOverflowIcon;
    /* access modifiers changed from: private */
    public SearchBarCallback mSearchBarCallback;
    /* access modifiers changed from: private */
    public SearchView mSearchView;
    private Theme mTheme;
    private LayoutParams mTitleLayoutParams;
    private ActionBarTitleView mTitleView;

    public enum ActionBarMode {
        DEFAULT,
        MENU_OPEN,
        SEARCH
    }

    public interface ActionBarUpdatedListener {
        void onActionBarUpdated();
    }

    public interface DrawerListener {
        void onMenuClosed();

        void onMenuOpened();

        void onRightDrawerClosed();

        void onRightDrawerOpened();
    }

    public enum HomeButtonMode {
        MENU_INDICATOR,
        BACK_ARROW,
        X_ICON,
        NO_ICON
    }

    public enum Theme {
        APP_COLOR_BACKGROUND,
        WHITE_BACKGROUND,
        TRANSPARENT_BACKGROUND_LIGHT_BUTTONS,
        TRANSPARENT_BACKGROUND_DARK_BUTTONS,
        WISH_PARTNER_BACKGROUND
    }

    public boolean onBackPressed() {
        return false;
    }

    public ActionBarManager(BaseActivity baseActivity, Bundle bundle) {
        this.mBaseActivity = baseActivity;
        this.mTitleView = new ActionBarTitleView(baseActivity);
        this.mTitleLayoutParams = createTitleViewLayoutParams();
        this.mTheme = Theme.APP_COLOR_BACKGROUND;
        this.mBackgroundDrawable = createBackgroundDrawable(getBackgroundColor(this.mTheme), 0);
        this.mDrawerListeners = new ArrayList();
        ApplicationEventManager.getInstance().addCallback(EventType.DATA_CENTER_UPDATED, StatusDataCenter.getInstance().getClass().toString(), this);
        initializeValues(bundle);
    }

    private void initializeValues(Bundle bundle) {
        this.mActionBarMode = ActionBarMode.DEFAULT;
        this.mInitialSearchQuery = null;
        if (bundle != null) {
            if (bundle.containsKey("SavedStateActionBarMode")) {
                this.mActionBarMode = ActionBarMode.values()[bundle.getInt("SavedStateActionBarMode")];
            }
            this.mInitialSearchQuery = bundle.getString("SavedStateActionBarSearchQueryMode");
        }
    }

    public void initializeBadge(View view) {
        this.mBadge = view;
        updateBadge();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.mActionBarMode == ActionBarMode.SEARCH && this.mSearchView != null) {
            bundle.putInt("SavedStateActionBarMode", this.mActionBarMode.ordinal());
            bundle.putString("SavedStateActionBarSearchQueryMode", this.mSearchView.getQuery().toString());
        }
    }

    private void updateBadge() {
        if (this.mBadge != null) {
            if (StatusDataCenter.getInstance().getUnviewedNotificationCount() <= 0 || this.mHomeButtonMode == HomeButtonMode.NO_ICON || this.mBaseActivity.showingAsModal() || PreferenceUtil.getBoolean("HideNotificationRedDot", false) || !ExperimentDataCenter.getInstance().shouldShowRedDotOnMenuIcon()) {
                this.mBadge.setVisibility(8);
            } else {
                this.mBadge.setVisibility(0);
            }
        }
    }

    public void setBadgeVisible(boolean z) {
        if (this.mBadge != null) {
            this.mBadge.setVisibility(z ? 0 : 8);
            initializeBadge(z ? this.mBadge : null);
        }
    }

    public void initializeActionBarDrawerToggle(DrawerLayout drawerLayout) {
        AnonymousClass1 r0 = new ColorableActionBarDrawerToggle(this.mBaseActivity, drawerLayout, R.string.open_menu, R.string.close_menu) {
            private ActionBarMode mClosedActionBarMode;

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) view.getLayoutParams();
                if (layoutParams.gravity == 8388611) {
                    if (this.mClosedActionBarMode != null && ActionBarManager.this.mActionBarMode == ActionBarMode.MENU_OPEN) {
                        ActionBarManager.this.setMode(this.mClosedActionBarMode);
                    }
                    for (DrawerListener onMenuClosed : ActionBarManager.this.mDrawerListeners) {
                        onMenuClosed.onMenuClosed();
                    }
                } else if (layoutParams.gravity == 8388613) {
                    for (DrawerListener onRightDrawerClosed : ActionBarManager.this.mDrawerListeners) {
                        onRightDrawerClosed.onRightDrawerClosed();
                    }
                }
            }

            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) view.getLayoutParams();
                if (layoutParams.gravity == 8388611) {
                    this.mClosedActionBarMode = ActionBarManager.this.mActionBarMode;
                    ActionBarManager.this.setMode(ActionBarMode.MENU_OPEN);
                    for (DrawerListener onMenuOpened : ActionBarManager.this.mDrawerListeners) {
                        onMenuOpened.onMenuOpened();
                    }
                } else if (layoutParams.gravity == 8388613) {
                    for (DrawerListener onRightDrawerOpened : ActionBarManager.this.mDrawerListeners) {
                        onRightDrawerOpened.onRightDrawerOpened();
                    }
                }
            }
        };
        this.mActionBarDrawerToggle = r0;
        drawerLayout.addDrawerListener(this.mActionBarDrawerToggle);
        apply();
    }

    public ActionBarDrawerToggle getActionBarDrawerToggle() {
        return this.mActionBarDrawerToggle;
    }

    public void addDrawerListener(DrawerListener drawerListener) {
        this.mDrawerListeners.add(drawerListener);
    }

    public void setActionBarUpdatedListener(ActionBarUpdatedListener actionBarUpdatedListener) {
        this.mActionBarUpdatedListener = actionBarUpdatedListener;
    }

    public String getTitle() {
        return this.mTitleView.getText();
    }

    public void setTitle(String str) {
        this.mTitleView.setText(str);
        apply();
    }

    public void applyMenu(Menu menu) {
        this.mMenu = menu;
        menu.clear();
        if (this.mActionBarMode == ActionBarMode.DEFAULT) {
            Iterator it = this.mActionBarItems.iterator();
            while (it.hasNext()) {
                ActionBarItem actionBarItem = (ActionBarItem) it.next();
                int i = 0;
                final MenuItem add = menu.add(0, actionBarItem.getActionId(), 0, actionBarItem.getTitle());
                if (actionBarItem.getActionId() == 1000) {
                    add.setActionView(new ActionBarItemView(this.mBaseActivity, actionBarItem));
                    add.getActionView().setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            ActionBarManager.this.mBaseActivity.onMenuActionViewClicked((ActionBarItemView) view);
                        }
                    });
                    if (actionBarItem.canAnimate()) {
                        animateActionBarItem(add, actionBarItem);
                    }
                } else if (actionBarItem.getIconDrawable() != null) {
                    add.setIcon(actionBarItem.getIconDrawable());
                } else {
                    ThemedTextView themedTextView = new ThemedTextView(this.mBaseActivity);
                    themedTextView.setText(actionBarItem.getTitle());
                    themedTextView.setTextAppearance(this.mBaseActivity, 2131821006);
                    themedTextView.setTypeface(1);
                    int dimensionPixelSize = this.mBaseActivity.getResources().getDimensionPixelSize(R.dimen.eight_padding);
                    themedTextView.setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
                    add.setActionView(themedTextView);
                    add.getActionView().setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            ActionBarManager.this.mBaseActivity.onOptionsItemSelected(add);
                        }
                    });
                }
                if (!actionBarItem.shouldShowAsOverflow()) {
                    i = 2;
                }
                add.setShowAsAction(i);
            }
        }
        if (this.mFadeToTheme == null || this.mActionBarUpdatedListener == null) {
            tintItems(getTextColor(this.mTheme), getNavIconTintColor(this.mTheme));
            return;
        }
        interpolateBackground(this.mLastInterpolationValue);
        this.mActionBarUpdatedListener.onActionBarUpdated();
    }

    private void animateActionBarItem(MenuItem menuItem, ActionBarItem actionBarItem) {
        actionBarItem.markAsAnimated();
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(200);
        scaleAnimation.setInterpolator(new DecelerateInterpolator());
        final ScaleAnimation scaleAnimation2 = new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation2.setDuration(500);
        scaleAnimation2.setInterpolator(new BounceInterpolator());
        final View actionView = menuItem.getActionView();
        scaleAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (actionView != null) {
                    actionView.startAnimation(scaleAnimation2);
                }
            }
        });
        if (actionView != null) {
            actionView.startAnimation(scaleAnimation);
        }
    }

    public void updateCartIcon(boolean z) {
        int i = 0;
        while (true) {
            if (i >= this.mActionBarItems.size()) {
                break;
            } else if (((ActionBarItem) this.mActionBarItems.get(i)).getActionId() == 1000) {
                this.mActionBarItems.set(i, ActionBarItem.createCartActionBarItem(this, z));
                break;
            } else {
                i++;
            }
        }
        apply();
    }

    public HomeButtonMode getHomeButtonMode() {
        return this.mHomeButtonMode;
    }

    public void setHomeButtonMode(HomeButtonMode homeButtonMode) {
        this.mHomeButtonMode = homeButtonMode;
        apply();
    }

    public void setMode(ActionBarMode actionBarMode) {
        this.mActionBarMode = actionBarMode;
        apply();
    }

    private LayoutParams createTitleViewLayoutParams() {
        return new LayoutParams(8388611);
    }

    private SearchView getSearchView() {
        if (this.mSearchView == null) {
            this.mSearchView = new SearchView(this.mBaseActivity);
            this.mSearchView.setIconifiedByDefault(false);
            this.mSearchView.setIconified(false);
            this.mSearchView.setOnQueryTextFocusChangeListener(new OnFocusChangeListener() {
                public void onFocusChange(View view, boolean z) {
                    if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation() && ActionBarManager.this.mBaseActivity.shouldUseDynamicBottomNavigationLayout()) {
                        ((DrawerActivity) ActionBarManager.this.mBaseActivity).setBottomNavigationVisible(!z);
                    }
                }
            });
            this.mSearchView.setOnQueryTextListener(new OnQueryTextListener() {
                public boolean onQueryTextSubmit(String str) {
                    if (!(str == null || str.trim().equals("") || ActionBarManager.this.mSearchBarCallback == null)) {
                        ActionBarManager.this.mSearchBarCallback.onSearchSubmit(str);
                    }
                    return true;
                }

                public boolean onQueryTextChange(String str) {
                    if (ActionBarManager.this.mSearchBarCallback != null) {
                        ActionBarManager.this.mSearchBarCallback.onQueryChanged(str);
                    }
                    return true;
                }
            });
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) this.mSearchView.findViewById(R.id.search_src_text);
            if (this.mSearchBarCallback != null) {
                if (ExperimentDataCenter.getInstance().shouldSeeSearchHistoryInAutocomplete()) {
                    if (autoCompleteTextView != null) {
                        autoCompleteTextView.setThreshold(0);
                    } else {
                        Crashlytics.logException(new Exception("Action Bar Manager - Can not find AutoCompleteTextView in Search"));
                    }
                }
                this.mSearchView.setSuggestionsAdapter(this.mSearchBarCallback.getSearchTypeaheadAdapter());
                this.mSearchView.setOnSuggestionListener(new OnSuggestionListener() {
                    public boolean onSuggestionSelect(int i) {
                        return false;
                    }

                    public boolean onSuggestionClick(int i) {
                        if (ActionBarManager.this.mSearchBarCallback != null) {
                            ActionBarManager.this.mSearchBarCallback.handleSearchTypeaheadClick(i);
                        }
                        return true;
                    }
                });
            }
            ((LinearLayout.LayoutParams) ((LinearLayout) this.mSearchView.findViewById(R.id.search_edit_frame)).getLayoutParams()).leftMargin = 0;
            boolean z = getTheme() == Theme.WHITE_BACKGROUND || getTheme() == Theme.TRANSPARENT_BACKGROUND_DARK_BUTTONS;
            if (autoCompleteTextView != null) {
                autoCompleteTextView.setTextSize(0, this.mBaseActivity.getResources().getDimension(R.dimen.action_bar_title_size));
                autoCompleteTextView.setTypeface(FontUtil.getTypefaceForStyle(0));
                autoCompleteTextView.setHint(this.mBaseActivity.getString(R.string.search));
                autoCompleteTextView.setHintTextColor(ContextCompat.getColor(this.mBaseActivity, z ? R.color.gray4 : R.color.translucent_white));
                autoCompleteTextView.setTextColor(ContextCompat.getColor(this.mBaseActivity, z ? R.color.gray1 : R.color.white));
            }
            View findViewById = this.mSearchView.findViewById(R.id.search_plate);
            if (findViewById != null) {
                findViewById.setBackgroundResource(R.color.transparent);
            }
            ImageView imageView = (ImageView) this.mSearchView.findViewById(R.id.search_mag_icon);
            ImageView imageView2 = (ImageView) this.mSearchView.findViewById(R.id.search_close_btn);
            if (z) {
                if (!(imageView == null || imageView.getDrawable() == null)) {
                    imageView.getDrawable().setColorFilter(getTextColor(), Mode.SRC_ATOP);
                }
                if (!(imageView2 == null || imageView2.getDrawable() == null)) {
                    imageView2.getDrawable().setColorFilter(getTextColor(), Mode.SRC_ATOP);
                }
            }
            if (this.mInitialSearchQuery != null) {
                this.mSearchView.setQuery(this.mInitialSearchQuery, false);
                this.mInitialSearchQuery = null;
            }
            this.mSearchView.setBackgroundResource(R.drawable.search_bar_background);
            this.mSearchView.findViewById(R.id.search_src_text).setBackgroundResource(R.color.transparent);
        }
        return this.mSearchView;
    }

    private LayoutParams createSearchViewLayoutParams() {
        return new LayoutParams(-1, -1, 8388611);
    }

    private void requestSearchFocus() {
        if (this.mSearchView != null) {
            this.mSearchView.post(new Runnable() {
                public void run() {
                    ActionBarManager.this.mSearchView.requestFocus();
                }
            });
        }
    }

    public void clearSearchFocus() {
        if (this.mSearchView != null) {
            this.mSearchView.post(new Runnable() {
                public void run() {
                    ActionBarManager.this.mSearchView.clearFocus();
                }
            });
        }
    }

    public void clearActionBarItems() {
        this.mActionBarItems.clear();
        apply();
    }

    public void clearRightActionBarItems() {
        this.mActionBarItems.clear();
    }

    public void clearCartInActionBarItems() {
        Iterator it = this.mActionBarItems.iterator();
        while (it.hasNext()) {
            ActionBarItem actionBarItem = (ActionBarItem) it.next();
            if (actionBarItem.getActionId() == 1000) {
                this.mActionBarItems.remove(actionBarItem);
            }
        }
    }

    public void addDefaultActionBarItems() {
        if (!ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
            addActionBarItem(ActionBarItem.createCartActionBarItem(this, false));
        }
    }

    public void addActionBarItem(ActionBarItem actionBarItem) {
        this.mActionBarItems.add(actionBarItem);
        apply();
    }

    public void addActionBarItem(int i, ActionBarItem actionBarItem) {
        this.mActionBarItems.add(i, actionBarItem);
        apply();
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.mActionBarDrawerToggle != null) {
            this.mActionBarDrawerToggle.onConfigurationChanged(configuration);
        }
    }

    private Drawable createBackgroundDrawable(int i, int i2) {
        if (i2 == 0) {
            return new ColorDrawable(i);
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{new ColorDrawable(i2), new ColorDrawable(i)});
        layerDrawable.setLayerInset(1, 0, 0, 0, this.mBaseActivity.getResources().getDimensionPixelSize(R.dimen.default_border_stroke));
        return layerDrawable;
    }

    public void apply() {
        ActionBar supportActionBar = this.mBaseActivity.getSupportActionBar();
        if (supportActionBar != null) {
            try {
                supportActionBar.setDisplayShowHomeEnabled(true);
                if (this.mFadeToBackgroundDrawable != null) {
                    supportActionBar.setBackgroundDrawable(new LayerDrawable(new Drawable[]{this.mFadeToBackgroundDrawable, this.mBackgroundDrawable}));
                } else {
                    supportActionBar.setBackgroundDrawable(this.mBackgroundDrawable);
                }
                supportActionBar.setDisplayUseLogoEnabled(false);
                supportActionBar.setTitle((CharSequence) null);
                supportActionBar.setSubtitle(null);
                supportActionBar.setDisplayShowTitleEnabled(false);
                this.mActionBarDrawerToggle.setColor(getNavIconTintColor(this.mTheme));
                updateHomeButtonDrawable();
                updateBackButtonColor();
                colorStatusBar();
                if (this.mActionBarMode == ActionBarMode.SEARCH) {
                    if (this.mActionBarDrawerToggle != null) {
                        this.mActionBarDrawerToggle.setDrawerIndicatorEnabled(this.mHomeButtonMode == HomeButtonMode.MENU_INDICATOR);
                    }
                    supportActionBar.setDisplayShowCustomEnabled(true);
                    supportActionBar.setCustomView(getSearchView(), createSearchViewLayoutParams());
                    supportActionBar.setDisplayHomeAsUpEnabled(true);
                    supportActionBar.setHomeButtonEnabled(true);
                } else if (this.mActionBarMode == ActionBarMode.DEFAULT) {
                    if (this.mActionBarDrawerToggle != null) {
                        this.mActionBarDrawerToggle.setDrawerIndicatorEnabled(this.mHomeButtonMode == HomeButtonMode.MENU_INDICATOR);
                    }
                    if (this.mTitleView != null) {
                        supportActionBar.setDisplayShowCustomEnabled(true);
                        if (supportActionBar.getCustomView() != this.mTitleView) {
                            supportActionBar.setCustomView(this.mTitleView, this.mTitleLayoutParams);
                        }
                    } else {
                        supportActionBar.setDisplayShowCustomEnabled(false);
                    }
                }
                if (this.mTitleView != null) {
                    this.mTitleView.setTextColor(getTextColor());
                }
                switch (this.mHomeButtonMode) {
                    case BACK_ARROW:
                        supportActionBar.setDisplayHomeAsUpEnabled(true);
                        supportActionBar.setHomeButtonEnabled(true);
                        break;
                    case MENU_INDICATOR:
                        supportActionBar.setDisplayHomeAsUpEnabled(true);
                        supportActionBar.setHomeButtonEnabled(true);
                        break;
                    case X_ICON:
                        supportActionBar.setDisplayHomeAsUpEnabled(true);
                        supportActionBar.setHomeButtonEnabled(true);
                        break;
                    case NO_ICON:
                        supportActionBar.setDisplayHomeAsUpEnabled(false);
                        supportActionBar.setHomeButtonEnabled(false);
                        break;
                }
                this.mBaseActivity.supportInvalidateOptionsMenu();
                updateBadge();
                if (this.mActionBarUpdatedListener != null) {
                    this.mActionBarUpdatedListener.onActionBarUpdated();
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void setSearchBarCallback(SearchBarCallback searchBarCallback) {
        this.mSearchBarCallback = searchBarCallback;
    }

    private void startSearch(boolean z) {
        setMode(ActionBarMode.SEARCH);
        if (z) {
            requestSearchFocus();
        } else {
            clearSearchFocus();
        }
    }

    public void startSearch(boolean z, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mInitialSearchQuery = str;
        }
        startSearch(z);
    }

    public void setTheme(Theme theme) {
        setTheme(theme, 0);
    }

    public void setTheme(Theme theme, int i) {
        this.mTheme = theme;
        this.mBackgroundDrawable = createBackgroundDrawable(getBackgroundColor(this.mTheme), i);
        apply();
    }

    public void setFadeToTheme(Theme theme) {
        if (theme == Theme.WHITE_BACKGROUND) {
            setFadeToTheme(theme, ContextCompat.getColor(this.mBaseActivity, R.color.gray5));
        } else {
            setFadeToTheme(theme, 0);
        }
    }

    public void setFadeToTheme(Theme theme, int i) {
        Drawable drawable;
        this.mFadeToTheme = theme;
        if (theme == null) {
            drawable = null;
        } else {
            drawable = createBackgroundDrawable(getBackgroundColor(this.mFadeToTheme), i);
        }
        this.mFadeToBackgroundDrawable = drawable;
        apply();
    }

    public Theme getTheme() {
        return this.mTheme;
    }

    private int getBackgroundColor(Theme theme) {
        if (theme == Theme.WHITE_BACKGROUND) {
            return -1;
        }
        if (theme == Theme.TRANSPARENT_BACKGROUND_DARK_BUTTONS || theme == Theme.TRANSPARENT_BACKGROUND_LIGHT_BUTTONS) {
            return 0;
        }
        if (theme == Theme.WISH_PARTNER_BACKGROUND) {
            return this.mBaseActivity.getResources().getColor(R.color.wish_partner_main_blue);
        }
        return this.mBaseActivity.getResources().getColor(R.color.main_primary);
    }

    public int getTextColor() {
        return getTextColor(getTheme());
    }

    public int getTextColor(Theme theme) {
        if (theme == Theme.WHITE_BACKGROUND || theme == Theme.TRANSPARENT_BACKGROUND_DARK_BUTTONS) {
            return this.mBaseActivity.getResources().getColor(R.color.text_primary);
        }
        return this.mBaseActivity.getResources().getColor(R.color.white);
    }

    private int getNavIconTintColor(Theme theme) {
        return getTextColor(theme);
    }

    private void updateBackButtonColor() {
        if (this.mHomeButtonDrawable != null) {
            this.mHomeButtonDrawable.setColorFilter(getNavIconTintColor(this.mTheme), Mode.SRC_ATOP);
        }
        this.mActionBarDrawerToggle.setHomeAsUpIndicator(this.mHomeButtonDrawable);
    }

    private void updateHomeButtonDrawable() {
        if (this.mHomeButtonMode == HomeButtonMode.X_ICON) {
            this.mHomeButtonDrawable = this.mBaseActivity.getResources().getDrawable(R.drawable.actionbar_close_x);
        } else {
            this.mHomeButtonDrawable = this.mBaseActivity.getResources().getDrawable(R.drawable.ic_back);
        }
    }

    public void onApplicationEventReceived(EventType eventType, String str, ApplicationEventBundle applicationEventBundle) {
        updateBadge();
    }

    public void interpolateBackground(float f) {
        if (this.mFadeToTheme != null && this.mTheme != this.mFadeToTheme && this.mFadeToBackgroundDrawable != null) {
            this.mLastInterpolationValue = f;
            this.mBackgroundDrawable.setAlpha((int) ((1.0f - f) * 255.0f));
            this.mFadeToBackgroundDrawable.setAlpha((int) (255.0f * f));
            tintItems(ColorUtils.blendARGB(getTextColor(this.mTheme), getTextColor(this.mFadeToTheme), f), ColorUtils.blendARGB(getNavIconTintColor(this.mTheme), getNavIconTintColor(this.mFadeToTheme), f));
        }
    }

    private void tintItems(int i, int i2) {
        if (this.mTitleView != null) {
            this.mTitleView.setTextColor(i);
        }
        if (this.mActionBarDrawerToggle != null) {
            this.mActionBarDrawerToggle.setColor(i2);
        }
        if (this.mOverflowIcon != null) {
            this.mOverflowIcon.setColorFilter(i, Mode.SRC_ATOP);
        }
        if (this.mHomeButtonDrawable != null) {
            this.mHomeButtonDrawable.setColorFilter(i2, Mode.SRC_ATOP);
        }
        int size = this.mMenu == null ? 0 : this.mMenu.size();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItem item = this.mMenu.getItem(i3);
            if (item.getIcon() != null) {
                item.getIcon().setColorFilter(i, Mode.SRC_ATOP);
            } else if (item.getActionView() instanceof ActionBarItemView) {
                ((ActionBarItemView) item.getActionView()).setIconColor(i);
            } else if (item.getActionView() instanceof TextView) {
                ((TextView) item.getActionView()).setTextColor(i);
            }
        }
    }

    private void colorStatusBar() {
        Window window = this.mBaseActivity == null ? null : this.mBaseActivity.getWindow();
        if (window != null && window.getDecorView() != null) {
            if (VERSION.SDK_INT >= 21 && this.mHideStatusBar) {
                window.getDecorView().setSystemUiVisibility(1280);
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(0);
            } else if (VERSION.SDK_INT >= 23 && (getTheme() == Theme.WHITE_BACKGROUND || this.mFadeToTheme == Theme.WHITE_BACKGROUND)) {
                window.getDecorView().setSystemUiVisibility(8192);
                window.setStatusBarColor(-1);
            } else if (VERSION.SDK_INT >= 23) {
                window.getDecorView().setSystemUiVisibility(0);
                window.setStatusBarColor(ContextCompat.getColor(this.mBaseActivity, R.color.main_dark));
            } else if (VERSION.SDK_INT >= 21) {
                window.setStatusBarColor(-16777216);
            }
        }
    }

    public void setHideStatusbar(boolean z) {
        if (this.mHideStatusBar != z) {
            this.mHideStatusBar = z;
            colorStatusBar();
        }
    }

    public boolean isStatusBarHidden() {
        return this.mHideStatusBar && VERSION.SDK_INT >= 21;
    }

    public void setOverflowIcon(Drawable drawable) {
        this.mOverflowIcon = drawable;
    }

    public boolean hasTransparentActionBar() {
        return this.mTheme == Theme.TRANSPARENT_BACKGROUND_LIGHT_BUTTONS || this.mTheme == Theme.TRANSPARENT_BACKGROUND_DARK_BUTTONS;
    }

    public void hideDivider() {
        setTheme(getTheme(), 0);
    }

    public void stylizeTabStrip(PagerSlidingTabStrip pagerSlidingTabStrip) {
        stylizeTabStrip(pagerSlidingTabStrip, false);
    }

    public void stylizeTabStrip(PagerSlidingTabStrip pagerSlidingTabStrip, boolean z) {
        int dimensionPixelOffset = this.mBaseActivity.getResources().getDimensionPixelOffset(R.dimen.tab_strip_indicator_height);
        int dimensionPixelOffset2 = this.mBaseActivity.getResources().getDimensionPixelOffset(R.dimen.tab_strip_text_size);
        pagerSlidingTabStrip.setIndicatorHeight(dimensionPixelOffset);
        pagerSlidingTabStrip.setTextSize(dimensionPixelOffset2);
        Theme theme = (!z || this.mFadeToTheme == null) ? this.mTheme : this.mFadeToTheme;
        if (theme == Theme.WHITE_BACKGROUND) {
            pagerSlidingTabStrip.setBackgroundResource(R.color.white);
            pagerSlidingTabStrip.setIndicatorColorResource(R.color.main_primary);
            pagerSlidingTabStrip.setDividerColorResource(R.color.white);
            pagerSlidingTabStrip.setTextColorResource(R.color.text_primary);
            pagerSlidingTabStrip.setUnderlineHeight(1);
            hideDivider();
        } else if (theme == Theme.APP_COLOR_BACKGROUND) {
            pagerSlidingTabStrip.setBackgroundResource(R.color.main_primary);
            pagerSlidingTabStrip.setIndicatorColorResource(R.color.white);
            pagerSlidingTabStrip.setDividerColorResource(R.color.main_primary);
            pagerSlidingTabStrip.setTextColorResource(R.color.white);
            pagerSlidingTabStrip.setUnderlineHeight(0);
        } else if (theme == Theme.WISH_PARTNER_BACKGROUND) {
            pagerSlidingTabStrip.setBackgroundResource(R.color.wish_partner_main_blue);
            pagerSlidingTabStrip.setIndicatorColorResource(R.color.white);
            pagerSlidingTabStrip.setDividerColorResource(R.color.wish_partner_main_blue);
            pagerSlidingTabStrip.setTextColorResource(R.color.white);
            pagerSlidingTabStrip.setUnderlineHeight(0);
        }
    }

    public void refreshTabStripFontColors(PagerSlidingTabStrip pagerSlidingTabStrip, int i) {
        refreshTabStripFontColors(pagerSlidingTabStrip, i, false);
    }

    public void refreshTabStripFontColors(PagerSlidingTabStrip pagerSlidingTabStrip, int i, boolean z) {
        TextView textView;
        LinearLayout linearLayout = (LinearLayout) pagerSlidingTabStrip.getChildAt(0);
        Theme theme = (!z || this.mFadeToTheme == null) ? this.mTheme : this.mFadeToTheme;
        int i2 = 0;
        while (i2 < linearLayout.getChildCount()) {
            if (pagerSlidingTabStrip.getTabType(i2) == TabType.TEXT_TAB) {
                if (linearLayout.getChildAt(i2) instanceof LinearLayout) {
                    textView = (TextView) ((LinearLayout) linearLayout.getChildAt(i2)).findViewById(R.id.tab_bar_text);
                } else {
                    textView = (TextView) linearLayout.getChildAt(i2);
                }
                if (theme == Theme.WHITE_BACKGROUND || theme == Theme.TRANSPARENT_BACKGROUND_DARK_BUTTONS) {
                    textView.setTextColor(ContextCompat.getColor(this.mBaseActivity, i2 == i ? R.color.text_primary : R.color.text_secondary));
                } else {
                    textView.setTextColor(ContextCompat.getColor(this.mBaseActivity, i2 == i ? R.color.white : R.color.dark_translucent_white));
                }
                int i3 = i2 == i ? 1 : 0;
                textView.setTypeface(FontUtil.getTypefaceForStyle(i3), i3);
            }
            i2++;
        }
    }
}
