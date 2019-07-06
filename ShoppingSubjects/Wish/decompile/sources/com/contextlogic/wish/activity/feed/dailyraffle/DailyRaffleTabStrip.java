package com.contextlogic.wish.activity.feed.dailyraffle;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;

public class DailyRaffleTabStrip extends TabLayout {

    interface DailyRaffleTabSelectedCallback {
        void onTabSelected(int i);
    }

    public DailyRaffleTabStrip(Context context) {
        this(context, null);
    }

    public DailyRaffleTabStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DailyRaffleTabStrip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        addTab(newTab().setText((int) R.string.current));
        addTab(newTab().setText((int) R.string.upcoming));
        addTab(newTab().setText((int) R.string.info));
        for (int i = 0; i < getTabCount(); i++) {
            Tab tabAt = getTabAt(i);
            if (tabAt != null) {
                TextView textView = new TextView(getContext());
                tabAt.setCustomView((View) textView);
                textView.getLayoutParams().width = -2;
                textView.getLayoutParams().height = -2;
                textView.setText(tabAt.getText());
                textView.setTextColor(getContext().getResources().getColor(R.color.gray4));
                textView.setTextSize(0, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.tab_strip_text_size));
                if (i == 0) {
                    textView.setTypeface(null, 1);
                    textView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.gray1));
                }
            }
        }
    }

    public void setup(final DailyRaffleTabSelectedCallback dailyRaffleTabSelectedCallback) {
        setOnTabSelectedListener(new OnTabSelectedListener() {
            public void onTabReselected(Tab tab) {
            }

            public void onTabSelected(Tab tab) {
                if (dailyRaffleTabSelectedCallback != null) {
                    dailyRaffleTabSelectedCallback.onTabSelected(tab.getPosition());
                }
                TextView textView = (TextView) tab.getCustomView();
                if (textView != null) {
                    textView.setTypeface(null, 1);
                    textView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.gray1));
                }
            }

            public void onTabUnselected(Tab tab) {
                TextView textView = (TextView) tab.getCustomView();
                if (textView != null) {
                    textView.setTypeface(null, 0);
                    textView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.gray4));
                }
            }
        });
    }
}
