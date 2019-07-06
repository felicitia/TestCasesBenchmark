package com.contextlogic.wish.ui.bottomnavigation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.activity.browse.BrowseFragment;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.activity.menu.MenuFragment;
import com.contextlogic.wish.activity.more.MoreActivity;
import com.contextlogic.wish.activity.search.SearchActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;

public class BottomNavigationView extends ConstraintLayout {
    private View mBrowse;
    private ImageView mBrowseIcon;
    private TextView mBrowseLabel;
    private View mCart;
    private View mCartBadge;
    private TextView mCartCount;
    private ImageView mCartIcon;
    private TextView mCartLabel;
    private View mContentView;
    private boolean mInAnimation;
    private View mMore;
    private View mMoreBadge;
    private ImageView mMoreIcon;
    private TextView mMoreLabel;
    private boolean mOutOfWindow;
    private View mSearch;
    private ImageView mSearchIcon;
    private TextView mSearchLabel;

    public BottomNavigationView(Context context) {
        this(context, null);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        this.mContentView = inflate(getContext(), R.layout.bottom_navigation_view, this);
        this.mBrowse = this.mContentView.findViewById(R.id.bottom_nav_browse);
        this.mBrowseIcon = (ImageView) this.mContentView.findViewById(R.id.bottom_nav_browse_icon);
        this.mBrowseLabel = (TextView) this.mContentView.findViewById(R.id.bottom_nav_browse_label);
        this.mSearch = this.mContentView.findViewById(R.id.bottom_nav_search);
        this.mSearchIcon = (ImageView) this.mContentView.findViewById(R.id.bottom_nav_search_icon);
        this.mSearchLabel = (TextView) this.mContentView.findViewById(R.id.bottom_nav_search_label);
        this.mCart = this.mContentView.findViewById(R.id.bottom_nav_cart);
        this.mCartIcon = (ImageView) this.mContentView.findViewById(R.id.bottom_nav_cart_icon);
        this.mCartLabel = (TextView) this.mContentView.findViewById(R.id.bottom_nav_cart_label);
        this.mCartBadge = this.mContentView.findViewById(R.id.bottom_nav_cart_badge);
        this.mCartCount = (TextView) this.mContentView.findViewById(R.id.bottom_nav_cart_count);
        this.mMore = this.mContentView.findViewById(R.id.bottom_nav_more);
        this.mMoreIcon = (ImageView) this.mContentView.findViewById(R.id.bottom_nav_more_icon);
        this.mMoreLabel = (TextView) this.mContentView.findViewById(R.id.bottom_nav_more_label);
        this.mMoreBadge = this.mContentView.findViewById(R.id.bottom_nav_more_badge);
    }

    public void setup(final DrawerActivity drawerActivity) {
        this.mBrowse.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!MenuFragment.MENU_KEY_BROWSE.equals(drawerActivity.getMenuKey())) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_HOME);
                    Intent intent = new Intent();
                    intent.setClass(drawerActivity, BrowseActivity.class);
                    intent.setFlags(131072);
                    drawerActivity.startActivity(intent);
                    return;
                }
                BrowseFragment browseFragment = (BrowseFragment) drawerActivity.getUiFragment("FragmentTagMainContent");
                if (browseFragment.getCurrentIndex() != browseFragment.getLatestPosition()) {
                    browseFragment.switchToBrowseAndGoToTop();
                } else {
                    browseFragment.smoothScrollToTop();
                }
            }
        });
        this.mSearch.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!MenuFragment.MENU_KEY_SEARCH.equals(drawerActivity.getMenuKey())) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_SEARCH);
                    Intent intent = new Intent();
                    intent.setClass(drawerActivity, SearchActivity.class);
                    drawerActivity.startActivity(intent);
                }
            }
        });
        this.mCart.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!MenuFragment.MENU_KEY_CART.equals(drawerActivity.getMenuKey())) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIDE_NAV_CART);
                    Intent intent = new Intent();
                    intent.setClass(drawerActivity, CartActivity.class);
                    drawerActivity.startActivity(intent);
                }
            }
        });
        this.mMore.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!MenuFragment.MENU_KEY_MORE.equals(drawerActivity.getMenuKey())) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_SIDE_NAV_MENU);
                    Intent intent = new Intent();
                    intent.setClass(drawerActivity, MoreActivity.class);
                    drawerActivity.startActivity(intent);
                }
            }
        });
    }

    public void updateState(DrawerActivity drawerActivity, int i) {
        if (drawerActivity.getBottomNavigationTabIndex() == -1) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        if (drawerActivity.getBottomNavigationTabIndex() == 1) {
            this.mBrowseIcon.setImageDrawable(getResources().getDrawable(R.drawable.bottom_nav_browse_selected));
            this.mBrowseLabel.setTextColor(getResources().getColor(R.color.wish_blue));
        } else if (drawerActivity.getBottomNavigationTabIndex() == 2) {
            this.mSearchIcon.setImageDrawable(getResources().getDrawable(R.drawable.bottom_nav_search_selected));
            this.mSearchLabel.setTextColor(getResources().getColor(R.color.wish_blue));
        } else if (drawerActivity.getBottomNavigationTabIndex() == 3) {
            this.mCartIcon.setImageDrawable(getResources().getDrawable(R.drawable.bottom_nav_cart_selected));
            this.mCartLabel.setTextColor(getResources().getColor(R.color.wish_blue));
            this.mCartCount.setTextColor(getResources().getColor(R.color.wish_blue));
        } else if (drawerActivity.getBottomNavigationTabIndex() == 4) {
            this.mMoreIcon.setImageDrawable(getResources().getDrawable(R.drawable.bottom_nav_more_selected));
            this.mMoreLabel.setTextColor(getResources().getColor(R.color.wish_blue));
        }
        if (i > 0) {
            this.mCartBadge.setVisibility(0);
            this.mCartCount.setVisibility(0);
            this.mCartCount.setText(i < 99 ? String.valueOf(i) : String.valueOf(99));
        } else {
            this.mCartBadge.setVisibility(8);
            this.mCartCount.setVisibility(8);
        }
        if (StatusDataCenter.getInstance().getUnviewedNotificationCount() > 0) {
            this.mMoreBadge.setVisibility(0);
        } else {
            this.mMoreBadge.setVisibility(8);
        }
    }

    public void popIn() {
        if (!this.mInAnimation && this.mOutOfWindow) {
            this.mInAnimation = true;
            clearAnimation();
            animate().translationYBy((float) (-getMeasuredHeight())).setDuration(200).setInterpolator(new LinearInterpolator()).setListener(new AnimatorListenerAdapter() {
                public void onAnimationCancel(Animator animator) {
                    BottomNavigationView.this.onPopUpAnimationEnded();
                }

                public void onAnimationEnd(Animator animator) {
                    BottomNavigationView.this.onPopUpAnimationEnded();
                }
            });
        }
    }

    public void popOut() {
        if (!this.mInAnimation && !this.mOutOfWindow) {
            this.mInAnimation = true;
            clearAnimation();
            animate().translationYBy((float) getMeasuredHeight()).setDuration(200).setInterpolator(new LinearInterpolator()).setListener(new AnimatorListenerAdapter() {
                public void onAnimationCancel(Animator animator) {
                    BottomNavigationView.this.onPopDownAnimationEnded();
                }

                public void onAnimationEnd(Animator animator) {
                    BottomNavigationView.this.onPopDownAnimationEnded();
                }
            });
        }
    }

    public void onPopUpAnimationEnded() {
        this.mInAnimation = false;
        this.mOutOfWindow = false;
    }

    public void onPopDownAnimationEnded() {
        this.mInAnimation = false;
        this.mOutOfWindow = true;
    }

    public boolean isOutOfWindow() {
        return this.mOutOfWindow;
    }
}
