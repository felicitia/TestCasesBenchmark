package com.etsy.android.ui.search.v2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.config.b.i;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.lib.qualtrics.QualtricsController;
import com.etsy.android.lib.qualtrics.b;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.c;
import com.etsy.android.ui.search.b.d;
import com.etsy.android.ui.search.v2.SearchSuggestionsPager.SearchTab;
import com.etsy.android.uikit.AppBarHelper;
import com.etsy.android.uikit.f;
import com.etsy.android.uikit.util.e;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SearchV2Activity extends BOENavDrawerActivity implements com.etsy.android.lib.core.b.a, b, d {
    static final int MODE_RESULTS = 2;
    static final int MODE_SUGGEST = 1;
    static final int MODE_TAXONOMY = 0;
    private static final String SAVED_MODE = "mode";
    private static final String SEARCH_FIELD_HAS_HAD_FOCUS = "search_field_has_had_focus";
    private boolean configShowSearchAnything;
    @Nullable
    String mAnchorListingId = null;
    private boolean mImmediatelyFocusSearchField = false;
    SearchSuggestionsPager mInputLayout;
    private final a mListeners = new a();
    /* access modifiers changed from: private */
    public boolean mSearchFieldHasHadFocus;
    private boolean mSearchInitiatedFromWithinApp = false;
    com.etsy.android.ui.search.b mSearchViewHelper;
    TabLayout mTabLayout;
    int mViewMode;
    private Disposable tabChangeDisposable;

    class a implements OnBackStackChangedListener, OnQueryTextListener, OnFocusChangeListener {
        public boolean onQueryTextSubmit(String str) {
            return false;
        }

        a() {
        }

        public void onFocusChange(View view, boolean z) {
            if (z) {
                SearchV2Activity.this.mSearchFieldHasHadFocus = true;
                if (SearchV2Activity.this.mViewMode != 1) {
                    SearchV2Activity.this.enterSuggestMode();
                }
            }
        }

        public boolean onQueryTextChange(String str) {
            if (SearchV2Activity.this.mViewMode == 1 && SearchV2Activity.this.mInputLayout.getTabSelected() == 0) {
                SearchSuggestionsLayout suggestionsLayout = SearchV2Activity.this.mInputLayout.getSuggestionsLayout();
                if (suggestionsLayout != null) {
                    suggestionsLayout.onQueryTextChange(str);
                }
            }
            return false;
        }

        public void onBackStackChanged() {
            f c = e.c(SearchV2Activity.this.getSupportFragmentManager());
            if (c != null) {
                if (c instanceof a) {
                    SearchV2Activity.this.mViewMode = 2;
                    SearchV2Activity.this.mTabLayout.setVisibility(8);
                    SearchV2Activity.this.mSearchViewHelper.a(((a) c).getQuery(), false);
                } else if ((c instanceof SearchTaxonomyCategoryPageFragment) || (c instanceof RootTaxonomyCategoryPageFragment)) {
                    SearchV2Activity.this.mViewMode = 0;
                    SearchV2Activity.this.mTabLayout.setVisibility(8);
                    SearchV2Activity.this.mSearchViewHelper.b("");
                    SearchV2Activity.this.mSearchViewHelper.c();
                }
                SearchV2Activity.this.onModeChanged();
            }
        }
    }

    public Context getContextForQualtricsPrompt() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        QualtricsController.a((b) this);
        if (this.mImmediatelyFocusSearchField && !this.mSearchFieldHasHadFocus) {
            this.mSearchViewHelper.d();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        QualtricsController.b((b) this);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.tabChangeDisposable != null) {
            this.tabChangeDisposable.dispose();
        }
    }

    public static void addSearchResultsFragment(FragmentActivity fragmentActivity, String str, TaxonomyNode taxonomyNode) {
        com.etsy.android.ui.nav.e.a(fragmentActivity).f().b((int) R.id.search_content_view).a(str, (SearchOptions) null, (String) null, taxonomyNode);
    }

    public static void addCategoryPageFragment(FragmentActivity fragmentActivity, TaxonomyNode taxonomyNode, @Nullable String str) {
        c a2 = com.etsy.android.ui.nav.e.a(fragmentActivity).f().b((int) R.id.search_content_view);
        if (taxonomyNode != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("SEARCH_TAXONOMY_NODE", org.parceler.d.a(taxonomyNode));
            bundle.putString("ANCHOR_LISTING_ID", str);
            a2.g(bundle);
            return;
        }
        a2.z();
    }

    private void addSearchRedirectFragment(Bundle bundle) {
        com.etsy.android.ui.nav.e.a((FragmentActivity) this).f().b((int) R.id.search_content_view).h(bundle);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_new_search);
        this.mTabLayout = getAppBarHelper().addTabLayout();
        this.mInputLayout = (SearchSuggestionsPager) findViewById(R.id.search_input_layout);
        this.mInputLayout.setupWithTabLayout(this.mTabLayout);
        this.tabChangeDisposable = this.mInputLayout.tabChangedObservable().a((Consumer<? super T>) new p<Object>(this));
        this.configShowSearchAnything = getConfigMap().c(i.a);
        this.mSearchViewHelper = new com.etsy.android.ui.search.b.a(this).a().a(this.configShowSearchAnything ? R.string.search_for_anything_hint : R.string.search_hint).a((OnQueryTextListener) this.mListeners).a((OnFocusChangeListener) this.mListeners).b();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sk_space_2);
        View e = this.mSearchViewHelper.e();
        e.setPadding(0, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        getAppBarHelper().showCustomView(e);
        this.mInputLayout.setSearchViewHelperProvider(this);
        getSupportFragmentManager().addOnBackStackChangedListener(this.mListeners);
        Intent intent = getIntent();
        if (bundle != null) {
            int i = bundle.getInt(SAVED_MODE);
            this.mSearchFieldHasHadFocus = bundle.getBoolean(SEARCH_FIELD_HAS_HAD_FOCUS, true);
            switch (i) {
                case 0:
                    enterTaxonomyMode();
                    return;
                case 1:
                    enterSuggestMode();
                    return;
                case 2:
                    enterResultsMode();
                    return;
                default:
                    return;
            }
        } else if (intent != null && "android.intent.action.SEARCH".equals(intent.getAction())) {
            handleExternalSearch(intent);
        } else if (intent != null && intent.hasExtra("SEARCH_CATEGORY_REDIRECT")) {
            addSearchRedirectFragment(intent.getExtras());
            enterTaxonomyMode();
        } else if (intent == null || !intent.hasExtra("SEARCH_TAXONOMY_NODE")) {
            addCategoryPageFragment(this, null, null);
            this.mImmediatelyFocusSearchField = getConfigMap().c(com.etsy.android.lib.config.b.bF);
            if (!this.mImmediatelyFocusSearchField) {
                enterTaxonomyMode();
            }
        } else {
            TaxonomyNode taxonomyNode = (TaxonomyNode) org.parceler.d.a(intent.getParcelableExtra("SEARCH_TAXONOMY_NODE"));
            if (intent.hasExtra("ANCHOR_LISTING_ID")) {
                this.mAnchorListingId = intent.getStringExtra("ANCHOR_LISTING_ID");
            }
            if ("SEARCH_TYPE_CATEGORY".equals(intent.getStringExtra("SEARCH_TYPE"))) {
                addCategoryPageFragment(this, taxonomyNode, this.mAnchorListingId);
                enterTaxonomyMode();
                return;
            }
            performTaxonomySearch(taxonomyNode);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$onCreate$0$SearchV2Activity(SearchTab searchTab) throws Exception {
        this.mSearchViewHelper.a(getTabSearchHint(searchTab));
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if ("android.intent.action.SEARCH".equals(intent.getAction())) {
            handleInternalSearch(intent);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        boolean z = true;
        if (!isTopLevelActivity()) {
            if (this.mSearchInitiatedFromWithinApp) {
                return popOrGoBack();
            }
            com.etsy.android.ui.nav.e.a((FragmentActivity) this).a(true);
            return true;
        } else if (this.mViewMode != 1) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            if (!handleBackLocally() && !popOrGoBack()) {
                z = false;
            }
            return z;
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(SAVED_MODE, this.mViewMode);
        bundle.putBoolean(SEARCH_FIELD_HAS_HAD_FOCUS, this.mSearchFieldHasHadFocus);
    }

    private void handleInternalSearch(Intent intent) {
        String a2 = this.mSearchViewHelper.a(intent);
        int tabSelected = this.mInputLayout.getTabSelected();
        intent.getStringExtra("SEARCH_TYPE");
        this.mSearchInitiatedFromWithinApp = getIntent().getBooleanExtra("SEARCH_INITIATED_FROM_WITHIN_APP", false);
        performTabSearch(a2, tabSelected, intent.getBundleExtra("SEARCH_REQUEST_PARAMS"));
    }

    private void handleExternalSearch(Intent intent) {
        String a2 = this.mSearchViewHelper.a(intent);
        SearchOptions b = this.mSearchViewHelper.b(intent);
        this.mSearchInitiatedFromWithinApp = intent.getBooleanExtra("SEARCH_INITIATED_FROM_WITHIN_APP", false);
        this.mAnchorListingId = intent.getStringExtra("ANCHOR_LISTING_ID");
        this.mSearchViewHelper.b(a2);
        enterResultsMode();
        String stringExtra = intent.getStringExtra("SEARCH_MAX_PRICE");
        if (stringExtra != null) {
            if (b == null) {
                b = new SearchOptions();
            }
            b.setMaxPrice(Integer.parseInt(stringExtra));
        }
        String stringExtra2 = intent.getStringExtra("SEARCH_MIN_PRICE");
        if (stringExtra2 != null) {
            if (b == null) {
                b = new SearchOptions();
            }
            b.setMinPrice(Integer.parseInt(stringExtra2));
        }
        com.etsy.android.ui.nav.e.a((FragmentActivity) this).f().b((int) R.id.search_content_view).a(a2, b, this.mAnchorListingId, intent.hasExtra("SEARCH_TAXONOMY_NODE") ? (TaxonomyNode) org.parceler.d.a(intent.getParcelableExtra("SEARCH_TAXONOMY_NODE")) : null);
    }

    private void performTabSearch(String str, int i, Bundle bundle) {
        c a2 = com.etsy.android.ui.nav.e.a((FragmentActivity) this).f().b((int) R.id.search_content_view);
        enterResultsMode();
        switch (i) {
            case 0:
                saveQueryToSearchHistory(str);
                a2.a(str, bundle);
                getGraphiteTimerManager().a("search_results.time_to_results_displayed");
                return;
            case 1:
                a2.g(str);
                return;
            default:
                return;
        }
    }

    private void performTaxonomySearch(TaxonomyNode taxonomyNode) {
        enterResultsMode();
        com.etsy.android.ui.nav.e.a((FragmentActivity) this).f().b((int) R.id.search_content_view).a("", (SearchOptions) null, (String) null, taxonomyNode);
    }

    /* access modifiers changed from: private */
    public void onModeChanged() {
        updateActionBarAfterModeChange();
        trackSearchModeView();
    }

    /* access modifiers changed from: 0000 */
    public void updateActionBarAfterModeChange() {
        Drawable drawable;
        invalidateOptionsMenu();
        AppBarHelper appBarHelper = getAppBarHelper();
        if (this.mViewMode == 1) {
            drawable = com.etsy.android.uikit.c.a(getApplicationContext(), (int) R.drawable.sk_ic_back);
        } else {
            drawable = com.etsy.android.uikit.c.a(getApplicationContext(), R.drawable.sk_ic_menu, R.color.sk_orange_30);
        }
        appBarHelper.setNavigationIcon(drawable);
    }

    private void enterResultsMode() {
        this.mViewMode = 2;
        this.mInputLayout.setVisibility(8);
        this.mTabLayout.setVisibility(8);
        onModeChanged();
    }

    /* access modifiers changed from: 0000 */
    public void enterSuggestMode() {
        this.mViewMode = 1;
        this.mTabLayout.setVisibility(0);
        this.mInputLayout.setVisibility(0);
        int tabSelected = this.mInputLayout.getTabSelected();
        if (tabSelected == 0 || tabSelected == -1) {
            this.mInputLayout.setCurrentItem(0);
        }
        onModeChanged();
    }

    private void enterTaxonomyMode() {
        this.mViewMode = 0;
        this.mTabLayout.setVisibility(8);
        this.mInputLayout.setVisibility(8);
        this.mSearchViewHelper.b("");
        this.mSearchViewHelper.c();
        onModeChanged();
    }

    private void saveQueryToSearchHistory(String str) {
        if (!TextUtils.isEmpty(str) && SharedPreferencesUtility.k(this)) {
            com.etsy.android.contentproviders.a.a((Context) this, str);
        }
    }

    public com.etsy.android.ui.search.b getSearchViewHelper() {
        return this.mSearchViewHelper;
    }

    private boolean isShowingInitialSearchInput() {
        return getSupportFragmentManager().getBackStackEntryCount() == 0;
    }

    public void onBackPressed() {
        if (!handleBackLocally()) {
            super.onBackPressed();
        }
    }

    private boolean handleBackLocally() {
        if (this.mViewMode != 1) {
            return false;
        }
        if (isShowingInitialSearchInput()) {
            enterTaxonomyMode();
        } else {
            enterResultsMode();
            this.mSearchViewHelper.c();
            f c = e.c(getSupportFragmentManager());
            if (c instanceof a) {
                this.mSearchViewHelper.b(((a) c).getQuery());
            }
        }
        return true;
    }

    @StringRes
    private int getTabSearchHint(SearchTab searchTab) {
        if (this.configShowSearchAnything) {
            switch (searchTab) {
                case ITEMS:
                    return R.string.search_for_anything_hint;
                case SHOP:
                    return R.string.search_shop_hint;
            }
        }
        return R.string.search_hint;
    }

    static com.etsy.android.ui.search.b getSearchViewHelper(Activity activity) {
        if (activity instanceof d) {
            return ((d) activity).getSearchViewHelper();
        }
        throw new IllegalStateException("Containing activity must implement SearchViewHelper.Provider");
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(R.menu.search_action_bar, menu);
        menu.findItem(R.id.menu_cart).setVisible(this.mViewMode == 2);
        return super.onCreateOptionsMenuWithIcons(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu.findItem(R.id.menu_cart);
        if (findItem != null) {
            findItem.setVisible(this.mViewMode == 2);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void trackSearchModeView() {
        String str = "";
        if (this.mViewMode == 0) {
            str = "all_categories";
        } else if (this.mViewMode == 1) {
            str = "search_suggestions";
        }
        if (!TextUtils.isEmpty(str)) {
            getAnalyticsContext().a(str, null);
        }
    }

    public ViewGroup getContainerRootViewGroupForQualtricsSurvey() {
        return (ViewGroup) findViewById(16908290);
    }
}
