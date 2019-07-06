package com.contextlogic.wish.activity.feed.dailygiveaway;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayFeedView.DailyGiveawayState;
import com.contextlogic.wish.activity.feed.dailygiveaway.DailyGiveawayFeedView.DailyGiveawaySubtab;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.image.NetworkImageView;

public class DailyGiveawayStatusBox extends LinearLayout implements ImageRestorable {
    private NetworkImageView mClaimedItemImage;
    private AutoReleasableImageView mClockIcon;
    private TextView mSubtitleTextView;
    private TextView mTitleTextView;

    public DailyGiveawayStatusBox(Context context) {
        this(context, null);
    }

    public DailyGiveawayStatusBox(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.daily_giveaway_status_box, this);
        this.mTitleTextView = (TextView) inflate.findViewById(R.id.daily_giveaway_status_box_title);
        this.mSubtitleTextView = (TextView) inflate.findViewById(R.id.daily_giveaway_status_box_subtitle);
        this.mClockIcon = (AutoReleasableImageView) inflate.findViewById(R.id.daily_giveaway_clock_icon);
        this.mClaimedItemImage = (NetworkImageView) inflate.findViewById(R.id.daily_giveaway_status_box_claimed_image);
    }

    public void setState(DailyGiveawayState dailyGiveawayState, DailyGiveawaySubtab dailyGiveawaySubtab, String str, String str2) {
        hideAll();
        if (dailyGiveawaySubtab != DailyGiveawaySubtab.CURRENT || dailyGiveawayState == DailyGiveawayState.ONGOING) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        if (dailyGiveawayState == DailyGiveawayState.NOT_STARTED) {
            this.mClockIcon.setVisibility(0);
        } else if (dailyGiveawayState == DailyGiveawayState.STARTING_SOON) {
            this.mClockIcon.setVisibility(0);
        } else if (dailyGiveawayState != DailyGiveawayState.ENDED && dailyGiveawayState == DailyGiveawayState.ALREADY_CLAIMED) {
            this.mClaimedItemImage.setVisibility(0);
        }
        this.mTitleTextView.setText(str);
        this.mSubtitleTextView.setText(str2);
    }

    private void hideAll() {
        this.mClockIcon.setVisibility(8);
        this.mClaimedItemImage.setVisibility(8);
    }

    public void setClaimedProduct(WishProduct wishProduct) {
        this.mClaimedItemImage.setImage(wishProduct.getImage());
    }

    public void releaseImages() {
        this.mClockIcon.releaseImages();
        this.mClaimedItemImage.releaseImages();
    }

    public void restoreImages() {
        this.mClockIcon.restoreImages();
        this.mClaimedItemImage.restoreImages();
    }
}
