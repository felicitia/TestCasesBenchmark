package com.etsy.android.ui.cart.viewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.cart.Promotion;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.ui.cart.a.a;
import com.etsy.android.ui.cart.view.PromotionView;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class PromotionViewHolder extends BaseCartGroupItemViewHolder {
    /* access modifiers changed from: private */
    public final a mClickHandler;
    private PromotionView mPromotionView = ((PromotionView) this.itemView);

    public PromotionViewHolder(ViewGroup viewGroup, a aVar) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_msco_promotion, viewGroup, false));
        this.mClickHandler = aVar;
    }

    public void bindCartGroupItem(final CartGroupItem cartGroupItem) {
        this.mPromotionView.setShowPrice(true);
        this.mPromotionView.bind((Promotion) cartGroupItem.getData(), cartGroupItem.getAction(ServerDrivenAction.TYPE_DELETE_COUPON) != null ? new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (PromotionViewHolder.this.mClickHandler != null) {
                    ServerDrivenAction action = cartGroupItem.getAction(ServerDrivenAction.TYPE_DELETE_COUPON);
                    if (action != null) {
                        PromotionViewHolder.this.mClickHandler.c(PromotionViewHolder.this.getRootView(), action);
                    }
                }
            }
        } : null);
    }
}
