package com.contextlogic.wish.activity.rewards.redesign;

import android.content.Context;
import android.content.res.Resources;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishRewardsReturnPolicyInformation;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.Iterator;

public class RewardsHelpInformationView extends LinearLayout {
    public RewardsHelpInformationView(Context context) {
        super(context);
    }

    public void setup(WishRewardsReturnPolicyInformation wishRewardsReturnPolicyInformation) {
        setOrientation(1);
        Resources resources = WishApplication.getInstance().getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.sixteen_padding);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.twenty_four_padding);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.eight_padding);
        if (wishRewardsReturnPolicyInformation.getTitle() != null) {
            ThemedTextView themedTextView = new ThemedTextView(getContext());
            themedTextView.setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, 0);
            themedTextView.setText(wishRewardsReturnPolicyInformation.getTitle());
            themedTextView.setTextSize(0, (float) resources.getDimensionPixelSize(R.dimen.text_size_subtitle));
            themedTextView.setTypeface(1);
            themedTextView.setTextColor(resources.getColor(R.color.text_primary));
            addView(themedTextView);
        }
        if (wishRewardsReturnPolicyInformation.getParagraphs() != null) {
            Iterator it = wishRewardsReturnPolicyInformation.getParagraphs().iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                ThemedTextView themedTextView2 = new ThemedTextView(getContext());
                themedTextView2.setPadding(dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize, 0);
                themedTextView2.setText(str);
                themedTextView2.setTextColor(resources.getColor(R.color.text_primary));
                themedTextView2.setTextSize(0, (float) resources.getDimensionPixelSize(R.dimen.text_size_body));
                addView(themedTextView2);
            }
        }
    }
}
