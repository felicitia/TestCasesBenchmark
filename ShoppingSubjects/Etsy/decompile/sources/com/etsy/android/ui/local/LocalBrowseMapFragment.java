package com.etsy.android.ui.local;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Point;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.apiv3.LocalBrowseResponse;
import com.etsy.android.lib.models.interfaces.LocalMarketLike;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.c;
import com.google.android.gms.maps.c.C0141c;
import com.google.android.gms.maps.c.b;
import com.google.android.gms.maps.c.d;
import com.google.android.gms.maps.e;
import com.google.android.gms.maps.g;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocalBrowseMapFragment extends EtsyFragment implements b, e {
    private static final float DEFAULT_ZOOM_LEVEL = 10.0f;
    private static final String SHOWN_INFO_WINDOW_INDEX = "shown_infowindow_index";
    private static final String TAG = f.a(LocalBrowseMapFragment.class);
    /* access modifiers changed from: private */
    public c mGoogleMap;
    /* access modifiers changed from: private */
    public boolean mHasCameraChanged;
    private final b mInfoWindowAdapter = new b() {
        public View b(com.google.android.gms.maps.model.c cVar) {
            return null;
        }

        public View a(com.google.android.gms.maps.model.c cVar) {
            LocalMarketCard localMarketCard = (LocalMarketCard) LocalBrowseMapFragment.this.mMarketMarkerMap.get(cVar.a());
            if (localMarketCard == null) {
                return null;
            }
            a aVar = new a(LocalBrowseMapFragment.this.getActivity().getLayoutInflater());
            aVar.a(LocalBrowseMapFragment.this.getImageBatch(), localMarketCard, cVar.a());
            return aVar.h;
        }
    };
    private boolean mKeepingMapState;
    /* access modifiers changed from: private */
    public com.google.android.gms.maps.model.c mLastOpenedMarker = null;
    /* access modifiers changed from: private */
    public a mLocalBrowseManager;
    private List<LocalMarketCard> mLocalMarkets;
    MapView mMapView;
    @Nullable
    private LatLngBounds mMarkerBounds;
    /* access modifiers changed from: private */
    public HashMap<String, LocalMarketCard> mMarketMarkerMap = new HashMap<>(0);
    /* access modifiers changed from: private */
    public boolean mNextCameraChangeIsManual;
    private d mOnInfoWindowClickListener = new d() {
        public void a(com.google.android.gms.maps.model.c cVar) {
            LocalMarketCard localMarketCard = (LocalMarketCard) LocalBrowseMapFragment.this.mMarketMarkerMap.get(cVar.a());
            if (localMarketCard != null) {
                com.etsy.android.ui.nav.e.a((Activity) LocalBrowseMapFragment.this.getActivity()).a(localMarketCard.getLocalMarketId(), false);
            }
        }
    };
    private c.e mOnMapClickListener = new c.e() {
        public void a(LatLng latLng) {
            LocalBrowseMapFragment.this.resetLastOpenedMarker();
        }
    };
    private final c.f mOnMarkerClickListener = new c.f() {
        public boolean a(com.google.android.gms.maps.model.c cVar) {
            if (LocalBrowseMapFragment.this.mLastOpenedMarker != null && !LocalBrowseMapFragment.this.mLastOpenedMarker.equals(cVar)) {
                LocalBrowseMapFragment.this.resetLastOpenedMarker();
            }
            LocalBrowseMapFragment.this.mLastOpenedMarker = cVar;
            LocalMarketCard localMarketCard = (LocalMarketCard) LocalBrowseMapFragment.this.mMarketMarkerMap.get(cVar.a());
            if (localMarketCard != null) {
                LocalBrowseMapFragment.this.getAnalyticsContext().a("map_pin_tapped", localMarketCard.getTrackingParameters());
                cVar.a(com.etsy.android.lib.util.c.b.b(localMarketCard));
            }
            int dimensionPixelSize = LocalBrowseMapFragment.this.getResources().getDimensionPixelSize(R.dimen.local_map_info_window_half_height_approx);
            g c = LocalBrowseMapFragment.this.mGoogleMap.c();
            Point a2 = c.a(cVar.b());
            a2.offset(0, -dimensionPixelSize);
            LocalBrowseMapFragment.this.mGoogleMap.b(com.google.android.gms.maps.b.a(c.a(a2)));
            cVar.d();
            return true;
        }
    };
    private String mShownInfoWindowIndex = null;
    private View mView;

    public class a {
        private final TextView b = ((TextView) this.h.findViewById(R.id.market_date));
        private final FrameLayout c = ((FrameLayout) this.h.findViewById(R.id.image_layout_1));
        private final FrameLayout d = ((FrameLayout) this.h.findViewById(R.id.image_layout_2));
        private final TextView e = ((TextView) this.h.findViewById(R.id.market_type));
        private final TextView f = ((TextView) this.h.findViewById(R.id.market_title));
        private final TextView g = ((TextView) this.h.findViewById(R.id.market_attendance));
        /* access modifiers changed from: private */
        public final View h;
        private int i;

        public a(LayoutInflater layoutInflater) {
            this.h = layoutInflater.inflate(R.layout.map_info_window_local, null, false);
        }

        public void a(com.etsy.android.lib.core.img.c cVar, LocalMarketCard localMarketCard, final String str) {
            if (localMarketCard != null) {
                Resources resources = this.h.getResources();
                boolean equals = LocalMarket.MARKET_TYPE_WHOLESALE_BUYER.equals(localMarketCard.getType());
                this.i = resources.getInteger(R.integer.local_browse_images_per_row);
                String dateSubtitle = localMarketCard.getDateSubtitle();
                if (!TextUtils.isEmpty(dateSubtitle)) {
                    this.b.setText(dateSubtitle);
                    this.b.setVisibility(0);
                } else {
                    this.b.setVisibility(8);
                }
                ArrayList arrayList = new ArrayList();
                if (!equals) {
                    arrayList.addAll(localMarketCard.getListingImages());
                } else {
                    arrayList.addAll(localMarketCard.getStoreImages());
                }
                com.etsy.android.lib.core.img.c cVar2 = cVar;
                g.a(this.c, this.d, cVar2, (List<? extends BaseModelImage>) arrayList, equals, this.i, (com.etsy.android.ui.local.g.a) new com.etsy.android.ui.local.g.a() {
                    public void a() {
                        if (LocalBrowseMapFragment.this.mLastOpenedMarker != null && LocalBrowseMapFragment.this.mLastOpenedMarker.a().equals(b()) && LocalBrowseMapFragment.this.mLastOpenedMarker.f()) {
                            LocalBrowseMapFragment.this.mLastOpenedMarker.d();
                        }
                    }

                    public String b() {
                        return str;
                    }
                });
                this.e.setText(localMarketCard.getMarketTypeLabelString(resources));
                this.f.setText(localMarketCard.getTitle());
                this.g.setVisibility(0);
                if (!localMarketCard.getAttendanceSummary(resources).isEmpty()) {
                    this.g.setText(localMarketCard.getAttendanceSummary(resources));
                } else {
                    this.g.setVisibility(8);
                }
            }
        }
    }

    public void onResultsEmpty() {
    }

    public void onResultsError() {
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mLocalBrowseManager = (a) activity;
            this.mLocalBrowseManager.registerUpdateListener(this);
        } catch (ClassCastException unused) {
            StringBuilder sb = new StringBuilder();
            sb.append(activity.toString());
            sb.append(" must implement LocalBrowseManager");
            throw new ClassCastException(sb.toString());
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mView = layoutInflater.inflate(R.layout.fragment_local_browse_map, viewGroup, false);
        this.mMapView = (MapView) this.mView.findViewById(R.id.map);
        this.mMapView.onCreate(bundle);
        com.google.android.gms.maps.d.a(getActivity());
        if (bundle != null) {
            this.mKeepingMapState = true;
            this.mShownInfoWindowIndex = bundle.getString(SHOWN_INFO_WINDOW_INDEX);
            this.mHasCameraChanged = true;
        }
        return this.mView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mView.findViewById(R.id.btn_list_view).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (LocalBrowseMapFragment.this.mLocalBrowseManager != null) {
                    LocalBrowseMapFragment.this.mLocalBrowseManager.onClickShowListView();
                }
            }
        });
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mLocalBrowseManager.getCurrentBrowseResponse() != null) {
            cacheMarketsList(this.mLocalBrowseManager.getCurrentBrowseResponse().getNearbyMarkets());
        } else {
            cacheMarketsList(this.mLocalBrowseManager.getCurrentSearchResponse());
        }
        if (this.mGoogleMap == null) {
            this.mMapView.getMapAsync(this);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.mMapView != null) {
            this.mMapView.onResume();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.mMapView != null) {
            this.mMapView.onPause();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.mLastOpenedMarker != null && this.mLastOpenedMarker.f()) {
            bundle.putString(SHOWN_INFO_WINDOW_INDEX, this.mLastOpenedMarker.c());
        }
        super.onSaveInstanceState(bundle);
        if (this.mMapView != null) {
            this.mMapView.onSaveInstanceState(bundle);
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mLocalBrowseManager.unregisterUpdateListener(this);
        this.mLocalBrowseManager = null;
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.mLastOpenedMarker != null) {
            this.mLastOpenedMarker = null;
        }
        if (this.mMarketMarkerMap != null) {
            this.mMarketMarkerMap.clear();
        }
        if (this.mGoogleMap != null) {
            this.mGoogleMap.a((C0141c) null);
            this.mGoogleMap.a((c.f) null);
            this.mGoogleMap.a((b) null);
            this.mGoogleMap.a((d) null);
            this.mGoogleMap.a((c.e) null);
            this.mGoogleMap.b();
        }
        this.mGoogleMap = null;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mMapView != null) {
            this.mMapView.onDestroy();
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (this.mMapView != null) {
            this.mMapView.onLowMemory();
        }
    }

    public void onInitialLocation(Location location) {
        moveMapToLocation(location);
    }

    public void onBrowseRequestPending() {
        clearCachedMarketsList();
        updateMap();
    }

    public void onBrowseResultsSuccess(LocalBrowseResponse localBrowseResponse) {
        cacheMarketsList(localBrowseResponse.getNearbyMarkets());
        updateMap();
    }

    public void onSearchResultsSuccess(List<LocalMarketCard> list) {
        this.mLocalMarkets = null;
        if (list != null && !list.isEmpty()) {
            this.mLocalMarkets = list;
            updateMap();
        }
    }

    public void onToggleListPanel() {
        animateMapToMarkerBounds();
        resetLastOpenedMarker();
    }

    public void onExpandSearchArea() {
        animateMap(com.google.android.gms.maps.b.a(-1.0f));
    }

    private void cacheMarketsList(List<LocalMarketCard> list) {
        this.mLocalMarkets = null;
        if (list != null && !list.isEmpty()) {
            this.mLocalMarkets = new ArrayList();
            this.mLocalMarkets.addAll(list);
        }
    }

    private void clearCachedMarketsList() {
        this.mLocalMarkets = null;
    }

    public void onMapReady(c cVar) {
        this.mGoogleMap = cVar;
        setupMap();
        if (this.mLocalMarkets != null) {
            if (!this.mKeepingMapState || this.mLocalBrowseManager.isListPanelShowing()) {
                updateMap();
                return;
            }
            updateMarkers();
            if (this.mLastOpenedMarker != null) {
                this.mLastOpenedMarker.d();
            }
        } else if (!this.mKeepingMapState) {
            moveMapToLocation(this.mLocalBrowseManager.getInitialLocation());
        }
    }

    private void setupMap() {
        if (this.mGoogleMap != null) {
            if (VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                this.mGoogleMap.a(true);
                this.mGoogleMap.a((C0141c) new C0141c() {
                    public void a(CameraPosition cameraPosition) {
                        if (!LocalBrowseMapFragment.this.mHasCameraChanged) {
                            LocalBrowseMapFragment.this.mHasCameraChanged = true;
                            LocalBrowseMapFragment.this.mNextCameraChangeIsManual = true;
                        } else if (!LocalBrowseMapFragment.this.mNextCameraChangeIsManual || LocalBrowseMapFragment.this.mLocalBrowseManager == null || LocalBrowseMapFragment.this.mGoogleMap == null) {
                            LocalBrowseMapFragment.this.mNextCameraChangeIsManual = true;
                        } else if (LocalBrowseMapFragment.this.mLastOpenedMarker == null || !LocalBrowseMapFragment.this.mLastOpenedMarker.f() || !LocalBrowseMapFragment.this.isLastOpenedMarkerOnScreen()) {
                            CameraPosition a2 = LocalBrowseMapFragment.this.mGoogleMap.a();
                            if (a2 != null && a2.target != null) {
                                LocalBrowseMapFragment.this.mLocalBrowseManager.onUserChangedMapPosition(a2.target.latitude, a2.target.longitude, LocalBrowseMapFragment.this.getMapVisibleRadius());
                            }
                        }
                    }
                });
            }
        }
    }

    private void updateMap() {
        if (this.mGoogleMap != null) {
            updateMarkers();
            animateMapToMarkerBounds();
        }
    }

    private void moveMapToLocation(Location location) {
        if (this.mGoogleMap != null && location != null) {
            this.mGoogleMap.a(com.google.android.gms.maps.b.a(new LatLng(location.getLatitude(), location.getLongitude()), (float) DEFAULT_ZOOM_LEVEL));
        }
    }

    private void animateMapToMarkerBounds() {
        int dimensionPixelOffset = getActivity().getResources().getDimensionPixelOffset(R.dimen.local_browse_header_map_height);
        int measuredHeight = (this.mLocalBrowseManager == null || !this.mLocalBrowseManager.isListPanelShowing() || this.mMapView == null) ? 0 : this.mMapView.getMeasuredHeight() - dimensionPixelOffset;
        if (this.mGoogleMap != null && this.mMarkerBounds != null) {
            this.mGoogleMap.a(0, 0, 0, measuredHeight);
            animateMap(com.google.android.gms.maps.b.a(this.mMarkerBounds, dimensionPixelOffset / 5));
        }
    }

    private void animateMap(com.google.android.gms.maps.a aVar) {
        if (this.mGoogleMap != null) {
            try {
                this.mGoogleMap.a(aVar, new com.google.android.gms.maps.c.a() {
                    public void a() {
                        LocalBrowseMapFragment.this.mNextCameraChangeIsManual = false;
                    }

                    public void b() {
                        LocalBrowseMapFragment.this.mNextCameraChangeIsManual = true;
                    }
                });
            } catch (IllegalStateException e) {
                f.c(TAG, "Crash animating camera", (Throwable) e);
            }
        }
    }

    private void updateMarkers() {
        this.mMarkerBounds = null;
        this.mGoogleMap.b();
        this.mMarketMarkerMap.clear();
        if (this.mLocalMarkets != null && !this.mLocalMarkets.isEmpty()) {
            com.google.android.gms.maps.model.LatLngBounds.a aVar = new com.google.android.gms.maps.model.LatLngBounds.a();
            for (int i = 0; i < this.mLocalMarkets.size(); i++) {
                LocalMarketCard localMarketCard = (LocalMarketCard) this.mLocalMarkets.get(i);
                try {
                    LatLng latLng = new LatLng(Double.valueOf(localMarketCard.getLat()).doubleValue(), Double.valueOf(localMarketCard.getLon()).doubleValue());
                    com.google.android.gms.maps.model.c a2 = this.mGoogleMap.a(new MarkerOptions().position(latLng).snippet(String.valueOf(i)).icon(com.etsy.android.lib.util.c.b.a((LocalMarketLike) localMarketCard)));
                    this.mMarketMarkerMap.put(a2.a(), localMarketCard);
                    aVar.a(latLng);
                    if (this.mShownInfoWindowIndex != null && this.mShownInfoWindowIndex.equals(a2.c())) {
                        a2.a(com.etsy.android.lib.util.c.b.b(localMarketCard));
                        this.mLastOpenedMarker = a2;
                    }
                } catch (NumberFormatException e) {
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Bad latitude and longitude for local market with ID ");
                    sb.append(localMarketCard.getLocalMarketId());
                    f.e(str, sb.toString(), e);
                    com.etsy.android.lib.logger.legacy.b a3 = com.etsy.android.lib.logger.legacy.b.a();
                    String str2 = TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("NumberFormatException in latitude / longitude for local market with ID ");
                    sb2.append(localMarketCard.getLocalMarketId());
                    a3.a(str2, sb2.toString());
                }
            }
            this.mShownInfoWindowIndex = null;
            try {
                this.mMarkerBounds = aVar.a();
            } catch (IllegalStateException e2) {
                f.e(TAG, "Bad argument when building bounds for Google map", e2);
            }
            this.mGoogleMap.a(this.mOnMarkerClickListener);
            this.mGoogleMap.a(this.mInfoWindowAdapter);
            this.mGoogleMap.a(this.mOnInfoWindowClickListener);
            this.mGoogleMap.a(this.mOnMapClickListener);
        }
    }

    /* access modifiers changed from: private */
    public int getMapVisibleRadius() {
        if (this.mGoogleMap == null) {
            return 0;
        }
        LatLngBounds latLngBounds = this.mGoogleMap.c().a().latLngBounds;
        Location location = new Location("northEast");
        location.setLatitude(latLngBounds.northeast.latitude);
        location.setLongitude(latLngBounds.northeast.longitude);
        Location location2 = new Location("southWest");
        location2.setLatitude(latLngBounds.southwest.latitude);
        location2.setLongitude(latLngBounds.southwest.longitude);
        return (int) (location.distanceTo(location2) / 2.0f);
    }

    /* access modifiers changed from: private */
    public void resetLastOpenedMarker() {
        if (this.mLastOpenedMarker != null) {
            if (this.mLastOpenedMarker.f()) {
                this.mLastOpenedMarker.e();
            }
            LocalMarketCard localMarketCard = (LocalMarketCard) this.mMarketMarkerMap.get(this.mLastOpenedMarker.a());
            if (localMarketCard != null) {
                this.mLastOpenedMarker.a(com.etsy.android.lib.util.c.b.a((LocalMarketLike) localMarketCard));
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean isLastOpenedMarkerOnScreen() {
        if (this.mLastOpenedMarker == null || this.mGoogleMap == null) {
            return false;
        }
        return this.mGoogleMap.c().a().latLngBounds.contains(this.mLastOpenedMarker.b());
    }
}
