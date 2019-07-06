package com.contextlogic.wish.ui.viewpager;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;
import com.contextlogic.wish.util.ViewUtil;

public class WishTabLayout extends TabLayout {
    public WishTabLayout(Context context) {
        super(context);
    }

    public WishTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WishTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnTabSelectedListener(final OnTabSelectedListener onTabSelectedListener) {
        super.setOnTabSelectedListener(new OnTabSelectedListener() {
            public void onTabSelected(Tab tab) {
                if (WishTabLayout.this.getChildCount() != 0 && (WishTabLayout.this.getChildAt(0) instanceof ViewGroup)) {
                    ViewGroup viewGroup = (ViewGroup) WishTabLayout.this.getChildAt(0);
                    if (tab.getPosition() < viewGroup.getChildCount()) {
                        TextView textView = (TextView) ViewUtil.findViewByClassReference(viewGroup.getChildAt(tab.getPosition()), TextView.class);
                        if (textView != null) {
                            textView.setTypeface(null, 1);
                        }
                        if (onTabSelectedListener != null) {
                            onTabSelectedListener.onTabSelected(tab);
                        }
                    }
                }
            }

            public void onTabUnselected(Tab tab) {
                if (WishTabLayout.this.getChildCount() != 0 && (WishTabLayout.this.getChildAt(0) instanceof ViewGroup)) {
                    ViewGroup viewGroup = (ViewGroup) WishTabLayout.this.getChildAt(0);
                    if (tab.getPosition() < viewGroup.getChildCount()) {
                        TextView textView = (TextView) ViewUtil.findViewByClassReference(viewGroup.getChildAt(tab.getPosition()), TextView.class);
                        if (textView != null) {
                            textView.setTypeface(null, 0);
                        }
                        if (onTabSelectedListener != null) {
                            onTabSelectedListener.onTabUnselected(tab);
                        }
                    }
                }
            }

            public void onTabReselected(Tab tab) {
                if (onTabSelectedListener != null) {
                    onTabSelectedListener.onTabReselected(tab);
                }
            }
        });
    }
}
