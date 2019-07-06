package com.etsy.android.ui.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.ui.cardview.a;
import com.etsy.android.ui.cardview.clickhandlers.g;
import com.etsy.android.vespa.VespaBaseFragment;
import com.etsy.android.vespa.b.b;

public class FavoriteItemsFragment extends VespaBaseFragment {
    b mPaginatorForNextLink = new b();
    private EtsyId mUserId;

    /* access modifiers changed from: protected */
    public boolean useConsistentRecyclerViewPadding() {
        return true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mUserId = getArguments() != null ? (EtsyId) getArguments().getSerializable("user_id") : new EtsyId();
        a aVar = new a(getActivity(), getAdapter(), getAnalyticsContext());
        aVar.a((int) R.id.view_type_listing_collection, (com.etsy.android.vespa.b) new g(getTrackingName(), getActivity(), getAnalyticsContext(), this));
        addDelegateViewHolderFactory(aVar);
        return onCreateView;
    }

    public com.etsy.android.vespa.b.a getPagination() {
        return this.mPaginatorForNextLink;
    }

    public String getApiUrl() {
        return String.format("/etsyapps/v3/bespoke/member/users/%s/favorites-page", new Object[]{this.mUserId.getId()});
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != 0 && i == 600) {
            onRefresh();
        }
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }
}
