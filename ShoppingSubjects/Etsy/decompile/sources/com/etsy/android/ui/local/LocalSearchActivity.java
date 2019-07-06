package com.etsy.android.ui.local;

import android.app.Activity;
import android.content.IntentSender.SendIntentException;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.f.a;
import com.etsy.android.lib.core.f.b;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.m;
import com.etsy.android.lib.core.m.c;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.LocalBrowseLandingPage;
import com.etsy.android.lib.models.apiv3.LocalBrowseModule;
import com.etsy.android.lib.models.apiv3.LocalBrowseResponse;
import com.etsy.android.lib.util.ae;
import com.etsy.android.lib.util.j;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.google.android.gms.common.ConnectionResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.parceler.d;

public class LocalSearchActivity extends BOENavDrawerActivity implements a, a {
    private static final String BROWSE_HISTORY = "browse_history";
    private static final String INITIAL_LOCATION = "initial_location";
    private static final String IS_LIST_PANEL_SHOWING = "is_list_panel_showing";
    private static final String SEARCH_RESPONSE = "search_response";
    public static final String START_FULLSCREEN_MAP = "start_on_full_map";
    /* access modifiers changed from: private */
    public static final String TAG = f.a(LocalSearchActivity.class);
    private List<LocalBrowseHistoryEntry> mBrowseHistory;
    /* access modifiers changed from: private */
    public List<b> mBrowseUpdateListeners = new ArrayList();
    private Button mEnableLocationButton;
    private TextView mEnableLocationMessage;
    private View mEnableLocationView;
    private ViewGroup mFilterBar;
    private LocalBrowseModule mInitialBrowseModule;
    private Location mInitialLocation;
    /* access modifiers changed from: private */
    public boolean mIsListPanelShowing;
    /* access modifiers changed from: private */
    public boolean mIsRequestPending;
    private d mLocationManager;
    /* access modifiers changed from: private */
    public List<LocalMarketCard> mSearchResponse;
    private LinearLayout mSearchingText;
    private boolean mStartOnFullMap;

    public LocalBrowseResponse getCurrentBrowseResponse() {
        return null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_local_browse);
        this.mEnableLocationView = findViewById(R.id.partial_local_fix_location);
        this.mEnableLocationMessage = (TextView) findViewById(R.id.enable_location_message);
        this.mEnableLocationButton = (Button) findViewById(R.id.enable_location_button);
        this.mLocationManager = new d(this, this);
        this.mInitialBrowseModule = (LocalBrowseModule) d.a(getIntent().getParcelableExtra("local_browse_module"));
        this.mStartOnFullMap = getIntent().getBooleanExtra(START_FULLSCREEN_MAP, false);
        if (bundle == null) {
            e.a((FragmentActivity) this).f().b((int) R.id.map_fragment_frame).q();
            if (!this.mStartOnFullMap) {
                showListPanel();
            }
        } else {
            this.mSearchResponse = (List) d.a(bundle.getParcelable(SEARCH_RESPONSE));
            this.mBrowseHistory = bundle.getParcelableArrayList(BROWSE_HISTORY);
            this.mIsListPanelShowing = bundle.getBoolean(IS_LIST_PANEL_SHOWING);
            this.mInitialLocation = (Location) bundle.getParcelable(INITIAL_LOCATION);
            restoreListPanelShownState();
            updateTitle();
        }
        showFilterBar(false);
        updateFilterBar();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mLocationManager.a();
        ae.b(false);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.mLocationManager.b();
        super.onStop();
        ae.a(false);
    }

    public void onDestroy() {
        this.mLocationManager.c();
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable(SEARCH_RESPONSE, d.a(this.mSearchResponse));
        bundle.putParcelableArrayList(BROWSE_HISTORY, (ArrayList) this.mBrowseHistory);
        bundle.putBoolean(IS_LIST_PANEL_SHOWING, this.mIsListPanelShowing);
        bundle.putParcelable(INITIAL_LOCATION, this.mInitialLocation);
        super.onSaveInstanceState(bundle);
    }

    public boolean onCreateOptionsMenuWithIcons(Menu menu) {
        getMenuInflater().inflate(R.menu.default_action_bar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        navigateUpAsBack();
        return true;
    }

    public void registerUpdateListener(b bVar) {
        if (this.mBrowseUpdateListeners == null) {
            this.mBrowseUpdateListeners = new ArrayList();
        }
        if (bVar != null && !this.mBrowseUpdateListeners.contains(bVar)) {
            this.mBrowseUpdateListeners.add(bVar);
        }
    }

    public void unregisterUpdateListener(b bVar) {
        if (this.mBrowseUpdateListeners != null && !this.mBrowseUpdateListeners.isEmpty()) {
            this.mBrowseUpdateListeners.remove(bVar);
        }
    }

    public void onClickMapArea() {
        hideListPanel();
    }

    public void onClickShowListView() {
        showListPanel();
    }

    public boolean isListPanelShowing() {
        return this.mIsListPanelShowing;
    }

    public void onLoadBrowseSection(@NonNull LocalBrowseModule localBrowseModule) {
        LocalBrowseLandingPage landingPage = localBrowseModule.getLandingPage();
        if (landingPage != null) {
            String title = localBrowseModule.getTitle();
            fetchSearchResults(landingPage.getApiPath());
            pushBrowseHistory(new LocalBrowseHistoryEntry(1, landingPage.getApiPath(), title));
            updateTitle();
            showFilterBar(true);
        }
    }

    public void onNewSearchQuery(@NonNull LocalSearchQuery localSearchQuery) {
        fetchSearchResults(localSearchQuery);
        LocalBrowseHistoryEntry localBrowseHistoryEntry = new LocalBrowseHistoryEntry(1, localSearchQuery, getString(R.string.local_browse_title_nearby));
        LocalBrowseHistoryEntry currentBrowseHistoryEntry = getCurrentBrowseHistoryEntry();
        if (currentBrowseHistoryEntry == null || currentBrowseHistoryEntry.a() != 1) {
            pushBrowseHistory(localBrowseHistoryEntry);
        } else {
            replaceLastBrowseHistoryEntry(localBrowseHistoryEntry);
        }
        updateTitle();
    }

    public void onUserChangedMapPosition(double d, double d2, int i) {
        showSearchThisAreaButton(d, d2, i);
    }

    public void expandSearchRadius() {
        LocalBrowseHistoryEntry currentBrowseHistoryEntry = getCurrentBrowseHistoryEntry();
        if (!(currentBrowseHistoryEntry == null || currentBrowseHistoryEntry.a() != 1 || currentBrowseHistoryEntry.c() == null)) {
            LocalSearchQuery c = currentBrowseHistoryEntry.c();
            LocalSearchQuery localSearchQuery = new LocalSearchQuery(c.getLatitude(), c.getLongitude(), c.getOrDefaultRadius() * 2);
            onNewSearchQuery(localSearchQuery);
        }
        for (b onExpandSearchArea : this.mBrowseUpdateListeners) {
            onExpandSearchArea.onExpandSearchArea();
        }
    }

    public String getPageInView() {
        return (this.mInitialBrowseModule == null || this.mInitialBrowseModule.getLandingPage() == null) ? "local_browse_nearby" : this.mInitialBrowseModule.getLandingPage().getAnalyticsEventName();
    }

    public void refreshLastRequest() {
        LocalBrowseHistoryEntry currentBrowseHistoryEntry = getCurrentBrowseHistoryEntry();
        if (currentBrowseHistoryEntry == null) {
            return;
        }
        if (currentBrowseHistoryEntry.c() != null) {
            fetchSearchResults(currentBrowseHistoryEntry.c());
        } else {
            fetchSearchResults(currentBrowseHistoryEntry.b());
        }
    }

    public Location getInitialLocation() {
        return this.mInitialLocation;
    }

    public List<LocalMarketCard> getCurrentSearchResponse() {
        return this.mSearchResponse;
    }

    public boolean isRequestPending() {
        return this.mIsRequestPending;
    }

    private void fetchSearchResults(LocalSearchQuery localSearchQuery) {
        if (localSearchQuery != null) {
            getRequestQueue().a((Object) this);
            m a = m.a(LocalMarketCard.class, "/etsyapps/v3/public/localmarkets/markets/search");
            HashMap hashMap = new HashMap();
            hashMap.put(ResponseConstants.LATITUDE, String.valueOf(localSearchQuery.getLatitude()));
            hashMap.put(ResponseConstants.LONGITUDE, String.valueOf(localSearchQuery.getLongitude()));
            hashMap.put(ResponseConstants.RADIUS, String.valueOf(localSearchQuery.getOrDefaultRadius()));
            a.a((Map<String, String>) hashMap);
            addCommonHandlers(a);
            getRequestQueue().a((Object) this, (g<Result>) a.a());
        }
    }

    private void fetchSearchResults(String str) {
        getRequestQueue().a((Object) this);
        m a = m.a(LocalMarketCard.class, str);
        addCommonHandlers(a);
        getRequestQueue().a((Object) this, (g<Result>) a.a());
    }

    private void addCommonHandlers(m mVar) {
        mVar.a((c) new c() {
            public void a() {
                LocalSearchActivity.this.mIsRequestPending = true;
                LocalSearchActivity.this.showSearchingText();
                for (b onBrowseRequestPending : LocalSearchActivity.this.mBrowseUpdateListeners) {
                    onBrowseRequestPending.onBrowseRequestPending();
                }
                LocalSearchActivity.this.updateFilterBar();
            }
        });
        mVar.a((com.etsy.android.lib.core.f.c<Result>) new com.etsy.android.lib.core.f.c<LocalMarketCard>() {
            public void a(List<LocalMarketCard> list, int i, k<LocalMarketCard> kVar) {
                LocalSearchActivity.this.hideSearchingText();
                LocalSearchActivity.this.mIsRequestPending = false;
                LocalSearchActivity.this.mSearchResponse = list;
                for (b onSearchResultsSuccess : LocalSearchActivity.this.mBrowseUpdateListeners) {
                    onSearchResultsSuccess.onSearchResultsSuccess(LocalSearchActivity.this.mSearchResponse);
                }
                LocalSearchActivity.this.updateFilterBar();
            }
        });
        mVar.a((a) new a() {
            public void a(k kVar) {
                LocalSearchActivity.this.hideSearchingText();
                LocalSearchActivity.this.mIsRequestPending = false;
                LocalSearchActivity.this.mSearchResponse = new ArrayList();
                for (b onResultsEmpty : LocalSearchActivity.this.mBrowseUpdateListeners) {
                    onResultsEmpty.onResultsEmpty();
                }
                LocalSearchActivity.this.updateFilterBar();
            }
        });
        mVar.a((b) new b() {
            public void a(int i, String str, k kVar) {
                LocalSearchActivity.this.hideSearchingText();
                LocalSearchActivity.this.mIsRequestPending = false;
                LocalSearchActivity.this.mSearchResponse = null;
                for (b onResultsError : LocalSearchActivity.this.mBrowseUpdateListeners) {
                    onResultsError.onResultsError();
                }
                if (!LocalSearchActivity.this.mIsListPanelShowing) {
                    LocalSearchActivity.this.showListPanel();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void showSearchingText() {
        if (this.mSearchingText != null) {
            this.mSearchingText.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void hideSearchingText() {
        if (this.mSearchingText != null) {
            this.mSearchingText.setVisibility(8);
        }
    }

    public void showEnableLocationMessage() {
        showEnableLocationDefaultView();
        this.mEnableLocationButton.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                j.a((Activity) LocalSearchActivity.this);
            }
        });
    }

    public void showWaitingForLocationMessage() {
        showEnableLocationDefaultView();
        this.mEnableLocationMessage.setText(R.string.waiting_for_location_description);
        this.mEnableLocationButton.setVisibility(8);
    }

    public void hideEnableLocationMessage() {
        this.mEnableLocationView.setVisibility(8);
    }

    public void showGooglePlayResolution(final ConnectionResult connectionResult) {
        showEnableLocationDefaultView();
        this.mEnableLocationButton.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                try {
                    connectionResult.startResolutionForResult(LocalSearchActivity.this, 0);
                } catch (SendIntentException unused) {
                    f.d(LocalSearchActivity.TAG, "Error with resolution attempt");
                }
            }
        });
    }

    public void showGooglePlayErrorMessage(@StringRes int i) {
        showEnableLocationDefaultView();
        this.mEnableLocationButton.setText(i);
        this.mEnableLocationButton.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                com.etsy.android.lib.messaging.a.c((Activity) LocalSearchActivity.this);
            }
        });
    }

    public void onLocationReceived(Location location) {
        if (this.mSearchResponse == null) {
            this.mInitialLocation = location;
            for (b onInitialLocation : this.mBrowseUpdateListeners) {
                onInitialLocation.onInitialLocation(this.mInitialLocation);
            }
            if (this.mInitialBrowseModule != null) {
                onLoadBrowseSection(this.mInitialBrowseModule);
            } else {
                onNewSearchQuery(new LocalSearchQuery(this.mInitialLocation.getLatitude(), this.mInitialLocation.getLongitude()));
            }
            updateTitle();
        }
    }

    private void showEnableLocationDefaultView() {
        this.mEnableLocationView.setVisibility(0);
        this.mEnableLocationMessage.setText(R.string.enable_location_description);
        this.mEnableLocationButton.setVisibility(0);
        this.mEnableLocationButton.setText(R.string.enable_location);
    }

    /* access modifiers changed from: private */
    public void showListPanel() {
        if (!isListPanelShowing()) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.setCustomAnimations(R.anim.local_search_list_panel_show, 0);
            Fragment findFragmentById = supportFragmentManager.findFragmentById(R.id.list_fragment_frame);
            if (findFragmentById != null) {
                beginTransaction.show(findFragmentById);
            } else {
                beginTransaction.add(R.id.list_fragment_frame, new LocalSearchListFragment(), LocalBrowseListFragment.class.getSimpleName());
            }
            beginTransaction.commitAllowingStateLoss();
            this.mIsListPanelShowing = true;
            for (b onToggleListPanel : this.mBrowseUpdateListeners) {
                onToggleListPanel.onToggleListPanel();
            }
            updateFilterBar();
        }
    }

    private void hideListPanel() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment findFragmentById = supportFragmentManager.findFragmentById(R.id.list_fragment_frame);
        if (findFragmentById != null) {
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.setCustomAnimations(0, R.anim.local_search_list_panel_hide);
            beginTransaction.hide(findFragmentById);
            beginTransaction.commitAllowingStateLoss();
            this.mIsListPanelShowing = false;
            for (b onToggleListPanel : this.mBrowseUpdateListeners) {
                onToggleListPanel.onToggleListPanel();
            }
            updateFilterBar();
        }
    }

    private void restoreListPanelShownState() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment findFragmentById = supportFragmentManager.findFragmentById(R.id.list_fragment_frame);
        if (findFragmentById != null) {
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            if (this.mIsListPanelShowing) {
                beginTransaction.show(findFragmentById);
            } else {
                beginTransaction.hide(findFragmentById);
            }
            beginTransaction.commitAllowingStateLoss();
        }
    }

    private void showFilterBar(boolean z) {
        if (this.mFilterBar == null) {
            this.mFilterBar = getAppBarHelper().addExtension(z);
            this.mFilterBar.addView(getLayoutInflater().inflate(R.layout.local_browse_filter_bar, this.mFilterBar, false));
        }
        this.mSearchingText = (LinearLayout) this.mFilterBar.findViewById(R.id.layout_searching_this_area);
    }

    /* access modifiers changed from: private */
    public void updateFilterBar() {
        if (this.mFilterBar != null) {
            hideExpandRadiusButton();
            String str = "";
            if (!this.mIsRequestPending) {
                int size = this.mSearchResponse != null ? this.mSearchResponse.size() : 0;
                String quantityString = getResources().getQuantityString(R.plurals.local_browse_results_summary, size, new Object[]{Integer.valueOf(size)});
                if (size == 0) {
                    showExpandRadiusButton();
                }
                str = quantityString;
            }
            ((TextView) this.mFilterBar.findViewById(R.id.txt_filter_summary)).setText(str);
            this.mFilterBar.findViewById(R.id.layout_filter_summary).setVisibility(0);
            this.mFilterBar.findViewById(R.id.txt_search_this_area).setVisibility(8);
        }
    }

    private void showSearchThisAreaButton(double d, double d2, int i) {
        if (this.mFilterBar != null) {
            this.mFilterBar.findViewById(R.id.layout_filter_summary).setVisibility(8);
            this.mSearchingText.setVisibility(8);
            this.mFilterBar.findViewById(R.id.txt_search_this_area).setVisibility(0);
            View findViewById = this.mFilterBar.findViewById(R.id.txt_search_this_area);
            final double d3 = d;
            final double d4 = d2;
            final int i2 = i;
            AnonymousClass8 r1 = new TrackingOnClickListener() {
                public void onPreTrack() {
                    addEventTrackedAttribute(AnalyticsLogAttribute.LAT, (Object) Double.valueOf(d3));
                    addEventTrackedAttribute(AnalyticsLogAttribute.LON, (Object) Double.valueOf(d4));
                }

                public void onViewClick(View view) {
                    LocalSearchActivity localSearchActivity = LocalSearchActivity.this;
                    LocalSearchQuery localSearchQuery = new LocalSearchQuery(d3, d4, i2);
                    localSearchActivity.onNewSearchQuery(localSearchQuery);
                }
            };
            findViewById.setOnClickListener(r1);
        }
    }

    private void showExpandRadiusButton() {
        if (this.mFilterBar != null) {
            this.mFilterBar.findViewById(R.id.txt_expand_radius).setVisibility(0);
            this.mFilterBar.findViewById(R.id.txt_expand_radius).setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    LocalSearchActivity.this.expandSearchRadius();
                }
            });
        }
    }

    private void hideExpandRadiusButton() {
        if (this.mFilterBar != null) {
            this.mFilterBar.findViewById(R.id.txt_expand_radius).setVisibility(8);
        }
    }

    private void hideFilterBar() {
        getAppBarHelper().removeExtension(this.mFilterBar, true);
        this.mFilterBar = null;
        this.mSearchingText = null;
    }

    private void updateTitle() {
        LocalBrowseHistoryEntry currentBrowseHistoryEntry = getCurrentBrowseHistoryEntry();
        if (currentBrowseHistoryEntry != null) {
            getAppBarHelper().setTitle((CharSequence) currentBrowseHistoryEntry.d());
        }
    }

    private void pushBrowseHistory(LocalBrowseHistoryEntry localBrowseHistoryEntry) {
        if (this.mBrowseHistory == null) {
            this.mBrowseHistory = new ArrayList();
        }
        this.mBrowseHistory.add(localBrowseHistoryEntry);
    }

    private LocalBrowseHistoryEntry popBrowseHistory() {
        if (this.mBrowseHistory == null || this.mBrowseHistory.isEmpty()) {
            return null;
        }
        return (LocalBrowseHistoryEntry) this.mBrowseHistory.remove(this.mBrowseHistory.size() - 1);
    }

    private void replaceLastBrowseHistoryEntry(LocalBrowseHistoryEntry localBrowseHistoryEntry) {
        popBrowseHistory();
        pushBrowseHistory(localBrowseHistoryEntry);
    }

    private LocalBrowseHistoryEntry getCurrentBrowseHistoryEntry() {
        if (this.mBrowseHistory == null || this.mBrowseHistory.isEmpty()) {
            return null;
        }
        return (LocalBrowseHistoryEntry) this.mBrowseHistory.get(this.mBrowseHistory.size() - 1);
    }

    private LocalBrowseHistoryEntry getPreviousBrowseHistoryEntry() {
        if (this.mBrowseHistory == null || this.mBrowseHistory.size() <= 1) {
            return null;
        }
        return (LocalBrowseHistoryEntry) this.mBrowseHistory.get(this.mBrowseHistory.size() - 2);
    }

    public void onBackPressed() {
        LocalBrowseHistoryEntry previousBrowseHistoryEntry = getPreviousBrowseHistoryEntry();
        if (this.mIsListPanelShowing && this.mStartOnFullMap) {
            hideListPanel();
        } else if (!this.mIsListPanelShowing && !this.mStartOnFullMap) {
            showListPanel();
        } else if (previousBrowseHistoryEntry != null) {
            if (previousBrowseHistoryEntry.c() != null) {
                fetchSearchResults(previousBrowseHistoryEntry.c());
            } else {
                fetchSearchResults(previousBrowseHistoryEntry.b());
            }
            popBrowseHistory();
            updateTitle();
        } else {
            super.onBackPressed();
        }
    }
}
