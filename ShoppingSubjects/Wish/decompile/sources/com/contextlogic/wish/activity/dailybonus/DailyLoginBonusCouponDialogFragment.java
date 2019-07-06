package com.contextlogic.wish.activity.dailybonus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCouponDialogSpec;
import com.contextlogic.wish.api.model.WishDailyLoginStampSpec;
import com.contextlogic.wish.dialog.BaseCouponDialogFragment;

public class DailyLoginBonusCouponDialogFragment<A extends BaseActivity> extends BaseCouponDialogFragment<A> {
    public static DailyLoginBonusCouponDialogFragment<BaseActivity> createDailyLoginBonusCouponDialogFragment(WishDailyLoginStampSpec wishDailyLoginStampSpec) {
        DailyLoginBonusCouponDialogFragment<BaseActivity> dailyLoginBonusCouponDialogFragment = new DailyLoginBonusCouponDialogFragment<>();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ArgumentStampSpec", wishDailyLoginStampSpec);
        dailyLoginBonusCouponDialogFragment.setArguments(bundle);
        return dailyLoginBonusCouponDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View contentView = super.getContentView(layoutInflater, viewGroup, bundle);
        WishDailyLoginStampSpec wishDailyLoginStampSpec = (WishDailyLoginStampSpec) getArguments().getParcelable("ArgumentStampSpec");
        if (wishDailyLoginStampSpec == null) {
            return null;
        }
        WishCouponDialogSpec couponDialogSpec = wishDailyLoginStampSpec.getCouponDialogSpec();
        setup(couponDialogSpec.getCouponAmount(), couponDialogSpec.getCouponExpiryDate(), couponDialogSpec.getCouponTitleText(), couponDialogSpec.getCouponCode(), couponDialogSpec.getCouponDesc1(), couponDialogSpec.getCouponDesc2(), couponDialogSpec.getFormattedLocalizedMaxDiscount());
        return contentView;
    }

    public void setupTicketImage() {
        setTicketImageDrawable(getResources().getDrawable(R.drawable.daily_login_bonus_coupon));
        setTicketImageShadow(getResources().getDrawable(R.drawable.daily_login_bonus_coupon_shadow));
    }

    public void logCopiedToClipboardEvent() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_DAILY_LOGIN_BONUS_COPY_COUPON_CODE);
    }

    public void logStartShoppingButtonOnClickEvent() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_DAILY_LOGIN_BONUS_START_SHOPPING);
    }
}
