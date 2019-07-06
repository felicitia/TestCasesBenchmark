package com.contextlogic.wish.ui.timer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.timer.TimerTextView.TimerWatcherAdapter;
import java.util.Date;

public class CartOfferTimerView extends LinearLayout {
    private ThemedTextView mDescriptionTextView;
    private TimerTextView mTimerTextView;

    public CartOfferTimerView(Context context) {
        this(context, null);
    }

    public CartOfferTimerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.cart_fragment_cart_item_redesign_timer_view, this);
        this.mTimerTextView = (TimerTextView) inflate.findViewById(R.id.cart_item_redesign_timer);
        this.mDescriptionTextView = (ThemedTextView) inflate.findViewById(R.id.cart_item_timer_message);
    }

    public void setDescription(CharSequence charSequence) {
        this.mDescriptionTextView.setText(charSequence);
    }

    public void setupTimer(Date date) {
        this.mTimerTextView.setup(date, new TimerWatcherAdapter() {
            public void onCountdownComplete() {
                CartOfferTimerView.this.onTimerExpired();
            }
        });
    }

    /* access modifiers changed from: private */
    public void onTimerExpired() {
        this.mTimerTextView.setText("0: 0 0");
        this.mTimerTextView.setTextColor(getResources().getColor(R.color.gray3));
        this.mTimerTextView.setBackground(getResources().getDrawable(R.drawable.cart_item_timer_expired_bg));
        this.mDescriptionTextView.setText(R.string.offer_expired);
    }
}
