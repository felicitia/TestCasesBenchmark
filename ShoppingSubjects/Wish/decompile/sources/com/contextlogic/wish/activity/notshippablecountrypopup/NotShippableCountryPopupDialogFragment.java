package com.contextlogic.wish.activity.notshippablecountrypopup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishNotShippableCountryPopupSpec;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.util.LocaleUtil;

public class NotShippableCountryPopupDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    private TextView mBrowseButton;
    private TextView mDescription;
    private AutoReleasableImageView mFlag;
    private WishNotShippableCountryPopupSpec mNotShippableCountryPopupSpec;
    private TextView mTitle;

    public static NotShippableCountryPopupDialogFragment<BaseActivity> createNotShippableCountryPopupDialogFragment(WishNotShippableCountryPopupSpec wishNotShippableCountryPopupSpec) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("ArgumentNotShippablePopup", wishNotShippableCountryPopupSpec);
        NotShippableCountryPopupDialogFragment<BaseActivity> notShippableCountryPopupDialogFragment = new NotShippableCountryPopupDialogFragment<>();
        notShippableCountryPopupDialogFragment.setArguments(bundle);
        return notShippableCountryPopupDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mNotShippableCountryPopupSpec = (WishNotShippableCountryPopupSpec) getArguments().getParcelable("ArgumentNotShippablePopup");
        View inflate = layoutInflater.inflate(R.layout.not_shippable_country_popup_dialog_fragment, viewGroup);
        this.mTitle = (TextView) inflate.findViewById(R.id.not_shippable_popup_title);
        this.mDescription = (TextView) inflate.findViewById(R.id.not_shippable_popup_description);
        this.mBrowseButton = (TextView) inflate.findViewById(R.id.not_shippable_popup_button);
        this.mFlag = (AutoReleasableImageView) inflate.findViewById(R.id.not_shippable_popup_flag);
        setUpHeaders();
        setUpFlag();
        this.mBrowseButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_NOT_SHIPPABLE_COUNTRY_BROWSE_BUTTON);
                NotShippableCountryPopupDialogFragment.super.cancel();
            }
        });
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_NOT_SHIPPABLE_COUNTRY_MODAL);
        return inflate;
    }

    private void setUpHeaders() {
        this.mTitle.setText(this.mNotShippableCountryPopupSpec.getTitle());
        this.mDescription.setText(this.mNotShippableCountryPopupSpec.getDescription());
        this.mBrowseButton.setText(this.mNotShippableCountryPopupSpec.getConfirmButtonText());
    }

    private void setUpFlag() {
        this.mFlag.setImageResource(LocaleUtil.getResIdFromCountryCode(ProfileDataCenter.getInstance().getCountryCode()));
    }

    public void cancel() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_NOT_SHIPPABLE_COUNTRY_DISMISS_MODAL);
        super.cancel();
    }
}
