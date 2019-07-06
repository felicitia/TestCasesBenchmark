package com.etsy.android.vespa.viewholders;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.lib.a;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.models.apiv3.cart.HTMLText;
import com.etsy.android.stylekit.e;
import com.etsy.android.uikit.util.EtsyLinkify;

public class HTMLTextViewHolder<T extends HTMLText> extends BaseViewHolder<T> {
    private boolean mStyleDiscreetLinks;
    private TextView mText = ((TextView) findViewById(i.html_text));

    private class CustomLinkSpan extends URLSpan {
        public CustomLinkSpan(String str) {
            super(str);
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            Context context = HTMLTextViewHolder.this.getRootView().getContext();
            TextPaint textPaint2 = (TextPaint) e.a(context, textPaint, o.sk_typeface_bold);
            textPaint2.setColor(ContextCompat.getColor(context, a.e.grey));
            textPaint2.setUnderlineText(false);
        }
    }

    public HTMLTextViewHolder(ViewGroup viewGroup) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(k.list_item_html_text, viewGroup, false));
    }

    public HTMLTextViewHolder(ViewGroup viewGroup, int i, boolean z) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
        this.mStyleDiscreetLinks = z;
    }

    public void bind(T t) {
        Spanned fromHtml = fromHtml(t.getText());
        if (this.mStyleDiscreetLinks) {
            this.mText.setText(styleURLs(fromHtml));
        } else {
            this.mText.setText(fromHtml);
        }
        this.mText.setMovementMethod(LinkMovementMethod.getInstance());
        this.mText.setGravity(t.getGravity());
        EtsyLinkify.a(getRootView().getContext(), this.mText);
    }

    private Spanned fromHtml(String str) {
        if (VERSION.SDK_INT >= 24) {
            return Html.fromHtml(str, 63);
        }
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(Html.fromHtml(str));
        for (int i = 0; i < valueOf.length() - 1; i++) {
            if (valueOf.charAt(i) == 10) {
                int i2 = i + 1;
                if (valueOf.charAt(i2) == 10) {
                    valueOf.replace(i2, i + 2, "");
                }
            }
        }
        return valueOf;
    }

    private Spannable styleURLs(Spanned spanned) {
        URLSpan[] uRLSpanArr;
        SpannableString spannableString = new SpannableString(spanned);
        for (URLSpan uRLSpan : (URLSpan[]) spannableString.getSpans(0, spannableString.length(), URLSpan.class)) {
            int spanStart = spannableString.getSpanStart(uRLSpan);
            int spanEnd = spannableString.getSpanEnd(uRLSpan);
            spannableString.removeSpan(uRLSpan);
            spannableString.setSpan(new CustomLinkSpan(uRLSpan.getURL()), spanStart, spanEnd, 0);
        }
        return spannableString;
    }
}
