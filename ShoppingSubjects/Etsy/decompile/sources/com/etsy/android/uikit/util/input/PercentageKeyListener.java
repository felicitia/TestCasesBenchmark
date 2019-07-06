package com.etsy.android.uikit.util.input;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.NumberKeyListener;
import org.apache.commons.lang3.ClassUtils;

public class PercentageKeyListener extends NumberKeyListener {
    private static final char[] ACCEPTED = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ClassUtils.PACKAGE_SEPARATOR_CHAR, PERCENT};
    private static final char[] ACCEPTED_NO_DECIMAL = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', PERCENT};
    private static final char PERCENT = '%';
    private boolean mNoDecimal;

    private static boolean isDecimalPointChar(char c) {
        return c == '.';
    }

    private static boolean isPercentageChar(char c) {
        return c == '%';
    }

    public int getInputType() {
        return 8194;
    }

    /* access modifiers changed from: protected */
    public char[] getAcceptedChars() {
        return this.mNoDecimal ? ACCEPTED_NO_DECIMAL : ACCEPTED;
    }

    public void setNoDecimal(boolean z) {
        this.mNoDecimal = z;
    }

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        CharSequence filter = super.filter(charSequence, i, i2, spanned, i3, i4);
        if (filter != null) {
            i2 = filter.length();
            charSequence = filter;
            i = 0;
        }
        int length = spanned.length();
        int i5 = -1;
        for (int i6 = 0; i6 < i3; i6++) {
            char charAt = spanned.charAt(i6);
            if (isDecimalPointChar(charAt)) {
                i5 = i6;
            } else if (isPercentageChar(charAt)) {
                return "";
            }
        }
        int i7 = -1;
        while (i4 < length) {
            char charAt2 = spanned.charAt(i4);
            if (isDecimalPointChar(charAt2)) {
                i5 = i4;
            } else if (isPercentageChar(charAt2)) {
                i7 = i4;
            }
            i4++;
        }
        SpannableStringBuilder spannableStringBuilder = null;
        for (int i8 = i2 - 1; i8 >= i; i8--) {
            char charAt3 = charSequence.charAt(i8);
            boolean z = true;
            if (!isPercentageChar(charAt3)) {
                if (isDecimalPointChar(charAt3)) {
                    if (i5 < 0) {
                        i5 = i8;
                    }
                }
                z = false;
            }
            if (z) {
                if (i2 == i + 1) {
                    return "";
                }
                if (spannableStringBuilder == null) {
                    spannableStringBuilder = new SpannableStringBuilder(charSequence, i, i2);
                }
                spannableStringBuilder.delete(i8 - i, (i8 + 1) - i);
            }
        }
        if (i7 == -1) {
            if (spannableStringBuilder == null) {
                spannableStringBuilder = new SpannableStringBuilder(charSequence, i, i2);
            }
            spannableStringBuilder.append(PERCENT);
        }
        if (spannableStringBuilder != null) {
            return spannableStringBuilder;
        }
        if (filter != null) {
            return filter;
        }
        return null;
    }
}
