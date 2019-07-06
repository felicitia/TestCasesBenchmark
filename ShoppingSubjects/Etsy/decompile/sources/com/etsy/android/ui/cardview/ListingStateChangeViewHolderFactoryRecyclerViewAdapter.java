package com.etsy.android.ui.cardview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView.ViewHolder;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.interfaces.ListingLike;
import com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter;
import com.etsy.android.vespa.k;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import java.util.HashMap;

public class ListingStateChangeViewHolderFactoryRecyclerViewAdapter extends BaseViewHolderFactoryRecyclerViewAdapter {
    private static final String TAG = f.a(ListingStateChangeViewHolderFactoryRecyclerViewAdapter.class);
    BroadcastReceiver mBroadcastReceiver;
    private boolean mCanRemoveItems;
    HashMap<String, Integer> mListingToPositionMap;
    @Deprecated
    private int mMaxSpanSize;
    private SpanSizeLookup mSpanSizeLookup;

    public ListingStateChangeViewHolderFactoryRecyclerViewAdapter(FragmentActivity fragmentActivity, @NonNull b bVar) {
        this(fragmentActivity, bVar, null);
    }

    public ListingStateChangeViewHolderFactoryRecyclerViewAdapter(FragmentActivity fragmentActivity, @NonNull b bVar, @Nullable com.etsy.android.vespa.f fVar) {
        super(fragmentActivity, bVar, fVar);
        this.mMaxSpanSize = 1;
        this.mCanRemoveItems = false;
        this.mListingToPositionMap = new HashMap<>();
        this.mBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(EtsyAction.STATE_CHANGE.getAction())) {
                    ListingStateChangeViewHolderFactoryRecyclerViewAdapter.this.listingStateChanged(intent.getExtras());
                }
            }
        };
        this.mSpanSizeLookup = new SpanSizeLookup() {
            public int getSpanSize(int i) {
                if (i >= ListingStateChangeViewHolderFactoryRecyclerViewAdapter.this.mItems.size()) {
                    return 1;
                }
                return ListingStateChangeViewHolderFactoryRecyclerViewAdapter.this.getViewHolderFactory().a(ListingStateChangeViewHolderFactoryRecyclerViewAdapter.this.getItemViewType(i), i);
            }
        };
        this.mSpanSizeLookup.setSpanIndexCacheEnabled(true);
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        if (viewHolder instanceof BaseViewHolder) {
            ((BaseViewHolder) viewHolder).recycle();
        }
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }

    public void setCanRemoveItems(boolean z) {
        this.mCanRemoveItems = z;
    }

    public void removeItem(int i) {
        super.removeItem(i);
        this.mListingToPositionMap.clear();
    }

    public void listingStateChanged(Bundle bundle) {
        String string = bundle.getString("id");
        if (string != null) {
            if (this.mListingToPositionMap.size() == 0) {
                for (int i = 0; i < this.mItems.size(); i++) {
                    if (((k) this.mItems.get(i)) instanceof ListingLike) {
                        ListingLike listingLike = (ListingLike) this.mItems.get(i);
                        if (listingLike.getListingId().getId().equals(string)) {
                            updateListingState(listingLike, bundle);
                            notifyItemChanged(i);
                        }
                        this.mListingToPositionMap.put(listingLike.getListingId().toString(), Integer.valueOf(i));
                    }
                }
            } else if (this.mListingToPositionMap.containsKey(string)) {
                int intValue = ((Integer) this.mListingToPositionMap.get(string)).intValue();
                updateListingState((ListingLike) this.mItems.get(intValue), bundle);
                notifyItemChanged(intValue);
            }
        }
    }

    private void updateListingState(ListingLike listingLike, Bundle bundle) {
        if (bundle.containsKey(EtsyAction.STATE_FAVORITE)) {
            listingLike.setIsFavorite(bundle.getBoolean(EtsyAction.STATE_FAVORITE));
        }
        if (bundle.containsKey(EtsyAction.STATE_COLLECTIONS)) {
            listingLike.setHasCollections(bundle.getBoolean(EtsyAction.STATE_COLLECTIONS));
        }
    }

    public BroadcastReceiver getStateBroadcastReceiver() {
        return this.mBroadcastReceiver;
    }

    public boolean canRemoveItems() {
        return this.mCanRemoveItems;
    }

    public void onRemoveItem(int i) {
        removeItem(i);
    }

    public void onItemChanged(int i) {
        notifyItemChanged(i);
    }

    public void addFooter(int i) {
        throw new RuntimeException("The ListingStateChangeViewHolderFactoryRecyclerViewAdapter does not support footers. Put the items in the list itself and define them as a mapping in the factory.");
    }

    public void addHeader(int i) {
        throw new RuntimeException("The ListingStateChangeViewHolderFactoryRecyclerViewAdapter does not support headers. Put the items in the list itself and define them as a mapping in the factory.");
    }

    public void clear() {
        super.clear();
        this.mListingToPositionMap.clear();
    }

    public void clearData() {
        super.clearData();
        this.mListingToPositionMap.clear();
    }
}
