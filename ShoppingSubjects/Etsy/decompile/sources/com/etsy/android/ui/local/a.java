package com.etsy.android.ui.local;

import android.location.Location;
import com.etsy.android.lib.models.LocalMarketCard;
import com.etsy.android.lib.models.apiv3.LocalBrowseModule;
import com.etsy.android.lib.models.apiv3.LocalBrowseResponse;
import java.util.List;

/* compiled from: LocalBrowseManager */
public interface a {
    void expandSearchRadius();

    LocalBrowseResponse getCurrentBrowseResponse();

    List<LocalMarketCard> getCurrentSearchResponse();

    Location getInitialLocation();

    boolean isListPanelShowing();

    boolean isRequestPending();

    void onClickMapArea();

    void onClickShowListView();

    void onLoadBrowseSection(LocalBrowseModule localBrowseModule);

    void onUserChangedMapPosition(double d, double d2, int i);

    void refreshLastRequest();

    void registerUpdateListener(b bVar);

    void unregisterUpdateListener(b bVar);
}
