package com.contextlogic.wish.activity.pricewatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishPriceWatchSpec;

public class PriceWatchHeaderView extends FrameLayout {
    private TextView mDescriptionText;

    public PriceWatchHeaderView(Context context) {
        this(context, null);
    }

    public PriceWatchHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.mDescriptionText = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.price_watch_header_view, this).findViewById(R.id.price_watch_description_text);
    }

    public void setup(WishPriceWatchSpec wishPriceWatchSpec) {
        this.mDescriptionText.setText(wishPriceWatchSpec.getDescription());
    }
}
