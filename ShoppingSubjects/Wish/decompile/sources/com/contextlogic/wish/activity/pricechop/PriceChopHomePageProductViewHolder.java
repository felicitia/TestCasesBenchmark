package com.contextlogic.wish.activity.pricechop;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.timer.TimerTextView;

public class PriceChopHomePageProductViewHolder extends ViewHolder {
    public final TimerTextView mCountDownTimer;
    public final TextView mDiscountText;
    public final NetworkImageView mImageView;
    public final TextView mPriceText;

    public PriceChopHomePageProductViewHolder(View view) {
        super(view);
        this.mImageView = (NetworkImageView) view.findViewById(R.id.price_chop_home_cell_image);
        this.mCountDownTimer = (TimerTextView) view.findViewById(R.id.price_chop_home_cell_timer);
        this.mPriceText = (TextView) view.findViewById(R.id.price_chop_home_cell_price_text);
        this.mDiscountText = (TextView) view.findViewById(R.id.price_chop_home_cell_discount_text);
        this.mDiscountText.setPaintFlags(this.mDiscountText.getPaintFlags() | 16);
    }
}
