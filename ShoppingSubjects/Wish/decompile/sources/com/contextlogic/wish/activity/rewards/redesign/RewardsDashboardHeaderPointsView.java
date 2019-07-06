package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishRewardsDashboardInfo;
import com.contextlogic.wish.api.model.WishRewardsDashboardInfo.BadgeType;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class RewardsDashboardHeaderPointsView extends LinearLayout {
    private AutoReleasableImageView mRewardsBadge;
    private ThemedTextView mSignupText;
    private ThemedTextView mTotalPointsText;

    public RewardsDashboardHeaderPointsView(Context context) {
        this(context, null);
    }

    public RewardsDashboardHeaderPointsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RewardsDashboardHeaderPointsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.rewards_dashboard_header_points_view, this, true);
        setOrientation(1);
        setGravity(1);
        this.mRewardsBadge = (AutoReleasableImageView) findViewById(R.id.rewards_dashboard_header_view_badge);
        this.mSignupText = (ThemedTextView) findViewById(R.id.rewards_dashboard_header_view_join_date);
        this.mTotalPointsText = (ThemedTextView) findViewById(R.id.rewards_dashboard_header_view_total_points);
    }

    public void setup(WishRewardsDashboardInfo wishRewardsDashboardInfo) {
        this.mRewardsBadge.setImageResource(BadgeType.getBadgeResource(BadgeType.BLUE));
        this.mTotalPointsText.setText(wishRewardsDashboardInfo.getTotalPointsText());
        if (wishRewardsDashboardInfo.getSignupText() != null) {
            this.mSignupText.setText(wishRewardsDashboardInfo.getSignupText());
        }
    }
}
