package com.etsy.android.ui.homescreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import com.etsy.android.R;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.apiv3.ListingCollection;
import com.etsy.android.lib.models.cardviewelement.Page;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.ui.cardview.ListingStateChangeViewHolderFactoryRecyclerViewAdapter;
import com.etsy.android.ui.cardview.a;
import com.etsy.android.ui.util.d;
import com.etsy.android.vespa.VespaBaseFragment;

public abstract class CardRecyclerViewBaseFragment extends VespaBaseFragment<Page> {
    private a mCardViewHolderFactory;
    BroadcastReceiver mStateChangeBroadCastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(EtsyAction.STATE_CHANGE.getAction())) {
                CardRecyclerViewBaseFragment.this.removeCachedResponses();
            }
        }
    };

    /* access modifiers changed from: protected */
    public void initAdapter() {
        this.mAdapter = new ListingStateChangeViewHolderFactoryRecyclerViewAdapter(getActivity(), getAnalyticsContext(), this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCardViewHolderFactory = new a(getActivity(), getAdapter(), getAnalyticsContext(), this);
        addDelegateViewHolderFactory(this.mCardViewHolderFactory);
    }

    public void onStart() {
        super.onStart();
    }

    public void onPause() {
        super.onPause();
        configureListingStateReceiver(true);
    }

    public void onResume() {
        super.onResume();
        configureListingStateReceiver(false);
    }

    public void onDestroy() {
        super.onDestroy();
        configureListingStateReceiver(false);
    }

    private void configureListingStateReceiver(boolean z) {
        if (this.mAdapter != null) {
            IntentFilter intentFilter = new IntentFilter(EtsyAction.STATE_CHANGE.getAction());
            if (z) {
                LocalBroadcastManager.getInstance(getActivity()).registerReceiver(((ListingStateChangeViewHolderFactoryRecyclerViewAdapter) this.mAdapter).getStateBroadcastReceiver(), intentFilter);
                LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.mStateChangeBroadCastReceiver, intentFilter);
            } else {
                LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(((ListingStateChangeViewHolderFactoryRecyclerViewAdapter) this.mAdapter).getStateBroadcastReceiver());
                LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mStateChangeBroadCastReceiver);
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 601 || intent == null) {
            super.onActivityResult(i, i2, intent);
            return;
        }
        ListingLike listingLike = (ListingLike) intent.getSerializableExtra("listing");
        ListingCollection listingCollection = (ListingCollection) intent.getSerializableExtra(Collection.TYPE_COLLECTION);
        if (listingLike != null && listingCollection != null && intent.getBooleanExtra("should_show_social_invites_prompt", false)) {
            d.a(getActivity(), (b) getAnalyticsContext(), listingCollection, listingLike);
        }
    }

    /* access modifiers changed from: protected */
    public void setLayoutManager(Class<? extends LayoutManager> cls) {
        if (cls == StaggeredGridLayoutManager.class) {
            this.mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(getResources().getInteger(R.integer.vespa_staggered_grid_layout_max_span), 1));
            this.mRecyclerView.setHasFixedSize(false);
            ListingStateChangeViewHolderFactoryRecyclerViewAdapter listingStateChangeViewHolderFactoryRecyclerViewAdapter = (ListingStateChangeViewHolderFactoryRecyclerViewAdapter) this.mRecyclerView.getAdapter();
            this.mCardViewHolderFactory.a(true);
        } else if (cls == GridLayoutManager.class) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), getAdapter().getViewHolderFactory().c());
            this.mRecyclerView.setLayoutManager(gridLayoutManager);
            gridLayoutManager.setSpanSizeLookup(getAdapter().getSpanSizeLookup());
            this.mCardViewHolderFactory.a(false);
        } else {
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            this.mCardViewHolderFactory.a(false);
        }
    }

    /* access modifiers changed from: protected */
    public void registerCardViewFactoryClickHandler(int i, com.etsy.android.vespa.b bVar) {
        this.mCardViewHolderFactory.a(i, bVar);
    }
}
