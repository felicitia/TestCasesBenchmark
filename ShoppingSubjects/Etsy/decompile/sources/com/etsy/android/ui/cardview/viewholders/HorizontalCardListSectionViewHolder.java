package com.etsy.android.ui.cardview.viewholders;

import android.content.IntentFilter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.ui.cardview.ListingStateChangeViewHolderFactoryRecyclerViewAdapter;
import com.etsy.android.ui.cardview.a;
import com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter;
import com.etsy.android.vespa.c;
import com.etsy.android.vespa.viewholders.HorizontalListSectionViewHolder;

public class HorizontalCardListSectionViewHolder extends HorizontalListSectionViewHolder {
    public HorizontalCardListSectionViewHolder(FragmentActivity fragmentActivity, ViewGroup viewGroup, b bVar, boolean z, c cVar) {
        super(fragmentActivity, viewGroup, bVar, z, cVar);
        getAdapter().getViewHolderFactory().a((c) new a(fragmentActivity, getAdapter(), bVar));
        this.itemView.addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
            ListingStateChangeViewHolderFactoryRecyclerViewAdapter a = ((ListingStateChangeViewHolderFactoryRecyclerViewAdapter) HorizontalCardListSectionViewHolder.this.getAdapter());

            public void onViewAttachedToWindow(View view) {
                LocalBroadcastManager.getInstance(HorizontalCardListSectionViewHolder.this.itemView.getContext()).registerReceiver(this.a.getStateBroadcastReceiver(), new IntentFilter(EtsyAction.STATE_CHANGE.getAction()));
            }

            public void onViewDetachedFromWindow(View view) {
                LocalBroadcastManager.getInstance(HorizontalCardListSectionViewHolder.this.itemView.getContext()).unregisterReceiver(this.a.getStateBroadcastReceiver());
            }
        });
    }

    /* access modifiers changed from: protected */
    public BaseViewHolderFactoryRecyclerViewAdapter initAdapter(FragmentActivity fragmentActivity, b bVar, c cVar) {
        return new ListingStateChangeViewHolderFactoryRecyclerViewAdapter(fragmentActivity, bVar);
    }
}
