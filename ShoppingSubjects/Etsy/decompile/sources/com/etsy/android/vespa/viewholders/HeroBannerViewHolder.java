package com.etsy.android.vespa.viewholders;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.util.l;
import com.etsy.android.vespa.a.a;

public class HeroBannerViewHolder extends BannerViewHolder {
    /* access modifiers changed from: protected */
    public void animate(String str) {
    }

    public HeroBannerViewHolder(ViewGroup viewGroup, a aVar, c cVar) {
        super(viewGroup, aVar, cVar, getLayoutRes(viewGroup));
    }

    @LayoutRes
    private static int getLayoutRes(View view) {
        return (l.b(view) && l.f(view)) || (l.c(view) && l.e(view)) ? k.list_item_card_view_banner_hero_wide : k.list_item_card_view_banner_hero;
    }
}
