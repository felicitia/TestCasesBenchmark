package com.contextlogic.wish.activity.feed.dailygiveaway;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.view.SlidingTabStrip.TabType;
import com.contextlogic.wish.ui.viewpager.PagerSlidingTabStrip;
import com.contextlogic.wish.util.FontUtil;

public class DailyGiveawayTabStrip extends PagerSlidingTabStrip {
    public void notifyDataSetChanged() {
    }

    public DailyGiveawayTabStrip(Context context) {
        this(context, null);
    }

    public DailyGiveawayTabStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DailyGiveawayTabStrip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        addTextTab(0, WishApplication.getInstance().getResources().getString(R.string.current));
        addTextTab(1, WishApplication.getInstance().getResources().getString(R.string.upcoming));
        addTextTab(2, WishApplication.getInstance().getResources().getString(R.string.info));
        this.tabCount = 3;
        setTabTypes(new TabType[]{TabType.TEXT_TAB, TabType.TEXT_TAB, TabType.TEXT_TAB});
        setUnderlineColorResource(R.color.cool_gray4);
        setUnderlineHeight(WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.divider));
        setIndicatorColorResource(R.color.main_primary);
        setIndicatorHeight(WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.daily_giveaway_tab_underline_height));
        setTextColorResource(R.color.cool_gray4);
        setTextSize(WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.daily_giveaway_tab_title_text_size));
        setAllCaps(false);
        setDividerColorResource(R.color.transparent);
        setTabPaddingLeftRight(WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.sixteen_padding));
        selectTab(0);
    }

    public void selectTab(int i) {
        LinearLayout linearLayout = (LinearLayout) getChildAt(0);
        for (int i2 = 0; i2 < linearLayout.getChildCount(); i2++) {
            TextView textView = (TextView) linearLayout.getChildAt(i2);
            if (i2 == i) {
                textView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
                textView.setTypeface(FontUtil.getTypefaceForStyle(1), 1);
            } else {
                textView.setTextColor(WishApplication.getInstance().getResources().getColor(R.color.cool_gray3));
                textView.setTypeface(FontUtil.getTypefaceForStyle(0), 0);
            }
        }
        this.currentPosition = i;
    }
}
