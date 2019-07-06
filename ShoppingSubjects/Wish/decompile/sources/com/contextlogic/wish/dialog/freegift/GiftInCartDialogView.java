package com.contextlogic.wish.dialog.freegift;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.timer.CountdownTimerView;
import java.util.Date;

public class GiftInCartDialogView extends LinearLayout {
    private LinearLayout mCountdownContainer;
    private CountdownTimerView mCountdownTimer;

    public GiftInCartDialogView(Context context, WishProduct wishProduct) {
        super(context);
        setOrientation(1);
        setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        setLayoutParams(new LayoutParams(-1, -1));
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.free_gift_in_cart_dialog_view, this);
        initImage(wishProduct);
        initTimer();
    }

    private void initTimer() {
        if (this.mCountdownTimer != null) {
            this.mCountdownTimer.pauseTimer();
        }
        this.mCountdownContainer = (LinearLayout) findViewById(R.id.item_added_dialog_popup_countdown_container);
        this.mCountdownContainer.removeAllViews();
        this.mCountdownContainer.setVisibility(8);
        this.mCountdownTimer = new CountdownTimerView(getContext());
        this.mCountdownTimer.setGravity(17);
        this.mCountdownTimer.setDigitPadding(0).setColonPadding(0).setPadding(0);
        this.mCountdownTimer.setup(new Date(System.currentTimeMillis() + 3599800), getResources().getDimensionPixelSize(R.dimen.full_screen_cart_timer_height), getResources().getColor(R.color.cool_gray1), getResources().getColor(R.color.white), getResources().getColor(R.color.white));
        this.mCountdownTimer.startTimer();
        this.mCountdownContainer.addView(this.mCountdownTimer);
        this.mCountdownContainer.setVisibility(0);
    }

    private void initImage(WishProduct wishProduct) {
        ((NetworkImageView) findViewById(R.id.product_image)).setImage(wishProduct.getImage());
    }
}
