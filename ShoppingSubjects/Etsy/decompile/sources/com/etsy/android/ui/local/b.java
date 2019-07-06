package com.etsy.android.ui.local;

import android.location.Location;
import com.etsy.android.lib.models.LocalMarketCard;
import java.util.List;

/* compiled from: LocalBrowseUpdateListener */
public interface b {
    void onBrowseRequestPending();

    void onExpandSearchArea();

    void onInitialLocation(Location location);

    void onResultsEmpty();

    void onResultsError();

    void onSearchResultsSuccess(List<LocalMarketCard> list);

    void onToggleListPanel();
}
