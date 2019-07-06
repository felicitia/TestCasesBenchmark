package com.etsy.android.vespa.viewholders;

import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.OvershootInterpolator;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.apiv3.Banner;
import com.etsy.android.lib.models.apiv3.vespa.CardActionableItem;
import com.etsy.android.uikit.a.b;
import com.etsy.android.uikit.util.j;
import com.etsy.android.vespa.a.a;

public class IconBannerViewHolder extends BannerViewHolder {
    public IconBannerViewHolder(ViewGroup viewGroup, a aVar, c cVar) {
        super(viewGroup, aVar, cVar, k.list_item_card_view_banner_icon);
    }

    /* access modifiers changed from: protected */
    public void animate(String str) {
        if (Banner.ANIMATION_SLIDE_FROM_LEFT.equals(str)) {
            float f = (float) (-(this.mImage.getWidth() * 2));
            this.mImage.setTranslationX(f);
            b.a((View) this.mImage).a(f, 0.0f).a(400).b(600).a((TimeInterpolator) new OvershootInterpolator(1.0f)).b();
        }
    }

    public void bind(CardActionableItem cardActionableItem) {
        super.bind(cardActionableItem);
        final ViewTreeObserver viewTreeObserver = this.itemView.getViewTreeObserver();
        j.a(viewTreeObserver, (OnGlobalLayoutListener) new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                j.b(viewTreeObserver, (OnGlobalLayoutListener) this);
                if (IconBannerViewHolder.this.mImage.getMeasuredHeight() > IconBannerViewHolder.this.mBgColorArea.getMeasuredHeight()) {
                    LayoutParams layoutParams = IconBannerViewHolder.this.mBgColorArea.getLayoutParams();
                    layoutParams.height = IconBannerViewHolder.this.mImage.getMeasuredHeight();
                    IconBannerViewHolder.this.mBgColorArea.setLayoutParams(layoutParams);
                }
            }
        });
    }
}
