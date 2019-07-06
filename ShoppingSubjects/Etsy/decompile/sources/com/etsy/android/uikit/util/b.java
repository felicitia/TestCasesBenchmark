package com.etsy.android.uikit.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.DrawableRes;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.etsy.android.iconsy.views.IconSelectorDrawable;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.util.l;
import com.etsy.android.uikit.c;

/* compiled from: BaseOptionsMenuItemHelper */
public abstract class b {

    /* compiled from: BaseOptionsMenuItemHelper */
    public interface a {
        int getMenuId();
    }

    /* renamed from: com.etsy.android.uikit.util.b$b reason: collision with other inner class name */
    /* compiled from: BaseOptionsMenuItemHelper */
    public interface C0114b extends a {
        @DrawableRes
        int getIcon();
    }

    protected static void a(C0114b[] bVarArr, Context context, Menu menu) {
        for (C0114b bVar : bVarArr) {
            MenuItem findItem = menu.findItem(bVar.getMenuId());
            if (findItem != null) {
                findItem.setIcon(c.a(context, bVar.getIcon(), e.sk_gray_70));
            }
        }
    }

    protected static void a(a[] aVarArr, Menu menu) {
        for (a menuId : aVarArr) {
            MenuItem findItem = menu.findItem(menuId.getMenuId());
            if (!(findItem == null || findItem.getTitleCondensed() == null)) {
                findItem.setTitleCondensed(findItem.getTitleCondensed().toString());
            }
        }
    }

    protected static View a(Context context, int i, String str, float f, com.etsy.android.iconsy.a aVar) {
        ColorStateList colorStateList = context.getResources().getColorStateList(i);
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(f.fixed_large);
        int b = l.b(context);
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LayoutParams(-2, -1));
        textView.setGravity(16);
        textView.setTextColor(colorStateList);
        textView.setTextSize(0, f);
        com.etsy.android.stylekit.e.a(textView, o.sk_typeface_normal);
        textView.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        textView.setMinHeight(b);
        textView.setBackgroundResource(g.bg_unbounded_selector);
        textView.setText(str);
        if (aVar != null) {
            com.etsy.android.iconsy.views.IconDrawable.a a2 = com.etsy.android.iconsy.views.IconDrawable.a.a(context.getResources());
            a2.a(aVar);
            a2.a(f);
            IconSelectorDrawable iconSelectorDrawable = new IconSelectorDrawable();
            a2.a(colorStateList.getColorForState(IconSelectorDrawable.DISABLED_STATE_SET, colorStateList.getDefaultColor()));
            iconSelectorDrawable.setDisabledState(a2.a());
            a2.a(colorStateList.getDefaultColor());
            iconSelectorDrawable.setBaseState(a2.a());
            textView.setCompoundDrawablesWithIntrinsicBounds(iconSelectorDrawable, null, null, null);
            textView.setCompoundDrawablePadding(context.getResources().getDimensionPixelSize(f.padding_tiny));
        }
        return textView;
    }
}
