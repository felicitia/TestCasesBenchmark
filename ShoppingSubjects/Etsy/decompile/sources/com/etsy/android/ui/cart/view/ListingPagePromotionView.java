package com.etsy.android.ui.cart.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.messaging.c;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;

public class ListingPagePromotionView extends LinearLayout {
    private TextView mDisclaimerText;
    /* access modifiers changed from: private */
    public TextView mTextDescription;
    private TextView mTextDisclaimer;
    private String shopLinkFormat = "<a href='https://www.etsy.com/shop/%s'>%s</a>";

    public ListingPagePromotionView(Context context) {
        super(context);
        init();
    }

    public ListingPagePromotionView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ListingPagePromotionView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @TargetApi(21)
    public ListingPagePromotionView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_listing_page_promotion, this).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
            }
        });
        this.mTextDescription = (TextView) findViewById(R.id.text_description);
        this.mTextDisclaimer = (TextView) findViewById(R.id.text_disclaimer);
    }

    public void bind(@NonNull String str, String str2) {
        this.mTextDescription.setText(Html.fromHtml(str));
        URLSpan[] urls = this.mTextDescription.getUrls();
        if (urls.length > 0) {
            final String url = urls[0].getURL();
            EtsyLinkify.a(this.mTextDescription, true, false, (OnClickListener) new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    if (c.a(Uri.parse(url)) != null) {
                        intent.setClass(ListingPagePromotionView.this.mTextDescription.getContext(), EtsyApplication.get().getDeepLinkRoutingActivity());
                    }
                    intent.setData(Uri.parse(url));
                    ListingPagePromotionView.this.mTextDescription.getContext().startActivity(intent);
                }
            });
        }
        if (!TextUtils.isEmpty(str2)) {
            this.mTextDisclaimer.setVisibility(0);
            this.mTextDisclaimer.setText(str2);
            return;
        }
        this.mTextDisclaimer.setVisibility(8);
    }
}
