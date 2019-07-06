package com.etsy.android.uikit.text;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.models.ResponseConstants;
import kotlin.jvm.internal.p;

/* compiled from: MultilineStyler.kt */
public final class a {

    /* renamed from: com.etsy.android.uikit.text.a$a reason: collision with other inner class name */
    /* compiled from: MultilineStyler.kt */
    public static final class C0111a {
        private String a;
        private String b;
        private String c;
        @ColorRes
        private int d = e.sk_text_gray_lightest;
        private int e = e.sk_text_gray_lightest;
        private float f = 0.8f;
        private float g = 0.7f;

        public final C0111a a(String str) {
            p.b(str, "text");
            this.a = str;
            return this;
        }

        public final C0111a b(String str) {
            p.b(str, "text");
            this.b = str;
            return this;
        }

        public final SpannableStringBuilder a(Context context) {
            p.b(context, ResponseConstants.CONTEXT);
            int color = ContextCompat.getColor(context, this.d);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append(this.a);
            spannableStringBuilder.append("\n");
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append(this.b);
            int length2 = spannableStringBuilder.length();
            spannableStringBuilder.setSpan(new ForegroundColorSpan(color), length, length2, 33);
            spannableStringBuilder.setSpan(new RelativeSizeSpan(this.f), length, length2, 33);
            if (this.c != null) {
                spannableStringBuilder.append("\n");
                int length3 = spannableStringBuilder.length();
                int color2 = ContextCompat.getColor(context, this.e);
                spannableStringBuilder.append(this.c);
                int length4 = spannableStringBuilder.length();
                spannableStringBuilder.setSpan(new ForegroundColorSpan(color2), length3, length4, 33);
                spannableStringBuilder.setSpan(new RelativeSizeSpan(this.g), length3, length4, 33);
            }
            return spannableStringBuilder;
        }
    }
}
