package com.contextlogic.wish.dialog.freegift;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.dialog.BaseDialogFragment;

public class OrderConfirmedDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    public boolean isCancelable() {
        return false;
    }

    public static OrderConfirmedDialogFragment getDialog(String str) {
        OrderConfirmedDialogFragment orderConfirmedDialogFragment = new OrderConfirmedDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ArgumentShippingInfo", str);
        orderConfirmedDialogFragment.setArguments(bundle);
        return orderConfirmedDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String string = getArguments().getString("ArgumentShippingInfo");
        View inflate = layoutInflater.inflate(R.layout.order_confirmed_dialog_fragment, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.order_confirmed_dialog_fragment_shipping_info_content);
        TextView textView2 = (TextView) inflate.findViewById(R.id.order_confirmed_dialog_fragment_ok_button);
        ((TextView) inflate.findViewById(R.id.order_confirmed_dialog_fragment_email)).setText(ProfileDataCenter.getInstance().getEmail());
        textView.setText(string);
        textView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ORDER_CONFIRMED_DIALOG_OK);
                OrderConfirmedDialogFragment.this.cancel();
            }
        });
        return inflate;
    }

    public int getMaxDialogWidth() {
        return getResources().getDimensionPixelSize(R.dimen.order_confirmed_dialog_max_width);
    }
}
