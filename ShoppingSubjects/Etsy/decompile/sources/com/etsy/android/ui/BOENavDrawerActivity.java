package com.etsy.android.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.BOEApplication;
import com.etsy.android.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.core.v.b;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.j;
import com.etsy.android.lib.logger.k;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import com.etsy.android.lib.util.aj;
import com.etsy.android.messaging.a;
import com.etsy.android.ui.cart.CartWithSavedActivity;
import com.etsy.android.ui.nav.d;
import com.etsy.android.uikit.AppBarHelper;
import com.etsy.android.uikit.c;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.navigationview.EtsyNavigationView;
import com.etsy.android.uikit.ui.core.NetworkLoaderActivity;
import com.etsy.android.uikit.util.e;
import com.etsy.android.util.BOEOptionsMenuItemHelper;

public abstract class BOENavDrawerActivity extends NetworkLoaderActivity {
    private static final String ARG_SOE_INSTALL_STATE = "soe_install_state";
    private static final String SIGN_IN_ACTION = "sign_in_action";
    private static final String TAG = f.a(BOENavDrawerActivity.class);
    private TextView mCartBadge;
    private ImageView mCartIcon;
    private DrawerLayout mDrawerLayout;
    @Nullable
    private a mEasyOptOutDelegate;
    private OnBackStackChangedListener mFragmentBackStackListener = new OnBackStackChangedListener() {
        public void onBackStackChanged() {
            BOENavDrawerActivity.this.mPrevBackstackCount = e.a((Activity) BOENavDrawerActivity.this, BOENavDrawerActivity.this.getSupportFragmentManager(), BOENavDrawerActivity.this.mPrevBackstackCount);
        }
    };
    private boolean mIsRestarted = false;
    private boolean mIsRetaining;
    private FrameLayout mNavContentView;
    private d mNavTracker;
    private a mNavigationMenuManager;
    private EtsyNavigationView mNavigationView;
    private final BroadcastReceiver mOnUserPrefsUpdated = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            BOENavDrawerActivity.this.onUserPrefsUpdated();
        }
    };
    /* access modifiers changed from: private */
    public int mPrevBackstackCount = 0;
    private EtsyAction mSignInAction;
    private b mSignInListener = new b() {
        public void onSignedInChanged(Context context, boolean z) {
            com.etsy.android.ui.cart.b.a(BOENavDrawerActivity.this.getApplicationContext(), 0);
            BOENavDrawerActivity.this.getRequestQueue().a((g<Result>) new com.etsy.android.ui.cart.b.a<Result>(BOENavDrawerActivity.this.getApplicationContext()));
            if (z) {
                BOENavDrawerActivity.this.onUserSignedIn();
            } else {
                BOENavDrawerActivity.this.onUserSignedOut();
            }
        }
    };
    private View mToolbarLayout;
    private BroadcastReceiver mUpdateCartBadgeReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("com.etsy.android.cart.UPDATE_CART".equalsIgnoreCase(intent.getAction())) {
                BOENavDrawerActivity.this.setCartBadge(com.etsy.android.ui.cart.b.a());
            }
        }
    };

    public TrackingBaseActivity getFragmentActivity() {
        return this;
    }

    public boolean isTopLevelActivity() {
        return true;
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onUserPrefsUpdated() {
    }

    /* access modifiers changed from: protected */
    public void onUserSignedIn() {
    }

    /* access modifiers changed from: protected */
    public void onUserSignedInForAction(EtsyAction etsyAction) {
    }

    /* access modifiers changed from: protected */
    public void onUserSignedOut() {
    }

    public void setContentView(int i) {
        getLayoutInflater().inflate(i, this.mNavContentView);
    }

    public void setContentView(View view) {
        this.mNavContentView.addView(view);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        this.mNavContentView.addView(view, layoutParams);
    }

    public void onCreate(Bundle bundle) {
        f.b(TAG, "onCreate");
        super.onCreate(bundle);
        super.setContentView((int) R.layout.activity_navigation_base);
        this.mNavContentView = (FrameLayout) findViewById(R.id.nav_content_frame);
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer_layout);
        this.mDrawerLayout.setScrimColor(getResources().getColor(R.color.drawer_scrim));
        this.mNavigationView = (EtsyNavigationView) findViewById(R.id.nav_drawer_window);
        this.mNavigationMenuManager = new a(this, this.mNavigationView, this.mDrawerLayout);
        this.mNavigationView.setNavigationItemSelectedListener(this.mNavigationMenuManager);
        this.mNavTracker = new d();
        this.mIsRestarted = bundle != null;
        if (this.mIsRestarted) {
            this.mPrevBackstackCount = bundle.getInt("backstackCount");
            e.a(getSupportFragmentManager());
            if (bundle.containsKey(SIGN_IN_ACTION)) {
                this.mSignInAction = EtsyAction.values()[bundle.getInt(SIGN_IN_ACTION)];
            }
        }
        getSupportFragmentManager().addOnBackStackChangedListener(this.mFragmentBackStackListener);
        com.etsy.android.ui.cart.b.a(getApplicationContext(), getRequestQueue());
        if (isTopLevelActivity()) {
            AppBarHelper appBarHelper = getAppBarHelper();
            if (appBarHelper != null) {
                appBarHelper.setNavigationIcon(c.a(getApplicationContext(), R.drawable.sk_ic_menu, R.color.sk_orange_30));
            }
        } else {
            AppBarHelper appBarHelper2 = getAppBarHelper();
            if (appBarHelper2 != null) {
                appBarHelper2.setNavigationIcon(c.a(getApplicationContext(), R.drawable.sk_ic_back, R.color.sk_gray_50));
            }
        }
        if (!this.mIsRestarted) {
            this.mEasyOptOutDelegate = new a(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        f.b(TAG, "onRestart");
        super.onRestart();
        this.mIsRestarted = true;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        f.b(TAG, "onStart");
        super.onStart();
        if (!EtsyApplication.get().shouldUseNewMonitor()) {
            if (k.c()) {
                com.etsy.android.lib.logger.legacy.b.a().a(this.mIsRestarted);
                getAnalyticsContext().a("became_active", null);
                f.b(TAG, "onStart - foregrounded");
                k.a();
            } else if (getLastCustomNonConfigurationInstance() == null) {
                k.a();
            }
        }
        this.mIsRestarted = false;
        v.a().a(this.mSignInListener);
        if (this.mNavigationMenuManager != null) {
            this.mNavigationMenuManager.b();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        f.b(TAG, "onResume");
        super.onResume();
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
        instance.registerReceiver(this.mOnUserPrefsUpdated, new IntentFilter("com.etsy.android.lib.action.PREFS_UPDATED"));
        com.etsy.android.lib.logger.g.a(getApplication());
        com.etsy.android.lib.toolbar.a.b(getClass().getSimpleName());
        instance.registerReceiver(this.mUpdateCartBadgeReceiver, new IntentFilter("com.etsy.android.cart.UPDATE_CART"));
        this.mNavigationMenuManager.c();
        invalidateOptionsMenu();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        f.b(TAG, "onPause");
        super.onPause();
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
        instance.unregisterReceiver(this.mOnUserPrefsUpdated);
        instance.unregisterReceiver(this.mUpdateCartBadgeReceiver);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        f.b(TAG, "onStop");
        this.mIsRetaining = false;
        if (!EtsyApplication.get().shouldUseNewMonitor() && !isChangingConfigurations()) {
            k.b();
            k.d();
        }
        v.a().b(this.mSignInListener);
        if (this.mNavigationMenuManager != null) {
            this.mNavigationMenuManager.d();
        }
        super.onStop();
    }

    public void onDestroy() {
        f.b(TAG, "onDestroy");
        if (!this.mIsRetaining) {
            e.b(getSupportFragmentManager());
        }
        getSupportFragmentManager().removeOnBackStackChangedListener(this.mFragmentBackStackListener);
        getRequestQueue().a((Object) this);
        super.onDestroy();
    }

    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        f.b(TAG, "onPostCreate");
        getAppBarHelper().addExtraUpPadding(this);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        f.b(TAG, "onActivityResult");
        switch (i) {
            case 300:
                break;
            case 301:
                if (i2 == 311) {
                    onUserSignedInForAction(this.mSignInAction);
                    this.mSignInAction = null;
                    break;
                } else {
                    finish();
                    return;
                }
            default:
                super.onActivityResult(i, i2, intent);
                break;
        }
        if (i2 == 311) {
            EtsyAction fromAction = EtsyAction.fromAction(intent.getAction());
            if (fromAction != null) {
                Bundle bundleExtra = intent.getBundleExtra(fromAction.getName());
                switch (fromAction) {
                    case FOLLOW:
                        setIntent(com.etsy.android.lib.messaging.d.a(fromAction, intent));
                        break;
                    case CONTACT_USER:
                        if (bundleExtra != null) {
                            intent.putExtras(bundleExtra);
                        }
                        com.etsy.android.ui.nav.e.a((FragmentActivity) this).a().e(intent.getExtras());
                        setIntent(null);
                        break;
                    case FAVORITE:
                        setIntent(com.etsy.android.lib.messaging.d.a(fromAction, intent));
                        break;
                    case MANAGE_ITEM_COLLECTIONS:
                        com.etsy.android.ui.nav.e.a((FragmentActivity) this).a().c(bundleExtra);
                        break;
                    case PURCHASE:
                        AndroidPayDataContract androidPayDataContract = (AndroidPayDataContract) bundleExtra.getSerializable(CartWithSavedActivity.CHECKED_OUT_CART);
                        boolean z = bundleExtra.getBoolean(CartWithSavedActivity.CHECKED_OUT_IS_MSCO);
                        if (androidPayDataContract != null) {
                            com.etsy.android.ui.nav.e.a((FragmentActivity) this).a().a(androidPayDataContract, z);
                        }
                        setIntent(null);
                        break;
                    default:
                        setIntent(null);
                        break;
                }
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        f.b(TAG, "onConfigurationChanged");
        invalidateOptionsMenu();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        f.b(TAG, "onSaveInstanceState - changing configuration: ");
        bundle.putInt("backstackCount", this.mPrevBackstackCount);
        if (this.mSignInAction != null) {
            bundle.putInt(SIGN_IN_ACTION, this.mSignInAction.ordinal());
        }
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        f.b(TAG, "onRestoreInstanceState");
    }

    public Object onRetainCustomNonConfigurationInstance() {
        f.b(TAG, "onRetainCustomNonConfigurationInstance");
        this.mIsRetaining = true;
        return Boolean.valueOf(true);
    }

    public final boolean onCreateOptionsMenu(Menu menu) {
        boolean onCreateOptionsMenuWithIcons = onCreateOptionsMenuWithIcons(menu);
        BOEOptionsMenuItemHelper.a(BOEApplication.get(), menu);
        MenuItem findItem = menu.findItem(R.id.menu_cart);
        if (findItem != null) {
            findItem.setActionView(buildCartActionBarView());
        }
        if (com.etsy.android.lib.util.a.a()) {
            BOEOptionsMenuItemHelper.a(menu);
        }
        return onCreateOptionsMenuWithIcons;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            return navigateUpAsBack();
        }
        if (itemId == R.id.menu_cart) {
            goToCart();
            return true;
        } else if (itemId != R.id.menu_search) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            goToSearch();
            return true;
        }
    }

    public void onBackPressed() {
        e.a(getSupportFragmentManager(), com.etsy.android.ui.nav.e.a((FragmentActivity) this));
    }

    public void setTitle(@StringRes int i) {
        setTitle(getString(i));
    }

    /* access modifiers changed from: protected */
    public void toggleDrawer() {
        if (isDrawerOpen()) {
            closeDrawer();
        } else {
            openDrawer();
        }
    }

    /* access modifiers changed from: protected */
    public boolean isDrawerOpen() {
        return this.mDrawerLayout != null && this.mDrawerLayout.isDrawerOpen((View) this.mNavigationView);
    }

    /* access modifiers changed from: protected */
    public void closeDrawer() {
        if (isDrawerOpen()) {
            this.mDrawerLayout.closeDrawer((View) this.mNavigationView);
        }
    }

    /* access modifiers changed from: protected */
    public void openDrawer() {
        if (this.mDrawerLayout.getDrawerLockMode((View) this.mNavigationView) == 0 && this.mDrawerLayout != null && !isDrawerOpen()) {
            this.mDrawerLayout.openDrawer((View) this.mNavigationView);
        }
    }

    /* access modifiers changed from: protected */
    public void setNavStyleModal() {
        getAppBarHelper().setNavigationIcon(c.a(this, R.drawable.sk_ic_close, R.color.sk_gray_70));
        this.mDrawerLayout.setDrawerLockMode(1);
    }

    public boolean popOrGoBack() {
        return e.b(getSupportFragmentManager(), com.etsy.android.ui.nav.e.a((FragmentActivity) this));
    }

    public boolean navigateUp() {
        if (!isTopLevelActivity()) {
            return e.a((Activity) this, getSupportFragmentManager(), (com.etsy.android.uikit.nav.b) com.etsy.android.ui.nav.e.a((FragmentActivity) this));
        }
        toggleDrawer();
        return true;
    }

    public boolean navigateUpAsBack() {
        if (!isTopLevelActivity()) {
            return e.a(getSupportFragmentManager(), getIntent(), (com.etsy.android.uikit.nav.b) com.etsy.android.ui.nav.e.a((FragmentActivity) this));
        }
        toggleDrawer();
        return true;
    }

    /* access modifiers changed from: private */
    public void goToCart() {
        com.etsy.android.ui.nav.e.a((FragmentActivity) this).a().r();
    }

    private View buildCartActionBarView() {
        View inflate = getLayoutInflater().inflate(R.layout.actionbar_cart, null);
        this.mCartBadge = (TextView) inflate.findViewById(R.id.cart_badge);
        this.mCartIcon = (ImageView) inflate.findViewById(R.id.cart_icon);
        setCartBadge(com.etsy.android.ui.cart.b.a());
        inflate.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BOENavDrawerActivity.this.goToCart();
            }
        });
        inflate.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View view) {
                aj.a((int) R.string.menu_cart, view);
                return true;
            }
        });
        return inflate;
    }

    public void setCartBadge(int i) {
        if (this.mCartBadge == null || this.mCartIcon == null) {
            invalidateOptionsMenu();
        } else if (i > 0) {
            this.mCartBadge.setText(String.valueOf(i));
            this.mCartBadge.setVisibility(0);
        } else {
            this.mCartBadge.setVisibility(8);
        }
        if (this.mCartIcon == null) {
            return;
        }
        if (i == 0) {
            this.mCartIcon.setContentDescription(getResources().getString(R.string.empty_shopping_cart_button));
        } else {
            this.mCartIcon.setContentDescription(getResources().getString(R.string.shopping_cart_button));
        }
    }

    public boolean onSearchRequested() {
        goToSearch();
        return false;
    }

    private void goToSearch() {
        com.etsy.android.ui.nav.e.a((FragmentActivity) this).a().q();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !isDrawerOpen()) {
            return super.onKeyDown(i, keyEvent);
        }
        closeDrawer();
        return true;
    }

    /* access modifiers changed from: protected */
    public void requireSignIn(EtsyAction etsyAction) {
        if (!v.a().e()) {
            this.mSignInAction = etsyAction;
            ((com.etsy.android.ui.nav.b) com.etsy.android.ui.nav.e.a((FragmentActivity) this).a().a((j) this)).a(etsyAction);
            return;
        }
        onUserSignedInForAction(etsyAction);
    }
}
