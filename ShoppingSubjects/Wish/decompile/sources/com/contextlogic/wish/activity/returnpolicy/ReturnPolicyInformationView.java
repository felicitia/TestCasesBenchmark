package com.contextlogic.wish.activity.returnpolicy;

import android.content.Context;
import android.content.res.Resources;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishRewardsReturnPolicyInformation;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.Iterator;

public class ReturnPolicyInformationView extends LinearLayout {
    public ReturnPolicyInformationView(Context context) {
        super(context);
    }

    public void setup(WishRewardsReturnPolicyInformation wishRewardsReturnPolicyInformation) {
        setOrientation(1);
        Resources resources = WishApplication.getInstance().getResources();
        if (wishRewardsReturnPolicyInformation.getTitle() != null && !wishRewardsReturnPolicyInformation.getTitle().isEmpty()) {
            ThemedTextView themedTextView = new ThemedTextView(getContext());
            themedTextView.setText(wishRewardsReturnPolicyInformation.getTitle());
            themedTextView.setTextSize(0, (float) resources.getDimensionPixelSize(R.dimen.text_size_body));
            themedTextView.setTypeface(1);
            themedTextView.setTextColor(resources.getColor(R.color.text_primary));
            themedTextView.setLineSpacing(0.0f, 1.2f);
            themedTextView.setPadding(0, 0, 0, resources.getDimensionPixelSize(R.dimen.eight_padding));
            addView(themedTextView);
        }
        if (wishRewardsReturnPolicyInformation.getParagraphs() != null) {
            Iterator it = wishRewardsReturnPolicyInformation.getParagraphs().iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                ThemedTextView themedTextView2 = new ThemedTextView(getContext());
                themedTextView2.setText(str);
                themedTextView2.setTextColor(resources.getColor(R.color.text_primary));
                themedTextView2.setTextSize(0, (float) resources.getDimensionPixelSize(R.dimen.text_size_body));
                themedTextView2.setLineSpacing(0.0f, 1.2f);
                addView(themedTextView2);
            }
        }
    }
}
