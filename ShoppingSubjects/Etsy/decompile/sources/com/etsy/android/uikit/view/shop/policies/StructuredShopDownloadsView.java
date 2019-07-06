package com.etsy.android.uikit.view.shop.policies;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.config.a;
import com.etsy.android.lib.config.b.l;
import com.etsy.android.uikit.util.EtsyLinkify;

public class StructuredShopDownloadsView extends StructuredShopPoliciesView {
    public StructuredShopDownloadsView(Context context) {
        super(context);
    }

    public StructuredShopDownloadsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StructuredShopDownloadsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public StructuredShopDownloadsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, LinearLayout linearLayout) {
        inflate(context, k.view_structured_shop_downloads, linearLayout);
        TextView textView = (TextView) findViewById(i.txt_downloads_info);
        String b = a.a().d().b(l.a.a);
        textView.setText(EtsyLinkify.a(context, Html.fromHtml(getResources().getString(o.structured_shipping_digital_message, new Object[]{b})), true, true, (OnClickListener) null));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
