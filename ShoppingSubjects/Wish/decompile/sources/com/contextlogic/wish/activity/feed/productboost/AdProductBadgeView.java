package com.contextlogic.wish.activity.feed.productboost;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class AdProductBadgeView extends LinearLayout {
    public AdProductBadgeView(Context context) {
        this(context, null);
    }

    public AdProductBadgeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AdProductBadgeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        setBackgroundResource(R.drawable.ad_product_badge_bg);
        LayoutParams layoutParams = new LayoutParams(-2, context.getResources().getDimensionPixelSize(R.dimen.sixteen_padding));
        layoutParams.setMargins(context.getResources().getDimensionPixelSize(R.dimen.eight_padding), 0, 0, 0);
        setLayoutParams(layoutParams);
        ThemedTextView themedTextView = new ThemedTextView(context, attributeSet);
        themedTextView.setTextSize(0, (float) context.getResources().getDimensionPixelSize(R.dimen.text_size_small));
        themedTextView.setText(context.getString(R.string.ad).toLowerCase());
        themedTextView.setTextColor(ContextCompat.getColor(context, R.color.cool_gray3));
        themedTextView.setPadding(context.getResources().getDimensionPixelSize(R.dimen.four_padding), 0, context.getResources().getDimensionPixelSize(R.dimen.four_padding), 0);
        themedTextView.setGravity(17);
        addView(themedTextView);
    }
}
