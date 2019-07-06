package com.contextlogic.wish.dialog.addtocartoffer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.api.model.WishAddToCartOfferApplied;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.timer.CountdownTimerView;
import com.contextlogic.wish.util.DisplayUtil;

public class AddToCartOfferDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    private CountdownTimerView mCountdownTimer;

    public boolean isCancelable() {
        return false;
    }

    public static AddToCartOfferDialogFragment createAddToCartOfferDialog(WishAddToCartOfferApplied wishAddToCartOfferApplied) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("ArgumentOfferApplied", wishAddToCartOfferApplied);
        AddToCartOfferDialogFragment addToCartOfferDialogFragment = new AddToCartOfferDialogFragment();
        addToCartOfferDialogFragment.setArguments(bundle);
        return addToCartOfferDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        WishAddToCartOfferApplied wishAddToCartOfferApplied = (WishAddToCartOfferApplied) getArguments().getParcelable("ArgumentOfferApplied");
        if (wishAddToCartOfferApplied == null) {
            return null;
        }
        View inflate = layoutInflater.inflate(R.layout.add_to_cart_offer_dialog_fragment, viewGroup, false);
        ((NetworkImageView) inflate.findViewById(R.id.add_to_cart_offer_dialog_image)).setImage(wishAddToCartOfferApplied.getProductImage());
        ((ThemedTextView) inflate.findViewById(R.id.add_to_cart_offer_dialog_title)).setText(wishAddToCartOfferApplied.getTitle());
        ((ThemedTextView) inflate.findViewById(R.id.add_to_cart_offer_dialog_message)).setText(wishAddToCartOfferApplied.getMessage());
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.add_to_cart_offer_dialog_timer_container);
        if (wishAddToCartOfferApplied.getExpiry() != null) {
            linearLayout.setVisibility(0);
            this.mCountdownTimer = new CountdownTimerView(getContext());
            this.mCountdownTimer.setup(wishAddToCartOfferApplied.getExpiry(), getResources().getDimensionPixelSize(R.dimen.cart_timer_height), getResources().getColor(R.color.main_primary), getResources().getColor(R.color.white));
            this.mCountdownTimer.startTimer();
            linearLayout.removeAllViews();
            linearLayout.addView(this.mCountdownTimer);
        } else {
            linearLayout.setVisibility(8);
        }
        ((ThemedTextView) inflate.findViewById(R.id.add_to_cart_offer_dialog_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AddToCartOfferDialogFragment.this.cancel();
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
