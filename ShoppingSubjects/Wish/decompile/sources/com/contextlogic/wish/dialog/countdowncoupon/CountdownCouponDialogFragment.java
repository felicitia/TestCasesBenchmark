package com.contextlogic.wish.dialog.countdowncoupon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.api.model.LoggedOutCountdownCoupon;
import com.contextlogic.wish.dialog.BaseCouponDialogFragment;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.DateUtil;

public class CountdownCouponDialogFragment<A extends BaseActivity> extends BaseCouponDialogFragment<A> {
    private ThemedTextView mDiscountText;

    public void logCopiedToClipboardEvent() {
    }

    public void logStartShoppingButtonOnClickEvent() {
    }

    public static CountdownCouponDialogFragment<BaseActivity> createCountdownCouponDialogFragment(LoggedOutCountdownCoupon loggedOutCountdownCoupon) {
        if (loggedOutCountdownCoupon == null) {
            return null;
        }
        CountdownCouponDialogFragment<BaseActivity> countdownCouponDialogFragment = new CountdownCouponDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ArgumentCouponSpec", loggedOutCountdownCoupon);
        countdownCouponDialogFragment.setArguments(bundle);
        return countdownCouponDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View contentView = super.getContentView(layoutInflater, viewGroup, bundle);
        if (contentView != null) {
            this.mDiscountText = (ThemedTextView) contentView.findViewById(R.id.coupon_dialog_discount_text);
        }
        LoggedOutCountdownCoupon loggedOutCountdownCoupon = (LoggedOutCountdownCoupon) getArguments().getParcelable("ArgumentCouponSpec");
        if (loggedOutCountdownCoupon == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(loggedOutCountdownCoupon.getDiscountPercent());
        sb.append(getString(R.string.percent_off));
        setup(sb.toString(), getString(R.string.expires_time, DateUtil.getLocalizedDate(loggedOutCountdownCoupon.getExpiry())), getString(R.string.countdown_coupon_title, Integer.toString(loggedOutCountdownCoupon.getDiscountPercent())), loggedOutCountdownCoupon.getCouponId(), getString(R.string.countdown_coupon_subtitle), null, loggedOutCountdownCoupon.getFormattedMaxDiscount());
        return contentView;
    }

    public void setupTicketImage() {
        setTicketImageDrawable(getResources().getDrawable(R.drawable.countdown_coupon_ticket));
        setTicketImageShadow(getResources().getDrawable(R.drawable.countdown_coupon_shadow));
    }
}
