package com.etsy.android.ui.cart.viewholders;

import android.view.View;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.uikit.util.TrackingOnClickListener;

/* compiled from: MessageBannerViewHolder.kt */
public final class MessageBannerViewHolder$bind$$inlined$apply$lambda$1 extends TrackingOnClickListener {
    final /* synthetic */ CartGroupItem $item$inlined;
    final /* synthetic */ MessageBannerViewHolder this$0;

    MessageBannerViewHolder$bind$$inlined$apply$lambda$1(MessageBannerViewHolder messageBannerViewHolder, CartGroupItem cartGroupItem) {
        this.this$0 = messageBannerViewHolder;
        this.$item$inlined = cartGroupItem;
    }

    public void onViewClick(View view) {
        ServerDrivenAction action = this.$item$inlined.getAction(ServerDrivenAction.TYPE_DISMISS);
        if (action != null) {
            this.this$0.clickHandler.c(this.this$0.getRootView(), action);
        }
    }
}
