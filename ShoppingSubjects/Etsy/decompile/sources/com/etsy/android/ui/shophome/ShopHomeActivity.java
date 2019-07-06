package com.etsy.android.ui.shophome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.etsy.android.R;
import com.etsy.android.lib.convos.Draft;
import com.etsy.android.lib.core.http.loader.NetworkLoader;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.j;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.ShopHomePage;
import com.etsy.android.lib.models.apiv3.ShopV3.Shareable;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.interfaces.ShopShareable;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.core.b;
import com.etsy.android.uikit.util.SocialShareUtil;
import com.etsy.android.uikit.util.SocialShareUtil.ShareType;
import java.util.ArrayList;
import java.util.HashMap;
import org.parceler.d;

public class ShopHomeActivity extends BOENavDrawerActivity {
    public static final String SAVED_SHAREABLE = "shareable";
    /* access modifiers changed from: private */
    public View mLoadingErrorView;
    private View mLoadingView;
    ArrayList<Pair<Integer, Integer>> mSectionToName = new ArrayList<>();
    protected Shareable mSharable;
    protected ShopHomePage mShopHomePage;
    @Nullable
    protected EtsyId mShopId;
    protected String mShopName = "";
    /* access modifiers changed from: private */
    public a mTabsAdapter;
    /* access modifiers changed from: private */
    public ViewPager mViewPager;

    private class a extends FragmentPagerAdapter {
        public a() {
            super(ShopHomeActivity.this.getSupportFragmentManager());
        }

        public Fragment getItem(int i) {
            SectionedShopHomeFragment sectionedShopHomeFragment;
            switch (((Integer) ((Pair) ShopHomeActivity.this.mSectionToName.get(i)).first).intValue()) {
                case 1:
                    sectionedShopHomeFragment = new ShopHomeMainFragment();
                    break;
                case 2:
                    sectionedShopHomeFragment = new ShopHomeReviewsFragment();
                    break;
                default:
                    sectionedShopHomeFragment = new SectionedShopHomeFragment();
                    break;
            }
            Bundle bundle = new Bundle(ShopHomeActivity.this.getIntent().getExtras());
            bundle.putParcelable("shop_id", d.a(ShopHomeActivity.this.mShopId));
            if (TextUtils.isEmpty(ShopHomeActivity.this.mShopName)) {
                bundle.putString(ResponseConstants.SHOP_NAME, ShopHomeActivity.this.mShopName);
            }
            bundle.putInt("section_id", ((Integer) ((Pair) ShopHomeActivity.this.mSectionToName.get(i)).first).intValue());
            sectionedShopHomeFragment.setArguments(bundle);
            return sectionedShopHomeFragment;
        }

        public int getCount() {
            return ShopHomeActivity.this.mSectionToName.size();
        }

        public CharSequence getPageTitle(int i) {
            return ShopHomeActivity.this.getString(((Integer) ((Pair) ShopHomeActivity.this.mSectionToName.get(i)).second).intValue());
        }

        public SectionedShopHomeFragment a(ViewPager viewPager, int i) {
            StringBuilder sb = new StringBuilder();
            sb.append("android:switcher:");
            sb.append(viewPager.getId());
            sb.append(Draft.IMAGE_DELIMITER);
            sb.append(i);
            return (SectionedShopHomeFragment) ShopHomeActivity.this.getSupportFragmentManager().findFragmentByTag(sb.toString());
        }
    }

    public ShopHomePage getShopHomePage() {
        return this.mShopHomePage;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.homescreen_viewpager);
        this.mViewPager = (ViewPager) findViewById(R.id.view_pager);
        this.mLoadingView = findViewById(R.id.loading_view);
        this.mLoadingErrorView = findViewById(R.id.no_internet);
        this.mShopId = (EtsyId) getIntent().getSerializableExtra("shop_id");
        this.mShopName = getIntent().getStringExtra(ResponseConstants.SHOP_NAME);
        loadTabs();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(SAVED_SHAREABLE, d.a(this.mSharable));
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mSharable = (Shareable) d.a(bundle.getParcelable(SAVED_SHAREABLE));
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(R.menu.explore_action_bar, menu);
        menu.findItem(R.id.menu_share).setVisible(this.mSharable != null);
        return true;
    }

    private String getApiUrl() {
        String str;
        Object[] objArr;
        if (this.mShopId != null) {
            str = "/etsyapps/v3/bespoke/member/shops/%s/home";
            objArr = new Object[]{this.mShopId.toString()};
        } else {
            str = "/etsyapps/v3/bespoke/member/shops/%s/home-by-name";
            objArr = new Object[]{this.mShopName};
        }
        return String.format(str, objArr);
    }

    private void loadTabs() {
        this.mLoadingErrorView.setVisibility(8);
        this.mLoadingView.setVisibility(0);
        String couponCode = getCouponCode(getIntent().getExtras());
        com.etsy.android.lib.core.http.request.EtsyApiV3Request.a a2 = com.etsy.android.lib.core.http.request.EtsyApiV3Request.a.a(ShopHomePage.class, getApiUrl());
        TextUtils.isEmpty(couponCode);
        b.a(getSupportLoaderManager(), 0, (BaseHttpRequest<?, ResultType, ?>) (EtsyApiV3Request) a2.d(), (NetworkLoader.b<ResultType>) new com.etsy.android.lib.core.http.loader.NetworkLoader.a<ShopHomePage>() {
            /* JADX WARNING: Code restructure failed: missing block: B:32:0x0173, code lost:
                r2 = r3;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void a(@android.support.annotation.NonNull java.util.List<com.etsy.android.lib.models.apiv3.ShopHomePage> r7, int r8, @android.support.annotation.NonNull com.etsy.android.lib.core.a.a<com.etsy.android.lib.models.apiv3.ShopHomePage> r9) {
                /*
                    r6 = this;
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    java.lang.Object r8 = r9.h()
                    com.etsy.android.lib.models.apiv3.ShopHomePage r8 = (com.etsy.android.lib.models.apiv3.ShopHomePage) r8
                    r7.mShopHomePage = r8
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    com.etsy.android.ui.shophome.ShopHomeActivity r8 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    com.etsy.android.lib.models.apiv3.ShopHomePage r8 = r8.mShopHomePage
                    com.etsy.android.lib.models.apiv3.ShopV3 r8 = r8.getShop()
                    com.etsy.android.lib.models.apiv3.ShopV3$Shareable r8 = r8.getShareable()
                    r7.mSharable = r8
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    com.etsy.android.ui.shophome.ShopHomeActivity r8 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    com.etsy.android.lib.models.apiv3.ShopHomePage r8 = r8.mShopHomePage
                    com.etsy.android.lib.models.apiv3.ShopV3 r8 = r8.getShop()
                    java.lang.String r8 = r8.getName()
                    r7.setTitle(r8)
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    r7.supportInvalidateOptionsMenu()
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    java.util.ArrayList<android.support.v4.util.Pair<java.lang.Integer, java.lang.Integer>> r7 = r7.mSectionToName
                    android.support.v4.util.Pair r8 = new android.support.v4.util.Pair
                    r9 = 1
                    java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
                    r0 = 2131757229(0x7f1008ad, float:1.9145388E38)
                    java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                    r8.<init>(r9, r0)
                    r7.add(r8)
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    java.util.ArrayList<android.support.v4.util.Pair<java.lang.Integer, java.lang.Integer>> r7 = r7.mSectionToName
                    android.support.v4.util.Pair r8 = new android.support.v4.util.Pair
                    r9 = 2
                    java.lang.Integer r0 = java.lang.Integer.valueOf(r9)
                    r1 = 2131756953(0x7f100799, float:1.9144828E38)
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                    r8.<init>(r0, r1)
                    r7.add(r8)
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    com.etsy.android.lib.models.apiv3.ShopHomePage r7 = r7.mShopHomePage
                    boolean r7 = r7.hasAboutSection()
                    r8 = 3
                    if (r7 == 0) goto L_0x0082
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    java.util.ArrayList<android.support.v4.util.Pair<java.lang.Integer, java.lang.Integer>> r7 = r7.mSectionToName
                    android.support.v4.util.Pair r0 = new android.support.v4.util.Pair
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r8)
                    r2 = 2131755041(0x7f100021, float:1.914095E38)
                    java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                    r0.<init>(r1, r2)
                    r7.add(r0)
                L_0x0082:
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    com.etsy.android.lib.models.apiv3.ShopHomePage r7 = r7.mShopHomePage
                    boolean r7 = r7.hasLocalSection()
                    if (r7 == 0) goto L_0x00a4
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    java.util.ArrayList<android.support.v4.util.Pair<java.lang.Integer, java.lang.Integer>> r7 = r7.mSectionToName
                    android.support.v4.util.Pair r0 = new android.support.v4.util.Pair
                    r1 = 5
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                    r2 = 2131757643(0x7f100a4b, float:1.9146228E38)
                    java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                    r0.<init>(r1, r2)
                    r7.add(r0)
                L_0x00a4:
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    com.etsy.android.lib.models.apiv3.ShopHomePage r7 = r7.mShopHomePage
                    boolean r7 = r7.hasPolicies()
                    r0 = 6
                    if (r7 == 0) goto L_0x00c6
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    java.util.ArrayList<android.support.v4.util.Pair<java.lang.Integer, java.lang.Integer>> r7 = r7.mSectionToName
                    android.support.v4.util.Pair r1 = new android.support.v4.util.Pair
                    java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
                    r3 = 2131756737(0x7f1006c1, float:1.914439E38)
                    java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                    r1.<init>(r2, r3)
                    r7.add(r1)
                L_0x00c6:
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    com.etsy.android.lib.models.apiv3.ShopHomePage r7 = r7.mShopHomePage
                    boolean r7 = r7.hasMoreSection()
                    r1 = 4
                    if (r7 == 0) goto L_0x00e8
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    java.util.ArrayList<android.support.v4.util.Pair<java.lang.Integer, java.lang.Integer>> r7 = r7.mSectionToName
                    android.support.v4.util.Pair r2 = new android.support.v4.util.Pair
                    java.lang.Integer r3 = java.lang.Integer.valueOf(r1)
                    r4 = 2131757270(0x7f1008d6, float:1.9145471E38)
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                    r2.<init>(r3, r4)
                    r7.add(r2)
                L_0x00e8:
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    com.etsy.android.ui.shophome.ShopHomeActivity$a r7 = r7.mTabsAdapter
                    if (r7 == 0) goto L_0x00ff
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    r2 = 0
                    r7.mTabsAdapter = r2
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    android.support.v4.view.ViewPager r7 = r7.mViewPager
                    r7.setAdapter(r2)
                L_0x00ff:
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    r7.initTabs()
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    android.content.Intent r7 = r7.getIntent()
                    java.lang.String r2 = "shop_home_load_configuration"
                    boolean r7 = r7.hasExtra(r2)
                    if (r7 == 0) goto L_0x0181
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    android.content.Intent r7 = r7.getIntent()
                    java.lang.String r2 = "shop_home_load_configuration"
                    android.os.Parcelable r7 = r7.getParcelableExtra(r2)
                    java.lang.Object r7 = org.parceler.d.a(r7)
                    com.etsy.android.lib.shophome.ShopHomeInitialLoadConfiguration r7 = (com.etsy.android.lib.shophome.ShopHomeInitialLoadConfiguration) r7
                    com.etsy.android.ui.shophome.ShopHomeActivity r2 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    android.content.Intent r2 = r2.getIntent()
                    java.lang.String r3 = "shop_home_load_configuration"
                    r2.removeExtra(r3)
                    r2 = 0
                    r3 = r2
                L_0x0131:
                    com.etsy.android.ui.shophome.ShopHomeActivity r4 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    java.util.ArrayList<android.support.v4.util.Pair<java.lang.Integer, java.lang.Integer>> r4 = r4.mSectionToName
                    int r4 = r4.size()
                    if (r3 >= r4) goto L_0x0178
                    com.etsy.android.ui.shophome.ShopHomeActivity r4 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    java.util.ArrayList<android.support.v4.util.Pair<java.lang.Integer, java.lang.Integer>> r4 = r4.mSectionToName
                    java.lang.Object r4 = r4.get(r3)
                    android.support.v4.util.Pair r4 = (android.support.v4.util.Pair) r4
                    F r4 = r4.first
                    java.lang.Integer r4 = (java.lang.Integer) r4
                    java.lang.Integer r5 = java.lang.Integer.valueOf(r8)
                    boolean r5 = r4.equals(r5)
                    if (r5 == 0) goto L_0x0157
                    int r5 = r7.mConfigType
                    if (r5 == r9) goto L_0x0173
                L_0x0157:
                    java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
                    boolean r5 = r4.equals(r5)
                    if (r5 == 0) goto L_0x0165
                    int r5 = r7.mConfigType
                    if (r5 == r8) goto L_0x0173
                L_0x0165:
                    java.lang.Integer r5 = java.lang.Integer.valueOf(r9)
                    boolean r4 = r4.equals(r5)
                    if (r4 == 0) goto L_0x0175
                    int r4 = r7.mConfigType
                    if (r4 != r1) goto L_0x0175
                L_0x0173:
                    r2 = r3
                    goto L_0x0178
                L_0x0175:
                    int r3 = r3 + 1
                    goto L_0x0131
                L_0x0178:
                    com.etsy.android.ui.shophome.ShopHomeActivity r7 = com.etsy.android.ui.shophome.ShopHomeActivity.this
                    android.support.v4.view.ViewPager r7 = r7.mViewPager
                    r7.setCurrentItem(r2)
                L_0x0181:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.shophome.ShopHomeActivity.AnonymousClass1.a(java.util.List, int, com.etsy.android.lib.core.a.a):void");
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<ShopHomePage> aVar) {
                ShopHomeActivity.this.mLoadingErrorView.setVisibility(0);
            }
        });
    }

    @Nullable
    private String getCouponCode(Bundle bundle) {
        if (bundle == null || bundle.getBundle("referral_args") == null || bundle.getBundle("referral_args").getBundle("url_params") == null || TextUtils.isEmpty(bundle.getBundle("referral_args").getBundle("url_params").getString(ResponseConstants.COUPON))) {
            return null;
        }
        return bundle.getBundle("referral_args").getBundle("url_params").getString(ResponseConstants.COUPON);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_share) {
            return super.onOptionsItemSelected(menuItem);
        }
        shareShop();
        return true;
    }

    /* access modifiers changed from: private */
    public void initTabs() {
        this.mLoadingView.setVisibility(8);
        if (this.mTabsAdapter == null) {
            this.mTabsAdapter = new a();
        }
        if (this.mViewPager.getAdapter() == null) {
            this.mViewPager.setAdapter(this.mTabsAdapter);
            TabLayout addTabLayout = getAppBarHelper().addTabLayout();
            if (addTabLayout != null) {
                addTabLayout.setupWithViewPager(this.mViewPager);
                addTabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
                    public void onTabSelected(Tab tab) {
                    }

                    public void onTabUnselected(Tab tab) {
                    }

                    public void onTabReselected(Tab tab) {
                        SectionedShopHomeFragment a2 = ShopHomeActivity.this.mTabsAdapter.a(ShopHomeActivity.this.mViewPager, tab.getPosition());
                        if (a2 != null) {
                            a2.resetScrollPosition();
                        }
                    }
                });
            }
            this.mViewPager.setVisibility(0);
        }
    }

    private void shareShop() {
        SocialShareUtil.a("shop_home", ShareType.SHOP_SHARE, getLocalClassName());
        final EtsyId shopId = this.mSharable.getShopId();
        getAnalyticsContext().a("share_shop", new HashMap<AnalyticsLogAttribute, Object>() {
            {
                put(AnalyticsLogAttribute.SHOP_ID, shopId);
            }
        });
        ((com.etsy.android.ui.nav.b) e.a((FragmentActivity) this).a().a((j) this)).a((ShopShareable) this.mSharable);
    }
}
