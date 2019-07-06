package com.etsy.android.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.ui.cart.b;

public class CartRefreshDelegate extends BroadcastReceiver {
    public static final String ARG_COUNT_CART = "count_cart";
    public static final String ARG_COUNT_SAVED = "count_saved";
    public static final String ARG_ORIGIN = "origin";
    public static final String ARG_USER_ACTION = "user_action";
    public static final int ORIGIN_CART = 1;
    public static final int ORIGIN_SAVED = 2;
    private Context mContext;
    private final a mListener;

    public interface a {
        void onCartCountsUpdated(int i, int i2, boolean z, int i3);
    }

    public CartRefreshDelegate(Context context, a aVar) {
        this.mContext = context;
        this.mListener = aVar;
    }

    public static void sendBroadcast(Context context, int i, int i2, boolean z, int i3) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context);
        Intent intent = new Intent();
        intent.setAction(EtsyAction.CART_COUNTS_UPDATED.getAction());
        intent.putExtra(ARG_COUNT_CART, i);
        intent.putExtra(ARG_COUNT_SAVED, i2);
        intent.putExtra(ARG_USER_ACTION, z);
        intent.putExtra(ARG_ORIGIN, i3);
        instance.sendBroadcast(intent);
    }

    public void onResume() {
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this, new IntentFilter(EtsyAction.CART_COUNTS_UPDATED.getAction()));
    }

    public void onPause() {
        LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this);
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(EtsyAction.CART_COUNTS_UPDATED.getAction()) && this.mListener != null) {
            int intExtra = intent.getIntExtra(ARG_COUNT_CART, -1);
            b.a(context, intExtra);
            this.mListener.onCartCountsUpdated(intExtra, intent.getIntExtra(ARG_COUNT_SAVED, -1), intent.getBooleanExtra(ARG_USER_ACTION, false), intent.getIntExtra(ARG_ORIGIN, -1));
        }
    }
}
