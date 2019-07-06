package com.etsy.android.ui.homescreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.http.loader.NetworkLoader;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.messaging.EtsyEntity;
import com.etsy.android.lib.messaging.c;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.ListingCollection;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.homescreen.HomescreenTab;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.lib.qualtrics.QualtricsController;
import com.etsy.android.lib.qualtrics.b;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.core.BaseLaunchActivity;
import com.etsy.android.ui.local.LocalBrowseHomescreenFragment;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.nav.ActivityNavigator.AnimationMode;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.usebutton.merchant.n;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.parceler.d;

public class HomescreenTabsActivity extends BaseLaunchActivity implements b {
    private static final String SAVED_TAB_DATA = "saved_tab_data";
    private static final String TRACKING_PAGE_VIEW_PREFIX = "homescreen_";
    /* access modifiers changed from: private */
    public static ArrayList<HomescreenTab> mTabData = new ArrayList<>();
    com.etsy.android.deeplinking.a button;
    com.etsy.android.lib.logger.elk.a.a graphite;
    /* access modifiers changed from: private */
    public View mLoadingErrorView;
    private View mLoadingView;
    /* access modifiers changed from: private */
    public a mTabsAdapter;
    /* access modifiers changed from: private */
    public ViewPager mViewPager;
    boolean mWasSignedIn;
    v session;

    /* renamed from: com.etsy.android.ui.homescreen.HomescreenTabsActivity$4 reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[EtsyEntity.values().length];

        static {
            try {
                a[EtsyEntity.LISTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    class a extends FragmentPagerAdapter {
        a() {
            super(HomescreenTabsActivity.this.getSupportFragmentManager());
        }

        private Bundle a(int i) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(HomescreenFragment.ARG_TAB_DATA, d.a(HomescreenTabsActivity.mTabData.get(i)));
            StringBuilder sb = new StringBuilder();
            sb.append(HomescreenTabsActivity.TRACKING_PAGE_VIEW_PREFIX);
            sb.append(((HomescreenTab) HomescreenTabsActivity.mTabData.get(i)).getTrackingName());
            bundle.putString("TRACKING_NAME", sb.toString());
            return bundle;
        }

        public Fragment getItem(int i) {
            if (((HomescreenTab) HomescreenTabsActivity.mTabData.get(i)).isLocal()) {
                return new LocalBrowseHomescreenFragment();
            }
            HomescreenFragment homescreenFragment = new HomescreenFragment();
            homescreenFragment.setArguments(a(i));
            return homescreenFragment;
        }

        public long getItemId(int i) {
            HomescreenTab homescreenTab = (HomescreenTab) HomescreenTabsActivity.mTabData.get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(homescreenTab.getTrackingName());
            if (!homescreenTab.isLocal() && HomescreenTabsActivity.this.session.e()) {
                sb.append(HomescreenTabsActivity.this.session.l().toString());
            }
            return (long) sb.toString().hashCode();
        }

        public int getCount() {
            return HomescreenTabsActivity.mTabData.size();
        }

        public CharSequence getPageTitle(int i) {
            return ((HomescreenTab) HomescreenTabsActivity.mTabData.get(i)).getTitle();
        }
    }

    public void onCreate(Bundle bundle) {
        setTheme(2131821250);
        super.onCreate(bundle);
        getAppBarHelper().setCustomTitleView((int) R.layout.image_view_etsy_text_logo);
        setContentView((int) R.layout.homescreen_viewpager);
        this.mViewPager = (ViewPager) findViewById(R.id.view_pager);
        this.mLoadingView = findViewById(R.id.loading_view);
        this.mLoadingErrorView = findViewById(R.id.no_internet);
        this.mLoadingErrorView.findViewById(R.id.btn_retry_internet).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                HomescreenTabsActivity.this.loadTabs();
            }
        });
        setIntent(com.etsy.android.ui.nav.b.a(getIntent(), AnimationMode.DEFAULT_OUT));
        this.mWasSignedIn = this.session.e();
        if (bundle != null) {
            mTabData = (ArrayList) d.a(bundle.getParcelable(SAVED_TAB_DATA));
            initTabs(mTabData.size());
        } else {
            getGraphiteTimerManager().a("homescreen_tabs.%s.time_to_results_displayed");
            loadTabs();
        }
        checkButtonDeepLink();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(SAVED_TAB_DATA, d.a(mTabData));
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mWasSignedIn = this.session.e();
        QualtricsController.b((b) this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!(this.mWasSignedIn == this.session.e() || this.mTabsAdapter == null)) {
            this.mViewPager.setVisibility(8);
            loadTabs();
        }
        QualtricsController.a((b) this);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getIntent().putExtra(ResponseConstants.PAGE_LINK, intent.getStringExtra(ResponseConstants.PAGE_LINK));
        if (this.mTabsAdapter != null) {
            setTabFromIntent(intent);
        }
    }

    private void setTabFromIntent(Intent intent) {
        String stringExtra = intent.getStringExtra(ResponseConstants.PAGE_LINK);
        if (stringExtra != null) {
            int i = 0;
            while (true) {
                if (i >= mTabData.size()) {
                    break;
                } else if (stringExtra.endsWith(((HomescreenTab) mTabData.get(i)).getDeepLinkPath())) {
                    this.mViewPager.setCurrentItem(i);
                    break;
                } else {
                    i++;
                }
            }
            intent.removeExtra(ResponseConstants.PAGE_LINK);
        }
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(R.menu.default_action_bar, menu);
        return true;
    }

    /* access modifiers changed from: private */
    public void loadTabs() {
        this.mLoadingErrorView.setVisibility(8);
        this.mLoadingView.setVisibility(0);
        com.etsy.android.uikit.ui.core.b.a(getSupportLoaderManager(), 0, (BaseHttpRequest<?, ResultType, ?>) (EtsyApiV3Request) ((com.etsy.android.lib.core.http.request.EtsyApiV3Request.a) ((com.etsy.android.lib.core.http.request.EtsyApiV3Request.a) com.etsy.android.lib.core.http.request.EtsyApiV3Request.a.a(HomescreenTab.class, "/etsyapps/v3/bespoke/member/homescreen/tabs").a("include_content", Integer.toString(1))).a("is_tablet", Boolean.toString((l.b((Activity) this) && l.f((Activity) this)) || (l.c((Activity) this) && l.e((Activity) this))))).d(), (NetworkLoader.b<ResultType>) new com.etsy.android.lib.core.http.loader.NetworkLoader.a<HomescreenTab>() {
            public void a(@NonNull List<HomescreenTab> list, int i, @NonNull com.etsy.android.lib.core.a.a<HomescreenTab> aVar) {
                HomescreenTabsActivity.mTabData.clear();
                HomescreenTabsActivity.mTabData.addAll(list);
                if (HomescreenTabsActivity.this.mTabsAdapter != null) {
                    HomescreenTabsActivity.this.mTabsAdapter.notifyDataSetChanged();
                    HomescreenTabsActivity.this.mTabsAdapter = null;
                    HomescreenTabsActivity.this.mViewPager.setAdapter(null);
                }
                HomescreenTabsActivity.this.initTabs(list.size());
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<HomescreenTab> aVar) {
                HomescreenTabsActivity.this.mLoadingErrorView.setVisibility(0);
            }
        });
    }

    /* access modifiers changed from: private */
    public void initTabs(int i) {
        this.mLoadingView.setVisibility(8);
        if (this.mTabsAdapter == null) {
            this.mTabsAdapter = new a();
        }
        if (this.mViewPager.getAdapter() == null) {
            this.mViewPager.setAdapter(this.mTabsAdapter);
            TabLayout addTabLayout = getAppBarHelper().addTabLayout();
            if (addTabLayout != null) {
                if (i > 1) {
                    addTabLayout.setVisibility(0);
                    addTabLayout.setupWithViewPager(this.mViewPager);
                } else {
                    addTabLayout.setVisibility(8);
                }
            }
            this.mViewPager.setVisibility(0);
        }
        setTabFromIntent(getIntent());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 601 && intent != null) {
            ListingLike listingLike = (ListingLike) intent.getSerializableExtra("listing");
            ListingCollection listingCollection = (ListingCollection) intent.getSerializableExtra(Collection.TYPE_COLLECTION);
            if (listingLike != null && listingCollection != null && intent.getBooleanExtra("should_show_social_invites_prompt", false)) {
                com.etsy.android.ui.util.d.a((FragmentActivity) this, (com.etsy.android.lib.logger.b) getAnalyticsContext(), listingCollection, listingLike);
            }
        }
    }

    public ViewGroup getContainerRootViewGroupForQualtricsSurvey() {
        return (ViewGroup) findViewById(16908290);
    }

    @NonNull
    public Context getContextForQualtricsPrompt() {
        return this.mViewPager.getContext();
    }

    private void checkButtonDeepLink() {
        this.button.a((n) new n() {
            public void a(@NonNull Intent intent) {
                c a2 = c.a(intent.getData());
                if (a2 != null && AnonymousClass4.a[a2.c().ordinal()] == 1) {
                    e.a((FragmentActivity) HomescreenTabsActivity.this).a().a(AnimationMode.DEFAULT).a(new EtsyId(a2.d()), HomescreenTabsActivity.this.getRouteBundle(a2));
                    HomescreenTabsActivity.this.finish();
                }
            }

            public void a(@Nullable Throwable th) {
                if (th != null) {
                    HomescreenTabsActivity.this.graphite.a("branch.error_fetching_post_install_intent", 0.1d);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public Bundle getRouteBundle(c cVar) {
        Bundle bundle = new Bundle();
        if (cVar.b() != null) {
            bundle.putString(EtsyAction.ACTION_TYPE_NAME, cVar.b().getName());
        }
        HashMap f = cVar.f();
        if (f != null && f.size() > 0) {
            Bundle bundle2 = new Bundle();
            for (String str : f.keySet()) {
                bundle2.putString(str, (String) f.get(str));
            }
            bundle.putBundle("url_params", bundle2);
        }
        return bundle;
    }
}
