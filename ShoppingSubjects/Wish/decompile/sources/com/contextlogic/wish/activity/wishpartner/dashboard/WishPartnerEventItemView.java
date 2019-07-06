package com.contextlogic.wish.activity.wishpartner.dashboard;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishPartnerSummary.WishPartnerEvent;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.view.ProfileImageView;
import com.contextlogic.wish.util.DateUtil;

public class WishPartnerEventItemView extends RelativeLayout implements ImageRestorable {
    private NetworkImageView mMainImageView;
    private ThemedTextView mPriceText;
    private ThemedTextView mRowTitle;
    private ProfileImageView mSecondaryImageView;
    private ThemedTextView mSubtitle;
    private ThemedTextView mTimestamp;

    public WishPartnerEventItemView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.wish_partner_event_item_view, this, true);
        this.mMainImageView = (NetworkImageView) inflate.findViewById(R.id.wish_partner_event_main_image);
        this.mSecondaryImageView = (ProfileImageView) inflate.findViewById(R.id.wish_partner_event_secondary_image);
        this.mRowTitle = (ThemedTextView) inflate.findViewById(R.id.wish_partner_event_title);
        this.mTimestamp = (ThemedTextView) inflate.findViewById(R.id.wish_partner_event_timestamp);
        this.mSubtitle = (ThemedTextView) inflate.findViewById(R.id.wish_partner_event_subtitle);
        this.mPriceText = (ThemedTextView) inflate.findViewById(R.id.wish_partner_event_amount);
    }

    public void setup(WishPartnerEvent wishPartnerEvent) {
        this.mMainImageView.setImage(new WishImage(wishPartnerEvent.geMainImageUrl()));
        if (wishPartnerEvent.getUserName() != null) {
            this.mSecondaryImageView.setVisibility(0);
            this.mSecondaryImageView.setup(wishPartnerEvent.getSecondaryImageUrl() != null ? new WishImage(wishPartnerEvent.getSecondaryImageUrl()) : null, wishPartnerEvent.getUserName());
        } else {
            this.mSecondaryImageView.setVisibility(8);
        }
        this.mRowTitle.setText(wishPartnerEvent.getTitle());
        this.mTimestamp.setText(DateUtil.getFuzzyDateFromNow(wishPartnerEvent.getTimestamp()));
        if (!TextUtils.isEmpty(wishPartnerEvent.getSubtitle())) {
            this.mSubtitle.setVisibility(0);
            this.mSubtitle.setText(wishPartnerEvent.getSubtitle());
        } else {
            this.mSubtitle.setVisibility(8);
        }
        if (wishPartnerEvent.isCashOut()) {
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.moneybag_11x15);
            this.mPriceText.setTextColor(getResources().getColor(R.color.green));
            this.mPriceText.setText(wishPartnerEvent.getAmount().toAbsFormattedString());
            this.mPriceText.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        } else if (wishPartnerEvent.getAmount().getValue() >= 0.0d) {
            this.mPriceText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            this.mPriceText.setText(getResources().getString(R.string.positive_value, new Object[]{wishPartnerEvent.getAmount().toAbsFormattedString()}));
            this.mPriceText.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.green));
        } else {
            this.mPriceText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            this.mPriceText.setText(getResources().getString(R.string.negative_value, new Object[]{wishPartnerEvent.getAmount().toAbsFormattedString()}));
            this.mPriceText.setTextColor(getResources().getColor(R.color.red));
        }
    }

    public void releaseImages() {
        this.mMainImageView.releaseImages();
        this.mSecondaryImageView.releaseImages();
    }

    public void restoreImages() {
        this.mMainImageView.restoreImages();
        this.mSecondaryImageView.restoreImages();
    }
}
