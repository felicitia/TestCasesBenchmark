package com.etsy.android.uikit.navigationview;

import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.widget.ListView;

public class EtsyNavigationMenuView extends ListView implements MenuView {
    public int getWindowAnimations() {
        return 0;
    }

    public void initialize(MenuBuilder menuBuilder) {
    }

    public EtsyNavigationMenuView(Context context) {
        this(context, null);
    }

    public EtsyNavigationMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EtsyNavigationMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
