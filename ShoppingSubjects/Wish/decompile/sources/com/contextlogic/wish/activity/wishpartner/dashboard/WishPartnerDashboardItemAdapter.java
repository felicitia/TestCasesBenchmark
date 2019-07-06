package com.contextlogic.wish.activity.wishpartner.dashboard;

import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishPartnerSummary.WishPartnerEvent;
import com.contextlogic.wish.ui.recyclerview.adapter.LoadingFooterAdapter;

public class WishPartnerDashboardItemAdapter extends LoadingFooterAdapter<WishPartnerEvent, WishPartnerDashboardItemViewHolder> {
    public int getMyItemViewType(int i) {
        return R.layout.wish_partner_event_item_view;
    }

    public WishPartnerDashboardItemViewHolder onCreateMyViewHolder(ViewGroup viewGroup, int i) {
        return new WishPartnerDashboardItemViewHolder(new WishPartnerEventItemView(viewGroup.getContext()));
    }

    public void onBindMyViewHolder(WishPartnerDashboardItemViewHolder wishPartnerDashboardItemViewHolder, int i) {
        wishPartnerDashboardItemViewHolder.itemView.setup((WishPartnerEvent) getValueAt(i));
    }

    public void onMyViewRecycled(WishPartnerDashboardItemViewHolder wishPartnerDashboardItemViewHolder, int i) {
        WishPartnerEvent wishPartnerEvent = (WishPartnerEvent) getValueAt(i);
    }
}
