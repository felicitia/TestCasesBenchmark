package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class RewardAddedPopupView extends LinearLayout {
    public RewardAddedPopupView(Context context, String str, String str2) {
        super(context);
        int dimensionPixelSize = WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.thirty_two_padding);
        setOrientation(1);
        setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.white));
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.reward_added_popup_view, this);
        ThemedTextView themedTextView = (ThemedTextView) findViewById(R.id.reward_added_popup_title);
        if (str != null) {
            themedTextView.setText(str);
        } else {
            themedTextView.setVisibility(8);
        }
        ThemedTextView themedTextView2 = (ThemedTextView) findViewById(R.id.reward_added_popup_description);
        if (str != null) {
            themedTextView2.setText(str2);
        } else {
            themedTextView2.setVisibility(8);
        }
    }
}
