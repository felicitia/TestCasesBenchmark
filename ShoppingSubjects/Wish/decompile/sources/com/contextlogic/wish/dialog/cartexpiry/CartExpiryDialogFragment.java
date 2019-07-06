package com.contextlogic.wish.dialog.cartexpiry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.timer.CountdownTimerView;
import com.contextlogic.wish.util.DisplayUtil;

public class CartExpiryDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    private CountdownTimerView mCountdownTimer;

    public boolean isCancelable() {
        return false;
    }

    public static CartExpiryDialogFragment<BaseActivity> createCartExpiryDialog(CartExpiryItem cartExpiryItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("ArgumentCartItem", cartExpiryItem);
        CartExpiryDialogFragment<BaseActivity> cartExpiryDialogFragment = new CartExpiryDialogFragment<>();
        cartExpiryDialogFragment.setArguments(bundle);
        return cartExpiryDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CartExpiryItem cartExpiryItem = (CartExpiryItem) getArguments().getParcelable("ArgumentCartItem");
        if (cartExpiryItem == null) {
            return null;
        }
        View inflate = layoutInflater.inflate(R.layout.cart_expiry_dialog_fragment, viewGroup, false);
        ((NetworkImageView) inflate.findViewById(R.id.cart_expiry_dialog_image)).setImage(cartExpiryItem.getImage());
        ((ThemedTextView) inflate.findViewById(R.id.cart_expiry_dialog_title)).setText(cartExpiryItem.getPriceExpiryInfo().getTitle());
        ((ThemedTextView) inflate.findViewById(R.id.cart_expiry_dialog_message)).setText(cartExpiryItem.getPriceExpiryInfo().getMessage());
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.cart_expiry_dialog_timer_container);
        if (cartExpiryItem.getPriceExpiryInfo().getExpiry() != null) {
            linearLayout.setVisibility(0);
            this.mCountdownTimer = new CountdownTimerView(getContext());
            this.mCountdownTimer.setup(cartExpiryItem.getPriceExpiryInfo().getExpiry(), getResources().getDimensionPixelSize(R.dimen.cart_expiry_dialog_fragment_timer_height), getResources().getColor(R.color.main_primary), getResources().getColor(R.color.white));
            this.mCountdownTimer.startTimer();
            linearLayout.removeAllViews();
            linearLayout.addView(this.mCountdownTimer);
        } else {
            linearLayout.setVisibility(8);
        }
        ThemedTextView themedTextView = (ThemedTextView) inflate.findViewById(R.id.cart_expiry_dialog_ok);
        themedTextView.setText(cartExpiryItem.getPriceExpiryInfo().getButtonText());
        themedTextView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CartExpiryDialogFragment.this.cancel();
            }
        });
        return inflate;
    }

    public void onDetach() {
        super.onDetach();
        if (this.mCountdownTimer != null) {
            this.mCountdownTimer.pauseTimer();
        }
    }

    public int getDialogWidth() {
        return (int) (getResources().getFraction(R.fraction.dialog_min_width_major, 1, 1) * ((float) DisplayUtil.getDisplayWidth()));
    }
}
