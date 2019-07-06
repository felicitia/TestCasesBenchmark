package com.etsy.android.ui.favorites;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.Menu;
import com.etsy.android.R;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.view.TabsActivity;

public class FavoritesActivity extends TabsActivity {
    public static final int TAB_LISTING = 0;
    public static final int TAB_SHOPS = 1;
    /* access modifiers changed from: private */
    public EtsyId mUserId;
    private String mUserName;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUserName = getIntent().getStringExtra(ResponseConstants.USERNAME);
        this.mUserId = (EtsyId) getIntent().getSerializableExtra("user_id");
        if (this.mUserId == null) {
            this.mUserId = SharedPreferencesUtility.c(this);
        }
        if (showNameInTitle()) {
            setTitle(String.format(getString(R.string.user_s_favorites), new Object[]{this.mUserName}));
        } else if (isYou()) {
            setTitle(R.string.favorites);
        } else {
            setTitle(R.string.user_single_favorites);
        }
        setupTabs();
        if (bundle == null) {
            selectTab(getIntent().getIntExtra("type", 0));
            this.mNavTracker.a(isYou());
        }
    }

    public boolean isYou() {
        return !this.mUserId.hasId() || v.a().m().equals(this.mUserId);
    }

    /* access modifiers changed from: protected */
    public PagerAdapter getViewPagerAdapter() {
        return new FragmentPagerAdapter(getSupportFragmentManager()) {
            public int getCount() {
                return 2;
            }

            public Fragment getItem(int i) {
                Fragment fragment;
                Bundle bundle = new Bundle();
                bundle.putSerializable("user_id", FavoritesActivity.this.mUserId);
                bundle.putInt("type", i);
                String str = "";
                switch (i) {
                    case 0:
                        str = FavoritesActivity.this.isYou() ? "your_favorite_items" : "profile_favorite_listings";
                        fragment = new FavoriteItemsFragment();
                        break;
                    case 1:
                        str = FavoritesActivity.this.isYou() ? "your_favorite_shops" : "profile_favorite_shops";
                        fragment = new FavoriteShopsFragment();
                        break;
                    default:
                        fragment = null;
                        break;
                }
                bundle.putString("TRACKING_NAME", str);
                fragment.setArguments(bundle);
                return fragment;
            }

            public CharSequence getPageTitle(int i) {
                switch (i) {
                    case 0:
                        return FavoritesActivity.this.getString(R.string.items);
                    case 1:
                        return FavoritesActivity.this.getString(R.string.shops);
                    default:
                        return null;
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public void logPageAtPosition(int i) {
        this.mNavTracker.a(i, isYou());
    }

    private boolean showNameInTitle() {
        return l.c((Activity) this) && af.a(this.mUserName) && !isYou();
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(R.menu.default_action_bar, menu);
        return true;
    }

    public boolean isTopLevelActivity() {
        if (!getIntent().hasExtra("NAV_TOP_LEVEL_DRAWER") || !getIntent().getBooleanExtra("NAV_TOP_LEVEL_DRAWER", false)) {
            return super.isTopLevelActivity();
        }
        return true;
    }
}
