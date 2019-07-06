package com.etsy.android.vespa.viewholders;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.Banner;
import com.etsy.android.lib.models.apiv3.BannerButton;
import com.etsy.android.lib.models.apiv3.vespa.CardActionableItem;
import com.etsy.android.vespa.a.a;
import java.util.HashMap;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;

/* compiled from: FullBleedBannerViewHolder.kt */
public final class FullBleedBannerViewHolder extends BaseViewHolder<CardActionableItem> {
    private final b analytics;
    /* access modifiers changed from: private */
    public final HashMap<View, Boolean> animationMap;
    /* access modifiers changed from: private */
    public final a mClickListener;
    /* access modifiers changed from: private */
    public final ImageView mImageLeft;
    /* access modifiers changed from: private */
    public final ImageView mImageRight;
    private final TextView mLink;
    private final LinearLayout mTextSection;
    private final TextView mTitle;

    public FullBleedBannerViewHolder(ViewGroup viewGroup, a aVar, b bVar) {
        p.b(viewGroup, ResponseConstants.PARENT);
        p.b(bVar, "analytics");
        super(LayoutInflater.from(viewGroup.getContext()).inflate(k.list_item_card_view_banner_full_bleed, viewGroup, false));
        this.mClickListener = aVar;
        this.analytics = bVar;
        View findViewById = findViewById(i.txt_title);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.mTitle = (TextView) findViewById;
        View findViewById2 = findViewById(i.txt_link);
        if (findViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.mLink = (TextView) findViewById2;
        View findViewById3 = findViewById(i.text_section);
        if (findViewById3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        this.mTextSection = (LinearLayout) findViewById3;
        View findViewById4 = findViewById(i.image_full_bleed_right);
        if (findViewById4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.mImageRight = (ImageView) findViewById4;
        View findViewById5 = findViewById(i.image_full_bleed_left);
        if (findViewById5 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.mImageLeft = (ImageView) findViewById5;
        this.animationMap = new HashMap<>();
    }

    public final b getAnalytics() {
        return this.analytics;
    }

    public void bind(CardActionableItem cardActionableItem) {
        p.b(cardActionableItem, "data");
        super.bind(cardActionableItem);
        Banner banner = (Banner) cardActionableItem.getData();
        if (banner == null) {
            this.mTextSection.setVisibility(8);
            return;
        }
        this.mTextSection.setVisibility(0);
        this.mTextSection.setBackgroundColor(banner.getBackgroundColor());
        this.mTitle.setText(banner.getTitle());
        this.mTitle.setTextColor(banner.getTextColor());
        if (banner.getButtonPrimary() != null) {
            this.mLink.setPaintFlags(8 | this.mLink.getPaintFlags());
            TextView textView = this.mLink;
            BannerButton buttonPrimary = banner.getButtonPrimary();
            textView.setText(buttonPrimary != null ? buttonPrimary.getText() : null);
            this.mLink.setTextColor(banner.getTextColor());
        }
        bindButton(this.mLink, banner.getButtonPrimary());
        animateBubbles();
    }

    private final void animateBubbles() {
        if (this.animationMap.get(this.mImageLeft) == null || p.a((Object) (Boolean) this.animationMap.get(this.mImageLeft), (Object) Boolean.valueOf(false))) {
            this.mImageLeft.getViewTreeObserver().addOnGlobalLayoutListener(new FullBleedBannerViewHolder$animateBubbles$1(this));
        }
        if (this.animationMap.get(this.mImageRight) == null || p.a((Object) (Boolean) this.animationMap.get(this.mImageRight), (Object) Boolean.valueOf(false))) {
            this.mImageRight.getViewTreeObserver().addOnGlobalLayoutListener(new FullBleedBannerViewHolder$animateBubbles$2(this));
        }
    }

    /* access modifiers changed from: private */
    public final void animateImages(View view, boolean z) {
        view.setTranslationX(z ? -1.0f * ((float) view.getWidth()) : (float) view.getWidth());
        view.setAlpha(0.0f);
        view.animate().translationX(0.0f).alpha(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(750).start();
    }

    private final void bindButton(TextView textView, BannerButton bannerButton) {
        String str = null;
        if (!TextUtils.isEmpty(bannerButton != null ? bannerButton.getUrl() : null)) {
            textView.setOnClickListener(new FullBleedBannerViewHolder$bindButton$trackingOnClickListener$1(this, bannerButton));
            textView.setVisibility(0);
            if (bannerButton != null) {
                str = bannerButton.getText();
            }
            textView.setText(str);
            return;
        }
        textView.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public final void trackUserClickedButtonEvent() {
        this.analytics.a("scc_did_tap_tips", null);
    }
}
