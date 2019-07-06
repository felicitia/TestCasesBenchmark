package com.contextlogic.wish.dialog.popupanimation.itemadded;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;

public class ItemChangeToPriceWatchPopupView extends LinearLayout {
    private TextView mBody;
    private TextView mLink;

    public ItemChangeToPriceWatchPopupView(Context context, String str, String str2, boolean z) {
        super(context);
        setOrientation(1);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        setLayoutParams(new LayoutParams(-1, -1));
        LayoutInflater.from(getContext()).inflate(R.layout.item_change_to_price_watch_popup_view, this);
        initText(str, str2);
        if (z) {
            initLink();
        } else {
            addPadding();
        }
    }

    private void addPadding() {
        this.mBody = (TextView) findViewById(R.id.item_change_body);
        this.mBody.setPadding(0, 0, 0, WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.twenty_four_padding));
    }

    private void initText(String str, String str2) {
        ((TextView) findViewById(R.id.item_change_title)).setText(str);
        ((TextView) findViewById(R.id.item_change_body)).setText(str2);
    }

    private void initLink() {
        this.mLink = (TextView) findViewById(R.id.view_price_watch_link);
        this.mLink.setVisibility(0);
    }

    public TextView getPriceWatchLink() {
        return this.mLink;
    }
}
