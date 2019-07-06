package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import com.etsy.android.lib.models.apiv3.FacetCount;
import com.etsy.android.lib.util.fonts.StandardFontIcon;
import com.etsy.android.uikit.text.typeface.a;
import java.util.List;

/* compiled from: SearchFacetCountUtils */
public final class b {
    public static CharSequence a(Context context, String str, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(StandardFontIcon.NAVIGATE_RIGHT.toString());
        sb.append(" ");
        String sb2 = sb.toString();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String[] split = str.split("\\|");
        int length = split.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 > 0) {
                SpannableString a = a.a().a(context, StandardFontIcon.getTypefaceName(), sb2);
                a.setSpan(new RelativeSizeSpan(0.6f), 0, sb2.length(), 33);
                a.setSpan(new ForegroundColorSpan(i), 0, sb2.length(), 33);
                spannableStringBuilder.append(a);
            }
            spannableStringBuilder.append(split[i2]);
        }
        return spannableStringBuilder;
    }

    public static String a(List<FacetCount> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append("|");
            }
            sb.append(((FacetCount) list.get(i)).getName());
        }
        return sb.toString();
    }
}
