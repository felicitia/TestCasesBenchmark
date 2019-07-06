package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class RewardsRedeemHeaderView extends LinearLayout {
    private ThemedTextView mDescriptionView;
    private ThemedTextView mTitleView;

    public RewardsRedeemHeaderView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setOrientation(1);
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.rewards_redeem_header_view, this);
        this.mTitleView = (ThemedTextView) inflate.findViewById(R.id.rewards_redeem_header_title);
        this.mDescriptionView = (ThemedTextView) inflate.findViewById(R.id.rewards_redeem_header_description);
    }

    public void setup(String str, String str2, RewardsFragment rewardsFragment) {
        this.mTitleView.setText(str);
        this.mDescriptionView.setText(str2);
        int dimension = (int) WishApplication.getInstance().getResources().getDimension(R.dimen.sixteen_padding);
        this.mTitleView.setPadding(dimension, 0, dimension, 0);
        this.mDescriptionView.setPadding(dimension, 0, dimension, 0);
        setPadding(0, rewardsFragment.getTabAreaSize(), 0, 0);
        setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.white));
    }
}
