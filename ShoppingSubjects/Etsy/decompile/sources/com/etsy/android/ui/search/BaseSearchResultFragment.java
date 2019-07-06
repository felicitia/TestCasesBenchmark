package com.etsy.android.ui.search;

import android.os.Bundle;
import com.etsy.android.ui.EtsyEndlessListFragment;
import com.etsy.android.uikit.listwrapper.a.C0109a;

public abstract class BaseSearchResultFragment extends EtsyEndlessListFragment implements C0109a {
    protected String mQuery;

    public void onCreate(Bundle bundle) {
        setRetainInstance(true);
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mQuery = getArguments().getString("SEARCH_QUERY");
        }
    }

    public String getQuery() {
        return this.mQuery;
    }
}
