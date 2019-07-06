package com.etsy.android.ui.local;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender.SendIntentException;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.m;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.LocalBrowseModule;
import com.etsy.android.lib.models.apiv3.LocalBrowseResponse;
import com.etsy.android.lib.models.interfaces.LocalMarketLike;
import com.etsy.android.lib.util.ae;
import com.etsy.android.lib.util.l;
import com.etsy.android.lib.util.w;
import com.etsy.android.lib.util.x;
import com.etsy.android.ui.cardview.viewholders.LocalBrowseBaseHeaderViewHolder;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import com.etsy.android.uikit.adapter.AbstractContextRecyclerViewAdapter;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.adapter.SectionedRecyclerViewAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.j;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.c;
import com.google.android.gms.maps.c.C0141c;
import com.google.android.gms.maps.e;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.parceler.d;

public class LocalBrowseHomescreenFragment extends BaseRecyclerViewListFragment implements OnRequestPermissionsResultCallback, LoaderCallbacks<Cursor>, com.etsy.android.lib.core.v.b, a, e {
    private static final int BROWSE_RADIUS_SERVER_DEFAULT = -1;
    private static final String BROWSE_RESPONSE = "browse_response";
    private static final float DEFAULT_MAP_ZOOM_LEVEL = 10.0f;
    private static final String HISTORY_IDS = "history_ids";
    private static final String INITIAL_LOCATION = "initial_location";
    public static final int LOADER_HISTORY = 2;
    private static final String MAPVIEW_SAVEDINSTANCESTATE = "mapview_savedinstancestate";
    private static final int PERMISSIONS_RESULT = 0;
    /* access modifiers changed from: private */
    public static final String TAG = f.a(LocalBrowseHomescreenFragment.class);
    /* access modifiers changed from: private */
    public LocalBrowseResponse mBrowseResponse;
    private Button mEnableLocationButton;
    private TextView mEnableLocationMessage;
    private View mEnableLocationView;
    private c mGoogleMap;
    protected com.etsy.android.ui.cardview.viewholders.LocalBrowseBaseHeaderViewHolder.a mHeaderListener = new com.etsy.android.ui.cardview.viewholders.LocalBrowseBaseHeaderViewHolder.a() {
        public void a() {
            com.etsy.android.ui.nav.e.a(LocalBrowseHomescreenFragment.this.getActivity()).a().a((LocalBrowseModule) null, true);
        }
    };
    private Location mInitialLocation;
    private View mListBg;
    private View mListBgForOverlap;
    private d mLocationManager;
    /* access modifiers changed from: private */
    public OnGlobalLayoutListener mMapLayoutListener;
    private MapView mMapView;
    /* access modifiers changed from: private */
    public ViewTreeObserver mMapViewTreeObserver;
    private f mMarketCardDecoration;
    protected com.etsy.android.ui.cardview.viewholders.LocalBrowseMarketViewHolder.a mMarketListener = new com.etsy.android.ui.cardview.viewholders.LocalBrowseMarketViewHolder.a() {
        public void a(LocalMarketCard localMarketCard) {
            com.etsy.android.ui.nav.e.a((Activity) LocalBrowseHomescreenFragment.this.getActivity()).a(localMarketCard.getLocalMarketId(), false);
        }
    };
    private ImageView mPlaceholder;
    private ArrayList<String> mRecentlyViewedMarketIds = new ArrayList<>();
    private com.etsy.android.ui.cardview.viewholders.LocalBrowseSectionFooterViewHolder.a mSectionListener = new com.etsy.android.ui.cardview.viewholders.LocalBrowseSectionFooterViewHolder.a() {
        public void a(LocalBrowseModule localBrowseModule) {
            com.etsy.android.ui.nav.e.a(LocalBrowseHomescreenFragment.this.getActivity()).a().a(localBrowseModule, false);
        }
    };

    private class a extends LocalBrowseBaseHeaderViewHolder {
        public a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(viewGroup, LocalBrowseHomescreenFragment.this.mHeaderListener);
        }
    }

    private class b extends SectionedRecyclerViewAdapter {
        public b(FragmentActivity fragmentActivity, com.etsy.android.lib.core.img.c cVar) {
            super(fragmentActivity, cVar);
        }

        public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
            return new a(this.mInflater, viewGroup);
        }

        public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
            ((LocalBrowseBaseHeaderViewHolder) viewHolder).bind(true);
        }
    }

    @LayoutRes
    public int getLayoutId() {
        return R.layout.fragment_local_browse_homescreen;
    }

    @NonNull
    public String getTrackingName() {
        return "local_browse";
    }

    public void onLoaderReset(Loader<Cursor> loader) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAdapter = new b(getActivity(), getImageBatch());
        x.a(0, (OnRequestPermissionsResultCallback) this);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public LayoutManager createLayoutManager() {
        final int integer = getResources().getInteger(R.integer.local_browse_list_columns);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), integer);
        AnonymousClass12 r2 = new SpanSizeLookup() {
            public int getSpanSize(int i) {
                return LocalBrowseHomescreenFragment.this.mAdapter.getSpanSize(i, integer);
            }
        };
        r2.setSpanIndexCacheEnabled(true);
        gridLayoutManager.setSpanSizeLookup(r2);
        this.mMarketCardDecoration = new f(getActivity(), r2, false);
        return gridLayoutManager;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle bundle2;
        ae.b(false);
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mLocationManager = new d(getActivity(), this);
        if (bundle != null) {
            bundle2 = bundle.getBundle(MAPVIEW_SAVEDINSTANCESTATE);
            this.mInitialLocation = (Location) bundle.getParcelable(INITIAL_LOCATION);
            this.mBrowseResponse = (LocalBrowseResponse) d.a(bundle.getParcelable(BROWSE_RESPONSE));
            bundle.remove(BROWSE_RESPONSE);
            this.mRecentlyViewedMarketIds = bundle.getStringArrayList(HISTORY_IDS);
        } else {
            bundle2 = null;
        }
        this.mMapView = (MapView) onCreateView.findViewById(R.id.map);
        this.mMapView.onCreate(bundle2);
        com.google.android.gms.maps.d.a(getActivity());
        onCreateView.findViewById(R.id.empty_button).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (LocalBrowseHomescreenFragment.this.mBrowseResponse != null) {
                    LocalBrowseHomescreenFragment.this.fetchBrowseResults(LocalBrowseHomescreenFragment.this.mBrowseResponse.getRadius() * 2);
                } else {
                    LocalBrowseHomescreenFragment.this.fetchBrowseResults(-1);
                }
            }
        });
        AnonymousClass14 r3 = new TrackingOnClickListener() {
            public void onViewClick(View view) {
                com.etsy.android.ui.nav.e.a(LocalBrowseHomescreenFragment.this.getActivity()).a().a((LocalBrowseModule) null, true);
            }
        };
        onCreateView.findViewById(R.id.empty_result_map_click_area).setOnClickListener(r3);
        onCreateView.findViewById(R.id.no_internet_map_click_area).setOnClickListener(r3);
        this.mRecyclerView.addItemDecoration(this.mMarketCardDecoration);
        this.mRecyclerView.addOnScrollListener(new OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                if (((BaseRecyclerViewAdapter) recyclerView.getAdapter()).getHeaderCount() <= 0 || recyclerView.getChildAdapterPosition(recyclerView.getChildAt(0)) != 0) {
                    recyclerView.setVerticalScrollBarEnabled(true);
                } else {
                    recyclerView.setVerticalScrollBarEnabled(false);
                }
            }
        });
        v.a().a((com.etsy.android.lib.core.v.b) this);
        return onCreateView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.sk_orange_30));
        this.mEnableLocationView = view.findViewById(R.id.partial_local_fix_location);
        this.mEnableLocationMessage = (TextView) view.findViewById(R.id.enable_location_message);
        this.mEnableLocationButton = (Button) view.findViewById(R.id.enable_location_button);
        this.mPlaceholder = (ImageView) view.findViewById(R.id.placeholder);
        if (this.mBrowseResponse == null) {
            this.mPlaceholder.setVisibility(0);
        }
        this.mListBg = view.findViewById(R.id.list_bg);
        this.mListBgForOverlap = view.findViewById(R.id.list_bg_for_header_overlap);
        if (getActivity() != null && x.a((Context) getActivity(), "android.permission.ACCESS_FINE_LOCATION") != 0) {
            showEnablePermissionsView();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (!l.b((Activity) getActivity())) {
            this.mListBg.setVisibility(0);
            this.mListBgForOverlap.setVisibility(8);
        }
        if (bundle == null) {
            getActivity().getSupportLoaderManager().initLoader(2, null, this);
        }
        this.mMapView.getMapAsync(this);
        if (this.mBrowseResponse != null && this.mBrowseResponse.getSections() != null) {
            fillAdapter(this.mBrowseResponse.getSections());
        }
    }

    public void onStart() {
        super.onStart();
        this.mLocationManager.a();
    }

    public void onStop() {
        this.mLocationManager.b();
        super.onStop();
    }

    public void onDestroyView() {
        v.a().b((com.etsy.android.lib.core.v.b) this);
        this.mLocationManager.c();
        super.onDestroyView();
        ae.a(false);
        if (!(this.mMapViewTreeObserver == null || this.mMapLayoutListener == null)) {
            j.b(this.mMapViewTreeObserver, this.mMapLayoutListener);
        }
        this.mMapViewTreeObserver = null;
        this.mMapLayoutListener = null;
        this.mMapView = null;
        this.mGoogleMap = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.mMapView != null) {
            Bundle bundle2 = new Bundle(bundle);
            this.mMapView.onSaveInstanceState(bundle2);
            bundle.putBundle(MAPVIEW_SAVEDINSTANCESTATE, bundle2);
        }
        bundle.putParcelable(INITIAL_LOCATION, this.mInitialLocation);
        bundle.putParcelable(BROWSE_RESPONSE, d.a(this.mBrowseResponse));
        bundle.putStringArrayList(HISTORY_IDS, this.mRecentlyViewedMarketIds);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        if (isRefreshing()) {
            refreshLocation();
        } else if (this.mBrowseResponse != null) {
            fillAdapter(this.mBrowseResponse.getSections());
        } else if (this.mInitialLocation != null) {
            fetchBrowseResults(-1);
        }
    }

    /* access modifiers changed from: private */
    public void fetchBrowseResults(int i) {
        if (this.mInitialLocation != null) {
            m a2 = m.a(LocalBrowseResponse.class, v.a().e() ? "/etsyapps/v3/bespoke/member/localmarkets/browse" : "/etsyapps/v3/bespoke/public/localmarkets/browse");
            HashMap hashMap = new HashMap();
            hashMap.put(ResponseConstants.LATITUDE, String.valueOf(this.mInitialLocation.getLatitude()));
            hashMap.put(ResponseConstants.LONGITUDE, String.valueOf(this.mInitialLocation.getLongitude()));
            if (i > 0) {
                hashMap.put(ResponseConstants.RADIUS, String.valueOf(i));
            }
            if (getActivity() != null) {
                hashMap.put("is_tablet", String.valueOf(l.c((Activity) getActivity())));
            }
            a2.a(addRecentlyViewedMarkets(hashMap));
            a2.a((m.c) new m.c() {
                public void a() {
                    LocalBrowseHomescreenFragment.this.showLoadingView();
                }
            });
            a2.a((com.etsy.android.lib.core.f.c<Result>) new com.etsy.android.lib.core.f.c<LocalBrowseResponse>() {
                public void a(List<LocalBrowseResponse> list, int i, k<LocalBrowseResponse> kVar) {
                    LocalBrowseResponse localBrowseResponse = (LocalBrowseResponse) list.get(0);
                    LocalBrowseHomescreenFragment.this.mBrowseResponse = localBrowseResponse;
                    LocalBrowseHomescreenFragment.this.fillMapMarkers();
                    if (LocalBrowseHomescreenFragment.this.mBrowseResponse != null && LocalBrowseHomescreenFragment.this.mBrowseResponse.getSections() != null) {
                        LocalBrowseHomescreenFragment.this.fillAdapter(localBrowseResponse.getSections());
                    }
                }
            });
            a2.a((com.etsy.android.lib.core.f.a) new com.etsy.android.lib.core.f.a() {
                public void a(k kVar) {
                    LocalBrowseHomescreenFragment.this.mBrowseResponse = new LocalBrowseResponse();
                }
            });
            a2.a((com.etsy.android.lib.core.f.b) new com.etsy.android.lib.core.f.b() {
                public void a(int i, String str, k kVar) {
                    LocalBrowseHomescreenFragment.this.mBrowseResponse = null;
                    LocalBrowseHomescreenFragment.this.onLoadFailure();
                }
            });
            getRequestQueue().a((Object) this, (g<Result>) a2.a());
        }
    }

    private Map<String, String> addRecentlyViewedMarkets(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.mRecentlyViewedMarketIds.size(); i++) {
            sb.append((String) this.mRecentlyViewedMarketIds.get(i));
            if (i < this.mRecentlyViewedMarketIds.size() - 1) {
                sb.append(",");
            }
        }
        if (sb.toString().length() > 0) {
            map.put("recent_market_ids", sb.toString());
        }
        return map;
    }

    /* access modifiers changed from: private */
    public void fillAdapter(@NonNull List<LocalBrowseModule> list) {
        this.mAdapter.clear();
        this.mAdapter.addHeader(AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER);
        ArrayList arrayList = new ArrayList();
        for (LocalBrowseModule localBrowseModule : list) {
            if (localBrowseModule.getLocalMarkets() != null && !localBrowseModule.getLocalMarkets().isEmpty()) {
                LocalBrowseAdapter localBrowseAdapter = new LocalBrowseAdapter(getActivity(), getImageBatch(), this.mMarketListener, this.mSectionListener);
                localBrowseAdapter.addSectionData(localBrowseModule);
                localBrowseAdapter.addItems(localBrowseModule.getLocalMarkets());
                arrayList.add(localBrowseAdapter);
            }
        }
        onLoadSuccess(arrayList, 0);
    }

    public void onSignedInChanged(Context context, boolean z) {
        onRefresh();
    }

    public void onMapReady(c cVar) {
        this.mGoogleMap = cVar;
        if (this.mMapView != null) {
            this.mMapViewTreeObserver = this.mMapView.getViewTreeObserver();
            this.mMapLayoutListener = new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    j.b(LocalBrowseHomescreenFragment.this.mMapViewTreeObserver, LocalBrowseHomescreenFragment.this.mMapLayoutListener);
                    LocalBrowseHomescreenFragment.this.mMapViewTreeObserver = null;
                    LocalBrowseHomescreenFragment.this.mMapLayoutListener = null;
                    LocalBrowseHomescreenFragment.this.setupMap();
                }
            };
            j.a(this.mMapViewTreeObserver, this.mMapLayoutListener);
        }
    }

    /* access modifiers changed from: private */
    public void setupMap() {
        if (this.mGoogleMap != null) {
            if (this.mInitialLocation != null) {
                this.mGoogleMap.a(com.google.android.gms.maps.b.a(new LatLng(this.mInitialLocation.getLatitude(), this.mInitialLocation.getLongitude()), (float) DEFAULT_MAP_ZOOM_LEVEL));
            }
            if (this.mBrowseResponse != null) {
                fillMapMarkers();
            }
            this.mGoogleMap.a((C0141c) new C0141c() {
                public void a(CameraPosition cameraPosition) {
                    LocalBrowseHomescreenFragment.this.hidePlaceholder();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void hidePlaceholder() {
        if (this.mBrowseResponse != null && this.mGoogleMap != null && this.mPlaceholder != null) {
            this.mPlaceholder.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void fillMapMarkers() {
        if (this.mGoogleMap != null) {
            this.mGoogleMap.b();
            if (this.mBrowseResponse == null || this.mBrowseResponse.getNearbyMarkets() == null || this.mBrowseResponse.getNearbyMarkets().isEmpty()) {
                if (this.mBrowseResponse != null) {
                    hidePlaceholder();
                }
                return;
            }
            com.google.android.gms.maps.model.LatLngBounds.a aVar = new com.google.android.gms.maps.model.LatLngBounds.a();
            for (int i = 0; i < this.mBrowseResponse.getNearbyMarkets().size(); i++) {
                LocalMarketCard localMarketCard = (LocalMarketCard) this.mBrowseResponse.getNearbyMarkets().get(i);
                try {
                    LatLng latLng = new LatLng(Double.valueOf(localMarketCard.getLat()).doubleValue(), Double.valueOf(localMarketCard.getLon()).doubleValue());
                    this.mGoogleMap.a(new MarkerOptions().position(latLng).icon(com.etsy.android.lib.util.c.b.a((LocalMarketLike) localMarketCard)));
                    aVar.a(latLng);
                } catch (NumberFormatException e) {
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Bad latitude and longitude for local market with ID ");
                    sb.append(localMarketCard.getLocalMarketId());
                    f.e(str, sb.toString(), e);
                    com.etsy.android.lib.logger.legacy.b a2 = com.etsy.android.lib.logger.legacy.b.a();
                    String str2 = TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("NumberFormatException in latitude / longitude for local market with ID ");
                    sb2.append(localMarketCard.getLocalMarketId());
                    a2.a(str2, sb2.toString());
                }
            }
            try {
                this.mGoogleMap.a(com.google.android.gms.maps.b.a(aVar.a(), getResources().getDimensionPixelSize(R.dimen.local_browse_map_marker_bounds_padding)));
            } catch (IllegalStateException e2) {
                f.e(TAG, "Bad argument when building bounds for Google map", e2);
            }
        }
    }

    public void showEnableLocationMessage() {
        showEnableLocationDefaultView();
        this.mEnableLocationButton.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                com.etsy.android.lib.util.j.a((Activity) LocalBrowseHomescreenFragment.this.getActivity());
            }
        });
    }

    public void showWaitingForLocationMessage() {
        showEnableLocationDefaultView();
        this.mEnableLocationMessage.setText(R.string.waiting_for_location_description);
        this.mEnableLocationButton.setVisibility(8);
    }

    public void onLocationReceived(Location location) {
        this.mInitialLocation = location;
        setupMap();
        if (isRefreshing() || this.mBrowseResponse == null) {
            fetchBrowseResults(-1);
        }
    }

    public void showGooglePlayResolution(final ConnectionResult connectionResult) {
        showEnableLocationDefaultView();
        this.mEnableLocationButton.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                try {
                    connectionResult.startResolutionForResult(LocalBrowseHomescreenFragment.this.getActivity(), 0);
                } catch (SendIntentException unused) {
                    f.d(LocalBrowseHomescreenFragment.TAG, "Error with resolution attempt");
                }
            }
        });
    }

    public void showGooglePlayErrorMessage(@StringRes int i) {
        showEnableLocationDefaultView();
        this.mEnableLocationButton.setText(i);
        this.mEnableLocationButton.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                com.etsy.android.lib.messaging.a.c((Activity) LocalBrowseHomescreenFragment.this.getActivity());
            }
        });
    }

    public void hideEnableLocationMessage() {
        this.mEnableLocationView.setVisibility(8);
    }

    private void showEnableLocationDefaultView() {
        this.mEnableLocationView.setVisibility(0);
        this.mEnableLocationMessage.setText(R.string.enable_location_description);
        this.mEnableLocationButton.setVisibility(0);
        this.mEnableLocationButton.setText(R.string.enable_location);
    }

    private void showEnablePermissionsView() {
        this.mEnableLocationView.setVisibility(0);
        this.mEnableLocationButton.setVisibility(0);
        this.mEnableLocationButton.setText(R.string.enable_location);
        this.mEnableLocationMessage.setText(R.string.enable_location_description);
        this.mEnableLocationButton.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (LocalBrowseHomescreenFragment.this.getActivity() == null || !x.b(LocalBrowseHomescreenFragment.this.getActivity(), "android.permission.ACCESS_FINE_LOCATION")) {
                    LocalBrowseHomescreenFragment.this.requestPermissions();
                } else {
                    com.etsy.android.ui.nav.e.a(LocalBrowseHomescreenFragment.this.getActivity()).d().a("android.permission.ACCESS_FINE_LOCATION");
                }
            }
        });
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        CursorLoader cursorLoader = new CursorLoader(getActivity(), c.a, c.b, null, null, "view_time DESC LIMIT 12");
        return cursorLoader;
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        this.mRecentlyViewedMarketIds.clear();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                this.mRecentlyViewedMarketIds.add(cursor.getString(1));
            }
        }
    }

    /* access modifiers changed from: private */
    public void requestPermissions() {
        x.a((Activity) getActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 0, (OnRequestPermissionsResultCallback) this);
    }

    private void refreshLocation() {
        if (getActivity() == null) {
            return;
        }
        if (x.a((Context) getActivity(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
            this.mLocationManager.d();
        } else {
            requestPermissions();
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i != 0) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        } else if (w.a(iArr)) {
            refreshLocation();
        } else {
            showEnablePermissionsView();
        }
    }
}
