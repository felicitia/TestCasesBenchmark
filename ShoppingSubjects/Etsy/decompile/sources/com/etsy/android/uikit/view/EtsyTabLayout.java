package com.etsy.android.uikit.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.o;
import com.etsy.android.stylekit.e;

public class EtsyTabLayout extends TabLayout {
    public EtsyTabLayout(Context context) {
        super(context);
    }

    public EtsyTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EtsyTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void addTab(@NonNull Tab tab, int i, boolean z) {
        super.addTab(tab, i, z);
        styleTabs();
    }

    public void addTab(@NonNull Tab tab) {
        super.addTab(tab);
        styleTabs();
    }

    public void addTab(@NonNull Tab tab, int i) {
        super.addTab(tab, i);
        styleTabs();
    }

    public void addTab(@NonNull Tab tab, boolean z) {
        super.addTab(tab, z);
        styleTabs();
    }

    private void styleTabs() {
        recursiveStyleTabs(this);
    }

    private void recursiveStyleTabs(View view) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            e.a(textView, o.sk_typeface_normal);
            TypedValue typedValue = new TypedValue();
            getResources().getValue(f.sk_text_line_spacing_multiplier, typedValue, true);
            textView.setLineSpacing(0.0f, typedValue.getFloat());
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                recursiveStyleTabs(viewGroup.getChildAt(i));
            }
        }
    }

    public void setTabText(int i, String str) {
        super.getTabAt(i).setText((CharSequence) str);
        styleTabs();
    }
}
