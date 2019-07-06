package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.Menu;
import com.etsy.android.R;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.af;
import com.etsy.android.ui.view.TabsActivity;

public class CirclesActivity extends TabsActivity {
    private static final int TAB_COUNT = 2;
    public static final int TAB_FOLLOWERS = 1;
    public static final int TAB_FOLLOWING = 0;
    /* access modifiers changed from: private */
    public EtsyId mUserId;
    /* access modifiers changed from: private */
    public String mUserLoginName;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUserId = (EtsyId) getIntent().getSerializableExtra("user_id");
        this.mUserLoginName = getIntent().getStringExtra(ResponseConstants.USERNAME);
        if (this.mUserId == null) {
            this.mUserId = new EtsyId();
        }
        setupTabs();
        if (bundle == null) {
            selectTab(getIntent().getIntExtra("type", 0));
        }
    }

    /* access modifiers changed from: protected */
    public PagerAdapter getViewPagerAdapter() {
        return new FragmentPagerAdapter(getSupportFragmentManager()) {
            public int getCount() {
                return 2;
            }

            public Fragment getItem(int i) {
                CirclesFragment circlesFragment = new CirclesFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("user_id", CirclesActivity.this.mUserId);
                bundle.putString(ResponseConstants.USERNAME, CirclesActivity.this.mUserLoginName);
                boolean z = !CirclesActivity.this.mUserId.hasId() || v.a().m().equals(CirclesActivity.this.mUserId);
                if (i != 1) {
                    if (z) {
                        bundle.putString("TRACKING_NAME", "your_circles_following");
                    } else {
                        bundle.putString("TRACKING_NAME", "people_circles_following");
                    }
                    bundle.putBoolean("CIRCLE_FOLLOWING", true);
                } else {
                    if (z) {
                        bundle.putString("TRACKING_NAME", "your_circles_followers");
                    } else {
                        bundle.putString("TRACKING_NAME", "people_circles_followers");
                    }
                    bundle.putBoolean("CIRCLE_FOLLOWING", false);
                }
                circlesFragment.setArguments(bundle);
                return circlesFragment;
            }

            public CharSequence getPageTitle(int i) {
                String str = "";
                switch (i) {
                    case 0:
                        return CirclesActivity.this.getString(R.string.following);
                    case 1:
                        return CirclesActivity.this.getString(R.string.followers);
                    default:
                        return str;
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public void logPageAtPosition(int i) {
        if (i != 1) {
            this.mNavTracker.a(true, this.mUserId);
        } else {
            this.mNavTracker.a(false, this.mUserId);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (af.a(this.mUserLoginName)) {
            setTitle(this.mUserLoginName);
        } else {
            setTitle("");
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(R.menu.default_action_bar, menu);
        return true;
    }
}
