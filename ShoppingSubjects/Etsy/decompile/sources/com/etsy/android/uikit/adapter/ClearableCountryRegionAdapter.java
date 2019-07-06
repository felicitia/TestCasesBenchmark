package com.etsy.android.uikit.adapter;

import android.content.Context;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.models.Region;
import java.util.List;

public class ClearableCountryRegionAdapter extends CountryRegionAdapter {
    private static Country empty = new Country();

    public ClearableCountryRegionAdapter(Context context, List<Country> list, List<Region> list2, Country country) {
        super(context, list, list2, country, true);
    }

    /* access modifiers changed from: protected */
    public void setupData() {
        internalAdd(empty);
        super.setupData();
    }
}
