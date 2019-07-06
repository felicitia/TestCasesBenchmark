package com.contextlogic.wish.activity.cart.commercecash;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;

public class CommerceCashPopupView extends LinearLayout {
    public CommerceCashPopupView(Context context, String str) {
        super(context);
        setOrientation(1);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        setLayoutParams(new LayoutParams(-1, -1));
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.commerce_cash_popup_view, this);
        ((TextView) findViewById(R.id.commerce_cash_popup_message)).setText(str);
    }
}
