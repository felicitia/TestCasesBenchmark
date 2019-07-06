package com.contextlogic.wish.activity.pricechop;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.productdetails.ProductDetailsFragment;
import com.contextlogic.wish.api.model.PriceChopProductDetail;
import com.contextlogic.wish.ui.timer.TimerTextView;
import com.contextlogic.wish.ui.timer.TimerTextView.TimerWatcherAdapter;

public class PriceChopDetailCounterView extends LinearLayout {
    private TimerTextView mTimerTextView;

    public PriceChopDetailCounterView(Context context) {
        super(context);
        init();
    }

    public PriceChopDetailCounterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PriceChopDetailCounterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.price_chop_detail_counter, this, true);
        this.mTimerTextView = (TimerTextView) findViewById(R.id.price_chop_count_down_timer);
        setOrientation(1);
    }

    public void setup(final ProductDetailsFragment productDetailsFragment, PriceChopProductDetail priceChopProductDetail) {
        if (priceChopProductDetail == null || !priceChopProductDetail.isRunning() || priceChopProductDetail.getExpireDate() == null) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        this.mTimerTextView.setup(priceChopProductDetail.getExpireDate(), new TimerWatcherAdapter() {
            public void onCountdownComplete() {
                productDetailsFragment.getLoadingPageView().reload();
            }
        });
    }
}
